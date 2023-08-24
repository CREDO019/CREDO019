package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public interface GifDecoder {
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARTIAL_DECODE = 3;
    public static final int TOTAL_ITERATION_COUNT_FOREVER = 0;

    /* loaded from: classes.dex */
    public interface BitmapProvider {
        Bitmap obtain(int r1, int r2, Bitmap.Config config);

        byte[] obtainByteArray(int r1);

        int[] obtainIntArray(int r1);

        void release(Bitmap bitmap);

        void release(byte[] bArr);

        void release(int[] r1);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface GifDecodeStatus {
    }

    void advance();

    void clear();

    int getByteSize();

    int getCurrentFrameIndex();

    ByteBuffer getData();

    int getDelay(int r1);

    int getFrameCount();

    int getHeight();

    @Deprecated
    int getLoopCount();

    int getNetscapeLoopCount();

    int getNextDelay();

    Bitmap getNextFrame();

    int getStatus();

    int getTotalIterationCount();

    int getWidth();

    int read(InputStream inputStream, int r2);

    int read(byte[] bArr);

    void resetFrameIndex();

    void setData(GifHeader gifHeader, ByteBuffer byteBuffer);

    void setData(GifHeader gifHeader, ByteBuffer byteBuffer, int r3);

    void setData(GifHeader gifHeader, byte[] bArr);

    void setDefaultBitmapConfig(Bitmap.Config config);
}
