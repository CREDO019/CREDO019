package com.google.android.gms.ads.internal.util;

import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzake;
import com.google.android.gms.internal.ads.zzakf;
import com.google.android.gms.internal.ads.zzalf;
import com.google.android.gms.internal.ads.zzcgm;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbi extends zzalf {
    final /* synthetic */ byte[] zza;
    final /* synthetic */ Map zzb;
    final /* synthetic */ zzcgm zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbi(zzbo zzboVar, int r2, String str, zzakf zzakfVar, zzake zzakeVar, byte[] bArr, Map map, zzcgm zzcgmVar) {
        super(r2, str, zzakfVar, zzakeVar);
        this.zza = bArr;
        this.zzb = map;
        this.zzc = zzcgmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaka
    public final Map zzl() throws zzaji {
        Map map = this.zzb;
        return map == null ? Collections.emptyMap() : map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzalf, com.google.android.gms.internal.ads.zzaka
    public final /* bridge */ /* synthetic */ void zzo(Object obj) {
        zzo((String) obj);
    }

    @Override // com.google.android.gms.internal.ads.zzaka
    public final byte[] zzx() throws zzaji {
        byte[] bArr = this.zza;
        if (bArr == null) {
            return null;
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzalf
    public final void zzz(String str) {
        this.zzc.zzg(str);
        super.zzo(str);
    }
}
