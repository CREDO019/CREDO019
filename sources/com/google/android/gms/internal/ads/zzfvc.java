package com.google.android.gms.internal.ads;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfvc extends zzfsy {
    final /* synthetic */ Iterator zza;
    final /* synthetic */ zzfsg zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfvc(Iterator it, zzfsg zzfsgVar) {
        this.zza = it;
        this.zzb = zzfsgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfsy
    @CheckForNull
    protected final Object zza() {
        while (this.zza.hasNext()) {
            Object next = this.zza.next();
            if (this.zzb.zza(next)) {
                return next;
            }
        }
        zzb();
        return null;
    }
}
