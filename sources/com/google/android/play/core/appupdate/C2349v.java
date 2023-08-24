package com.google.android.play.core.appupdate;

/* renamed from: com.google.android.play.core.appupdate.v */
/* loaded from: classes3.dex */
final class C2349v extends AppUpdateOptions {

    /* renamed from: a */
    private final int f360a;

    /* renamed from: b */
    private final boolean f361b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ C2349v(int r1, boolean z) {
        this.f360a = r1;
        this.f361b = z;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateOptions
    public final boolean allowAssetPackDeletion() {
        return this.f361b;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateOptions
    public final int appUpdateType() {
        return this.f360a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AppUpdateOptions) {
            AppUpdateOptions appUpdateOptions = (AppUpdateOptions) obj;
            if (this.f360a == appUpdateOptions.appUpdateType() && this.f361b == appUpdateOptions.allowAssetPackDeletion()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.f360a ^ 1000003) * 1000003) ^ (true != this.f361b ? 1237 : 1231);
    }

    public final String toString() {
        int r0 = this.f360a;
        boolean z = this.f361b;
        StringBuilder sb = new StringBuilder(73);
        sb.append("AppUpdateOptions{appUpdateType=");
        sb.append(r0);
        sb.append(", allowAssetPackDeletion=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
