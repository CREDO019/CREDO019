package com.google.android.exoplayer2.decoder;

import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.Format;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes2.dex */
public class DecoderInputBuffer extends Buffer {
    public static final int BUFFER_REPLACEMENT_MODE_DIRECT = 2;
    public static final int BUFFER_REPLACEMENT_MODE_DISABLED = 0;
    public static final int BUFFER_REPLACEMENT_MODE_NORMAL = 1;
    private final int bufferReplacementMode;
    public final CryptoInfo cryptoInfo;
    public ByteBuffer data;
    public Format format;
    private final int paddingSize;
    public ByteBuffer supplementalData;
    public long timeUs;
    public boolean waitingForKeys;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface BufferReplacementMode {
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.decoder");
    }

    /* loaded from: classes2.dex */
    public static final class InsufficientCapacityException extends IllegalStateException {
        public final int currentCapacity;
        public final int requiredCapacity;

        public InsufficientCapacityException(int r3, int r4) {
            super("Buffer too small (" + r3 + " < " + r4 + ")");
            this.currentCapacity = r3;
            this.requiredCapacity = r4;
        }
    }

    public static DecoderInputBuffer newNoDataInstance() {
        return new DecoderInputBuffer(0);
    }

    public DecoderInputBuffer(int r2) {
        this(r2, 0);
    }

    public DecoderInputBuffer(int r2, int r3) {
        this.cryptoInfo = new CryptoInfo();
        this.bufferReplacementMode = r2;
        this.paddingSize = r3;
    }

    @EnsuresNonNull({"supplementalData"})
    public void resetSupplementalData(int r2) {
        ByteBuffer byteBuffer = this.supplementalData;
        if (byteBuffer == null || byteBuffer.capacity() < r2) {
            this.supplementalData = ByteBuffer.allocate(r2);
        } else {
            this.supplementalData.clear();
        }
    }

    @EnsuresNonNull({"data"})
    public void ensureSpaceForWrite(int r4) {
        int r42 = r4 + this.paddingSize;
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer == null) {
            this.data = createReplacementByteBuffer(r42);
            return;
        }
        int capacity = byteBuffer.capacity();
        int position = byteBuffer.position();
        int r43 = r42 + position;
        if (capacity >= r43) {
            this.data = byteBuffer;
            return;
        }
        ByteBuffer createReplacementByteBuffer = createReplacementByteBuffer(r43);
        createReplacementByteBuffer.order(byteBuffer.order());
        if (position > 0) {
            byteBuffer.flip();
            createReplacementByteBuffer.put(byteBuffer);
        }
        this.data = createReplacementByteBuffer;
    }

    public final boolean isEncrypted() {
        return getFlag(1073741824);
    }

    public final void flip() {
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            byteBuffer.flip();
        }
        ByteBuffer byteBuffer2 = this.supplementalData;
        if (byteBuffer2 != null) {
            byteBuffer2.flip();
        }
    }

    @Override // com.google.android.exoplayer2.decoder.Buffer
    public void clear() {
        super.clear();
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        ByteBuffer byteBuffer2 = this.supplementalData;
        if (byteBuffer2 != null) {
            byteBuffer2.clear();
        }
        this.waitingForKeys = false;
    }

    private ByteBuffer createReplacementByteBuffer(int r3) {
        int r0 = this.bufferReplacementMode;
        if (r0 == 1) {
            return ByteBuffer.allocate(r3);
        }
        if (r0 == 2) {
            return ByteBuffer.allocateDirect(r3);
        }
        ByteBuffer byteBuffer = this.data;
        throw new InsufficientCapacityException(byteBuffer == null ? 0 : byteBuffer.capacity(), r3);
    }
}
