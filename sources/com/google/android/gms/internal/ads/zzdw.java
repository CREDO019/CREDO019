package com.google.android.gms.internal.ads;

import android.media.MediaFormat;
import java.nio.ByteBuffer;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdw {
    public static void zza(MediaFormat mediaFormat, String str, int r3) {
        if (r3 != -1) {
            mediaFormat.setInteger(str, r3);
        }
    }

    public static void zzb(MediaFormat mediaFormat, List list) {
        for (int r0 = 0; r0 < list.size(); r0++) {
            mediaFormat.setByteBuffer("csd-" + r0, ByteBuffer.wrap((byte[]) list.get(r0)));
        }
    }
}
