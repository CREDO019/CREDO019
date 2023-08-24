package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcru implements zzfcl {
    private final zzcpu zza;
    private Context zzb;
    private String zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcru(zzcpu zzcpuVar, zzcrt zzcrtVar) {
        this.zza = zzcpuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfcl
    public final /* synthetic */ zzfcl zza(String str) {
        this.zzc = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfcl
    public final /* synthetic */ zzfcl zzb(Context context) {
        Objects.requireNonNull(context);
        this.zzb = context;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfcl
    public final zzfcm zzc() {
        zzguz.zzc(this.zzb, Context.class);
        return new zzcrw(this.zza, this.zzb, this.zzc, null);
    }
}
