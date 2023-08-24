package com.onesignal;

/* loaded from: classes3.dex */
class UserStateEmail extends UserState {
    private static final String EMAIL = "email";

    @Override // com.onesignal.UserState
    protected void addDependFields() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.UserState
    public boolean isSubscribed() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserStateEmail(String str, boolean z) {
        super("email" + str, z);
    }

    @Override // com.onesignal.UserState
    UserState newInstance(String str) {
        return new UserStateEmail(str, false);
    }
}
