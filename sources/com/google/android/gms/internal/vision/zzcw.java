package com.google.android.gms.internal.vision;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzcw<T> implements zzcu<T>, Serializable {
    @NullableDecl
    private transient T value;
    private final zzcu<T> zzli;
    private volatile transient boolean zzlj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcw(zzcu<T> zzcuVar) {
        this.zzli = (zzcu) zzct.checkNotNull(zzcuVar);
    }

    @Override // com.google.android.gms.internal.vision.zzcu
    public final T get() {
        if (!this.zzlj) {
            synchronized (this) {
                if (!this.zzlj) {
                    T t = this.zzli.get();
                    this.value = t;
                    this.zzlj = true;
                    return t;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        Object obj;
        if (this.zzlj) {
            String valueOf = String.valueOf(this.value);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("<supplier that returned ");
            sb.append(valueOf);
            sb.append(">");
            obj = sb.toString();
        } else {
            obj = this.zzli;
        }
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(valueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
