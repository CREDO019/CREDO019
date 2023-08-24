package com.google.android.gms.internal.ads;

import android.util.SparseArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaif implements zzahy {
    final /* synthetic */ zzaih zza;
    private final zzec zzb = new zzec(new byte[4], 4);

    public zzaif(zzaih zzaihVar) {
        this.zza = zzaihVar;
    }

    @Override // com.google.android.gms.internal.ads.zzahy
    public final void zza(zzed zzedVar) {
        SparseArray sparseArray;
        SparseArray sparseArray2;
        SparseArray sparseArray3;
        int r5;
        if (zzedVar.zzk() == 0 && (zzedVar.zzk() & 128) != 0) {
            zzedVar.zzG(6);
            int zza = zzedVar.zza() / 4;
            for (int r3 = 0; r3 < zza; r3++) {
                zzedVar.zzA(this.zzb, 4);
                int zzc = this.zzb.zzc(16);
                this.zzb.zzj(3);
                if (zzc == 0) {
                    this.zzb.zzj(13);
                } else {
                    int zzc2 = this.zzb.zzc(13);
                    sparseArray2 = this.zza.zzf;
                    if (sparseArray2.get(zzc2) == null) {
                        zzaih zzaihVar = this.zza;
                        sparseArray3 = zzaihVar.zzf;
                        sparseArray3.put(zzc2, new zzahz(new zzaig(zzaihVar, zzc2)));
                        zzaih zzaihVar2 = this.zza;
                        r5 = zzaihVar2.zzl;
                        zzaihVar2.zzl = r5 + 1;
                    }
                }
            }
            sparseArray = this.zza.zzf;
            sparseArray.remove(0);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzahy
    public final void zzb(zzej zzejVar, zzzi zzziVar, zzail zzailVar) {
    }
}
