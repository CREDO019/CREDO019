package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzjw extends zzgh {
    private final int zzc;
    private final int zzd;
    private final int[] zze;
    private final int[] zzf;
    private final zzcn[] zzg;
    private final Object[] zzh;
    private final HashMap zzi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzjw(Collection collection, zztz zztzVar, byte[] bArr) {
        super(false, zztzVar, null);
        int r7 = 0;
        int size = collection.size();
        this.zze = new int[size];
        this.zzf = new int[size];
        this.zzg = new zzcn[size];
        this.zzh = new Object[size];
        this.zzi = new HashMap();
        Iterator it = collection.iterator();
        int r6 = 0;
        int r0 = 0;
        while (it.hasNext()) {
            zzjl zzjlVar = (zzjl) it.next();
            this.zzg[r0] = zzjlVar.zza();
            this.zzf[r0] = r7;
            this.zze[r0] = r6;
            r7 += this.zzg[r0].zzc();
            r6 += this.zzg[r0].zzb();
            this.zzh[r0] = zzjlVar.zzb();
            this.zzi.put(this.zzh[r0], Integer.valueOf(r0));
            r0++;
        }
        this.zzc = r7;
        this.zzd = r6;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zzb() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzgh
    protected final int zzp(Object obj) {
        Integer num = (Integer) this.zzi.get(obj);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    @Override // com.google.android.gms.internal.ads.zzgh
    protected final int zzq(int r3) {
        return zzel.zzc(this.zze, r3 + 1, false, false);
    }

    @Override // com.google.android.gms.internal.ads.zzgh
    protected final int zzr(int r3) {
        return zzel.zzc(this.zzf, r3 + 1, false, false);
    }

    @Override // com.google.android.gms.internal.ads.zzgh
    protected final int zzs(int r2) {
        return this.zze[r2];
    }

    @Override // com.google.android.gms.internal.ads.zzgh
    protected final int zzt(int r2) {
        return this.zzf[r2];
    }

    @Override // com.google.android.gms.internal.ads.zzgh
    protected final zzcn zzu(int r2) {
        return this.zzg[r2];
    }

    @Override // com.google.android.gms.internal.ads.zzgh
    protected final Object zzv(int r2) {
        return this.zzh[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List zzw() {
        return Arrays.asList(this.zzg);
    }
}
