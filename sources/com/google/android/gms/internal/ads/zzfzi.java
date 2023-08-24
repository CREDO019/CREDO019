package com.google.android.gms.internal.ads;

import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfzi implements Runnable {
    @CheckForNull
    zzfzk zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfzi(zzfzk zzfzkVar) {
        this.zza = zzfzkVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0005, code lost:
        r1 = r0.zza;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r10 = this;
            com.google.android.gms.internal.ads.zzfzk r0 = r10.zza
            if (r0 != 0) goto L5
            return
        L5:
            com.google.android.gms.internal.ads.zzfyx r1 = com.google.android.gms.internal.ads.zzfzk.zzf(r0)
            if (r1 != 0) goto Lc
            return
        Lc:
            r2 = 0
            r10.zza = r2
            boolean r3 = r1.isDone()
            if (r3 == 0) goto L19
            r0.zzt(r1)
            return
        L19:
            r3 = 1
            java.util.concurrent.ScheduledFuture r4 = com.google.android.gms.internal.ads.zzfzk.zzw(r0)     // Catch: java.lang.Throwable -> L7b
            com.google.android.gms.internal.ads.zzfzk.zzx(r0, r2)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r5 = "Timed out"
            if (r4 == 0) goto L52
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L50
            long r6 = r4.getDelay(r6)     // Catch: java.lang.Throwable -> L50
            long r6 = java.lang.Math.abs(r6)     // Catch: java.lang.Throwable -> L50
            r8 = 10
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 <= 0) goto L52
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L50
            r4.<init>()     // Catch: java.lang.Throwable -> L50
            r4.append(r5)     // Catch: java.lang.Throwable -> L50
            java.lang.String r8 = " (timeout delayed by "
            r4.append(r8)     // Catch: java.lang.Throwable -> L50
            r4.append(r6)     // Catch: java.lang.Throwable -> L50
            java.lang.String r6 = " ms after scheduled time)"
            r4.append(r6)     // Catch: java.lang.Throwable -> L50
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L50
            r5 = r4
            goto L52
        L50:
            r4 = move-exception
            goto L72
        L52:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L50
            r4.<init>()     // Catch: java.lang.Throwable -> L50
            r4.append(r5)     // Catch: java.lang.Throwable -> L50
            java.lang.String r6 = ": "
            r4.append(r6)     // Catch: java.lang.Throwable -> L50
            r4.append(r1)     // Catch: java.lang.Throwable -> L50
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L50
            com.google.android.gms.internal.ads.zzfzj r5 = new com.google.android.gms.internal.ads.zzfzj     // Catch: java.lang.Throwable -> L7b
            r5.<init>(r4, r2)     // Catch: java.lang.Throwable -> L7b
            r0.zze(r5)     // Catch: java.lang.Throwable -> L7b
            r1.cancel(r3)
            return
        L72:
            com.google.android.gms.internal.ads.zzfzj r6 = new com.google.android.gms.internal.ads.zzfzj     // Catch: java.lang.Throwable -> L7b
            r6.<init>(r5, r2)     // Catch: java.lang.Throwable -> L7b
            r0.zze(r6)     // Catch: java.lang.Throwable -> L7b
            throw r4     // Catch: java.lang.Throwable -> L7b
        L7b:
            r0 = move-exception
            r1.cancel(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfzi.run():void");
    }
}
