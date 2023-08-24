package org.bouncycastle.crypto.generators;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.GOST3410Parameters;
import org.bouncycastle.crypto.params.GOST3410ValidationParameters;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes5.dex */
public class GOST3410ParametersGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private SecureRandom init_random;
    private int size;
    private int typeproc;

    private int procedure_A(int r19, int r20, BigInteger[] bigIntegerArr, int r22) {
        BigInteger bigInteger;
        BigInteger[] bigIntegerArr2;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        int r222;
        int r17;
        int r1 = r19;
        while (true) {
            if (r1 >= 0 && r1 <= 65536) {
                break;
            }
            r1 = this.init_random.nextInt() / 32768;
        }
        int r4 = r20;
        while (true) {
            if (r4 >= 0 && r4 <= 65536 && r4 / 2 != 0) {
                break;
            }
            r4 = (this.init_random.nextInt() / 32768) + 1;
        }
        BigInteger bigInteger4 = new BigInteger(Integer.toString(r4));
        BigInteger bigInteger5 = new BigInteger("19381");
        BigInteger bigInteger6 = new BigInteger(Integer.toString(r1));
        int r12 = 0;
        BigInteger[] bigIntegerArr3 = {bigInteger6};
        int[] r6 = {r22};
        int r7 = 0;
        int r8 = 0;
        while (r6[r7] >= 17) {
            int length = r6.length + 1;
            int[] r9 = new int[length];
            System.arraycopy(r6, 0, r9, 0, r6.length);
            r6 = new int[length];
            System.arraycopy(r9, 0, r6, 0, length);
            r8 = r7 + 1;
            r6[r8] = r6[r7] / 2;
            r7 = r8;
        }
        BigInteger[] bigIntegerArr4 = new BigInteger[r8 + 1];
        int r11 = 16;
        bigIntegerArr4[r8] = new BigInteger("8003", 16);
        int r92 = r8 - 1;
        int r10 = 0;
        while (true) {
            if (r10 >= r8) {
                bigInteger = bigIntegerArr3[0];
                break;
            }
            int r122 = r6[r92] / r11;
            while (true) {
                int length2 = bigIntegerArr3.length;
                BigInteger[] bigIntegerArr5 = new BigInteger[length2];
                System.arraycopy(bigIntegerArr3, r12, bigIntegerArr5, r12, bigIntegerArr3.length);
                bigIntegerArr2 = new BigInteger[r122 + 1];
                System.arraycopy(bigIntegerArr5, r12, bigIntegerArr2, r12, length2);
                int r42 = 0;
                while (r42 < r122) {
                    int r13 = r42 + 1;
                    bigIntegerArr2[r13] = bigIntegerArr2[r42].multiply(bigInteger5).add(bigInteger4).mod(TWO.pow(r11));
                    r42 = r13;
                }
                BigInteger bigInteger7 = new BigInteger(SessionDescription.SUPPORTED_SDP_VERSION);
                for (int r132 = 0; r132 < r122; r132++) {
                    bigInteger7 = bigInteger7.add(bigIntegerArr2[r132].multiply(TWO.pow(r132 * 16)));
                }
                bigIntegerArr2[r12] = bigIntegerArr2[r122];
                BigInteger bigInteger8 = TWO;
                int r14 = r92 + 1;
                BigInteger add = bigInteger8.pow(r6[r92] - 1).divide(bigIntegerArr4[r14]).add(bigInteger8.pow(r6[r92] - 1).multiply(bigInteger7).divide(bigIntegerArr4[r14].multiply(bigInteger8.pow(r122 * 16))));
                BigInteger mod = add.mod(bigInteger8);
                BigInteger bigInteger9 = ONE;
                if (mod.compareTo(bigInteger9) == 0) {
                    add = add.add(bigInteger9);
                }
                int r43 = 0;
                while (true) {
                    bigInteger2 = bigInteger4;
                    bigInteger3 = bigInteger5;
                    long j = r43;
                    r222 = r8;
                    BigInteger multiply = bigIntegerArr4[r14].multiply(add.add(BigInteger.valueOf(j)));
                    BigInteger bigInteger10 = ONE;
                    bigIntegerArr4[r92] = multiply.add(bigInteger10);
                    BigInteger bigInteger11 = bigIntegerArr4[r92];
                    BigInteger bigInteger12 = TWO;
                    r17 = r122;
                    if (bigInteger11.compareTo(bigInteger12.pow(r6[r92])) == 1) {
                        break;
                    }
                    if (bigInteger12.modPow(bigIntegerArr4[r14].multiply(add.add(BigInteger.valueOf(j))), bigIntegerArr4[r92]).compareTo(bigInteger10) == 0 && bigInteger12.modPow(add.add(BigInteger.valueOf(j)), bigIntegerArr4[r92]).compareTo(bigInteger10) != 0) {
                        break;
                    }
                    r43 += 2;
                    r8 = r222;
                    bigInteger5 = bigInteger3;
                    bigInteger4 = bigInteger2;
                    r122 = r17;
                }
                r8 = r222;
                bigInteger5 = bigInteger3;
                bigIntegerArr3 = bigIntegerArr2;
                bigInteger4 = bigInteger2;
                r122 = r17;
                r12 = 0;
                r11 = 16;
            }
            r92--;
            if (r92 < 0) {
                bigIntegerArr[0] = bigIntegerArr4[0];
                bigIntegerArr[1] = bigIntegerArr4[1];
                bigInteger = bigIntegerArr2[0];
                break;
            }
            r10++;
            r8 = r222;
            bigInteger5 = bigInteger3;
            bigIntegerArr3 = bigIntegerArr2;
            bigInteger4 = bigInteger2;
            r12 = 0;
            r11 = 16;
        }
        return bigInteger.intValue();
    }

    private long procedure_Aa(long j, long j2, BigInteger[] bigIntegerArr, int r23) {
        BigInteger bigInteger;
        BigInteger[] bigIntegerArr2;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        int r232;
        long j3 = j;
        while (true) {
            if (j3 >= 0 && j3 <= 4294967296L) {
                break;
            }
            j3 = this.init_random.nextInt() * 2;
        }
        long j4 = j2;
        while (true) {
            if (j4 >= 0 && j4 <= 4294967296L && j4 / 2 != 0) {
                break;
            }
            j4 = (this.init_random.nextInt() * 2) + 1;
        }
        BigInteger bigInteger4 = new BigInteger(Long.toString(j4));
        BigInteger bigInteger5 = new BigInteger("97781173");
        BigInteger bigInteger6 = new BigInteger(Long.toString(j3));
        int r1 = 0;
        BigInteger[] bigIntegerArr3 = {bigInteger6};
        int[] r2 = {r23};
        int r6 = 0;
        int r7 = 0;
        while (r2[r6] >= 33) {
            int length = r2.length + 1;
            int[] r8 = new int[length];
            System.arraycopy(r2, 0, r8, 0, r2.length);
            r2 = new int[length];
            System.arraycopy(r8, 0, r2, 0, length);
            r7 = r6 + 1;
            r2[r7] = r2[r6] / 2;
            r6 = r7;
        }
        BigInteger[] bigIntegerArr4 = new BigInteger[r7 + 1];
        bigIntegerArr4[r7] = new BigInteger("8000000B", 16);
        int r82 = r7 - 1;
        int r10 = 0;
        while (true) {
            if (r10 >= r7) {
                bigInteger = bigIntegerArr3[0];
                break;
            }
            int r12 = 32;
            int r11 = r2[r82] / 32;
            while (true) {
                int length2 = bigIntegerArr3.length;
                BigInteger[] bigIntegerArr5 = new BigInteger[length2];
                System.arraycopy(bigIntegerArr3, r1, bigIntegerArr5, r1, bigIntegerArr3.length);
                bigIntegerArr2 = new BigInteger[r11 + 1];
                System.arraycopy(bigIntegerArr5, r1, bigIntegerArr2, r1, length2);
                int r5 = 0;
                while (r5 < r11) {
                    int r13 = r5 + 1;
                    bigIntegerArr2[r13] = bigIntegerArr2[r5].multiply(bigInteger5).add(bigInteger4).mod(TWO.pow(r12));
                    r5 = r13;
                }
                BigInteger bigInteger7 = new BigInteger(SessionDescription.SUPPORTED_SDP_VERSION);
                for (int r132 = 0; r132 < r11; r132++) {
                    bigInteger7 = bigInteger7.add(bigIntegerArr2[r132].multiply(TWO.pow(r132 * 32)));
                }
                bigIntegerArr2[r1] = bigIntegerArr2[r11];
                BigInteger bigInteger8 = TWO;
                int r14 = r82 + 1;
                BigInteger add = bigInteger8.pow(r2[r82] - 1).divide(bigIntegerArr4[r14]).add(bigInteger8.pow(r2[r82] - 1).multiply(bigInteger7).divide(bigIntegerArr4[r14].multiply(bigInteger8.pow(r11 * 32))));
                BigInteger mod = add.mod(bigInteger8);
                BigInteger bigInteger9 = ONE;
                if (mod.compareTo(bigInteger9) == 0) {
                    add = add.add(bigInteger9);
                }
                int r52 = 0;
                while (true) {
                    long j5 = r52;
                    bigInteger2 = bigInteger4;
                    BigInteger multiply = bigIntegerArr4[r14].multiply(add.add(BigInteger.valueOf(j5)));
                    BigInteger bigInteger10 = ONE;
                    bigIntegerArr4[r82] = multiply.add(bigInteger10);
                    BigInteger bigInteger11 = bigIntegerArr4[r82];
                    bigInteger3 = bigInteger5;
                    BigInteger bigInteger12 = TWO;
                    r232 = r7;
                    if (bigInteger11.compareTo(bigInteger12.pow(r2[r82])) == 1) {
                        break;
                    }
                    if (bigInteger12.modPow(bigIntegerArr4[r14].multiply(add.add(BigInteger.valueOf(j5))), bigIntegerArr4[r82]).compareTo(bigInteger10) == 0 && bigInteger12.modPow(add.add(BigInteger.valueOf(j5)), bigIntegerArr4[r82]).compareTo(bigInteger10) != 0) {
                        break;
                    }
                    r52 += 2;
                    bigInteger4 = bigInteger2;
                    r7 = r232;
                    bigInteger5 = bigInteger3;
                }
                bigInteger4 = bigInteger2;
                r7 = r232;
                bigIntegerArr3 = bigIntegerArr2;
                bigInteger5 = bigInteger3;
                r1 = 0;
                r12 = 32;
            }
            r82--;
            if (r82 < 0) {
                bigIntegerArr[0] = bigIntegerArr4[0];
                bigIntegerArr[1] = bigIntegerArr4[1];
                bigInteger = bigIntegerArr2[0];
                break;
            }
            r10++;
            bigInteger4 = bigInteger2;
            r7 = r232;
            bigIntegerArr3 = bigIntegerArr2;
            bigInteger5 = bigInteger3;
            r1 = 0;
        }
        return bigInteger.longValue();
    }

    private void procedure_B(int r17, int r18, BigInteger[] bigIntegerArr) {
        int r1 = r17;
        while (true) {
            if (r1 >= 0 && r1 <= 65536) {
                break;
            }
            r1 = this.init_random.nextInt() / 32768;
        }
        int r4 = r18;
        while (true) {
            if (r4 >= 0 && r4 <= 65536 && r4 / 2 != 0) {
                break;
            }
            r4 = (this.init_random.nextInt() / 32768) + 1;
        }
        BigInteger[] bigIntegerArr2 = new BigInteger[2];
        BigInteger bigInteger = new BigInteger(Integer.toString(r4));
        BigInteger bigInteger2 = new BigInteger("19381");
        int procedure_A = procedure_A(r1, r4, bigIntegerArr2, 256);
        char c = 0;
        BigInteger bigInteger3 = bigIntegerArr2[0];
        int procedure_A2 = procedure_A(procedure_A, r4, bigIntegerArr2, 512);
        BigInteger bigInteger4 = bigIntegerArr2[0];
        BigInteger[] bigIntegerArr3 = new BigInteger[65];
        bigIntegerArr3[0] = new BigInteger(Integer.toString(procedure_A2));
        while (true) {
            int r12 = 0;
            while (r12 < 64) {
                int r2 = r12 + 1;
                bigIntegerArr3[r2] = bigIntegerArr3[r12].multiply(bigInteger2).add(bigInteger).mod(TWO.pow(16));
                r12 = r2;
            }
            BigInteger bigInteger5 = new BigInteger(SessionDescription.SUPPORTED_SDP_VERSION);
            for (int r3 = 0; r3 < 64; r3++) {
                bigInteger5 = bigInteger5.add(bigIntegerArr3[r3].multiply(TWO.pow(r3 * 16)));
            }
            bigIntegerArr3[c] = bigIntegerArr3[64];
            BigInteger bigInteger6 = TWO;
            int r122 = 1024;
            BigInteger add = bigInteger6.pow(AnalyticsListener.EVENT_DRM_KEYS_LOADED).divide(bigInteger3.multiply(bigInteger4)).add(bigInteger6.pow(AnalyticsListener.EVENT_DRM_KEYS_LOADED).multiply(bigInteger5).divide(bigInteger3.multiply(bigInteger4).multiply(bigInteger6.pow(1024))));
            BigInteger mod = add.mod(bigInteger6);
            BigInteger bigInteger7 = ONE;
            if (mod.compareTo(bigInteger7) == 0) {
                add = add.add(bigInteger7);
            }
            BigInteger bigInteger8 = add;
            int r13 = 0;
            while (true) {
                long j = r13;
                BigInteger multiply = bigInteger3.multiply(bigInteger4).multiply(bigInteger8.add(BigInteger.valueOf(j)));
                BigInteger bigInteger9 = ONE;
                BigInteger add2 = multiply.add(bigInteger9);
                BigInteger bigInteger10 = TWO;
                if (add2.compareTo(bigInteger10.pow(r122)) == 1) {
                    break;
                } else if (bigInteger10.modPow(bigInteger3.multiply(bigInteger4).multiply(bigInteger8.add(BigInteger.valueOf(j))), add2).compareTo(bigInteger9) == 0 && bigInteger10.modPow(bigInteger3.multiply(bigInteger8.add(BigInteger.valueOf(j))), add2).compareTo(bigInteger9) != 0) {
                    bigIntegerArr[0] = add2;
                    bigIntegerArr[1] = bigInteger3;
                    return;
                } else {
                    r13 += 2;
                    r122 = 1024;
                }
            }
            c = 0;
        }
    }

    private void procedure_Bb(long j, long j2, BigInteger[] bigIntegerArr) {
        long j3 = j;
        while (true) {
            if (j3 >= 0 && j3 <= 4294967296L) {
                break;
            }
            j3 = this.init_random.nextInt() * 2;
        }
        long j4 = j2;
        while (true) {
            if (j4 >= 0 && j4 <= 4294967296L && j4 / 2 != 0) {
                break;
            }
            j4 = (this.init_random.nextInt() * 2) + 1;
        }
        BigInteger[] bigIntegerArr2 = new BigInteger[2];
        BigInteger bigInteger = new BigInteger(Long.toString(j4));
        BigInteger bigInteger2 = new BigInteger("97781173");
        long j5 = j4;
        long procedure_Aa = procedure_Aa(j3, j5, bigIntegerArr2, 256);
        char c = 0;
        BigInteger bigInteger3 = bigIntegerArr2[0];
        long procedure_Aa2 = procedure_Aa(procedure_Aa, j5, bigIntegerArr2, 512);
        BigInteger bigInteger4 = bigIntegerArr2[0];
        BigInteger[] bigIntegerArr3 = new BigInteger[33];
        bigIntegerArr3[0] = new BigInteger(Long.toString(procedure_Aa2));
        while (true) {
            int r0 = 0;
            while (r0 < 32) {
                int r2 = r0 + 1;
                bigIntegerArr3[r2] = bigIntegerArr3[r0].multiply(bigInteger2).add(bigInteger).mod(TWO.pow(32));
                r0 = r2;
            }
            BigInteger bigInteger5 = new BigInteger(SessionDescription.SUPPORTED_SDP_VERSION);
            for (int r22 = 0; r22 < 32; r22++) {
                bigInteger5 = bigInteger5.add(bigIntegerArr3[r22].multiply(TWO.pow(r22 * 32)));
            }
            bigIntegerArr3[c] = bigIntegerArr3[32];
            BigInteger bigInteger6 = TWO;
            int r11 = 1024;
            BigInteger add = bigInteger6.pow(AnalyticsListener.EVENT_DRM_KEYS_LOADED).divide(bigInteger3.multiply(bigInteger4)).add(bigInteger6.pow(AnalyticsListener.EVENT_DRM_KEYS_LOADED).multiply(bigInteger5).divide(bigInteger3.multiply(bigInteger4).multiply(bigInteger6.pow(1024))));
            BigInteger mod = add.mod(bigInteger6);
            BigInteger bigInteger7 = ONE;
            if (mod.compareTo(bigInteger7) == 0) {
                add = add.add(bigInteger7);
            }
            int r1 = 0;
            while (true) {
                long j6 = r1;
                BigInteger multiply = bigInteger3.multiply(bigInteger4).multiply(add.add(BigInteger.valueOf(j6)));
                BigInteger bigInteger8 = ONE;
                BigInteger add2 = multiply.add(bigInteger8);
                BigInteger bigInteger9 = TWO;
                if (add2.compareTo(bigInteger9.pow(r11)) == 1) {
                    break;
                } else if (bigInteger9.modPow(bigInteger3.multiply(bigInteger4).multiply(add.add(BigInteger.valueOf(j6))), add2).compareTo(bigInteger8) == 0 && bigInteger9.modPow(bigInteger3.multiply(add.add(BigInteger.valueOf(j6))), add2).compareTo(bigInteger8) != 0) {
                    bigIntegerArr[0] = add2;
                    bigIntegerArr[1] = bigInteger3;
                    return;
                } else {
                    r1 += 2;
                    r11 = 1024;
                }
            }
            c = 0;
        }
    }

    private BigInteger procedure_C(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger subtract = bigInteger.subtract(ONE);
        BigInteger divide = subtract.divide(bigInteger2);
        int bitLength = bigInteger.bitLength();
        while (true) {
            BigInteger createRandomBigInteger = BigIntegers.createRandomBigInteger(bitLength, this.init_random);
            BigInteger bigInteger3 = ONE;
            if (createRandomBigInteger.compareTo(bigInteger3) > 0 && createRandomBigInteger.compareTo(subtract) < 0) {
                BigInteger modPow = createRandomBigInteger.modPow(divide, bigInteger);
                if (modPow.compareTo(bigInteger3) != 0) {
                    return modPow;
                }
            }
        }
    }

    public GOST3410Parameters generateParameters() {
        BigInteger[] bigIntegerArr = new BigInteger[2];
        if (this.typeproc == 1) {
            int nextInt = this.init_random.nextInt();
            int nextInt2 = this.init_random.nextInt();
            int r6 = this.size;
            if (r6 == 512) {
                procedure_A(nextInt, nextInt2, bigIntegerArr, 512);
            } else if (r6 != 1024) {
                throw new IllegalArgumentException("Ooops! key size 512 or 1024 bit.");
            } else {
                procedure_B(nextInt, nextInt2, bigIntegerArr);
            }
            BigInteger bigInteger = bigIntegerArr[0];
            BigInteger bigInteger2 = bigIntegerArr[1];
            return new GOST3410Parameters(bigInteger, bigInteger2, procedure_C(bigInteger, bigInteger2), new GOST3410ValidationParameters(nextInt, nextInt2));
        }
        long nextLong = this.init_random.nextLong();
        long nextLong2 = this.init_random.nextLong();
        int r1 = this.size;
        if (r1 == 512) {
            procedure_Aa(nextLong, nextLong2, bigIntegerArr, 512);
        } else if (r1 != 1024) {
            throw new IllegalStateException("Ooops! key size 512 or 1024 bit.");
        } else {
            procedure_Bb(nextLong, nextLong2, bigIntegerArr);
        }
        BigInteger bigInteger3 = bigIntegerArr[0];
        BigInteger bigInteger4 = bigIntegerArr[1];
        return new GOST3410Parameters(bigInteger3, bigInteger4, procedure_C(bigInteger3, bigInteger4), new GOST3410ValidationParameters(nextLong, nextLong2));
    }

    public void init(int r1, int r2, SecureRandom secureRandom) {
        this.size = r1;
        this.typeproc = r2;
        this.init_random = secureRandom;
    }
}
