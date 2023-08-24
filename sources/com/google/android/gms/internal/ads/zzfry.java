package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfry {
    private final String zza;
    private final zzfrx zzb;
    private zzfrx zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfry(String str, zzfrw zzfrwVar) {
        zzfrx zzfrxVar = new zzfrx(null);
        this.zzb = zzfrxVar;
        this.zzc = zzfrxVar;
        Objects.requireNonNull(str);
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzfrx zzfrxVar = this.zzb.zzb;
        String str = "";
        while (zzfrxVar != null) {
            Object obj = zzfrxVar.zza;
            sb.append(str);
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
            }
            zzfrxVar = zzfrxVar.zzb;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzfry zza(@CheckForNull Object obj) {
        zzfrx zzfrxVar = new zzfrx(null);
        this.zzc.zzb = zzfrxVar;
        this.zzc = zzfrxVar;
        zzfrxVar.zza = obj;
        return this;
    }
}
