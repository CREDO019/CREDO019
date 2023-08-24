package com.onesignal;

import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import com.onesignal.influence.domain.OSInfluence;
import com.onesignal.influence.domain.OSInfluenceChannel;
import com.onesignal.influence.domain.OSInfluenceType;
import com.onesignal.outcomes.data.OSOutcomeEventsFactory;
import com.onesignal.outcomes.domain.OSOutcomeEventParams;
import com.onesignal.outcomes.domain.OSOutcomeSource;
import com.onesignal.outcomes.domain.OSOutcomeSourceBody;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSOutcomeEventsController {
    private static final String OS_DELETE_CACHED_UNIQUE_OUTCOMES_NOTIFICATIONS_THREAD = "OS_DELETE_CACHED_UNIQUE_OUTCOMES_NOTIFICATIONS_THREAD";
    private static final String OS_SAVE_OUTCOMES = "OS_SAVE_OUTCOMES";
    private static final String OS_SAVE_UNIQUE_OUTCOME_NOTIFICATIONS = "OS_SAVE_UNIQUE_OUTCOME_NOTIFICATIONS";
    private static final String OS_SEND_SAVED_OUTCOMES = "OS_SEND_SAVED_OUTCOMES";
    private final OSSessionManager osSessionManager;
    private final OSOutcomeEventsFactory outcomeEventsFactory;
    private Set<String> unattributedUniqueOutcomeEventsSentOnSession;

    public OSOutcomeEventsController(OSSessionManager oSSessionManager, OSOutcomeEventsFactory oSOutcomeEventsFactory) {
        this.osSessionManager = oSSessionManager;
        this.outcomeEventsFactory = oSOutcomeEventsFactory;
        initUniqueOutcomeEventsSentSets();
    }

    private void initUniqueOutcomeEventsSentSets() {
        this.unattributedUniqueOutcomeEventsSentOnSession = OSUtils.newConcurrentSet();
        Set<String> unattributedUniqueOutcomeEventsSent = this.outcomeEventsFactory.getRepository().getUnattributedUniqueOutcomeEventsSent();
        if (unattributedUniqueOutcomeEventsSent != null) {
            this.unattributedUniqueOutcomeEventsSentOnSession = unattributedUniqueOutcomeEventsSent;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cleanOutcomes() {
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignal cleanOutcomes for session");
        this.unattributedUniqueOutcomeEventsSentOnSession = OSUtils.newConcurrentSet();
        saveUnattributedUniqueOutcomeEvents();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cleanCachedUniqueOutcomes() {
        new Thread(new Runnable() { // from class: com.onesignal.OSOutcomeEventsController.1
            @Override // java.lang.Runnable
            public void run() {
                Thread.currentThread().setPriority(10);
                OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().cleanCachedUniqueOutcomeEventNotifications(OneSignalDbContract.NotificationTable.TABLE_NAME, "notification_id");
            }
        }, OS_DELETE_CACHED_UNIQUE_OUTCOMES_NOTIFICATIONS_THREAD).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendSavedOutcomes() {
        new Thread(new Runnable() { // from class: com.onesignal.OSOutcomeEventsController.2
            @Override // java.lang.Runnable
            public void run() {
                Thread.currentThread().setPriority(10);
                for (OSOutcomeEventParams oSOutcomeEventParams : OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().getSavedOutcomeEvents()) {
                    OSOutcomeEventsController.this.sendSavedOutcomeEvent(oSOutcomeEventParams);
                }
            }
        }, OS_SEND_SAVED_OUTCOMES).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSavedOutcomeEvent(final OSOutcomeEventParams oSOutcomeEventParams) {
        int deviceType = new OSUtils().getDeviceType();
        this.outcomeEventsFactory.getRepository().requestMeasureOutcomeEvent(OneSignal.appId, deviceType, oSOutcomeEventParams, new OneSignalApiResponseHandler() { // from class: com.onesignal.OSOutcomeEventsController.3
            @Override // com.onesignal.OneSignalApiResponseHandler
            public void onFailure(int r1, String str, Throwable th) {
            }

            @Override // com.onesignal.OneSignalApiResponseHandler
            public void onSuccess(String str) {
                OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().removeEvent(oSOutcomeEventParams);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendClickActionOutcomes(List<OSInAppMessageOutcome> list) {
        for (OSInAppMessageOutcome oSInAppMessageOutcome : list) {
            String name = oSInAppMessageOutcome.getName();
            if (oSInAppMessageOutcome.isUnique()) {
                sendUniqueOutcomeEvent(name, null);
            } else if (oSInAppMessageOutcome.getWeight() > 0.0f) {
                sendOutcomeEventWithValue(name, oSInAppMessageOutcome.getWeight(), null);
            } else {
                sendOutcomeEvent(name, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendUniqueOutcomeEvent(String str, OneSignal.OutcomeCallback outcomeCallback) {
        sendUniqueOutcomeEvent(str, this.osSessionManager.getInfluences(), outcomeCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendOutcomeEvent(String str, OneSignal.OutcomeCallback outcomeCallback) {
        sendAndCreateOutcomeEvent(str, 0.0f, this.osSessionManager.getInfluences(), outcomeCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendOutcomeEventWithValue(String str, float f, OneSignal.OutcomeCallback outcomeCallback) {
        sendAndCreateOutcomeEvent(str, f, this.osSessionManager.getInfluences(), outcomeCallback);
    }

    private void sendUniqueOutcomeEvent(String str, List<OSInfluence> list, OneSignal.OutcomeCallback outcomeCallback) {
        List<OSInfluence> removeDisabledInfluences = removeDisabledInfluences(list);
        if (removeDisabledInfluences.isEmpty()) {
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "Unique Outcome disabled for current session");
            return;
        }
        boolean z = false;
        Iterator<OSInfluence> it = removeDisabledInfluences.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().getInfluenceType().isAttributed()) {
                z = true;
                break;
            }
        }
        if (z) {
            List<OSInfluence> uniqueIds = getUniqueIds(str, removeDisabledInfluences);
            if (uniqueIds == null) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                OneSignal.Log(log_level, "Measure endpoint will not send because unique outcome already sent for: \nSessionInfluences: " + removeDisabledInfluences.toString() + "\nOutcome name: " + str);
                if (outcomeCallback != null) {
                    outcomeCallback.onSuccess(null);
                    return;
                }
                return;
            }
            sendAndCreateOutcomeEvent(str, 0.0f, uniqueIds, outcomeCallback);
        } else if (this.unattributedUniqueOutcomeEventsSentOnSession.contains(str)) {
            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level2, "Measure endpoint will not send because unique outcome already sent for: \nSession: " + OSInfluenceType.UNATTRIBUTED + "\nOutcome name: " + str);
            if (outcomeCallback != null) {
                outcomeCallback.onSuccess(null);
            }
        } else {
            this.unattributedUniqueOutcomeEventsSentOnSession.add(str);
            sendAndCreateOutcomeEvent(str, 0.0f, removeDisabledInfluences, outcomeCallback);
        }
    }

    private void sendAndCreateOutcomeEvent(final String str, float f, List<OSInfluence> list, final OneSignal.OutcomeCallback outcomeCallback) {
        final long currentTimeMillis = OneSignal.getTime().getCurrentTimeMillis() / 1000;
        int deviceType = new OSUtils().getDeviceType();
        String str2 = OneSignal.appId;
        boolean z = false;
        OSOutcomeSourceBody oSOutcomeSourceBody = null;
        OSOutcomeSourceBody oSOutcomeSourceBody2 = null;
        for (OSInfluence oSInfluence : list) {
            int r12 = C35726.$SwitchMap$com$onesignal$influence$domain$OSInfluenceType[oSInfluence.getInfluenceType().ordinal()];
            if (r12 == 1) {
                if (oSOutcomeSourceBody == null) {
                    oSOutcomeSourceBody = new OSOutcomeSourceBody();
                }
                oSOutcomeSourceBody = setSourceChannelIds(oSInfluence, oSOutcomeSourceBody);
            } else if (r12 == 2) {
                if (oSOutcomeSourceBody2 == null) {
                    oSOutcomeSourceBody2 = new OSOutcomeSourceBody();
                }
                oSOutcomeSourceBody2 = setSourceChannelIds(oSInfluence, oSOutcomeSourceBody2);
            } else if (r12 == 3) {
                z = true;
            } else if (r12 == 4) {
                OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "Outcomes disabled for channel: " + oSInfluence.getInfluenceChannel());
                if (outcomeCallback != null) {
                    outcomeCallback.onSuccess(null);
                    return;
                }
                return;
            }
        }
        if (oSOutcomeSourceBody == null && oSOutcomeSourceBody2 == null && !z) {
            OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "Outcomes disabled for all channels");
            if (outcomeCallback != null) {
                outcomeCallback.onSuccess(null);
                return;
            }
            return;
        }
        final OSOutcomeEventParams oSOutcomeEventParams = new OSOutcomeEventParams(str, new OSOutcomeSource(oSOutcomeSourceBody, oSOutcomeSourceBody2), f, 0L);
        this.outcomeEventsFactory.getRepository().requestMeasureOutcomeEvent(str2, deviceType, oSOutcomeEventParams, new OneSignalApiResponseHandler() { // from class: com.onesignal.OSOutcomeEventsController.4
            @Override // com.onesignal.OneSignalApiResponseHandler
            public void onSuccess(String str3) {
                OSOutcomeEventsController.this.saveUniqueOutcome(oSOutcomeEventParams);
                OneSignal.OutcomeCallback outcomeCallback2 = outcomeCallback;
                if (outcomeCallback2 != null) {
                    outcomeCallback2.onSuccess(OSOutcomeEvent.fromOutcomeEventParamsV2toOutcomeEventV1(oSOutcomeEventParams));
                }
            }

            @Override // com.onesignal.OneSignalApiResponseHandler
            public void onFailure(int r3, String str3, Throwable th) {
                new Thread(new Runnable() { // from class: com.onesignal.OSOutcomeEventsController.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Thread.currentThread().setPriority(10);
                        oSOutcomeEventParams.setTimestamp(currentTimeMillis);
                        OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().saveOutcomeEvent(oSOutcomeEventParams);
                    }
                }, OSOutcomeEventsController.OS_SAVE_OUTCOMES).start();
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.WARN;
                OneSignal.onesignalLog(log_level, "Sending outcome with name: " + str + " failed with status code: " + r3 + " and response: " + str3 + "\nOutcome event was cached and will be reattempted on app cold start");
                OneSignal.OutcomeCallback outcomeCallback2 = outcomeCallback;
                if (outcomeCallback2 != null) {
                    outcomeCallback2.onSuccess(null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.onesignal.OSOutcomeEventsController$6 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C35726 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$influence$domain$OSInfluenceChannel;
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$influence$domain$OSInfluenceType;

        static {
            int[] r0 = new int[OSInfluenceChannel.values().length];
            $SwitchMap$com$onesignal$influence$domain$OSInfluenceChannel = r0;
            try {
                r0[OSInfluenceChannel.IAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$onesignal$influence$domain$OSInfluenceChannel[OSInfluenceChannel.NOTIFICATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] r2 = new int[OSInfluenceType.values().length];
            $SwitchMap$com$onesignal$influence$domain$OSInfluenceType = r2;
            try {
                r2[OSInfluenceType.DIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$onesignal$influence$domain$OSInfluenceType[OSInfluenceType.INDIRECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$onesignal$influence$domain$OSInfluenceType[OSInfluenceType.UNATTRIBUTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$onesignal$influence$domain$OSInfluenceType[OSInfluenceType.DISABLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private OSOutcomeSourceBody setSourceChannelIds(OSInfluence oSInfluence, OSOutcomeSourceBody oSOutcomeSourceBody) {
        int r0 = C35726.$SwitchMap$com$onesignal$influence$domain$OSInfluenceChannel[oSInfluence.getInfluenceChannel().ordinal()];
        if (r0 == 1) {
            oSOutcomeSourceBody.setInAppMessagesIds(oSInfluence.getIds());
        } else if (r0 == 2) {
            oSOutcomeSourceBody.setNotificationIds(oSInfluence.getIds());
        }
        return oSOutcomeSourceBody;
    }

    private List<OSInfluence> removeDisabledInfluences(List<OSInfluence> list) {
        ArrayList arrayList = new ArrayList(list);
        for (OSInfluence oSInfluence : list) {
            if (oSInfluence.getInfluenceType().isDisabled()) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                OneSignal.onesignalLog(log_level, "Outcomes disabled for channel: " + oSInfluence.getInfluenceChannel().toString());
                arrayList.remove(oSInfluence);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveUniqueOutcome(OSOutcomeEventParams oSOutcomeEventParams) {
        if (oSOutcomeEventParams.isUnattributed()) {
            saveUnattributedUniqueOutcomeEvents();
        } else {
            saveAttributedUniqueOutcomeNotifications(oSOutcomeEventParams);
        }
    }

    private void saveAttributedUniqueOutcomeNotifications(final OSOutcomeEventParams oSOutcomeEventParams) {
        new Thread(new Runnable() { // from class: com.onesignal.OSOutcomeEventsController.5
            @Override // java.lang.Runnable
            public void run() {
                Thread.currentThread().setPriority(10);
                OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().saveUniqueOutcomeNotifications(oSOutcomeEventParams);
            }
        }, OS_SAVE_UNIQUE_OUTCOME_NOTIFICATIONS).start();
    }

    private void saveUnattributedUniqueOutcomeEvents() {
        this.outcomeEventsFactory.getRepository().saveUnattributedUniqueOutcomeEventsSent(this.unattributedUniqueOutcomeEventsSentOnSession);
    }

    private List<OSInfluence> getUniqueIds(String str, List<OSInfluence> list) {
        List<OSInfluence> notCachedUniqueOutcome = this.outcomeEventsFactory.getRepository().getNotCachedUniqueOutcome(str, list);
        if (notCachedUniqueOutcome.size() > 0) {
            return notCachedUniqueOutcome;
        }
        return null;
    }
}
