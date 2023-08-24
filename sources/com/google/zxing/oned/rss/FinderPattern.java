package com.google.zxing.oned.rss;

import com.google.zxing.ResultPoint;

/* loaded from: classes3.dex */
public final class FinderPattern {
    private final ResultPoint[] resultPoints;
    private final int[] startEnd;
    private final int value;

    public FinderPattern(int r1, int[] r2, int r3, int r4, int r5) {
        this.value = r1;
        this.startEnd = r2;
        float f = r5;
        this.resultPoints = new ResultPoint[]{new ResultPoint(r3, f), new ResultPoint(r4, f)};
    }

    public int getValue() {
        return this.value;
    }

    public int[] getStartEnd() {
        return this.startEnd;
    }

    public ResultPoint[] getResultPoints() {
        return this.resultPoints;
    }

    public boolean equals(Object obj) {
        return (obj instanceof FinderPattern) && this.value == ((FinderPattern) obj).value;
    }

    public int hashCode() {
        return this.value;
    }
}
