package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public interface Text {
    Rect getBoundingBox();

    List<? extends Text> getComponents();

    Point[] getCornerPoints();

    String getValue();
}
