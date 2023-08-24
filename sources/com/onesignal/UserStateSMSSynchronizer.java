package com.onesignal;

import com.onesignal.OneSignalStateSynchronizer;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class UserStateSMSSynchronizer extends UserStateSecondaryChannelSynchronizer {
    @Override // com.onesignal.UserStateSecondaryChannelSynchronizer
    protected String getAuthHashKey() {
        return "sms_auth_hash";
    }

    @Override // com.onesignal.UserStateSecondaryChannelSynchronizer
    protected String getChannelKey() {
        return "sms_number";
    }

    @Override // com.onesignal.UserStateSecondaryChannelSynchronizer
    protected int getDeviceType() {
        return 14;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserStateSMSSynchronizer() {
        super(OneSignalStateSynchronizer.UserStateSynchronizerType.SMS);
    }

    @Override // com.onesignal.UserStateSecondaryChannelSynchronizer, com.onesignal.UserStateSynchronizer
    protected UserState newUserState(String str, boolean z) {
        return new UserStateSMS(str, z);
    }

    @Override // com.onesignal.UserStateSecondaryChannelSynchronizer, com.onesignal.UserStateSynchronizer
    protected String getId() {
        return OneSignal.getSMSId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public void saveChannelId(String str) {
        OneSignal.saveSMSId(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSecondaryChannelSynchronizer, com.onesignal.UserStateSynchronizer
    public void logoutChannel() {
        saveChannelId("");
        resetCurrentState();
        getToSyncUserState().removeFromSyncValues("identifier");
        ArrayList arrayList = new ArrayList();
        arrayList.add("sms_auth_hash");
        arrayList.add("device_player_id");
        arrayList.add("external_user_id");
        getToSyncUserState().removeFromSyncValues(arrayList);
        getToSyncUserState().persistState();
        OneSignal.getSMSSubscriptionState().clearSMSAndId();
    }

    @Override // com.onesignal.UserStateSecondaryChannelSynchronizer
    void fireUpdateSuccess(JSONObject jSONObject) {
        OneSignal.fireSMSUpdateSuccess(jSONObject);
    }

    @Override // com.onesignal.UserStateSecondaryChannelSynchronizer
    void fireUpdateFailure() {
        OneSignal.fireSMSUpdateFailure();
    }

    @Override // com.onesignal.UserStateSecondaryChannelSynchronizer, com.onesignal.UserStateSynchronizer
    void updateIdDependents(String str) {
        OneSignal.updateSMSIdDependents(str);
    }
}
