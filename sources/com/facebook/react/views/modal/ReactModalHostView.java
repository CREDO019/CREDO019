package com.facebook.react.views.modal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.C1413R;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.JSPointerDispatcher;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.common.ContextUtils;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ReactModalHostView extends ViewGroup implements LifecycleEventListener, FabricViewStateManager.HasFabricViewStateManager {
    private static final String TAG = "ReactModalHost";
    private String mAnimationType;
    private Dialog mDialog;
    private boolean mHardwareAccelerated;
    private DialogRootViewGroup mHostView;
    private OnRequestCloseListener mOnRequestCloseListener;
    private DialogInterface.OnShowListener mOnShowListener;
    private boolean mPropertyRequiresNewDialog;
    private boolean mStatusBarTranslucent;
    private boolean mTransparent;

    /* loaded from: classes.dex */
    public interface OnRequestCloseListener {
        void onRequestClose(DialogInterface dialogInterface);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r2, int r3, int r4, int r5) {
    }

    public ReactModalHostView(Context context) {
        super(context);
        ((ReactContext) context).addLifecycleEventListener(this);
        this.mHostView = new DialogRootViewGroup(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        this.mHostView.dispatchProvideStructure(viewStructure);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismiss();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int r3) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.addView(view, r3);
    }

    @Override // android.view.ViewGroup
    public int getChildCount() {
        return this.mHostView.getChildCount();
    }

    @Override // android.view.ViewGroup
    public View getChildAt(int r2) {
        return this.mHostView.getChildAt(r2);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int r2) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.removeView(getChildAt(r2));
    }

    public void onDropInstance() {
        ((ReactContext) getContext()).removeLifecycleEventListener(this);
        dismiss();
    }

    private void dismiss() {
        Activity activity;
        UiThreadUtil.assertOnUiThread();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            if (dialog.isShowing() && ((activity = (Activity) ContextUtils.findContextOfType(this.mDialog.getContext(), Activity.class)) == null || !activity.isFinishing())) {
                this.mDialog.dismiss();
            }
            this.mDialog = null;
            ((ViewGroup) this.mHostView.getParent()).removeViewAt(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnRequestCloseListener(OnRequestCloseListener onRequestCloseListener) {
        this.mOnRequestCloseListener = onRequestCloseListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.mOnShowListener = onShowListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTransparent(boolean z) {
        this.mTransparent = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStatusBarTranslucent(boolean z) {
        this.mStatusBarTranslucent = z;
        this.mPropertyRequiresNewDialog = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAnimationType(String str) {
        this.mAnimationType = str;
        this.mPropertyRequiresNewDialog = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setHardwareAccelerated(boolean z) {
        this.mHardwareAccelerated = z;
        this.mPropertyRequiresNewDialog = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.mHostView.setEventDispatcher(eventDispatcher);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        showOrUpdate();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        onDropInstance();
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    private Activity getCurrentActivity() {
        return ((ReactContext) getContext()).getCurrentActivity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showOrUpdate() {
        UiThreadUtil.assertOnUiThread();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            Context context = (Context) ContextUtils.findContextOfType(dialog.getContext(), Activity.class);
            FLog.m1328e(TAG, "Updating existing dialog with context: " + context + "@" + context.hashCode());
            if (this.mPropertyRequiresNewDialog) {
                dismiss();
            } else {
                updateProperties();
                return;
            }
        }
        this.mPropertyRequiresNewDialog = false;
        int r0 = C1413R.C1420style.Theme_FullScreenDialog;
        if (this.mAnimationType.equals("fade")) {
            r0 = C1413R.C1420style.Theme_FullScreenDialogAnimatedFade;
        } else if (this.mAnimationType.equals("slide")) {
            r0 = C1413R.C1420style.Theme_FullScreenDialogAnimatedSlide;
        }
        Activity currentActivity = getCurrentActivity();
        Context context2 = currentActivity == null ? getContext() : currentActivity;
        Dialog dialog2 = new Dialog(context2, r0);
        this.mDialog = dialog2;
        dialog2.getWindow().setFlags(8, 8);
        FLog.m1328e(TAG, "Creating new dialog from context: " + context2 + "@" + context2.hashCode());
        this.mDialog.setContentView(getContentView());
        updateProperties();
        this.mDialog.setOnShowListener(this.mOnShowListener);
        this.mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.facebook.react.views.modal.ReactModalHostView.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int r4, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 1) {
                    if (r4 == 4 || r4 == 111) {
                        Assertions.assertNotNull(ReactModalHostView.this.mOnRequestCloseListener, "setOnRequestCloseListener must be called by the manager");
                        ReactModalHostView.this.mOnRequestCloseListener.onRequestClose(dialogInterface);
                        return true;
                    }
                    Activity currentActivity2 = ((ReactContext) ReactModalHostView.this.getContext()).getCurrentActivity();
                    if (currentActivity2 != null) {
                        return currentActivity2.onKeyUp(r4, keyEvent);
                    }
                    return false;
                }
                return false;
            }
        });
        this.mDialog.getWindow().setSoftInputMode(16);
        if (this.mHardwareAccelerated) {
            this.mDialog.getWindow().addFlags(16777216);
        }
        if (currentActivity == null || currentActivity.isFinishing()) {
            return;
        }
        this.mDialog.show();
        if (context2 instanceof Activity) {
            this.mDialog.getWindow().getDecorView().setSystemUiVisibility(((Activity) context2).getWindow().getDecorView().getSystemUiVisibility());
        }
        this.mDialog.getWindow().clearFlags(8);
    }

    private View getContentView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.mHostView);
        if (this.mStatusBarTranslucent) {
            frameLayout.setSystemUiVisibility(1024);
        } else {
            frameLayout.setFitsSystemWindows(true);
        }
        return frameLayout;
    }

    private void updateProperties() {
        Assertions.assertNotNull(this.mDialog, "mDialog must exist when we call updateProperties");
        Activity currentActivity = getCurrentActivity();
        Window window = this.mDialog.getWindow();
        if (currentActivity == null || currentActivity.isFinishing() || !window.isActive()) {
            return;
        }
        if ((currentActivity.getWindow().getAttributes().flags & 1024) != 0) {
            window.addFlags(1024);
        } else {
            window.clearFlags(1024);
        }
        if (this.mTransparent) {
            window.clearFlags(2);
            return;
        }
        window.setDimAmount(0.5f);
        window.setFlags(2, 2);
    }

    @Override // com.facebook.react.uimanager.FabricViewStateManager.HasFabricViewStateManager
    public FabricViewStateManager getFabricViewStateManager() {
        return this.mHostView.getFabricViewStateManager();
    }

    public void updateState(int r2, int r3) {
        this.mHostView.updateState(r2, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class DialogRootViewGroup extends ReactViewGroup implements RootView, FabricViewStateManager.HasFabricViewStateManager {
        private boolean hasAdjustedSize;
        private EventDispatcher mEventDispatcher;
        private final FabricViewStateManager mFabricViewStateManager;
        private JSPointerDispatcher mJSPointerDispatcher;
        private final JSTouchDispatcher mJSTouchDispatcher;
        private int viewHeight;
        private int viewWidth;

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean z) {
        }

        public DialogRootViewGroup(Context context) {
            super(context);
            this.hasAdjustedSize = false;
            this.mFabricViewStateManager = new FabricViewStateManager();
            this.mJSTouchDispatcher = new JSTouchDispatcher(this);
            if (ReactFeatureFlags.dispatchPointerEvents) {
                this.mJSPointerDispatcher = new JSPointerDispatcher(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEventDispatcher(EventDispatcher eventDispatcher) {
            this.mEventDispatcher = eventDispatcher;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            this.viewWidth = r1;
            this.viewHeight = r2;
            updateFirstChildView();
        }

        private void updateFirstChildView() {
            if (getChildCount() > 0) {
                this.hasAdjustedSize = false;
                final int id = getChildAt(0).getId();
                if (this.mFabricViewStateManager.hasStateWrapper()) {
                    updateState(this.viewWidth, this.viewHeight);
                    return;
                }
                ReactContext reactContext = getReactContext();
                reactContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactContext) { // from class: com.facebook.react.views.modal.ReactModalHostView.DialogRootViewGroup.1
                    @Override // com.facebook.react.bridge.GuardedRunnable
                    public void runGuarded() {
                        UIManagerModule uIManagerModule = (UIManagerModule) DialogRootViewGroup.this.getReactContext().getNativeModule(UIManagerModule.class);
                        if (uIManagerModule == null) {
                            return;
                        }
                        uIManagerModule.updateNodeSize(id, DialogRootViewGroup.this.viewWidth, DialogRootViewGroup.this.viewHeight);
                    }
                });
                return;
            }
            this.hasAdjustedSize = true;
        }

        public void updateState(int r7, int r8) {
            final float dIPFromPixel = PixelUtil.toDIPFromPixel(r7);
            final float dIPFromPixel2 = PixelUtil.toDIPFromPixel(r8);
            ReadableMap stateData = getFabricViewStateManager().getStateData();
            if (stateData != null) {
                float f = stateData.hasKey("screenHeight") ? (float) stateData.getDouble("screenHeight") : 0.0f;
                if (Math.abs((stateData.hasKey("screenWidth") ? (float) stateData.getDouble("screenWidth") : 0.0f) - dIPFromPixel) < 0.9f && Math.abs(f - dIPFromPixel2) < 0.9f) {
                    return;
                }
            }
            this.mFabricViewStateManager.setState(new FabricViewStateManager.StateUpdateCallback() { // from class: com.facebook.react.views.modal.ReactModalHostView.DialogRootViewGroup.2
                @Override // com.facebook.react.uimanager.FabricViewStateManager.StateUpdateCallback
                public WritableMap getStateUpdate() {
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble("screenWidth", dIPFromPixel);
                    writableNativeMap.putDouble("screenHeight", dIPFromPixel2);
                    return writableNativeMap;
                }
            });
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public void addView(View view, int r2, ViewGroup.LayoutParams layoutParams) {
            super.addView(view, r2, layoutParams);
            if (this.hasAdjustedSize) {
                updateFirstChildView();
            }
        }

        @Override // com.facebook.react.uimanager.RootView
        public void handleException(Throwable th) {
            getReactContext().handleException(new RuntimeException(th));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ReactContext getReactContext() {
            return (ReactContext) getContext();
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, this.mEventDispatcher);
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.handleMotionEvent(motionEvent, this.mEventDispatcher);
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, this.mEventDispatcher);
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.handleMotionEvent(motionEvent, this.mEventDispatcher);
            }
            super.onTouchEvent(motionEvent);
            return true;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.handleMotionEvent(motionEvent, this.mEventDispatcher);
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.handleMotionEvent(motionEvent, this.mEventDispatcher);
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // com.facebook.react.uimanager.RootView
        public void onChildStartedNativeGesture(MotionEvent motionEvent) {
            onChildStartedNativeGesture(null, motionEvent);
        }

        @Override // com.facebook.react.uimanager.RootView
        public void onChildStartedNativeGesture(View view, MotionEvent motionEvent) {
            this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, this.mEventDispatcher);
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.onChildStartedNativeGesture(view, motionEvent, this.mEventDispatcher);
            }
        }

        @Override // com.facebook.react.uimanager.RootView
        public void onChildEndedNativeGesture(View view, MotionEvent motionEvent) {
            this.mJSTouchDispatcher.onChildEndedNativeGesture(motionEvent, this.mEventDispatcher);
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.onChildEndedNativeGesture();
            }
        }

        @Override // com.facebook.react.uimanager.FabricViewStateManager.HasFabricViewStateManager
        public FabricViewStateManager getFabricViewStateManager() {
            return this.mFabricViewStateManager;
        }
    }
}
