package com.google.android.gms.internal.ads;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzvc extends zzcu {
    public static final zzvc zzD;
    @Deprecated
    public static final zzvc zzE;
    public static final zzn zzF;
    public final boolean zzG;
    public final boolean zzH;
    public final boolean zzI;
    public final boolean zzJ;
    public final boolean zzK;
    public final boolean zzL;
    public final boolean zzM;
    public final boolean zzN;
    public final boolean zzO;
    public final boolean zzP;
    public final boolean zzQ;
    public final boolean zzR;
    public final boolean zzS;
    private final SparseArray zzT;
    private final SparseBooleanArray zzU;

    static {
        zzvc zzvcVar = new zzvc(new zzva());
        zzD = zzvcVar;
        zzE = zzvcVar;
        zzF = new zzn() { // from class: com.google.android.gms.internal.ads.zzuy
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public zzvc(zzva zzvaVar) {
        super(zzvaVar);
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        SparseArray sparseArray;
        SparseBooleanArray sparseBooleanArray;
        z = zzvaVar.zza;
        this.zzG = z;
        this.zzH = false;
        z2 = zzvaVar.zzb;
        this.zzI = z2;
        this.zzJ = false;
        z3 = zzvaVar.zzc;
        this.zzK = z3;
        this.zzL = false;
        this.zzM = false;
        this.zzN = false;
        this.zzO = false;
        z4 = zzvaVar.zzd;
        this.zzP = z4;
        z5 = zzvaVar.zze;
        this.zzQ = z5;
        this.zzR = false;
        z6 = zzvaVar.zzf;
        this.zzS = z6;
        sparseArray = zzvaVar.zzg;
        this.zzT = sparseArray;
        sparseBooleanArray = zzvaVar.zzh;
        this.zzU = sparseBooleanArray;
    }

    public static zzvc zzd(Context context) {
        return new zzvc(new zzva(context));
    }

    @Override // com.google.android.gms.internal.ads.zzcu
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzvc zzvcVar = (zzvc) obj;
            if (super.equals(zzvcVar) && this.zzG == zzvcVar.zzG && this.zzI == zzvcVar.zzI && this.zzK == zzvcVar.zzK && this.zzP == zzvcVar.zzP && this.zzQ == zzvcVar.zzQ && this.zzS == zzvcVar.zzS) {
                SparseBooleanArray sparseBooleanArray = this.zzU;
                SparseBooleanArray sparseBooleanArray2 = zzvcVar.zzU;
                int size = sparseBooleanArray.size();
                if (sparseBooleanArray2.size() == size) {
                    int r5 = 0;
                    while (true) {
                        if (r5 < size) {
                            if (sparseBooleanArray2.indexOfKey(sparseBooleanArray.keyAt(r5)) < 0) {
                                break;
                            }
                            r5++;
                        } else {
                            SparseArray sparseArray = this.zzT;
                            SparseArray sparseArray2 = zzvcVar.zzT;
                            int size2 = sparseArray.size();
                            if (sparseArray2.size() == size2) {
                                for (int r4 = 0; r4 < size2; r4++) {
                                    int indexOfKey = sparseArray2.indexOfKey(sparseArray.keyAt(r4));
                                    if (indexOfKey >= 0) {
                                        Map map = (Map) sparseArray.valueAt(r4);
                                        Map map2 = (Map) sparseArray2.valueAt(indexOfKey);
                                        if (map2.size() == map.size()) {
                                            for (Map.Entry entry : map.entrySet()) {
                                                zzue zzueVar = (zzue) entry.getKey();
                                                if (map2.containsKey(zzueVar)) {
                                                    if (!zzel.zzT(entry.getValue(), map2.get(zzueVar))) {
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzcu
    public final int hashCode() {
        return ((((((((((((super.hashCode() + 31) * 31) + (this.zzG ? 1 : 0)) * 961) + (this.zzI ? 1 : 0)) * 961) + (this.zzK ? 1 : 0)) * 28629151) + (this.zzP ? 1 : 0)) * 31) + (this.zzQ ? 1 : 0)) * 961) + (this.zzS ? 1 : 0);
    }

    public final zzva zzc() {
        return new zzva(this, null);
    }

    @Deprecated
    public final zzve zze(int r2, zzue zzueVar) {
        Map map = (Map) this.zzT.get(r2);
        if (map != null) {
            return (zzve) map.get(zzueVar);
        }
        return null;
    }

    public final boolean zzf(int r2) {
        return this.zzU.get(r2);
    }

    @Deprecated
    public final boolean zzg(int r2, zzue zzueVar) {
        Map map = (Map) this.zzT.get(r2);
        return map != null && map.containsKey(zzueVar);
    }
}
