package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.pqc.crypto.rainbow.Layer;
import org.bouncycastle.pqc.crypto.rainbow.util.RainbowUtil;

/* loaded from: classes3.dex */
public class RainbowPrivateKey extends ASN1Object {

    /* renamed from: b1 */
    private byte[] f2401b1;

    /* renamed from: b2 */
    private byte[] f2402b2;
    private byte[][] invA1;
    private byte[][] invA2;
    private Layer[] layers;
    private ASN1ObjectIdentifier oid;
    private ASN1Integer version;

    /* renamed from: vi */
    private byte[] f2403vi;

    private RainbowPrivateKey(ASN1Sequence aSN1Sequence) {
        int r2 = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        } else {
            this.oid = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        }
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        this.invA1 = new byte[aSN1Sequence2.size()];
        for (int r5 = 0; r5 < aSN1Sequence2.size(); r5++) {
            this.invA1[r5] = ((ASN1OctetString) aSN1Sequence2.getObjectAt(r5)).getOctets();
        }
        this.f2401b1 = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.getObjectAt(2)).getObjectAt(0)).getOctets();
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        this.invA2 = new byte[aSN1Sequence3.size()];
        for (int r7 = 0; r7 < aSN1Sequence3.size(); r7++) {
            this.invA2[r7] = ((ASN1OctetString) aSN1Sequence3.getObjectAt(r7)).getOctets();
        }
        this.f2402b2 = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.getObjectAt(4)).getObjectAt(0)).getOctets();
        this.f2403vi = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.getObjectAt(5)).getObjectAt(0)).getOctets();
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(6);
        byte[][][][] bArr = new byte[aSN1Sequence4.size()][][];
        byte[][][][] bArr2 = new byte[aSN1Sequence4.size()][][];
        byte[][][] bArr3 = new byte[aSN1Sequence4.size()][];
        byte[][] bArr4 = new byte[aSN1Sequence4.size()];
        int r10 = 0;
        while (r10 < aSN1Sequence4.size()) {
            ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence4.getObjectAt(r10);
            ASN1Sequence aSN1Sequence6 = (ASN1Sequence) aSN1Sequence5.getObjectAt(r2);
            bArr[r10] = new byte[aSN1Sequence6.size()][];
            for (int r13 = 0; r13 < aSN1Sequence6.size(); r13++) {
                ASN1Sequence aSN1Sequence7 = (ASN1Sequence) aSN1Sequence6.getObjectAt(r13);
                bArr[r10][r13] = new byte[aSN1Sequence7.size()];
                for (int r22 = 0; r22 < aSN1Sequence7.size(); r22++) {
                    bArr[r10][r13][r22] = ((ASN1OctetString) aSN1Sequence7.getObjectAt(r22)).getOctets();
                }
            }
            ASN1Sequence aSN1Sequence8 = (ASN1Sequence) aSN1Sequence5.getObjectAt(1);
            bArr2[r10] = new byte[aSN1Sequence8.size()][];
            for (int r12 = 0; r12 < aSN1Sequence8.size(); r12++) {
                ASN1Sequence aSN1Sequence9 = (ASN1Sequence) aSN1Sequence8.getObjectAt(r12);
                bArr2[r10][r12] = new byte[aSN1Sequence9.size()];
                for (int r14 = 0; r14 < aSN1Sequence9.size(); r14++) {
                    bArr2[r10][r12][r14] = ((ASN1OctetString) aSN1Sequence9.getObjectAt(r14)).getOctets();
                }
            }
            ASN1Sequence aSN1Sequence10 = (ASN1Sequence) aSN1Sequence5.getObjectAt(2);
            bArr3[r10] = new byte[aSN1Sequence10.size()];
            for (int r122 = 0; r122 < aSN1Sequence10.size(); r122++) {
                bArr3[r10][r122] = ((ASN1OctetString) aSN1Sequence10.getObjectAt(r122)).getOctets();
            }
            bArr4[r10] = ((ASN1OctetString) aSN1Sequence5.getObjectAt(3)).getOctets();
            r10++;
            r2 = 0;
        }
        int length = this.f2403vi.length - 1;
        this.layers = new Layer[length];
        int r23 = 0;
        while (r23 < length) {
            byte[] bArr5 = this.f2403vi;
            int r52 = r23 + 1;
            this.layers[r23] = new Layer(bArr5[r23], bArr5[r52], RainbowUtil.convertArray(bArr[r23]), RainbowUtil.convertArray(bArr2[r23]), RainbowUtil.convertArray(bArr3[r23]), RainbowUtil.convertArray(bArr4[r23]));
            r23 = r52;
        }
    }

    public RainbowPrivateKey(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] r8, Layer[] layerArr) {
        this.version = new ASN1Integer(1L);
        this.invA1 = RainbowUtil.convertArray(sArr);
        this.f2401b1 = RainbowUtil.convertArray(sArr2);
        this.invA2 = RainbowUtil.convertArray(sArr3);
        this.f2402b2 = RainbowUtil.convertArray(sArr4);
        this.f2403vi = RainbowUtil.convertIntArray(r8);
        this.layers = layerArr;
    }

    public static RainbowPrivateKey getInstance(Object obj) {
        if (obj instanceof RainbowPrivateKey) {
            return (RainbowPrivateKey) obj;
        }
        if (obj != null) {
            return new RainbowPrivateKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public short[] getB1() {
        return RainbowUtil.convertArray(this.f2401b1);
    }

    public short[] getB2() {
        return RainbowUtil.convertArray(this.f2402b2);
    }

    public short[][] getInvA1() {
        return RainbowUtil.convertArray(this.invA1);
    }

    public short[][] getInvA2() {
        return RainbowUtil.convertArray(this.invA2);
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public int[] getVi() {
        return RainbowUtil.convertArraytoInt(this.f2403vi);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Encodable aSN1Encodable = this.version;
        if (aSN1Encodable == null) {
            aSN1Encodable = this.oid;
        }
        aSN1EncodableVector.add(aSN1Encodable);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (int r3 = 0; r3 < this.invA1.length; r3++) {
            aSN1EncodableVector2.add(new DEROctetString(this.invA1[r3]));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        aSN1EncodableVector3.add(new DEROctetString(this.f2401b1));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector3));
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        for (int r32 = 0; r32 < this.invA2.length; r32++) {
            aSN1EncodableVector4.add(new DEROctetString(this.invA2[r32]));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector4));
        ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
        aSN1EncodableVector5.add(new DEROctetString(this.f2402b2));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector5));
        ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
        aSN1EncodableVector6.add(new DEROctetString(this.f2403vi));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector6));
        ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
        for (int r33 = 0; r33 < this.layers.length; r33++) {
            ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
            byte[][][] convertArray = RainbowUtil.convertArray(this.layers[r33].getCoeffAlpha());
            ASN1EncodableVector aSN1EncodableVector9 = new ASN1EncodableVector();
            for (int r7 = 0; r7 < convertArray.length; r7++) {
                ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
                for (int r9 = 0; r9 < convertArray[r7].length; r9++) {
                    aSN1EncodableVector10.add(new DEROctetString(convertArray[r7][r9]));
                }
                aSN1EncodableVector9.add(new DERSequence(aSN1EncodableVector10));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector9));
            byte[][][] convertArray2 = RainbowUtil.convertArray(this.layers[r33].getCoeffBeta());
            ASN1EncodableVector aSN1EncodableVector11 = new ASN1EncodableVector();
            for (int r72 = 0; r72 < convertArray2.length; r72++) {
                ASN1EncodableVector aSN1EncodableVector12 = new ASN1EncodableVector();
                for (int r92 = 0; r92 < convertArray2[r72].length; r92++) {
                    aSN1EncodableVector12.add(new DEROctetString(convertArray2[r72][r92]));
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector11));
            byte[][] convertArray3 = RainbowUtil.convertArray(this.layers[r33].getCoeffGamma());
            ASN1EncodableVector aSN1EncodableVector13 = new ASN1EncodableVector();
            for (byte[] bArr : convertArray3) {
                aSN1EncodableVector13.add(new DEROctetString(bArr));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector13));
            aSN1EncodableVector8.add(new DEROctetString(RainbowUtil.convertArray(this.layers[r33].getCoeffEta())));
            aSN1EncodableVector7.add(new DERSequence(aSN1EncodableVector8));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector7));
        return new DERSequence(aSN1EncodableVector);
    }
}
