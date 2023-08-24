package com.google.android.gms.internal.ads;

import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzfxe extends zzfye implements Runnable {
    @CheckForNull
    zzfyx zza;
    @CheckForNull
    Class zzb;
    @CheckForNull
    Object zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfxe(zzfyx zzfyxVar, Class cls, Object obj) {
        Objects.requireNonNull(zzfyxVar);
        this.zza = zzfyxVar;
        this.zzb = cls;
        Objects.requireNonNull(obj);
        this.zzc = obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0078  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r8 = this;
            com.google.android.gms.internal.ads.zzfyx r0 = r8.zza
            java.lang.Class r1 = r8.zzb
            java.lang.Object r2 = r8.zzc
            r3 = 1
            r4 = 0
            if (r0 != 0) goto Lc
            r5 = 1
            goto Ld
        Lc:
            r5 = 0
        Ld:
            if (r1 != 0) goto L11
            r6 = 1
            goto L12
        L11:
            r6 = 0
        L12:
            r5 = r5 | r6
            if (r2 != 0) goto L16
            goto L17
        L16:
            r3 = 0
        L17:
            r3 = r3 | r5
            if (r3 != 0) goto L9f
            boolean r3 = r8.isCancelled()
            if (r3 == 0) goto L22
            goto L9f
        L22:
            r3 = 0
            r8.zza = r3
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzfzq     // Catch: java.lang.Error -> L39 java.lang.RuntimeException -> L3b java.util.concurrent.ExecutionException -> L3e
            if (r4 == 0) goto L31
            r4 = r0
            com.google.android.gms.internal.ads.zzfzq r4 = (com.google.android.gms.internal.ads.zzfzq) r4     // Catch: java.lang.Error -> L39 java.lang.RuntimeException -> L3b java.util.concurrent.ExecutionException -> L3e
            java.lang.Throwable r4 = r4.zzp()     // Catch: java.lang.Error -> L39 java.lang.RuntimeException -> L3b java.util.concurrent.ExecutionException -> L3e
            goto L32
        L31:
            r4 = r3
        L32:
            if (r4 != 0) goto L3c
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzfyo.zzp(r0)     // Catch: java.lang.Error -> L39 java.lang.RuntimeException -> L3b java.util.concurrent.ExecutionException -> L3e
            goto L72
        L39:
            r4 = move-exception
            goto L3c
        L3b:
            r4 = move-exception
        L3c:
            r5 = r3
            goto L72
        L3e:
            r4 = move-exception
            java.lang.Throwable r5 = r4.getCause()
            if (r5 != 0) goto L70
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Future type "
            r6.append(r7)
            java.lang.Class r7 = r0.getClass()
            r6.append(r7)
            java.lang.String r7 = " threw "
            r6.append(r7)
            java.lang.Class r4 = r4.getClass()
            r6.append(r4)
            java.lang.String r4 = " without a cause"
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r5.<init>(r4)
        L70:
            r4 = r5
            goto L3c
        L72:
            if (r4 != 0) goto L78
            r8.zzd(r5)
            return
        L78:
            boolean r1 = r1.isInstance(r4)
            if (r1 == 0) goto L9c
            java.lang.Object r0 = r8.zzf(r2, r4)     // Catch: java.lang.Throwable -> L8a
            r8.zzb = r3
            r8.zzc = r3
            r8.zzg(r0)
            return
        L8a:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzfzf.zza(r0)     // Catch: java.lang.Throwable -> L96
            r8.zze(r0)     // Catch: java.lang.Throwable -> L96
            r8.zzb = r3
            r8.zzc = r3
            return
        L96:
            r0 = move-exception
            r8.zzb = r3
            r8.zzc = r3
            throw r0
        L9c:
            r8.zzt(r0)
        L9f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfxe.run():void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzfxf
    @CheckForNull
    public final String zza() {
        String str;
        zzfyx zzfyxVar = this.zza;
        Class cls = this.zzb;
        Object obj = this.zzc;
        String zza = super.zza();
        if (zzfyxVar != null) {
            str = "inputFuture=[" + zzfyxVar + "], ";
        } else {
            str = "";
        }
        if (cls == null || obj == null) {
            if (zza != null) {
                return str.concat(zza);
            }
            return null;
        }
        return str + "exceptionType=[" + cls + "], fallback=[" + obj + "]";
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    protected final void zzb() {
        zzs(this.zza);
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
    }

    abstract Object zzf(Object obj, Throwable th) throws Exception;

    abstract void zzg(Object obj);
}
