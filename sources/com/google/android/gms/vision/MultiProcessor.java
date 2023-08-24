package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class MultiProcessor<T> implements Detector.Processor<T> {
    private int zzak;
    private Factory<T> zzax;
    private SparseArray<zza> zzay;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void release() {
        for (int r0 = 0; r0 < this.zzay.size(); r0++) {
            this.zzay.valueAt(r0).zzaj.onDone();
        }
        this.zzay.clear();
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    class zza {
        private Tracker<T> zzaj;
        private int zzan;

        private zza(MultiProcessor multiProcessor) {
            this.zzan = 0;
        }

        static /* synthetic */ int zzb(zza zzaVar) {
            int r0 = zzaVar.zzan;
            zzaVar.zzan = r0 + 1;
            return r0;
        }

        static /* synthetic */ int zza(zza zzaVar, int r1) {
            zzaVar.zzan = 0;
            return 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class Builder<T> {
        private MultiProcessor<T> zzba;

        public Builder(Factory<T> factory) {
            MultiProcessor<T> multiProcessor = new MultiProcessor<>();
            this.zzba = multiProcessor;
            if (factory == null) {
                throw new IllegalArgumentException("No factory supplied.");
            }
            ((MultiProcessor) multiProcessor).zzax = factory;
        }

        public Builder<T> setMaxGapFrames(int r4) {
            if (r4 < 0) {
                StringBuilder sb = new StringBuilder(28);
                sb.append("Invalid max gap: ");
                sb.append(r4);
                throw new IllegalArgumentException(sb.toString());
            }
            ((MultiProcessor) this.zzba).zzak = r4;
            return this;
        }

        public MultiProcessor<T> build() {
            return this.zzba;
        }
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void receiveDetections(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        for (int r2 = 0; r2 < detectedItems.size(); r2++) {
            int keyAt = detectedItems.keyAt(r2);
            T valueAt = detectedItems.valueAt(r2);
            if (this.zzay.get(keyAt) == null) {
                zza zzaVar = new zza();
                zzaVar.zzaj = this.zzax.create(valueAt);
                zzaVar.zzaj.onNewItem(keyAt, valueAt);
                this.zzay.append(keyAt, zzaVar);
            }
        }
        SparseArray<T> detectedItems2 = detections.getDetectedItems();
        HashSet<Integer> hashSet = new HashSet();
        for (int r3 = 0; r3 < this.zzay.size(); r3++) {
            int keyAt2 = this.zzay.keyAt(r3);
            if (detectedItems2.get(keyAt2) == null) {
                zza valueAt2 = this.zzay.valueAt(r3);
                zza.zzb(valueAt2);
                if (valueAt2.zzan >= this.zzak) {
                    valueAt2.zzaj.onDone();
                    hashSet.add(Integer.valueOf(keyAt2));
                } else {
                    valueAt2.zzaj.onMissing(detections);
                }
            }
        }
        for (Integer num : hashSet) {
            this.zzay.delete(num.intValue());
        }
        SparseArray<T> detectedItems3 = detections.getDetectedItems();
        for (int r22 = 0; r22 < detectedItems3.size(); r22++) {
            int keyAt3 = detectedItems3.keyAt(r22);
            T valueAt3 = detectedItems3.valueAt(r22);
            zza zzaVar2 = this.zzay.get(keyAt3);
            zza.zza(zzaVar2, 0);
            zzaVar2.zzaj.onUpdate(detections, valueAt3);
        }
    }

    private MultiProcessor() {
        this.zzay = new SparseArray<>();
        this.zzak = 3;
    }
}
