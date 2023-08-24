package com.google.android.gms.internal.consent_sdk;

import java.util.Objects;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.0.0 */
/* loaded from: classes.dex */
public final class zzcg<T> implements zzcl<T> {
    private static final Object zza = new Object();
    private volatile zzcl<T> zzb;
    private volatile Object zzc = zza;

    private zzcg(zzcl<T> zzclVar) {
        this.zzb = zzclVar;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    public final T zzb() {
        T t = (T) this.zzc;
        Object obj = zza;
        if (t == obj) {
            synchronized (this) {
                t = this.zzc;
                if (t == obj) {
                    t = this.zzb.zzb();
                    Object obj2 = this.zzc;
                    if (obj2 != obj && !(obj2 instanceof zzcj) && obj2 != t) {
                        String valueOf = String.valueOf(obj2);
                        String valueOf2 = String.valueOf(t);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 118 + String.valueOf(valueOf2).length());
                        sb.append("Scoped provider was invoked recursively returning different results: ");
                        sb.append(valueOf);
                        sb.append(" & ");
                        sb.append(valueOf2);
                        sb.append(". This is likely due to a circular dependency.");
                        throw new IllegalStateException(sb.toString());
                    }
                    this.zzc = t;
                    this.zzb = null;
                }
            }
        }
        return t;
    }

    public static <P extends zzcl<T>, T> zzcl<T> zza(P p) {
        Objects.requireNonNull(p);
        return p instanceof zzcg ? p : new zzcg(p);
    }
}
