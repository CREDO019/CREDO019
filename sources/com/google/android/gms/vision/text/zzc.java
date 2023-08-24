package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzw;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
final class zzc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Rect zza(Text text) {
        Point[] cornerPoints;
        int r1 = Integer.MIN_VALUE;
        int r2 = Integer.MIN_VALUE;
        int r3 = Integer.MAX_VALUE;
        int r4 = Integer.MAX_VALUE;
        for (Point point : text.getCornerPoints()) {
            r3 = Math.min(r3, point.x);
            r1 = Math.max(r1, point.x);
            r4 = Math.min(r4, point.y);
            r2 = Math.max(r2, point.y);
        }
        return new Rect(r3, r4, r1, r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Point[] zza(zzw zzwVar) {
        double sin = Math.sin(Math.toRadians(zzwVar.zzeg));
        double cos = Math.cos(Math.toRadians(zzwVar.zzeg));
        Point[] pointArr = {new Point(zzwVar.left, zzwVar.top), new Point((int) (zzwVar.left + (zzwVar.width * cos)), (int) (zzwVar.top + (zzwVar.width * sin))), new Point((int) (pointArr[1].x - (zzwVar.height * sin)), (int) (pointArr[1].y + (zzwVar.height * cos))), new Point(pointArr[0].x + (pointArr[2].x - pointArr[1].x), pointArr[0].y + (pointArr[2].y - pointArr[1].y))};
        return pointArr;
    }
}
