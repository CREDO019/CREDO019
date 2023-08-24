package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzaj;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public class Element implements Text {
    private zzaj zzdx;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element(zzaj zzajVar) {
        this.zzdx = zzajVar;
    }

    public String getLanguage() {
        return this.zzdx.zzec;
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getValue() {
        return this.zzdx.zzel;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Rect getBoundingBox() {
        return zzc.zza(this);
    }

    @Override // com.google.android.gms.vision.text.Text
    public Point[] getCornerPoints() {
        return zzc.zza(this.zzdx.zzei);
    }

    @Override // com.google.android.gms.vision.text.Text
    public List<? extends Text> getComponents() {
        return new ArrayList();
    }
}
