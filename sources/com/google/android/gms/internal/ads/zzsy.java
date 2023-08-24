package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzsy extends zzrp {
    private static final zzbg zza;
    private final zzsi[] zzb;
    private final zzcn[] zzc;
    private final ArrayList zzd;
    private final Map zze;
    private final zzfvr zzf;
    private int zzg;
    private long[][] zzh;
    private zzsx zzi;
    private final zzrr zzj;

    static {
        zzaj zzajVar = new zzaj();
        zzajVar.zza("MergingMediaSource");
        zza = zzajVar.zzc();
    }

    public zzsy(boolean z, boolean z2, zzsi... zzsiVarArr) {
        zzrr zzrrVar = new zzrr();
        this.zzb = zzsiVarArr;
        this.zzj = zzrrVar;
        this.zzd = new ArrayList(Arrays.asList(zzsiVarArr));
        this.zzg = -1;
        this.zzc = new zzcn[zzsiVarArr.length];
        this.zzh = new long[0];
        this.zze = new HashMap();
        this.zzf = zzfvy.zzb(8).zzb(2).zza();
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzB(zzse zzseVar) {
        zzsw zzswVar = (zzsw) zzseVar;
        int r0 = 0;
        while (true) {
            zzsi[] zzsiVarArr = this.zzb;
            if (r0 >= zzsiVarArr.length) {
                return;
            }
            zzsiVarArr[r0].zzB(zzswVar.zzn(r0));
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final zzse zzD(zzsg zzsgVar, zzwf zzwfVar, long j) {
        int length = this.zzb.length;
        zzse[] zzseVarArr = new zzse[length];
        int zza2 = this.zzc[0].zza(zzsgVar.zza);
        for (int r3 = 0; r3 < length; r3++) {
            zzseVarArr[r3] = this.zzb[r3].zzD(zzsgVar.zzc(this.zzc[r3].zzf(zza2)), zzwfVar, j - this.zzh[zza2][r3]);
        }
        return new zzsw(this.zzj, this.zzh[zza2], zzseVarArr, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzrp, com.google.android.gms.internal.ads.zzrh
    public final void zzn(zzfx zzfxVar) {
        super.zzn(zzfxVar);
        for (int r3 = 0; r3 < this.zzb.length; r3++) {
            zzy(Integer.valueOf(r3), this.zzb[r3]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzrp, com.google.android.gms.internal.ads.zzrh
    public final void zzq() {
        super.zzq();
        Arrays.fill(this.zzc, (Object) null);
        this.zzg = -1;
        this.zzi = null;
        this.zzd.clear();
        Collections.addAll(this.zzd, this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzrp
    public final /* bridge */ /* synthetic */ zzsg zzv(Object obj, zzsg zzsgVar) {
        if (((Integer) obj).intValue() == 0) {
            return zzsgVar;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzrp, com.google.android.gms.internal.ads.zzsi
    public final void zzw() throws IOException {
        zzsx zzsxVar = this.zzi;
        if (zzsxVar != null) {
            throw zzsxVar;
        }
        super.zzw();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzrp
    public final /* bridge */ /* synthetic */ void zzx(Object obj, zzsi zzsiVar, zzcn zzcnVar) {
        int r0;
        if (this.zzi != null) {
            return;
        }
        if (this.zzg == -1) {
            r0 = zzcnVar.zzb();
            this.zzg = r0;
        } else {
            int zzb = zzcnVar.zzb();
            int r1 = this.zzg;
            if (zzb != r1) {
                this.zzi = new zzsx(0);
                return;
            }
            r0 = r1;
        }
        if (this.zzh.length == 0) {
            this.zzh = (long[][]) Array.newInstance(long.class, r0, this.zzc.length);
        }
        this.zzd.remove(zzsiVar);
        this.zzc[((Integer) obj).intValue()] = zzcnVar;
        if (this.zzd.isEmpty()) {
            zzo(this.zzc[0]);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final zzbg zzz() {
        zzsi[] zzsiVarArr = this.zzb;
        return zzsiVarArr.length > 0 ? zzsiVarArr[0].zzz() : zza;
    }
}
