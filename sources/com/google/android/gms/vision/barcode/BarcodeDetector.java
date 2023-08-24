package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class BarcodeDetector extends Detector<Barcode> {
    private final com.google.android.gms.internal.vision.zzh zzbk;

    private BarcodeDetector() {
        throw new IllegalStateException("Default constructor called");
    }

    private BarcodeDetector(com.google.android.gms.internal.vision.zzh zzhVar) {
        this.zzbk = zzhVar;
    }

    /* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class Builder {
        private com.google.android.gms.internal.vision.zzf zzbl = new com.google.android.gms.internal.vision.zzf();
        private Context zze;

        public Builder(Context context) {
            this.zze = context;
        }

        public Builder setBarcodeFormats(int r2) {
            this.zzbl.zzbm = r2;
            return this;
        }

        public BarcodeDetector build() {
            return new BarcodeDetector(new com.google.android.gms.internal.vision.zzh(this.zze, this.zzbl));
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        this.zzbk.zzo();
    }

    @Override // com.google.android.gms.vision.Detector
    public final SparseArray<Barcode> detect(Frame frame) {
        Barcode[] zza;
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        zzp zzc = zzp.zzc(frame);
        if (frame.getBitmap() != null) {
            zza = this.zzbk.zza(frame.getBitmap(), zzc);
            if (zza == null) {
                throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
            }
        } else {
            zza = this.zzbk.zza(frame.getGrayscaleImageData(), zzc);
        }
        SparseArray<Barcode> sparseArray = new SparseArray<>(zza.length);
        for (Barcode barcode : zza) {
            sparseArray.append(barcode.rawValue.hashCode(), barcode);
        }
        return sparseArray;
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean isOperational() {
        return this.zzbk.isOperational();
    }
}
