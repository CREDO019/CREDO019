package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.PlaybackException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzfo extends zzew {
    public final zzfa zzb;
    public final int zzc;

    public zzfo(zzfa zzfaVar, int r2, int r3) {
        super(zzb(2008, 1));
        this.zzb = zzfaVar;
        this.zzc = 1;
    }

    public static zzfo zza(IOException iOException, zzfa zzfaVar, int r6) {
        String message = iOException.getMessage();
        boolean z = iOException instanceof SocketTimeoutException;
        int r2 = PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED;
        if (z) {
            r2 = PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT;
        } else if (iOException instanceof InterruptedIOException) {
            r2 = 1004;
        } else if (message != null && zzfrm.zza(message).matches("cleartext.*not permitted.*")) {
            r2 = PlaybackException.ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED;
        }
        if (r2 == 2007) {
            return new zzfn(iOException, zzfaVar);
        }
        return new zzfo(iOException, zzfaVar, r2, r6);
    }

    private static int zzb(int r1, int r2) {
        if (r1 == 2000) {
            if (r2 != 1) {
                return 2000;
            }
            return PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED;
        }
        return r1;
    }

    public zzfo(IOException iOException, zzfa zzfaVar, int r3, int r4) {
        super(iOException, zzb(r3, r4));
        this.zzb = zzfaVar;
        this.zzc = r4;
    }

    public zzfo(String str, zzfa zzfaVar, int r3, int r4) {
        super(str, zzb(r3, r4));
        this.zzb = zzfaVar;
        this.zzc = r4;
    }

    public zzfo(String str, IOException iOException, zzfa zzfaVar, int r4, int r5) {
        super(str, iOException, zzb(r4, r5));
        this.zzb = zzfaVar;
        this.zzc = r5;
    }
}
