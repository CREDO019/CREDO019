package com.facebook.react.uimanager;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.PopupMenu;
import com.facebook.common.logging.FLog;
import com.facebook.react.C1413R;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationController;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class NativeViewHierarchyManager {
    private static final String TAG = "NativeViewHierarchyManager";
    private final boolean DEBUG_MODE;
    private final RectF mBoundingBox;
    private final JSResponderHandler mJSResponderHandler;
    private boolean mLayoutAnimationEnabled;
    private final LayoutAnimationController mLayoutAnimator;
    private HashMap<Integer, Set<Integer>> mPendingDeletionsForTag;
    private PopupMenu mPopupMenu;
    private final SparseBooleanArray mRootTags;
    private final RootViewManager mRootViewManager;
    private final SparseArray<ViewManager> mTagsToViewManagers;
    private final SparseArray<View> mTagsToViews;
    private final ViewManagerRegistry mViewManagers;

    public NativeViewHierarchyManager(ViewManagerRegistry viewManagerRegistry) {
        this(viewManagerRegistry, new RootViewManager());
    }

    public NativeViewHierarchyManager(ViewManagerRegistry viewManagerRegistry, RootViewManager rootViewManager) {
        this.DEBUG_MODE = false;
        this.mJSResponderHandler = new JSResponderHandler();
        this.mLayoutAnimator = new LayoutAnimationController();
        this.mBoundingBox = new RectF();
        this.mViewManagers = viewManagerRegistry;
        this.mTagsToViews = new SparseArray<>();
        this.mTagsToViewManagers = new SparseArray<>();
        this.mRootTags = new SparseBooleanArray();
        this.mRootViewManager = rootViewManager;
    }

    public final synchronized View resolveView(int r4) {
        View view;
        view = this.mTagsToViews.get(r4);
        if (view == null) {
            throw new IllegalViewOperationException("Trying to resolve view with tag " + r4 + " which doesn't exist");
        }
        return view;
    }

    public final synchronized ViewManager resolveViewManager(int r4) {
        ViewManager viewManager;
        viewManager = this.mTagsToViewManagers.get(r4);
        if (viewManager == null) {
            throw new IllegalViewOperationException("ViewManager for tag " + r4 + " could not be found.\n");
        }
        return viewManager;
    }

    public void setLayoutAnimationEnabled(boolean z) {
        this.mLayoutAnimationEnabled = z;
    }

    public synchronized void updateInstanceHandle(int r3, long j) {
        UiThreadUtil.assertOnUiThread();
        try {
            updateInstanceHandle(resolveView(r3), j);
        } catch (IllegalViewOperationException e) {
            String str = TAG;
            FLog.m1327e(str, "Unable to update properties for view tag " + r3, e);
        }
    }

    public synchronized void updateProperties(int r4, ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        try {
            ViewManager resolveViewManager = resolveViewManager(r4);
            View resolveView = resolveView(r4);
            if (reactStylesDiffMap != null) {
                resolveViewManager.updateProperties(resolveView, reactStylesDiffMap);
            }
        } catch (IllegalViewOperationException e) {
            String str = TAG;
            FLog.m1327e(str, "Unable to update properties for view tag " + r4, e);
        }
    }

    public synchronized void updateViewExtraData(int r2, Object obj) {
        UiThreadUtil.assertOnUiThread();
        resolveViewManager(r2).updateExtraData(resolveView(r2), obj);
    }

    public synchronized void updateLayout(int r10, int r11, int r12, int r13, int r14, int r15) {
        UiThreadUtil.assertOnUiThread();
        SystraceMessage.beginSection(0L, "NativeViewHierarchyManager_updateLayout").arg("parentTag", r10).arg("tag", r11).flush();
        View resolveView = resolveView(r11);
        resolveView.measure(View.MeasureSpec.makeMeasureSpec(r14, 1073741824), View.MeasureSpec.makeMeasureSpec(r15, 1073741824));
        ViewParent parent = resolveView.getParent();
        if (parent instanceof RootView) {
            parent.requestLayout();
        }
        if (!this.mRootTags.get(r10)) {
            ViewManager viewManager = this.mTagsToViewManagers.get(r10);
            if (viewManager instanceof IViewManagerWithChildren) {
                IViewManagerWithChildren iViewManagerWithChildren = (IViewManagerWithChildren) viewManager;
                if (iViewManagerWithChildren != null && !iViewManagerWithChildren.needsCustomLayoutForChildren()) {
                    updateLayout(resolveView, r12, r13, r14, r15);
                }
            } else {
                throw new IllegalViewOperationException("Trying to use view with tag " + r10 + " as a parent, but its Manager doesn't implement IViewManagerWithChildren");
            }
        } else {
            updateLayout(resolveView, r12, r13, r14, r15);
        }
        Systrace.endSection(0L);
    }

    private void updateInstanceHandle(View view, long j) {
        UiThreadUtil.assertOnUiThread();
        view.setTag(C1413R.C1417id.view_tag_instance_handle, Long.valueOf(j));
    }

    public long getInstanceHandle(int r4) {
        View view = this.mTagsToViews.get(r4);
        if (view == null) {
            throw new IllegalViewOperationException("Unable to find view for tag: " + r4);
        }
        Long l = (Long) view.getTag(C1413R.C1417id.view_tag_instance_handle);
        if (l == null) {
            throw new IllegalViewOperationException("Unable to find instanceHandle for tag: " + r4);
        }
        return l.longValue();
    }

    private void updateLayout(View view, int r9, int r10, int r11, int r12) {
        if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(view)) {
            this.mLayoutAnimator.applyLayoutUpdate(view, r9, r10, r11, r12);
        } else {
            view.layout(r9, r10, r11 + r9, r12 + r10);
        }
    }

    public synchronized void createView(ThemedReactContext themedReactContext, int r11, String str, ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        SystraceMessage.beginSection(0L, "NativeViewHierarchyManager_createView").arg("tag", r11).arg("className", str).flush();
        ViewManager viewManager = this.mViewManagers.get(str);
        this.mTagsToViews.put(r11, viewManager.createView(r11, themedReactContext, reactStylesDiffMap, null, this.mJSResponderHandler));
        this.mTagsToViewManagers.put(r11, viewManager);
        Systrace.endSection(0L);
    }

    private static String constructManageChildrenErrorMessage(ViewGroup viewGroup, ViewGroupManager viewGroupManager, int[] r13, ViewAtIndex[] viewAtIndexArr, int[] r15) {
        StringBuilder sb = new StringBuilder();
        if (viewGroup != null) {
            sb.append("View tag:" + viewGroup.getId() + " View Type:" + viewGroup.getClass().toString() + "\n");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  children(");
            sb2.append(viewGroupManager.getChildCount(viewGroup));
            sb2.append("): [\n");
            sb.append(sb2.toString());
            for (int r7 = 0; viewGroupManager.getChildAt(viewGroup, r7) != null; r7 += 16) {
                int r8 = 0;
                while (true) {
                    int r9 = r7 + r8;
                    if (viewGroupManager.getChildAt(viewGroup, r9) != null && r8 < 16) {
                        sb.append(viewGroupManager.getChildAt(viewGroup, r9).getId() + ",");
                        r8++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (r13 != null) {
            sb.append("  indicesToRemove(" + r13.length + "): [\n");
            for (int r11 = 0; r11 < r13.length; r11 += 16) {
                int r12 = 0;
                while (true) {
                    int r72 = r11 + r12;
                    if (r72 < r13.length && r12 < 16) {
                        sb.append(r13[r72] + ",");
                        r12++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (viewAtIndexArr != null) {
            sb.append("  viewsToAdd(" + viewAtIndexArr.length + "): [\n");
            for (int r112 = 0; r112 < viewAtIndexArr.length; r112 += 16) {
                int r122 = 0;
                while (true) {
                    int r132 = r112 + r122;
                    if (r132 < viewAtIndexArr.length && r122 < 16) {
                        sb.append("[" + viewAtIndexArr[r132].mIndex + "," + viewAtIndexArr[r132].mTag + "],");
                        r122++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (r15 != null) {
            sb.append("  tagsToDelete(" + r15.length + "): [\n");
            for (int r113 = 0; r113 < r15.length; r113 += 16) {
                int r123 = 0;
                while (true) {
                    int r133 = r113 + r123;
                    if (r133 < r15.length && r123 < 16) {
                        sb.append(r15[r133] + ",");
                        r123++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }

    private Set<Integer> getPendingDeletionsForTag(int r4) {
        if (this.mPendingDeletionsForTag == null) {
            this.mPendingDeletionsForTag = new HashMap<>();
        }
        if (!this.mPendingDeletionsForTag.containsKey(Integer.valueOf(r4))) {
            this.mPendingDeletionsForTag.put(Integer.valueOf(r4), new HashSet());
        }
        return this.mPendingDeletionsForTag.get(Integer.valueOf(r4));
    }

    public synchronized void manageChildren(final int r18, int[] r19, ViewAtIndex[] viewAtIndexArr, int[] r21) {
        int r16;
        int[] r9 = r19;
        synchronized (this) {
            UiThreadUtil.assertOnUiThread();
            final Set<Integer> pendingDeletionsForTag = getPendingDeletionsForTag(r18);
            final ViewGroup viewGroup = (ViewGroup) this.mTagsToViews.get(r18);
            final ViewGroupManager viewGroupManager = (ViewGroupManager) resolveViewManager(r18);
            if (viewGroup == null) {
                throw new IllegalViewOperationException("Trying to manageChildren view with tag " + r18 + " which doesn't exist\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, r9, viewAtIndexArr, r21));
            }
            int childCount = viewGroupManager.getChildCount(viewGroup);
            if (r9 != null) {
                int length = r9.length - 1;
                while (length >= 0) {
                    int r3 = r9[length];
                    if (r3 < 0) {
                        throw new IllegalViewOperationException("Trying to remove a negative view index:" + r3 + " view tag: " + r18 + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, r9, viewAtIndexArr, r21));
                    } else if (viewGroupManager.getChildAt(viewGroup, r3) == null) {
                        if (this.mRootTags.get(r18) && viewGroupManager.getChildCount(viewGroup) == 0) {
                            return;
                        }
                        throw new IllegalViewOperationException("Trying to remove a view index above child count " + r3 + " view tag: " + r18 + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, r9, viewAtIndexArr, r21));
                    } else if (r3 >= childCount) {
                        throw new IllegalViewOperationException("Trying to remove an out of order view index:" + r3 + " view tag: " + r18 + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, r9, viewAtIndexArr, r21));
                    } else {
                        View childAt = viewGroupManager.getChildAt(viewGroup, r3);
                        if (!this.mLayoutAnimationEnabled || !this.mLayoutAnimator.shouldAnimateLayout(childAt) || !arrayContains(r21, childAt.getId())) {
                            viewGroupManager.removeViewAt(viewGroup, r3);
                        }
                        length--;
                        childCount = r3;
                    }
                }
            }
            if (r21 != null) {
                int r7 = 0;
                while (r7 < r21.length) {
                    int r1 = r21[r7];
                    final View view = this.mTagsToViews.get(r1);
                    if (view == null) {
                        throw new IllegalViewOperationException("Trying to destroy unknown view tag: " + r1 + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, r19, viewAtIndexArr, r21));
                    }
                    if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(view)) {
                        pendingDeletionsForTag.add(Integer.valueOf(r1));
                        r16 = r7;
                        this.mLayoutAnimator.deleteView(view, new LayoutAnimationListener() { // from class: com.facebook.react.uimanager.NativeViewHierarchyManager.1
                            @Override // com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener
                            public void onAnimationEnd() {
                                UiThreadUtil.assertOnUiThread();
                                viewGroupManager.removeView(viewGroup, view);
                                NativeViewHierarchyManager.this.dropView(view);
                                pendingDeletionsForTag.remove(Integer.valueOf(view.getId()));
                                if (pendingDeletionsForTag.isEmpty()) {
                                    NativeViewHierarchyManager.this.mPendingDeletionsForTag.remove(Integer.valueOf(r18));
                                }
                            }
                        });
                    } else {
                        r16 = r7;
                        dropView(view);
                    }
                    r7 = r16 + 1;
                    r9 = r19;
                }
            }
            int[] r12 = r9;
            if (viewAtIndexArr != null) {
                for (ViewAtIndex viewAtIndex : viewAtIndexArr) {
                    View view2 = this.mTagsToViews.get(viewAtIndex.mTag);
                    if (view2 == null) {
                        throw new IllegalViewOperationException("Trying to add unknown view tag: " + viewAtIndex.mTag + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, r12, viewAtIndexArr, r21));
                    }
                    int r4 = viewAtIndex.mIndex;
                    if (!pendingDeletionsForTag.isEmpty()) {
                        r4 = 0;
                        int r5 = 0;
                        while (r4 < viewGroup.getChildCount() && r5 != viewAtIndex.mIndex) {
                            if (!pendingDeletionsForTag.contains(Integer.valueOf(viewGroup.getChildAt(r4).getId()))) {
                                r5++;
                            }
                            r4++;
                        }
                    }
                    viewGroupManager.addView(viewGroup, view2, r4);
                }
            }
            if (pendingDeletionsForTag.isEmpty()) {
                this.mPendingDeletionsForTag.remove(Integer.valueOf(r18));
            }
        }
    }

    private boolean arrayContains(int[] r5, int r6) {
        if (r5 == null) {
            return false;
        }
        for (int r3 : r5) {
            if (r3 == r6) {
                return true;
            }
        }
        return false;
    }

    private static String constructSetChildrenErrorMessage(ViewGroup viewGroup, ViewGroupManager viewGroupManager, ReadableArray readableArray) {
        ViewAtIndex[] viewAtIndexArr = new ViewAtIndex[readableArray.size()];
        for (int r1 = 0; r1 < readableArray.size(); r1++) {
            viewAtIndexArr[r1] = new ViewAtIndex(readableArray.getInt(r1), r1);
        }
        return constructManageChildrenErrorMessage(viewGroup, viewGroupManager, null, viewAtIndexArr, null);
    }

    public synchronized void setChildren(int r6, ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        ViewGroup viewGroup = (ViewGroup) this.mTagsToViews.get(r6);
        ViewGroupManager viewGroupManager = (ViewGroupManager) resolveViewManager(r6);
        for (int r1 = 0; r1 < readableArray.size(); r1++) {
            View view = this.mTagsToViews.get(readableArray.getInt(r1));
            if (view == null) {
                throw new IllegalViewOperationException("Trying to add unknown view tag: " + readableArray.getInt(r1) + "\n detail: " + constructSetChildrenErrorMessage(viewGroup, viewGroupManager, readableArray));
            }
            viewGroupManager.addView(viewGroup, view, r1);
        }
    }

    public synchronized void addRootView(int r1, View view) {
        addRootViewGroup(r1, view);
    }

    protected final synchronized void addRootViewGroup(int r4, View view) {
        if (view.getId() != -1) {
            String str = TAG;
            FLog.m1328e(str, "Trying to add a root view with an explicit id (" + view.getId() + ") already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
        }
        this.mTagsToViews.put(r4, view);
        this.mTagsToViewManagers.put(r4, this.mRootViewManager);
        this.mRootTags.put(r4, true);
        view.setId(r4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void dropView(View view) {
        UiThreadUtil.assertOnUiThread();
        if (view == null) {
            return;
        }
        if (this.mTagsToViewManagers.get(view.getId()) == null) {
            return;
        }
        if (!this.mRootTags.get(view.getId())) {
            resolveViewManager(view.getId()).onDropViewInstance(view);
        }
        ViewManager viewManager = this.mTagsToViewManagers.get(view.getId());
        if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
            ViewGroup viewGroup = (ViewGroup) view;
            ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
            for (int childCount = viewGroupManager.getChildCount(viewGroup) - 1; childCount >= 0; childCount--) {
                View childAt = viewGroupManager.getChildAt(viewGroup, childCount);
                if (childAt == null) {
                    FLog.m1328e(TAG, "Unable to drop null child view");
                } else if (this.mTagsToViews.get(childAt.getId()) != null) {
                    dropView(childAt);
                }
            }
            viewGroupManager.removeAllViews(viewGroup);
        }
        this.mTagsToViews.remove(view.getId());
        this.mTagsToViewManagers.remove(view.getId());
    }

    public synchronized void removeRootView(int r3) {
        UiThreadUtil.assertOnUiThread();
        if (!this.mRootTags.get(r3)) {
            SoftAssertions.assertUnreachable("View with tag " + r3 + " is not registered as a root view");
        }
        dropView(this.mTagsToViews.get(r3));
        this.mRootTags.delete(r3);
    }

    public synchronized void measure(int r5, int[] r6) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(r5);
        if (view == null) {
            throw new NoSuchNativeViewException("No native view for " + r5 + " currently exists");
        }
        View view2 = (View) RootViewUtil.getRootView(view);
        if (view2 == null) {
            throw new NoSuchNativeViewException("Native view " + r5 + " is no longer on screen");
        }
        computeBoundingBox(view2, r6);
        int r1 = r6[0];
        int r3 = r6[1];
        computeBoundingBox(view, r6);
        r6[0] = r6[0] - r1;
        r6[1] = r6[1] - r3;
    }

    private void computeBoundingBox(View view, int[] r6) {
        this.mBoundingBox.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        mapRectFromViewToWindowCoords(view, this.mBoundingBox);
        r6[0] = Math.round(this.mBoundingBox.left);
        r6[1] = Math.round(this.mBoundingBox.top);
        r6[2] = Math.round(this.mBoundingBox.right - this.mBoundingBox.left);
        r6[3] = Math.round(this.mBoundingBox.bottom - this.mBoundingBox.top);
    }

    private void mapRectFromViewToWindowCoords(View view, RectF rectF) {
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            matrix.mapRect(rectF);
        }
        rectF.offset(view.getLeft(), view.getTop());
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            View view2 = (View) parent;
            rectF.offset(-view2.getScrollX(), -view2.getScrollY());
            Matrix matrix2 = view2.getMatrix();
            if (!matrix2.isIdentity()) {
                matrix2.mapRect(rectF);
            }
            rectF.offset(view2.getLeft(), view2.getTop());
            parent = view2.getParent();
        }
    }

    public synchronized void measureInWindow(int r5, int[] r6) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(r5);
        if (view == null) {
            throw new NoSuchNativeViewException("No native view for " + r5 + " currently exists");
        }
        view.getLocationOnScreen(r6);
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        r6[0] = r6[0] - rect.left;
        r6[1] = r6[1] - rect.top;
        r6[2] = view.getWidth();
        r6[3] = view.getHeight();
    }

    public synchronized int findTargetTagForTouch(int r2, float f, float f2) {
        View view;
        UiThreadUtil.assertOnUiThread();
        view = this.mTagsToViews.get(r2);
        if (view == null) {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + r2);
        }
        return TouchTargetHelper.findTargetTagForTouch(f, f2, (ViewGroup) view);
    }

    public synchronized void setJSResponder(int r3, int r4, boolean z) {
        if (!z) {
            this.mJSResponderHandler.setJSResponder(r4, null);
            return;
        }
        View view = this.mTagsToViews.get(r3);
        if (r4 != r3 && (view instanceof ViewParent)) {
            this.mJSResponderHandler.setJSResponder(r4, (ViewParent) view);
            return;
        }
        if (this.mRootTags.get(r3)) {
            SoftAssertions.assertUnreachable("Cannot block native responder on " + r3 + " that is a root view");
        }
        this.mJSResponderHandler.setJSResponder(r4, view.getParent());
    }

    public void clearJSResponder() {
        this.mJSResponderHandler.clearJSResponder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void configureLayoutAnimation(ReadableMap readableMap, Callback callback) {
        this.mLayoutAnimator.initializeFromConfig(readableMap, callback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearLayoutAnimation() {
        this.mLayoutAnimator.reset();
    }

    @Deprecated
    public synchronized void dispatchCommand(int r3, int r4, ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(r3);
        if (view == null) {
            throw new RetryableMountingLayerException("Trying to send command to a non-existing view with tag [" + r3 + "] and command " + r4);
        }
        resolveViewManager(r3).receiveCommand((ViewManager) view, r4, readableArray);
    }

    public synchronized void dispatchCommand(int r3, String str, ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(r3);
        if (view == null) {
            throw new RetryableMountingLayerException("Trying to send command to a non-existing view with tag [" + r3 + "] and command " + str);
        }
        ViewManager resolveViewManager = resolveViewManager(r3);
        ViewManagerDelegate delegate = resolveViewManager.getDelegate();
        if (delegate != null) {
            delegate.receiveCommand(view, str, readableArray);
        } else {
            resolveViewManager.receiveCommand((ViewManager) view, str, readableArray);
        }
    }

    public synchronized void showPopupMenu(int r3, ReadableArray readableArray, Callback callback, Callback callback2) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(r3);
        if (view == null) {
            callback2.invoke("Can't display popup. Could not find view with tag " + r3);
            return;
        }
        PopupMenu popupMenu = new PopupMenu(getReactContextForView(r3), view);
        this.mPopupMenu = popupMenu;
        Menu menu = popupMenu.getMenu();
        for (int r6 = 0; r6 < readableArray.size(); r6++) {
            menu.add(0, 0, r6, readableArray.getString(r6));
        }
        PopupMenuCallbackHandler popupMenuCallbackHandler = new PopupMenuCallbackHandler(callback);
        this.mPopupMenu.setOnMenuItemClickListener(popupMenuCallbackHandler);
        this.mPopupMenu.setOnDismissListener(popupMenuCallbackHandler);
        this.mPopupMenu.show();
    }

    public void dismissPopupMenu() {
        PopupMenu popupMenu = this.mPopupMenu;
        if (popupMenu != null) {
            popupMenu.dismiss();
        }
    }

    /* loaded from: classes.dex */
    private static class PopupMenuCallbackHandler implements PopupMenu.OnMenuItemClickListener, PopupMenu.OnDismissListener {
        boolean mConsumed;
        final Callback mSuccess;

        private PopupMenuCallbackHandler(Callback callback) {
            this.mConsumed = false;
            this.mSuccess = callback;
        }

        @Override // android.widget.PopupMenu.OnDismissListener
        public void onDismiss(PopupMenu popupMenu) {
            if (this.mConsumed) {
                return;
            }
            this.mSuccess.invoke("dismissed");
            this.mConsumed = true;
        }

        @Override // android.widget.PopupMenu.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (this.mConsumed) {
                return false;
            }
            this.mSuccess.invoke(UIManagerModuleConstants.ACTION_ITEM_SELECTED, Integer.valueOf(menuItem.getOrder()));
            this.mConsumed = true;
            return true;
        }
    }

    private ThemedReactContext getReactContextForView(int r4) {
        View view = this.mTagsToViews.get(r4);
        if (view == null) {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + r4);
        }
        return (ThemedReactContext) view.getContext();
    }

    public void sendAccessibilityEvent(int r3, int r4) {
        View view = this.mTagsToViews.get(r3);
        if (view == null) {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + r3);
        }
        view.sendAccessibilityEvent(r4);
    }
}
