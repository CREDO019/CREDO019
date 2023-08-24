package org.bouncycastle.pqc.crypto.lms;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.p041io.Streams;

/* loaded from: classes3.dex */
class LMOtsPublicKey implements Encodable {

    /* renamed from: I */
    private final byte[] f2425I;

    /* renamed from: K */
    private final byte[] f2426K;
    private final LMOtsParameters parameter;

    /* renamed from: q */
    private final int f2427q;

    public LMOtsPublicKey(LMOtsParameters lMOtsParameters, byte[] bArr, int r3, byte[] bArr2) {
        this.parameter = lMOtsParameters;
        this.f2425I = bArr;
        this.f2427q = r3;
        this.f2426K = bArr2;
    }

    public static LMOtsPublicKey getInstance(Object obj) throws Exception {
        DataInputStream dataInputStream;
        if (obj instanceof LMOtsPublicKey) {
            return (LMOtsPublicKey) obj;
        }
        if (obj instanceof DataInputStream) {
            DataInputStream dataInputStream2 = (DataInputStream) obj;
            LMOtsParameters parametersForType = LMOtsParameters.getParametersForType(dataInputStream2.readInt());
            byte[] bArr = new byte[16];
            dataInputStream2.readFully(bArr);
            int readInt = dataInputStream2.readInt();
            byte[] bArr2 = new byte[parametersForType.getN()];
            dataInputStream2.readFully(bArr2);
            return new LMOtsPublicKey(parametersForType, bArr, readInt, bArr2);
        } else if (!(obj instanceof byte[])) {
            if (obj instanceof InputStream) {
                return getInstance(Streams.readAll((InputStream) obj));
            }
            throw new IllegalArgumentException("cannot parse " + obj);
        } else {
            DataInputStream dataInputStream3 = null;
            try {
                dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) obj));
            } catch (Throwable th) {
                th = th;
            }
            try {
                LMOtsPublicKey lMOtsPublicKey = getInstance(dataInputStream);
                dataInputStream.close();
                return lMOtsPublicKey;
            } catch (Throwable th2) {
                th = th2;
                dataInputStream3 = dataInputStream;
                if (dataInputStream3 != null) {
                    dataInputStream3.close();
                }
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMSContext createOtsContext(LMOtsSignature lMOtsSignature) {
        Digest digest = DigestUtil.getDigest(this.parameter.getDigestOID());
        LmsUtils.byteArray(this.f2425I, digest);
        LmsUtils.u32str(this.f2427q, digest);
        LmsUtils.u16str((short) -32383, digest);
        LmsUtils.byteArray(lMOtsSignature.getC(), digest);
        return new LMSContext(this, lMOtsSignature, digest);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMSContext createOtsContext(LMSSignature lMSSignature) {
        Digest digest = DigestUtil.getDigest(this.parameter.getDigestOID());
        LmsUtils.byteArray(this.f2425I, digest);
        LmsUtils.u32str(this.f2427q, digest);
        LmsUtils.u16str((short) -32383, digest);
        LmsUtils.byteArray(lMSSignature.getOtsSignature().getC(), digest);
        return new LMSContext(this, lMSSignature, digest);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LMOtsPublicKey lMOtsPublicKey = (LMOtsPublicKey) obj;
        if (this.f2427q != lMOtsPublicKey.f2427q) {
            return false;
        }
        LMOtsParameters lMOtsParameters = this.parameter;
        if (lMOtsParameters == null ? lMOtsPublicKey.parameter == null : lMOtsParameters.equals(lMOtsPublicKey.parameter)) {
            if (Arrays.equals(this.f2425I, lMOtsPublicKey.f2425I)) {
                return Arrays.equals(this.f2426K, lMOtsPublicKey.f2426K);
            }
            return false;
        }
        return false;
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return Composer.compose().u32str(this.parameter.getType()).bytes(this.f2425I).u32str(this.f2427q).bytes(this.f2426K).build();
    }

    public byte[] getI() {
        return this.f2425I;
    }

    public byte[] getK() {
        return this.f2426K;
    }

    public LMOtsParameters getParameter() {
        return this.parameter;
    }

    public int getQ() {
        return this.f2427q;
    }

    public int hashCode() {
        LMOtsParameters lMOtsParameters = this.parameter;
        return ((((((lMOtsParameters != null ? lMOtsParameters.hashCode() : 0) * 31) + Arrays.hashCode(this.f2425I)) * 31) + this.f2427q) * 31) + Arrays.hashCode(this.f2426K);
    }
}
