package com.onesignal;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.RNFetchBlob.RNFetchBlobConst;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.onesignal.ActivityLifecycleHandler;
import com.onesignal.InAppMessageView;
import com.onesignal.OneSignal;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class WebViewManager extends ActivityLifecycleHandler.ActivityAvailableListener {
    private static final int IN_APP_MESSAGE_INIT_DELAY = 200;
    private static final String TAG = "com.onesignal.WebViewManager";
    private Activity activity;
    private OSInAppMessageInternal message;
    private OSInAppMessageContent messageContent;
    private InAppMessageView messageView;
    private OSWebView webView;
    private static final int MARGIN_PX_SIZE = OSViewUtils.dpToPx(24);
    protected static WebViewManager lastInstance = null;
    private final Object messageViewSyncLock = new Object() { // from class: com.onesignal.WebViewManager.1
    };
    private String currentActivityName = null;
    private Integer lastPageHeight = null;
    private boolean dismissFired = false;
    private boolean closing = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface OneSignalGenericCallback {
        void onComplete();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.onesignal.WebViewManager$10 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C366410 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$WebViewManager$Position;

        static {
            int[] r0 = new int[Position.values().length];
            $SwitchMap$com$onesignal$WebViewManager$Position = r0;
            try {
                r0[Position.TOP_BANNER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$onesignal$WebViewManager$Position[Position.BOTTOM_BANNER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum Position {
        TOP_BANNER,
        BOTTOM_BANNER,
        CENTER_MODAL,
        FULL_SCREEN;

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isBanner() {
            int r0 = C366410.$SwitchMap$com$onesignal$WebViewManager$Position[ordinal()];
            return r0 == 1 || r0 == 2;
        }
    }

    protected WebViewManager(OSInAppMessageInternal oSInAppMessageInternal, Activity activity, OSInAppMessageContent oSInAppMessageContent) {
        this.message = oSInAppMessageInternal;
        this.activity = activity;
        this.messageContent = oSInAppMessageContent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void showMessageContent(final OSInAppMessageInternal oSInAppMessageInternal, final OSInAppMessageContent oSInAppMessageContent) {
        final Activity currentActivity = OneSignal.getCurrentActivity();
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.onesignalLog(log_level, "in app message showMessageContent on currentActivity: " + currentActivity);
        if (currentActivity != null) {
            if (lastInstance != null && oSInAppMessageInternal.isPreview) {
                lastInstance.dismissAndAwaitNextMessage(new OneSignalGenericCallback() { // from class: com.onesignal.WebViewManager.2
                    @Override // com.onesignal.WebViewManager.OneSignalGenericCallback
                    public void onComplete() {
                        WebViewManager.lastInstance = null;
                        WebViewManager.initInAppMessage(currentActivity, oSInAppMessageInternal, oSInAppMessageContent);
                    }
                });
                return;
            } else {
                initInAppMessage(currentActivity, oSInAppMessageInternal, oSInAppMessageContent);
                return;
            }
        }
        Looper.prepare();
        new Handler().postDelayed(new Runnable() { // from class: com.onesignal.WebViewManager.3
            @Override // java.lang.Runnable
            public void run() {
                WebViewManager.showMessageContent(OSInAppMessageInternal.this, oSInAppMessageContent);
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void dismissCurrentInAppMessage() {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.onesignalLog(log_level, "WebViewManager IAM dismissAndAwaitNextMessage lastInstance: " + lastInstance);
        WebViewManager webViewManager = lastInstance;
        if (webViewManager != null) {
            webViewManager.dismissAndAwaitNextMessage(null);
        }
    }

    private static void setContentSafeAreaInsets(OSInAppMessageContent oSInAppMessageContent, Activity activity) {
        String contentHtml = oSInAppMessageContent.getContentHtml();
        int[] cutoutAndStatusBarInsets = OSViewUtils.getCutoutAndStatusBarInsets(activity);
        String format = String.format("\n\n<script>\n    setSafeAreaInsets(%s);\n</script>", String.format("{\n   top: %d,\n   bottom: %d,\n   right: %d,\n   left: %d,\n}", Integer.valueOf(cutoutAndStatusBarInsets[0]), Integer.valueOf(cutoutAndStatusBarInsets[1]), Integer.valueOf(cutoutAndStatusBarInsets[2]), Integer.valueOf(cutoutAndStatusBarInsets[3])));
        oSInAppMessageContent.setContentHtml(contentHtml + format);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initInAppMessage(final Activity activity, OSInAppMessageInternal oSInAppMessageInternal, final OSInAppMessageContent oSInAppMessageContent) {
        if (oSInAppMessageContent.isFullBleed()) {
            setContentSafeAreaInsets(oSInAppMessageContent, activity);
        }
        try {
            final String encodeToString = Base64.encodeToString(oSInAppMessageContent.getContentHtml().getBytes("UTF-8"), 2);
            WebViewManager webViewManager = new WebViewManager(oSInAppMessageInternal, activity, oSInAppMessageContent);
            lastInstance = webViewManager;
            OSUtils.runOnMainUIThread(new Runnable() { // from class: com.onesignal.WebViewManager.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        WebViewManager.this.setupWebView(activity, encodeToString, oSInAppMessageContent.isFullBleed());
                    } catch (Exception e) {
                        if (e.getMessage() != null && e.getMessage().contains("No WebView installed")) {
                            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error setting up WebView: ", e);
                            return;
                        }
                        throw e;
                    }
                }
            });
        } catch (UnsupportedEncodingException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Catch on initInAppMessage: ", e);
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class OSJavaScriptInterface {
        static final String EVENT_TYPE_ACTION_TAKEN = "action_taken";
        static final String EVENT_TYPE_KEY = "type";
        static final String EVENT_TYPE_PAGE_CHANGE = "page_change";
        static final String EVENT_TYPE_RENDERING_COMPLETE = "rendering_complete";
        static final String EVENT_TYPE_RESIZE = "resize";
        static final String GET_PAGE_META_DATA_JS_FUNCTION = "getPageMetaData()";
        static final String IAM_DISPLAY_LOCATION_KEY = "displayLocation";
        static final String IAM_DRAG_TO_DISMISS_DISABLED_KEY = "dragToDismissDisabled";
        static final String IAM_PAGE_META_DATA_KEY = "pageMetaData";
        static final String JS_OBJ_NAME = "OSAndroid";
        static final String SAFE_AREA_JS_OBJECT = "{\n   top: %d,\n   bottom: %d,\n   right: %d,\n   left: %d,\n}";
        static final String SET_SAFE_AREA_INSETS_JS_FUNCTION = "setSafeAreaInsets(%s)";
        static final String SET_SAFE_AREA_INSETS_SCRIPT = "\n\n<script>\n    setSafeAreaInsets(%s);\n</script>";

        OSJavaScriptInterface() {
        }

        @JavascriptInterface
        public void postMessage(String str) {
            try {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                OneSignal.onesignalLog(log_level, "OSJavaScriptInterface:postMessage: " + str);
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("type");
                char c = 65535;
                switch (string.hashCode()) {
                    case -1484226720:
                        if (string.equals(EVENT_TYPE_PAGE_CHANGE)) {
                            c = 3;
                            break;
                        }
                        break;
                    case -934437708:
                        if (string.equals(EVENT_TYPE_RESIZE)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 42998156:
                        if (string.equals(EVENT_TYPE_RENDERING_COMPLETE)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1851145598:
                        if (string.equals(EVENT_TYPE_ACTION_TAKEN)) {
                            c = 1;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    handleRenderComplete(jSONObject);
                } else if (c != 1) {
                    if (c != 3) {
                        return;
                    }
                    handlePageChange(jSONObject);
                } else if (WebViewManager.this.messageView.isDragging()) {
                } else {
                    handleActionTaken(jSONObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private void handleRenderComplete(JSONObject jSONObject) {
            Position displayLocation = getDisplayLocation(jSONObject);
            int pageHeightData = displayLocation == Position.FULL_SCREEN ? -1 : getPageHeightData(jSONObject);
            boolean dragToDismissDisabled = getDragToDismissDisabled(jSONObject);
            WebViewManager.this.messageContent.setDisplayLocation(displayLocation);
            WebViewManager.this.messageContent.setPageHeight(pageHeightData);
            WebViewManager.this.createNewInAppMessageView(dragToDismissDisabled);
        }

        private int getPageHeightData(JSONObject jSONObject) {
            try {
                WebViewManager webViewManager = WebViewManager.this;
                return webViewManager.pageRectToViewHeight(webViewManager.activity, jSONObject.getJSONObject(IAM_PAGE_META_DATA_KEY));
            } catch (JSONException unused) {
                return -1;
            }
        }

        private Position getDisplayLocation(JSONObject jSONObject) {
            Position position = Position.FULL_SCREEN;
            try {
                return (!jSONObject.has(IAM_DISPLAY_LOCATION_KEY) || jSONObject.get(IAM_DISPLAY_LOCATION_KEY).equals("")) ? position : Position.valueOf(jSONObject.optString(IAM_DISPLAY_LOCATION_KEY, "FULL_SCREEN").toUpperCase());
            } catch (JSONException e) {
                e.printStackTrace();
                return position;
            }
        }

        private boolean getDragToDismissDisabled(JSONObject jSONObject) {
            try {
                return jSONObject.getBoolean(IAM_DRAG_TO_DISMISS_DISABLED_KEY);
            } catch (JSONException unused) {
                return false;
            }
        }

        private void handleActionTaken(JSONObject jSONObject) throws JSONException {
            JSONObject jSONObject2 = jSONObject.getJSONObject(TtmlNode.TAG_BODY);
            String optString = jSONObject2.optString("id", null);
            WebViewManager.this.closing = jSONObject2.getBoolean("close");
            if (WebViewManager.this.message.isPreview) {
                OneSignal.getInAppMessageController().onMessageActionOccurredOnPreview(WebViewManager.this.message, jSONObject2);
            } else if (optString != null) {
                OneSignal.getInAppMessageController().onMessageActionOccurredOnMessage(WebViewManager.this.message, jSONObject2);
            }
            if (WebViewManager.this.closing) {
                WebViewManager.this.dismissAndAwaitNextMessage(null);
            }
        }

        private void handlePageChange(JSONObject jSONObject) throws JSONException {
            OneSignal.getInAppMessageController().onPageChanged(WebViewManager.this.message, jSONObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int pageRectToViewHeight(Activity activity, JSONObject jSONObject) {
        try {
            int dpToPx = OSViewUtils.dpToPx(jSONObject.getJSONObject("rect").getInt("height"));
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.onesignalLog(log_level, "getPageHeightData:pxHeight: " + dpToPx);
            int webViewMaxSizeY = getWebViewMaxSizeY(activity);
            if (dpToPx > webViewMaxSizeY) {
                OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.DEBUG;
                OneSignal.Log(log_level2, "getPageHeightData:pxHeight is over screen max: " + webViewMaxSizeY);
                return webViewMaxSizeY;
            }
            return dpToPx;
        } catch (JSONException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "pageRectToViewHeight could not get page height", e);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSafeAreaInsets() {
        OSUtils.runOnMainUIThread(new Runnable() { // from class: com.onesignal.WebViewManager.5
            @Override // java.lang.Runnable
            public void run() {
                int[] cutoutAndStatusBarInsets = OSViewUtils.getCutoutAndStatusBarInsets(WebViewManager.this.activity);
                WebViewManager.this.webView.evaluateJavascript(String.format("setSafeAreaInsets(%s)", String.format("{\n   top: %d,\n   bottom: %d,\n   right: %d,\n   left: %d,\n}", Integer.valueOf(cutoutAndStatusBarInsets[0]), Integer.valueOf(cutoutAndStatusBarInsets[1]), Integer.valueOf(cutoutAndStatusBarInsets[2]), Integer.valueOf(cutoutAndStatusBarInsets[3]))), null);
            }
        });
    }

    private void calculateHeightAndShowWebViewAfterNewActivity() {
        InAppMessageView inAppMessageView = this.messageView;
        if (inAppMessageView == null) {
            return;
        }
        if (inAppMessageView.getDisplayPosition() == Position.FULL_SCREEN && !this.messageContent.isFullBleed()) {
            showMessageView(null);
            return;
        }
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "In app message new activity, calculate height and show ");
        OSViewUtils.decorViewReady(this.activity, new Runnable() { // from class: com.onesignal.WebViewManager.6
            @Override // java.lang.Runnable
            public void run() {
                WebViewManager webViewManager = WebViewManager.this;
                webViewManager.setWebViewToMaxSize(webViewManager.activity);
                if (WebViewManager.this.messageContent.isFullBleed()) {
                    WebViewManager.this.updateSafeAreaInsets();
                }
                WebViewManager.this.webView.evaluateJavascript("getPageMetaData()", new ValueCallback<String>() { // from class: com.onesignal.WebViewManager.6.1
                    @Override // android.webkit.ValueCallback
                    public void onReceiveValue(String str) {
                        try {
                            WebViewManager.this.showMessageView(Integer.valueOf(WebViewManager.this.pageRectToViewHeight(WebViewManager.this.activity, new JSONObject(str))));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override // com.onesignal.ActivityLifecycleHandler.ActivityAvailableListener
    void available(Activity activity) {
        String str = this.currentActivityName;
        this.activity = activity;
        this.currentActivityName = activity.getLocalClassName();
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "In app message activity available currentActivityName: " + this.currentActivityName + " lastActivityName: " + str);
        if (str == null) {
            showMessageView(null);
        } else if (!str.equals(this.currentActivityName)) {
            if (this.closing) {
                return;
            }
            InAppMessageView inAppMessageView = this.messageView;
            if (inAppMessageView != null) {
                inAppMessageView.removeAllViews();
            }
            showMessageView(this.lastPageHeight);
        } else {
            calculateHeightAndShowWebViewAfterNewActivity();
        }
    }

    @Override // com.onesignal.ActivityLifecycleHandler.ActivityAvailableListener
    void stopped(Activity activity) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "In app message activity stopped, cleaning views, currentActivityName: " + this.currentActivityName + "\nactivity: " + this.activity + "\nmessageView: " + this.messageView);
        if (this.messageView == null || !activity.getLocalClassName().equals(this.currentActivityName)) {
            return;
        }
        this.messageView.removeAllViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMessageView(Integer num) {
        synchronized (this.messageViewSyncLock) {
            if (this.messageView == null) {
                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "No messageView found to update a with a new height.");
                return;
            }
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, "In app message, showing first one with height: " + num);
            this.messageView.setWebView(this.webView);
            if (num != null) {
                this.lastPageHeight = num;
                this.messageView.updateHeight(num.intValue());
            }
            this.messageView.showView(this.activity);
            this.messageView.checkIfShouldDismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupWebView(final Activity activity, final String str, boolean z) {
        enableWebViewRemoteDebugging();
        OSWebView oSWebView = new OSWebView(activity);
        this.webView = oSWebView;
        oSWebView.setOverScrollMode(2);
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.addJavascriptInterface(new OSJavaScriptInterface(), "OSAndroid");
        if (z) {
            this.webView.setSystemUiVisibility(3074);
            if (Build.VERSION.SDK_INT >= 30) {
                this.webView.setFitsSystemWindows(false);
            }
        }
        blurryRenderingWebViewForKitKatWorkAround(this.webView);
        OSViewUtils.decorViewReady(activity, new Runnable() { // from class: com.onesignal.WebViewManager.7
            @Override // java.lang.Runnable
            public void run() {
                WebViewManager.this.setWebViewToMaxSize(activity);
                WebViewManager.this.webView.loadData(str, "text/html; charset=utf-8", RNFetchBlobConst.RNFB_RESPONSE_BASE64);
            }
        });
    }

    private void blurryRenderingWebViewForKitKatWorkAround(WebView webView) {
        if (Build.VERSION.SDK_INT == 19) {
            webView.setLayerType(1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWebViewToMaxSize(Activity activity) {
        this.webView.layout(0, 0, getWebViewMaxSizeX(activity), getWebViewMaxSizeY(activity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMessageView(InAppMessageView inAppMessageView) {
        synchronized (this.messageViewSyncLock) {
            this.messageView = inAppMessageView;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createNewInAppMessageView(boolean z) {
        this.lastPageHeight = Integer.valueOf(this.messageContent.getPageHeight());
        setMessageView(new InAppMessageView(this.webView, this.messageContent, z));
        this.messageView.setMessageController(new InAppMessageView.InAppMessageViewListener() { // from class: com.onesignal.WebViewManager.8
            @Override // com.onesignal.InAppMessageView.InAppMessageViewListener
            public void onMessageWasShown() {
                OneSignal.getInAppMessageController().onMessageWasShown(WebViewManager.this.message);
            }

            @Override // com.onesignal.InAppMessageView.InAppMessageViewListener
            public void onMessageWillDismiss() {
                OneSignal.getInAppMessageController().onMessageWillDismiss(WebViewManager.this.message);
            }

            @Override // com.onesignal.InAppMessageView.InAppMessageViewListener
            public void onMessageWasDismissed() {
                OneSignal.getInAppMessageController().messageWasDismissed(WebViewManager.this.message);
                WebViewManager.this.removeActivityListener();
            }
        });
        ActivityLifecycleHandler activityLifecycleHandler = ActivityLifecycleListener.getActivityLifecycleHandler();
        if (activityLifecycleHandler != null) {
            activityLifecycleHandler.addActivityAvailableListener(TAG + this.message.messageId, this);
        }
    }

    private static void enableWebViewRemoteDebugging() {
        if (Build.VERSION.SDK_INT < 19 || !OneSignal.atLogLevel(OneSignal.LOG_LEVEL.DEBUG)) {
            return;
        }
        WebView.setWebContentsDebuggingEnabled(true);
    }

    private int getWebViewMaxSizeX(Activity activity) {
        if (this.messageContent.isFullBleed()) {
            return OSViewUtils.getFullbleedWindowWidth(activity);
        }
        return OSViewUtils.getWindowWidth(activity) - (MARGIN_PX_SIZE * 2);
    }

    private int getWebViewMaxSizeY(Activity activity) {
        return OSViewUtils.getWindowHeight(activity) - (this.messageContent.isFullBleed() ? 0 : MARGIN_PX_SIZE * 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeActivityListener() {
        ActivityLifecycleHandler activityLifecycleHandler = ActivityLifecycleListener.getActivityLifecycleHandler();
        if (activityLifecycleHandler != null) {
            activityLifecycleHandler.removeActivityAvailableListener(TAG + this.message.messageId);
        }
    }

    protected void dismissAndAwaitNextMessage(final OneSignalGenericCallback oneSignalGenericCallback) {
        InAppMessageView inAppMessageView = this.messageView;
        if (inAppMessageView == null || this.dismissFired) {
            if (oneSignalGenericCallback != null) {
                oneSignalGenericCallback.onComplete();
                return;
            }
            return;
        }
        if (this.message != null && inAppMessageView != null) {
            OneSignal.getInAppMessageController().onMessageWillDismiss(this.message);
        }
        this.messageView.dismissAndAwaitNextMessage(new OneSignalGenericCallback() { // from class: com.onesignal.WebViewManager.9
            @Override // com.onesignal.WebViewManager.OneSignalGenericCallback
            public void onComplete() {
                WebViewManager.this.dismissFired = false;
                WebViewManager.this.setMessageView(null);
                OneSignalGenericCallback oneSignalGenericCallback2 = oneSignalGenericCallback;
                if (oneSignalGenericCallback2 != null) {
                    oneSignalGenericCallback2.onComplete();
                }
            }
        });
        this.dismissFired = true;
    }
}
