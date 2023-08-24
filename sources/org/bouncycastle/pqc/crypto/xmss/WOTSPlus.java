package org.bouncycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
final class WOTSPlus {
    private final KeyedHashFunctions khf;
    private final WOTSPlusParameters params;
    private byte[] publicSeed;
    private byte[] secretKeySeed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlus(WOTSPlusParameters wOTSPlusParameters) {
        Objects.requireNonNull(wOTSPlusParameters, "params == null");
        this.params = wOTSPlusParameters;
        int treeDigestSize = wOTSPlusParameters.getTreeDigestSize();
        this.khf = new KeyedHashFunctions(wOTSPlusParameters.getTreeDigest(), treeDigestSize);
        this.secretKeySeed = new byte[treeDigestSize];
        this.publicSeed = new byte[treeDigestSize];
    }

    private byte[] chain(byte[] bArr, int r8, int r9, OTSHashAddress oTSHashAddress) {
        int treeDigestSize = this.params.getTreeDigestSize();
        Objects.requireNonNull(bArr, "startHash == null");
        if (bArr.length != treeDigestSize) {
            throw new IllegalArgumentException("startHash needs to be " + treeDigestSize + "bytes");
        }
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        Objects.requireNonNull(oTSHashAddress.toByteArray(), "otsHashAddress byte array == null");
        int r1 = r8 + r9;
        if (r1 <= this.params.getWinternitzParameter() - 1) {
            if (r9 == 0) {
                return bArr;
            }
            byte[] chain = chain(bArr, r8, r9 - 1, oTSHashAddress);
            OTSHashAddress oTSHashAddress2 = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(oTSHashAddress.getChainAddress()).withHashAddress(r1 - 1).withKeyAndMask(0).build();
            byte[] PRF = this.khf.PRF(this.publicSeed, oTSHashAddress2.toByteArray());
            byte[] PRF2 = this.khf.PRF(this.publicSeed, ((OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress2.getLayerAddress()).withTreeAddress(oTSHashAddress2.getTreeAddress()).withOTSAddress(oTSHashAddress2.getOTSAddress()).withChainAddress(oTSHashAddress2.getChainAddress()).withHashAddress(oTSHashAddress2.getHashAddress()).withKeyAndMask(1).build()).toByteArray());
            byte[] bArr2 = new byte[treeDigestSize];
            for (int r92 = 0; r92 < treeDigestSize; r92++) {
                bArr2[r92] = (byte) (chain[r92] ^ PRF2[r92]);
            }
            return this.khf.m6F(PRF, bArr2);
        }
        throw new IllegalArgumentException("max chain length must not be greater than w");
    }

    private List<Integer> convertToBaseW(byte[] bArr, int r8, int r9) {
        Objects.requireNonNull(bArr, "msg == null");
        if (r8 == 4 || r8 == 16) {
            int log2 = XMSSUtil.log2(r8);
            if (r9 <= (bArr.length * 8) / log2) {
                ArrayList arrayList = new ArrayList();
                for (int r4 : bArr) {
                    for (int r3 = 8 - log2; r3 >= 0; r3 -= log2) {
                        arrayList.add(Integer.valueOf((r4 >> r3) & (r8 - 1)));
                        if (arrayList.size() == r9) {
                            return arrayList;
                        }
                    }
                }
                return arrayList;
            }
            throw new IllegalArgumentException("outLength too big");
        }
        throw new IllegalArgumentException("w needs to be 4 or 16");
    }

    private byte[] expandSecretKeySeed(int r5) {
        if (r5 < 0 || r5 >= this.params.getLen()) {
            throw new IllegalArgumentException("index out of bounds");
        }
        return this.khf.PRF(this.secretKeySeed, XMSSUtil.toBytesBigEndian(r5, 32));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KeyedHashFunctions getKhf() {
        return this.khf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlusParameters getParams() {
        return this.params;
    }

    protected WOTSPlusPrivateKeyParameters getPrivateKey() {
        int len = this.params.getLen();
        byte[][] bArr = new byte[len];
        for (int r2 = 0; r2 < len; r2++) {
            bArr[r2] = expandSecretKeySeed(r2);
        }
        return new WOTSPlusPrivateKeyParameters(this.params, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlusPublicKeyParameters getPublicKey(OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        byte[][] bArr = new byte[this.params.getLen()];
        for (int r2 = 0; r2 < this.params.getLen(); r2++) {
            oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(r2).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
            bArr[r2] = chain(expandSecretKeySeed(r2), 0, this.params.getWinternitzParameter() - 1, oTSHashAddress);
        }
        return new WOTSPlusPublicKeyParameters(this.params, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlusPublicKeyParameters getPublicKeyFromSignature(byte[] bArr, WOTSPlusSignature wOTSPlusSignature, OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(bArr, "messageDigest == null");
        if (bArr.length == this.params.getTreeDigestSize()) {
            Objects.requireNonNull(wOTSPlusSignature, "signature == null");
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            List<Integer> convertToBaseW = convertToBaseW(bArr, this.params.getWinternitzParameter(), this.params.getLen1());
            int r2 = 0;
            for (int r1 = 0; r1 < this.params.getLen1(); r1++) {
                r2 += (this.params.getWinternitzParameter() - 1) - convertToBaseW.get(r1).intValue();
            }
            convertToBaseW.addAll(convertToBaseW(XMSSUtil.toBytesBigEndian(r2 << (8 - ((this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter())) % 8)), (int) Math.ceil((this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter())) / 8.0d)), this.params.getWinternitzParameter(), this.params.getLen2()));
            byte[][] bArr2 = new byte[this.params.getLen()];
            for (int r0 = 0; r0 < this.params.getLen(); r0++) {
                oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(r0).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
                bArr2[r0] = chain(wOTSPlusSignature.toByteArray()[r0], convertToBaseW.get(r0).intValue(), (this.params.getWinternitzParameter() - 1) - convertToBaseW.get(r0).intValue(), oTSHashAddress);
            }
            return new WOTSPlusPublicKeyParameters(this.params, bArr2);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] getPublicSeed() {
        return Arrays.clone(this.publicSeed);
    }

    protected byte[] getSecretKeySeed() {
        return Arrays.clone(this.secretKeySeed);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] getWOTSPlusSecretKey(byte[] bArr, OTSHashAddress oTSHashAddress) {
        return this.khf.PRF(bArr, ((OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.getOTSAddress()).build()).toByteArray());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void importKeys(byte[] bArr, byte[] bArr2) {
        Objects.requireNonNull(bArr, "secretKeySeed == null");
        if (bArr.length != this.params.getTreeDigestSize()) {
            throw new IllegalArgumentException("size of secretKeySeed needs to be equal to size of digest");
        }
        Objects.requireNonNull(bArr2, "publicSeed == null");
        if (bArr2.length != this.params.getTreeDigestSize()) {
            throw new IllegalArgumentException("size of publicSeed needs to be equal to size of digest");
        }
        this.secretKeySeed = bArr;
        this.publicSeed = bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlusSignature sign(byte[] bArr, OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(bArr, "messageDigest == null");
        if (bArr.length == this.params.getTreeDigestSize()) {
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            List<Integer> convertToBaseW = convertToBaseW(bArr, this.params.getWinternitzParameter(), this.params.getLen1());
            int r2 = 0;
            for (int r1 = 0; r1 < this.params.getLen1(); r1++) {
                r2 += (this.params.getWinternitzParameter() - 1) - convertToBaseW.get(r1).intValue();
            }
            convertToBaseW.addAll(convertToBaseW(XMSSUtil.toBytesBigEndian(r2 << (8 - ((this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter())) % 8)), (int) Math.ceil((this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter())) / 8.0d)), this.params.getWinternitzParameter(), this.params.getLen2()));
            byte[][] bArr2 = new byte[this.params.getLen()];
            for (int r22 = 0; r22 < this.params.getLen(); r22++) {
                oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(r22).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
                bArr2[r22] = chain(expandSecretKeySeed(r22), 0, convertToBaseW.get(r22).intValue(), oTSHashAddress);
            }
            return new WOTSPlusSignature(this.params, bArr2);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }
}
