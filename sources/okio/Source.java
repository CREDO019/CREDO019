package okio;

import com.facebook.imagepipeline.producers.DecodeProducer;
import com.polidea.rxandroidble.ClientComponent;
import java.io.Closeable;
import java.io.IOException;
import kotlin.Metadata;

/* compiled from: Source.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H&J\b\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, m183d2 = {"Lokio/Source;", "Ljava/io/Closeable;", "close", "", "read", "", "sink", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, ClientComponent.NamedSchedulers.TIMEOUT, "Lokio/Timeout;", "okio"}, m182k = 1, m181mv = {1, 4, 0})
/* loaded from: classes5.dex */
public interface Source extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    long read(Buffer buffer, long j) throws IOException;

    Timeout timeout();
}
