package com.facebook.react.fabric.mounting;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.fabric.GuardedFrameCallback;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.ReactOverflowViewWithInset;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.views.view.ReactMapBufferViewManager;
import com.facebook.react.views.view.ReactViewManagerWrapper;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class SurfaceMountingManager {
    private static final boolean SHOW_CHANGED_VIEW_HIERARCHIES = false;
    public static final String TAG = "SurfaceMountingManager";
    private JSResponderHandler mJSResponderHandler;
    private MountingManager.MountItemExecutor mMountItemExecutor;
    private RemoveDeleteTreeUIFrameCallback mRemoveDeleteTreeUIFrameCallback;
    private RootViewManager mRootViewManager;
    private Set<Integer> mScheduledForDeletionViewStateTags;
    private Set<Integer> mSoftDeletedViewStateTags;
    private final int mSurfaceId;
    private Set<Integer> mTagSetForStoppedSurface;
    @Nullable
    private ThemedReactContext mThemedReactContext;
    private ViewManagerRegistry mViewManagerRegistry;
    private volatile boolean mIsStopped = false;
    private volatile boolean mRootViewAttached = false;
    private ConcurrentHashMap<Integer, ViewState> mTagToViewState = new ConcurrentHashMap<>();
    private ConcurrentLinkedQueue<MountItem> mOnViewAttachItems = new ConcurrentLinkedQueue<>();
    private final Stack<Integer> mReactTagsToRemove = new Stack<>();
    private final Set<Integer> mErroneouslyReaddedReactTags = new HashSet();

    public SurfaceMountingManager(int r2, JSResponderHandler jSResponderHandler, ViewManagerRegistry viewManagerRegistry, RootViewManager rootViewManager, MountingManager.MountItemExecutor mountItemExecutor, ThemedReactContext themedReactContext) {
        this.mSurfaceId = r2;
        this.mJSResponderHandler = jSResponderHandler;
        this.mViewManagerRegistry = viewManagerRegistry;
        this.mRootViewManager = rootViewManager;
        this.mMountItemExecutor = mountItemExecutor;
        this.mThemedReactContext = themedReactContext;
        if (ReactFeatureFlags.enableDelayedViewStateDeletion) {
            this.mSoftDeletedViewStateTags = new HashSet();
            this.mScheduledForDeletionViewStateTags = new HashSet();
        }
    }

    public boolean isStopped() {
        return this.mIsStopped;
    }

    public void attachRootView(View view, ThemedReactContext themedReactContext) {
        this.mThemedReactContext = themedReactContext;
        addRootView(view);
    }

    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    public boolean isRootViewAttached() {
        return this.mRootViewAttached;
    }

    @Nullable
    public ThemedReactContext getContext() {
        return this.mThemedReactContext;
    }

    private static void logViewHierarchy(ViewGroup viewGroup, boolean z) {
        int id = viewGroup.getId();
        String str = TAG;
        FLog.m1328e(str, "  <ViewGroup tag=" + id + " class=" + viewGroup.getClass().toString() + ">");
        for (int r1 = 0; r1 < viewGroup.getChildCount(); r1++) {
            String str2 = TAG;
            FLog.m1328e(str2, "     <View idx=" + r1 + " tag=" + viewGroup.getChildAt(r1).getId() + " class=" + viewGroup.getChildAt(r1).getClass().toString() + ">");
        }
        String str3 = TAG;
        FLog.m1328e(str3, "  </ViewGroup tag=" + id + ">");
        if (z) {
            FLog.m1328e(str3, "Displaying Ancestors:");
            for (ViewParent parent = viewGroup.getParent(); parent != null; parent = parent.getParent()) {
                ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                int id2 = viewGroup2 == null ? -1 : viewGroup2.getId();
                String str4 = TAG;
                FLog.m1328e(str4, "<ViewParent tag=" + id2 + " class=" + parent.getClass().toString() + ">");
            }
        }
    }

    public boolean getViewExists(int r3) {
        Set<Integer> set = this.mTagSetForStoppedSurface;
        if (set == null || !set.contains(Integer.valueOf(r3))) {
            ConcurrentHashMap<Integer, ViewState> concurrentHashMap = this.mTagToViewState;
            if (concurrentHashMap == null) {
                return false;
            }
            return concurrentHashMap.containsKey(Integer.valueOf(r3));
        }
        return true;
    }

    public void executeOnViewAttach(MountItem mountItem) {
        this.mOnViewAttachItems.add(mountItem);
    }

    private void addRootView(final View view) {
        if (isStopped()) {
            return;
        }
        this.mTagToViewState.put(Integer.valueOf(this.mSurfaceId), new ViewState(this.mSurfaceId, view, new ReactViewManagerWrapper.DefaultViewManager(this.mRootViewManager), true));
        Runnable runnable = new Runnable() { // from class: com.facebook.react.fabric.mounting.SurfaceMountingManager.1
            @Override // java.lang.Runnable
            public void run() {
                if (SurfaceMountingManager.this.isStopped()) {
                    return;
                }
                if (view.getId() == SurfaceMountingManager.this.mSurfaceId) {
                    String str = SurfaceMountingManager.TAG;
                    ReactSoftExceptionLogger.logSoftException(str, new IllegalViewOperationException("Race condition in addRootView detected. Trying to set an id of [" + SurfaceMountingManager.this.mSurfaceId + "] on the RootView, but that id has already been set. "));
                } else if (view.getId() != -1) {
                    FLog.m1326e(SurfaceMountingManager.TAG, "Trying to add RootTag to RootView that already has a tag: existing tag: [%d] new tag: [%d]", Integer.valueOf(view.getId()), Integer.valueOf(SurfaceMountingManager.this.mSurfaceId));
                    throw new IllegalViewOperationException("Trying to add a root view with an explicit id already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
                }
                view.setId(SurfaceMountingManager.this.mSurfaceId);
                View view2 = view;
                if (view2 instanceof ReactRoot) {
                    ((ReactRoot) view2).setRootViewTag(SurfaceMountingManager.this.mSurfaceId);
                }
                SurfaceMountingManager.this.mRootViewAttached = true;
                SurfaceMountingManager.this.executeViewAttachMountItems();
            }
        };
        if (UiThreadUtil.isOnUiThread()) {
            runnable.run();
        } else {
            UiThreadUtil.runOnUiThread(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeViewAttachMountItems() {
        this.mMountItemExecutor.executeItems(this.mOnViewAttachItems);
    }

    public void stopSurface() {
        if (isStopped()) {
            return;
        }
        this.mIsStopped = true;
        for (ViewState viewState : this.mTagToViewState.values()) {
            if (viewState.mStateWrapper != null) {
                viewState.mStateWrapper.destroyState();
                viewState.mStateWrapper = null;
            }
            if (ReactFeatureFlags.enableAggressiveEventEmitterCleanup && viewState.mEventEmitter != null) {
                viewState.mEventEmitter.destroy();
                viewState.mEventEmitter = null;
            }
        }
        Runnable runnable = new Runnable() { // from class: com.facebook.react.fabric.mounting.SurfaceMountingManager.2
            @Override // java.lang.Runnable
            public void run() {
                for (ViewState viewState2 : SurfaceMountingManager.this.mTagToViewState.values()) {
                    SurfaceMountingManager.this.onViewStateDeleted(viewState2);
                }
                SurfaceMountingManager surfaceMountingManager = SurfaceMountingManager.this;
                surfaceMountingManager.mTagSetForStoppedSurface = surfaceMountingManager.mTagToViewState.keySet();
                SurfaceMountingManager.this.mTagToViewState = null;
                SurfaceMountingManager.this.mJSResponderHandler = null;
                SurfaceMountingManager.this.mRootViewManager = null;
                SurfaceMountingManager.this.mMountItemExecutor = null;
                SurfaceMountingManager.this.mOnViewAttachItems.clear();
                if (ReactFeatureFlags.enableViewRecycling) {
                    SurfaceMountingManager.this.mViewManagerRegistry.onSurfaceStopped(SurfaceMountingManager.this.mSurfaceId);
                }
            }
        };
        if (UiThreadUtil.isOnUiThread()) {
            runnable.run();
        } else {
            UiThreadUtil.runOnUiThread(runnable);
        }
    }

    public void addViewAt(int r12, int r13, int r14) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(r12);
        if (!(viewState.mView instanceof ViewGroup)) {
            String str = "Unable to add a view into a view that is not a ViewGroup. ParentTag: " + r12 + " - Tag: " + r13 + " - Index: " + r14;
            FLog.m1328e(TAG, str);
            throw new IllegalStateException(str);
        }
        ViewGroup viewGroup = (ViewGroup) viewState.mView;
        ViewState viewState2 = getViewState(r13);
        View view = viewState2.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find view for viewState " + viewState2 + " and tag " + r13);
        }
        ViewParent parent = view.getParent();
        if (parent != null) {
            boolean z = parent instanceof ViewGroup;
            int id = z ? ((ViewGroup) parent).getId() : -1;
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("addViewAt: cannot insert view [" + r13 + "] into parent [" + r12 + "]: View already has a parent: [" + id + "]  Parent: " + parent.getClass().getSimpleName() + " View: " + view.getClass().getSimpleName()));
            if (z) {
                ((ViewGroup) parent).removeView(view);
            }
            this.mErroneouslyReaddedReactTags.add(Integer.valueOf(r13));
        }
        try {
            getViewGroupManager(viewState).addView(viewGroup, view, r14);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("addViewAt: failed to insert view [" + r13 + "] into parent [" + r12 + "] at index " + r14, e);
        }
    }

    public void removeViewAt(int r10, int r11, int r12) {
        if (isStopped()) {
            return;
        }
        if (this.mErroneouslyReaddedReactTags.contains(Integer.valueOf(r10))) {
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalViewOperationException("removeViewAt tried to remove a React View that was actually reused. This indicates a bug in the Differ (specifically instruction ordering). [" + r10 + "]"));
            return;
        }
        UiThreadUtil.assertOnUiThread();
        ViewState nullableViewState = getNullableViewState(r11);
        if (nullableViewState == null) {
            ReactSoftExceptionLogger.logSoftException(MountingManager.TAG, new IllegalStateException("Unable to find viewState for tag: [" + r11 + "] for removeViewAt"));
        } else if (!(nullableViewState.mView instanceof ViewGroup)) {
            String str = "Unable to remove a view from a view that is not a ViewGroup. ParentTag: " + r11 + " - Tag: " + r10 + " - Index: " + r12;
            FLog.m1328e(TAG, str);
            throw new IllegalStateException(str);
        } else {
            ViewGroup viewGroup = (ViewGroup) nullableViewState.mView;
            if (viewGroup == null) {
                throw new IllegalStateException("Unable to find view for tag [" + r11 + "]");
            }
            ViewGroupManager<ViewGroup> viewGroupManager = getViewGroupManager(nullableViewState);
            View childAt = viewGroupManager.getChildAt(viewGroup, r12);
            int id = childAt != null ? childAt.getId() : -1;
            if (id != r10) {
                int childCount = viewGroup.getChildCount();
                int r6 = 0;
                while (true) {
                    if (r6 >= childCount) {
                        r6 = -1;
                        break;
                    } else if (viewGroup.getChildAt(r6).getId() == r10) {
                        break;
                    } else {
                        r6++;
                    }
                }
                if (r6 == -1) {
                    FLog.m1328e(TAG, "removeViewAt: [" + r10 + "] -> [" + r11 + "] @" + r12 + ": view already removed from parent! Children in parent: " + childCount);
                    return;
                }
                logViewHierarchy(viewGroup, true);
                ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Tried to remove view [" + r10 + "] of parent [" + r11 + "] at index " + r12 + ", but got view tag " + id + " - actual index of view: " + r6));
                r12 = r6;
            }
            try {
                viewGroupManager.removeViewAt(viewGroup, r12);
            } catch (RuntimeException e) {
                int childCount2 = viewGroupManager.getChildCount(viewGroup);
                logViewHierarchy(viewGroup, true);
                throw new IllegalStateException("Cannot remove child at index " + r12 + " from parent ViewGroup [" + viewGroup.getId() + "], only " + childCount2 + " children in parent. Warning: childCount may be incorrect!", e);
            }
        }
    }

    public void removeDeleteTreeAt(int r10, int r11, int r12) {
        if (isStopped()) {
            return;
        }
        UiThreadUtil.assertOnUiThread();
        ViewState nullableViewState = getNullableViewState(r11);
        if (nullableViewState == null) {
            ReactSoftExceptionLogger.logSoftException(MountingManager.TAG, new IllegalStateException("Unable to find viewState for tag: [" + r11 + "] for removeDeleteTreeAt"));
        } else if (!(nullableViewState.mView instanceof ViewGroup)) {
            String str = "Unable to remove+delete a view from a view that is not a ViewGroup. ParentTag: " + r11 + " - Tag: " + r10 + " - Index: " + r12;
            FLog.m1328e(TAG, str);
            throw new IllegalStateException(str);
        } else {
            ViewGroup viewGroup = (ViewGroup) nullableViewState.mView;
            if (viewGroup == null) {
                throw new IllegalStateException("Unable to find view for tag [" + r11 + "]");
            }
            ViewGroupManager<ViewGroup> viewGroupManager = getViewGroupManager(nullableViewState);
            View childAt = viewGroupManager.getChildAt(viewGroup, r12);
            int id = childAt != null ? childAt.getId() : -1;
            if (id != r10) {
                int childCount = viewGroup.getChildCount();
                int r6 = 0;
                while (true) {
                    if (r6 >= childCount) {
                        r6 = -1;
                        break;
                    } else if (viewGroup.getChildAt(r6).getId() == r10) {
                        break;
                    } else {
                        r6++;
                    }
                }
                if (r6 == -1) {
                    FLog.m1328e(TAG, "removeDeleteTreeAt: [" + r10 + "] -> [" + r11 + "] @" + r12 + ": view already removed from parent! Children in parent: " + childCount);
                    return;
                }
                logViewHierarchy(viewGroup, true);
                ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Tried to remove+delete view [" + r10 + "] of parent [" + r11 + "] at index " + r12 + ", but got view tag " + id + " - actual index of view: " + r6));
                r12 = r6;
            }
            try {
                viewGroupManager.removeViewAt(viewGroup, r12);
                runDeferredTagRemovalAndDeletion();
                this.mReactTagsToRemove.push(Integer.valueOf(r10));
            } catch (RuntimeException e) {
                int childCount2 = viewGroupManager.getChildCount(viewGroup);
                logViewHierarchy(viewGroup, true);
                throw new IllegalStateException("Cannot remove child at index " + r12 + " from parent ViewGroup [" + viewGroup.getId() + "], only " + childCount2 + " children in parent. Warning: childCount may be incorrect!", e);
            }
        }
    }

    private void runDeferredTagRemovalAndDeletion() {
        if (this.mReactTagsToRemove.empty()) {
            if (this.mRemoveDeleteTreeUIFrameCallback == null) {
                this.mRemoveDeleteTreeUIFrameCallback = new RemoveDeleteTreeUIFrameCallback(this.mThemedReactContext);
            }
            ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this.mRemoveDeleteTreeUIFrameCallback);
        }
    }

    public void createView(String str, int r3, @Nullable Object obj, @Nullable StateWrapper stateWrapper, @Nullable EventEmitterWrapper eventEmitterWrapper, boolean z) {
        if (isStopped()) {
            return;
        }
        ViewState nullableViewState = getNullableViewState(r3);
        if (nullableViewState == null || nullableViewState.mView == null) {
            createViewUnsafe(str, r3, obj, stateWrapper, eventEmitterWrapper, z);
        }
    }

    public void createViewUnsafe(String str, int r10, @Nullable Object obj, @Nullable StateWrapper stateWrapper, @Nullable EventEmitterWrapper eventEmitterWrapper, boolean z) {
        ReactViewManagerWrapper reactViewManagerWrapper;
        View view;
        Object reactStylesDiffMap = obj instanceof ReadableMap ? new ReactStylesDiffMap((ReadableMap) obj) : obj;
        if (z) {
            if (obj instanceof ReadableMapBuffer) {
                reactViewManagerWrapper = ReactMapBufferViewManager.INSTANCE;
            } else {
                reactViewManagerWrapper = new ReactViewManagerWrapper.DefaultViewManager(this.mViewManagerRegistry.get(str));
            }
            view = reactViewManagerWrapper.createView(r10, this.mThemedReactContext, reactStylesDiffMap, stateWrapper, this.mJSResponderHandler);
        } else {
            reactViewManagerWrapper = null;
            view = null;
        }
        ViewState viewState = new ViewState(r10, view, reactViewManagerWrapper);
        viewState.mCurrentProps = reactStylesDiffMap;
        viewState.mStateWrapper = stateWrapper;
        viewState.mEventEmitter = eventEmitterWrapper;
        this.mTagToViewState.put(Integer.valueOf(r10), viewState);
    }

    public void updateProps(int r3, Object obj) {
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(r3);
        if (obj instanceof ReadableMap) {
            obj = new ReactStylesDiffMap((ReadableMap) obj);
        }
        viewState.mCurrentProps = obj;
        View view = viewState.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find view for tag [" + r3 + "]");
        }
        ((ReactViewManagerWrapper) Assertions.assertNotNull(viewState.mViewManager)).updateProperties(view, viewState.mCurrentProps);
    }

    @Deprecated
    public void receiveCommand(int r3, int r4, @Nullable ReadableArray readableArray) {
        if (isStopped()) {
            return;
        }
        ViewState nullableViewState = getNullableViewState(r3);
        if (nullableViewState == null) {
            throw new RetryableMountingLayerException("Unable to find viewState for tag: [" + r3 + "] for commandId: " + r4);
        } else if (nullableViewState.mViewManager == null) {
            throw new RetryableMountingLayerException("Unable to find viewManager for tag " + r3);
        } else if (nullableViewState.mView == null) {
            throw new RetryableMountingLayerException("Unable to find viewState view for tag " + r3);
        } else {
            nullableViewState.mViewManager.receiveCommand(nullableViewState.mView, r4, readableArray);
        }
    }

    public void receiveCommand(int r3, String str, @Nullable ReadableArray readableArray) {
        if (isStopped()) {
            return;
        }
        ViewState nullableViewState = getNullableViewState(r3);
        if (nullableViewState == null) {
            throw new RetryableMountingLayerException("Unable to find viewState for tag: " + r3 + " for commandId: " + str);
        } else if (nullableViewState.mViewManager == null) {
            throw new RetryableMountingLayerException("Unable to find viewState manager for tag " + r3);
        } else if (nullableViewState.mView == null) {
            throw new RetryableMountingLayerException("Unable to find viewState view for tag " + r3);
        } else {
            nullableViewState.mViewManager.receiveCommand(nullableViewState.mView, str, readableArray);
        }
    }

    public void sendAccessibilityEvent(int r3, int r4) {
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(r3);
        if (viewState.mViewManager == null) {
            throw new RetryableMountingLayerException("Unable to find viewState manager for tag " + r3);
        } else if (viewState.mView == null) {
            throw new RetryableMountingLayerException("Unable to find viewState view for tag " + r3);
        } else {
            viewState.mView.sendAccessibilityEvent(r4);
        }
    }

    public void updateLayout(int r3, int r4, int r5, int r6, int r7, int r8) {
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(r3);
        if (viewState.mIsRoot) {
            return;
        }
        View view = viewState.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find View for tag: " + r3);
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(r6, 1073741824), View.MeasureSpec.makeMeasureSpec(r7, 1073741824));
        ViewParent parent = view.getParent();
        if (parent instanceof RootView) {
            parent.requestLayout();
        }
        view.layout(r4, r5, r6 + r4, r7 + r5);
        int r32 = r8 == 0 ? 4 : 0;
        if (view.getVisibility() != r32) {
            view.setVisibility(r32);
        }
    }

    public void updatePadding(int r9, int r10, int r11, int r12, int r13) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(r9);
        if (viewState.mIsRoot) {
            return;
        }
        View view = viewState.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find View for tag: " + r9);
        }
        ReactViewManagerWrapper reactViewManagerWrapper = viewState.mViewManager;
        if (reactViewManagerWrapper == null) {
            throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
        }
        reactViewManagerWrapper.setPadding(view, r10, r11, r12, r13);
    }

    public void updateOverflowInset(int r3, int r4, int r5, int r6, int r7) {
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(r3);
        if (viewState.mIsRoot) {
            return;
        }
        View view = viewState.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find View for tag: " + r3);
        } else if (view instanceof ReactOverflowViewWithInset) {
            ((ReactOverflowViewWithInset) view).setOverflowInset(r4, r5, r6, r7);
        }
    }

    public void updateState(int r5, @Nullable StateWrapper stateWrapper) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(r5);
        StateWrapper stateWrapper2 = viewState.mStateWrapper;
        viewState.mStateWrapper = stateWrapper;
        ReactViewManagerWrapper reactViewManagerWrapper = viewState.mViewManager;
        if (reactViewManagerWrapper == null) {
            throw new IllegalStateException("Unable to find ViewManager for tag: " + r5);
        }
        Object updateState = reactViewManagerWrapper.updateState(viewState.mView, viewState.mCurrentProps, stateWrapper);
        if (updateState != null) {
            reactViewManagerWrapper.updateExtraData(viewState.mView, updateState);
        }
        if (stateWrapper2 != null) {
            stateWrapper2.destroyState();
        }
    }

    public void updateEventEmitter(int r6, EventEmitterWrapper eventEmitterWrapper) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(r6));
        if (viewState == null) {
            viewState = new ViewState(r6, (View) null, (ReactViewManagerWrapper) null);
            this.mTagToViewState.put(Integer.valueOf(r6), viewState);
        }
        EventEmitterWrapper eventEmitterWrapper2 = viewState.mEventEmitter;
        viewState.mEventEmitter = eventEmitterWrapper;
        if (eventEmitterWrapper2 != eventEmitterWrapper && eventEmitterWrapper2 != null) {
            eventEmitterWrapper2.destroy();
        }
        if (viewState.mPendingEventQueue != null) {
            for (ViewEvent viewEvent : viewState.mPendingEventQueue) {
                if (viewEvent.canCoalesceEvent()) {
                    eventEmitterWrapper.invokeUnique(viewEvent.getEventName(), viewEvent.getParams(), viewEvent.getCustomCoalesceKey());
                } else {
                    eventEmitterWrapper.invoke(viewEvent.getEventName(), viewEvent.getParams(), viewEvent.getEventCategory());
                }
            }
            viewState.mPendingEventQueue = null;
        }
    }

    public synchronized void setJSResponder(int r3, int r4, boolean z) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        if (!z) {
            this.mJSResponderHandler.setJSResponder(r4, null);
            return;
        }
        ViewState viewState = getViewState(r3);
        View view = viewState.mView;
        if (r4 != r3 && (view instanceof ViewParent)) {
            this.mJSResponderHandler.setJSResponder(r4, (ViewParent) view);
        } else if (view == null) {
            SoftAssertions.assertUnreachable("Cannot find view for tag [" + r3 + "].");
        } else {
            if (viewState.mIsRoot) {
                SoftAssertions.assertUnreachable("Cannot block native responder on [" + r3 + "] that is a root view");
            }
            this.mJSResponderHandler.setJSResponder(r4, view.getParent());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onViewStateDeleted(ViewState viewState) {
        if (viewState.mStateWrapper != null) {
            viewState.mStateWrapper.destroyState();
            viewState.mStateWrapper = null;
        }
        if (viewState.mEventEmitter != null) {
            viewState.mEventEmitter.destroy();
            viewState.mEventEmitter = null;
        }
        ReactViewManagerWrapper reactViewManagerWrapper = viewState.mViewManager;
        if (viewState.mIsRoot || reactViewManagerWrapper == null) {
            return;
        }
        reactViewManagerWrapper.onDropViewInstance(viewState.mView);
    }

    public void didUpdateViews() {
        if (ReactFeatureFlags.enableDelayedViewStateDeletion) {
            for (Integer num : this.mScheduledForDeletionViewStateTags) {
                ViewState remove = this.mTagToViewState.remove(num);
                if (remove != null) {
                    onViewStateDeleted(remove);
                }
            }
            this.mScheduledForDeletionViewStateTags = this.mSoftDeletedViewStateTags;
            this.mSoftDeletedViewStateTags = new HashSet();
        }
    }

    public void deleteView(int r5) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        ViewState nullableViewState = getNullableViewState(r5);
        if (nullableViewState == null) {
            String str = MountingManager.TAG;
            ReactSoftExceptionLogger.logSoftException(str, new IllegalStateException("Unable to find viewState for tag: " + r5 + " for deleteView"));
        } else if (ReactFeatureFlags.enableDelayedViewStateDeletion) {
            this.mSoftDeletedViewStateTags.add(Integer.valueOf(r5));
        } else {
            this.mTagToViewState.remove(Integer.valueOf(r5));
            onViewStateDeleted(nullableViewState);
        }
    }

    public void preallocateView(String str, int r3, @Nullable Object obj, @Nullable StateWrapper stateWrapper, @Nullable EventEmitterWrapper eventEmitterWrapper, boolean z) {
        UiThreadUtil.assertOnUiThread();
        if (!isStopped() && getNullableViewState(r3) == null) {
            createViewUnsafe(str, r3, obj, stateWrapper, eventEmitterWrapper, z);
        }
    }

    @Nullable
    public EventEmitterWrapper getEventEmitter(int r1) {
        ViewState nullableViewState = getNullableViewState(r1);
        if (nullableViewState == null) {
            return null;
        }
        return nullableViewState.mEventEmitter;
    }

    public View getView(int r4) {
        ViewState nullableViewState = getNullableViewState(r4);
        View view = nullableViewState == null ? null : nullableViewState.mView;
        if (view != null) {
            return view;
        }
        throw new IllegalViewOperationException("Trying to resolve view with tag " + r4 + " which doesn't exist");
    }

    private ViewState getViewState(int r4) {
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(r4));
        if (viewState == null) {
            throw new RetryableMountingLayerException("Unable to find viewState for tag " + r4 + ". Surface stopped: " + isStopped());
        }
        if (ReactFeatureFlags.enableDelayedViewStateDeletion) {
            this.mScheduledForDeletionViewStateTags.remove(Integer.valueOf(r4));
        }
        return viewState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public ViewState getNullableViewState(int r4) {
        ConcurrentHashMap<Integer, ViewState> concurrentHashMap = this.mTagToViewState;
        if (concurrentHashMap == null) {
            return null;
        }
        if (ReactFeatureFlags.enableDelayedViewStateDeletion) {
            this.mScheduledForDeletionViewStateTags.remove(Integer.valueOf(r4));
        }
        return concurrentHashMap.get(Integer.valueOf(r4));
    }

    private static ViewGroupManager<ViewGroup> getViewGroupManager(ViewState viewState) {
        if (viewState.mViewManager == null) {
            throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
        }
        return viewState.mViewManager.getViewGroupManager();
    }

    public void printSurfaceState() {
        FLog.m1326e(TAG, "Views created for surface {%d}:", Integer.valueOf(getSurfaceId()));
        for (ViewState viewState : this.mTagToViewState.values()) {
            Integer num = null;
            String name = viewState.mViewManager != null ? viewState.mViewManager.getName() : null;
            View view = viewState.mView;
            View view2 = view != null ? (View) view.getParent() : null;
            if (view2 != null) {
                num = Integer.valueOf(view2.getId());
            }
            FLog.m1326e(TAG, "<%s id=%d parentTag=%s isRoot=%b />", name, Integer.valueOf(viewState.mReactTag), num, Boolean.valueOf(viewState.mIsRoot));
        }
    }

    public void enqueuePendingEvent(int r3, ViewEvent viewEvent) {
        ViewState viewState;
        UiThreadUtil.assertOnUiThread();
        ConcurrentHashMap<Integer, ViewState> concurrentHashMap = this.mTagToViewState;
        if (concurrentHashMap == null || (viewState = concurrentHashMap.get(Integer.valueOf(r3))) == null) {
            return;
        }
        Assertions.assertCondition(viewState.mEventEmitter == null, "Only queue pending events when event emitter is null for the given view state");
        if (viewState.mPendingEventQueue == null) {
            viewState.mPendingEventQueue = new LinkedList();
        }
        viewState.mPendingEventQueue.add(viewEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ViewState {
        @Nullable
        public ReadableMap mCurrentLocalData;
        @Nullable
        public Object mCurrentProps;
        @Nullable
        public EventEmitterWrapper mEventEmitter;
        final boolean mIsRoot;
        @Nullable
        public Queue<ViewEvent> mPendingEventQueue;
        final int mReactTag;
        @Nullable
        public StateWrapper mStateWrapper;
        @Nullable
        final View mView;
        @Nullable
        final ReactViewManagerWrapper mViewManager;

        private ViewState(int r2, @Nullable View view, @Nullable ReactViewManagerWrapper reactViewManagerWrapper) {
            this(r2, view, reactViewManagerWrapper, false);
        }

        private ViewState(int r2, @Nullable View view, @Nullable ReactViewManagerWrapper reactViewManagerWrapper, boolean z) {
            this.mCurrentProps = null;
            this.mCurrentLocalData = null;
            this.mStateWrapper = null;
            this.mEventEmitter = null;
            this.mPendingEventQueue = null;
            this.mReactTag = r2;
            this.mView = view;
            this.mIsRoot = z;
            this.mViewManager = reactViewManagerWrapper;
        }

        public String toString() {
            boolean z = this.mViewManager == null;
            return "ViewState [" + this.mReactTag + "] - isRoot: " + this.mIsRoot + " - props: " + this.mCurrentProps + " - localData: " + this.mCurrentLocalData + " - viewManager: " + this.mViewManager + " - isLayoutOnly: " + z;
        }
    }

    /* loaded from: classes.dex */
    public static class ViewEvent {
        private final boolean mCanCoalesceEvent;
        private final int mCustomCoalesceKey;
        private final int mEventCategory;
        private final String mEventName;
        @Nullable
        private WritableMap mParams;

        public ViewEvent(String str, @Nullable WritableMap writableMap, int r3, boolean z, int r5) {
            this.mEventName = str;
            this.mParams = writableMap;
            this.mEventCategory = r3;
            this.mCanCoalesceEvent = z;
            this.mCustomCoalesceKey = r5;
        }

        public String getEventName() {
            return this.mEventName;
        }

        public boolean canCoalesceEvent() {
            return this.mCanCoalesceEvent;
        }

        public int getCustomCoalesceKey() {
            return this.mCustomCoalesceKey;
        }

        public int getEventCategory() {
            return this.mEventCategory;
        }

        @Nullable
        public WritableMap getParams() {
            return this.mParams;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RemoveDeleteTreeUIFrameCallback extends GuardedFrameCallback {
        private static final long FRAME_TIME_MS = 16;
        private static final long MAX_TIME_IN_FRAME = 9;

        private RemoveDeleteTreeUIFrameCallback(ReactContext reactContext) {
            super(reactContext);
        }

        private boolean haveExceededNonBatchedFrameTime(long j) {
            return 16 - ((System.nanoTime() - j) / 1000000) < MAX_TIME_IN_FRAME;
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x00a3 A[Catch: all -> 0x00f1, TryCatch #0 {all -> 0x00f1, blocks: (B:3:0x0007, B:5:0x0013, B:7:0x0035, B:8:0x0056, B:10:0x0061, B:13:0x0069, B:15:0x0072, B:17:0x0078, B:22:0x0084, B:25:0x0094, B:31:0x00a3, B:32:0x00ac, B:34:0x00c2, B:28:0x009b), top: B:47:0x0007, inners: #1 }] */
        @Override // com.facebook.react.fabric.GuardedFrameCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void doFrameGuarded(long r12) {
            /*
                Method dump skipped, instructions count: 283
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.fabric.mounting.SurfaceMountingManager.RemoveDeleteTreeUIFrameCallback.doFrameGuarded(long):void");
        }
    }
}
