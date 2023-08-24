package com.google.android.exoplayer2.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.canhub.cropper.CropImage;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.math.Primes;

/* loaded from: classes2.dex */
public final class Cea608Decoder extends CeaDecoder {
    private static final int CC_FIELD_FLAG = 1;
    private static final byte CC_IMPLICIT_DATA_HEADER = -4;
    private static final int CC_MODE_PAINT_ON = 3;
    private static final int CC_MODE_POP_ON = 2;
    private static final int CC_MODE_ROLL_UP = 1;
    private static final int CC_MODE_UNKNOWN = 0;
    private static final int CC_TYPE_FLAG = 2;
    private static final int CC_VALID_FLAG = 4;
    private static final byte CTRL_BACKSPACE = 33;
    private static final byte CTRL_CARRIAGE_RETURN = 45;
    private static final byte CTRL_DELETE_TO_END_OF_ROW = 36;
    private static final byte CTRL_END_OF_CAPTION = 47;
    private static final byte CTRL_ERASE_DISPLAYED_MEMORY = 44;
    private static final byte CTRL_ERASE_NON_DISPLAYED_MEMORY = 46;
    private static final byte CTRL_RESUME_CAPTION_LOADING = 32;
    private static final byte CTRL_RESUME_DIRECT_CAPTIONING = 41;
    private static final byte CTRL_RESUME_TEXT_DISPLAY = 43;
    private static final byte CTRL_ROLL_UP_CAPTIONS_2_ROWS = 37;
    private static final byte CTRL_ROLL_UP_CAPTIONS_3_ROWS = 38;
    private static final byte CTRL_ROLL_UP_CAPTIONS_4_ROWS = 39;
    private static final byte CTRL_TEXT_RESTART = 42;
    private static final int DEFAULT_CAPTIONS_ROW_COUNT = 4;
    public static final long MIN_DATA_CHANNEL_TIMEOUT_MS = 16000;
    private static final int NTSC_CC_CHANNEL_1 = 0;
    private static final int NTSC_CC_CHANNEL_2 = 1;
    private static final int NTSC_CC_FIELD_1 = 0;
    private static final int NTSC_CC_FIELD_2 = 1;
    private static final int STYLE_ITALICS = 7;
    private static final int STYLE_UNCHANGED = 8;
    private static final String TAG = "Cea608Decoder";
    private int captionMode;
    private int captionRowCount;
    private List<Cue> cues;
    private boolean isCaptionValid;
    private boolean isInCaptionService;
    private long lastCueUpdateUs;
    private List<Cue> lastCues;
    private final int packetLength;
    private byte repeatableControlCc1;
    private byte repeatableControlCc2;
    private boolean repeatableControlSet;
    private final int selectedChannel;
    private final int selectedField;
    private final long validDataChannelTimeoutUs;
    private static final int[] ROW_INDICES = {11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] COLUMN_INDICES = {0, 4, 8, 12, 16, 20, 24, 28};
    private static final int[] STYLE_COLORS = {-1, -16711936, -16776961, -16711681, SupportMenu.CATEGORY_MASK, InputDeviceCompat.SOURCE_ANY, -65281};
    private static final int[] BASIC_CHARACTER_SET = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, JfifUtil.MARKER_APP1, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, 209, 241, 9632};
    private static final int[] SPECIAL_CHARACTER_SET = {174, 176, PsExtractor.PRIVATE_STREAM_1, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = {193, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE, Primes.SMALL_FACTOR_LIMIT, JfifUtil.MARKER_SOS, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, 192, 194, 199, 200, 202, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE, 235, 206, 207, 239, 212, JfifUtil.MARKER_EOI, 249, 219, 171, 187};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = {195, 227, 205, CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, JfifUtil.MARKER_SOI, 248, 9484, 9488, 9492, 9496};
    private static final boolean[] ODD_PARITY_BYTE_TABLE = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final ArrayList<CueBuilder> cueBuilders = new ArrayList<>();
    private CueBuilder currentCueBuilder = new CueBuilder(0, 4);
    private int currentChannel = 0;

    private static int getChannel(byte b) {
        return (b >> 3) & 1;
    }

    private static boolean isCtrlCode(byte b) {
        return (b & 224) == 0;
    }

    private static boolean isExtendedWestEuropeanChar(byte b, byte b2) {
        return (b & 246) == 18 && (b2 & 224) == 32;
    }

    private static boolean isMidrowCtrlCode(byte b, byte b2) {
        return (b & 247) == 17 && (b2 & 240) == 32;
    }

    private static boolean isMiscCode(byte b, byte b2) {
        return (b & 246) == 20 && (b2 & 240) == 32;
    }

    private static boolean isPreambleAddressCode(byte b, byte b2) {
        return (b & 240) == 16 && (b2 & 192) == 64;
    }

    private static boolean isRepeatable(byte b) {
        return (b & 240) == 16;
    }

    private static boolean isServiceSwitchCommand(byte b) {
        return (b & 247) == 20;
    }

    private static boolean isSpecialNorthAmericanChar(byte b, byte b2) {
        return (b & 247) == 17 && (b2 & 240) == 48;
    }

    private static boolean isTabCtrlCode(byte b, byte b2) {
        return (b & 247) == 23 && b2 >= 33 && b2 <= 35;
    }

    private static boolean isXdsControlCode(byte b) {
        return 1 <= b && b <= 15;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public String getName() {
        return TAG;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public void release() {
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.text.SubtitleDecoder
    public /* bridge */ /* synthetic */ void setPositionUs(long j) {
        super.setPositionUs(j);
    }

    public Cea608Decoder(String str, int r9, long j) {
        this.validDataChannelTimeoutUs = j > 0 ? j * 1000 : -9223372036854775807L;
        this.packetLength = MimeTypes.APPLICATION_MP4CEA608.equals(str) ? 2 : 3;
        if (r9 == 1) {
            this.selectedChannel = 0;
            this.selectedField = 0;
        } else if (r9 == 2) {
            this.selectedChannel = 1;
            this.selectedField = 0;
        } else if (r9 == 3) {
            this.selectedChannel = 0;
            this.selectedField = 1;
        } else if (r9 == 4) {
            this.selectedChannel = 1;
            this.selectedField = 1;
        } else {
            Log.m1132w(TAG, "Invalid channel. Defaulting to CC1.");
            this.selectedChannel = 0;
            this.selectedField = 0;
        }
        setCaptionMode(0);
        resetCueBuilders();
        this.isInCaptionService = true;
        this.lastCueUpdateUs = C1856C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        setCaptionMode(0);
        setCaptionRowCount(4);
        resetCueBuilders();
        this.isCaptionValid = false;
        this.repeatableControlSet = false;
        this.repeatableControlCc1 = (byte) 0;
        this.repeatableControlCc2 = (byte) 0;
        this.currentChannel = 0;
        this.isInCaptionService = true;
        this.lastCueUpdateUs = C1856C.TIME_UNSET;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        SubtitleOutputBuffer availableOutputBuffer;
        SubtitleOutputBuffer dequeueOutputBuffer = super.dequeueOutputBuffer();
        if (dequeueOutputBuffer != null) {
            return dequeueOutputBuffer;
        }
        if (!shouldClearStuckCaptions() || (availableOutputBuffer = getAvailableOutputBuffer()) == null) {
            return null;
        }
        this.cues = Collections.emptyList();
        this.lastCueUpdateUs = C1856C.TIME_UNSET;
        availableOutputBuffer.setContent(getPositionUs(), createSubtitle(), Long.MAX_VALUE);
        return availableOutputBuffer;
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

    /* JADX WARN: Removed duplicated region for block: B:76:0x006e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0018 A[SYNTHETIC] */
    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void decode(com.google.android.exoplayer2.text.SubtitleInputBuffer r10) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea608Decoder.decode(com.google.android.exoplayer2.text.SubtitleInputBuffer):void");
    }

    private boolean updateAndVerifyCurrentChannel(byte b) {
        if (isCtrlCode(b)) {
            this.currentChannel = getChannel(b);
        }
        return this.currentChannel == this.selectedChannel;
    }

    private boolean isRepeatedCommand(boolean z, byte b, byte b2) {
        if (z && isRepeatable(b)) {
            if (this.repeatableControlSet && this.repeatableControlCc1 == b && this.repeatableControlCc2 == b2) {
                this.repeatableControlSet = false;
                return true;
            }
            this.repeatableControlSet = true;
            this.repeatableControlCc1 = b;
            this.repeatableControlCc2 = b2;
        } else {
            this.repeatableControlSet = false;
        }
        return false;
    }

    private void handleMidrowCtrl(byte b) {
        this.currentCueBuilder.append(' ');
        this.currentCueBuilder.setStyle((b >> 1) & 7, (b & 1) == 1);
    }

    private void handlePreambleAddressCode(byte b, byte b2) {
        int r6 = ROW_INDICES[b & 7];
        if ((b2 & 32) != 0) {
            r6++;
        }
        if (r6 != this.currentCueBuilder.row) {
            if (this.captionMode != 1 && !this.currentCueBuilder.isEmpty()) {
                CueBuilder cueBuilder = new CueBuilder(this.captionMode, this.captionRowCount);
                this.currentCueBuilder = cueBuilder;
                this.cueBuilders.add(cueBuilder);
            }
            this.currentCueBuilder.row = r6;
        }
        boolean z = (b2 & 16) == 16;
        boolean z2 = (b2 & 1) == 1;
        int r7 = (b2 >> 1) & 7;
        this.currentCueBuilder.setStyle(z ? 8 : r7, z2);
        if (z) {
            this.currentCueBuilder.indent = COLUMN_INDICES[r7];
        }
    }

    private void handleMiscCode(byte b) {
        if (b == 32) {
            setCaptionMode(2);
        } else if (b != 41) {
            switch (b) {
                case 37:
                    setCaptionMode(1);
                    setCaptionRowCount(2);
                    return;
                case 38:
                    setCaptionMode(1);
                    setCaptionRowCount(3);
                    return;
                case 39:
                    setCaptionMode(1);
                    setCaptionRowCount(4);
                    return;
                default:
                    int r1 = this.captionMode;
                    if (r1 == 0) {
                        return;
                    }
                    if (b != 33) {
                        switch (b) {
                            case 44:
                                this.cues = Collections.emptyList();
                                int r5 = this.captionMode;
                                if (r5 == 1 || r5 == 3) {
                                    resetCueBuilders();
                                    return;
                                }
                                return;
                            case 45:
                                if (r1 != 1 || this.currentCueBuilder.isEmpty()) {
                                    return;
                                }
                                this.currentCueBuilder.rollUp();
                                return;
                            case 46:
                                resetCueBuilders();
                                return;
                            case 47:
                                this.cues = getDisplayCues();
                                resetCueBuilders();
                                return;
                            default:
                                return;
                        }
                    }
                    this.currentCueBuilder.backspace();
                    return;
            }
        } else {
            setCaptionMode(3);
        }
    }

    private List<Cue> getDisplayCues() {
        int size = this.cueBuilders.size();
        ArrayList arrayList = new ArrayList(size);
        int r3 = 2;
        for (int r4 = 0; r4 < size; r4++) {
            Cue build = this.cueBuilders.get(r4).build(Integer.MIN_VALUE);
            arrayList.add(build);
            if (build != null) {
                r3 = Math.min(r3, build.positionAnchor);
            }
        }
        ArrayList arrayList2 = new ArrayList(size);
        for (int r2 = 0; r2 < size; r2++) {
            Cue cue = (Cue) arrayList.get(r2);
            if (cue != null) {
                if (cue.positionAnchor != r3) {
                    cue = (Cue) Assertions.checkNotNull(this.cueBuilders.get(r2).build(r3));
                }
                arrayList2.add(cue);
            }
        }
        return arrayList2;
    }

    private void setCaptionMode(int r3) {
        int r0 = this.captionMode;
        if (r0 == r3) {
            return;
        }
        this.captionMode = r3;
        if (r3 == 3) {
            for (int r02 = 0; r02 < this.cueBuilders.size(); r02++) {
                this.cueBuilders.get(r02).setCaptionMode(r3);
            }
            return;
        }
        resetCueBuilders();
        if (r0 == 3 || r3 == 1 || r3 == 0) {
            this.cues = Collections.emptyList();
        }
    }

    private void setCaptionRowCount(int r2) {
        this.captionRowCount = r2;
        this.currentCueBuilder.setCaptionRowCount(r2);
    }

    private void resetCueBuilders() {
        this.currentCueBuilder.reset(this.captionMode);
        this.cueBuilders.clear();
        this.cueBuilders.add(this.currentCueBuilder);
    }

    private void maybeUpdateIsInCaptionService(byte b, byte b2) {
        if (isXdsControlCode(b)) {
            this.isInCaptionService = false;
        } else if (isServiceSwitchCommand(b)) {
            if (b2 != 32 && b2 != 47) {
                switch (b2) {
                    case 37:
                    case 38:
                    case 39:
                        break;
                    default:
                        switch (b2) {
                            case 41:
                                break;
                            case 42:
                            case 43:
                                this.isInCaptionService = false;
                                return;
                            default:
                                return;
                        }
                }
            }
            this.isInCaptionService = true;
        }
    }

    private static char getBasicChar(byte b) {
        return (char) BASIC_CHARACTER_SET[(b & Byte.MAX_VALUE) - 32];
    }

    private static char getSpecialNorthAmericanChar(byte b) {
        return (char) SPECIAL_CHARACTER_SET[b & Ascii.f1128SI];
    }

    private static char getExtendedWestEuropeanChar(byte b, byte b2) {
        if ((b & 1) == 0) {
            return getExtendedEsFrChar(b2);
        }
        return getExtendedPtDeChar(b2);
    }

    private static char getExtendedEsFrChar(byte b) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[b & Ascii.f1131US];
    }

    private static char getExtendedPtDeChar(byte b) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[b & Ascii.f1131US];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class CueBuilder {
        private static final int BASE_ROW = 15;
        private static final int SCREEN_CHARWIDTH = 32;
        private int captionMode;
        private int captionRowCount;
        private int indent;
        private int row;
        private int tabOffset;
        private final List<CueStyle> cueStyles = new ArrayList();
        private final List<SpannableString> rolledUpCaptions = new ArrayList();
        private final StringBuilder captionStringBuilder = new StringBuilder();

        public CueBuilder(int r2, int r3) {
            reset(r2);
            this.captionRowCount = r3;
        }

        public void reset(int r2) {
            this.captionMode = r2;
            this.cueStyles.clear();
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.setLength(0);
            this.row = 15;
            this.indent = 0;
            this.tabOffset = 0;
        }

        public boolean isEmpty() {
            return this.cueStyles.isEmpty() && this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0;
        }

        public void setCaptionMode(int r1) {
            this.captionMode = r1;
        }

        public void setCaptionRowCount(int r1) {
            this.captionRowCount = r1;
        }

        public void setStyle(int r4, boolean z) {
            this.cueStyles.add(new CueStyle(r4, z, this.captionStringBuilder.length()));
        }

        public void backspace() {
            CueStyle cueStyle;
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
                for (int size = this.cueStyles.size() - 1; size >= 0; size--) {
                    if (this.cueStyles.get(size).start != length) {
                        return;
                    }
                    cueStyle.start--;
                }
            }
        }

        public void append(char c) {
            if (this.captionStringBuilder.length() < 32) {
                this.captionStringBuilder.append(c);
            }
        }

        public void rollUp() {
            this.rolledUpCaptions.add(buildCurrentLine());
            this.captionStringBuilder.setLength(0);
            this.cueStyles.clear();
            int min = Math.min(this.captionRowCount, this.row);
            while (this.rolledUpCaptions.size() >= min) {
                this.rolledUpCaptions.remove(0);
            }
        }

        public Cue build(int r9) {
            float f;
            int r0 = this.indent + this.tabOffset;
            int r1 = 32 - r0;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int r4 = 0; r4 < this.rolledUpCaptions.size(); r4++) {
                spannableStringBuilder.append(Util.truncateAscii(this.rolledUpCaptions.get(r4), r1));
                spannableStringBuilder.append('\n');
            }
            spannableStringBuilder.append(Util.truncateAscii(buildCurrentLine(), r1));
            if (spannableStringBuilder.length() == 0) {
                return null;
            }
            int length = r1 - spannableStringBuilder.length();
            int r42 = r0 - length;
            if (r9 == Integer.MIN_VALUE) {
                if (this.captionMode != 2 || (Math.abs(r42) >= 3 && length >= 0)) {
                    r9 = (this.captionMode != 2 || r42 <= 0) ? 0 : 2;
                } else {
                    r9 = 1;
                }
            }
            if (r9 != 1) {
                if (r9 == 2) {
                    r0 = 32 - length;
                }
                f = ((r0 / 32.0f) * 0.8f) + 0.1f;
            } else {
                f = 0.5f;
            }
            int r12 = this.row;
            if (r12 > 7) {
                r12 = (r12 - 15) - 2;
            } else if (this.captionMode == 1) {
                r12 -= this.captionRowCount - 1;
            }
            return new Cue.Builder().setText(spannableStringBuilder).setTextAlignment(Layout.Alignment.ALIGN_NORMAL).setLine(r12, 1).setPosition(f).setPositionAnchor(r9).build();
        }

        private SpannableString buildCurrentLine() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            int r4 = 0;
            int r5 = -1;
            int r6 = -1;
            int r7 = 0;
            int r8 = -1;
            int r9 = -1;
            boolean z = false;
            while (r4 < this.cueStyles.size()) {
                CueStyle cueStyle = this.cueStyles.get(r4);
                boolean z2 = cueStyle.underline;
                int r13 = cueStyle.style;
                if (r13 != 8) {
                    boolean z3 = r13 == 7;
                    if (r13 != 7) {
                        r9 = Cea608Decoder.STYLE_COLORS[r13];
                    }
                    z = z3;
                }
                int r11 = cueStyle.start;
                r4++;
                if (r11 != (r4 < this.cueStyles.size() ? this.cueStyles.get(r4).start : length)) {
                    if (r5 != -1 && !z2) {
                        setUnderlineSpan(spannableStringBuilder, r5, r11);
                        r5 = -1;
                    } else if (r5 == -1 && z2) {
                        r5 = r11;
                    }
                    if (r6 != -1 && !z) {
                        setItalicSpan(spannableStringBuilder, r6, r11);
                        r6 = -1;
                    } else if (r6 == -1 && z) {
                        r6 = r11;
                    }
                    if (r9 != r8) {
                        setColorSpan(spannableStringBuilder, r7, r11, r8);
                        r8 = r9;
                        r7 = r11;
                    }
                }
            }
            if (r5 != -1 && r5 != length) {
                setUnderlineSpan(spannableStringBuilder, r5, length);
            }
            if (r6 != -1 && r6 != length) {
                setItalicSpan(spannableStringBuilder, r6, length);
            }
            if (r7 != length) {
                setColorSpan(spannableStringBuilder, r7, length, r8);
            }
            return new SpannableString(spannableStringBuilder);
        }

        private static void setUnderlineSpan(SpannableStringBuilder spannableStringBuilder, int r3, int r4) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), r3, r4, 33);
        }

        private static void setItalicSpan(SpannableStringBuilder spannableStringBuilder, int r3, int r4) {
            spannableStringBuilder.setSpan(new StyleSpan(2), r3, r4, 33);
        }

        private static void setColorSpan(SpannableStringBuilder spannableStringBuilder, int r2, int r3, int r4) {
            if (r4 == -1) {
                return;
            }
            spannableStringBuilder.setSpan(new ForegroundColorSpan(r4), r2, r3, 33);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class CueStyle {
            public int start;
            public final int style;
            public final boolean underline;

            public CueStyle(int r1, boolean z, int r3) {
                this.style = r1;
                this.underline = z;
                this.start = r3;
            }
        }
    }

    private boolean shouldClearStuckCaptions() {
        return (this.validDataChannelTimeoutUs == C1856C.TIME_UNSET || this.lastCueUpdateUs == C1856C.TIME_UNSET || getPositionUs() - this.lastCueUpdateUs < this.validDataChannelTimeoutUs) ? false : true;
    }
}
