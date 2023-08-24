package com.google.zxing.common;

import java.util.Arrays;

/* loaded from: classes3.dex */
public final class BitMatrix implements Cloneable {
    private final int[] bits;
    private final int height;
    private final int rowSize;
    private final int width;

    public BitMatrix(int r1) {
        this(r1, r1);
    }

    public BitMatrix(int r1, int r2) {
        if (r1 <= 0 || r2 <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.width = r1;
        this.height = r2;
        int r12 = (r1 + 31) / 32;
        this.rowSize = r12;
        this.bits = new int[r12 * r2];
    }

    private BitMatrix(int r1, int r2, int r3, int[] r4) {
        this.width = r1;
        this.height = r2;
        this.rowSize = r3;
        this.bits = r4;
    }

    public static BitMatrix parse(boolean[][] zArr) {
        int length = zArr.length;
        int length2 = zArr[0].length;
        BitMatrix bitMatrix = new BitMatrix(length2, length);
        for (int r4 = 0; r4 < length; r4++) {
            boolean[] zArr2 = zArr[r4];
            for (int r6 = 0; r6 < length2; r6++) {
                if (zArr2[r6]) {
                    bitMatrix.set(r6, r4);
                }
            }
        }
        return bitMatrix;
    }

    public static BitMatrix parse(String str, String str2, String str3) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        boolean[] zArr = new boolean[str.length()];
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = -1;
        int r7 = 0;
        while (r3 < str.length()) {
            if (str.charAt(r3) == '\n' || str.charAt(r3) == '\r') {
                if (r4 > r5) {
                    if (r6 == -1) {
                        r6 = r4 - r5;
                    } else if (r4 - r5 != r6) {
                        throw new IllegalArgumentException("row lengths do not match");
                    }
                    r7++;
                    r5 = r4;
                }
                r3++;
            } else {
                if (str.substring(r3, str2.length() + r3).equals(str2)) {
                    r3 += str2.length();
                    zArr[r4] = true;
                } else if (str.substring(r3, str3.length() + r3).equals(str3)) {
                    r3 += str3.length();
                    zArr[r4] = false;
                } else {
                    throw new IllegalArgumentException("illegal character encountered: " + str.substring(r3));
                }
                r4++;
            }
        }
        if (r4 > r5) {
            if (r6 == -1) {
                r6 = r4 - r5;
            } else if (r4 - r5 != r6) {
                throw new IllegalArgumentException("row lengths do not match");
            }
            r7++;
        }
        BitMatrix bitMatrix = new BitMatrix(r6, r7);
        for (int r2 = 0; r2 < r4; r2++) {
            if (zArr[r2]) {
                bitMatrix.set(r2 % r6, r2 / r6);
            }
        }
        return bitMatrix;
    }

    public boolean get(int r2, int r3) {
        return ((this.bits[(r3 * this.rowSize) + (r2 / 32)] >>> (r2 & 31)) & 1) != 0;
    }

    public void set(int r4, int r5) {
        int r52 = (r5 * this.rowSize) + (r4 / 32);
        int[] r0 = this.bits;
        r0[r52] = (1 << (r4 & 31)) | r0[r52];
    }

    public void unset(int r4, int r5) {
        int r52 = (r5 * this.rowSize) + (r4 / 32);
        int[] r0 = this.bits;
        r0[r52] = (~(1 << (r4 & 31))) & r0[r52];
    }

    public void flip(int r4, int r5) {
        int r52 = (r5 * this.rowSize) + (r4 / 32);
        int[] r0 = this.bits;
        r0[r52] = (1 << (r4 & 31)) ^ r0[r52];
    }

    public void xor(BitMatrix bitMatrix) {
        if (this.width != bitMatrix.getWidth() || this.height != bitMatrix.getHeight() || this.rowSize != bitMatrix.getRowSize()) {
            throw new IllegalArgumentException("input matrix dimensions do not match");
        }
        BitArray bitArray = new BitArray(this.width);
        for (int r2 = 0; r2 < this.height; r2++) {
            int r3 = this.rowSize * r2;
            int[] bitArray2 = bitMatrix.getRow(r2, bitArray).getBitArray();
            for (int r5 = 0; r5 < this.rowSize; r5++) {
                int[] r6 = this.bits;
                int r7 = r3 + r5;
                r6[r7] = r6[r7] ^ bitArray2[r5];
            }
        }
    }

    public void clear() {
        int length = this.bits.length;
        for (int r2 = 0; r2 < length; r2++) {
            this.bits[r2] = 0;
        }
    }

    public void setRegion(int r8, int r9, int r10, int r11) {
        if (r9 < 0 || r8 < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (r11 <= 0 || r10 <= 0) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        }
        int r102 = r10 + r8;
        int r112 = r11 + r9;
        if (r112 > this.height || r102 > this.width) {
            throw new IllegalArgumentException("The region must fit inside the matrix");
        }
        while (r9 < r112) {
            int r0 = this.rowSize * r9;
            for (int r1 = r8; r1 < r102; r1++) {
                int[] r2 = this.bits;
                int r3 = (r1 / 32) + r0;
                r2[r3] = r2[r3] | (1 << (r1 & 31));
            }
            r9++;
        }
    }

    public BitArray getRow(int r5, BitArray bitArray) {
        if (bitArray == null || bitArray.getSize() < this.width) {
            bitArray = new BitArray(this.width);
        } else {
            bitArray.clear();
        }
        int r52 = r5 * this.rowSize;
        for (int r0 = 0; r0 < this.rowSize; r0++) {
            bitArray.setBulk(r0 << 5, this.bits[r52 + r0]);
        }
        return bitArray;
    }

    public void setRow(int r4, BitArray bitArray) {
        int[] bitArray2 = bitArray.getBitArray();
        int[] r0 = this.bits;
        int r1 = this.rowSize;
        System.arraycopy(bitArray2, 0, r0, r4 * r1, r1);
    }

    public void rotate180() {
        int width = getWidth();
        int height = getHeight();
        BitArray bitArray = new BitArray(width);
        BitArray bitArray2 = new BitArray(width);
        for (int r0 = 0; r0 < (height + 1) / 2; r0++) {
            bitArray = getRow(r0, bitArray);
            int r4 = (height - 1) - r0;
            bitArray2 = getRow(r4, bitArray2);
            bitArray.reverse();
            bitArray2.reverse();
            setRow(r0, bitArray2);
            setRow(r4, bitArray);
        }
    }

    public int[] getEnclosingRectangle() {
        int r0 = this.width;
        int r1 = this.height;
        int r2 = -1;
        int r4 = -1;
        for (int r5 = 0; r5 < this.height; r5++) {
            int r6 = 0;
            while (true) {
                int r7 = this.rowSize;
                if (r6 < r7) {
                    int r72 = this.bits[(r7 * r5) + r6];
                    if (r72 != 0) {
                        if (r5 < r1) {
                            r1 = r5;
                        }
                        if (r5 > r4) {
                            r4 = r5;
                        }
                        int r8 = r6 << 5;
                        if (r8 < r0) {
                            int r9 = 0;
                            while ((r72 << (31 - r9)) == 0) {
                                r9++;
                            }
                            int r92 = r9 + r8;
                            if (r92 < r0) {
                                r0 = r92;
                            }
                        }
                        if (r8 + 31 > r2) {
                            int r93 = 31;
                            while ((r72 >>> r93) == 0) {
                                r93--;
                            }
                            int r82 = r8 + r93;
                            if (r82 > r2) {
                                r2 = r82;
                            }
                        }
                    }
                    r6++;
                }
            }
        }
        if (r2 < r0 || r4 < r1) {
            return null;
        }
        return new int[]{r0, r1, (r2 - r0) + 1, (r4 - r1) + 1};
    }

    public int[] getTopLeftOnBit() {
        int[] r2;
        int r1 = 0;
        while (true) {
            r2 = this.bits;
            if (r1 >= r2.length || r2[r1] != 0) {
                break;
            }
            r1++;
        }
        if (r1 == r2.length) {
            return null;
        }
        int r3 = this.rowSize;
        int r4 = r1 / r3;
        int r32 = (r1 % r3) << 5;
        int r12 = r2[r1];
        int r22 = 0;
        while ((r12 << (31 - r22)) == 0) {
            r22++;
        }
        return new int[]{r32 + r22, r4};
    }

    public int[] getBottomRightOnBit() {
        int length = this.bits.length - 1;
        while (length >= 0 && this.bits[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int r2 = this.rowSize;
        int r3 = length / r2;
        int r22 = (length % r2) << 5;
        int r4 = 31;
        while ((this.bits[length] >>> r4) == 0) {
            r4--;
        }
        return new int[]{r22 + r4, r3};
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BitMatrix) {
            BitMatrix bitMatrix = (BitMatrix) obj;
            return this.width == bitMatrix.width && this.height == bitMatrix.height && this.rowSize == bitMatrix.rowSize && Arrays.equals(this.bits, bitMatrix.bits);
        }
        return false;
    }

    public int hashCode() {
        int r0 = this.width;
        return (((((((r0 * 31) + r0) * 31) + this.height) * 31) + this.rowSize) * 31) + Arrays.hashCode(this.bits);
    }

    public String toString() {
        return toString("X ", "  ");
    }

    public String toString(String str, String str2) {
        return buildToString(str, str2, "\n");
    }

    @Deprecated
    public String toString(String str, String str2, String str3) {
        return buildToString(str, str2, str3);
    }

    private String buildToString(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.height * (this.width + 1));
        for (int r2 = 0; r2 < this.height; r2++) {
            for (int r3 = 0; r3 < this.width; r3++) {
                sb.append(get(r3, r2) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    /* renamed from: clone */
    public BitMatrix m1519clone() {
        return new BitMatrix(this.width, this.height, this.rowSize, (int[]) this.bits.clone());
    }
}
