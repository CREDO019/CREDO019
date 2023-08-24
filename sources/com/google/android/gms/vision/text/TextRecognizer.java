package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzac;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzah;
import com.google.android.gms.internal.vision.zzai;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.internal.vision.zzq;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class TextRecognizer extends Detector<TextBlock> {
    private final zzai zzee;

    private TextRecognizer() {
        throw new IllegalStateException("Default constructor called");
    }

    private TextRecognizer(zzai zzaiVar) {
        this.zzee = zzaiVar;
    }

    /* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class Builder {
        private Context zze;
        private zzah zzef = new zzah();

        public Builder(Context context) {
            this.zze = context;
        }

        public TextRecognizer build() {
            return new TextRecognizer(new zzai(this.zze, this.zzef));
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public final SparseArray<TextBlock> detect(Frame frame) {
        byte[] bArr;
        Bitmap decodeByteArray;
        zzae zzaeVar = new zzae(new Rect());
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        zzp zzc = zzp.zzc(frame);
        if (frame.getBitmap() != null) {
            decodeByteArray = frame.getBitmap();
        } else {
            Frame.Metadata metadata = frame.getMetadata();
            ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
            int format = metadata.getFormat();
            int r2 = zzc.width;
            int r11 = zzc.height;
            if (grayscaleImageData.hasArray() && grayscaleImageData.arrayOffset() == 0) {
                bArr = grayscaleImageData.array();
            } else {
                byte[] bArr2 = new byte[grayscaleImageData.capacity()];
                grayscaleImageData.get(bArr2);
                bArr = bArr2;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new YuvImage(bArr, format, r2, r11, null).compressToJpeg(new Rect(0, 0, r2, r11), 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }
        Bitmap zzb = zzq.zzb(decodeByteArray, zzc);
        if (!zzaeVar.zzeq.isEmpty()) {
            Rect rect = zzaeVar.zzeq;
            int width = frame.getMetadata().getWidth();
            int height = frame.getMetadata().getHeight();
            int r6 = zzc.rotation;
            if (r6 == 1) {
                rect = new Rect(height - rect.bottom, rect.left, height - rect.top, rect.right);
            } else if (r6 == 2) {
                rect = new Rect(width - rect.right, height - rect.bottom, width - rect.left, height - rect.top);
            } else if (r6 == 3) {
                rect = new Rect(rect.top, width - rect.right, rect.bottom, width - rect.left);
            }
            zzaeVar.zzeq.set(rect);
        }
        zzc.rotation = 0;
        zzac[] zza = this.zzee.zza(zzb, zzc, zzaeVar);
        SparseArray sparseArray = new SparseArray();
        for (zzac zzacVar : zza) {
            SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzacVar.zzeo);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                sparseArray.append(zzacVar.zzeo, sparseArray2);
            }
            sparseArray2.append(zzacVar.zzep, zzacVar);
        }
        SparseArray<TextBlock> sparseArray3 = new SparseArray<>(sparseArray.size());
        for (int r3 = 0; r3 < sparseArray.size(); r3++) {
            sparseArray3.append(sparseArray.keyAt(r3), new TextBlock((SparseArray) sparseArray.valueAt(r3)));
        }
        return sparseArray3;
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean isOperational() {
        return this.zzee.isOperational();
    }

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        this.zzee.zzo();
    }
}
