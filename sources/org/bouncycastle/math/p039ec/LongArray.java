package org.bouncycastle.math.p039ec;

import android.support.p001v4.media.session.PlaybackStateCompat;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.base.Ascii;
import com.google.common.primitives.Shorts;
import java.math.BigInteger;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: org.bouncycastle.math.ec.LongArray */
/* loaded from: classes5.dex */
public class LongArray implements Cloneable {
    private static final String ZEROES = "0000000000000000000000000000000000000000000000000000000000000000";
    private long[] m_ints;
    private static final short[] INTERLEAVE2_TABLE = {0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 68, 69, 80, 81, 84, 85, 256, 257, 260, 261, 272, 273, 276, 277, 320, 321, 324, 325, 336, 337, 340, 341, SilenceSkippingAudioProcessor.DEFAULT_SILENCE_THRESHOLD_LEVEL, 1025, 1028, 1029, 1040, 1041, 1044, 1045, 1088, 1089, 1092, 1093, 1104, 1105, 1108, 1109, 1280, 1281, 1284, 1285, 1296, 1297, 1300, 1301, 1344, 1345, 1348, 1349, 1360, 1361, 1364, 1365, 4096, 4097, 4100, 4101, 4112, 4113, 4116, 4117, 4160, 4161, 4164, 4165, 4176, 4177, 4180, 4181, 4352, 4353, 4356, 4357, 4368, 4369, 4372, 4373, 4416, 4417, 4420, 4421, 4432, 4433, 4436, 4437, 5120, 5121, 5124, 5125, 5136, 5137, 5140, 5141, 5184, 5185, 5188, 5189, 5200, 5201, 5204, 5205, 5376, 5377, 5380, 5381, 5392, 5393, 5396, 5397, 5440, 5441, 5444, 5445, 5456, 5457, 5460, 5461, Shorts.MAX_POWER_OF_TWO, 16385, 16388, 16389, 16400, 16401, 16404, 16405, 16448, 16449, 16452, 16453, 16464, 16465, 16468, 16469, 16640, 16641, 16644, 16645, 16656, 16657, 16660, 16661, 16704, 16705, 16708, 16709, 16720, 16721, 16724, 16725, 17408, 17409, 17412, 17413, 17424, 17425, 17428, 17429, 17472, 17473, 17476, 17477, 17488, 17489, 17492, 17493, 17664, 17665, 17668, 17669, 17680, 17681, 17684, 17685, 17728, 17729, 17732, 17733, 17744, 17745, 17748, 17749, 20480, 20481, 20484, 20485, 20496, 20497, 20500, 20501, 20544, 20545, 20548, 20549, 20560, 20561, 20564, 20565, 20736, 20737, 20740, 20741, 20752, 20753, 20756, 20757, 20800, 20801, 20804, 20805, 20816, 20817, 20820, 20821, 21504, 21505, 21508, 21509, 21520, 21521, 21524, 21525, 21568, 21569, 21572, 21573, 21584, 21585, 21588, 21589, 21760, 21761, 21764, 21765, 21776, 21777, 21780, 21781, 21824, 21825, 21828, 21829, 21840, 21841, 21844, 21845};
    private static final int[] INTERLEAVE3_TABLE = {0, 1, 8, 9, 64, 65, 72, 73, 512, InputDeviceCompat.SOURCE_DPAD, 520, 521, 576, 577, 584, 585, 4096, FragmentTransaction.TRANSIT_FRAGMENT_OPEN, 4104, 4105, 4160, 4161, 4168, 4169, 4608, 4609, 4616, 4617, 4672, 4673, 4680, 4681, 32768, 32769, 32776, 32777, 32832, 32833, 32840, 32841, 33280, 33281, 33288, 33289, 33344, 33345, 33352, 33353, 36864, 36865, 36872, 36873, 36928, 36929, 36936, 36937, 37376, 37377, 37384, 37385, 37440, 37441, 37448, 37449, 262144, 262145, 262152, 262153, 262208, 262209, 262216, 262217, 262656, 262657, 262664, 262665, 262720, 262721, 262728, 262729, 266240, 266241, 266248, 266249, 266304, 266305, 266312, 266313, 266752, 266753, 266760, 266761, 266816, 266817, 266824, 266825, 294912, 294913, 294920, 294921, 294976, 294977, 294984, 294985, 295424, 295425, 295432, 295433, 295488, 295489, 295496, 295497, 299008, 299009, 299016, 299017, 299072, 299073, 299080, 299081, 299520, 299521, 299528, 299529, 299584, 299585, 299592, 299593};
    private static final int[] INTERLEAVE4_TABLE = {0, 1, 16, 17, 256, 257, 272, 273, 4096, FragmentTransaction.TRANSIT_FRAGMENT_OPEN, 4112, 4113, 4352, 4353, 4368, 4369, 65536, 65537, 65552, 65553, 65792, 65793, 65808, 65809, 69632, 69633, 69648, 69649, 69888, 69889, 69904, 69905, 1048576, 1048577, 1048592, 1048593, 1048832, 1048833, 1048848, 1048849, 1052672, 1052673, 1052688, 1052689, 1052928, 1052929, 1052944, 1052945, 1114112, 1114113, 1114128, 1114129, 1114368, 1114369, 1114384, 1114385, 1118208, 1118209, 1118224, 1118225, 1118464, 1118465, 1118480, 1118481, 16777216, 16777217, InputDeviceCompat.SOURCE_JOYSTICK, 16777233, 16777472, 16777473, 16777488, 16777489, 16781312, 16781313, 16781328, 16781329, 16781568, 16781569, 16781584, 16781585, 16842752, 16842753, 16842768, 16842769, 16843008, 16843009, 16843024, 16843025, 16846848, 16846849, 16846864, 16846865, 16847104, 16847105, 16847120, 16847121, 17825792, 17825793, 17825808, 17825809, 17826048, 17826049, 17826064, 17826065, 17829888, 17829889, 17829904, 17829905, 17830144, 17830145, 17830160, 17830161, 17891328, 17891329, 17891344, 17891345, 17891584, 17891585, 17891600, 17891601, 17895424, 17895425, 17895440, 17895441, 17895680, 17895681, 17895696, 17895697, 268435456, 268435457, 268435472, 268435473, 268435712, 268435713, 268435728, 268435729, 268439552, 268439553, 268439568, 268439569, 268439808, 268439809, 268439824, 268439825, 268500992, 268500993, 268501008, 268501009, 268501248, 268501249, 268501264, 268501265, 268505088, 268505089, 268505104, 268505105, 268505344, 268505345, 268505360, 268505361, 269484032, 269484033, 269484048, 269484049, 269484288, 269484289, 269484304, 269484305, 269488128, 269488129, 269488144, 269488145, 269488384, 269488385, 269488400, 269488401, 269549568, 269549569, 269549584, 269549585, 269549824, 269549825, 269549840, 269549841, 269553664, 269553665, 269553680, 269553681, 269553920, 269553921, 269553936, 269553937, 285212672, 285212673, 285212688, 285212689, 285212928, 285212929, 285212944, 285212945, 285216768, 285216769, 285216784, 285216785, 285217024, 285217025, 285217040, 285217041, 285278208, 285278209, 285278224, 285278225, 285278464, 285278465, 285278480, 285278481, 285282304, 285282305, 285282320, 285282321, 285282560, 285282561, 285282576, 285282577, 286261248, 286261249, 286261264, 286261265, 286261504, 286261505, 286261520, 286261521, 286265344, 286265345, 286265360, 286265361, 286265600, 286265601, 286265616, 286265617, 286326784, 286326785, 286326800, 286326801, 286327040, 286327041, 286327056, 286327057, 286330880, 286330881, 286330896, 286330897, 286331136, 286331137, 286331152, 286331153};
    private static final int[] INTERLEAVE5_TABLE = {0, 1, 32, 33, 1024, 1025, 1056, 1057, 32768, 32769, 32800, 32801, 33792, 33793, 33824, 33825, 1048576, 1048577, 1048608, 1048609, 1049600, 1049601, 1049632, 1049633, 1081344, 1081345, 1081376, 1081377, 1082368, 1082369, 1082400, 1082401, 33554432, InputDeviceCompat.SOURCE_HDMI, 33554464, 33554465, 33555456, 33555457, 33555488, 33555489, 33587200, 33587201, 33587232, 33587233, 33588224, 33588225, 33588256, 33588257, 34603008, 34603009, 34603040, 34603041, 34604032, 34604033, 34604064, 34604065, 34635776, 34635777, 34635808, 34635809, 34636800, 34636801, 34636832, 34636833, 1073741824, 1073741825, 1073741856, 1073741857, 1073742848, 1073742849, 1073742880, 1073742881, 1073774592, 1073774593, 1073774624, 1073774625, 1073775616, 1073775617, 1073775648, 1073775649, 1074790400, 1074790401, 1074790432, 1074790433, 1074791424, 1074791425, 1074791456, 1074791457, 1074823168, 1074823169, 1074823200, 1074823201, 1074824192, 1074824193, 1074824224, 1074824225, 1107296256, 1107296257, 1107296288, 1107296289, 1107297280, 1107297281, 1107297312, 1107297313, 1107329024, 1107329025, 1107329056, 1107329057, 1107330048, 1107330049, 1107330080, 1107330081, 1108344832, 1108344833, 1108344864, 1108344865, 1108345856, 1108345857, 1108345888, 1108345889, 1108377600, 1108377601, 1108377632, 1108377633, 1108378624, 1108378625, 1108378656, 1108378657};
    private static final long[] INTERLEAVE7_TABLE = {0, 1, 128, 129, 16384, 16385, 16512, 16513, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 2097153, 2097280, 2097281, 2113536, 2113537, 2113664, 2113665, 268435456, 268435457, 268435584, 268435585, 268451840, 268451841, 268451968, 268451969, 270532608, 270532609, 270532736, 270532737, 270548992, 270548993, 270549120, 270549121, 34359738368L, 34359738369L, 34359738496L, 34359738497L, 34359754752L, 34359754753L, 34359754880L, 34359754881L, 34361835520L, 34361835521L, 34361835648L, 34361835649L, 34361851904L, 34361851905L, 34361852032L, 34361852033L, 34628173824L, 34628173825L, 34628173952L, 34628173953L, 34628190208L, 34628190209L, 34628190336L, 34628190337L, 34630270976L, 34630270977L, 34630271104L, 34630271105L, 34630287360L, 34630287361L, 34630287488L, 34630287489L, 4398046511104L, 4398046511105L, 4398046511232L, 4398046511233L, 4398046527488L, 4398046527489L, 4398046527616L, 4398046527617L, 4398048608256L, 4398048608257L, 4398048608384L, 4398048608385L, 4398048624640L, 4398048624641L, 4398048624768L, 4398048624769L, 4398314946560L, 4398314946561L, 4398314946688L, 4398314946689L, 4398314962944L, 4398314962945L, 4398314963072L, 4398314963073L, 4398317043712L, 4398317043713L, 4398317043840L, 4398317043841L, 4398317060096L, 4398317060097L, 4398317060224L, 4398317060225L, 4432406249472L, 4432406249473L, 4432406249600L, 4432406249601L, 4432406265856L, 4432406265857L, 4432406265984L, 4432406265985L, 4432408346624L, 4432408346625L, 4432408346752L, 4432408346753L, 4432408363008L, 4432408363009L, 4432408363136L, 4432408363137L, 4432674684928L, 4432674684929L, 4432674685056L, 4432674685057L, 4432674701312L, 4432674701313L, 4432674701440L, 4432674701441L, 4432676782080L, 4432676782081L, 4432676782208L, 4432676782209L, 4432676798464L, 4432676798465L, 4432676798592L, 4432676798593L, 562949953421312L, 562949953421313L, 562949953421440L, 562949953421441L, 562949953437696L, 562949953437697L, 562949953437824L, 562949953437825L, 562949955518464L, 562949955518465L, 562949955518592L, 562949955518593L, 562949955534848L, 562949955534849L, 562949955534976L, 562949955534977L, 562950221856768L, 562950221856769L, 562950221856896L, 562950221856897L, 562950221873152L, 562950221873153L, 562950221873280L, 562950221873281L, 562950223953920L, 562950223953921L, 562950223954048L, 562950223954049L, 562950223970304L, 562950223970305L, 562950223970432L, 562950223970433L, 562984313159680L, 562984313159681L, 562984313159808L, 562984313159809L, 562984313176064L, 562984313176065L, 562984313176192L, 562984313176193L, 562984315256832L, 562984315256833L, 562984315256960L, 562984315256961L, 562984315273216L, 562984315273217L, 562984315273344L, 562984315273345L, 562984581595136L, 562984581595137L, 562984581595264L, 562984581595265L, 562984581611520L, 562984581611521L, 562984581611648L, 562984581611649L, 562984583692288L, 562984583692289L, 562984583692416L, 562984583692417L, 562984583708672L, 562984583708673L, 562984583708800L, 562984583708801L, 567347999932416L, 567347999932417L, 567347999932544L, 567347999932545L, 567347999948800L, 567347999948801L, 567347999948928L, 567347999948929L, 567348002029568L, 567348002029569L, 567348002029696L, 567348002029697L, 567348002045952L, 567348002045953L, 567348002046080L, 567348002046081L, 567348268367872L, 567348268367873L, 567348268368000L, 567348268368001L, 567348268384256L, 567348268384257L, 567348268384384L, 567348268384385L, 567348270465024L, 567348270465025L, 567348270465152L, 567348270465153L, 567348270481408L, 567348270481409L, 567348270481536L, 567348270481537L, 567382359670784L, 567382359670785L, 567382359670912L, 567382359670913L, 567382359687168L, 567382359687169L, 567382359687296L, 567382359687297L, 567382361767936L, 567382361767937L, 567382361768064L, 567382361768065L, 567382361784320L, 567382361784321L, 567382361784448L, 567382361784449L, 567382628106240L, 567382628106241L, 567382628106368L, 567382628106369L, 567382628122624L, 567382628122625L, 567382628122752L, 567382628122753L, 567382630203392L, 567382630203393L, 567382630203520L, 567382630203521L, 567382630219776L, 567382630219777L, 567382630219904L, 567382630219905L, 72057594037927936L, 72057594037927937L, 72057594037928064L, 72057594037928065L, 72057594037944320L, 72057594037944321L, 72057594037944448L, 72057594037944449L, 72057594040025088L, 72057594040025089L, 72057594040025216L, 72057594040025217L, 72057594040041472L, 72057594040041473L, 72057594040041600L, 72057594040041601L, 72057594306363392L, 72057594306363393L, 72057594306363520L, 72057594306363521L, 72057594306379776L, 72057594306379777L, 72057594306379904L, 72057594306379905L, 72057594308460544L, 72057594308460545L, 72057594308460672L, 72057594308460673L, 72057594308476928L, 72057594308476929L, 72057594308477056L, 72057594308477057L, 72057628397666304L, 72057628397666305L, 72057628397666432L, 72057628397666433L, 72057628397682688L, 72057628397682689L, 72057628397682816L, 72057628397682817L, 72057628399763456L, 72057628399763457L, 72057628399763584L, 72057628399763585L, 72057628399779840L, 72057628399779841L, 72057628399779968L, 72057628399779969L, 72057628666101760L, 72057628666101761L, 72057628666101888L, 72057628666101889L, 72057628666118144L, 72057628666118145L, 72057628666118272L, 72057628666118273L, 72057628668198912L, 72057628668198913L, 72057628668199040L, 72057628668199041L, 72057628668215296L, 72057628668215297L, 72057628668215424L, 72057628668215425L, 72061992084439040L, 72061992084439041L, 72061992084439168L, 72061992084439169L, 72061992084455424L, 72061992084455425L, 72061992084455552L, 72061992084455553L, 72061992086536192L, 72061992086536193L, 72061992086536320L, 72061992086536321L, 72061992086552576L, 72061992086552577L, 72061992086552704L, 72061992086552705L, 72061992352874496L, 72061992352874497L, 72061992352874624L, 72061992352874625L, 72061992352890880L, 72061992352890881L, 72061992352891008L, 72061992352891009L, 72061992354971648L, 72061992354971649L, 72061992354971776L, 72061992354971777L, 72061992354988032L, 72061992354988033L, 72061992354988160L, 72061992354988161L, 72062026444177408L, 72062026444177409L, 72062026444177536L, 72062026444177537L, 72062026444193792L, 72062026444193793L, 72062026444193920L, 72062026444193921L, 72062026446274560L, 72062026446274561L, 72062026446274688L, 72062026446274689L, 72062026446290944L, 72062026446290945L, 72062026446291072L, 72062026446291073L, 72062026712612864L, 72062026712612865L, 72062026712612992L, 72062026712612993L, 72062026712629248L, 72062026712629249L, 72062026712629376L, 72062026712629377L, 72062026714710016L, 72062026714710017L, 72062026714710144L, 72062026714710145L, 72062026714726400L, 72062026714726401L, 72062026714726528L, 72062026714726529L, 72620543991349248L, 72620543991349249L, 72620543991349376L, 72620543991349377L, 72620543991365632L, 72620543991365633L, 72620543991365760L, 72620543991365761L, 72620543993446400L, 72620543993446401L, 72620543993446528L, 72620543993446529L, 72620543993462784L, 72620543993462785L, 72620543993462912L, 72620543993462913L, 72620544259784704L, 72620544259784705L, 72620544259784832L, 72620544259784833L, 72620544259801088L, 72620544259801089L, 72620544259801216L, 72620544259801217L, 72620544261881856L, 72620544261881857L, 72620544261881984L, 72620544261881985L, 72620544261898240L, 72620544261898241L, 72620544261898368L, 72620544261898369L, 72620578351087616L, 72620578351087617L, 72620578351087744L, 72620578351087745L, 72620578351104000L, 72620578351104001L, 72620578351104128L, 72620578351104129L, 72620578353184768L, 72620578353184769L, 72620578353184896L, 72620578353184897L, 72620578353201152L, 72620578353201153L, 72620578353201280L, 72620578353201281L, 72620578619523072L, 72620578619523073L, 72620578619523200L, 72620578619523201L, 72620578619539456L, 72620578619539457L, 72620578619539584L, 72620578619539585L, 72620578621620224L, 72620578621620225L, 72620578621620352L, 72620578621620353L, 72620578621636608L, 72620578621636609L, 72620578621636736L, 72620578621636737L, 72624942037860352L, 72624942037860353L, 72624942037860480L, 72624942037860481L, 72624942037876736L, 72624942037876737L, 72624942037876864L, 72624942037876865L, 72624942039957504L, 72624942039957505L, 72624942039957632L, 72624942039957633L, 72624942039973888L, 72624942039973889L, 72624942039974016L, 72624942039974017L, 72624942306295808L, 72624942306295809L, 72624942306295936L, 72624942306295937L, 72624942306312192L, 72624942306312193L, 72624942306312320L, 72624942306312321L, 72624942308392960L, 72624942308392961L, 72624942308393088L, 72624942308393089L, 72624942308409344L, 72624942308409345L, 72624942308409472L, 72624942308409473L, 72624976397598720L, 72624976397598721L, 72624976397598848L, 72624976397598849L, 72624976397615104L, 72624976397615105L, 72624976397615232L, 72624976397615233L, 72624976399695872L, 72624976399695873L, 72624976399696000L, 72624976399696001L, 72624976399712256L, 72624976399712257L, 72624976399712384L, 72624976399712385L, 72624976666034176L, 72624976666034177L, 72624976666034304L, 72624976666034305L, 72624976666050560L, 72624976666050561L, 72624976666050688L, 72624976666050689L, 72624976668131328L, 72624976668131329L, 72624976668131456L, 72624976668131457L, 72624976668147712L, 72624976668147713L, 72624976668147840L, 72624976668147841L};
    static final byte[] bitLengths = {0, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};

    public LongArray(int r1) {
        this.m_ints = new long[r1];
    }

    public LongArray(BigInteger bigInteger) {
        int r5;
        if (bigInteger == null || bigInteger.signum() < 0) {
            throw new IllegalArgumentException("invalid F2m field value");
        }
        if (bigInteger.signum() == 0) {
            this.m_ints = new long[]{0};
            return;
        }
        byte[] byteArray = bigInteger.toByteArray();
        int length = byteArray.length;
        if (byteArray[0] == 0) {
            length--;
            r5 = 1;
        } else {
            r5 = 0;
        }
        int r6 = (length + 7) / 8;
        this.m_ints = new long[r6];
        int r62 = r6 - 1;
        int r0 = (length % 8) + r5;
        if (r5 < r0) {
            long j = 0;
            while (r5 < r0) {
                j = (j << 8) | (byteArray[r5] & 255);
                r5++;
            }
            this.m_ints[r62] = j;
            r62--;
        }
        while (r62 >= 0) {
            long j2 = 0;
            int r02 = 0;
            while (r02 < 8) {
                j2 = (j2 << 8) | (byteArray[r5] & 255);
                r02++;
                r5++;
            }
            this.m_ints[r62] = j2;
            r62--;
        }
    }

    public LongArray(long[] jArr) {
        this.m_ints = jArr;
    }

    public LongArray(long[] jArr, int r4, int r5) {
        if (r4 == 0 && r5 == jArr.length) {
            this.m_ints = jArr;
            return;
        }
        long[] jArr2 = new long[r5];
        this.m_ints = jArr2;
        System.arraycopy(jArr, r4, jArr2, 0, r5);
    }

    private static void add(long[] jArr, int r7, long[] jArr2, int r9, int r10) {
        for (int r0 = 0; r0 < r10; r0++) {
            int r1 = r7 + r0;
            jArr[r1] = jArr[r1] ^ jArr2[r9 + r0];
        }
    }

    private static void add(long[] jArr, int r7, long[] jArr2, int r9, long[] jArr3, int r11, int r12) {
        for (int r0 = 0; r0 < r12; r0++) {
            jArr3[r11 + r0] = jArr[r7 + r0] ^ jArr2[r9 + r0];
        }
    }

    private static void addBoth(long[] jArr, int r9, long[] jArr2, int r11, long[] jArr3, int r13, int r14) {
        for (int r0 = 0; r0 < r14; r0++) {
            int r1 = r9 + r0;
            jArr[r1] = jArr[r1] ^ (jArr2[r11 + r0] ^ jArr3[r13 + r0]);
        }
    }

    private void addShiftedByBitsSafe(LongArray longArray, int r9, int r10) {
        int r92 = (r9 + 63) >>> 6;
        int r6 = r10 >>> 6;
        int r5 = r10 & 63;
        if (r5 == 0) {
            add(this.m_ints, r6, longArray.m_ints, 0, r92);
            return;
        }
        long addShiftedUp = addShiftedUp(this.m_ints, r6, longArray.m_ints, 0, r92, r5);
        if (addShiftedUp != 0) {
            long[] jArr = this.m_ints;
            int r93 = r92 + r6;
            jArr[r93] = addShiftedUp ^ jArr[r93];
        }
    }

    private static long addShiftedDown(long[] jArr, int r11, long[] jArr2, int r13, int r14, int r15) {
        int r0 = 64 - r15;
        long j = 0;
        while (true) {
            r14--;
            if (r14 < 0) {
                return j;
            }
            long j2 = jArr2[r13 + r14];
            int r5 = r11 + r14;
            jArr[r5] = (j | (j2 >>> r15)) ^ jArr[r5];
            j = j2 << r0;
        }
    }

    private static long addShiftedUp(long[] jArr, int r13, long[] jArr2, int r15, int r16, int r17) {
        int r0 = 64 - r17;
        long j = 0;
        for (int r3 = 0; r3 < r16; r3++) {
            long j2 = jArr2[r15 + r3];
            int r7 = r13 + r3;
            jArr[r7] = (j | (j2 << r17)) ^ jArr[r7];
            j = j2 >>> r0;
        }
        return j;
    }

    private static int bitLength(long j) {
        int r3;
        int r0 = 32;
        int r2 = (int) (j >>> 32);
        if (r2 == 0) {
            r2 = (int) j;
            r0 = 0;
        }
        int r32 = r2 >>> 16;
        if (r32 == 0) {
            int r33 = r2 >>> 8;
            r3 = r33 == 0 ? bitLengths[r2] : bitLengths[r33] + 8;
        } else {
            int r4 = r32 >>> 8;
            r3 = r4 == 0 ? bitLengths[r32] + 16 : bitLengths[r4] + Ascii.CAN;
        }
        return r0 + r3;
    }

    private int degreeFrom(int r6) {
        int r62 = (r6 + 62) >>> 6;
        while (r62 != 0) {
            r62--;
            long j = this.m_ints[r62];
            if (j != 0) {
                return (r62 << 6) + bitLength(j);
            }
        }
        return 0;
    }

    private static void distribute(long[] jArr, int r7, int r8, int r9, int r10) {
        for (int r0 = 0; r0 < r10; r0++) {
            long j = jArr[r7 + r0];
            int r3 = r8 + r0;
            jArr[r3] = jArr[r3] ^ j;
            int r32 = r9 + r0;
            jArr[r32] = j ^ jArr[r32];
        }
    }

    private static void flipBit(long[] jArr, int r6, int r7) {
        int r62 = r6 + (r7 >>> 6);
        jArr[r62] = jArr[r62] ^ (1 << (r7 & 63));
    }

    private static void flipVector(long[] jArr, int r7, long[] jArr2, int r9, int r10, int r11) {
        int r72 = r7 + (r11 >>> 6);
        int r112 = r11 & 63;
        if (r112 == 0) {
            add(jArr, r72, jArr2, r9, r10);
        } else {
            jArr[r72] = addShiftedDown(jArr, r72 + 1, jArr2, r9, r10, 64 - r112) ^ jArr[r72];
        }
    }

    private static void flipWord(long[] jArr, int r5, int r6, long j) {
        int r52 = r5 + (r6 >>> 6);
        int r62 = r6 & 63;
        if (r62 == 0) {
            jArr[r52] = jArr[r52] ^ j;
            return;
        }
        jArr[r52] = jArr[r52] ^ (j << r62);
        long j2 = j >>> (64 - r62);
        if (j2 != 0) {
            int r53 = r52 + 1;
            jArr[r53] = j2 ^ jArr[r53];
        }
    }

    private static void interleave(long[] jArr, int r7, long[] jArr2, int r9, int r10, int r11) {
        if (r11 == 3) {
            interleave3(jArr, r7, jArr2, r9, r10);
        } else if (r11 == 5) {
            interleave5(jArr, r7, jArr2, r9, r10);
        } else if (r11 != 7) {
            interleave2_n(jArr, r7, jArr2, r9, r10, bitLengths[r11] - 1);
        } else {
            interleave7(jArr, r7, jArr2, r9, r10);
        }
    }

    private static long interleave2_32to64(int r6) {
        short[] sArr = INTERLEAVE2_TABLE;
        int r1 = sArr[r6 & 255] | (sArr[(r6 >>> 8) & 255] << 16);
        short s = sArr[(r6 >>> 16) & 255];
        return (r1 & BodyPartID.bodyIdMax) | ((((sArr[r6 >>> 24] << 16) | s) & BodyPartID.bodyIdMax) << 32);
    }

    private static long interleave2_n(long j, int r10) {
        while (r10 > 1) {
            r10 -= 2;
            j = (interleave4_16to64(((int) (j >>> 48)) & 65535) << 3) | (interleave4_16to64(((int) (j >>> 16)) & 65535) << 1) | interleave4_16to64(((int) j) & 65535) | (interleave4_16to64(((int) (j >>> 32)) & 65535) << 2);
        }
        if (r10 > 0) {
            return (interleave2_32to64((int) (j >>> 32)) << 1) | interleave2_32to64((int) j);
        }
        return j;
    }

    private static void interleave2_n(long[] jArr, int r5, long[] jArr2, int r7, int r8, int r9) {
        for (int r0 = 0; r0 < r8; r0++) {
            jArr2[r7 + r0] = interleave2_n(jArr[r5 + r0], r9);
        }
    }

    private static long interleave3(long j) {
        return (interleave3_21to63(((int) (j >>> 42)) & 2097151) << 2) | (Long.MIN_VALUE & j) | interleave3_21to63(((int) j) & 2097151) | (interleave3_21to63(((int) (j >>> 21)) & 2097151) << 1);
    }

    private static void interleave3(long[] jArr, int r5, long[] jArr2, int r7, int r8) {
        for (int r0 = 0; r0 < r8; r0++) {
            jArr2[r7 + r0] = interleave3(jArr[r5 + r0]);
        }
    }

    private static long interleave3_13to65(int r6) {
        int[] r0 = INTERLEAVE5_TABLE;
        return (r0[r6 & 127] & BodyPartID.bodyIdMax) | ((r0[r6 >>> 7] & BodyPartID.bodyIdMax) << 35);
    }

    private static long interleave3_21to63(int r9) {
        int[] r0 = INTERLEAVE3_TABLE;
        int r1 = r0[r9 & 127];
        int r2 = r0[(r9 >>> 7) & 127];
        return (r1 & BodyPartID.bodyIdMax) | ((r0[r9 >>> 14] & BodyPartID.bodyIdMax) << 42) | ((r2 & BodyPartID.bodyIdMax) << 21);
    }

    private static long interleave4_16to64(int r6) {
        int[] r0 = INTERLEAVE4_TABLE;
        return (r0[r6 & 255] & BodyPartID.bodyIdMax) | ((r0[r6 >>> 8] & BodyPartID.bodyIdMax) << 32);
    }

    private static long interleave5(long j) {
        return (interleave3_13to65(((int) (j >>> 52)) & 8191) << 4) | interleave3_13to65(((int) j) & 8191) | (interleave3_13to65(((int) (j >>> 13)) & 8191) << 1) | (interleave3_13to65(((int) (j >>> 26)) & 8191) << 2) | (interleave3_13to65(((int) (j >>> 39)) & 8191) << 3);
    }

    private static void interleave5(long[] jArr, int r5, long[] jArr2, int r7, int r8) {
        for (int r0 = 0; r0 < r8; r0++) {
            jArr2[r7 + r0] = interleave5(jArr[r5 + r0]);
        }
    }

    private static long interleave7(long j) {
        long[] jArr = INTERLEAVE7_TABLE;
        return (jArr[((int) (j >>> 54)) & FrameMetricsAggregator.EVERY_DURATION] << 6) | (Long.MIN_VALUE & j) | jArr[((int) j) & FrameMetricsAggregator.EVERY_DURATION] | (jArr[((int) (j >>> 9)) & FrameMetricsAggregator.EVERY_DURATION] << 1) | (jArr[((int) (j >>> 18)) & FrameMetricsAggregator.EVERY_DURATION] << 2) | (jArr[((int) (j >>> 27)) & FrameMetricsAggregator.EVERY_DURATION] << 3) | (jArr[((int) (j >>> 36)) & FrameMetricsAggregator.EVERY_DURATION] << 4) | (jArr[((int) (j >>> 45)) & FrameMetricsAggregator.EVERY_DURATION] << 5);
    }

    private static void interleave7(long[] jArr, int r5, long[] jArr2, int r7, int r8) {
        for (int r0 = 0; r0 < r8; r0++) {
            jArr2[r7 + r0] = interleave7(jArr[r5 + r0]);
        }
    }

    private static void multiplyWord(long j, long[] jArr, int r21, long[] jArr2, int r23) {
        if ((j & 1) != 0) {
            add(jArr2, r23, jArr, 0, r21);
        }
        int r15 = 1;
        long j2 = j;
        while (true) {
            long j3 = j2 >>> 1;
            if (j3 == 0) {
                return;
            }
            if ((j3 & 1) != 0) {
                long addShiftedUp = addShiftedUp(jArr2, r23, jArr, 0, r21, r15);
                if (addShiftedUp != 0) {
                    int r2 = r23 + r21;
                    jArr2[r2] = addShiftedUp ^ jArr2[r2];
                }
            }
            r15++;
            j2 = j3;
        }
    }

    private static void reduceBit(long[] jArr, int r2, int r3, int r4, int[] r5) {
        flipBit(jArr, r2, r3);
        int r32 = r3 - r4;
        int length = r5.length;
        while (true) {
            length--;
            if (length < 0) {
                flipBit(jArr, r2, r32);
                return;
            }
            flipBit(jArr, r2, r5[length] + r32);
        }
    }

    private static void reduceBitWise(long[] jArr, int r2, int r3, int r4, int[] r5) {
        while (true) {
            r3--;
            if (r3 < r4) {
                return;
            }
            if (testBit(jArr, r2, r3)) {
                reduceBit(jArr, r2, r3, r4, r5);
            }
        }
    }

    private static int reduceInPlace(long[] jArr, int r15, int r16, int r17, int[] r18) {
        int r10 = (r17 + 63) >>> 6;
        if (r16 < r10) {
            return r16;
        }
        int r1 = r16 << 6;
        int min = Math.min(r1, (r17 << 1) - 1);
        int r12 = r1 - min;
        int r11 = r16;
        while (r12 >= 64) {
            r11--;
            r12 -= 64;
        }
        int length = r18.length;
        int r5 = r18[length - 1];
        int r4 = length > 1 ? r18[length - 2] : 0;
        int max = Math.max(r17, r5 + 64);
        int min2 = (r12 + Math.min(min - max, r17 - r4)) >> 6;
        if (min2 > 1) {
            int r13 = r11 - min2;
            reduceVectorWise(jArr, r15, r11, r13, r17, r18);
            while (r11 > r13) {
                r11--;
                jArr[r15 + r11] = 0;
            }
            min = r13 << 6;
        }
        if (min > max) {
            reduceWordWise(jArr, r15, r11, max, r17, r18);
        } else {
            max = min;
        }
        if (max > r17) {
            reduceBitWise(jArr, r15, max, r17, r18);
        }
        return r10;
    }

    private static LongArray reduceResult(long[] jArr, int r1, int r2, int r3, int[] r4) {
        return new LongArray(jArr, r1, reduceInPlace(jArr, r1, r2, r3, r4));
    }

    private static void reduceVectorWise(long[] jArr, int r8, int r9, int r10, int r11, int[] r12) {
        int r6 = (r10 << 6) - r11;
        int length = r12.length;
        while (true) {
            length--;
            if (length < 0) {
                flipVector(jArr, r8, jArr, r8 + r10, r9 - r10, r6);
                return;
            }
            flipVector(jArr, r8, jArr, r8 + r10, r9 - r10, r6 + r12[length]);
        }
    }

    private static void reduceWord(long[] jArr, int r2, int r3, long j, int r6, int[] r7) {
        int r32 = r3 - r6;
        int length = r7.length;
        while (true) {
            length--;
            if (length < 0) {
                flipWord(jArr, r2, r32, j);
                return;
            }
            flipWord(jArr, r2, r7[length] + r32, j);
        }
    }

    private static void reduceWordWise(long[] jArr, int r10, int r11, int r12, int r13, int[] r14) {
        int r7 = r12 >>> 6;
        int r0 = r11;
        while (true) {
            int r8 = r0 - 1;
            if (r8 <= r7) {
                break;
            }
            int r2 = r10 + r8;
            long j = jArr[r2];
            if (j != 0) {
                jArr[r2] = 0;
                reduceWord(jArr, r10, r8 << 6, j, r13, r14);
            }
            r0 = r8;
        }
        int r22 = r12 & 63;
        int r3 = r10 + r7;
        long j2 = jArr[r3] >>> r22;
        if (j2 != 0) {
            jArr[r3] = jArr[r3] ^ (j2 << r22);
            reduceWord(jArr, r10, r12, j2, r13, r14);
        }
    }

    private long[] resizedInts(int r4) {
        long[] jArr = new long[r4];
        long[] jArr2 = this.m_ints;
        System.arraycopy(jArr2, 0, jArr, 0, Math.min(jArr2.length, r4));
        return jArr;
    }

    private static long shiftUp(long[] jArr, int r10, int r11, int r12) {
        int r0 = 64 - r12;
        long j = 0;
        for (int r3 = 0; r3 < r11; r3++) {
            int r4 = r10 + r3;
            long j2 = jArr[r4];
            jArr[r4] = j | (j2 << r12);
            j = j2 >>> r0;
        }
        return j;
    }

    private static long shiftUp(long[] jArr, int r10, long[] jArr2, int r12, int r13, int r14) {
        int r0 = 64 - r14;
        long j = 0;
        for (int r3 = 0; r3 < r13; r3++) {
            long j2 = jArr[r10 + r3];
            jArr2[r12 + r3] = j | (j2 << r14);
            j = j2 >>> r0;
        }
        return j;
    }

    private static void squareInPlace(long[] jArr, int r5, int r6, int[] r7) {
        int r62 = r5 << 1;
        while (true) {
            r5--;
            if (r5 < 0) {
                return;
            }
            long j = jArr[r5];
            int r63 = r62 - 1;
            jArr[r63] = interleave2_32to64((int) (j >>> 32));
            r62 = r63 - 1;
            jArr[r62] = interleave2_32to64((int) j);
        }
    }

    private static boolean testBit(long[] jArr, int r4, int r5) {
        return (jArr[r4 + (r5 >>> 6)] & (1 << (r5 & 63))) != 0;
    }

    public LongArray addOne() {
        if (this.m_ints.length == 0) {
            return new LongArray(new long[]{1});
        }
        long[] resizedInts = resizedInts(Math.max(1, getUsedLength()));
        resizedInts[0] = 1 ^ resizedInts[0];
        return new LongArray(resizedInts);
    }

    public void addShiftedByWords(LongArray longArray, int r5) {
        int usedLength = longArray.getUsedLength();
        if (usedLength == 0) {
            return;
        }
        int r1 = usedLength + r5;
        if (r1 > this.m_ints.length) {
            this.m_ints = resizedInts(r1);
        }
        add(this.m_ints, r5, longArray.m_ints, 0, usedLength);
    }

    public Object clone() {
        return new LongArray(Arrays.clone(this.m_ints));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void copyTo(long[] jArr, int r5) {
        long[] jArr2 = this.m_ints;
        System.arraycopy(jArr2, 0, jArr, r5, jArr2.length);
    }

    public int degree() {
        int length = this.m_ints.length;
        while (length != 0) {
            length--;
            long j = this.m_ints[length];
            if (j != 0) {
                return (length << 6) + bitLength(j);
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LongArray) {
            LongArray longArray = (LongArray) obj;
            int usedLength = getUsedLength();
            if (longArray.getUsedLength() != usedLength) {
                return false;
            }
            for (int r2 = 0; r2 < usedLength; r2++) {
                if (this.m_ints[r2] != longArray.m_ints[r2]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int getLength() {
        return this.m_ints.length;
    }

    public int getUsedLength() {
        return getUsedLengthFrom(this.m_ints.length);
    }

    public int getUsedLengthFrom(int r9) {
        long[] jArr = this.m_ints;
        int min = Math.min(r9, jArr.length);
        if (min < 1) {
            return 0;
        }
        if (jArr[0] != 0) {
            do {
                min--;
            } while (jArr[min] == 0);
            return min + 1;
        }
        do {
            min--;
            if (jArr[min] != 0) {
                return min + 1;
            }
        } while (min > 0);
        return 0;
    }

    public int hashCode() {
        int usedLength = getUsedLength();
        int r1 = 1;
        for (int r2 = 0; r2 < usedLength; r2++) {
            long j = this.m_ints[r2];
            r1 = (((r1 * 31) ^ ((int) j)) * 31) ^ ((int) (j >>> 32));
        }
        return r1;
    }

    public boolean isOne() {
        long[] jArr = this.m_ints;
        if (jArr[0] != 1) {
            return false;
        }
        for (int r3 = 1; r3 < jArr.length; r3++) {
            if (jArr[r3] != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isZero() {
        for (long j : this.m_ints) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    public LongArray modInverse(int r12, int[] r13) {
        int degree = degree();
        if (degree == 0) {
            throw new IllegalStateException();
        }
        int r1 = 1;
        if (degree == 1) {
            return this;
        }
        int r3 = (r12 + 63) >>> 6;
        LongArray longArray = new LongArray(r3);
        reduceBit(longArray.m_ints, 0, r12, r12, r13);
        LongArray longArray2 = new LongArray(r3);
        longArray2.m_ints[0] = 1;
        LongArray longArray3 = new LongArray(r3);
        int[] r7 = new int[2];
        r7[0] = degree;
        r7[1] = r12 + 1;
        LongArray[] longArrayArr = {(LongArray) clone(), longArray};
        int[] r0 = {1, 0};
        LongArray[] longArrayArr2 = {longArray2, longArray3};
        int r132 = r7[1];
        int r32 = r0[1];
        int r4 = r132 - r7[0];
        while (true) {
            if (r4 < 0) {
                r4 = -r4;
                r7[r1] = r132;
                r0[r1] = r32;
                int r133 = 1 - r1;
                int r14 = r7[r133];
                r32 = r0[r133];
                r1 = r133;
                r132 = r14;
            }
            int r6 = 1 - r1;
            longArrayArr[r1].addShiftedByBitsSafe(longArrayArr[r6], r7[r6], r4);
            int degreeFrom = longArrayArr[r1].degreeFrom(r132);
            if (degreeFrom == 0) {
                return longArrayArr2[r6];
            }
            int r8 = r0[r6];
            longArrayArr2[r1].addShiftedByBitsSafe(longArrayArr2[r6], r8, r4);
            int r82 = r8 + r4;
            if (r82 > r32) {
                r32 = r82;
            } else if (r82 == r32) {
                r32 = longArrayArr2[r1].degreeFrom(r32);
            }
            r4 += degreeFrom - r132;
            r132 = degreeFrom;
        }
    }

    public LongArray modMultiply(LongArray longArray, int r25, int[] r26) {
        int r4;
        int r5;
        LongArray longArray2;
        LongArray longArray3;
        int r7;
        long[] jArr;
        int r9;
        int degree = degree();
        if (degree == 0) {
            return this;
        }
        int degree2 = longArray.degree();
        if (degree2 == 0) {
            return longArray;
        }
        if (degree > degree2) {
            r5 = degree;
            r4 = degree2;
            longArray3 = this;
            longArray2 = longArray;
        } else {
            r4 = degree;
            r5 = degree2;
            longArray2 = this;
            longArray3 = longArray;
        }
        int r6 = (r4 + 63) >>> 6;
        int r11 = (r5 + 63) >>> 6;
        int r42 = ((r4 + r5) + 62) >>> 6;
        if (r6 == 1) {
            long j = longArray2.m_ints[0];
            if (j == 1) {
                return longArray3;
            }
            long[] jArr2 = new long[r42];
            multiplyWord(j, longArray3.m_ints, r11, jArr2, 0);
            return reduceResult(jArr2, 0, r42, r25, r26);
        }
        int r52 = ((r5 + 7) + 63) >>> 6;
        int[] r10 = new int[16];
        int r15 = r52 << 4;
        long[] jArr3 = new long[r15];
        r10[1] = r52;
        System.arraycopy(longArray3.m_ints, 0, jArr3, r52, r11);
        int r2 = 2;
        int r8 = r52;
        for (int r92 = 16; r2 < r92; r92 = 16) {
            r8 += r52;
            r10[r2] = r8;
            if ((r2 & 1) == 0) {
                jArr = jArr3;
                r9 = r15;
                shiftUp(jArr3, r8 >>> 1, jArr3, r8, r52, 1);
            } else {
                jArr = jArr3;
                r9 = r15;
                add(jArr, r52, jArr3, r8 - r52, jArr, r8, r52);
            }
            r2++;
            r15 = r9;
            jArr3 = jArr;
        }
        long[] jArr4 = jArr3;
        int r93 = r15;
        long[] jArr5 = new long[r93];
        shiftUp(jArr4, 0, jArr5, 0, r93, 4);
        long[] jArr6 = longArray2.m_ints;
        int r82 = r42 << 3;
        long[] jArr7 = new long[r82];
        int r152 = 0;
        while (r152 < r6) {
            long j2 = jArr6[r152];
            int r19 = r152;
            while (true) {
                long j3 = j2 >>> 4;
                r7 = r152;
                addBoth(jArr7, r19, jArr4, r10[((int) j2) & 15], jArr5, r10[((int) j3) & 15], r52);
                j2 = j3 >>> 4;
                if (j2 == 0) {
                    break;
                }
                r19 += r42;
                r152 = r7;
            }
            r152 = r7 + 1;
        }
        while (true) {
            r82 -= r42;
            if (r82 == 0) {
                return reduceResult(jArr7, 0, r42, r25, r26);
            }
            addShiftedUp(jArr7, r82 - r42, jArr7, r82, r42, 8);
        }
    }

    public LongArray modMultiplyAlt(LongArray longArray, int r25, int[] r26) {
        int r4;
        int r5;
        LongArray longArray2;
        LongArray longArray3;
        int r12;
        int r122;
        int degree = degree();
        if (degree == 0) {
            return this;
        }
        int degree2 = longArray.degree();
        if (degree2 == 0) {
            return longArray;
        }
        if (degree > degree2) {
            r5 = degree;
            r4 = degree2;
            longArray3 = this;
            longArray2 = longArray;
        } else {
            r4 = degree;
            r5 = degree2;
            longArray2 = this;
            longArray3 = longArray;
        }
        int r6 = (r4 + 63) >>> 6;
        int r13 = (r5 + 63) >>> 6;
        int r42 = ((r4 + r5) + 62) >>> 6;
        if (r6 == 1) {
            long j = longArray2.m_ints[0];
            if (j == 1) {
                return longArray3;
            }
            long[] jArr = new long[r42];
            multiplyWord(j, longArray3.m_ints, r13, jArr, 0);
            return reduceResult(jArr, 0, r42, r25, r26);
        }
        int r16 = 15;
        int r52 = ((r5 + 15) + 63) >>> 6;
        int r10 = r52 * 8;
        int[] r8 = new int[16];
        r8[0] = r6;
        int r17 = r6 + r10;
        r8[1] = r17;
        int r7 = 2;
        while (true) {
            r17 += r42;
            if (r7 >= 16) {
                break;
            }
            r8[r7] = r17;
            r7++;
        }
        long[] jArr2 = new long[r17 + 1];
        int r22 = r10;
        interleave(longArray2.m_ints, 0, jArr2, 0, r6, 4);
        System.arraycopy(longArray3.m_ints, 0, jArr2, r6, r13);
        int r72 = r6;
        int r3 = 1;
        while (r3 < 8) {
            int r18 = r72 + r52;
            shiftUp(jArr2, r6, jArr2, r18, r52, r3);
            r3++;
            r72 = r18;
        }
        int r73 = 0;
        while (true) {
            int r32 = 0;
            do {
                int r11 = r6;
                long j2 = jArr2[r32] >>> r73;
                int r82 = 0;
                while (true) {
                    int r123 = ((int) j2) & r16;
                    if (r123 != 0) {
                        add(jArr2, r8[r123] + r32, jArr2, r11, r52);
                    }
                    r12 = 1;
                    r82++;
                    if (r82 == 8) {
                        break;
                    }
                    r11 += r52;
                    j2 >>>= 4;
                }
                r32++;
            } while (r32 < r6);
            r73 += 32;
            if (r73 < 64) {
                r122 = r22;
            } else if (r73 >= 64) {
                break;
            } else {
                r16 &= r16 << 4;
                r122 = r22;
                r73 = 60;
            }
            shiftUp(jArr2, r6, r122, 8);
            r22 = r122;
        }
        int r9 = 16;
        while (true) {
            int r33 = r9 - 1;
            if (r33 <= r12) {
                return reduceResult(jArr2, r8[1], r42, r25, r26);
            }
            if ((r33 & 1) == 0) {
                addShiftedUp(jArr2, r8[r33 >>> 1], jArr2, r8[r33], r42, 16);
            } else {
                distribute(jArr2, r8[r33], r8[r33 - 1], r8[1], r42);
            }
            r9 = r33;
            r12 = 1;
        }
    }

    public LongArray modMultiplyLD(LongArray longArray, int r24, int[] r25) {
        int r4;
        int r5;
        LongArray longArray2;
        LongArray longArray3;
        long[] jArr;
        int r8;
        int degree = degree();
        if (degree == 0) {
            return this;
        }
        int degree2 = longArray.degree();
        if (degree2 == 0) {
            return longArray;
        }
        if (degree > degree2) {
            r5 = degree;
            r4 = degree2;
            longArray3 = this;
            longArray2 = longArray;
        } else {
            r4 = degree;
            r5 = degree2;
            longArray2 = this;
            longArray3 = longArray;
        }
        int r6 = (r4 + 63) >>> 6;
        int r11 = (r5 + 63) >>> 6;
        int r42 = ((r4 + r5) + 62) >>> 6;
        if (r6 == 1) {
            long j = longArray2.m_ints[0];
            if (j == 1) {
                return longArray3;
            }
            long[] jArr2 = new long[r42];
            multiplyWord(j, longArray3.m_ints, r11, jArr2, 0);
            return reduceResult(jArr2, 0, r42, r24, r25);
        }
        int r52 = ((r5 + 7) + 63) >>> 6;
        int[] r10 = new int[16];
        int r15 = r52 << 4;
        long[] jArr3 = new long[r15];
        r10[1] = r52;
        System.arraycopy(longArray3.m_ints, 0, jArr3, r52, r11);
        int r2 = 2;
        int r112 = r52;
        while (r2 < 16) {
            r112 += r52;
            r10[r2] = r112;
            if ((r2 & 1) == 0) {
                jArr = jArr3;
                r8 = r15;
                shiftUp(jArr3, r112 >>> 1, jArr3, r112, r52, 1);
            } else {
                jArr = jArr3;
                r8 = r15;
                add(jArr, r52, jArr3, r112 - r52, jArr, r112, r52);
            }
            r2++;
            r15 = r8;
            jArr3 = jArr;
        }
        long[] jArr4 = jArr3;
        int r82 = r15;
        long[] jArr5 = new long[r82];
        shiftUp(jArr4, 0, jArr5, 0, r82, 4);
        long[] jArr6 = longArray2.m_ints;
        long[] jArr7 = new long[r42];
        for (int r113 = 56; r113 >= 0; r113 -= 8) {
            for (int r14 = 1; r14 < r6; r14 += 2) {
                int r13 = (int) (jArr6[r14] >>> r113);
                addBoth(jArr7, r14 - 1, jArr4, r10[r13 & 15], jArr5, r10[(r13 >>> 4) & 15], r52);
            }
            shiftUp(jArr7, 0, r42, 8);
        }
        for (int r21 = 56; r21 >= 0; r21 -= 8) {
            for (int r114 = 0; r114 < r6; r114 += 2) {
                int r132 = (int) (jArr6[r114] >>> r21);
                addBoth(jArr7, r114, jArr4, r10[r132 & 15], jArr5, r10[(r132 >>> 4) & 15], r52);
            }
            if (r21 > 0) {
                shiftUp(jArr7, 0, r42, 8);
            }
        }
        return reduceResult(jArr7, 0, r42, r24, r25);
    }

    public LongArray modReduce(int r4, int[] r5) {
        long[] clone = Arrays.clone(this.m_ints);
        return new LongArray(clone, 0, reduceInPlace(clone, 0, clone.length, r4, r5));
    }

    public LongArray modSquare(int r10, int[] r11) {
        int usedLength = getUsedLength();
        if (usedLength == 0) {
            return this;
        }
        int r0 = usedLength << 1;
        long[] jArr = new long[r0];
        int r3 = 0;
        while (r3 < r0) {
            long j = this.m_ints[r3 >>> 1];
            int r4 = r3 + 1;
            jArr[r3] = interleave2_32to64((int) j);
            r3 = r4 + 1;
            jArr[r4] = interleave2_32to64((int) (j >>> 32));
        }
        return new LongArray(jArr, 0, reduceInPlace(jArr, 0, r0, r10, r11));
    }

    public LongArray modSquareN(int r6, int r7, int[] r8) {
        int usedLength = getUsedLength();
        if (usedLength == 0) {
            return this;
        }
        int r1 = ((r7 + 63) >>> 6) << 1;
        long[] jArr = new long[r1];
        System.arraycopy(this.m_ints, 0, jArr, 0, usedLength);
        while (true) {
            r6--;
            if (r6 < 0) {
                return new LongArray(jArr, 0, usedLength);
            }
            squareInPlace(jArr, usedLength, r7, r8);
            usedLength = reduceInPlace(jArr, 0, r1, r7, r8);
        }
    }

    public LongArray multiply(LongArray longArray, int r24, int[] r25) {
        int r2;
        int r3;
        LongArray longArray2;
        LongArray longArray3;
        long[] jArr;
        int r7;
        int degree = degree();
        if (degree == 0) {
            return this;
        }
        int degree2 = longArray.degree();
        if (degree2 == 0) {
            return longArray;
        }
        if (degree > degree2) {
            r3 = degree;
            r2 = degree2;
            longArray3 = this;
            longArray2 = longArray;
        } else {
            r2 = degree;
            r3 = degree2;
            longArray2 = this;
            longArray3 = longArray;
        }
        int r4 = (r2 + 63) >>> 6;
        int r9 = (r3 + 63) >>> 6;
        int r22 = ((r2 + r3) + 62) >>> 6;
        if (r4 == 1) {
            long j = longArray2.m_ints[0];
            if (j == 1) {
                return longArray3;
            }
            long[] jArr2 = new long[r22];
            multiplyWord(j, longArray3.m_ints, r9, jArr2, 0);
            return new LongArray(jArr2, 0, r22);
        }
        int r32 = ((r3 + 7) + 63) >>> 6;
        int[] r8 = new int[16];
        int r15 = r32 << 4;
        long[] jArr3 = new long[r15];
        r8[1] = r32;
        System.arraycopy(longArray3.m_ints, 0, jArr3, r32, r9);
        int r0 = 2;
        int r6 = r32;
        for (int r72 = 16; r0 < r72; r72 = 16) {
            r6 += r32;
            r8[r0] = r6;
            if ((r0 & 1) == 0) {
                jArr = jArr3;
                r7 = r15;
                shiftUp(jArr3, r6 >>> 1, jArr3, r6, r32, 1);
            } else {
                jArr = jArr3;
                r7 = r15;
                add(jArr, r32, jArr, r6 - r32, jArr3, r6, r32);
            }
            r0++;
            r15 = r7;
            jArr3 = jArr;
        }
        long[] jArr4 = jArr3;
        int r73 = r15;
        long[] jArr5 = new long[r73];
        shiftUp(jArr4, 0, jArr5, 0, r73, 4);
        long[] jArr6 = longArray2.m_ints;
        int r62 = r22 << 3;
        long[] jArr7 = new long[r62];
        for (int r92 = 0; r92 < r4; r92++) {
            long j2 = jArr6[r92];
            int r18 = r92;
            while (true) {
                long j3 = j2 >>> 4;
                addBoth(jArr7, r18, jArr4, r8[((int) j2) & 15], jArr5, r8[((int) j3) & 15], r32);
                j2 = j3 >>> 4;
                if (j2 == 0) {
                    break;
                }
                r18 += r22;
            }
        }
        while (true) {
            r62 -= r22;
            if (r62 == 0) {
                return new LongArray(jArr7, 0, r22);
            }
            addShiftedUp(jArr7, r62 - r22, jArr7, r62, r22, 8);
        }
    }

    public void reduce(int r4, int[] r5) {
        long[] jArr = this.m_ints;
        int reduceInPlace = reduceInPlace(jArr, 0, jArr.length, r4, r5);
        if (reduceInPlace < jArr.length) {
            long[] jArr2 = new long[reduceInPlace];
            this.m_ints = jArr2;
            System.arraycopy(jArr, 0, jArr2, 0, reduceInPlace);
        }
    }

    public LongArray square(int r8, int[] r9) {
        int usedLength = getUsedLength();
        if (usedLength == 0) {
            return this;
        }
        int r82 = usedLength << 1;
        long[] jArr = new long[r82];
        int r1 = 0;
        while (r1 < r82) {
            long j = this.m_ints[r1 >>> 1];
            int r2 = r1 + 1;
            jArr[r1] = interleave2_32to64((int) j);
            r1 = r2 + 1;
            jArr[r2] = interleave2_32to64((int) (j >>> 32));
        }
        return new LongArray(jArr, 0, r82);
    }

    public boolean testBitZero() {
        long[] jArr = this.m_ints;
        return jArr.length > 0 && (1 & jArr[0]) != 0;
    }

    public BigInteger toBigInteger() {
        int usedLength = getUsedLength();
        if (usedLength == 0) {
            return ECConstants.ZERO;
        }
        int r2 = usedLength - 1;
        long j = this.m_ints[r2];
        byte[] bArr = new byte[8];
        int r9 = 0;
        boolean z = false;
        for (int r8 = 7; r8 >= 0; r8--) {
            byte b = (byte) (j >>> (r8 * 8));
            if (z || b != 0) {
                bArr[r9] = b;
                r9++;
                z = true;
            }
        }
        byte[] bArr2 = new byte[(r2 * 8) + r9];
        for (int r7 = 0; r7 < r9; r7++) {
            bArr2[r7] = bArr[r7];
        }
        for (int r0 = usedLength - 2; r0 >= 0; r0--) {
            long j2 = this.m_ints[r0];
            int r22 = 7;
            while (r22 >= 0) {
                bArr2[r9] = (byte) (j2 >>> (r22 * 8));
                r22--;
                r9++;
            }
        }
        return new BigInteger(1, bArr2);
    }

    public String toString() {
        int usedLength = getUsedLength();
        if (usedLength == 0) {
            return SessionDescription.SUPPORTED_SDP_VERSION;
        }
        int r0 = usedLength - 1;
        StringBuffer stringBuffer = new StringBuffer(Long.toBinaryString(this.m_ints[r0]));
        while (true) {
            r0--;
            if (r0 < 0) {
                return stringBuffer.toString();
            }
            String binaryString = Long.toBinaryString(this.m_ints[r0]);
            int length = binaryString.length();
            if (length < 64) {
                stringBuffer.append(ZEROES.substring(length));
            }
            stringBuffer.append(binaryString);
        }
    }
}
