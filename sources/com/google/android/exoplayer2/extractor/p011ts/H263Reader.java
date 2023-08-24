package com.google.android.exoplayer2.extractor.p011ts;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p011ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.Collections;

/* renamed from: com.google.android.exoplayer2.extractor.ts.H263Reader */
/* loaded from: classes2.dex */
public final class H263Reader implements ElementaryStreamReader {
    private static final float[] PIXEL_WIDTH_HEIGHT_RATIO_BY_ASPECT_RATIO_INFO = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 1.0f};
    private static final int START_CODE_VALUE_GROUP_OF_VOP = 179;
    private static final int START_CODE_VALUE_MAX_VIDEO_OBJECT = 31;
    private static final int START_CODE_VALUE_UNSET = -1;
    private static final int START_CODE_VALUE_USER_DATA = 178;
    private static final int START_CODE_VALUE_VISUAL_OBJECT = 181;
    private static final int START_CODE_VALUE_VISUAL_OBJECT_SEQUENCE = 176;
    private static final int START_CODE_VALUE_VOP = 182;
    private static final String TAG = "H263Reader";
    private static final int VIDEO_OBJECT_LAYER_SHAPE_RECTANGULAR = 0;
    private final CsdBuffer csdBuffer;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final boolean[] prefixFlags;
    private SampleReader sampleReader;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer userData;
    private final ParsableByteArray userDataParsable;
    private final UserDataReader userDataReader;

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public H263Reader() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public H263Reader(UserDataReader userDataReader) {
        this.userDataReader = userDataReader;
        this.prefixFlags = new boolean[4];
        this.csdBuffer = new CsdBuffer(128);
        this.pesTimeUs = C1856C.TIME_UNSET;
        if (userDataReader != null) {
            this.userData = new NalUnitTargetBuffer(START_CODE_VALUE_USER_DATA, 128);
            this.userDataParsable = new ParsableByteArray();
            return;
        }
        this.userData = null;
        this.userDataParsable = null;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        SampleReader sampleReader = this.sampleReader;
        if (sampleReader != null) {
            sampleReader.reset();
        }
        NalUnitTargetBuffer nalUnitTargetBuffer = this.userData;
        if (nalUnitTargetBuffer != null) {
            nalUnitTargetBuffer.reset();
        }
        this.totalBytesWritten = 0L;
        this.pesTimeUs = C1856C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.output = track;
        this.sampleReader = new SampleReader(track);
        UserDataReader userDataReader = this.userDataReader;
        if (userDataReader != null) {
            userDataReader.createTracks(extractorOutput, trackIdGenerator);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void packetStarted(long j, int r5) {
        if (j != C1856C.TIME_UNSET) {
            this.pesTimeUs = j;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.sampleReader);
        Assertions.checkStateNotNull(this.output);
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] data = parsableByteArray.getData();
        this.totalBytesWritten += parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int findNalUnit = NalUnitUtil.findNalUnit(data, position, limit, this.prefixFlags);
            if (findNalUnit == limit) {
                break;
            }
            int r5 = findNalUnit + 3;
            int r4 = parsableByteArray.getData()[r5] & 255;
            int r6 = findNalUnit - position;
            int r9 = 0;
            if (!this.hasOutputFormat) {
                if (r6 > 0) {
                    this.csdBuffer.onData(data, position, findNalUnit);
                }
                if (this.csdBuffer.onStartCode(r4, r6 < 0 ? -r6 : 0)) {
                    TrackOutput trackOutput = this.output;
                    CsdBuffer csdBuffer = this.csdBuffer;
                    trackOutput.format(parseCsdBuffer(csdBuffer, csdBuffer.volStartPosition, (String) Assertions.checkNotNull(this.formatId)));
                    this.hasOutputFormat = true;
                }
            }
            this.sampleReader.onData(data, position, findNalUnit);
            NalUnitTargetBuffer nalUnitTargetBuffer = this.userData;
            if (nalUnitTargetBuffer != null) {
                if (r6 > 0) {
                    nalUnitTargetBuffer.appendToNalUnit(data, position, findNalUnit);
                } else {
                    r9 = -r6;
                }
                if (this.userData.endNalUnit(r9)) {
                    ((ParsableByteArray) Util.castNonNull(this.userDataParsable)).reset(this.userData.nalData, NalUnitUtil.unescapeStream(this.userData.nalData, this.userData.nalLength));
                    ((UserDataReader) Util.castNonNull(this.userDataReader)).consume(this.pesTimeUs, this.userDataParsable);
                }
                if (r4 == START_CODE_VALUE_USER_DATA && parsableByteArray.getData()[findNalUnit + 2] == 1) {
                    this.userData.startNalUnit(r4);
                }
            }
            int r0 = limit - findNalUnit;
            this.sampleReader.onDataEnd(this.totalBytesWritten - r0, r0, this.hasOutputFormat);
            this.sampleReader.onStartCode(r4, this.pesTimeUs);
            position = r5;
        }
        if (!this.hasOutputFormat) {
            this.csdBuffer.onData(data, position, limit);
        }
        this.sampleReader.onData(data, position, limit);
        NalUnitTargetBuffer nalUnitTargetBuffer2 = this.userData;
        if (nalUnitTargetBuffer2 != null) {
            nalUnitTargetBuffer2.appendToNalUnit(data, position, limit);
        }
    }

    private static Format parseCsdBuffer(CsdBuffer csdBuffer, int r9, String str) {
        byte[] copyOf = Arrays.copyOf(csdBuffer.data, csdBuffer.length);
        ParsableBitArray parsableBitArray = new ParsableBitArray(copyOf);
        parsableBitArray.skipBytes(r9);
        parsableBitArray.skipBytes(4);
        parsableBitArray.skipBit();
        parsableBitArray.skipBits(8);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(4);
            parsableBitArray.skipBits(3);
        }
        int readBits = parsableBitArray.readBits(4);
        float f = 1.0f;
        if (readBits == 15) {
            int readBits2 = parsableBitArray.readBits(8);
            int readBits3 = parsableBitArray.readBits(8);
            if (readBits3 == 0) {
                Log.m1132w(TAG, "Invalid aspect ratio");
            } else {
                f = readBits2 / readBits3;
            }
        } else {
            float[] fArr = PIXEL_WIDTH_HEIGHT_RATIO_BY_ASPECT_RATIO_INFO;
            if (readBits < fArr.length) {
                f = fArr[readBits];
            } else {
                Log.m1132w(TAG, "Invalid aspect ratio");
            }
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(2);
            parsableBitArray.skipBits(1);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(3);
                parsableBitArray.skipBits(11);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
            }
        }
        if (parsableBitArray.readBits(2) != 0) {
            Log.m1132w(TAG, "Unhandled video object layer shape");
        }
        parsableBitArray.skipBit();
        int readBits4 = parsableBitArray.readBits(16);
        parsableBitArray.skipBit();
        if (parsableBitArray.readBit()) {
            if (readBits4 == 0) {
                Log.m1132w(TAG, "Invalid vop_increment_time_resolution");
            } else {
                int r1 = 0;
                for (int r92 = readBits4 - 1; r92 > 0; r92 >>= 1) {
                    r1++;
                }
                parsableBitArray.skipBits(r1);
            }
        }
        parsableBitArray.skipBit();
        int readBits5 = parsableBitArray.readBits(13);
        parsableBitArray.skipBit();
        int readBits6 = parsableBitArray.readBits(13);
        parsableBitArray.skipBit();
        parsableBitArray.skipBit();
        return new Format.Builder().setId(str).setSampleMimeType(MimeTypes.VIDEO_MP4V).setWidth(readBits5).setHeight(readBits6).setPixelWidthHeightRatio(f).setInitializationData(Collections.singletonList(copyOf)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.android.exoplayer2.extractor.ts.H263Reader$CsdBuffer */
    /* loaded from: classes2.dex */
    public static final class CsdBuffer {
        private static final byte[] START_CODE = {0, 0, 1};
        private static final int STATE_EXPECT_VIDEO_OBJECT_LAYER_START = 3;
        private static final int STATE_EXPECT_VIDEO_OBJECT_START = 2;
        private static final int STATE_EXPECT_VISUAL_OBJECT_START = 1;
        private static final int STATE_SKIP_TO_VISUAL_OBJECT_SEQUENCE_START = 0;
        private static final int STATE_WAIT_FOR_VOP_START = 4;
        public byte[] data;
        private boolean isFilling;
        public int length;
        private int state;
        public int volStartPosition;

        public CsdBuffer(int r1) {
            this.data = new byte[r1];
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.state = 0;
        }

        public boolean onStartCode(int r9, int r10) {
            int r0 = this.state;
            if (r0 != 0) {
                if (r0 != 1) {
                    if (r0 != 2) {
                        if (r0 != 3) {
                            if (r0 != 4) {
                                throw new IllegalStateException();
                            }
                            if (r9 == H263Reader.START_CODE_VALUE_GROUP_OF_VOP || r9 == H263Reader.START_CODE_VALUE_VISUAL_OBJECT) {
                                this.length -= r10;
                                this.isFilling = false;
                                return true;
                            }
                        } else if ((r9 & PsExtractor.VIDEO_STREAM_MASK) != 32) {
                            Log.m1132w(H263Reader.TAG, "Unexpected start code value");
                            reset();
                        } else {
                            this.volStartPosition = this.length;
                            this.state = 4;
                        }
                    } else if (r9 > 31) {
                        Log.m1132w(H263Reader.TAG, "Unexpected start code value");
                        reset();
                    } else {
                        this.state = 3;
                    }
                } else if (r9 != H263Reader.START_CODE_VALUE_VISUAL_OBJECT) {
                    Log.m1132w(H263Reader.TAG, "Unexpected start code value");
                    reset();
                } else {
                    this.state = 2;
                }
            } else if (r9 == H263Reader.START_CODE_VALUE_VISUAL_OBJECT_SEQUENCE) {
                this.state = 1;
                this.isFilling = true;
            }
            byte[] bArr = START_CODE;
            onData(bArr, 0, bArr.length);
            return false;
        }

        public void onData(byte[] bArr, int r6, int r7) {
            if (this.isFilling) {
                int r72 = r7 - r6;
                byte[] bArr2 = this.data;
                int length = bArr2.length;
                int r2 = this.length;
                if (length < r2 + r72) {
                    this.data = Arrays.copyOf(bArr2, (r2 + r72) * 2);
                }
                System.arraycopy(bArr, r6, this.data, this.length, r72);
                this.length += r72;
            }
        }
    }

    /* renamed from: com.google.android.exoplayer2.extractor.ts.H263Reader$SampleReader */
    /* loaded from: classes2.dex */
    private static final class SampleReader {
        private static final int OFFSET_VOP_CODING_TYPE = 1;
        private static final int VOP_CODING_TYPE_INTRA = 0;
        private boolean lookingForVopCodingType;
        private final TrackOutput output;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private int startCodeValue;
        private int vopBytesRead;

        public SampleReader(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        public void reset() {
            this.readingSample = false;
            this.lookingForVopCodingType = false;
            this.sampleIsKeyframe = false;
            this.startCodeValue = -1;
        }

        public void onStartCode(int r5, long j) {
            this.startCodeValue = r5;
            this.sampleIsKeyframe = false;
            this.readingSample = r5 == H263Reader.START_CODE_VALUE_VOP || r5 == H263Reader.START_CODE_VALUE_GROUP_OF_VOP;
            this.lookingForVopCodingType = r5 == H263Reader.START_CODE_VALUE_VOP;
            this.vopBytesRead = 0;
            this.sampleTimeUs = j;
        }

        public void onData(byte[] bArr, int r4, int r5) {
            if (this.lookingForVopCodingType) {
                int r1 = this.vopBytesRead;
                int r0 = (r4 + 1) - r1;
                if (r0 < r5) {
                    this.sampleIsKeyframe = ((bArr[r0] & 192) >> 6) == 0;
                    this.lookingForVopCodingType = false;
                    return;
                }
                this.vopBytesRead = r1 + (r5 - r4);
            }
        }

        public void onDataEnd(long j, int r10, boolean z) {
            if (this.startCodeValue == H263Reader.START_CODE_VALUE_VOP && z && this.readingSample) {
                long j2 = this.sampleTimeUs;
                if (j2 != C1856C.TIME_UNSET) {
                    this.output.sampleMetadata(j2, this.sampleIsKeyframe ? 1 : 0, (int) (j - this.samplePosition), r10, null);
                }
            }
            if (this.startCodeValue != H263Reader.START_CODE_VALUE_GROUP_OF_VOP) {
                this.samplePosition = j;
            }
        }
    }
}
