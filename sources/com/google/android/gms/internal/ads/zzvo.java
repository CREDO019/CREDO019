package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C1856C;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzvo extends zzvt {
    public static final /* synthetic */ int zzb = 0;
    private static final zzfwd zzc = zzfwd.zzb(new Comparator() { // from class: com.google.android.gms.internal.ads.zzuu
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            Integer num = (Integer) obj;
            Integer num2 = (Integer) obj2;
            int r0 = zzvo.zzb;
            if (num.intValue() == -1) {
                return num2.intValue() == -1 ? 0 : -1;
            } else if (num2.intValue() == -1) {
                return 1;
            } else {
                return num.intValue() - num2.intValue();
            }
        }
    });
    private static final zzfwd zzd = zzfwd.zzb(new Comparator() { // from class: com.google.android.gms.internal.ads.zzuv
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            Integer num = (Integer) obj;
            Integer num2 = (Integer) obj2;
            int r1 = zzvo.zzb;
            return 0;
        }
    });
    public final Context zza;
    private final Object zze;
    private final boolean zzf;
    private zzvc zzg;
    private zzvh zzh;
    private zzk zzi;
    private final zzuj zzj;

    @Deprecated
    public zzvo() {
        zzvc zzvcVar = zzvc.zzD;
        throw null;
    }

    public static int zza(zzaf zzafVar, String str, boolean z) {
        if (TextUtils.isEmpty(str) || !str.equals(zzafVar.zzd)) {
            String zzf = zzf(str);
            String zzf2 = zzf(zzafVar.zzd);
            if (zzf2 == null || zzf == null) {
                return (z && zzf2 == null) ? 1 : 0;
            } else if (zzf2.startsWith(zzf) || zzf.startsWith(zzf2)) {
                return 3;
            } else {
                return zzel.zzah(zzf2, "-")[0].equals(zzel.zzah(zzf, "-")[0]) ? 2 : 0;
            }
        }
        return 4;
    }

    public static String zzf(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, C1856C.LANGUAGE_UNDETERMINED)) {
            return null;
        }
        return str;
    }

    public static /* bridge */ /* synthetic */ void zzg(zzvo zzvoVar) {
        zzvoVar.zzt();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0089, code lost:
        if (r8.zzh.zzd(r8.zzi, r9) != false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0053, code lost:
        if (r1 != 3) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ boolean zzk(com.google.android.gms.internal.ads.zzvo r8, com.google.android.gms.internal.ads.zzaf r9) {
        /*
            java.lang.Object r0 = r8.zze
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzvc r1 = r8.zzg     // Catch: java.lang.Throwable -> L8e
            boolean r1 = r1.zzP     // Catch: java.lang.Throwable -> L8e
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L8b
            boolean r1 = r8.zzf     // Catch: java.lang.Throwable -> L8e
            if (r1 != 0) goto L8b
            int r1 = r9.zzz     // Catch: java.lang.Throwable -> L8e
            r4 = 2
            if (r1 <= r4) goto L8b
            java.lang.String r1 = r9.zzm     // Catch: java.lang.Throwable -> L8e
            r5 = 32
            if (r1 != 0) goto L1b
            goto L65
        L1b:
            int r6 = r1.hashCode()     // Catch: java.lang.Throwable -> L8e
            r7 = 3
            switch(r6) {
                case -2123537834: goto L42;
                case 187078296: goto L38;
                case 187078297: goto L2e;
                case 1504578661: goto L24;
                default: goto L23;
            }
        L23:
            goto L4c
        L24:
            java.lang.String r6 = "audio/eac3"
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L4c
            r1 = 1
            goto L4d
        L2e:
            java.lang.String r6 = "audio/ac4"
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L4c
            r1 = 3
            goto L4d
        L38:
            java.lang.String r6 = "audio/ac3"
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L4c
            r1 = 0
            goto L4d
        L42:
            java.lang.String r6 = "audio/eac3-joc"
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L4c
            r1 = 2
            goto L4d
        L4c:
            r1 = -1
        L4d:
            if (r1 == 0) goto L56
            if (r1 == r3) goto L56
            if (r1 == r4) goto L56
            if (r1 == r7) goto L56
            goto L65
        L56:
            int r1 = com.google.android.gms.internal.ads.zzel.zza     // Catch: java.lang.Throwable -> L8e
            if (r1 < r5) goto L8b
            com.google.android.gms.internal.ads.zzvh r1 = r8.zzh     // Catch: java.lang.Throwable -> L8e
            if (r1 == 0) goto L8b
            boolean r1 = r1.zzg()     // Catch: java.lang.Throwable -> L8e
            if (r1 != 0) goto L65
            goto L8b
        L65:
            int r1 = com.google.android.gms.internal.ads.zzel.zza     // Catch: java.lang.Throwable -> L8e
            if (r1 < r5) goto L8c
            com.google.android.gms.internal.ads.zzvh r1 = r8.zzh     // Catch: java.lang.Throwable -> L8e
            if (r1 == 0) goto L8c
            boolean r4 = r1.zzg()     // Catch: java.lang.Throwable -> L8e
            if (r4 == 0) goto L8c
            boolean r1 = r1.zze()     // Catch: java.lang.Throwable -> L8e
            if (r1 == 0) goto L8c
            com.google.android.gms.internal.ads.zzvh r1 = r8.zzh     // Catch: java.lang.Throwable -> L8e
            boolean r1 = r1.zzf()     // Catch: java.lang.Throwable -> L8e
            if (r1 == 0) goto L8c
            com.google.android.gms.internal.ads.zzvh r1 = r8.zzh     // Catch: java.lang.Throwable -> L8e
            com.google.android.gms.internal.ads.zzk r8 = r8.zzi     // Catch: java.lang.Throwable -> L8e
            boolean r8 = r1.zzd(r8, r9)     // Catch: java.lang.Throwable -> L8e
            if (r8 == 0) goto L8c
        L8b:
            r2 = 1
        L8c:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8e
            return r2
        L8e:
            r8 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8e
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzvo.zzk(com.google.android.gms.internal.ads.zzvo, com.google.android.gms.internal.ads.zzaf):boolean");
    }

    public static boolean zzm(int r3, boolean z) {
        int r32 = r3 & 7;
        return r32 == 4 || (z && r32 == 3);
    }

    private static void zzs(zzue zzueVar, zzcu zzcuVar, Map map) {
        for (int r4 = 0; r4 < zzueVar.zzc; r4++) {
            if (((zzcr) zzcuVar.zzB.get(zzueVar.zzb(r4))) != null) {
                throw null;
            }
        }
    }

    public final void zzt() {
        boolean z;
        zzvh zzvhVar;
        synchronized (this.zze) {
            z = false;
            if (this.zzg.zzP && !this.zzf && zzel.zza >= 32 && (zzvhVar = this.zzh) != null && zzvhVar.zzg()) {
                z = true;
            }
        }
        if (z) {
            zzr();
        }
    }

    private static final Pair zzu(int r17, zzvs zzvsVar, int[][][] r19, zzvj zzvjVar, Comparator comparator) {
        RandomAccess randomAccess;
        zzvs zzvsVar2 = zzvsVar;
        ArrayList arrayList = new ArrayList();
        int r3 = 0;
        while (r3 < 2) {
            if (r17 == zzvsVar2.zzc(r3)) {
                zzue zzd2 = zzvsVar2.zzd(r3);
                for (int r7 = 0; r7 < zzd2.zzc; r7++) {
                    zzcp zzb2 = zzd2.zzb(r7);
                    List zza = zzvjVar.zza(r3, zzb2, r19[r3][r7]);
                    int r11 = zzb2.zzb;
                    int r112 = 1;
                    boolean[] zArr = new boolean[1];
                    int r13 = 0;
                    while (true) {
                        int r14 = zzb2.zzb;
                        if (r13 <= 0) {
                            zzvk zzvkVar = (zzvk) zza.get(r13);
                            int zzb3 = zzvkVar.zzb();
                            if (!zArr[r13] && zzb3 != 0) {
                                if (zzb3 == r112) {
                                    randomAccess = zzfuv.zzp(zzvkVar);
                                } else {
                                    ArrayList arrayList2 = new ArrayList();
                                    arrayList2.add(zzvkVar);
                                    int r2 = r13 + 1;
                                    while (true) {
                                        int r113 = zzb2.zzb;
                                        if (r2 > 0) {
                                            break;
                                        }
                                        zzvk zzvkVar2 = (zzvk) zza.get(r2);
                                        if (zzvkVar2.zzb() == 2 && zzvkVar.zzc(zzvkVar2)) {
                                            arrayList2.add(zzvkVar2);
                                            zArr[r2] = true;
                                        }
                                        r2++;
                                    }
                                    randomAccess = arrayList2;
                                }
                                arrayList.add(randomAccess);
                            }
                            r13++;
                            r112 = 1;
                        }
                    }
                }
            }
            r3++;
            zzvsVar2 = zzvsVar;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        List list = (List) Collections.max(arrayList, comparator);
        int[] r1 = new int[list.size()];
        for (int r22 = 0; r22 < list.size(); r22++) {
            r1[r22] = ((zzvk) list.get(r22)).zzc;
        }
        zzvk zzvkVar3 = (zzvk) list.get(0);
        return Pair.create(new zzvp(zzvkVar3.zzb, r1, 0), Integer.valueOf(zzvkVar3.zza));
    }

    @Override // com.google.android.gms.internal.ads.zzvt
    protected final Pair zzb(zzvs zzvsVar, int[][][] r25, final int[] r26, zzsg zzsgVar, zzcn zzcnVar) throws zzgy {
        final zzvc zzvcVar;
        int r8;
        final boolean z;
        int[] r82;
        int length;
        zzvq zza;
        zzvh zzvhVar;
        int[][][] r2 = r25;
        synchronized (this.zze) {
            zzvcVar = this.zzg;
            if (zzvcVar.zzP && zzel.zza >= 32 && (zzvhVar = this.zzh) != null) {
                Looper myLooper = Looper.myLooper();
                zzdd.zzb(myLooper);
                zzvhVar.zzb(this, myLooper);
            }
        }
        int r3 = 2;
        zzvp[] zzvpVarArr = new zzvp[2];
        Pair zzu = zzu(2, zzvsVar, r2, new zzvj() { // from class: com.google.android.gms.internal.ads.zzuq
            /* JADX WARN: Removed duplicated region for block: B:25:0x0048  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0052  */
            @Override // com.google.android.gms.internal.ads.zzvj
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.util.List zza(int r17, com.google.android.gms.internal.ads.zzcp r18, int[] r19) {
                /*
                    Method dump skipped, instructions count: 191
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzuq.zza(int, com.google.android.gms.internal.ads.zzcp, int[]):java.util.List");
            }
        }, new Comparator() { // from class: com.google.android.gms.internal.ads.zzur
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                List list = (List) obj;
                List list2 = (List) obj2;
                return zzfuk.zzj().zzc((zzvn) Collections.max(list, new Comparator() { // from class: com.google.android.gms.internal.ads.zzvl
                    @Override // java.util.Comparator
                    public final int compare(Object obj3, Object obj4) {
                        return zzvn.zzd((zzvn) obj3, (zzvn) obj4);
                    }
                }), (zzvn) Collections.max(list2, new Comparator() { // from class: com.google.android.gms.internal.ads.zzvl
                    @Override // java.util.Comparator
                    public final int compare(Object obj3, Object obj4) {
                        return zzvn.zzd((zzvn) obj3, (zzvn) obj4);
                    }
                }), new Comparator() { // from class: com.google.android.gms.internal.ads.zzvl
                    @Override // java.util.Comparator
                    public final int compare(Object obj3, Object obj4) {
                        return zzvn.zzd((zzvn) obj3, (zzvn) obj4);
                    }
                }).zzb(list.size(), list2.size()).zzc((zzvn) Collections.max(list, new Comparator() { // from class: com.google.android.gms.internal.ads.zzvm
                    @Override // java.util.Comparator
                    public final int compare(Object obj3, Object obj4) {
                        return zzvn.zza((zzvn) obj3, (zzvn) obj4);
                    }
                }), (zzvn) Collections.max(list2, new Comparator() { // from class: com.google.android.gms.internal.ads.zzvm
                    @Override // java.util.Comparator
                    public final int compare(Object obj3, Object obj4) {
                        return zzvn.zza((zzvn) obj3, (zzvn) obj4);
                    }
                }), new Comparator() { // from class: com.google.android.gms.internal.ads.zzvm
                    @Override // java.util.Comparator
                    public final int compare(Object obj3, Object obj4) {
                        return zzvn.zza((zzvn) obj3, (zzvn) obj4);
                    }
                }).zza();
            }
        });
        if (zzu != null) {
            zzvpVarArr[((Integer) zzu.second).intValue()] = (zzvp) zzu.first;
        }
        int r7 = 0;
        while (true) {
            r8 = 1;
            if (r7 >= 2) {
                z = false;
                break;
            } else if (zzvsVar.zzc(r7) == 2 && zzvsVar.zzd(r7).zzc > 0) {
                z = true;
                break;
            } else {
                r7++;
            }
        }
        Pair zzu2 = zzu(1, zzvsVar, r2, new zzvj() { // from class: com.google.android.gms.internal.ads.zzuo
            @Override // com.google.android.gms.internal.ads.zzvj
            public final List zza(int r17, zzcp zzcpVar, int[] r19) {
                final zzvo zzvoVar = zzvo.this;
                zzvc zzvcVar2 = zzvcVar;
                boolean z2 = z;
                zzfsg zzfsgVar = new zzfsg() { // from class: com.google.android.gms.internal.ads.zzun
                    @Override // com.google.android.gms.internal.ads.zzfsg
                    public final boolean zza(Object obj) {
                        return zzvo.zzk(zzvo.this, (zzaf) obj);
                    }
                };
                zzfus zzi = zzfuv.zzi();
                int r13 = 0;
                while (true) {
                    int r22 = zzcpVar.zzb;
                    if (r13 <= 0) {
                        zzi.zze(new zzuw(r17, zzcpVar, r13, zzvcVar2, r19[r13], z2, zzfsgVar));
                        r13++;
                    } else {
                        return zzi.zzg();
                    }
                }
            }
        }, new Comparator() { // from class: com.google.android.gms.internal.ads.zzup
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((zzuw) Collections.max((List) obj)).zza((zzuw) Collections.max((List) obj2));
            }
        });
        if (zzu2 != null) {
            zzvpVarArr[((Integer) zzu2.second).intValue()] = (zzvp) zzu2.first;
        }
        final String str = zzu2 == null ? null : ((zzvp) zzu2.first).zza.zzb(((zzvp) zzu2.first).zzb[0]).zzd;
        int r11 = 3;
        Pair zzu3 = zzu(3, zzvsVar, r2, new zzvj() { // from class: com.google.android.gms.internal.ads.zzus
            @Override // com.google.android.gms.internal.ads.zzvj
            public final List zza(int r13, zzcp zzcpVar, int[] r15) {
                zzvc zzvcVar2 = zzvc.this;
                String str2 = str;
                int r0 = zzvo.zzb;
                zzfus zzi = zzfuv.zzi();
                int r10 = 0;
                while (true) {
                    int r02 = zzcpVar.zzb;
                    if (r10 <= 0) {
                        zzi.zze(new zzvi(r13, zzcpVar, r10, zzvcVar2, r15[r10], str2));
                        r10++;
                    } else {
                        return zzi.zzg();
                    }
                }
            }
        }, new Comparator() { // from class: com.google.android.gms.internal.ads.zzut
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((zzvi) ((List) obj).get(0)).zza((zzvi) ((List) obj2).get(0));
            }
        });
        if (zzu3 != null) {
            zzvpVarArr[((Integer) zzu3.second).intValue()] = (zzvp) zzu3.first;
        }
        int r72 = 0;
        while (r72 < r3) {
            int zzc2 = zzvsVar.zzc(r72);
            if (zzc2 != r3 && zzc2 != r8 && zzc2 != r11) {
                zzue zzd2 = zzvsVar.zzd(r72);
                int[][] r12 = r2[r72];
                int r13 = 0;
                zzcp zzcpVar = null;
                int r15 = 0;
                zzux zzuxVar = null;
                while (r13 < zzd2.zzc) {
                    zzcp zzb2 = zzd2.zzb(r13);
                    int[] r17 = r12[r13];
                    zzux zzuxVar2 = zzuxVar;
                    int r32 = 0;
                    while (true) {
                        int r6 = zzb2.zzb;
                        if (r32 <= 0) {
                            if (zzm(r17[r32], zzvcVar.zzQ)) {
                                zzux zzuxVar3 = new zzux(zzb2.zzb(r32), r17[r32]);
                                if (zzuxVar2 == null || zzuxVar3.compareTo(zzuxVar2) > 0) {
                                    r15 = r32;
                                    zzuxVar2 = zzuxVar3;
                                    zzcpVar = zzb2;
                                }
                            }
                            r32++;
                        }
                    }
                    r13++;
                    zzuxVar = zzuxVar2;
                }
                zzvpVarArr[r72] = zzcpVar == null ? null : new zzvp(zzcpVar, new int[]{r15}, 0);
            }
            r72++;
            r2 = r25;
            r3 = 2;
            r8 = 1;
            r11 = 3;
        }
        HashMap hashMap = new HashMap();
        for (int r33 = 0; r33 < 2; r33++) {
            zzs(zzvsVar.zzd(r33), zzvcVar, hashMap);
        }
        zzs(zzvsVar.zze(), zzvcVar, hashMap);
        for (int r34 = 0; r34 < 2; r34++) {
            if (((zzcr) hashMap.get(Integer.valueOf(zzvsVar.zzc(r34)))) != null) {
                throw null;
            }
        }
        int[] r35 = null;
        int r22 = 0;
        for (int r62 = 2; r22 < r62; r62 = 2) {
            zzue zzd3 = zzvsVar.zzd(r22);
            if (zzvcVar.zzg(r22, zzd3)) {
                zzvpVarArr[r22] = (zzvcVar.zze(r22, zzd3) == null || r35.length == 0) ? null : new zzvp(zzd3.zzb(0), r35, 0);
            }
            r22++;
            r35 = null;
        }
        int r36 = 0;
        for (int r23 = 2; r36 < r23; r23 = 2) {
            int zzc3 = zzvsVar.zzc(r36);
            if (zzvcVar.zzf(r36) || zzvcVar.zzC.contains(Integer.valueOf(zzc3))) {
                zzvpVarArr[r36] = null;
            }
            r36++;
        }
        zzuj zzujVar = this.zzj;
        zzwe zzp = zzp();
        zzfuv zzf = zzuk.zzf(zzvpVarArr);
        int r63 = 2;
        zzvq[] zzvqVarArr = new zzvq[2];
        int r152 = 0;
        while (r152 < r63) {
            zzvp zzvpVar = zzvpVarArr[r152];
            if (zzvpVar != null && (length = (r82 = zzvpVar.zzb).length) != 0) {
                if (length == 1) {
                    zza = new zzvr(zzvpVar.zza, r82[0], 0, 0, null);
                } else {
                    zza = zzujVar.zza(zzvpVar.zza, r82, 0, zzp, (zzfuv) zzf.get(r152));
                }
                zzvqVarArr[r152] = zza;
            }
            r152++;
            r63 = 2;
        }
        zzka[] zzkaVarArr = new zzka[r63];
        for (int r5 = 0; r5 < r63; r5++) {
            zzkaVarArr[r5] = (zzvcVar.zzf(r5) || zzvcVar.zzC.contains(Integer.valueOf(zzvsVar.zzc(r5))) || (zzvsVar.zzc(r5) != -2 && zzvqVarArr[r5] == null)) ? null : zzka.zza;
        }
        boolean z2 = zzvcVar.zzR;
        return Pair.create(zzkaVarArr, zzvqVarArr);
    }

    public final zzvc zzc() {
        zzvc zzvcVar;
        synchronized (this.zze) {
            zzvcVar = this.zzg;
        }
        return zzvcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvw
    public final void zzh() {
        zzvh zzvhVar;
        synchronized (this.zze) {
            if (zzel.zza >= 32 && (zzvhVar = this.zzh) != null) {
                zzvhVar.zzc();
            }
        }
        super.zzh();
    }

    @Override // com.google.android.gms.internal.ads.zzvw
    public final void zzi(zzk zzkVar) {
        boolean z;
        synchronized (this.zze) {
            z = !this.zzi.equals(zzkVar);
            this.zzi = zzkVar;
        }
        if (z) {
            zzt();
        }
    }

    public final void zzj(zzva zzvaVar) {
        boolean z;
        zzvc zzvcVar = new zzvc(zzvaVar);
        synchronized (this.zze) {
            z = !this.zzg.equals(zzvcVar);
            this.zzg = zzvcVar;
        }
        if (z) {
            if (zzvcVar.zzP && this.zza == null) {
                Log.w("DefaultTrackSelector", "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.");
            }
            zzr();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzvw
    public final boolean zzl() {
        return true;
    }

    public zzvo(Context context) {
        zzuj zzujVar = new zzuj();
        zzvc zzd2 = zzvc.zzd(context);
        this.zze = new Object();
        this.zza = context != null ? context.getApplicationContext() : null;
        this.zzj = zzujVar;
        this.zzg = zzd2;
        this.zzi = zzk.zza;
        boolean z = false;
        if (context != null && zzel.zzX(context)) {
            z = true;
        }
        this.zzf = z;
        if (!z && context != null && zzel.zza >= 32) {
            this.zzh = zzvh.zza(context);
        }
        if (this.zzg.zzP && context == null) {
            Log.w("DefaultTrackSelector", "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.");
        }
    }
}
