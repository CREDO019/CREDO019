package com.google.android.exoplayer2.decoder;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderOutputBuffer;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class VideoDecoderOutputBuffer extends DecoderOutputBuffer {
    public static final int COLORSPACE_BT2020 = 3;
    public static final int COLORSPACE_BT601 = 1;
    public static final int COLORSPACE_BT709 = 2;
    public static final int COLORSPACE_UNKNOWN = 0;
    public int colorspace;
    public ByteBuffer data;
    public int decoderPrivate;
    public Format format;
    public int height;
    public int mode;
    private final DecoderOutputBuffer.Owner<VideoDecoderOutputBuffer> owner;
    public ByteBuffer supplementalData;
    public int width;
    public ByteBuffer[] yuvPlanes;
    public int[] yuvStrides;

    public VideoDecoderOutputBuffer(DecoderOutputBuffer.Owner<VideoDecoderOutputBuffer> owner) {
        this.owner = owner;
    }

    @Override // com.google.android.exoplayer2.decoder.DecoderOutputBuffer
    public void release() {
        this.owner.releaseOutputBuffer(this);
    }

    public void init(long j, int r3, ByteBuffer byteBuffer) {
        this.timeUs = j;
        this.mode = r3;
        if (byteBuffer != null && byteBuffer.hasRemaining()) {
            addFlag(268435456);
            int limit = byteBuffer.limit();
            ByteBuffer byteBuffer2 = this.supplementalData;
            if (byteBuffer2 == null || byteBuffer2.capacity() < limit) {
                this.supplementalData = ByteBuffer.allocate(limit);
            } else {
                this.supplementalData.clear();
            }
            this.supplementalData.put(byteBuffer);
            this.supplementalData.flip();
            byteBuffer.position(0);
            return;
        }
        this.supplementalData = null;
    }

    public boolean initForYuvFrame(int r7, int r8, int r9, int r10, int r11) {
        this.width = r7;
        this.height = r8;
        this.colorspace = r11;
        int r72 = (int) ((r8 + 1) / 2);
        if (isSafeToMultiply(r9, r8) && isSafeToMultiply(r10, r72)) {
            int r82 = r8 * r9;
            int r73 = r72 * r10;
            int r112 = (r73 * 2) + r82;
            if (isSafeToMultiply(r73, 2) && r112 >= r82) {
                ByteBuffer byteBuffer = this.data;
                if (byteBuffer == null || byteBuffer.capacity() < r112) {
                    this.data = ByteBuffer.allocateDirect(r112);
                } else {
                    this.data.position(0);
                    this.data.limit(r112);
                }
                if (this.yuvPlanes == null) {
                    this.yuvPlanes = new ByteBuffer[3];
                }
                ByteBuffer byteBuffer2 = this.data;
                ByteBuffer[] byteBufferArr = this.yuvPlanes;
                byteBufferArr[0] = byteBuffer2.slice();
                byteBufferArr[0].limit(r82);
                byteBuffer2.position(r82);
                byteBufferArr[1] = byteBuffer2.slice();
                byteBufferArr[1].limit(r73);
                byteBuffer2.position(r82 + r73);
                byteBufferArr[2] = byteBuffer2.slice();
                byteBufferArr[2].limit(r73);
                if (this.yuvStrides == null) {
                    this.yuvStrides = new int[3];
                }
                int[] r74 = this.yuvStrides;
                r74[0] = r9;
                r74[1] = r10;
                r74[2] = r10;
                return true;
            }
        }
        return false;
    }

    public void initForPrivateFrame(int r1, int r2) {
        this.width = r1;
        this.height = r2;
    }

    private static boolean isSafeToMultiply(int r1, int r2) {
        return r1 >= 0 && r2 >= 0 && (r2 <= 0 || r1 < Integer.MAX_VALUE / r2);
    }
}
