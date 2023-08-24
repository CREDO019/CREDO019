package expo.modules.p019av.progress;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProgressLooper.kt */
@Metadata(m184d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0006H\u0002J\u0006\u0010\u0015\u001a\u00020\rJ \u0010\u0016\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0007\u001a\f\u0012\u0004\u0012\u00020\t0\bj\u0002`\nJ\b\u0010\u0017\u001a\u00020\tH\u0002J\u0018\u0010\u0018\u001a\u00020\t2\u0010\u0010\u0007\u001a\f\u0012\u0004\u0012\u00020\t0\bj\u0002`\nJ\u0006\u0010\u0019\u001a\u00020\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m183d2 = {"Lexpo/modules/av/progress/ProgressLooper;", "", "timeMachine", "Lexpo/modules/av/progress/TimeMachine;", "(Lexpo/modules/av/progress/TimeMachine;)V", "interval", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function0;", "", "Lexpo/modules/av/progress/PlayerProgressListener;", "nextExpectedTick", "value", "", "shouldLoop", "getShouldLoop", "()Z", "setShouldLoop", "(Z)V", "waiting", "calculateNextInterval", "isLooping", "loop", "scheduleNextTick", "setListener", "stopLooping", "expo-av_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.av.progress.ProgressLooper */
/* loaded from: classes4.dex */
public final class ProgressLooper {
    private long interval;
    private Functions<Unit> listener;
    private long nextExpectedTick;
    private final TimeMachine timeMachine;
    private boolean waiting;

    public ProgressLooper(TimeMachine timeMachine) {
        Intrinsics.checkNotNullParameter(timeMachine, "timeMachine");
        this.timeMachine = timeMachine;
        this.nextExpectedTick = -1L;
    }

    private final boolean getShouldLoop() {
        return this.interval > 0 && this.nextExpectedTick >= 0 && !this.waiting;
    }

    private final void setShouldLoop(boolean z) {
        if (z) {
            return;
        }
        this.interval = 0L;
        this.nextExpectedTick = -1L;
        this.waiting = false;
    }

    public final void setListener(Functions<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    public final void loop(long j, Functions<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
        this.interval = j;
        scheduleNextTick();
    }

    public final void stopLooping() {
        setShouldLoop(false);
        this.listener = null;
    }

    public final boolean isLooping() {
        return this.interval > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleNextTick() {
        if (this.nextExpectedTick == -1) {
            this.nextExpectedTick = this.timeMachine.getTime();
        }
        if (getShouldLoop()) {
            long calculateNextInterval = this.nextExpectedTick + calculateNextInterval();
            this.nextExpectedTick = calculateNextInterval;
            this.waiting = true;
            TimeMachine timeMachine = this.timeMachine;
            timeMachine.scheduleAt(calculateNextInterval - timeMachine.getTime(), new Functions<Unit>() { // from class: expo.modules.av.progress.ProgressLooper$scheduleNextTick$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Functions
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Functions functions;
                    ProgressLooper.this.waiting = false;
                    functions = ProgressLooper.this.listener;
                    if (functions != null) {
                        functions.invoke();
                    }
                    ProgressLooper.this.scheduleNextTick();
                }
            });
        }
    }

    private final long calculateNextInterval() {
        if (this.nextExpectedTick > this.timeMachine.getTime()) {
            return this.interval;
        }
        long time = this.timeMachine.getTime() - this.nextExpectedTick;
        long j = this.interval;
        return ((time / j) + 1) * j;
    }
}
