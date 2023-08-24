package com.google.zxing.datamatrix.encoder;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class DefaultPlacement {
    private final byte[] bits;
    private final CharSequence codewords;
    private final int numcols;
    private final int numrows;

    public DefaultPlacement(CharSequence charSequence, int r2, int r3) {
        this.codewords = charSequence;
        this.numcols = r2;
        this.numrows = r3;
        byte[] bArr = new byte[r2 * r3];
        this.bits = bArr;
        Arrays.fill(bArr, (byte) -1);
    }

    final int getNumrows() {
        return this.numrows;
    }

    final int getNumcols() {
        return this.numcols;
    }

    final byte[] getBits() {
        return this.bits;
    }

    public final boolean getBit(int r3, int r4) {
        return this.bits[(r4 * this.numcols) + r3] == 1;
    }

    private void setBit(int r3, int r4, boolean z) {
        this.bits[(r4 * this.numcols) + r3] = z ? (byte) 1 : (byte) 0;
    }

    private boolean hasBit(int r3, int r4) {
        return this.bits[(r4 * this.numcols) + r3] >= 0;
    }

    public final void place() {
        int r4;
        int r6;
        int r0 = 0;
        int r2 = 0;
        int r3 = 4;
        while (true) {
            if (r3 == this.numrows && r0 == 0) {
                corner1(r2);
                r2++;
            }
            if (r3 == this.numrows - 2 && r0 == 0 && this.numcols % 4 != 0) {
                corner2(r2);
                r2++;
            }
            if (r3 == this.numrows - 2 && r0 == 0 && this.numcols % 8 == 4) {
                corner3(r2);
                r2++;
            }
            if (r3 == this.numrows + 4 && r0 == 2 && this.numcols % 8 == 0) {
                corner4(r2);
                r2++;
            }
            do {
                if (r3 < this.numrows && r0 >= 0 && !hasBit(r0, r3)) {
                    utah(r3, r0, r2);
                    r2++;
                }
                r3 -= 2;
                r0 += 2;
                if (r3 < 0) {
                    break;
                }
            } while (r0 < this.numcols);
            int r32 = r3 + 1;
            int r02 = r0 + 3;
            do {
                if (r32 >= 0 && r02 < this.numcols && !hasBit(r02, r32)) {
                    utah(r32, r02, r2);
                    r2++;
                }
                r32 += 2;
                r02 -= 2;
                r4 = this.numrows;
                if (r32 >= r4) {
                    break;
                }
            } while (r02 >= 0);
            r3 = r32 + 3;
            r0 = r02 + 1;
            if (r3 >= r4 && r0 >= (r6 = this.numcols)) {
                break;
            }
        }
        if (hasBit(r6 - 1, r4 - 1)) {
            return;
        }
        setBit(this.numcols - 1, this.numrows - 1, true);
        setBit(this.numcols - 2, this.numrows - 2, true);
    }

    private void module(int r2, int r3, int r4, int r5) {
        if (r2 < 0) {
            int r0 = this.numrows;
            r2 += r0;
            r3 += 4 - ((r0 + 4) % 8);
        }
        if (r3 < 0) {
            int r02 = this.numcols;
            r3 += r02;
            r2 += 4 - ((r02 + 4) % 8);
        }
        setBit(r3, r2, (this.codewords.charAt(r4) & (1 << (8 - r5))) != 0);
    }

    private void utah(int r5, int r6, int r7) {
        int r0 = r5 - 2;
        int r1 = r6 - 2;
        module(r0, r1, r7, 1);
        int r2 = r6 - 1;
        module(r0, r2, r7, 2);
        int r02 = r5 - 1;
        module(r02, r1, r7, 3);
        module(r02, r2, r7, 4);
        module(r02, r6, r7, 5);
        module(r5, r1, r7, 6);
        module(r5, r2, r7, 7);
        module(r5, r6, r7, 8);
    }

    private void corner1(int r7) {
        module(this.numrows - 1, 0, r7, 1);
        module(this.numrows - 1, 1, r7, 2);
        module(this.numrows - 1, 2, r7, 3);
        module(0, this.numcols - 2, r7, 4);
        module(0, this.numcols - 1, r7, 5);
        module(1, this.numcols - 1, r7, 6);
        module(2, this.numcols - 1, r7, 7);
        module(3, this.numcols - 1, r7, 8);
    }

    private void corner2(int r7) {
        module(this.numrows - 3, 0, r7, 1);
        module(this.numrows - 2, 0, r7, 2);
        module(this.numrows - 1, 0, r7, 3);
        module(0, this.numcols - 4, r7, 4);
        module(0, this.numcols - 3, r7, 5);
        module(0, this.numcols - 2, r7, 6);
        module(0, this.numcols - 1, r7, 7);
        module(1, this.numcols - 1, r7, 8);
    }

    private void corner3(int r7) {
        module(this.numrows - 3, 0, r7, 1);
        module(this.numrows - 2, 0, r7, 2);
        module(this.numrows - 1, 0, r7, 3);
        module(0, this.numcols - 2, r7, 4);
        module(0, this.numcols - 1, r7, 5);
        module(1, this.numcols - 1, r7, 6);
        module(2, this.numcols - 1, r7, 7);
        module(3, this.numcols - 1, r7, 8);
    }

    private void corner4(int r7) {
        module(this.numrows - 1, 0, r7, 1);
        module(this.numrows - 1, this.numcols - 1, r7, 2);
        module(0, this.numcols - 3, r7, 3);
        module(0, this.numcols - 2, r7, 4);
        module(0, this.numcols - 1, r7, 5);
        module(1, this.numcols - 3, r7, 6);
        module(1, this.numcols - 2, r7, 7);
        module(1, this.numcols - 1, r7, 8);
    }
}
