package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.facebook.imageutils.JfifUtil;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.lang.reflect.Array;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class AESEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: m1 */
    private static final int f1871m1 = -2139062144;

    /* renamed from: m2 */
    private static final int f1872m2 = 2139062143;

    /* renamed from: m3 */
    private static final int f1873m3 = 27;

    /* renamed from: m4 */
    private static final int f1874m4 = -1061109568;

    /* renamed from: m5 */
    private static final int f1875m5 = 1061109567;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;

    /* renamed from: s */
    private byte[] f1876s;

    /* renamed from: S */
    private static final byte[] f1868S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, 16, -1, -13, -46, -51, Ascii.f1121FF, 19, -20, 95, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.f1120EM, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.f1132VT, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, Ascii.f1122FS, -90, -76, -58, -24, -35, 116, Ascii.f1131US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.f1129SO, 97, 53, 87, -71, -122, -63, Ascii.f1123GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.f1127RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, MultipartStream.DASH, Ascii.f1128SI, -80, 84, -69, Ascii.SYN};

    /* renamed from: Si */
    private static final byte[] f1869Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, Ascii.f1132VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, Ascii.f1127RS, -113, -54, Utf8.REPLACEMENT_BYTE, Ascii.f1128SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.f1122FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.f1123GS, 41, -59, -119, 111, -73, 98, Ascii.f1129SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, Ascii.f1131US, -35, -88, 51, -120, 7, -57, 49, -79, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.f1120EM, -75, 74, 13, MultipartStream.DASH, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, 33, Ascii.f1121FF, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, JfifUtil.MARKER_SOI, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, 145};

    /* renamed from: T0 */
    private static final int[] f1870T0 = {-1520213050, -2072216328, -1720223762, -1921287178, 234025727, -1117033514, -1318096930, 1422247313, 1345335392, 50397442, -1452841010, 2099981142, 436141799, 1658312629, -424957107, -1703512340, 1170918031, -1652391393, 1086966153, -2021818886, 368769775, -346465870, -918075506, 200339707, -324162239, 1742001331, -39673249, -357585083, -1080255453, -140204973, -1770884380, 1539358875, -1028147339, 486407649, -1366060227, 1780885068, 1513502316, 1094664062, 49805301, 1338821763, 1546925160, -190470831, 887481809, 150073849, -1821281822, 1943591083, 1395732834, 1058346282, 201589768, 1388824469, 1696801606, 1589887901, 672667696, -1583966665, 251987210, -1248159185, 151455502, 907153956, -1686077413, 1038279391, 652995533, 1764173646, -843926913, -1619692054, 453576978, -1635548387, 1949051992, 773462580, 756751158, -1301385508, -296068428, -73359269, -162377052, 1295727478, 1641469623, -827083907, 2066295122, 1055122397, 1898917726, -1752923117, -179088474, 1758581177, 0, 753790401, 1612718144, 536673507, -927878791, -312779850, -1100322092, 1187761037, -641810841, 1262041458, -565556588, -733197160, -396863312, 1255133061, 1808847035, 720367557, -441800113, 385612781, -985447546, -682799718, 1429418854, -1803188975, -817543798, 284817897, 100794884, -2122350594, -263171936, 1144798328, -1163944155, -475486133, -212774494, -22830243, -1069531008, -1970303227, -1382903233, -1130521311, 1211644016, 83228145, -541279133, -1044990345, 1977277103, 1663115586, 806359072, 452984805, 250868733, 1842533055, 1288555905, 336333848, 890442534, 804056259, -513843266, -1567123659, -867941240, 957814574, 1472513171, -223893675, -2105639172, 1195195770, -1402706744, -413311558, 723065138, -1787595802, -1604296512, -1736343271, -783331426, 2145180835, 1713513028, 2116692564, -1416589253, -2088204277, -901364084, 703524551, -742868885, 1007948840, 2044649127, -497131844, 487262998, 1994120109, 1004593371, 1446130276, 1312438900, 503974420, -615954030, 168166924, 1814307912, -463709000, 1573044895, 1859376061, -273896381, -1503501628, -1466855111, -1533700815, 937747667, -1954973198, 854058965, 1137232011, 1496790894, -1217565222, -1936880383, 1691735473, -766620004, -525751991, -1267962664, -95005012, 133494003, 636152527, -1352309302, -1904575756, -374428089, 403179536, -709182865, -2005370640, 1864705354, 1915629148, 605822008, -240736681, -944458637, 1371981463, 602466507, 2094914977, -1670089496, 555687742, -582268010, -591544991, -2037675251, -2054518257, -1871679264, 1111375484, -994724495, -1436129588, -666351472, 84083462, 32962295, 302911004, -1553899070, 1597322602, -111716434, -793134743, -1853454825, 1489093017, 656219450, -1180787161, 954327513, 335083755, -1281845205, 856756514, -1150719534, 1893325225, -1987146233, -1483434957, -1231316179, 572399164, -1836611819, 552200649, 1238290055, -11184726, 2015897680, 2061492133, -1886614525, -123625127, -2138470135, 386731290, -624967835, 837215959, -968736124, -1201116976, -1019133566, -1332111063, 1999449434, 286199582, -877612933, -61582168, -692339859, 974525996};
    private static final int[] Tinv0 = {1353184337, 1399144830, -1012656358, -1772214470, -882136261, -247096033, -1420232020, -1828461749, 1442459680, -160598355, -1854485368, 625738485, -52959921, -674551099, -2143013594, -1885117771, 1230680542, 1729870373, -1743852987, -507445667, 41234371, 317738113, -1550367091, -956705941, -413167869, -1784901099, -344298049, -631680363, 763608788, -752782248, 694804553, 1154009486, 1787413109, 2021232372, 1799248025, -579749593, -1236278850, 397248752, 1722556617, -1271214467, 407560035, -2110711067, 1613975959, 1165972322, -529046351, -2068943941, 480281086, -1809118983, 1483229296, 436028815, -2022908268, -1208452270, 601060267, -503166094, 1468997603, 715871590, 120122290, 63092015, -1703164538, -1526188077, -226023376, -1297760477, -1167457534, 1552029421, 723308426, -1833666137, -252573709, -1578997426, -839591323, -708967162, 526529745, -1963022652, -1655493068, -1604979806, 853641733, 1978398372, 971801355, -1427152832, 111112542, 1360031421, -108388034, 1023860118, -1375387939, 1186850381, -1249028975, 90031217, 1876166148, -15380384, 620468249, -1746289194, -868007799, 2006899047, -1119688528, -2004121337, 945494503, -605108103, 1191869601, -384875908, -920746760, 0, -2088337399, 1223502642, -1401941730, 1316117100, -67170563, 1446544655, 517320253, 658058550, 1691946762, 564550760, -783000677, 976107044, -1318647284, 266819475, -761860428, -1634624741, 1338359936, -1574904735, 1766553434, 370807324, 179999714, -450191168, 1138762300, 488053522, 185403662, -1379431438, -1180125651, -928440812, -2061897385, 1275557295, -1143105042, -44007517, -1624899081, -1124765092, -985962940, 880737115, 1982415755, -590994485, 1761406390, 1676797112, -891538985, 277177154, 1076008723, 538035844, 2099530373, -130171950, 288553390, 1839278535, 1261411869, -214912292, -330136051, -790380169, 1813426987, -1715900247, -95906799, 577038663, -997393240, 440397984, -668172970, -275762398, -951170681, -1043253031, -22885748, 906744984, -813566554, 685669029, 646887386, -1530942145, -459458004, 227702864, -1681105046, 1648787028, -1038905866, -390539120, 1593260334, -173030526, -1098883681, 2090061929, -1456614033, -1290656305, 999926984, -1484974064, 1852021992, 2075868123, 158869197, -199730834, 28809964, -1466282109, 1701746150, 2129067946, 147831841, -420997649, -644094022, -835293366, -737566742, -696471511, -1347247055, 824393514, 815048134, -1067015627, 935087732, -1496677636, -1328508704, 366520115, 1251476721, -136647615, 240176511, 804688151, -1915335306, 1303441219, 1414376140, -553347356, -474623586, 461924940, -1205916479, 2136040774, 82468509, 1563790337, 1937016826, 776014843, 1511876531, 1389550482, 861278441, 323475053, -1939744870, 2047648055, -1911228327, -1992551445, -299390514, 902390199, -303751967, 1018251130, 1507840668, 1064563285, 2043548696, -1086863501, -355600557, 1537932639, 342834655, -2032450440, -2114736182, 1053059257, 741614648, 1598071746, 1925389590, 203809468, -1958134744, 1100287487, 1895934009, -558691320, -1662733096, -1866377628, 1636092795, 1890988757, 1952214088, 1113045200};

    private static int FFmulX(int r2) {
        return (((r2 & f1871m1) >>> 7) * 27) ^ ((f1872m2 & r2) << 1);
    }

    private static int FFmulX2(int r2) {
        int r22 = r2 & f1874m4;
        int r23 = r22 ^ (r22 >>> 1);
        return (r23 >>> 5) ^ (((f1875m5 & r2) << 2) ^ (r23 >>> 2));
    }

    private void decryptBlock(byte[] bArr, int r19, byte[] bArr2, int r21, int[][] r22) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, r19 + 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, r19 + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, r19 + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, r19 + 12);
        int r6 = this.ROUNDS;
        char c = 0;
        int r3 = littleEndianToInt ^ r22[r6][0];
        int r9 = 1;
        int r4 = littleEndianToInt2 ^ r22[r6][1];
        int r5 = littleEndianToInt3 ^ r22[r6][2];
        int r7 = r6 - 1;
        int r1 = littleEndianToInt4 ^ r22[r6][3];
        while (r7 > r9) {
            int[] r14 = Tinv0;
            int shift = (((shift(r14[(r1 >> 8) & 255], 24) ^ r14[r3 & 255]) ^ shift(r14[(r5 >> 16) & 255], 16)) ^ shift(r14[(r4 >> 24) & 255], 8)) ^ r22[r7][c];
            int shift2 = (((shift(r14[(r3 >> 8) & 255], 24) ^ r14[r4 & 255]) ^ shift(r14[(r1 >> 16) & 255], 16)) ^ shift(r14[(r5 >> 24) & 255], 8)) ^ r22[r7][r9];
            int shift3 = (((shift(r14[(r4 >> 8) & 255], 24) ^ r14[r5 & 255]) ^ shift(r14[(r3 >> 16) & 255], 16)) ^ shift(r14[(r1 >> 24) & 255], 8)) ^ r22[r7][2];
            int shift4 = ((r14[r1 & 255] ^ shift(r14[(r5 >> 8) & 255], 24)) ^ shift(r14[(r4 >> 16) & 255], 16)) ^ shift(r14[(r3 >> 24) & 255], 8);
            int r32 = r7 - 1;
            int r12 = shift4 ^ r22[r7][3];
            int shift5 = (((r14[shift & 255] ^ shift(r14[(r12 >> 8) & 255], 24)) ^ shift(r14[(shift3 >> 16) & 255], 16)) ^ shift(r14[(shift2 >> 24) & 255], 8)) ^ r22[r32][0];
            int shift6 = (((r14[shift2 & 255] ^ shift(r14[(shift >> 8) & 255], 24)) ^ shift(r14[(r12 >> 16) & 255], 16)) ^ shift(r14[(shift3 >> 24) & 255], 8)) ^ r22[r32][1];
            int r62 = r32 - 1;
            r1 = (((r14[r12 & 255] ^ shift(r14[(shift3 >> 8) & 255], 24)) ^ shift(r14[(shift2 >> 16) & 255], 16)) ^ shift(r14[(shift >> 24) & 255], 8)) ^ r22[r32][3];
            r3 = shift5;
            r4 = shift6;
            r5 = (((r14[shift3 & 255] ^ shift(r14[(shift2 >> 8) & 255], 24)) ^ shift(r14[(shift >> 16) & 255], 16)) ^ shift(r14[(r12 >> 24) & 255], 8)) ^ r22[r32][2];
            c = 0;
            r9 = 1;
            r7 = r62;
        }
        int[] r8 = Tinv0;
        int shift7 = (((r8[r3 & 255] ^ shift(r8[(r1 >> 8) & 255], 24)) ^ shift(r8[(r5 >> 16) & 255], 16)) ^ shift(r8[(r4 >> 24) & 255], 8)) ^ r22[r7][0];
        int shift8 = (((r8[r4 & 255] ^ shift(r8[(r3 >> 8) & 255], 24)) ^ shift(r8[(r1 >> 16) & 255], 16)) ^ shift(r8[(r5 >> 24) & 255], 8)) ^ r22[r7][1];
        int shift9 = (((r8[r5 & 255] ^ shift(r8[(r4 >> 8) & 255], 24)) ^ shift(r8[(r3 >> 16) & 255], 16)) ^ shift(r8[(r1 >> 24) & 255], 8)) ^ r22[r7][2];
        int shift10 = (((r8[r1 & 255] ^ shift(r8[(r5 >> 8) & 255], 24)) ^ shift(r8[(r4 >> 16) & 255], 16)) ^ shift(r8[(r3 >> 24) & 255], 8)) ^ r22[r7][3];
        byte[] bArr3 = f1869Si;
        byte[] bArr4 = this.f1876s;
        int r42 = ((((bArr3[shift7 & 255] & 255) ^ ((bArr4[(shift10 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(shift9 >> 16) & 255] & 255) << 16)) ^ (bArr3[(shift8 >> 24) & 255] << Ascii.CAN)) ^ r22[0][0];
        int r72 = ((((bArr4[shift8 & 255] & 255) ^ ((bArr4[(shift7 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(shift10 >> 16) & 255] & 255) << 16)) ^ (bArr4[(shift9 >> 24) & 255] << Ascii.CAN)) ^ r22[0][1];
        int r13 = ((((bArr3[shift10 & 255] & 255) ^ ((bArr4[(shift9 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(shift8 >> 16) & 255] & 255) << 16)) ^ (bArr4[(shift7 >> 24) & 255] << Ascii.CAN)) ^ r22[0][3];
        Pack.intToLittleEndian(r42, bArr2, r21 + 0);
        Pack.intToLittleEndian(r72, bArr2, r21 + 4);
        Pack.intToLittleEndian(((((bArr4[shift9 & 255] & 255) ^ ((bArr3[(shift8 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(shift7 >> 16) & 255] & 255) << 16)) ^ (bArr4[(shift10 >> 24) & 255] << Ascii.CAN)) ^ r22[0][2], bArr2, r21 + 8);
        Pack.intToLittleEndian(r13, bArr2, r21 + 12);
    }

    private void encryptBlock(byte[] bArr, int r19, byte[] bArr2, int r21, int[][] r22) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, r19 + 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, r19 + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, r19 + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, r19 + 12);
        char c = 0;
        int r3 = littleEndianToInt ^ r22[0][0];
        int r8 = 1;
        int r4 = littleEndianToInt2 ^ r22[0][1];
        int r5 = littleEndianToInt3 ^ r22[0][2];
        int r1 = littleEndianToInt4 ^ r22[0][3];
        int r7 = 1;
        while (r7 < this.ROUNDS - r8) {
            int[] r11 = f1870T0;
            int shift = (((shift(r11[(r4 >> 8) & 255], 24) ^ r11[r3 & 255]) ^ shift(r11[(r5 >> 16) & 255], 16)) ^ shift(r11[(r1 >> 24) & 255], 8)) ^ r22[r7][c];
            int shift2 = (((shift(r11[(r5 >> 8) & 255], 24) ^ r11[r4 & 255]) ^ shift(r11[(r1 >> 16) & 255], 16)) ^ shift(r11[(r3 >> 24) & 255], 8)) ^ r22[r7][r8];
            int shift3 = (((shift(r11[(r1 >> 8) & 255], 24) ^ r11[r5 & 255]) ^ shift(r11[(r3 >> 16) & 255], 16)) ^ shift(r11[(r4 >> 24) & 255], 8)) ^ r22[r7][2];
            int shift4 = ((r11[r1 & 255] ^ shift(r11[(r3 >> 8) & 255], 24)) ^ shift(r11[(r4 >> 16) & 255], 16)) ^ shift(r11[(r5 >> 24) & 255], 8);
            int r32 = r7 + 1;
            int r12 = shift4 ^ r22[r7][3];
            int shift5 = (((r11[shift & 255] ^ shift(r11[(shift2 >> 8) & 255], 24)) ^ shift(r11[(shift3 >> 16) & 255], 16)) ^ shift(r11[(r12 >> 24) & 255], 8)) ^ r22[r32][0];
            int shift6 = (((r11[shift2 & 255] ^ shift(r11[(shift3 >> 8) & 255], 24)) ^ shift(r11[(r12 >> 16) & 255], 16)) ^ shift(r11[(shift >> 24) & 255], 8)) ^ r22[r32][1];
            int shift7 = ((r11[r12 & 255] ^ shift(r11[(shift >> 8) & 255], 24)) ^ shift(r11[(shift2 >> 16) & 255], 16)) ^ shift(r11[(shift3 >> 24) & 255], 8);
            int r6 = r32 + 1;
            r1 = shift7 ^ r22[r32][3];
            r3 = shift5;
            r4 = shift6;
            r5 = (((r11[shift3 & 255] ^ shift(r11[(r12 >> 8) & 255], 24)) ^ shift(r11[(shift >> 16) & 255], 16)) ^ shift(r11[(shift2 >> 24) & 255], 8)) ^ r22[r32][2];
            r8 = 1;
            r7 = r6;
            c = 0;
        }
        int[] r62 = f1870T0;
        int shift8 = (((r62[r3 & 255] ^ shift(r62[(r4 >> 8) & 255], 24)) ^ shift(r62[(r5 >> 16) & 255], 16)) ^ shift(r62[(r1 >> 24) & 255], 8)) ^ r22[r7][0];
        int shift9 = (((r62[r4 & 255] ^ shift(r62[(r5 >> 8) & 255], 24)) ^ shift(r62[(r1 >> 16) & 255], 16)) ^ shift(r62[(r3 >> 24) & 255], 8)) ^ r22[r7][1];
        int shift10 = (((r62[r5 & 255] ^ shift(r62[(r1 >> 8) & 255], 24)) ^ shift(r62[(r3 >> 16) & 255], 16)) ^ shift(r62[(r4 >> 24) & 255], 8)) ^ r22[r7][2];
        int shift11 = ((r62[r1 & 255] ^ shift(r62[(r3 >> 8) & 255], 24)) ^ shift(r62[(r4 >> 16) & 255], 16)) ^ shift(r62[(r5 >> 24) & 255], 8);
        int r33 = r7 + 1;
        int r13 = shift11 ^ r22[r7][3];
        byte[] bArr3 = f1868S;
        int r52 = (bArr3[shift8 & 255] & 255) ^ ((bArr3[(shift9 >> 8) & 255] & 255) << 8);
        byte[] bArr4 = this.f1876s;
        int r53 = ((r52 ^ ((bArr4[(shift10 >> 16) & 255] & 255) << 16)) ^ (bArr4[(r13 >> 24) & 255] << Ascii.CAN)) ^ r22[r33][0];
        int r72 = ((((bArr4[shift9 & 255] & 255) ^ ((bArr3[(shift10 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r13 >> 16) & 255] & 255) << 16)) ^ (bArr4[(shift8 >> 24) & 255] << Ascii.CAN)) ^ r22[r33][1];
        int r9 = (bArr3[(r13 >> 8) & 255] & 255) << 8;
        int r14 = ((((bArr4[r13 & 255] & 255) ^ ((bArr4[(shift8 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(shift9 >> 16) & 255] & 255) << 16)) ^ (bArr3[(shift10 >> 24) & 255] << Ascii.CAN)) ^ r22[r33][3];
        Pack.intToLittleEndian(r53, bArr2, r21 + 0);
        Pack.intToLittleEndian(r72, bArr2, r21 + 4);
        Pack.intToLittleEndian((((r9 ^ (bArr4[shift10 & 255] & 255)) ^ ((bArr3[(shift8 >> 16) & 255] & 255) << 16)) ^ (bArr3[(shift9 >> 24) & 255] << Ascii.CAN)) ^ r22[r33][2], bArr2, r21 + 8);
        Pack.intToLittleEndian(r14, bArr2, r21 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int r2 = length >>> 2;
        int r5 = r2 + 6;
        this.ROUNDS = r5;
        int[][] r52 = (int[][]) Array.newInstance(int.class, r5 + 1, 4);
        int r10 = 8;
        char c = 3;
        if (r2 == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            r52[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            r52[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            r52[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            r52[0][3] = littleEndianToInt4;
            for (int r7 = 1; r7 <= 10; r7++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[r7 - 1];
                r52[r7][0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                r52[r7][1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                r52[r7][2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                r52[r7][3] = littleEndianToInt4;
            }
        } else if (r2 == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            r52[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            r52[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            r52[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            r52[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            int r8 = 1;
            int r12 = 1;
            while (true) {
                r52[r8][0] = littleEndianToInt9;
                r52[r8][1] = littleEndianToInt10;
                int subWord = subWord(shift(littleEndianToInt10, 8)) ^ r12;
                int r122 = r12 << 1;
                int r22 = littleEndianToInt5 ^ subWord;
                r52[r8][2] = r22;
                int r102 = littleEndianToInt6 ^ r22;
                r52[r8][3] = r102;
                int r11 = littleEndianToInt7 ^ r102;
                int r13 = r8 + 1;
                r52[r13][0] = r11;
                int r72 = littleEndianToInt8 ^ r11;
                r52[r13][1] = r72;
                int r3 = littleEndianToInt9 ^ r72;
                r52[r13][2] = r3;
                int r1 = littleEndianToInt10 ^ r3;
                r52[r13][3] = r1;
                int subWord2 = subWord(shift(r1, 8)) ^ r122;
                r12 = r122 << 1;
                littleEndianToInt5 = r22 ^ subWord2;
                int r132 = r8 + 2;
                r52[r132][0] = littleEndianToInt5;
                littleEndianToInt6 = r102 ^ littleEndianToInt5;
                r52[r132][1] = littleEndianToInt6;
                littleEndianToInt7 = r11 ^ littleEndianToInt6;
                r52[r132][2] = littleEndianToInt7;
                littleEndianToInt8 = r72 ^ littleEndianToInt7;
                r52[r132][3] = littleEndianToInt8;
                r8 += 3;
                if (r8 >= 13) {
                    break;
                }
                littleEndianToInt9 = r3 ^ littleEndianToInt8;
                littleEndianToInt10 = r1 ^ littleEndianToInt9;
            }
        } else if (r2 != 8) {
            throw new IllegalStateException("Should never get here");
        } else {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            r52[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            r52[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            r52[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            r52[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            r52[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            r52[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            r52[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            r52[1][3] = littleEndianToInt18;
            int r82 = 1;
            int r17 = 2;
            while (true) {
                int subWord3 = subWord(shift(littleEndianToInt18, r10)) ^ r82;
                r82 <<= 1;
                littleEndianToInt11 ^= subWord3;
                r52[r17][0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                r52[r17][1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                r52[r17][2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                r52[r17][c] = littleEndianToInt14;
                int r103 = r17 + 1;
                if (r103 >= 15) {
                    break;
                }
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                r52[r103][0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                r52[r103][1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                r52[r103][2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                r52[r103][3] = littleEndianToInt18;
                r17 = r103 + 1;
                r10 = 8;
                c = 3;
            }
        }
        if (!z) {
            for (int r6 = 1; r6 < this.ROUNDS; r6++) {
                for (int r14 = 0; r14 < 4; r14++) {
                    r52[r6][r14] = inv_mcol(r52[r6][r14]);
                }
            }
        }
        return r52;
    }

    private static int inv_mcol(int r2) {
        int shift = shift(r2, 8) ^ r2;
        int FFmulX = r2 ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int shift(int r1, int r2) {
        return (r1 << (-r2)) | (r1 >>> r2);
    }

    private static int subWord(int r3) {
        byte[] bArr = f1868S;
        return (bArr[(r3 >> 24) & 255] << Ascii.CAN) | (bArr[r3 & 255] & 255) | ((bArr[(r3 >> 8) & 255] & 255) << 8) | ((bArr[(r3 >> 16) & 255] & 255) << 16);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
        this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
        this.forEncryption = z;
        if (z) {
            this.f1876s = Arrays.clone(f1868S);
        } else {
            this.f1876s = Arrays.clone(f1869Si);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r9, byte[] bArr2, int r11) {
        int[][] r5 = this.WorkingKey;
        if (r5 != null) {
            if (r9 <= bArr.length - 16) {
                if (r11 <= bArr2.length - 16) {
                    if (this.forEncryption) {
                        encryptBlock(bArr, r9, bArr2, r11, r5);
                    } else {
                        decryptBlock(bArr, r9, bArr2, r11, r5);
                    }
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("AES engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
