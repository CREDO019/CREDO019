package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfka {
    private final zzfkh zza;
    private final zzfkh zzb;
    private final zzfke zzc;
    private final zzfkg zzd;

    private zzfka(zzfke zzfkeVar, zzfkg zzfkgVar, zzfkh zzfkhVar, zzfkh zzfkhVar2, boolean z) {
        this.zzc = zzfkeVar;
        this.zzd = zzfkgVar;
        this.zza = zzfkhVar;
        if (zzfkhVar2 == null) {
            this.zzb = zzfkh.NONE;
        } else {
            this.zzb = zzfkhVar2;
        }
    }

    public static zzfka zza(zzfke zzfkeVar, zzfkg zzfkgVar, zzfkh zzfkhVar, zzfkh zzfkhVar2, boolean z) {
        zzflg.zzb(zzfkgVar, "ImpressionType is null");
        zzflg.zzb(zzfkhVar, "Impression owner is null");
        if (zzfkhVar != zzfkh.NONE) {
            if (zzfkeVar != zzfke.DEFINED_BY_JAVASCRIPT || zzfkhVar != zzfkh.NATIVE) {
                if (zzfkgVar != zzfkg.DEFINED_BY_JAVASCRIPT || zzfkhVar != zzfkh.NATIVE) {
                    return new zzfka(zzfkeVar, zzfkgVar, zzfkhVar, zzfkhVar2, true);
                }
                throw new IllegalArgumentException("ImpressionType/CreativeType can only be defined as DEFINED_BY_JAVASCRIPT if Impression Owner is JavaScript");
            }
            throw new IllegalArgumentException("ImpressionType/CreativeType can only be defined as DEFINED_BY_JAVASCRIPT if Impression Owner is JavaScript");
        }
        throw new IllegalArgumentException("Impression owner is none");
    }

    public final JSONObject zzb() {
        JSONObject jSONObject = new JSONObject();
        zzfle.zzh(jSONObject, "impressionOwner", this.zza);
        zzfle.zzh(jSONObject, "mediaEventsOwner", this.zzb);
        zzfle.zzh(jSONObject, "creativeType", this.zzc);
        zzfle.zzh(jSONObject, "impressionType", this.zzd);
        zzfle.zzh(jSONObject, "isolateVerificationScripts", true);
        return jSONObject;
    }
}
