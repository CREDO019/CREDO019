package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes3.dex */
final class BitMatrixParser {
    private final BitMatrix bitMatrix;
    private boolean mirror;
    private FormatInformation parsedFormatInfo;
    private Version parsedVersion;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BitMatrixParser(BitMatrix bitMatrix) throws FormatException {
        int height = bitMatrix.getHeight();
        if (height < 21 || (height & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.bitMatrix = bitMatrix;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FormatInformation readFormatInformation() throws FormatException {
        FormatInformation formatInformation = this.parsedFormatInfo;
        if (formatInformation != null) {
            return formatInformation;
        }
        int r0 = 0;
        int r2 = 0;
        for (int r1 = 0; r1 < 6; r1++) {
            r2 = copyBit(r1, 8, r2);
        }
        int copyBit = copyBit(8, 7, copyBit(8, 8, copyBit(7, 8, r2)));
        for (int r22 = 5; r22 >= 0; r22--) {
            copyBit = copyBit(8, r22, copyBit);
        }
        int height = this.bitMatrix.getHeight();
        int r3 = height - 7;
        for (int r5 = height - 1; r5 >= r3; r5--) {
            r0 = copyBit(8, r5, r0);
        }
        for (int r32 = height - 8; r32 < height; r32++) {
            r0 = copyBit(r32, 8, r0);
        }
        FormatInformation decodeFormatInformation = FormatInformation.decodeFormatInformation(copyBit, r0);
        this.parsedFormatInfo = decodeFormatInformation;
        if (decodeFormatInformation != null) {
            return decodeFormatInformation;
        }
        throw FormatException.getFormatInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Version readVersion() throws FormatException {
        Version version = this.parsedVersion;
        if (version != null) {
            return version;
        }
        int height = this.bitMatrix.getHeight();
        int r1 = (height - 17) / 4;
        if (r1 <= 6) {
            return Version.getVersionForNumber(r1);
        }
        int r12 = height - 11;
        int r3 = 0;
        int r5 = 0;
        for (int r4 = 5; r4 >= 0; r4--) {
            for (int r6 = height - 9; r6 >= r12; r6--) {
                r5 = copyBit(r6, r4, r5);
            }
        }
        Version decodeVersionInformation = Version.decodeVersionInformation(r5);
        if (decodeVersionInformation != null && decodeVersionInformation.getDimensionForVersion() == height) {
            this.parsedVersion = decodeVersionInformation;
            return decodeVersionInformation;
        }
        for (int r2 = 5; r2 >= 0; r2--) {
            for (int r42 = height - 9; r42 >= r12; r42--) {
                r3 = copyBit(r2, r42, r3);
            }
        }
        Version decodeVersionInformation2 = Version.decodeVersionInformation(r3);
        if (decodeVersionInformation2 != null && decodeVersionInformation2.getDimensionForVersion() == height) {
            this.parsedVersion = decodeVersionInformation2;
            return decodeVersionInformation2;
        }
        throw FormatException.getFormatInstance();
    }

    private int copyBit(int r2, int r3, int r4) {
        return this.mirror ? this.bitMatrix.get(r3, r2) : this.bitMatrix.get(r2, r3) ? (r4 << 1) | 1 : r4 << 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] readCodewords() throws FormatException {
        FormatInformation readFormatInformation = readFormatInformation();
        Version readVersion = readVersion();
        DataMask dataMask = DataMask.values()[readFormatInformation.getDataMask()];
        int height = this.bitMatrix.getHeight();
        dataMask.unmaskBitMatrix(this.bitMatrix, height);
        BitMatrix buildFunctionPattern = readVersion.buildFunctionPattern();
        byte[] bArr = new byte[readVersion.getTotalCodewords()];
        int r5 = height - 1;
        boolean z = true;
        int r8 = r5;
        int r9 = 0;
        int r10 = 0;
        int r11 = 0;
        while (r8 > 0) {
            if (r8 == 6) {
                r8--;
            }
            for (int r12 = 0; r12 < height; r12++) {
                int r13 = z ? r5 - r12 : r12;
                for (int r14 = 0; r14 < 2; r14++) {
                    int r15 = r8 - r14;
                    if (!buildFunctionPattern.get(r15, r13)) {
                        r10++;
                        r11 <<= 1;
                        if (this.bitMatrix.get(r15, r13)) {
                            r11 |= 1;
                        }
                        if (r10 == 8) {
                            bArr[r9] = (byte) r11;
                            r9++;
                            r10 = 0;
                            r11 = 0;
                        }
                    }
                }
            }
            z = !z;
            r8 -= 2;
        }
        if (r9 == readVersion.getTotalCodewords()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void remask() {
        if (this.parsedFormatInfo == null) {
            return;
        }
        DataMask.values()[this.parsedFormatInfo.getDataMask()].unmaskBitMatrix(this.bitMatrix, this.bitMatrix.getHeight());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMirror(boolean z) {
        this.parsedVersion = null;
        this.parsedFormatInfo = null;
        this.mirror = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void mirror() {
        int r0 = 0;
        while (r0 < this.bitMatrix.getWidth()) {
            int r1 = r0 + 1;
            for (int r2 = r1; r2 < this.bitMatrix.getHeight(); r2++) {
                if (this.bitMatrix.get(r0, r2) != this.bitMatrix.get(r2, r0)) {
                    this.bitMatrix.flip(r2, r0);
                    this.bitMatrix.flip(r0, r2);
                }
            }
            r0 = r1;
        }
    }
}
