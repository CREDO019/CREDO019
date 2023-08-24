package okio.internal;

import com.facebook.imagepipeline.producers.DecodeProducer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.Segment;
import okio.Util;

@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000R\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0017\u0010\u0006\u001a\u00020\u0007*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0080\b\u001a\r\u0010\u000b\u001a\u00020\u0001*\u00020\bH\u0080\b\u001a\r\u0010\f\u001a\u00020\u0001*\u00020\bH\u0080\b\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0001H\u0080\b\u001a-\u0010\u0010\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0080\b\u001a-\u0010\u0010\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0080\b\u001a\u001d\u0010\u0016\u001a\u00020\u0015*\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001H\u0080\b\u001a\r\u0010\u0019\u001a\u00020\u0012*\u00020\bH\u0080\b\u001a%\u0010\u001a\u001a\u00020\u001b*\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0080\b\u001a]\u0010\u001e\u001a\u00020\u001b*\u00020\b2K\u0010\u001f\u001aG\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u001b0 H\u0080\bø\u0001\u0000\u001aj\u0010\u001e\u001a\u00020\u001b*\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012K\u0010\u001f\u001aG\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u001b0 H\u0082\b\u001a\u0014\u0010$\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0001H\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006%"}, m183d2 = {"binarySearch", "", "", "value", "fromIndex", "toIndex", "commonEquals", "", "Lokio/SegmentedByteString;", "other", "", "commonGetSize", "commonHashCode", "commonInternalGet", "", "pos", "commonRangeEquals", "offset", "", "otherOffset", DecodeProducer.EXTRA_BITMAP_BYTES, "Lokio/ByteString;", "commonSubstring", "beginIndex", "endIndex", "commonToByteArray", "commonWrite", "", "buffer", "Lokio/Buffer;", "forEachSegment", "action", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "data", "segment", "okio"}, m182k = 2, m181mv = {1, 4, 0})
/* renamed from: okio.internal.SegmentedByteStringKt */
/* loaded from: classes5.dex */
public final class SegmentedByteString {
    public static final int binarySearch(int[] binarySearch, int r3, int r4, int r5) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        int r52 = r5 - 1;
        while (r4 <= r52) {
            int r0 = (r4 + r52) >>> 1;
            int r1 = binarySearch[r0];
            if (r1 < r3) {
                r4 = r0 + 1;
            } else if (r1 <= r3) {
                return r0;
            } else {
                r52 = r0 - 1;
            }
        }
        return (-r4) - 1;
    }

    public static final int segment(okio.SegmentedByteString segment, int r3) {
        Intrinsics.checkNotNullParameter(segment, "$this$segment");
        int binarySearch = binarySearch(segment.getDirectory$okio(), r3 + 1, 0, segment.getSegments$okio().length);
        return binarySearch >= 0 ? binarySearch : ~binarySearch;
    }

    public static final void forEachSegment(okio.SegmentedByteString forEachSegment, Function3<? super byte[], ? super Integer, ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachSegment, "$this$forEachSegment");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = forEachSegment.getSegments$okio().length;
        int r1 = 0;
        int r2 = 0;
        while (r1 < length) {
            int r3 = forEachSegment.getDirectory$okio()[length + r1];
            int r4 = forEachSegment.getDirectory$okio()[r1];
            action.invoke(forEachSegment.getSegments$okio()[r1], Integer.valueOf(r3), Integer.valueOf(r4 - r2));
            r1++;
            r2 = r4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void forEachSegment(okio.SegmentedByteString segmentedByteString, int r6, int r7, Function3<? super byte[], ? super Integer, ? super Integer, Unit> function3) {
        int segment = segment(segmentedByteString, r6);
        while (r6 < r7) {
            int r1 = segment == 0 ? 0 : segmentedByteString.getDirectory$okio()[segment - 1];
            int r3 = segmentedByteString.getDirectory$okio()[segmentedByteString.getSegments$okio().length + segment];
            int min = Math.min(r7, (segmentedByteString.getDirectory$okio()[segment] - r1) + r1) - r6;
            function3.invoke(segmentedByteString.getSegments$okio()[segment], Integer.valueOf(r3 + (r6 - r1)), Integer.valueOf(min));
            r6 += min;
            segment++;
        }
    }

    public static final ByteString commonSubstring(okio.SegmentedByteString commonSubstring, int r13, int r14) {
        Intrinsics.checkNotNullParameter(commonSubstring, "$this$commonSubstring");
        if (!(r13 >= 0)) {
            throw new IllegalArgumentException(("beginIndex=" + r13 + " < 0").toString());
        }
        if (!(r14 <= commonSubstring.size())) {
            throw new IllegalArgumentException(("endIndex=" + r14 + " > length(" + commonSubstring.size() + ')').toString());
        }
        int r2 = r14 - r13;
        if (!(r2 >= 0)) {
            throw new IllegalArgumentException(("endIndex=" + r14 + " < beginIndex=" + r13).toString());
        } else if (r13 == 0 && r14 == commonSubstring.size()) {
            return commonSubstring;
        } else {
            if (r13 == r14) {
                return ByteString.EMPTY;
            }
            int segment = segment(commonSubstring, r13);
            int segment2 = segment(commonSubstring, r14 - 1);
            byte[][] bArr = (byte[][]) ArraysKt.copyOfRange(commonSubstring.getSegments$okio(), segment, segment2 + 1);
            byte[][] bArr2 = bArr;
            int[] r6 = new int[bArr2.length * 2];
            if (segment <= segment2) {
                int r8 = segment;
                int r7 = 0;
                while (true) {
                    r6[r7] = Math.min(commonSubstring.getDirectory$okio()[r8] - r13, r2);
                    int r9 = r7 + 1;
                    r6[r7 + bArr2.length] = commonSubstring.getDirectory$okio()[commonSubstring.getSegments$okio().length + r8];
                    if (r8 == segment2) {
                        break;
                    }
                    r8++;
                    r7 = r9;
                }
            }
            int r0 = segment != 0 ? commonSubstring.getDirectory$okio()[segment - 1] : 0;
            int length = bArr2.length;
            r6[length] = r6[length] + (r13 - r0);
            return new okio.SegmentedByteString(bArr, r6);
        }
    }

    public static final byte commonInternalGet(okio.SegmentedByteString commonInternalGet, int r8) {
        Intrinsics.checkNotNullParameter(commonInternalGet, "$this$commonInternalGet");
        Util.checkOffsetAndCount(commonInternalGet.getDirectory$okio()[commonInternalGet.getSegments$okio().length - 1], r8, 1L);
        int segment = segment(commonInternalGet, r8);
        return commonInternalGet.getSegments$okio()[segment][(r8 - (segment == 0 ? 0 : commonInternalGet.getDirectory$okio()[segment - 1])) + commonInternalGet.getDirectory$okio()[commonInternalGet.getSegments$okio().length + segment]];
    }

    public static final int commonGetSize(okio.SegmentedByteString commonGetSize) {
        Intrinsics.checkNotNullParameter(commonGetSize, "$this$commonGetSize");
        return commonGetSize.getDirectory$okio()[commonGetSize.getSegments$okio().length - 1];
    }

    public static final byte[] commonToByteArray(okio.SegmentedByteString commonToByteArray) {
        Intrinsics.checkNotNullParameter(commonToByteArray, "$this$commonToByteArray");
        byte[] bArr = new byte[commonToByteArray.size()];
        int length = commonToByteArray.getSegments$okio().length;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        while (r2 < length) {
            int r5 = commonToByteArray.getDirectory$okio()[length + r2];
            int r6 = commonToByteArray.getDirectory$okio()[r2];
            int r32 = r6 - r3;
            ArraysKt.copyInto(commonToByteArray.getSegments$okio()[r2], bArr, r4, r5, r5 + r32);
            r4 += r32;
            r2++;
            r3 = r6;
        }
        return bArr;
    }

    public static final boolean commonRangeEquals(okio.SegmentedByteString commonRangeEquals, int r7, ByteString other, int r9, int r10) {
        Intrinsics.checkNotNullParameter(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        if (r7 < 0 || r7 > commonRangeEquals.size() - r10) {
            return false;
        }
        int r102 = r10 + r7;
        int segment = segment(commonRangeEquals, r7);
        while (r7 < r102) {
            int r2 = segment == 0 ? 0 : commonRangeEquals.getDirectory$okio()[segment - 1];
            int r4 = commonRangeEquals.getDirectory$okio()[commonRangeEquals.getSegments$okio().length + segment];
            int min = Math.min(r102, (commonRangeEquals.getDirectory$okio()[segment] - r2) + r2) - r7;
            if (!other.rangeEquals(r9, commonRangeEquals.getSegments$okio()[segment], r4 + (r7 - r2), min)) {
                return false;
            }
            r9 += min;
            r7 += min;
            segment++;
        }
        return true;
    }

    public static final boolean commonRangeEquals(okio.SegmentedByteString commonRangeEquals, int r7, byte[] other, int r9, int r10) {
        Intrinsics.checkNotNullParameter(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        if (r7 < 0 || r7 > commonRangeEquals.size() - r10 || r9 < 0 || r9 > other.length - r10) {
            return false;
        }
        int r102 = r10 + r7;
        int segment = segment(commonRangeEquals, r7);
        while (r7 < r102) {
            int r2 = segment == 0 ? 0 : commonRangeEquals.getDirectory$okio()[segment - 1];
            int r4 = commonRangeEquals.getDirectory$okio()[commonRangeEquals.getSegments$okio().length + segment];
            int min = Math.min(r102, (commonRangeEquals.getDirectory$okio()[segment] - r2) + r2) - r7;
            if (!Util.arrayRangeEquals(commonRangeEquals.getSegments$okio()[segment], r4 + (r7 - r2), other, r9, min)) {
                return false;
            }
            r9 += min;
            r7 += min;
            segment++;
        }
        return true;
    }

    public static final boolean commonEquals(okio.SegmentedByteString commonEquals, Object obj) {
        Intrinsics.checkNotNullParameter(commonEquals, "$this$commonEquals");
        if (obj == commonEquals) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == commonEquals.size() && commonEquals.rangeEquals(0, byteString, 0, commonEquals.size())) {
                return true;
            }
        }
        return false;
    }

    public static final int commonHashCode(okio.SegmentedByteString commonHashCode) {
        Intrinsics.checkNotNullParameter(commonHashCode, "$this$commonHashCode");
        int hashCode$okio = commonHashCode.getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int length = commonHashCode.getSegments$okio().length;
        int r1 = 0;
        int r2 = 0;
        int r3 = 1;
        while (r1 < length) {
            int r4 = commonHashCode.getDirectory$okio()[length + r1];
            int r5 = commonHashCode.getDirectory$okio()[r1];
            byte[] bArr = commonHashCode.getSegments$okio()[r1];
            int r22 = (r5 - r2) + r4;
            while (r4 < r22) {
                r3 = (r3 * 31) + bArr[r4];
                r4++;
            }
            r1++;
            r2 = r5;
        }
        commonHashCode.setHashCode$okio(r3);
        return r3;
    }

    public static final void commonWrite(okio.SegmentedByteString commonWrite, Buffer buffer, int r13, int r14) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int r0 = r13 + r14;
        int segment = segment(commonWrite, r13);
        while (r13 < r0) {
            int r2 = segment == 0 ? 0 : commonWrite.getDirectory$okio()[segment - 1];
            int r4 = commonWrite.getDirectory$okio()[commonWrite.getSegments$okio().length + segment];
            int min = Math.min(r0, (commonWrite.getDirectory$okio()[segment] - r2) + r2) - r13;
            int r7 = r4 + (r13 - r2);
            Segment segment2 = new Segment(commonWrite.getSegments$okio()[segment], r7, r7 + min, true, false);
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
}
