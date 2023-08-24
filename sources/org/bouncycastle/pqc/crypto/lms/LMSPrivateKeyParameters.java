package org.bouncycastle.pqc.crypto.lms;

import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.WeakHashMap;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.ExhaustedPrivateKeyException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.p041io.Streams;

/* loaded from: classes3.dex */
public class LMSPrivateKeyParameters extends LMSKeyParameters implements LMSContextBasedSigner {

    /* renamed from: T1 */
    private static CacheKey f2431T1;
    private static CacheKey[] internedKeys;

    /* renamed from: I */
    private final byte[] f2432I;
    private final byte[] masterSecret;
    private final int maxCacheR;
    private final int maxQ;
    private final LMOtsParameters otsParameters;
    private final LMSigParameters parameters;
    private LMSPublicKeyParameters publicKey;

    /* renamed from: q */
    private int f2433q;
    private final Map<CacheKey, byte[]> tCache;
    private final Digest tDigest;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class CacheKey {
        private final int index;

        CacheKey(int r1) {
            this.index = r1;
        }

        public boolean equals(Object obj) {
            return (obj instanceof CacheKey) && ((CacheKey) obj).index == this.index;
        }

        public int hashCode() {
            return this.index;
        }
    }

    static {
        CacheKey cacheKey = new CacheKey(1);
        f2431T1 = cacheKey;
        CacheKey[] cacheKeyArr = new CacheKey[TsExtractor.TS_STREAM_TYPE_AC3];
        internedKeys = cacheKeyArr;
        cacheKeyArr[1] = cacheKey;
        int r0 = 2;
        while (true) {
            CacheKey[] cacheKeyArr2 = internedKeys;
            if (r0 >= cacheKeyArr2.length) {
                return;
            }
            cacheKeyArr2[r0] = new CacheKey(r0);
            r0++;
        }
    }

    private LMSPrivateKeyParameters(LMSPrivateKeyParameters lMSPrivateKeyParameters, int r5, int r6) {
        super(true);
        LMSigParameters lMSigParameters = lMSPrivateKeyParameters.parameters;
        this.parameters = lMSigParameters;
        this.otsParameters = lMSPrivateKeyParameters.otsParameters;
        this.f2433q = r5;
        this.f2432I = lMSPrivateKeyParameters.f2432I;
        this.maxQ = r6;
        this.masterSecret = lMSPrivateKeyParameters.masterSecret;
        this.maxCacheR = 1 << lMSigParameters.getH();
        this.tCache = lMSPrivateKeyParameters.tCache;
        this.tDigest = DigestUtil.getDigest(lMSigParameters.getDigestOID());
        this.publicKey = lMSPrivateKeyParameters.publicKey;
    }

    public LMSPrivateKeyParameters(LMSigParameters lMSigParameters, LMOtsParameters lMOtsParameters, int r4, byte[] bArr, int r6, byte[] bArr2) {
        super(true);
        this.parameters = lMSigParameters;
        this.otsParameters = lMOtsParameters;
        this.f2433q = r4;
        this.f2432I = Arrays.clone(bArr);
        this.maxQ = r6;
        this.masterSecret = Arrays.clone(bArr2);
        this.maxCacheR = 1 << (lMSigParameters.getH() + 1);
        this.tCache = new WeakHashMap();
        this.tDigest = DigestUtil.getDigest(lMSigParameters.getDigestOID());
    }

    private byte[] calcT(int r6) {
        int h = 1 << getSigParameters().getH();
        if (r6 >= h) {
            LmsUtils.byteArray(getI(), this.tDigest);
            LmsUtils.u32str(r6, this.tDigest);
            LmsUtils.u16str((short) -32126, this.tDigest);
            LmsUtils.byteArray(LM_OTS.lms_ots_generatePublicKey(getOtsParameters(), getI(), r6 - h, getMasterSecret()), this.tDigest);
            byte[] bArr = new byte[this.tDigest.getDigestSize()];
            this.tDigest.doFinal(bArr, 0);
            return bArr;
        }
        int r0 = r6 * 2;
        byte[] findT = findT(r0);
        byte[] findT2 = findT(r0 + 1);
        LmsUtils.byteArray(getI(), this.tDigest);
        LmsUtils.u32str(r6, this.tDigest);
        LmsUtils.u16str((short) -31869, this.tDigest);
        LmsUtils.byteArray(findT, this.tDigest);
        LmsUtils.byteArray(findT2, this.tDigest);
        byte[] bArr2 = new byte[this.tDigest.getDigestSize()];
        this.tDigest.doFinal(bArr2, 0);
        return bArr2;
    }

    private byte[] findT(CacheKey cacheKey) {
        synchronized (this.tCache) {
            byte[] bArr = this.tCache.get(cacheKey);
            if (bArr != null) {
                return bArr;
            }
            byte[] calcT = calcT(cacheKey.index);
            this.tCache.put(cacheKey, calcT);
            return calcT;
        }
    }

    public static LMSPrivateKeyParameters getInstance(Object obj) throws IOException {
        DataInputStream dataInputStream;
        if (obj instanceof LMSPrivateKeyParameters) {
            return (LMSPrivateKeyParameters) obj;
        }
        if (!(obj instanceof DataInputStream)) {
            if (!(obj instanceof byte[])) {
                if (obj instanceof InputStream) {
                    return getInstance(Streams.readAll((InputStream) obj));
                }
                throw new IllegalArgumentException("cannot parse " + obj);
            }
            DataInputStream dataInputStream2 = null;
            try {
                dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) obj));
            } catch (Throwable th) {
                th = th;
            }
            try {
                LMSPrivateKeyParameters lMSPrivateKeyParameters = getInstance(dataInputStream);
                dataInputStream.close();
                return lMSPrivateKeyParameters;
            } catch (Throwable th2) {
                th = th2;
                dataInputStream2 = dataInputStream;
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                throw th;
            }
        }
        DataInputStream dataInputStream3 = (DataInputStream) obj;
        if (dataInputStream3.readInt() == 0) {
            LMSigParameters parametersForType = LMSigParameters.getParametersForType(dataInputStream3.readInt());
            LMOtsParameters parametersForType2 = LMOtsParameters.getParametersForType(dataInputStream3.readInt());
            byte[] bArr = new byte[16];
            dataInputStream3.readFully(bArr);
            int readInt = dataInputStream3.readInt();
            int readInt2 = dataInputStream3.readInt();
            int readInt3 = dataInputStream3.readInt();
            if (readInt3 >= 0) {
                if (readInt3 <= dataInputStream3.available()) {
                    byte[] bArr2 = new byte[readInt3];
                    dataInputStream3.readFully(bArr2);
                    return new LMSPrivateKeyParameters(parametersForType, parametersForType2, readInt, bArr, readInt2, bArr2);
                }
                throw new IOException("secret length exceeded " + dataInputStream3.available());
            }
            throw new IllegalStateException("secret length less than zero");
        }
        throw new IllegalStateException("expected version 0 lms private key");
    }

    public static LMSPrivateKeyParameters getInstance(byte[] bArr, byte[] bArr2) throws IOException {
        LMSPrivateKeyParameters lMSPrivateKeyParameters = getInstance(bArr);
        lMSPrivateKeyParameters.publicKey = LMSPublicKeyParameters.getInstance(bArr2);
        return lMSPrivateKeyParameters;
    }

    public boolean equals(Object obj) {
        LMSPublicKeyParameters lMSPublicKeyParameters;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LMSPrivateKeyParameters lMSPrivateKeyParameters = (LMSPrivateKeyParameters) obj;
        if (this.f2433q == lMSPrivateKeyParameters.f2433q && this.maxQ == lMSPrivateKeyParameters.maxQ && Arrays.areEqual(this.f2432I, lMSPrivateKeyParameters.f2432I)) {
            LMSigParameters lMSigParameters = this.parameters;
            if (lMSigParameters == null ? lMSPrivateKeyParameters.parameters == null : lMSigParameters.equals(lMSPrivateKeyParameters.parameters)) {
                LMOtsParameters lMOtsParameters = this.otsParameters;
                if (lMOtsParameters == null ? lMSPrivateKeyParameters.otsParameters == null : lMOtsParameters.equals(lMSPrivateKeyParameters.otsParameters)) {
                    if (Arrays.areEqual(this.masterSecret, lMSPrivateKeyParameters.masterSecret)) {
                        LMSPublicKeyParameters lMSPublicKeyParameters2 = this.publicKey;
                        if (lMSPublicKeyParameters2 == null || (lMSPublicKeyParameters = lMSPrivateKeyParameters.publicKey) == null) {
                            return true;
                        }
                        return lMSPublicKeyParameters2.equals(lMSPublicKeyParameters);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public LMSPrivateKeyParameters extractKeyShard(int r4) {
        LMSPrivateKeyParameters lMSPrivateKeyParameters;
        synchronized (this) {
            if (this.f2433q + r4 >= this.maxQ) {
                throw new IllegalArgumentException("usageCount exceeds usages remaining");
            }
            int r1 = this.f2433q;
            lMSPrivateKeyParameters = new LMSPrivateKeyParameters(this, r1, r1 + r4);
            this.f2433q += r4;
        }
        return lMSPrivateKeyParameters;
    }

    byte[] findT(int r3) {
        if (r3 < this.maxCacheR) {
            CacheKey[] cacheKeyArr = internedKeys;
            return findT(r3 < cacheKeyArr.length ? cacheKeyArr[r3] : new CacheKey(r3));
        }
        return calcT(r3);
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSContextBasedSigner
    public LMSContext generateLMSContext() {
        int h = getSigParameters().getH();
        int index = getIndex();
        LMOtsPrivateKey nextOtsPrivateKey = getNextOtsPrivateKey();
        int r4 = (1 << h) + index;
        byte[][] bArr = new byte[h];
        for (int r5 = 0; r5 < h; r5++) {
            bArr[r5] = findT((r4 / (1 << r5)) ^ 1);
        }
        return nextOtsPrivateKey.getSignatureContext(getSigParameters(), bArr);
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSContextBasedSigner
    public byte[] generateSignature(LMSContext lMSContext) {
        try {
            return LMS.generateSign(lMSContext).getEncoded();
        } catch (IOException e) {
            throw new IllegalStateException("unable to encode signature: " + e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMOtsPrivateKey getCurrentOTSKey() {
        LMOtsPrivateKey lMOtsPrivateKey;
        synchronized (this) {
            int r0 = this.f2433q;
            if (r0 >= this.maxQ) {
                throw new ExhaustedPrivateKeyException("ots private keys expired");
            }
            lMOtsPrivateKey = new LMOtsPrivateKey(this.otsParameters, this.f2432I, r0, this.masterSecret);
        }
        return lMOtsPrivateKey;
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSKeyParameters, org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return Composer.compose().u32str(0).u32str(this.parameters.getType()).u32str(this.otsParameters.getType()).bytes(this.f2432I).u32str(this.f2433q).u32str(this.maxQ).u32str(this.masterSecret.length).bytes(this.masterSecret).build();
    }

    public byte[] getI() {
        return Arrays.clone(this.f2432I);
    }

    public synchronized int getIndex() {
        return this.f2433q;
    }

    public byte[] getMasterSecret() {
        return Arrays.clone(this.masterSecret);
    }

    LMOtsPrivateKey getNextOtsPrivateKey() {
        LMOtsPrivateKey lMOtsPrivateKey;
        synchronized (this) {
            int r0 = this.f2433q;
            if (r0 >= this.maxQ) {
                throw new ExhaustedPrivateKeyException("ots private key exhausted");
            }
            lMOtsPrivateKey = new LMOtsPrivateKey(this.otsParameters, this.f2432I, r0, this.masterSecret);
            incIndex();
        }
        return lMOtsPrivateKey;
    }

    public LMOtsParameters getOtsParameters() {
        return this.otsParameters;
    }

    public LMSPublicKeyParameters getPublicKey() {
        LMSPublicKeyParameters lMSPublicKeyParameters;
        synchronized (this) {
            if (this.publicKey == null) {
                this.publicKey = new LMSPublicKeyParameters(this.parameters, this.otsParameters, findT(f2431T1), this.f2432I);
            }
            lMSPublicKeyParameters = this.publicKey;
        }
        return lMSPublicKeyParameters;
    }

    public LMSigParameters getSigParameters() {
        return this.parameters;
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSContextBasedSigner
    public long getUsagesRemaining() {
        return this.maxQ - this.f2433q;
    }

    public int hashCode() {
        int hashCode = ((this.f2433q * 31) + Arrays.hashCode(this.f2432I)) * 31;
        LMSigParameters lMSigParameters = this.parameters;
        int hashCode2 = (hashCode + (lMSigParameters != null ? lMSigParameters.hashCode() : 0)) * 31;
        LMOtsParameters lMOtsParameters = this.otsParameters;
        int hashCode3 = (((((hashCode2 + (lMOtsParameters != null ? lMOtsParameters.hashCode() : 0)) * 31) + this.maxQ) * 31) + Arrays.hashCode(this.masterSecret)) * 31;
        LMSPublicKeyParameters lMSPublicKeyParameters = this.publicKey;
        return hashCode3 + (lMSPublicKeyParameters != null ? lMSPublicKeyParameters.hashCode() : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void incIndex() {
        this.f2433q++;
    }
}
