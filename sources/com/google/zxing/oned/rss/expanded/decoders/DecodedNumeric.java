package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;

/* loaded from: classes3.dex */
final class DecodedNumeric extends DecodedObject {
    static final int FNC1 = 10;
    private final int firstDigit;
    private final int secondDigit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodedNumeric(int r1, int r2, int r3) throws FormatException {
        super(r1);
        if (r2 < 0 || r2 > 10 || r3 < 0 || r3 > 10) {
            throw FormatException.getFormatInstance();
        }
        this.firstDigit = r2;
        this.secondDigit = r3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getFirstDigit() {
        return this.firstDigit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSecondDigit() {
        return this.secondDigit;
    }

    int getValue() {
        return (this.firstDigit * 10) + this.secondDigit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFirstDigitFNC1() {
        return this.firstDigit == 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSecondDigitFNC1() {
        return this.secondDigit == 10;
    }

    boolean isAnyFNC1() {
        return this.firstDigit == 10 || this.secondDigit == 10;
    }
}
