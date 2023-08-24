package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzftw {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza(Collection collection, @CheckForNull Object obj) {
        Objects.requireNonNull(collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }
}
