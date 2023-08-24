package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfes implements zzddb {
    private final HashSet zza = new HashSet();
    private final Context zzb;
    private final zzcga zzc;

    public zzfes(Context context, zzcga zzcgaVar) {
        this.zzb = context;
        this.zzc = zzcgaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final synchronized void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        if (zzeVar.zza != 3) {
            this.zzc.zzi(this.zza);
        }
    }

    public final Bundle zzb() {
        return this.zzc.zzk(this.zzb, this);
    }

    public final synchronized void zzc(HashSet hashSet) {
        this.zza.clear();
        this.zza.addAll(hashSet);
    }
}
