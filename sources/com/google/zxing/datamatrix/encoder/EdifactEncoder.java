package com.google.zxing.datamatrix.encoder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class EdifactEncoder implements Encoder {
    @Override // com.google.zxing.datamatrix.encoder.Encoder
    public int getEncodingMode() {
        return 4;
    }

    @Override // com.google.zxing.datamatrix.encoder.Encoder
    public void encode(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!encoderContext.hasMoreCharacters()) {
                break;
            }
            encodeChar(encoderContext.getCurrentChar(), sb);
            encoderContext.pos++;
            if (sb.length() >= 4) {
                encoderContext.writeCodewords(encodeToCodewords(sb, 0));
                sb.delete(0, 4);
                if (HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode()) != getEncodingMode()) {
                    encoderContext.signalEncoderChange(0);
                    break;
                }
            }
        }
        sb.append((char) 31);
        handleEOD(encoderContext, sb);
    }

    private static void handleEOD(EncoderContext encoderContext, CharSequence charSequence) {
        try {
            int length = charSequence.length();
            if (length == 0) {
                return;
            }
            boolean z = true;
            if (length == 1) {
                encoderContext.updateSymbolInfo();
                int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount();
                int remainingCharacters = encoderContext.getRemainingCharacters();
                if (remainingCharacters > dataCapacity) {
                    encoderContext.updateSymbolInfo(encoderContext.getCodewordCount() + 1);
                    dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount();
                }
                if (remainingCharacters <= dataCapacity && dataCapacity <= 2) {
                    return;
                }
            }
            if (length > 4) {
                throw new IllegalStateException("Count must not exceed 4");
            }
            int r1 = length - 1;
            String encodeToCodewords = encodeToCodewords(charSequence, 0);
            if (!(!encoderContext.hasMoreCharacters()) || r1 > 2) {
                z = false;
            }
            if (r1 <= 2) {
                encoderContext.updateSymbolInfo(encoderContext.getCodewordCount() + r1);
                if (encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount() >= 3) {
                    encoderContext.updateSymbolInfo(encoderContext.getCodewordCount() + encodeToCodewords.length());
                    z = false;
                }
            }
            if (z) {
                encoderContext.resetSymbolInfo();
                encoderContext.pos -= r1;
            } else {
                encoderContext.writeCodewords(encodeToCodewords);
            }
        } finally {
            encoderContext.signalEncoderChange(0);
        }
    }

    private static void encodeChar(char c, StringBuilder sb) {
        if (c >= ' ' && c <= '?') {
            sb.append(c);
        } else if (c >= '@' && c <= '^') {
            sb.append((char) (c - '@'));
        } else {
            HighLevelEncoder.illegalCharacter(c);
        }
    }

    private static String encodeToCodewords(CharSequence charSequence, int r9) {
        int length = charSequence.length() - r9;
        if (length == 0) {
            throw new IllegalStateException("StringBuilder must not be empty");
        }
        int charAt = (charSequence.charAt(r9) << 18) + ((length >= 2 ? charSequence.charAt(r9 + 1) : (char) 0) << '\f') + ((length >= 3 ? charSequence.charAt(r9 + 2) : (char) 0) << 6) + (length >= 4 ? charSequence.charAt(r9 + 3) : (char) 0);
        char c = (char) ((charAt >> 8) & 255);
        char c2 = (char) (charAt & 255);
        StringBuilder sb = new StringBuilder(3);
        sb.append((char) ((charAt >> 16) & 255));
        if (length >= 2) {
            sb.append(c);
        }
        if (length >= 3) {
            sb.append(c2);
        }
        return sb.toString();
    }
}
