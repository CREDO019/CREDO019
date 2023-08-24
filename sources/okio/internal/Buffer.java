package okio.internal;

import androidx.work.WorkRequest;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.MediaPeriodQueue;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.common.base.Ascii;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import okhttp3.internal.connection.RealConnection;
import okio.ByteString;
import okio.Options;
import okio.Platform;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Source;
import okio.Utf8;
import okio.Util;
import org.bouncycastle.asn1.cmc.BodyPartID;

@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0000\u001a\r\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0080\b\u001a\r\u0010\u0014\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u0010\u0015\u001a\u00020\u0013*\u00020\u0013H\u0080\b\u001a%\u0010\u0016\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0017\u0010\u001a\u001a\u00020\n*\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0080\b\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0005H\u0080\b\u001a\r\u0010 \u001a\u00020\b*\u00020\u0013H\u0080\b\u001a%\u0010!\u001a\u00020\u0005*\u00020\u00132\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0080\b\u001a\u001d\u0010!\u001a\u00020\u0005*\u00020\u00132\u0006\u0010\u000e\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a\u001d\u0010&\u001a\u00020\u0005*\u00020\u00132\u0006\u0010'\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a-\u0010(\u001a\u00020\n*\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u0015\u0010)\u001a\u00020\b*\u00020\u00132\u0006\u0010*\u001a\u00020\u0001H\u0080\b\u001a%\u0010)\u001a\u00020\b*\u00020\u00132\u0006\u0010*\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010)\u001a\u00020\u0005*\u00020\u00132\u0006\u0010*\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010+\u001a\u00020\u0005*\u00020\u00132\u0006\u0010*\u001a\u00020,H\u0080\b\u001a\r\u0010-\u001a\u00020\u001e*\u00020\u0013H\u0080\b\u001a\r\u0010.\u001a\u00020\u0001*\u00020\u0013H\u0080\b\u001a\u0015\u0010.\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u0010/\u001a\u00020%*\u00020\u0013H\u0080\b\u001a\u0015\u0010/\u001a\u00020%*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00100\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\u0015\u00101\u001a\u00020\u0012*\u00020\u00132\u0006\u0010*\u001a\u00020\u0001H\u0080\b\u001a\u001d\u00101\u001a\u00020\u0012*\u00020\u00132\u0006\u0010*\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00102\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u00103\u001a\u00020\b*\u00020\u0013H\u0080\b\u001a\r\u00104\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u00105\u001a\u000206*\u00020\u0013H\u0080\b\u001a\u0015\u00107\u001a\u000208*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00109\u001a\u00020\b*\u00020\u0013H\u0080\b\u001a\u000f\u0010:\u001a\u0004\u0018\u000108*\u00020\u0013H\u0080\b\u001a\u0015\u0010;\u001a\u000208*\u00020\u00132\u0006\u0010<\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010=\u001a\u00020\b*\u00020\u00132\u0006\u0010>\u001a\u00020?H\u0080\b\u001a\u0015\u0010@\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u0010A\u001a\u00020%*\u00020\u0013H\u0080\b\u001a\u0015\u0010A\u001a\u00020%*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u0015\u0010B\u001a\u00020\f*\u00020\u00132\u0006\u0010C\u001a\u00020\bH\u0080\b\u001a\u0015\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020\u0001H\u0080\b\u001a%\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010D\u001a\u00020\u0012*\u00020\u00132\u0006\u0010E\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a)\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010F\u001a\u00020%2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020G2\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010H\u001a\u00020\u0005*\u00020\u00132\u0006\u0010E\u001a\u00020GH\u0080\b\u001a\u0015\u0010I\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\"\u001a\u00020\bH\u0080\b\u001a\u0015\u0010J\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010L\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010M\u001a\u00020\u0013*\u00020\u00132\u0006\u0010N\u001a\u00020\bH\u0080\b\u001a\u0015\u0010O\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010P\u001a\u00020\u0013*\u00020\u00132\u0006\u0010Q\u001a\u00020\bH\u0080\b\u001a%\u0010R\u001a\u00020\u0013*\u00020\u00132\u0006\u0010S\u001a\u0002082\u0006\u0010T\u001a\u00020\b2\u0006\u0010U\u001a\u00020\bH\u0080\b\u001a\u0015\u0010V\u001a\u00020\u0013*\u00020\u00132\u0006\u0010W\u001a\u00020\bH\u0080\b\u001a\u0014\u0010X\u001a\u000208*\u00020\u00132\u0006\u0010Y\u001a\u00020\u0005H\u0000\u001a?\u0010Z\u001a\u0002H[\"\u0004\b\u0000\u0010[*\u00020\u00132\u0006\u0010#\u001a\u00020\u00052\u001a\u0010\\\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H[0]H\u0080\bø\u0001\u0000¢\u0006\u0002\u0010^\u001a\u001e\u0010_\u001a\u00020\b*\u00020\u00132\u0006\u0010>\u001a\u00020?2\b\b\u0002\u0010`\u001a\u00020\nH\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006a"}, m183d2 = {"HEX_DIGIT_BYTES", "", "getHEX_DIGIT_BYTES", "()[B", "OVERFLOW_DIGIT_START", "", "OVERFLOW_ZONE", "SEGMENTING_THRESHOLD", "", "rangeEquals", "", "segment", "Lokio/Segment;", "segmentPos", "bytes", "bytesOffset", "bytesLimit", "commonClear", "", "Lokio/Buffer;", "commonCompleteSegmentByteCount", "commonCopy", "commonCopyTo", "out", "offset", DecodeProducer.EXTRA_BITMAP_BYTES, "commonEquals", "other", "", "commonGet", "", "pos", "commonHashCode", "commonIndexOf", "b", "fromIndex", "toIndex", "Lokio/ByteString;", "commonIndexOfElement", "targetBytes", "commonRangeEquals", "commonRead", "sink", "commonReadAll", "Lokio/Sink;", "commonReadByte", "commonReadByteArray", "commonReadByteString", "commonReadDecimalLong", "commonReadFully", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadLong", "commonReadShort", "", "commonReadUtf8", "", "commonReadUtf8CodePoint", "commonReadUtf8Line", "commonReadUtf8LineStrict", "limit", "commonSelect", "options", "Lokio/Options;", "commonSkip", "commonSnapshot", "commonWritableSegment", "minimumCapacity", "commonWrite", "source", "byteString", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "commonWriteDecimalLong", "v", "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteLong", "commonWriteShort", "s", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "readUtf8Line", "newline", "seek", "T", "lambda", "Lkotlin/Function2;", "(Lokio/Buffer;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "selectPrefix", "selectTruncated", "okio"}, m182k = 2, m181mv = {1, 4, 0})
/* renamed from: okio.internal.BufferKt */
/* loaded from: classes5.dex */
public final class Buffer {
    private static final byte[] HEX_DIGIT_BYTES = Platform.asUtf8ToByteArray("0123456789abcdef");
    public static final long OVERFLOW_DIGIT_START = -7;
    public static final long OVERFLOW_ZONE = -922337203685477580L;
    public static final int SEGMENTING_THRESHOLD = 4096;

    public static final byte[] getHEX_DIGIT_BYTES() {
        return HEX_DIGIT_BYTES;
    }

    public static final boolean rangeEquals(Segment segment, int r6, byte[] bytes, int r8, int r9) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        int r0 = segment.limit;
        byte[] bArr = segment.data;
        while (r8 < r9) {
            if (r6 == r0) {
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                byte[] bArr2 = segment.data;
                bArr = bArr2;
                r6 = segment.pos;
                r0 = segment.limit;
            }
            if (bArr[r6] != bytes[r8]) {
                return false;
            }
            r6++;
            r8++;
        }
        return true;
    }

    public static final String readUtf8Line(okio.Buffer readUtf8Line, long j) {
        Intrinsics.checkNotNullParameter(readUtf8Line, "$this$readUtf8Line");
        if (j > 0) {
            long j2 = j - 1;
            if (readUtf8Line.getByte(j2) == ((byte) 13)) {
                String readUtf8 = readUtf8Line.readUtf8(j2);
                readUtf8Line.skip(2L);
                return readUtf8;
            }
        }
        String readUtf82 = readUtf8Line.readUtf8(j);
        readUtf8Line.skip(1L);
        return readUtf82;
    }

    public static final <T> T seek(okio.Buffer seek, long j, Function2<? super Segment, ? super Long, ? extends T> lambda) {
        Intrinsics.checkNotNullParameter(seek, "$this$seek");
        Intrinsics.checkNotNullParameter(lambda, "lambda");
        Segment segment = seek.head;
        if (segment == null) {
            return lambda.invoke(null, -1L);
        }
        if (seek.size() - j < j) {
            long size = seek.size();
            while (size > j) {
                segment = segment.prev;
                Intrinsics.checkNotNull(segment);
                size -= segment.limit - segment.pos;
            }
            return lambda.invoke(segment, Long.valueOf(size));
        }
        long j2 = 0;
        while (true) {
            long j3 = (segment.limit - segment.pos) + j2;
            if (j3 <= j) {
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j2 = j3;
            } else {
                return lambda.invoke(segment, Long.valueOf(j2));
            }
        }
    }

    public static /* synthetic */ int selectPrefix$default(okio.Buffer buffer, Options options, boolean z, int r3, Object obj) {
        if ((r3 & 2) != 0) {
            z = false;
        }
        return selectPrefix(buffer, options, z);
    }

    public static final int selectPrefix(okio.Buffer selectPrefix, Options options, boolean z) {
        int r13;
        int r5;
        int r9;
        int r6;
        Segment segment;
        Intrinsics.checkNotNullParameter(selectPrefix, "$this$selectPrefix");
        Intrinsics.checkNotNullParameter(options, "options");
        Segment segment2 = selectPrefix.head;
        if (segment2 == null) {
            return z ? -2 : -1;
        }
        byte[] bArr = segment2.data;
        int r52 = segment2.pos;
        int r62 = segment2.limit;
        int[] trie$okio = options.getTrie$okio();
        Segment segment3 = segment2;
        int r8 = 0;
        int r10 = -1;
        loop0: while (true) {
            int r11 = r8 + 1;
            int r82 = trie$okio[r8];
            int r12 = r11 + 1;
            int r112 = trie$okio[r11];
            if (r112 != -1) {
                r10 = r112;
            }
            if (segment3 == null) {
                break;
            }
            if (r82 >= 0) {
                r13 = r52 + 1;
                int r53 = bArr[r52] & 255;
                int r14 = r12 + r82;
                while (r12 != r14) {
                    if (r53 == trie$okio[r12]) {
                        r5 = trie$okio[r12 + r82];
                        if (r13 == r62) {
                            segment3 = segment3.next;
                            Intrinsics.checkNotNull(segment3);
                            int r4 = segment3.pos;
                            byte[] bArr2 = segment3.data;
                            int r83 = segment3.limit;
                            if (segment3 == segment2) {
                                r13 = r4;
                                bArr = bArr2;
                                r62 = r83;
                                segment3 = null;
                            } else {
                                r13 = r4;
                                bArr = bArr2;
                                r62 = r83;
                            }
                        }
                    } else {
                        r12++;
                    }
                }
                return r10;
            }
            int r132 = r12 + (r82 * (-1));
            while (true) {
                int r84 = r52 + 1;
                int r142 = r12 + 1;
                if ((bArr[r52] & 255) != trie$okio[r12]) {
                    return r10;
                }
                boolean z2 = r142 == r132;
                if (r84 == r62) {
                    Intrinsics.checkNotNull(segment3);
                    Segment segment4 = segment3.next;
                    Intrinsics.checkNotNull(segment4);
                    r6 = segment4.pos;
                    byte[] bArr3 = segment4.data;
                    r9 = segment4.limit;
                    if (segment4 != segment2) {
                        segment = segment4;
                        bArr = bArr3;
                    } else if (!z2) {
                        break loop0;
                    } else {
                        bArr = bArr3;
                        segment = null;
                    }
                } else {
                    Segment segment5 = segment3;
                    r9 = r62;
                    r6 = r84;
                    segment = segment5;
                }
                if (z2) {
                    r5 = trie$okio[r142];
                    r13 = r6;
                    r62 = r9;
                    segment3 = segment;
                    break;
                }
                r52 = r6;
                r62 = r9;
                r12 = r142;
                segment3 = segment;
            }
            if (r5 >= 0) {
                return r5;
            }
            r8 = -r5;
            r52 = r13;
        }
        if (z) {
            return -2;
        }
        return r10;
    }

    public static final okio.Buffer commonCopyTo(okio.Buffer commonCopyTo, okio.Buffer out, long j, long j2) {
        Intrinsics.checkNotNullParameter(commonCopyTo, "$this$commonCopyTo");
        Intrinsics.checkNotNullParameter(out, "out");
        Util.checkOffsetAndCount(commonCopyTo.size(), j, j2);
        if (j2 == 0) {
            return commonCopyTo;
        }
        out.setSize$okio(out.size() + j2);
        Segment segment = commonCopyTo.head;
        while (true) {
            Intrinsics.checkNotNull(segment);
            if (j < segment.limit - segment.pos) {
                break;
            }
            j -= segment.limit - segment.pos;
            segment = segment.next;
        }
        while (j2 > 0) {
            Intrinsics.checkNotNull(segment);
            Segment sharedCopy = segment.sharedCopy();
            sharedCopy.pos += (int) j;
            sharedCopy.limit = Math.min(sharedCopy.pos + ((int) j2), sharedCopy.limit);
            if (out.head == null) {
                sharedCopy.prev = sharedCopy;
                sharedCopy.next = sharedCopy.prev;
                out.head = sharedCopy.next;
            } else {
                Segment segment2 = out.head;
                Intrinsics.checkNotNull(segment2);
                Segment segment3 = segment2.prev;
                Intrinsics.checkNotNull(segment3);
                segment3.push(sharedCopy);
            }
            j2 -= sharedCopy.limit - sharedCopy.pos;
            segment = segment.next;
            j = 0;
        }
        return commonCopyTo;
    }

    public static final long commonCompleteSegmentByteCount(okio.Buffer commonCompleteSegmentByteCount) {
        Intrinsics.checkNotNullParameter(commonCompleteSegmentByteCount, "$this$commonCompleteSegmentByteCount");
        long size = commonCompleteSegmentByteCount.size();
        if (size == 0) {
            return 0L;
        }
        Segment segment = commonCompleteSegmentByteCount.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment.prev;
        Intrinsics.checkNotNull(segment2);
        return (segment2.limit >= 8192 || !segment2.owner) ? size : size - (segment2.limit - segment2.pos);
    }

    public static final byte commonReadByte(okio.Buffer commonReadByte) {
        Intrinsics.checkNotNullParameter(commonReadByte, "$this$commonReadByte");
        if (commonReadByte.size() == 0) {
            throw new EOFException();
        }
        Segment segment = commonReadByte.head;
        Intrinsics.checkNotNull(segment);
        int r1 = segment.pos;
        int r2 = segment.limit;
        int r4 = r1 + 1;
        byte b = segment.data[r1];
        commonReadByte.setSize$okio(commonReadByte.size() - 1);
        if (r4 == r2) {
            commonReadByte.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = r4;
        }
        return b;
    }

    public static final short commonReadShort(okio.Buffer commonReadShort) {
        Intrinsics.checkNotNullParameter(commonReadShort, "$this$commonReadShort");
        if (commonReadShort.size() < 2) {
            throw new EOFException();
        }
        Segment segment = commonReadShort.head;
        Intrinsics.checkNotNull(segment);
        int r1 = segment.pos;
        int r4 = segment.limit;
        if (r4 - r1 < 2) {
            return (short) ((commonReadShort.readByte() & 255) | ((commonReadShort.readByte() & 255) << 8));
        }
        byte[] bArr = segment.data;
        int r6 = r1 + 1;
        int r7 = r6 + 1;
        int r12 = ((bArr[r1] & 255) << 8) | (bArr[r6] & 255);
        commonReadShort.setSize$okio(commonReadShort.size() - 2);
        if (r7 == r4) {
            commonReadShort.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = r7;
        }
        return (short) r12;
    }

    public static final int commonReadInt(okio.Buffer commonReadInt) {
        Intrinsics.checkNotNullParameter(commonReadInt, "$this$commonReadInt");
        if (commonReadInt.size() < 4) {
            throw new EOFException();
        }
        Segment segment = commonReadInt.head;
        Intrinsics.checkNotNull(segment);
        int r1 = segment.pos;
        int r4 = segment.limit;
        if (r4 - r1 < 4) {
            return (commonReadInt.readByte() & 255) | ((commonReadInt.readByte() & 255) << 24) | ((commonReadInt.readByte() & 255) << 16) | ((commonReadInt.readByte() & 255) << 8);
        }
        byte[] bArr = segment.data;
        int r6 = r1 + 1;
        int r7 = r6 + 1;
        int r12 = ((bArr[r1] & 255) << 24) | ((bArr[r6] & 255) << 16);
        int r62 = r7 + 1;
        int r13 = r12 | ((bArr[r7] & 255) << 8);
        int r72 = r62 + 1;
        int r14 = r13 | (bArr[r62] & 255);
        commonReadInt.setSize$okio(commonReadInt.size() - 4);
        if (r72 == r4) {
            commonReadInt.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = r72;
        }
        return r14;
    }

    public static final long commonReadLong(okio.Buffer commonReadLong) {
        Intrinsics.checkNotNullParameter(commonReadLong, "$this$commonReadLong");
        if (commonReadLong.size() < 8) {
            throw new EOFException();
        }
        Segment segment = commonReadLong.head;
        Intrinsics.checkNotNull(segment);
        int r1 = segment.pos;
        int r4 = segment.limit;
        if (r4 - r1 < 8) {
            return ((commonReadLong.readInt() & BodyPartID.bodyIdMax) << 32) | (BodyPartID.bodyIdMax & commonReadLong.readInt());
        }
        byte[] bArr = segment.data;
        int r6 = r1 + 1;
        int r12 = r6 + 1;
        int r62 = r12 + 1;
        long j = ((bArr[r1] & 255) << 56) | ((bArr[r6] & 255) << 48) | ((bArr[r12] & 255) << 40);
        int r13 = r62 + 1;
        long j2 = ((bArr[r62] & 255) << 32) | j;
        int r8 = r13 + 1;
        int r14 = r8 + 1;
        long j3 = j2 | ((bArr[r13] & 255) << 24) | ((bArr[r8] & 255) << 16);
        int r82 = r14 + 1;
        int r15 = r82 + 1;
        long j4 = j3 | ((bArr[r14] & 255) << 8) | (bArr[r82] & 255);
        commonReadLong.setSize$okio(commonReadLong.size() - 8);
        if (r15 == r4) {
            commonReadLong.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = r15;
        }
        return j4;
    }

    public static final byte commonGet(okio.Buffer commonGet, long j) {
        Intrinsics.checkNotNullParameter(commonGet, "$this$commonGet");
        Util.checkOffsetAndCount(commonGet.size(), j, 1L);
        Segment segment = commonGet.head;
        if (segment == null) {
            Segment segment2 = null;
            Intrinsics.checkNotNull(null);
            return segment2.data[(int) ((segment2.pos + j) - (-1))];
        } else if (commonGet.size() - j < j) {
            long size = commonGet.size();
            while (size > j) {
                segment = segment.prev;
                Intrinsics.checkNotNull(segment);
                size -= segment.limit - segment.pos;
            }
            Intrinsics.checkNotNull(segment);
            return segment.data[(int) ((segment.pos + j) - size)];
        } else {
            long j2 = 0;
            while (true) {
                long j3 = (segment.limit - segment.pos) + j2;
                if (j3 > j) {
                    Intrinsics.checkNotNull(segment);
                    return segment.data[(int) ((segment.pos + j) - j2)];
                }
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j2 = j3;
            }
        }
    }

    public static final void commonClear(okio.Buffer commonClear) {
        Intrinsics.checkNotNullParameter(commonClear, "$this$commonClear");
        commonClear.skip(commonClear.size());
    }

    public static final void commonSkip(okio.Buffer commonSkip, long j) {
        Intrinsics.checkNotNullParameter(commonSkip, "$this$commonSkip");
        while (j > 0) {
            Segment segment = commonSkip.head;
            if (segment == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, segment.limit - segment.pos);
            long j2 = min;
            commonSkip.setSize$okio(commonSkip.size() - j2);
            j -= j2;
            segment.pos += min;
            if (segment.pos == segment.limit) {
                commonSkip.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }

    public static /* synthetic */ okio.Buffer commonWrite$default(okio.Buffer commonWrite, ByteString byteString, int r2, int r3, int r4, Object obj) {
        if ((r4 & 2) != 0) {
            r2 = 0;
        }
        if ((r4 & 4) != 0) {
            r3 = byteString.size();
        }
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(commonWrite, r2, r3);
        return commonWrite;
    }

    public static final okio.Buffer commonWrite(okio.Buffer commonWrite, ByteString byteString, int r3, int r4) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(commonWrite, r3, r4);
        return commonWrite;
    }

    public static final okio.Buffer commonWriteDecimalLong(okio.Buffer commonWriteDecimalLong, long j) {
        Intrinsics.checkNotNullParameter(commonWriteDecimalLong, "$this$commonWriteDecimalLong");
        int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (r2 == 0) {
            return commonWriteDecimalLong.writeByte(48);
        }
        boolean z = false;
        int r4 = 1;
        if (r2 < 0) {
            j = -j;
            if (j < 0) {
                return commonWriteDecimalLong.writeUtf8("-9223372036854775808");
            }
            z = true;
        }
        if (j >= 100000000) {
            r4 = j < MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US ? j < RealConnection.IDLE_CONNECTION_HEALTHY_NS ? j < C1856C.NANOS_PER_SECOND ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= WorkRequest.MIN_BACKOFF_MILLIS) {
            r4 = j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            r4 = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            r4 = 2;
        }
        if (z) {
            r4++;
        }
        Segment writableSegment$okio = commonWriteDecimalLong.writableSegment$okio(r4);
        byte[] bArr = writableSegment$okio.data;
        int r7 = writableSegment$okio.limit + r4;
        while (j != 0) {
            long j2 = 10;
            r7--;
            bArr[r7] = getHEX_DIGIT_BYTES()[(int) (j % j2)];
            j /= j2;
        }
        if (z) {
            bArr[r7 - 1] = (byte) 45;
        }
        writableSegment$okio.limit += r4;
        commonWriteDecimalLong.setSize$okio(commonWriteDecimalLong.size() + r4);
        return commonWriteDecimalLong;
    }

    public static final okio.Buffer commonWriteHexadecimalUnsignedLong(okio.Buffer commonWriteHexadecimalUnsignedLong, long j) {
        Intrinsics.checkNotNullParameter(commonWriteHexadecimalUnsignedLong, "$this$commonWriteHexadecimalUnsignedLong");
        if (j == 0) {
            return commonWriteHexadecimalUnsignedLong.writeByte(48);
        }
        long j2 = (j >>> 1) | j;
        long j3 = j2 | (j2 >>> 2);
        long j4 = j3 | (j3 >>> 4);
        long j5 = j4 | (j4 >>> 8);
        long j6 = j5 | (j5 >>> 16);
        long j7 = j6 | (j6 >>> 32);
        long j8 = j7 - ((j7 >>> 1) & 6148914691236517205L);
        long j9 = ((j8 >>> 2) & 3689348814741910323L) + (j8 & 3689348814741910323L);
        long j10 = ((j9 >>> 4) + j9) & 1085102592571150095L;
        long j11 = j10 + (j10 >>> 8);
        long j12 = j11 + (j11 >>> 16);
        int r1 = (int) ((((j12 & 63) + ((j12 >>> 32) & 63)) + 3) / 4);
        Segment writableSegment$okio = commonWriteHexadecimalUnsignedLong.writableSegment$okio(r1);
        byte[] bArr = writableSegment$okio.data;
        int r0 = writableSegment$okio.limit;
        for (int r5 = (writableSegment$okio.limit + r1) - 1; r5 >= r0; r5--) {
            bArr[r5] = getHEX_DIGIT_BYTES()[(int) (15 & j)];
            j >>>= 4;
        }
        writableSegment$okio.limit += r1;
        commonWriteHexadecimalUnsignedLong.setSize$okio(commonWriteHexadecimalUnsignedLong.size() + r1);
        return commonWriteHexadecimalUnsignedLong;
    }

    public static final Segment commonWritableSegment(okio.Buffer commonWritableSegment, int r3) {
        Intrinsics.checkNotNullParameter(commonWritableSegment, "$this$commonWritableSegment");
        boolean z = true;
        if (!((r3 < 1 || r3 > 8192) ? false : false)) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        if (commonWritableSegment.head == null) {
            Segment take = SegmentPool.take();
            commonWritableSegment.head = take;
            take.prev = take;
            take.next = take;
            return take;
        }
        Segment segment = commonWritableSegment.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment.prev;
        Intrinsics.checkNotNull(segment2);
        return (segment2.limit + r3 > 8192 || !segment2.owner) ? segment2.push(SegmentPool.take()) : segment2;
    }

    public static final okio.Buffer commonWrite(okio.Buffer commonWrite, byte[] source) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        return commonWrite.write(source, 0, source.length);
    }

    public static final okio.Buffer commonWrite(okio.Buffer commonWrite, byte[] source, int r11, int r12) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        long j = r12;
        Util.checkOffsetAndCount(source.length, r11, j);
        int r122 = r12 + r11;
        while (r11 < r122) {
            Segment writableSegment$okio = commonWrite.writableSegment$okio(1);
            int min = Math.min(r122 - r11, 8192 - writableSegment$okio.limit);
            int r4 = r11 + min;
            ArraysKt.copyInto(source, writableSegment$okio.data, writableSegment$okio.limit, r11, r4);
            writableSegment$okio.limit += min;
            r11 = r4;
        }
        commonWrite.setSize$okio(commonWrite.size() + j);
        return commonWrite;
    }

    public static final byte[] commonReadByteArray(okio.Buffer commonReadByteArray) {
        Intrinsics.checkNotNullParameter(commonReadByteArray, "$this$commonReadByteArray");
        return commonReadByteArray.readByteArray(commonReadByteArray.size());
    }

    public static final byte[] commonReadByteArray(okio.Buffer commonReadByteArray, long j) {
        Intrinsics.checkNotNullParameter(commonReadByteArray, "$this$commonReadByteArray");
        if (!(j >= 0 && j <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        } else if (commonReadByteArray.size() < j) {
            throw new EOFException();
        } else {
            byte[] bArr = new byte[(int) j];
            commonReadByteArray.readFully(bArr);
            return bArr;
        }
    }

    public static final int commonRead(okio.Buffer commonRead, byte[] sink) {
        Intrinsics.checkNotNullParameter(commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink, "sink");
        return commonRead.read(sink, 0, sink.length);
    }

    public static final void commonReadFully(okio.Buffer commonReadFully, byte[] sink) {
        Intrinsics.checkNotNullParameter(commonReadFully, "$this$commonReadFully");
        Intrinsics.checkNotNullParameter(sink, "sink");
        int r0 = 0;
        while (r0 < sink.length) {
            int read = commonReadFully.read(sink, r0, sink.length - r0);
            if (read == -1) {
                throw new EOFException();
            }
            r0 += read;
        }
    }

    public static final int commonRead(okio.Buffer commonRead, byte[] sink, int r9, int r10) {
        Intrinsics.checkNotNullParameter(commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink, "sink");
        Util.checkOffsetAndCount(sink.length, r9, r10);
        Segment segment = commonRead.head;
        if (segment != null) {
            int min = Math.min(r10, segment.limit - segment.pos);
            ArraysKt.copyInto(segment.data, sink, r9, segment.pos, segment.pos + min);
            segment.pos += min;
            commonRead.setSize$okio(commonRead.size() - min);
            if (segment.pos == segment.limit) {
                commonRead.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            return min;
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x00cb A[EDGE_INSN: B:108:0x00cb->B:100:0x00cb ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long commonReadDecimalLong(okio.Buffer r17) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.Buffer.commonReadDecimalLong(okio.Buffer):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00b7 A[EDGE_INSN: B:91:0x00b7->B:85:0x00b7 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long commonReadHexadecimalUnsignedLong(okio.Buffer r15) {
        /*
            java.lang.String r0 = "$this$commonReadHexadecimalUnsignedLong"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            long r0 = r15.size()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto Lc1
            r0 = 0
            r4 = r2
            r1 = 0
        L12:
            okio.Segment r6 = r15.head
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            byte[] r7 = r6.data
            int r8 = r6.pos
            int r9 = r6.limit
        L1d:
            if (r8 >= r9) goto La3
            r10 = r7[r8]
            r11 = 48
            byte r11 = (byte) r11
            if (r10 < r11) goto L2e
            r12 = 57
            byte r12 = (byte) r12
            if (r10 > r12) goto L2e
            int r11 = r10 - r11
            goto L48
        L2e:
            r11 = 97
            byte r11 = (byte) r11
            if (r10 < r11) goto L3d
            r12 = 102(0x66, float:1.43E-43)
            byte r12 = (byte) r12
            if (r10 > r12) goto L3d
        L38:
            int r11 = r10 - r11
            int r11 = r11 + 10
            goto L48
        L3d:
            r11 = 65
            byte r11 = (byte) r11
            if (r10 < r11) goto L82
            r12 = 70
            byte r12 = (byte) r12
            if (r10 > r12) goto L82
            goto L38
        L48:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r14 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r14 != 0) goto L58
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L1d
        L58:
            okio.Buffer r15 = new okio.Buffer
            r15.<init>()
            okio.Buffer r15 = r15.writeHexadecimalUnsignedLong(r4)
            okio.Buffer r15 = r15.writeByte(r10)
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Number too large: "
            r1.append(r2)
            java.lang.String r15 = r15.readUtf8()
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            r0.<init>(r15)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L82:
            if (r0 == 0) goto L86
            r1 = 1
            goto La3
        L86:
            java.lang.NumberFormatException r15 = new java.lang.NumberFormatException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Expected leading [0-9a-fA-F] character but was 0x"
            r0.append(r1)
            java.lang.String r1 = okio.Util.toHexString(r10)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            throw r15
        La3:
            if (r8 != r9) goto Laf
            okio.Segment r7 = r6.pop()
            r15.head = r7
            okio.SegmentPool.recycle(r6)
            goto Lb1
        Laf:
            r6.pos = r8
        Lb1:
            if (r1 != 0) goto Lb7
            okio.Segment r6 = r15.head
            if (r6 != 0) goto L12
        Lb7:
            long r1 = r15.size()
            long r6 = (long) r0
            long r1 = r1 - r6
            r15.setSize$okio(r1)
            return r4
        Lc1:
            java.io.EOFException r15 = new java.io.EOFException
            r15.<init>()
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.Buffer.commonReadHexadecimalUnsignedLong(okio.Buffer):long");
    }

    public static final ByteString commonReadByteString(okio.Buffer commonReadByteString) {
        Intrinsics.checkNotNullParameter(commonReadByteString, "$this$commonReadByteString");
        return commonReadByteString.readByteString(commonReadByteString.size());
    }

    public static final ByteString commonReadByteString(okio.Buffer commonReadByteString, long j) {
        Intrinsics.checkNotNullParameter(commonReadByteString, "$this$commonReadByteString");
        if (!(j >= 0 && j <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        } else if (commonReadByteString.size() >= j) {
            if (j >= 4096) {
                ByteString snapshot = commonReadByteString.snapshot((int) j);
                commonReadByteString.skip(j);
                return snapshot;
            }
            return new ByteString(commonReadByteString.readByteArray(j));
        } else {
            throw new EOFException();
        }
    }

    public static final int commonSelect(okio.Buffer commonSelect, Options options) {
        Intrinsics.checkNotNullParameter(commonSelect, "$this$commonSelect");
        Intrinsics.checkNotNullParameter(options, "options");
        int selectPrefix$default = selectPrefix$default(commonSelect, options, false, 2, null);
        if (selectPrefix$default == -1) {
            return -1;
        }
        commonSelect.skip(options.getByteStrings$okio()[selectPrefix$default].size());
        return selectPrefix$default;
    }

    public static final void commonReadFully(okio.Buffer commonReadFully, okio.Buffer sink, long j) {
        Intrinsics.checkNotNullParameter(commonReadFully, "$this$commonReadFully");
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (commonReadFully.size() < j) {
            sink.write(commonReadFully, commonReadFully.size());
            throw new EOFException();
        } else {
            sink.write(commonReadFully, j);
        }
    }

    public static final long commonReadAll(okio.Buffer commonReadAll, Sink sink) {
        Intrinsics.checkNotNullParameter(commonReadAll, "$this$commonReadAll");
        Intrinsics.checkNotNullParameter(sink, "sink");
        long size = commonReadAll.size();
        if (size > 0) {
            sink.write(commonReadAll, size);
        }
        return size;
    }

    public static final String commonReadUtf8(okio.Buffer commonReadUtf8, long j) {
        Intrinsics.checkNotNullParameter(commonReadUtf8, "$this$commonReadUtf8");
        int r3 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (!(r3 >= 0 && j <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        } else if (commonReadUtf8.size() >= j) {
            if (r3 == 0) {
                return "";
            }
            Segment segment = commonReadUtf8.head;
            Intrinsics.checkNotNull(segment);
            if (segment.pos + j > segment.limit) {
                return _Utf8Kt.commonToUtf8String$default(commonReadUtf8.readByteArray(j), 0, 0, 3, null);
            }
            int r4 = (int) j;
            String commonToUtf8String = _Utf8Kt.commonToUtf8String(segment.data, segment.pos, segment.pos + r4);
            segment.pos += r4;
            commonReadUtf8.setSize$okio(commonReadUtf8.size() - j);
            if (segment.pos == segment.limit) {
                commonReadUtf8.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            return commonToUtf8String;
        } else {
            throw new EOFException();
        }
    }

    public static final String commonReadUtf8Line(okio.Buffer commonReadUtf8Line) {
        Intrinsics.checkNotNullParameter(commonReadUtf8Line, "$this$commonReadUtf8Line");
        long indexOf = commonReadUtf8Line.indexOf((byte) 10);
        if (indexOf != -1) {
            return readUtf8Line(commonReadUtf8Line, indexOf);
        }
        if (commonReadUtf8Line.size() != 0) {
            return commonReadUtf8Line.readUtf8(commonReadUtf8Line.size());
        }
        return null;
    }

    public static final String commonReadUtf8LineStrict(okio.Buffer commonReadUtf8LineStrict, long j) {
        Intrinsics.checkNotNullParameter(commonReadUtf8LineStrict, "$this$commonReadUtf8LineStrict");
        if (!(j >= 0)) {
            throw new IllegalArgumentException(("limit < 0: " + j).toString());
        }
        long j2 = j != Long.MAX_VALUE ? j + 1 : Long.MAX_VALUE;
        byte b = (byte) 10;
        long indexOf = commonReadUtf8LineStrict.indexOf(b, 0L, j2);
        if (indexOf != -1) {
            return readUtf8Line(commonReadUtf8LineStrict, indexOf);
        }
        if (j2 < commonReadUtf8LineStrict.size() && commonReadUtf8LineStrict.getByte(j2 - 1) == ((byte) 13) && commonReadUtf8LineStrict.getByte(j2) == b) {
            return readUtf8Line(commonReadUtf8LineStrict, j2);
        }
        okio.Buffer buffer = new okio.Buffer();
        commonReadUtf8LineStrict.copyTo(buffer, 0L, Math.min(32, commonReadUtf8LineStrict.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(commonReadUtf8LineStrict.size(), j) + " content=" + buffer.readByteString().hex() + Typography.ellipsis);
    }

    public static final int commonReadUtf8CodePoint(okio.Buffer commonReadUtf8CodePoint) {
        int r1;
        int r5;
        int r6;
        Intrinsics.checkNotNullParameter(commonReadUtf8CodePoint, "$this$commonReadUtf8CodePoint");
        if (commonReadUtf8CodePoint.size() == 0) {
            throw new EOFException();
        }
        byte b = commonReadUtf8CodePoint.getByte(0L);
        if ((b & 128) == 0) {
            r1 = b & Byte.MAX_VALUE;
            r5 = 1;
            r6 = 0;
        } else if ((b & 224) == 192) {
            r1 = b & Ascii.f1131US;
            r5 = 2;
            r6 = 128;
        } else if ((b & 240) == 224) {
            r1 = b & Ascii.f1128SI;
            r5 = 3;
            r6 = 2048;
        } else if ((b & 248) != 240) {
            commonReadUtf8CodePoint.skip(1L);
            return Utf8.REPLACEMENT_CODE_POINT;
        } else {
            r1 = b & 7;
            r5 = 4;
            r6 = 65536;
        }
        long j = r5;
        if (commonReadUtf8CodePoint.size() < j) {
            throw new EOFException("size < " + r5 + ": " + commonReadUtf8CodePoint.size() + " (to read code point prefixed 0x" + Util.toHexString(b) + ')');
        }
        for (int r2 = 1; r2 < r5; r2++) {
            long j2 = r2;
            byte b2 = commonReadUtf8CodePoint.getByte(j2);
            if ((b2 & 192) != 128) {
                commonReadUtf8CodePoint.skip(j2);
                return Utf8.REPLACEMENT_CODE_POINT;
            }
            r1 = (r1 << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
        }
        commonReadUtf8CodePoint.skip(j);
        return r1 > 1114111 ? Utf8.REPLACEMENT_CODE_POINT : ((55296 <= r1 && 57343 >= r1) || r1 < r6) ? Utf8.REPLACEMENT_CODE_POINT : r1;
    }

    public static final okio.Buffer commonWriteUtf8(okio.Buffer commonWriteUtf8, String string, int r14, int r15) {
        int r8;
        Intrinsics.checkNotNullParameter(commonWriteUtf8, "$this$commonWriteUtf8");
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(r14 >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + r14).toString());
        }
        if (!(r15 >= r14)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + r15 + " < " + r14).toString());
        }
        if (!(r15 <= string.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + r15 + " > " + string.length()).toString());
        }
        while (r14 < r15) {
            char charAt = string.charAt(r14);
            if (charAt < 128) {
                Segment writableSegment$okio = commonWriteUtf8.writableSegment$okio(1);
                byte[] bArr = writableSegment$okio.data;
                int r6 = writableSegment$okio.limit - r14;
                int min = Math.min(r15, 8192 - r6);
                r8 = r14 + 1;
                bArr[r14 + r6] = (byte) charAt;
                while (r8 < min) {
                    char charAt2 = string.charAt(r8);
                    if (charAt2 >= 128) {
                        break;
                    }
                    bArr[r8 + r6] = (byte) charAt2;
                    r8++;
                }
                int r62 = (r6 + r8) - writableSegment$okio.limit;
                writableSegment$okio.limit += r62;
                commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + r62);
            } else {
                if (charAt < 2048) {
                    Segment writableSegment$okio2 = commonWriteUtf8.writableSegment$okio(2);
                    writableSegment$okio2.data[writableSegment$okio2.limit] = (byte) ((charAt >> 6) | 192);
                    writableSegment$okio2.data[writableSegment$okio2.limit + 1] = (byte) ((charAt & '?') | 128);
                    writableSegment$okio2.limit += 2;
                    commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + 2);
                } else if (charAt < 55296 || charAt > 57343) {
                    Segment writableSegment$okio3 = commonWriteUtf8.writableSegment$okio(3);
                    writableSegment$okio3.data[writableSegment$okio3.limit] = (byte) ((charAt >> '\f') | 224);
                    writableSegment$okio3.data[writableSegment$okio3.limit + 1] = (byte) ((63 & (charAt >> 6)) | 128);
                    writableSegment$okio3.data[writableSegment$okio3.limit + 2] = (byte) ((charAt & '?') | 128);
                    writableSegment$okio3.limit += 3;
                    commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + 3);
                } else {
                    r8 = r14 + 1;
                    char charAt3 = r8 < r15 ? string.charAt(r8) : (char) 0;
                    if (charAt > 56319 || 56320 > charAt3 || 57343 < charAt3) {
                        commonWriteUtf8.writeByte(63);
                    } else {
                        int r2 = (((charAt & 1023) << 10) | (charAt3 & 1023)) + 65536;
                        Segment writableSegment$okio4 = commonWriteUtf8.writableSegment$okio(4);
                        writableSegment$okio4.data[writableSegment$okio4.limit] = (byte) ((r2 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                        writableSegment$okio4.data[writableSegment$okio4.limit + 1] = (byte) (((r2 >> 12) & 63) | 128);
                        writableSegment$okio4.data[writableSegment$okio4.limit + 2] = (byte) (((r2 >> 6) & 63) | 128);
                        writableSegment$okio4.data[writableSegment$okio4.limit + 3] = (byte) ((r2 & 63) | 128);
                        writableSegment$okio4.limit += 4;
                        commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + 4);
                        r14 += 2;
                    }
                }
                r14++;
            }
            r14 = r8;
        }
        return commonWriteUtf8;
    }

    public static final okio.Buffer commonWriteUtf8CodePoint(okio.Buffer commonWriteUtf8CodePoint, int r10) {
        Intrinsics.checkNotNullParameter(commonWriteUtf8CodePoint, "$this$commonWriteUtf8CodePoint");
        if (r10 < 128) {
            commonWriteUtf8CodePoint.writeByte(r10);
        } else if (r10 < 2048) {
            Segment writableSegment$okio = commonWriteUtf8CodePoint.writableSegment$okio(2);
            writableSegment$okio.data[writableSegment$okio.limit] = (byte) ((r10 >> 6) | 192);
            writableSegment$okio.data[writableSegment$okio.limit + 1] = (byte) ((r10 & 63) | 128);
            writableSegment$okio.limit += 2;
            commonWriteUtf8CodePoint.setSize$okio(commonWriteUtf8CodePoint.size() + 2);
        } else if (55296 <= r10 && 57343 >= r10) {
            commonWriteUtf8CodePoint.writeByte(63);
        } else if (r10 < 65536) {
            Segment writableSegment$okio2 = commonWriteUtf8CodePoint.writableSegment$okio(3);
            writableSegment$okio2.data[writableSegment$okio2.limit] = (byte) ((r10 >> 12) | 224);
            writableSegment$okio2.data[writableSegment$okio2.limit + 1] = (byte) (((r10 >> 6) & 63) | 128);
            writableSegment$okio2.data[writableSegment$okio2.limit + 2] = (byte) ((r10 & 63) | 128);
            writableSegment$okio2.limit += 3;
            commonWriteUtf8CodePoint.setSize$okio(commonWriteUtf8CodePoint.size() + 3);
        } else if (r10 <= 1114111) {
            Segment writableSegment$okio3 = commonWriteUtf8CodePoint.writableSegment$okio(4);
            writableSegment$okio3.data[writableSegment$okio3.limit] = (byte) ((r10 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
            writableSegment$okio3.data[writableSegment$okio3.limit + 1] = (byte) (((r10 >> 12) & 63) | 128);
            writableSegment$okio3.data[writableSegment$okio3.limit + 2] = (byte) (((r10 >> 6) & 63) | 128);
            writableSegment$okio3.data[writableSegment$okio3.limit + 3] = (byte) ((r10 & 63) | 128);
            writableSegment$okio3.limit += 4;
            commonWriteUtf8CodePoint.setSize$okio(commonWriteUtf8CodePoint.size() + 4);
        } else {
            throw new IllegalArgumentException("Unexpected code point: 0x" + Util.toHexString(r10));
        }
        return commonWriteUtf8CodePoint;
    }

    public static final long commonWriteAll(okio.Buffer commonWriteAll, Source source) {
        Intrinsics.checkNotNullParameter(commonWriteAll, "$this$commonWriteAll");
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long read = source.read(commonWriteAll, 8192);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    public static final okio.Buffer commonWrite(okio.Buffer commonWrite, Source source, long j) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        while (j > 0) {
            long read = source.read(commonWrite, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
        }
        return commonWrite;
    }

    public static final okio.Buffer commonWriteByte(okio.Buffer commonWriteByte, int r5) {
        Intrinsics.checkNotNullParameter(commonWriteByte, "$this$commonWriteByte");
        Segment writableSegment$okio = commonWriteByte.writableSegment$okio(1);
        byte[] bArr = writableSegment$okio.data;
        int r2 = writableSegment$okio.limit;
        writableSegment$okio.limit = r2 + 1;
        bArr[r2] = (byte) r5;
        commonWriteByte.setSize$okio(commonWriteByte.size() + 1);
        return commonWriteByte;
    }

    public static final okio.Buffer commonWriteShort(okio.Buffer commonWriteShort, int r6) {
        Intrinsics.checkNotNullParameter(commonWriteShort, "$this$commonWriteShort");
        Segment writableSegment$okio = commonWriteShort.writableSegment$okio(2);
        byte[] bArr = writableSegment$okio.data;
        int r2 = writableSegment$okio.limit;
        int r3 = r2 + 1;
        bArr[r2] = (byte) ((r6 >>> 8) & 255);
        bArr[r3] = (byte) (r6 & 255);
        writableSegment$okio.limit = r3 + 1;
        commonWriteShort.setSize$okio(commonWriteShort.size() + 2);
        return commonWriteShort;
    }

    public static final okio.Buffer commonWriteInt(okio.Buffer commonWriteInt, int r6) {
        Intrinsics.checkNotNullParameter(commonWriteInt, "$this$commonWriteInt");
        Segment writableSegment$okio = commonWriteInt.writableSegment$okio(4);
        byte[] bArr = writableSegment$okio.data;
        int r2 = writableSegment$okio.limit;
        int r3 = r2 + 1;
        bArr[r2] = (byte) ((r6 >>> 24) & 255);
        int r22 = r3 + 1;
        bArr[r3] = (byte) ((r6 >>> 16) & 255);
        int r32 = r22 + 1;
        bArr[r22] = (byte) ((r6 >>> 8) & 255);
        bArr[r32] = (byte) (r6 & 255);
        writableSegment$okio.limit = r32 + 1;
        commonWriteInt.setSize$okio(commonWriteInt.size() + 4);
        return commonWriteInt;
    }

    public static final okio.Buffer commonWriteLong(okio.Buffer commonWriteLong, long j) {
        Intrinsics.checkNotNullParameter(commonWriteLong, "$this$commonWriteLong");
        Segment writableSegment$okio = commonWriteLong.writableSegment$okio(8);
        byte[] bArr = writableSegment$okio.data;
        int r3 = writableSegment$okio.limit;
        int r4 = r3 + 1;
        bArr[r3] = (byte) ((j >>> 56) & 255);
        int r32 = r4 + 1;
        bArr[r4] = (byte) ((j >>> 48) & 255);
        int r42 = r32 + 1;
        bArr[r32] = (byte) ((j >>> 40) & 255);
        int r33 = r42 + 1;
        bArr[r42] = (byte) ((j >>> 32) & 255);
        int r43 = r33 + 1;
        bArr[r33] = (byte) ((j >>> 24) & 255);
        int r34 = r43 + 1;
        bArr[r43] = (byte) ((j >>> 16) & 255);
        int r44 = r34 + 1;
        bArr[r34] = (byte) ((j >>> 8) & 255);
        bArr[r44] = (byte) (j & 255);
        writableSegment$okio.limit = r44 + 1;
        commonWriteLong.setSize$okio(commonWriteLong.size() + 8);
        return commonWriteLong;
    }

    public static final void commonWrite(okio.Buffer commonWrite, okio.Buffer source, long j) {
        Segment segment;
        Segment segment2;
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(source != commonWrite)) {
            throw new IllegalArgumentException("source == this".toString());
        }
        Util.checkOffsetAndCount(source.size(), 0L, j);
        while (j > 0) {
            Segment segment3 = source.head;
            Intrinsics.checkNotNull(segment3);
            int r1 = segment3.limit;
            Intrinsics.checkNotNull(source.head);
            if (j < r1 - segment.pos) {
                if (commonWrite.head != null) {
                    Segment segment4 = commonWrite.head;
                    Intrinsics.checkNotNull(segment4);
                    segment2 = segment4.prev;
                } else {
                    segment2 = null;
                }
                if (segment2 != null && segment2.owner) {
                    if ((segment2.limit + j) - (segment2.shared ? 0 : segment2.pos) <= 8192) {
                        Segment segment5 = source.head;
                        Intrinsics.checkNotNull(segment5);
                        segment5.writeTo(segment2, (int) j);
                        source.setSize$okio(source.size() - j);
                        commonWrite.setSize$okio(commonWrite.size() + j);
                        return;
                    }
                }
                Segment segment6 = source.head;
                Intrinsics.checkNotNull(segment6);
                source.head = segment6.split((int) j);
            }
            Segment segment7 = source.head;
            Intrinsics.checkNotNull(segment7);
            long j2 = segment7.limit - segment7.pos;
            source.head = segment7.pop();
            if (commonWrite.head == null) {
                commonWrite.head = segment7;
                segment7.prev = segment7;
                segment7.next = segment7.prev;
            } else {
                Segment segment8 = commonWrite.head;
                Intrinsics.checkNotNull(segment8);
                Segment segment9 = segment8.prev;
                Intrinsics.checkNotNull(segment9);
                segment9.push(segment7).compact();
            }
            source.setSize$okio(source.size() - j2);
            commonWrite.setSize$okio(commonWrite.size() + j2);
            j -= j2;
        }
    }

    public static final long commonRead(okio.Buffer commonRead, okio.Buffer sink, long j) {
        Intrinsics.checkNotNullParameter(commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (!(j >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        } else if (commonRead.size() == 0) {
            return -1L;
        } else {
            if (j > commonRead.size()) {
                j = commonRead.size();
            }
            sink.write(commonRead, j);
            return j;
        }
    }

    public static final long commonIndexOf(okio.Buffer commonIndexOf, byte b, long j, long j2) {
        Segment segment;
        int r11;
        Intrinsics.checkNotNullParameter(commonIndexOf, "$this$commonIndexOf");
        long j3 = 0;
        if (!(0 <= j && j2 >= j)) {
            throw new IllegalArgumentException(("size=" + commonIndexOf.size() + " fromIndex=" + j + " toIndex=" + j2).toString());
        }
        if (j2 > commonIndexOf.size()) {
            j2 = commonIndexOf.size();
        }
        if (j != j2 && (segment = commonIndexOf.head) != null) {
            if (commonIndexOf.size() - j < j) {
                j3 = commonIndexOf.size();
                while (j3 > j) {
                    segment = segment.prev;
                    Intrinsics.checkNotNull(segment);
                    j3 -= segment.limit - segment.pos;
                }
                if (segment != null) {
                    while (j3 < j2) {
                        byte[] bArr = segment.data;
                        int min = (int) Math.min(segment.limit, (segment.pos + j2) - j3);
                        r11 = (int) ((segment.pos + j) - j3);
                        while (r11 < min) {
                            if (bArr[r11] != b) {
                                r11++;
                            }
                        }
                        j3 += segment.limit - segment.pos;
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j = j3;
                    }
                }
                return -1L;
            }
            while (true) {
                long j4 = (segment.limit - segment.pos) + j3;
                if (j4 > j) {
                    break;
                }
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j3 = j4;
            }
            if (segment != null) {
                while (j3 < j2) {
                    byte[] bArr2 = segment.data;
                    int min2 = (int) Math.min(segment.limit, (segment.pos + j2) - j3);
                    r11 = (int) ((segment.pos + j) - j3);
                    while (r11 < min2) {
                        if (bArr2[r11] != b) {
                            r11++;
                        }
                    }
                    j3 += segment.limit - segment.pos;
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    j = j3;
                }
            }
            return -1L;
            return (r11 - segment.pos) + j3;
        }
        return -1L;
    }

    public static final long commonIndexOf(okio.Buffer commonIndexOf, ByteString bytes, long j) {
        long j2;
        int r1;
        long j3 = j;
        Intrinsics.checkNotNullParameter(commonIndexOf, "$this$commonIndexOf");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (bytes.size() > 0) {
            long j4 = 0;
            if (!(j3 >= 0)) {
                throw new IllegalArgumentException(("fromIndex < 0: " + j3).toString());
            }
            Segment segment = commonIndexOf.head;
            if (segment != null) {
                if (commonIndexOf.size() - j3 < j3) {
                    j2 = commonIndexOf.size();
                    while (j2 > j3) {
                        segment = segment.prev;
                        Intrinsics.checkNotNull(segment);
                        j2 -= segment.limit - segment.pos;
                    }
                    if (segment != null) {
                        byte[] internalArray$okio = bytes.internalArray$okio();
                        byte b = internalArray$okio[0];
                        int size = bytes.size();
                        long size2 = (commonIndexOf.size() - size) + 1;
                        while (j2 < size2) {
                            byte[] bArr = segment.data;
                            int min = (int) Math.min(segment.limit, (segment.pos + size2) - j2);
                            r1 = (int) ((segment.pos + j3) - j2);
                            while (r1 < min) {
                                if (bArr[r1] != b || !rangeEquals(segment, r1 + 1, internalArray$okio, 1, size)) {
                                    r1++;
                                }
                            }
                            j2 += segment.limit - segment.pos;
                            segment = segment.next;
                            Intrinsics.checkNotNull(segment);
                            j3 = j2;
                        }
                        return -1L;
                    }
                    return -1L;
                }
                while (true) {
                    long j5 = (segment.limit - segment.pos) + j4;
                    if (j5 > j3) {
                        break;
                    }
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    j4 = j5;
                }
                if (segment != null) {
                    byte[] internalArray$okio2 = bytes.internalArray$okio();
                    byte b2 = internalArray$okio2[0];
                    int size3 = bytes.size();
                    long size4 = (commonIndexOf.size() - size3) + 1;
                    j2 = j4;
                    while (j2 < size4) {
                        byte[] bArr2 = segment.data;
                        long j6 = size4;
                        int min2 = (int) Math.min(segment.limit, (segment.pos + size4) - j2);
                        r1 = (int) ((segment.pos + j3) - j2);
                        while (r1 < min2) {
                            if (bArr2[r1] == b2 && rangeEquals(segment, r1 + 1, internalArray$okio2, 1, size3)) {
                            }
                            r1++;
                        }
                        j2 += segment.limit - segment.pos;
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        size4 = j6;
                        j3 = j2;
                    }
                    return -1L;
                }
                return -1L;
                return (r1 - segment.pos) + j2;
            }
            return -1L;
        }
        throw new IllegalArgumentException("bytes is empty".toString());
    }

    public static final boolean commonRangeEquals(okio.Buffer commonRangeEquals, long j, ByteString bytes, int r10, int r11) {
        Intrinsics.checkNotNullParameter(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (j < 0 || r10 < 0 || r11 < 0 || commonRangeEquals.size() - j < r11 || bytes.size() - r10 < r11) {
            return false;
        }
        for (int r1 = 0; r1 < r11; r1++) {
            if (commonRangeEquals.getByte(r1 + j) != bytes.getByte(r10 + r1)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean commonEquals(okio.Buffer commonEquals, Object obj) {
        Intrinsics.checkNotNullParameter(commonEquals, "$this$commonEquals");
        if (commonEquals == obj) {
            return true;
        }
        if (obj instanceof okio.Buffer) {
            okio.Buffer buffer = (okio.Buffer) obj;
            if (commonEquals.size() != buffer.size()) {
                return false;
            }
            if (commonEquals.size() == 0) {
                return true;
            }
            Segment segment = commonEquals.head;
            Intrinsics.checkNotNull(segment);
            Segment segment2 = buffer.head;
            Intrinsics.checkNotNull(segment2);
            int r5 = segment.pos;
            int r6 = segment2.pos;
            long j = 0;
            while (j < commonEquals.size()) {
                long min = Math.min(segment.limit - r5, segment2.limit - r6);
                long j2 = 0;
                while (j2 < min) {
                    int r16 = r5 + 1;
                    int r17 = r6 + 1;
                    if (segment.data[r5] != segment2.data[r6]) {
                        return false;
                    }
                    j2++;
                    r5 = r16;
                    r6 = r17;
                }
                if (r5 == segment.limit) {
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    r5 = segment.pos;
                }
                if (r6 == segment2.limit) {
                    segment2 = segment2.next;
                    Intrinsics.checkNotNull(segment2);
                    r6 = segment2.pos;
                }
                j += min;
            }
            return true;
        }
        return false;
    }

    public static final int commonHashCode(okio.Buffer commonHashCode) {
        Intrinsics.checkNotNullParameter(commonHashCode, "$this$commonHashCode");
        Segment segment = commonHashCode.head;
        if (segment != null) {
            int r1 = 1;
            do {
                int r3 = segment.limit;
                for (int r2 = segment.pos; r2 < r3; r2++) {
                    r1 = (r1 * 31) + segment.data[r2];
                }
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
            } while (segment != commonHashCode.head);
            return r1;
        }
        return 0;
    }

    public static final okio.Buffer commonCopy(okio.Buffer commonCopy) {
        Intrinsics.checkNotNullParameter(commonCopy, "$this$commonCopy");
        okio.Buffer buffer = new okio.Buffer();
        if (commonCopy.size() == 0) {
            return buffer;
        }
        Segment segment = commonCopy.head;
        Intrinsics.checkNotNull(segment);
        Segment sharedCopy = segment.sharedCopy();
        buffer.head = sharedCopy;
        sharedCopy.prev = buffer.head;
        sharedCopy.next = sharedCopy.prev;
        for (Segment segment2 = segment.next; segment2 != segment; segment2 = segment2.next) {
            Segment segment3 = sharedCopy.prev;
            Intrinsics.checkNotNull(segment3);
            Intrinsics.checkNotNull(segment2);
            segment3.push(segment2.sharedCopy());
        }
        buffer.setSize$okio(commonCopy.size());
        return buffer;
    }

    public static final ByteString commonSnapshot(okio.Buffer commonSnapshot) {
        Intrinsics.checkNotNullParameter(commonSnapshot, "$this$commonSnapshot");
        if (!(commonSnapshot.size() <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalStateException(("size > Int.MAX_VALUE: " + commonSnapshot.size()).toString());
        }
        return commonSnapshot.snapshot((int) commonSnapshot.size());
    }

    public static final ByteString commonSnapshot(okio.Buffer commonSnapshot, int r7) {
        Intrinsics.checkNotNullParameter(commonSnapshot, "$this$commonSnapshot");
        if (r7 == 0) {
            return ByteString.EMPTY;
        }
        Util.checkOffsetAndCount(commonSnapshot.size(), 0L, r7);
        Segment segment = commonSnapshot.head;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        while (r2 < r7) {
            Intrinsics.checkNotNull(segment);
            if (segment.limit == segment.pos) {
                throw new AssertionError("s.limit == s.pos");
            }
            r2 += segment.limit - segment.pos;
            r3++;
            segment = segment.next;
        }
        byte[][] bArr = new byte[r3];
        int[] r22 = new int[r3 * 2];
        Segment segment2 = commonSnapshot.head;
        int r32 = 0;
        while (r1 < r7) {
            Intrinsics.checkNotNull(segment2);
            bArr[r32] = segment2.data;
            r1 += segment2.limit - segment2.pos;
            r22[r32] = Math.min(r1, r7);
            r22[bArr.length + r32] = segment2.pos;
            segment2.shared = true;
            r32++;
            segment2 = segment2.next;
        }
        return new SegmentedByteString(bArr, r22);
    }

    public static final long commonIndexOfElement(okio.Buffer commonIndexOfElement, ByteString targetBytes, long j) {
        int r13;
        int r11;
        Intrinsics.checkNotNullParameter(commonIndexOfElement, "$this$commonIndexOfElement");
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        long j2 = 0;
        if (!(j >= 0)) {
            throw new IllegalArgumentException(("fromIndex < 0: " + j).toString());
        }
        Segment segment = commonIndexOfElement.head;
        if (segment != null) {
            if (commonIndexOfElement.size() - j < j) {
                j2 = commonIndexOfElement.size();
                while (j2 > j) {
                    segment = segment.prev;
                    Intrinsics.checkNotNull(segment);
                    j2 -= segment.limit - segment.pos;
                }
                if (segment != null) {
                    if (targetBytes.size() == 2) {
                        byte b = targetBytes.getByte(0);
                        byte b2 = targetBytes.getByte(1);
                        while (j2 < commonIndexOfElement.size()) {
                            byte[] bArr = segment.data;
                            r13 = (int) ((segment.pos + j) - j2);
                            int r14 = segment.limit;
                            while (r13 < r14) {
                                byte b3 = bArr[r13];
                                if (b3 != b && b3 != b2) {
                                    r13++;
                                }
                                r11 = segment.pos;
                            }
                            j2 += segment.limit - segment.pos;
                            segment = segment.next;
                            Intrinsics.checkNotNull(segment);
                            j = j2;
                        }
                    } else {
                        byte[] internalArray$okio = targetBytes.internalArray$okio();
                        while (j2 < commonIndexOfElement.size()) {
                            byte[] bArr2 = segment.data;
                            r13 = (int) ((segment.pos + j) - j2);
                            int r142 = segment.limit;
                            while (r13 < r142) {
                                byte b4 = bArr2[r13];
                                for (byte b5 : internalArray$okio) {
                                    if (b4 == b5) {
                                        r11 = segment.pos;
                                    }
                                }
                                r13++;
                            }
                            j2 += segment.limit - segment.pos;
                            segment = segment.next;
                            Intrinsics.checkNotNull(segment);
                            j = j2;
                        }
                    }
                }
                return -1L;
            }
            while (true) {
                long j3 = (segment.limit - segment.pos) + j2;
                if (j3 > j) {
                    break;
                }
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j2 = j3;
            }
            if (segment != null) {
                if (targetBytes.size() == 2) {
                    byte b6 = targetBytes.getByte(0);
                    byte b7 = targetBytes.getByte(1);
                    while (j2 < commonIndexOfElement.size()) {
                        byte[] bArr3 = segment.data;
                        r13 = (int) ((segment.pos + j) - j2);
                        int r143 = segment.limit;
                        while (r13 < r143) {
                            byte b8 = bArr3[r13];
                            if (b8 != b6 && b8 != b7) {
                                r13++;
                            }
                            r11 = segment.pos;
                        }
                        j2 += segment.limit - segment.pos;
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j = j2;
                    }
                } else {
                    byte[] internalArray$okio2 = targetBytes.internalArray$okio();
                    while (j2 < commonIndexOfElement.size()) {
                        byte[] bArr4 = segment.data;
                        r13 = (int) ((segment.pos + j) - j2);
                        int r144 = segment.limit;
                        while (r13 < r144) {
                            byte b9 = bArr4[r13];
                            for (byte b10 : internalArray$okio2) {
                                if (b9 == b10) {
                                    r11 = segment.pos;
                                }
                            }
                            r13++;
                        }
                        j2 += segment.limit - segment.pos;
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j = j2;
                    }
                }
            }
            return -1L;
            return (r13 - r11) + j2;
        }
        return -1L;
    }
}
