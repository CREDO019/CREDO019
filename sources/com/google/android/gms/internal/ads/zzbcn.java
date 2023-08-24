package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.ParametersAreNonnullByDefault;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public abstract class zzbcn {
    private static MessageDigest zzb;
    protected final Object zza = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] zzb(String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public final MessageDigest zza() {
        synchronized (this.zza) {
            MessageDigest messageDigest = zzb;
            if (messageDigest != null) {
                return messageDigest;
            }
            for (int r1 = 0; r1 < 2; r1++) {
                try {
                    zzb = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return zzb;
        }
    }
}
