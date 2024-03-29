package com.onesignal;

import com.onesignal.OneSignal;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSDynamicTriggerTimer {
    OSDynamicTriggerTimer() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void scheduleTrigger(TimerTask timerTask, String str, long j) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.onesignalLog(log_level, "scheduleTrigger: " + str + " delay: " + j);
        StringBuilder sb = new StringBuilder();
        sb.append("trigger_timer:");
        sb.append(str);
        new Timer(sb.toString()).schedule(timerTask, j);
    }
}
