package com.google.android.gms.internal.clearcut;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public abstract class zzbn extends zzba {
    private static final Logger logger = Logger.getLogger(zzbn.class.getName());
    private static final boolean zzfy = zzfd.zzed();
    zzbp zzfz;

    /* loaded from: classes2.dex */
    static class zza extends zzbn {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int r5, int r6) {
            super();
            Objects.requireNonNull(bArr, "buffer");
            int r2 = r5 + r6;
            if ((r5 | r6 | (bArr.length - r2)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(r5), Integer.valueOf(r6)));
            }
            this.buffer = bArr;
            this.offset = r5;
            this.position = r5;
            this.limit = r2;
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public void flush() {
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void write(byte[] bArr, int r5, int r6) throws IOException {
            try {
                System.arraycopy(bArr, r5, this.buffer, this.position, r6);
                this.position += r6;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(r6)), e);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int r1 = this.position;
                this.position = r1 + 1;
                bArr[r1] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, long j) throws IOException {
            zzb(r2, 0);
            zzb(j);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzbb zzbbVar) throws IOException {
            zzb(r2, 2);
            zza(zzbbVar);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzdo zzdoVar) throws IOException {
            zzb(r2, 2);
            zzb(zzdoVar);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        final void zza(int r3, zzdo zzdoVar, zzef zzefVar) throws IOException {
            zzb(r3, 2);
            zzas zzasVar = (zzas) zzdoVar;
            int zzs = zzasVar.zzs();
            if (zzs == -1) {
                zzs = zzefVar.zzm(zzasVar);
                zzasVar.zzf(zzs);
            }
            zzo(zzs);
            zzefVar.zza(zzdoVar, this.zzfz);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, String str) throws IOException {
            zzb(r2, 2);
            zzg(str);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(zzbb zzbbVar) throws IOException {
            zzo(zzbbVar.size());
            zzbbVar.zza(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        final void zza(zzdo zzdoVar, zzef zzefVar) throws IOException {
            zzas zzasVar = (zzas) zzdoVar;
            int zzs = zzasVar.zzs();
            if (zzs == -1) {
                zzs = zzefVar.zzm(zzasVar);
                zzasVar.zzf(zzs);
            }
            zzo(zzs);
            zzefVar.zza(zzdoVar, this.zzfz);
        }

        @Override // com.google.android.gms.internal.clearcut.zzba
        public final void zza(byte[] bArr, int r2, int r3) throws IOException {
            write(bArr, r2, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final int zzag() {
            return this.limit - this.position;
        }

        public final int zzai() {
            return this.position - this.offset;
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r1, int r2) throws IOException {
            zzo((r1 << 3) | r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzbb zzbbVar) throws IOException {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, zzbbVar);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzdo zzdoVar) throws IOException {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, zzdoVar);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r2, boolean z) throws IOException {
            zzb(r2, 0);
            zza(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(long j) throws IOException {
            if (zzbn.zzfy && zzag() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int r6 = this.position;
                    this.position = r6 + 1;
                    zzfd.zza(bArr, r6, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int r1 = this.position;
                this.position = r1 + 1;
                zzfd.zza(bArr2, r1, (byte) j);
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
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int r12 = this.position;
            this.position = r12 + 1;
            bArr4[r12] = (byte) j;
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(zzdo zzdoVar) throws IOException {
            zzo(zzdoVar.zzas());
            zzdoVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, int r3) throws IOException {
            zzb(r2, 0);
            zzn(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, long j) throws IOException {
            zzb(r2, 1);
            zzd(j);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(int r2, int r3) throws IOException {
            zzb(r2, 0);
            zzo(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(long j) throws IOException {
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
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(byte[] bArr, int r2, int r3) throws IOException {
            zzo(r3);
            write(bArr, 0, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzf(int r2, int r3) throws IOException {
            zzb(r2, 5);
            zzq(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzg(String str) throws IOException {
            int r0 = this.position;
            try {
                int zzt = zzt(str.length() * 3);
                int zzt2 = zzt(str.length());
                if (zzt2 != zzt) {
                    zzo(zzff.zza(str));
                    this.position = zzff.zza(str, this.buffer, this.position, zzag());
                    return;
                }
                int r1 = r0 + zzt2;
                this.position = r1;
                int zza = zzff.zza(str, this.buffer, r1, zzag());
                this.position = r0;
                zzo((zza - r0) - zzt2);
                this.position = zza;
            } catch (zzfi e) {
                this.position = r0;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzc(e2);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzn(int r3) throws IOException {
            if (r3 >= 0) {
                zzo(r3);
            } else {
                zzb(r3);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzo(int r5) throws IOException {
            if (zzbn.zzfy && zzag() >= 10) {
                while ((r5 & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int r1 = this.position;
                    this.position = r1 + 1;
                    zzfd.zza(bArr, r1, (byte) ((r5 & 127) | 128));
                    r5 >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int r12 = this.position;
                this.position = r12 + 1;
                zzfd.zza(bArr2, r12, (byte) r5);
                return;
            }
            while ((r5 & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int r13 = this.position;
                    this.position = r13 + 1;
                    bArr3[r13] = (byte) ((r5 & 127) | 128);
                    r5 >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int r14 = this.position;
            this.position = r14 + 1;
            bArr4[r14] = (byte) r5;
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzq(int r5) throws IOException {
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
                bArr[r22] = r5 >> Ascii.CAN;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class zzb extends zza {
        private final ByteBuffer zzga;
        private int zzgb;

        zzb(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzga = byteBuffer;
            this.zzgb = byteBuffer.position();
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn.zza, com.google.android.gms.internal.clearcut.zzbn
        public final void flush() {
            this.zzga.position(this.zzgb + zzai());
        }
    }

    /* loaded from: classes2.dex */
    public static class zzc extends IOException {
        zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        zzc(java.lang.String r3) {
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
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzbn.zzc.<init>(java.lang.String):void");
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        zzc(java.lang.String r3, java.lang.Throwable r4) {
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzbn.zzc.<init>(java.lang.String, java.lang.Throwable):void");
        }

        zzc(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class zzd extends zzbn {
        private final int zzgb;
        private final ByteBuffer zzgc;
        private final ByteBuffer zzgd;

        zzd(ByteBuffer byteBuffer) {
            super();
            this.zzgc = byteBuffer;
            this.zzgd = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzgb = byteBuffer.position();
        }

        private final void zzi(String str) throws IOException {
            try {
                zzff.zza(str, this.zzgd);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(e);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void flush() {
            this.zzgc.position(this.zzgd.position());
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void write(byte[] bArr, int r3, int r4) throws IOException {
            try {
                this.zzgd.put(bArr, r3, r4);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(e);
            } catch (BufferOverflowException e2) {
                throw new zzc(e2);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(byte b) throws IOException {
            try {
                this.zzgd.put(b);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, long j) throws IOException {
            zzb(r2, 0);
            zzb(j);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzbb zzbbVar) throws IOException {
            zzb(r2, 2);
            zza(zzbbVar);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzdo zzdoVar) throws IOException {
            zzb(r2, 2);
            zzb(zzdoVar);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        final void zza(int r2, zzdo zzdoVar, zzef zzefVar) throws IOException {
            zzb(r2, 2);
            zza(zzdoVar, zzefVar);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, String str) throws IOException {
            zzb(r2, 2);
            zzg(str);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(zzbb zzbbVar) throws IOException {
            zzo(zzbbVar.size());
            zzbbVar.zza(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        final void zza(zzdo zzdoVar, zzef zzefVar) throws IOException {
            zzas zzasVar = (zzas) zzdoVar;
            int zzs = zzasVar.zzs();
            if (zzs == -1) {
                zzs = zzefVar.zzm(zzasVar);
                zzasVar.zzf(zzs);
            }
            zzo(zzs);
            zzefVar.zza(zzdoVar, this.zzfz);
        }

        @Override // com.google.android.gms.internal.clearcut.zzba
        public final void zza(byte[] bArr, int r2, int r3) throws IOException {
            write(bArr, r2, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final int zzag() {
            return this.zzgd.remaining();
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r1, int r2) throws IOException {
            zzo((r1 << 3) | r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzbb zzbbVar) throws IOException {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, zzbbVar);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzdo zzdoVar) throws IOException {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, zzdoVar);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r2, boolean z) throws IOException {
            zzb(r2, 0);
            zza(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(long j) throws IOException {
            while (((-128) & j) != 0) {
                try {
                    this.zzgd.put((byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                } catch (BufferOverflowException e) {
                    throw new zzc(e);
                }
            }
            this.zzgd.put((byte) j);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(zzdo zzdoVar) throws IOException {
            zzo(zzdoVar.zzas());
            zzdoVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, int r3) throws IOException {
            zzb(r2, 0);
            zzn(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, long j) throws IOException {
            zzb(r2, 1);
            zzd(j);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(int r2, int r3) throws IOException {
            zzb(r2, 0);
            zzo(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(long j) throws IOException {
            try {
                this.zzgd.putLong(j);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(byte[] bArr, int r2, int r3) throws IOException {
            zzo(r3);
            write(bArr, 0, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzf(int r2, int r3) throws IOException {
            zzb(r2, 5);
            zzq(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzg(String str) throws IOException {
            int position = this.zzgd.position();
            try {
                int zzt = zzt(str.length() * 3);
                int zzt2 = zzt(str.length());
                if (zzt2 != zzt) {
                    zzo(zzff.zza(str));
                    zzi(str);
                    return;
                }
                int position2 = this.zzgd.position() + zzt2;
                this.zzgd.position(position2);
                zzi(str);
                int position3 = this.zzgd.position();
                this.zzgd.position(position);
                zzo(position3 - position2);
                this.zzgd.position(position3);
            } catch (zzfi e) {
                this.zzgd.position(position);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc(e2);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzn(int r3) throws IOException {
            if (r3 >= 0) {
                zzo(r3);
            } else {
                zzb(r3);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzo(int r3) throws IOException {
            while ((r3 & (-128)) != 0) {
                try {
                    this.zzgd.put((byte) ((r3 & 127) | 128));
                    r3 >>>= 7;
                } catch (BufferOverflowException e) {
                    throw new zzc(e);
                }
            }
            this.zzgd.put((byte) r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzq(int r2) throws IOException {
            try {
                this.zzgd.putInt(r2);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class zze extends zzbn {
        private final ByteBuffer zzgc;
        private final ByteBuffer zzgd;
        private final long zzge;
        private final long zzgf;
        private final long zzgg;
        private final long zzgh;
        private long zzgi;

        zze(ByteBuffer byteBuffer) {
            super();
            this.zzgc = byteBuffer;
            this.zzgd = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            long zzb = zzfd.zzb(byteBuffer);
            this.zzge = zzb;
            long position = byteBuffer.position() + zzb;
            this.zzgf = position;
            long limit = zzb + byteBuffer.limit();
            this.zzgg = limit;
            this.zzgh = limit - 10;
            this.zzgi = position;
        }

        private final void zzk(long j) {
            this.zzgd.position((int) (j - this.zzge));
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void flush() {
            this.zzgc.position((int) (this.zzgi - this.zzge));
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void write(byte[] bArr, int r13, int r14) throws IOException {
            if (bArr != null && r13 >= 0 && r14 >= 0 && bArr.length - r14 >= r13) {
                long j = r14;
                long j2 = this.zzgi;
                if (this.zzgg - j >= j2) {
                    zzfd.zza(bArr, r13, j2, j);
                    this.zzgi += j;
                    return;
                }
            }
            Objects.requireNonNull(bArr, "value");
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(r14)));
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(byte b) throws IOException {
            long j = this.zzgi;
            if (j >= this.zzgg) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), 1));
            }
            this.zzgi = 1 + j;
            zzfd.zza(j, b);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, long j) throws IOException {
            zzb(r2, 0);
            zzb(j);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzbb zzbbVar) throws IOException {
            zzb(r2, 2);
            zza(zzbbVar);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzdo zzdoVar) throws IOException {
            zzb(r2, 2);
            zzb(zzdoVar);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        final void zza(int r2, zzdo zzdoVar, zzef zzefVar) throws IOException {
            zzb(r2, 2);
            zza(zzdoVar, zzefVar);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, String str) throws IOException {
            zzb(r2, 2);
            zzg(str);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(zzbb zzbbVar) throws IOException {
            zzo(zzbbVar.size());
            zzbbVar.zza(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        final void zza(zzdo zzdoVar, zzef zzefVar) throws IOException {
            zzas zzasVar = (zzas) zzdoVar;
            int zzs = zzasVar.zzs();
            if (zzs == -1) {
                zzs = zzefVar.zzm(zzasVar);
                zzasVar.zzf(zzs);
            }
            zzo(zzs);
            zzefVar.zza(zzdoVar, this.zzfz);
        }

        @Override // com.google.android.gms.internal.clearcut.zzba
        public final void zza(byte[] bArr, int r2, int r3) throws IOException {
            write(bArr, r2, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final int zzag() {
            return (int) (this.zzgg - this.zzgi);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r1, int r2) throws IOException {
            zzo((r1 << 3) | r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzbb zzbbVar) throws IOException {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, zzbbVar);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzdo zzdoVar) throws IOException {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, zzdoVar);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r2, boolean z) throws IOException {
            zzb(r2, 0);
            zza(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(long j) throws IOException {
            long j2;
            if (this.zzgi <= this.zzgh) {
                while (true) {
                    int r2 = ((j & (-128)) > 0L ? 1 : ((j & (-128)) == 0L ? 0 : -1));
                    j2 = this.zzgi;
                    if (r2 == 0) {
                        break;
                    }
                    this.zzgi = j2 + 1;
                    zzfd.zza(j2, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
            } else {
                while (true) {
                    j2 = this.zzgi;
                    if (j2 >= this.zzgg) {
                        throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), 1));
                    }
                    if ((j & (-128)) == 0) {
                        break;
                    }
                    this.zzgi = j2 + 1;
                    zzfd.zza(j2, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
            }
            this.zzgi = 1 + j2;
            zzfd.zza(j2, (byte) j);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(zzdo zzdoVar) throws IOException {
            zzo(zzdoVar.zzas());
            zzdoVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, int r3) throws IOException {
            zzb(r2, 0);
            zzn(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, long j) throws IOException {
            zzb(r2, 1);
            zzd(j);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(int r2, int r3) throws IOException {
            zzb(r2, 0);
            zzo(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(long j) throws IOException {
            this.zzgd.putLong((int) (this.zzgi - this.zzge), j);
            this.zzgi += 8;
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(byte[] bArr, int r2, int r3) throws IOException {
            zzo(r3);
            write(bArr, 0, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzf(int r2, int r3) throws IOException {
            zzb(r2, 5);
            zzq(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzg(String str) throws IOException {
            long j = this.zzgi;
            try {
                int zzt = zzt(str.length() * 3);
                int zzt2 = zzt(str.length());
                if (zzt2 != zzt) {
                    int zza = zzff.zza(str);
                    zzo(zza);
                    zzk(this.zzgi);
                    zzff.zza(str, this.zzgd);
                    this.zzgi += zza;
                    return;
                }
                int r2 = ((int) (this.zzgi - this.zzge)) + zzt2;
                this.zzgd.position(r2);
                zzff.zza(str, this.zzgd);
                int position = this.zzgd.position() - r2;
                zzo(position);
                this.zzgi += position;
            } catch (zzfi e) {
                this.zzgi = j;
                zzk(j);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc(e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzc(e3);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzn(int r3) throws IOException {
            if (r3 >= 0) {
                zzo(r3);
            } else {
                zzb(r3);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzo(int r8) throws IOException {
            long j;
            if (this.zzgi <= this.zzgh) {
                while ((r8 & (-128)) != 0) {
                    long j2 = this.zzgi;
                    this.zzgi = j2 + 1;
                    zzfd.zza(j2, (byte) ((r8 & 127) | 128));
                    r8 >>>= 7;
                }
                j = this.zzgi;
            } else {
                while (true) {
                    j = this.zzgi;
                    if (j >= this.zzgg) {
                        throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), 1));
                    }
                    if ((r8 & (-128)) == 0) {
                        break;
                    }
                    this.zzgi = j + 1;
                    zzfd.zza(j, (byte) ((r8 & 127) | 128));
                    r8 >>>= 7;
                }
            }
            this.zzgi = 1 + j;
            zzfd.zza(j, (byte) r8);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzq(int r6) throws IOException {
            this.zzgd.putInt((int) (this.zzgi - this.zzge), r6);
            this.zzgi += 4;
        }
    }

    private zzbn() {
    }

    public static int zza(int r1, zzcv zzcvVar) {
        int zzr = zzr(r1);
        int zzas = zzcvVar.zzas();
        return zzr + zzt(zzas) + zzas;
    }

    public static int zza(zzcv zzcvVar) {
        int zzas = zzcvVar.zzas();
        return zzt(zzas) + zzas;
    }

    public static zzbn zza(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzb(byteBuffer);
        }
        if (!byteBuffer.isDirect() || byteBuffer.isReadOnly()) {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        }
        return zzfd.zzee() ? new zze(byteBuffer) : new zzd(byteBuffer);
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzb(int r0, double d) {
        return zzr(r0) + 8;
    }

    public static int zzb(int r0, float f) {
        return zzr(r0) + 4;
    }

    public static int zzb(int r2, zzcv zzcvVar) {
        return (zzr(1) << 1) + zzh(2, r2) + zza(3, zzcvVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int r0, zzdo zzdoVar, zzef zzefVar) {
        return zzr(r0) + zzb(zzdoVar, zzefVar);
    }

    public static int zzb(int r0, String str) {
        return zzr(r0) + zzh(str);
    }

    public static int zzb(zzbb zzbbVar) {
        int size = zzbbVar.size();
        return zzt(size) + size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(zzdo zzdoVar, zzef zzefVar) {
        zzas zzasVar = (zzas) zzdoVar;
        int zzs = zzasVar.zzs();
        if (zzs == -1) {
            zzs = zzefVar.zzm(zzasVar);
            zzasVar.zzf(zzs);
        }
        return zzt(zzs) + zzs;
    }

    public static int zzb(boolean z) {
        return 1;
    }

    public static int zzc(int r1, zzbb zzbbVar) {
        int zzr = zzr(r1);
        int size = zzbbVar.size();
        return zzr + zzt(size) + size;
    }

    public static int zzc(int r0, zzdo zzdoVar) {
        return zzr(r0) + zzc(zzdoVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzc(int r2, zzdo zzdoVar, zzef zzefVar) {
        int zzr = zzr(r2) << 1;
        zzas zzasVar = (zzas) zzdoVar;
        int zzs = zzasVar.zzs();
        if (zzs == -1) {
            zzs = zzefVar.zzm(zzasVar);
            zzasVar.zzf(zzs);
        }
        return zzr + zzs;
    }

    public static int zzc(int r0, boolean z) {
        return zzr(r0) + 1;
    }

    public static int zzc(zzdo zzdoVar) {
        int zzas = zzdoVar.zzas();
        return zzt(zzas) + zzas;
    }

    public static zzbn zzc(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzd(int r0, long j) {
        return zzr(r0) + zzf(j);
    }

    public static int zzd(int r2, zzbb zzbbVar) {
        return (zzr(1) << 1) + zzh(2, r2) + zzc(3, zzbbVar);
    }

    public static int zzd(int r2, zzdo zzdoVar) {
        return (zzr(1) << 1) + zzh(2, r2) + zzc(3, zzdoVar);
    }

    @Deprecated
    public static int zzd(zzdo zzdoVar) {
        return zzdoVar.zzas();
    }

    public static int zzd(byte[] bArr) {
        int length = bArr.length;
        return zzt(length) + length;
    }

    public static int zze(int r0, long j) {
        return zzr(r0) + zzf(j);
    }

    public static int zze(long j) {
        return zzf(j);
    }

    public static int zzf(int r0, long j) {
        return zzr(r0) + zzf(zzj(j));
    }

    public static int zzf(long j) {
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

    public static int zzg(int r0, int r1) {
        return zzr(r0) + zzs(r1);
    }

    public static int zzg(int r0, long j) {
        return zzr(r0) + 8;
    }

    public static int zzg(long j) {
        return zzf(zzj(j));
    }

    public static int zzh(int r0, int r1) {
        return zzr(r0) + zzt(r1);
    }

    public static int zzh(int r0, long j) {
        return zzr(r0) + 8;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzh(String str) {
        int length;
        try {
            length = zzff.zza(str);
        } catch (zzfi unused) {
            length = str.getBytes(zzci.UTF_8).length;
        }
        return zzt(length) + length;
    }

    public static int zzi(int r0, int r1) {
        return zzr(r0) + zzt(zzy(r1));
    }

    public static int zzi(long j) {
        return 8;
    }

    public static int zzj(int r0, int r1) {
        return zzr(r0) + 4;
    }

    private static long zzj(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzk(int r0, int r1) {
        return zzr(r0) + 4;
    }

    public static int zzl(int r0, int r1) {
        return zzr(r0) + zzs(r1);
    }

    public static int zzr(int r0) {
        return zzt(r0 << 3);
    }

    public static int zzs(int r0) {
        if (r0 >= 0) {
            return zzt(r0);
        }
        return 10;
    }

    public static int zzt(int r1) {
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

    public static int zzu(int r0) {
        return zzt(zzy(r0));
    }

    public static int zzv(int r0) {
        return 4;
    }

    public static int zzw(int r0) {
        return 4;
    }

    public static int zzx(int r0) {
        return zzs(r0);
    }

    private static int zzy(int r1) {
        return (r1 >> 31) ^ (r1 << 1);
    }

    @Deprecated
    public static int zzz(int r0) {
        return zzt(r0);
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] bArr, int r2, int r3) throws IOException;

    public abstract void zza(byte b) throws IOException;

    public final void zza(double d) throws IOException {
        zzd(Double.doubleToRawLongBits(d));
    }

    public final void zza(float f) throws IOException {
        zzq(Float.floatToRawIntBits(f));
    }

    public final void zza(int r1, double d) throws IOException {
        zzc(r1, Double.doubleToRawLongBits(d));
    }

    public final void zza(int r1, float f) throws IOException {
        zzf(r1, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int r1, long j) throws IOException;

    public abstract void zza(int r1, zzbb zzbbVar) throws IOException;

    public abstract void zza(int r1, zzdo zzdoVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(int r1, zzdo zzdoVar, zzef zzefVar) throws IOException;

    public abstract void zza(int r1, String str) throws IOException;

    public abstract void zza(zzbb zzbbVar) throws IOException;

    abstract void zza(zzdo zzdoVar, zzef zzefVar) throws IOException;

    final void zza(String str, zzfi zzfiVar) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzfiVar);
        byte[] bytes = str.getBytes(zzci.UTF_8);
        try {
            zzo(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (zzc e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzc(e2);
        }
    }

    public final void zza(boolean z) throws IOException {
        zza(z ? (byte) 1 : (byte) 0);
    }

    public abstract int zzag();

    public abstract void zzb(int r1, int r2) throws IOException;

    public final void zzb(int r1, long j) throws IOException {
        zza(r1, zzj(j));
    }

    public abstract void zzb(int r1, zzbb zzbbVar) throws IOException;

    public abstract void zzb(int r1, zzdo zzdoVar) throws IOException;

    public abstract void zzb(int r1, boolean z) throws IOException;

    public abstract void zzb(long j) throws IOException;

    public abstract void zzb(zzdo zzdoVar) throws IOException;

    public abstract void zzc(int r1, int r2) throws IOException;

    public abstract void zzc(int r1, long j) throws IOException;

    public final void zzc(long j) throws IOException {
        zzb(zzj(j));
    }

    public abstract void zzd(int r1, int r2) throws IOException;

    public abstract void zzd(long j) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzd(byte[] bArr, int r2, int r3) throws IOException;

    public final void zze(int r1, int r2) throws IOException {
        zzd(r1, zzy(r2));
    }

    public abstract void zzf(int r1, int r2) throws IOException;

    public abstract void zzg(String str) throws IOException;

    public abstract void zzn(int r1) throws IOException;

    public abstract void zzo(int r1) throws IOException;

    public final void zzp(int r1) throws IOException {
        zzo(zzy(r1));
    }

    public abstract void zzq(int r1) throws IOException;
}
