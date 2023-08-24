package expo.modules.p019av.progress;

import android.os.Handler;
import com.onesignal.influence.OSInfluenceConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidLooperTimeMachine.kt */
@Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0010\u0010\n\u001a\f\u0012\u0004\u0012\u00020\b0\u000bj\u0002`\fH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, m183d2 = {"Lexpo/modules/av/progress/AndroidLooperTimeMachine;", "Lexpo/modules/av/progress/TimeMachine;", "()V", OSInfluenceConstants.TIME, "", "getTime", "()J", "scheduleAt", "", "intervalMillis", "callback", "Lkotlin/Function0;", "Lexpo/modules/av/progress/TimeMachineTick;", "expo-av_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.av.progress.AndroidLooperTimeMachine */
/* loaded from: classes4.dex */
public final class AndroidLooperTimeMachine implements TimeMachine {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: scheduleAt$lambda-0  reason: not valid java name */
    public static final void m1593scheduleAt$lambda0(Functions tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    @Override // expo.modules.p019av.progress.TimeMachine
    public void scheduleAt(long j, final Functions<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        new Handler().postDelayed(new Runnable() { // from class: expo.modules.av.progress.AndroidLooperTimeMachine$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AndroidLooperTimeMachine.m1593scheduleAt$lambda0(Functions.this);
            }
        }, j);
    }

    @Override // expo.modules.p019av.progress.TimeMachine
    public long getTime() {
        return System.currentTimeMillis();
    }
}
