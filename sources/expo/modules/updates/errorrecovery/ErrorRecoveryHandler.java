package expo.modules.updates.errorrecovery;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.errorrecovery.ErrorRecoveryDelegate;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogger;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ErrorRecoveryHandler.kt */
@Metadata(m184d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0003\"#$B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0014\u0010\u001d\u001a\u00020\u00152\n\u0010\u001e\u001a\u00060\u000bj\u0002`\fH\u0002J\b\u0010\u001f\u001a\u00020\u0015H\u0002J\b\u0010 \u001a\u00020\u0015H\u0002J\b\u0010!\u001a\u00020\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\t\u001a\u001a\u0012\b\u0012\u00060\u000bj\u0002`\f0\nj\f\u0012\b\u0012\u00060\u000bj\u0002`\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00130\nj\b\u0012\u0004\u0012\u00020\u0013`\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, m183d2 = {"Lexpo/modules/updates/errorrecovery/ErrorRecoveryHandler;", "Landroid/os/Handler;", "looper", "Landroid/os/Looper;", "delegate", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate;", "logger", "Lexpo/modules/updates/logging/UpdatesLogger;", "(Landroid/os/Looper;Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate;Lexpo/modules/updates/logging/UpdatesLogger;)V", "encounteredErrors", "Ljava/util/ArrayList;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lkotlin/collections/ArrayList;", "hasContentAppeared", "", "isPipelineRunning", "isWaitingForRemoteUpdate", "pipeline", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryHandler$Task;", "crash", "", "handleContentAppeared", "handleMessage", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "handleRemoteLoadStatusChanged", "newStatus", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate$RemoteLoadStatus;", "maybeStartPipeline", "exception", "runNextTask", "tryRelaunchFromCache", "waitForRemoteUpdate", "Companion", "MessageType", "Task", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ErrorRecoveryHandler extends Handler {
    public static final Companion Companion = new Companion(null);
    public static final long REMOTE_LOAD_TIMEOUT_MS = 5000;
    private final ErrorRecoveryDelegate delegate;
    private final ArrayList<Exception> encounteredErrors;
    private boolean hasContentAppeared;
    private boolean isPipelineRunning;
    private boolean isWaitingForRemoteUpdate;
    private final UpdatesLogger logger;
    private final ArrayList<Task> pipeline;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ErrorRecoveryHandler.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/updates/errorrecovery/ErrorRecoveryHandler$Task;", "", "(Ljava/lang/String;I)V", "WAIT_FOR_REMOTE_UPDATE", "LAUNCH_NEW_UPDATE", "LAUNCH_CACHED_UPDATE", "CRASH", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public enum Task {
        WAIT_FOR_REMOTE_UPDATE,
        LAUNCH_NEW_UPDATE,
        LAUNCH_CACHED_UPDATE,
        CRASH
    }

    /* compiled from: ErrorRecoveryHandler.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[Task.values().length];
            r0[Task.WAIT_FOR_REMOTE_UPDATE.ordinal()] = 1;
            r0[Task.LAUNCH_NEW_UPDATE.ordinal()] = 2;
            r0[Task.LAUNCH_CACHED_UPDATE.ordinal()] = 3;
            r0[Task.CRASH.ordinal()] = 4;
            $EnumSwitchMapping$0 = r0;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorRecoveryHandler(Looper looper, ErrorRecoveryDelegate delegate, UpdatesLogger logger) {
        super(looper);
        Intrinsics.checkNotNullParameter(looper, "looper");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.delegate = delegate;
        this.logger = logger;
        this.pipeline = CollectionsKt.arrayListOf(Task.WAIT_FOR_REMOTE_UPDATE, Task.LAUNCH_NEW_UPDATE, Task.LAUNCH_CACHED_UPDATE, Task.CRASH);
        this.encounteredErrors = new ArrayList<>();
    }

    /* compiled from: ErrorRecoveryHandler.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/updates/errorrecovery/ErrorRecoveryHandler$MessageType;", "", "()V", "CONTENT_APPEARED", "", "EXCEPTION_ENCOUNTERED", "REMOTE_LOAD_STATUS_CHANGED", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class MessageType {
        public static final int CONTENT_APPEARED = 1;
        public static final int EXCEPTION_ENCOUNTERED = 0;
        public static final MessageType INSTANCE = new MessageType();
        public static final int REMOTE_LOAD_STATUS_CHANGED = 2;

        private MessageType() {
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        int r0 = msg.what;
        if (r0 == 0) {
            Object obj = msg.obj;
            Objects.requireNonNull(obj, "null cannot be cast to non-null type java.lang.Exception{ kotlin.TypeAliasesKt.Exception }");
            maybeStartPipeline((Exception) obj);
        } else if (r0 == 1) {
            handleContentAppeared();
        } else if (r0 == 2) {
            Object obj2 = msg.obj;
            Objects.requireNonNull(obj2, "null cannot be cast to non-null type expo.modules.updates.errorrecovery.ErrorRecoveryDelegate.RemoteLoadStatus");
            handleRemoteLoadStatusChanged((ErrorRecoveryDelegate.RemoteLoadStatus) obj2);
        } else {
            int r4 = msg.what;
            throw new RuntimeException("ErrorRecoveryHandler cannot handle message " + r4);
        }
    }

    private final void handleContentAppeared() {
        this.hasContentAppeared = true;
        this.pipeline.retainAll(SetsKt.setOf((Object[]) new Task[]{Task.WAIT_FOR_REMOTE_UPDATE, Task.CRASH}));
        this.delegate.markSuccessfulLaunchForLaunchedUpdate();
    }

    private final void handleRemoteLoadStatusChanged(ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus) {
        if (this.isWaitingForRemoteUpdate) {
            this.isWaitingForRemoteUpdate = false;
            if (remoteLoadStatus != ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADED) {
                this.pipeline.remove(Task.LAUNCH_NEW_UPDATE);
            }
            runNextTask();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runNextTask() {
        Task remove = this.pipeline.remove(0);
        Intrinsics.checkNotNullExpressionValue(remove, "pipeline.removeAt(0)");
        Task task = remove;
        int r1 = WhenMappings.$EnumSwitchMapping$0[task.ordinal()];
        if (r1 == 1) {
            UpdatesLogger.info$default(this.logger, "UpdatesErrorRecovery: attempting to fetch a new update, waiting", null, 2, null);
            waitForRemoteUpdate();
        } else if (r1 == 2) {
            UpdatesLogger.info$default(this.logger, "UpdatesErrorRecovery: launching new update", null, 2, null);
            tryRelaunchFromCache();
        } else if (r1 == 3) {
            UpdatesLogger.info$default(this.logger, "UpdatesErrorRecovery: falling back to older update", null, 2, null);
            tryRelaunchFromCache();
        } else if (r1 == 4) {
            UpdatesLogger.error$default(this.logger, "UpdatesErrorRecovery: could not recover from error, crashing", UpdatesErrorCode.Unknown, null, 4, null);
            crash();
        } else {
            throw new RuntimeException("ErrorRecoveryHandler cannot perform task " + task);
        }
    }

    private final void maybeStartPipeline(Exception exc) {
        this.encounteredErrors.add(exc);
        if (this.delegate.getLaunchedUpdateSuccessfulLaunchCount() > 0) {
            this.pipeline.remove(Task.LAUNCH_CACHED_UPDATE);
        } else if (!this.hasContentAppeared) {
            this.delegate.markFailedLaunchForLaunchedUpdate();
        }
        if (this.isPipelineRunning) {
            return;
        }
        this.isPipelineRunning = true;
        runNextTask();
    }

    private final void waitForRemoteUpdate() {
        ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus = this.delegate.getRemoteLoadStatus();
        if (remoteLoadStatus == ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADED) {
            runNextTask();
        } else if (remoteLoadStatus == ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADING || this.delegate.getCheckAutomaticallyConfiguration() != UpdatesConfiguration.CheckAutomaticallyConfiguration.NEVER) {
            this.isWaitingForRemoteUpdate = true;
            if (this.delegate.getRemoteLoadStatus() != ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADING) {
                this.delegate.loadRemoteUpdate();
            }
            postDelayed(new Runnable() { // from class: expo.modules.updates.errorrecovery.ErrorRecoveryHandler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ErrorRecoveryHandler.m1729waitForRemoteUpdate$lambda0(ErrorRecoveryHandler.this);
                }
            }, 5000L);
        } else {
            this.pipeline.remove(Task.LAUNCH_NEW_UPDATE);
            runNextTask();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: waitForRemoteUpdate$lambda-0  reason: not valid java name */
    public static final void m1729waitForRemoteUpdate$lambda0(ErrorRecoveryHandler this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handleRemoteLoadStatusChanged(ErrorRecoveryDelegate.RemoteLoadStatus.IDLE);
    }

    private final void tryRelaunchFromCache() {
        this.delegate.relaunch(new ErrorRecoveryHandler$tryRelaunchFromCache$1(this));
    }

    private final void crash() {
        ErrorRecoveryDelegate errorRecoveryDelegate = this.delegate;
        Exception exc = this.encounteredErrors.get(0);
        Intrinsics.checkNotNullExpressionValue(exc, "encounteredErrors[0]");
        errorRecoveryDelegate.throwException(exc);
    }

    /* compiled from: ErrorRecoveryHandler.kt */
    @Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m183d2 = {"Lexpo/modules/updates/errorrecovery/ErrorRecoveryHandler$Companion;", "", "()V", "REMOTE_LOAD_TIMEOUT_MS", "", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
