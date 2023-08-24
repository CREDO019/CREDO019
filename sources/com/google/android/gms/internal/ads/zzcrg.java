package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcrg implements zzdyw {
    private final zzcpu zza;
    private Context zzb;
    private zzbqm zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcrg(zzcpu zzcpuVar, zzcrf zzcrfVar) {
        this.zza = zzcpuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdyw
    public final /* synthetic */ zzdyw zza(zzbqm zzbqmVar) {
        Objects.requireNonNull(zzbqmVar);
        this.zzc = zzbqmVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdyw
    public final /* synthetic */ zzdyw zzb(Context context) {
        Objects.requireNonNull(context);
        this.zzb = context;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdyw
    public final zzdyx zzc() {
        zzguz.zzc(this.zzb, Context.class);
        zzguz.zzc(this.zzc, zzbqm.class);
        return new zzcri(this.zza, this.zzb, this.zzc, null);
    }
}
