package com.google.android.gms.internal.ads;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfzt {
    private final InputStream zza;

    private zzfzt(InputStream inputStream) {
        this.zza = inputStream;
    }

    public static zzfzt zzb(byte[] bArr) {
        return new zzfzt(new ByteArrayInputStream(bArr));
    }

    public final zzgjt zza() throws IOException {
        try {
            return zzgjt.zzf(this.zza, zzgnz.zza());
        } finally {
            this.zza.close();
        }
    }
}
