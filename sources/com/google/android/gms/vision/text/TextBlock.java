package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.exoplayer2.C1856C;
import com.google.android.gms.internal.vision.zzac;
import com.google.android.gms.internal.vision.zzw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzac[] zzea;
    private List<Line> zzeb;
    private String zzec;
    private Rect zzed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextBlock(SparseArray<zzac> sparseArray) {
        this.zzea = new zzac[sparseArray.size()];
        int r0 = 0;
        while (true) {
            zzac[] zzacVarArr = this.zzea;
            if (r0 >= zzacVarArr.length) {
                return;
            }
            zzacVarArr[r0] = sparseArray.valueAt(r0);
            r0++;
        }
    }

    public String getLanguage() {
        zzac[] zzacVarArr;
        String str = this.zzec;
        if (str != null) {
            return str;
        }
        HashMap hashMap = new HashMap();
        for (zzac zzacVar : this.zzea) {
            hashMap.put(zzacVar.zzec, Integer.valueOf((hashMap.containsKey(zzacVar.zzec) ? ((Integer) hashMap.get(zzacVar.zzec)).intValue() : 0) + 1));
        }
        String str2 = (String) ((Map.Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        this.zzec = str2;
        if (str2 == null || str2.isEmpty()) {
            this.zzec = C1856C.LANGUAGE_UNDETERMINED;
        }
        return this.zzec;
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getValue() {
        zzac[] zzacVarArr = this.zzea;
        if (zzacVarArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zzacVarArr[0].zzel);
        for (int r0 = 1; r0 < this.zzea.length; r0++) {
            sb.append("\n");
            sb.append(this.zzea[r0].zzel);
        }
        return sb.toString();
    }

    @Override // com.google.android.gms.vision.text.Text
    public Point[] getCornerPoints() {
        TextBlock textBlock;
        zzac[] zzacVarArr;
        TextBlock textBlock2 = this;
        if (textBlock2.cornerPoints == null) {
            char c = 0;
            if (textBlock2.zzea.length == 0) {
                textBlock2.cornerPoints = new Point[0];
            } else {
                int r1 = Integer.MIN_VALUE;
                int r3 = Integer.MIN_VALUE;
                int r4 = Integer.MAX_VALUE;
                int r5 = Integer.MAX_VALUE;
                int r6 = 0;
                while (true) {
                    zzacVarArr = textBlock2.zzea;
                    if (r6 >= zzacVarArr.length) {
                        break;
                    }
                    zzw zzwVar = zzacVarArr[r6].zzei;
                    zzw zzwVar2 = textBlock2.zzea[c].zzei;
                    double sin = Math.sin(Math.toRadians(zzwVar2.zzeg));
                    double cos = Math.cos(Math.toRadians(zzwVar2.zzeg));
                    Point[] pointArr = new Point[4];
                    pointArr[c] = new Point(zzwVar.left, zzwVar.top);
                    pointArr[c].offset(-zzwVar2.left, -zzwVar2.top);
                    int r16 = r3;
                    int r0 = (int) ((pointArr[c].x * cos) + (pointArr[c].y * sin));
                    int r32 = (int) (((-pointArr[0].x) * sin) + (pointArr[0].y * cos));
                    pointArr[0].x = r0;
                    pointArr[0].y = r32;
                    pointArr[1] = new Point(zzwVar.width + r0, r32);
                    pointArr[2] = new Point(zzwVar.width + r0, zzwVar.height + r32);
                    pointArr[3] = new Point(r0, r32 + zzwVar.height);
                    r3 = r16;
                    for (int r02 = 0; r02 < 4; r02++) {
                        Point point = pointArr[r02];
                        r4 = Math.min(r4, point.x);
                        r1 = Math.max(r1, point.x);
                        r5 = Math.min(r5, point.y);
                        r3 = Math.max(r3, point.y);
                    }
                    r6++;
                    c = 0;
                    textBlock2 = this;
                }
                int r162 = r3;
                zzw zzwVar3 = zzacVarArr[0].zzei;
                int r2 = zzwVar3.left;
                int r33 = zzwVar3.top;
                double sin2 = Math.sin(Math.toRadians(zzwVar3.zzeg));
                double cos2 = Math.cos(Math.toRadians(zzwVar3.zzeg));
                Point[] pointArr2 = {new Point(r4, r5), new Point(r1, r5), new Point(r1, r162), new Point(r4, r162)};
                for (int r11 = 0; r11 < 4; r11++) {
                    pointArr2[r11].x = (int) ((pointArr2[r11].x * cos2) - (pointArr2[r11].y * sin2));
                    pointArr2[r11].y = (int) ((pointArr2[r11].x * sin2) + (pointArr2[r11].y * cos2));
                    pointArr2[r11].offset(r2, r33);
                }
                textBlock = this;
                textBlock.cornerPoints = pointArr2;
                return textBlock.cornerPoints;
            }
        }
        textBlock = textBlock2;
        return textBlock.cornerPoints;
    }

    @Override // com.google.android.gms.vision.text.Text
    public List<? extends Text> getComponents() {
        if (this.zzea.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzeb == null) {
            this.zzeb = new ArrayList(this.zzea.length);
            for (zzac zzacVar : this.zzea) {
                this.zzeb.add(new Line(zzacVar));
            }
        }
        return this.zzeb;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Rect getBoundingBox() {
        if (this.zzed == null) {
            this.zzed = zzc.zza(this);
        }
        return this.zzed;
    }
}
