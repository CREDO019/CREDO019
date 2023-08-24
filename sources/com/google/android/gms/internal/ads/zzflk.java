package com.google.android.gms.internal.ads;

import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzflk {
    private JSONObject zza;
    private final zzflt zzb;

    public zzflk(zzflt zzfltVar) {
        this.zzb = zzfltVar;
    }

    public final JSONObject zza() {
        return this.zza;
    }

    public final void zzb() {
        this.zzb.zzb(new zzflu(this, null));
    }

    public final void zzc(JSONObject jSONObject, HashSet hashSet, long j) {
        this.zzb.zzb(new zzflv(this, hashSet, jSONObject, j, null));
    }

    public final void zzd(JSONObject jSONObject, HashSet hashSet, long j) {
        this.zzb.zzb(new zzflw(this, hashSet, jSONObject, j, null));
    }

    public final void zze(JSONObject jSONObject) {
        this.zza = jSONObject;
    }
}
