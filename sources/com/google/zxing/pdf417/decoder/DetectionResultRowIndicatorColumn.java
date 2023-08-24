package com.google.zxing.pdf417.decoder;

import com.google.zxing.ResultPoint;

/* loaded from: classes3.dex */
final class DetectionResultRowIndicatorColumn extends DetectionResultColumn {
    private final boolean isLeft;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResultRowIndicatorColumn(BoundingBox boundingBox, boolean z) {
        super(boundingBox);
        this.isLeft = z;
    }

    private void setRowNumbers() {
        Codeword[] codewords;
        for (Codeword codeword : getCodewords()) {
            if (codeword != null) {
                codeword.setRowNumberAsRowIndicatorColumn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void adjustCompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        Codeword[] codewords = getCodewords();
        setRowNumbers();
        removeIncorrectCodewords(codewords, barcodeMetadata);
        BoundingBox boundingBox = getBoundingBox();
        ResultPoint topLeft = this.isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        ResultPoint bottomLeft = this.isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int imageRowToCodewordIndex = imageRowToCodewordIndex((int) topLeft.getY());
        int imageRowToCodewordIndex2 = imageRowToCodewordIndex((int) bottomLeft.getY());
        int r3 = -1;
        int r6 = 0;
        int r7 = 1;
        while (imageRowToCodewordIndex < imageRowToCodewordIndex2) {
            if (codewords[imageRowToCodewordIndex] != null) {
                Codeword codeword = codewords[imageRowToCodewordIndex];
                int rowNumber = codeword.getRowNumber() - r3;
                if (rowNumber == 0) {
                    r6++;
                } else {
                    if (rowNumber == 1) {
                        r7 = Math.max(r7, r6);
                        r3 = codeword.getRowNumber();
                    } else if (rowNumber < 0 || codeword.getRowNumber() >= barcodeMetadata.getRowCount() || rowNumber > imageRowToCodewordIndex) {
                        codewords[imageRowToCodewordIndex] = null;
                    } else {
                        if (r7 > 2) {
                            rowNumber *= r7 - 2;
                        }
                        boolean z = rowNumber >= imageRowToCodewordIndex;
                        for (int r12 = 1; r12 <= rowNumber && !z; r12++) {
                            z = codewords[imageRowToCodewordIndex - r12] != null;
                        }
                        if (z) {
                            codewords[imageRowToCodewordIndex] = null;
                        } else {
                            r3 = codeword.getRowNumber();
                        }
                    }
                    r6 = 1;
                }
            }
            imageRowToCodewordIndex++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] getRowHeights() {
        Codeword[] codewords;
        int rowNumber;
        BarcodeMetadata barcodeMetadata = getBarcodeMetadata();
        if (barcodeMetadata == null) {
            return null;
        }
        adjustIncompleteIndicatorColumnRowNumbers(barcodeMetadata);
        int rowCount = barcodeMetadata.getRowCount();
        int[] r1 = new int[rowCount];
        for (Codeword codeword : getCodewords()) {
            if (codeword != null && (rowNumber = codeword.getRowNumber()) < rowCount) {
                r1[rowNumber] = r1[rowNumber] + 1;
            }
        }
        return r1;
    }

    private void adjustIncompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        BoundingBox boundingBox = getBoundingBox();
        ResultPoint topLeft = this.isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        ResultPoint bottomLeft = this.isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int imageRowToCodewordIndex = imageRowToCodewordIndex((int) bottomLeft.getY());
        Codeword[] codewords = getCodewords();
        int r3 = -1;
        int r4 = 0;
        int r6 = 1;
        for (int imageRowToCodewordIndex2 = imageRowToCodewordIndex((int) topLeft.getY()); imageRowToCodewordIndex2 < imageRowToCodewordIndex; imageRowToCodewordIndex2++) {
            if (codewords[imageRowToCodewordIndex2] != null) {
                Codeword codeword = codewords[imageRowToCodewordIndex2];
                codeword.setRowNumberAsRowIndicatorColumn();
                int rowNumber = codeword.getRowNumber() - r3;
                if (rowNumber == 0) {
                    r4++;
                } else {
                    if (rowNumber == 1) {
                        r6 = Math.max(r6, r4);
                        r3 = codeword.getRowNumber();
                    } else if (codeword.getRowNumber() >= barcodeMetadata.getRowCount()) {
                        codewords[imageRowToCodewordIndex2] = null;
                    } else {
                        r3 = codeword.getRowNumber();
                    }
                    r4 = 1;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeMetadata getBarcodeMetadata() {
        Codeword[] codewords = getCodewords();
        BarcodeValue barcodeValue = new BarcodeValue();
        BarcodeValue barcodeValue2 = new BarcodeValue();
        BarcodeValue barcodeValue3 = new BarcodeValue();
        BarcodeValue barcodeValue4 = new BarcodeValue();
        for (Codeword codeword : codewords) {
            if (codeword != null) {
                codeword.setRowNumberAsRowIndicatorColumn();
                int value = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                if (!this.isLeft) {
                    rowNumber += 2;
                }
                int r9 = rowNumber % 3;
                if (r9 == 0) {
                    barcodeValue2.setValue((value * 3) + 1);
                } else if (r9 == 1) {
                    barcodeValue4.setValue(value / 3);
                    barcodeValue3.setValue(value % 3);
                } else if (r9 == 2) {
                    barcodeValue.setValue(value + 1);
                }
            }
        }
        if (barcodeValue.getValue().length == 0 || barcodeValue2.getValue().length == 0 || barcodeValue3.getValue().length == 0 || barcodeValue4.getValue().length == 0 || barcodeValue.getValue()[0] <= 0 || barcodeValue2.getValue()[0] + barcodeValue3.getValue()[0] < 3 || barcodeValue2.getValue()[0] + barcodeValue3.getValue()[0] > 90) {
            return null;
        }
        BarcodeMetadata barcodeMetadata = new BarcodeMetadata(barcodeValue.getValue()[0], barcodeValue2.getValue()[0], barcodeValue3.getValue()[0], barcodeValue4.getValue()[0]);
        removeIncorrectCodewords(codewords, barcodeMetadata);
        return barcodeMetadata;
    }

    private void removeIncorrectCodewords(Codeword[] codewordArr, BarcodeMetadata barcodeMetadata) {
        for (int r0 = 0; r0 < codewordArr.length; r0++) {
            Codeword codeword = codewordArr[r0];
            if (codewordArr[r0] != null) {
                int value = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                if (rowNumber > barcodeMetadata.getRowCount()) {
                    codewordArr[r0] = null;
                } else {
                    if (!this.isLeft) {
                        rowNumber += 2;
                    }
                    int r1 = rowNumber % 3;
                    if (r1 != 0) {
                        if (r1 == 1) {
                            if (value / 3 != barcodeMetadata.getErrorCorrectionLevel() || value % 3 != barcodeMetadata.getRowCountLowerPart()) {
                                codewordArr[r0] = null;
                            }
                        } else if (r1 == 2 && value + 1 != barcodeMetadata.getColumnCount()) {
                            codewordArr[r0] = null;
                        }
                    } else if ((value * 3) + 1 != barcodeMetadata.getRowCountUpperPart()) {
                        codewordArr[r0] = null;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLeft() {
        return this.isLeft;
    }

    @Override // com.google.zxing.pdf417.decoder.DetectionResultColumn
    public String toString() {
        return "IsLeft: " + this.isLeft + '\n' + super.toString();
    }
}
