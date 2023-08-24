package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaih implements zzzf {
    public static final zzzm zza = new zzzm() { // from class: com.google.android.gms.internal.ads.zzaie
        @Override // com.google.android.gms.internal.ads.zzzm
        public final zzzf[] zza() {
            zzzm zzzmVar = zzaih.zza;
            return new zzzf[]{new zzaih(0)};
        }

        @Override // com.google.android.gms.internal.ads.zzzm
        public final /* synthetic */ zzzf[] zzb(Uri uri, Map map) {
            return zzzl.zza(this, uri, map);
        }
    };
    private final List zzb;
    private final zzed zzc;
    private final SparseIntArray zzd;
    private final zzaik zze;
    private final SparseArray zzf;
    private final SparseBooleanArray zzg;
    private final SparseBooleanArray zzh;
    private final zzaid zzi;
    private zzaic zzj;
    private zzzi zzk;
    private int zzl;
    private boolean zzm;
    private boolean zzn;
    private boolean zzo;
    private int zzp;
    private int zzq;

    public zzaih() {
        this(0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0194, code lost:
        if (r1 == false) goto L78;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v2, types: [int, boolean] */
    @Override // com.google.android.gms.internal.ads.zzzf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(com.google.android.gms.internal.ads.zzzg r19, com.google.android.gms.internal.ads.zzaaf r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 425
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaih.zza(com.google.android.gms.internal.ads.zzzg, com.google.android.gms.internal.ads.zzaaf):int");
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzb(zzzi zzziVar) {
        this.zzk = zzziVar;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzc(long j, long j2) {
        zzaic zzaicVar;
        int size = this.zzb.size();
        for (int r0 = 0; r0 < size; r0++) {
            zzej zzejVar = (zzej) this.zzb.get(r0);
            if (zzejVar.zze() != C1856C.TIME_UNSET) {
                long zzc = zzejVar.zzc();
                if (zzc != C1856C.TIME_UNSET) {
                    if (zzc != 0) {
                        if (zzc == j2) {
                        }
                    }
                }
            }
            zzejVar.zzf(j2);
        }
        if (j2 != 0 && (zzaicVar = this.zzj) != null) {
            zzaicVar.zzd(j2);
        }
        this.zzc.zzC(0);
        this.zzd.clear();
        for (int r10 = 0; r10 < this.zzf.size(); r10++) {
            ((zzaim) this.zzf.valueAt(r10)).zzc();
        }
        this.zzp = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
        r2 = r2 + 1;
     */
    @Override // com.google.android.gms.internal.ads.zzzf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzd(com.google.android.gms.internal.ads.zzzg r7) throws java.io.IOException {
        /*
            r6 = this;
            com.google.android.gms.internal.ads.zzed r0 = r6.zzc
            byte[] r0 = r0.zzH()
            com.google.android.gms.internal.ads.zzyv r7 = (com.google.android.gms.internal.ads.zzyv) r7
            r1 = 0
            r2 = 940(0x3ac, float:1.317E-42)
            r7.zzm(r0, r1, r2, r1)
            r2 = 0
        Lf:
            r3 = 188(0xbc, float:2.63E-43)
            if (r2 >= r3) goto L2b
            r3 = 0
        L14:
            r4 = 5
            if (r3 >= r4) goto L26
            int r4 = r3 * 188
            int r4 = r4 + r2
            r4 = r0[r4]
            r5 = 71
            if (r4 == r5) goto L23
            int r2 = r2 + 1
            goto Lf
        L23:
            int r3 = r3 + 1
            goto L14
        L26:
            r7.zzo(r2, r1)
            r7 = 1
            return r7
        L2b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaih.zzd(com.google.android.gms.internal.ads.zzzg):boolean");
    }

    public zzaih(int r3) {
        this(1, 0, TsExtractor.DEFAULT_TIMESTAMP_SEARCH_BYTES);
    }

    public zzaih(int r5, int r6, int r7) {
        zzej zzejVar = new zzej(0L);
        this.zze = new zzagw(0);
        this.zzb = Collections.singletonList(zzejVar);
        this.zzc = new zzed(new byte[9400], 0);
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        this.zzg = sparseBooleanArray;
        this.zzh = new SparseBooleanArray();
        SparseArray sparseArray = new SparseArray();
        this.zzf = sparseArray;
        this.zzd = new SparseIntArray();
        this.zzi = new zzaid(TsExtractor.DEFAULT_TIMESTAMP_SEARCH_BYTES);
        this.zzk = zzzi.zza;
        this.zzq = -1;
        sparseBooleanArray.clear();
        sparseArray.clear();
        SparseArray sparseArray2 = new SparseArray();
        int size = sparseArray2.size();
        for (int r0 = 0; r0 < size; r0++) {
            this.zzf.put(sparseArray2.keyAt(r0), (zzaim) sparseArray2.valueAt(r0));
        }
        this.zzf.put(0, new zzahz(new zzaif(this)));
    }
}
