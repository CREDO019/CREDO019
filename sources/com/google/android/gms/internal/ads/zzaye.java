package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaye implements zzaya {
    private final zzaya[] zza;
    private final ArrayList zzb;
    private zzaxz zzd;
    private zzatd zze;
    private zzayd zzg;
    private final zzatc zzc = new zzatc();
    private int zzf = -1;

    public zzaye(zzaya... zzayaVarArr) {
        this.zza = zzayaVarArr;
        this.zzb = new ArrayList(Arrays.asList(zzayaVarArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzf(zzaye zzayeVar, int r4, zzatd zzatdVar, Object obj) {
        zzayd zzaydVar;
        if (zzayeVar.zzg == null) {
            for (int r1 = 0; r1 <= 0; r1++) {
                zzatdVar.zzg(r1, zzayeVar.zzc, false);
            }
            int r6 = zzayeVar.zzf;
            if (r6 == -1) {
                zzayeVar.zzf = 1;
            } else if (r6 != 1) {
                zzaydVar = new zzayd(1);
                zzayeVar.zzg = zzaydVar;
            }
            zzaydVar = null;
            zzayeVar.zzg = zzaydVar;
        }
        if (zzayeVar.zzg != null) {
            return;
        }
        zzayeVar.zzb.remove(zzayeVar.zza[r4]);
        if (r4 == 0) {
            zzayeVar.zze = zzatdVar;
        }
        if (zzayeVar.zzb.isEmpty()) {
            zzayeVar.zzd.zzg(zzayeVar.zze, null);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zza() throws IOException {
        zzayd zzaydVar = this.zzg;
        if (zzaydVar != null) {
            throw zzaydVar;
        }
        for (zzaya zzayaVar : this.zza) {
            zzayaVar.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzb(zzasi zzasiVar, boolean z, zzaxz zzaxzVar) {
        this.zzd = zzaxzVar;
        int r5 = 0;
        while (true) {
            zzaya[] zzayaVarArr = this.zza;
            if (r5 >= zzayaVarArr.length) {
                return;
            }
            zzayaVarArr[r5].zzb(zzasiVar, false, new zzayc(this, r5));
            r5++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzc(zzaxy zzaxyVar) {
        zzayb zzaybVar = (zzayb) zzaxyVar;
        int r0 = 0;
        while (true) {
            zzaya[] zzayaVarArr = this.zza;
            if (r0 >= zzayaVarArr.length) {
                return;
            }
            zzayaVarArr[r0].zzc(zzaybVar.zza[r0]);
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzd() {
        for (zzaya zzayaVar : this.zza) {
            zzayaVar.zzd();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final zzaxy zze(int r5, zzazl zzazlVar) {
        int length = this.zza.length;
        zzaxy[] zzaxyVarArr = new zzaxy[length];
        for (int r2 = 0; r2 < length; r2++) {
            zzaxyVarArr[r2] = this.zza[r2].zze(r5, zzazlVar);
        }
        return new zzayb(zzaxyVarArr);
    }
}
