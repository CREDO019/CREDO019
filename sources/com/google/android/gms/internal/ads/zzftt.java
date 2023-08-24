package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzftt implements zzfvr {
    @CheckForNull
    private transient Set zza;
    @CheckForNull
    private transient Collection zzb;
    @CheckForNull
    private transient Map zzc;

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfvr) {
            return zzu().equals(((zzfvr) obj).zzu());
        }
        return false;
    }

    public final int hashCode() {
        return zzu().hashCode();
    }

    public final String toString() {
        return zzu().toString();
    }

    abstract Collection zzi();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Iterator zzj() {
        throw null;
    }

    abstract Map zzm();

    abstract Set zzo();

    @Override // com.google.android.gms.internal.ads.zzfvr
    public boolean zzs(Object obj, Object obj2) {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzfvr
    public final Collection zzt() {
        Collection collection = this.zzb;
        if (collection == null) {
            Collection zzi = zzi();
            this.zzb = zzi;
            return zzi;
        }
        return collection;
    }

    @Override // com.google.android.gms.internal.ads.zzfvr
    public final Map zzu() {
        Map map = this.zzc;
        if (map == null) {
            Map zzm = zzm();
            this.zzc = zzm;
            return zzm;
        }
        return map;
    }

    public final Set zzv() {
        Set set = this.zza;
        if (set == null) {
            Set zzo = zzo();
            this.zza = zzo;
            return zzo;
        }
        return set;
    }
}
