package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfl extends zzep {
    private RandomAccessFile zza;
    private Uri zzb;
    private long zzc;
    private boolean zzd;

    public zzfl() {
        super(false);
    }

    @Override // com.google.android.gms.internal.ads.zzr
    public final int zza(byte[] bArr, int r7, int r8) throws zzfk {
        if (r8 == 0) {
            return 0;
        }
        if (this.zzc == 0) {
            return -1;
        }
        try {
            RandomAccessFile randomAccessFile = this.zza;
            int r1 = zzel.zza;
            int read = randomAccessFile.read(bArr, r7, (int) Math.min(this.zzc, r8));
            if (read > 0) {
                this.zzc -= read;
                zzg(read);
            }
            return read;
        } catch (IOException e) {
            throw new zzfk(e, 2000);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0087, code lost:
        if (r1 != false) goto L33;
     */
    @Override // com.google.android.gms.internal.ads.zzev
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long zzb(com.google.android.gms.internal.ads.zzfa r8) throws com.google.android.gms.internal.ads.zzfk {
        /*
            r7 = this;
            android.net.Uri r0 = r8.zza
            r7.zzb = r0
            r7.zzi(r8)
            r1 = 1
            r2 = 2000(0x7d0, float:2.803E-42)
            r3 = 2006(0x7d6, float:2.811E-42)
            java.io.RandomAccessFile r4 = new java.io.RandomAccessFile     // Catch: java.lang.RuntimeException -> L52 java.lang.SecurityException -> L59 java.io.FileNotFoundException -> L60
            java.lang.String r5 = r0.getPath()     // Catch: java.lang.RuntimeException -> L52 java.lang.SecurityException -> L59 java.io.FileNotFoundException -> L60
            java.util.Objects.requireNonNull(r5)
            java.lang.String r6 = "r"
            r4.<init>(r5, r6)     // Catch: java.lang.RuntimeException -> L52 java.lang.SecurityException -> L59 java.io.FileNotFoundException -> L60
            r7.zza = r4
            long r5 = r8.zzf     // Catch: java.io.IOException -> L4b
            r4.seek(r5)     // Catch: java.io.IOException -> L4b
            long r3 = r8.zzg     // Catch: java.io.IOException -> L4b
            r5 = -1
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 != 0) goto L32
            java.io.RandomAccessFile r0 = r7.zza     // Catch: java.io.IOException -> L4b
            long r3 = r0.length()     // Catch: java.io.IOException -> L4b
            long r5 = r8.zzf     // Catch: java.io.IOException -> L4b
            long r3 = r3 - r5
        L32:
            r7.zzc = r3     // Catch: java.io.IOException -> L4b
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 < 0) goto L42
            r7.zzd = r1
            r7.zzj(r8)
            long r0 = r7.zzc
            return r0
        L42:
            com.google.android.gms.internal.ads.zzfk r8 = new com.google.android.gms.internal.ads.zzfk
            r0 = 2008(0x7d8, float:2.814E-42)
            r1 = 0
            r8.<init>(r1, r1, r0)
            throw r8
        L4b:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzfk r0 = new com.google.android.gms.internal.ads.zzfk
            r0.<init>(r8, r2)
            throw r0
        L52:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzfk r0 = new com.google.android.gms.internal.ads.zzfk
            r0.<init>(r8, r2)
            throw r0
        L59:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzfk r0 = new com.google.android.gms.internal.ads.zzfk
            r0.<init>(r8, r3)
            throw r0
        L60:
            r8 = move-exception
            java.lang.String r2 = r0.getQuery()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L90
            java.lang.String r2 = r0.getFragment()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L90
            com.google.android.gms.internal.ads.zzfk r0 = new com.google.android.gms.internal.ads.zzfk
            int r1 = com.google.android.gms.internal.ads.zzel.zza
            r2 = 21
            r4 = 2005(0x7d5, float:2.81E-42)
            if (r1 < r2) goto L8a
            java.lang.Throwable r1 = r8.getCause()
            boolean r1 = com.google.android.gms.internal.ads.zzfj.zza(r1)
            if (r1 == 0) goto L8a
            goto L8c
        L8a:
            r3 = 2005(0x7d5, float:2.81E-42)
        L8c:
            r0.<init>(r8, r3)
            throw r0
        L90:
            com.google.android.gms.internal.ads.zzfk r2 = new com.google.android.gms.internal.ads.zzfk
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            java.lang.String r5 = r0.getPath()
            r3[r4] = r5
            java.lang.String r4 = r0.getQuery()
            r3[r1] = r4
            r1 = 2
            java.lang.String r0 = r0.getFragment()
            r3[r1] = r0
            java.lang.String r0 = "uri has query and/or fragment, which are not supported. Did you call Uri.parse() on a string containing '?' or '#'? Use Uri.fromFile(new File(path)) to avoid this. path=%s,query=%s,fragment=%s"
            java.lang.String r0 = java.lang.String.format(r0, r3)
            r1 = 1004(0x3ec, float:1.407E-42)
            r2.<init>(r0, r8, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfl.zzb(com.google.android.gms.internal.ads.zzfa):long");
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final Uri zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzd() throws zzfk {
        this.zzb = null;
        try {
            try {
                RandomAccessFile randomAccessFile = this.zza;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                this.zza = null;
                if (this.zzd) {
                    this.zzd = false;
                    zzh();
                }
            } catch (IOException e) {
                throw new zzfk(e, 2000);
            }
        } catch (Throwable th) {
            this.zza = null;
            if (this.zzd) {
                this.zzd = false;
                zzh();
            }
            throw th;
        }
    }
}
