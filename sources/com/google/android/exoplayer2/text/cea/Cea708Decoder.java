package com.google.android.exoplayer2.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.text.cea.Cea708Decoder;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.text.Typography;
import okio.Utf8;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes2.dex */
public final class Cea708Decoder extends CeaDecoder {
    private static final int CC_VALID_FLAG = 4;
    private static final int CHARACTER_BIG_CARONS = 42;
    private static final int CHARACTER_BIG_OE = 44;
    private static final int CHARACTER_BOLD_BULLET = 53;
    private static final int CHARACTER_CLOSE_DOUBLE_QUOTE = 52;
    private static final int CHARACTER_CLOSE_SINGLE_QUOTE = 50;
    private static final int CHARACTER_DIAERESIS_Y = 63;
    private static final int CHARACTER_ELLIPSIS = 37;
    private static final int CHARACTER_FIVE_EIGHTHS = 120;
    private static final int CHARACTER_HORIZONTAL_BORDER = 125;
    private static final int CHARACTER_LOWER_LEFT_BORDER = 124;
    private static final int CHARACTER_LOWER_RIGHT_BORDER = 126;
    private static final int CHARACTER_MN = 127;
    private static final int CHARACTER_NBTSP = 33;
    private static final int CHARACTER_ONE_EIGHTH = 118;
    private static final int CHARACTER_OPEN_DOUBLE_QUOTE = 51;
    private static final int CHARACTER_OPEN_SINGLE_QUOTE = 49;
    private static final int CHARACTER_SEVEN_EIGHTHS = 121;
    private static final int CHARACTER_SM = 61;
    private static final int CHARACTER_SMALL_CARONS = 58;
    private static final int CHARACTER_SMALL_OE = 60;
    private static final int CHARACTER_SOLID_BLOCK = 48;
    private static final int CHARACTER_THREE_EIGHTHS = 119;
    private static final int CHARACTER_TM = 57;
    private static final int CHARACTER_TSP = 32;
    private static final int CHARACTER_UPPER_LEFT_BORDER = 127;
    private static final int CHARACTER_UPPER_RIGHT_BORDER = 123;
    private static final int CHARACTER_VERTICAL_BORDER = 122;
    private static final int COMMAND_BS = 8;
    private static final int COMMAND_CLW = 136;
    private static final int COMMAND_CR = 13;
    private static final int COMMAND_CW0 = 128;
    private static final int COMMAND_CW1 = 129;
    private static final int COMMAND_CW2 = 130;
    private static final int COMMAND_CW3 = 131;
    private static final int COMMAND_CW4 = 132;
    private static final int COMMAND_CW5 = 133;
    private static final int COMMAND_CW6 = 134;
    private static final int COMMAND_CW7 = 135;
    private static final int COMMAND_DF0 = 152;
    private static final int COMMAND_DF1 = 153;
    private static final int COMMAND_DF2 = 154;
    private static final int COMMAND_DF3 = 155;
    private static final int COMMAND_DF4 = 156;
    private static final int COMMAND_DF5 = 157;
    private static final int COMMAND_DF6 = 158;
    private static final int COMMAND_DF7 = 159;
    private static final int COMMAND_DLC = 142;
    private static final int COMMAND_DLW = 140;
    private static final int COMMAND_DLY = 141;
    private static final int COMMAND_DSW = 137;
    private static final int COMMAND_ETX = 3;
    private static final int COMMAND_EXT1 = 16;
    private static final int COMMAND_EXT1_END = 23;
    private static final int COMMAND_EXT1_START = 17;
    private static final int COMMAND_FF = 12;
    private static final int COMMAND_HCR = 14;
    private static final int COMMAND_HDW = 138;
    private static final int COMMAND_NUL = 0;
    private static final int COMMAND_P16_END = 31;
    private static final int COMMAND_P16_START = 24;
    private static final int COMMAND_RST = 143;
    private static final int COMMAND_SPA = 144;
    private static final int COMMAND_SPC = 145;
    private static final int COMMAND_SPL = 146;
    private static final int COMMAND_SWA = 151;
    private static final int COMMAND_TGW = 139;
    private static final int DTVCC_PACKET_DATA = 2;
    private static final int DTVCC_PACKET_START = 3;
    private static final int GROUP_C0_END = 31;
    private static final int GROUP_C1_END = 159;
    private static final int GROUP_C2_END = 31;
    private static final int GROUP_C3_END = 159;
    private static final int GROUP_G0_END = 127;
    private static final int GROUP_G1_END = 255;
    private static final int GROUP_G2_END = 127;
    private static final int GROUP_G3_END = 255;
    private static final int NUM_WINDOWS = 8;
    private static final String TAG = "Cea708Decoder";
    private final CueInfoBuilder[] cueInfoBuilders;
    private List<Cue> cues;
    private CueInfoBuilder currentCueInfoBuilder;
    private DtvCcPacket currentDtvCcPacket;
    private int currentWindow;
    private final boolean isWideAspectRatio;
    private List<Cue> lastCues;
    private final int selectedServiceNumber;
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final ParsableBitArray captionChannelPacketData = new ParsableBitArray();
    private int previousSequenceNumber = -1;

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public String getName() {
        return TAG;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        return super.dequeueOutputBuffer();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.text.SubtitleDecoder
    public /* bridge */ /* synthetic */ void setPositionUs(long j) {
        super.setPositionUs(j);
    }

    public Cea708Decoder(int r4, List<byte[]> list) {
        boolean z = true;
        this.selectedServiceNumber = r4 == -1 ? 1 : r4;
        this.isWideAspectRatio = (list == null || !CodecSpecificDataUtil.parseCea708InitializationData(list)) ? false : false;
        this.cueInfoBuilders = new CueInfoBuilder[8];
        for (int r0 = 0; r0 < 8; r0++) {
            this.cueInfoBuilders[r0] = new CueInfoBuilder();
        }
        this.currentCueInfoBuilder = this.cueInfoBuilders[0];
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        this.currentWindow = 0;
        this.currentCueInfoBuilder = this.cueInfoBuilders[0];
        resetCueBuilders();
        this.currentDtvCcPacket = null;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    protected boolean isNewSubtitleDataAvailable() {
        return this.cues != this.lastCues;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    protected Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        return new CeaSubtitle((List) Assertions.checkNotNull(list));
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    protected void decode(SubtitleInputBuffer subtitleInputBuffer) {
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(subtitleInputBuffer.data);
        this.ccData.reset(byteBuffer.array(), byteBuffer.limit());
        while (this.ccData.bytesLeft() >= 3) {
            int readUnsignedByte = this.ccData.readUnsignedByte() & 7;
            int r1 = readUnsignedByte & 3;
            boolean z = (readUnsignedByte & 4) == 4;
            byte readUnsignedByte2 = (byte) this.ccData.readUnsignedByte();
            byte readUnsignedByte3 = (byte) this.ccData.readUnsignedByte();
            if (r1 == 2 || r1 == 3) {
                if (z) {
                    if (r1 == 3) {
                        finalizeCurrentPacket();
                        int r0 = (readUnsignedByte2 & 192) >> 6;
                        int r12 = this.previousSequenceNumber;
                        if (r12 != -1 && r0 != (r12 + 1) % 4) {
                            resetCueBuilders();
                            Log.m1132w(TAG, "Sequence number discontinuity. previous=" + this.previousSequenceNumber + " current=" + r0);
                        }
                        this.previousSequenceNumber = r0;
                        int r9 = readUnsignedByte2 & Utf8.REPLACEMENT_BYTE;
                        if (r9 == 0) {
                            r9 = 64;
                        }
                        DtvCcPacket dtvCcPacket = new DtvCcPacket(r0, r9);
                        this.currentDtvCcPacket = dtvCcPacket;
                        byte[] bArr = dtvCcPacket.packetData;
                        DtvCcPacket dtvCcPacket2 = this.currentDtvCcPacket;
                        int r13 = dtvCcPacket2.currentIndex;
                        dtvCcPacket2.currentIndex = r13 + 1;
                        bArr[r13] = readUnsignedByte3;
                    } else {
                        Assertions.checkArgument(r1 == 2);
                        DtvCcPacket dtvCcPacket3 = this.currentDtvCcPacket;
                        if (dtvCcPacket3 == null) {
                            Log.m1136e(TAG, "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                        } else {
                            byte[] bArr2 = dtvCcPacket3.packetData;
                            DtvCcPacket dtvCcPacket4 = this.currentDtvCcPacket;
                            int r14 = dtvCcPacket4.currentIndex;
                            dtvCcPacket4.currentIndex = r14 + 1;
                            bArr2[r14] = readUnsignedByte2;
                            byte[] bArr3 = this.currentDtvCcPacket.packetData;
                            DtvCcPacket dtvCcPacket5 = this.currentDtvCcPacket;
                            int r15 = dtvCcPacket5.currentIndex;
                            dtvCcPacket5.currentIndex = r15 + 1;
                            bArr3[r15] = readUnsignedByte3;
                        }
                    }
                    if (this.currentDtvCcPacket.currentIndex == (this.currentDtvCcPacket.packetSize * 2) - 1) {
                        finalizeCurrentPacket();
                    }
                }
            }
        }
    }

    private void finalizeCurrentPacket() {
        if (this.currentDtvCcPacket == null) {
            return;
        }
        processCurrentPacket();
        this.currentDtvCcPacket = null;
    }

    @RequiresNonNull({"currentDtvCcPacket"})
    private void processCurrentPacket() {
        if (this.currentDtvCcPacket.currentIndex != (this.currentDtvCcPacket.packetSize * 2) - 1) {
            Log.m1138d(TAG, "DtvCcPacket ended prematurely; size is " + ((this.currentDtvCcPacket.packetSize * 2) - 1) + ", but current index is " + this.currentDtvCcPacket.currentIndex + " (sequence number " + this.currentDtvCcPacket.sequenceNumber + ");");
        }
        boolean z = false;
        this.captionChannelPacketData.reset(this.currentDtvCcPacket.packetData, this.currentDtvCcPacket.currentIndex);
        while (true) {
            if (this.captionChannelPacketData.bitsLeft() <= 0) {
                break;
            }
            int readBits = this.captionChannelPacketData.readBits(3);
            int readBits2 = this.captionChannelPacketData.readBits(5);
            if (readBits == 7) {
                this.captionChannelPacketData.skipBits(2);
                readBits = this.captionChannelPacketData.readBits(6);
                if (readBits < 7) {
                    Log.m1132w(TAG, "Invalid extended service number: " + readBits);
                }
            }
            if (readBits2 == 0) {
                if (readBits != 0) {
                    Log.m1132w(TAG, "serviceNumber is non-zero (" + readBits + ") when blockSize is 0");
                }
            } else if (readBits != this.selectedServiceNumber) {
                this.captionChannelPacketData.skipBytes(readBits2);
            } else {
                int position = this.captionChannelPacketData.getPosition() + (readBits2 * 8);
                while (this.captionChannelPacketData.getPosition() < position) {
                    int readBits3 = this.captionChannelPacketData.readBits(8);
                    if (readBits3 == 16) {
                        int readBits4 = this.captionChannelPacketData.readBits(8);
                        if (readBits4 <= 31) {
                            handleC2Command(readBits4);
                        } else {
                            if (readBits4 <= 127) {
                                handleG2Character(readBits4);
                            } else if (readBits4 <= 159) {
                                handleC3Command(readBits4);
                            } else if (readBits4 <= 255) {
                                handleG3Character(readBits4);
                            } else {
                                Log.m1132w(TAG, "Invalid extended command: " + readBits4);
                            }
                            z = true;
                        }
                    } else if (readBits3 <= 31) {
                        handleC0Command(readBits3);
                    } else {
                        if (readBits3 <= 127) {
                            handleG0Character(readBits3);
                        } else if (readBits3 <= 159) {
                            handleC1Command(readBits3);
                        } else if (readBits3 <= 255) {
                            handleG1Character(readBits3);
                        } else {
                            Log.m1132w(TAG, "Invalid base command: " + readBits3);
                        }
                        z = true;
                    }
                }
            }
        }
        if (z) {
            this.cues = getDisplayCues();
        }
    }

    private void handleC0Command(int r5) {
        if (r5 != 0) {
            if (r5 == 3) {
                this.cues = getDisplayCues();
            } else if (r5 == 8) {
                this.currentCueInfoBuilder.backspace();
            } else {
                switch (r5) {
                    case 12:
                        resetCueBuilders();
                        return;
                    case 13:
                        this.currentCueInfoBuilder.append('\n');
                        return;
                    case 14:
                        return;
                    default:
                        if (r5 >= 17 && r5 <= 23) {
                            Log.m1132w(TAG, "Currently unsupported COMMAND_EXT1 Command: " + r5);
                            this.captionChannelPacketData.skipBits(8);
                            return;
                        } else if (r5 >= 24 && r5 <= 31) {
                            Log.m1132w(TAG, "Currently unsupported COMMAND_P16 Command: " + r5);
                            this.captionChannelPacketData.skipBits(16);
                            return;
                        } else {
                            Log.m1132w(TAG, "Invalid C0 command: " + r5);
                            return;
                        }
                }
            }
        }
    }

    private void handleC1Command(int r5) {
        CueInfoBuilder cueInfoBuilder;
        int r2 = 1;
        switch (r5) {
            case 128:
            case 129:
            case 130:
            case COMMAND_CW3 /* 131 */:
            case COMMAND_CW4 /* 132 */:
            case COMMAND_CW5 /* 133 */:
            case 134:
            case 135:
                int r52 = r5 - 128;
                if (this.currentWindow != r52) {
                    this.currentWindow = r52;
                    this.currentCueInfoBuilder = this.cueInfoBuilders[r52];
                    return;
                }
                return;
            case COMMAND_CLW /* 136 */:
                while (r2 <= 8) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - r2].clear();
                    }
                    r2++;
                }
                return;
            case COMMAND_DSW /* 137 */:
                for (int r53 = 1; r53 <= 8; r53++) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - r53].setVisibility(true);
                    }
                }
                return;
            case 138:
                while (r2 <= 8) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - r2].setVisibility(false);
                    }
                    r2++;
                }
                return;
            case COMMAND_TGW /* 139 */:
                for (int r54 = 1; r54 <= 8; r54++) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - r54].setVisibility(!cueInfoBuilder.isVisible());
                    }
                }
                return;
            case COMMAND_DLW /* 140 */:
                while (r2 <= 8) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - r2].reset();
                    }
                    r2++;
                }
                return;
            case COMMAND_DLY /* 141 */:
                this.captionChannelPacketData.skipBits(8);
                return;
            case COMMAND_DLC /* 142 */:
                return;
            case COMMAND_RST /* 143 */:
                resetCueBuilders();
                return;
            case COMMAND_SPA /* 144 */:
                if (!this.currentCueInfoBuilder.isDefined()) {
                    this.captionChannelPacketData.skipBits(16);
                    return;
                } else {
                    handleSetPenAttributes();
                    return;
                }
            case COMMAND_SPC /* 145 */:
                if (!this.currentCueInfoBuilder.isDefined()) {
                    this.captionChannelPacketData.skipBits(24);
                    return;
                } else {
                    handleSetPenColor();
                    return;
                }
            case COMMAND_SPL /* 146 */:
                if (!this.currentCueInfoBuilder.isDefined()) {
                    this.captionChannelPacketData.skipBits(16);
                    return;
                } else {
                    handleSetPenLocation();
                    return;
                }
            case 147:
            case 148:
            case 149:
            case 150:
            default:
                Log.m1132w(TAG, "Invalid C1 command: " + r5);
                return;
            case COMMAND_SWA /* 151 */:
                if (!this.currentCueInfoBuilder.isDefined()) {
                    this.captionChannelPacketData.skipBits(32);
                    return;
                } else {
                    handleSetWindowAttributes();
                    return;
                }
            case COMMAND_DF0 /* 152 */:
            case COMMAND_DF1 /* 153 */:
            case COMMAND_DF2 /* 154 */:
            case COMMAND_DF3 /* 155 */:
            case COMMAND_DF4 /* 156 */:
            case COMMAND_DF5 /* 157 */:
            case COMMAND_DF6 /* 158 */:
            case 159:
                int r55 = r5 - 152;
                handleDefineWindow(r55);
                if (this.currentWindow != r55) {
                    this.currentWindow = r55;
                    this.currentCueInfoBuilder = this.cueInfoBuilders[r55];
                    return;
                }
                return;
        }
    }

    private void handleC2Command(int r2) {
        if (r2 <= 7) {
            return;
        }
        if (r2 <= 15) {
            this.captionChannelPacketData.skipBits(8);
        } else if (r2 <= 23) {
            this.captionChannelPacketData.skipBits(16);
        } else if (r2 <= 31) {
            this.captionChannelPacketData.skipBits(24);
        }
    }

    private void handleC3Command(int r2) {
        if (r2 <= 135) {
            this.captionChannelPacketData.skipBits(32);
        } else if (r2 <= COMMAND_RST) {
            this.captionChannelPacketData.skipBits(40);
        } else if (r2 <= 159) {
            this.captionChannelPacketData.skipBits(2);
            this.captionChannelPacketData.skipBits(this.captionChannelPacketData.readBits(6) * 8);
        }
    }

    private void handleG0Character(int r2) {
        if (r2 == 127) {
            this.currentCueInfoBuilder.append((char) 9835);
        } else {
            this.currentCueInfoBuilder.append((char) (r2 & 255));
        }
    }

    private void handleG1Character(int r2) {
        this.currentCueInfoBuilder.append((char) (r2 & 255));
    }

    private void handleG2Character(int r3) {
        if (r3 == 32) {
            this.currentCueInfoBuilder.append(' ');
        } else if (r3 == 33) {
            this.currentCueInfoBuilder.append(Typography.nbsp);
        } else if (r3 == 37) {
            this.currentCueInfoBuilder.append(Typography.ellipsis);
        } else if (r3 == 42) {
            this.currentCueInfoBuilder.append((char) 352);
        } else if (r3 == 44) {
            this.currentCueInfoBuilder.append((char) 338);
        } else if (r3 == 63) {
            this.currentCueInfoBuilder.append((char) 376);
        } else if (r3 == 57) {
            this.currentCueInfoBuilder.append(Typography.f1537tm);
        } else if (r3 == 58) {
            this.currentCueInfoBuilder.append((char) 353);
        } else if (r3 == 60) {
            this.currentCueInfoBuilder.append((char) 339);
        } else if (r3 != 61) {
            switch (r3) {
                case 48:
                    this.currentCueInfoBuilder.append((char) 9608);
                    return;
                case 49:
                    this.currentCueInfoBuilder.append(Typography.leftSingleQuote);
                    return;
                case 50:
                    this.currentCueInfoBuilder.append(Typography.rightSingleQuote);
                    return;
                case 51:
                    this.currentCueInfoBuilder.append(Typography.leftDoubleQuote);
                    return;
                case 52:
                    this.currentCueInfoBuilder.append(Typography.rightDoubleQuote);
                    return;
                case 53:
                    this.currentCueInfoBuilder.append(Typography.bullet);
                    return;
                default:
                    switch (r3) {
                        case 118:
                            this.currentCueInfoBuilder.append((char) 8539);
                            return;
                        case 119:
                            this.currentCueInfoBuilder.append((char) 8540);
                            return;
                        case 120:
                            this.currentCueInfoBuilder.append((char) 8541);
                            return;
                        case 121:
                            this.currentCueInfoBuilder.append((char) 8542);
                            return;
                        case 122:
                            this.currentCueInfoBuilder.append((char) 9474);
                            return;
                        case 123:
                            this.currentCueInfoBuilder.append((char) 9488);
                            return;
                        case 124:
                            this.currentCueInfoBuilder.append((char) 9492);
                            return;
                        case 125:
                            this.currentCueInfoBuilder.append((char) 9472);
                            return;
                        case 126:
                            this.currentCueInfoBuilder.append((char) 9496);
                            return;
                        case 127:
                            this.currentCueInfoBuilder.append((char) 9484);
                            return;
                        default:
                            Log.m1132w(TAG, "Invalid G2 character: " + r3);
                            return;
                    }
            }
        } else {
            this.currentCueInfoBuilder.append((char) 8480);
        }
    }

    private void handleG3Character(int r3) {
        if (r3 == 160) {
            this.currentCueInfoBuilder.append((char) 13252);
            return;
        }
        Log.m1132w(TAG, "Invalid G3 character: " + r3);
        this.currentCueInfoBuilder.append('_');
    }

    private void handleSetPenAttributes() {
        this.currentCueInfoBuilder.setPenAttributes(this.captionChannelPacketData.readBits(4), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBit(), this.captionChannelPacketData.readBit(), this.captionChannelPacketData.readBits(3), this.captionChannelPacketData.readBits(3));
    }

    private void handleSetPenColor() {
        int argbColorFromCeaColor = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        int argbColorFromCeaColor2 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        this.captionChannelPacketData.skipBits(2);
        this.currentCueInfoBuilder.setPenColor(argbColorFromCeaColor, argbColorFromCeaColor2, CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2)));
    }

    private void handleSetPenLocation() {
        this.captionChannelPacketData.skipBits(4);
        int readBits = this.captionChannelPacketData.readBits(4);
        this.captionChannelPacketData.skipBits(2);
        this.currentCueInfoBuilder.setPenLocation(readBits, this.captionChannelPacketData.readBits(6));
    }

    private void handleSetWindowAttributes() {
        int argbColorFromCeaColor = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        int readBits = this.captionChannelPacketData.readBits(2);
        int argbColorFromCeaColor2 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        if (this.captionChannelPacketData.readBit()) {
            readBits |= 4;
        }
        boolean readBit = this.captionChannelPacketData.readBit();
        int readBits2 = this.captionChannelPacketData.readBits(2);
        int readBits3 = this.captionChannelPacketData.readBits(2);
        int readBits4 = this.captionChannelPacketData.readBits(2);
        this.captionChannelPacketData.skipBits(8);
        this.currentCueInfoBuilder.setWindowAttributes(argbColorFromCeaColor, argbColorFromCeaColor2, readBit, readBits, readBits2, readBits3, readBits4);
    }

    private void handleDefineWindow(int r15) {
        CueInfoBuilder cueInfoBuilder = this.cueInfoBuilders[r15];
        this.captionChannelPacketData.skipBits(2);
        boolean readBit = this.captionChannelPacketData.readBit();
        boolean readBit2 = this.captionChannelPacketData.readBit();
        boolean readBit3 = this.captionChannelPacketData.readBit();
        int readBits = this.captionChannelPacketData.readBits(3);
        boolean readBit4 = this.captionChannelPacketData.readBit();
        int readBits2 = this.captionChannelPacketData.readBits(7);
        int readBits3 = this.captionChannelPacketData.readBits(8);
        int readBits4 = this.captionChannelPacketData.readBits(4);
        int readBits5 = this.captionChannelPacketData.readBits(4);
        this.captionChannelPacketData.skipBits(2);
        int readBits6 = this.captionChannelPacketData.readBits(6);
        this.captionChannelPacketData.skipBits(2);
        cueInfoBuilder.defineWindow(readBit, readBit2, readBit3, readBits, readBit4, readBits2, readBits3, readBits5, readBits6, readBits4, this.captionChannelPacketData.readBits(3), this.captionChannelPacketData.readBits(3));
    }

    private List<Cue> getDisplayCues() {
        Cea708CueInfo build;
        ArrayList arrayList = new ArrayList();
        for (int r2 = 0; r2 < 8; r2++) {
            if (!this.cueInfoBuilders[r2].isEmpty() && this.cueInfoBuilders[r2].isVisible() && (build = this.cueInfoBuilders[r2].build()) != null) {
                arrayList.add(build);
            }
        }
        Collections.sort(arrayList, Cea708CueInfo.LEAST_IMPORTANT_FIRST);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (int r1 = 0; r1 < arrayList.size(); r1++) {
            arrayList2.add(((Cea708CueInfo) arrayList.get(r1)).cue);
        }
        return Collections.unmodifiableList(arrayList2);
    }

    private void resetCueBuilders() {
        for (int r0 = 0; r0 < 8; r0++) {
            this.cueInfoBuilders[r0].reset();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class DtvCcPacket {
        int currentIndex = 0;
        public final byte[] packetData;
        public final int packetSize;
        public final int sequenceNumber;

        public DtvCcPacket(int r1, int r2) {
            this.sequenceNumber = r1;
            this.packetSize = r2;
            this.packetData = new byte[(r2 * 2) - 1];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class CueInfoBuilder {
        private static final int BORDER_AND_EDGE_TYPE_NONE = 0;
        private static final int BORDER_AND_EDGE_TYPE_UNIFORM = 3;
        public static final int COLOR_SOLID_BLACK;
        public static final int COLOR_SOLID_WHITE = getArgbColorFromCeaColor(2, 2, 2, 0);
        public static final int COLOR_TRANSPARENT;
        private static final int DEFAULT_PRIORITY = 4;
        private static final int DIRECTION_BOTTOM_TO_TOP = 3;
        private static final int DIRECTION_LEFT_TO_RIGHT = 0;
        private static final int DIRECTION_RIGHT_TO_LEFT = 1;
        private static final int DIRECTION_TOP_TO_BOTTOM = 2;
        private static final int HORIZONTAL_SIZE = 209;
        private static final int JUSTIFICATION_CENTER = 2;
        private static final int JUSTIFICATION_FULL = 3;
        private static final int JUSTIFICATION_LEFT = 0;
        private static final int JUSTIFICATION_RIGHT = 1;
        private static final int MAXIMUM_ROW_COUNT = 15;
        private static final int PEN_FONT_STYLE_DEFAULT = 0;
        private static final int PEN_FONT_STYLE_MONOSPACED_WITHOUT_SERIFS = 3;
        private static final int PEN_FONT_STYLE_MONOSPACED_WITH_SERIFS = 1;
        private static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITHOUT_SERIFS = 4;
        private static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITH_SERIFS = 2;
        private static final int PEN_OFFSET_NORMAL = 1;
        private static final int PEN_SIZE_STANDARD = 1;
        private static final int[] PEN_STYLE_BACKGROUND;
        private static final int[] PEN_STYLE_EDGE_TYPE;
        private static final int[] PEN_STYLE_FONT_STYLE;
        private static final int RELATIVE_CUE_SIZE = 99;
        private static final int VERTICAL_SIZE = 74;
        private static final int[] WINDOW_STYLE_FILL;
        private static final int[] WINDOW_STYLE_JUSTIFICATION;
        private static final int[] WINDOW_STYLE_PRINT_DIRECTION;
        private static final int[] WINDOW_STYLE_SCROLL_DIRECTION;
        private static final boolean[] WINDOW_STYLE_WORD_WRAP;
        private int anchorId;
        private int backgroundColor;
        private int backgroundColorStartPosition;
        private boolean defined;
        private int foregroundColor;
        private int foregroundColorStartPosition;
        private int horizontalAnchor;
        private int italicsStartPosition;
        private int justification;
        private int penStyleId;
        private int priority;
        private boolean relativePositioning;
        private int row;
        private int rowCount;
        private boolean rowLock;
        private int underlineStartPosition;
        private int verticalAnchor;
        private boolean visible;
        private int windowFillColor;
        private int windowStyleId;
        private final List<SpannableString> rolledUpCaptions = new ArrayList();
        private final SpannableStringBuilder captionStringBuilder = new SpannableStringBuilder();

        static {
            int argbColorFromCeaColor = getArgbColorFromCeaColor(0, 0, 0, 0);
            COLOR_SOLID_BLACK = argbColorFromCeaColor;
            int argbColorFromCeaColor2 = getArgbColorFromCeaColor(0, 0, 0, 3);
            COLOR_TRANSPARENT = argbColorFromCeaColor2;
            WINDOW_STYLE_JUSTIFICATION = new int[]{0, 0, 0, 0, 0, 2, 0};
            WINDOW_STYLE_PRINT_DIRECTION = new int[]{0, 0, 0, 0, 0, 0, 2};
            WINDOW_STYLE_SCROLL_DIRECTION = new int[]{3, 3, 3, 3, 3, 3, 1};
            WINDOW_STYLE_WORD_WRAP = new boolean[]{false, false, false, true, true, true, false};
            WINDOW_STYLE_FILL = new int[]{argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor};
            PEN_STYLE_FONT_STYLE = new int[]{0, 1, 2, 3, 4, 3, 4};
            PEN_STYLE_EDGE_TYPE = new int[]{0, 0, 0, 0, 0, 3, 3};
            PEN_STYLE_BACKGROUND = new int[]{argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor2};
        }

        public CueInfoBuilder() {
            reset();
        }

        public boolean isEmpty() {
            return !isDefined() || (this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0);
        }

        public void reset() {
            clear();
            this.defined = false;
            this.visible = false;
            this.priority = 4;
            this.relativePositioning = false;
            this.verticalAnchor = 0;
            this.horizontalAnchor = 0;
            this.anchorId = 0;
            this.rowCount = 15;
            this.rowLock = true;
            this.justification = 0;
            this.windowStyleId = 0;
            this.penStyleId = 0;
            int r0 = COLOR_SOLID_BLACK;
            this.windowFillColor = r0;
            this.foregroundColor = COLOR_SOLID_WHITE;
            this.backgroundColor = r0;
        }

        public void clear() {
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.clear();
            this.italicsStartPosition = -1;
            this.underlineStartPosition = -1;
            this.foregroundColorStartPosition = -1;
            this.backgroundColorStartPosition = -1;
            this.row = 0;
        }

        public boolean isDefined() {
            return this.defined;
        }

        public void setVisibility(boolean z) {
            this.visible = z;
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void defineWindow(boolean z, boolean z2, boolean z3, int r15, boolean z4, int r17, int r18, int r19, int r20, int r21, int r22, int r23) {
            this.defined = true;
            this.visible = z;
            this.rowLock = z2;
            this.priority = r15;
            this.relativePositioning = z4;
            this.verticalAnchor = r17;
            this.horizontalAnchor = r18;
            this.anchorId = r21;
            int r6 = r19 + 1;
            if (this.rowCount != r6) {
                this.rowCount = r6;
                while (true) {
                    if ((!z2 || this.rolledUpCaptions.size() < this.rowCount) && this.rolledUpCaptions.size() < 15) {
                        break;
                    }
                    this.rolledUpCaptions.remove(0);
                }
            }
            if (r22 != 0 && this.windowStyleId != r22) {
                this.windowStyleId = r22;
                int r1 = r22 - 1;
                setWindowAttributes(WINDOW_STYLE_FILL[r1], COLOR_TRANSPARENT, WINDOW_STYLE_WORD_WRAP[r1], 0, WINDOW_STYLE_PRINT_DIRECTION[r1], WINDOW_STYLE_SCROLL_DIRECTION[r1], WINDOW_STYLE_JUSTIFICATION[r1]);
            }
            if (r23 == 0 || this.penStyleId == r23) {
                return;
            }
            this.penStyleId = r23;
            int r12 = r23 - 1;
            setPenAttributes(0, 1, 1, false, false, PEN_STYLE_EDGE_TYPE[r12], PEN_STYLE_FONT_STYLE[r12]);
            setPenColor(COLOR_SOLID_WHITE, PEN_STYLE_BACKGROUND[r12], COLOR_SOLID_BLACK);
        }

        public void setWindowAttributes(int r1, int r2, boolean z, int r4, int r5, int r6, int r7) {
            this.windowFillColor = r1;
            this.justification = r7;
        }

        public void setPenAttributes(int r1, int r2, int r3, boolean z, boolean z2, int r6, int r7) {
            if (this.italicsStartPosition != -1) {
                if (!z) {
                    this.captionStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, this.captionStringBuilder.length(), 33);
                    this.italicsStartPosition = -1;
                }
            } else if (z) {
                this.italicsStartPosition = this.captionStringBuilder.length();
            }
            if (this.underlineStartPosition == -1) {
                if (z2) {
                    this.underlineStartPosition = this.captionStringBuilder.length();
                }
            } else if (z2) {
            } else {
                this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, this.captionStringBuilder.length(), 33);
                this.underlineStartPosition = -1;
            }
        }

        public void setPenColor(int r6, int r7, int r8) {
            if (this.foregroundColorStartPosition != -1 && this.foregroundColor != r6) {
                this.captionStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (r6 != COLOR_SOLID_WHITE) {
                this.foregroundColorStartPosition = this.captionStringBuilder.length();
                this.foregroundColor = r6;
            }
            if (this.backgroundColorStartPosition != -1 && this.backgroundColor != r7) {
                this.captionStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (r7 != COLOR_SOLID_BLACK) {
                this.backgroundColorStartPosition = this.captionStringBuilder.length();
                this.backgroundColor = r7;
            }
        }

        public void setPenLocation(int r1, int r2) {
            if (this.row != r1) {
                append('\n');
            }
            this.row = r1;
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
            }
        }

        public void append(char c) {
            if (c == '\n') {
                this.rolledUpCaptions.add(buildSpannableString());
                this.captionStringBuilder.clear();
                if (this.italicsStartPosition != -1) {
                    this.italicsStartPosition = 0;
                }
                if (this.underlineStartPosition != -1) {
                    this.underlineStartPosition = 0;
                }
                if (this.foregroundColorStartPosition != -1) {
                    this.foregroundColorStartPosition = 0;
                }
                if (this.backgroundColorStartPosition != -1) {
                    this.backgroundColorStartPosition = 0;
                }
                while (true) {
                    if ((!this.rowLock || this.rolledUpCaptions.size() < this.rowCount) && this.rolledUpCaptions.size() < 15) {
                        return;
                    }
                    this.rolledUpCaptions.remove(0);
                }
            } else {
                this.captionStringBuilder.append(c);
            }
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.italicsStartPosition != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, length, 33);
                }
                if (this.underlineStartPosition != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, length, 33);
                }
                if (this.foregroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, length, 33);
                }
                if (this.backgroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0065  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0070  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0091  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0093  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00a0  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00ac  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.android.exoplayer2.text.cea.Cea708Decoder.Cea708CueInfo build() {
            /*
                Method dump skipped, instructions count: 197
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.build():com.google.android.exoplayer2.text.cea.Cea708Decoder$Cea708CueInfo");
        }

        public static int getArgbColorFromCeaColor(int r1, int r2, int r3) {
            return getArgbColorFromCeaColor(r1, r2, r3, 0);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x002b  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x002e  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0031  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int getArgbColorFromCeaColor(int r4, int r5, int r6, int r7) {
            /*
                r0 = 0
                r1 = 4
                com.google.android.exoplayer2.util.Assertions.checkIndex(r4, r0, r1)
                com.google.android.exoplayer2.util.Assertions.checkIndex(r5, r0, r1)
                com.google.android.exoplayer2.util.Assertions.checkIndex(r6, r0, r1)
                com.google.android.exoplayer2.util.Assertions.checkIndex(r7, r0, r1)
                r1 = 1
                r2 = 255(0xff, float:3.57E-43)
                if (r7 == 0) goto L21
                if (r7 == r1) goto L21
                r3 = 2
                if (r7 == r3) goto L1e
                r3 = 3
                if (r7 == r3) goto L1c
                goto L21
            L1c:
                r7 = 0
                goto L23
            L1e:
                r7 = 127(0x7f, float:1.78E-43)
                goto L23
            L21:
                r7 = 255(0xff, float:3.57E-43)
            L23:
                if (r4 <= r1) goto L28
                r4 = 255(0xff, float:3.57E-43)
                goto L29
            L28:
                r4 = 0
            L29:
                if (r5 <= r1) goto L2e
                r5 = 255(0xff, float:3.57E-43)
                goto L2f
            L2e:
                r5 = 0
            L2f:
                if (r6 <= r1) goto L33
                r0 = 255(0xff, float:3.57E-43)
            L33:
                int r4 = android.graphics.Color.argb(r7, r4, r5, r0)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.getArgbColorFromCeaColor(int, int, int, int):int");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Cea708CueInfo {
        private static final Comparator<Cea708CueInfo> LEAST_IMPORTANT_FIRST = new Comparator() { // from class: com.google.android.exoplayer2.text.cea.Cea708Decoder$Cea708CueInfo$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Integer.compare(((Cea708Decoder.Cea708CueInfo) obj2).priority, ((Cea708Decoder.Cea708CueInfo) obj).priority);
                return compare;
            }
        };
        public final Cue cue;
        public final int priority;

        public Cea708CueInfo(CharSequence charSequence, Layout.Alignment alignment, float f, int r5, int r6, float f2, int r8, float f3, boolean z, int r11, int r12) {
            Cue.Builder size = new Cue.Builder().setText(charSequence).setTextAlignment(alignment).setLine(f, r5).setLineAnchor(r6).setPosition(f2).setPositionAnchor(r8).setSize(f3);
            if (z) {
                size.setWindowColor(r11);
            }
            this.cue = size.build();
            this.priority = r12;
        }
    }
}
