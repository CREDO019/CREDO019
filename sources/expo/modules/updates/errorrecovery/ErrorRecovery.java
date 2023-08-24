package expo.modules.updates.errorrecovery;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import androidx.work.WorkRequest;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.DefaultJSExceptionHandler;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.devsupport.DisabledDevSupportManager;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import expo.modules.updates.errorrecovery.ErrorRecoveryDelegate;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogger;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ErrorRecovery.kt */
@Metadata(m184d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 +2\u00020\u0001:\u0001+B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001aJ\u0019\u0010\u001b\u001a\u00020\u00192\n\u0010\u001c\u001a\u00060\u001dj\u0002`\u001eH\u0000¢\u0006\u0002\b\u001fJ\u000e\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%J\b\u0010&\u001a\u00020\u0019H\u0002J\u0010\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0017H\u0002J\u000e\u0010)\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0017J\b\u0010*\u001a\u00020\u0019H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, m183d2 = {"Lexpo/modules/updates/errorrecovery/ErrorRecovery;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "handler", "Landroid/os/Handler;", "getHandler$expo_updates_release", "()Landroid/os/Handler;", "setHandler$expo_updates_release", "(Landroid/os/Handler;)V", "handlerThread", "Landroid/os/HandlerThread;", "getHandlerThread$expo_updates_release", "()Landroid/os/HandlerThread;", "logger", "Lexpo/modules/updates/logging/UpdatesLogger;", "getLogger$expo_updates_release", "()Lexpo/modules/updates/logging/UpdatesLogger;", "previousExceptionHandler", "Lcom/facebook/react/bridge/DefaultJSExceptionHandler;", "weakReactInstanceManager", "Ljava/lang/ref/WeakReference;", "Lcom/facebook/react/ReactInstanceManager;", "handleContentAppeared", "", "handleContentAppeared$expo_updates_release", "handleException", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleException$expo_updates_release", "initialize", "delegate", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate;", "notifyNewRemoteLoadStatus", "newStatus", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate$RemoteLoadStatus;", "registerContentAppearedListener", "registerErrorHandler", "reactInstanceManager", "startMonitoring", "unregisterErrorHandler", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ErrorRecovery {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ErrorRecovery";
    private final Context context;
    public Handler handler;
    private final HandlerThread handlerThread;
    private final UpdatesLogger logger;
    private DefaultJSExceptionHandler previousExceptionHandler;
    private WeakReference<ReactInstanceManager> weakReactInstanceManager;

    public ErrorRecovery(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.handlerThread = new HandlerThread("expo-updates-error-recovery");
        this.logger = new UpdatesLogger(context);
    }

    public final HandlerThread getHandlerThread$expo_updates_release() {
        return this.handlerThread;
    }

    public final Handler getHandler$expo_updates_release() {
        Handler handler = this.handler;
        if (handler != null) {
            return handler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("handler");
        return null;
    }

    public final void setHandler$expo_updates_release(Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "<set-?>");
        this.handler = handler;
    }

    public final UpdatesLogger getLogger$expo_updates_release() {
        return this.logger;
    }

    public final void initialize(ErrorRecoveryDelegate delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        if (this.handler == null) {
            this.handlerThread.start();
            Looper looper = this.handlerThread.getLooper();
            Intrinsics.checkNotNullExpressionValue(looper, "handlerThread.looper");
            setHandler$expo_updates_release(new ErrorRecoveryHandler(looper, delegate, this.logger));
        }
    }

    public final void startMonitoring(ReactInstanceManager reactInstanceManager) {
        Intrinsics.checkNotNullParameter(reactInstanceManager, "reactInstanceManager");
        registerContentAppearedListener();
        registerErrorHandler(reactInstanceManager);
    }

    public final void notifyNewRemoteLoadStatus(ErrorRecoveryDelegate.RemoteLoadStatus newStatus) {
        Intrinsics.checkNotNullParameter(newStatus, "newStatus");
        UpdatesLogger updatesLogger = this.logger;
        UpdatesLogger.info$default(updatesLogger, "ErrorRecovery: remote load status changed: " + newStatus, null, 2, null);
        getHandler$expo_updates_release().sendMessage(getHandler$expo_updates_release().obtainMessage(2, newStatus));
    }

    public final void handleException$expo_updates_release(Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        UpdatesLogger updatesLogger = this.logger;
        String localizedMessage = exception.getLocalizedMessage();
        updatesLogger.error("ErrorRecovery: exception encountered: " + localizedMessage, UpdatesErrorCode.Unknown, exception);
        getHandler$expo_updates_release().sendMessage(getHandler$expo_updates_release().obtainMessage(0, exception));
    }

    public final void handleContentAppeared$expo_updates_release() {
        getHandler$expo_updates_release().sendMessage(getHandler$expo_updates_release().obtainMessage(1));
        getHandler$expo_updates_release().postDelayed(new Runnable() { // from class: expo.modules.updates.errorrecovery.ErrorRecovery$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ErrorRecovery.m1726handleContentAppeared$lambda0(ErrorRecovery.this);
            }
        }, WorkRequest.MIN_BACKOFF_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleContentAppeared$lambda-0  reason: not valid java name */
    public static final void m1726handleContentAppeared$lambda0(ErrorRecovery this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.unregisterErrorHandler();
    }

    private final void registerContentAppearedListener() {
        ReactMarker.addListener(new ReactMarker.MarkerListener() { // from class: expo.modules.updates.errorrecovery.ErrorRecovery$$ExternalSyntheticLambda0
            @Override // com.facebook.react.bridge.ReactMarker.MarkerListener
            public final void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int r4) {
                ErrorRecovery.m1727registerContentAppearedListener$lambda1(ErrorRecovery.this, reactMarkerConstants, str, r4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: registerContentAppearedListener$lambda-1  reason: not valid java name */
    public static final void m1727registerContentAppearedListener$lambda1(ErrorRecovery this$0, ReactMarkerConstants reactMarkerConstants, String str, int r3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (reactMarkerConstants == ReactMarkerConstants.CONTENT_APPEARED) {
            this$0.handleContentAppeared$expo_updates_release();
        }
    }

    private final void registerErrorHandler(ReactInstanceManager reactInstanceManager) {
        if (!(reactInstanceManager.getDevSupportManager() instanceof DisabledDevSupportManager)) {
            Log.d(TAG, "Unexpected type of ReactInstanceManager.DevSupportManager. expo-updates error recovery will not behave properly.");
            return;
        }
        DevSupportManager devSupportManager = reactInstanceManager.getDevSupportManager();
        Objects.requireNonNull(devSupportManager, "null cannot be cast to non-null type com.facebook.react.devsupport.DisabledDevSupportManager");
        DisabledDevSupportManager disabledDevSupportManager = (DisabledDevSupportManager) devSupportManager;
        DefaultJSExceptionHandler defaultJSExceptionHandler = new DefaultJSExceptionHandler() { // from class: expo.modules.updates.errorrecovery.ErrorRecovery$registerErrorHandler$defaultJSExceptionHandler$1
            @Override // com.facebook.react.bridge.DefaultJSExceptionHandler, com.facebook.react.bridge.JSExceptionHandler
            public void handleException(Exception exc) {
                ErrorRecovery errorRecovery = ErrorRecovery.this;
                Intrinsics.checkNotNull(exc);
                errorRecovery.handleException$expo_updates_release(exc);
            }
        };
        Field declaredField = disabledDevSupportManager.getClass().getDeclaredField("mDefaultJSExceptionHandler");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(disabledDevSupportManager);
        declaredField.set(disabledDevSupportManager, defaultJSExceptionHandler);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type com.facebook.react.bridge.DefaultJSExceptionHandler");
        this.previousExceptionHandler = (DefaultJSExceptionHandler) obj;
        this.weakReactInstanceManager = new WeakReference<>(reactInstanceManager);
    }

    private final void unregisterErrorHandler() {
        ReactInstanceManager reactInstanceManager;
        WeakReference<ReactInstanceManager> weakReference = this.weakReactInstanceManager;
        if (weakReference != null && (reactInstanceManager = weakReference.get()) != null) {
            if (!(reactInstanceManager.getDevSupportManager() instanceof DisabledDevSupportManager)) {
                Log.d(TAG, "Unexpected type of ReactInstanceManager.DevSupportManager. expo-updates could not unregister its error handler");
                return;
            } else if (this.previousExceptionHandler == null) {
                return;
            } else {
                DevSupportManager devSupportManager = reactInstanceManager.getDevSupportManager();
                Objects.requireNonNull(devSupportManager, "null cannot be cast to non-null type com.facebook.react.devsupport.DisabledDevSupportManager");
                DisabledDevSupportManager disabledDevSupportManager = (DisabledDevSupportManager) devSupportManager;
                Field declaredField = disabledDevSupportManager.getClass().getDeclaredField("mDefaultJSExceptionHandler");
                declaredField.setAccessible(true);
                declaredField.set(disabledDevSupportManager, this.previousExceptionHandler);
                this.weakReactInstanceManager = null;
            }
        }
        getHandler$expo_updates_release().postDelayed(new Runnable() { // from class: expo.modules.updates.errorrecovery.ErrorRecovery$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ErrorRecovery.m1728unregisterErrorHandler$lambda5(ErrorRecovery.this);
            }
        }, WorkRequest.MIN_BACKOFF_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: unregisterErrorHandler$lambda-5  reason: not valid java name */
    public static final void m1728unregisterErrorHandler$lambda5(ErrorRecovery this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handlerThread.quitSafely();
    }

    /* compiled from: ErrorRecovery.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/errorrecovery/ErrorRecovery$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
