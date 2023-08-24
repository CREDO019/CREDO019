package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcrq implements zzfax {
    private final zzcpu zza;
    private Context zzb;
    private String zzc;
    private com.google.android.gms.ads.internal.client.zzq zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcrq(zzcpu zzcpuVar, zzcrp zzcrpVar) {
        this.zza = zzcpuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfax
    public final /* synthetic */ zzfax zza(com.google.android.gms.ads.internal.client.zzq zzqVar) {
        Objects.requireNonNull(zzqVar);
        this.zzd = zzqVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfax
    public final /* synthetic */ zzfax zzb(String str) {
        Objects.requireNonNull(str);
        this.zzc = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfax
    public final /* synthetic */ zzfax zzc(Context context) {
        Objects.requireNonNull(context);
        this.zzb = context;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfax
    public final zzfay zzd() {
        zzguz.zzc(this.zzb, Context.class);
        zzguz.zzc(this.zzc, String.class);
        zzguz.zzc(this.zzd, com.google.android.gms.ads.internal.client.zzq.class);
        return new zzcrs(this.zza, this.zzb, this.zzc, this.zzd, null);
    }
}
