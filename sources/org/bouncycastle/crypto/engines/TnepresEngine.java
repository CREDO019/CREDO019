package org.bouncycastle.crypto.engines;

import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public final class TnepresEngine extends SerpentEngineBase {
    @Override // org.bouncycastle.crypto.engines.SerpentEngineBase
    protected void decryptBlock(byte[] bArr, int r5, byte[] bArr2, int r7) {
        this.f1966X3 = this.wKey[131] ^ Pack.bigEndianToInt(bArr, r5);
        this.f1965X2 = this.wKey[130] ^ Pack.bigEndianToInt(bArr, r5 + 4);
        this.f1964X1 = this.wKey[129] ^ Pack.bigEndianToInt(bArr, r5 + 8);
        this.f1963X0 = Pack.bigEndianToInt(bArr, r5 + 12) ^ this.wKey[128];
        ib7(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[124];
        this.f1964X1 ^= this.wKey[125];
        this.f1965X2 ^= this.wKey[126];
        this.f1966X3 ^= this.wKey[127];
        inverseLT();
        ib6(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[120];
        this.f1964X1 ^= this.wKey[121];
        this.f1965X2 ^= this.wKey[122];
        this.f1966X3 ^= this.wKey[123];
        inverseLT();
        ib5(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[116];
        this.f1964X1 ^= this.wKey[117];
        this.f1965X2 ^= this.wKey[118];
        this.f1966X3 ^= this.wKey[119];
        inverseLT();
        ib4(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[112];
        this.f1964X1 ^= this.wKey[113];
        this.f1965X2 ^= this.wKey[114];
        this.f1966X3 ^= this.wKey[115];
        inverseLT();
        ib3(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[108];
        this.f1964X1 ^= this.wKey[109];
        this.f1965X2 ^= this.wKey[110];
        this.f1966X3 ^= this.wKey[111];
        inverseLT();
        ib2(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[104];
        this.f1964X1 ^= this.wKey[105];
        this.f1965X2 ^= this.wKey[106];
        this.f1966X3 ^= this.wKey[107];
        inverseLT();
        ib1(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[100];
        this.f1964X1 ^= this.wKey[101];
        this.f1965X2 ^= this.wKey[102];
        this.f1966X3 ^= this.wKey[103];
        inverseLT();
        ib0(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[96];
        this.f1964X1 ^= this.wKey[97];
        this.f1965X2 ^= this.wKey[98];
        this.f1966X3 ^= this.wKey[99];
        inverseLT();
        ib7(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[92];
        this.f1964X1 ^= this.wKey[93];
        this.f1965X2 ^= this.wKey[94];
        this.f1966X3 ^= this.wKey[95];
        inverseLT();
        ib6(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[88];
        this.f1964X1 ^= this.wKey[89];
        this.f1965X2 ^= this.wKey[90];
        this.f1966X3 ^= this.wKey[91];
        inverseLT();
        ib5(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[84];
        this.f1964X1 ^= this.wKey[85];
        this.f1965X2 ^= this.wKey[86];
        this.f1966X3 ^= this.wKey[87];
        inverseLT();
        ib4(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[80];
        this.f1964X1 ^= this.wKey[81];
        this.f1965X2 ^= this.wKey[82];
        this.f1966X3 ^= this.wKey[83];
        inverseLT();
        ib3(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[76];
        this.f1964X1 ^= this.wKey[77];
        this.f1965X2 ^= this.wKey[78];
        this.f1966X3 ^= this.wKey[79];
        inverseLT();
        ib2(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[72];
        this.f1964X1 ^= this.wKey[73];
        this.f1965X2 ^= this.wKey[74];
        this.f1966X3 ^= this.wKey[75];
        inverseLT();
        ib1(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[68];
        this.f1964X1 ^= this.wKey[69];
        this.f1965X2 ^= this.wKey[70];
        this.f1966X3 ^= this.wKey[71];
        inverseLT();
        ib0(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[64];
        this.f1964X1 ^= this.wKey[65];
        this.f1965X2 ^= this.wKey[66];
        this.f1966X3 ^= this.wKey[67];
        inverseLT();
        ib7(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[60];
        this.f1964X1 ^= this.wKey[61];
        this.f1965X2 ^= this.wKey[62];
        this.f1966X3 ^= this.wKey[63];
        inverseLT();
        ib6(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[56];
        this.f1964X1 ^= this.wKey[57];
        this.f1965X2 ^= this.wKey[58];
        this.f1966X3 ^= this.wKey[59];
        inverseLT();
        ib5(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[52];
        this.f1964X1 ^= this.wKey[53];
        this.f1965X2 ^= this.wKey[54];
        this.f1966X3 ^= this.wKey[55];
        inverseLT();
        ib4(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[48];
        this.f1964X1 ^= this.wKey[49];
        this.f1965X2 ^= this.wKey[50];
        this.f1966X3 ^= this.wKey[51];
        inverseLT();
        ib3(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[44];
        this.f1964X1 ^= this.wKey[45];
        this.f1965X2 ^= this.wKey[46];
        this.f1966X3 ^= this.wKey[47];
        inverseLT();
        ib2(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[40];
        this.f1964X1 ^= this.wKey[41];
        this.f1965X2 ^= this.wKey[42];
        this.f1966X3 ^= this.wKey[43];
        inverseLT();
        ib1(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[36];
        this.f1964X1 ^= this.wKey[37];
        this.f1965X2 ^= this.wKey[38];
        this.f1966X3 ^= this.wKey[39];
        inverseLT();
        ib0(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[32];
        this.f1964X1 ^= this.wKey[33];
        this.f1965X2 ^= this.wKey[34];
        this.f1966X3 ^= this.wKey[35];
        inverseLT();
        ib7(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[28];
        this.f1964X1 ^= this.wKey[29];
        this.f1965X2 ^= this.wKey[30];
        this.f1966X3 ^= this.wKey[31];
        inverseLT();
        ib6(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[24];
        this.f1964X1 ^= this.wKey[25];
        this.f1965X2 ^= this.wKey[26];
        this.f1966X3 ^= this.wKey[27];
        inverseLT();
        ib5(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[20];
        this.f1964X1 ^= this.wKey[21];
        this.f1965X2 ^= this.wKey[22];
        this.f1966X3 ^= this.wKey[23];
        inverseLT();
        ib4(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[16];
        this.f1964X1 ^= this.wKey[17];
        this.f1965X2 ^= this.wKey[18];
        this.f1966X3 ^= this.wKey[19];
        inverseLT();
        ib3(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[12];
        this.f1964X1 ^= this.wKey[13];
        this.f1965X2 ^= this.wKey[14];
        this.f1966X3 ^= this.wKey[15];
        inverseLT();
        ib2(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[8];
        this.f1964X1 ^= this.wKey[9];
        this.f1965X2 ^= this.wKey[10];
        this.f1966X3 ^= this.wKey[11];
        inverseLT();
        ib1(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        this.f1963X0 ^= this.wKey[4];
        this.f1964X1 ^= this.wKey[5];
        this.f1965X2 ^= this.wKey[6];
        this.f1966X3 ^= this.wKey[7];
        inverseLT();
        ib0(this.f1963X0, this.f1964X1, this.f1965X2, this.f1966X3);
        Pack.intToBigEndian(this.f1966X3 ^ this.wKey[3], bArr2, r7);
        Pack.intToBigEndian(this.f1965X2 ^ this.wKey[2], bArr2, r7 + 4);
        Pack.intToBigEndian(this.f1964X1 ^ this.wKey[1], bArr2, r7 + 8);
        Pack.intToBigEndian(this.f1963X0 ^ this.wKey[0], bArr2, r7 + 12);
    }

    @Override // org.bouncycastle.crypto.engines.SerpentEngineBase
    protected void encryptBlock(byte[] bArr, int r6, byte[] bArr2, int r8) {
        this.f1966X3 = Pack.bigEndianToInt(bArr, r6);
        this.f1965X2 = Pack.bigEndianToInt(bArr, r6 + 4);
        this.f1964X1 = Pack.bigEndianToInt(bArr, r6 + 8);
        this.f1963X0 = Pack.bigEndianToInt(bArr, r6 + 12);
        sb0(this.wKey[0] ^ this.f1963X0, this.wKey[1] ^ this.f1964X1, this.wKey[2] ^ this.f1965X2, this.wKey[3] ^ this.f1966X3);
        m42LT();
        sb1(this.wKey[4] ^ this.f1963X0, this.wKey[5] ^ this.f1964X1, this.wKey[6] ^ this.f1965X2, this.wKey[7] ^ this.f1966X3);
        m42LT();
        sb2(this.wKey[8] ^ this.f1963X0, this.wKey[9] ^ this.f1964X1, this.wKey[10] ^ this.f1965X2, this.wKey[11] ^ this.f1966X3);
        m42LT();
        sb3(this.wKey[12] ^ this.f1963X0, this.wKey[13] ^ this.f1964X1, this.wKey[14] ^ this.f1965X2, this.wKey[15] ^ this.f1966X3);
        m42LT();
        sb4(this.wKey[16] ^ this.f1963X0, this.wKey[17] ^ this.f1964X1, this.wKey[18] ^ this.f1965X2, this.wKey[19] ^ this.f1966X3);
        m42LT();
        sb5(this.wKey[20] ^ this.f1963X0, this.wKey[21] ^ this.f1964X1, this.wKey[22] ^ this.f1965X2, this.wKey[23] ^ this.f1966X3);
        m42LT();
        sb6(this.wKey[24] ^ this.f1963X0, this.wKey[25] ^ this.f1964X1, this.wKey[26] ^ this.f1965X2, this.wKey[27] ^ this.f1966X3);
        m42LT();
        sb7(this.wKey[28] ^ this.f1963X0, this.wKey[29] ^ this.f1964X1, this.wKey[30] ^ this.f1965X2, this.wKey[31] ^ this.f1966X3);
        m42LT();
        sb0(this.wKey[32] ^ this.f1963X0, this.wKey[33] ^ this.f1964X1, this.wKey[34] ^ this.f1965X2, this.wKey[35] ^ this.f1966X3);
        m42LT();
        sb1(this.wKey[36] ^ this.f1963X0, this.wKey[37] ^ this.f1964X1, this.wKey[38] ^ this.f1965X2, this.wKey[39] ^ this.f1966X3);
        m42LT();
        sb2(this.wKey[40] ^ this.f1963X0, this.wKey[41] ^ this.f1964X1, this.wKey[42] ^ this.f1965X2, this.wKey[43] ^ this.f1966X3);
        m42LT();
        sb3(this.wKey[44] ^ this.f1963X0, this.wKey[45] ^ this.f1964X1, this.wKey[46] ^ this.f1965X2, this.wKey[47] ^ this.f1966X3);
        m42LT();
        sb4(this.wKey[48] ^ this.f1963X0, this.wKey[49] ^ this.f1964X1, this.wKey[50] ^ this.f1965X2, this.wKey[51] ^ this.f1966X3);
        m42LT();
        sb5(this.wKey[52] ^ this.f1963X0, this.wKey[53] ^ this.f1964X1, this.wKey[54] ^ this.f1965X2, this.wKey[55] ^ this.f1966X3);
        m42LT();
        sb6(this.wKey[56] ^ this.f1963X0, this.wKey[57] ^ this.f1964X1, this.wKey[58] ^ this.f1965X2, this.wKey[59] ^ this.f1966X3);
        m42LT();
        sb7(this.wKey[60] ^ this.f1963X0, this.wKey[61] ^ this.f1964X1, this.wKey[62] ^ this.f1965X2, this.wKey[63] ^ this.f1966X3);
        m42LT();
        sb0(this.wKey[64] ^ this.f1963X0, this.wKey[65] ^ this.f1964X1, this.wKey[66] ^ this.f1965X2, this.wKey[67] ^ this.f1966X3);
        m42LT();
        sb1(this.wKey[68] ^ this.f1963X0, this.wKey[69] ^ this.f1964X1, this.wKey[70] ^ this.f1965X2, this.wKey[71] ^ this.f1966X3);
        m42LT();
        sb2(this.wKey[72] ^ this.f1963X0, this.wKey[73] ^ this.f1964X1, this.wKey[74] ^ this.f1965X2, this.wKey[75] ^ this.f1966X3);
        m42LT();
        sb3(this.wKey[76] ^ this.f1963X0, this.wKey[77] ^ this.f1964X1, this.wKey[78] ^ this.f1965X2, this.wKey[79] ^ this.f1966X3);
        m42LT();
        sb4(this.wKey[80] ^ this.f1963X0, this.wKey[81] ^ this.f1964X1, this.wKey[82] ^ this.f1965X2, this.wKey[83] ^ this.f1966X3);
        m42LT();
        sb5(this.wKey[84] ^ this.f1963X0, this.wKey[85] ^ this.f1964X1, this.wKey[86] ^ this.f1965X2, this.wKey[87] ^ this.f1966X3);
        m42LT();
        sb6(this.wKey[88] ^ this.f1963X0, this.wKey[89] ^ this.f1964X1, this.wKey[90] ^ this.f1965X2, this.wKey[91] ^ this.f1966X3);
        m42LT();
        sb7(this.wKey[92] ^ this.f1963X0, this.wKey[93] ^ this.f1964X1, this.wKey[94] ^ this.f1965X2, this.wKey[95] ^ this.f1966X3);
        m42LT();
        sb0(this.wKey[96] ^ this.f1963X0, this.wKey[97] ^ this.f1964X1, this.wKey[98] ^ this.f1965X2, this.wKey[99] ^ this.f1966X3);
        m42LT();
        sb1(this.wKey[100] ^ this.f1963X0, this.wKey[101] ^ this.f1964X1, this.wKey[102] ^ this.f1965X2, this.wKey[103] ^ this.f1966X3);
        m42LT();
        sb2(this.wKey[104] ^ this.f1963X0, this.wKey[105] ^ this.f1964X1, this.wKey[106] ^ this.f1965X2, this.wKey[107] ^ this.f1966X3);
        m42LT();
        sb3(this.wKey[108] ^ this.f1963X0, this.wKey[109] ^ this.f1964X1, this.wKey[110] ^ this.f1965X2, this.wKey[111] ^ this.f1966X3);
        m42LT();
        sb4(this.wKey[112] ^ this.f1963X0, this.wKey[113] ^ this.f1964X1, this.wKey[114] ^ this.f1965X2, this.wKey[115] ^ this.f1966X3);
        m42LT();
        sb5(this.wKey[116] ^ this.f1963X0, this.wKey[117] ^ this.f1964X1, this.wKey[118] ^ this.f1965X2, this.wKey[119] ^ this.f1966X3);
        m42LT();
        sb6(this.wKey[120] ^ this.f1963X0, this.wKey[121] ^ this.f1964X1, this.wKey[122] ^ this.f1965X2, this.wKey[123] ^ this.f1966X3);
        m42LT();
        sb7(this.wKey[124] ^ this.f1963X0, this.wKey[125] ^ this.f1964X1, this.wKey[126] ^ this.f1965X2, this.wKey[127] ^ this.f1966X3);
        Pack.intToBigEndian(this.wKey[131] ^ this.f1966X3, bArr2, r8);
        Pack.intToBigEndian(this.wKey[130] ^ this.f1965X2, bArr2, r8 + 4);
        Pack.intToBigEndian(this.wKey[129] ^ this.f1964X1, bArr2, r8 + 8);
        Pack.intToBigEndian(this.wKey[128] ^ this.f1963X0, bArr2, r8 + 12);
    }

    @Override // org.bouncycastle.crypto.engines.SerpentEngineBase, org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Tnepres";
    }

    @Override // org.bouncycastle.crypto.engines.SerpentEngineBase
    protected int[] makeWorkingKey(byte[] bArr) throws IllegalArgumentException {
        int[] r1 = new int[16];
        int length = bArr.length - 4;
        int r5 = 0;
        while (length > 0) {
            r1[r5] = Pack.bigEndianToInt(bArr, length);
            length -= 4;
            r5++;
        }
        if (length == 0) {
            int r2 = r5 + 1;
            r1[r5] = Pack.bigEndianToInt(bArr, 0);
            if (r2 < 8) {
                r1[r2] = 1;
            }
            int[] r6 = new int[132];
            for (int r7 = 8; r7 < 16; r7++) {
                int r10 = r7 - 8;
                r1[r7] = rotateLeft(((-1640531527) ^ (((r1[r10] ^ r1[r7 - 5]) ^ r1[r7 - 3]) ^ r1[r7 - 1])) ^ r10, 11);
            }
            System.arraycopy(r1, 8, r6, 0, 8);
            for (int r12 = 8; r12 < 132; r12++) {
                r6[r12] = rotateLeft(((((r6[r12 - 8] ^ r6[r12 - 5]) ^ r6[r12 - 3]) ^ r6[r12 - 1]) ^ (-1640531527)) ^ r12, 11);
            }
            sb3(r6[0], r6[1], r6[2], r6[3]);
            r6[0] = this.f1963X0;
            r6[1] = this.f1964X1;
            r6[2] = this.f1965X2;
            r6[3] = this.f1966X3;
            sb2(r6[4], r6[5], r6[6], r6[7]);
            r6[4] = this.f1963X0;
            r6[5] = this.f1964X1;
            r6[6] = this.f1965X2;
            r6[7] = this.f1966X3;
            sb1(r6[8], r6[9], r6[10], r6[11]);
            r6[8] = this.f1963X0;
            r6[9] = this.f1964X1;
            r6[10] = this.f1965X2;
            r6[11] = this.f1966X3;
            sb0(r6[12], r6[13], r6[14], r6[15]);
            r6[12] = this.f1963X0;
            r6[13] = this.f1964X1;
            r6[14] = this.f1965X2;
            r6[15] = this.f1966X3;
            sb7(r6[16], r6[17], r6[18], r6[19]);
            r6[16] = this.f1963X0;
            r6[17] = this.f1964X1;
            r6[18] = this.f1965X2;
            r6[19] = this.f1966X3;
            sb6(r6[20], r6[21], r6[22], r6[23]);
            r6[20] = this.f1963X0;
            r6[21] = this.f1964X1;
            r6[22] = this.f1965X2;
            r6[23] = this.f1966X3;
            sb5(r6[24], r6[25], r6[26], r6[27]);
            r6[24] = this.f1963X0;
            r6[25] = this.f1964X1;
            r6[26] = this.f1965X2;
            r6[27] = this.f1966X3;
            sb4(r6[28], r6[29], r6[30], r6[31]);
            r6[28] = this.f1963X0;
            r6[29] = this.f1964X1;
            r6[30] = this.f1965X2;
            r6[31] = this.f1966X3;
            sb3(r6[32], r6[33], r6[34], r6[35]);
            r6[32] = this.f1963X0;
            r6[33] = this.f1964X1;
            r6[34] = this.f1965X2;
            r6[35] = this.f1966X3;
            sb2(r6[36], r6[37], r6[38], r6[39]);
            r6[36] = this.f1963X0;
            r6[37] = this.f1964X1;
            r6[38] = this.f1965X2;
            r6[39] = this.f1966X3;
            sb1(r6[40], r6[41], r6[42], r6[43]);
            r6[40] = this.f1963X0;
            r6[41] = this.f1964X1;
            r6[42] = this.f1965X2;
            r6[43] = this.f1966X3;
            sb0(r6[44], r6[45], r6[46], r6[47]);
            r6[44] = this.f1963X0;
            r6[45] = this.f1964X1;
            r6[46] = this.f1965X2;
            r6[47] = this.f1966X3;
            sb7(r6[48], r6[49], r6[50], r6[51]);
            r6[48] = this.f1963X0;
            r6[49] = this.f1964X1;
            r6[50] = this.f1965X2;
            r6[51] = this.f1966X3;
            sb6(r6[52], r6[53], r6[54], r6[55]);
            r6[52] = this.f1963X0;
            r6[53] = this.f1964X1;
            r6[54] = this.f1965X2;
            r6[55] = this.f1966X3;
            sb5(r6[56], r6[57], r6[58], r6[59]);
            r6[56] = this.f1963X0;
            r6[57] = this.f1964X1;
            r6[58] = this.f1965X2;
            r6[59] = this.f1966X3;
            sb4(r6[60], r6[61], r6[62], r6[63]);
            r6[60] = this.f1963X0;
            r6[61] = this.f1964X1;
            r6[62] = this.f1965X2;
            r6[63] = this.f1966X3;
            sb3(r6[64], r6[65], r6[66], r6[67]);
            r6[64] = this.f1963X0;
            r6[65] = this.f1964X1;
            r6[66] = this.f1965X2;
            r6[67] = this.f1966X3;
            sb2(r6[68], r6[69], r6[70], r6[71]);
            r6[68] = this.f1963X0;
            r6[69] = this.f1964X1;
            r6[70] = this.f1965X2;
            r6[71] = this.f1966X3;
            sb1(r6[72], r6[73], r6[74], r6[75]);
            r6[72] = this.f1963X0;
            r6[73] = this.f1964X1;
            r6[74] = this.f1965X2;
            r6[75] = this.f1966X3;
            sb0(r6[76], r6[77], r6[78], r6[79]);
            r6[76] = this.f1963X0;
            r6[77] = this.f1964X1;
            r6[78] = this.f1965X2;
            r6[79] = this.f1966X3;
            sb7(r6[80], r6[81], r6[82], r6[83]);
            r6[80] = this.f1963X0;
            r6[81] = this.f1964X1;
            r6[82] = this.f1965X2;
            r6[83] = this.f1966X3;
            sb6(r6[84], r6[85], r6[86], r6[87]);
            r6[84] = this.f1963X0;
            r6[85] = this.f1964X1;
            r6[86] = this.f1965X2;
            r6[87] = this.f1966X3;
            sb5(r6[88], r6[89], r6[90], r6[91]);
            r6[88] = this.f1963X0;
            r6[89] = this.f1964X1;
            r6[90] = this.f1965X2;
            r6[91] = this.f1966X3;
            sb4(r6[92], r6[93], r6[94], r6[95]);
            r6[92] = this.f1963X0;
            r6[93] = this.f1964X1;
            r6[94] = this.f1965X2;
            r6[95] = this.f1966X3;
            sb3(r6[96], r6[97], r6[98], r6[99]);
            r6[96] = this.f1963X0;
            r6[97] = this.f1964X1;
            r6[98] = this.f1965X2;
            r6[99] = this.f1966X3;
            sb2(r6[100], r6[101], r6[102], r6[103]);
            r6[100] = this.f1963X0;
            r6[101] = this.f1964X1;
            r6[102] = this.f1965X2;
            r6[103] = this.f1966X3;
            sb1(r6[104], r6[105], r6[106], r6[107]);
            r6[104] = this.f1963X0;
            r6[105] = this.f1964X1;
            r6[106] = this.f1965X2;
            r6[107] = this.f1966X3;
            sb0(r6[108], r6[109], r6[110], r6[111]);
            r6[108] = this.f1963X0;
            r6[109] = this.f1964X1;
            r6[110] = this.f1965X2;
            r6[111] = this.f1966X3;
            sb7(r6[112], r6[113], r6[114], r6[115]);
            r6[112] = this.f1963X0;
            r6[113] = this.f1964X1;
            r6[114] = this.f1965X2;
            r6[115] = this.f1966X3;
            sb6(r6[116], r6[117], r6[118], r6[119]);
            r6[116] = this.f1963X0;
            r6[117] = this.f1964X1;
            r6[118] = this.f1965X2;
            r6[119] = this.f1966X3;
            sb5(r6[120], r6[121], r6[122], r6[123]);
            r6[120] = this.f1963X0;
            r6[121] = this.f1964X1;
            r6[122] = this.f1965X2;
            r6[123] = this.f1966X3;
            sb4(r6[124], r6[125], r6[126], r6[127]);
            r6[124] = this.f1963X0;
            r6[125] = this.f1964X1;
            r6[126] = this.f1965X2;
            r6[127] = this.f1966X3;
            sb3(r6[128], r6[129], r6[130], r6[131]);
            r6[128] = this.f1963X0;
            r6[129] = this.f1964X1;
            r6[130] = this.f1965X2;
            r6[131] = this.f1966X3;
            return r6;
        }
        throw new IllegalArgumentException("key must be a multiple of 4 bytes");
    }
}