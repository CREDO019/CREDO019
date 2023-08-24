package org.bouncycastle.pqc.math.linearalgebra;

import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor;
import com.google.common.base.Ascii;
import com.google.common.primitives.Shorts;
import java.math.BigInteger;
import java.util.Random;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.apache.commons.codec.language.p027bm.Rule;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class GF2Polynomial {
    private int blocks;
    private int len;
    private int[] value;
    private static Random rand = new Random();
    private static final boolean[] parity = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    private static final short[] squaringTable = {0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 68, 69, 80, 81, 84, 85, 256, 257, 260, 261, 272, 273, 276, 277, 320, 321, 324, 325, 336, 337, 340, 341, SilenceSkippingAudioProcessor.DEFAULT_SILENCE_THRESHOLD_LEVEL, 1025, 1028, 1029, 1040, 1041, 1044, 1045, 1088, 1089, 1092, 1093, 1104, 1105, 1108, 1109, 1280, 1281, 1284, 1285, 1296, 1297, 1300, 1301, 1344, 1345, 1348, 1349, 1360, 1361, 1364, 1365, 4096, 4097, 4100, 4101, 4112, 4113, 4116, 4117, 4160, 4161, 4164, 4165, 4176, 4177, 4180, 4181, 4352, 4353, 4356, 4357, 4368, 4369, 4372, 4373, 4416, 4417, 4420, 4421, 4432, 4433, 4436, 4437, 5120, 5121, 5124, 5125, 5136, 5137, 5140, 5141, 5184, 5185, 5188, 5189, 5200, 5201, 5204, 5205, 5376, 5377, 5380, 5381, 5392, 5393, 5396, 5397, 5440, 5441, 5444, 5445, 5456, 5457, 5460, 5461, Shorts.MAX_POWER_OF_TWO, 16385, 16388, 16389, 16400, 16401, 16404, 16405, 16448, 16449, 16452, 16453, 16464, 16465, 16468, 16469, 16640, 16641, 16644, 16645, 16656, 16657, 16660, 16661, 16704, 16705, 16708, 16709, 16720, 16721, 16724, 16725, 17408, 17409, 17412, 17413, 17424, 17425, 17428, 17429, 17472, 17473, 17476, 17477, 17488, 17489, 17492, 17493, 17664, 17665, 17668, 17669, 17680, 17681, 17684, 17685, 17728, 17729, 17732, 17733, 17744, 17745, 17748, 17749, 20480, 20481, 20484, 20485, 20496, 20497, 20500, 20501, 20544, 20545, 20548, 20549, 20560, 20561, 20564, 20565, 20736, 20737, 20740, 20741, 20752, 20753, 20756, 20757, 20800, 20801, 20804, 20805, 20816, 20817, 20820, 20821, 21504, 21505, 21508, 21509, 21520, 21521, 21524, 21525, 21568, 21569, 21572, 21573, 21584, 21585, 21588, 21589, 21760, 21761, 21764, 21765, 21776, 21777, 21780, 21781, 21824, 21825, 21828, 21829, 21840, 21841, 21844, 21845};
    private static final int[] bitMask = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, C1856C.BUFFER_FLAG_FIRST_SAMPLE, 268435456, 536870912, 1073741824, Integer.MIN_VALUE, 0};
    private static final int[] reverseRightMask = {0, 1, 3, 7, 15, 31, 63, 127, 255, FrameMetricsAggregator.EVERY_DURATION, AnalyticsListener.EVENT_DRM_KEYS_LOADED, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, ViewCompat.MEASURED_SIZE_MASK, 33554431, 67108863, 134217727, 268435455, 536870911, LockFreeTaskQueueCore.MAX_CAPACITY_MASK, Integer.MAX_VALUE, -1};

    public GF2Polynomial(int r3) {
        r3 = r3 < 1 ? 1 : r3;
        int r1 = ((r3 - 1) >> 5) + 1;
        this.blocks = r1;
        this.value = new int[r1];
        this.len = r3;
    }

    public GF2Polynomial(int r3, String str) {
        r3 = r3 < 1 ? 1 : r3;
        int r1 = ((r3 - 1) >> 5) + 1;
        this.blocks = r1;
        this.value = new int[r1];
        this.len = r3;
        if (str.equalsIgnoreCase("ZERO")) {
            assignZero();
        } else if (str.equalsIgnoreCase("ONE")) {
            assignOne();
        } else if (str.equalsIgnoreCase("RANDOM")) {
            randomize();
        } else if (str.equalsIgnoreCase("X")) {
            assignX();
        } else if (str.equalsIgnoreCase(Rule.ALL)) {
            assignAll();
        } else {
            throw new IllegalArgumentException("Error: GF2Polynomial was called using " + str + " as value!");
        }
    }

    public GF2Polynomial(int r10, BigInteger bigInteger) {
        r10 = r10 < 1 ? 1 : r10;
        int r1 = ((r10 - 1) >> 5) + 1;
        this.blocks = r1;
        this.value = new int[r1];
        this.len = r10;
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            byteArray = bArr;
        }
        int length2 = byteArray.length & 3;
        int length3 = ((byteArray.length - 1) >> 2) + 1;
        for (int r3 = 0; r3 < length2; r3++) {
            int[] r4 = this.value;
            int r5 = length3 - 1;
            r4[r5] = r4[r5] | ((byteArray[r3] & 255) << (((length2 - 1) - r3) << 3));
        }
        for (int r11 = 0; r11 <= ((byteArray.length - 4) >> 2); r11++) {
            int length4 = (byteArray.length - 1) - (r11 << 2);
            int[] r2 = this.value;
            r2[r11] = byteArray[length4] & 255;
            r2[r11] = r2[r11] | ((byteArray[length4 - 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
            r2[r11] = r2[r11] | ((byteArray[length4 - 2] << 16) & 16711680);
            r2[r11] = ((byteArray[length4 - 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | r2[r11];
        }
        int r102 = this.len;
        if ((r102 & 31) != 0) {
            int[] r112 = this.value;
            int r12 = this.blocks - 1;
            r112[r12] = reverseRightMask[r102 & 31] & r112[r12];
        }
        reduceN();
    }

    public GF2Polynomial(int r3, Random random) {
        r3 = r3 < 1 ? 1 : r3;
        int r1 = ((r3 - 1) >> 5) + 1;
        this.blocks = r1;
        this.value = new int[r1];
        this.len = r3;
        randomize(random);
    }

    public GF2Polynomial(int r11, byte[] bArr) {
        int r3;
        r11 = r11 < 1 ? 1 : r11;
        int r1 = ((r11 - 1) >> 5) + 1;
        this.blocks = r1;
        this.value = new int[r1];
        this.len = r11;
        int min = Math.min(((bArr.length - 1) >> 2) + 1, r1);
        int r12 = 0;
        while (true) {
            r3 = min - 1;
            if (r12 >= r3) {
                break;
            }
            int length = (bArr.length - (r12 << 2)) - 1;
            int[] r7 = this.value;
            r7[r12] = bArr[length] & 255;
            r7[r12] = (65280 & (bArr[length - 1] << 8)) | r7[r12];
            r7[r12] = (16711680 & (bArr[length - 2] << 16)) | r7[r12];
            r7[r12] = ((bArr[length - 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | r7[r12];
            r12++;
        }
        int length2 = (bArr.length - (r3 << 2)) - 1;
        int[] r13 = this.value;
        r13[r3] = bArr[length2] & 255;
        if (length2 > 0) {
            r13[r3] = (65280 & (bArr[length2 - 1] << 8)) | r13[r3];
        }
        if (length2 > 1) {
            r13[r3] = r13[r3] | (16711680 & (bArr[length2 - 2] << 16));
        }
        if (length2 > 2) {
            r13[r3] = ((bArr[length2 - 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | r13[r3];
        }
        zeroUnusedBits();
        reduceN();
    }

    public GF2Polynomial(int r3, int[] r4) {
        r3 = r3 < 1 ? 1 : r3;
        int r1 = ((r3 - 1) >> 5) + 1;
        this.blocks = r1;
        this.value = new int[r1];
        this.len = r3;
        System.arraycopy(r4, 0, this.value, 0, Math.min(r1, r4.length));
        zeroUnusedBits();
    }

    public GF2Polynomial(GF2Polynomial gF2Polynomial) {
        this.len = gF2Polynomial.len;
        this.blocks = gF2Polynomial.blocks;
        this.value = IntUtils.clone(gF2Polynomial.value);
    }

    private void doShiftBlocksLeft(int r5) {
        int r0 = this.blocks;
        int[] r1 = this.value;
        if (r0 > r1.length) {
            int[] r2 = new int[r0];
            System.arraycopy(r1, 0, r2, r5, r0 - r5);
            this.value = r2;
            return;
        }
        for (int r02 = r0 - 1; r02 >= r5; r02--) {
            int[] r12 = this.value;
            r12[r02] = r12[r02 - r5];
        }
        for (int r03 = 0; r03 < r5; r03++) {
            this.value[r03] = 0;
        }
    }

    private GF2Polynomial karaMult(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.len << 1);
        int r1 = this.len;
        if (r1 <= 32) {
            gF2Polynomial2.value = mult32(this.value[0], gF2Polynomial.value[0]);
            return gF2Polynomial2;
        } else if (r1 <= 64) {
            gF2Polynomial2.value = mult64(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        } else if (r1 <= 128) {
            gF2Polynomial2.value = mult128(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        } else if (r1 <= 256) {
            gF2Polynomial2.value = mult256(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        } else if (r1 <= 512) {
            gF2Polynomial2.value = mult512(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        } else {
            int r12 = bitMask[IntegerFunctions.floorLog(r1 - 1)];
            int r2 = ((r12 - 1) >> 5) + 1;
            GF2Polynomial lower = lower(r2);
            GF2Polynomial upper = upper(r2);
            GF2Polynomial lower2 = gF2Polynomial.lower(r2);
            GF2Polynomial upper2 = gF2Polynomial.upper(r2);
            GF2Polynomial karaMult = upper.karaMult(upper2);
            GF2Polynomial karaMult2 = lower.karaMult(lower2);
            lower.addToThis(upper);
            lower2.addToThis(upper2);
            GF2Polynomial karaMult3 = lower.karaMult(lower2);
            gF2Polynomial2.shiftLeftAddThis(karaMult, r12 << 1);
            gF2Polynomial2.shiftLeftAddThis(karaMult, r12);
            gF2Polynomial2.shiftLeftAddThis(karaMult3, r12);
            gF2Polynomial2.shiftLeftAddThis(karaMult2, r12);
            gF2Polynomial2.addToThis(karaMult2);
            return gF2Polynomial2;
        }
    }

    private GF2Polynomial lower(int r5) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(r5 << 5);
        System.arraycopy(this.value, 0, gF2Polynomial.value, 0, Math.min(r5, this.blocks));
        return gF2Polynomial;
    }

    private static int[] mult128(int[] r13, int[] r14) {
        int[] r0 = new int[8];
        int[] r2 = new int[2];
        System.arraycopy(r13, 0, r2, 0, Math.min(2, r13.length));
        int[] r3 = new int[2];
        if (r13.length > 2) {
            System.arraycopy(r13, 2, r3, 0, Math.min(2, r13.length - 2));
        }
        int[] r132 = new int[2];
        System.arraycopy(r14, 0, r132, 0, Math.min(2, r14.length));
        int[] r5 = new int[2];
        if (r14.length > 2) {
            System.arraycopy(r14, 2, r5, 0, Math.min(2, r14.length - 2));
        }
        if (r3[1] != 0 || r5[1] != 0) {
            int[] mult64 = mult64(r3, r5);
            r0[7] = r0[7] ^ mult64[3];
            r0[6] = r0[6] ^ mult64[2];
            r0[5] = r0[5] ^ (mult64[1] ^ mult64[3]);
            r0[4] = r0[4] ^ (mult64[0] ^ mult64[2]);
            r0[3] = r0[3] ^ mult64[1];
            r0[2] = mult64[0] ^ r0[2];
        } else if (r3[0] != 0 || r5[0] != 0) {
            int[] mult32 = mult32(r3[0], r5[0]);
            r0[5] = r0[5] ^ mult32[1];
            r0[4] = r0[4] ^ mult32[0];
            r0[3] = r0[3] ^ mult32[1];
            r0[2] = mult32[0] ^ r0[2];
        }
        r3[0] = r3[0] ^ r2[0];
        r3[1] = r3[1] ^ r2[1];
        r5[0] = r5[0] ^ r132[0];
        r5[1] = r5[1] ^ r132[1];
        if (r3[1] == 0 && r5[1] == 0) {
            int[] mult322 = mult32(r3[0], r5[0]);
            r0[3] = r0[3] ^ mult322[1];
            r0[2] = mult322[0] ^ r0[2];
        } else {
            int[] mult642 = mult64(r3, r5);
            r0[5] = r0[5] ^ mult642[3];
            r0[4] = r0[4] ^ mult642[2];
            r0[3] = r0[3] ^ mult642[1];
            r0[2] = mult642[0] ^ r0[2];
        }
        if (r2[1] == 0 && r132[1] == 0) {
            int[] mult323 = mult32(r2[0], r132[0]);
            r0[3] = r0[3] ^ mult323[1];
            r0[2] = r0[2] ^ mult323[0];
            r0[1] = r0[1] ^ mult323[1];
            r0[0] = mult323[0] ^ r0[0];
        } else {
            int[] mult643 = mult64(r2, r132);
            r0[5] = r0[5] ^ mult643[3];
            r0[4] = r0[4] ^ mult643[2];
            r0[3] = r0[3] ^ (mult643[1] ^ mult643[3]);
            r0[2] = r0[2] ^ (mult643[0] ^ mult643[2]);
            r0[1] = r0[1] ^ mult643[1];
            r0[0] = mult643[0] ^ r0[0];
        }
        return r0;
    }

    private static int[] mult256(int[] r21, int[] r22) {
        int[] r2 = new int[16];
        int[] r4 = new int[4];
        System.arraycopy(r21, 0, r4, 0, Math.min(4, r21.length));
        int[] r5 = new int[4];
        if (r21.length > 4) {
            System.arraycopy(r21, 4, r5, 0, Math.min(4, r21.length - 4));
        }
        int[] r0 = new int[4];
        System.arraycopy(r22, 0, r0, 0, Math.min(4, r22.length));
        int[] r7 = new int[4];
        if (r22.length > 4) {
            System.arraycopy(r22, 4, r7, 0, Math.min(4, r22.length - 4));
        }
        if (r5[3] != 0 || r5[2] != 0 || r7[3] != 0 || r7[2] != 0) {
            int[] mult128 = mult128(r5, r7);
            r2[15] = r2[15] ^ mult128[7];
            r2[14] = r2[14] ^ mult128[6];
            r2[13] = r2[13] ^ mult128[5];
            r2[12] = r2[12] ^ mult128[4];
            r2[11] = r2[11] ^ (mult128[3] ^ mult128[7]);
            r2[10] = r2[10] ^ (mult128[2] ^ mult128[6]);
            r2[9] = r2[9] ^ (mult128[1] ^ mult128[5]);
            r2[8] = r2[8] ^ (mult128[0] ^ mult128[4]);
            r2[7] = r2[7] ^ mult128[3];
            r2[6] = r2[6] ^ mult128[2];
            r2[5] = r2[5] ^ mult128[1];
            r2[4] = mult128[0] ^ r2[4];
        } else if (r5[1] != 0 || r7[1] != 0) {
            int[] mult64 = mult64(r5, r7);
            r2[11] = r2[11] ^ mult64[3];
            r2[10] = r2[10] ^ mult64[2];
            r2[9] = r2[9] ^ mult64[1];
            r2[8] = r2[8] ^ mult64[0];
            r2[7] = r2[7] ^ mult64[3];
            r2[6] = r2[6] ^ mult64[2];
            r2[5] = r2[5] ^ mult64[1];
            r2[4] = mult64[0] ^ r2[4];
        } else if (r5[0] != 0 || r7[0] != 0) {
            int[] mult32 = mult32(r5[0], r7[0]);
            r2[9] = r2[9] ^ mult32[1];
            r2[8] = r2[8] ^ mult32[0];
            r2[5] = r2[5] ^ mult32[1];
            r2[4] = mult32[0] ^ r2[4];
        }
        r5[0] = r5[0] ^ r4[0];
        r5[1] = r5[1] ^ r4[1];
        r5[2] = r5[2] ^ r4[2];
        r5[3] = r5[3] ^ r4[3];
        r7[0] = r7[0] ^ r0[0];
        r7[1] = r7[1] ^ r0[1];
        r7[2] = r7[2] ^ r0[2];
        r7[3] = r7[3] ^ r0[3];
        int[] mult1282 = mult128(r5, r7);
        r2[11] = r2[11] ^ mult1282[7];
        r2[10] = r2[10] ^ mult1282[6];
        r2[9] = r2[9] ^ mult1282[5];
        r2[8] = r2[8] ^ mult1282[4];
        r2[7] = r2[7] ^ mult1282[3];
        r2[6] = r2[6] ^ mult1282[2];
        r2[5] = r2[5] ^ mult1282[1];
        r2[4] = mult1282[0] ^ r2[4];
        int[] mult1283 = mult128(r4, r0);
        r2[11] = r2[11] ^ mult1283[7];
        r2[10] = r2[10] ^ mult1283[6];
        r2[9] = r2[9] ^ mult1283[5];
        r2[8] = r2[8] ^ mult1283[4];
        r2[7] = r2[7] ^ (mult1283[3] ^ mult1283[7]);
        r2[6] = r2[6] ^ (mult1283[2] ^ mult1283[6]);
        r2[5] = r2[5] ^ (mult1283[1] ^ mult1283[5]);
        r2[4] = r2[4] ^ (mult1283[0] ^ mult1283[4]);
        r2[3] = r2[3] ^ mult1283[3];
        r2[2] = r2[2] ^ mult1283[2];
        r2[1] = r2[1] ^ mult1283[1];
        r2[0] = mult1283[0] ^ r2[0];
        return r2;
    }

    private static int[] mult32(int r10, int r11) {
        int[] r0 = new int[2];
        if (r10 != 0 && r11 != 0) {
            long j = r11 & BodyPartID.bodyIdMax;
            long j2 = 0;
            for (int r7 = 1; r7 <= 32; r7++) {
                if ((bitMask[r7 - 1] & r10) != 0) {
                    j2 ^= j;
                }
                j <<= 1;
            }
            r0[1] = (int) (j2 >>> 32);
            r0[0] = (int) (j2 & BodyPartID.bodyIdMax);
        }
        return r0;
    }

    private static int[] mult512(int[] r32, int[] r33) {
        int[] r2 = new int[32];
        int[] r4 = new int[8];
        System.arraycopy(r32, 0, r4, 0, Math.min(8, r32.length));
        int[] r5 = new int[8];
        if (r32.length > 8) {
            System.arraycopy(r32, 8, r5, 0, Math.min(8, r32.length - 8));
        }
        int[] r0 = new int[8];
        System.arraycopy(r33, 0, r0, 0, Math.min(8, r33.length));
        int[] r7 = new int[8];
        if (r33.length > 8) {
            System.arraycopy(r33, 8, r7, 0, Math.min(8, r33.length - 8));
        }
        int[] mult256 = mult256(r5, r7);
        r2[31] = r2[31] ^ mult256[15];
        r2[30] = r2[30] ^ mult256[14];
        r2[29] = r2[29] ^ mult256[13];
        r2[28] = r2[28] ^ mult256[12];
        r2[27] = r2[27] ^ mult256[11];
        r2[26] = r2[26] ^ mult256[10];
        r2[25] = r2[25] ^ mult256[9];
        r2[24] = r2[24] ^ mult256[8];
        r2[23] = r2[23] ^ (mult256[7] ^ mult256[15]);
        r2[22] = r2[22] ^ (mult256[6] ^ mult256[14]);
        r2[21] = r2[21] ^ (mult256[5] ^ mult256[13]);
        r2[20] = r2[20] ^ (mult256[4] ^ mult256[12]);
        r2[19] = r2[19] ^ (mult256[3] ^ mult256[11]);
        r2[18] = r2[18] ^ (mult256[2] ^ mult256[10]);
        r2[17] = r2[17] ^ (mult256[1] ^ mult256[9]);
        r2[16] = r2[16] ^ (mult256[0] ^ mult256[8]);
        r2[15] = r2[15] ^ mult256[7];
        r2[14] = r2[14] ^ mult256[6];
        r2[13] = r2[13] ^ mult256[5];
        r2[12] = r2[12] ^ mult256[4];
        r2[11] = r2[11] ^ mult256[3];
        r2[10] = r2[10] ^ mult256[2];
        r2[9] = r2[9] ^ mult256[1];
        r2[8] = r2[8] ^ mult256[0];
        r5[0] = r5[0] ^ r4[0];
        r5[1] = r5[1] ^ r4[1];
        r5[2] = r5[2] ^ r4[2];
        r5[3] = r5[3] ^ r4[3];
        r5[4] = r5[4] ^ r4[4];
        r5[5] = r5[5] ^ r4[5];
        r5[6] = r5[6] ^ r4[6];
        r5[7] = r5[7] ^ r4[7];
        r7[0] = r7[0] ^ r0[0];
        r7[1] = r7[1] ^ r0[1];
        r7[2] = r7[2] ^ r0[2];
        r7[3] = r7[3] ^ r0[3];
        r7[4] = r7[4] ^ r0[4];
        r7[5] = r7[5] ^ r0[5];
        r7[6] = r7[6] ^ r0[6];
        r7[7] = r7[7] ^ r0[7];
        int[] mult2562 = mult256(r5, r7);
        r2[23] = r2[23] ^ mult2562[15];
        r2[22] = r2[22] ^ mult2562[14];
        r2[21] = r2[21] ^ mult2562[13];
        r2[20] = r2[20] ^ mult2562[12];
        r2[19] = r2[19] ^ mult2562[11];
        r2[18] = r2[18] ^ mult2562[10];
        r2[17] = r2[17] ^ mult2562[9];
        r2[16] = r2[16] ^ mult2562[8];
        r2[15] = r2[15] ^ mult2562[7];
        r2[14] = r2[14] ^ mult2562[6];
        r2[13] = r2[13] ^ mult2562[5];
        r2[12] = r2[12] ^ mult2562[4];
        r2[11] = r2[11] ^ mult2562[3];
        r2[10] = r2[10] ^ mult2562[2];
        r2[9] = r2[9] ^ mult2562[1];
        r2[8] = mult2562[0] ^ r2[8];
        int[] mult2563 = mult256(r4, r0);
        r2[23] = r2[23] ^ mult2563[15];
        r2[22] = r2[22] ^ mult2563[14];
        r2[21] = r2[21] ^ mult2563[13];
        r2[20] = r2[20] ^ mult2563[12];
        r2[19] = r2[19] ^ mult2563[11];
        r2[18] = r2[18] ^ mult2563[10];
        r2[17] = r2[17] ^ mult2563[9];
        r2[16] = r2[16] ^ mult2563[8];
        r2[15] = r2[15] ^ (mult2563[7] ^ mult2563[15]);
        r2[14] = r2[14] ^ (mult2563[6] ^ mult2563[14]);
        r2[13] = r2[13] ^ (mult2563[5] ^ mult2563[13]);
        r2[12] = r2[12] ^ (mult2563[4] ^ mult2563[12]);
        r2[11] = r2[11] ^ (mult2563[3] ^ mult2563[11]);
        r2[10] = r2[10] ^ (mult2563[2] ^ mult2563[10]);
        r2[9] = r2[9] ^ (mult2563[1] ^ mult2563[9]);
        r2[8] = r2[8] ^ (mult2563[0] ^ mult2563[8]);
        r2[7] = r2[7] ^ mult2563[7];
        r2[6] = r2[6] ^ mult2563[6];
        r2[5] = r2[5] ^ mult2563[5];
        r2[4] = r2[4] ^ mult2563[4];
        r2[3] = r2[3] ^ mult2563[3];
        r2[2] = r2[2] ^ mult2563[2];
        r2[1] = r2[1] ^ mult2563[1];
        r2[0] = mult2563[0] ^ r2[0];
        return r2;
    }

    private static int[] mult64(int[] r10, int[] r11) {
        int[] r0 = new int[4];
        int r2 = r10[0];
        int r102 = r10.length > 1 ? r10[1] : 0;
        int r3 = r11[0];
        int r112 = r11.length > 1 ? r11[1] : 0;
        if (r102 != 0 || r112 != 0) {
            int[] mult32 = mult32(r102, r112);
            r0[3] = r0[3] ^ mult32[1];
            r0[2] = r0[2] ^ (mult32[0] ^ mult32[1]);
            r0[1] = mult32[0] ^ r0[1];
        }
        int[] mult322 = mult32(r102 ^ r2, r112 ^ r3);
        r0[2] = r0[2] ^ mult322[1];
        r0[1] = mult322[0] ^ r0[1];
        int[] mult323 = mult32(r2, r3);
        r0[2] = r0[2] ^ mult323[1];
        r0[1] = r0[1] ^ (mult323[0] ^ mult323[1]);
        r0[0] = mult323[0] ^ r0[0];
        return r0;
    }

    private GF2Polynomial upper(int r6) {
        int min = Math.min(r6, this.blocks - r6);
        GF2Polynomial gF2Polynomial = new GF2Polynomial(min << 5);
        if (this.blocks >= r6) {
            System.arraycopy(this.value, r6, gF2Polynomial.value, 0, min);
        }
        return gF2Polynomial;
    }

    private void zeroUnusedBits() {
        int r0 = this.len;
        if ((r0 & 31) != 0) {
            int[] r1 = this.value;
            int r2 = this.blocks - 1;
            r1[r2] = reverseRightMask[r0 & 31] & r1[r2];
        }
    }

    public GF2Polynomial add(GF2Polynomial gF2Polynomial) {
        return xor(gF2Polynomial);
    }

    public void addToThis(GF2Polynomial gF2Polynomial) {
        expandN(gF2Polynomial.len);
        xorThisBy(gF2Polynomial);
    }

    public void assignAll() {
        for (int r0 = 0; r0 < this.blocks; r0++) {
            this.value[r0] = -1;
        }
        zeroUnusedBits();
    }

    public void assignOne() {
        for (int r1 = 1; r1 < this.blocks; r1++) {
            this.value[r1] = 0;
        }
        this.value[0] = 1;
    }

    public void assignX() {
        for (int r0 = 1; r0 < this.blocks; r0++) {
            this.value[r0] = 0;
        }
        this.value[0] = 2;
    }

    public void assignZero() {
        for (int r1 = 0; r1 < this.blocks; r1++) {
            this.value[r1] = 0;
        }
    }

    public Object clone() {
        return new GF2Polynomial(this);
    }

    public GF2Polynomial[] divide(GF2Polynomial gF2Polynomial) throws RuntimeException {
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[2];
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.len);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial4 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial4.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial3.reduceN();
        gF2Polynomial4.reduceN();
        int r8 = gF2Polynomial3.len;
        int r4 = gF2Polynomial4.len;
        if (r8 < r4) {
            gF2PolynomialArr[0] = new GF2Polynomial(0);
            gF2PolynomialArr[1] = gF2Polynomial3;
            return gF2PolynomialArr;
        }
        int r82 = r8 - r4;
        gF2Polynomial2.expandN(r82 + 1);
        while (r82 >= 0) {
            gF2Polynomial3.subtractFromThis(gF2Polynomial4.shiftLeft(r82));
            gF2Polynomial3.reduceN();
            gF2Polynomial2.xorBit(r82);
            r82 = gF2Polynomial3.len - gF2Polynomial4.len;
        }
        gF2PolynomialArr[0] = gF2Polynomial2;
        gF2PolynomialArr[1] = gF2Polynomial3;
        return gF2PolynomialArr;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2Polynomial)) {
            return false;
        }
        GF2Polynomial gF2Polynomial = (GF2Polynomial) obj;
        if (this.len != gF2Polynomial.len) {
            return false;
        }
        for (int r1 = 0; r1 < this.blocks; r1++) {
            if (this.value[r1] != gF2Polynomial.value[r1]) {
                return false;
            }
        }
        return true;
    }

    public void expandN(int r5) {
        if (this.len >= r5) {
            return;
        }
        this.len = r5;
        int r52 = ((r5 - 1) >>> 5) + 1;
        int r0 = this.blocks;
        if (r0 >= r52) {
            return;
        }
        int[] r1 = this.value;
        if (r1.length >= r52) {
            while (r0 < r52) {
                this.value[r0] = 0;
                r0++;
            }
            this.blocks = r52;
            return;
        }
        int[] r2 = new int[r52];
        System.arraycopy(r1, 0, r2, 0, r0);
        this.blocks = r52;
        this.value = r2;
    }

    public GF2Polynomial gcd(GF2Polynomial gF2Polynomial) throws RuntimeException {
        if (isZero() && gF2Polynomial.isZero()) {
            throw new ArithmeticException("Both operands of gcd equal zero.");
        }
        if (isZero()) {
            return new GF2Polynomial(gF2Polynomial);
        }
        if (gF2Polynomial.isZero()) {
            return new GF2Polynomial(this);
        }
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(gF2Polynomial);
        GF2Polynomial gF2Polynomial4 = gF2Polynomial2;
        GF2Polynomial gF2Polynomial5 = gF2Polynomial3;
        while (!gF2Polynomial5.isZero()) {
            GF2Polynomial gF2Polynomial6 = gF2Polynomial5;
            gF2Polynomial5 = gF2Polynomial4.remainder(gF2Polynomial5);
            gF2Polynomial4 = gF2Polynomial6;
        }
        return gF2Polynomial4;
    }

    public int getBit(int r5) {
        if (r5 >= 0) {
            if (r5 > this.len - 1) {
                return 0;
            }
            return (bitMask[r5 & 31] & this.value[r5 >>> 5]) != 0 ? 1 : 0;
        }
        throw new RuntimeException();
    }

    public int getLength() {
        return this.len;
    }

    public int hashCode() {
        return this.len + Arrays.hashCode(this.value);
    }

    public GF2Polynomial increase() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this);
        gF2Polynomial.increaseThis();
        return gF2Polynomial;
    }

    public void increaseThis() {
        xorBit(0);
    }

    public boolean isIrreducible() {
        if (isZero()) {
            return false;
        }
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this);
        gF2Polynomial.reduceN();
        int r2 = gF2Polynomial.len - 1;
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(gF2Polynomial.len, "X");
        for (int r5 = 1; r5 <= (r2 >> 1); r5++) {
            gF2Polynomial2.squareThisPreCalc();
            gF2Polynomial2 = gF2Polynomial2.remainder(gF2Polynomial);
            GF2Polynomial add = gF2Polynomial2.add(new GF2Polynomial(32, "X"));
            if (add.isZero() || !gF2Polynomial.gcd(add).isOne()) {
                return false;
            }
        }
        return true;
    }

    public boolean isOne() {
        for (int r1 = 1; r1 < this.blocks; r1++) {
            if (this.value[r1] != 0) {
                return false;
            }
        }
        return this.value[0] == 1;
    }

    public boolean isZero() {
        if (this.len == 0) {
            return true;
        }
        for (int r2 = 0; r2 < this.blocks; r2++) {
            if (this.value[r2] != 0) {
                return false;
            }
        }
        return true;
    }

    public GF2Polynomial multiply(GF2Polynomial gF2Polynomial) {
        int max = Math.max(this.len, gF2Polynomial.len);
        expandN(max);
        gF2Polynomial.expandN(max);
        return karaMult(gF2Polynomial);
    }

    public GF2Polynomial multiplyClassic(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(Math.max(this.len, gF2Polynomial.len) << 1);
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[32];
        gF2PolynomialArr[0] = new GF2Polynomial(this);
        for (int r2 = 1; r2 <= 31; r2++) {
            gF2PolynomialArr[r2] = gF2PolynomialArr[r2 - 1].shiftLeft();
        }
        for (int r22 = 0; r22 < gF2Polynomial.blocks; r22++) {
            for (int r5 = 0; r5 <= 31; r5++) {
                if ((gF2Polynomial.value[r22] & bitMask[r5]) != 0) {
                    gF2Polynomial2.xorThisBy(gF2PolynomialArr[r5]);
                }
            }
            for (int r52 = 0; r52 <= 31; r52++) {
                gF2PolynomialArr[r52].shiftBlocksLeft();
            }
        }
        return gF2Polynomial2;
    }

    public GF2Polynomial quotient(GF2Polynomial gF2Polynomial) throws RuntimeException {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.len);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial4 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial4.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial3.reduceN();
        gF2Polynomial4.reduceN();
        int r5 = gF2Polynomial3.len;
        int r3 = gF2Polynomial4.len;
        if (r5 < r3) {
            return new GF2Polynomial(0);
        }
        int r52 = r5 - r3;
        gF2Polynomial2.expandN(r52 + 1);
        while (r52 >= 0) {
            gF2Polynomial3.subtractFromThis(gF2Polynomial4.shiftLeft(r52));
            gF2Polynomial3.reduceN();
            gF2Polynomial2.xorBit(r52);
            r52 = gF2Polynomial3.len - gF2Polynomial4.len;
        }
        return gF2Polynomial2;
    }

    public void randomize() {
        for (int r0 = 0; r0 < this.blocks; r0++) {
            this.value[r0] = rand.nextInt();
        }
        zeroUnusedBits();
    }

    public void randomize(Random random) {
        for (int r0 = 0; r0 < this.blocks; r0++) {
            this.value[r0] = random.nextInt();
        }
        zeroUnusedBits();
    }

    public void reduceN() {
        int[] r1;
        int r0 = this.blocks;
        while (true) {
            r0--;
            r1 = this.value;
            if (r1[r0] != 0 || r0 <= 0) {
                break;
            }
        }
        int r12 = r1[r0];
        int r2 = 0;
        while (r12 != 0) {
            r12 >>>= 1;
            r2++;
        }
        this.len = (r0 << 5) + r2;
        this.blocks = r0 + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reducePentanomial(int r21, int[] r22) {
        GF2Polynomial gF2Polynomial = this;
        int r2 = r21 >>> 5;
        int r3 = r21 & 31;
        int r4 = 32 - r3;
        int r6 = (r21 - r22[0]) >>> 5;
        int r7 = 32 - ((r21 - r22[0]) & 31);
        int r9 = (r21 - r22[1]) >>> 5;
        int r10 = 32 - ((r21 - r22[1]) & 31);
        int r12 = (r21 - r22[2]) >>> 5;
        int r13 = 32 - ((r21 - r22[2]) & 31);
        int r11 = ((r21 << 1) - 2) >>> 5;
        while (r11 > r2) {
            int[] r8 = gF2Polynomial.value;
            long j = r8[r11] & BodyPartID.bodyIdMax;
            int r5 = r11 - r2;
            int r14 = r5 - 1;
            int r16 = r2;
            int r17 = r3;
            r8[r14] = r8[r14] ^ ((int) (j << r4));
            r8[r5] = (int) (r8[r5] ^ (j >>> (32 - r4)));
            int r23 = r11 - r6;
            int r32 = r23 - 1;
            r8[r32] = r8[r32] ^ ((int) (j << r7));
            r8[r23] = (int) (r8[r23] ^ (j >>> (32 - r7)));
            int r24 = r11 - r9;
            int r33 = r24 - 1;
            r8[r33] = r8[r33] ^ ((int) (j << r10));
            r8[r24] = (int) (r8[r24] ^ (j >>> (32 - r10)));
            int r25 = r11 - r12;
            int r34 = r25 - 1;
            r8[r34] = r8[r34] ^ ((int) (j << r13));
            r8[r25] = (int) ((j >>> (32 - r13)) ^ r8[r25]);
            r8[r11] = 0;
            r11--;
            gF2Polynomial = this;
            r2 = r16;
            r3 = r17;
        }
        int r162 = r2;
        int r172 = r3;
        int[] r26 = gF2Polynomial.value;
        long j2 = r26[r162] & BodyPartID.bodyIdMax & (BodyPartID.bodyIdMax << r172);
        r26[0] = (int) ((j2 >>> (32 - r4)) ^ r26[0]);
        int r35 = r162 - r6;
        int r42 = r35 - 1;
        if (r42 >= 0) {
            r26[r42] = r26[r42] ^ ((int) (j2 << r7));
        }
        r26[r35] = (int) (r26[r35] ^ (j2 >>> (32 - r7)));
        int r36 = r162 - r9;
        int r43 = r36 - 1;
        if (r43 >= 0) {
            r26[r43] = r26[r43] ^ ((int) (j2 << r10));
        }
        r26[r36] = (int) (r26[r36] ^ (j2 >>> (32 - r10)));
        int r37 = r162 - r12;
        int r44 = r37 - 1;
        if (r44 >= 0) {
            r26[r44] = r26[r44] ^ ((int) (j2 << r13));
        }
        r26[r37] = (int) ((j2 >>> (32 - r13)) ^ r26[r37]);
        r26[r162] = r26[r162] & reverseRightMask[r172];
        this.blocks = ((r21 - 1) >>> 5) + 1;
        this.len = r21;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reduceTrinomial(int r17, int r18) {
        int r2 = r17 >>> 5;
        int r3 = r17 & 31;
        int r4 = 32 - r3;
        int r5 = r17 - r18;
        int r6 = r5 >>> 5;
        int r52 = 32 - (r5 & 31);
        int r7 = ((r17 << 1) - 2) >>> 5;
        while (r7 > r2) {
            int[] r11 = this.value;
            long j = BodyPartID.bodyIdMax & r11[r7];
            int r12 = r7 - r2;
            int r13 = r12 - 1;
            int r15 = r2;
            r11[r13] = r11[r13] ^ ((int) (j << r4));
            r11[r12] = (int) (r11[r12] ^ (j >>> (32 - r4)));
            int r1 = r7 - r6;
            int r22 = r1 - 1;
            r11[r22] = r11[r22] ^ ((int) (j << r52));
            r11[r1] = (int) ((j >>> (32 - r52)) ^ r11[r1]);
            r11[r7] = 0;
            r7--;
            r2 = r15;
        }
        int r152 = r2;
        int[] r14 = this.value;
        long j2 = (BodyPartID.bodyIdMax << r3) & r14[r152] & BodyPartID.bodyIdMax;
        r14[0] = (int) (r14[0] ^ (j2 >>> (32 - r4)));
        int r23 = r152 - r6;
        int r42 = r23 - 1;
        if (r42 >= 0) {
            r14[r42] = r14[r42] ^ ((int) (j2 << r52));
        }
        r14[r23] = (int) ((j2 >>> (32 - r52)) ^ r14[r23]);
        r14[r152] = r14[r152] & reverseRightMask[r3];
        this.blocks = ((r17 - 1) >>> 5) + 1;
        this.len = r17;
    }

    public GF2Polynomial remainder(GF2Polynomial gF2Polynomial) throws RuntimeException {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial3.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial2.reduceN();
        gF2Polynomial3.reduceN();
        int r4 = gF2Polynomial2.len;
        int r2 = gF2Polynomial3.len;
        if (r4 < r2) {
            return gF2Polynomial2;
        }
        while (true) {
            int r42 = r4 - r2;
            if (r42 < 0) {
                return gF2Polynomial2;
            }
            gF2Polynomial2.subtractFromThis(gF2Polynomial3.shiftLeft(r42));
            gF2Polynomial2.reduceN();
            r4 = gF2Polynomial2.len;
            r2 = gF2Polynomial3.len;
        }
    }

    public void resetBit(int r5) throws RuntimeException {
        if (r5 < 0) {
            throw new RuntimeException();
        }
        if (r5 > this.len - 1) {
            return;
        }
        int[] r0 = this.value;
        int r1 = r5 >>> 5;
        r0[r1] = (~bitMask[r5 & 31]) & r0[r1];
    }

    public void setBit(int r5) throws RuntimeException {
        if (r5 < 0 || r5 > this.len - 1) {
            throw new RuntimeException();
        }
        int[] r0 = this.value;
        int r1 = r5 >>> 5;
        r0[r1] = bitMask[r5 & 31] | r0[r1];
    }

    void shiftBlocksLeft() {
        int r0 = this.blocks + 1;
        this.blocks = r0;
        this.len += 32;
        int[] r2 = this.value;
        if (r0 > r2.length) {
            int[] r3 = new int[r0];
            System.arraycopy(r2, 0, r3, 1, r0 - 1);
            this.value = r3;
            return;
        }
        for (int r02 = r0 - 1; r02 >= 1; r02--) {
            int[] r22 = this.value;
            r22[r02] = r22[r02 - 1];
        }
        this.value[0] = 0;
    }

    public GF2Polynomial shiftLeft() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.len + 1, this.value);
        for (int r1 = gF2Polynomial.blocks - 1; r1 >= 1; r1--) {
            int[] r3 = gF2Polynomial.value;
            r3[r1] = r3[r1] << 1;
            r3[r1] = r3[r1] | (r3[r1 - 1] >>> 31);
        }
        int[] r12 = gF2Polynomial.value;
        r12[0] = r12[0] << 1;
        return gF2Polynomial;
    }

    public GF2Polynomial shiftLeft(int r8) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.len + r8, this.value);
        if (r8 >= 32) {
            gF2Polynomial.doShiftBlocksLeft(r8 >>> 5);
        }
        int r82 = r8 & 31;
        if (r82 != 0) {
            for (int r1 = gF2Polynomial.blocks - 1; r1 >= 1; r1--) {
                int[] r3 = gF2Polynomial.value;
                r3[r1] = r3[r1] << r82;
                r3[r1] = r3[r1] | (r3[r1 - 1] >>> (32 - r82));
            }
            int[] r12 = gF2Polynomial.value;
            r12[0] = r12[0] << r82;
        }
        return gF2Polynomial;
    }

    public void shiftLeftAddThis(GF2Polynomial gF2Polynomial, int r10) {
        int r4;
        if (r10 == 0) {
            addToThis(gF2Polynomial);
            return;
        }
        expandN(gF2Polynomial.len + r10);
        int r0 = r10 >>> 5;
        for (int r1 = gF2Polynomial.blocks - 1; r1 >= 0; r1--) {
            int r2 = r1 + r0;
            int r3 = r2 + 1;
            if (r3 < this.blocks && (r4 = r10 & 31) != 0) {
                int[] r5 = this.value;
                r5[r3] = (gF2Polynomial.value[r1] >>> (32 - r4)) ^ r5[r3];
            }
            int[] r32 = this.value;
            r32[r2] = r32[r2] ^ (gF2Polynomial.value[r1] << (r10 & 31));
        }
    }

    public void shiftLeftThis() {
        int r0 = this.len;
        int r1 = r0 & 31;
        this.len = r0 + 1;
        int r02 = this.blocks;
        if (r1 != 0) {
            for (int r03 = r02 - 1; r03 >= 1; r03--) {
                int[] r12 = this.value;
                r12[r03] = r12[r03] << 1;
                r12[r03] = r12[r03] | (r12[r03 - 1] >>> 31);
            }
            int[] r04 = this.value;
            r04[0] = r04[0] << 1;
            return;
        }
        int r05 = r02 + 1;
        this.blocks = r05;
        int[] r13 = this.value;
        if (r05 > r13.length) {
            int[] r06 = new int[r05];
            System.arraycopy(r13, 0, r06, 0, r13.length);
            this.value = r06;
        }
        for (int r07 = this.blocks - 1; r07 >= 1; r07--) {
            int[] r14 = this.value;
            int r4 = r07 - 1;
            r14[r07] = r14[r07] | (r14[r4] >>> 31);
            r14[r4] = r14[r4] << 1;
        }
    }

    public GF2Polynomial shiftRight() {
        int r1;
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.len - 1);
        int r4 = 0;
        System.arraycopy(this.value, 0, gF2Polynomial.value, 0, gF2Polynomial.blocks);
        while (true) {
            r1 = gF2Polynomial.blocks;
            if (r4 > r1 - 2) {
                break;
            }
            int[] r12 = gF2Polynomial.value;
            r12[r4] = r12[r4] >>> 1;
            int r3 = r4 + 1;
            r12[r4] = r12[r4] | (r12[r3] << 31);
            r4 = r3;
        }
        int[] r2 = gF2Polynomial.value;
        int r32 = r1 - 1;
        r2[r32] = r2[r32] >>> 1;
        if (r1 < this.blocks) {
            int r33 = r1 - 1;
            r2[r33] = (this.value[r1] << 31) | r2[r33];
        }
        return gF2Polynomial;
    }

    public void shiftRightThis() {
        int r1;
        int r0 = this.len - 1;
        this.len = r0;
        this.blocks = ((r0 - 1) >>> 5) + 1;
        int r02 = 0;
        while (true) {
            r1 = this.blocks;
            if (r02 > r1 - 2) {
                break;
            }
            int[] r12 = this.value;
            r12[r02] = r12[r02] >>> 1;
            int r3 = r02 + 1;
            r12[r02] = r12[r02] | (r12[r3] << 31);
            r02 = r3;
        }
        int[] r03 = this.value;
        int r2 = r1 - 1;
        r03[r2] = r03[r2] >>> 1;
        if ((this.len & 31) == 0) {
            int r22 = r1 - 1;
            r03[r22] = (r03[r1] << 31) | r03[r22];
        }
    }

    public void squareThisBitwise() {
        if (isZero()) {
            return;
        }
        int r0 = this.blocks;
        int r1 = r0 << 1;
        int[] r2 = new int[r1];
        for (int r02 = r0 - 1; r02 >= 0; r02--) {
            int r4 = this.value[r02];
            int r6 = 1;
            for (int r5 = 0; r5 < 16; r5++) {
                if ((r4 & 1) != 0) {
                    int r7 = r02 << 1;
                    r2[r7] = r2[r7] | r6;
                }
                if ((65536 & r4) != 0) {
                    int r72 = (r02 << 1) + 1;
                    r2[r72] = r2[r72] | r6;
                }
                r6 <<= 2;
                r4 >>>= 1;
            }
        }
        this.value = r2;
        this.blocks = r1;
        this.len = (this.len << 1) - 1;
    }

    public void squareThisPreCalc() {
        int r2;
        int r0;
        if (isZero()) {
            return;
        }
        int length = this.value.length;
        int r1 = this.blocks;
        if (length >= (r1 << 1)) {
            for (int r12 = r1 - 1; r12 >= 0; r12--) {
                int[] r02 = this.value;
                int r22 = r12 << 1;
                short[] sArr = squaringTable;
                r02[r22 + 1] = sArr[(r02[r12] & 16711680) >>> 16] | (sArr[(r02[r12] & ViewCompat.MEASURED_STATE_MASK) >>> 24] << 16);
                r02[r22] = sArr[r02[r12] & 255] | (sArr[(r02[r12] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8] << 16);
            }
            r0 = this.blocks << 1;
        } else {
            int[] r03 = new int[r1 << 1];
            int r13 = 0;
            while (true) {
                r2 = this.blocks;
                if (r13 >= r2) {
                    break;
                }
                int r23 = r13 << 1;
                short[] sArr2 = squaringTable;
                int[] r7 = this.value;
                r03[r23] = sArr2[r7[r13] & 255] | (sArr2[(r7[r13] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8] << 16);
                r03[r23 + 1] = (sArr2[(r7[r13] & ViewCompat.MEASURED_STATE_MASK) >>> 24] << 16) | sArr2[(r7[r13] & 16711680) >>> 16];
                r13++;
            }
            this.value = r03;
            r0 = r2 << 1;
        }
        this.blocks = r0;
        this.len = (this.len << 1) - 1;
    }

    public GF2Polynomial subtract(GF2Polynomial gF2Polynomial) {
        return xor(gF2Polynomial);
    }

    public void subtractFromThis(GF2Polynomial gF2Polynomial) {
        expandN(gF2Polynomial.len);
        xorThisBy(gF2Polynomial);
    }

    public boolean testBit(int r5) {
        if (r5 >= 0) {
            if (r5 > this.len - 1) {
                return false;
            }
            return (bitMask[r5 & 31] & this.value[r5 >>> 5]) != 0;
        }
        throw new RuntimeException();
    }

    public byte[] toByteArray() {
        int r0 = ((this.len - 1) >> 3) + 1;
        int r1 = r0 & 3;
        byte[] bArr = new byte[r0];
        for (int r4 = 0; r4 < (r0 >> 2); r4++) {
            int r5 = (r0 - (r4 << 2)) - 1;
            int[] r7 = this.value;
            bArr[r5] = (byte) (255 & r7[r4]);
            bArr[r5 - 1] = (byte) ((r7[r4] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8);
            bArr[r5 - 2] = (byte) ((r7[r4] & 16711680) >>> 16);
            bArr[r5 - 3] = (byte) ((r7[r4] & ViewCompat.MEASURED_STATE_MASK) >>> 24);
        }
        for (int r3 = 0; r3 < r1; r3++) {
            int r02 = ((r1 - r3) - 1) << 3;
            bArr[r3] = (byte) ((this.value[this.blocks - 1] & (255 << r02)) >>> r02);
        }
        return bArr;
    }

    public BigInteger toFlexiBigInt() {
        return (this.len == 0 || isZero()) ? new BigInteger(0, new byte[0]) : new BigInteger(1, toByteArray());
    }

    public int[] toIntegerArray() {
        int r0 = this.blocks;
        int[] r1 = new int[r0];
        System.arraycopy(this.value, 0, r1, 0, r0);
        return r1;
    }

    public String toString(int r20) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String[] strArr = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
        String str = new String();
        if (r20 == 16) {
            for (int r3 = this.blocks - 1; r3 >= 0; r3--) {
                str = ((((((((str + cArr[(this.value[r3] >>> 28) & 15]) + cArr[(this.value[r3] >>> 24) & 15]) + cArr[(this.value[r3] >>> 20) & 15]) + cArr[(this.value[r3] >>> 16) & 15]) + cArr[(this.value[r3] >>> 12) & 15]) + cArr[(this.value[r3] >>> 8) & 15]) + cArr[(this.value[r3] >>> 4) & 15]) + cArr[this.value[r3] & 15]) + " ";
            }
        } else {
            for (int r2 = this.blocks - 1; r2 >= 0; r2--) {
                str = ((((((((str + strArr[(this.value[r2] >>> 28) & 15]) + strArr[(this.value[r2] >>> 24) & 15]) + strArr[(this.value[r2] >>> 20) & 15]) + strArr[(this.value[r2] >>> 16) & 15]) + strArr[(this.value[r2] >>> 12) & 15]) + strArr[(this.value[r2] >>> 8) & 15]) + strArr[(this.value[r2] >>> 4) & 15]) + strArr[this.value[r2] & 15]) + " ";
            }
        }
        return str;
    }

    public boolean vectorMult(GF2Polynomial gF2Polynomial) throws RuntimeException {
        if (this.len == gF2Polynomial.len) {
            boolean z = false;
            for (int r0 = 0; r0 < this.blocks; r0++) {
                int r2 = this.value[r0] & gF2Polynomial.value[r0];
                boolean[] zArr = parity;
                z = (((z ^ zArr[r2 & 255]) ^ zArr[(r2 >>> 8) & 255]) ^ zArr[(r2 >>> 16) & 255]) ^ zArr[(r2 >>> 24) & 255];
            }
            return z;
        }
        throw new RuntimeException();
    }

    public GF2Polynomial xor(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2;
        int min = Math.min(this.blocks, gF2Polynomial.blocks);
        int r3 = 0;
        if (this.len >= gF2Polynomial.len) {
            gF2Polynomial2 = new GF2Polynomial(this);
            while (r3 < min) {
                int[] r2 = gF2Polynomial2.value;
                r2[r3] = r2[r3] ^ gF2Polynomial.value[r3];
                r3++;
            }
        } else {
            gF2Polynomial2 = new GF2Polynomial(gF2Polynomial);
            while (r3 < min) {
                int[] r7 = gF2Polynomial2.value;
                r7[r3] = r7[r3] ^ this.value[r3];
                r3++;
            }
        }
        gF2Polynomial2.zeroUnusedBits();
        return gF2Polynomial2;
    }

    public void xorBit(int r5) throws RuntimeException {
        if (r5 < 0 || r5 > this.len - 1) {
            throw new RuntimeException();
        }
        int[] r0 = this.value;
        int r1 = r5 >>> 5;
        r0[r1] = bitMask[r5 & 31] ^ r0[r1];
    }

    public void xorThisBy(GF2Polynomial gF2Polynomial) {
        for (int r0 = 0; r0 < Math.min(this.blocks, gF2Polynomial.blocks); r0++) {
            int[] r1 = this.value;
            r1[r0] = r1[r0] ^ gF2Polynomial.value[r0];
        }
        zeroUnusedBits();
    }
}
