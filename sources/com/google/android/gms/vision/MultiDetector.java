package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class MultiDetector extends Detector<Object> {
    private List<Detector<? extends Object>> zzaw;

    @Override // com.google.android.gms.vision.Detector
    public void release() {
        for (Detector<? extends Object> detector : this.zzaw) {
            detector.release();
        }
        this.zzaw.clear();
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class Builder {
        private MultiDetector zzaz = new MultiDetector();

        public Builder add(Detector<? extends Object> detector) {
            this.zzaz.zzaw.add(detector);
            return this;
        }

        public MultiDetector build() {
            if (this.zzaz.zzaw.size() == 0) {
                throw new RuntimeException("No underlying detectors added to MultiDetector.");
            }
            return this.zzaz;
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public SparseArray<Object> detect(Frame frame) {
        SparseArray<Object> sparseArray = new SparseArray<>();
        for (Detector<? extends Object> detector : this.zzaw) {
            SparseArray<? extends Object> detect = detector.detect(frame);
            for (int r3 = 0; r3 < detect.size(); r3++) {
                int keyAt = detect.keyAt(r3);
                if (sparseArray.get(keyAt) != null) {
                    StringBuilder sb = new StringBuilder(104);
                    sb.append("Detection ID overlap for id = ");
                    sb.append(keyAt);
                    sb.append("  This means that one of the detectors is not using global IDs.");
                    throw new IllegalStateException(sb.toString());
                }
                sparseArray.append(keyAt, detect.valueAt(r3));
            }
        }
        return sparseArray;
    }

    @Override // com.google.android.gms.vision.Detector
    public void receiveFrame(Frame frame) {
        for (Detector<? extends Object> detector : this.zzaw) {
            detector.receiveFrame(frame);
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public void setProcessor(Detector.Processor<Object> processor) {
        throw new UnsupportedOperationException("MultiDetector.setProcessor is not supported.  You should set a processor instance on each underlying detector instead.");
    }

    @Override // com.google.android.gms.vision.Detector
    public boolean isOperational() {
        for (Detector<? extends Object> detector : this.zzaw) {
            if (!detector.isOperational()) {
                return false;
            }
        }
        return true;
    }

    private MultiDetector() {
        this.zzaw = new ArrayList();
    }
}
