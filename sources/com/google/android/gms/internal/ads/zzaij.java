package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaij {
    public final int zza;
    public final String zzb;
    public final List zzc;
    public final byte[] zzd;

    public zzaij(int r1, String str, List list, byte[] bArr) {
        this.zza = r1;
        this.zzb = str;
        this.zzc = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.zzd = bArr;
    }
}
