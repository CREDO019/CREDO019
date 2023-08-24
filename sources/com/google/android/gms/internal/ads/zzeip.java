package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzeip implements zzdmn {
    private final zzfcs zza;
    private final zzbwy zzb;
    private final boolean zzc;
    private zzddq zzd = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeip(zzfcs zzfcsVar, zzbwy zzbwyVar, boolean z) {
        this.zza = zzfcsVar;
        this.zzb = zzbwyVar;
        this.zzc = z;
    }

    @Override // com.google.android.gms.internal.ads.zzdmn
    public final void zza(boolean z, Context context, zzddl zzddlVar) throws zzdmm {
        try {
            if (this.zzc ? this.zzb.zzr(ObjectWrapper.wrap(context)) : this.zzb.zzq(ObjectWrapper.wrap(context))) {
                if (this.zzd == null) {
                    return;
                }
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbp)).booleanValue() || this.zza.zzZ != 2) {
                    return;
                }
                this.zzd.zza();
                return;
            }
            throw new zzdmm("Adapter failed to show.");
        } catch (Throwable th) {
            throw new zzdmm(th);
        }
    }

    public final void zzb(zzddq zzddqVar) {
        this.zzd = zzddqVar;
    }
}
