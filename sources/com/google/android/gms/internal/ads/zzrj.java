package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzrj implements zzsz {
    private final zzzm zza;
    private zzzf zzb;
    private zzzg zzc;

    public zzrj(zzzm zzzmVar) {
        this.zza = zzzmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzsz
    public final long zzb() {
        zzzg zzzgVar = this.zzc;
        if (zzzgVar != null) {
            return zzzgVar.zzf();
        }
        return -1L;
    }

    @Override // com.google.android.gms.internal.ads.zzsz
    public final void zzc() {
        zzzf zzzfVar = this.zzb;
        if (zzzfVar instanceof zzaen) {
            ((zzaen) zzzfVar).zze();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0049, code lost:
        if (r6.zzf() != r11) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006c, code lost:
        if (r6.zzf() != r11) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006f, code lost:
        r1 = false;
     */
    @Override // com.google.android.gms.internal.ads.zzsz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzd(com.google.android.gms.internal.ads.zzr r8, android.net.Uri r9, java.util.Map r10, long r11, long r13, com.google.android.gms.internal.ads.zzzi r15) throws java.io.IOException {
        /*
            r7 = this;
            com.google.android.gms.internal.ads.zzyv r6 = new com.google.android.gms.internal.ads.zzyv
            r0 = r6
            r1 = r8
            r2 = r11
            r4 = r13
            r0.<init>(r1, r2, r4)
            r7.zzc = r6
            com.google.android.gms.internal.ads.zzzf r8 = r7.zzb
            if (r8 == 0) goto L10
            return
        L10:
            com.google.android.gms.internal.ads.zzzm r8 = r7.zza
            com.google.android.gms.internal.ads.zzzf[] r8 = r8.zzb(r9, r10)
            int r10 = r8.length
            r13 = 0
            r14 = 1
            if (r10 != r14) goto L20
            r8 = r8[r13]
            r7.zzb = r8
            goto L7f
        L20:
            r0 = 0
        L21:
            if (r0 >= r10) goto L7b
            r1 = r8[r0]
            boolean r2 = r1.zzd(r6)     // Catch: java.lang.Throwable -> L4c java.io.EOFException -> L61
            if (r2 == 0) goto L3f
            r7.zzb = r1     // Catch: java.lang.Throwable -> L4c java.io.EOFException -> L61
            if (r1 != 0) goto L37
            long r0 = r6.zzf()
            int r10 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r10 != 0) goto L38
        L37:
            r13 = 1
        L38:
            com.google.android.gms.internal.ads.zzdd.zzf(r13)
            r6.zzj()
            goto L7b
        L3f:
            com.google.android.gms.internal.ads.zzzf r1 = r7.zzb
            if (r1 != 0) goto L71
            long r1 = r6.zzf()
            int r3 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r3 != 0) goto L6f
            goto L71
        L4c:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzzf r9 = r7.zzb
            if (r9 != 0) goto L59
            long r9 = r6.zzf()
            int r15 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r15 != 0) goto L5a
        L59:
            r13 = 1
        L5a:
            com.google.android.gms.internal.ads.zzdd.zzf(r13)
            r6.zzj()
            throw r8
        L61:
            com.google.android.gms.internal.ads.zzzf r1 = r7.zzb
            if (r1 != 0) goto L71
            long r1 = r6.zzf()
            int r3 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r3 != 0) goto L6f
            goto L71
        L6f:
            r1 = 0
            goto L72
        L71:
            r1 = 1
        L72:
            com.google.android.gms.internal.ads.zzdd.zzf(r1)
            r6.zzj()
            int r0 = r0 + 1
            goto L21
        L7b:
            com.google.android.gms.internal.ads.zzzf r10 = r7.zzb
            if (r10 == 0) goto L85
        L7f:
            com.google.android.gms.internal.ads.zzzf r8 = r7.zzb
            r8.zzb(r15)
            return
        L85:
            com.google.android.gms.internal.ads.zzuf r10 = new com.google.android.gms.internal.ads.zzuf
            java.lang.String r8 = com.google.android.gms.internal.ads.zzel.zzK(r8)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "None of the available extractors ("
            r11.append(r12)
            r11.append(r8)
            java.lang.String r8 = ") could read the stream."
            r11.append(r8)
            java.lang.String r8 = r11.toString()
            r10.<init>(r8, r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzrj.zzd(com.google.android.gms.internal.ads.zzr, android.net.Uri, java.util.Map, long, long, com.google.android.gms.internal.ads.zzzi):void");
    }

    @Override // com.google.android.gms.internal.ads.zzsz
    public final void zze() {
        if (this.zzb != null) {
            this.zzb = null;
        }
        this.zzc = null;
    }

    @Override // com.google.android.gms.internal.ads.zzsz
    public final int zza(zzaaf zzaafVar) throws IOException {
        zzzf zzzfVar = this.zzb;
        Objects.requireNonNull(zzzfVar);
        zzzg zzzgVar = this.zzc;
        Objects.requireNonNull(zzzgVar);
        return zzzfVar.zza(zzzgVar, zzaafVar);
    }

    @Override // com.google.android.gms.internal.ads.zzsz
    public final void zzf(long j, long j2) {
        zzzf zzzfVar = this.zzb;
        Objects.requireNonNull(zzzfVar);
        zzzfVar.zzc(j, j2);
    }
}
