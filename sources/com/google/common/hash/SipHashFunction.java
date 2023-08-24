package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import javax.annotation.CheckForNull;

@Immutable
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f1171c;

    /* renamed from: d */
    private final int f1172d;

    /* renamed from: k0 */
    private final long f1173k0;

    /* renamed from: k1 */
    private final long f1174k1;

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SipHashFunction(int r5, int r6, long j, long j2) {
        Preconditions.checkArgument(r5 > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", r5);
        Preconditions.checkArgument(r6 > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", r6);
        this.f1171c = r5;
        this.f1172d = r6;
        this.f1173k0 = j;
        this.f1174k1 = j2;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new SipHasher(this.f1171c, this.f1172d, this.f1173k0, this.f1174k1);
    }

    public String toString() {
        int r0 = this.f1171c;
        int r1 = this.f1172d;
        long j = this.f1173k0;
        long j2 = this.f1174k1;
        StringBuilder sb = new StringBuilder(81);
        sb.append("Hashing.sipHash");
        sb.append(r0);
        sb.append(r1);
        sb.append("(");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj instanceof SipHashFunction) {
            SipHashFunction sipHashFunction = (SipHashFunction) obj;
            return this.f1171c == sipHashFunction.f1171c && this.f1172d == sipHashFunction.f1172d && this.f1173k0 == sipHashFunction.f1173k0 && this.f1174k1 == sipHashFunction.f1174k1;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((getClass().hashCode() ^ this.f1171c) ^ this.f1172d) ^ this.f1173k0) ^ this.f1174k1);
    }

    /* loaded from: classes3.dex */
    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f1175b;

        /* renamed from: c */
        private final int f1176c;

        /* renamed from: d */
        private final int f1177d;
        private long finalM;

        /* renamed from: v0 */
        private long f1178v0;

        /* renamed from: v1 */
        private long f1179v1;

        /* renamed from: v2 */
        private long f1180v2;

        /* renamed from: v3 */
        private long f1181v3;

        SipHasher(int r3, int r4, long j, long j2) {
            super(8);
            this.f1175b = 0L;
            this.finalM = 0L;
            this.f1176c = r3;
            this.f1177d = r4;
            this.f1178v0 = 8317987319222330741L ^ j;
            this.f1179v1 = 7237128888997146477L ^ j2;
            this.f1180v2 = 7816392313619706465L ^ j;
            this.f1181v3 = 8387220255154660723L ^ j2;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void process(ByteBuffer byteBuffer) {
            this.f1175b += 8;
            processM(byteBuffer.getLong());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void processRemaining(ByteBuffer byteBuffer) {
            this.f1175b += byteBuffer.remaining();
            int r0 = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (byteBuffer.get() & 255) << r0;
                r0 += 8;
            }
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected HashCode makeHash() {
            long j = this.finalM ^ (this.f1175b << 56);
            this.finalM = j;
            processM(j);
            this.f1180v2 ^= 255;
            sipRound(this.f1177d);
            return HashCode.fromLong(((this.f1178v0 ^ this.f1179v1) ^ this.f1180v2) ^ this.f1181v3);
        }

        private void processM(long j) {
            this.f1181v3 ^= j;
            sipRound(this.f1176c);
            this.f1178v0 = j ^ this.f1178v0;
        }

        private void sipRound(int r9) {
            for (int r0 = 0; r0 < r9; r0++) {
                long j = this.f1178v0;
                long j2 = this.f1179v1;
                this.f1178v0 = j + j2;
                this.f1180v2 += this.f1181v3;
                this.f1179v1 = Long.rotateLeft(j2, 13);
                long rotateLeft = Long.rotateLeft(this.f1181v3, 16);
                long j3 = this.f1179v1;
                long j4 = this.f1178v0;
                this.f1179v1 = j3 ^ j4;
                this.f1181v3 = rotateLeft ^ this.f1180v2;
                long rotateLeft2 = Long.rotateLeft(j4, 32);
                long j5 = this.f1180v2;
                long j6 = this.f1179v1;
                this.f1180v2 = j5 + j6;
                this.f1178v0 = rotateLeft2 + this.f1181v3;
                this.f1179v1 = Long.rotateLeft(j6, 17);
                long rotateLeft3 = Long.rotateLeft(this.f1181v3, 21);
                long j7 = this.f1179v1;
                long j8 = this.f1180v2;
                this.f1179v1 = j7 ^ j8;
                this.f1181v3 = rotateLeft3 ^ this.f1178v0;
                this.f1180v2 = Long.rotateLeft(j8, 32);
            }
        }
    }
}
