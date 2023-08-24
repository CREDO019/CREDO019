package com.facebook.react.uimanager;

import android.util.SparseBooleanArray;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMapKeySetIterator;

/* loaded from: classes.dex */
public class NativeViewHierarchyOptimizer {
    private static final boolean ENABLED = true;
    private static final String TAG = "NativeViewHierarchyOptimizer";
    private final ShadowNodeRegistry mShadowNodeRegistry;
    private final SparseBooleanArray mTagsWithLayoutVisited = new SparseBooleanArray();
    private final UIViewOperationQueue mUIViewOperationQueue;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class NodeIndexPair {
        public final int index;
        public final ReactShadowNode node;

        NodeIndexPair(ReactShadowNode reactShadowNode, int r2) {
            this.node = reactShadowNode;
            this.index = r2;
        }
    }

    public static void assertNodeSupportedWithoutOptimizer(ReactShadowNode reactShadowNode) {
        Assertions.assertCondition(reactShadowNode.getNativeKind() != NativeKind.LEAF, "Nodes with NativeKind.LEAF are not supported when the optimizer is disabled");
    }

    public NativeViewHierarchyOptimizer(UIViewOperationQueue uIViewOperationQueue, ShadowNodeRegistry shadowNodeRegistry) {
        this.mUIViewOperationQueue = uIViewOperationQueue;
        this.mShadowNodeRegistry = shadowNodeRegistry;
    }

    public void handleCreateView(ReactShadowNode reactShadowNode, ThemedReactContext themedReactContext, ReactStylesDiffMap reactStylesDiffMap) {
        reactShadowNode.setIsLayoutOnly(reactShadowNode.getViewClass().equals("RCTView") && isLayoutOnlyAndCollapsable(reactStylesDiffMap));
        if (reactShadowNode.getNativeKind() != NativeKind.NONE) {
            this.mUIViewOperationQueue.enqueueCreateView(themedReactContext, reactShadowNode.getReactTag(), reactShadowNode.getViewClass(), reactStylesDiffMap);
        }
    }

    public static void handleRemoveNode(ReactShadowNode reactShadowNode) {
        reactShadowNode.removeAllNativeChildren();
    }

    public void handleUpdateView(ReactShadowNode reactShadowNode, String str, ReactStylesDiffMap reactStylesDiffMap) {
        if (reactShadowNode.isLayoutOnly() && !isLayoutOnlyAndCollapsable(reactStylesDiffMap)) {
            transitionLayoutOnlyViewToNativeView(reactShadowNode, reactStylesDiffMap);
        } else if (reactShadowNode.isLayoutOnly()) {
        } else {
            this.mUIViewOperationQueue.enqueueUpdateProperties(reactShadowNode.getReactTag(), str, reactStylesDiffMap);
        }
    }

    public void handleManageChildren(ReactShadowNode reactShadowNode, int[] r6, int[] r7, ViewAtIndex[] viewAtIndexArr, int[] r9) {
        boolean z;
        for (int r1 : r7) {
            int r2 = 0;
            while (true) {
                if (r2 >= r9.length) {
                    z = false;
                    break;
                } else if (r9[r2] == r1) {
                    z = true;
                    break;
                } else {
                    r2++;
                }
            }
            removeNodeFromParent(this.mShadowNodeRegistry.getNode(r1), z);
        }
        for (ViewAtIndex viewAtIndex : viewAtIndexArr) {
            addNodeToNode(reactShadowNode, this.mShadowNodeRegistry.getNode(viewAtIndex.mTag), viewAtIndex.mIndex);
        }
    }

    public void handleSetChildren(ReactShadowNode reactShadowNode, ReadableArray readableArray) {
        for (int r0 = 0; r0 < readableArray.size(); r0++) {
            addNodeToNode(reactShadowNode, this.mShadowNodeRegistry.getNode(readableArray.getInt(r0)), r0);
        }
    }

    public void handleUpdateLayout(ReactShadowNode reactShadowNode) {
        applyLayoutBase(reactShadowNode);
    }

    public void handleForceViewToBeNonLayoutOnly(ReactShadowNode reactShadowNode) {
        if (reactShadowNode.isLayoutOnly()) {
            transitionLayoutOnlyViewToNativeView(reactShadowNode, null);
        }
    }

    public void onBatchComplete() {
        this.mTagsWithLayoutVisited.clear();
    }

    private NodeIndexPair walkUpUntilNativeKindIsParent(ReactShadowNode reactShadowNode, int r5) {
        while (reactShadowNode.getNativeKind() != NativeKind.PARENT) {
            ReactShadowNode parent = reactShadowNode.getParent();
            if (parent == null) {
                return null;
            }
            r5 = r5 + (reactShadowNode.getNativeKind() == NativeKind.LEAF ? 1 : 0) + parent.getNativeOffsetForChild(reactShadowNode);
            reactShadowNode = parent;
        }
        return new NodeIndexPair(reactShadowNode, r5);
    }

    private void addNodeToNode(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int r6) {
        int nativeOffsetForChild = reactShadowNode.getNativeOffsetForChild(reactShadowNode.getChildAt(r6));
        if (reactShadowNode.getNativeKind() != NativeKind.PARENT) {
            NodeIndexPair walkUpUntilNativeKindIsParent = walkUpUntilNativeKindIsParent(reactShadowNode, nativeOffsetForChild);
            if (walkUpUntilNativeKindIsParent == null) {
                return;
            }
            ReactShadowNode reactShadowNode3 = walkUpUntilNativeKindIsParent.node;
            nativeOffsetForChild = walkUpUntilNativeKindIsParent.index;
            reactShadowNode = reactShadowNode3;
        }
        if (reactShadowNode2.getNativeKind() != NativeKind.NONE) {
            addNativeChild(reactShadowNode, reactShadowNode2, nativeOffsetForChild);
        } else {
            addNonNativeChild(reactShadowNode, reactShadowNode2, nativeOffsetForChild);
        }
    }

    private void removeNodeFromParent(ReactShadowNode reactShadowNode, boolean z) {
        if (reactShadowNode.getNativeKind() != NativeKind.PARENT) {
            for (int childCount = reactShadowNode.getChildCount() - 1; childCount >= 0; childCount--) {
                removeNodeFromParent(reactShadowNode.getChildAt(childCount), z);
            }
        }
        ReactShadowNode nativeParent = reactShadowNode.getNativeParent();
        if (nativeParent != null) {
            int indexOfNativeChild = nativeParent.indexOfNativeChild(reactShadowNode);
            nativeParent.removeNativeChildAt(indexOfNativeChild);
            this.mUIViewOperationQueue.enqueueManageChildren(nativeParent.getReactTag(), new int[]{indexOfNativeChild}, null, z ? new int[]{reactShadowNode.getReactTag()} : null);
        }
    }

    private void addNonNativeChild(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int r3) {
        addGrandchildren(reactShadowNode, reactShadowNode2, r3);
    }

    private void addNativeChild(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int r9) {
        reactShadowNode.addNativeChildAt(reactShadowNode2, r9);
        this.mUIViewOperationQueue.enqueueManageChildren(reactShadowNode.getReactTag(), null, new ViewAtIndex[]{new ViewAtIndex(reactShadowNode2.getReactTag(), r9)}, null);
        if (reactShadowNode2.getNativeKind() != NativeKind.PARENT) {
            addGrandchildren(reactShadowNode, reactShadowNode2, r9 + 1);
        }
    }

    private void addGrandchildren(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int r10) {
        Assertions.assertCondition(reactShadowNode2.getNativeKind() != NativeKind.PARENT);
        for (int r0 = 0; r0 < reactShadowNode2.getChildCount(); r0++) {
            ReactShadowNode childAt = reactShadowNode2.getChildAt(r0);
            Assertions.assertCondition(childAt.getNativeParent() == null);
            int nativeChildCount = reactShadowNode.getNativeChildCount();
            if (childAt.getNativeKind() == NativeKind.NONE) {
                addNonNativeChild(reactShadowNode, childAt, r10);
            } else {
                addNativeChild(reactShadowNode, childAt, r10);
            }
            r10 += reactShadowNode.getNativeChildCount() - nativeChildCount;
        }
    }

    private void applyLayoutBase(ReactShadowNode reactShadowNode) {
        int reactTag = reactShadowNode.getReactTag();
        if (this.mTagsWithLayoutVisited.get(reactTag)) {
            return;
        }
        this.mTagsWithLayoutVisited.put(reactTag, true);
        int screenX = reactShadowNode.getScreenX();
        int screenY = reactShadowNode.getScreenY();
        for (ReactShadowNode parent = reactShadowNode.getParent(); parent != null && parent.getNativeKind() != NativeKind.PARENT; parent = parent.getParent()) {
            if (!parent.isVirtual()) {
                screenX += Math.round(parent.getLayoutX());
                screenY += Math.round(parent.getLayoutY());
            }
        }
        applyLayoutRecursive(reactShadowNode, screenX, screenY);
    }

    private void applyLayoutRecursive(ReactShadowNode reactShadowNode, int r10, int r11) {
        if (reactShadowNode.getNativeKind() != NativeKind.NONE && reactShadowNode.getNativeParent() != null) {
            this.mUIViewOperationQueue.enqueueUpdateLayout(reactShadowNode.getLayoutParent().getReactTag(), reactShadowNode.getReactTag(), r10, r11, reactShadowNode.getScreenWidth(), reactShadowNode.getScreenHeight());
            return;
        }
        for (int r0 = 0; r0 < reactShadowNode.getChildCount(); r0++) {
            ReactShadowNode childAt = reactShadowNode.getChildAt(r0);
            int reactTag = childAt.getReactTag();
            if (!this.mTagsWithLayoutVisited.get(reactTag)) {
                this.mTagsWithLayoutVisited.put(reactTag, true);
                applyLayoutRecursive(childAt, childAt.getScreenX() + r10, childAt.getScreenY() + r11);
            }
        }
    }

    private void transitionLayoutOnlyViewToNativeView(ReactShadowNode reactShadowNode, ReactStylesDiffMap reactStylesDiffMap) {
        ReactShadowNode parent = reactShadowNode.getParent();
        if (parent == null) {
            reactShadowNode.setIsLayoutOnly(false);
            return;
        }
        int indexOf = parent.indexOf(reactShadowNode);
        parent.removeChildAt(indexOf);
        removeNodeFromParent(reactShadowNode, false);
        reactShadowNode.setIsLayoutOnly(false);
        this.mUIViewOperationQueue.enqueueCreateView(reactShadowNode.getThemedContext(), reactShadowNode.getReactTag(), reactShadowNode.getViewClass(), reactStylesDiffMap);
        parent.addChildAt(reactShadowNode, indexOf);
        addNodeToNode(parent, reactShadowNode, indexOf);
        for (int r0 = 0; r0 < reactShadowNode.getChildCount(); r0++) {
            addNodeToNode(reactShadowNode, reactShadowNode.getChildAt(r0), r0);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Transitioning LayoutOnlyView - tag: ");
        sb.append(reactShadowNode.getReactTag());
        sb.append(" - rootTag: ");
        sb.append(reactShadowNode.getRootTag());
        sb.append(" - hasProps: ");
        sb.append(reactStylesDiffMap != null);
        sb.append(" - tagsWithLayout.size: ");
        sb.append(this.mTagsWithLayoutVisited.size());
        FLog.m1316i(TAG, sb.toString());
        Assertions.assertCondition(this.mTagsWithLayoutVisited.size() == 0);
        applyLayoutBase(reactShadowNode);
        for (int r1 = 0; r1 < reactShadowNode.getChildCount(); r1++) {
            applyLayoutBase(reactShadowNode.getChildAt(r1));
        }
        this.mTagsWithLayoutVisited.clear();
    }

    private static boolean isLayoutOnlyAndCollapsable(ReactStylesDiffMap reactStylesDiffMap) {
        if (reactStylesDiffMap == null) {
            return true;
        }
        if (!reactStylesDiffMap.hasKey(ViewProps.COLLAPSABLE) || reactStylesDiffMap.getBoolean(ViewProps.COLLAPSABLE, true)) {
            ReadableMapKeySetIterator keySetIterator = reactStylesDiffMap.mBackingMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                if (!ViewProps.isLayoutOnly(reactStylesDiffMap.mBackingMap, keySetIterator.nextKey())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onViewUpdatesCompleted(ReactShadowNode reactShadowNode) {
        this.mTagsWithLayoutVisited.clear();
    }
}
