package com.google.android.gms.internal.ads;

import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbuj implements zzbtv {
    private final zzbtx zza;
    private final zzbty zzb;
    private final zzbtr zzc;
    private final String zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbuj(zzbtr zzbtrVar, String str, zzbty zzbtyVar, zzbtx zzbtxVar) {
        this.zzc = zzbtrVar;
        this.zzd = str;
        this.zzb = zzbtyVar;
        this.zza = zzbtxVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzd(zzbuj zzbujVar, zzbtl zzbtlVar, zzbts zzbtsVar, Object obj, zzchf zzchfVar) {
        try {
            com.google.android.gms.ads.internal.zzt.zzq();
            String str = UUID.randomUUID().toString();
            zzbpp.zzo.zzc(str, new zzbui(zzbujVar, zzbtlVar, zzchfVar));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", str);
            jSONObject.put("args", zzbujVar.zzb.zzb(obj));
            zzbtsVar.zzl(zzbujVar.zzd, jSONObject);
        } catch (Exception e) {
            try {
                zzchfVar.zze(e);
                com.google.android.gms.ads.internal.util.zze.zzh("Unable to invokeJavascript", e);
            } finally {
                zzbtlVar.zzb();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfxv
    public final zzfyx zza(Object obj) throws Exception {
        return zzb(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzbtv
    public final zzfyx zzb(Object obj) {
        zzchf zzchfVar = new zzchf();
        zzbtl zzb = this.zzc.zzb(null);
        zzb.zzi(new zzbug(this, zzb, obj, zzchfVar), new zzbuh(this, zzchfVar, zzb));
        return zzchfVar;
    }
}
