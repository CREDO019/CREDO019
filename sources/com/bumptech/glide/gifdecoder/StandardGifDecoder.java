package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes.dex */
public class StandardGifDecoder implements GifDecoder {
    private static final int BYTES_PER_INTEGER = 4;
    private static final int COLOR_TRANSPARENT_BLACK = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MASK_INT_LOWEST_BYTE = 255;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    private static final String TAG = "StandardGifDecoder";
    private int[] act;
    private Bitmap.Config bitmapConfig;
    private final GifDecoder.BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    private Boolean isFirstFrameTransparent;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private final int[] pct;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer) {
        this(bitmapProvider, gifHeader, byteBuffer, 1);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int r4) {
        this(bitmapProvider);
        setData(gifHeader, byteBuffer, r4);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider) {
        this.pct = new int[256];
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.bitmapProvider = bitmapProvider;
        this.header = new GifHeader();
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getWidth() {
        return this.header.width;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getHeight() {
        return this.header.height;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public ByteBuffer getData() {
        return this.rawData;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getStatus() {
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getDelay(int r2) {
        if (r2 < 0 || r2 >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(r2).delay;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNextDelay() {
        int r0;
        if (this.header.frameCount <= 0 || (r0 = this.framePointer) < 0) {
            return 0;
        }
        return getDelay(r0);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getFrameCount() {
        return this.header.frameCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getByteSize() {
        return this.rawData.limit() + this.mainPixels.length + (this.mainScratch.length * 4);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized Bitmap getNextFrame() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            String str = TAG;
            if (Log.isLoggable(str, 3)) {
                Log.d(str, "Unable to decode frame, frameCount=" + this.header.frameCount + ", framePointer=" + this.framePointer);
            }
            this.status = 1;
        }
        int r0 = this.status;
        if (r0 != 1 && r0 != 2) {
            this.status = 0;
            if (this.block == null) {
                this.block = this.bitmapProvider.obtainByteArray(255);
            }
            GifFrame gifFrame = this.header.frames.get(this.framePointer);
            int r5 = this.framePointer - 1;
            GifFrame gifFrame2 = r5 >= 0 ? this.header.frames.get(r5) : null;
            int[] r6 = gifFrame.lct != null ? gifFrame.lct : this.header.gct;
            this.act = r6;
            if (r6 == null) {
                String str2 = TAG;
                if (Log.isLoggable(str2, 3)) {
                    Log.d(str2, "No valid color table found for frame #" + this.framePointer);
                }
                this.status = 1;
                return null;
            }
            if (gifFrame.transparency) {
                int[] r1 = this.act;
                System.arraycopy(r1, 0, this.pct, 0, r1.length);
                int[] r12 = this.pct;
                this.act = r12;
                r12[gifFrame.transIndex] = 0;
            }
            return setPixels(gifFrame, gifFrame2);
        }
        String str3 = TAG;
        if (Log.isLoggable(str3, 3)) {
            Log.d(str3, "Unable to decode frame, status=" + this.status);
        }
        return null;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int read(InputStream inputStream, int r7) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(r7 > 0 ? r7 + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr, 0, 16384);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void clear() {
        this.header = null;
        byte[] bArr = this.mainPixels;
        if (bArr != null) {
            this.bitmapProvider.release(bArr);
        }
        int[] r1 = this.mainScratch;
        if (r1 != null) {
            this.bitmapProvider.release(r1);
        }
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            this.bitmapProvider.release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        byte[] bArr2 = this.block;
        if (bArr2 != null) {
            this.bitmapProvider.release(bArr2);
        }
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader gifHeader, byte[] bArr) {
        setData(gifHeader, ByteBuffer.wrap(bArr));
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer) {
        setData(gifHeader, byteBuffer, 1);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer, int r5) {
        if (r5 <= 0) {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + r5);
        }
        int highestOneBit = Integer.highestOneBit(r5);
        this.status = 0;
        this.header = gifHeader;
        this.framePointer = -1;
        ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.rawData = asReadOnlyBuffer;
        asReadOnlyBuffer.position(0);
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = gifHeader.frames.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().dispose == 3) {
                this.savePrevious = true;
                break;
            }
        }
        this.sampleSize = highestOneBit;
        this.downsampledWidth = gifHeader.width / highestOneBit;
        this.downsampledHeight = gifHeader.height / highestOneBit;
        this.mainPixels = this.bitmapProvider.obtainByteArray(gifHeader.width * gifHeader.height);
        this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized int read(byte[] bArr) {
        GifHeader parseHeader = getHeaderParser().setData(bArr).parseHeader();
        this.header = parseHeader;
        if (bArr != null) {
            setData(parseHeader, bArr);
        }
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void setDefaultBitmapConfig(Bitmap.Config config) {
        if (config != Bitmap.Config.ARGB_8888 && config != Bitmap.Config.RGB_565) {
            throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
        }
        this.bitmapConfig = config;
    }

    private Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        Bitmap bitmap;
        int[] r8 = this.mainScratch;
        int r0 = 0;
        if (gifFrame2 == null) {
            Bitmap bitmap2 = this.previousImage;
            if (bitmap2 != null) {
                this.bitmapProvider.release(bitmap2);
            }
            this.previousImage = null;
            Arrays.fill(r8, 0);
        }
        if (gifFrame2 != null && gifFrame2.dispose == 3 && this.previousImage == null) {
            Arrays.fill(r8, 0);
        }
        if (gifFrame2 != null && gifFrame2.dispose > 0) {
            if (gifFrame2.dispose == 2) {
                if (!gifFrame.transparency) {
                    int r1 = this.header.bgColor;
                    if (gifFrame.lct == null || this.header.bgIndex != gifFrame.transIndex) {
                        r0 = r1;
                    }
                } else if (this.framePointer == 0) {
                    this.isFirstFrameTransparent = true;
                }
                int r12 = gifFrame2.f96ih / this.sampleSize;
                int r2 = gifFrame2.f99iy / this.sampleSize;
                int r3 = gifFrame2.f97iw / this.sampleSize;
                int r122 = gifFrame2.f98ix / this.sampleSize;
                int r4 = this.downsampledWidth;
                int r22 = (r2 * r4) + r122;
                int r13 = (r12 * r4) + r22;
                while (r22 < r13) {
                    int r123 = r22 + r3;
                    for (int r42 = r22; r42 < r123; r42++) {
                        r8[r42] = r0;
                    }
                    r22 += this.downsampledWidth;
                }
            } else if (gifFrame2.dispose == 3 && (bitmap = this.previousImage) != null) {
                int r6 = this.downsampledWidth;
                bitmap.getPixels(r8, 0, r6, 0, 0, r6, this.downsampledHeight);
            }
        }
        decodeBitmapData(gifFrame);
        if (gifFrame.interlace || this.sampleSize != 1) {
            copyCopyIntoScratchRobust(gifFrame);
        } else {
            copyIntoScratchFast(gifFrame);
        }
        if (this.savePrevious && (gifFrame.dispose == 0 || gifFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            Bitmap bitmap3 = this.previousImage;
            int r62 = this.downsampledWidth;
            bitmap3.setPixels(r8, 0, r62, 0, 0, r62, this.downsampledHeight);
        }
        Bitmap nextBitmap = getNextBitmap();
        int r63 = this.downsampledWidth;
        nextBitmap.setPixels(r8, 0, r63, 0, 0, r63, this.downsampledHeight);
        return nextBitmap;
    }

    private void copyIntoScratchFast(GifFrame gifFrame) {
        GifFrame gifFrame2 = gifFrame;
        int[] r2 = this.mainScratch;
        int r3 = gifFrame2.f96ih;
        int r4 = gifFrame2.f99iy;
        int r5 = gifFrame2.f97iw;
        int r6 = gifFrame2.f98ix;
        boolean z = this.framePointer == 0;
        int r10 = this.downsampledWidth;
        byte[] bArr = this.mainPixels;
        int[] r12 = this.act;
        int r14 = 0;
        byte b = -1;
        while (r14 < r3) {
            int r16 = (r14 + r4) * r10;
            int r17 = r16 + r6;
            int r8 = r17 + r5;
            int r9 = r16 + r10;
            if (r9 < r8) {
                r8 = r9;
            }
            int r92 = gifFrame2.f97iw * r14;
            int r13 = r17;
            while (r13 < r8) {
                byte b2 = bArr[r92];
                int r172 = r3;
                int r32 = b2 & 255;
                if (r32 != b) {
                    int r33 = r12[r32];
                    if (r33 != 0) {
                        r2[r13] = r33;
                    } else {
                        b = b2;
                    }
                }
                r92++;
                r13++;
                r3 = r172;
            }
            r14++;
            gifFrame2 = gifFrame;
        }
        this.isFirstFrameTransparent = Boolean.valueOf(this.isFirstFrameTransparent == null && z && b != -1);
    }

    private void copyCopyIntoScratchRobust(GifFrame gifFrame) {
        int r22;
        int r3;
        int r23;
        int r21;
        int r17;
        int[] r2 = this.mainScratch;
        int r32 = gifFrame.f96ih / this.sampleSize;
        int r4 = gifFrame.f99iy / this.sampleSize;
        int r5 = gifFrame.f97iw / this.sampleSize;
        int r6 = gifFrame.f98ix;
        int r7 = this.sampleSize;
        int r62 = r6 / r7;
        boolean z = this.framePointer == 0;
        int r12 = this.downsampledWidth;
        int r13 = this.downsampledHeight;
        byte[] bArr = this.mainPixels;
        int[] r15 = this.act;
        Boolean bool = this.isFirstFrameTransparent;
        int r16 = 8;
        int r10 = 0;
        int r11 = 0;
        int r19 = 1;
        while (r10 < r32) {
            Boolean bool2 = bool;
            if (gifFrame.interlace) {
                if (r11 >= r32) {
                    r22 = r32;
                    int r33 = r19 + 1;
                    if (r33 == 2) {
                        r19 = r33;
                        r11 = 4;
                    } else if (r33 == 3) {
                        r19 = r33;
                        r11 = 2;
                        r16 = 4;
                    } else if (r33 != 4) {
                        r19 = r33;
                    } else {
                        r19 = r33;
                        r11 = 1;
                        r16 = 2;
                    }
                } else {
                    r22 = r32;
                }
                r3 = r11 + r16;
            } else {
                r22 = r32;
                r3 = r11;
                r11 = r10;
            }
            int r112 = r11 + r4;
            boolean z2 = r7 == 1;
            if (r112 < r13) {
                int r113 = r112 * r12;
                int r212 = r113 + r62;
                int r9 = r212 + r5;
                int r114 = r113 + r12;
                if (r114 < r9) {
                    r9 = r114;
                }
                r23 = r3;
                int r115 = r10 * r7 * gifFrame.f97iw;
                if (z2) {
                    int r34 = r212;
                    while (r34 < r9) {
                        int r172 = r4;
                        int r42 = r15[bArr[r115] & 255];
                        if (r42 != 0) {
                            r2[r34] = r42;
                        } else if (z && bool2 == null) {
                            bool2 = true;
                        }
                        r115 += r7;
                        r34++;
                        r4 = r172;
                    }
                } else {
                    r17 = r4;
                    int r35 = ((r9 - r212) * r7) + r115;
                    int r43 = r212;
                    while (true) {
                        r21 = r5;
                        if (r43 < r9) {
                            int averageColorsNear = averageColorsNear(r115, r35, gifFrame.f97iw);
                            if (averageColorsNear != 0) {
                                r2[r43] = averageColorsNear;
                            } else if (z && bool2 == null) {
                                bool2 = true;
                            }
                            r115 += r7;
                            r43++;
                            r5 = r21;
                        }
                    }
                    bool = bool2;
                    r10++;
                    r4 = r17;
                    r5 = r21;
                    r32 = r22;
                    r11 = r23;
                }
            } else {
                r23 = r3;
            }
            r17 = r4;
            r21 = r5;
            bool = bool2;
            r10++;
            r4 = r17;
            r5 = r21;
            r32 = r22;
            r11 = r23;
        }
        Boolean bool3 = bool;
        if (this.isFirstFrameTransparent == null) {
            this.isFirstFrameTransparent = Boolean.valueOf(bool3 == null ? false : bool3.booleanValue());
        }
    }

    private int averageColorsNear(int r10, int r11, int r12) {
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        for (int r1 = r10; r1 < this.sampleSize + r10; r1++) {
            byte[] bArr = this.mainPixels;
            if (r1 >= bArr.length || r1 >= r11) {
                break;
            }
            int r7 = this.act[bArr[r1] & 255];
            if (r7 != 0) {
                r2 += (r7 >> 24) & 255;
                r3 += (r7 >> 16) & 255;
                r4 += (r7 >> 8) & 255;
                r5 += r7 & 255;
                r6++;
            }
        }
        int r102 = r10 + r12;
        for (int r122 = r102; r122 < this.sampleSize + r102; r122++) {
            byte[] bArr2 = this.mainPixels;
            if (r122 >= bArr2.length || r122 >= r11) {
                break;
            }
            int r13 = this.act[bArr2[r122] & 255];
            if (r13 != 0) {
                r2 += (r13 >> 24) & 255;
                r3 += (r13 >> 16) & 255;
                r4 += (r13 >> 8) & 255;
                r5 += r13 & 255;
                r6++;
            }
        }
        if (r6 == 0) {
            return 0;
        }
        return ((r2 / r6) << 24) | ((r3 / r6) << 16) | ((r4 / r6) << 8) | (r5 / r6);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void decodeBitmapData(GifFrame gifFrame) {
        short s;
        StandardGifDecoder standardGifDecoder = this;
        if (gifFrame != null) {
            standardGifDecoder.rawData.position(gifFrame.bufferFrameStart);
        }
        int r1 = gifFrame == null ? standardGifDecoder.header.width * standardGifDecoder.header.height : gifFrame.f96ih * gifFrame.f97iw;
        byte[] bArr = standardGifDecoder.mainPixels;
        if (bArr == null || bArr.length < r1) {
            standardGifDecoder.mainPixels = standardGifDecoder.bitmapProvider.obtainByteArray(r1);
        }
        byte[] bArr2 = standardGifDecoder.mainPixels;
        if (standardGifDecoder.prefix == null) {
            standardGifDecoder.prefix = new short[4096];
        }
        short[] sArr = standardGifDecoder.prefix;
        if (standardGifDecoder.suffix == null) {
            standardGifDecoder.suffix = new byte[4096];
        }
        byte[] bArr3 = standardGifDecoder.suffix;
        if (standardGifDecoder.pixelStack == null) {
            standardGifDecoder.pixelStack = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        byte[] bArr4 = standardGifDecoder.pixelStack;
        int readByte = readByte();
        int r9 = 1 << readByte;
        int r10 = r9 + 1;
        int r11 = r9 + 2;
        int r7 = readByte + 1;
        int r12 = (1 << r7) - 1;
        int r13 = 0;
        for (int r14 = 0; r14 < r9; r14++) {
            sArr[r14] = 0;
            bArr3[r14] = (byte) r14;
        }
        byte[] bArr5 = standardGifDecoder.block;
        int r23 = r7;
        int r21 = r11;
        int r22 = r12;
        int r16 = 0;
        int r17 = 0;
        int r18 = 0;
        int r19 = 0;
        int r20 = 0;
        int r24 = -1;
        int r25 = 0;
        int r26 = 0;
        while (true) {
            if (r13 >= r1) {
                break;
            }
            if (r16 == 0) {
                r16 = readBlock();
                if (r16 <= 0) {
                    standardGifDecoder.status = 3;
                    break;
                }
                r17 = 0;
            }
            r19 += (bArr5[r17] & 255) << r18;
            r17++;
            r16--;
            int r4 = r18 + 8;
            int r8 = r21;
            int r15 = r23;
            int r0 = r24;
            int r232 = r7;
            int r72 = r25;
            while (true) {
                if (r4 < r15) {
                    r24 = r0;
                    r21 = r8;
                    r18 = r4;
                    standardGifDecoder = this;
                    r25 = r72;
                    r7 = r232;
                    r23 = r15;
                    break;
                }
                int r242 = r11;
                int r112 = r19 & r22;
                r19 >>= r15;
                r4 -= r15;
                if (r112 == r9) {
                    r22 = r12;
                    r15 = r232;
                    r8 = r242;
                    r11 = r8;
                    r0 = -1;
                } else if (r112 == r10) {
                    r18 = r4;
                    r25 = r72;
                    r21 = r8;
                    r7 = r232;
                    r11 = r242;
                    r24 = r0;
                    r23 = r15;
                    standardGifDecoder = this;
                    break;
                } else if (r0 == -1) {
                    bArr2[r20] = bArr3[r112];
                    r20++;
                    r13++;
                    r0 = r112;
                    r72 = r0;
                    r11 = r242;
                    r4 = r4;
                } else {
                    if (r112 >= r8) {
                        bArr4[r26] = (byte) r72;
                        r26++;
                        s = r0;
                    } else {
                        s = r112;
                    }
                    while (s >= r9) {
                        bArr4[r26] = bArr3[s];
                        r26++;
                        s = sArr[s];
                    }
                    r72 = bArr3[s] & 255;
                    byte b = (byte) r72;
                    bArr2[r20] = b;
                    while (true) {
                        r20++;
                        r13++;
                        if (r26 <= 0) {
                            break;
                        }
                        r26--;
                        bArr2[r20] = bArr4[r26];
                    }
                    byte[] bArr6 = bArr4;
                    if (r8 < 4096) {
                        sArr[r8] = (short) r0;
                        bArr3[r8] = b;
                        r8++;
                        if ((r8 & r22) == 0 && r8 < 4096) {
                            r15++;
                            r22 += r8;
                        }
                    }
                    r0 = r112;
                    r11 = r242;
                    r4 = r4;
                    bArr4 = bArr6;
                }
            }
        }
        Arrays.fill(bArr2, r20, r1, (byte) 0);
    }

    private int readByte() {
        return this.rawData.get() & 255;
    }

    private int readBlock() {
        int readByte = readByte();
        if (readByte <= 0) {
            return readByte;
        }
        ByteBuffer byteBuffer = this.rawData;
        byteBuffer.get(this.block, 0, Math.min(readByte, byteBuffer.remaining()));
        return readByte;
    }

    private Bitmap getNextBitmap() {
        Boolean bool = this.isFirstFrameTransparent;
        Bitmap obtain = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.bitmapConfig);
        obtain.setHasAlpha(true);
        return obtain;
    }
}
