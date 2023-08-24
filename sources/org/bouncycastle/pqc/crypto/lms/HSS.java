package org.bouncycastle.pqc.crypto.lms;

import java.util.Arrays;
import java.util.List;
import org.bouncycastle.pqc.crypto.ExhaustedPrivateKeyException;

/* loaded from: classes3.dex */
class HSS {

    /* loaded from: classes3.dex */
    static class PlaceholderLMSPrivateKey extends LMSPrivateKeyParameters {
        public PlaceholderLMSPrivateKey(LMSigParameters lMSigParameters, LMOtsParameters lMOtsParameters, int r3, byte[] bArr, int r5, byte[] bArr2) {
            super(lMSigParameters, lMOtsParameters, r3, bArr, r5, bArr2);
        }

        @Override // org.bouncycastle.pqc.crypto.lms.LMSPrivateKeyParameters
        LMOtsPrivateKey getNextOtsPrivateKey() {
            throw new RuntimeException("placeholder only");
        }

        @Override // org.bouncycastle.pqc.crypto.lms.LMSPrivateKeyParameters
        public LMSPublicKeyParameters getPublicKey() {
            throw new RuntimeException("placeholder only");
        }
    }

    HSS() {
    }

    public static HSSPrivateKeyParameters generateHSSKeyPair(HSSKeyGenerationParameters hSSKeyGenerationParameters) {
        int r8;
        byte[] bArr;
        int depth = hSSKeyGenerationParameters.getDepth();
        LMSPrivateKeyParameters[] lMSPrivateKeyParametersArr = new LMSPrivateKeyParameters[depth];
        LMSSignature[] lMSSignatureArr = new LMSSignature[hSSKeyGenerationParameters.getDepth() - 1];
        byte[] bArr2 = new byte[32];
        hSSKeyGenerationParameters.getRandom().nextBytes(bArr2);
        byte[] bArr3 = new byte[16];
        hSSKeyGenerationParameters.getRandom().nextBytes(bArr3);
        byte[] bArr4 = new byte[0];
        long j = 1;
        int r14 = 0;
        while (r14 < depth) {
            if (r14 == 0) {
                lMSPrivateKeyParametersArr[r14] = new LMSPrivateKeyParameters(hSSKeyGenerationParameters.getLmsParameters()[r14].getLMSigParam(), hSSKeyGenerationParameters.getLmsParameters()[r14].getLMOTSParam(), 0, bArr3, 1 << hSSKeyGenerationParameters.getLmsParameters()[r14].getLMSigParam().getH(), bArr2);
                r8 = r14;
                bArr = bArr4;
            } else {
                r8 = r14;
                bArr = bArr4;
                lMSPrivateKeyParametersArr[r8] = new PlaceholderLMSPrivateKey(hSSKeyGenerationParameters.getLmsParameters()[r14].getLMSigParam(), hSSKeyGenerationParameters.getLmsParameters()[r14].getLMOTSParam(), -1, bArr, 1 << hSSKeyGenerationParameters.getLmsParameters()[r14].getLMSigParam().getH(), bArr);
            }
            j *= 1 << hSSKeyGenerationParameters.getLmsParameters()[r8].getLMSigParam().getH();
            r14 = r8 + 1;
            bArr4 = bArr;
        }
        if (j == 0) {
            j = Long.MAX_VALUE;
        }
        return new HSSPrivateKeyParameters(hSSKeyGenerationParameters.getDepth(), Arrays.asList(lMSPrivateKeyParametersArr), Arrays.asList(lMSSignatureArr), 0L, j);
    }

    public static HSSSignature generateSignature(int r2, LMSContext lMSContext) {
        return new HSSSignature(r2 - 1, lMSContext.getSignedPubKeys(), LMS.generateSign(lMSContext));
    }

    public static HSSSignature generateSignature(HSSPrivateKeyParameters hSSPrivateKeyParameters, byte[] bArr) {
        LMSPrivateKeyParameters lMSPrivateKeyParameters;
        LMSSignedPubKey[] lMSSignedPubKeyArr;
        int l = hSSPrivateKeyParameters.getL();
        synchronized (hSSPrivateKeyParameters) {
            rangeTestKeys(hSSPrivateKeyParameters);
            List<LMSPrivateKeyParameters> keys = hSSPrivateKeyParameters.getKeys();
            List<LMSSignature> sig = hSSPrivateKeyParameters.getSig();
            int r4 = l - 1;
            lMSPrivateKeyParameters = hSSPrivateKeyParameters.getKeys().get(r4);
            lMSSignedPubKeyArr = new LMSSignedPubKey[r4];
            int r7 = 0;
            while (r7 < r4) {
                int r10 = r7 + 1;
                lMSSignedPubKeyArr[r7] = new LMSSignedPubKey(sig.get(r7), keys.get(r10).getPublicKey());
                r7 = r10;
            }
            hSSPrivateKeyParameters.incIndex();
        }
        LMSContext withSignedPublicKeys = lMSPrivateKeyParameters.generateLMSContext().withSignedPublicKeys(lMSSignedPubKeyArr);
        withSignedPublicKeys.update(bArr, 0, bArr.length);
        return generateSignature(l, withSignedPublicKeys);
    }

    public static void incrementIndex(HSSPrivateKeyParameters hSSPrivateKeyParameters) {
        synchronized (hSSPrivateKeyParameters) {
            rangeTestKeys(hSSPrivateKeyParameters);
            hSSPrivateKeyParameters.incIndex();
            hSSPrivateKeyParameters.getKeys().get(hSSPrivateKeyParameters.getL() - 1).incIndex();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void rangeTestKeys(HSSPrivateKeyParameters hSSPrivateKeyParameters) {
        synchronized (hSSPrivateKeyParameters) {
            if (hSSPrivateKeyParameters.getIndex() >= hSSPrivateKeyParameters.getIndexLimit()) {
                StringBuilder sb = new StringBuilder();
                sb.append("hss private key");
                sb.append(hSSPrivateKeyParameters.isShard() ? " shard" : "");
                sb.append(" is exhausted");
                throw new ExhaustedPrivateKeyException(sb.toString());
            }
            int l = hSSPrivateKeyParameters.getL();
            List<LMSPrivateKeyParameters> keys = hSSPrivateKeyParameters.getKeys();
            int r2 = l;
            while (true) {
                int r3 = r2 - 1;
                if (keys.get(r3).getIndex() != (1 << keys.get(r3).getSigParameters().getH())) {
                    while (r2 < l) {
                        hSSPrivateKeyParameters.replaceConsumedKey(r2);
                        r2++;
                    }
                } else if (r3 == 0) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("hss private key");
                    sb2.append(hSSPrivateKeyParameters.isShard() ? " shard" : "");
                    sb2.append(" is exhausted the maximum limit for this HSS private key");
                    throw new ExhaustedPrivateKeyException(sb2.toString());
                } else {
                    r2 = r3;
                }
            }
        }
    }

    public static boolean verifySignature(HSSPublicKeyParameters hSSPublicKeyParameters, HSSSignature hSSSignature, byte[] bArr) {
        int r0 = hSSSignature.getlMinus1();
        int r1 = r0 + 1;
        if (r1 != hSSPublicKeyParameters.getL()) {
            return false;
        }
        LMSSignature[] lMSSignatureArr = new LMSSignature[r1];
        LMSPublicKeyParameters[] lMSPublicKeyParametersArr = new LMSPublicKeyParameters[r0];
        for (int r4 = 0; r4 < r0; r4++) {
            lMSSignatureArr[r4] = hSSSignature.getSignedPubKey()[r4].getSignature();
            lMSPublicKeyParametersArr[r4] = hSSSignature.getSignedPubKey()[r4].getPublicKey();
        }
        lMSSignatureArr[r0] = hSSSignature.getSignature();
        LMSPublicKeyParameters lMSPublicKey = hSSPublicKeyParameters.getLMSPublicKey();
        for (int r7 = 0; r7 < r0; r7++) {
            if (!LMS.verifySignature(lMSPublicKey, lMSSignatureArr[r7], lMSPublicKeyParametersArr[r7].toByteArray())) {
                return false;
            }
            try {
                lMSPublicKey = lMSPublicKeyParametersArr[r7];
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
        return LMS.verifySignature(lMSPublicKey, lMSSignatureArr[r0], bArr);
    }
}
