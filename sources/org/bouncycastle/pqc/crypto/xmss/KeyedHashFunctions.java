package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class KeyedHashFunctions {
    private final Digest digest;
    private final int digestSize;

    /* JADX INFO: Access modifiers changed from: protected */
    public KeyedHashFunctions(ASN1ObjectIdentifier aSN1ObjectIdentifier, int r3) {
        Objects.requireNonNull(aSN1ObjectIdentifier, "digest == null");
        this.digest = DigestUtil.getDigest(aSN1ObjectIdentifier);
        this.digestSize = r3;
    }

    private byte[] coreDigest(int r4, byte[] bArr, byte[] bArr2) {
        byte[] bytesBigEndian = XMSSUtil.toBytesBigEndian(r4, this.digestSize);
        this.digest.update(bytesBigEndian, 0, bytesBigEndian.length);
        this.digest.update(bArr, 0, bArr.length);
        this.digest.update(bArr2, 0, bArr2.length);
        int r42 = this.digestSize;
        byte[] bArr3 = new byte[r42];
        Digest digest = this.digest;
        if (digest instanceof Xof) {
            ((Xof) digest).doFinal(bArr3, 0, r42);
        } else {
            digest.doFinal(bArr3, 0);
        }
        return bArr3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: F */
    public byte[] m6F(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int r1 = this.digestSize;
        if (length == r1) {
            if (bArr2.length == r1) {
                return coreDigest(0, bArr, bArr2);
            }
            throw new IllegalArgumentException("wrong in length");
        }
        throw new IllegalArgumentException("wrong key length");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: H */
    public byte[] m5H(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int r1 = this.digestSize;
        if (length == r1) {
            if (bArr2.length == r1 * 2) {
                return coreDigest(1, bArr, bArr2);
            }
            throw new IllegalArgumentException("wrong in length");
        }
        throw new IllegalArgumentException("wrong key length");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] HMsg(byte[] bArr, byte[] bArr2) {
        if (bArr.length == this.digestSize * 3) {
            return coreDigest(2, bArr, bArr2);
        }
        throw new IllegalArgumentException("wrong key length");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] PRF(byte[] bArr, byte[] bArr2) {
        if (bArr.length == this.digestSize) {
            if (bArr2.length == 32) {
                return coreDigest(3, bArr, bArr2);
            }
            throw new IllegalArgumentException("wrong address length");
        }
        throw new IllegalArgumentException("wrong key length");
    }
}
