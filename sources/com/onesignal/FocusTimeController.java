package com.onesignal;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import com.onesignal.influence.domain.OSInfluence;
import com.onesignal.outcomes.OSOutcomeConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class FocusTimeController {
    private OSLogger logger;
    private OSFocusTimeProcessorFactory processorFactory;
    private Long timeFocusedAtMs;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum FocusEventType {
        BACKGROUND,
        END_SESSION
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FocusTimeController(OSFocusTimeProcessorFactory oSFocusTimeProcessorFactory, OSLogger oSLogger) {
        this.processorFactory = oSFocusTimeProcessorFactory;
        this.logger = oSLogger;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appForegrounded() {
        this.timeFocusedAtMs = Long.valueOf(OneSignal.getTime().getElapsedRealtime());
        OSLogger oSLogger = this.logger;
        oSLogger.debug("Application foregrounded focus time: " + this.timeFocusedAtMs);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appStopped() {
        Long timeFocusedElapsed = getTimeFocusedElapsed();
        OSLogger oSLogger = this.logger;
        oSLogger.debug("Application stopped focus time: " + this.timeFocusedAtMs + " timeElapsed: " + timeFocusedElapsed);
        if (timeFocusedElapsed == null) {
            return;
        }
        List<OSInfluence> sessionInfluences = OneSignal.getSessionManager().getSessionInfluences();
        this.processorFactory.getTimeProcessorWithInfluences(sessionInfluences).saveUnsentActiveData(timeFocusedElapsed.longValue(), sessionInfluences);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appBackgrounded() {
        OSLogger oSLogger = this.logger;
        oSLogger.debug("Application backgrounded focus time: " + this.timeFocusedAtMs);
        this.processorFactory.getTimeProcessorSaved().sendUnsentTimeNow();
        this.timeFocusedAtMs = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void doBlockingBackgroundSyncOfUnsentTime() {
        if (OneSignal.isInForeground()) {
            return;
        }
        this.processorFactory.getTimeProcessorSaved().syncUnsentTimeFromSyncJob();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onSessionEnded(List<OSInfluence> list) {
        FocusEventType focusEventType = FocusEventType.END_SESSION;
        if (giveProcessorsValidFocusTime(list, focusEventType)) {
            return;
        }
        this.processorFactory.getTimeProcessorWithInfluences(list).sendUnsentTimeNow(focusEventType);
    }

    private boolean giveProcessorsValidFocusTime(List<OSInfluence> list, FocusEventType focusEventType) {
        Long timeFocusedElapsed = getTimeFocusedElapsed();
        if (timeFocusedElapsed == null) {
            return false;
        }
        this.processorFactory.getTimeProcessorWithInfluences(list).addTime(timeFocusedElapsed.longValue(), list, focusEventType);
        return true;
    }

    private Long getTimeFocusedElapsed() {
        if (this.timeFocusedAtMs == null) {
            return null;
        }
        long elapsedRealtime = (long) (((OneSignal.getTime().getElapsedRealtime() - this.timeFocusedAtMs.longValue()) / 1000.0d) + 0.5d);
        if (elapsedRealtime < 1 || elapsedRealtime > 86400) {
            return null;
        }
        return Long.valueOf(elapsedRealtime);
    }

    /* loaded from: classes3.dex */
    static class FocusTimeProcessorUnattributed extends FocusTimeProcessorBase {
        @Override // com.onesignal.FocusTimeController.FocusTimeProcessorBase
        protected void saveInfluences(List<OSInfluence> list) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public FocusTimeProcessorUnattributed() {
            this.MIN_ON_FOCUS_TIME_SEC = 60L;
            this.PREF_KEY_FOR_UNSENT_TIME = OneSignalPrefs.PREFS_GT_UNSENT_ACTIVE_TIME;
        }

        @Override // com.onesignal.FocusTimeController.FocusTimeProcessorBase
        protected void sendTime(FocusEventType focusEventType) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.onesignalLog(log_level, getClass().getSimpleName() + " sendTime with: " + focusEventType);
            if (focusEventType.equals(FocusEventType.END_SESSION)) {
                return;
            }
            syncUnsentTimeOnBackgroundEvent();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.onesignal.FocusTimeController.FocusTimeProcessorBase
        public List<OSInfluence> getInfluences() {
            return new ArrayList();
        }
    }

    /* loaded from: classes3.dex */
    static class FocusTimeProcessorAttributed extends FocusTimeProcessorBase {
        /* JADX INFO: Access modifiers changed from: package-private */
        public FocusTimeProcessorAttributed() {
            this.MIN_ON_FOCUS_TIME_SEC = 1L;
            this.PREF_KEY_FOR_UNSENT_TIME = OneSignalPrefs.PREFS_OS_UNSENT_ATTRIBUTED_ACTIVE_TIME;
        }

        @Override // com.onesignal.FocusTimeController.FocusTimeProcessorBase
        protected List<OSInfluence> getInfluences() {
            ArrayList arrayList = new ArrayList();
            for (String str : OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_ATTRIBUTED_INFLUENCES, new HashSet())) {
                try {
                    arrayList.add(new OSInfluence(str));
                } catch (JSONException e) {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level, getClass().getSimpleName() + ": error generation OSInfluence from json object: " + e);
                }
            }
            return arrayList;
        }

        @Override // com.onesignal.FocusTimeController.FocusTimeProcessorBase
        protected void saveInfluences(List<OSInfluence> list) {
            HashSet hashSet = new HashSet();
            for (OSInfluence oSInfluence : list) {
                try {
                    hashSet.add(oSInfluence.toJSONString());
                } catch (JSONException e) {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level, getClass().getSimpleName() + ": error generation json object OSInfluence: " + e);
                }
            }
            OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_ATTRIBUTED_INFLUENCES, hashSet);
        }

        @Override // com.onesignal.FocusTimeController.FocusTimeProcessorBase
        protected void additionalFieldsToAddToOnFocusPayload(JSONObject jSONObject) {
            OneSignal.getSessionManager().addSessionIds(jSONObject, getInfluences());
        }

        @Override // com.onesignal.FocusTimeController.FocusTimeProcessorBase
        protected void sendTime(FocusEventType focusEventType) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.onesignalLog(log_level, getClass().getSimpleName() + " sendTime with: " + focusEventType);
            if (focusEventType.equals(FocusEventType.END_SESSION)) {
                syncOnFocusTime();
            } else {
                OSSyncService.getInstance().scheduleSyncTask(OneSignal.appContext);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class FocusTimeProcessorBase {
        protected long MIN_ON_FOCUS_TIME_SEC;
        protected String PREF_KEY_FOR_UNSENT_TIME;
        private Long unsentActiveTime = null;
        private final AtomicBoolean runningOnFocusTime = new AtomicBoolean();

        protected void additionalFieldsToAddToOnFocusPayload(JSONObject jSONObject) {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public abstract List<OSInfluence> getInfluences();

        protected abstract void saveInfluences(List<OSInfluence> list);

        protected abstract void sendTime(FocusEventType focusEventType);

        FocusTimeProcessorBase() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void saveUnsentActiveData(long j, List<OSInfluence> list) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, getClass().getSimpleName() + ":saveUnsentActiveData with lastFocusTimeInfluences: " + list.toString());
            saveInfluences(list);
            saveUnsentActiveTime(getUnsentActiveTime() + j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void saveUnsentActiveTime(long j) {
            this.unsentActiveTime = Long.valueOf(j);
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, getClass().getSimpleName() + ":saveUnsentActiveTime: " + this.unsentActiveTime);
            OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, this.PREF_KEY_FOR_UNSENT_TIME, j);
        }

        private long getUnsentActiveTime() {
            if (this.unsentActiveTime == null) {
                this.unsentActiveTime = Long.valueOf(OneSignalPrefs.getLong(OneSignalPrefs.PREFS_ONESIGNAL, this.PREF_KEY_FOR_UNSENT_TIME, 0L));
            }
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, getClass().getSimpleName() + ":getUnsentActiveTime: " + this.unsentActiveTime);
            return this.unsentActiveTime.longValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addTime(long j, List<OSInfluence> list, FocusEventType focusEventType) {
            saveUnsentActiveData(j, list);
            sendUnsentTimeNow(focusEventType);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendUnsentTimeNow() {
            List<OSInfluence> influences = getInfluences();
            long unsentActiveTime = getUnsentActiveTime();
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, getClass().getSimpleName() + ":sendUnsentTimeNow with time: " + unsentActiveTime + " and influences: " + influences.toString());
            sendUnsentTimeNow(FocusEventType.BACKGROUND);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendUnsentTimeNow(FocusEventType focusEventType) {
            if (!OneSignal.hasUserId()) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.WARN;
                OneSignal.Log(log_level, getClass().getSimpleName() + ":sendUnsentTimeNow not possible due to user id null");
                return;
            }
            sendTime(focusEventType);
        }

        private boolean hasMinSyncTime() {
            return getUnsentActiveTime() >= this.MIN_ON_FOCUS_TIME_SEC;
        }

        protected void syncUnsentTimeOnBackgroundEvent() {
            if (hasMinSyncTime()) {
                OSSyncService.getInstance().scheduleSyncTask(OneSignal.appContext);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void syncUnsentTimeFromSyncJob() {
            if (hasMinSyncTime()) {
                syncOnFocusTime();
            }
        }

        protected void syncOnFocusTime() {
            if (this.runningOnFocusTime.get()) {
                return;
            }
            synchronized (this.runningOnFocusTime) {
                this.runningOnFocusTime.set(true);
                if (hasMinSyncTime()) {
                    sendOnFocus(getUnsentActiveTime());
                }
                this.runningOnFocusTime.set(false);
            }
        }

        private void sendOnFocusToPlayer(String str, JSONObject jSONObject) {
            OneSignalRestClient.ResponseHandler responseHandler = new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.FocusTimeController.FocusTimeProcessorBase.1
                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onFailure(int r2, String str2, Throwable th) {
                    OneSignal.logHttpError("sending on_focus Failed", r2, th, str2);
                }

                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onSuccess(String str2) {
                    FocusTimeProcessorBase.this.saveUnsentActiveTime(0L);
                }
            };
            OneSignalRestClient.postSync("players/" + str + "/on_focus", jSONObject, responseHandler);
        }

        private JSONObject generateOnFocusPayload(long j) throws JSONException {
            JSONObject put = new JSONObject().put(OSOutcomeConstants.APP_ID, OneSignal.getSavedAppId()).put(SessionDescription.ATTR_TYPE, 1).put("state", "ping").put("active_time", j).put(OSOutcomeConstants.DEVICE_TYPE, new OSUtils().getDeviceType());
            OneSignal.addNetType(put);
            return put;
        }

        private void sendOnFocus(long j) {
            try {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                OneSignal.Log(log_level, getClass().getSimpleName() + ":sendOnFocus with totalTimeActive: " + j);
                JSONObject generateOnFocusPayload = generateOnFocusPayload(j);
                additionalFieldsToAddToOnFocusPayload(generateOnFocusPayload);
                sendOnFocusToPlayer(OneSignal.getUserId(), generateOnFocusPayload);
                if (OneSignal.hasEmailId()) {
                    sendOnFocusToPlayer(OneSignal.getEmailId(), generateOnFocusPayload(j));
                }
                if (OneSignal.hasSMSlId()) {
                    sendOnFocusToPlayer(OneSignal.getSMSId(), generateOnFocusPayload(j));
                }
                saveInfluences(new ArrayList());
            } catch (JSONException e) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Generating on_focus:JSON Failed.", e);
            }
        }
    }
}
