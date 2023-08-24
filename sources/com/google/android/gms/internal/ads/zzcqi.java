package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcqi implements zzexq {
    private final zzcpu zza;
    private Context zzb;
    private String zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcqi(zzcpu zzcpuVar, zzcqh zzcqhVar) {
        this.zza = zzcpuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzexq
    public final /* synthetic */ zzexq zza(String str) {
        Objects.requireNonNull(str);
        this.zzc = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzexq
    public final /* synthetic */ zzexq zzb(Context context) {
        Objects.requireNonNull(context);
        this.zzb = context;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzexq
    public final zzexr zzc() {
        zzguz.zzc(this.zzb, Context.class);
        zzguz.zzc(this.zzc, String.class);
        return new zzcqk(this.zza, this.zzb, this.zzc, null);
    }
}
