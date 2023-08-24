package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzevi implements zzeun {
    private final Executor zza;
    private final String zzb;
    private final PackageInfo zzc;
    private final zzcfn zzd;

    public zzevi(zzcfn zzcfnVar, Executor executor, String str, PackageInfo packageInfo, int r5, byte[] bArr) {
        this.zzd = zzcfnVar;
        this.zza = executor;
        this.zzb = str;
        this.zzc = packageInfo;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 41;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return zzfyo.zzg(zzfyo.zzm(zzfyo.zzi(this.zzb), new zzfru() { // from class: com.google.android.gms.internal.ads.zzevg
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                return new zzevj((String) obj);
            }
        }, this.zza), Throwable.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzevh
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzevi.this.zzc((Throwable) obj);
            }
        }, this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(Throwable th) throws Exception {
        return zzfyo.zzi(new zzevj(this.zzb));
    }
}
