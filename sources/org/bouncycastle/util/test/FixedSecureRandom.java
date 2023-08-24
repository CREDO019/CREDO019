package org.bouncycastle.util.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Provider;
import java.security.SecureRandom;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes4.dex */
public class FixedSecureRandom extends SecureRandom {
    private static final boolean isAndroidStyle;
    private static final boolean isClasspathStyle;
    private static final boolean isRegularStyle;
    private byte[] _data;
    private int _index;
    private static java.math.BigInteger REGULAR = new java.math.BigInteger("01020304ffffffff0506070811111111", 16);
    private static java.math.BigInteger ANDROID = new java.math.BigInteger("1111111105060708ffffffff01020304", 16);
    private static java.math.BigInteger CLASSPATH = new java.math.BigInteger("3020104ffffffff05060708111111", 16);

    /* loaded from: classes4.dex */
    public static class BigInteger extends Source {
        public BigInteger(int r1, String str) {
            super(FixedSecureRandom.expandToBitLength(r1, Hex.decode(str)));
        }

        public BigInteger(int r1, byte[] bArr) {
            super(FixedSecureRandom.expandToBitLength(r1, bArr));
        }

        public BigInteger(String str) {
            this(Hex.decode(str));
        }

        public BigInteger(byte[] bArr) {
            super(bArr);
        }
    }

    /* loaded from: classes4.dex */
    public static class Data extends Source {
        public Data(byte[] bArr) {
            super(bArr);
        }
    }

    /* loaded from: classes4.dex */
    private static class DummyProvider extends Provider {
        DummyProvider() {
            super("BCFIPS_FIXED_RNG", 1.0d, "BCFIPS Fixed Secure Random Provider");
        }
    }

    /* loaded from: classes4.dex */
    private static class RandomChecker extends SecureRandom {
        byte[] data;
        int index;

        RandomChecker() {
            super(null, new DummyProvider());
            this.data = Hex.decode("01020304ffffffff0506070811111111");
            this.index = 0;
        }

        @Override // java.security.SecureRandom, java.util.Random
        public void nextBytes(byte[] bArr) {
            System.arraycopy(this.data, this.index, bArr, 0, bArr.length);
            this.index += bArr.length;
        }
    }

    /* loaded from: classes4.dex */
    public static class Source {
        byte[] data;

        Source(byte[] bArr) {
            this.data = bArr;
        }
    }

    static {
        java.math.BigInteger bigInteger = new java.math.BigInteger(128, new RandomChecker());
        java.math.BigInteger bigInteger2 = new java.math.BigInteger(120, new RandomChecker());
        isAndroidStyle = bigInteger.equals(ANDROID);
        isRegularStyle = bigInteger.equals(REGULAR);
        isClasspathStyle = bigInteger2.equals(CLASSPATH);
    }

    public FixedSecureRandom(byte[] bArr) {
        this(new Source[]{new Data(bArr)});
    }

    public FixedSecureRandom(Source[] sourceArr) {
        super(null, new DummyProvider());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int r4 = 0;
        if (isRegularStyle) {
            if (isClasspathStyle) {
                while (r4 != sourceArr.length) {
                    try {
                        if (sourceArr[r4] instanceof BigInteger) {
                            byte[] bArr = sourceArr[r4].data;
                            int length = bArr.length - (bArr.length % 4);
                            for (int length2 = (bArr.length - length) - 1; length2 >= 0; length2--) {
                                byteArrayOutputStream.write(bArr[length2]);
                            }
                            for (int length3 = bArr.length - length; length3 < bArr.length; length3 += 4) {
                                byteArrayOutputStream.write(bArr, length3, 4);
                            }
                        } else {
                            byteArrayOutputStream.write(sourceArr[r4].data);
                        }
                        r4++;
                    } catch (IOException unused) {
                        throw new IllegalArgumentException("can't save value source.");
                    }
                }
            } else {
                while (r4 != sourceArr.length) {
                    try {
                        byteArrayOutputStream.write(sourceArr[r4].data);
                        r4++;
                    } catch (IOException unused2) {
                        throw new IllegalArgumentException("can't save value source.");
                    }
                }
            }
        } else if (!isAndroidStyle) {
            throw new IllegalStateException("Unrecognized BigInteger implementation");
        } else {
            for (int r1 = 0; r1 != sourceArr.length; r1++) {
                try {
                    if (sourceArr[r1] instanceof BigInteger) {
                        byte[] bArr2 = sourceArr[r1].data;
                        int length4 = bArr2.length - (bArr2.length % 4);
                        int r7 = 0;
                        while (r7 < length4) {
                            r7 += 4;
                            byteArrayOutputStream.write(bArr2, bArr2.length - r7, 4);
                        }
                        if (bArr2.length - length4 != 0) {
                            for (int r72 = 0; r72 != 4 - (bArr2.length - length4); r72++) {
                                byteArrayOutputStream.write(0);
                            }
                        }
                        for (int r73 = 0; r73 != bArr2.length - length4; r73++) {
                            byteArrayOutputStream.write(bArr2[length4 + r73]);
                        }
                    } else {
                        byteArrayOutputStream.write(sourceArr[r1].data);
                    }
                } catch (IOException unused3) {
                    throw new IllegalArgumentException("can't save value source.");
                }
            }
        }
        this._data = byteArrayOutputStream.toByteArray();
    }

    public FixedSecureRandom(byte[][] bArr) {
        this(buildDataArray(bArr));
    }

    private static Data[] buildDataArray(byte[][] bArr) {
        Data[] dataArr = new Data[bArr.length];
        for (int r1 = 0; r1 != bArr.length; r1++) {
            dataArr[r1] = new Data(bArr[r1]);
        }
        return dataArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] expandToBitLength(int r4, byte[] bArr) {
        int r42;
        int r43;
        int r0 = (r4 + 7) / 8;
        if (r0 <= bArr.length) {
            if (isAndroidStyle && r4 < bArr.length * 8 && (r42 = r4 % 8) != 0) {
                Pack.intToBigEndian(Pack.bigEndianToInt(bArr, 0) << (8 - r42), bArr, 0);
            }
            return bArr;
        }
        byte[] bArr2 = new byte[r0];
        System.arraycopy(bArr, 0, bArr2, r0 - bArr.length, bArr.length);
        if (isAndroidStyle && (r43 = r4 % 8) != 0) {
            Pack.intToBigEndian(Pack.bigEndianToInt(bArr2, 0) << (8 - r43), bArr2, 0);
        }
        return bArr2;
    }

    private int nextValue() {
        byte[] bArr = this._data;
        int r1 = this._index;
        this._index = r1 + 1;
        return bArr[r1] & 255;
    }

    @Override // java.security.SecureRandom
    public byte[] generateSeed(int r1) {
        byte[] bArr = new byte[r1];
        nextBytes(bArr);
        return bArr;
    }

    public boolean isExhausted() {
        return this._index == this._data.length;
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void nextBytes(byte[] bArr) {
        System.arraycopy(this._data, this._index, bArr, 0, bArr.length);
        this._index += bArr.length;
    }

    @Override // java.util.Random
    public int nextInt() {
        return (nextValue() << 24) | 0 | (nextValue() << 16) | (nextValue() << 8) | nextValue();
    }

    @Override // java.util.Random
    public long nextLong() {
        return (nextValue() << 56) | 0 | (nextValue() << 48) | (nextValue() << 40) | (nextValue() << 32) | (nextValue() << 24) | (nextValue() << 16) | (nextValue() << 8) | nextValue();
    }
}
