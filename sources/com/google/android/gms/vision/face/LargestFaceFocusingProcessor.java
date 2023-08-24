package com.google.android.gms.vision.face;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.FocusingProcessor;
import com.google.android.gms.vision.Tracker;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public class LargestFaceFocusingProcessor extends FocusingProcessor<Face> {
    public LargestFaceFocusingProcessor(Detector<Face> detector, Tracker<Face> tracker) {
        super(detector, tracker);
    }

    /* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class Builder {
        private LargestFaceFocusingProcessor zzcp;

        public Builder(Detector<Face> detector, Tracker<Face> tracker) {
            this.zzcp = new LargestFaceFocusingProcessor(detector, tracker);
        }

        public Builder setMaxGapFrames(int r2) {
            this.zzcp.zza(r2);
            return this;
        }

        public LargestFaceFocusingProcessor build() {
            return this.zzcp;
        }
    }

    @Override // com.google.android.gms.vision.FocusingProcessor
    public int selectFocus(Detector.Detections<Face> detections) {
        SparseArray<Face> detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            throw new IllegalArgumentException("No faces for selectFocus.");
        }
        int keyAt = detectedItems.keyAt(0);
        float width = detectedItems.valueAt(0).getWidth();
        for (int r2 = 1; r2 < detectedItems.size(); r2++) {
            int keyAt2 = detectedItems.keyAt(r2);
            float width2 = detectedItems.valueAt(r2).getWidth();
            if (width2 > width) {
                keyAt = keyAt2;
                width = width2;
            }
        }
        return keyAt;
    }
}
