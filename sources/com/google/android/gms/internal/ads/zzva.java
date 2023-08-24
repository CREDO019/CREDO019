package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Point;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzva extends zzct {
    private boolean zza;
    private boolean zzb;
    private boolean zzc;
    private boolean zzd;
    private boolean zze;
    private boolean zzf;
    private final SparseArray zzg;
    private final SparseBooleanArray zzh;

    @Deprecated
    public zzva() {
        this.zzg = new SparseArray();
        this.zzh = new SparseBooleanArray();
        zzv();
    }

    private final void zzv() {
        this.zza = true;
        this.zzb = true;
        this.zzc = true;
        this.zzd = true;
        this.zze = true;
        this.zzf = true;
    }

    @Override // com.google.android.gms.internal.ads.zzct
    public final /* synthetic */ zzct zze(int r1, int r2, boolean z) {
        super.zze(r1, r2, true);
        return this;
    }

    public final zzva zzo(int r2, boolean z) {
        if (this.zzh.get(r2) == z) {
            return this;
        }
        if (z) {
            this.zzh.put(r2, true);
        } else {
            this.zzh.delete(r2);
        }
        return this;
    }

    public zzva(Context context) {
        super.zzd(context);
        Point zzB = zzel.zzB(context);
        zze(zzB.x, zzB.y, true);
        this.zzg = new SparseArray();
        this.zzh = new SparseBooleanArray();
        zzv();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzva(zzvc zzvcVar, zzuz zzuzVar) {
        super(zzvcVar);
        this.zza = zzvcVar.zzG;
        this.zzb = zzvcVar.zzI;
        this.zzc = zzvcVar.zzK;
        this.zzd = zzvcVar.zzP;
        this.zze = zzvcVar.zzQ;
        this.zzf = zzvcVar.zzS;
        SparseArray zza = zzvc.zza(zzvcVar);
        SparseArray sparseArray = new SparseArray();
        for (int r1 = 0; r1 < zza.size(); r1++) {
            sparseArray.put(zza.keyAt(r1), new HashMap((Map) zza.valueAt(r1)));
        }
        this.zzg = sparseArray;
        this.zzh = zzvc.zzb(zzvcVar).clone();
    }
}
