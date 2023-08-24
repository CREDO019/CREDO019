package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitMatrix;

/* loaded from: classes3.dex */
public final class AztecCode {
    private int codeWords;
    private boolean compact;
    private int layers;
    private BitMatrix matrix;
    private int size;

    public boolean isCompact() {
        return this.compact;
    }

    public void setCompact(boolean z) {
        this.compact = z;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int r1) {
        this.size = r1;
    }

    public int getLayers() {
        return this.layers;
    }

    public void setLayers(int r1) {
        this.layers = r1;
    }

    public int getCodeWords() {
        return this.codeWords;
    }

    public void setCodeWords(int r1) {
        this.codeWords = r1;
    }

    public BitMatrix getMatrix() {
        return this.matrix;
    }

    public void setMatrix(BitMatrix bitMatrix) {
        this.matrix = bitMatrix;
    }
}
