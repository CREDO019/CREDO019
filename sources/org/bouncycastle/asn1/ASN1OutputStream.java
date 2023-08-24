package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class ASN1OutputStream {

    /* renamed from: os */
    private OutputStream f1586os;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1OutputStream(OutputStream outputStream) {
        this.f1586os = outputStream;
    }

    public static ASN1OutputStream create(OutputStream outputStream) {
        return new ASN1OutputStream(outputStream);
    }

    public static ASN1OutputStream create(OutputStream outputStream, String str) {
        return str.equals(ASN1Encoding.DER) ? new DEROutputStream(outputStream) : str.equals(ASN1Encoding.f1585DL) ? new DLOutputStream(outputStream) : new ASN1OutputStream(outputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getLengthOfDL(int r1) {
        if (r1 < 128) {
            return 1;
        }
        int r0 = 2;
        while (true) {
            r1 >>>= 8;
            if (r1 == 0) {
                return r0;
            }
            r0++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getLengthOfEncodingDL(boolean z, int r2) {
        return (z ? 1 : 0) + getLengthOfDL(r2) + r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getLengthOfIdentifier(int r1) {
        if (r1 < 31) {
            return 1;
        }
        int r0 = 2;
        while (true) {
            r1 >>>= 7;
            if (r1 == 0) {
                return r0;
            }
            r0++;
        }
    }

    public void close() throws IOException {
        this.f1586os.close();
    }

    public void flush() throws IOException {
        this.f1586os.flush();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flushInternal() throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DEROutputStream getDERSubStream() {
        return new DEROutputStream(this.f1586os);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DLOutputStream getDLSubStream() {
        return new DLOutputStream(this.f1586os);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void write(int r2) throws IOException {
        this.f1586os.write(r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void write(byte[] bArr, int r3, int r4) throws IOException {
        this.f1586os.write(bArr, r3, r4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeDL(int r4) throws IOException {
        if (r4 < 128) {
            write(r4);
            return;
        }
        int r0 = 5;
        byte[] bArr = new byte[5];
        do {
            r0--;
            bArr[r0] = (byte) r4;
            r4 >>>= 8;
        } while (r4 != 0);
        int r42 = 5 - r0;
        int r02 = r0 - 1;
        bArr[r02] = (byte) (r42 | 128);
        write(bArr, r02, r42 + 1);
    }

    void writeElements(ASN1Encodable[] aSN1EncodableArr) throws IOException {
        for (ASN1Encodable aSN1Encodable : aSN1EncodableArr) {
            aSN1Encodable.toASN1Primitive().encode(this, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncodingDL(boolean z, int r2, byte b) throws IOException {
        writeIdentifier(z, r2);
        writeDL(1);
        write(b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncodingDL(boolean z, int r2, byte b, byte[] bArr, int r5, int r6) throws IOException {
        writeIdentifier(z, r2);
        writeDL(r6 + 1);
        write(b);
        write(bArr, r5, r6);
    }

    final void writeEncodingDL(boolean z, int r2, int r3, byte[] bArr) throws IOException {
        writeIdentifier(z, r2, r3);
        writeDL(bArr.length);
        write(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncodingDL(boolean z, int r2, byte[] bArr) throws IOException {
        writeIdentifier(z, r2);
        writeDL(bArr.length);
        write(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncodingDL(boolean z, int r2, byte[] bArr, int r4, int r5) throws IOException {
        writeIdentifier(z, r2);
        writeDL(r5);
        write(bArr, r4, r5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncodingDL(boolean z, int r2, byte[] bArr, int r4, int r5, byte b) throws IOException {
        writeIdentifier(z, r2);
        writeDL(r5 + 1);
        write(bArr, r4, r5);
        write(b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncodingIL(boolean z, int r2, ASN1Encodable[] aSN1EncodableArr) throws IOException {
        writeIdentifier(z, r2);
        write(128);
        writeElements(aSN1EncodableArr);
        write(0);
        write(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeIdentifier(boolean z, int r2) throws IOException {
        if (z) {
            write(r2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeIdentifier(boolean z, int r5, int r6) throws IOException {
        if (z) {
            if (r6 < 31) {
                write(r5 | r6);
                return;
            }
            byte[] bArr = new byte[6];
            int r1 = 5;
            bArr[5] = (byte) (r6 & 127);
            while (r6 > 127) {
                r6 >>>= 7;
                r1--;
                bArr[r1] = (byte) ((r6 & 127) | 128);
            }
            int r12 = r1 - 1;
            bArr[r12] = (byte) (31 | r5);
            write(bArr, r12, 6 - r12);
        }
    }

    public final void writeObject(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable == null) {
            throw new IOException("null object detected");
        }
        writePrimitive(aSN1Encodable.toASN1Primitive(), true);
        flushInternal();
    }

    public final void writeObject(ASN1Primitive aSN1Primitive) throws IOException {
        if (aSN1Primitive == null) {
            throw new IOException("null object detected");
        }
        writePrimitive(aSN1Primitive, true);
        flushInternal();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writePrimitive(ASN1Primitive aSN1Primitive, boolean z) throws IOException {
        aSN1Primitive.encode(this, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writePrimitives(ASN1Primitive[] aSN1PrimitiveArr) throws IOException {
        for (ASN1Primitive aSN1Primitive : aSN1PrimitiveArr) {
            aSN1Primitive.encode(this, true);
        }
    }
}
