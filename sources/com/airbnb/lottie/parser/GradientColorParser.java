package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class GradientColorParser implements ValueParser<GradientColor> {
    private int colorPoints;

    public GradientColorParser(int r1) {
        this.colorPoints = r1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.parser.ValueParser
    public GradientColor parse(JsonReader jsonReader, float f) throws IOException {
        ArrayList arrayList = new ArrayList();
        boolean z = jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY;
        if (z) {
            jsonReader.beginArray();
        }
        while (jsonReader.hasNext()) {
            arrayList.add(Float.valueOf((float) jsonReader.nextDouble()));
        }
        if (arrayList.size() == 4 && arrayList.get(0).floatValue() == 1.0f) {
            arrayList.set(0, Float.valueOf(0.0f));
            arrayList.add(Float.valueOf(1.0f));
            arrayList.add(arrayList.get(1));
            arrayList.add(arrayList.get(2));
            arrayList.add(arrayList.get(3));
            this.colorPoints = 2;
        }
        if (z) {
            jsonReader.endArray();
        }
        if (this.colorPoints == -1) {
            this.colorPoints = arrayList.size() / 4;
        }
        int r2 = this.colorPoints;
        float[] fArr = new float[r2];
        int[] r22 = new int[r2];
        int r9 = 0;
        int r10 = 0;
        for (int r5 = 0; r5 < this.colorPoints * 4; r5++) {
            int r11 = r5 / 4;
            double floatValue = arrayList.get(r5).floatValue();
            int r14 = r5 % 4;
            if (r14 == 0) {
                if (r11 > 0) {
                    float f2 = (float) floatValue;
                    if (fArr[r11 - 1] >= f2) {
                        fArr[r11] = f2 + 0.01f;
                    }
                }
                fArr[r11] = (float) floatValue;
            } else if (r14 == 1) {
                r9 = (int) (floatValue * 255.0d);
            } else if (r14 == 2) {
                r10 = (int) (floatValue * 255.0d);
            } else if (r14 == 3) {
                r22[r11] = Color.argb(255, r9, r10, (int) (floatValue * 255.0d));
            }
        }
        return addOpacityStopsToGradientIfNeeded(new GradientColor(fArr, r22), arrayList);
    }

    private GradientColor addOpacityStopsToGradientIfNeeded(GradientColor gradientColor, List<Float> list) {
        int r0 = this.colorPoints * 4;
        if (list.size() <= r0) {
            return gradientColor;
        }
        float[] positions = gradientColor.getPositions();
        int[] colors = gradientColor.getColors();
        int size = (list.size() - r0) / 2;
        float[] fArr = new float[size];
        float[] fArr2 = new float[size];
        int r7 = 0;
        while (r0 < list.size()) {
            if (r0 % 2 == 0) {
                fArr[r7] = list.get(r0).floatValue();
            } else {
                fArr2[r7] = list.get(r0).floatValue();
                r7++;
            }
            r0++;
        }
        int r11 = this.colorPoints + size;
        float[] fArr3 = new float[r11];
        int[] r72 = new int[r11];
        System.arraycopy(gradientColor.getPositions(), 0, fArr3, 0, this.colorPoints);
        System.arraycopy(fArr, 0, fArr3, this.colorPoints, size);
        Arrays.sort(fArr3);
        for (int r6 = 0; r6 < r11; r6++) {
            float f = fArr3[r6];
            int binarySearch = Arrays.binarySearch(positions, f);
            int binarySearch2 = Arrays.binarySearch(fArr, f);
            if (binarySearch < 0 || binarySearch2 > 0) {
                if (binarySearch2 < 0) {
                    binarySearch2 = -(binarySearch2 + 1);
                }
                r72[r6] = getColorInBetweenColorStops(f, fArr2[binarySearch2], positions, colors);
            } else {
                r72[r6] = getColorInBetweenOpacityStops(f, colors[binarySearch], fArr, fArr2);
            }
        }
        return new GradientColor(fArr3, r72);
    }

    private int getColorInBetweenColorStops(float f, float f2, float[] fArr, int[] r8) {
        if (r8.length < 2 || f == fArr[0]) {
            return r8[0];
        }
        for (int r1 = 1; r1 < fArr.length; r1++) {
            if (fArr[r1] >= f || r1 == fArr.length - 1) {
                int r2 = r1 - 1;
                float f3 = (f - fArr[r2]) / (fArr[r1] - fArr[r2]);
                int r7 = r8[r1];
                int r82 = r8[r2];
                return Color.argb((int) (f2 * 255.0f), MiscUtils.lerp(Color.red(r82), Color.red(r7), f3), MiscUtils.lerp(Color.green(r82), Color.green(r7), f3), MiscUtils.lerp(Color.blue(r82), Color.blue(r7), f3));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    private int getColorInBetweenOpacityStops(float f, int r7, float[] fArr, float[] fArr2) {
        float lerp;
        if (fArr2.length < 2 || f <= fArr[0]) {
            return Color.argb((int) (fArr2[0] * 255.0f), Color.red(r7), Color.green(r7), Color.blue(r7));
        }
        for (int r1 = 1; r1 < fArr.length; r1++) {
            int r3 = (fArr[r1] > f ? 1 : (fArr[r1] == f ? 0 : -1));
            if (r3 >= 0 || r1 == fArr.length - 1) {
                if (r3 <= 0) {
                    lerp = fArr2[r1];
                } else {
                    int r32 = r1 - 1;
                    lerp = MiscUtils.lerp(fArr2[r32], fArr2[r1], (f - fArr[r32]) / (fArr[r1] - fArr[r32]));
                }
                return Color.argb((int) (lerp * 255.0f), Color.red(r7), Color.green(r7), Color.blue(r7));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }
}
