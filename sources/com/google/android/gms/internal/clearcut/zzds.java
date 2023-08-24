package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
final class zzds<T> implements zzef<T> {
    private static final Unsafe zzmh = zzfd.zzef();
    private final int[] zzmi;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final zzdo zzmn;
    private final boolean zzmo;
    private final boolean zzmp;
    private final boolean zzmq;
    private final boolean zzmr;
    private final int[] zzms;
    private final int[] zzmt;
    private final int[] zzmu;
    private final zzdw zzmv;
    private final zzcy zzmw;
    private final zzex<?, ?> zzmx;
    private final zzbu<?> zzmy;
    private final zzdj zzmz;

    private zzds(int[] r6, Object[] objArr, int r8, int r9, int r10, zzdo zzdoVar, boolean z, boolean z2, int[] r14, int[] r15, int[] r16, zzdw zzdwVar, zzcy zzcyVar, zzex<?, ?> zzexVar, zzbu<?> zzbuVar, zzdj zzdjVar) {
        this.zzmi = r6;
        this.zzmj = objArr;
        this.zzmk = r8;
        this.zzml = r9;
        this.zzmm = r10;
        this.zzmp = zzdoVar instanceof zzcg;
        this.zzmq = z;
        this.zzmo = zzbuVar != null && zzbuVar.zze(zzdoVar);
        this.zzmr = false;
        this.zzms = r14;
        this.zzmt = r15;
        this.zzmu = r16;
        this.zzmv = zzdwVar;
        this.zzmw = zzcyVar;
        this.zzmx = zzexVar;
        this.zzmy = zzbuVar;
        this.zzmn = zzdoVar;
        this.zzmz = zzdjVar;
    }

    private static int zza(int r6, byte[] bArr, int r8, int r9, Object obj, zzay zzayVar) throws IOException {
        return zzax.zza(r6, bArr, r8, r9, zzn(obj), zzayVar);
    }

    private static int zza(zzef<?> zzefVar, int r3, byte[] bArr, int r5, int r6, zzcn<?> zzcnVar, zzay zzayVar) throws IOException {
        int zza = zza((zzef) zzefVar, bArr, r5, r6, zzayVar);
        while (true) {
            zzcnVar.add(zzayVar.zzff);
            if (zza >= r6) {
                break;
            }
            int zza2 = zzax.zza(bArr, zza, zzayVar);
            if (r3 != zzayVar.zzfd) {
                break;
            }
            zza = zza((zzef) zzefVar, bArr, zza2, r6, zzayVar);
        }
        return zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int zza(zzef zzefVar, byte[] bArr, int r10, int r11, int r12, zzay zzayVar) throws IOException {
        zzds zzdsVar = (zzds) zzefVar;
        Object newInstance = zzdsVar.newInstance();
        int zza = zzdsVar.zza((zzds) newInstance, bArr, r10, r11, r12, zzayVar);
        zzdsVar.zzc(newInstance);
        zzayVar.zzff = newInstance;
        return zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int zza(zzef zzefVar, byte[] bArr, int r8, int r9, zzay zzayVar) throws IOException {
        int r0 = r8 + 1;
        int r82 = bArr[r8];
        if (r82 < 0) {
            r0 = zzax.zza(r82, bArr, r0, zzayVar);
            r82 = zzayVar.zzfd;
        }
        int r3 = r0;
        if (r82 < 0 || r82 > r9 - r3) {
            throw zzco.zzbl();
        }
        Object newInstance = zzefVar.newInstance();
        int r83 = r82 + r3;
        zzefVar.zza(newInstance, bArr, r3, r83, zzayVar);
        zzefVar.zzc(newInstance);
        zzayVar.zzff = newInstance;
        return r83;
    }

    private static <UT, UB> int zza(zzex<UT, UB> zzexVar, T t) {
        return zzexVar.zzm(zzexVar.zzq(t));
    }

    private final int zza(T t, byte[] bArr, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long j, int r28, zzay zzayVar) throws IOException {
        Object valueOf;
        Object valueOf2;
        int zzb;
        long j2;
        int r3;
        Object valueOf3;
        int r4;
        Unsafe unsafe = zzmh;
        long j3 = this.zzmi[r28 + 2] & 1048575;
        switch (r25) {
            case 51:
                if (r23 == 1) {
                    valueOf = Double.valueOf(zzax.zze(bArr, r19));
                    unsafe.putObject(t, j, valueOf);
                    zzb = r19 + 8;
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 52:
                if (r23 == 5) {
                    valueOf2 = Float.valueOf(zzax.zzf(bArr, r19));
                    unsafe.putObject(t, j, valueOf2);
                    zzb = r19 + 4;
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 53:
            case 54:
                if (r23 == 0) {
                    zzb = zzax.zzb(bArr, r19, zzayVar);
                    j2 = zzayVar.zzfe;
                    valueOf3 = Long.valueOf(j2);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 55:
            case 62:
                if (r23 == 0) {
                    zzb = zzax.zza(bArr, r19, zzayVar);
                    r3 = zzayVar.zzfd;
                    valueOf3 = Integer.valueOf(r3);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 56:
            case 65:
                if (r23 == 1) {
                    valueOf = Long.valueOf(zzax.zzd(bArr, r19));
                    unsafe.putObject(t, j, valueOf);
                    zzb = r19 + 8;
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 57:
            case 64:
                if (r23 == 5) {
                    valueOf2 = Integer.valueOf(zzax.zzc(bArr, r19));
                    unsafe.putObject(t, j, valueOf2);
                    zzb = r19 + 4;
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 58:
                if (r23 == 0) {
                    zzb = zzax.zzb(bArr, r19, zzayVar);
                    valueOf3 = Boolean.valueOf(zzayVar.zzfe != 0);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 59:
                if (r23 == 2) {
                    zzb = zzax.zza(bArr, r19, zzayVar);
                    r4 = zzayVar.zzfd;
                    if (r4 == 0) {
                        valueOf3 = "";
                        unsafe.putObject(t, j, valueOf3);
                        unsafe.putInt(t, j3, r22);
                        return zzb;
                    } else if ((r24 & 536870912) == 0 || zzff.zze(bArr, zzb, zzb + r4)) {
                        unsafe.putObject(t, j, new String(bArr, zzb, r4, zzci.UTF_8));
                        zzb += r4;
                        unsafe.putInt(t, j3, r22);
                        return zzb;
                    } else {
                        throw zzco.zzbp();
                    }
                }
                return r19;
            case 60:
                if (r23 == 2) {
                    zzb = zza(zzad(r28), bArr, r19, r20, zzayVar);
                    Object object = unsafe.getInt(t, j3) == r22 ? unsafe.getObject(t, j) : null;
                    valueOf3 = zzayVar.zzff;
                    if (object != null) {
                        valueOf3 = zzci.zza(object, valueOf3);
                    }
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 61:
                if (r23 == 2) {
                    zzb = zzax.zza(bArr, r19, zzayVar);
                    r4 = zzayVar.zzfd;
                    if (r4 == 0) {
                        valueOf3 = zzbb.zzfi;
                        unsafe.putObject(t, j, valueOf3);
                        unsafe.putInt(t, j3, r22);
                        return zzb;
                    }
                    unsafe.putObject(t, j, zzbb.zzb(bArr, zzb, r4));
                    zzb += r4;
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 63:
                if (r23 == 0) {
                    int zza = zzax.zza(bArr, r19, zzayVar);
                    int r42 = zzayVar.zzfd;
                    zzck<?> zzaf = zzaf(r28);
                    if (zzaf != null && zzaf.zzb(r42) == null) {
                        zzn(t).zzb(r21, Long.valueOf(r42));
                        return zza;
                    }
                    unsafe.putObject(t, j, Integer.valueOf(r42));
                    zzb = zza;
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 66:
                if (r23 == 0) {
                    zzb = zzax.zza(bArr, r19, zzayVar);
                    r3 = zzbk.zzm(zzayVar.zzfd);
                    valueOf3 = Integer.valueOf(r3);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 67:
                if (r23 == 0) {
                    zzb = zzax.zzb(bArr, r19, zzayVar);
                    j2 = zzbk.zza(zzayVar.zzfe);
                    valueOf3 = Long.valueOf(j2);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            case 68:
                if (r23 == 3) {
                    zzb = zza(zzad(r28), bArr, r19, r20, (r21 & (-8)) | 4, zzayVar);
                    Object object2 = unsafe.getInt(t, j3) == r22 ? unsafe.getObject(t, j) : null;
                    valueOf3 = zzayVar.zzff;
                    if (object2 != null) {
                        valueOf3 = zzci.zza(object2, valueOf3);
                    }
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, r22);
                    return zzb;
                }
                return r19;
            default:
                return r19;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x022f, code lost:
        if (r29.zzfe != 0) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0231, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0233, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0234, code lost:
        r12.addBoolean(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0237, code lost:
        if (r4 >= r19) goto L250;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0239, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x023f, code lost:
        if (r20 != r29.zzfd) goto L250;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0241, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzax.zzb(r17, r6, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0249, code lost:
        if (r29.zzfe == 0) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:?, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:?, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0137, code lost:
        if (r4 == 0) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0139, code lost:
        r12.add(com.google.android.gms.internal.clearcut.zzbb.zzfi);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x013f, code lost:
        r12.add(com.google.android.gms.internal.clearcut.zzbb.zzb(r17, r1, r4));
        r1 = r1 + r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0147, code lost:
        if (r1 >= r19) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0149, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x014f, code lost:
        if (r20 != r29.zzfd) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0151, code lost:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
        r4 = r29.zzfd;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0157, code lost:
        if (r4 != 0) goto L82;
     */
    /* JADX WARN: Removed duplicated region for block: B:83:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01d0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:100:0x01de -> B:91:0x01b7). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:126:0x0233 -> B:127:0x0234). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:132:0x0249 -> B:125:0x0231). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x013f -> B:66:0x0147). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x0157 -> B:64:0x0139). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x0194 -> B:82:0x0198). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:86:0x01a8 -> B:79:0x0189). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:95:0x01ca -> B:96:0x01ce). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zza(T r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.clearcut.zzay r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 990
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.clearcut.zzay):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <K, V> int zza(T t, byte[] bArr, int r9, int r10, int r11, int r12, long j, zzay zzayVar) throws IOException {
        Unsafe unsafe = zzmh;
        Object zzae = zzae(r11);
        Object object = unsafe.getObject(t, j);
        if (this.zzmz.zzi(object)) {
            Object zzk = this.zzmz.zzk(zzae);
            this.zzmz.zzb(zzk, object);
            unsafe.putObject(t, j, zzk);
            object = zzk;
        }
        zzdh<?, ?> zzl = this.zzmz.zzl(zzae);
        Map<?, ?> zzg = this.zzmz.zzg(object);
        int zza = zzax.zza(bArr, r9, zzayVar);
        int r122 = zzayVar.zzfd;
        if (r122 < 0 || r122 > r10 - zza) {
            throw zzco.zzbl();
        }
        int r123 = r122 + zza;
        Object obj = (K) zzl.zzmc;
        Object obj2 = (V) zzl.zzdu;
        while (zza < r123) {
            int r0 = zza + 1;
            int r92 = bArr[zza];
            if (r92 < 0) {
                r0 = zzax.zza(r92, bArr, r0, zzayVar);
                r92 = zzayVar.zzfd;
            }
            int r1 = r0;
            int r02 = r92 >>> 3;
            int r2 = r92 & 7;
            if (r02 != 1) {
                if (r02 == 2 && r2 == zzl.zzmd.zzel()) {
                    zza = zza(bArr, r1, r10, zzl.zzmd, zzl.zzdu.getClass(), zzayVar);
                    obj2 = zzayVar.zzff;
                }
                zza = zzax.zza(r92, bArr, r1, r10, zzayVar);
            } else if (r2 == zzl.zzmb.zzel()) {
                zza = zza(bArr, r1, r10, zzl.zzmb, (Class<?>) null, zzayVar);
                obj = (K) zzayVar.zzff;
            } else {
                zza = zzax.zza(r92, bArr, r1, r10, zzayVar);
            }
        }
        if (zza == r123) {
            zzg.put(obj, obj2);
            return r123;
        }
        throw zzco.zzbo();
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x0370 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zza(T r27, byte[] r28, int r29, int r30, int r31, com.google.android.gms.internal.clearcut.zzay r32) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1070
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.clearcut.zzay):int");
    }

    private static int zza(byte[] bArr, int r2, int r3, zzfl zzflVar, Class<?> cls, zzay zzayVar) throws IOException {
        int zzb;
        Object valueOf;
        Object valueOf2;
        Object valueOf3;
        int r22;
        long j;
        switch (zzdt.zzgq[zzflVar.ordinal()]) {
            case 1:
                zzb = zzax.zzb(bArr, r2, zzayVar);
                valueOf = Boolean.valueOf(zzayVar.zzfe != 0);
                zzayVar.zzff = valueOf;
                return zzb;
            case 2:
                return zzax.zze(bArr, r2, zzayVar);
            case 3:
                valueOf2 = Double.valueOf(zzax.zze(bArr, r2));
                zzayVar.zzff = valueOf2;
                return r2 + 8;
            case 4:
            case 5:
                valueOf3 = Integer.valueOf(zzax.zzc(bArr, r2));
                zzayVar.zzff = valueOf3;
                return r2 + 4;
            case 6:
            case 7:
                valueOf2 = Long.valueOf(zzax.zzd(bArr, r2));
                zzayVar.zzff = valueOf2;
                return r2 + 8;
            case 8:
                valueOf3 = Float.valueOf(zzax.zzf(bArr, r2));
                zzayVar.zzff = valueOf3;
                return r2 + 4;
            case 9:
            case 10:
            case 11:
                zzb = zzax.zza(bArr, r2, zzayVar);
                r22 = zzayVar.zzfd;
                valueOf = Integer.valueOf(r22);
                zzayVar.zzff = valueOf;
                return zzb;
            case 12:
            case 13:
                zzb = zzax.zzb(bArr, r2, zzayVar);
                j = zzayVar.zzfe;
                valueOf = Long.valueOf(j);
                zzayVar.zzff = valueOf;
                return zzb;
            case 14:
                return zza((zzef) zzea.zzcm().zze(cls), bArr, r2, r3, zzayVar);
            case 15:
                zzb = zzax.zza(bArr, r2, zzayVar);
                r22 = zzbk.zzm(zzayVar.zzfd);
                valueOf = Integer.valueOf(r22);
                zzayVar.zzff = valueOf;
                return zzb;
            case 16:
                zzb = zzax.zzb(bArr, r2, zzayVar);
                j = zzbk.zza(zzayVar.zzfe);
                valueOf = Long.valueOf(j);
                zzayVar.zzff = valueOf;
                return zzb;
            case 17:
                return zzax.zzd(bArr, r2, zzayVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzds<T> zza(Class<T> cls, zzdm zzdmVar, zzdw zzdwVar, zzcy zzcyVar, zzex<?, ?> zzexVar, zzbu<?> zzbuVar, zzdj zzdjVar) {
        int zzcu;
        int r8;
        int r9;
        int zza;
        int r13;
        int r14;
        if (!(zzdmVar instanceof zzec)) {
            ((zzes) zzdmVar).zzcf();
            throw new NoSuchMethodError();
        }
        zzec zzecVar = (zzec) zzdmVar;
        boolean z = zzecVar.zzcf() == zzcg.zzg.zzkm;
        if (zzecVar.getFieldCount() == 0) {
            zzcu = 0;
            r8 = 0;
            r9 = 0;
        } else {
            int zzcp = zzecVar.zzcp();
            int zzcq = zzecVar.zzcq();
            zzcu = zzecVar.zzcu();
            r8 = zzcp;
            r9 = zzcq;
        }
        int[] r6 = new int[zzcu << 2];
        Object[] objArr = new Object[zzcu << 1];
        int[] r15 = zzecVar.zzcr() > 0 ? new int[zzecVar.zzcr()] : null;
        int[] r16 = zzecVar.zzcs() > 0 ? new int[zzecVar.zzcs()] : null;
        zzed zzco = zzecVar.zzco();
        if (zzco.next()) {
            int zzcx = zzco.zzcx();
            int r5 = 0;
            int r10 = 0;
            int r11 = 0;
            while (true) {
                if (zzcx >= zzecVar.zzcv() || r5 >= ((zzcx - r8) << 2)) {
                    if (zzco.zzda()) {
                        zza = (int) zzfd.zza(zzco.zzdb());
                        r14 = (int) zzfd.zza(zzco.zzdc());
                        r13 = 0;
                    } else {
                        zza = (int) zzfd.zza(zzco.zzdd());
                        if (zzco.zzde()) {
                            r14 = (int) zzfd.zza(zzco.zzdf());
                            r13 = zzco.zzdg();
                        } else {
                            r13 = 0;
                            r14 = 0;
                        }
                    }
                    r6[r5] = zzco.zzcx();
                    int r17 = r5 + 1;
                    r6[r17] = (zzco.zzdi() ? 536870912 : 0) | (zzco.zzdh() ? 268435456 : 0) | (zzco.zzcy() << 20) | zza;
                    r6[r5 + 2] = (r13 << 20) | r14;
                    if (zzco.zzdl() != null) {
                        int r2 = (r5 / 4) << 1;
                        objArr[r2] = zzco.zzdl();
                        if (zzco.zzdj() != null) {
                            objArr[r2 + 1] = zzco.zzdj();
                        } else if (zzco.zzdk() != null) {
                            objArr[r2 + 1] = zzco.zzdk();
                        }
                    } else if (zzco.zzdj() != null) {
                        objArr[((r5 / 4) << 1) + 1] = zzco.zzdj();
                    } else if (zzco.zzdk() != null) {
                        objArr[((r5 / 4) << 1) + 1] = zzco.zzdk();
                    }
                    int zzcy = zzco.zzcy();
                    if (zzcy == zzcb.MAP.ordinal()) {
                        r15[r10] = r5;
                        r10++;
                    } else if (zzcy >= 18 && zzcy <= 49) {
                        r16[r11] = r6[r17] & 1048575;
                        r11++;
                    }
                    if (!zzco.next()) {
                        break;
                    }
                    zzcx = zzco.zzcx();
                } else {
                    for (int r132 = 0; r132 < 4; r132++) {
                        r6[r5 + r132] = -1;
                    }
                }
                r5 += 4;
            }
        }
        return new zzds<>(r6, objArr, r8, r9, zzecVar.zzcv(), zzecVar.zzch(), z, false, zzecVar.zzct(), r15, r16, zzdwVar, zzcyVar, zzexVar, zzbuVar, zzdjVar);
    }

    private final <K, V, UT, UB> UB zza(int r5, int r6, Map<K, V> map, zzck<?> zzckVar, UB ub, zzex<UT, UB> zzexVar) {
        zzdh<?, ?> zzl = this.zzmz.zzl(zzae(r5));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (zzckVar.zzb(((Integer) next.getValue()).intValue()) == null) {
                if (ub == null) {
                    ub = zzexVar.zzdz();
                }
                zzbg zzk = zzbb.zzk(zzdg.zza(zzl, next.getKey(), next.getValue()));
                try {
                    zzdg.zza(zzk.zzae(), zzl, next.getKey(), next.getValue());
                    zzexVar.zza((zzex<UT, UB>) ub, r6, zzk.zzad());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private static void zza(int r1, Object obj, zzfr zzfrVar) throws IOException {
        if (obj instanceof String) {
            zzfrVar.zza(r1, (String) obj);
        } else {
            zzfrVar.zza(r1, (zzbb) obj);
        }
    }

    private static <UT, UB> void zza(zzex<UT, UB> zzexVar, T t, zzfr zzfrVar) throws IOException {
        zzexVar.zza(zzexVar.zzq(t), zzfrVar);
    }

    private final <K, V> void zza(zzfr zzfrVar, int r3, Object obj, int r5) throws IOException {
        if (obj != null) {
            zzfrVar.zza(r3, this.zzmz.zzl(zzae(r5)), this.zzmz.zzh(obj));
        }
    }

    private final void zza(T t, T t2, int r6) {
        long zzag = zzag(r6) & 1048575;
        if (zza((zzds<T>) t2, r6)) {
            Object zzo = zzfd.zzo(t, zzag);
            Object zzo2 = zzfd.zzo(t2, zzag);
            if (zzo != null && zzo2 != null) {
                zzfd.zza(t, zzag, zzci.zza(zzo, zzo2));
                zzb((zzds<T>) t, r6);
            } else if (zzo2 != null) {
                zzfd.zza(t, zzag, zzo2);
                zzb((zzds<T>) t, r6);
            }
        }
    }

    private final boolean zza(T t, int r8) {
        if (!this.zzmq) {
            int zzah = zzah(r8);
            return (zzfd.zzj(t, (long) (zzah & 1048575)) & (1 << (zzah >>> 20))) != 0;
        }
        int zzag = zzag(r8);
        long j = zzag & 1048575;
        switch ((zzag & 267386880) >>> 20) {
            case 0:
                return zzfd.zzn(t, j) != 0.0d;
            case 1:
                return zzfd.zzm(t, j) != 0.0f;
            case 2:
                return zzfd.zzk(t, j) != 0;
            case 3:
                return zzfd.zzk(t, j) != 0;
            case 4:
                return zzfd.zzj(t, j) != 0;
            case 5:
                return zzfd.zzk(t, j) != 0;
            case 6:
                return zzfd.zzj(t, j) != 0;
            case 7:
                return zzfd.zzl(t, j);
            case 8:
                Object zzo = zzfd.zzo(t, j);
                if (zzo instanceof String) {
                    return !((String) zzo).isEmpty();
                } else if (zzo instanceof zzbb) {
                    return !zzbb.zzfi.equals(zzo);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzfd.zzo(t, j) != null;
            case 10:
                return !zzbb.zzfi.equals(zzfd.zzo(t, j));
            case 11:
                return zzfd.zzj(t, j) != 0;
            case 12:
                return zzfd.zzj(t, j) != 0;
            case 13:
                return zzfd.zzj(t, j) != 0;
            case 14:
                return zzfd.zzk(t, j) != 0;
            case 15:
                return zzfd.zzj(t, j) != 0;
            case 16:
                return zzfd.zzk(t, j) != 0;
            case 17:
                return zzfd.zzo(t, j) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zza(T t, int r4, int r5) {
        return zzfd.zzj(t, (long) (zzah(r5) & 1048575)) == r4;
    }

    private final boolean zza(T t, int r3, int r4, int r5) {
        return this.zzmq ? zza((zzds<T>) t, r3) : (r4 & r5) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int r3, zzef zzefVar) {
        return zzefVar.zzo(zzfd.zzo(obj, r3 & 1048575));
    }

    private final zzef zzad(int r4) {
        int r42 = (r4 / 4) << 1;
        zzef zzefVar = (zzef) this.zzmj[r42];
        if (zzefVar != null) {
            return zzefVar;
        }
        zzef<T> zze = zzea.zzcm().zze((Class) this.zzmj[r42 + 1]);
        this.zzmj[r42] = zze;
        return zze;
    }

    private final Object zzae(int r2) {
        return this.zzmj[(r2 / 4) << 1];
    }

    private final zzck<?> zzaf(int r2) {
        return (zzck) this.zzmj[((r2 / 4) << 1) + 1];
    }

    private final int zzag(int r2) {
        return this.zzmi[r2 + 1];
    }

    private final int zzah(int r2) {
        return this.zzmi[r2 + 2];
    }

    private final int zzai(int r7) {
        int r0 = this.zzmk;
        if (r7 >= r0) {
            int r2 = this.zzmm;
            if (r7 < r2) {
                int r02 = (r7 - r0) << 2;
                if (this.zzmi[r02] == r7) {
                    return r02;
                }
                return -1;
            } else if (r7 <= this.zzml) {
                int r22 = r2 - r0;
                int length = (this.zzmi.length / 4) - 1;
                while (r22 <= length) {
                    int r3 = (length + r22) >>> 1;
                    int r4 = r3 << 2;
                    int r5 = this.zzmi[r4];
                    if (r7 == r5) {
                        return r4;
                    }
                    if (r7 < r5) {
                        length = r3 - 1;
                    } else {
                        r22 = r3 + 1;
                    }
                }
            }
        }
        return -1;
    }

    private final void zzb(T t, int r5) {
        if (this.zzmq) {
            return;
        }
        int zzah = zzah(r5);
        long j = zzah & 1048575;
        zzfd.zza((Object) t, j, zzfd.zzj(t, j) | (1 << (zzah >>> 20)));
    }

    private final void zzb(T t, int r4, int r5) {
        zzfd.zza((Object) t, zzah(r5) & 1048575, r4);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0494  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzb(T r19, com.google.android.gms.internal.clearcut.zzfr r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzb(java.lang.Object, com.google.android.gms.internal.clearcut.zzfr):void");
    }

    private final void zzb(T t, T t2, int r7) {
        int zzag = zzag(r7);
        int r1 = this.zzmi[r7];
        long j = zzag & 1048575;
        if (zza((zzds<T>) t2, r1, r7)) {
            Object zzo = zzfd.zzo(t, j);
            Object zzo2 = zzfd.zzo(t2, j);
            if (zzo != null && zzo2 != null) {
                zzfd.zza(t, j, zzci.zza(zzo, zzo2));
                zzb((zzds<T>) t, r1, r7);
            } else if (zzo2 != null) {
                zzfd.zza(t, j, zzo2);
                zzb((zzds<T>) t, r1, r7);
            }
        }
    }

    private final boolean zzc(T t, T t2, int r3) {
        return zza((zzds<T>) t, r3) == zza((zzds<T>) t2, r3);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzfd.zzo(obj, j);
    }

    private static <T> double zze(T t, long j) {
        return ((Double) zzfd.zzo(t, j)).doubleValue();
    }

    private static <T> float zzf(T t, long j) {
        return ((Float) zzfd.zzo(t, j)).floatValue();
    }

    private static <T> int zzg(T t, long j) {
        return ((Integer) zzfd.zzo(t, j)).intValue();
    }

    private static <T> long zzh(T t, long j) {
        return ((Long) zzfd.zzo(t, j)).longValue();
    }

    private static <T> boolean zzi(T t, long j) {
        return ((Boolean) zzfd.zzo(t, j)).booleanValue();
    }

    private static zzey zzn(Object obj) {
        zzcg zzcgVar = (zzcg) obj;
        zzey zzeyVar = zzcgVar.zzjp;
        if (zzeyVar == zzey.zzea()) {
            zzey zzeb = zzey.zzeb();
            zzcgVar.zzjp = zzeb;
            return zzeb;
        }
        return zzeyVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x01a0, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005c, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0082, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0096, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a8, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ba, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00cc, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e2, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f8, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x010e, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0120, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzl(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzl(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0132, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0145, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0156, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0169, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x017c, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x018d, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    @Override // com.google.android.gms.internal.clearcut.zzef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 610
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00ce, code lost:
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e0, code lost:
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e2, code lost:
        r7 = r3.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e6, code lost:
        r2 = (r2 * 53) + r7;
     */
    @Override // com.google.android.gms.internal.clearcut.zzef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int hashCode(T r9) {
        /*
            Method dump skipped, instructions count: 476
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.hashCode(java.lang.Object):int");
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final T newInstance() {
        return (T) this.zzmv.newInstance(this.zzmn);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x04b9  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0976  */
    @Override // com.google.android.gms.internal.clearcut.zzef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r14, com.google.android.gms.internal.clearcut.zzfr r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2736
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, com.google.android.gms.internal.clearcut.zzfr):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x0164, code lost:
        if (r0 == r15) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0188, code lost:
        if (r0 == r15) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01a1, code lost:
        if (r0 == r15) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01a3, code lost:
        r2 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v25, types: [int] */
    @Override // com.google.android.gms.internal.clearcut.zzef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r23, byte[] r24, int r25, int r26, com.google.android.gms.internal.clearcut.zzay r27) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 518
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.clearcut.zzay):void");
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zzc(T t) {
        int[] r0 = this.zzmt;
        if (r0 != null) {
            for (int r4 : r0) {
                long zzag = zzag(r4) & 1048575;
                Object zzo = zzfd.zzo(t, zzag);
                if (zzo != null) {
                    zzfd.zza(t, zzag, this.zzmz.zzj(zzo));
                }
            }
        }
        int[] r02 = this.zzmu;
        if (r02 != null) {
            for (int r3 : r02) {
                this.zzmw.zza(t, r3);
            }
        }
        this.zzmx.zzc(t);
        if (this.zzmo) {
            this.zzmy.zzc(t);
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zzc(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int r0 = 0; r0 < this.zzmi.length; r0 += 4) {
            int zzag = zzag(r0);
            long j = 1048575 & zzag;
            int r4 = this.zzmi[r0];
            switch ((zzag & 267386880) >>> 20) {
                case 0:
                    if (zza((zzds<T>) t2, r0)) {
                        zzfd.zza(t, j, zzfd.zzn(t2, j));
                        zzb((zzds<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zza((zzds<T>) t2, r0)) {
                        zzfd.zza((Object) t, j, zzfd.zzm(t2, j));
                        zzb((zzds<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 3:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 4:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 5:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 6:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 7:
                    if (zza((zzds<T>) t2, r0)) {
                        zzfd.zza(t, j, zzfd.zzl(t2, j));
                        zzb((zzds<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza(t, j, zzfd.zzo(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 9:
                case 17:
                    zza(t, t2, r0);
                    break;
                case 10:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza(t, j, zzfd.zzo(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 11:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 12:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 13:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 14:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 15:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 16:
                    if (!zza((zzds<T>) t2, r0)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                    zzb((zzds<T>) t, r0);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzmw.zza(t, t2, j);
                    break;
                case 50:
                    zzeh.zza(this.zzmz, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zza((zzds<T>) t2, r4, r0)) {
                        break;
                    }
                    zzfd.zza(t, j, zzfd.zzo(t2, j));
                    zzb((zzds<T>) t, r4, r0);
                    break;
                case 60:
                case 68:
                    zzb(t, t2, r0);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zza((zzds<T>) t2, r4, r0)) {
                        break;
                    }
                    zzfd.zza(t, j, zzfd.zzo(t2, j));
                    zzb((zzds<T>) t, r4, r0);
                    break;
            }
        }
        if (this.zzmq) {
            return;
        }
        zzeh.zza(this.zzmx, t, t2);
        if (this.zzmo) {
            zzeh.zza(this.zzmy, t, t2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0181, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0193, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01a5, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x01b6, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01c7, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x01d8, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x01e9, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01fa, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x020b, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x020d, code lost:
        r2.putInt(r20, r14, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0211, code lost:
        r3 = (com.google.android.gms.internal.clearcut.zzbn.zzr(r3) + com.google.android.gms.internal.clearcut.zzbn.zzt(r5)) + r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0331, code lost:
        if ((r5 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0334, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, (java.lang.String) r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x0414, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L266;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x0434, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L278;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x043c, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x045c, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x0464, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x0474, code lost:
        if ((r4 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L294;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x047c, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L305;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x0514, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x0526, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x0538, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x054a, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x055c, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x056e, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:338:0x0580, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x0592, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x05a3, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x05b4, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:358:0x05c5, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x05d6, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x05e7, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x05f8, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x05fa, code lost:
        r2.putInt(r20, r9, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x05fe, code lost:
        r9 = (com.google.android.gms.internal.clearcut.zzbn.zzr(r15) + com.google.android.gms.internal.clearcut.zzbn.zzt(r4)) + r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:393:0x06b4, code lost:
        if ((r12 & r18) != 0) goto L266;
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:0x06b6, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzc(r15, (com.google.android.gms.internal.clearcut.zzdo) r2.getObject(r20, r10), zzad(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x06e1, code lost:
        if ((r12 & r18) != 0) goto L278;
     */
    /* JADX WARN: Code restructure failed: missing block: B:405:0x06e3, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzh(r15, 0L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x06ec, code lost:
        if ((r12 & r18) != 0) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:408:0x06ee, code lost:
        r9 = com.google.android.gms.internal.clearcut.zzbn.zzk(r15, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:419:0x0711, code lost:
        if ((r12 & r18) != 0) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:420:0x0713, code lost:
        r4 = r2.getObject(r20, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x0717, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzc(r15, (com.google.android.gms.internal.clearcut.zzbb) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:423:0x0720, code lost:
        if ((r12 & r18) != 0) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:424:0x0722, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzc(r15, r2.getObject(r20, r10), zzad(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:428:0x073a, code lost:
        if ((r4 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L294;
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x073d, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzb(r15, (java.lang.String) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:432:0x0747, code lost:
        if ((r12 & r18) != 0) goto L305;
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x0749, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzc(r15, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ab, code lost:
        if ((r5 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0127, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0139, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x014b, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x015d, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x016f, code lost:
        if (r19.zzmr != false) goto L104;
     */
    @Override // com.google.android.gms.internal.clearcut.zzef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzm(T r20) {
        /*
            Method dump skipped, instructions count: 2290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzm(java.lang.Object):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10, types: [com.google.android.gms.internal.clearcut.zzef] */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v27, types: [com.google.android.gms.internal.clearcut.zzef] */
    /* JADX WARN: Type inference failed for: r7v30 */
    @Override // com.google.android.gms.internal.clearcut.zzef
    public final boolean zzo(T t) {
        int r15;
        int r12;
        boolean z;
        boolean z2;
        int[] r2 = this.zzms;
        if (r2 != null && r2.length != 0) {
            int r4 = -1;
            int length = r2.length;
            int r8 = 0;
            for (int r7 = 0; r7 < length; r7 = r15 + 1) {
                int r9 = r2[r7];
                int zzai = zzai(r9);
                int zzag = zzag(zzai);
                if (this.zzmq) {
                    r15 = r7;
                    r12 = 0;
                } else {
                    int r122 = this.zzmi[zzai + 2];
                    int r14 = r122 & 1048575;
                    r12 = 1 << (r122 >>> 20);
                    if (r14 != r4) {
                        r15 = r7;
                        r8 = zzmh.getInt(t, r14);
                        r4 = r14;
                    } else {
                        r15 = r7;
                    }
                }
                if (((268435456 & zzag) != 0) && !zza((zzds<T>) t, zzai, r8, r12)) {
                    return false;
                }
                int r6 = (267386880 & zzag) >>> 20;
                if (r6 != 9 && r6 != 17) {
                    if (r6 != 27) {
                        if (r6 == 60 || r6 == 68) {
                            if (zza((zzds<T>) t, r9, zzai) && !zza(t, zzag, zzad(zzai))) {
                                return false;
                            }
                        } else if (r6 != 49) {
                            if (r6 != 50) {
                                continue;
                            } else {
                                Map<?, ?> zzh = this.zzmz.zzh(zzfd.zzo(t, zzag & 1048575));
                                if (!zzh.isEmpty()) {
                                    if (this.zzmz.zzl(zzae(zzai)).zzmd.zzek() == zzfq.MESSAGE) {
                                        zzef<T> zzefVar = 0;
                                        for (Object obj : zzh.values()) {
                                            if (zzefVar == null) {
                                                zzefVar = zzea.zzcm().zze(obj.getClass());
                                            }
                                            boolean zzo = zzefVar.zzo(obj);
                                            zzefVar = zzefVar;
                                            if (!zzo) {
                                                z2 = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                                z2 = true;
                                if (!z2) {
                                    return false;
                                }
                            }
                        }
                    }
                    List list = (List) zzfd.zzo(t, zzag & 1048575);
                    if (!list.isEmpty()) {
                        ?? zzad = zzad(zzai);
                        for (int r92 = 0; r92 < list.size(); r92++) {
                            if (!zzad.zzo(list.get(r92))) {
                                z = false;
                                break;
                            }
                        }
                    }
                    z = true;
                    if (!z) {
                        return false;
                    }
                } else if (zza((zzds<T>) t, zzai, r8, r12) && !zza(t, zzag, zzad(zzai))) {
                    return false;
                }
            }
            if (this.zzmo && !this.zzmy.zza(t).isInitialized()) {
                return false;
            }
        }
        return true;
    }
}
