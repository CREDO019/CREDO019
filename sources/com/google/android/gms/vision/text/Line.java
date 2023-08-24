package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzac;
import com.google.android.gms.internal.vision.zzaj;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public class Line implements Text {
    private zzac zzdy;
    private List<Element> zzdz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Line(zzac zzacVar) {
        this.zzdy = zzacVar;
    }

    public String getLanguage() {
        return this.zzdy.zzec;
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getValue() {
        return this.zzdy.zzel;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Rect getBoundingBox() {
        return zzc.zza(this);
    }

    @Override // com.google.android.gms.vision.text.Text
    public Point[] getCornerPoints() {
        return zzc.zza(this.zzdy.zzei);
    }

    @Override // com.google.android.gms.vision.text.Text
    public List<? extends Text> getComponents() {
        if (this.zzdy.zzeh.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzdz == null) {
            this.zzdz = new ArrayList(this.zzdy.zzeh.length);
            for (zzaj zzajVar : this.zzdy.zzeh) {
                this.zzdz.add(new Element(zzajVar));
            }
        }
        return this.zzdz;
    }

    public float getAngle() {
        return this.zzdy.zzei.zzeg;
    }

    public boolean isVertical() {
        return this.zzdy.zzen;
    }
}
