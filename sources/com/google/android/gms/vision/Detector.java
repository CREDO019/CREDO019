package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Frame;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class Detector<T> {
    private final Object zzah = new Object();
    private Processor<T> zzai;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public interface Processor<T> {
        void receiveDetections(Detections<T> detections);

        void release();
    }

    public abstract SparseArray<T> detect(Frame frame);

    public boolean isOperational() {
        return true;
    }

    public boolean setFocus(int r1) {
        return true;
    }

    public void release() {
        synchronized (this.zzah) {
            Processor<T> processor = this.zzai;
            if (processor != null) {
                processor.release();
                this.zzai = null;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class Detections<T> {
        private final SparseArray<T> zzae;
        private final Frame.Metadata zzaf;
        private final boolean zzag;

        public Detections(SparseArray<T> sparseArray, Frame.Metadata metadata, boolean z) {
            this.zzae = sparseArray;
            this.zzaf = metadata;
            this.zzag = z;
        }

        public SparseArray<T> getDetectedItems() {
            return this.zzae;
        }

        public Frame.Metadata getFrameMetadata() {
            return this.zzaf;
        }

        public boolean detectorIsOperational() {
            return this.zzag;
        }
    }

    public void receiveFrame(Frame frame) {
        Frame.Metadata metadata = new Frame.Metadata(frame.getMetadata());
        metadata.zzd();
        Detections<T> detections = new Detections<>(detect(frame), metadata, isOperational());
        synchronized (this.zzah) {
            Processor<T> processor = this.zzai;
            if (processor == null) {
                throw new IllegalStateException("Detector processor must first be set with setProcessor in order to receive detection results.");
            }
            processor.receiveDetections(detections);
        }
    }

    public void setProcessor(Processor<T> processor) {
        synchronized (this.zzah) {
            Processor<T> processor2 = this.zzai;
            if (processor2 != null) {
                processor2.release();
            }
            this.zzai = processor;
        }
    }
}
