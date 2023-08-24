package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Looper;
import com.google.android.exoplayer2.C1856C;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zztn extends zzrh implements zzte {
    private final zzbg zza;
    private final zzay zzb;
    private final zzeu zzc;
    private final zzpo zzd;
    private final int zze;
    private boolean zzf;
    private long zzg;
    private boolean zzh;
    private boolean zzi;
    private zzfx zzj;
    private final zztk zzk;
    private final zzwj zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zztn(zzbg zzbgVar, zzeu zzeuVar, zztk zztkVar, zzpo zzpoVar, zzwj zzwjVar, int r6, zztm zztmVar, byte[] bArr) {
        zzay zzayVar = zzbgVar.zzd;
        Objects.requireNonNull(zzayVar);
        this.zzb = zzayVar;
        this.zza = zzbgVar;
        this.zzc = zzeuVar;
        this.zzk = zztkVar;
        this.zzd = zzpoVar;
        this.zzl = zzwjVar;
        this.zze = r6;
        this.zzf = true;
        this.zzg = C1856C.TIME_UNSET;
    }

    private final void zzv() {
        long j = this.zzg;
        boolean z = this.zzh;
        boolean z2 = this.zzi;
        zzbg zzbgVar = this.zza;
        zzua zzuaVar = new zzua(C1856C.TIME_UNSET, C1856C.TIME_UNSET, C1856C.TIME_UNSET, j, j, 0L, 0L, z, false, false, null, zzbgVar, z2 ? zzbgVar.zzf : null);
        zzo(this.zzf ? new zztj(this, zzuaVar) : zzuaVar);
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzB(zzse zzseVar) {
        ((zzti) zzseVar).zzK();
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final zzse zzD(zzsg zzsgVar, zzwf zzwfVar, long j) {
        zzev zza = this.zzc.zza();
        zzfx zzfxVar = this.zzj;
        if (zzfxVar != null) {
            zza.zzf(zzfxVar);
        }
        Uri uri = this.zzb.zza;
        zztk zztkVar = this.zzk;
        zzb();
        zzrj zzrjVar = new zzrj(zztkVar.zza);
        zzpo zzpoVar = this.zzd;
        zzpi zzc = zzc(zzsgVar);
        zzwj zzwjVar = this.zzl;
        zzsp zze = zze(zzsgVar);
        String str = this.zzb.zzf;
        return new zzti(uri, zza, zzrjVar, zzpoVar, zzc, zzwjVar, zze, this, zzwfVar, null, this.zze, null);
    }

    @Override // com.google.android.gms.internal.ads.zzte
    public final void zza(long j, boolean z, boolean z2) {
        if (j == C1856C.TIME_UNSET) {
            j = this.zzg;
        }
        if (!this.zzf && this.zzg == j && this.zzh == z && this.zzi == z2) {
            return;
        }
        this.zzg = j;
        this.zzh = z;
        this.zzi = z2;
        this.zzf = false;
        zzv();
    }

    @Override // com.google.android.gms.internal.ads.zzrh
    protected final void zzn(zzfx zzfxVar) {
        this.zzj = zzfxVar;
        Objects.requireNonNull(Looper.myLooper());
        zzb();
        zzv();
    }

    @Override // com.google.android.gms.internal.ads.zzrh
    protected final void zzq() {
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzw() {
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final zzbg zzz() {
        return this.zza;
    }
}
