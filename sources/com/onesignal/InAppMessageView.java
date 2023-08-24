package com.onesignal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import androidx.cardview.widget.CardView;
import com.onesignal.DraggableRelativeLayout;
import com.onesignal.OneSignal;
import com.onesignal.WebViewManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class InAppMessageView {
    private static final int ACTIVITY_FINISH_AFTER_DISMISS_DELAY_MS = 600;
    private static final int ACTIVITY_INIT_DELAY = 200;
    private static final int IN_APP_BACKGROUND_ANIMATION_DURATION_MS = 400;
    private static final int IN_APP_BANNER_ANIMATION_DURATION_MS = 1000;
    private static final int IN_APP_CENTER_ANIMATION_DURATION_MS = 1000;
    private static final String IN_APP_MESSAGE_CARD_VIEW_TAG = "IN_APP_MESSAGE_CARD_VIEW_TAG";
    private Activity currentActivity;
    private boolean disableDragDismiss;
    private double displayDuration;
    private WebViewManager.Position displayLocation;
    private DraggableRelativeLayout draggableRelativeLayout;
    private boolean hasBackground;
    private OSInAppMessageContent messageContent;
    private InAppMessageViewListener messageController;
    private int pageHeight;
    private RelativeLayout parentRelativeLayout;
    private PopupWindow popupWindow;
    private Runnable scheduleDismissRunnable;
    private WebView webView;
    private static final int ACTIVITY_BACKGROUND_COLOR_EMPTY = Color.parseColor("#00000000");
    private static final int ACTIVITY_BACKGROUND_COLOR_FULL = Color.parseColor("#BB000000");
    private static final int DRAG_THRESHOLD_PX_SIZE = OSViewUtils.dpToPx(4);
    private final Handler handler = new Handler();
    private int marginPxSizeLeft = OSViewUtils.dpToPx(24);
    private int marginPxSizeRight = OSViewUtils.dpToPx(24);
    private int marginPxSizeTop = OSViewUtils.dpToPx(24);
    private int marginPxSizeBottom = OSViewUtils.dpToPx(24);
    private boolean shouldDismissWhenActive = false;
    private boolean isDragging = false;
    private int pageWidth = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface InAppMessageViewListener {
        void onMessageWasDismissed();

        void onMessageWasShown();

        void onMessageWillDismiss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InAppMessageView(WebView webView, OSInAppMessageContent oSInAppMessageContent, boolean z) {
        this.disableDragDismiss = false;
        this.webView = webView;
        this.displayLocation = oSInAppMessageContent.getDisplayLocation();
        this.pageHeight = oSInAppMessageContent.getPageHeight();
        this.displayDuration = oSInAppMessageContent.getDisplayDuration() == null ? 0.0d : oSInAppMessageContent.getDisplayDuration().doubleValue();
        this.hasBackground = !this.displayLocation.isBanner();
        this.disableDragDismiss = z;
        this.messageContent = oSInAppMessageContent;
        setMarginsFromContent(oSInAppMessageContent);
    }

    private void setMarginsFromContent(OSInAppMessageContent oSInAppMessageContent) {
        this.marginPxSizeTop = oSInAppMessageContent.getUseHeightMargin() ? OSViewUtils.dpToPx(24) : 0;
        this.marginPxSizeBottom = oSInAppMessageContent.getUseHeightMargin() ? OSViewUtils.dpToPx(24) : 0;
        this.marginPxSizeLeft = oSInAppMessageContent.getUseWidthMargin() ? OSViewUtils.dpToPx(24) : 0;
        this.marginPxSizeRight = oSInAppMessageContent.getUseWidthMargin() ? OSViewUtils.dpToPx(24) : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setWebView(WebView webView) {
        this.webView = webView;
        webView.setBackgroundColor(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMessageController(InAppMessageViewListener inAppMessageViewListener) {
        this.messageController = inAppMessageViewListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebViewManager.Position getDisplayPosition() {
        return this.displayLocation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showView(Activity activity) {
        delayShowUntilAvailable(activity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkIfShouldDismiss() {
        if (this.shouldDismissWhenActive) {
            this.shouldDismissWhenActive = false;
            finishAfterDelay(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateHeight(final int r2) {
        this.pageHeight = r2;
        OSUtils.runOnMainUIThread(new Runnable() { // from class: com.onesignal.InAppMessageView.1
            @Override // java.lang.Runnable
            public void run() {
                if (InAppMessageView.this.webView != null) {
                    ViewGroup.LayoutParams layoutParams = InAppMessageView.this.webView.getLayoutParams();
                    if (layoutParams == null) {
                        OneSignal.onesignalLog(OneSignal.LOG_LEVEL.WARN, "WebView height update skipped because of null layoutParams, new height will be used once it is displayed.");
                        return;
                    }
                    layoutParams.height = r2;
                    InAppMessageView.this.webView.setLayoutParams(layoutParams);
                    if (InAppMessageView.this.draggableRelativeLayout != null) {
                        DraggableRelativeLayout draggableRelativeLayout = InAppMessageView.this.draggableRelativeLayout;
                        InAppMessageView inAppMessageView = InAppMessageView.this;
                        draggableRelativeLayout.setParams(inAppMessageView.createDraggableLayoutParams(r2, inAppMessageView.displayLocation, InAppMessageView.this.disableDragDismiss));
                        return;
                    }
                    return;
                }
                OneSignal.onesignalLog(OneSignal.LOG_LEVEL.WARN, "WebView height update skipped, new height will be used once it is displayed.");
            }
        });
    }

    void showInAppMessageView(Activity activity) {
        this.currentActivity = activity;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.pageHeight);
        layoutParams.addRule(13);
        RelativeLayout.LayoutParams createParentRelativeLayoutParams = this.hasBackground ? createParentRelativeLayoutParams() : null;
        WebViewManager.Position position = this.displayLocation;
        showDraggableView(position, layoutParams, createParentRelativeLayoutParams, createDraggableLayoutParams(this.pageHeight, position, this.disableDragDismiss));
    }

    private int getDisplayYSize() {
        return OSViewUtils.getWindowHeight(this.currentActivity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.onesignal.InAppMessageView$9 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C35249 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$WebViewManager$Position;

        static {
            int[] r0 = new int[WebViewManager.Position.values().length];
            $SwitchMap$com$onesignal$WebViewManager$Position = r0;
            try {
                r0[WebViewManager.Position.TOP_BANNER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$onesignal$WebViewManager$Position[WebViewManager.Position.BOTTOM_BANNER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$onesignal$WebViewManager$Position[WebViewManager.Position.CENTER_MODAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$onesignal$WebViewManager$Position[WebViewManager.Position.FULL_SCREEN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private RelativeLayout.LayoutParams createParentRelativeLayoutParams() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.pageWidth, -1);
        int r1 = C35249.$SwitchMap$com$onesignal$WebViewManager$Position[this.displayLocation.ordinal()];
        if (r1 == 1) {
            layoutParams.addRule(10);
            layoutParams.addRule(14);
        } else if (r1 == 2) {
            layoutParams.addRule(12);
            layoutParams.addRule(14);
        } else if (r1 == 3 || r1 == 4) {
            layoutParams.addRule(13);
        }
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DraggableRelativeLayout.Params createDraggableLayoutParams(int r5, WebViewManager.Position position, boolean z) {
        DraggableRelativeLayout.Params params = new DraggableRelativeLayout.Params();
        params.maxXPos = this.marginPxSizeRight;
        params.maxYPos = this.marginPxSizeTop;
        params.draggingDisabled = z;
        params.messageHeight = r5;
        params.height = getDisplayYSize();
        int r7 = C35249.$SwitchMap$com$onesignal$WebViewManager$Position[position.ordinal()];
        if (r7 == 1) {
            params.dragThresholdY = this.marginPxSizeTop - DRAG_THRESHOLD_PX_SIZE;
        } else if (r7 == 2) {
            params.posY = getDisplayYSize() - r5;
            params.dragThresholdY = this.marginPxSizeBottom + DRAG_THRESHOLD_PX_SIZE;
        } else {
            if (r7 != 3) {
                if (r7 == 4) {
                    r5 = getDisplayYSize() - (this.marginPxSizeBottom + this.marginPxSizeTop);
                    params.messageHeight = r5;
                }
            }
            int displayYSize = (getDisplayYSize() / 2) - (r5 / 2);
            params.dragThresholdY = DRAG_THRESHOLD_PX_SIZE + displayYSize;
            params.maxYPos = displayYSize;
            params.posY = displayYSize;
        }
        params.dragDirection = position == WebViewManager.Position.TOP_BANNER ? 0 : 1;
        return params;
    }

    private void showDraggableView(final WebViewManager.Position position, final RelativeLayout.LayoutParams layoutParams, final RelativeLayout.LayoutParams layoutParams2, final DraggableRelativeLayout.Params params) {
        OSUtils.runOnMainUIThread(new Runnable() { // from class: com.onesignal.InAppMessageView.2
            @Override // java.lang.Runnable
            public void run() {
                if (InAppMessageView.this.webView == null) {
                    return;
                }
                InAppMessageView.this.webView.setLayoutParams(layoutParams);
                Context applicationContext = InAppMessageView.this.currentActivity.getApplicationContext();
                InAppMessageView.this.setUpDraggableLayout(applicationContext, layoutParams2, params);
                InAppMessageView.this.setUpParentRelativeLayout(applicationContext);
                InAppMessageView inAppMessageView = InAppMessageView.this;
                inAppMessageView.createPopupWindow(inAppMessageView.parentRelativeLayout);
                if (InAppMessageView.this.messageController != null) {
                    InAppMessageView inAppMessageView2 = InAppMessageView.this;
                    inAppMessageView2.animateInAppMessage(position, inAppMessageView2.draggableRelativeLayout, InAppMessageView.this.parentRelativeLayout);
                }
                InAppMessageView.this.startDismissTimerIfNeeded();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003f, code lost:
        if (r5 != 4) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void createPopupWindow(android.widget.RelativeLayout r5) {
        /*
            r4 = this;
            android.widget.PopupWindow r0 = new android.widget.PopupWindow
            boolean r1 = r4.hasBackground
            r2 = -1
            if (r1 == 0) goto L9
            r3 = -1
            goto Lb
        L9:
            int r3 = r4.pageWidth
        Lb:
            if (r1 == 0) goto Le
            goto Lf
        Le:
            r2 = -2
        Lf:
            r1 = 1
            r0.<init>(r5, r3, r2, r1)
            r4.popupWindow = r0
            android.graphics.drawable.ColorDrawable r5 = new android.graphics.drawable.ColorDrawable
            r2 = 0
            r5.<init>(r2)
            r0.setBackgroundDrawable(r5)
            android.widget.PopupWindow r5 = r4.popupWindow
            r5.setTouchable(r1)
            android.widget.PopupWindow r5 = r4.popupWindow
            r5.setClippingEnabled(r2)
            boolean r5 = r4.hasBackground
            if (r5 != 0) goto L48
            int[] r5 = com.onesignal.InAppMessageView.C35249.$SwitchMap$com$onesignal$WebViewManager$Position
            com.onesignal.WebViewManager$Position r0 = r4.displayLocation
            int r0 = r0.ordinal()
            r5 = r5[r0]
            if (r5 == r1) goto L45
            r0 = 2
            if (r5 == r0) goto L42
            r0 = 3
            if (r5 == r0) goto L49
            r0 = 4
            if (r5 == r0) goto L49
            goto L48
        L42:
            r1 = 81
            goto L49
        L45:
            r1 = 49
            goto L49
        L48:
            r1 = 0
        L49:
            com.onesignal.OSInAppMessageContent r5 = r4.messageContent
            boolean r5 = r5.isFullBleed()
            if (r5 == 0) goto L54
            r5 = 1000(0x3e8, float:1.401E-42)
            goto L56
        L54:
            r5 = 1003(0x3eb, float:1.406E-42)
        L56:
            android.widget.PopupWindow r0 = r4.popupWindow
            androidx.core.widget.PopupWindowCompat.setWindowLayoutType(r0, r5)
            android.widget.PopupWindow r5 = r4.popupWindow
            android.app.Activity r0 = r4.currentActivity
            android.view.Window r0 = r0.getWindow()
            android.view.View r0 = r0.getDecorView()
            android.view.View r0 = r0.getRootView()
            r5.showAtLocation(r0, r1, r2, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.InAppMessageView.createPopupWindow(android.widget.RelativeLayout):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpParentRelativeLayout(Context context) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.parentRelativeLayout = relativeLayout;
        relativeLayout.setBackgroundDrawable(new ColorDrawable(0));
        this.parentRelativeLayout.setClipChildren(false);
        this.parentRelativeLayout.setClipToPadding(false);
        this.parentRelativeLayout.addView(this.draggableRelativeLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpDraggableLayout(Context context, RelativeLayout.LayoutParams layoutParams, DraggableRelativeLayout.Params params) {
        DraggableRelativeLayout draggableRelativeLayout = new DraggableRelativeLayout(context);
        this.draggableRelativeLayout = draggableRelativeLayout;
        if (layoutParams != null) {
            draggableRelativeLayout.setLayoutParams(layoutParams);
        }
        this.draggableRelativeLayout.setParams(params);
        this.draggableRelativeLayout.setListener(new DraggableRelativeLayout.DraggableListener() { // from class: com.onesignal.InAppMessageView.3
            @Override // com.onesignal.DraggableRelativeLayout.DraggableListener
            public void onDismiss() {
                if (InAppMessageView.this.messageController != null) {
                    InAppMessageView.this.messageController.onMessageWillDismiss();
                }
                InAppMessageView.this.finishAfterDelay(null);
            }

            @Override // com.onesignal.DraggableRelativeLayout.DraggableListener
            public void onDragStart() {
                InAppMessageView.this.isDragging = true;
            }

            @Override // com.onesignal.DraggableRelativeLayout.DraggableListener
            public void onDragEnd() {
                InAppMessageView.this.isDragging = false;
            }
        });
        if (this.webView.getParent() != null) {
            ((ViewGroup) this.webView.getParent()).removeAllViews();
        }
        CardView createCardView = createCardView(context);
        createCardView.setTag(IN_APP_MESSAGE_CARD_VIEW_TAG);
        createCardView.addView(this.webView);
        this.draggableRelativeLayout.setPadding(this.marginPxSizeLeft, this.marginPxSizeTop, this.marginPxSizeRight, this.marginPxSizeBottom);
        this.draggableRelativeLayout.setClipChildren(false);
        this.draggableRelativeLayout.setClipToPadding(false);
        this.draggableRelativeLayout.addView(createCardView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDragging() {
        return this.isDragging;
    }

    private CardView createCardView(Context context) {
        CardView cardView = new CardView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.displayLocation == WebViewManager.Position.FULL_SCREEN ? -1 : -2);
        layoutParams.addRule(13);
        cardView.setLayoutParams(layoutParams);
        if (Build.VERSION.SDK_INT == 23) {
            cardView.setCardElevation(0.0f);
        } else {
            cardView.setCardElevation(OSViewUtils.dpToPx(5));
        }
        cardView.setRadius(OSViewUtils.dpToPx(8));
        cardView.setClipChildren(false);
        cardView.setClipToPadding(false);
        cardView.setPreventCornerOverlap(false);
        cardView.setCardBackgroundColor(0);
        return cardView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDismissTimerIfNeeded() {
        if (this.displayDuration > 0.0d && this.scheduleDismissRunnable == null) {
            Runnable runnable = new Runnable() { // from class: com.onesignal.InAppMessageView.4
                @Override // java.lang.Runnable
                public void run() {
                    if (InAppMessageView.this.messageController != null) {
                        InAppMessageView.this.messageController.onMessageWillDismiss();
                    }
                    if (InAppMessageView.this.currentActivity == null) {
                        InAppMessageView.this.shouldDismissWhenActive = true;
                        return;
                    }
                    InAppMessageView.this.dismissAndAwaitNextMessage(null);
                    InAppMessageView.this.scheduleDismissRunnable = null;
                }
            };
            this.scheduleDismissRunnable = runnable;
            this.handler.postDelayed(runnable, ((long) this.displayDuration) * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delayShowUntilAvailable(final Activity activity) {
        if (OSViewUtils.isActivityFullyReady(activity) && this.parentRelativeLayout == null) {
            showInAppMessageView(activity);
        } else {
            new Handler().postDelayed(new Runnable() { // from class: com.onesignal.InAppMessageView.5
                @Override // java.lang.Runnable
                public void run() {
                    InAppMessageView.this.delayShowUntilAvailable(activity);
                }
            }, 200L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dismissAndAwaitNextMessage(WebViewManager.OneSignalGenericCallback oneSignalGenericCallback) {
        DraggableRelativeLayout draggableRelativeLayout = this.draggableRelativeLayout;
        if (draggableRelativeLayout == null) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "No host presenter to trigger dismiss animation, counting as dismissed already", new Throwable());
            dereferenceViews();
            if (oneSignalGenericCallback != null) {
                oneSignalGenericCallback.onComplete();
                return;
            }
            return;
        }
        draggableRelativeLayout.dismiss();
        finishAfterDelay(oneSignalGenericCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishAfterDelay(final WebViewManager.OneSignalGenericCallback oneSignalGenericCallback) {
        OSUtils.runOnMainThreadDelayed(new Runnable() { // from class: com.onesignal.InAppMessageView.6
            @Override // java.lang.Runnable
            public void run() {
                if (!InAppMessageView.this.hasBackground || InAppMessageView.this.parentRelativeLayout == null) {
                    InAppMessageView.this.cleanupViewsAfterDismiss();
                    WebViewManager.OneSignalGenericCallback oneSignalGenericCallback2 = oneSignalGenericCallback;
                    if (oneSignalGenericCallback2 != null) {
                        oneSignalGenericCallback2.onComplete();
                        return;
                    }
                    return;
                }
                InAppMessageView inAppMessageView = InAppMessageView.this;
                inAppMessageView.animateAndDismissLayout(inAppMessageView.parentRelativeLayout, oneSignalGenericCallback);
            }
        }, ACTIVITY_FINISH_AFTER_DISMISS_DELAY_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanupViewsAfterDismiss() {
        removeAllViews();
        InAppMessageViewListener inAppMessageViewListener = this.messageController;
        if (inAppMessageViewListener != null) {
            inAppMessageViewListener.onMessageWasDismissed();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeAllViews() {
        OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, "InAppMessageView removing views");
        Runnable runnable = this.scheduleDismissRunnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
            this.scheduleDismissRunnable = null;
        }
        DraggableRelativeLayout draggableRelativeLayout = this.draggableRelativeLayout;
        if (draggableRelativeLayout != null) {
            draggableRelativeLayout.removeAllViews();
        }
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        dereferenceViews();
    }

    private void dereferenceViews() {
        this.parentRelativeLayout = null;
        this.draggableRelativeLayout = null;
        this.webView = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateInAppMessage(WebViewManager.Position position, View view, View view2) {
        CardView cardView = (CardView) view.findViewWithTag(IN_APP_MESSAGE_CARD_VIEW_TAG);
        Animation.AnimationListener createAnimationListener = createAnimationListener(cardView);
        int r4 = C35249.$SwitchMap$com$onesignal$WebViewManager$Position[position.ordinal()];
        if (r4 == 1) {
            animateTop(cardView, this.webView.getHeight(), createAnimationListener);
        } else if (r4 == 2) {
            animateBottom(cardView, this.webView.getHeight(), createAnimationListener);
        } else if (r4 == 3 || r4 == 4) {
            animateCenter(view, view2, createAnimationListener, null);
        }
    }

    private Animation.AnimationListener createAnimationListener(final CardView cardView) {
        return new Animation.AnimationListener() { // from class: com.onesignal.InAppMessageView.7
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (Build.VERSION.SDK_INT == 23) {
                    cardView.setCardElevation(OSViewUtils.dpToPx(5));
                }
                if (InAppMessageView.this.messageController != null) {
                    InAppMessageView.this.messageController.onMessageWasShown();
                }
            }
        };
    }

    private void animateTop(View view, int r9, Animation.AnimationListener animationListener) {
        OneSignalAnimate.animateViewByTranslation(view, (-r9) - this.marginPxSizeTop, 0.0f, 1000, new OneSignalBounceInterpolator(0.1d, 8.0d), animationListener).start();
    }

    private void animateBottom(View view, int r9, Animation.AnimationListener animationListener) {
        OneSignalAnimate.animateViewByTranslation(view, r9 + this.marginPxSizeBottom, 0.0f, 1000, new OneSignalBounceInterpolator(0.1d, 8.0d), animationListener).start();
    }

    private void animateCenter(View view, View view2, Animation.AnimationListener animationListener, Animator.AnimatorListener animatorListener) {
        Animation animateViewSmallToLarge = OneSignalAnimate.animateViewSmallToLarge(view, 1000, new OneSignalBounceInterpolator(0.1d, 8.0d), animationListener);
        ValueAnimator animateBackgroundColor = animateBackgroundColor(view2, 400, ACTIVITY_BACKGROUND_COLOR_EMPTY, ACTIVITY_BACKGROUND_COLOR_FULL, animatorListener);
        animateViewSmallToLarge.start();
        animateBackgroundColor.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateAndDismissLayout(View view, final WebViewManager.OneSignalGenericCallback oneSignalGenericCallback) {
        animateBackgroundColor(view, 400, ACTIVITY_BACKGROUND_COLOR_FULL, ACTIVITY_BACKGROUND_COLOR_EMPTY, new AnimatorListenerAdapter() { // from class: com.onesignal.InAppMessageView.8
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                InAppMessageView.this.cleanupViewsAfterDismiss();
                WebViewManager.OneSignalGenericCallback oneSignalGenericCallback2 = oneSignalGenericCallback;
                if (oneSignalGenericCallback2 != null) {
                    oneSignalGenericCallback2.onComplete();
                }
            }
        }).start();
    }

    private ValueAnimator animateBackgroundColor(View view, int r2, int r3, int r4, Animator.AnimatorListener animatorListener) {
        return OneSignalAnimate.animateViewColor(view, r2, r3, r4, animatorListener);
    }

    public String toString() {
        return "InAppMessageView{currentActivity=" + this.currentActivity + ", pageWidth=" + this.pageWidth + ", pageHeight=" + this.pageHeight + ", displayDuration=" + this.displayDuration + ", hasBackground=" + this.hasBackground + ", shouldDismissWhenActive=" + this.shouldDismissWhenActive + ", isDragging=" + this.isDragging + ", disableDragDismiss=" + this.disableDragDismiss + ", displayLocation=" + this.displayLocation + ", webView=" + this.webView + '}';
    }
}
