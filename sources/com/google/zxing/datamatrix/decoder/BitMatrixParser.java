package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes3.dex */
final class BitMatrixParser {
    private final BitMatrix mappingBitMatrix;
    private final BitMatrix readMappingMatrix;
    private final Version version;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BitMatrixParser(BitMatrix bitMatrix) throws FormatException {
        int height = bitMatrix.getHeight();
        if (height < 8 || height > 144 || (height & 1) != 0) {
            throw FormatException.getFormatInstance();
        }
        this.version = readVersion(bitMatrix);
        BitMatrix extractDataRegion = extractDataRegion(bitMatrix);
        this.mappingBitMatrix = extractDataRegion;
        this.readMappingMatrix = new BitMatrix(extractDataRegion.getWidth(), extractDataRegion.getHeight());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Version getVersion() {
        return this.version;
    }

    private static Version readVersion(BitMatrix bitMatrix) throws FormatException {
        return Version.getVersionForDimensions(bitMatrix.getHeight(), bitMatrix.getWidth());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] readCodewords() throws FormatException {
        byte[] bArr = new byte[this.version.getTotalCodewords()];
        int height = this.mappingBitMatrix.getHeight();
        int width = this.mappingBitMatrix.getWidth();
        int r4 = 0;
        boolean z = false;
        int r6 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int r10 = 4;
        while (true) {
            if (r10 == height && r4 == 0 && !z) {
                bArr[r6] = (byte) readCorner1(height, width);
                r10 -= 2;
                r4 += 2;
                r6++;
                z = true;
            } else {
                int r12 = height - 2;
                if (r10 == r12 && r4 == 0 && (width & 3) != 0 && !z2) {
                    bArr[r6] = (byte) readCorner2(height, width);
                    r10 -= 2;
                    r4 += 2;
                    r6++;
                    z2 = true;
                } else if (r10 == height + 4 && r4 == 2 && (width & 7) == 0 && !z3) {
                    bArr[r6] = (byte) readCorner3(height, width);
                    r10 -= 2;
                    r4 += 2;
                    r6++;
                    z3 = true;
                } else if (r10 == r12 && r4 == 0 && (width & 7) == 4 && !z4) {
                    bArr[r6] = (byte) readCorner4(height, width);
                    r10 -= 2;
                    r4 += 2;
                    r6++;
                    z4 = true;
                } else {
                    do {
                        if (r10 < height && r4 >= 0 && !this.readMappingMatrix.get(r4, r10)) {
                            bArr[r6] = (byte) readUtah(r10, r4, height, width);
                            r6++;
                        }
                        r10 -= 2;
                        r4 += 2;
                        if (r10 < 0) {
                            break;
                        }
                    } while (r4 < width);
                    int r102 = r10 + 1;
                    int r42 = r4 + 3;
                    do {
                        if (r102 >= 0 && r42 < width && !this.readMappingMatrix.get(r42, r102)) {
                            bArr[r6] = (byte) readUtah(r102, r42, height, width);
                            r6++;
                        }
                        r102 += 2;
                        r42 -= 2;
                        if (r102 >= height) {
                            break;
                        }
                    } while (r42 >= 0);
                    r10 = r102 + 3;
                    r4 = r42 + 1;
                }
            }
            if (r10 >= height && r4 >= width) {
                break;
            }
        }
        if (r6 == this.version.getTotalCodewords()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    private boolean readModule(int r1, int r2, int r3, int r4) {
        if (r1 < 0) {
            r1 += r3;
            r2 += 4 - ((r3 + 4) & 7);
        }
        if (r2 < 0) {
            r2 += r4;
            r1 += 4 - ((r4 + 4) & 7);
        }
        this.readMappingMatrix.set(r2, r1);
        return this.mappingBitMatrix.get(r2, r1);
    }

    private int readUtah(int r6, int r7, int r8, int r9) {
        int r0 = r6 - 2;
        int r1 = r7 - 2;
        int r2 = (readModule(r0, r1, r8, r9) ? 1 : 0) << 1;
        int r3 = r7 - 1;
        if (readModule(r0, r3, r8, r9)) {
            r2 |= 1;
        }
        int r02 = r2 << 1;
        int r22 = r6 - 1;
        if (readModule(r22, r1, r8, r9)) {
            r02 |= 1;
        }
        int r03 = r02 << 1;
        if (readModule(r22, r3, r8, r9)) {
            r03 |= 1;
        }
        int r04 = r03 << 1;
        if (readModule(r22, r7, r8, r9)) {
            r04 |= 1;
        }
        int r05 = r04 << 1;
        if (readModule(r6, r1, r8, r9)) {
            r05 |= 1;
        }
        int r06 = r05 << 1;
        if (readModule(r6, r3, r8, r9)) {
            r06 |= 1;
        }
        int r07 = r06 << 1;
        return readModule(r6, r7, r8, r9) ? r07 | 1 : r07;
    }

    private int readCorner1(int r6, int r7) {
        int r0 = r6 - 1;
        int r2 = (readModule(r0, 0, r6, r7) ? 1 : 0) << 1;
        if (readModule(r0, 1, r6, r7)) {
            r2 |= 1;
        }
        int r22 = r2 << 1;
        if (readModule(r0, 2, r6, r7)) {
            r22 |= 1;
        }
        int r02 = r22 << 1;
        if (readModule(0, r7 - 2, r6, r7)) {
            r02 |= 1;
        }
        int r03 = r02 << 1;
        int r23 = r7 - 1;
        if (readModule(0, r23, r6, r7)) {
            r03 |= 1;
        }
        int r04 = r03 << 1;
        if (readModule(1, r23, r6, r7)) {
            r04 |= 1;
        }
        int r05 = r04 << 1;
        if (readModule(2, r23, r6, r7)) {
            r05 |= 1;
        }
        int r06 = r05 << 1;
        return readModule(3, r23, r6, r7) ? r06 | 1 : r06;
    }

    private int readCorner2(int r5, int r6) {
        int r0 = (readModule(r5 + (-3), 0, r5, r6) ? 1 : 0) << 1;
        if (readModule(r5 - 2, 0, r5, r6)) {
            r0 |= 1;
        }
        int r02 = r0 << 1;
        if (readModule(r5 - 1, 0, r5, r6)) {
            r02 |= 1;
        }
        int r03 = r02 << 1;
        if (readModule(0, r6 - 4, r5, r6)) {
            r03 |= 1;
        }
        int r04 = r03 << 1;
        if (readModule(0, r6 - 3, r5, r6)) {
            r04 |= 1;
        }
        int r05 = r04 << 1;
        if (readModule(0, r6 - 2, r5, r6)) {
            r05 |= 1;
        }
        int r06 = r05 << 1;
        int r3 = r6 - 1;
        if (readModule(0, r3, r5, r6)) {
            r06 |= 1;
        }
        int r07 = r06 << 1;
        return readModule(1, r3, r5, r6) ? r07 | 1 : r07;
    }

    private int readCorner3(int r8, int r9) {
        int r0 = r8 - 1;
        int r2 = (readModule(r0, 0, r8, r9) ? 1 : 0) << 1;
        int r4 = r9 - 1;
        if (readModule(r0, r4, r8, r9)) {
            r2 |= 1;
        }
        int r02 = r2 << 1;
        int r22 = r9 - 3;
        if (readModule(0, r22, r8, r9)) {
            r02 |= 1;
        }
        int r03 = r02 << 1;
        int r5 = r9 - 2;
        if (readModule(0, r5, r8, r9)) {
            r03 |= 1;
        }
        int r04 = r03 << 1;
        if (readModule(0, r4, r8, r9)) {
            r04 |= 1;
        }
        int r05 = r04 << 1;
        if (readModule(1, r22, r8, r9)) {
            r05 |= 1;
        }
        int r06 = r05 << 1;
        if (readModule(1, r5, r8, r9)) {
            r06 |= 1;
        }
        int r07 = r06 << 1;
        return readModule(1, r4, r8, r9) ? r07 | 1 : r07;
    }

    private int readCorner4(int r5, int r6) {
        int r0 = (readModule(r5 + (-3), 0, r5, r6) ? 1 : 0) << 1;
        if (readModule(r5 - 2, 0, r5, r6)) {
            r0 |= 1;
        }
        int r02 = r0 << 1;
        if (readModule(r5 - 1, 0, r5, r6)) {
            r02 |= 1;
        }
        int r03 = r02 << 1;
        if (readModule(0, r6 - 2, r5, r6)) {
            r03 |= 1;
        }
        int r04 = r03 << 1;
        int r3 = r6 - 1;
        if (readModule(0, r3, r5, r6)) {
            r04 |= 1;
        }
        int r05 = r04 << 1;
        if (readModule(1, r3, r5, r6)) {
            r05 |= 1;
        }
        int r06 = r05 << 1;
        if (readModule(2, r3, r5, r6)) {
            r06 |= 1;
        }
        int r07 = r06 << 1;
        return readModule(3, r3, r5, r6) ? r07 | 1 : r07;
    }

    private BitMatrix extractDataRegion(BitMatrix bitMatrix) {
        int symbolSizeRows = this.version.getSymbolSizeRows();
        int symbolSizeColumns = this.version.getSymbolSizeColumns();
        if (bitMatrix.getHeight() != symbolSizeRows) {
            throw new IllegalArgumentException("Dimension of bitMatrix must match the version size");
        }
        int dataRegionSizeRows = this.version.getDataRegionSizeRows();
        int dataRegionSizeColumns = this.version.getDataRegionSizeColumns();
        int r1 = symbolSizeRows / dataRegionSizeRows;
        int r2 = symbolSizeColumns / dataRegionSizeColumns;
        BitMatrix bitMatrix2 = new BitMatrix(r2 * dataRegionSizeColumns, r1 * dataRegionSizeRows);
        for (int r6 = 0; r6 < r1; r6++) {
            int r8 = r6 * dataRegionSizeRows;
            for (int r9 = 0; r9 < r2; r9++) {
                int r10 = r9 * dataRegionSizeColumns;
                for (int r11 = 0; r11 < dataRegionSizeRows; r11++) {
                    int r12 = ((dataRegionSizeRows + 2) * r6) + 1 + r11;
                    int r13 = r8 + r11;
                    for (int r14 = 0; r14 < dataRegionSizeColumns; r14++) {
                        if (bitMatrix.get(((dataRegionSizeColumns + 2) * r9) + 1 + r14, r12)) {
                            bitMatrix2.set(r10 + r14, r13);
                        }
                    }
                }
            }
        }
        return bitMatrix2;
    }
}
