package com.google.android.gms.internal.ads;

import android.util.SparseArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzuc {
    private int zza;
    private final SparseArray zzb;
    private final zzdh zzc;

    public zzuc() {
        zzub zzubVar = new zzdh() { // from class: com.google.android.gms.internal.ads.zzub
        };
        throw null;
    }

    public final Object zzb() {
        SparseArray sparseArray = this.zzb;
        return sparseArray.valueAt(sparseArray.size() - 1);
    }

    public final void zzc(int r6, Object obj) {
        if (this.zza == -1) {
            zzdd.zzf(this.zzb.size() == 0);
            this.zza = 0;
        }
        if (this.zzb.size() > 0) {
            SparseArray sparseArray = this.zzb;
            int keyAt = sparseArray.keyAt(sparseArray.size() - 1);
            zzdd.zzd(r6 >= keyAt);
            if (keyAt == r6) {
                SparseArray sparseArray2 = this.zzb;
                zztv.zzl((zztt) sparseArray2.valueAt(sparseArray2.size() - 1));
            }
        }
        this.zzb.append(r6, obj);
    }

    public final void zzd() {
        for (int r0 = 0; r0 < this.zzb.size(); r0++) {
            zztv.zzl((zztt) this.zzb.valueAt(r0));
        }
        this.zza = -1;
        this.zzb.clear();
    }

    public final void zze(int r4) {
        int r0 = 0;
        while (r0 < this.zzb.size() - 1) {
            int r1 = r0 + 1;
            if (r4 < this.zzb.keyAt(r1)) {
                return;
            }
            zztv.zzl((zztt) this.zzb.valueAt(r0));
            this.zzb.removeAt(r0);
            int r02 = this.zza;
            if (r02 > 0) {
                this.zza = r02 - 1;
            }
            r0 = r1;
        }
    }

    public final boolean zzf() {
        return this.zzb.size() == 0;
    }

    public zzuc(zzdh zzdhVar) {
        this.zzb = new SparseArray();
        this.zzc = zzdhVar;
        this.zza = -1;
    }

    public final Object zza(int r4) {
        if (this.zza == -1) {
            this.zza = 0;
        }
        while (true) {
            int r0 = this.zza;
            if (r0 > 0 && r4 < this.zzb.keyAt(r0)) {
                this.zza--;
            }
        }
        while (this.zza < this.zzb.size() - 1 && r4 >= this.zzb.keyAt(this.zza + 1)) {
            this.zza++;
        }
        return this.zzb.valueAt(this.zza);
    }
}
