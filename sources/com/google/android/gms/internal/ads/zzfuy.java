package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.commons.p028io.FileUtils;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfuy implements Map, Serializable {
    @CheckForNull
    private transient zzfva zza;
    @CheckForNull
    private transient zzfva zzb;
    @CheckForNull
    private transient zzfuq zzc;

    public static zzfuy zzc(Map map) {
        Set entrySet = map.entrySet();
        zzfux zzfuxVar = new zzfux(entrySet instanceof Collection ? entrySet.size() : 4);
        zzfuxVar.zzb(entrySet);
        return zzfuxVar.zzc();
    }

    public static zzfuy zzd() {
        return zzfwj.zza;
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        return zzfvq.zzb(this, obj);
    }

    @Override // java.util.Map
    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @Override // java.util.Map
    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzfwq.zza(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        zzftu.zza(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(size * 8, (long) FileUtils.ONE_GB));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    abstract zzfuq zza();

    @Override // java.util.Map
    /* renamed from: zzb */
    public final zzfuq values() {
        zzfuq zzfuqVar = this.zzc;
        if (zzfuqVar == null) {
            zzfuq zza = zza();
            this.zzc = zza;
            return zza;
        }
        return zzfuqVar;
    }

    abstract zzfva zze();

    abstract zzfva zzf();

    @Override // java.util.Map
    /* renamed from: zzg */
    public final zzfva entrySet() {
        zzfva zzfvaVar = this.zza;
        if (zzfvaVar == null) {
            zzfva zze = zze();
            this.zza = zze;
            return zze;
        }
        return zzfvaVar;
    }

    @Override // java.util.Map
    /* renamed from: zzh */
    public final zzfva keySet() {
        zzfva zzfvaVar = this.zzb;
        if (zzfvaVar == null) {
            zzfva zzf = zzf();
            this.zzb = zzf;
            return zzf;
        }
        return zzfvaVar;
    }
}
