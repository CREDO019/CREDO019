package org.bouncycastle.pqc.crypto.xmss;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class XMSSReducedSignature implements XMSSStoreableObjectInterface {
    private final List<XMSSNode> authPath;
    private final XMSSParameters params;
    private final WOTSPlusSignature wotsPlusSignature;

    /* loaded from: classes3.dex */
    public static class Builder {
        private final XMSSParameters params;
        private WOTSPlusSignature wotsPlusSignature = null;
        private List<XMSSNode> authPath = null;
        private byte[] reducedSignature = null;

        public Builder(XMSSParameters xMSSParameters) {
            this.params = xMSSParameters;
        }

        public XMSSReducedSignature build() {
            return new XMSSReducedSignature(this);
        }

        public Builder withAuthPath(List<XMSSNode> list) {
            this.authPath = list;
            return this;
        }

        public Builder withReducedSignature(byte[] bArr) {
            this.reducedSignature = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withWOTSPlusSignature(WOTSPlusSignature wOTSPlusSignature) {
            this.wotsPlusSignature = wOTSPlusSignature;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XMSSReducedSignature(Builder builder) {
        List<XMSSNode> list;
        XMSSParameters xMSSParameters = builder.params;
        this.params = xMSSParameters;
        Objects.requireNonNull(xMSSParameters, "params == null");
        int treeDigestSize = xMSSParameters.getTreeDigestSize();
        int len = xMSSParameters.getWOTSPlus().getParams().getLen();
        int height = xMSSParameters.getHeight();
        byte[] bArr = builder.reducedSignature;
        if (bArr == null) {
            WOTSPlusSignature wOTSPlusSignature = builder.wotsPlusSignature;
            this.wotsPlusSignature = wOTSPlusSignature == null ? new WOTSPlusSignature(xMSSParameters.getWOTSPlus().getParams(), (byte[][]) Array.newInstance(byte.class, len, treeDigestSize)) : wOTSPlusSignature;
            list = builder.authPath;
            if (list == null) {
                list = new ArrayList<>();
            } else if (list.size() != height) {
                throw new IllegalArgumentException("size of authPath needs to be equal to height of tree");
            }
        } else if (bArr.length != (len * treeDigestSize) + (height * treeDigestSize)) {
            throw new IllegalArgumentException("signature has wrong size");
        } else {
            byte[][] bArr2 = new byte[len];
            int r6 = 0;
            for (int r0 = 0; r0 < len; r0++) {
                bArr2[r0] = XMSSUtil.extractBytesAtOffset(bArr, r6, treeDigestSize);
                r6 += treeDigestSize;
            }
            this.wotsPlusSignature = new WOTSPlusSignature(this.params.getWOTSPlus().getParams(), bArr2);
            list = new ArrayList<>();
            for (int r5 = 0; r5 < height; r5++) {
                list.add(new XMSSNode(r5, XMSSUtil.extractBytesAtOffset(bArr, r6, treeDigestSize)));
                r6 += treeDigestSize;
            }
        }
        this.authPath = list;
    }

    public List<XMSSNode> getAuthPath() {
        return this.authPath;
    }

    public XMSSParameters getParams() {
        return this.params;
    }

    public WOTSPlusSignature getWOTSPlusSignature() {
        return this.wotsPlusSignature;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int treeDigestSize = this.params.getTreeDigestSize();
        byte[] bArr = new byte[(this.params.getWOTSPlus().getParams().getLen() * treeDigestSize) + (this.params.getHeight() * treeDigestSize)];
        int r5 = 0;
        for (byte[] bArr2 : this.wotsPlusSignature.toByteArray()) {
            XMSSUtil.copyBytesAtOffset(bArr, bArr2, r5);
            r5 += treeDigestSize;
        }
        for (int r3 = 0; r3 < this.authPath.size(); r3++) {
            XMSSUtil.copyBytesAtOffset(bArr, this.authPath.get(r3).getValue(), r5);
            r5 += treeDigestSize;
        }
        return bArr;
    }
}
