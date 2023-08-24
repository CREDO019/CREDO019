package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzga extends zzfi {
    private static final Logger logger = Logger.getLogger(zzga.class.getName());
    private static final boolean zzsr = zzjp.zzij();
    zzgc zzss;

    private static long zzaa(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzbc(int r1) {
        if ((r1 & (-128)) == 0) {
            return 1;
        }
        if ((r1 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & r1) == 0) {
            return 3;
        }
        return (r1 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzbe(int r0) {
        return 4;
    }

    public static int zzbf(int r0) {
        return 4;
    }

    private static int zzbh(int r1) {
        return (r1 >> 31) ^ (r1 << 1);
    }

    public static zzga zze(byte[] bArr) {
        return new zzb(bArr, 0, bArr.length);
    }

    public static int zzl(boolean z) {
        return 1;
    }

    public static int zzt(float f) {
        return 4;
    }

    public static int zzw(long j) {
        int r0;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            r0 = 6;
            j >>>= 28;
        } else {
            r0 = 2;
        }
        if (((-2097152) & j) != 0) {
            r0 += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? r0 + 1 : r0;
    }

    public static int zzy(long j) {
        return 8;
    }

    public static int zzz(long j) {
        return 8;
    }

    public abstract void zza(int r1, long j) throws IOException;

    public abstract void zza(int r1, zzfh zzfhVar) throws IOException;

    public abstract void zza(int r1, zzic zzicVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(int r1, zzic zzicVar, zzir zzirVar) throws IOException;

    public abstract void zza(int r1, String str) throws IOException;

    public abstract void zza(int r1, boolean z) throws IOException;

    public abstract void zza(zzfh zzfhVar) throws IOException;

    public abstract void zzaw(int r1) throws IOException;

    public abstract void zzax(int r1) throws IOException;

    public abstract void zzaz(int r1) throws IOException;

    public abstract void zzb(int r1, zzfh zzfhVar) throws IOException;

    public abstract void zzb(zzic zzicVar) throws IOException;

    public abstract void zzc(byte b) throws IOException;

    public abstract void zzc(int r1, long j) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zze(byte[] bArr, int r2, int r3) throws IOException;

    public abstract int zzfg();

    public abstract void zzg(int r1, int r2) throws IOException;

    public abstract void zzh(int r1, int r2) throws IOException;

    public abstract void zzi(int r1, int r2) throws IOException;

    public abstract void zzk(int r1, int r2) throws IOException;

    public abstract void zzs(long j) throws IOException;

    public abstract void zzu(long j) throws IOException;

    public abstract void zzx(String str) throws IOException;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class zza extends IOException {
        zza() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zza(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        zza(java.lang.String r3, java.lang.Throwable r4) {
            /*
                r2 = this;
                java.lang.String r3 = java.lang.String.valueOf(r3)
                int r0 = r3.length()
                java.lang.String r1 = "CodedOutputStream was writing to a flat byte array and ran out of space.: "
                if (r0 == 0) goto L11
                java.lang.String r3 = r1.concat(r3)
                goto L16
            L11:
                java.lang.String r3 = new java.lang.String
                r3.<init>(r1)
            L16:
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzga.zza.<init>(java.lang.String, java.lang.Throwable):void");
        }
    }

    private zzga() {
    }

    public final void zzj(int r1, int r2) throws IOException {
        zzi(r1, zzbh(r2));
    }

    public final void zzb(int r1, long j) throws IOException {
        zza(r1, zzaa(j));
    }

    public final void zza(int r1, float f) throws IOException {
        zzk(r1, Float.floatToRawIntBits(f));
    }

    public final void zza(int r1, double d) throws IOException {
        zzc(r1, Double.doubleToRawLongBits(d));
    }

    public final void zzay(int r1) throws IOException {
        zzax(zzbh(r1));
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    static class zzb extends zzga {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zzb(byte[] bArr, int r4, int r5) {
            super();
            Objects.requireNonNull(bArr, "buffer");
            int r1 = r5 + 0;
            if ((r5 | 0 | (bArr.length - r1)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(r5)));
            }
            this.buffer = bArr;
            this.offset = 0;
            this.position = 0;
            this.limit = r1;
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzg(int r1, int r2) throws IOException {
            zzax((r1 << 3) | r2);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzh(int r2, int r3) throws IOException {
            zzg(r2, 0);
            zzaw(r3);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzi(int r2, int r3) throws IOException {
            zzg(r2, 0);
            zzax(r3);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzk(int r2, int r3) throws IOException {
            zzg(r2, 5);
            zzaz(r3);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(int r2, long j) throws IOException {
            zzg(r2, 0);
            zzs(j);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzc(int r2, long j) throws IOException {
            zzg(r2, 1);
            zzu(j);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(int r2, boolean z) throws IOException {
            zzg(r2, 0);
            zzc(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(int r2, String str) throws IOException {
            zzg(r2, 2);
            zzx(str);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(int r2, zzfh zzfhVar) throws IOException {
            zzg(r2, 2);
            zza(zzfhVar);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(zzfh zzfhVar) throws IOException {
            zzax(zzfhVar.size());
            zzfhVar.zza(this);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zze(byte[] bArr, int r2, int r3) throws IOException {
            zzax(r3);
            write(bArr, 0, r3);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        final void zza(int r3, zzic zzicVar, zzir zzirVar) throws IOException {
            zzg(r3, 2);
            zzet zzetVar = (zzet) zzicVar;
            int zzdl = zzetVar.zzdl();
            if (zzdl == -1) {
                zzdl = zzirVar.zzr(zzetVar);
                zzetVar.zzad(zzdl);
            }
            zzax(zzdl);
            zzirVar.zza(zzicVar, this.zzss);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(int r4, zzic zzicVar) throws IOException {
            zzg(1, 3);
            zzi(2, r4);
            zzg(3, 2);
            zzb(zzicVar);
            zzg(1, 4);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzb(int r4, zzfh zzfhVar) throws IOException {
            zzg(1, 3);
            zzi(2, r4);
            zza(3, zzfhVar);
            zzg(1, 4);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzb(zzic zzicVar) throws IOException {
            zzax(zzicVar.zzgf());
            zzicVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzc(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int r1 = this.position;
                this.position = r1 + 1;
                bArr[r1] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzaw(int r3) throws IOException {
            if (r3 >= 0) {
                zzax(r3);
            } else {
                zzs(r3);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzax(int r5) throws IOException {
            if (!zzga.zzsr || zzfa.zzdr() || zzfg() < 5) {
                while ((r5 & (-128)) != 0) {
                    try {
                        byte[] bArr = this.buffer;
                        int r1 = this.position;
                        this.position = r1 + 1;
                        bArr[r1] = (byte) ((r5 & 127) | 128);
                        r5 >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                    }
                }
                byte[] bArr2 = this.buffer;
                int r12 = this.position;
                this.position = r12 + 1;
                bArr2[r12] = (byte) r5;
            } else if ((r5 & (-128)) == 0) {
                byte[] bArr3 = this.buffer;
                int r13 = this.position;
                this.position = r13 + 1;
                zzjp.zza(bArr3, r13, (byte) r5);
            } else {
                byte[] bArr4 = this.buffer;
                int r14 = this.position;
                this.position = r14 + 1;
                zzjp.zza(bArr4, r14, (byte) (r5 | 128));
                int r52 = r5 >>> 7;
                if ((r52 & (-128)) == 0) {
                    byte[] bArr5 = this.buffer;
                    int r15 = this.position;
                    this.position = r15 + 1;
                    zzjp.zza(bArr5, r15, (byte) r52);
                    return;
                }
                byte[] bArr6 = this.buffer;
                int r16 = this.position;
                this.position = r16 + 1;
                zzjp.zza(bArr6, r16, (byte) (r52 | 128));
                int r53 = r52 >>> 7;
                if ((r53 & (-128)) == 0) {
                    byte[] bArr7 = this.buffer;
                    int r17 = this.position;
                    this.position = r17 + 1;
                    zzjp.zza(bArr7, r17, (byte) r53);
                    return;
                }
                byte[] bArr8 = this.buffer;
                int r18 = this.position;
                this.position = r18 + 1;
                zzjp.zza(bArr8, r18, (byte) (r53 | 128));
                int r54 = r53 >>> 7;
                if ((r54 & (-128)) == 0) {
                    byte[] bArr9 = this.buffer;
                    int r19 = this.position;
                    this.position = r19 + 1;
                    zzjp.zza(bArr9, r19, (byte) r54);
                    return;
                }
                byte[] bArr10 = this.buffer;
                int r110 = this.position;
                this.position = r110 + 1;
                zzjp.zza(bArr10, r110, (byte) (r54 | 128));
                byte[] bArr11 = this.buffer;
                int r111 = this.position;
                this.position = r111 + 1;
                zzjp.zza(bArr11, r111, (byte) (r54 >>> 7));
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzaz(int r5) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int r1 = this.position;
                int r2 = r1 + 1;
                this.position = r2;
                bArr[r1] = (byte) r5;
                int r12 = r2 + 1;
                this.position = r12;
                bArr[r2] = (byte) (r5 >> 8);
                int r22 = r12 + 1;
                this.position = r22;
                bArr[r12] = (byte) (r5 >> 16);
                this.position = r22 + 1;
                bArr[r22] = (byte) (r5 >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzs(long j) throws IOException {
            if (zzga.zzsr && zzfg() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int r6 = this.position;
                    this.position = r6 + 1;
                    zzjp.zza(bArr, r6, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int r1 = this.position;
                this.position = r1 + 1;
                zzjp.zza(bArr2, r1, (byte) j);
                return;
            }
            while ((j & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int r62 = this.position;
                    this.position = r62 + 1;
                    bArr3[r62] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int r12 = this.position;
            this.position = r12 + 1;
            bArr4[r12] = (byte) j;
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzu(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int r1 = this.position;
                int r2 = r1 + 1;
                this.position = r2;
                bArr[r1] = (byte) j;
                int r12 = r2 + 1;
                this.position = r12;
                bArr[r2] = (byte) (j >> 8);
                int r22 = r12 + 1;
                this.position = r22;
                bArr[r12] = (byte) (j >> 16);
                int r13 = r22 + 1;
                this.position = r13;
                bArr[r22] = (byte) (j >> 24);
                int r23 = r13 + 1;
                this.position = r23;
                bArr[r13] = (byte) (j >> 32);
                int r14 = r23 + 1;
                this.position = r14;
                bArr[r23] = (byte) (j >> 40);
                int r24 = r14 + 1;
                this.position = r24;
                bArr[r14] = (byte) (j >> 48);
                this.position = r24 + 1;
                bArr[r24] = (byte) (j >> 56);
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        private final void write(byte[] bArr, int r5, int r6) throws IOException {
            try {
                System.arraycopy(bArr, r5, this.buffer, this.position, r6);
                this.position += r6;
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(r6)), e);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzfi
        public final void zzc(byte[] bArr, int r2, int r3) throws IOException {
            write(bArr, r2, r3);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzx(String str) throws IOException {
            int r0 = this.position;
            try {
                int zzbc = zzbc(str.length() * 3);
                int zzbc2 = zzbc(str.length());
                if (zzbc2 == zzbc) {
                    int r1 = r0 + zzbc2;
                    this.position = r1;
                    int zza = zzjs.zza(str, this.buffer, r1, zzfg());
                    this.position = r0;
                    zzax((zza - r0) - zzbc2);
                    this.position = zza;
                    return;
                }
                zzax(zzjs.zza(str));
                this.position = zzjs.zza(str, this.buffer, this.position, zzfg());
            } catch (zzjv e) {
                this.position = r0;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zza(e2);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final int zzfg() {
            return this.limit - this.position;
        }
    }

    public final void zzt(long j) throws IOException {
        zzs(zzaa(j));
    }

    public final void zzs(float f) throws IOException {
        zzaz(Float.floatToRawIntBits(f));
    }

    public final void zza(double d) throws IOException {
        zzu(Double.doubleToRawLongBits(d));
    }

    public final void zzk(boolean z) throws IOException {
        zzc(z ? (byte) 1 : (byte) 0);
    }

    public static int zzl(int r0, int r1) {
        return zzba(r0) + zzbb(r1);
    }

    public static int zzm(int r0, int r1) {
        return zzba(r0) + zzbc(r1);
    }

    public static int zzn(int r0, int r1) {
        return zzba(r0) + zzbc(zzbh(r1));
    }

    public static int zzo(int r0, int r1) {
        return zzba(r0) + 4;
    }

    public static int zzp(int r0, int r1) {
        return zzba(r0) + 4;
    }

    public static int zzd(int r0, long j) {
        return zzba(r0) + zzw(j);
    }

    public static int zze(int r0, long j) {
        return zzba(r0) + zzw(j);
    }

    public static int zzf(int r0, long j) {
        return zzba(r0) + zzw(zzaa(j));
    }

    public static int zzg(int r0, long j) {
        return zzba(r0) + 8;
    }

    public static int zzh(int r0, long j) {
        return zzba(r0) + 8;
    }

    public static int zzb(int r0, float f) {
        return zzba(r0) + 4;
    }

    public static int zzb(int r0, double d) {
        return zzba(r0) + 8;
    }

    public static int zzb(int r0, boolean z) {
        return zzba(r0) + 1;
    }

    public static int zzq(int r0, int r1) {
        return zzba(r0) + zzbb(r1);
    }

    public static int zzb(int r0, String str) {
        return zzba(r0) + zzy(str);
    }

    public static int zzc(int r1, zzfh zzfhVar) {
        int zzba = zzba(r1);
        int size = zzfhVar.size();
        return zzba + zzbc(size) + size;
    }

    public static int zza(int r1, zzhh zzhhVar) {
        int zzba = zzba(r1);
        int zzgf = zzhhVar.zzgf();
        return zzba + zzbc(zzgf) + zzgf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int r0, zzic zzicVar, zzir zzirVar) {
        return zzba(r0) + zza(zzicVar, zzirVar);
    }

    public static int zzb(int r2, zzic zzicVar) {
        return (zzba(1) << 1) + zzm(2, r2) + zzba(3) + zzc(zzicVar);
    }

    public static int zzd(int r2, zzfh zzfhVar) {
        return (zzba(1) << 1) + zzm(2, r2) + zzc(3, zzfhVar);
    }

    public static int zzb(int r2, zzhh zzhhVar) {
        return (zzba(1) << 1) + zzm(2, r2) + zza(3, zzhhVar);
    }

    public static int zzba(int r0) {
        return zzbc(r0 << 3);
    }

    public static int zzbb(int r0) {
        if (r0 >= 0) {
            return zzbc(r0);
        }
        return 10;
    }

    public static int zzbd(int r0) {
        return zzbc(zzbh(r0));
    }

    public static int zzv(long j) {
        return zzw(j);
    }

    public static int zzx(long j) {
        return zzw(zzaa(j));
    }

    public static int zzbg(int r0) {
        return zzbb(r0);
    }

    public static int zzy(String str) {
        int length;
        try {
            length = zzjs.zza(str);
        } catch (zzjv unused) {
            length = str.getBytes(zzgt.UTF_8).length;
        }
        return zzbc(length) + length;
    }

    public static int zza(zzhh zzhhVar) {
        int zzgf = zzhhVar.zzgf();
        return zzbc(zzgf) + zzgf;
    }

    public static int zzb(zzfh zzfhVar) {
        int size = zzfhVar.size();
        return zzbc(size) + size;
    }

    public static int zzf(byte[] bArr) {
        int length = bArr.length;
        return zzbc(length) + length;
    }

    public static int zzc(zzic zzicVar) {
        int zzgf = zzicVar.zzgf();
        return zzbc(zzgf) + zzgf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzic zzicVar, zzir zzirVar) {
        zzet zzetVar = (zzet) zzicVar;
        int zzdl = zzetVar.zzdl();
        if (zzdl == -1) {
            zzdl = zzirVar.zzr(zzetVar);
            zzetVar.zzad(zzdl);
        }
        return zzbc(zzdl) + zzdl;
    }

    public final void zzfh() {
        if (zzfg() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zza(String str, zzjv zzjvVar) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzjvVar);
        byte[] bytes = str.getBytes(zzgt.UTF_8);
        try {
            zzax(bytes.length);
            zzc(bytes, 0, bytes.length);
        } catch (zza e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zza(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzc(int r2, zzic zzicVar, zzir zzirVar) {
        int zzba = zzba(r2) << 1;
        zzet zzetVar = (zzet) zzicVar;
        int zzdl = zzetVar.zzdl();
        if (zzdl == -1) {
            zzdl = zzirVar.zzr(zzetVar);
            zzetVar.zzad(zzdl);
        }
        return zzba + zzdl;
    }

    @Deprecated
    public static int zzd(zzic zzicVar) {
        return zzicVar.zzgf();
    }

    @Deprecated
    public static int zzbi(int r0) {
        return zzbc(r0);
    }
}
