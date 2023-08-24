package expo.modules.filesystem;

import com.facebook.imagepipeline.producers.DecodeProducer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

/* compiled from: CountingRequestBody.kt */
@Metadata(m184d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m183d2 = {"Lexpo/modules/filesystem/CountingSink;", "Lokio/ForwardingSink;", "sink", "Lokio/Sink;", "requestBody", "Lokhttp3/RequestBody;", "progressListener", "Lexpo/modules/filesystem/CountingRequestListener;", "(Lokio/Sink;Lokhttp3/RequestBody;Lexpo/modules/filesystem/CountingRequestListener;)V", "bytesWritten", "", "write", "", "source", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, "expo-file-system_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
final class CountingSink extends ForwardingSink {
    private long bytesWritten;
    private final CountingRequestListener progressListener;
    private final RequestBody requestBody;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountingSink(Sink sink, RequestBody requestBody, CountingRequestListener progressListener) {
        super(sink);
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(requestBody, "requestBody");
        Intrinsics.checkNotNullParameter(progressListener, "progressListener");
        this.requestBody = requestBody;
        this.progressListener = progressListener;
    }

    @Override // okio.ForwardingSink, okio.Sink
    public void write(Buffer source, long j) {
        Intrinsics.checkNotNullParameter(source, "source");
        super.write(source, j);
        long j2 = this.bytesWritten + j;
        this.bytesWritten = j2;
        this.progressListener.onProgress(j2, this.requestBody.contentLength());
    }
}
