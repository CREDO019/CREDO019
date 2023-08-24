package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.PlaybackException;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfq extends zzfo {
    public final int zzd;
    public final String zze;
    public final Map zzf;
    public final byte[] zzg;

    public zzfq(int r9, String str, IOException iOException, Map map, zzfa zzfaVar, byte[] bArr) {
        super("Response code: " + r9, iOException, zzfaVar, PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS, 1);
        this.zzd = r9;
        this.zze = str;
        this.zzf = map;
        this.zzg = bArr;
    }
}
