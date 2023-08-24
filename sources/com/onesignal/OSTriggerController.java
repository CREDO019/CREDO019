package com.onesignal;

import com.onesignal.OSDynamicTriggerController;
import com.onesignal.OSTrigger;
import com.onesignal.OneSignal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSTriggerController {
    OSDynamicTriggerController dynamicTriggerController;
    private final ConcurrentHashMap<String, Object> triggers = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSTriggerController(OSDynamicTriggerController.OSDynamicTriggerControllerObserver oSDynamicTriggerControllerObserver) {
        this.dynamicTriggerController = new OSDynamicTriggerController(oSDynamicTriggerControllerObserver);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean evaluateMessageTriggers(OSInAppMessageInternal oSInAppMessageInternal) {
        if (oSInAppMessageInternal.triggers.size() == 0) {
            return true;
        }
        Iterator<ArrayList<OSTrigger>> it = oSInAppMessageInternal.triggers.iterator();
        while (it.hasNext()) {
            if (evaluateAndTriggers(it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean evaluateAndTriggers(ArrayList<OSTrigger> arrayList) {
        Iterator<OSTrigger> it = arrayList.iterator();
        while (it.hasNext()) {
            if (!evaluateTrigger(it.next())) {
                return false;
            }
        }
        return true;
    }

    private boolean evaluateTrigger(OSTrigger oSTrigger) {
        if (oSTrigger.kind == OSTrigger.OSTriggerKind.UNKNOWN) {
            return false;
        }
        if (oSTrigger.kind != OSTrigger.OSTriggerKind.CUSTOM) {
            return this.dynamicTriggerController.dynamicTriggerShouldFire(oSTrigger);
        }
        OSTrigger.OSTriggerOperator oSTriggerOperator = oSTrigger.operatorType;
        Object obj = this.triggers.get(oSTrigger.property);
        if (obj == null) {
            if (oSTriggerOperator == OSTrigger.OSTriggerOperator.NOT_EXISTS) {
                return true;
            }
            return oSTriggerOperator == OSTrigger.OSTriggerOperator.NOT_EQUAL_TO && oSTrigger.value != null;
        } else if (oSTriggerOperator == OSTrigger.OSTriggerOperator.EXISTS) {
            return true;
        } else {
            if (oSTriggerOperator == OSTrigger.OSTriggerOperator.NOT_EXISTS) {
                return false;
            }
            if (oSTriggerOperator == OSTrigger.OSTriggerOperator.CONTAINS) {
                return (obj instanceof Collection) && ((Collection) obj).contains(oSTrigger.value);
            } else if ((obj instanceof String) && (oSTrigger.value instanceof String) && triggerMatchesStringValue((String) oSTrigger.value, (String) obj, oSTriggerOperator)) {
                return true;
            } else {
                return ((oSTrigger.value instanceof Number) && (obj instanceof Number) && triggerMatchesNumericValue((Number) oSTrigger.value, (Number) obj, oSTriggerOperator)) || triggerMatchesFlex(oSTrigger.value, obj, oSTriggerOperator);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.onesignal.OSTriggerController$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C35781 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator;

        static {
            int[] r0 = new int[OSTrigger.OSTriggerOperator.values().length];
            $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator = r0;
            try {
                r0[OSTrigger.OSTriggerOperator.EQUAL_TO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.NOT_EQUAL_TO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.EXISTS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.CONTAINS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.NOT_EXISTS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.LESS_THAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.GREATER_THAN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.LESS_THAN_OR_EQUAL_TO.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.GREATER_THAN_OR_EQUAL_TO.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private boolean triggerMatchesStringValue(String str, String str2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        int r0 = C35781.$SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[oSTriggerOperator.ordinal()];
        if (r0 != 1) {
            if (r0 == 2) {
                return !str.equals(str2);
            }
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
            OneSignal.onesignalLog(log_level, "Attempted to use an invalid operator for a string trigger comparison: " + oSTriggerOperator.toString());
            return false;
        }
        return str.equals(str2);
    }

    private boolean triggerMatchesFlex(Object obj, Object obj2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        if (obj == null) {
            return false;
        }
        if (oSTriggerOperator.checksEquality()) {
            String obj3 = obj.toString();
            String obj4 = obj2.toString();
            if (obj2 instanceof Number) {
                obj4 = new DecimalFormat("0.#").format(obj2);
            }
            return triggerMatchesStringValue(obj3, obj4, oSTriggerOperator);
        } else if ((obj2 instanceof String) && (obj instanceof Number)) {
            return triggerMatchesNumericValueFlex((Number) obj, (String) obj2, oSTriggerOperator);
        } else {
            return false;
        }
    }

    private boolean triggerMatchesNumericValueFlex(Number number, String str, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        try {
            return triggerMatchesNumericValue(Double.valueOf(number.doubleValue()), Double.valueOf(Double.parseDouble(str)), oSTriggerOperator);
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private boolean triggerMatchesNumericValue(Number number, Number number2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        double doubleValue = number.doubleValue();
        double doubleValue2 = number2.doubleValue();
        switch (C35781.$SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[oSTriggerOperator.ordinal()]) {
            case 1:
                return doubleValue2 == doubleValue;
            case 2:
                return doubleValue2 != doubleValue;
            case 3:
            case 4:
            case 5:
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.onesignalLog(log_level, "Attempted to use an invalid operator with a numeric value: " + oSTriggerOperator.toString());
                return false;
            case 6:
                return doubleValue2 < doubleValue;
            case 7:
                return doubleValue2 > doubleValue;
            case 8:
                return doubleValue2 < doubleValue || doubleValue2 == doubleValue;
            case 9:
                int r8 = (doubleValue2 > doubleValue ? 1 : (doubleValue2 == doubleValue ? 0 : -1));
                return r8 > 0 || r8 == 0;
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isTriggerOnMessage(com.onesignal.OSInAppMessageInternal r7, java.util.Collection<java.lang.String> r8) {
        /*
            r6 = this;
            java.util.ArrayList<java.util.ArrayList<com.onesignal.OSTrigger>> r0 = r7.triggers
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            java.util.Iterator r8 = r8.iterator()
        La:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L4a
            java.lang.Object r0 = r8.next()
            java.lang.String r0 = (java.lang.String) r0
            java.util.ArrayList<java.util.ArrayList<com.onesignal.OSTrigger>> r2 = r7.triggers
            java.util.Iterator r2 = r2.iterator()
        L1c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto La
            java.lang.Object r3 = r2.next()
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            java.util.Iterator r3 = r3.iterator()
        L2c:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L1c
            java.lang.Object r4 = r3.next()
            com.onesignal.OSTrigger r4 = (com.onesignal.OSTrigger) r4
            java.lang.String r5 = r4.property
            boolean r5 = r0.equals(r5)
            if (r5 != 0) goto L48
            java.lang.String r4 = r4.triggerId
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L2c
        L48:
            r7 = 1
            return r7
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSTriggerController.isTriggerOnMessage(com.onesignal.OSInAppMessageInternal, java.util.Collection):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean messageHasOnlyDynamicTriggers(com.onesignal.OSInAppMessageInternal r6) {
        /*
            r5 = this;
            java.util.ArrayList<java.util.ArrayList<com.onesignal.OSTrigger>> r0 = r6.triggers
            r1 = 0
            if (r0 == 0) goto L3f
            java.util.ArrayList<java.util.ArrayList<com.onesignal.OSTrigger>> r0 = r6.triggers
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto Le
            goto L3f
        Le:
            java.util.ArrayList<java.util.ArrayList<com.onesignal.OSTrigger>> r6 = r6.triggers
            java.util.Iterator r6 = r6.iterator()
        L14:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L3d
            java.lang.Object r0 = r6.next()
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            java.util.Iterator r0 = r0.iterator()
        L24:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L14
            java.lang.Object r2 = r0.next()
            com.onesignal.OSTrigger r2 = (com.onesignal.OSTrigger) r2
            com.onesignal.OSTrigger$OSTriggerKind r3 = r2.kind
            com.onesignal.OSTrigger$OSTriggerKind r4 = com.onesignal.OSTrigger.OSTriggerKind.CUSTOM
            if (r3 == r4) goto L3c
            com.onesignal.OSTrigger$OSTriggerKind r2 = r2.kind
            com.onesignal.OSTrigger$OSTriggerKind r3 = com.onesignal.OSTrigger.OSTriggerKind.UNKNOWN
            if (r2 != r3) goto L24
        L3c:
            return r1
        L3d:
            r6 = 1
            return r6
        L3f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSTriggerController.messageHasOnlyDynamicTriggers(com.onesignal.OSInAppMessageInternal):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addTriggers(Map<String, Object> map) {
        synchronized (this.triggers) {
            for (String str : map.keySet()) {
                this.triggers.put(str, map.get(str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeTriggersForKeys(Collection<String> collection) {
        synchronized (this.triggers) {
            for (String str : collection) {
                this.triggers.remove(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getTriggerValue(String str) {
        synchronized (this.triggers) {
            if (this.triggers.containsKey(str)) {
                return this.triggers.get(str);
            }
            return null;
        }
    }

    public ConcurrentHashMap<String, Object> getTriggers() {
        return this.triggers;
    }
}
