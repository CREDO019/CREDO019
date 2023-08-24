package com.google.zxing.pdf417.encoder;

/* loaded from: classes3.dex */
public final class Dimensions {
    private final int maxCols;
    private final int maxRows;
    private final int minCols;
    private final int minRows;

    public Dimensions(int r1, int r2, int r3, int r4) {
        this.minCols = r1;
        this.maxCols = r2;
        this.minRows = r3;
        this.maxRows = r4;
    }

    public int getMinCols() {
        return this.minCols;
    }

    public int getMaxCols() {
        return this.maxCols;
    }

    public int getMinRows() {
        return this.minRows;
    }

    public int getMaxRows() {
        return this.maxRows;
    }
}
