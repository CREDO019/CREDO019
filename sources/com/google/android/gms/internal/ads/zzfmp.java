package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfmp {
    public static zzfny zza(Context context, int r9, int r10, String str, String str2, String str3, zzfmf zzfmfVar) {
        return new zzfmo(context, 1, r10, str, str2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, zzfmfVar).zzb(50000);
    }
}
