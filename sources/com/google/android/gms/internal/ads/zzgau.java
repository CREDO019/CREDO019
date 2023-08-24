package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgau {
    private final Object zza;
    private final byte[] zzb;
    private final zzgab zzc;
    private final int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgau(Object obj, byte[] bArr, int r3, int r4, int r5, zzgab zzgabVar) {
        this.zza = obj;
        this.zzb = Arrays.copyOf(bArr, bArr.length);
        this.zzd = r4;
        this.zzc = zzgabVar;
    }

    public final Object zza() {
        return this.zza;
    }

    public final byte[] zzb() {
        byte[] bArr = this.zzb;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public final int zzc() {
        return this.zzd;
    }
}
