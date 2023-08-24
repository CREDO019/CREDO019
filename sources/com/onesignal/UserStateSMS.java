package com.onesignal;

/* loaded from: classes3.dex */
class UserStateSMS extends UserState {
    private static final String SMS = "sms";

    @Override // com.onesignal.UserState
    protected void addDependFields() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.UserState
    public boolean isSubscribed() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserStateSMS(String str, boolean z) {
        super(SMS + str, z);
    }

    @Override // com.onesignal.UserState
    UserState newInstance(String str) {
        return new UserStateSMS(str, false);
    }
}
