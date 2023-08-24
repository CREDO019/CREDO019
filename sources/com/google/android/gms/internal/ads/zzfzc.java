package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzfxf;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfzc extends zzfxf.zzi implements Runnable {
    private final Runnable zza;

    public zzfzc(Runnable runnable) {
        Objects.requireNonNull(runnable);
        this.zza = runnable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzfxf
    public final String zza() {
        return "task=[" + this.zza + "]";
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zza.run();
        } catch (Error | RuntimeException e) {
            zze(e);
            throw e;
        }
    }
}
