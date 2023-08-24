package okio.internal;

import com.facebook.imagepipeline.producers.DecodeProducer;
import kotlin.Metadata;

/* compiled from: HashFunction.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&¨\u0006\n"}, m183d2 = {"Lokio/internal/HashFunction;", "", "digest", "", "update", "", "input", "offset", "", DecodeProducer.EXTRA_BITMAP_BYTES, "okio"}, m182k = 1, m181mv = {1, 4, 0})
/* loaded from: classes5.dex */
public interface HashFunction {
    byte[] digest();

    void update(byte[] bArr, int r2, int r3);
}
