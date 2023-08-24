package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzdcb implements zzdem, zzddt {
    private final Context zza;
    private final zzfcs zzb;
    private final zzbzf zzc;

    public zzdcb(Context context, zzfcs zzfcsVar, zzbzf zzbzfVar, byte[] bArr) {
        this.zza = context;
        this.zzb = zzfcsVar;
        this.zzc = zzbzfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbq(Context context) {
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbs(Context context) {
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbt(Context context) {
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final void zzn() {
        zzbzg zzbzgVar = this.zzb.zzae;
        if (zzbzgVar == null || !zzbzgVar.zza) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (this.zzb.zzae.zzb.isEmpty()) {
            return;
        }
        arrayList.add(this.zzb.zzae.zzb);
    }
}
