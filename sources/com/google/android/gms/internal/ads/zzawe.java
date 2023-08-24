package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import com.google.android.exoplayer2.C1856C;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzawe implements zzauv {
    public static final zzaux zza = new zzawb();
    private static final int zzb = zzban.zzg("seig");
    private static final byte[] zzc = {-94, 57, 79, 82, 90, -101, 79, Ascii.DC4, -94, 68, 108, 66, 124, 100, -115, -12};
    private final SparseArray zzd;
    private final zzbag zze;
    private final zzbag zzf;
    private final zzbag zzg;
    private final zzbag zzh;
    private final zzbag zzi;
    private final byte[] zzj;
    private final Stack zzk;
    private final LinkedList zzl;
    private int zzm;
    private int zzn;
    private long zzo;
    private int zzp;
    private zzbag zzq;
    private long zzr;
    private long zzs;
    private zzawd zzt;
    private int zzu;
    private int zzv;
    private int zzw;
    private zzauw zzx;
    private boolean zzy;

    public zzawe() {
        this(0, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.android.gms.internal.ads.zzaur zza(java.util.List r14) {
        /*
            int r0 = r14.size()
            r1 = 0
            r2 = 0
            r4 = r2
            r3 = 0
        L8:
            if (r3 >= r0) goto Lb7
            java.lang.Object r5 = r14.get(r3)
            com.google.android.gms.internal.ads.zzavr r5 = (com.google.android.gms.internal.ads.zzavr) r5
            int r6 = r5.zzaR
            int r7 = com.google.android.gms.internal.ads.zzavs.zzX
            if (r6 != r7) goto Lb3
            if (r4 != 0) goto L1d
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        L1d:
            com.google.android.gms.internal.ads.zzbag r5 = r5.zza
            byte[] r5 = r5.zza
            com.google.android.gms.internal.ads.zzbag r6 = new com.google.android.gms.internal.ads.zzbag
            r6.<init>(r5)
            int r7 = r6.zzd()
            r8 = 32
            if (r7 >= r8) goto L30
        L2e:
            r6 = r2
            goto L97
        L30:
            r6.zzv(r1)
            int r7 = r6.zze()
            int r8 = r6.zza()
            int r8 = r8 + 4
            if (r7 == r8) goto L40
            goto L2e
        L40:
            int r7 = r6.zze()
            int r8 = com.google.android.gms.internal.ads.zzavs.zzX
            if (r7 == r8) goto L49
            goto L2e
        L49:
            int r7 = r6.zze()
            int r7 = com.google.android.gms.internal.ads.zzavs.zzf(r7)
            r8 = 1
            if (r7 <= r8) goto L6b
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "Unsupported pssh version: "
            r6.append(r8)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "PsshAtomUtil"
            android.util.Log.w(r7, r6)
            goto L2e
        L6b:
            java.util.UUID r9 = new java.util.UUID
            long r10 = r6.zzl()
            long r12 = r6.zzl()
            r9.<init>(r10, r12)
            if (r7 != r8) goto L83
            int r7 = r6.zzi()
            int r7 = r7 * 16
            r6.zzw(r7)
        L83:
            int r7 = r6.zzi()
            int r8 = r6.zza()
            if (r7 == r8) goto L8e
            goto L2e
        L8e:
            byte[] r8 = new byte[r7]
            r6.zzq(r8, r1, r7)
            android.util.Pair r6 = android.util.Pair.create(r9, r8)
        L97:
            if (r6 != 0) goto L9b
            r6 = r2
            goto L9f
        L9b:
            java.lang.Object r6 = r6.first
            java.util.UUID r6 = (java.util.UUID) r6
        L9f:
            if (r6 != 0) goto La9
            java.lang.String r5 = "FragmentedMp4Extractor"
            java.lang.String r6 = "Skipped pssh atom (failed to extract uuid)"
            android.util.Log.w(r5, r6)
            goto Lb3
        La9:
            com.google.android.gms.internal.ads.zzauq r7 = new com.google.android.gms.internal.ads.zzauq
            java.lang.String r8 = "video/mp4"
            r7.<init>(r6, r8, r5, r1)
            r4.add(r7)
        Lb3:
            int r3 = r3 + 1
            goto L8
        Lb7:
            if (r4 != 0) goto Lba
            return r2
        Lba:
            com.google.android.gms.internal.ads.zzaur r14 = new com.google.android.gms.internal.ads.zzaur
            r14.<init>(r4)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzawe.zza(java.util.List):com.google.android.gms.internal.ads.zzaur");
    }

    private final void zzb() {
        this.zzm = 0;
        this.zzp = 0;
    }

    private static void zzc(zzbag zzbagVar, int r4, zzawm zzawmVar) throws zzasv {
        zzbagVar.zzv(r4 + 8);
        int zze = zzavs.zze(zzbagVar.zze());
        if ((zze & 1) != 0) {
            throw new zzasv("Overriding TrackEncryptionBox parameters is unsupported.");
        }
        boolean z = (zze & 2) != 0;
        int zzi = zzbagVar.zzi();
        int r2 = zzawmVar.zze;
        if (zzi != r2) {
            throw new zzasv("Length mismatch: " + zzi + ", " + r2);
        }
        Arrays.fill(zzawmVar.zzm, 0, zzi, z);
        zzawmVar.zza(zzbagVar.zza());
        zzbagVar.zzq(zzawmVar.zzp.zza, 0, zzawmVar.zzo);
        zzawmVar.zzp.zzv(0);
        zzawmVar.zzq = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:241:0x0631, code lost:
        zzb();
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x0635, code lost:
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0599  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzh(long r50) throws com.google.android.gms.internal.ads.zzasv {
        /*
            Method dump skipped, instructions count: 1590
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzawe.zzh(long):void");
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final void zzd(zzauw zzauwVar) {
        this.zzx = zzauwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final void zze(long j, long j2) {
        int size = this.zzd.size();
        for (int r2 = 0; r2 < size; r2++) {
            ((zzawd) this.zzd.valueAt(r2)).zzb();
        }
        this.zzl.clear();
        this.zzk.clear();
        zzb();
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x007f, code lost:
        r2 = r24.zzt;
        r3 = r2.zza;
        r5 = r3.zzh;
        r9 = r2.zze;
        r5 = r5[r9];
        r24.zzu = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008d, code lost:
        if (r3.zzl == false) goto L299;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x008f, code lost:
        r5 = r3.zzp;
        r10 = r3.zza.zza;
        r11 = r3.zzn;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0097, code lost:
        if (r11 == null) goto L242;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009a, code lost:
        r11 = r2.zzc.zzh[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a0, code lost:
        r10 = r11.zza;
        r3 = r3.zzm[r9];
        r9 = r24.zzh;
        r11 = r9.zza;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00aa, code lost:
        if (true == r3) goto L298;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ac, code lost:
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ae, code lost:
        r12 = 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b0, code lost:
        r11[0] = (byte) (r12 | r10);
        r9.zzv(0);
        r2 = r2.zzb;
        r2.zzb(r24.zzh, 1);
        r2.zzb(r5, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c1, code lost:
        if (r3 != false) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c3, code lost:
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c5, code lost:
        r3 = r5.zzj();
        r5.zzw(-2);
        r3 = (r3 * 6) + 2;
        r2.zzb(r5, r3);
        r10 = (r10 + 1) + r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d5, code lost:
        r24.zzv = r10;
        r5 = r24.zzu + r10;
        r24.zzu = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00de, code lost:
        r24.zzv = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e6, code lost:
        if (r24.zzt.zzc.zzg != 1) goto L253;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00e8, code lost:
        r24.zzu = r5 - 8;
        r1.zzi(8, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ef, code lost:
        r24.zzm = 4;
        r24.zzw = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00f4, code lost:
        r2 = r24.zzt;
        r3 = r2.zza;
        r5 = r2.zzc;
        r9 = r2.zzb;
        r2 = r2.zze;
        r6 = r5.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0100, code lost:
        if (r6 != 0) goto L284;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0102, code lost:
        r4 = r24.zzv;
        r6 = r24.zzu;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0106, code lost:
        if (r4 >= r6) goto L259;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0108, code lost:
        r24.zzv += r9.zzd(r1, r6 - r4, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0113, code lost:
        r10 = r24.zzf.zza;
        r10[0] = 0;
        r10[1] = 0;
        r10[2] = 0;
        r4 = r6 + 1;
        r6 = 4 - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0126, code lost:
        if (r24.zzv >= r24.zzu) goto L296;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0128, code lost:
        r11 = r24.zzw;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x012a, code lost:
        if (r11 != 0) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x012c, code lost:
        r1.zzh(r10, r6, r4, false);
        r24.zzf.zzv(0);
        r24.zzw = r24.zzf.zzi() - 1;
        r24.zze.zzv(0);
        r9.zzb(r24.zze, 4);
        r9.zzb(r24.zzf, 1);
        r24.zzv += 5;
        r24.zzu += r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x015a, code lost:
        r11 = r9.zzd(r1, r11, false);
        r24.zzv += r11;
        r24.zzw -= r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0169, code lost:
        r10 = (r3.zzj[r2] + r3.zzi[r2]) * 1000;
        r1 = r3.zzl;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0179, code lost:
        if (true == r1) goto L283;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x017b, code lost:
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x017d, code lost:
        r4 = 1073741824;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x017f, code lost:
        r12 = r4 | (r3.zzk[r2] ? 1 : 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0185, code lost:
        if (r1 == false) goto L282;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0187, code lost:
        r1 = r3.zzn;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0189, code lost:
        if (r1 != null) goto L268;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x018b, code lost:
        r1 = r5.zzh[r3.zza.zza];
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0193, code lost:
        r2 = r24.zzt;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0197, code lost:
        if (r1 == r2.zzi) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0199, code lost:
        r2 = new com.google.android.gms.internal.ads.zzavd(1, r1.zzb);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01a1, code lost:
        r2 = r2.zzh;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01a3, code lost:
        r15 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01a5, code lost:
        r1 = null;
        r15 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01a7, code lost:
        r2 = r24.zzt;
        r2.zzh = r15;
        r2.zzi = r1;
        r9.zzc(r10, r12, r24.zzu, 0, r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01b9, code lost:
        if (r24.zzl.isEmpty() == false) goto L279;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01bb, code lost:
        r1 = r24.zzt;
        r1.zze++;
        r2 = r1.zzf + 1;
        r1.zzf = r2;
        r3 = r3.zzg;
        r4 = r1.zzg;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01cd, code lost:
        if (r2 != r3[r4]) goto L277;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01cf, code lost:
        r1.zzg = r4 + 1;
        r1.zzf = 0;
        r24.zzt = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01d7, code lost:
        r24.zzm = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01da, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01db, code lost:
        r2 = (com.google.android.gms.internal.ads.zzawc) r24.zzl.removeFirst();
        r3 = r2.zzb;
        r2 = r2.zza;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01e8, code lost:
        throw null;
     */
    @Override // com.google.android.gms.internal.ads.zzauv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzf(com.google.android.gms.internal.ads.zzauu r25, com.google.android.gms.internal.ads.zzava r26) throws java.io.IOException, java.lang.InterruptedException {
        /*
            Method dump skipped, instructions count: 1222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzawe.zzf(com.google.android.gms.internal.ads.zzauu, com.google.android.gms.internal.ads.zzava):int");
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final boolean zzg(zzauu zzauuVar) throws IOException, InterruptedException {
        return zzawj.zza(zzauuVar);
    }

    public zzawe(int r1, zzbak zzbakVar, zzawk zzawkVar) {
        this.zzi = new zzbag(16);
        this.zze = new zzbag(zzbae.zza);
        this.zzf = new zzbag(5);
        this.zzg = new zzbag();
        this.zzh = new zzbag(1);
        this.zzj = new byte[16];
        this.zzk = new Stack();
        this.zzl = new LinkedList();
        this.zzd = new SparseArray();
        this.zzs = C1856C.TIME_UNSET;
        zzb();
    }
}
