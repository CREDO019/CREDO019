package okio.internal;

import com.facebook.imagepipeline.producers.DecodeProducer;
import com.google.common.base.Ascii;
import java.util.Arrays;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Base64;
import okio.Buffer;
import okio.Platform;
import okio.SegmentedByteString;
import okio.Util;
import org.apache.commons.lang3.StringUtils;

@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0002\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0080\b\u001a\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\r\u0010\u000f\u001a\u00020\u0010*\u00020\nH\u0080\b\u001a\r\u0010\u0011\u001a\u00020\u0010*\u00020\nH\u0080\b\u001a\u0015\u0010\u0012\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0080\b\u001a\u000f\u0010\u0014\u001a\u0004\u0018\u00010\n*\u00020\u0010H\u0080\b\u001a\r\u0010\u0015\u001a\u00020\n*\u00020\u0010H\u0080\b\u001a\u0014\u0010\u0016\u001a\u00020\n*\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0010H\u0000\u001a\r\u0010\u0018\u001a\u00020\n*\u00020\u0010H\u0080\b\u001a\u0015\u0010\u0019\u001a\u00020\u001a*\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010\u0019\u001a\u00020\u001a*\u00020\n2\u0006\u0010\u001b\u001a\u00020\nH\u0080\b\u001a\u0017\u0010\u001c\u001a\u00020\u001a*\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u001dH\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\u001f*\u00020\n2\u0006\u0010 \u001a\u00020\u0005H\u0080\b\u001a\r\u0010!\u001a\u00020\u0005*\u00020\nH\u0080\b\u001a\r\u0010\"\u001a\u00020\u0005*\u00020\nH\u0080\b\u001a\r\u0010#\u001a\u00020\u0010*\u00020\nH\u0080\b\u001a\u001d\u0010$\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0005H\u0080\b\u001a\r\u0010&\u001a\u00020\u0007*\u00020\nH\u0080\b\u001a\u001d\u0010'\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0005H\u0080\b\u001a\u001d\u0010'\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010%\u001a\u00020\u0005H\u0080\b\u001a-\u0010(\u001a\u00020\u001a*\u00020\n2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005H\u0080\b\u001a-\u0010(\u001a\u00020\u001a*\u00020\n2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005H\u0080\b\u001a\u0014\u0010,\u001a\u00020\n*\u00020-2\u0006\u0010\u0017\u001a\u00020\u0010H\u0000\u001a\u0015\u0010.\u001a\u00020\u001a*\u00020\n2\u0006\u0010/\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010.\u001a\u00020\u001a*\u00020\n2\u0006\u0010/\u001a\u00020\nH\u0080\b\u001a\u001d\u00100\u001a\u00020\n*\u00020\n2\u0006\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u0005H\u0080\b\u001a\r\u00103\u001a\u00020\n*\u00020\nH\u0080\b\u001a\r\u00104\u001a\u00020\n*\u00020\nH\u0080\b\u001a\r\u00105\u001a\u00020\u0007*\u00020\nH\u0080\b\u001a\u001d\u00106\u001a\u00020\n*\u00020\u00072\u0006\u0010)\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005H\u0080\b\u001a\r\u00107\u001a\u00020\u0010*\u00020\nH\u0080\b\u001a\r\u00108\u001a\u00020\u0010*\u00020\nH\u0080\b\u001a$\u00109\u001a\u00020:*\u00020\n2\u0006\u0010;\u001a\u00020<2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006="}, m183d2 = {"HEX_DIGIT_CHARS", "", "getHEX_DIGIT_CHARS", "()[C", "codePointIndexToCharIndex", "", "s", "", "codePointCount", "commonOf", "Lokio/ByteString;", "data", "decodeHexDigit", "c", "", "commonBase64", "", "commonBase64Url", "commonCompareTo", "other", "commonDecodeBase64", "commonDecodeHex", "commonDigest", "algorithm", "commonEncodeUtf8", "commonEndsWith", "", "suffix", "commonEquals", "", "commonGetByte", "", "pos", "commonGetSize", "commonHashCode", "commonHex", "commonIndexOf", "fromIndex", "commonInternalArray", "commonLastIndexOf", "commonRangeEquals", "offset", "otherOffset", DecodeProducer.EXTRA_BITMAP_BYTES, "commonSegmentDigest", "Lokio/SegmentedByteString;", "commonStartsWith", "prefix", "commonSubstring", "beginIndex", "endIndex", "commonToAsciiLowercase", "commonToAsciiUppercase", "commonToByteArray", "commonToByteString", "commonToString", "commonUtf8", "commonWrite", "", "buffer", "Lokio/Buffer;", "okio"}, m182k = 2, m181mv = {1, 4, 0})
/* renamed from: okio.internal.ByteStringKt */
/* loaded from: classes5.dex */
public final class ByteString {
    private static final char[] HEX_DIGIT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final String commonUtf8(okio.ByteString commonUtf8) {
        Intrinsics.checkNotNullParameter(commonUtf8, "$this$commonUtf8");
        String utf8$okio = commonUtf8.getUtf8$okio();
        if (utf8$okio == null) {
            String utf8String = Platform.toUtf8String(commonUtf8.internalArray$okio());
            commonUtf8.setUtf8$okio(utf8String);
            return utf8String;
        }
        return utf8$okio;
    }

    public static final String commonBase64(okio.ByteString commonBase64) {
        Intrinsics.checkNotNullParameter(commonBase64, "$this$commonBase64");
        return Base64.encodeBase64$default(commonBase64.getData$okio(), null, 1, null);
    }

    public static final String commonBase64Url(okio.ByteString commonBase64Url) {
        Intrinsics.checkNotNullParameter(commonBase64Url, "$this$commonBase64Url");
        return Base64.encodeBase64(commonBase64Url.getData$okio(), Base64.getBASE64_URL_SAFE());
    }

    public static final char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }

    public static final String commonHex(okio.ByteString commonHex) {
        byte[] data$okio;
        Intrinsics.checkNotNullParameter(commonHex, "$this$commonHex");
        char[] cArr = new char[commonHex.getData$okio().length * 2];
        int r3 = 0;
        for (byte b : commonHex.getData$okio()) {
            int r5 = r3 + 1;
            cArr[r3] = getHEX_DIGIT_CHARS()[(b >> 4) & 15];
            r3 = r5 + 1;
            cArr[r5] = getHEX_DIGIT_CHARS()[b & Ascii.f1128SI];
        }
        return new String(cArr);
    }

    public static final okio.ByteString commonDigest(okio.ByteString commonDigest, String algorithm) {
        Intrinsics.checkNotNullParameter(commonDigest, "$this$commonDigest");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        HashFunction newHashFunction = HashFunctionKt.newHashFunction(algorithm);
        newHashFunction.update(commonDigest.getData$okio(), 0, commonDigest.size());
        return new okio.ByteString(newHashFunction.digest());
    }

    public static final okio.ByteString commonSegmentDigest(SegmentedByteString commonSegmentDigest, String algorithm) {
        Intrinsics.checkNotNullParameter(commonSegmentDigest, "$this$commonSegmentDigest");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        HashFunction newHashFunction = HashFunctionKt.newHashFunction(algorithm);
        int length = commonSegmentDigest.getSegments$okio().length;
        int r1 = 0;
        int r2 = 0;
        while (r1 < length) {
            int r3 = commonSegmentDigest.getDirectory$okio()[length + r1];
            int r4 = commonSegmentDigest.getDirectory$okio()[r1];
            newHashFunction.update(commonSegmentDigest.getSegments$okio()[r1], r3, r4 - r2);
            r1++;
            r2 = r4;
        }
        return new okio.ByteString(newHashFunction.digest());
    }

    public static final okio.ByteString commonToAsciiLowercase(okio.ByteString commonToAsciiLowercase) {
        byte b;
        Intrinsics.checkNotNullParameter(commonToAsciiLowercase, "$this$commonToAsciiLowercase");
        for (int r0 = 0; r0 < commonToAsciiLowercase.getData$okio().length; r0++) {
            byte b2 = commonToAsciiLowercase.getData$okio()[r0];
            byte b3 = (byte) 65;
            if (b2 >= b3 && b2 <= (b = (byte) 90)) {
                byte[] data$okio = commonToAsciiLowercase.getData$okio();
                byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
                copyOf[r0] = (byte) (b2 + 32);
                for (int r4 = r0 + 1; r4 < copyOf.length; r4++) {
                    byte b4 = copyOf[r4];
                    if (b4 >= b3 && b4 <= b) {
                        copyOf[r4] = (byte) (b4 + 32);
                    }
                }
                return new okio.ByteString(copyOf);
            }
        }
        return commonToAsciiLowercase;
    }

    public static final okio.ByteString commonToAsciiUppercase(okio.ByteString commonToAsciiUppercase) {
        byte b;
        Intrinsics.checkNotNullParameter(commonToAsciiUppercase, "$this$commonToAsciiUppercase");
        for (int r0 = 0; r0 < commonToAsciiUppercase.getData$okio().length; r0++) {
            byte b2 = commonToAsciiUppercase.getData$okio()[r0];
            byte b3 = (byte) 97;
            if (b2 >= b3 && b2 <= (b = (byte) 122)) {
                byte[] data$okio = commonToAsciiUppercase.getData$okio();
                byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
                copyOf[r0] = (byte) (b2 - 32);
                for (int r4 = r0 + 1; r4 < copyOf.length; r4++) {
                    byte b4 = copyOf[r4];
                    if (b4 >= b3 && b4 <= b) {
                        copyOf[r4] = (byte) (b4 - 32);
                    }
                }
                return new okio.ByteString(copyOf);
            }
        }
        return commonToAsciiUppercase;
    }

    public static final okio.ByteString commonSubstring(okio.ByteString commonSubstring, int r4, int r5) {
        Intrinsics.checkNotNullParameter(commonSubstring, "$this$commonSubstring");
        if (!(r4 >= 0)) {
            throw new IllegalArgumentException("beginIndex < 0".toString());
        }
        if (r5 <= commonSubstring.getData$okio().length) {
            if (r5 - r4 >= 0) {
                return (r4 == 0 && r5 == commonSubstring.getData$okio().length) ? commonSubstring : new okio.ByteString(ArraysKt.copyOfRange(commonSubstring.getData$okio(), r4, r5));
            }
            throw new IllegalArgumentException("endIndex < beginIndex".toString());
        }
        throw new IllegalArgumentException(("endIndex > length(" + commonSubstring.getData$okio().length + ')').toString());
    }

    public static final byte commonGetByte(okio.ByteString commonGetByte, int r2) {
        Intrinsics.checkNotNullParameter(commonGetByte, "$this$commonGetByte");
        return commonGetByte.getData$okio()[r2];
    }

    public static final int commonGetSize(okio.ByteString commonGetSize) {
        Intrinsics.checkNotNullParameter(commonGetSize, "$this$commonGetSize");
        return commonGetSize.getData$okio().length;
    }

    public static final byte[] commonToByteArray(okio.ByteString commonToByteArray) {
        Intrinsics.checkNotNullParameter(commonToByteArray, "$this$commonToByteArray");
        byte[] data$okio = commonToByteArray.getData$okio();
        byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    public static final byte[] commonInternalArray(okio.ByteString commonInternalArray) {
        Intrinsics.checkNotNullParameter(commonInternalArray, "$this$commonInternalArray");
        return commonInternalArray.getData$okio();
    }

    public static final boolean commonRangeEquals(okio.ByteString commonRangeEquals, int r2, okio.ByteString other, int r4, int r5) {
        Intrinsics.checkNotNullParameter(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        return other.rangeEquals(r4, commonRangeEquals.getData$okio(), r2, r5);
    }

    public static final boolean commonRangeEquals(okio.ByteString commonRangeEquals, int r2, byte[] other, int r4, int r5) {
        Intrinsics.checkNotNullParameter(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        return r2 >= 0 && r2 <= commonRangeEquals.getData$okio().length - r5 && r4 >= 0 && r4 <= other.length - r5 && Util.arrayRangeEquals(commonRangeEquals.getData$okio(), r2, other, r4, r5);
    }

    public static final boolean commonStartsWith(okio.ByteString commonStartsWith, okio.ByteString prefix) {
        Intrinsics.checkNotNullParameter(commonStartsWith, "$this$commonStartsWith");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return commonStartsWith.rangeEquals(0, prefix, 0, prefix.size());
    }

    public static final boolean commonStartsWith(okio.ByteString commonStartsWith, byte[] prefix) {
        Intrinsics.checkNotNullParameter(commonStartsWith, "$this$commonStartsWith");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return commonStartsWith.rangeEquals(0, prefix, 0, prefix.length);
    }

    public static final boolean commonEndsWith(okio.ByteString commonEndsWith, okio.ByteString suffix) {
        Intrinsics.checkNotNullParameter(commonEndsWith, "$this$commonEndsWith");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return commonEndsWith.rangeEquals(commonEndsWith.size() - suffix.size(), suffix, 0, suffix.size());
    }

    public static final boolean commonEndsWith(okio.ByteString commonEndsWith, byte[] suffix) {
        Intrinsics.checkNotNullParameter(commonEndsWith, "$this$commonEndsWith");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return commonEndsWith.rangeEquals(commonEndsWith.size() - suffix.length, suffix, 0, suffix.length);
    }

    public static final int commonIndexOf(okio.ByteString commonIndexOf, byte[] other, int r6) {
        Intrinsics.checkNotNullParameter(commonIndexOf, "$this$commonIndexOf");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = commonIndexOf.getData$okio().length - other.length;
        int max = Math.max(r6, 0);
        if (max <= length) {
            while (!Util.arrayRangeEquals(commonIndexOf.getData$okio(), max, other, 0, other.length)) {
                if (max == length) {
                    return -1;
                }
                max++;
            }
            return max;
        }
        return -1;
    }

    public static final int commonLastIndexOf(okio.ByteString commonLastIndexOf, okio.ByteString other, int r3) {
        Intrinsics.checkNotNullParameter(commonLastIndexOf, "$this$commonLastIndexOf");
        Intrinsics.checkNotNullParameter(other, "other");
        return commonLastIndexOf.lastIndexOf(other.internalArray$okio(), r3);
    }

    public static final int commonLastIndexOf(okio.ByteString commonLastIndexOf, byte[] other, int r5) {
        Intrinsics.checkNotNullParameter(commonLastIndexOf, "$this$commonLastIndexOf");
        Intrinsics.checkNotNullParameter(other, "other");
        for (int min = Math.min(r5, commonLastIndexOf.getData$okio().length - other.length); min >= 0; min--) {
            if (Util.arrayRangeEquals(commonLastIndexOf.getData$okio(), min, other, 0, other.length)) {
                return min;
            }
        }
        return -1;
    }

    public static final boolean commonEquals(okio.ByteString commonEquals, Object obj) {
        Intrinsics.checkNotNullParameter(commonEquals, "$this$commonEquals");
        if (obj == commonEquals) {
            return true;
        }
        if (obj instanceof okio.ByteString) {
            okio.ByteString byteString = (okio.ByteString) obj;
            if (byteString.size() == commonEquals.getData$okio().length && byteString.rangeEquals(0, commonEquals.getData$okio(), 0, commonEquals.getData$okio().length)) {
                return true;
            }
        }
        return false;
    }

    public static final int commonHashCode(okio.ByteString commonHashCode) {
        Intrinsics.checkNotNullParameter(commonHashCode, "$this$commonHashCode");
        int hashCode$okio = commonHashCode.getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int hashCode = Arrays.hashCode(commonHashCode.getData$okio());
        commonHashCode.setHashCode$okio(hashCode);
        return hashCode;
    }

    public static final int commonCompareTo(okio.ByteString commonCompareTo, okio.ByteString other) {
        Intrinsics.checkNotNullParameter(commonCompareTo, "$this$commonCompareTo");
        Intrinsics.checkNotNullParameter(other, "other");
        int size = commonCompareTo.size();
        int size2 = other.size();
        int min = Math.min(size, size2);
        for (int r4 = 0; r4 < min; r4++) {
            int r7 = commonCompareTo.getByte(r4) & 255;
            int r8 = other.getByte(r4) & 255;
            if (r7 != r8) {
                return r7 < r8 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    public static final okio.ByteString commonOf(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] copyOf = Arrays.copyOf(data, data.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
        return new okio.ByteString(copyOf);
    }

    public static final okio.ByteString commonToByteString(byte[] commonToByteString, int r8, int r9) {
        Intrinsics.checkNotNullParameter(commonToByteString, "$this$commonToByteString");
        Util.checkOffsetAndCount(commonToByteString.length, r8, r9);
        return new okio.ByteString(ArraysKt.copyOfRange(commonToByteString, r8, r9 + r8));
    }

    public static final okio.ByteString commonEncodeUtf8(String commonEncodeUtf8) {
        Intrinsics.checkNotNullParameter(commonEncodeUtf8, "$this$commonEncodeUtf8");
        okio.ByteString byteString = new okio.ByteString(Platform.asUtf8ToByteArray(commonEncodeUtf8));
        byteString.setUtf8$okio(commonEncodeUtf8);
        return byteString;
    }

    public static final okio.ByteString commonDecodeBase64(String commonDecodeBase64) {
        Intrinsics.checkNotNullParameter(commonDecodeBase64, "$this$commonDecodeBase64");
        byte[] decodeBase64ToArray = Base64.decodeBase64ToArray(commonDecodeBase64);
        if (decodeBase64ToArray != null) {
            return new okio.ByteString(decodeBase64ToArray);
        }
        return null;
    }

    public static final okio.ByteString commonDecodeHex(String commonDecodeHex) {
        Intrinsics.checkNotNullParameter(commonDecodeHex, "$this$commonDecodeHex");
        if (!(commonDecodeHex.length() % 2 == 0)) {
            throw new IllegalArgumentException(("Unexpected hex string: " + commonDecodeHex).toString());
        }
        int length = commonDecodeHex.length() / 2;
        byte[] bArr = new byte[length];
        for (int r1 = 0; r1 < length; r1++) {
            int r4 = r1 * 2;
            bArr[r1] = (byte) ((decodeHexDigit(commonDecodeHex.charAt(r4)) << 4) + decodeHexDigit(commonDecodeHex.charAt(r4 + 1)));
        }
        return new okio.ByteString(bArr);
    }

    public static final void commonWrite(okio.ByteString commonWrite, Buffer buffer, int r3, int r4) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.write(commonWrite.getData$okio(), r3, r4);
    }

    public static final int decodeHexDigit(char c) {
        if ('0' <= c && '9' >= c) {
            return c - '0';
        }
        char c2 = 'a';
        if ('a' > c || 'f' < c) {
            c2 = 'A';
            if ('A' > c || 'F' < c) {
                throw new IllegalArgumentException("Unexpected hex digit: " + c);
            }
        }
        return (c - c2) + 10;
    }

    public static final String commonToString(okio.ByteString byteString) {
        okio.ByteString commonToString = byteString;
        Intrinsics.checkNotNullParameter(commonToString, "$this$commonToString");
        if (byteString.getData$okio().length == 0) {
            return "[size=0]";
        }
        int codePointIndexToCharIndex = codePointIndexToCharIndex(byteString.getData$okio(), 64);
        if (codePointIndexToCharIndex == -1) {
            if (byteString.getData$okio().length <= 64) {
                return "[hex=" + byteString.hex() + ']';
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[size=");
            sb.append(byteString.getData$okio().length);
            sb.append(" hex=");
            if (!(64 <= byteString.getData$okio().length)) {
                throw new IllegalArgumentException(("endIndex > length(" + byteString.getData$okio().length + ')').toString());
            }
            if (64 != byteString.getData$okio().length) {
                commonToString = new okio.ByteString(ArraysKt.copyOfRange(byteString.getData$okio(), 0, 64));
            }
            sb.append(commonToString.hex());
            sb.append("…]");
            return sb.toString();
        }
        String utf8 = byteString.utf8();
        Objects.requireNonNull(utf8, "null cannot be cast to non-null type java.lang.String");
        String substring = utf8.substring(0, codePointIndexToCharIndex);
        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(substring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), StringUtils.f1569CR, "\\r", false, 4, (Object) null);
        if (codePointIndexToCharIndex < utf8.length()) {
            return "[size=" + byteString.getData$okio().length + " text=" + replace$default + "…]";
        }
        return "[text=" + replace$default + ']';
    }

    /* JADX WARN: Code restructure failed: missing block: B:304:0x0068, code lost:
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int codePointIndexToCharIndex(byte[] r19, int r20) {
        /*
            Method dump skipped, instructions count: 478
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ByteString.codePointIndexToCharIndex(byte[], int):int");
    }
}
