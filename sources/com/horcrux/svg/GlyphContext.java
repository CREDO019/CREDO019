package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class GlyphContext {
    private double mDX;
    private int mDXIndex;
    private final ArrayList<Integer> mDXIndices;
    private SVGLength[] mDXs;
    private final ArrayList<SVGLength[]> mDXsContext;
    private int mDXsIndex;
    private final ArrayList<Integer> mDXsIndices;
    private double mDY;
    private int mDYIndex;
    private final ArrayList<Integer> mDYIndices;
    private SVGLength[] mDYs;
    private final ArrayList<SVGLength[]> mDYsContext;
    private int mDYsIndex;
    private final ArrayList<Integer> mDYsIndices;
    final ArrayList<FontData> mFontContext;
    private double mFontSize;
    private final float mHeight;
    private int mRIndex;
    private final ArrayList<Integer> mRIndices;
    private double[] mRs;
    private final ArrayList<double[]> mRsContext;
    private int mRsIndex;
    private final ArrayList<Integer> mRsIndices;
    private final float mScale;
    private int mTop;
    private final float mWidth;

    /* renamed from: mX */
    private double f1249mX;
    private int mXIndex;
    private final ArrayList<Integer> mXIndices;
    private SVGLength[] mXs;
    private final ArrayList<SVGLength[]> mXsContext;
    private int mXsIndex;
    private final ArrayList<Integer> mXsIndices;

    /* renamed from: mY */
    private double f1250mY;
    private int mYIndex;
    private final ArrayList<Integer> mYIndices;
    private SVGLength[] mYs;
    private final ArrayList<SVGLength[]> mYsContext;
    private int mYsIndex;
    private final ArrayList<Integer> mYsIndices;
    private FontData topFont;

    private void pushIndices() {
        this.mXsIndices.add(Integer.valueOf(this.mXsIndex));
        this.mYsIndices.add(Integer.valueOf(this.mYsIndex));
        this.mDXsIndices.add(Integer.valueOf(this.mDXsIndex));
        this.mDYsIndices.add(Integer.valueOf(this.mDYsIndex));
        this.mRsIndices.add(Integer.valueOf(this.mRsIndex));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GlyphContext(float f, float f2, float f3) {
        ArrayList<FontData> arrayList = new ArrayList<>();
        this.mFontContext = arrayList;
        ArrayList<SVGLength[]> arrayList2 = new ArrayList<>();
        this.mXsContext = arrayList2;
        ArrayList<SVGLength[]> arrayList3 = new ArrayList<>();
        this.mYsContext = arrayList3;
        ArrayList<SVGLength[]> arrayList4 = new ArrayList<>();
        this.mDXsContext = arrayList4;
        ArrayList<SVGLength[]> arrayList5 = new ArrayList<>();
        this.mDYsContext = arrayList5;
        ArrayList<double[]> arrayList6 = new ArrayList<>();
        this.mRsContext = arrayList6;
        ArrayList<Integer> arrayList7 = new ArrayList<>();
        this.mXIndices = arrayList7;
        ArrayList<Integer> arrayList8 = new ArrayList<>();
        this.mYIndices = arrayList8;
        ArrayList<Integer> arrayList9 = new ArrayList<>();
        this.mDXIndices = arrayList9;
        ArrayList<Integer> arrayList10 = new ArrayList<>();
        this.mDYIndices = arrayList10;
        ArrayList<Integer> arrayList11 = new ArrayList<>();
        this.mRIndices = arrayList11;
        this.mXsIndices = new ArrayList<>();
        this.mYsIndices = new ArrayList<>();
        this.mDXsIndices = new ArrayList<>();
        this.mDYsIndices = new ArrayList<>();
        this.mRsIndices = new ArrayList<>();
        this.mFontSize = 12.0d;
        this.topFont = FontData.Defaults;
        SVGLength[] sVGLengthArr = new SVGLength[0];
        this.mXs = sVGLengthArr;
        this.mYs = new SVGLength[0];
        this.mDXs = new SVGLength[0];
        this.mDYs = new SVGLength[0];
        this.mRs = new double[]{0.0d};
        this.mXIndex = -1;
        this.mYIndex = -1;
        this.mDXIndex = -1;
        this.mDYIndex = -1;
        this.mRIndex = -1;
        this.mScale = f;
        this.mWidth = f2;
        this.mHeight = f3;
        arrayList2.add(sVGLengthArr);
        arrayList3.add(this.mYs);
        arrayList4.add(this.mDXs);
        arrayList5.add(this.mDYs);
        arrayList6.add(this.mRs);
        arrayList7.add(Integer.valueOf(this.mXIndex));
        arrayList8.add(Integer.valueOf(this.mYIndex));
        arrayList9.add(Integer.valueOf(this.mDXIndex));
        arrayList10.add(Integer.valueOf(this.mDYIndex));
        arrayList11.add(Integer.valueOf(this.mRIndex));
        arrayList.add(this.topFont);
        pushIndices();
    }

    private void reset() {
        this.mRsIndex = 0;
        this.mDYsIndex = 0;
        this.mDXsIndex = 0;
        this.mYsIndex = 0;
        this.mXsIndex = 0;
        this.mRIndex = -1;
        this.mDYIndex = -1;
        this.mDXIndex = -1;
        this.mYIndex = -1;
        this.mXIndex = -1;
        this.mDY = 0.0d;
        this.mDX = 0.0d;
        this.f1250mY = 0.0d;
        this.f1249mX = 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FontData getFont() {
        return this.topFont;
    }

    private FontData getTopOrParentFont(GroupView groupView) {
        if (this.mTop > 0) {
            return this.topFont;
        }
        for (GroupView parentTextRoot = groupView.getParentTextRoot(); parentTextRoot != null; parentTextRoot = parentTextRoot.getParentTextRoot()) {
            FontData font = parentTextRoot.getGlyphContext().getFont();
            if (font != FontData.Defaults) {
                return font;
            }
        }
        return FontData.Defaults;
    }

    private void pushNodeAndFont(GroupView groupView, @Nullable ReadableMap readableMap) {
        FontData topOrParentFont = getTopOrParentFont(groupView);
        this.mTop++;
        if (readableMap == null) {
            this.mFontContext.add(topOrParentFont);
            return;
        }
        FontData fontData = new FontData(readableMap, topOrParentFont, this.mScale);
        this.mFontSize = fontData.fontSize;
        this.mFontContext.add(fontData);
        this.topFont = fontData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushContext(GroupView groupView, @Nullable ReadableMap readableMap) {
        pushNodeAndFont(groupView, readableMap);
        pushIndices();
    }

    private SVGLength[] getStringArrayFromReadableArray(ArrayList<SVGLength> arrayList) {
        int size = arrayList.size();
        SVGLength[] sVGLengthArr = new SVGLength[size];
        for (int r2 = 0; r2 < size; r2++) {
            sVGLengthArr[r2] = arrayList.get(r2);
        }
        return sVGLengthArr;
    }

    private double[] getDoubleArrayFromReadableArray(ArrayList<SVGLength> arrayList) {
        int size = arrayList.size();
        double[] dArr = new double[size];
        for (int r2 = 0; r2 < size; r2++) {
            dArr[r2] = arrayList.get(r2).value;
        }
        return dArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushContext(boolean z, TextView textView, @Nullable ReadableMap readableMap, @Nullable ArrayList<SVGLength> arrayList, @Nullable ArrayList<SVGLength> arrayList2, @Nullable ArrayList<SVGLength> arrayList3, @Nullable ArrayList<SVGLength> arrayList4, @Nullable ArrayList<SVGLength> arrayList5) {
        if (z) {
            reset();
        }
        pushNodeAndFont(textView, readableMap);
        if (arrayList != null && arrayList.size() != 0) {
            this.mXsIndex++;
            this.mXIndex = -1;
            this.mXIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray = getStringArrayFromReadableArray(arrayList);
            this.mXs = stringArrayFromReadableArray;
            this.mXsContext.add(stringArrayFromReadableArray);
        }
        if (arrayList2 != null && arrayList2.size() != 0) {
            this.mYsIndex++;
            this.mYIndex = -1;
            this.mYIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray2 = getStringArrayFromReadableArray(arrayList2);
            this.mYs = stringArrayFromReadableArray2;
            this.mYsContext.add(stringArrayFromReadableArray2);
        }
        if (arrayList3 != null && arrayList3.size() != 0) {
            this.mDXsIndex++;
            this.mDXIndex = -1;
            this.mDXIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray3 = getStringArrayFromReadableArray(arrayList3);
            this.mDXs = stringArrayFromReadableArray3;
            this.mDXsContext.add(stringArrayFromReadableArray3);
        }
        if (arrayList4 != null && arrayList4.size() != 0) {
            this.mDYsIndex++;
            this.mDYIndex = -1;
            this.mDYIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray4 = getStringArrayFromReadableArray(arrayList4);
            this.mDYs = stringArrayFromReadableArray4;
            this.mDYsContext.add(stringArrayFromReadableArray4);
        }
        if (arrayList5 != null && arrayList5.size() != 0) {
            this.mRsIndex++;
            this.mRIndex = -1;
            this.mRIndices.add(-1);
            double[] doubleArrayFromReadableArray = getDoubleArrayFromReadableArray(arrayList5);
            this.mRs = doubleArrayFromReadableArray;
            this.mRsContext.add(doubleArrayFromReadableArray);
        }
        pushIndices();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void popContext() {
        this.mFontContext.remove(this.mTop);
        this.mXsIndices.remove(this.mTop);
        this.mYsIndices.remove(this.mTop);
        this.mDXsIndices.remove(this.mTop);
        this.mDYsIndices.remove(this.mTop);
        this.mRsIndices.remove(this.mTop);
        int r0 = this.mTop - 1;
        this.mTop = r0;
        int r1 = this.mXsIndex;
        int r2 = this.mYsIndex;
        int r3 = this.mDXsIndex;
        int r4 = this.mDYsIndex;
        int r5 = this.mRsIndex;
        this.topFont = this.mFontContext.get(r0);
        this.mXsIndex = this.mXsIndices.get(this.mTop).intValue();
        this.mYsIndex = this.mYsIndices.get(this.mTop).intValue();
        this.mDXsIndex = this.mDXsIndices.get(this.mTop).intValue();
        this.mDYsIndex = this.mDYsIndices.get(this.mTop).intValue();
        this.mRsIndex = this.mRsIndices.get(this.mTop).intValue();
        if (r1 != this.mXsIndex) {
            this.mXsContext.remove(r1);
            this.mXs = this.mXsContext.get(this.mXsIndex);
            this.mXIndex = this.mXIndices.get(this.mXsIndex).intValue();
        }
        if (r2 != this.mYsIndex) {
            this.mYsContext.remove(r2);
            this.mYs = this.mYsContext.get(this.mYsIndex);
            this.mYIndex = this.mYIndices.get(this.mYsIndex).intValue();
        }
        if (r3 != this.mDXsIndex) {
            this.mDXsContext.remove(r3);
            this.mDXs = this.mDXsContext.get(this.mDXsIndex);
            this.mDXIndex = this.mDXIndices.get(this.mDXsIndex).intValue();
        }
        if (r4 != this.mDYsIndex) {
            this.mDYsContext.remove(r4);
            this.mDYs = this.mDYsContext.get(this.mDYsIndex);
            this.mDYIndex = this.mDYIndices.get(this.mDYsIndex).intValue();
        }
        if (r5 != this.mRsIndex) {
            this.mRsContext.remove(r5);
            this.mRs = this.mRsContext.get(this.mRsIndex);
            this.mRIndex = this.mRIndices.get(this.mRsIndex).intValue();
        }
    }

    private static void incrementIndices(ArrayList<Integer> arrayList, int r2) {
        while (r2 >= 0) {
            arrayList.set(r2, Integer.valueOf(arrayList.get(r2).intValue() + 1));
            r2--;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getFontSize() {
        return this.mFontSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double nextX(double d) {
        incrementIndices(this.mXIndices, this.mXsIndex);
        int r0 = this.mXIndex + 1;
        SVGLength[] sVGLengthArr = this.mXs;
        if (r0 < sVGLengthArr.length) {
            this.mDX = 0.0d;
            this.mXIndex = r0;
            this.f1249mX = PropHelper.fromRelative(sVGLengthArr[r0], this.mWidth, 0.0d, this.mScale, this.mFontSize);
        }
        double d2 = this.f1249mX + d;
        this.f1249mX = d2;
        return d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double nextY() {
        incrementIndices(this.mYIndices, this.mYsIndex);
        int r0 = this.mYIndex + 1;
        SVGLength[] sVGLengthArr = this.mYs;
        if (r0 < sVGLengthArr.length) {
            this.mDY = 0.0d;
            this.mYIndex = r0;
            this.f1250mY = PropHelper.fromRelative(sVGLengthArr[r0], this.mHeight, 0.0d, this.mScale, this.mFontSize);
        }
        return this.f1250mY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double nextDeltaX() {
        incrementIndices(this.mDXIndices, this.mDXsIndex);
        int r0 = this.mDXIndex + 1;
        SVGLength[] sVGLengthArr = this.mDXs;
        if (r0 < sVGLengthArr.length) {
            this.mDXIndex = r0;
            this.mDX += PropHelper.fromRelative(sVGLengthArr[r0], this.mWidth, 0.0d, this.mScale, this.mFontSize);
        }
        return this.mDX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double nextDeltaY() {
        incrementIndices(this.mDYIndices, this.mDYsIndex);
        int r0 = this.mDYIndex + 1;
        SVGLength[] sVGLengthArr = this.mDYs;
        if (r0 < sVGLengthArr.length) {
            this.mDYIndex = r0;
            this.mDY += PropHelper.fromRelative(sVGLengthArr[r0], this.mHeight, 0.0d, this.mScale, this.mFontSize);
        }
        return this.mDY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double nextRotation() {
        incrementIndices(this.mRIndices, this.mRsIndex);
        int min = Math.min(this.mRIndex + 1, this.mRs.length - 1);
        this.mRIndex = min;
        return this.mRs[min];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getWidth() {
        return this.mWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getHeight() {
        return this.mHeight;
    }
}
