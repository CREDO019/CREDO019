package com.google.android.gms.vision.face;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.internal.client.zzb;
import com.google.android.gms.vision.face.internal.client.zzf;
import com.google.android.gms.vision.zzc;
import java.nio.ByteBuffer;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class FaceDetector extends Detector<Face> {
    public static final int ACCURATE_MODE = 1;
    public static final int ALL_CLASSIFICATIONS = 1;
    public static final int ALL_LANDMARKS = 1;
    public static final int CONTOUR_LANDMARKS = 2;
    public static final int FAST_MODE = 0;
    public static final int NO_CLASSIFICATIONS = 0;
    public static final int NO_LANDMARKS = 0;
    public static final int SELFIE_MODE = 2;
    private final Object lock;
    private final zzc zzcj;
    private final zzb zzck;
    private boolean zzcl;

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        synchronized (this.lock) {
            if (this.zzcl) {
                this.zzck.zzo();
                this.zzcl = false;
            }
        }
    }

    protected final void finalize() throws Throwable {
        try {
            synchronized (this.lock) {
                if (this.zzcl) {
                    Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");
                    release();
                }
            }
        } finally {
            super.finalize();
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class Builder {
        private final Context zze;
        private int landmarkType = 0;
        private boolean zzcn = false;
        private int zzco = 0;
        private boolean trackingEnabled = true;
        private int mode = 0;
        private float proportionalMinFaceSize = -1.0f;

        public Builder(Context context) {
            this.zze = context;
        }

        public Builder setLandmarkType(int r4) {
            if (r4 != 0 && r4 != 1 && r4 != 2) {
                StringBuilder sb = new StringBuilder(34);
                sb.append("Invalid landmark type: ");
                sb.append(r4);
                throw new IllegalArgumentException(sb.toString());
            }
            this.landmarkType = r4;
            return this;
        }

        public Builder setProminentFaceOnly(boolean z) {
            this.zzcn = z;
            return this;
        }

        public Builder setClassificationType(int r4) {
            if (r4 != 0 && r4 != 1) {
                StringBuilder sb = new StringBuilder(40);
                sb.append("Invalid classification type: ");
                sb.append(r4);
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzco = r4;
            return this;
        }

        public Builder setTrackingEnabled(boolean z) {
            this.trackingEnabled = z;
            return this;
        }

        public Builder setMode(int r4) {
            if (r4 != 0 && r4 != 1 && r4 != 2) {
                StringBuilder sb = new StringBuilder(25);
                sb.append("Invalid mode: ");
                sb.append(r4);
                throw new IllegalArgumentException(sb.toString());
            }
            this.mode = r4;
            return this;
        }

        public Builder setMinFaceSize(float f) {
            if (f < 0.0f || f > 1.0f) {
                StringBuilder sb = new StringBuilder(47);
                sb.append("Invalid proportional face size: ");
                sb.append(f);
                throw new IllegalArgumentException(sb.toString());
            }
            this.proportionalMinFaceSize = f;
            return this;
        }

        public FaceDetector build() {
            zzf zzfVar = new zzf();
            zzfVar.mode = this.mode;
            zzfVar.landmarkType = this.landmarkType;
            zzfVar.zzco = this.zzco;
            zzfVar.zzcn = this.zzcn;
            zzfVar.trackingEnabled = this.trackingEnabled;
            zzfVar.proportionalMinFaceSize = this.proportionalMinFaceSize;
            if (!FaceDetector.zza(zzfVar)) {
                throw new IllegalArgumentException("Invalid build options");
            }
            return new FaceDetector(new zzb(this.zze, zzfVar));
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public final SparseArray<Face> detect(Frame frame) {
        ByteBuffer grayscaleImageData;
        Face[] zzb;
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        if (frame.getBitmap() != null) {
            Bitmap bitmap = frame.getBitmap();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int r4 = width * height;
            grayscaleImageData = ByteBuffer.allocateDirect(((((width + 1) / 2) * ((height + 1) / 2)) << 1) + r4);
            int r6 = r4;
            for (int r5 = 0; r5 < r4; r5++) {
                int r7 = r5 % width;
                int r8 = r5 / width;
                int pixel = bitmap.getPixel(r7, r8);
                float red = Color.red(pixel);
                float green = Color.green(pixel);
                float blue = Color.blue(pixel);
                grayscaleImageData.put(r5, (byte) ((0.299f * red) + (0.587f * green) + (0.114f * blue)));
                if (r8 % 2 == 0 && r7 % 2 == 0) {
                    int r82 = r6 + 1;
                    grayscaleImageData.put(r6, (byte) (((-0.169f) * red) + ((-0.331f) * green) + (blue * 0.5f) + 128.0f));
                    r6 = r82 + 1;
                    grayscaleImageData.put(r82, (byte) ((red * 0.5f) + (green * (-0.419f)) + (blue * (-0.081f)) + 128.0f));
                }
            }
        } else {
            grayscaleImageData = frame.getGrayscaleImageData();
        }
        synchronized (this.lock) {
            if (!this.zzcl) {
                throw new RuntimeException("Cannot use detector after release()");
            }
            zzb = this.zzck.zzb(grayscaleImageData, zzp.zzc(frame));
        }
        HashSet hashSet = new HashSet();
        SparseArray<Face> sparseArray = new SparseArray<>(zzb.length);
        int r42 = 0;
        for (Face face : zzb) {
            int id = face.getId();
            r42 = Math.max(r42, id);
            if (hashSet.contains(Integer.valueOf(id))) {
                id = r42 + 1;
                r42 = id;
            }
            hashSet.add(Integer.valueOf(id));
            sparseArray.append(this.zzcj.zzb(id), face);
        }
        return sparseArray;
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean setFocus(int r3) {
        boolean zzd;
        int zzc = this.zzcj.zzc(r3);
        synchronized (this.lock) {
            if (!this.zzcl) {
                throw new RuntimeException("Cannot use detector after release()");
            }
            zzd = this.zzck.zzd(zzc);
        }
        return zzd;
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean isOperational() {
        return this.zzck.isOperational();
    }

    private FaceDetector() {
        this.zzcj = new zzc();
        this.lock = new Object();
        this.zzcl = true;
        throw new IllegalStateException("Default constructor called");
    }

    private FaceDetector(zzb zzbVar) {
        this.zzcj = new zzc();
        this.lock = new Object();
        this.zzcl = true;
        this.zzck = zzbVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zza(zzf zzfVar) {
        boolean z;
        if (zzfVar.mode == 2 || zzfVar.landmarkType != 2) {
            z = true;
        } else {
            Log.e("FaceDetector", "Contour is not supported for non-SELFIE mode.");
            z = false;
        }
        if (zzfVar.landmarkType == 2 && zzfVar.zzco == 1) {
            Log.e("FaceDetector", "Classification is not supported with contour.");
            return false;
        }
        return z;
    }
}
