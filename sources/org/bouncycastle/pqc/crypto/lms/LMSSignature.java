package org.bouncycastle.pqc.crypto.lms;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.p041io.Streams;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class LMSSignature implements Encodable {
    private final LMOtsSignature otsSignature;
    private final LMSigParameters parameter;

    /* renamed from: q */
    private final int f2436q;

    /* renamed from: y */
    private final byte[][] f2437y;

    public LMSSignature(int r1, LMOtsSignature lMOtsSignature, LMSigParameters lMSigParameters, byte[][] bArr) {
        this.f2436q = r1;
        this.otsSignature = lMOtsSignature;
        this.parameter = lMSigParameters;
        this.f2437y = bArr;
    }

    public static LMSSignature getInstance(Object obj) throws IOException {
        DataInputStream dataInputStream;
        if (obj instanceof LMSSignature) {
            return (LMSSignature) obj;
        }
        if (obj instanceof DataInputStream) {
            DataInputStream dataInputStream2 = (DataInputStream) obj;
            int readInt = dataInputStream2.readInt();
            LMOtsSignature lMOtsSignature = LMOtsSignature.getInstance(obj);
            LMSigParameters parametersForType = LMSigParameters.getParametersForType(dataInputStream2.readInt());
            int h = parametersForType.getH();
            byte[][] bArr = new byte[h];
            for (int r5 = 0; r5 < h; r5++) {
                bArr[r5] = new byte[parametersForType.getM()];
                dataInputStream2.readFully(bArr[r5]);
            }
            return new LMSSignature(readInt, lMOtsSignature, parametersForType, bArr);
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
                LMSSignature lMSSignature = getInstance(dataInputStream);
                dataInputStream.close();
                return lMSSignature;
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LMSSignature lMSSignature = (LMSSignature) obj;
        if (this.f2436q != lMSSignature.f2436q) {
            return false;
        }
        LMOtsSignature lMOtsSignature = this.otsSignature;
        if (lMOtsSignature == null ? lMSSignature.otsSignature == null : lMOtsSignature.equals(lMSSignature.otsSignature)) {
            LMSigParameters lMSigParameters = this.parameter;
            if (lMSigParameters == null ? lMSSignature.parameter == null : lMSigParameters.equals(lMSSignature.parameter)) {
                return Arrays.deepEquals(this.f2437y, lMSSignature.f2437y);
            }
            return false;
        }
        return false;
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return Composer.compose().u32str(this.f2436q).bytes(this.otsSignature.getEncoded()).u32str(this.parameter.getType()).bytes(this.f2437y).build();
    }

    public LMOtsSignature getOtsSignature() {
        return this.otsSignature;
    }

    public LMSigParameters getParameter() {
        return this.parameter;
    }

    public int getQ() {
        return this.f2436q;
    }

    public byte[][] getY() {
        return this.f2437y;
    }

    public int hashCode() {
        int r0 = this.f2436q * 31;
        LMOtsSignature lMOtsSignature = this.otsSignature;
        int hashCode = (r0 + (lMOtsSignature != null ? lMOtsSignature.hashCode() : 0)) * 31;
        LMSigParameters lMSigParameters = this.parameter;
        return ((hashCode + (lMSigParameters != null ? lMSigParameters.hashCode() : 0)) * 31) + Arrays.deepHashCode(this.f2437y);
    }
}
