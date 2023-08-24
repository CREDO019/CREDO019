package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzebk implements zzgur {
    private final zzgve zza;

    public zzebk(zzgve zzgveVar) {
        this.zza = zzgveVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final String zzb() {
        String packageName = ((zzcoq) this.zza).zza().getPackageName();
        zzguz.zzb(packageName);
        return packageName;
    }
}
