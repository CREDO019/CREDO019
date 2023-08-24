package com.google.android.exoplayer2.extractor.mkv;

import android.net.Uri;
import android.util.Pair;
import android.util.SparseArray;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.TrueHdSampleRechunker;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.android.exoplayer2.video.ColorInfo;
import com.google.android.exoplayer2.video.DolbyVisionConfig;
import com.google.android.exoplayer2.video.HevcConfig;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.lang3.CharUtils;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes2.dex */
public class MatroskaExtractor implements Extractor {
    private static final int BLOCK_ADDITIONAL_ID_VP9_ITU_T_35 = 4;
    private static final int BLOCK_ADD_ID_TYPE_DVCC = 1685480259;
    private static final int BLOCK_ADD_ID_TYPE_DVVC = 1685485123;
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_AV1 = "V_AV1";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_FLOAT = "A_PCM/FLOAT/IEEE";
    private static final String CODEC_ID_PCM_INT_BIG = "A_PCM/INT/BIG";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String CODEC_ID_VTT = "S_TEXT/WEBVTT";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    private static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    private static final int FOURCC_COMPRESSION_H263 = 859189832;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_ADDITIONAL = 165;
    private static final int ID_BLOCK_ADDITIONS = 30113;
    private static final int ID_BLOCK_ADDITION_MAPPING = 16868;
    private static final int ID_BLOCK_ADD_ID = 238;
    private static final int ID_BLOCK_ADD_ID_EXTRA_DATA = 16877;
    private static final int ID_BLOCK_ADD_ID_TYPE = 16871;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_BLOCK_MORE = 166;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISCARD_PADDING = 30114;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_BLOCK_ADDITION_ID = 21998;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_NAME = 21358;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_POSE_PITCH = 30324;
    private static final int ID_PROJECTION_POSE_ROLL = 30325;
    private static final int ID_PROJECTION_POSE_YAW = 30323;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_PROJECTION_TYPE = 30321;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    private static final Map<String, Integer> TRACK_NAME_TO_ROTATION_DEGREES;
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int VTT_PREFIX_END_TIMECODE_OFFSET = 25;
    private static final String VTT_TIMECODE_FORMAT = "%02d:%02d:%02d.%03d";
    private static final long VTT_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    private int blockAdditionalId;
    private long blockDurationUs;
    private int blockFlags;
    private long blockGroupDiscardPaddingNs;
    private boolean blockHasReferenceBlock;
    private int blockSampleCount;
    private int blockSampleIndex;
    private int[] blockSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private boolean haveOutputSample;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private final ParsableByteArray supplementalData;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$$ExternalSyntheticLambda0
        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public final Extractor[] createExtractors() {
            return MatroskaExtractor.lambda$static$0();
        }

        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
            Extractor[] createExtractors;
            createExtractors = createExtractors();
            return createExtractors;
        }
    };
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, MultipartStream.DASH, MultipartStream.DASH, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final byte[] VTT_PREFIX = {87, 69, 66, 86, 84, 84, 10, 10, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 32, MultipartStream.DASH, MultipartStream.DASH, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 10};
    private static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Flags {
    }

    protected int getElementType(int r1) {
        switch (r1) {
            case ID_TRACK_TYPE /* 131 */:
            case ID_FLAG_DEFAULT /* 136 */:
            case ID_BLOCK_DURATION /* 155 */:
            case ID_CHANNELS /* 159 */:
            case ID_PIXEL_WIDTH /* 176 */:
            case ID_CUE_TIME /* 179 */:
            case ID_PIXEL_HEIGHT /* 186 */:
            case 215:
            case ID_TIME_CODE /* 231 */:
            case ID_BLOCK_ADD_ID /* 238 */:
            case ID_CUE_CLUSTER_POSITION /* 241 */:
            case ID_REFERENCE_BLOCK /* 251 */:
            case ID_BLOCK_ADD_ID_TYPE /* 16871 */:
            case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
            case ID_DOC_TYPE_READ_VERSION /* 17029 */:
            case ID_EBML_READ_VERSION /* 17143 */:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
            case ID_CONTENT_ENCODING_ORDER /* 20529 */:
            case ID_CONTENT_ENCODING_SCOPE /* 20530 */:
            case ID_SEEK_POSITION /* 21420 */:
            case ID_STEREO_MODE /* 21432 */:
            case ID_DISPLAY_WIDTH /* 21680 */:
            case ID_DISPLAY_UNIT /* 21682 */:
            case ID_DISPLAY_HEIGHT /* 21690 */:
            case ID_FLAG_FORCED /* 21930 */:
            case ID_COLOUR_RANGE /* 21945 */:
            case ID_COLOUR_TRANSFER /* 21946 */:
            case ID_COLOUR_PRIMARIES /* 21947 */:
            case ID_MAX_CLL /* 21948 */:
            case ID_MAX_FALL /* 21949 */:
            case ID_MAX_BLOCK_ADDITION_ID /* 21998 */:
            case ID_CODEC_DELAY /* 22186 */:
            case ID_SEEK_PRE_ROLL /* 22203 */:
            case ID_AUDIO_BIT_DEPTH /* 25188 */:
            case ID_DISCARD_PADDING /* 30114 */:
            case ID_PROJECTION_TYPE /* 30321 */:
            case ID_DEFAULT_DURATION /* 2352003 */:
            case ID_TIMECODE_SCALE /* 2807729 */:
                return 2;
            case 134:
            case 17026:
            case ID_NAME /* 21358 */:
            case ID_LANGUAGE /* 2274716 */:
                return 3;
            case ID_BLOCK_GROUP /* 160 */:
            case ID_BLOCK_MORE /* 166 */:
            case ID_TRACK_ENTRY /* 174 */:
            case ID_CUE_TRACK_POSITIONS /* 183 */:
            case ID_CUE_POINT /* 187 */:
            case 224:
            case 225:
            case ID_BLOCK_ADDITION_MAPPING /* 16868 */:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /* 18407 */:
            case ID_SEEK /* 19899 */:
            case ID_CONTENT_COMPRESSION /* 20532 */:
            case ID_CONTENT_ENCRYPTION /* 20533 */:
            case ID_COLOUR /* 21936 */:
            case ID_MASTERING_METADATA /* 21968 */:
            case ID_CONTENT_ENCODING /* 25152 */:
            case ID_CONTENT_ENCODINGS /* 28032 */:
            case ID_BLOCK_ADDITIONS /* 30113 */:
            case ID_PROJECTION /* 30320 */:
            case ID_SEEK_HEAD /* 290298740 */:
            case 357149030:
            case ID_TRACKS /* 374648427 */:
            case ID_SEGMENT /* 408125543 */:
            case ID_EBML /* 440786851 */:
            case ID_CUES /* 475249515 */:
            case ID_CLUSTER /* 524531317 */:
                return 1;
            case ID_BLOCK /* 161 */:
            case ID_SIMPLE_BLOCK /* 163 */:
            case ID_BLOCK_ADDITIONAL /* 165 */:
            case ID_BLOCK_ADD_ID_EXTRA_DATA /* 16877 */:
            case ID_CONTENT_COMPRESSION_SETTINGS /* 16981 */:
            case ID_CONTENT_ENCRYPTION_KEY_ID /* 18402 */:
            case ID_SEEK_ID /* 21419 */:
            case ID_CODEC_PRIVATE /* 25506 */:
            case ID_PROJECTION_PRIVATE /* 30322 */:
                return 4;
            case ID_SAMPLING_FREQUENCY /* 181 */:
            case ID_DURATION /* 17545 */:
            case ID_PRIMARY_R_CHROMATICITY_X /* 21969 */:
            case ID_PRIMARY_R_CHROMATICITY_Y /* 21970 */:
            case ID_PRIMARY_G_CHROMATICITY_X /* 21971 */:
            case ID_PRIMARY_G_CHROMATICITY_Y /* 21972 */:
            case ID_PRIMARY_B_CHROMATICITY_X /* 21973 */:
            case ID_PRIMARY_B_CHROMATICITY_Y /* 21974 */:
            case ID_WHITE_POINT_CHROMATICITY_X /* 21975 */:
            case ID_WHITE_POINT_CHROMATICITY_Y /* 21976 */:
            case ID_LUMNINANCE_MAX /* 21977 */:
            case ID_LUMNINANCE_MIN /* 21978 */:
            case ID_PROJECTION_POSE_YAW /* 30323 */:
            case ID_PROJECTION_POSE_PITCH /* 30324 */:
            case ID_PROJECTION_POSE_ROLL /* 30325 */:
                return 5;
            default:
                return 0;
        }
    }

    protected boolean isLevel1Element(int r2) {
        return r2 == 357149030 || r2 == ID_CLUSTER || r2 == ID_CUES || r2 == ID_TRACKS;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public final void release() {
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("htc_video_rotA-000", 0);
        hashMap.put("htc_video_rotA-090", 90);
        hashMap.put("htc_video_rotA-180", Integer.valueOf((int) RotationOptions.ROTATE_180));
        hashMap.put("htc_video_rotA-270", 270);
        TRACK_NAME_TO_ROTATION_DEGREES = Collections.unmodifiableMap(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new MatroskaExtractor()};
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int r2) {
        this(new DefaultEbmlReader(), r2);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int r6) {
        this.segmentContentPosition = -1L;
        this.timecodeScale = C1856C.TIME_UNSET;
        this.durationTimecode = C1856C.TIME_UNSET;
        this.durationUs = C1856C.TIME_UNSET;
        this.cuesContentPosition = -1L;
        this.seekPositionAfterBuildingCues = -1L;
        this.clusterTimecodeUs = C1856C.TIME_UNSET;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor());
        this.seekForCuesEnabled = (r6 & 1) == 0;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
        this.supplementalData = new ParsableByteArray();
        this.blockSampleSizes = new int[1];
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public final boolean sniff(ExtractorInput extractorInput) throws IOException {
        return new Sniffer().sniff(extractorInput);
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public final void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.clusterTimecodeUs = C1856C.TIME_UNSET;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetWriteSampleData();
        for (int r1 = 0; r1 < this.tracks.size(); r1++) {
            this.tracks.valueAt(r1).reset();
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        this.haveOutputSample = false;
        boolean z = true;
        while (z && !this.haveOutputSample) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int r0 = 0; r0 < this.tracks.size(); r0++) {
            Track valueAt = this.tracks.valueAt(r0);
            valueAt.assertOutputInitialized();
            valueAt.outputPendingSampleMetadata();
        }
        return -1;
    }

    protected void startMasterElement(int r6, long j, long j2) throws ParserException {
        assertInitialized();
        if (r6 == ID_BLOCK_GROUP) {
            this.blockHasReferenceBlock = false;
            this.blockGroupDiscardPaddingNs = 0L;
        } else if (r6 == ID_TRACK_ENTRY) {
            this.currentTrack = new Track();
        } else if (r6 == ID_CUE_POINT) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (r6 == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1L;
        } else if (r6 == ID_CONTENT_ENCRYPTION) {
            getCurrentTrack(r6).hasContentEncryption = true;
        } else if (r6 == ID_MASTERING_METADATA) {
            getCurrentTrack(r6).hasColorInfo = true;
        } else if (r6 == ID_SEGMENT) {
            long j3 = this.segmentContentPosition;
            if (j3 != -1 && j3 != j) {
                throw ParserException.createForMalformedContainer("Multiple Segment elements not supported", null);
            }
            this.segmentContentPosition = j;
            this.segmentContentSize = j2;
        } else if (r6 == ID_CUES) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (r6 == ID_CLUSTER && !this.sentSeekMap) {
            if (this.seekForCuesEnabled && this.cuesContentPosition != -1) {
                this.seekForCues = true;
                return;
            }
            this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
            this.sentSeekMap = true;
        }
    }

    protected void endMasterElement(int r11) throws ParserException {
        assertInitialized();
        if (r11 == ID_BLOCK_GROUP) {
            if (this.blockState != 2) {
                return;
            }
            Track track = this.tracks.get(this.blockTrackNumber);
            track.assertOutputInitialized();
            if (this.blockGroupDiscardPaddingNs > 0 && CODEC_ID_OPUS.equals(track.codecId)) {
                this.supplementalData.reset(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.blockGroupDiscardPaddingNs).array());
            }
            int r2 = 0;
            for (int r0 = 0; r0 < this.blockSampleCount; r0++) {
                r2 += this.blockSampleSizes[r0];
            }
            int r02 = 0;
            while (r02 < this.blockSampleCount) {
                long j = this.blockTimeUs + ((track.defaultSampleDurationNs * r02) / 1000);
                int r3 = this.blockFlags;
                if (r02 == 0 && !this.blockHasReferenceBlock) {
                    r3 |= 1;
                }
                int r7 = this.blockSampleSizes[r02];
                int r9 = r2 - r7;
                commitSampleToOutput(track, j, r3, r7, r9);
                r02++;
                r2 = r9;
            }
            this.blockState = 0;
        } else if (r11 == ID_TRACK_ENTRY) {
            Track track2 = (Track) Assertions.checkStateNotNull(this.currentTrack);
            if (track2.codecId == null) {
                throw ParserException.createForMalformedContainer("CodecId is missing in TrackEntry element", null);
            }
            if (isCodecSupported(track2.codecId)) {
                track2.initializeOutput(this.extractorOutput, track2.number);
                this.tracks.put(track2.number, track2);
            }
            this.currentTrack = null;
        } else if (r11 == ID_SEEK) {
            int r112 = this.seekEntryId;
            if (r112 != -1) {
                long j2 = this.seekEntryPosition;
                if (j2 != -1) {
                    if (r112 == ID_CUES) {
                        this.cuesContentPosition = j2;
                        return;
                    }
                    return;
                }
            }
            throw ParserException.createForMalformedContainer("Mandatory element SeekID or SeekPosition not found", null);
        } else if (r11 == ID_CONTENT_ENCODING) {
            assertInTrackEntry(r11);
            if (this.currentTrack.hasContentEncryption) {
                if (this.currentTrack.cryptoData == null) {
                    throw ParserException.createForMalformedContainer("Encrypted Track found but ContentEncKeyID was not found", null);
                }
                this.currentTrack.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C1856C.UUID_NIL, MimeTypes.VIDEO_WEBM, this.currentTrack.cryptoData.encryptionKey));
            }
        } else if (r11 == ID_CONTENT_ENCODINGS) {
            assertInTrackEntry(r11);
            if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                throw ParserException.createForMalformedContainer("Combining encryption and compression is not supported", null);
            }
        } else if (r11 == 357149030) {
            if (this.timecodeScale == C1856C.TIME_UNSET) {
                this.timecodeScale = 1000000L;
            }
            long j3 = this.durationTimecode;
            if (j3 != C1856C.TIME_UNSET) {
                this.durationUs = scaleTimecodeToUs(j3);
            }
        } else if (r11 == ID_TRACKS) {
            if (this.tracks.size() == 0) {
                throw ParserException.createForMalformedContainer("No valid tracks were found", null);
            }
            this.extractorOutput.endTracks();
        } else if (r11 == ID_CUES) {
            if (!this.sentSeekMap) {
                this.extractorOutput.seekMap(buildSeekMap(this.cueTimesUs, this.cueClusterPositions));
                this.sentSeekMap = true;
            }
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
        }
    }

    protected void integerElement(int r9, long j) throws ParserException {
        if (r9 == ID_CONTENT_ENCODING_ORDER) {
            if (j == 0) {
                return;
            }
            throw ParserException.createForMalformedContainer("ContentEncodingOrder " + j + " not supported", null);
        } else if (r9 == ID_CONTENT_ENCODING_SCOPE) {
            if (j == 1) {
                return;
            }
            throw ParserException.createForMalformedContainer("ContentEncodingScope " + j + " not supported", null);
        } else {
            switch (r9) {
                case ID_TRACK_TYPE /* 131 */:
                    getCurrentTrack(r9).type = (int) j;
                    return;
                case ID_FLAG_DEFAULT /* 136 */:
                    getCurrentTrack(r9).flagDefault = j == 1;
                    return;
                case ID_BLOCK_DURATION /* 155 */:
                    this.blockDurationUs = scaleTimecodeToUs(j);
                    return;
                case ID_CHANNELS /* 159 */:
                    getCurrentTrack(r9).channelCount = (int) j;
                    return;
                case ID_PIXEL_WIDTH /* 176 */:
                    getCurrentTrack(r9).width = (int) j;
                    return;
                case ID_CUE_TIME /* 179 */:
                    assertInCues(r9);
                    this.cueTimesUs.add(scaleTimecodeToUs(j));
                    return;
                case ID_PIXEL_HEIGHT /* 186 */:
                    getCurrentTrack(r9).height = (int) j;
                    return;
                case 215:
                    getCurrentTrack(r9).number = (int) j;
                    return;
                case ID_TIME_CODE /* 231 */:
                    this.clusterTimecodeUs = scaleTimecodeToUs(j);
                    return;
                case ID_BLOCK_ADD_ID /* 238 */:
                    this.blockAdditionalId = (int) j;
                    return;
                case ID_CUE_CLUSTER_POSITION /* 241 */:
                    if (this.seenClusterPositionForCurrentCuePoint) {
                        return;
                    }
                    assertInCues(r9);
                    this.cueClusterPositions.add(j);
                    this.seenClusterPositionForCurrentCuePoint = true;
                    return;
                case ID_REFERENCE_BLOCK /* 251 */:
                    this.blockHasReferenceBlock = true;
                    return;
                case ID_BLOCK_ADD_ID_TYPE /* 16871 */:
                    getCurrentTrack(r9).blockAddIdType = (int) j;
                    return;
                case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
                    if (j == 3) {
                        return;
                    }
                    throw ParserException.createForMalformedContainer("ContentCompAlgo " + j + " not supported", null);
                case ID_DOC_TYPE_READ_VERSION /* 17029 */:
                    if (j < 1 || j > 2) {
                        throw ParserException.createForMalformedContainer("DocTypeReadVersion " + j + " not supported", null);
                    }
                    return;
                case ID_EBML_READ_VERSION /* 17143 */:
                    if (j == 1) {
                        return;
                    }
                    throw ParserException.createForMalformedContainer("EBMLReadVersion " + j + " not supported", null);
                case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
                    if (j == 5) {
                        return;
                    }
                    throw ParserException.createForMalformedContainer("ContentEncAlgo " + j + " not supported", null);
                case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
                    if (j == 1) {
                        return;
                    }
                    throw ParserException.createForMalformedContainer("AESSettingsCipherMode " + j + " not supported", null);
                case ID_SEEK_POSITION /* 21420 */:
                    this.seekEntryPosition = j + this.segmentContentPosition;
                    return;
                case ID_STEREO_MODE /* 21432 */:
                    int r11 = (int) j;
                    assertInTrackEntry(r9);
                    if (r11 == 0) {
                        this.currentTrack.stereoMode = 0;
                        return;
                    } else if (r11 == 1) {
                        this.currentTrack.stereoMode = 2;
                        return;
                    } else if (r11 == 3) {
                        this.currentTrack.stereoMode = 1;
                        return;
                    } else if (r11 != 15) {
                        return;
                    } else {
                        this.currentTrack.stereoMode = 3;
                        return;
                    }
                case ID_DISPLAY_WIDTH /* 21680 */:
                    getCurrentTrack(r9).displayWidth = (int) j;
                    return;
                case ID_DISPLAY_UNIT /* 21682 */:
                    getCurrentTrack(r9).displayUnit = (int) j;
                    return;
                case ID_DISPLAY_HEIGHT /* 21690 */:
                    getCurrentTrack(r9).displayHeight = (int) j;
                    return;
                case ID_FLAG_FORCED /* 21930 */:
                    getCurrentTrack(r9).flagForced = j == 1;
                    return;
                case ID_MAX_BLOCK_ADDITION_ID /* 21998 */:
                    getCurrentTrack(r9).maxBlockAdditionId = (int) j;
                    return;
                case ID_CODEC_DELAY /* 22186 */:
                    getCurrentTrack(r9).codecDelayNs = j;
                    return;
                case ID_SEEK_PRE_ROLL /* 22203 */:
                    getCurrentTrack(r9).seekPreRollNs = j;
                    return;
                case ID_AUDIO_BIT_DEPTH /* 25188 */:
                    getCurrentTrack(r9).audioBitDepth = (int) j;
                    return;
                case ID_DISCARD_PADDING /* 30114 */:
                    this.blockGroupDiscardPaddingNs = j;
                    return;
                case ID_PROJECTION_TYPE /* 30321 */:
                    assertInTrackEntry(r9);
                    int r92 = (int) j;
                    if (r92 == 0) {
                        this.currentTrack.projectionType = 0;
                        return;
                    } else if (r92 == 1) {
                        this.currentTrack.projectionType = 1;
                        return;
                    } else if (r92 == 2) {
                        this.currentTrack.projectionType = 2;
                        return;
                    } else if (r92 != 3) {
                        return;
                    } else {
                        this.currentTrack.projectionType = 3;
                        return;
                    }
                case ID_DEFAULT_DURATION /* 2352003 */:
                    getCurrentTrack(r9).defaultSampleDurationNs = (int) j;
                    return;
                case ID_TIMECODE_SCALE /* 2807729 */:
                    this.timecodeScale = j;
                    return;
                default:
                    switch (r9) {
                        case ID_COLOUR_RANGE /* 21945 */:
                            assertInTrackEntry(r9);
                            int r93 = (int) j;
                            if (r93 == 1) {
                                this.currentTrack.colorRange = 2;
                                return;
                            } else if (r93 != 2) {
                                return;
                            } else {
                                this.currentTrack.colorRange = 1;
                                return;
                            }
                        case ID_COLOUR_TRANSFER /* 21946 */:
                            assertInTrackEntry(r9);
                            int isoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer((int) j);
                            if (isoTransferCharacteristicsToColorTransfer != -1) {
                                this.currentTrack.colorTransfer = isoTransferCharacteristicsToColorTransfer;
                                return;
                            }
                            return;
                        case ID_COLOUR_PRIMARIES /* 21947 */:
                            assertInTrackEntry(r9);
                            this.currentTrack.hasColorInfo = true;
                            int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace((int) j);
                            if (isoColorPrimariesToColorSpace != -1) {
                                this.currentTrack.colorSpace = isoColorPrimariesToColorSpace;
                                return;
                            }
                            return;
                        case ID_MAX_CLL /* 21948 */:
                            getCurrentTrack(r9).maxContentLuminance = (int) j;
                            return;
                        case ID_MAX_FALL /* 21949 */:
                            getCurrentTrack(r9).maxFrameAverageLuminance = (int) j;
                            return;
                        default:
                            return;
                    }
            }
        }
    }

    protected void floatElement(int r2, double d) throws ParserException {
        if (r2 == ID_SAMPLING_FREQUENCY) {
            getCurrentTrack(r2).sampleRate = (int) d;
        } else if (r2 == ID_DURATION) {
            this.durationTimecode = (long) d;
        } else {
            switch (r2) {
                case ID_PRIMARY_R_CHROMATICITY_X /* 21969 */:
                    getCurrentTrack(r2).primaryRChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_R_CHROMATICITY_Y /* 21970 */:
                    getCurrentTrack(r2).primaryRChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_X /* 21971 */:
                    getCurrentTrack(r2).primaryGChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_Y /* 21972 */:
                    getCurrentTrack(r2).primaryGChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_X /* 21973 */:
                    getCurrentTrack(r2).primaryBChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_Y /* 21974 */:
                    getCurrentTrack(r2).primaryBChromaticityY = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_X /* 21975 */:
                    getCurrentTrack(r2).whitePointChromaticityX = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_Y /* 21976 */:
                    getCurrentTrack(r2).whitePointChromaticityY = (float) d;
                    return;
                case ID_LUMNINANCE_MAX /* 21977 */:
                    getCurrentTrack(r2).maxMasteringLuminance = (float) d;
                    return;
                case ID_LUMNINANCE_MIN /* 21978 */:
                    getCurrentTrack(r2).minMasteringLuminance = (float) d;
                    return;
                default:
                    switch (r2) {
                        case ID_PROJECTION_POSE_YAW /* 30323 */:
                            getCurrentTrack(r2).projectionPoseYaw = (float) d;
                            return;
                        case ID_PROJECTION_POSE_PITCH /* 30324 */:
                            getCurrentTrack(r2).projectionPosePitch = (float) d;
                            return;
                        case ID_PROJECTION_POSE_ROLL /* 30325 */:
                            getCurrentTrack(r2).projectionPoseRoll = (float) d;
                            return;
                        default:
                            return;
                    }
            }
        }
    }

    protected void stringElement(int r2, String str) throws ParserException {
        if (r2 == 134) {
            getCurrentTrack(r2).codecId = str;
        } else if (r2 != 17026) {
            if (r2 == ID_NAME) {
                getCurrentTrack(r2).name = str;
            } else if (r2 != ID_LANGUAGE) {
            } else {
                getCurrentTrack(r2).language = str;
            }
        } else if (DOC_TYPE_WEBM.equals(str) || DOC_TYPE_MATROSKA.equals(str)) {
        } else {
            throw ParserException.createForMalformedContainer("DocType " + str + " not supported", null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x024d, code lost:
        throw com.google.android.exoplayer2.ParserException.createForMalformedContainer("EBML lacing sample size out of range.", null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void binaryElement(int r22, int r23, com.google.android.exoplayer2.extractor.ExtractorInput r24) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 778
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.binaryElement(int, int, com.google.android.exoplayer2.extractor.ExtractorInput):void");
    }

    protected void handleBlockAddIDExtraData(Track track, ExtractorInput extractorInput, int r5) throws IOException {
        if (track.blockAddIdType == 1685485123 || track.blockAddIdType == 1685480259) {
            track.dolbyVisionConfigBytes = new byte[r5];
            extractorInput.readFully(track.dolbyVisionConfigBytes, 0, r5);
            return;
        }
        extractorInput.skipFully(r5);
    }

    protected void handleBlockAdditionalData(Track track, int r3, ExtractorInput extractorInput, int r5) throws IOException {
        if (r3 == 4 && CODEC_ID_VP9.equals(track.codecId)) {
            this.supplementalData.reset(r5);
            extractorInput.readFully(this.supplementalData.getData(), 0, r5);
            return;
        }
        extractorInput.skipFully(r5);
    }

    @EnsuresNonNull({"currentTrack"})
    private void assertInTrackEntry(int r3) throws ParserException {
        if (this.currentTrack != null) {
            return;
        }
        throw ParserException.createForMalformedContainer("Element " + r3 + " must be in a TrackEntry", null);
    }

    @EnsuresNonNull({"cueTimesUs", "cueClusterPositions"})
    private void assertInCues(int r3) throws ParserException {
        if (this.cueTimesUs == null || this.cueClusterPositions == null) {
            throw ParserException.createForMalformedContainer("Element " + r3 + " must be in a Cues", null);
        }
    }

    protected Track getCurrentTrack(int r1) throws ParserException {
        assertInTrackEntry(r1);
        return this.currentTrack;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x009d  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"#1.output"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void commitSampleToOutput(com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track r13, long r14, int r16, int r17, int r18) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            com.google.android.exoplayer2.extractor.TrueHdSampleRechunker r2 = r1.trueHdSampleRechunker
            r3 = 1
            if (r2 == 0) goto L19
            com.google.android.exoplayer2.extractor.TrueHdSampleRechunker r4 = r1.trueHdSampleRechunker
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r1.output
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r11 = r1.cryptoData
            r6 = r14
            r8 = r16
            r9 = r17
            r10 = r18
            r4.sampleMetadata(r5, r6, r8, r9, r10, r11)
            goto Lc4
        L19:
            java.lang.String r2 = r1.codecId
            java.lang.String r4 = "S_TEXT/UTF8"
            boolean r2 = r4.equals(r2)
            if (r2 != 0) goto L37
            java.lang.String r2 = r1.codecId
            java.lang.String r4 = "S_TEXT/ASS"
            boolean r2 = r4.equals(r2)
            if (r2 != 0) goto L37
            java.lang.String r2 = r1.codecId
            java.lang.String r4 = "S_TEXT/WEBVTT"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L53
        L37:
            int r2 = r0.blockSampleCount
            java.lang.String r4 = "MatroskaExtractor"
            if (r2 <= r3) goto L43
            java.lang.String r2 = "Skipping subtitle sample in laced block."
            com.google.android.exoplayer2.util.Log.m1132w(r4, r2)
            goto L53
        L43:
            long r5 = r0.blockDurationUs
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 != 0) goto L56
            java.lang.String r2 = "Skipping subtitle sample with no duration."
            com.google.android.exoplayer2.util.Log.m1132w(r4, r2)
        L53:
            r2 = r17
            goto L97
        L56:
            java.lang.String r2 = r1.codecId
            long r4 = r0.blockDurationUs
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r0.subtitleSample
            byte[] r6 = r6.getData()
            setSubtitleEndTime(r2, r4, r6)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.subtitleSample
            int r2 = r2.getPosition()
        L69:
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.subtitleSample
            int r4 = r4.limit()
            if (r2 >= r4) goto L84
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.subtitleSample
            byte[] r4 = r4.getData()
            r4 = r4[r2]
            if (r4 != 0) goto L81
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.subtitleSample
            r4.setLimit(r2)
            goto L84
        L81:
            int r2 = r2 + 1
            goto L69
        L84:
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r1.output
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.subtitleSample
            int r5 = r4.limit()
            r2.sampleData(r4, r5)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.subtitleSample
            int r2 = r2.limit()
            int r2 = r17 + r2
        L97:
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            r4 = r16 & r4
            if (r4 == 0) goto Lb7
            int r4 = r0.blockSampleCount
            if (r4 <= r3) goto La8
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.supplementalData
            r5 = 0
            r4.reset(r5)
            goto Lb7
        La8:
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.supplementalData
            int r4 = r4.limit()
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r1.output
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r0.supplementalData
            r7 = 2
            r5.sampleData(r6, r4, r7)
            int r2 = r2 + r4
        Lb7:
            r9 = r2
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r1.output
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r11 = r1.cryptoData
            r6 = r14
            r8 = r16
            r10 = r18
            r5.sampleMetadata(r6, r8, r9, r10, r11)
        Lc4:
            r0.haveOutputSample = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.commitSampleToOutput(com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track, long, int, int, int):void");
    }

    private void readScratch(ExtractorInput extractorInput, int r5) throws IOException {
        if (this.scratch.limit() >= r5) {
            return;
        }
        if (this.scratch.capacity() < r5) {
            ParsableByteArray parsableByteArray = this.scratch;
            parsableByteArray.ensureCapacity(Math.max(parsableByteArray.capacity() * 2, r5));
        }
        extractorInput.readFully(this.scratch.getData(), this.scratch.limit(), r5 - this.scratch.limit());
        this.scratch.setLimit(r5);
    }

    @RequiresNonNull({"#2.output"})
    private int writeSampleData(ExtractorInput extractorInput, Track track, int r13, boolean z) throws IOException {
        int r8;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, r13);
            return finishWriteSampleData();
        } else if (CODEC_ID_ASS.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, r13);
            return finishWriteSampleData();
        } else if (CODEC_ID_VTT.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, VTT_PREFIX, r13);
            return finishWriteSampleData();
        } else {
            TrackOutput trackOutput = track.output;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.getData(), 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.getData()[0] & 128) == 128) {
                            throw ParserException.createForMalformedContainer("Extension bit is set in signal byte", null);
                        }
                        this.sampleSignalByte = this.scratch.getData()[0];
                        this.sampleSignalByteRead = true;
                    }
                    byte b = this.sampleSignalByte;
                    if ((b & 1) == 1) {
                        boolean z2 = (b & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.getData(), 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            this.scratch.getData()[0] = (byte) ((z2 ? 128 : 0) | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8, 1);
                            this.sampleBytesWritten += 8;
                        }
                        if (z2) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.getData(), 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int r1 = this.samplePartitionCount * 4;
                            this.scratch.reset(r1);
                            extractorInput.readFully(this.scratch.getData(), 0, r1);
                            this.sampleBytesRead += r1;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            int r6 = (s * 6) + 2;
                            ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                            if (byteBuffer == null || byteBuffer.capacity() < r6) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(r6);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            int r12 = 0;
                            int r7 = 0;
                            while (true) {
                                r8 = this.samplePartitionCount;
                                if (r12 >= r8) {
                                    break;
                                }
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (r12 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - r7));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - r7);
                                }
                                r12++;
                                r7 = readUnsignedIntToInt;
                            }
                            int r14 = (r13 - this.sampleBytesRead) - r7;
                            if (r8 % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(r14);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) r14);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), r6);
                            trackOutput.sampleData(this.encryptionSubsampleData, r6, 1);
                            this.sampleBytesWritten += r6;
                        }
                    }
                } else if (track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
                }
                if (track.samplesHaveSupplementalData(z)) {
                    this.blockFlags |= 268435456;
                    this.supplementalData.reset(0);
                    int limit = (this.sampleStrippedBytes.limit() + r13) - this.sampleBytesRead;
                    this.scratch.reset(4);
                    this.scratch.getData()[0] = (byte) ((limit >> 24) & 255);
                    this.scratch.getData()[1] = (byte) ((limit >> 16) & 255);
                    this.scratch.getData()[2] = (byte) ((limit >> 8) & 255);
                    this.scratch.getData()[3] = (byte) (limit & 255);
                    trackOutput.sampleData(this.scratch, 4, 2);
                    this.sampleBytesWritten += 4;
                }
                this.sampleEncodingHandled = true;
            }
            int limit2 = r13 + this.sampleStrippedBytes.limit();
            if (CODEC_ID_H264.equals(track.codecId) || CODEC_ID_H265.equals(track.codecId)) {
                byte[] data = this.nalLength.getData();
                data[0] = 0;
                data[1] = 0;
                data[2] = 0;
                int r15 = track.nalUnitLengthFieldLength;
                int r3 = 4 - track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < limit2) {
                    int r4 = this.sampleCurrentNalBytesRemaining;
                    if (r4 == 0) {
                        writeToTarget(extractorInput, data, r3, r15);
                        this.sampleBytesRead += r15;
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        int writeToOutput = writeToOutput(extractorInput, trackOutput, r4);
                        this.sampleBytesRead += writeToOutput;
                        this.sampleBytesWritten += writeToOutput;
                        this.sampleCurrentNalBytesRemaining -= writeToOutput;
                    }
                }
            } else {
                if (track.trueHdSampleRechunker != null) {
                    Assertions.checkState(this.sampleStrippedBytes.limit() == 0);
                    track.trueHdSampleRechunker.startSample(extractorInput);
                }
                while (true) {
                    int r142 = this.sampleBytesRead;
                    if (r142 >= limit2) {
                        break;
                    }
                    int writeToOutput2 = writeToOutput(extractorInput, trackOutput, limit2 - r142);
                    this.sampleBytesRead += writeToOutput2;
                    this.sampleBytesWritten += writeToOutput2;
                }
            }
            if (CODEC_ID_VORBIS.equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
            return finishWriteSampleData();
        }
    }

    private int finishWriteSampleData() {
        int r0 = this.sampleBytesWritten;
        resetWriteSampleData();
        return r0;
    }

    private void resetWriteSampleData() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = (byte) 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset(0);
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int r7) throws IOException {
        int length = bArr.length + r7;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.reset(Arrays.copyOf(bArr, length + r7));
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.getData(), 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.getData(), bArr.length, r7);
        this.subtitleSample.setPosition(0);
        this.subtitleSample.setLimit(length);
    }

    private static void setSubtitleEndTime(String str, long j, byte[] bArr) {
        byte[] formatSubtitleTimecode;
        int r6;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 738597099:
                if (str.equals(CODEC_ID_ASS)) {
                    c = 0;
                    break;
                }
                break;
            case 1045209816:
                if (str.equals(CODEC_ID_VTT)) {
                    c = 1;
                    break;
                }
                break;
            case 1422270023:
                if (str.equals(CODEC_ID_SUBRIP)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                formatSubtitleTimecode = formatSubtitleTimecode(j, SSA_TIMECODE_FORMAT, 10000L);
                r6 = 21;
                break;
            case 1:
                formatSubtitleTimecode = formatSubtitleTimecode(j, VTT_TIMECODE_FORMAT, 1000L);
                r6 = 25;
                break;
            case 2:
                formatSubtitleTimecode = formatSubtitleTimecode(j, SUBRIP_TIMECODE_FORMAT, 1000L);
                r6 = 19;
                break;
            default:
                throw new IllegalArgumentException();
        }
        System.arraycopy(formatSubtitleTimecode, 0, bArr, r6, formatSubtitleTimecode.length);
    }

    private static byte[] formatSubtitleTimecode(long j, String str, long j2) {
        Assertions.checkArgument(j != C1856C.TIME_UNSET);
        int r3 = (int) (j / 3600000000L);
        long j3 = j - ((r3 * 3600) * 1000000);
        int r2 = (int) (j3 / 60000000);
        long j4 = j3 - ((r2 * 60) * 1000000);
        int r5 = (int) (j4 / 1000000);
        return Util.getUtf8Bytes(String.format(Locale.US, str, Integer.valueOf(r3), Integer.valueOf(r2), Integer.valueOf(r5), Integer.valueOf((int) ((j4 - (r5 * 1000000)) / j2))));
    }

    private void writeToTarget(ExtractorInput extractorInput, byte[] bArr, int r5, int r6) throws IOException {
        int min = Math.min(r6, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, r5 + min, r6 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, r5, min);
        }
    }

    private int writeToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int r4) throws IOException {
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft > 0) {
            int min = Math.min(r4, bytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, min);
            return min;
        }
        return trackOutput.sampleData((DataReader) extractorInput, r4, false);
    }

    private SeekMap buildSeekMap(LongArray longArray, LongArray longArray2) {
        int r12;
        if (this.segmentContentPosition == -1 || this.durationUs == C1856C.TIME_UNSET || longArray == null || longArray.size() == 0 || longArray2 == null || longArray2.size() != longArray.size()) {
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = longArray.size();
        int[] r1 = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int r5 = 0;
        for (int r6 = 0; r6 < size; r6++) {
            jArr3[r6] = longArray.get(r6);
            jArr[r6] = this.segmentContentPosition + longArray2.get(r6);
        }
        while (true) {
            r12 = size - 1;
            if (r5 >= r12) {
                break;
            }
            int r122 = r5 + 1;
            r1[r5] = (int) (jArr[r122] - jArr[r5]);
            jArr2[r5] = jArr3[r122] - jArr3[r5];
            r5 = r122;
        }
        r1[r12] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[r12]);
        jArr2[r12] = this.durationUs - jArr3[r12];
        long j = jArr2[r12];
        if (j <= 0) {
            Log.m1132w(TAG, "Discarding last cue point with unexpected duration: " + j);
            r1 = Arrays.copyOf(r1, r12);
            jArr = Arrays.copyOf(jArr, r12);
            jArr2 = Arrays.copyOf(jArr2, r12);
            jArr3 = Arrays.copyOf(jArr3, r12);
        }
        return new ChunkIndex(r1, jArr, jArr2, jArr3);
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1L;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 == C1856C.TIME_UNSET) {
            throw ParserException.createForMalformedContainer("Can't scale timecode prior to timecodeScale being set.", null);
        }
        return Util.scaleLargeTimestamp(j, j2, 1000L);
    }

    private static boolean isCodecSupported(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2095576542:
                if (str.equals(CODEC_ID_MPEG4_AP)) {
                    c = 0;
                    break;
                }
                break;
            case -2095575984:
                if (str.equals(CODEC_ID_MPEG4_SP)) {
                    c = 1;
                    break;
                }
                break;
            case -1985379776:
                if (str.equals(CODEC_ID_ACM)) {
                    c = 2;
                    break;
                }
                break;
            case -1784763192:
                if (str.equals(CODEC_ID_TRUEHD)) {
                    c = 3;
                    break;
                }
                break;
            case -1730367663:
                if (str.equals(CODEC_ID_VORBIS)) {
                    c = 4;
                    break;
                }
                break;
            case -1482641358:
                if (str.equals(CODEC_ID_MP2)) {
                    c = 5;
                    break;
                }
                break;
            case -1482641357:
                if (str.equals(CODEC_ID_MP3)) {
                    c = 6;
                    break;
                }
                break;
            case -1373388978:
                if (str.equals(CODEC_ID_FOURCC)) {
                    c = 7;
                    break;
                }
                break;
            case -933872740:
                if (str.equals(CODEC_ID_DVBSUB)) {
                    c = '\b';
                    break;
                }
                break;
            case -538363189:
                if (str.equals(CODEC_ID_MPEG4_ASP)) {
                    c = '\t';
                    break;
                }
                break;
            case -538363109:
                if (str.equals(CODEC_ID_H264)) {
                    c = '\n';
                    break;
                }
                break;
            case -425012669:
                if (str.equals(CODEC_ID_VOBSUB)) {
                    c = 11;
                    break;
                }
                break;
            case -356037306:
                if (str.equals(CODEC_ID_DTS_LOSSLESS)) {
                    c = '\f';
                    break;
                }
                break;
            case 62923557:
                if (str.equals(CODEC_ID_AAC)) {
                    c = CharUtils.f1567CR;
                    break;
                }
                break;
            case 62923603:
                if (str.equals(CODEC_ID_AC3)) {
                    c = 14;
                    break;
                }
                break;
            case 62927045:
                if (str.equals(CODEC_ID_DTS)) {
                    c = 15;
                    break;
                }
                break;
            case 82318131:
                if (str.equals(CODEC_ID_AV1)) {
                    c = 16;
                    break;
                }
                break;
            case 82338133:
                if (str.equals(CODEC_ID_VP8)) {
                    c = 17;
                    break;
                }
                break;
            case 82338134:
                if (str.equals(CODEC_ID_VP9)) {
                    c = 18;
                    break;
                }
                break;
            case 99146302:
                if (str.equals(CODEC_ID_PGS)) {
                    c = 19;
                    break;
                }
                break;
            case 444813526:
                if (str.equals(CODEC_ID_THEORA)) {
                    c = 20;
                    break;
                }
                break;
            case 542569478:
                if (str.equals(CODEC_ID_DTS_EXPRESS)) {
                    c = 21;
                    break;
                }
                break;
            case 635596514:
                if (str.equals(CODEC_ID_PCM_FLOAT)) {
                    c = 22;
                    break;
                }
                break;
            case 725948237:
                if (str.equals(CODEC_ID_PCM_INT_BIG)) {
                    c = 23;
                    break;
                }
                break;
            case 725957860:
                if (str.equals(CODEC_ID_PCM_INT_LIT)) {
                    c = 24;
                    break;
                }
                break;
            case 738597099:
                if (str.equals(CODEC_ID_ASS)) {
                    c = 25;
                    break;
                }
                break;
            case 855502857:
                if (str.equals(CODEC_ID_H265)) {
                    c = 26;
                    break;
                }
                break;
            case 1045209816:
                if (str.equals(CODEC_ID_VTT)) {
                    c = 27;
                    break;
                }
                break;
            case 1422270023:
                if (str.equals(CODEC_ID_SUBRIP)) {
                    c = 28;
                    break;
                }
                break;
            case 1809237540:
                if (str.equals(CODEC_ID_MPEG2)) {
                    c = 29;
                    break;
                }
                break;
            case 1950749482:
                if (str.equals(CODEC_ID_E_AC3)) {
                    c = 30;
                    break;
                }
                break;
            case 1950789798:
                if (str.equals(CODEC_ID_FLAC)) {
                    c = 31;
                    break;
                }
                break;
            case 1951062397:
                if (str.equals(CODEC_ID_OPUS)) {
                    c = ' ';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
            case '\n':
            case 11:
            case '\f':
            case '\r':
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case ' ':
                return true;
            default:
                return false;
        }
    }

    private static int[] ensureArrayCapacity(int[] r1, int r2) {
        if (r1 == null) {
            return new int[r2];
        }
        return r1.length >= r2 ? r1 : new int[Math.max(r1.length * 2, r2)];
    }

    @EnsuresNonNull({"extractorOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.extractorOutput);
    }

    /* loaded from: classes2.dex */
    private final class InnerEbmlProcessor implements EbmlProcessor {
        private InnerEbmlProcessor() {
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlProcessor
        public int getElementType(int r2) {
            return MatroskaExtractor.this.getElementType(r2);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlProcessor
        public boolean isLevel1Element(int r2) {
            return MatroskaExtractor.this.isLevel1Element(r2);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlProcessor
        public void startMasterElement(int r7, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(r7, j, j2);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlProcessor
        public void endMasterElement(int r2) throws ParserException {
            MatroskaExtractor.this.endMasterElement(r2);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlProcessor
        public void integerElement(int r2, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(r2, j);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlProcessor
        public void floatElement(int r2, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(r2, d);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlProcessor
        public void stringElement(int r2, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(r2, str);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlProcessor
        public void binaryElement(int r2, int r3, ExtractorInput extractorInput) throws IOException {
            MatroskaExtractor.this.binaryElement(r2, r3, extractorInput);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        private int blockAddIdType;
        public String codecId;
        public byte[] codecPrivate;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public byte[] dolbyVisionConfigBytes;
        public DrmInitData drmInitData;
        public boolean flagForced;
        public boolean hasContentEncryption;
        public int maxBlockAdditionId;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public byte[] sampleStrippedBytes;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public int width = -1;
        public int height = -1;
        public int displayWidth = -1;
        public int displayHeight = -1;
        public int displayUnit = 0;
        public int projectionType = -1;
        public float projectionPoseYaw = 0.0f;
        public float projectionPosePitch = 0.0f;
        public float projectionPoseRoll = 0.0f;
        public byte[] projectionData = null;
        public int stereoMode = -1;
        public boolean hasColorInfo = false;
        public int colorSpace = -1;
        public int colorTransfer = -1;
        public int colorRange = -1;
        public int maxContentLuminance = 1000;
        public int maxFrameAverageLuminance = 200;
        public float primaryRChromaticityX = -1.0f;
        public float primaryRChromaticityY = -1.0f;
        public float primaryGChromaticityX = -1.0f;
        public float primaryGChromaticityY = -1.0f;
        public float primaryBChromaticityX = -1.0f;
        public float primaryBChromaticityY = -1.0f;
        public float whitePointChromaticityX = -1.0f;
        public float whitePointChromaticityY = -1.0f;
        public float maxMasteringLuminance = -1.0f;
        public float minMasteringLuminance = -1.0f;
        public int channelCount = 1;
        public int audioBitDepth = -1;
        public int sampleRate = 8000;
        public long codecDelayNs = 0;
        public long seekPreRollNs = 0;
        public boolean flagDefault = true;
        private String language = "eng";

        protected Track() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @EnsuresNonNull({"this.output"})
        @RequiresNonNull({"codecId"})
        public void initializeOutput(ExtractorOutput extractorOutput, int r21) throws ParserException {
            char c;
            List<byte[]> singletonList;
            int pcmEncoding;
            String str;
            int r4;
            int r6;
            List<byte[]> list;
            String str2;
            int r5;
            int r42;
            int r52;
            int r12;
            DolbyVisionConfig parse;
            String str3 = this.codecId;
            str3.hashCode();
            switch (str3.hashCode()) {
                case -2095576542:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_MPEG4_AP)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -2095575984:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_MPEG4_SP)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1985379776:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_ACM)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1784763192:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_TRUEHD)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -1730367663:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_VORBIS)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -1482641358:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_MP2)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -1482641357:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_MP3)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -1373388978:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_FOURCC)) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -933872740:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_DVBSUB)) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case -538363189:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_MPEG4_ASP)) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case -538363109:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_H264)) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case -425012669:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_VOBSUB)) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case -356037306:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_DTS_LOSSLESS)) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case 62923557:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_AAC)) {
                        c = CharUtils.f1567CR;
                        break;
                    }
                    c = 65535;
                    break;
                case 62923603:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_AC3)) {
                        c = 14;
                        break;
                    }
                    c = 65535;
                    break;
                case 62927045:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_DTS)) {
                        c = 15;
                        break;
                    }
                    c = 65535;
                    break;
                case 82318131:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_AV1)) {
                        c = 16;
                        break;
                    }
                    c = 65535;
                    break;
                case 82338133:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_VP8)) {
                        c = 17;
                        break;
                    }
                    c = 65535;
                    break;
                case 82338134:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_VP9)) {
                        c = 18;
                        break;
                    }
                    c = 65535;
                    break;
                case 99146302:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_PGS)) {
                        c = 19;
                        break;
                    }
                    c = 65535;
                    break;
                case 444813526:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_THEORA)) {
                        c = 20;
                        break;
                    }
                    c = 65535;
                    break;
                case 542569478:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_DTS_EXPRESS)) {
                        c = 21;
                        break;
                    }
                    c = 65535;
                    break;
                case 635596514:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_PCM_FLOAT)) {
                        c = 22;
                        break;
                    }
                    c = 65535;
                    break;
                case 725948237:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_PCM_INT_BIG)) {
                        c = 23;
                        break;
                    }
                    c = 65535;
                    break;
                case 725957860:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_PCM_INT_LIT)) {
                        c = 24;
                        break;
                    }
                    c = 65535;
                    break;
                case 738597099:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_ASS)) {
                        c = 25;
                        break;
                    }
                    c = 65535;
                    break;
                case 855502857:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_H265)) {
                        c = 26;
                        break;
                    }
                    c = 65535;
                    break;
                case 1045209816:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_VTT)) {
                        c = 27;
                        break;
                    }
                    c = 65535;
                    break;
                case 1422270023:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_SUBRIP)) {
                        c = 28;
                        break;
                    }
                    c = 65535;
                    break;
                case 1809237540:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_MPEG2)) {
                        c = 29;
                        break;
                    }
                    c = 65535;
                    break;
                case 1950749482:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_E_AC3)) {
                        c = 30;
                        break;
                    }
                    c = 65535;
                    break;
                case 1950789798:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_FLAC)) {
                        c = 31;
                        break;
                    }
                    c = 65535;
                    break;
                case 1951062397:
                    if (str3.equals(MatroskaExtractor.CODEC_ID_OPUS)) {
                        c = ' ';
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            String str4 = MimeTypes.AUDIO_RAW;
            switch (c) {
                case 0:
                case 1:
                case '\t':
                    byte[] bArr = this.codecPrivate;
                    singletonList = bArr == null ? null : Collections.singletonList(bArr);
                    str4 = MimeTypes.VIDEO_MP4V;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 2:
                    if (parseMsAcmCodecPrivate(new ParsableByteArray(getCodecPrivate(this.codecId)))) {
                        pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                        if (pcmEncoding == 0) {
                            Log.m1132w(MatroskaExtractor.TAG, "Unsupported PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                        }
                        r4 = pcmEncoding;
                        singletonList = null;
                        str = null;
                        r6 = -1;
                        break;
                    } else {
                        Log.m1132w(MatroskaExtractor.TAG, "Non-PCM MS/ACM is unsupported. Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                    }
                    singletonList = null;
                    str = null;
                    str4 = MimeTypes.AUDIO_UNKNOWN;
                    r4 = -1;
                    r6 = -1;
                case 3:
                    this.trueHdSampleRechunker = new TrueHdSampleRechunker();
                    str4 = MimeTypes.AUDIO_TRUEHD;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 4:
                    singletonList = parseVorbisCodecPrivate(getCodecPrivate(this.codecId));
                    str4 = MimeTypes.AUDIO_VORBIS;
                    str = null;
                    r4 = -1;
                    r6 = 8192;
                    break;
                case 5:
                    str4 = MimeTypes.AUDIO_MPEG_L2;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = 4096;
                    break;
                case 6:
                    str4 = MimeTypes.AUDIO_MPEG;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = 4096;
                    break;
                case 7:
                    Pair<String, List<byte[]>> parseFourCcPrivate = parseFourCcPrivate(new ParsableByteArray(getCodecPrivate(this.codecId)));
                    str4 = (String) parseFourCcPrivate.first;
                    singletonList = (List) parseFourCcPrivate.second;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case '\b':
                    byte[] bArr2 = new byte[4];
                    System.arraycopy(getCodecPrivate(this.codecId), 0, bArr2, 0, 4);
                    singletonList = ImmutableList.m408of(bArr2);
                    str4 = MimeTypes.APPLICATION_DVBSUBS;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case '\n':
                    AvcConfig parse2 = AvcConfig.parse(new ParsableByteArray(getCodecPrivate(this.codecId)));
                    list = parse2.initializationData;
                    this.nalUnitLengthFieldLength = parse2.nalUnitLengthFieldLength;
                    str2 = parse2.codecs;
                    str4 = MimeTypes.VIDEO_H264;
                    r4 = -1;
                    r6 = -1;
                    List<byte[]> list2 = list;
                    str = str2;
                    singletonList = list2;
                    break;
                case 11:
                    singletonList = ImmutableList.m408of(getCodecPrivate(this.codecId));
                    str = null;
                    str4 = MimeTypes.APPLICATION_VOBSUB;
                    r4 = -1;
                    r6 = -1;
                    break;
                case '\f':
                    str4 = MimeTypes.AUDIO_DTS_HD;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case '\r':
                    singletonList = Collections.singletonList(getCodecPrivate(this.codecId));
                    AacUtil.Config parseAudioSpecificConfig = AacUtil.parseAudioSpecificConfig(this.codecPrivate);
                    this.sampleRate = parseAudioSpecificConfig.sampleRateHz;
                    this.channelCount = parseAudioSpecificConfig.channelCount;
                    str = parseAudioSpecificConfig.codecs;
                    str4 = MimeTypes.AUDIO_AAC;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 14:
                    str4 = MimeTypes.AUDIO_AC3;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 15:
                case 21:
                    str4 = MimeTypes.AUDIO_DTS;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 16:
                    str4 = MimeTypes.VIDEO_AV1;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 17:
                    str4 = MimeTypes.VIDEO_VP8;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 18:
                    str4 = MimeTypes.VIDEO_VP9;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 19:
                    singletonList = null;
                    str = null;
                    str4 = MimeTypes.APPLICATION_PGS;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 20:
                    str4 = MimeTypes.VIDEO_UNKNOWN;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 22:
                    if (this.audioBitDepth == 32) {
                        singletonList = null;
                        str = null;
                        r4 = 4;
                        r6 = -1;
                        break;
                    } else {
                        Log.m1132w(MatroskaExtractor.TAG, "Unsupported floating point PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                        singletonList = null;
                        str = null;
                        str4 = MimeTypes.AUDIO_UNKNOWN;
                        r4 = -1;
                        r6 = -1;
                    }
                case 23:
                    int r1 = this.audioBitDepth;
                    if (r1 == 8) {
                        singletonList = null;
                        str = null;
                        r4 = 3;
                    } else if (r1 == 16) {
                        singletonList = null;
                        str = null;
                        r4 = 268435456;
                    } else {
                        Log.m1132w(MatroskaExtractor.TAG, "Unsupported big endian PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                        singletonList = null;
                        str = null;
                        str4 = MimeTypes.AUDIO_UNKNOWN;
                        r4 = -1;
                    }
                    r6 = -1;
                    break;
                case 24:
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        Log.m1132w(MatroskaExtractor.TAG, "Unsupported little endian PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                        singletonList = null;
                        str = null;
                        str4 = MimeTypes.AUDIO_UNKNOWN;
                        r4 = -1;
                        r6 = -1;
                        break;
                    }
                    r4 = pcmEncoding;
                    singletonList = null;
                    str = null;
                    r6 = -1;
                case 25:
                    singletonList = ImmutableList.m407of(MatroskaExtractor.SSA_DIALOGUE_FORMAT, getCodecPrivate(this.codecId));
                    str = null;
                    str4 = MimeTypes.TEXT_SSA;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 26:
                    HevcConfig parse3 = HevcConfig.parse(new ParsableByteArray(getCodecPrivate(this.codecId)));
                    list = parse3.initializationData;
                    this.nalUnitLengthFieldLength = parse3.nalUnitLengthFieldLength;
                    str2 = parse3.codecs;
                    str4 = MimeTypes.VIDEO_H265;
                    r4 = -1;
                    r6 = -1;
                    List<byte[]> list22 = list;
                    str = str2;
                    singletonList = list22;
                    break;
                case 27:
                    singletonList = null;
                    str = null;
                    str4 = MimeTypes.TEXT_VTT;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 28:
                    str4 = MimeTypes.APPLICATION_SUBRIP;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 29:
                    str4 = MimeTypes.VIDEO_MPEG2;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 30:
                    str4 = MimeTypes.AUDIO_E_AC3;
                    singletonList = null;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case 31:
                    singletonList = Collections.singletonList(getCodecPrivate(this.codecId));
                    str4 = MimeTypes.AUDIO_FLAC;
                    str = null;
                    r4 = -1;
                    r6 = -1;
                    break;
                case ' ':
                    singletonList = new ArrayList<>(3);
                    singletonList.add(getCodecPrivate(this.codecId));
                    singletonList.add(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.codecDelayNs).array());
                    singletonList.add(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.seekPreRollNs).array());
                    str4 = MimeTypes.AUDIO_OPUS;
                    str = null;
                    r4 = -1;
                    r6 = MatroskaExtractor.OPUS_MAX_INPUT_SIZE;
                    break;
                default:
                    throw ParserException.createForMalformedContainer("Unrecognized codec identifier.", null);
            }
            if (this.dolbyVisionConfigBytes != null && (parse = DolbyVisionConfig.parse(new ParsableByteArray(this.dolbyVisionConfigBytes))) != null) {
                str = parse.codecs;
                str4 = MimeTypes.VIDEO_DOLBY_VISION;
            }
            String str5 = str4;
            int r9 = (this.flagForced ? 2 : 0) | (this.flagDefault ? 1 : 0) | 0;
            Format.Builder builder = new Format.Builder();
            if (MimeTypes.isAudio(str5)) {
                builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(r4);
                r5 = 1;
            } else if (MimeTypes.isVideo(str5)) {
                if (this.displayUnit == 0) {
                    int r2 = this.displayWidth;
                    r42 = -1;
                    if (r2 == -1) {
                        r2 = this.width;
                    }
                    this.displayWidth = r2;
                    int r22 = this.displayHeight;
                    if (r22 == -1) {
                        r22 = this.height;
                    }
                    this.displayHeight = r22;
                } else {
                    r42 = -1;
                }
                float f = -1.0f;
                if (this.displayWidth != r42 && (r12 = this.displayHeight) != r42) {
                    f = (this.height * r52) / (this.width * r12);
                }
                ColorInfo colorInfo = this.hasColorInfo ? new ColorInfo(this.colorSpace, this.colorRange, this.colorTransfer, getHdrStaticInfo()) : null;
                if (this.name != null && MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES.containsKey(this.name)) {
                    r42 = ((Integer) MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES.get(this.name)).intValue();
                }
                if (this.projectionType == 0 && Float.compare(this.projectionPoseYaw, 0.0f) == 0 && Float.compare(this.projectionPosePitch, 0.0f) == 0) {
                    if (Float.compare(this.projectionPoseRoll, 0.0f) == 0) {
                        r42 = 0;
                    } else if (Float.compare(this.projectionPosePitch, 90.0f) == 0) {
                        r42 = 90;
                    } else if (Float.compare(this.projectionPosePitch, -180.0f) == 0 || Float.compare(this.projectionPosePitch, 180.0f) == 0) {
                        r42 = RotationOptions.ROTATE_180;
                    } else if (Float.compare(this.projectionPosePitch, -90.0f) == 0) {
                        r42 = 270;
                    }
                }
                builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(r42).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfo);
                r5 = 2;
            } else if (!MimeTypes.APPLICATION_SUBRIP.equals(str5) && !MimeTypes.TEXT_SSA.equals(str5) && !MimeTypes.TEXT_VTT.equals(str5) && !MimeTypes.APPLICATION_VOBSUB.equals(str5) && !MimeTypes.APPLICATION_PGS.equals(str5) && !MimeTypes.APPLICATION_DVBSUBS.equals(str5)) {
                throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
            } else {
                r5 = 3;
            }
            if (this.name != null && !MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES.containsKey(this.name)) {
                builder.setLabel(this.name);
            }
            Format build = builder.setId(r21).setSampleMimeType(str5).setMaxInputSize(r6).setLanguage(this.language).setSelectionFlags(r9).setInitializationData(singletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
            TrackOutput track = extractorOutput.track(this.number, r5);
            this.output = track;
            track.format(build);
        }

        @RequiresNonNull({"output"})
        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.outputPendingSampleMetadata(this.output, this.cryptoData);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.reset();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean samplesHaveSupplementalData(boolean z) {
            return MatroskaExtractor.CODEC_ID_OPUS.equals(this.codecId) ? z : this.maxBlockAdditionId > 0;
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            order.put((byte) 0);
            order.putShort((short) ((this.primaryRChromaticityX * 50000.0f) + 0.5f));
            order.putShort((short) ((this.primaryRChromaticityY * 50000.0f) + 0.5f));
            order.putShort((short) ((this.primaryGChromaticityX * 50000.0f) + 0.5f));
            order.putShort((short) ((this.primaryGChromaticityY * 50000.0f) + 0.5f));
            order.putShort((short) ((this.primaryBChromaticityX * 50000.0f) + 0.5f));
            order.putShort((short) ((this.primaryBChromaticityY * 50000.0f) + 0.5f));
            order.putShort((short) ((this.whitePointChromaticityX * 50000.0f) + 0.5f));
            order.putShort((short) ((this.whitePointChromaticityY * 50000.0f) + 0.5f));
            order.putShort((short) (this.maxMasteringLuminance + 0.5f));
            order.putShort((short) (this.minMasteringLuminance + 0.5f));
            order.putShort((short) this.maxContentLuminance);
            order.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (readLittleEndianUnsignedInt == 1482049860) {
                    return new Pair<>(MimeTypes.VIDEO_DIVX, null);
                }
                if (readLittleEndianUnsignedInt == 859189832) {
                    return new Pair<>(MimeTypes.VIDEO_H263, null);
                }
                if (readLittleEndianUnsignedInt == 826496599) {
                    byte[] data = parsableByteArray.getData();
                    for (int position = parsableByteArray.getPosition() + 20; position < data.length - 4; position++) {
                        if (data[position] == 0 && data[position + 1] == 0 && data[position + 2] == 1 && data[position + 3] == 15) {
                            return new Pair<>(MimeTypes.VIDEO_VC1, Collections.singletonList(Arrays.copyOfRange(data, position, data.length)));
                        }
                    }
                    throw ParserException.createForMalformedContainer("Failed to find FourCC VC1 initialization data", null);
                }
                Log.m1132w(MatroskaExtractor.TAG, "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair<>(MimeTypes.VIDEO_UNKNOWN, null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing FourCC private data", null);
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] != 2) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                int r5 = 1;
                int r6 = 0;
                while ((bArr[r5] & 255) == 255) {
                    r6 += 255;
                    r5++;
                }
                int r7 = r5 + 1;
                int r62 = r6 + (bArr[r5] & 255);
                int r52 = 0;
                while ((bArr[r7] & 255) == 255) {
                    r52 += 255;
                    r7++;
                }
                int r9 = r7 + 1;
                int r53 = r52 + (bArr[r7] & 255);
                if (bArr[r9] != 1) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                byte[] bArr2 = new byte[r62];
                System.arraycopy(bArr, r9, bArr2, 0, r62);
                int r92 = r9 + r62;
                if (bArr[r92] != 3) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                int r93 = r92 + r53;
                if (bArr[r93] != 5) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                byte[] bArr3 = new byte[bArr.length - r93];
                System.arraycopy(bArr, r93, bArr3, 0, bArr.length - r93);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort == 65534) {
                    parsableByteArray.setPosition(24);
                    if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits()) {
                        if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing MS/ACM codec private", null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @EnsuresNonNull({"output"})
        public void assertOutputInitialized() {
            Assertions.checkNotNull(this.output);
        }

        @EnsuresNonNull({"codecPrivate"})
        private byte[] getCodecPrivate(String str) throws ParserException {
            byte[] bArr = this.codecPrivate;
            if (bArr != null) {
                return bArr;
            }
            throw ParserException.createForMalformedContainer("Missing CodecPrivate for codec " + str, null);
        }
    }
}
