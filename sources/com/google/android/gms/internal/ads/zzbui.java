package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbui implements zzbqd {
    final /* synthetic */ zzbuj zza;
    private final zzbtl zzb;
    private final zzchf zzc;

    public zzbui(zzbuj zzbujVar, zzbtl zzbtlVar, zzchf zzchfVar) {
        this.zza = zzbujVar;
        this.zzb = zzbtlVar;
        this.zzc = zzchfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbqd
    public final void zza(String str) {
        zzbtl zzbtlVar;
        try {
            if (str == null) {
                this.zzc.zze(new zzbtu());
            } else {
                this.zzc.zze(new zzbtu(str));
            }
            zzbtlVar = this.zzb;
        } catch (IllegalStateException unused) {
            zzbtlVar = this.zzb;
        } catch (Throwable th) {
            this.zzb.zzb();
            throw th;
        }
        zzbtlVar.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzbqd
    public final void zzb(JSONObject jSONObject) {
        zzbtl zzbtlVar;
        zzbtx zzbtxVar;
        try {
            try {
                zzchf zzchfVar = this.zzc;
                zzbtxVar = this.zza.zza;
                zzchfVar.zzd(zzbtxVar.zza(jSONObject));
                zzbtlVar = this.zzb;
            } catch (IllegalStateException unused) {
                zzbtlVar = this.zzb;
            } catch (JSONException e) {
                this.zzc.zze(e);
                zzbtlVar = this.zzb;
            }
            zzbtlVar.zzb();
        } catch (Throwable th) {
            this.zzb.zzb();
            throw th;
        }
    }
}
