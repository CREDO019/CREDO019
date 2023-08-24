package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class LM_OTS {
    static final short D_MESG = -32383;
    private static final short D_PBLC = -32640;
    private static final int ITER_J = 22;
    private static final int ITER_K = 20;
    private static final int ITER_PREV = 23;
    static final int MAX_HASH = 32;
    static final int SEED_LEN = 32;
    static final int SEED_RANDOMISER_INDEX = -3;

    LM_OTS() {
    }

    public static int cksm(byte[] bArr, int r6, LMOtsParameters lMOtsParameters) {
        int w = (1 << lMOtsParameters.getW()) - 1;
        int r2 = 0;
        for (int r1 = 0; r1 < (r6 * 8) / lMOtsParameters.getW(); r1++) {
            r2 = (r2 + w) - coef(bArr, r1, lMOtsParameters.getW());
        }
        return r2 << lMOtsParameters.getLs();
    }

    public static int coef(byte[] bArr, int r4, int r5) {
        return (bArr[(r4 * r5) / 8] >>> (((~r4) & ((8 / r5) - 1)) * r5)) & ((1 << r5) - 1);
    }

    public static LMOtsSignature lm_ots_generate_signature(LMOtsPrivateKey lMOtsPrivateKey, byte[] bArr, byte[] bArr2) {
        LMOtsParameters parameter = lMOtsPrivateKey.getParameter();
        int n = parameter.getN();
        int p = parameter.getP();
        int w = parameter.getW();
        byte[] bArr3 = new byte[p * n];
        Digest digest = DigestUtil.getDigest(parameter.getDigestOID());
        SeedDerive derivationFunction = lMOtsPrivateKey.getDerivationFunction();
        int cksm = cksm(bArr, n, parameter);
        bArr[n] = (byte) ((cksm >>> 8) & 255);
        bArr[n + 1] = (byte) cksm;
        int r9 = n + 23;
        byte[] build = Composer.compose().bytes(lMOtsPrivateKey.getI()).u32str(lMOtsPrivateKey.getQ()).padUntil(0, r9).build();
        derivationFunction.setJ(0);
        int r11 = 0;
        while (r11 < p) {
            Pack.shortToBigEndian((short) r11, build, 20);
            int r13 = 23;
            derivationFunction.deriveSeed(build, r11 < p + (-1), 23);
            int coef = coef(bArr, r11, w);
            for (int r14 = 0; r14 < coef; r14++) {
                build[22] = (byte) r14;
                digest.update(build, 0, r9);
                r13 = 23;
                digest.doFinal(build, 23);
            }
            System.arraycopy(build, r13, bArr3, n * r11, n);
            r11++;
        }
        return new LMOtsSignature(parameter, bArr2, bArr3);
    }

    public static LMOtsSignature lm_ots_generate_signature(LMSigParameters lMSigParameters, LMOtsPrivateKey lMOtsPrivateKey, byte[][] bArr, byte[] bArr2, boolean z) {
        byte[] bArr3;
        byte[] bArr4 = new byte[34];
        if (z) {
            bArr3 = new byte[32];
            System.arraycopy(bArr2, 0, bArr4, 0, lMOtsPrivateKey.getParameter().getN());
        } else {
            LMSContext signatureContext = lMOtsPrivateKey.getSignatureContext(lMSigParameters, bArr);
            LmsUtils.byteArray(bArr2, 0, bArr2.length, signatureContext);
            bArr3 = signatureContext.getC();
            bArr4 = signatureContext.getQ();
        }
        return lm_ots_generate_signature(lMOtsPrivateKey, bArr4, bArr3);
    }

    public static boolean lm_ots_validate_signature(LMOtsPublicKey lMOtsPublicKey, LMOtsSignature lMOtsSignature, byte[] bArr, boolean z) throws LMSException {
        if (lMOtsSignature.getType().equals(lMOtsPublicKey.getParameter())) {
            return Arrays.areEqual(lm_ots_validate_signature_calculate(lMOtsPublicKey, lMOtsSignature, bArr), lMOtsPublicKey.getK());
        }
        throw new LMSException("public key and signature ots types do not match");
    }

    public static byte[] lm_ots_validate_signature_calculate(LMOtsPublicKey lMOtsPublicKey, LMOtsSignature lMOtsSignature, byte[] bArr) {
        LMSContext createOtsContext = lMOtsPublicKey.createOtsContext(lMOtsSignature);
        LmsUtils.byteArray(bArr, createOtsContext);
        return lm_ots_validate_signature_calculate(createOtsContext);
    }

    public static byte[] lm_ots_validate_signature_calculate(LMSContext lMSContext) {
        LMOtsPublicKey publicKey = lMSContext.getPublicKey();
        LMOtsParameters parameter = publicKey.getParameter();
        Object signature = lMSContext.getSignature();
        LMOtsSignature otsSignature = signature instanceof LMSSignature ? ((LMSSignature) signature).getOtsSignature() : (LMOtsSignature) signature;
        int n = parameter.getN();
        int w = parameter.getW();
        int p = parameter.getP();
        byte[] q = lMSContext.getQ();
        int cksm = cksm(q, n, parameter);
        q[n] = (byte) ((cksm >>> 8) & 255);
        q[n + 1] = (byte) cksm;
        byte[] bArr = publicKey.getI();
        int q2 = publicKey.getQ();
        Digest digest = DigestUtil.getDigest(parameter.getDigestOID());
        LmsUtils.byteArray(bArr, digest);
        LmsUtils.u32str(q2, digest);
        LmsUtils.u16str(D_PBLC, digest);
        Composer u32str = Composer.compose().bytes(bArr).u32str(q2);
        int r6 = n + 23;
        byte[] build = u32str.padUntil(0, r6).build();
        int r10 = (1 << w) - 1;
        byte[] y = otsSignature.getY();
        Digest digest2 = DigestUtil.getDigest(parameter.getDigestOID());
        for (int r9 = 0; r9 < p; r9++) {
            Pack.shortToBigEndian((short) r9, build, 20);
            System.arraycopy(y, r9 * n, build, 23, n);
            for (int coef = coef(q, r9, w); coef < r10; coef++) {
                build[22] = (byte) coef;
                digest2.update(build, 0, r6);
                digest2.doFinal(build, 23);
            }
            digest.update(build, 23, n);
        }
        byte[] bArr2 = new byte[n];
        digest.doFinal(bArr2, 0);
        return bArr2;
    }

    public static LMOtsPublicKey lms_ots_generatePublicKey(LMOtsPrivateKey lMOtsPrivateKey) {
        return new LMOtsPublicKey(lMOtsPrivateKey.getParameter(), lMOtsPrivateKey.getI(), lMOtsPrivateKey.getQ(), lms_ots_generatePublicKey(lMOtsPrivateKey.getParameter(), lMOtsPrivateKey.getI(), lMOtsPrivateKey.getQ(), lMOtsPrivateKey.getMasterSecret()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] lms_ots_generatePublicKey(LMOtsParameters lMOtsParameters, byte[] bArr, int r12, byte[] bArr2) {
        Digest digest = DigestUtil.getDigest(lMOtsParameters.getDigestOID());
        byte[] build = Composer.compose().bytes(bArr).u32str(r12).u16str(-32640).padUntil(0, 22).build();
        digest.update(build, 0, build.length);
        Digest digest2 = DigestUtil.getDigest(lMOtsParameters.getDigestOID());
        byte[] build2 = Composer.compose().bytes(bArr).u32str(r12).padUntil(0, digest2.getDigestSize() + 23).build();
        SeedDerive seedDerive = new SeedDerive(bArr, bArr2, DigestUtil.getDigest(lMOtsParameters.getDigestOID()));
        seedDerive.setQ(r12);
        seedDerive.setJ(0);
        int p = lMOtsParameters.getP();
        int n = lMOtsParameters.getN();
        int w = (1 << lMOtsParameters.getW()) - 1;
        int r7 = 0;
        while (r7 < p) {
            seedDerive.deriveSeed(build2, r7 < p + (-1), 23);
            Pack.shortToBigEndian((short) r7, build2, 20);
            for (int r8 = 0; r8 < w; r8++) {
                build2[22] = (byte) r8;
                digest2.update(build2, 0, build2.length);
                digest2.doFinal(build2, 23);
            }
            digest.update(build2, 23, n);
            r7++;
        }
        byte[] bArr3 = new byte[digest.getDigestSize()];
        digest.doFinal(bArr3, 0);
        return bArr3;
    }
}
