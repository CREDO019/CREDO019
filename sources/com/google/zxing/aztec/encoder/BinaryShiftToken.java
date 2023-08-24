package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
final class BinaryShiftToken extends Token {
    private final short binaryShiftByteCount;
    private final short binaryShiftStart;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinaryShiftToken(Token token, int r2, int r3) {
        super(token);
        this.binaryShiftStart = (short) r2;
        this.binaryShiftByteCount = (short) r3;
    }

    @Override // com.google.zxing.aztec.encoder.Token
    public void appendTo(BitArray bitArray, byte[] bArr) {
        int r0 = 0;
        while (true) {
            short s = this.binaryShiftByteCount;
            if (r0 >= s) {
                return;
            }
            if (r0 == 0 || (r0 == 31 && s <= 62)) {
                bitArray.appendBits(31, 5);
                short s2 = this.binaryShiftByteCount;
                if (s2 > 62) {
                    bitArray.appendBits(s2 - 31, 16);
                } else if (r0 == 0) {
                    bitArray.appendBits(Math.min((int) s2, 31), 5);
                } else {
                    bitArray.appendBits(s2 - 31, 5);
                }
            }
            bitArray.appendBits(bArr[this.binaryShiftStart + r0], 8);
            r0++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append((int) this.binaryShiftStart);
        sb.append("::");
        sb.append((this.binaryShiftStart + this.binaryShiftByteCount) - 1);
        sb.append(Typography.greater);
        return sb.toString();
    }
}
