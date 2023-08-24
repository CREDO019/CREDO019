package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfzu {
    private final OutputStream zza;

    private zzfzu(OutputStream outputStream) {
        this.zza = outputStream;
    }

    public static zzfzu zzb(OutputStream outputStream) {
        return new zzfzu(outputStream);
    }

    public final void zza(zzgjt zzgjtVar) throws IOException {
        try {
            zzgjtVar.zzav(this.zza);
        } finally {
            this.zza.close();
        }
    }
}
