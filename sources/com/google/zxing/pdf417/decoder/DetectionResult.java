package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.Formatter;

/* loaded from: classes3.dex */
final class DetectionResult {
    private static final int ADJUST_ROW_NUMBER_SKIP = 2;
    private final int barcodeColumnCount;
    private final BarcodeMetadata barcodeMetadata;
    private BoundingBox boundingBox;
    private final DetectionResultColumn[] detectionResultColumns;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResult(BarcodeMetadata barcodeMetadata, BoundingBox boundingBox) {
        this.barcodeMetadata = barcodeMetadata;
        int columnCount = barcodeMetadata.getColumnCount();
        this.barcodeColumnCount = columnCount;
        this.boundingBox = boundingBox;
        this.detectionResultColumns = new DetectionResultColumn[columnCount + 2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResultColumn[] getDetectionResultColumns() {
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[0]);
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[this.barcodeColumnCount + 1]);
        int r0 = PDF417Common.MAX_CODEWORDS_IN_BARCODE;
        while (true) {
            int adjustRowNumbers = adjustRowNumbers();
            if (adjustRowNumbers <= 0 || adjustRowNumbers >= r0) {
                break;
            }
            r0 = adjustRowNumbers;
        }
        return this.detectionResultColumns;
    }

    private void adjustIndicatorColumnRowNumbers(DetectionResultColumn detectionResultColumn) {
        if (detectionResultColumn != null) {
            ((DetectionResultRowIndicatorColumn) detectionResultColumn).adjustCompleteIndicatorColumnRowNumbers(this.barcodeMetadata);
        }
    }

    private int adjustRowNumbers() {
        int adjustRowNumbersByRow = adjustRowNumbersByRow();
        if (adjustRowNumbersByRow == 0) {
            return 0;
        }
        for (int r3 = 1; r3 < this.barcodeColumnCount + 1; r3++) {
            Codeword[] codewords = this.detectionResultColumns[r3].getCodewords();
            for (int r5 = 0; r5 < codewords.length; r5++) {
                if (codewords[r5] != null && !codewords[r5].hasValidRowNumber()) {
                    adjustRowNumbers(r3, r5, codewords);
                }
            }
        }
        return adjustRowNumbersByRow;
    }

    private int adjustRowNumbersByRow() {
        adjustRowNumbersFromBothRI();
        return adjustRowNumbersFromLRI() + adjustRowNumbersFromRRI();
    }

    private void adjustRowNumbersFromBothRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] == null || detectionResultColumnArr[this.barcodeColumnCount + 1] == null) {
            return;
        }
        Codeword[] codewords = detectionResultColumnArr[0].getCodewords();
        Codeword[] codewords2 = this.detectionResultColumns[this.barcodeColumnCount + 1].getCodewords();
        for (int r1 = 0; r1 < codewords.length; r1++) {
            if (codewords[r1] != null && codewords2[r1] != null && codewords[r1].getRowNumber() == codewords2[r1].getRowNumber()) {
                for (int r4 = 1; r4 <= this.barcodeColumnCount; r4++) {
                    Codeword codeword = this.detectionResultColumns[r4].getCodewords()[r1];
                    if (codeword != null) {
                        codeword.setRowNumber(codewords[r1].getRowNumber());
                        if (!codeword.hasValidRowNumber()) {
                            this.detectionResultColumns[r4].getCodewords()[r1] = null;
                        }
                    }
                }
            }
        }
    }

    private int adjustRowNumbersFromRRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int r1 = this.barcodeColumnCount;
        if (detectionResultColumnArr[r1 + 1] == null) {
            return 0;
        }
        Codeword[] codewords = detectionResultColumnArr[r1 + 1].getCodewords();
        int r2 = 0;
        for (int r12 = 0; r12 < codewords.length; r12++) {
            if (codewords[r12] != null) {
                int rowNumber = codewords[r12].getRowNumber();
                int r6 = 0;
                for (int r5 = this.barcodeColumnCount + 1; r5 > 0 && r6 < 2; r5--) {
                    Codeword codeword = this.detectionResultColumns[r5].getCodewords()[r12];
                    if (codeword != null) {
                        r6 = adjustRowNumberIfValid(rowNumber, r6, codeword);
                        if (!codeword.hasValidRowNumber()) {
                            r2++;
                        }
                    }
                }
            }
        }
        return r2;
    }

    private int adjustRowNumbersFromLRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] == null) {
            return 0;
        }
        Codeword[] codewords = detectionResultColumnArr[0].getCodewords();
        int r3 = 0;
        for (int r2 = 0; r2 < codewords.length; r2++) {
            if (codewords[r2] != null) {
                int rowNumber = codewords[r2].getRowNumber();
                int r7 = 0;
                for (int r6 = 1; r6 < this.barcodeColumnCount + 1 && r7 < 2; r6++) {
                    Codeword codeword = this.detectionResultColumns[r6].getCodewords()[r2];
                    if (codeword != null) {
                        r7 = adjustRowNumberIfValid(rowNumber, r7, codeword);
                        if (!codeword.hasValidRowNumber()) {
                            r3++;
                        }
                    }
                }
            }
        }
        return r3;
    }

    private static int adjustRowNumberIfValid(int r1, int r2, Codeword codeword) {
        if (codeword == null || codeword.hasValidRowNumber()) {
            return r2;
        }
        if (codeword.isValidRowNumber(r1)) {
            codeword.setRowNumber(r1);
            return 0;
        }
        return r2 + 1;
    }

    private void adjustRowNumbers(int r11, int r12, Codeword[] codewordArr) {
        Codeword codeword = codewordArr[r12];
        Codeword[] codewords = this.detectionResultColumns[r11 - 1].getCodewords();
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int r112 = r11 + 1;
        Codeword[] codewords2 = detectionResultColumnArr[r112] != null ? detectionResultColumnArr[r112].getCodewords() : codewords;
        Codeword[] codewordArr2 = new Codeword[14];
        codewordArr2[2] = codewords[r12];
        codewordArr2[3] = codewords2[r12];
        if (r12 > 0) {
            int r7 = r12 - 1;
            codewordArr2[0] = codewordArr[r7];
            codewordArr2[4] = codewords[r7];
            codewordArr2[5] = codewords2[r7];
        }
        if (r12 > 1) {
            int r8 = r12 - 2;
            codewordArr2[8] = codewordArr[r8];
            codewordArr2[10] = codewords[r8];
            codewordArr2[11] = codewords2[r8];
        }
        if (r12 < codewordArr.length - 1) {
            int r72 = r12 + 1;
            codewordArr2[1] = codewordArr[r72];
            codewordArr2[6] = codewords[r72];
            codewordArr2[7] = codewords2[r72];
        }
        if (r12 < codewordArr.length - 2) {
            int r122 = r12 + 2;
            codewordArr2[9] = codewordArr[r122];
            codewordArr2[12] = codewords[r122];
            codewordArr2[13] = codewords2[r122];
        }
        for (int r5 = 0; r5 < 14 && !adjustRowNumber(codeword, codewordArr2[r5]); r5++) {
        }
    }

    private static boolean adjustRowNumber(Codeword codeword, Codeword codeword2) {
        if (codeword2 != null && codeword2.hasValidRowNumber() && codeword2.getBucket() == codeword.getBucket()) {
            codeword.setRowNumber(codeword2.getRowNumber());
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBarcodeColumnCount() {
        return this.barcodeColumnCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBarcodeRowCount() {
        return this.barcodeMetadata.getRowCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBarcodeECLevel() {
        return this.barcodeMetadata.getErrorCorrectionLevel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDetectionResultColumn(int r2, DetectionResultColumn detectionResultColumn) {
        this.detectionResultColumns[r2] = detectionResultColumn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResultColumn getDetectionResultColumn(int r2) {
        return this.detectionResultColumns[r2];
    }

    public String toString() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        DetectionResultColumn detectionResultColumn = detectionResultColumnArr[0];
        if (detectionResultColumn == null) {
            detectionResultColumn = detectionResultColumnArr[this.barcodeColumnCount + 1];
        }
        Formatter formatter = new Formatter();
        for (int r4 = 0; r4 < detectionResultColumn.getCodewords().length; r4++) {
            try {
                formatter.format("CW %3d:", Integer.valueOf(r4));
                for (int r5 = 0; r5 < this.barcodeColumnCount + 2; r5++) {
                    DetectionResultColumn[] detectionResultColumnArr2 = this.detectionResultColumns;
                    if (detectionResultColumnArr2[r5] == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        Codeword codeword = detectionResultColumnArr2[r5].getCodewords()[r4];
                        if (codeword == null) {
                            formatter.format("    |   ", new Object[0]);
                        } else {
                            formatter.format(" %3d|%3d", Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue()));
                        }
                    }
                }
                formatter.format("%n", new Object[0]);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        formatter.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
