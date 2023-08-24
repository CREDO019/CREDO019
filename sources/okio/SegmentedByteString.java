package okio;

import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.imagepipeline.producers.DecodeProducer;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.util.Objects;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SegmentedByteString.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0015\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\r\u0010\u0019\u001a\u00020\u001aH\u0010¢\u0006\u0002\b\u001bJ\b\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\u001d\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0001H\u0010¢\u0006\u0002\b J\u0018\u0010!\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u001aH\u0016J\r\u0010#\u001a\u00020\u0004H\u0010¢\u0006\u0002\b$J\u0015\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001aH\u0010¢\u0006\u0002\b(J\u0018\u0010)\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u001aH\u0016J(\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u001aH\u0016J(\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u001aH\u0016J\u0010\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u000200H\u0016J\u0018\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u001a2\u0006\u00103\u001a\u00020\u001aH\u0016J\b\u00104\u001a\u00020\u0001H\u0016J\b\u00105\u001a\u00020\u0001H\u0016J\b\u00106\u001a\u00020\u0004H\u0016J\b\u00107\u001a\u00020\u0001H\u0002J\b\u00108\u001a\u00020\u0010H\u0016J\u0010\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0016J%\u00109\u001a\u00020:2\u0006\u0010=\u001a\u00020>2\u0006\u0010+\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u001aH\u0010¢\u0006\u0002\b?J\b\u0010@\u001a\u00020AH\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006B"}, m183d2 = {"Lokio/SegmentedByteString;", "Lokio/ByteString;", "segments", "", "", "directory", "", "([[B[I)V", "getDirectory$okio", "()[I", "getSegments$okio", "()[[B", "[[B", "asByteBuffer", "Ljava/nio/ByteBuffer;", RNFetchBlobConst.RNFB_RESPONSE_BASE64, "", "base64Url", "digest", "algorithm", "digest$okio", "equals", "", "other", "", "getSize", "", "getSize$okio", "hashCode", "hex", "hmac", "key", "hmac$okio", "indexOf", "fromIndex", "internalArray", "internalArray$okio", "internalGet", "", "pos", "internalGet$okio", "lastIndexOf", "rangeEquals", "offset", "otherOffset", DecodeProducer.EXTRA_BITMAP_BYTES, "string", "charset", "Ljava/nio/charset/Charset;", "substring", "beginIndex", "endIndex", "toAsciiLowercase", "toAsciiUppercase", "toByteArray", "toByteString", "toString", "write", "", "out", "Ljava/io/OutputStream;", "buffer", "Lokio/Buffer;", "write$okio", "writeReplace", "Ljava/lang/Object;", "okio"}, m182k = 1, m181mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class SegmentedByteString extends ByteString {
    private final transient int[] directory;
    private final transient byte[][] segments;

    public final byte[][] getSegments$okio() {
        return this.segments;
    }

    public final int[] getDirectory$okio() {
        return this.directory;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SegmentedByteString(byte[][] segments, int[] directory) {
        super(ByteString.EMPTY.getData$okio());
        Intrinsics.checkNotNullParameter(segments, "segments");
        Intrinsics.checkNotNullParameter(directory, "directory");
        this.segments = segments;
        this.directory = directory;
    }

    @Override // okio.ByteString
    public String string(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return toByteString().string(charset);
    }

    @Override // okio.ByteString
    public String base64() {
        return toByteString().base64();
    }

    @Override // okio.ByteString
    public String hex() {
        return toByteString().hex();
    }

    @Override // okio.ByteString
    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    @Override // okio.ByteString
    public ByteString toAsciiUppercase() {
        return toByteString().toAsciiUppercase();
    }

    @Override // okio.ByteString
    public ByteString digest$okio(String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        return okio.internal.ByteString.commonSegmentDigest(this, algorithm);
    }

    @Override // okio.ByteString
    public ByteString hmac$okio(String algorithm, ByteString key) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            int length = getSegments$okio().length;
            int r7 = 0;
            int r1 = 0;
            while (r7 < length) {
                int r2 = getDirectory$okio()[length + r7];
                int r3 = getDirectory$okio()[r7];
                mac.update(getSegments$okio()[r7], r2, r3 - r1);
                r7++;
                r1 = r3;
            }
            byte[] doFinal = mac.doFinal();
            Intrinsics.checkNotNullExpressionValue(doFinal, "mac.doFinal()");
            return new ByteString(doFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // okio.ByteString
    public String base64Url() {
        return toByteString().base64Url();
    }

    @Override // okio.ByteString
    public ByteBuffer asByteBuffer() {
        ByteBuffer asReadOnlyBuffer = ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
        Intrinsics.checkNotNullExpressionValue(asReadOnlyBuffer, "ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer()");
        return asReadOnlyBuffer;
    }

    @Override // okio.ByteString
    public int indexOf(byte[] other, int r3) {
        Intrinsics.checkNotNullParameter(other, "other");
        return toByteString().indexOf(other, r3);
    }

    @Override // okio.ByteString
    public int lastIndexOf(byte[] other, int r3) {
        Intrinsics.checkNotNullParameter(other, "other");
        return toByteString().lastIndexOf(other, r3);
    }

    private final ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    @Override // okio.ByteString
    public byte[] internalArray$okio() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public String toString() {
        return toByteString().toString();
    }

    private final Object writeReplace() {
        ByteString byteString = toByteString();
        Objects.requireNonNull(byteString, "null cannot be cast to non-null type java.lang.Object");
        return byteString;
    }

    @Override // okio.ByteString
    public ByteString substring(int r13, int r14) {
        if (!(r13 >= 0)) {
            throw new IllegalArgumentException(("beginIndex=" + r13 + " < 0").toString());
        }
        if (!(r14 <= size())) {
            throw new IllegalArgumentException(("endIndex=" + r14 + " > length(" + size() + ')').toString());
        }
        int r2 = r14 - r13;
        if (!(r2 >= 0)) {
            throw new IllegalArgumentException(("endIndex=" + r14 + " < beginIndex=" + r13).toString());
        } else if (r13 == 0 && r14 == size()) {
            return this;
        } else {
            if (r13 == r14) {
                return ByteString.EMPTY;
            }
            int segment = okio.internal.SegmentedByteString.segment(this, r13);
            int segment2 = okio.internal.SegmentedByteString.segment(this, r14 - 1);
            byte[][] bArr = (byte[][]) ArraysKt.copyOfRange(getSegments$okio(), segment, segment2 + 1);
            byte[][] bArr2 = bArr;
            int[] r6 = new int[bArr2.length * 2];
            if (segment <= segment2) {
                int r8 = segment;
                int r7 = 0;
                while (true) {
                    r6[r7] = Math.min(getDirectory$okio()[r8] - r13, r2);
                    int r9 = r7 + 1;
                    r6[r7 + bArr2.length] = getDirectory$okio()[getSegments$okio().length + r8];
                    if (r8 == segment2) {
                        break;
                    }
                    r8++;
                    r7 = r9;
                }
            }
            int r0 = segment != 0 ? getDirectory$okio()[segment - 1] : 0;
            int length = bArr2.length;
            r6[length] = r6[length] + (r13 - r0);
            return new SegmentedByteString(bArr, r6);
        }
    }

    @Override // okio.ByteString
    public byte internalGet$okio(int r8) {
        Util.checkOffsetAndCount(getDirectory$okio()[getSegments$okio().length - 1], r8, 1L);
        int segment = okio.internal.SegmentedByteString.segment(this, r8);
        return getSegments$okio()[segment][(r8 - (segment == 0 ? 0 : getDirectory$okio()[segment - 1])) + getDirectory$okio()[getSegments$okio().length + segment]];
    }

    @Override // okio.ByteString
    public int getSize$okio() {
        return getDirectory$okio()[getSegments$okio().length - 1];
    }

    @Override // okio.ByteString
    public byte[] toByteArray() {
        byte[] bArr = new byte[size()];
        int length = getSegments$okio().length;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        while (r2 < length) {
            int r5 = getDirectory$okio()[length + r2];
            int r6 = getDirectory$okio()[r2];
            int r32 = r6 - r3;
            ArraysKt.copyInto(getSegments$okio()[r2], bArr, r4, r5, r5 + r32);
            r4 += r32;
            r2++;
            r3 = r6;
        }
        return bArr;
    }

    @Override // okio.ByteString
    public void write(OutputStream out) throws IOException {
        Intrinsics.checkNotNullParameter(out, "out");
        int length = getSegments$okio().length;
        int r1 = 0;
        int r2 = 0;
        while (r1 < length) {
            int r3 = getDirectory$okio()[length + r1];
            int r4 = getDirectory$okio()[r1];
            out.write(getSegments$okio()[r1], r3, r4 - r2);
            r1++;
            r2 = r4;
        }
    }

    @Override // okio.ByteString
    public void write$okio(Buffer buffer, int r13, int r14) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int r0 = r13 + r14;
        int segment = okio.internal.SegmentedByteString.segment(this, r13);
        while (r13 < r0) {
            int r2 = segment == 0 ? 0 : getDirectory$okio()[segment - 1];
            int r4 = getDirectory$okio()[getSegments$okio().length + segment];
            int min = Math.min(r0, (getDirectory$okio()[segment] - r2) + r2) - r13;
            int r7 = r4 + (r13 - r2);
            Segment segment2 = new Segment(getSegments$okio()[segment], r7, r7 + min, true, false);
            if (buffer.head == null) {
                segment2.prev = segment2;
                segment2.next = segment2.prev;
                buffer.head = segment2.next;
            } else {
                Segment segment3 = buffer.head;
                Intrinsics.checkNotNull(segment3);
                Segment segment4 = segment3.prev;
                Intrinsics.checkNotNull(segment4);
                segment4.push(segment2);
            }
            r13 += min;
            segment++;
        }
        buffer.setSize$okio(buffer.size() + r14);
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int r7, ByteString other, int r9, int r10) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (r7 < 0 || r7 > size() - r10) {
            return false;
        }
        int r102 = r10 + r7;
        int segment = okio.internal.SegmentedByteString.segment(this, r7);
        while (r7 < r102) {
            int r2 = segment == 0 ? 0 : getDirectory$okio()[segment - 1];
            int r4 = getDirectory$okio()[getSegments$okio().length + segment];
            int min = Math.min(r102, (getDirectory$okio()[segment] - r2) + r2) - r7;
            if (!other.rangeEquals(r9, getSegments$okio()[segment], r4 + (r7 - r2), min)) {
                return false;
            }
            r9 += min;
            r7 += min;
            segment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int r7, byte[] other, int r9, int r10) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (r7 < 0 || r7 > size() - r10 || r9 < 0 || r9 > other.length - r10) {
            return false;
        }
        int r102 = r10 + r7;
        int segment = okio.internal.SegmentedByteString.segment(this, r7);
        while (r7 < r102) {
            int r2 = segment == 0 ? 0 : getDirectory$okio()[segment - 1];
            int r4 = getDirectory$okio()[getSegments$okio().length + segment];
            int min = Math.min(r102, (getDirectory$okio()[segment] - r2) + r2) - r7;
            if (!Util.arrayRangeEquals(getSegments$okio()[segment], r4 + (r7 - r2), other, r9, min)) {
                return false;
            }
            r9 += min;
            r7 += min;
            segment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == size() && rangeEquals(0, byteString, 0, size())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public int hashCode() {
        int hashCode$okio = getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int length = getSegments$okio().length;
        int r1 = 0;
        int r2 = 1;
        int r3 = 0;
        while (r1 < length) {
            int r4 = getDirectory$okio()[length + r1];
            int r5 = getDirectory$okio()[r1];
            byte[] bArr = getSegments$okio()[r1];
            int r32 = (r5 - r3) + r4;
            while (r4 < r32) {
                r2 = (r2 * 31) + bArr[r4];
                r4++;
            }
            r1++;
            r3 = r5;
        }
        setHashCode$okio(r2);
        return r2;
    }
}
