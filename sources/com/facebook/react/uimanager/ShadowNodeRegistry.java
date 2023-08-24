package com.facebook.react.uimanager;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.facebook.react.common.SingleThreadAsserter;

/* loaded from: classes.dex */
public class ShadowNodeRegistry {
    private final SparseArray<ReactShadowNode> mTagsToCSSNodes = new SparseArray<>();
    private final SparseBooleanArray mRootTags = new SparseBooleanArray();
    private final SingleThreadAsserter mThreadAsserter = new SingleThreadAsserter();

    public void addRootNode(ReactShadowNode reactShadowNode) {
        this.mThreadAsserter.assertNow();
        int reactTag = reactShadowNode.getReactTag();
        this.mTagsToCSSNodes.put(reactTag, reactShadowNode);
        this.mRootTags.put(reactTag, true);
    }

    public void removeRootNode(int r4) {
        this.mThreadAsserter.assertNow();
        if (r4 == -1) {
            return;
        }
        if (!this.mRootTags.get(r4)) {
            throw new IllegalViewOperationException("View with tag " + r4 + " is not registered as a root view");
        }
        this.mTagsToCSSNodes.remove(r4);
        this.mRootTags.delete(r4);
    }

    public void addNode(ReactShadowNode reactShadowNode) {
        this.mThreadAsserter.assertNow();
        this.mTagsToCSSNodes.put(reactShadowNode.getReactTag(), reactShadowNode);
    }

    public void removeNode(int r4) {
        this.mThreadAsserter.assertNow();
        if (this.mRootTags.get(r4)) {
            throw new IllegalViewOperationException("Trying to remove root node " + r4 + " without using removeRootNode!");
        }
        this.mTagsToCSSNodes.remove(r4);
    }

    public ReactShadowNode getNode(int r2) {
        this.mThreadAsserter.assertNow();
        return this.mTagsToCSSNodes.get(r2);
    }

    public boolean isRootNode(int r2) {
        this.mThreadAsserter.assertNow();
        return this.mRootTags.get(r2);
    }

    public int getRootNodeCount() {
        this.mThreadAsserter.assertNow();
        return this.mRootTags.size();
    }

    public int getRootTag(int r2) {
        this.mThreadAsserter.assertNow();
        return this.mRootTags.keyAt(r2);
    }
}
