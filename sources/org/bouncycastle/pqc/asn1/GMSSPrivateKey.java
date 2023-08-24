package org.bouncycastle.pqc.asn1;

import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.pqc.crypto.gmss.GMSSLeaf;
import org.bouncycastle.pqc.crypto.gmss.GMSSParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSRootCalc;
import org.bouncycastle.pqc.crypto.gmss.GMSSRootSig;
import org.bouncycastle.pqc.crypto.gmss.Treehash;

/* loaded from: classes3.dex */
public class GMSSPrivateKey extends ASN1Object {
    private ASN1Primitive primitive;

    private GMSSPrivateKey(ASN1Sequence aSN1Sequence) {
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(0);
        int[] r2 = new int[aSN1Sequence2.size()];
        for (int r3 = 0; r3 < aSN1Sequence2.size(); r3++) {
            r2[r3] = checkBigIntegerInIntRange(aSN1Sequence2.getObjectAt(r3));
        }
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        int size = aSN1Sequence3.size();
        byte[][] bArr = new byte[size];
        for (int r4 = 0; r4 < size; r4++) {
            bArr[r4] = ((DEROctetString) aSN1Sequence3.getObjectAt(r4)).getOctets();
        }
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(2);
        int size2 = aSN1Sequence4.size();
        byte[][] bArr2 = new byte[size2];
        for (int r42 = 0; r42 < size2; r42++) {
            bArr2[r42] = ((DEROctetString) aSN1Sequence4.getObjectAt(r42)).getOctets();
        }
        ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        int size3 = aSN1Sequence5.size();
        byte[][][] bArr3 = new byte[size3][];
        for (int r43 = 0; r43 < size3; r43++) {
            ASN1Sequence aSN1Sequence6 = (ASN1Sequence) aSN1Sequence5.getObjectAt(r43);
            bArr3[r43] = new byte[aSN1Sequence6.size()];
            for (int r6 = 0; r6 < bArr3[r43].length; r6++) {
                bArr3[r43][r6] = ((DEROctetString) aSN1Sequence6.getObjectAt(r6)).getOctets();
            }
        }
        ASN1Sequence aSN1Sequence7 = (ASN1Sequence) aSN1Sequence.getObjectAt(4);
        int size4 = aSN1Sequence7.size();
        byte[][][] bArr4 = new byte[size4][];
        for (int r44 = 0; r44 < size4; r44++) {
            ASN1Sequence aSN1Sequence8 = (ASN1Sequence) aSN1Sequence7.getObjectAt(r44);
            bArr4[r44] = new byte[aSN1Sequence8.size()];
            for (int r62 = 0; r62 < bArr4[r44].length; r62++) {
                bArr4[r44][r62] = ((DEROctetString) aSN1Sequence8.getObjectAt(r62)).getOctets();
            }
        }
        Treehash[][] treehashArr = new Treehash[((ASN1Sequence) aSN1Sequence.getObjectAt(5)).size()];
    }

    public GMSSPrivateKey(int[] r26, byte[][] bArr, byte[][] bArr2, byte[][][] bArr3, byte[][][] bArr4, Treehash[][] treehashArr, Treehash[][] treehashArr2, Vector[] vectorArr, Vector[] vectorArr2, Vector[][] vectorArr3, Vector[][] vectorArr4, byte[][][] bArr5, GMSSLeaf[] gMSSLeafArr, GMSSLeaf[] gMSSLeafArr2, GMSSLeaf[] gMSSLeafArr3, int[] r41, byte[][] bArr6, GMSSRootCalc[] gMSSRootCalcArr, byte[][] bArr7, GMSSRootSig[] gMSSRootSigArr, GMSSParameters gMSSParameters, AlgorithmIdentifier algorithmIdentifier) {
        this.primitive = encode(r26, bArr, bArr2, bArr3, bArr4, bArr5, treehashArr, treehashArr2, vectorArr, vectorArr2, vectorArr3, vectorArr4, gMSSLeafArr, gMSSLeafArr2, gMSSLeafArr3, r41, bArr6, gMSSRootCalcArr, bArr7, gMSSRootSigArr, gMSSParameters, new AlgorithmIdentifier[]{algorithmIdentifier});
    }

    private static int checkBigIntegerInIntRange(ASN1Encodable aSN1Encodable) {
        return ((ASN1Integer) aSN1Encodable).intValueExact();
    }

    private ASN1Primitive encode(int[] r22, byte[][] bArr, byte[][] bArr2, byte[][][] bArr3, byte[][][] bArr4, byte[][][] bArr5, Treehash[][] treehashArr, Treehash[][] treehashArr2, Vector[] vectorArr, Vector[] vectorArr2, Vector[][] vectorArr3, Vector[][] vectorArr4, GMSSLeaf[] gMSSLeafArr, GMSSLeaf[] gMSSLeafArr2, GMSSLeaf[] gMSSLeafArr3, int[] r37, byte[][] bArr6, GMSSRootCalc[] gMSSRootCalcArr, byte[][] bArr7, GMSSRootSig[] gMSSRootSigArr, GMSSParameters gMSSParameters, AlgorithmIdentifier[] algorithmIdentifierArr) {
        Treehash[][] treehashArr3 = treehashArr;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (int r10 : r22) {
            aSN1EncodableVector2.add(new ASN1Integer(r10));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        for (byte[] bArr8 : bArr) {
            aSN1EncodableVector3.add(new DEROctetString(bArr8));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector3));
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        for (byte[] bArr9 : bArr2) {
            aSN1EncodableVector4.add(new DEROctetString(bArr9));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector4));
        ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
        for (int r2 = 0; r2 < bArr3.length; r2++) {
            for (int r9 = 0; r9 < bArr3[r2].length; r9++) {
                aSN1EncodableVector5.add(new DEROctetString(bArr3[r2][r9]));
            }
            aSN1EncodableVector6.add(new DERSequence(aSN1EncodableVector5));
            aSN1EncodableVector5 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector6));
        ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
        for (int r23 = 0; r23 < bArr4.length; r23++) {
            for (int r3 = 0; r3 < bArr4[r23].length; r3++) {
                aSN1EncodableVector7.add(new DEROctetString(bArr4[r23][r3]));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector7));
            aSN1EncodableVector7 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector8));
        ASN1EncodableVector aSN1EncodableVector9 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector11 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector12 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector13 = new ASN1EncodableVector();
        int r92 = 0;
        while (r92 < treehashArr3.length) {
            int r102 = 0;
            while (r102 < treehashArr3[r92].length) {
                aSN1EncodableVector11.add(new DERSequence(algorithmIdentifierArr[0]));
                int r11 = treehashArr3[r92][r102].getStatInt()[1];
                aSN1EncodableVector12.add(new DEROctetString(treehashArr3[r92][r102].getStatByte()[0]));
                aSN1EncodableVector12.add(new DEROctetString(treehashArr3[r92][r102].getStatByte()[1]));
                aSN1EncodableVector12.add(new DEROctetString(treehashArr3[r92][r102].getStatByte()[2]));
                for (int r12 = 0; r12 < r11; r12++) {
                    aSN1EncodableVector12.add(new DEROctetString(treehashArr3[r92][r102].getStatByte()[r12 + 3]));
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
                aSN1EncodableVector12 = new ASN1EncodableVector();
                aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[r92][r102].getStatInt()[0]));
                aSN1EncodableVector13.add(new ASN1Integer(r11));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[r92][r102].getStatInt()[2]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[r92][r102].getStatInt()[3]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[r92][r102].getStatInt()[4]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[r92][r102].getStatInt()[5]));
                int r8 = 0;
                while (r8 < r11) {
                    aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[r92][r102].getStatInt()[r8 + 6]));
                    r8++;
                    treehashArr3 = treehashArr;
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector13));
                aSN1EncodableVector13 = new ASN1EncodableVector();
                aSN1EncodableVector10.add(new DERSequence(aSN1EncodableVector11));
                aSN1EncodableVector11 = new ASN1EncodableVector();
                r102++;
                treehashArr3 = treehashArr;
            }
            aSN1EncodableVector9.add(new DERSequence(aSN1EncodableVector10));
            aSN1EncodableVector10 = new ASN1EncodableVector();
            r92++;
            treehashArr3 = treehashArr;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector9));
        ASN1EncodableVector aSN1EncodableVector14 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector15 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector16 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector17 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector18 = new ASN1EncodableVector();
        for (int r5 = 0; r5 < treehashArr2.length; r5++) {
            for (int r6 = 0; r6 < treehashArr2[r5].length; r6++) {
                aSN1EncodableVector16.add(new DERSequence(algorithmIdentifierArr[0]));
                int r82 = treehashArr2[r5][r6].getStatInt()[1];
                aSN1EncodableVector17.add(new DEROctetString(treehashArr2[r5][r6].getStatByte()[0]));
                aSN1EncodableVector17.add(new DEROctetString(treehashArr2[r5][r6].getStatByte()[1]));
                aSN1EncodableVector17.add(new DEROctetString(treehashArr2[r5][r6].getStatByte()[2]));
                for (int r93 = 0; r93 < r82; r93++) {
                    aSN1EncodableVector17.add(new DEROctetString(treehashArr2[r5][r6].getStatByte()[r93 + 3]));
                }
                aSN1EncodableVector16.add(new DERSequence(aSN1EncodableVector17));
                aSN1EncodableVector17 = new ASN1EncodableVector();
                aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[r5][r6].getStatInt()[0]));
                aSN1EncodableVector18.add(new ASN1Integer(r82));
                aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[r5][r6].getStatInt()[2]));
                aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[r5][r6].getStatInt()[3]));
                aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[r5][r6].getStatInt()[4]));
                aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[r5][r6].getStatInt()[5]));
                for (int r94 = 0; r94 < r82; r94++) {
                    aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[r5][r6].getStatInt()[r94 + 6]));
                }
                aSN1EncodableVector16.add(new DERSequence(aSN1EncodableVector18));
                aSN1EncodableVector18 = new ASN1EncodableVector();
                aSN1EncodableVector15.add(new DERSequence(aSN1EncodableVector16));
                aSN1EncodableVector16 = new ASN1EncodableVector();
            }
            aSN1EncodableVector14.add(new DERSequence(new DERSequence(aSN1EncodableVector15)));
            aSN1EncodableVector15 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector14));
        ASN1EncodableVector aSN1EncodableVector19 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector20 = new ASN1EncodableVector();
        for (int r32 = 0; r32 < bArr5.length; r32++) {
            for (int r4 = 0; r4 < bArr5[r32].length; r4++) {
                aSN1EncodableVector19.add(new DEROctetString(bArr5[r32][r4]));
            }
            aSN1EncodableVector20.add(new DERSequence(aSN1EncodableVector19));
            aSN1EncodableVector19 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector20));
        ASN1EncodableVector aSN1EncodableVector21 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector22 = new ASN1EncodableVector();
        for (int r33 = 0; r33 < vectorArr.length; r33++) {
            for (int r42 = 0; r42 < vectorArr[r33].size(); r42++) {
                aSN1EncodableVector21.add(new DEROctetString((byte[]) vectorArr[r33].elementAt(r42)));
            }
            aSN1EncodableVector22.add(new DERSequence(aSN1EncodableVector21));
            aSN1EncodableVector21 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector22));
        ASN1EncodableVector aSN1EncodableVector23 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector24 = new ASN1EncodableVector();
        for (int r34 = 0; r34 < vectorArr2.length; r34++) {
            for (int r43 = 0; r43 < vectorArr2[r34].size(); r43++) {
                aSN1EncodableVector23.add(new DEROctetString((byte[]) vectorArr2[r34].elementAt(r43)));
            }
            aSN1EncodableVector24.add(new DERSequence(aSN1EncodableVector23));
            aSN1EncodableVector23 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector24));
        ASN1EncodableVector aSN1EncodableVector25 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector26 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector27 = new ASN1EncodableVector();
        for (int r44 = 0; r44 < vectorArr3.length; r44++) {
            for (int r52 = 0; r52 < vectorArr3[r44].length; r52++) {
                for (int r62 = 0; r62 < vectorArr3[r44][r52].size(); r62++) {
                    aSN1EncodableVector25.add(new DEROctetString((byte[]) vectorArr3[r44][r52].elementAt(r62)));
                }
                aSN1EncodableVector26.add(new DERSequence(aSN1EncodableVector25));
                aSN1EncodableVector25 = new ASN1EncodableVector();
            }
            aSN1EncodableVector27.add(new DERSequence(aSN1EncodableVector26));
            aSN1EncodableVector26 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector27));
        ASN1EncodableVector aSN1EncodableVector28 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector29 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector30 = new ASN1EncodableVector();
        for (int r45 = 0; r45 < vectorArr4.length; r45++) {
            for (int r53 = 0; r53 < vectorArr4[r45].length; r53++) {
                for (int r63 = 0; r63 < vectorArr4[r45][r53].size(); r63++) {
                    aSN1EncodableVector28.add(new DEROctetString((byte[]) vectorArr4[r45][r53].elementAt(r63)));
                }
                aSN1EncodableVector29.add(new DERSequence(aSN1EncodableVector28));
                aSN1EncodableVector28 = new ASN1EncodableVector();
            }
            aSN1EncodableVector30.add(new DERSequence(aSN1EncodableVector29));
            aSN1EncodableVector29 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector30));
        ASN1EncodableVector aSN1EncodableVector31 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector32 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector33 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector34 = new ASN1EncodableVector();
        for (int r54 = 0; r54 < gMSSLeafArr.length; r54++) {
            aSN1EncodableVector32.add(new DERSequence(algorithmIdentifierArr[0]));
            byte[][] statByte = gMSSLeafArr[r54].getStatByte();
            aSN1EncodableVector33.add(new DEROctetString(statByte[0]));
            aSN1EncodableVector33.add(new DEROctetString(statByte[1]));
            aSN1EncodableVector33.add(new DEROctetString(statByte[2]));
            aSN1EncodableVector33.add(new DEROctetString(statByte[3]));
            aSN1EncodableVector32.add(new DERSequence(aSN1EncodableVector33));
            aSN1EncodableVector33 = new ASN1EncodableVector();
            int[] statInt = gMSSLeafArr[r54].getStatInt();
            aSN1EncodableVector34.add(new ASN1Integer(statInt[0]));
            aSN1EncodableVector34.add(new ASN1Integer(statInt[1]));
            aSN1EncodableVector34.add(new ASN1Integer(statInt[2]));
            aSN1EncodableVector34.add(new ASN1Integer(statInt[3]));
            aSN1EncodableVector32.add(new DERSequence(aSN1EncodableVector34));
            aSN1EncodableVector34 = new ASN1EncodableVector();
            aSN1EncodableVector31.add(new DERSequence(aSN1EncodableVector32));
            aSN1EncodableVector32 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector31));
        ASN1EncodableVector aSN1EncodableVector35 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector36 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector37 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector38 = new ASN1EncodableVector();
        for (int r55 = 0; r55 < gMSSLeafArr2.length; r55++) {
            aSN1EncodableVector36.add(new DERSequence(algorithmIdentifierArr[0]));
            byte[][] statByte2 = gMSSLeafArr2[r55].getStatByte();
            aSN1EncodableVector37.add(new DEROctetString(statByte2[0]));
            aSN1EncodableVector37.add(new DEROctetString(statByte2[1]));
            aSN1EncodableVector37.add(new DEROctetString(statByte2[2]));
            aSN1EncodableVector37.add(new DEROctetString(statByte2[3]));
            aSN1EncodableVector36.add(new DERSequence(aSN1EncodableVector37));
            aSN1EncodableVector37 = new ASN1EncodableVector();
            int[] statInt2 = gMSSLeafArr2[r55].getStatInt();
            aSN1EncodableVector38.add(new ASN1Integer(statInt2[0]));
            aSN1EncodableVector38.add(new ASN1Integer(statInt2[1]));
            aSN1EncodableVector38.add(new ASN1Integer(statInt2[2]));
            aSN1EncodableVector38.add(new ASN1Integer(statInt2[3]));
            aSN1EncodableVector36.add(new DERSequence(aSN1EncodableVector38));
            aSN1EncodableVector38 = new ASN1EncodableVector();
            aSN1EncodableVector35.add(new DERSequence(aSN1EncodableVector36));
            aSN1EncodableVector36 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector35));
        ASN1EncodableVector aSN1EncodableVector39 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector40 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector41 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector42 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector43 = aSN1EncodableVector;
        for (int r64 = 0; r64 < gMSSLeafArr3.length; r64++) {
            aSN1EncodableVector40.add(new DERSequence(algorithmIdentifierArr[0]));
            byte[][] statByte3 = gMSSLeafArr3[r64].getStatByte();
            aSN1EncodableVector41.add(new DEROctetString(statByte3[0]));
            aSN1EncodableVector41.add(new DEROctetString(statByte3[1]));
            aSN1EncodableVector41.add(new DEROctetString(statByte3[2]));
            aSN1EncodableVector41.add(new DEROctetString(statByte3[3]));
            aSN1EncodableVector40.add(new DERSequence(aSN1EncodableVector41));
            aSN1EncodableVector41 = new ASN1EncodableVector();
            int[] statInt3 = gMSSLeafArr3[r64].getStatInt();
            aSN1EncodableVector42.add(new ASN1Integer(statInt3[0]));
            aSN1EncodableVector42.add(new ASN1Integer(statInt3[1]));
            aSN1EncodableVector42.add(new ASN1Integer(statInt3[2]));
            aSN1EncodableVector42.add(new ASN1Integer(statInt3[3]));
            aSN1EncodableVector40.add(new DERSequence(aSN1EncodableVector42));
            aSN1EncodableVector42 = new ASN1EncodableVector();
            aSN1EncodableVector39.add(new DERSequence(aSN1EncodableVector40));
            aSN1EncodableVector40 = new ASN1EncodableVector();
        }
        aSN1EncodableVector43.add(new DERSequence(aSN1EncodableVector39));
        ASN1EncodableVector aSN1EncodableVector44 = new ASN1EncodableVector();
        for (int r65 : r37) {
            aSN1EncodableVector44.add(new ASN1Integer(r65));
        }
        aSN1EncodableVector43.add(new DERSequence(aSN1EncodableVector44));
        ASN1EncodableVector aSN1EncodableVector45 = new ASN1EncodableVector();
        for (byte[] bArr10 : bArr6) {
            aSN1EncodableVector45.add(new DEROctetString(bArr10));
        }
        aSN1EncodableVector43.add(new DERSequence(aSN1EncodableVector45));
        ASN1EncodableVector aSN1EncodableVector46 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector47 = new ASN1EncodableVector();
        new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector48 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector49 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector50 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector51 = new ASN1EncodableVector();
        int r95 = 0;
        while (r95 < gMSSRootCalcArr.length) {
            aSN1EncodableVector47.add(new DERSequence(algorithmIdentifierArr[0]));
            new ASN1EncodableVector();
            int r103 = gMSSRootCalcArr[r95].getStatInt()[0];
            int r112 = gMSSRootCalcArr[r95].getStatInt()[7];
            aSN1EncodableVector48.add(new DEROctetString(gMSSRootCalcArr[r95].getStatByte()[0]));
            int r13 = 0;
            while (r13 < r103) {
                r13++;
                aSN1EncodableVector48.add(new DEROctetString(gMSSRootCalcArr[r95].getStatByte()[r13]));
            }
            for (int r132 = 0; r132 < r112; r132++) {
                aSN1EncodableVector48.add(new DEROctetString(gMSSRootCalcArr[r95].getStatByte()[r103 + 1 + r132]));
            }
            aSN1EncodableVector47.add(new DERSequence(aSN1EncodableVector48));
            ASN1EncodableVector aSN1EncodableVector52 = new ASN1EncodableVector();
            aSN1EncodableVector49.add(new ASN1Integer(r103));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[r95].getStatInt()[1]));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[r95].getStatInt()[2]));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[r95].getStatInt()[3]));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[r95].getStatInt()[4]));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[r95].getStatInt()[5]));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[r95].getStatInt()[6]));
            aSN1EncodableVector49.add(new ASN1Integer(r112));
            for (int r122 = 0; r122 < r103; r122++) {
                aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[r95].getStatInt()[r122 + 8]));
            }
            for (int r123 = 0; r123 < r112; r123++) {
                aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[r95].getStatInt()[r103 + 8 + r123]));
            }
            aSN1EncodableVector47.add(new DERSequence(aSN1EncodableVector49));
            ASN1EncodableVector aSN1EncodableVector53 = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector54 = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector55 = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector56 = new ASN1EncodableVector();
            if (gMSSRootCalcArr[r95].getTreehash() != null) {
                int r133 = 0;
                while (r133 < gMSSRootCalcArr[r95].getTreehash().length) {
                    aSN1EncodableVector54.add(new DERSequence(algorithmIdentifierArr[0]));
                    int r14 = gMSSRootCalcArr[r95].getTreehash()[r133].getStatInt()[1];
                    ASN1EncodableVector aSN1EncodableVector57 = aSN1EncodableVector52;
                    aSN1EncodableVector55.add(new DEROctetString(gMSSRootCalcArr[r95].getTreehash()[r133].getStatByte()[0]));
                    aSN1EncodableVector55.add(new DEROctetString(gMSSRootCalcArr[r95].getTreehash()[r133].getStatByte()[1]));
                    aSN1EncodableVector55.add(new DEROctetString(gMSSRootCalcArr[r95].getTreehash()[r133].getStatByte()[2]));
                    int r35 = 0;
                    while (r35 < r14) {
                        aSN1EncodableVector55.add(new DEROctetString(gMSSRootCalcArr[r95].getTreehash()[r133].getStatByte()[r35 + 3]));
                        r35++;
                        aSN1EncodableVector53 = aSN1EncodableVector53;
                    }
                    ASN1EncodableVector aSN1EncodableVector58 = aSN1EncodableVector53;
                    aSN1EncodableVector54.add(new DERSequence(aSN1EncodableVector55));
                    aSN1EncodableVector55 = new ASN1EncodableVector();
                    ASN1EncodableVector aSN1EncodableVector59 = aSN1EncodableVector43;
                    aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[r95].getTreehash()[r133].getStatInt()[0]));
                    aSN1EncodableVector56.add(new ASN1Integer(r14));
                    aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[r95].getTreehash()[r133].getStatInt()[2]));
                    aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[r95].getTreehash()[r133].getStatInt()[3]));
                    aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[r95].getTreehash()[r133].getStatInt()[4]));
                    aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[r95].getTreehash()[r133].getStatInt()[5]));
                    int r36 = 0;
                    while (r36 < r14) {
                        aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[r95].getTreehash()[r133].getStatInt()[r36 + 6]));
                        r36++;
                        r14 = r14;
                        aSN1EncodableVector59 = aSN1EncodableVector59;
                    }
                    aSN1EncodableVector54.add(new DERSequence(aSN1EncodableVector56));
                    aSN1EncodableVector56 = new ASN1EncodableVector();
                    aSN1EncodableVector50.add(new DERSequence(aSN1EncodableVector54));
                    aSN1EncodableVector54 = new ASN1EncodableVector();
                    r133++;
                    aSN1EncodableVector52 = aSN1EncodableVector57;
                    aSN1EncodableVector53 = aSN1EncodableVector58;
                    aSN1EncodableVector43 = aSN1EncodableVector59;
                }
            }
            ASN1EncodableVector aSN1EncodableVector60 = aSN1EncodableVector52;
            ASN1EncodableVector aSN1EncodableVector61 = aSN1EncodableVector53;
            ASN1EncodableVector aSN1EncodableVector62 = aSN1EncodableVector43;
            aSN1EncodableVector47.add(new DERSequence(aSN1EncodableVector50));
            aSN1EncodableVector50 = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector63 = new ASN1EncodableVector();
            if (gMSSRootCalcArr[r95].getRetain() != null) {
                for (int r46 = 0; r46 < gMSSRootCalcArr[r95].getRetain().length; r46++) {
                    for (int r56 = 0; r56 < gMSSRootCalcArr[r95].getRetain()[r46].size(); r56++) {
                        aSN1EncodableVector63.add(new DEROctetString((byte[]) gMSSRootCalcArr[r95].getRetain()[r46].elementAt(r56)));
                    }
                    aSN1EncodableVector51.add(new DERSequence(aSN1EncodableVector63));
                    aSN1EncodableVector63 = new ASN1EncodableVector();
                }
            }
            aSN1EncodableVector47.add(new DERSequence(aSN1EncodableVector51));
            aSN1EncodableVector51 = new ASN1EncodableVector();
            aSN1EncodableVector46.add(new DERSequence(aSN1EncodableVector47));
            aSN1EncodableVector47 = new ASN1EncodableVector();
            r95++;
            aSN1EncodableVector48 = aSN1EncodableVector60;
            aSN1EncodableVector49 = aSN1EncodableVector61;
            aSN1EncodableVector43 = aSN1EncodableVector62;
        }
        ASN1EncodableVector aSN1EncodableVector64 = aSN1EncodableVector43;
        aSN1EncodableVector64.add(new DERSequence(aSN1EncodableVector46));
        ASN1EncodableVector aSN1EncodableVector65 = new ASN1EncodableVector();
        for (byte[] bArr11 : bArr7) {
            aSN1EncodableVector65.add(new DEROctetString(bArr11));
        }
        aSN1EncodableVector64.add(new DERSequence(aSN1EncodableVector65));
        ASN1EncodableVector aSN1EncodableVector66 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector67 = new ASN1EncodableVector();
        new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector68 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector69 = new ASN1EncodableVector();
        for (int r7 = 0; r7 < gMSSRootSigArr.length; r7++) {
            aSN1EncodableVector67.add(new DERSequence(algorithmIdentifierArr[0]));
            new ASN1EncodableVector();
            aSN1EncodableVector68.add(new DEROctetString(gMSSRootSigArr[r7].getStatByte()[0]));
            aSN1EncodableVector68.add(new DEROctetString(gMSSRootSigArr[r7].getStatByte()[1]));
            aSN1EncodableVector68.add(new DEROctetString(gMSSRootSigArr[r7].getStatByte()[2]));
            aSN1EncodableVector68.add(new DEROctetString(gMSSRootSigArr[r7].getStatByte()[3]));
            aSN1EncodableVector68.add(new DEROctetString(gMSSRootSigArr[r7].getStatByte()[4]));
            aSN1EncodableVector67.add(new DERSequence(aSN1EncodableVector68));
            aSN1EncodableVector68 = new ASN1EncodableVector();
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[r7].getStatInt()[0]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[r7].getStatInt()[1]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[r7].getStatInt()[2]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[r7].getStatInt()[3]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[r7].getStatInt()[4]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[r7].getStatInt()[5]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[r7].getStatInt()[6]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[r7].getStatInt()[7]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[r7].getStatInt()[8]));
            aSN1EncodableVector67.add(new DERSequence(aSN1EncodableVector69));
            aSN1EncodableVector69 = new ASN1EncodableVector();
            aSN1EncodableVector66.add(new DERSequence(aSN1EncodableVector67));
            aSN1EncodableVector67 = new ASN1EncodableVector();
        }
        aSN1EncodableVector64.add(new DERSequence(aSN1EncodableVector66));
        ASN1EncodableVector aSN1EncodableVector70 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector71 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector72 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector73 = new ASN1EncodableVector();
        for (int r66 = 0; r66 < gMSSParameters.getHeightOfTrees().length; r66++) {
            aSN1EncodableVector71.add(new ASN1Integer(gMSSParameters.getHeightOfTrees()[r66]));
            aSN1EncodableVector72.add(new ASN1Integer(gMSSParameters.getWinternitzParameter()[r66]));
            aSN1EncodableVector73.add(new ASN1Integer(gMSSParameters.getK()[r66]));
        }
        aSN1EncodableVector70.add(new ASN1Integer(gMSSParameters.getNumOfLayers()));
        aSN1EncodableVector70.add(new DERSequence(aSN1EncodableVector71));
        aSN1EncodableVector70.add(new DERSequence(aSN1EncodableVector72));
        aSN1EncodableVector70.add(new DERSequence(aSN1EncodableVector73));
        aSN1EncodableVector64.add(new DERSequence(aSN1EncodableVector70));
        ASN1EncodableVector aSN1EncodableVector74 = new ASN1EncodableVector();
        for (AlgorithmIdentifier algorithmIdentifier : algorithmIdentifierArr) {
            aSN1EncodableVector74.add(algorithmIdentifier);
        }
        aSN1EncodableVector64.add(new DERSequence(aSN1EncodableVector74));
        return new DERSequence(aSN1EncodableVector64);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.primitive;
    }
}
