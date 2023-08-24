package com.google.zxing.qrcode.encoder;

/* loaded from: classes3.dex */
final class MaskUtil {

    /* renamed from: N1 */
    private static final int f1240N1 = 3;

    /* renamed from: N2 */
    private static final int f1241N2 = 3;

    /* renamed from: N3 */
    private static final int f1242N3 = 40;

    /* renamed from: N4 */
    private static final int f1243N4 = 10;

    private MaskUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int applyMaskPenaltyRule1(ByteMatrix byteMatrix) {
        return applyMaskPenaltyRule1Internal(byteMatrix, true) + applyMaskPenaltyRule1Internal(byteMatrix, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int applyMaskPenaltyRule2(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int r4 = 0;
        for (int r3 = 0; r3 < height - 1; r3++) {
            byte[] bArr = array[r3];
            int r6 = 0;
            while (r6 < width - 1) {
                byte b = bArr[r6];
                int r8 = r6 + 1;
                if (b == bArr[r8]) {
                    int r9 = r3 + 1;
                    if (b == array[r9][r6] && b == array[r9][r8]) {
                        r4++;
                    }
                }
                r6 = r8;
            }
        }
        return r4 * 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int applyMaskPenaltyRule3(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int r4 = 0;
        for (int r3 = 0; r3 < height; r3++) {
            for (int r5 = 0; r5 < width; r5++) {
                byte[] bArr = array[r3];
                int r7 = r5 + 6;
                if (r7 < width && bArr[r5] == 1 && bArr[r5 + 1] == 0 && bArr[r5 + 2] == 1 && bArr[r5 + 3] == 1 && bArr[r5 + 4] == 1 && bArr[r5 + 5] == 0 && bArr[r7] == 1 && (isWhiteHorizontal(bArr, r5 - 4, r5) || isWhiteHorizontal(bArr, r5 + 7, r5 + 11))) {
                    r4++;
                }
                int r6 = r3 + 6;
                if (r6 < height && array[r3][r5] == 1 && array[r3 + 1][r5] == 0 && array[r3 + 2][r5] == 1 && array[r3 + 3][r5] == 1 && array[r3 + 4][r5] == 1 && array[r3 + 5][r5] == 0 && array[r6][r5] == 1 && (isWhiteVertical(array, r5, r3 - 4, r3) || isWhiteVertical(array, r5, r3 + 7, r3 + 11))) {
                    r4++;
                }
            }
        }
        return r4 * 40;
    }

    private static boolean isWhiteHorizontal(byte[] bArr, int r4, int r5) {
        int min = Math.min(r5, bArr.length);
        for (int max = Math.max(r4, 0); max < min; max++) {
            if (bArr[max] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isWhiteVertical(byte[][] bArr, int r4, int r5, int r6) {
        int min = Math.min(r6, bArr.length);
        for (int max = Math.max(r5, 0); max < min; max++) {
            if (bArr[max][r4] == 1) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int applyMaskPenaltyRule4(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int r5 = 0;
        for (int r4 = 0; r4 < height; r4++) {
            byte[] bArr = array[r4];
            for (int r8 = 0; r8 < width; r8++) {
                if (bArr[r8] == 1) {
                    r5++;
                }
            }
        }
        int height2 = byteMatrix.getHeight() * byteMatrix.getWidth();
        return ((Math.abs((r5 << 1) - height2) * 10) / height2) * 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean getDataMaskBit(int r1, int r2, int r3) {
        int r12;
        int r13;
        switch (r1) {
            case 0:
                r3 += r2;
                r12 = r3 & 1;
                break;
            case 1:
                r12 = r3 & 1;
                break;
            case 2:
                r12 = r2 % 3;
                break;
            case 3:
                r12 = (r3 + r2) % 3;
                break;
            case 4:
                r3 /= 2;
                r2 /= 3;
                r3 += r2;
                r12 = r3 & 1;
                break;
            case 5:
                int r32 = r3 * r2;
                r12 = (r32 & 1) + (r32 % 3);
                break;
            case 6:
                int r33 = r3 * r2;
                r13 = (r33 & 1) + (r33 % 3);
                r12 = r13 & 1;
                break;
            case 7:
                r13 = ((r3 * r2) % 3) + ((r3 + r2) & 1);
                r12 = r13 & 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: ".concat(String.valueOf(r1)));
        }
        return r12 == 0;
    }

    private static int applyMaskPenaltyRule1Internal(ByteMatrix byteMatrix, boolean z) {
        int height = z ? byteMatrix.getHeight() : byteMatrix.getWidth();
        int width = z ? byteMatrix.getWidth() : byteMatrix.getHeight();
        byte[][] array = byteMatrix.getArray();
        int r4 = 0;
        for (int r3 = 0; r3 < height; r3++) {
            byte b = -1;
            int r7 = 0;
            for (int r6 = 0; r6 < width; r6++) {
                byte b2 = z ? array[r3][r6] : array[r6][r3];
                if (b2 == b) {
                    r7++;
                } else {
                    if (r7 >= 5) {
                        r4 += (r7 - 5) + 3;
                    }
                    b = b2;
                    r7 = 1;
                }
            }
            if (r7 >= 5) {
                r4 += (r7 - 5) + 3;
            }
        }
        return r4;
    }
}
