package com.google.zxing;

/* loaded from: classes3.dex */
public final class Dimension {
    private final int height;
    private final int width;

    public Dimension(int r1, int r2) {
        if (r1 < 0 || r2 < 0) {
            throw new IllegalArgumentException();
        }
        this.width = r1;
        this.height = r2;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Dimension) {
            Dimension dimension = (Dimension) obj;
            if (this.width == dimension.width && this.height == dimension.height) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.width * 32713) + this.height;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }
}
