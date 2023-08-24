package org.bouncycastle.pqc.crypto.sphincsplus;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class SPHINCSPlusSigner implements MessageSigner {
    private SPHINCSPlusPrivateKeyParameters privKey;
    private SPHINCSPlusPublicKeyParameters pubKey;
    private SecureRandom random;

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        SPHINCSPlusEngine engine = this.privKey.getParameters().getEngine();
        byte[] bArr2 = new byte[engine.f2507N];
        SecureRandom secureRandom = this.random;
        if (secureRandom != null) {
            secureRandom.nextBytes(bArr2);
        }
        Fors fors = new Fors(engine);
        byte[] PRF_msg = engine.PRF_msg(this.privKey.f2510sk.prf, bArr2, bArr);
        IndexedDigest H_msg = engine.H_msg(PRF_msg, this.privKey.f2509pk.seed, this.privKey.f2509pk.root, bArr);
        byte[] bArr3 = H_msg.digest;
        long j = H_msg.idx_tree;
        int r11 = H_msg.idx_leaf;
        ADRS adrs = new ADRS();
        adrs.setType(3);
        adrs.setTreeAddress(j);
        adrs.setKeyPairAddress(r11);
        SIG_FORS[] sign = fors.sign(bArr3, this.privKey.f2510sk.seed, this.privKey.f2509pk.seed, adrs);
        byte[] pkFromSig = fors.pkFromSig(sign, bArr3, this.privKey.f2509pk.seed, adrs);
        new ADRS().setType(2);
        byte[] sign2 = new C5393HT(engine, this.privKey.getSeed(), this.privKey.getPublicSeed()).sign(pkFromSig, j, r11);
        int length = sign.length + 2;
        byte[][] bArr4 = new byte[length];
        int r3 = 0;
        bArr4[0] = PRF_msg;
        while (r3 != sign.length) {
            int r1 = r3 + 1;
            bArr4[r1] = Arrays.concatenate(sign[r3].f2502sk, Arrays.concatenate(sign[r3].authPath));
            r3 = r1;
        }
        bArr4[length - 1] = sign2;
        return Arrays.concatenate(bArr4);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.pubKey = (SPHINCSPlusPublicKeyParameters) cipherParameters;
        } else if (!(cipherParameters instanceof ParametersWithRandom)) {
            this.privKey = (SPHINCSPlusPrivateKeyParameters) cipherParameters;
        } else {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.privKey = (SPHINCSPlusPrivateKeyParameters) parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        SPHINCSPlusEngine engine = this.pubKey.getParameters().getEngine();
        ADRS adrs = new ADRS();
        SIG r11 = new SIG(engine.f2507N, engine.f2506K, engine.f2503A, engine.f2504D, engine.H_PRIME, engine.WOTS_LEN, bArr2);
        byte[] r = r11.getR();
        SIG_FORS[] r4 = r11.getSIG_FORS();
        SIG_XMSS[] r14 = r11.getSIG_HT();
        IndexedDigest H_msg = engine.H_msg(r, this.pubKey.getSeed(), this.pubKey.getRoot(), bArr);
        byte[] bArr3 = H_msg.digest;
        long j = H_msg.idx_tree;
        int r3 = H_msg.idx_leaf;
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j);
        adrs.setType(3);
        adrs.setKeyPairAddress(r3);
        byte[] pkFromSig = new Fors(engine).pkFromSig(r4, bArr3, this.pubKey.getSeed(), adrs);
        adrs.setType(2);
        return new C5393HT(engine, null, this.pubKey.getSeed()).verify(pkFromSig, r14, this.pubKey.getSeed(), j, r3, this.pubKey.getRoot());
    }
}
