package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfuf extends AbstractMap implements Serializable {
    private static final Object zzd = new Object();
    @CheckForNull
    transient int[] zza;
    @CheckForNull
    transient Object[] zzb;
    @CheckForNull
    transient Object[] zzc;
    @CheckForNull
    private transient Object zze;
    private transient int zzf;
    private transient int zzg;
    @CheckForNull
    private transient Set zzh;
    @CheckForNull
    private transient Set zzi;
    @CheckForNull
    private transient Collection zzj;

    zzfuf() {
        zzo(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] zzA() {
        Object[] objArr = this.zzb;
        objArr.getClass();
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] zzB() {
        Object[] objArr = this.zzc;
        objArr.getClass();
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int zzb(zzfuf zzfufVar) {
        int r0 = zzfufVar.zzg;
        zzfufVar.zzg = r0 - 1;
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object zzg(zzfuf zzfufVar, int r1) {
        return zzfufVar.zzA()[r1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object zzj(zzfuf zzfufVar, int r1) {
        return zzfufVar.zzB()[r1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object zzk(zzfuf zzfufVar) {
        Object obj = zzfufVar.zze;
        obj.getClass();
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzm(zzfuf zzfufVar, int r1, Object obj) {
        zzfufVar.zzB()[r1] = obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzu() {
        return (1 << (this.zzf & 31)) - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzv(@CheckForNull Object obj) {
        if (zzq()) {
            return -1;
        }
        int zzb = zzfun.zzb(obj);
        int zzu = zzu();
        Object obj2 = this.zze;
        obj2.getClass();
        int zzc = zzfug.zzc(obj2, zzb & zzu);
        if (zzc != 0) {
            int r4 = ~zzu;
            int r0 = zzb & r4;
            do {
                int r3 = zzc - 1;
                int r5 = zzz()[r3];
                if ((r5 & r4) == r0 && zzfsa.zza(obj, zzA()[r3])) {
                    return r3;
                }
                zzc = r5 & zzu;
            } while (zzc != 0);
            return -1;
        }
        return -1;
    }

    private final int zzw(int r9, int r10, int r11, int r12) {
        Object zzd2 = zzfug.zzd(r10);
        int r102 = r10 - 1;
        if (r12 != 0) {
            zzfug.zze(zzd2, r11 & r102, r12 + 1);
        }
        Object obj = this.zze;
        obj.getClass();
        int[] zzz = zzz();
        for (int r1 = 0; r1 <= r9; r1++) {
            int zzc = zzfug.zzc(obj, r1);
            while (zzc != 0) {
                int r3 = zzc - 1;
                int r4 = zzz[r3];
                int r5 = ((~r9) & r4) | r1;
                int r6 = r5 & r102;
                int zzc2 = zzfug.zzc(zzd2, r6);
                zzfug.zze(zzd2, r6, zzc);
                zzz[r3] = ((~r102) & r5) | (zzc2 & r102);
                zzc = r4 & r9;
            }
        }
        this.zze = zzd2;
        zzy(r102);
        return r102;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzx(@CheckForNull Object obj) {
        if (zzq()) {
            return zzd;
        }
        int zzu = zzu();
        Object obj2 = this.zze;
        obj2.getClass();
        int zzb = zzfug.zzb(obj, null, zzu, obj2, zzz(), zzA(), null);
        if (zzb == -1) {
            return zzd;
        }
        Object obj3 = zzB()[zzb];
        zzp(zzb, zzu);
        this.zzg--;
        zzn();
        return obj3;
    }

    private final void zzy(int r2) {
        this.zzf = ((32 - Integer.numberOfLeadingZeros(r2)) & 31) | (this.zzf & (-32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int[] zzz() {
        int[] r0 = this.zza;
        r0.getClass();
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (zzq()) {
            return;
        }
        zzn();
        Map zzl = zzl();
        if (zzl != null) {
            this.zzf = zzfxa.zzb(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            zzl.clear();
            this.zze = null;
            this.zzg = 0;
            return;
        }
        Arrays.fill(zzA(), 0, this.zzg, (Object) null);
        Arrays.fill(zzB(), 0, this.zzg, (Object) null);
        Object obj = this.zze;
        obj.getClass();
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
        Arrays.fill(zzz(), 0, this.zzg, 0);
        this.zzg = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.containsKey(obj);
        }
        return zzv(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl == null) {
            for (int r1 = 0; r1 < this.zzg; r1++) {
                if (zzfsa.zza(obj, zzB()[r1])) {
                    return true;
                }
            }
            return false;
        }
        return zzl.containsValue(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.zzi;
        if (set == null) {
            zzfua zzfuaVar = new zzfua(this);
            this.zzi = zzfuaVar;
            return zzfuaVar;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.get(obj);
        }
        int zzv = zzv(obj);
        if (zzv == -1) {
            return null;
        }
        return zzB()[zzv];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        Set set = this.zzh;
        if (set == null) {
            zzfuc zzfucVar = new zzfuc(this);
            this.zzh = zzfucVar;
            return zzfucVar;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object put(Object obj, Object obj2) {
        int min;
        if (zzq()) {
            zzfsf.zzi(zzq(), "Arrays already allocated");
            int r3 = this.zzf;
            int max = Math.max(r3 + 1, 2);
            int highestOneBit = Integer.highestOneBit(max);
            if (max > highestOneBit && (highestOneBit = highestOneBit + highestOneBit) <= 0) {
                highestOneBit = 1073741824;
            }
            int max2 = Math.max(4, highestOneBit);
            this.zze = zzfug.zzd(max2);
            zzy(max2 - 1);
            this.zza = new int[r3];
            this.zzb = new Object[r3];
            this.zzc = new Object[r3];
        }
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.put(obj, obj2);
        }
        int[] zzz = zzz();
        Object[] zzA = zzA();
        Object[] zzB = zzB();
        int r6 = this.zzg;
        int r7 = r6 + 1;
        int zzb = zzfun.zzb(obj);
        int zzu = zzu();
        int r10 = zzb & zzu;
        Object obj3 = this.zze;
        obj3.getClass();
        int zzc = zzfug.zzc(obj3, r10);
        if (zzc != 0) {
            int r102 = ~zzu;
            int r14 = zzb & r102;
            int r15 = 0;
            while (true) {
                int r11 = zzc - 1;
                int r16 = zzz[r11];
                int r12 = r16 & r102;
                if (r12 == r14 && zzfsa.zza(obj, zzA[r11])) {
                    Object obj4 = zzB[r11];
                    zzB[r11] = obj2;
                    return obj4;
                }
                int r13 = r16 & zzu;
                r15++;
                if (r13 != 0) {
                    zzc = r13;
                } else if (r15 >= 9) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap(zzu() + 1, 1.0f);
                    int zze = zze();
                    while (zze >= 0) {
                        linkedHashMap.put(zzA()[zze], zzB()[zze]);
                        zze = zzf(zze);
                    }
                    this.zze = linkedHashMap;
                    this.zza = null;
                    this.zzb = null;
                    this.zzc = null;
                    zzn();
                    return linkedHashMap.put(obj, obj2);
                } else if (r7 > zzu) {
                    zzu = zzw(zzu, zzfug.zza(zzu), zzb, r6);
                } else {
                    zzz[r11] = (r7 & zzu) | r12;
                }
            }
        } else if (r7 > zzu) {
            zzu = zzw(zzu, zzfug.zza(zzu), zzb, r6);
        } else {
            Object obj5 = this.zze;
            obj5.getClass();
            zzfug.zze(obj5, r10, r7);
        }
        int length = zzz().length;
        if (r7 > length && (min = Math.min((int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            this.zza = Arrays.copyOf(zzz(), min);
            this.zzb = Arrays.copyOf(zzA(), min);
            this.zzc = Arrays.copyOf(zzB(), min);
        }
        zzz()[r6] = (~zzu) & zzb;
        zzA()[r6] = obj;
        zzB()[r6] = obj2;
        this.zzg = r7;
        zzn();
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object remove(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.remove(obj);
        }
        Object zzx = zzx(obj);
        if (zzx == zzd) {
            return null;
        }
        return zzx;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map zzl = zzl();
        return zzl != null ? zzl.size() : this.zzg;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.zzj;
        if (collection == null) {
            zzfue zzfueVar = new zzfue(this);
            this.zzj = zzfueVar;
            return zzfueVar;
        }
        return collection;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zze() {
        return isEmpty() ? -1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzf(int r2) {
        int r22 = r2 + 1;
        if (r22 < this.zzg) {
            return r22;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CheckForNull
    public final Map zzl() {
        Object obj = this.zze;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzn() {
        this.zzf += 32;
    }

    final void zzo(int r3) {
        this.zzf = zzfxa.zzb(8, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzp(int r10, int r11) {
        Object obj = this.zze;
        obj.getClass();
        int[] zzz = zzz();
        Object[] zzA = zzA();
        Object[] zzB = zzB();
        int size = size() - 1;
        if (r10 < size) {
            Object obj2 = zzA[size];
            zzA[r10] = obj2;
            zzB[r10] = zzB[size];
            zzA[size] = null;
            zzB[size] = null;
            zzz[r10] = zzz[size];
            zzz[size] = 0;
            int zzb = zzfun.zzb(obj2) & r11;
            int zzc = zzfug.zzc(obj, zzb);
            int r4 = size + 1;
            if (zzc == r4) {
                zzfug.zze(obj, zzb, r10 + 1);
                return;
            }
            while (true) {
                int r3 = zzc - 1;
                int r0 = zzz[r3];
                int r2 = r0 & r11;
                if (r2 == r4) {
                    zzz[r3] = ((r10 + 1) & r11) | (r0 & (~r11));
                    return;
                }
                zzc = r2;
            }
        } else {
            zzA[r10] = null;
            zzB[r10] = null;
            zzz[r10] = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzq() {
        return this.zze == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfuf(int r1) {
        zzo(8);
    }
}
