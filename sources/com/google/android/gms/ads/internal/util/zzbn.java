package com.google.android.gms.ads.internal.util;

import androidx.browser.trusted.sharing.ShareTarget;
import com.google.android.gms.internal.ads.zzajw;
import com.google.android.gms.internal.ads.zzaka;
import com.google.android.gms.internal.ads.zzakg;
import com.google.android.gms.internal.ads.zzakx;
import com.google.android.gms.internal.ads.zzcgm;
import com.google.android.gms.internal.ads.zzchf;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbn extends zzaka {
    private final zzchf zza;
    private final zzcgm zzb;

    public zzbn(String str, Map map, zzchf zzchfVar) {
        super(0, str, new zzbm(zzchfVar));
        this.zza = zzchfVar;
        zzcgm zzcgmVar = new zzcgm(null);
        this.zzb = zzcgmVar;
        zzcgmVar.zzd(str, ShareTarget.METHOD_GET, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzaka
    public final zzakg zzh(zzajw zzajwVar) {
        return zzakg.zzb(zzajwVar, zzakx.zzb(zzajwVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzaka
    public final /* bridge */ /* synthetic */ void zzo(Object obj) {
        zzajw zzajwVar = (zzajw) obj;
        this.zzb.zzf(zzajwVar.zzc, zzajwVar.zza);
        zzcgm zzcgmVar = this.zzb;
        byte[] bArr = zzajwVar.zzb;
        if (zzcgm.zzl() && bArr != null) {
            zzcgmVar.zzh(bArr);
        }
        this.zza.zzd(zzajwVar);
    }
}
