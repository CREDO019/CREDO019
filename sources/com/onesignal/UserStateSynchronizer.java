package com.onesignal;

import android.os.Handler;
import android.os.HandlerThread;
import com.onesignal.LocationController;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import com.onesignal.OneSignalStateSynchronizer;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class UserStateSynchronizer {
    protected static final String ANDROID_PERMISSION = "androidPermission";
    static final String APP_ID = "app_id";
    private static final String CURRENT_STATE = "CURRENT_STATE";
    protected static final String DEVICE_PLAYER_ID = "device_player_id";
    protected static final String DEVICE_TYPE = "device_type";
    static final String EMAIL_AUTH_HASH_KEY = "email_auth_hash";
    protected static final String EMAIL_KEY = "email";
    private static final String ERRORS = "errors";
    protected static final String EXTERNAL_USER_ID = "external_user_id";
    static final String EXTERNAL_USER_ID_AUTH_HASH = "external_user_id_auth_hash";

    /* renamed from: ID */
    private static final String f1308ID = "id";
    protected static final String IDENTIFIER = "identifier";
    protected static final String LANGUAGE = "language";
    protected static final String LOGOUT_EMAIL = "logoutEmail";
    protected static final String PARENT_PLAYER_ID = "parent_player_id";
    private static final String SESSION = "session";
    static final String SMS_AUTH_HASH_KEY = "sms_auth_hash";
    protected static final String SMS_NUMBER_KEY = "sms_number";
    protected static final String SUBSCRIBABLE_STATUS = "subscribableStatus";
    protected static final String TAGS = "tags";
    private static final String TOSYNC_STATE = "TOSYNC_STATE";
    protected static final String USER_SUBSCRIBE_PREF = "userSubscribePref";
    private boolean canMakeUpdates;
    private OneSignalStateSynchronizer.UserStateSynchronizerType channel;
    private UserState currentUserState;
    private UserState toSyncUserState;
    protected final Object LOCK = new Object();
    private AtomicBoolean runningSyncUserState = new AtomicBoolean();
    private final Queue<OneSignal.ChangeTagsUpdateHandler> sendTagsHandlers = new ConcurrentLinkedQueue();
    private final Queue<OneSignal.OSInternalExternalUserIdUpdateCompletionHandler> externalUserIdUpdateHandlers = new ConcurrentLinkedQueue();
    private final Queue<OneSignalStateSynchronizer.OSDeviceInfoCompletionHandler> deviceInfoCompletionHandler = new ConcurrentLinkedQueue();
    HashMap<Integer, NetworkHandlerThread> networkHandlerThreads = new HashMap<>();
    private final Object networkHandlerSyncLock = new Object() { // from class: com.onesignal.UserStateSynchronizer.1
    };
    protected boolean waitingForSessionResponse = false;

    protected abstract void addOnSessionOrCreateExtras(JSONObject jSONObject);

    protected abstract void fireEventsForUpdateFailure(JSONObject jSONObject);

    abstract String getExternalId(boolean z);

    protected abstract String getId();

    protected abstract OneSignal.LOG_LEVEL getLogLevel();

    abstract boolean getSubscribed();

    abstract GetTagsResult getTags(boolean z);

    public abstract boolean getUserSubscribePreference();

    abstract void logoutChannel();

    protected abstract UserState newUserState(String str, boolean z);

    protected abstract void onSuccessfulSync(JSONObject jSONObject);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void saveChannelId(String str);

    protected abstract void scheduleSyncToServer();

    public abstract void setPermission(boolean z);

    abstract void setSubscription(boolean z);

    abstract void updateIdDependents(String str);

    abstract void updateState(JSONObject jSONObject);

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserStateSynchronizer(OneSignalStateSynchronizer.UserStateSynchronizerType userStateSynchronizerType) {
        this.channel = userStateSynchronizerType;
    }

    OneSignalStateSynchronizer.UserStateSynchronizerType getChannelType() {
        return this.channel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getChannelString() {
        return this.channel.name().toLowerCase();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GetTagsResult {
        JSONObject result;
        boolean serverSuccess;

        /* JADX INFO: Access modifiers changed from: package-private */
        public GetTagsResult(boolean z, JSONObject jSONObject) {
            this.serverSuccess = z;
            this.result = jSONObject;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getRegistrationId() {
        return getToSyncUserState().getSyncValues().optString(IDENTIFIER, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasQueuedHandlers() {
        return this.externalUserIdUpdateHandlers.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class NetworkHandlerThread extends HandlerThread {
        static final int MAX_RETRIES = 3;
        static final int NETWORK_CALL_DELAY_TO_BUFFER_MS = 5000;
        protected static final int NETWORK_HANDLER_USERSTATE = 0;
        private static final String THREAD_NAME_PREFIX = "OSH_NetworkHandlerThread_";
        int currentRetry;
        Handler mHandler;
        int mType;

        NetworkHandlerThread(int r4) {
            super(THREAD_NAME_PREFIX + UserStateSynchronizer.this.channel);
            this.mType = r4;
            start();
            this.mHandler = new Handler(getLooper());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void runNewJobDelayed() {
            if (UserStateSynchronizer.this.canMakeUpdates) {
                synchronized (this.mHandler) {
                    this.currentRetry = 0;
                    this.mHandler.removeCallbacksAndMessages(null);
                    this.mHandler.postDelayed(getNewRunnable(), 5000L);
                }
            }
        }

        private Runnable getNewRunnable() {
            if (this.mType != 0) {
                return null;
            }
            return new Runnable() { // from class: com.onesignal.UserStateSynchronizer.NetworkHandlerThread.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!UserStateSynchronizer.this.runningSyncUserState.get()) {
                        UserStateSynchronizer.this.syncUserState(false);
                    }
                }
            };
        }

        void stopScheduledRunnable() {
            this.mHandler.removeCallbacksAndMessages(null);
        }

        boolean doRetry() {
            boolean hasMessages;
            synchronized (this.mHandler) {
                boolean z = this.currentRetry < 3;
                boolean hasMessages2 = this.mHandler.hasMessages(0);
                if (z && !hasMessages2) {
                    this.currentRetry++;
                    this.mHandler.postDelayed(getNewRunnable(), this.currentRetry * 15000);
                }
                hasMessages = this.mHandler.hasMessages(0);
            }
            return hasMessages;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject generateJsonDiff(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, Set<String> set) {
        JSONObject generateJsonDiff;
        synchronized (this.LOCK) {
            generateJsonDiff = JSONUtils.generateJsonDiff(jSONObject, jSONObject2, jSONObject3, set);
        }
        return generateJsonDiff;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UserState getCurrentUserState() {
        if (this.currentUserState == null) {
            synchronized (this.LOCK) {
                if (this.currentUserState == null) {
                    this.currentUserState = newUserState(CURRENT_STATE, true);
                }
            }
        }
        return this.currentUserState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UserState getToSyncUserState() {
        if (this.toSyncUserState == null) {
            synchronized (this.LOCK) {
                if (this.toSyncUserState == null) {
                    this.toSyncUserState = newUserState(TOSYNC_STATE, true);
                }
            }
        }
        return this.toSyncUserState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initUserState() {
        if (this.currentUserState == null) {
            synchronized (this.LOCK) {
                if (this.currentUserState == null) {
                    this.currentUserState = newUserState(CURRENT_STATE, true);
                }
            }
        }
        getToSyncUserState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearLocation() {
        getToSyncUserState().clearLocation();
        getToSyncUserState().persistState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean persist() {
        boolean z;
        if (this.toSyncUserState != null) {
            synchronized (this.LOCK) {
                z = getCurrentUserState().generateJsonDiff(this.toSyncUserState, isSessionCall()) != null;
                this.toSyncUserState.persistState();
            }
            return z;
        }
        return false;
    }

    private boolean isSessionCall() {
        return (getToSyncUserState().getDependValues().optBoolean("session") || getId() == null) && !this.waitingForSessionResponse;
    }

    private boolean syncEmailLogout() {
        return getToSyncUserState().getDependValues().optBoolean(LOGOUT_EMAIL, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void syncUserState(boolean z) {
        this.runningSyncUserState.set(true);
        internalSyncUserState(z);
        this.runningSyncUserState.set(false);
    }

    private void internalSyncUserState(boolean z) {
        String id = getId();
        if (syncEmailLogout() && id != null) {
            doEmailLogout(id);
            return;
        }
        if (this.currentUserState == null) {
            initUserState();
        }
        boolean z2 = !z && isSessionCall();
        synchronized (this.LOCK) {
            JSONObject generateJsonDiff = getCurrentUserState().generateJsonDiff(getToSyncUserState(), z2);
            JSONObject generateJsonDiffFromDependValues = getCurrentUserState().generateJsonDiffFromDependValues(getToSyncUserState(), null);
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.onesignalLog(log_level, "UserStateSynchronizer internalSyncUserState from session call: " + z2 + " jsonBody: " + generateJsonDiff);
            if (generateJsonDiff == null) {
                getCurrentUserState().persistStateAfterSync(generateJsonDiffFromDependValues, null);
                sendTagsHandlersPerformOnSuccess();
                externalUserIdUpdateHandlersPerformOnSuccess();
                deviceInfoHandlersPerformOnSuccess();
                return;
            }
            getToSyncUserState().persistState();
            if (!z2) {
                doPutSync(id, generateJsonDiff, generateJsonDiffFromDependValues);
            } else {
                doCreateOrNewSession(id, generateJsonDiff, generateJsonDiffFromDependValues);
            }
        }
    }

    private void doEmailLogout(String str) {
        String str2 = "players/" + str + "/email_logout";
        JSONObject jSONObject = new JSONObject();
        try {
            ImmutableJSONObject dependValues = getCurrentUserState().getDependValues();
            if (dependValues.has(EMAIL_AUTH_HASH_KEY)) {
                jSONObject.put(EMAIL_AUTH_HASH_KEY, dependValues.optString(EMAIL_AUTH_HASH_KEY));
            }
            ImmutableJSONObject syncValues = getCurrentUserState().getSyncValues();
            if (syncValues.has(PARENT_PLAYER_ID)) {
                jSONObject.put(PARENT_PLAYER_ID, syncValues.optString(PARENT_PLAYER_ID));
            }
            jSONObject.put("app_id", syncValues.optString("app_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OneSignalRestClient.postSync(str2, jSONObject, new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.UserStateSynchronizer.2
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onFailure(int r3, String str3, Throwable th) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.WARN;
                OneSignal.Log(log_level, "Failed last request. statusCode: " + r3 + "\nresponse: " + str3);
                if (UserStateSynchronizer.this.response400WithErrorsContaining(r3, str3, "already logged out of email")) {
                    UserStateSynchronizer.this.logoutEmailSyncSuccess();
                } else if (UserStateSynchronizer.this.response400WithErrorsContaining(r3, str3, "not a valid device_type")) {
                    UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                } else {
                    UserStateSynchronizer.this.handleNetworkFailure(r3);
                }
            }

            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onSuccess(String str3) {
                UserStateSynchronizer.this.logoutEmailSyncSuccess();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logoutEmailSyncSuccess() {
        getToSyncUserState().removeFromDependValues(LOGOUT_EMAIL);
        this.toSyncUserState.removeFromDependValues(EMAIL_AUTH_HASH_KEY);
        this.toSyncUserState.removeFromSyncValues(PARENT_PLAYER_ID);
        this.toSyncUserState.removeFromSyncValues("email");
        this.toSyncUserState.persistState();
        getCurrentUserState().removeFromDependValues(EMAIL_AUTH_HASH_KEY);
        getCurrentUserState().removeFromSyncValues(PARENT_PLAYER_ID);
        String optString = getCurrentUserState().getSyncValues().optString("email");
        getCurrentUserState().removeFromSyncValues("email");
        OneSignalStateSynchronizer.setNewSessionForEmail();
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
        OneSignal.Log(log_level, "Device successfully logged out of email: " + optString);
        OneSignal.handleSuccessfulEmailLogout();
    }

    private void doPutSync(String str, final JSONObject jSONObject, final JSONObject jSONObject2) {
        if (str == null) {
            OneSignal.onesignalLog(getLogLevel(), "Error updating the user record because of the null user id");
            sendTagsHandlersPerformOnFailure(new OneSignal.SendTagsError(-1, "Unable to update tags: the current user is not registered with OneSignal"));
            externalUserIdUpdateHandlersPerformOnFailure();
            deviceInfoHandlersPerformOnFailure(new OneSignalStateSynchronizer.OSDeviceInfoError(-1, "Unable to set Language: the current user is not registered with OneSignal"));
            return;
        }
        OneSignalRestClient.putSync("players/" + str, jSONObject, new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.UserStateSynchronizer.3
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onFailure(int r3, String str2, Throwable th) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.Log(log_level, "Failed PUT sync request with status code: " + r3 + " and response: " + str2);
                synchronized (UserStateSynchronizer.this.LOCK) {
                    if (UserStateSynchronizer.this.response400WithErrorsContaining(r3, str2, "No user with this id found")) {
                        UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                    } else {
                        UserStateSynchronizer.this.handleNetworkFailure(r3);
                    }
                }
                if (jSONObject.has("tags")) {
                    UserStateSynchronizer.this.sendTagsHandlersPerformOnFailure(new OneSignal.SendTagsError(r3, str2));
                }
                if (jSONObject.has(UserStateSynchronizer.EXTERNAL_USER_ID)) {
                    OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.onesignalLog(log_level2, "Error setting external user id for push with status code: " + r3 + " and message: " + str2);
                    UserStateSynchronizer.this.externalUserIdUpdateHandlersPerformOnFailure();
                }
                if (jSONObject.has("language")) {
                    UserStateSynchronizer.this.deviceInfoHandlersPerformOnFailure(new OneSignalStateSynchronizer.OSDeviceInfoError(r3, str2));
                }
            }

            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onSuccess(String str2) {
                synchronized (UserStateSynchronizer.this.LOCK) {
                    UserStateSynchronizer.this.getCurrentUserState().persistStateAfterSync(jSONObject2, jSONObject);
                    UserStateSynchronizer.this.onSuccessfulSync(jSONObject);
                }
                if (jSONObject.has("tags")) {
                    UserStateSynchronizer.this.sendTagsHandlersPerformOnSuccess();
                }
                if (jSONObject.has(UserStateSynchronizer.EXTERNAL_USER_ID)) {
                    UserStateSynchronizer.this.externalUserIdUpdateHandlersPerformOnSuccess();
                }
                if (jSONObject.has("language")) {
                    UserStateSynchronizer.this.deviceInfoHandlersPerformOnSuccess();
                }
            }
        });
    }

    private void doCreateOrNewSession(final String str, final JSONObject jSONObject, final JSONObject jSONObject2) {
        String str2;
        if (str == null) {
            str2 = "players";
        } else {
            str2 = "players/" + str + "/on_session";
        }
        this.waitingForSessionResponse = true;
        addOnSessionOrCreateExtras(jSONObject);
        OneSignalRestClient.postSync(str2, jSONObject, new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.UserStateSynchronizer.4
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onFailure(int r4, String str3, Throwable th) {
                synchronized (UserStateSynchronizer.this.LOCK) {
                    UserStateSynchronizer.this.waitingForSessionResponse = false;
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.WARN;
                    OneSignal.Log(log_level, "Failed last request. statusCode: " + r4 + "\nresponse: " + str3);
                    if (UserStateSynchronizer.this.response400WithErrorsContaining(r4, str3, "not a valid device_type")) {
                        UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                    } else {
                        UserStateSynchronizer.this.handleNetworkFailure(r4);
                    }
                }
            }

            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onSuccess(String str3) {
                synchronized (UserStateSynchronizer.this.LOCK) {
                    UserStateSynchronizer.this.waitingForSessionResponse = false;
                    UserStateSynchronizer.this.getCurrentUserState().persistStateAfterSync(jSONObject2, jSONObject);
                    try {
                        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                        OneSignal.onesignalLog(log_level, "doCreateOrNewSession:response: " + str3);
                        JSONObject jSONObject3 = new JSONObject(str3);
                        if (jSONObject3.has("id")) {
                            String optString = jSONObject3.optString("id");
                            UserStateSynchronizer.this.updateIdDependents(optString);
                            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.INFO;
                            OneSignal.Log(log_level2, "Device registered, UserId = " + optString);
                        } else {
                            OneSignal.LOG_LEVEL log_level3 = OneSignal.LOG_LEVEL.INFO;
                            OneSignal.Log(log_level3, "session sent, UserId = " + str);
                        }
                        UserStateSynchronizer.this.getUserStateForModification().putOnDependValues("session", false);
                        UserStateSynchronizer.this.getUserStateForModification().persistState();
                        if (jSONObject3.has(OSInAppMessageController.IN_APP_MESSAGES_JSON_KEY)) {
                            OneSignal.getInAppMessageController().receivedInAppMessageJson(jSONObject3.getJSONArray(OSInAppMessageController.IN_APP_MESSAGES_JSON_KEY));
                        }
                        UserStateSynchronizer.this.onSuccessfulSync(jSONObject);
                    } catch (JSONException e) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "ERROR parsing on_session or create JSON Response.", e);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNetworkFailure(int r2) {
        if (r2 == 403) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "403 error updating player, omitting further retries!");
            fireNetworkFailureEvents();
        } else if (getNetworkHandlerThread(0).doRetry()) {
        } else {
            fireNetworkFailureEvents();
        }
    }

    private void fireNetworkFailureEvents() {
        JSONObject generateJsonDiff = getCurrentUserState().generateJsonDiff(this.toSyncUserState, false);
        if (generateJsonDiff != null) {
            fireEventsForUpdateFailure(generateJsonDiff);
        }
        if (getToSyncUserState().getDependValues().optBoolean(LOGOUT_EMAIL, false)) {
            OneSignal.handleFailedEmailLogout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean response400WithErrorsContaining(int r4, String str, String str2) {
        if (r4 == 400 && str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(ERRORS)) {
                    return jSONObject.optString(ERRORS).contains(str2);
                }
                return false;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public NetworkHandlerThread getNetworkHandlerThread(Integer num) {
        NetworkHandlerThread networkHandlerThread;
        synchronized (this.networkHandlerSyncLock) {
            if (!this.networkHandlerThreads.containsKey(num)) {
                this.networkHandlerThreads.put(num, new NetworkHandlerThread(num.intValue()));
            }
            networkHandlerThread = this.networkHandlerThreads.get(num);
        }
        return networkHandlerThread;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UserState getUserStateForModification() {
        if (this.toSyncUserState == null) {
            this.toSyncUserState = getCurrentUserState().deepClone(TOSYNC_STATE);
        }
        scheduleSyncToServer();
        return this.toSyncUserState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateDeviceInfo(JSONObject jSONObject, OneSignalStateSynchronizer.OSDeviceInfoCompletionHandler oSDeviceInfoCompletionHandler) {
        if (oSDeviceInfoCompletionHandler != null) {
            this.deviceInfoCompletionHandler.add(oSDeviceInfoCompletionHandler);
        }
        getUserStateForModification().generateJsonDiffFromIntoSyncValued(jSONObject, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNewSession() {
        try {
            synchronized (this.LOCK) {
                getUserStateForModification().putOnDependValues("session", true);
                getUserStateForModification().persistState();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getSyncAsNewSession() {
        return getUserStateForModification().getDependValues().optBoolean("session");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendTags(JSONObject jSONObject, OneSignal.ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        if (changeTagsUpdateHandler != null) {
            this.sendTagsHandlers.add(changeTagsUpdateHandler);
        }
        getUserStateForModification().generateJsonDiffFromIntoSyncValued(jSONObject, null);
    }

    void syncHashedEmail(JSONObject jSONObject) {
        getUserStateForModification().generateJsonDiffFromIntoSyncValued(jSONObject, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setExternalUserId(String str, String str2, OneSignal.OSInternalExternalUserIdUpdateCompletionHandler oSInternalExternalUserIdUpdateCompletionHandler) throws JSONException {
        if (oSInternalExternalUserIdUpdateCompletionHandler != null) {
            this.externalUserIdUpdateHandlers.add(oSInternalExternalUserIdUpdateCompletionHandler);
        }
        UserState userStateForModification = getUserStateForModification();
        userStateForModification.putOnSyncValues(EXTERNAL_USER_ID, str);
        if (str2 != null) {
            userStateForModification.putOnSyncValues(EXTERNAL_USER_ID_AUTH_HASH, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePlayerDeletedFromServer() {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Creating new player based on missing player_id noted above.");
        OneSignal.handleSuccessfulEmailLogout();
        resetCurrentState();
        updateIdDependents(null);
        scheduleSyncToServer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetCurrentState() {
        getCurrentUserState().setSyncValues(new JSONObject());
        getCurrentUserState().persistState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateLocation(LocationController.LocationPoint locationPoint) {
        getUserStateForModification().setLocation(locationPoint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendPurchases(JSONObject jSONObject, OneSignalRestClient.ResponseHandler responseHandler) {
        OneSignalRestClient.post("players/" + getId() + "/on_purchase", jSONObject, responseHandler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void readyToUpdate(boolean z) {
        boolean z2 = this.canMakeUpdates != z;
        this.canMakeUpdates = z;
        if (z2 && z) {
            scheduleSyncToServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTagsHandlersPerformOnSuccess() {
        JSONObject jSONObject = OneSignalStateSynchronizer.getTags(false).result;
        while (true) {
            OneSignal.ChangeTagsUpdateHandler poll = this.sendTagsHandlers.poll();
            if (poll == null) {
                return;
            }
            poll.onSuccess(jSONObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTagsHandlersPerformOnFailure(OneSignal.SendTagsError sendTagsError) {
        while (true) {
            OneSignal.ChangeTagsUpdateHandler poll = this.sendTagsHandlers.poll();
            if (poll == null) {
                return;
            }
            poll.onFailure(sendTagsError);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void externalUserIdUpdateHandlersPerformOnSuccess() {
        while (true) {
            OneSignal.OSInternalExternalUserIdUpdateCompletionHandler poll = this.externalUserIdUpdateHandlers.poll();
            if (poll == null) {
                return;
            }
            poll.onComplete(getChannelString(), true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void externalUserIdUpdateHandlersPerformOnFailure() {
        while (true) {
            OneSignal.OSInternalExternalUserIdUpdateCompletionHandler poll = this.externalUserIdUpdateHandlers.poll();
            if (poll == null) {
                return;
            }
            poll.onComplete(getChannelString(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deviceInfoHandlersPerformOnSuccess() {
        String language = OneSignalStateSynchronizer.getLanguage();
        while (true) {
            OneSignalStateSynchronizer.OSDeviceInfoCompletionHandler poll = this.deviceInfoCompletionHandler.poll();
            if (poll == null) {
                return;
            }
            poll.onSuccess(language);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deviceInfoHandlersPerformOnFailure(OneSignalStateSynchronizer.OSDeviceInfoError oSDeviceInfoError) {
        while (true) {
            OneSignalStateSynchronizer.OSDeviceInfoCompletionHandler poll = this.deviceInfoCompletionHandler.poll();
            if (poll == null) {
                return;
            }
            poll.onFailure(oSDeviceInfoError);
        }
    }
}
