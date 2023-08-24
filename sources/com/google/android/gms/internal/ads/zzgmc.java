package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.Key;
import javax.crypto.Mac;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgmc extends ThreadLocal {
    final /* synthetic */ zzgmd zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgmc(zzgmd zzgmdVar) {
        this.zza = zzgmdVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.lang.ThreadLocal
    /* renamed from: zza */
    public final Mac initialValue() {
        String str;
        Key key;
        try {
            zzglp zzglpVar = zzglp.zzb;
            str = this.zza.zzb;
            Mac mac = (Mac) zzglpVar.zza(str);
            key = this.zza.zzc;
            mac.init(key);
            return mac;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
