package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgck extends zzgel {
    final /* synthetic */ zzgcl zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgck(zzgcl zzgclVar, Class cls) {
        super(cls);
        this.zza = zzgclVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgkv zzgkvVar = (zzgkv) zzgpxVar;
        zzgkr zzc = zzgks.zzc();
        zzc.zzb(0);
        zzc.zza(zzgnf.zzv(zzgmg.zza(32)));
        return (zzgks) zzc.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgkv.zzd(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("XCHACHA20_POLY1305", new zzgek(zzgkv.zzc(), 1));
        hashMap.put("XCHACHA20_POLY1305_RAW", new zzgek(zzgkv.zzc(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgkv zzgkvVar = (zzgkv) zzgpxVar;
    }
}
