package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaad {
    public static List zza(byte[] bArr) {
        byte b = bArr[11];
        byte b2 = bArr[10];
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(bArr);
        arrayList.add(zzc(zzb(((b & 255) << 8) | (b2 & 255))));
        arrayList.add(zzc(zzb(3840L)));
        return arrayList;
    }

    private static long zzb(long j) {
        return (j * C1856C.NANOS_PER_SECOND) / 48000;
    }

    private static byte[] zzc(long j) {
        return ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(j).array();
    }
}
