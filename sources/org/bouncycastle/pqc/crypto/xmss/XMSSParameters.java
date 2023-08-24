package org.bouncycastle.pqc.crypto.xmss;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Integers;

/* loaded from: classes3.dex */
public final class XMSSParameters {
    private static final Map<Integer, XMSSParameters> paramsLookupTable;
    private final int height;

    /* renamed from: k */
    private final int f2514k;
    private final XMSSOid oid;
    private final String treeDigest;
    private final ASN1ObjectIdentifier treeDigestOID;
    private final int treeDigestSize;
    private final int winternitzParameter;
    private final WOTSPlusParameters wotsPlusParams;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(Integers.valueOf(1), new XMSSParameters(10, NISTObjectIdentifiers.id_sha256));
        hashMap.put(Integers.valueOf(2), new XMSSParameters(16, NISTObjectIdentifiers.id_sha256));
        hashMap.put(Integers.valueOf(3), new XMSSParameters(20, NISTObjectIdentifiers.id_sha256));
        hashMap.put(Integers.valueOf(4), new XMSSParameters(10, NISTObjectIdentifiers.id_sha512));
        hashMap.put(Integers.valueOf(5), new XMSSParameters(16, NISTObjectIdentifiers.id_sha512));
        hashMap.put(Integers.valueOf(6), new XMSSParameters(20, NISTObjectIdentifiers.id_sha512));
        hashMap.put(Integers.valueOf(7), new XMSSParameters(10, NISTObjectIdentifiers.id_shake128));
        hashMap.put(Integers.valueOf(8), new XMSSParameters(16, NISTObjectIdentifiers.id_shake128));
        hashMap.put(Integers.valueOf(9), new XMSSParameters(20, NISTObjectIdentifiers.id_shake128));
        hashMap.put(Integers.valueOf(10), new XMSSParameters(10, NISTObjectIdentifiers.id_shake256));
        hashMap.put(Integers.valueOf(11), new XMSSParameters(16, NISTObjectIdentifiers.id_shake256));
        hashMap.put(Integers.valueOf(12), new XMSSParameters(20, NISTObjectIdentifiers.id_shake256));
        paramsLookupTable = Collections.unmodifiableMap(hashMap);
    }

    public XMSSParameters(int r4, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (r4 < 2) {
            throw new IllegalArgumentException("height must be >= 2");
        }
        Objects.requireNonNull(aSN1ObjectIdentifier, "digest == null");
        this.height = r4;
        this.f2514k = determineMinK();
        String digestName = DigestUtil.getDigestName(aSN1ObjectIdentifier);
        this.treeDigest = digestName;
        this.treeDigestOID = aSN1ObjectIdentifier;
        WOTSPlusParameters wOTSPlusParameters = new WOTSPlusParameters(aSN1ObjectIdentifier);
        this.wotsPlusParams = wOTSPlusParameters;
        int treeDigestSize = wOTSPlusParameters.getTreeDigestSize();
        this.treeDigestSize = treeDigestSize;
        int winternitzParameter = wOTSPlusParameters.getWinternitzParameter();
        this.winternitzParameter = winternitzParameter;
        this.oid = DefaultXMSSOid.lookup(digestName, treeDigestSize, winternitzParameter, wOTSPlusParameters.getLen(), r4);
    }

    public XMSSParameters(int r1, Digest digest) {
        this(r1, DigestUtil.getDigestOID(digest.getAlgorithmName()));
    }

    private int determineMinK() {
        int r1 = 2;
        while (true) {
            int r2 = this.height;
            if (r1 > r2) {
                throw new IllegalStateException("should never happen...");
            }
            if ((r2 - r1) % 2 == 0) {
                return r1;
            }
            r1++;
        }
    }

    public static XMSSParameters lookupByOID(int r1) {
        return paramsLookupTable.get(Integers.valueOf(r1));
    }

    public int getHeight() {
        return this.height;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getK() {
        return this.f2514k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLen() {
        return this.wotsPlusParams.getLen();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XMSSOid getOid() {
        return this.oid;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTreeDigest() {
        return this.treeDigest;
    }

    public ASN1ObjectIdentifier getTreeDigestOID() {
        return this.treeDigestOID;
    }

    public int getTreeDigestSize() {
        return this.treeDigestSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlus getWOTSPlus() {
        return new WOTSPlus(this.wotsPlusParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getWinternitzParameter() {
        return this.winternitzParameter;
    }
}
