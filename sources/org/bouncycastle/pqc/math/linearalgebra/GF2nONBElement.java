package org.bouncycastle.pqc.math.linearalgebra;

import android.support.p001v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.primitives.Longs;
import java.math.BigInteger;
import java.security.SecureRandom;
import kotlin.time.DurationKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import okhttp3.internal.p026ws.WebSocketProtocol;
import org.apache.commons.p028io.FileUtils;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class GF2nONBElement extends GF2nElement {
    private static final int MAXLONG = 64;
    private int mBit;
    private int mLength;
    private long[] mPol;
    private static final long[] mBitmask = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, PlaybackStateCompat.ACTION_PLAY_FROM_URI, 16384, PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID, PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH, PlaybackStateCompat.ACTION_PREPARE_FROM_URI, PlaybackStateCompat.ACTION_SET_REPEAT_MODE, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, 1048576, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, PlaybackStateCompat.ACTION_SET_PLAYBACK_SPEED, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, FileUtils.ONE_GB, 2147483648L, 4294967296L, 8589934592L, 17179869184L, 34359738368L, 68719476736L, 137438953472L, 274877906944L, 549755813888L, FileUtils.ONE_TB, 2199023255552L, 4398046511104L, 8796093022208L, 17592186044416L, 35184372088832L, 70368744177664L, 140737488355328L, 281474976710656L, 562949953421312L, FileUtils.ONE_PB, 2251799813685248L, 4503599627370496L, 9007199254740992L, 18014398509481984L, 36028797018963968L, 72057594037927936L, 144115188075855872L, 288230376151711744L, 576460752303423488L, 1152921504606846976L, LockFreeTaskQueueCore.CLOSED_MASK, Longs.MAX_POWER_OF_TWO, Long.MIN_VALUE};
    private static final long[] mMaxmask = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, WebSocketProtocol.PAYLOAD_SHORT_MAX, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, LockFreeTaskQueueCore.HEAD_MASK, 2147483647L, BodyPartID.bodyIdMax, 8589934591L, 17179869183L, 34359738367L, 68719476735L, 137438953471L, 274877906943L, 549755813887L, 1099511627775L, 2199023255551L, 4398046511103L, 8796093022207L, 17592186044415L, 35184372088831L, 70368744177663L, 140737488355327L, 281474976710655L, 562949953421311L, 1125899906842623L, 2251799813685247L, 4503599627370495L, 9007199254740991L, 18014398509481983L, 36028797018963967L, 72057594037927935L, 144115188075855871L, 288230376151711743L, 576460752303423487L, 1152921504606846975L, 2305843009213693951L, DurationKt.MAX_MILLIS, Long.MAX_VALUE, -1};
    private static final int[] mIBY64 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

    public GF2nONBElement(GF2nONBElement gF2nONBElement) {
        this.mField = gF2nONBElement.mField;
        this.mDegree = this.mField.getDegree();
        this.mLength = ((GF2nONBField) this.mField).getONBLength();
        this.mBit = ((GF2nONBField) this.mField).getONBBit();
        this.mPol = new long[this.mLength];
        assign(gF2nONBElement.getElement());
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, BigInteger bigInteger) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        assign(bigInteger);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, SecureRandom secureRandom) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        int r6 = this.mLength;
        long[] jArr = new long[r6];
        this.mPol = jArr;
        if (r6 <= 1) {
            jArr[0] = secureRandom.nextLong();
            long[] jArr2 = this.mPol;
            jArr2[0] = jArr2[0] >>> (64 - this.mBit);
            return;
        }
        for (int r1 = 0; r1 < this.mLength - 1; r1++) {
            this.mPol[r1] = secureRandom.nextLong();
        }
        this.mPol[this.mLength - 1] = secureRandom.nextLong() >>> (64 - this.mBit);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, byte[] bArr) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        assign(bArr);
    }

    private GF2nONBElement(GF2nONBField gF2nONBField, long[] jArr) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = jArr;
    }

    public static GF2nONBElement ONE(GF2nONBField gF2nONBField) {
        int oNBLength = gF2nONBField.getONBLength();
        long[] jArr = new long[oNBLength];
        int r2 = 0;
        while (true) {
            int r3 = oNBLength - 1;
            if (r2 >= r3) {
                jArr[r3] = mMaxmask[gF2nONBField.getONBBit() - 1];
                return new GF2nONBElement(gF2nONBField, jArr);
            }
            jArr[r2] = -1;
            r2++;
        }
    }

    public static GF2nONBElement ZERO(GF2nONBField gF2nONBField) {
        return new GF2nONBElement(gF2nONBField, new long[gF2nONBField.getONBLength()]);
    }

    private void assign(BigInteger bigInteger) {
        assign(bigInteger.toByteArray());
    }

    private void assign(byte[] bArr) {
        this.mPol = new long[this.mLength];
        for (int r0 = 0; r0 < bArr.length; r0++) {
            long[] jArr = this.mPol;
            int r2 = r0 >>> 3;
            jArr[r2] = jArr[r2] | ((bArr[(bArr.length - 1) - r0] & 255) << ((r0 & 7) << 3));
        }
    }

    private void assign(long[] jArr) {
        System.arraycopy(jArr, 0, this.mPol, 0, this.mLength);
    }

    private long[] getElement() {
        long[] jArr = this.mPol;
        long[] jArr2 = new long[jArr.length];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        return jArr2;
    }

    private long[] getElementReverseOrder() {
        long[] jArr = new long[this.mPol.length];
        for (int r1 = 0; r1 < this.mDegree; r1++) {
            if (testBit((this.mDegree - r1) - 1)) {
                int r2 = r1 >>> 6;
                jArr[r2] = jArr[r2] | mBitmask[r1 & 63];
            }
        }
        return jArr;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public GFElement add(GFElement gFElement) throws RuntimeException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.addToThis(gFElement);
        return gF2nONBElement;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public void addToThis(GFElement gFElement) throws RuntimeException {
        if (!(gFElement instanceof GF2nONBElement)) {
            throw new RuntimeException();
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) gFElement;
        if (!this.mField.equals(gF2nONBElement.mField)) {
            throw new RuntimeException();
        }
        for (int r0 = 0; r0 < this.mLength; r0++) {
            long[] jArr = this.mPol;
            jArr[r0] = jArr[r0] ^ gF2nONBElement.mPol[r0];
        }
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    void assignOne() {
        int r0 = 0;
        while (true) {
            int r1 = this.mLength;
            if (r0 >= r1 - 1) {
                this.mPol[r1 - 1] = mMaxmask[this.mBit - 1];
                return;
            } else {
                this.mPol[r0] = -1;
                r0++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public void assignZero() {
        this.mPol = new long[this.mLength];
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement, org.bouncycastle.pqc.math.linearalgebra.GFElement
    public Object clone() {
        return new GF2nONBElement(this);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nONBElement)) {
            return false;
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) obj;
        for (int r1 = 0; r1 < this.mLength; r1++) {
            if (this.mPol[r1] != gF2nONBElement.mPol[r1]) {
                return false;
            }
        }
        return true;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public int hashCode() {
        return Arrays.hashCode(this.mPol);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement increase() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.increaseThis();
        return gF2nONBElement;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public void increaseThis() {
        addToThis(ONE((GF2nONBField) this.mField));
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public GFElement invert() throws ArithmeticException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.invertThis();
        return gF2nONBElement;
    }

    public void invertThis() throws ArithmeticException {
        if (isZero()) {
            throw new ArithmeticException();
        }
        int r0 = 31;
        boolean z = false;
        while (!z && r0 >= 0) {
            if (((this.mDegree - 1) & mBitmask[r0]) != 0) {
                z = true;
            }
            r0--;
        }
        ZERO((GF2nONBField) this.mField);
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        int r5 = 1;
        for (int r02 = (r0 + 1) - 1; r02 >= 0; r02--) {
            GF2nElement gF2nElement = (GF2nElement) gF2nONBElement.clone();
            for (int r7 = 1; r7 <= r5; r7++) {
                gF2nElement.squareThis();
            }
            gF2nONBElement.multiplyThisBy(gF2nElement);
            r5 <<= 1;
            if (((this.mDegree - 1) & mBitmask[r02]) != 0) {
                gF2nONBElement.squareThis();
                gF2nONBElement.multiplyThisBy(this);
                r5++;
            }
        }
        gF2nONBElement.squareThis();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public boolean isOne() {
        int r4;
        boolean z = false;
        int r2 = 0;
        boolean z2 = true;
        while (true) {
            r4 = this.mLength;
            if (r2 >= r4 - 1 || !z2) {
                break;
            }
            z2 = z2 && (this.mPol[r2] & (-1)) == -1;
            r2++;
        }
        if (z2) {
            if (z2) {
                long j = this.mPol[r4 - 1];
                long[] jArr = mMaxmask;
                int r5 = this.mBit;
                if ((j & jArr[r5 - 1]) == jArr[r5 - 1]) {
                    z = true;
                }
            }
            return z;
        }
        return z2;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public boolean isZero() {
        boolean z = true;
        for (int r2 = 0; r2 < this.mLength && z; r2++) {
            z = z && (this.mPol[r2] & (-1)) == 0;
        }
        return z;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public GFElement multiply(GFElement gFElement) throws RuntimeException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.multiplyThisBy(gFElement);
        return gF2nONBElement;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public void multiplyThisBy(GFElement gFElement) throws RuntimeException {
        if (!(gFElement instanceof GF2nONBElement)) {
            throw new RuntimeException("The elements have different representation: not yet implemented");
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) gFElement;
        if (!this.mField.equals(gF2nONBElement.mField)) {
            throw new RuntimeException();
        }
        if (equals(gFElement)) {
            squareThis();
            return;
        }
        long[] jArr = this.mPol;
        long[] jArr2 = gF2nONBElement.mPol;
        long[] jArr3 = new long[this.mLength];
        int[][] r4 = ((GF2nONBField) this.mField).mMult;
        int r5 = this.mLength - 1;
        long[] jArr4 = mBitmask;
        long j = jArr4[63];
        long j2 = jArr4[this.mBit - 1];
        char c = 0;
        int r13 = 0;
        while (r13 < this.mDegree) {
            int r14 = 0;
            boolean z = false;
            while (r14 < this.mDegree) {
                int[] r6 = mIBY64;
                int r18 = r6[r14];
                int r20 = r6[r4[r14][c]];
                int r21 = r4[r14][c] & 63;
                long j3 = jArr[r18];
                long[] jArr5 = mBitmask;
                if ((j3 & jArr5[r14 & 63]) != 0) {
                    if ((jArr2[r20] & jArr5[r21]) != 0) {
                        z = !z;
                    }
                    if (r4[r14][1] != -1) {
                        if ((jArr2[r6[r4[r14][1]]] & jArr5[r4[r14][1] & 63]) != 0) {
                            z = !z;
                        }
                        r14++;
                        c = 0;
                    }
                }
                r14++;
                c = 0;
            }
            int r62 = mIBY64[r13];
            int r9 = r13 & 63;
            if (z) {
                jArr3[r62] = jArr3[r62] ^ mBitmask[r9];
            }
            if (this.mLength > 1) {
                boolean z2 = (jArr[r5] & 1) == 1;
                int r92 = r5 - 1;
                int r182 = r92;
                while (r182 >= 0) {
                    boolean z3 = (jArr[r182] & 1) != 0;
                    jArr[r182] = jArr[r182] >>> 1;
                    if (z2) {
                        jArr[r182] = jArr[r182] ^ j;
                    }
                    r182--;
                    z2 = z3;
                }
                jArr[r5] = jArr[r5] >>> 1;
                if (z2) {
                    jArr[r5] = jArr[r5] ^ j2;
                }
                boolean z4 = (jArr2[r5] & 1) == 1;
                while (r92 >= 0) {
                    boolean z5 = (jArr2[r92] & 1) != 0;
                    jArr2[r92] = jArr2[r92] >>> 1;
                    if (z4) {
                        jArr2[r92] = jArr2[r92] ^ j;
                    }
                    r92--;
                    z4 = z5;
                }
                jArr2[r5] = jArr2[r5] >>> 1;
                if (z4) {
                    jArr2[r5] = jArr2[r5] ^ j2;
                }
            } else {
                boolean z6 = (jArr[0] & 1) == 1;
                jArr[0] = jArr[0] >>> 1;
                if (z6) {
                    jArr[0] = jArr[0] ^ j2;
                }
                boolean z7 = (jArr2[0] & 1) == 1;
                jArr2[0] = jArr2[0] >>> 1;
                if (z7) {
                    jArr2[0] = jArr2[0] ^ j2;
                }
            }
            r13++;
            c = 0;
        }
        assign(jArr3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reverseOrder() {
        this.mPol = getElementReverseOrder();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement solveQuadraticEquation() throws RuntimeException {
        if (trace() != 1) {
            long j = mBitmask[63];
            long[] jArr = new long[this.mLength];
            int r8 = 0;
            long j2 = 0;
            for (int r2 = 1; r8 < this.mLength - r2; r2 = 1) {
                for (int r13 = 1; r13 < 64; r13++) {
                    long[] jArr2 = mBitmask;
                    long j3 = jArr2[r13];
                    long[] jArr3 = this.mPol;
                    if (((j3 & jArr3[r8]) == 0 || (jArr2[r13 - 1] & j2) == 0) && ((jArr3[r8] & jArr2[r13]) != 0 || (jArr2[r13 - 1] & j2) != 0)) {
                        j2 ^= jArr2[r13];
                    }
                }
                jArr[r8] = j2;
                int r22 = ((j2 & j) > 0L ? 1 : ((j2 & j) == 0L ? 0 : -1));
                j2 = ((r22 == 0 || (1 & this.mPol[r8 + 1]) != 1) && !(r22 == 0 && (this.mPol[r8 + 1] & 1) == 0)) ? 1L : 0L;
                r8++;
            }
            int r23 = this.mDegree & 63;
            long j4 = this.mPol[this.mLength - 1];
            for (int r3 = 1; r3 < r23; r3++) {
                long[] jArr4 = mBitmask;
                if (((jArr4[r3] & j4) == 0 || (jArr4[r3 - 1] & j2) == 0) && ((jArr4[r3] & j4) != 0 || (jArr4[r3 - 1] & j2) != 0)) {
                    j2 ^= jArr4[r3];
                }
            }
            jArr[this.mLength - 1] = j2;
            return new GF2nONBElement((GF2nONBField) this.mField, jArr);
        }
        throw new RuntimeException();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement square() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareThis();
        return gF2nONBElement;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement squareRoot() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareRootThis();
        return gF2nONBElement;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public void squareRootThis() {
        long[] element = getElement();
        int r2 = this.mLength - 1;
        int r4 = this.mBit - 1;
        long j = mBitmask[63];
        boolean z = (element[0] & 1) != 0;
        int r9 = r2;
        while (r9 >= 0) {
            boolean z2 = (element[r9] & 1) != 0;
            element[r9] = element[r9] >>> 1;
            if (z) {
                if (r9 == r2) {
                    element[r9] = element[r9] ^ mBitmask[r4];
                } else {
                    element[r9] = element[r9] ^ j;
                }
            }
            r9--;
            z = z2;
        }
        assign(element);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public void squareThis() {
        long[] element = getElement();
        int r2 = this.mLength - 1;
        int r4 = this.mBit - 1;
        long[] jArr = mBitmask;
        long j = jArr[63];
        boolean z = (element[r2] & jArr[r4]) != 0;
        int r9 = 0;
        while (r9 < r2) {
            boolean z2 = (element[r9] & j) != 0;
            element[r9] = element[r9] << 1;
            if (z) {
                element[r9] = 1 ^ element[r9];
            }
            r9++;
            z = z2;
        }
        long j2 = element[r2];
        long[] jArr2 = mBitmask;
        boolean z3 = (j2 & jArr2[r4]) != 0;
        element[r2] = element[r2] << 1;
        if (z) {
            element[r2] = element[r2] ^ 1;
        }
        if (z3) {
            element[r2] = jArr2[r4 + 1] ^ element[r2];
        }
        assign(element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public boolean testBit(int r7) {
        return r7 >= 0 && r7 <= this.mDegree && (this.mPol[r7 >>> 6] & mBitmask[r7 & 63]) != 0;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public boolean testRightmostBit() {
        return (this.mPol[this.mLength - 1] & mBitmask[this.mBit - 1]) != 0;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public byte[] toByteArray() {
        int r0 = ((this.mDegree - 1) >> 3) + 1;
        byte[] bArr = new byte[r0];
        for (int r2 = 0; r2 < r0; r2++) {
            int r4 = (r2 & 7) << 3;
            bArr[(r0 - r2) - 1] = (byte) ((this.mPol[r2 >>> 3] & (255 << r4)) >>> r4);
        }
        return bArr;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public BigInteger toFlexiBigInt() {
        return new BigInteger(1, toByteArray());
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public String toString() {
        return toString(16);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public String toString(int r13) {
        StringBuilder sb;
        long[] element = getElement();
        int r1 = this.mBit;
        String str = "";
        if (r13 == 2) {
            while (true) {
                r1--;
                if (r1 < 0) {
                    break;
                }
                str = (element[element.length - 1] & (1 << r1)) == 0 ? str + SessionDescription.SUPPORTED_SDP_VERSION : str + IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            for (int length = element.length - 2; length >= 0; length--) {
                for (int r2 = 63; r2 >= 0; r2--) {
                    if ((element[length] & mBitmask[r2]) == 0) {
                        sb = new StringBuilder();
                        sb.append(str);
                        sb.append(SessionDescription.SUPPORTED_SDP_VERSION);
                    } else {
                        sb = new StringBuilder();
                        sb.append(str);
                        sb.append(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                    }
                    str = sb.toString();
                }
            }
        } else if (r13 == 16) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            for (int length2 = element.length - 1; length2 >= 0; length2--) {
                str = ((((((((((((((((str + cArr[((int) (element[length2] >>> 60)) & 15]) + cArr[((int) (element[length2] >>> 56)) & 15]) + cArr[((int) (element[length2] >>> 52)) & 15]) + cArr[((int) (element[length2] >>> 48)) & 15]) + cArr[((int) (element[length2] >>> 44)) & 15]) + cArr[((int) (element[length2] >>> 40)) & 15]) + cArr[((int) (element[length2] >>> 36)) & 15]) + cArr[((int) (element[length2] >>> 32)) & 15]) + cArr[((int) (element[length2] >>> 28)) & 15]) + cArr[((int) (element[length2] >>> 24)) & 15]) + cArr[((int) (element[length2] >>> 20)) & 15]) + cArr[((int) (element[length2] >>> 16)) & 15]) + cArr[((int) (element[length2] >>> 12)) & 15]) + cArr[((int) (element[length2] >>> 8)) & 15]) + cArr[((int) (element[length2] >>> 4)) & 15]) + cArr[((int) element[length2]) & 15]) + " ";
            }
        }
        return str;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public int trace() {
        int r0 = this.mLength - 1;
        int r3 = 0;
        for (int r2 = 0; r2 < r0; r2++) {
            for (int r6 = 0; r6 < 64; r6++) {
                if ((this.mPol[r2] & mBitmask[r6]) != 0) {
                    r3 ^= 1;
                }
            }
        }
        int r22 = this.mBit;
        for (int r1 = 0; r1 < r22; r1++) {
            if ((this.mPol[r0] & mBitmask[r1]) != 0) {
                r3 ^= 1;
            }
        }
        return r3;
    }
}
