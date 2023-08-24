package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzanp implements Runnable {
    private zzanp() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzanp(zzano zzanoVar) {
    }

    @Override // java.lang.Runnable
    public final void run() {
        CountDownLatch countDownLatch;
        try {
            zzanq.zzc(MessageDigest.getInstance(MessageDigestAlgorithms.MD5));
            countDownLatch = zzanq.zzb;
        } catch (NoSuchAlgorithmException unused) {
            countDownLatch = zzanq.zzb;
        } catch (Throwable th) {
            zzanq.zzb.countDown();
            throw th;
        }
        countDownLatch.countDown();
    }
}
