package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class Frame {
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;
    private Metadata zzao;
    private ByteBuffer zzap;
    private Bitmap zzaq;

    public Metadata getMetadata() {
        return this.zzao;
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class Builder {
        private Frame zzas = new Frame();

        public Builder setBitmap(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            this.zzas.zzaq = bitmap;
            Metadata metadata = this.zzas.getMetadata();
            metadata.width = width;
            metadata.height = height;
            return this;
        }

        public Builder setImageData(ByteBuffer byteBuffer, int r4, int r5, int r6) {
            if (byteBuffer == null) {
                throw new IllegalArgumentException("Null image data supplied.");
            }
            if (byteBuffer.capacity() >= r4 * r5) {
                if (r6 != 16 && r6 != 17 && r6 != 842094169) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Unsupported image format: ");
                    sb.append(r6);
                    throw new IllegalArgumentException(sb.toString());
                }
                this.zzas.zzap = byteBuffer;
                Metadata metadata = this.zzas.getMetadata();
                metadata.width = r4;
                metadata.height = r5;
                metadata.format = r6;
                return this;
            }
            throw new IllegalArgumentException("Invalid image data size.");
        }

        public Builder setId(int r2) {
            this.zzas.getMetadata().f267id = r2;
            return this;
        }

        public Builder setTimestampMillis(long j) {
            this.zzas.getMetadata().zzar = j;
            return this;
        }

        public Builder setRotation(int r2) {
            this.zzas.getMetadata().rotation = r2;
            return this;
        }

        public Frame build() {
            if (this.zzas.zzap == null && this.zzas.zzaq == null) {
                throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
            }
            return this.zzas;
        }
    }

    public ByteBuffer getGrayscaleImageData() {
        Bitmap bitmap = this.zzaq;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = this.zzaq.getHeight();
            int r0 = width * height;
            int[] r9 = new int[r0];
            this.zzaq.getPixels(r9, 0, width, 0, 0, width, height);
            byte[] bArr = new byte[r0];
            for (int r2 = 0; r2 < r0; r2++) {
                bArr[r2] = (byte) ((Color.red(r9[r2]) * 0.299f) + (Color.green(r9[r2]) * 0.587f) + (Color.blue(r9[r2]) * 0.114f));
            }
            return ByteBuffer.wrap(bArr);
        }
        return this.zzap;
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class Metadata {
        private int format = -1;
        private int height;

        /* renamed from: id */
        private int f267id;
        private int rotation;
        private int width;
        private long zzar;

        public Metadata() {
        }

        public Metadata(Metadata metadata) {
            this.width = metadata.getWidth();
            this.height = metadata.getHeight();
            this.f267id = metadata.getId();
            this.zzar = metadata.getTimestampMillis();
            this.rotation = metadata.getRotation();
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public int getId() {
            return this.f267id;
        }

        public long getTimestampMillis() {
            return this.zzar;
        }

        public int getRotation() {
            return this.rotation;
        }

        public int getFormat() {
            return this.format;
        }

        public final void zzd() {
            if (this.rotation % 2 != 0) {
                int r0 = this.width;
                this.width = this.height;
                this.height = r0;
            }
            this.rotation = 0;
        }
    }

    public Bitmap getBitmap() {
        return this.zzaq;
    }

    private Frame() {
        this.zzao = new Metadata();
        this.zzap = null;
        this.zzaq = null;
    }
}
