package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzgi<T extends zzgk<T>> {
    private static final zzgi zzte = new zzgi(true);
    final zziw<T, Object> zztb;
    private boolean zztc;
    private boolean zztd;

    private zzgi() {
        this.zztb = zziw.zzbt(16);
    }

    private zzgi(boolean z) {
        this(zziw.zzbt(0));
        zzdp();
    }

    private zzgi(zziw<T, Object> zziwVar) {
        this.zztb = zziwVar;
        zzdp();
    }

    public static <T extends zzgk<T>> zzgi<T> zzfn() {
        return zzte;
    }

    public final void zzdp() {
        if (this.zztc) {
            return;
        }
        this.zztb.zzdp();
        this.zztc = true;
    }

    public final boolean isImmutable() {
        return this.zztc;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzgi) {
            return this.zztb.equals(((zzgi) obj).zztb);
        }
        return false;
    }

    public final int hashCode() {
        return this.zztb.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> iterator() {
        if (this.zztd) {
            return new zzhi(this.zztb.entrySet().iterator());
        }
        return this.zztb.entrySet().iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> descendingIterator() {
        if (this.zztd) {
            return new zzhi(this.zztb.zzhz().iterator());
        }
        return this.zztb.zzhz().iterator();
    }

    public final Object zza(T t) {
        Object obj = this.zztb.get(t);
        if (obj instanceof zzhd) {
            zzhd zzhdVar = (zzhd) obj;
            return zzhd.zzgu();
        }
        return obj;
    }

    public final void zza(T t, Object obj) {
        if (t.zzfu()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            int r1 = 0;
            while (r1 < size) {
                Object obj2 = arrayList.get(r1);
                r1++;
                zza(t.zzfs(), obj2);
            }
            obj = arrayList;
        } else {
            zza(t.zzfs(), obj);
        }
        if (obj instanceof zzhd) {
            this.zztd = true;
        }
        this.zztb.zza((zziw<T, Object>) t, (T) obj);
    }

    public final void zzb(T t, Object obj) {
        List list;
        if (!t.zzfu()) {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }
        zza(t.zzfs(), obj);
        Object zza = zza((zzgi<T>) t);
        if (zza == null) {
            list = new ArrayList();
            this.zztb.zza((zziw<T, Object>) t, (T) list);
        } else {
            list = (List) zza;
        }
        list.add(obj);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        if ((r3 instanceof com.google.android.gms.internal.vision.zzgw) == false) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
        if ((r3 instanceof com.google.android.gms.internal.vision.zzhd) == false) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void zza(com.google.android.gms.internal.vision.zzka r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.vision.zzgt.checkNotNull(r3)
            int[] r0 = com.google.android.gms.internal.vision.zzgl.zzth
            com.google.android.gms.internal.vision.zzkd r2 = r2.zzip()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L40;
                case 2: goto L3d;
                case 3: goto L3a;
                case 4: goto L37;
                case 5: goto L34;
                case 6: goto L31;
                case 7: goto L28;
                case 8: goto L1f;
                case 9: goto L16;
                default: goto L14;
            }
        L14:
            r0 = 0
            goto L42
        L16:
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzic
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzhd
            if (r2 == 0) goto L14
            goto L42
        L1f:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzgw
            if (r2 == 0) goto L14
            goto L42
        L28:
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzfh
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L14
            goto L42
        L31:
            boolean r0 = r3 instanceof java.lang.String
            goto L42
        L34:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L42
        L37:
            boolean r0 = r3 instanceof java.lang.Double
            goto L42
        L3a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L42
        L3d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L42
        L40:
            boolean r0 = r3 instanceof java.lang.Integer
        L42:
            if (r0 == 0) goto L45
            return
        L45:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzgi.zza(com.google.android.gms.internal.vision.zzka, java.lang.Object):void");
    }

    public final boolean isInitialized() {
        for (int r1 = 0; r1 < this.zztb.zzhx(); r1++) {
            if (!zzb(this.zztb.zzbu(r1))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> entry : this.zztb.zzhy()) {
            if (!zzb(entry)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzgk<T>> boolean zzb(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzft() == zzkd.MESSAGE) {
            if (key.zzfu()) {
                for (zzic zzicVar : (List) entry.getValue()) {
                    if (!zzicVar.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzic) {
                    if (!((zzic) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzhd) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzgi<T> zzgiVar) {
        for (int r0 = 0; r0 < zzgiVar.zztb.zzhx(); r0++) {
            zzc(zzgiVar.zztb.zzbu(r0));
        }
        for (Map.Entry<T, Object> entry : zzgiVar.zztb.zzhy()) {
            zzc(entry);
        }
    }

    private static Object zzh(Object obj) {
        if (obj instanceof zzih) {
            return ((zzih) obj).zzdm();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        return obj;
    }

    private final void zzc(Map.Entry<T, Object> entry) {
        zzic zzgc;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzhd) {
            zzhd zzhdVar = (zzhd) value;
            value = zzhd.zzgu();
        }
        if (key.zzfu()) {
            Object zza = zza((zzgi<T>) key);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) zza).add(zzh(obj));
            }
            this.zztb.zza((zziw<T, Object>) key, (T) zza);
        } else if (key.zzft() == zzkd.MESSAGE) {
            Object zza2 = zza((zzgi<T>) key);
            if (zza2 == null) {
                this.zztb.zza((zziw<T, Object>) key, (T) zzh(value));
                return;
            }
            if (zza2 instanceof zzih) {
                zzgc = key.zza((zzih) zza2, (zzih) value);
            } else {
                zzgc = key.zza(((zzic) zza2).zzgi(), (zzic) value).zzgc();
            }
            this.zztb.zza((zziw<T, Object>) key, (T) zzgc);
        } else {
            this.zztb.zza((zziw<T, Object>) key, (T) zzh(value));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(zzga zzgaVar, zzka zzkaVar, int r3, Object obj) throws IOException {
        if (zzkaVar == zzka.zzabv) {
            zzic zzicVar = (zzic) obj;
            zzgt.zzf(zzicVar);
            zzgaVar.zzg(r3, 3);
            zzicVar.zzb(zzgaVar);
            zzgaVar.zzg(r3, 4);
            return;
        }
        zzgaVar.zzg(r3, zzkaVar.zziq());
        switch (zzgl.zzrr[zzkaVar.ordinal()]) {
            case 1:
                zzgaVar.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzgaVar.zzs(((Float) obj).floatValue());
                return;
            case 3:
                zzgaVar.zzs(((Long) obj).longValue());
                return;
            case 4:
                zzgaVar.zzs(((Long) obj).longValue());
                return;
            case 5:
                zzgaVar.zzaw(((Integer) obj).intValue());
                return;
            case 6:
                zzgaVar.zzu(((Long) obj).longValue());
                return;
            case 7:
                zzgaVar.zzaz(((Integer) obj).intValue());
                return;
            case 8:
                zzgaVar.zzk(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzic) obj).zzb(zzgaVar);
                return;
            case 10:
                zzgaVar.zzb((zzic) obj);
                return;
            case 11:
                if (obj instanceof zzfh) {
                    zzgaVar.zza((zzfh) obj);
                    return;
                } else {
                    zzgaVar.zzx((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzfh) {
                    zzgaVar.zza((zzfh) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzgaVar.zze(bArr, 0, bArr.length);
                return;
            case 13:
                zzgaVar.zzax(((Integer) obj).intValue());
                return;
            case 14:
                zzgaVar.zzaz(((Integer) obj).intValue());
                return;
            case 15:
                zzgaVar.zzu(((Long) obj).longValue());
                return;
            case 16:
                zzgaVar.zzay(((Integer) obj).intValue());
                return;
            case 17:
                zzgaVar.zzt(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzgw) {
                    zzgaVar.zzaw(((zzgw) obj).zzag());
                    return;
                } else {
                    zzgaVar.zzaw(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final int zzfo() {
        int r1 = 0;
        for (int r0 = 0; r0 < this.zztb.zzhx(); r0++) {
            r1 += zzd(this.zztb.zzbu(r0));
        }
        for (Map.Entry<T, Object> entry : this.zztb.zzhy()) {
            r1 += zzd(entry);
        }
        return r1;
    }

    private static int zzd(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzft() == zzkd.MESSAGE && !key.zzfu() && !key.zzfv()) {
            if (value instanceof zzhd) {
                return zzga.zzb(entry.getKey().zzag(), (zzhd) value);
            }
            return zzga.zzb(entry.getKey().zzag(), (zzic) value);
        }
        return zzc(key, value);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzka zzkaVar, int r2, Object obj) {
        int zzba = zzga.zzba(r2);
        if (zzkaVar == zzka.zzabv) {
            zzgt.zzf((zzic) obj);
            zzba <<= 1;
        }
        return zzba + zzb(zzkaVar, obj);
    }

    private static int zzb(zzka zzkaVar, Object obj) {
        switch (zzgl.zzrr[zzkaVar.ordinal()]) {
            case 1:
                return zzga.zzb(((Double) obj).doubleValue());
            case 2:
                return zzga.zzt(((Float) obj).floatValue());
            case 3:
                return zzga.zzv(((Long) obj).longValue());
            case 4:
                return zzga.zzw(((Long) obj).longValue());
            case 5:
                return zzga.zzbb(((Integer) obj).intValue());
            case 6:
                return zzga.zzy(((Long) obj).longValue());
            case 7:
                return zzga.zzbe(((Integer) obj).intValue());
            case 8:
                return zzga.zzl(((Boolean) obj).booleanValue());
            case 9:
                return zzga.zzd((zzic) obj);
            case 10:
                if (obj instanceof zzhd) {
                    return zzga.zza((zzhd) obj);
                }
                return zzga.zzc((zzic) obj);
            case 11:
                if (obj instanceof zzfh) {
                    return zzga.zzb((zzfh) obj);
                }
                return zzga.zzy((String) obj);
            case 12:
                if (obj instanceof zzfh) {
                    return zzga.zzb((zzfh) obj);
                }
                return zzga.zzf((byte[]) obj);
            case 13:
                return zzga.zzbc(((Integer) obj).intValue());
            case 14:
                return zzga.zzbf(((Integer) obj).intValue());
            case 15:
                return zzga.zzz(((Long) obj).longValue());
            case 16:
                return zzga.zzbd(((Integer) obj).intValue());
            case 17:
                return zzga.zzx(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzgw) {
                    return zzga.zzbg(((zzgw) obj).zzag());
                }
                return zzga.zzbg(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zzc(zzgk<?> zzgkVar, Object obj) {
        zzka zzfs = zzgkVar.zzfs();
        int zzag = zzgkVar.zzag();
        if (zzgkVar.zzfu()) {
            int r2 = 0;
            if (zzgkVar.zzfv()) {
                for (Object obj2 : (List) obj) {
                    r2 += zzb(zzfs, obj2);
                }
                return zzga.zzba(zzag) + r2 + zzga.zzbi(r2);
            }
            for (Object obj3 : (List) obj) {
                r2 += zza(zzfs, zzag, obj3);
            }
            return r2;
        }
        return zza(zzfs, zzag, obj);
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzgi zzgiVar = new zzgi();
        for (int r1 = 0; r1 < this.zztb.zzhx(); r1++) {
            Map.Entry<T, Object> zzbu = this.zztb.zzbu(r1);
            zzgiVar.zza((zzgi) zzbu.getKey(), zzbu.getValue());
        }
        for (Map.Entry<T, Object> entry : this.zztb.zzhy()) {
            zzgiVar.zza((zzgi) entry.getKey(), entry.getValue());
        }
        zzgiVar.zztd = this.zztd;
        return zzgiVar;
    }
}
