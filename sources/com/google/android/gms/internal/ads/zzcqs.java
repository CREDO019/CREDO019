package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcqs implements zzeze {
    private final zzcpu zza;
    private Context zzb;
    private String zzc;
    private com.google.android.gms.ads.internal.client.zzq zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcqs(zzcpu zzcpuVar, zzcqr zzcqrVar) {
        this.zza = zzcpuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeze
    public final /* synthetic */ zzeze zza(com.google.android.gms.ads.internal.client.zzq zzqVar) {
        Objects.requireNonNull(zzqVar);
        this.zzd = zzqVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzeze
    public final /* synthetic */ zzeze zzb(String str) {
        Objects.requireNonNull(str);
        this.zzc = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzeze
    public final /* synthetic */ zzeze zzc(Context context) {
        Objects.requireNonNull(context);
        this.zzb = context;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzeze
    public final zzezf zzd() {
        zzguz.zzc(this.zzb, Context.class);
        zzguz.zzc(this.zzc, String.class);
        zzguz.zzc(this.zzd, com.google.android.gms.ads.internal.client.zzq.class);
        return new zzcqu(this.zza, this.zzb, this.zzc, this.zzd, null);
    }
}
