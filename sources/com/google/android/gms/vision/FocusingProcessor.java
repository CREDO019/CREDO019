package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class FocusingProcessor<T> implements Detector.Processor<T> {
    private Tracker<T> zzaj;
    private int zzam;
    private Detector<T> zzt;
    private int zzak = 3;
    private boolean zzal = false;
    private int zzan = 0;

    public FocusingProcessor(Detector<T> detector, Tracker<T> tracker) {
        this.zzt = detector;
        this.zzaj = tracker;
    }

    public abstract int selectFocus(Detector.Detections<T> detections);

    @Override // com.google.android.gms.vision.Detector.Processor
    public void release() {
        this.zzaj.onDone();
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void receiveDetections(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            if (this.zzan == this.zzak) {
                this.zzaj.onDone();
                this.zzal = false;
            } else {
                this.zzaj.onMissing(detections);
            }
            this.zzan++;
            return;
        }
        this.zzan = 0;
        if (this.zzal) {
            T t = detectedItems.get(this.zzam);
            if (t != null) {
                this.zzaj.onUpdate(detections, t);
                return;
            } else {
                this.zzaj.onDone();
                this.zzal = false;
            }
        }
        int selectFocus = selectFocus(detections);
        T t2 = detectedItems.get(selectFocus);
        if (t2 == null) {
            StringBuilder sb = new StringBuilder(35);
            sb.append("Invalid focus selected: ");
            sb.append(selectFocus);
            Log.w("FocusingProcessor", sb.toString());
            return;
        }
        this.zzal = true;
        this.zzam = selectFocus;
        this.zzt.setFocus(selectFocus);
        this.zzaj.onNewItem(this.zzam, t2);
        this.zzaj.onUpdate(detections, t2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(int r4) {
        if (r4 < 0) {
            StringBuilder sb = new StringBuilder(28);
            sb.append("Invalid max gap: ");
            sb.append(r4);
            throw new IllegalArgumentException(sb.toString());
        }
        this.zzak = r4;
    }
}
