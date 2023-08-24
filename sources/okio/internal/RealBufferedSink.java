package okio.internal;

import com.facebook.imagepipeline.producers.DecodeProducer;
import io.invertase.googlemobileads.ReactNativeGoogleMobileAdsEvent;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0005\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0080\b\u001a\r\u0010\t\u001a\u00020\n*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u0016\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0015H\u0080\b\u001a\u0015\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u0019\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001c\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001f\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010!\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010#\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\nH\u0080\b\u001a%\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010(\u001a\u00020\u0004*\u00020\u00022\u0006\u0010)\u001a\u00020\u000fH\u0080\b¨\u0006*"}, m183d2 = {"commonClose", "", "Lokio/RealBufferedSink;", "commonEmit", "Lokio/BufferedSink;", "commonEmitCompleteSegments", "commonFlush", "commonTimeout", "Lokio/Timeout;", "commonToString", "", "commonWrite", "source", "", "offset", "", DecodeProducer.EXTRA_BITMAP_BYTES, "Lokio/Buffer;", "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "b", "commonWriteDecimalLong", "v", "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteIntLe", "commonWriteLong", "commonWriteLongLe", "commonWriteShort", "s", "commonWriteShortLe", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "okio"}, m182k = 2, m181mv = {1, 4, 0})
/* renamed from: okio.internal.RealBufferedSinkKt */
/* loaded from: classes5.dex */
public final class RealBufferedSink {
    public static final void commonWrite(okio.RealBufferedSink commonWrite, Buffer source, long j) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!commonWrite.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWrite.bufferField.write(source, j);
        commonWrite.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink commonWrite, ByteString byteString) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        if (!(!commonWrite.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWrite.bufferField.write(byteString);
        return commonWrite.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink commonWrite, ByteString byteString, int r3, int r4) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        if (!(!commonWrite.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWrite.bufferField.write(byteString, r3, r4);
        return commonWrite.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8(okio.RealBufferedSink commonWriteUtf8, String string) {
        Intrinsics.checkNotNullParameter(commonWriteUtf8, "$this$commonWriteUtf8");
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(!commonWriteUtf8.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteUtf8.bufferField.writeUtf8(string);
        return commonWriteUtf8.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8(okio.RealBufferedSink commonWriteUtf8, String string, int r3, int r4) {
        Intrinsics.checkNotNullParameter(commonWriteUtf8, "$this$commonWriteUtf8");
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(!commonWriteUtf8.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteUtf8.bufferField.writeUtf8(string, r3, r4);
        return commonWriteUtf8.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8CodePoint(okio.RealBufferedSink commonWriteUtf8CodePoint, int r2) {
        Intrinsics.checkNotNullParameter(commonWriteUtf8CodePoint, "$this$commonWriteUtf8CodePoint");
        if (!(!commonWriteUtf8CodePoint.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteUtf8CodePoint.bufferField.writeUtf8CodePoint(r2);
        return commonWriteUtf8CodePoint.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink commonWrite, byte[] source) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!commonWrite.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWrite.bufferField.write(source);
        return commonWrite.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink commonWrite, byte[] source, int r3, int r4) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!commonWrite.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWrite.bufferField.write(source, r3, r4);
        return commonWrite.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteByte(okio.RealBufferedSink commonWriteByte, int r2) {
        Intrinsics.checkNotNullParameter(commonWriteByte, "$this$commonWriteByte");
        if (!(!commonWriteByte.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteByte.bufferField.writeByte(r2);
        return commonWriteByte.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteShort(okio.RealBufferedSink commonWriteShort, int r2) {
        Intrinsics.checkNotNullParameter(commonWriteShort, "$this$commonWriteShort");
        if (!(!commonWriteShort.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteShort.bufferField.writeShort(r2);
        return commonWriteShort.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteShortLe(okio.RealBufferedSink commonWriteShortLe, int r2) {
        Intrinsics.checkNotNullParameter(commonWriteShortLe, "$this$commonWriteShortLe");
        if (!(!commonWriteShortLe.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteShortLe.bufferField.writeShortLe(r2);
        return commonWriteShortLe.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteInt(okio.RealBufferedSink commonWriteInt, int r2) {
        Intrinsics.checkNotNullParameter(commonWriteInt, "$this$commonWriteInt");
        if (!(!commonWriteInt.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteInt.bufferField.writeInt(r2);
        return commonWriteInt.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteIntLe(okio.RealBufferedSink commonWriteIntLe, int r2) {
        Intrinsics.checkNotNullParameter(commonWriteIntLe, "$this$commonWriteIntLe");
        if (!(!commonWriteIntLe.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteIntLe.bufferField.writeIntLe(r2);
        return commonWriteIntLe.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteLong(okio.RealBufferedSink commonWriteLong, long j) {
        Intrinsics.checkNotNullParameter(commonWriteLong, "$this$commonWriteLong");
        if (!(!commonWriteLong.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteLong.bufferField.writeLong(j);
        return commonWriteLong.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteLongLe(okio.RealBufferedSink commonWriteLongLe, long j) {
        Intrinsics.checkNotNullParameter(commonWriteLongLe, "$this$commonWriteLongLe");
        if (!(!commonWriteLongLe.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteLongLe.bufferField.writeLongLe(j);
        return commonWriteLongLe.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteDecimalLong(okio.RealBufferedSink commonWriteDecimalLong, long j) {
        Intrinsics.checkNotNullParameter(commonWriteDecimalLong, "$this$commonWriteDecimalLong");
        if (!(!commonWriteDecimalLong.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteDecimalLong.bufferField.writeDecimalLong(j);
        return commonWriteDecimalLong.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteHexadecimalUnsignedLong(okio.RealBufferedSink commonWriteHexadecimalUnsignedLong, long j) {
        Intrinsics.checkNotNullParameter(commonWriteHexadecimalUnsignedLong, "$this$commonWriteHexadecimalUnsignedLong");
        if (!(!commonWriteHexadecimalUnsignedLong.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        commonWriteHexadecimalUnsignedLong.bufferField.writeHexadecimalUnsignedLong(j);
        return commonWriteHexadecimalUnsignedLong.emitCompleteSegments();
    }

    public static final BufferedSink commonEmitCompleteSegments(okio.RealBufferedSink commonEmitCompleteSegments) {
        Intrinsics.checkNotNullParameter(commonEmitCompleteSegments, "$this$commonEmitCompleteSegments");
        if (!(!commonEmitCompleteSegments.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        long completeSegmentByteCount = commonEmitCompleteSegments.bufferField.completeSegmentByteCount();
        if (completeSegmentByteCount > 0) {
            commonEmitCompleteSegments.sink.write(commonEmitCompleteSegments.bufferField, completeSegmentByteCount);
        }
        return commonEmitCompleteSegments;
    }

    public static final BufferedSink commonEmit(okio.RealBufferedSink commonEmit) {
        Intrinsics.checkNotNullParameter(commonEmit, "$this$commonEmit");
        if (!(!commonEmit.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        long size = commonEmit.bufferField.size();
        if (size > 0) {
            commonEmit.sink.write(commonEmit.bufferField, size);
        }
        return commonEmit;
    }

    public static final void commonFlush(okio.RealBufferedSink commonFlush) {
        Intrinsics.checkNotNullParameter(commonFlush, "$this$commonFlush");
        if (!(!commonFlush.closed)) {
            throw new IllegalStateException(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_CLOSED.toString());
        }
        if (commonFlush.bufferField.size() > 0) {
            commonFlush.sink.write(commonFlush.bufferField, commonFlush.bufferField.size());
        }
        commonFlush.sink.flush();
    }

    public static final void commonClose(okio.RealBufferedSink commonClose) {
        Intrinsics.checkNotNullParameter(commonClose, "$this$commonClose");
        if (commonClose.closed) {
            return;
        }
        Throwable th = null;
        try {
            if (commonClose.bufferField.size() > 0) {
                commonClose.sink.write(commonClose.bufferField, commonClose.bufferField.size());
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            commonClose.sink.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        commonClose.closed = true;
        if (th != null) {
            throw th;
        }
    }

    public static final Timeout commonTimeout(okio.RealBufferedSink commonTimeout) {
        Intrinsics.checkNotNullParameter(commonTimeout, "$this$commonTimeout");
        return commonTimeout.sink.timeout();
    }

    public static final String commonToString(okio.RealBufferedSink commonToString) {
        Intrinsics.checkNotNullParameter(commonToString, "$this$commonToString");
        return "buffer(" + commonToString.sink + ')';
    }

    public static final long commonWriteAll(okio.RealBufferedSink commonWriteAll, Source source) {
        Intrinsics.checkNotNullParameter(commonWriteAll, "$this$commonWriteAll");
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long read = source.read(commonWriteAll.bufferField, 8192);
            if (read == -1) {
                return j;
            }
            j += read;
            commonWriteAll.emitCompleteSegments();
        }
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink commonWrite, Source source, long j) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        while (j > 0) {
            long read = source.read(commonWrite.bufferField, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
            commonWrite.emitCompleteSegments();
        }
        return commonWrite;
    }
}
