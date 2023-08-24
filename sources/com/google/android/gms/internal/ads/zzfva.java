package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfva extends zzfuq implements Set {
    @CheckForNull
    private transient zzfuv zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(int r6) {
        int max = Math.max(r6, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            do {
                highestOneBit += highestOneBit;
            } while (highestOneBit * 0.7d < max);
            return highestOneBit;
        }
        zzfsf.zzf(max < 1073741824, "collection too large");
        return 1073741824;
    }

    public static zzfuz zzj(int r1) {
        return new zzfuz(r1);
    }

    public static zzfva zzl(Collection collection) {
        Object[] array = collection.toArray();
        return zzp(array.length, array);
    }

    @SafeVarargs
    public static zzfva zzm(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object... objArr) {
        Object[] objArr2 = new Object[12];
        objArr2[0] = obj;
        objArr2[1] = obj2;
        objArr2[2] = obj3;
        objArr2[3] = obj4;
        objArr2[4] = obj5;
        objArr2[5] = obj6;
        System.arraycopy(objArr, 0, objArr2, 6, 6);
        return zzp(12, objArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzq(int r1, int r2) {
        return r1 < (r2 >> 1) + (r2 >> 2);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzfva) && zzo() && ((zzfva) obj).zzo() && hashCode() != obj.hashCode()) {
            return false;
        }
        return zzfwq.zzc(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzfwq.zza(this);
    }

    @Override // com.google.android.gms.internal.ads.zzfuq
    public zzfuv zzd() {
        zzfuv zzfuvVar = this.zza;
        if (zzfuvVar == null) {
            zzfuv zzi = zzi();
            this.zza = zzi;
            return zzi;
        }
        return zzfuvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfuq, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zze */
    public abstract zzfwu iterator();

    zzfuv zzi() {
        return zzfuv.zzj(toArray());
    }

    boolean zzo() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzfva zzp(int r13, Object... objArr) {
        if (r13 != 0) {
            if (r13 == 1) {
                Object obj = objArr[0];
                obj.getClass();
                return new zzfwr(obj);
            }
            int zzh = zzh(r13);
            Object[] objArr2 = new Object[zzh];
            int r7 = zzh - 1;
            int r5 = 0;
            int r8 = 0;
            for (int r3 = 0; r3 < r13; r3++) {
                Object obj2 = objArr[r3];
                zzfwc.zza(obj2, r3);
                int hashCode = obj2.hashCode();
                int zza = zzfun.zza(hashCode);
                while (true) {
                    int r11 = zza & r7;
                    Object obj3 = objArr2[r11];
                    if (obj3 != null) {
                        if (obj3.equals(obj2)) {
                            break;
                        }
                        zza++;
                    } else {
                        objArr[r8] = obj2;
                        objArr2[r11] = obj2;
                        r5 += hashCode;
                        r8++;
                        break;
                    }
                }
            }
            Arrays.fill(objArr, r8, r13, (Object) null);
            if (r8 == 1) {
                Object obj4 = objArr[0];
                obj4.getClass();
                return new zzfwr(obj4);
            } else if (zzh(r8) < zzh / 2) {
                return zzp(r8, objArr);
            } else {
                if (zzq(r8, objArr.length)) {
                    objArr = Arrays.copyOf(objArr, r8);
                }
                return new zzfwk(objArr, r5, objArr2, r7, r8);
            }
        }
        return zzfwk.zza;
    }
}
