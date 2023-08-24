package com.google.zxing.oned.rss.expanded.decoders;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import kotlin.text.Typography;
import org.apache.commons.p028io.IOUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class GeneralAppIdDecoder {
    private final BitArray information;
    private final CurrentParsingState current = new CurrentParsingState();
    private final StringBuilder buffer = new StringBuilder();

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeneralAppIdDecoder(BitArray bitArray) {
        this.information = bitArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String decodeAllCodes(StringBuilder sb, int r6) throws NotFoundException, FormatException {
        String str = null;
        while (true) {
            DecodedInformation decodeGeneralPurposeField = decodeGeneralPurposeField(r6, str);
            String parseFieldsInGeneralPurpose = FieldParser.parseFieldsInGeneralPurpose(decodeGeneralPurposeField.getNewString());
            if (parseFieldsInGeneralPurpose != null) {
                sb.append(parseFieldsInGeneralPurpose);
            }
            String valueOf = decodeGeneralPurposeField.isRemaining() ? String.valueOf(decodeGeneralPurposeField.getRemainingValue()) : null;
            if (r6 != decodeGeneralPurposeField.getNewPosition()) {
                r6 = decodeGeneralPurposeField.getNewPosition();
                str = valueOf;
            } else {
                return sb.toString();
            }
        }
    }

    private boolean isStillNumeric(int r4) {
        if (r4 + 7 > this.information.getSize()) {
            return r4 + 4 <= this.information.getSize();
        }
        int r0 = r4;
        while (true) {
            int r1 = r4 + 3;
            if (r0 < r1) {
                if (this.information.get(r0)) {
                    return true;
                }
                r0++;
            } else {
                return this.information.get(r1);
            }
        }
    }

    private DecodedNumeric decodeNumeric(int r4) throws FormatException {
        int r0 = r4 + 7;
        if (r0 > this.information.getSize()) {
            int extractNumericValueFromBitArray = extractNumericValueFromBitArray(r4, 4);
            if (extractNumericValueFromBitArray == 0) {
                return new DecodedNumeric(this.information.getSize(), 10, 10);
            }
            return new DecodedNumeric(this.information.getSize(), extractNumericValueFromBitArray - 1, 10);
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(r4, 7) - 8;
        return new DecodedNumeric(r0, extractNumericValueFromBitArray2 / 11, extractNumericValueFromBitArray2 % 11);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int extractNumericValueFromBitArray(int r2, int r3) {
        return extractNumericValueFromBitArray(this.information, r2, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int extractNumericValueFromBitArray(BitArray bitArray, int r5, int r6) {
        int r1 = 0;
        for (int r0 = 0; r0 < r6; r0++) {
            if (bitArray.get(r5 + r0)) {
                r1 |= 1 << ((r6 - r0) - 1);
            }
        }
        return r1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodedInformation decodeGeneralPurposeField(int r3, String str) throws FormatException {
        this.buffer.setLength(0);
        if (str != null) {
            this.buffer.append(str);
        }
        this.current.setPosition(r3);
        DecodedInformation parseBlocks = parseBlocks();
        if (parseBlocks != null && parseBlocks.isRemaining()) {
            return new DecodedInformation(this.current.getPosition(), this.buffer.toString(), parseBlocks.getRemainingValue());
        }
        return new DecodedInformation(this.current.getPosition(), this.buffer.toString());
    }

    private DecodedInformation parseBlocks() throws FormatException {
        BlockParsedResult parseNumericBlock;
        boolean isFinished;
        do {
            int position = this.current.getPosition();
            if (this.current.isAlpha()) {
                parseNumericBlock = parseAlphaBlock();
                isFinished = parseNumericBlock.isFinished();
            } else if (this.current.isIsoIec646()) {
                parseNumericBlock = parseIsoIec646Block();
                isFinished = parseNumericBlock.isFinished();
            } else {
                parseNumericBlock = parseNumericBlock();
                isFinished = parseNumericBlock.isFinished();
            }
            if (!(position != this.current.getPosition()) && !isFinished) {
                break;
            }
        } while (!isFinished);
        return parseNumericBlock.getDecodedInformation();
    }

    private BlockParsedResult parseNumericBlock() throws FormatException {
        DecodedInformation decodedInformation;
        while (isStillNumeric(this.current.getPosition())) {
            DecodedNumeric decodeNumeric = decodeNumeric(this.current.getPosition());
            this.current.setPosition(decodeNumeric.getNewPosition());
            if (decodeNumeric.isFirstDigitFNC1()) {
                if (decodeNumeric.isSecondDigitFNC1()) {
                    decodedInformation = new DecodedInformation(this.current.getPosition(), this.buffer.toString());
                } else {
                    decodedInformation = new DecodedInformation(this.current.getPosition(), this.buffer.toString(), decodeNumeric.getSecondDigit());
                }
                return new BlockParsedResult(decodedInformation, true);
            }
            this.buffer.append(decodeNumeric.getFirstDigit());
            if (decodeNumeric.isSecondDigitFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
            }
            this.buffer.append(decodeNumeric.getSecondDigit());
        }
        if (isNumericToAlphaNumericLatch(this.current.getPosition())) {
            this.current.setAlpha();
            this.current.incrementPosition(4);
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult parseIsoIec646Block() throws FormatException {
        while (isStillIsoIec646(this.current.getPosition())) {
            DecodedChar decodeIsoIec646 = decodeIsoIec646(this.current.getPosition());
            this.current.setPosition(decodeIsoIec646.getNewPosition());
            if (decodeIsoIec646.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
            }
            this.buffer.append(decodeIsoIec646.getValue());
        }
        if (isAlphaOr646ToNumericLatch(this.current.getPosition())) {
            this.current.incrementPosition(3);
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.getPosition())) {
            if (this.current.getPosition() + 5 < this.information.getSize()) {
                this.current.incrementPosition(5);
            } else {
                this.current.setPosition(this.information.getSize());
            }
            this.current.setAlpha();
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult parseAlphaBlock() {
        while (isStillAlpha(this.current.getPosition())) {
            DecodedChar decodeAlphanumeric = decodeAlphanumeric(this.current.getPosition());
            this.current.setPosition(decodeAlphanumeric.getNewPosition());
            if (decodeAlphanumeric.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
            }
            this.buffer.append(decodeAlphanumeric.getValue());
        }
        if (isAlphaOr646ToNumericLatch(this.current.getPosition())) {
            this.current.incrementPosition(3);
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.getPosition())) {
            if (this.current.getPosition() + 5 < this.information.getSize()) {
                this.current.incrementPosition(5);
            } else {
                this.current.setPosition(this.information.getSize());
            }
            this.current.setIsoIec646();
        }
        return new BlockParsedResult(false);
    }

    private boolean isStillIsoIec646(int r5) {
        int extractNumericValueFromBitArray;
        if (r5 + 5 > this.information.getSize()) {
            return false;
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(r5, 5);
        if (extractNumericValueFromBitArray2 < 5 || extractNumericValueFromBitArray2 >= 16) {
            if (r5 + 7 > this.information.getSize()) {
                return false;
            }
            int extractNumericValueFromBitArray3 = extractNumericValueFromBitArray(r5, 7);
            if (extractNumericValueFromBitArray3 < 64 || extractNumericValueFromBitArray3 >= 116) {
                return r5 + 8 <= this.information.getSize() && (extractNumericValueFromBitArray = extractNumericValueFromBitArray(r5, 8)) >= 232 && extractNumericValueFromBitArray < 253;
            }
            return true;
        }
        return true;
    }

    private DecodedChar decodeIsoIec646(int r5) throws FormatException {
        char c;
        int extractNumericValueFromBitArray = extractNumericValueFromBitArray(r5, 5);
        if (extractNumericValueFromBitArray == 15) {
            return new DecodedChar(r5 + 5, '$');
        }
        if (extractNumericValueFromBitArray >= 5 && extractNumericValueFromBitArray < 15) {
            return new DecodedChar(r5 + 5, (char) ((extractNumericValueFromBitArray + 48) - 5));
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(r5, 7);
        if (extractNumericValueFromBitArray2 < 64 || extractNumericValueFromBitArray2 >= 90) {
            if (extractNumericValueFromBitArray2 >= 90 && extractNumericValueFromBitArray2 < 116) {
                return new DecodedChar(r5 + 7, (char) (extractNumericValueFromBitArray2 + 7));
            }
            switch (extractNumericValueFromBitArray(r5, 8)) {
                case 232:
                    c = '!';
                    break;
                case 233:
                    c = Typography.quote;
                    break;
                case 234:
                    c = '%';
                    break;
                case 235:
                    c = Typography.amp;
                    break;
                case 236:
                    c = '\'';
                    break;
                case 237:
                    c = '(';
                    break;
                case 238:
                    c = ')';
                    break;
                case 239:
                    c = '*';
                    break;
                case PsExtractor.VIDEO_STREAM_MASK /* 240 */:
                    c = '+';
                    break;
                case 241:
                    c = ',';
                    break;
                case 242:
                    c = '-';
                    break;
                case 243:
                    c = '.';
                    break;
                case 244:
                    c = IOUtils.DIR_SEPARATOR_UNIX;
                    break;
                case 245:
                    c = ':';
                    break;
                case 246:
                    c = ';';
                    break;
                case 247:
                    c = Typography.less;
                    break;
                case 248:
                    c = '=';
                    break;
                case 249:
                    c = Typography.greater;
                    break;
                case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
                    c = '?';
                    break;
                case 251:
                    c = '_';
                    break;
                case 252:
                    c = ' ';
                    break;
                default:
                    throw FormatException.getFormatInstance();
            }
            return new DecodedChar(r5 + 8, c);
        }
        return new DecodedChar(r5 + 7, (char) (extractNumericValueFromBitArray2 + 1));
    }

    private boolean isStillAlpha(int r6) {
        int extractNumericValueFromBitArray;
        if (r6 + 5 > this.information.getSize()) {
            return false;
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(r6, 5);
        if (extractNumericValueFromBitArray2 < 5 || extractNumericValueFromBitArray2 >= 16) {
            return r6 + 6 <= this.information.getSize() && (extractNumericValueFromBitArray = extractNumericValueFromBitArray(r6, 6)) >= 16 && extractNumericValueFromBitArray < 63;
        }
        return true;
    }

    private DecodedChar decodeAlphanumeric(int r4) {
        char c;
        int extractNumericValueFromBitArray = extractNumericValueFromBitArray(r4, 5);
        if (extractNumericValueFromBitArray == 15) {
            return new DecodedChar(r4 + 5, '$');
        }
        if (extractNumericValueFromBitArray >= 5 && extractNumericValueFromBitArray < 15) {
            return new DecodedChar(r4 + 5, (char) ((extractNumericValueFromBitArray + 48) - 5));
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(r4, 6);
        if (extractNumericValueFromBitArray2 >= 32 && extractNumericValueFromBitArray2 < 58) {
            return new DecodedChar(r4 + 6, (char) (extractNumericValueFromBitArray2 + 33));
        }
        switch (extractNumericValueFromBitArray2) {
            case 58:
                c = '*';
                break;
            case 59:
                c = ',';
                break;
            case 60:
                c = '-';
                break;
            case 61:
                c = '.';
                break;
            case 62:
                c = IOUtils.DIR_SEPARATOR_UNIX;
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: ".concat(String.valueOf(extractNumericValueFromBitArray2)));
        }
        return new DecodedChar(r4 + 6, c);
    }

    private boolean isAlphaTo646ToAlphaLatch(int r5) {
        int r1;
        if (r5 + 1 > this.information.getSize()) {
            return false;
        }
        for (int r0 = 0; r0 < 5 && (r1 = r0 + r5) < this.information.getSize(); r0++) {
            if (r0 == 2) {
                if (!this.information.get(r5 + 2)) {
                    return false;
                }
            } else if (this.information.get(r1)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlphaOr646ToNumericLatch(int r4) {
        int r0 = r4 + 3;
        if (r0 > this.information.getSize()) {
            return false;
        }
        while (r4 < r0) {
            if (this.information.get(r4)) {
                return false;
            }
            r4++;
        }
        return true;
    }

    private boolean isNumericToAlphaNumericLatch(int r5) {
        int r1;
        if (r5 + 1 > this.information.getSize()) {
            return false;
        }
        for (int r0 = 0; r0 < 4 && (r1 = r0 + r5) < this.information.getSize(); r0++) {
            if (this.information.get(r1)) {
                return false;
            }
        }
        return true;
    }
}
