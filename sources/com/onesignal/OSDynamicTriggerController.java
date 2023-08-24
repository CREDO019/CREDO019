package com.onesignal;

import com.onesignal.OSTrigger;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import java.util.Date;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSDynamicTriggerController {
    private static final long DEFAULT_LAST_IN_APP_TIME_AGO = 999999;
    private static final double REQUIRED_ACCURACY = 0.3d;
    private static Date sessionLaunchTime = new Date();
    private final OSDynamicTriggerControllerObserver observer;
    private final ArrayList<String> scheduledMessages = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface OSDynamicTriggerControllerObserver {
        void messageDynamicTriggerCompleted(String str);

        void messageTriggerConditionChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSDynamicTriggerController(OSDynamicTriggerControllerObserver oSDynamicTriggerControllerObserver) {
        this.observer = oSDynamicTriggerControllerObserver;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0077 A[Catch: all -> 0x009e, TryCatch #0 {, blocks: (B:7:0x0009, B:9:0x000f, B:11:0x0011, B:26:0x005b, B:28:0x0077, B:29:0x007c, B:31:0x007e, B:33:0x0083, B:35:0x0085, B:37:0x008d, B:39:0x008f, B:40:0x009c, B:16:0x0025, B:18:0x002f, B:20:0x0031, B:23:0x003d, B:25:0x005a, B:24:0x004b), top: B:45:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007e A[Catch: all -> 0x009e, TryCatch #0 {, blocks: (B:7:0x0009, B:9:0x000f, B:11:0x0011, B:26:0x005b, B:28:0x0077, B:29:0x007c, B:31:0x007e, B:33:0x0083, B:35:0x0085, B:37:0x008d, B:39:0x008f, B:40:0x009c, B:16:0x0025, B:18:0x002f, B:20:0x0031, B:23:0x003d, B:25:0x005a, B:24:0x004b), top: B:45:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dynamicTriggerShouldFire(com.onesignal.OSTrigger r15) {
        /*
            r14 = this;
            java.lang.Object r0 = r15.value
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            java.util.ArrayList<java.lang.String> r0 = r14.scheduledMessages
            monitor-enter(r0)
            java.lang.Object r2 = r15.value     // Catch: java.lang.Throwable -> L9e
            boolean r2 = r2 instanceof java.lang.Number     // Catch: java.lang.Throwable -> L9e
            if (r2 != 0) goto L11
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9e
            return r1
        L11:
            int[] r2 = com.onesignal.OSDynamicTriggerController.C35312.$SwitchMap$com$onesignal$OSTrigger$OSTriggerKind     // Catch: java.lang.Throwable -> L9e
            com.onesignal.OSTrigger$OSTriggerKind r3 = r15.kind     // Catch: java.lang.Throwable -> L9e
            int r3 = r3.ordinal()     // Catch: java.lang.Throwable -> L9e
            r2 = r2[r3]     // Catch: java.lang.Throwable -> L9e
            r3 = 1
            r4 = 0
            if (r2 == r3) goto L4b
            r6 = 2
            if (r2 == r6) goto L25
            r6 = r4
            goto L5b
        L25:
            com.onesignal.OSInAppMessageController r2 = com.onesignal.OneSignal.getInAppMessageController()     // Catch: java.lang.Throwable -> L9e
            boolean r2 = r2.isInAppMessageShowing()     // Catch: java.lang.Throwable -> L9e
            if (r2 == 0) goto L31
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9e
            return r1
        L31:
            com.onesignal.OSInAppMessageController r2 = com.onesignal.OneSignal.getInAppMessageController()     // Catch: java.lang.Throwable -> L9e
            java.util.Date r2 = r2.lastTimeInAppDismissed     // Catch: java.lang.Throwable -> L9e
            if (r2 != 0) goto L3d
            r6 = 999999(0xf423f, double:4.94065E-318)
            goto L5b
        L3d:
            java.util.Date r6 = new java.util.Date     // Catch: java.lang.Throwable -> L9e
            r6.<init>()     // Catch: java.lang.Throwable -> L9e
            long r6 = r6.getTime()     // Catch: java.lang.Throwable -> L9e
            long r8 = r2.getTime()     // Catch: java.lang.Throwable -> L9e
            goto L5a
        L4b:
            java.util.Date r2 = new java.util.Date     // Catch: java.lang.Throwable -> L9e
            r2.<init>()     // Catch: java.lang.Throwable -> L9e
            long r6 = r2.getTime()     // Catch: java.lang.Throwable -> L9e
            java.util.Date r2 = com.onesignal.OSDynamicTriggerController.sessionLaunchTime     // Catch: java.lang.Throwable -> L9e
            long r8 = r2.getTime()     // Catch: java.lang.Throwable -> L9e
        L5a:
            long r6 = r6 - r8
        L5b:
            java.lang.String r2 = r15.triggerId     // Catch: java.lang.Throwable -> L9e
            java.lang.Object r8 = r15.value     // Catch: java.lang.Throwable -> L9e
            java.lang.Number r8 = (java.lang.Number) r8     // Catch: java.lang.Throwable -> L9e
            double r8 = r8.doubleValue()     // Catch: java.lang.Throwable -> L9e
            r10 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r8 = r8 * r10
            long r8 = (long) r8     // Catch: java.lang.Throwable -> L9e
            double r10 = (double) r8     // Catch: java.lang.Throwable -> L9e
            double r12 = (double) r6     // Catch: java.lang.Throwable -> L9e
            com.onesignal.OSTrigger$OSTriggerOperator r15 = r15.operatorType     // Catch: java.lang.Throwable -> L9e
            boolean r15 = evaluateTimeIntervalWithOperator(r10, r12, r15)     // Catch: java.lang.Throwable -> L9e
            if (r15 == 0) goto L7e
            com.onesignal.OSDynamicTriggerController$OSDynamicTriggerControllerObserver r15 = r14.observer     // Catch: java.lang.Throwable -> L9e
            r15.messageDynamicTriggerCompleted(r2)     // Catch: java.lang.Throwable -> L9e
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9e
            return r3
        L7e:
            long r8 = r8 - r6
            int r15 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r15 > 0) goto L85
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9e
            return r1
        L85:
            java.util.ArrayList<java.lang.String> r15 = r14.scheduledMessages     // Catch: java.lang.Throwable -> L9e
            boolean r15 = r15.contains(r2)     // Catch: java.lang.Throwable -> L9e
            if (r15 == 0) goto L8f
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9e
            return r1
        L8f:
            com.onesignal.OSDynamicTriggerController$1 r15 = new com.onesignal.OSDynamicTriggerController$1     // Catch: java.lang.Throwable -> L9e
            r15.<init>()     // Catch: java.lang.Throwable -> L9e
            com.onesignal.OSDynamicTriggerTimer.scheduleTrigger(r15, r2, r8)     // Catch: java.lang.Throwable -> L9e
            java.util.ArrayList<java.lang.String> r15 = r14.scheduledMessages     // Catch: java.lang.Throwable -> L9e
            r15.add(r2)     // Catch: java.lang.Throwable -> L9e
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9e
            return r1
        L9e:
            r15 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9e
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSDynamicTriggerController.dynamicTriggerShouldFire(com.onesignal.OSTrigger):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void resetSessionLaunchTime() {
        sessionLaunchTime = new Date();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.onesignal.OSDynamicTriggerController$2 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C35312 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$OSTrigger$OSTriggerKind;
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator;

        static {
            int[] r0 = new int[OSTrigger.OSTriggerOperator.values().length];
            $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator = r0;
            try {
                r0[OSTrigger.OSTriggerOperator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.LESS_THAN_OR_EQUAL_TO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.GREATER_THAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.GREATER_THAN_OR_EQUAL_TO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.EQUAL_TO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.NOT_EQUAL_TO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] r2 = new int[OSTrigger.OSTriggerKind.values().length];
            $SwitchMap$com$onesignal$OSTrigger$OSTriggerKind = r2;
            try {
                r2[OSTrigger.OSTriggerKind.SESSION_TIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerKind[OSTrigger.OSTriggerKind.TIME_SINCE_LAST_IN_APP.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private static boolean evaluateTimeIntervalWithOperator(double d, double d2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        switch (C35312.$SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[oSTriggerOperator.ordinal()]) {
            case 1:
                return d2 < d;
            case 2:
                return d2 <= d || roughlyEqual(d, d2);
            case 3:
                return d2 >= d;
            case 4:
                return d2 >= d || roughlyEqual(d, d2);
            case 5:
                return roughlyEqual(d, d2);
            case 6:
                return !roughlyEqual(d, d2);
            default:
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.onesignalLog(log_level, "Attempted to apply an invalid operator on a time-based in-app-message trigger: " + oSTriggerOperator.toString());
                return false;
        }
    }

    private static boolean roughlyEqual(double d, double d2) {
        return Math.abs(d - d2) < REQUIRED_ACCURACY;
    }
}
