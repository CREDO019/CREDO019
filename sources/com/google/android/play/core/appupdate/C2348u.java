package com.google.android.play.core.appupdate;

import com.google.android.play.core.appupdate.AppUpdateOptions;

/* renamed from: com.google.android.play.core.appupdate.u */
/* loaded from: classes3.dex */
final class C2348u extends AppUpdateOptions.Builder {

    /* renamed from: a */
    private Integer f358a;

    /* renamed from: b */
    private Boolean f359b;

    @Override // com.google.android.play.core.appupdate.AppUpdateOptions.Builder
    public final AppUpdateOptions build() {
        String concat = this.f358a == null ? "".concat(" appUpdateType") : "";
        if (this.f359b == null) {
            concat = String.valueOf(concat).concat(" allowAssetPackDeletion");
        }
        if (concat.isEmpty()) {
            return new C2349v(this.f358a.intValue(), this.f359b.booleanValue());
        }
        String valueOf = String.valueOf(concat);
        throw new IllegalStateException(valueOf.length() != 0 ? "Missing required properties:".concat(valueOf) : new String("Missing required properties:"));
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateOptions.Builder
    public final AppUpdateOptions.Builder setAllowAssetPackDeletion(boolean z) {
        this.f359b = Boolean.valueOf(z);
        return this;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateOptions.Builder
    public final AppUpdateOptions.Builder setAppUpdateType(int r1) {
        this.f358a = Integer.valueOf(r1);
        return this;
    }
}
