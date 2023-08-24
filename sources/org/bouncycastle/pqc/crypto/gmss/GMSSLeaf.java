package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class GMSSLeaf {
    private byte[] concHashs;
    private GMSSRandom gmssRandom;

    /* renamed from: i */
    private int f2405i;

    /* renamed from: j */
    private int f2406j;
    private int keysize;
    private byte[] leaf;
    private int mdsize;
    private Digest messDigestOTS;
    byte[] privateKeyOTS;
    private byte[] seed;
    private int steps;
    private int two_power_w;

    /* renamed from: w */
    private int f2407w;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GMSSLeaf(Digest digest, int r8, int r9) {
        int digestSize;
        int ceil;
        int ceil2;
        int r82;
        this.f2407w = r8;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = r8;
        this.keysize = ((int) Math.ceil((digestSize << 3) / d)) + ((int) Math.ceil(getLog((ceil << r8) + 1) / d));
        this.two_power_w = 1 << r8;
        this.steps = (int) Math.ceil(((((r82 - 1) * ceil2) + 1) + ceil2) / r9);
        int r7 = this.mdsize;
        this.seed = new byte[r7];
        this.leaf = new byte[r7];
        this.privateKeyOTS = new byte[r7];
        this.concHashs = new byte[r7 * this.keysize];
    }

    public GMSSLeaf(Digest digest, int r8, int r9, byte[] bArr) {
        int digestSize;
        int ceil;
        int ceil2;
        int r82;
        this.f2407w = r8;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = r8;
        this.keysize = ((int) Math.ceil((digestSize << 3) / d)) + ((int) Math.ceil(getLog((ceil << r8) + 1) / d));
        this.two_power_w = 1 << r8;
        this.steps = (int) Math.ceil(((((r82 - 1) * ceil2) + 1) + ceil2) / r9);
        int r7 = this.mdsize;
        this.seed = new byte[r7];
        this.leaf = new byte[r7];
        this.privateKeyOTS = new byte[r7];
        this.concHashs = new byte[r7 * this.keysize];
        initLeafCalc(bArr);
    }

    public GMSSLeaf(Digest digest, byte[][] bArr, int[] r11) {
        this.f2405i = r11[0];
        this.f2406j = r11[1];
        this.steps = r11[2];
        this.f2407w = r11[3];
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        int digestSize = this.messDigestOTS.getDigestSize();
        this.mdsize = digestSize;
        int ceil = (int) Math.ceil((digestSize << 3) / this.f2407w);
        this.keysize = ceil + ((int) Math.ceil(getLog((ceil << this.f2407w) + 1) / this.f2407w));
        this.two_power_w = 1 << this.f2407w;
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.concHashs = bArr[2];
        this.leaf = bArr[3];
    }

    private GMSSLeaf(GMSSLeaf gMSSLeaf) {
        this.messDigestOTS = gMSSLeaf.messDigestOTS;
        this.mdsize = gMSSLeaf.mdsize;
        this.keysize = gMSSLeaf.keysize;
        this.gmssRandom = gMSSLeaf.gmssRandom;
        this.leaf = Arrays.clone(gMSSLeaf.leaf);
        this.concHashs = Arrays.clone(gMSSLeaf.concHashs);
        this.f2405i = gMSSLeaf.f2405i;
        this.f2406j = gMSSLeaf.f2406j;
        this.two_power_w = gMSSLeaf.two_power_w;
        this.f2407w = gMSSLeaf.f2407w;
        this.steps = gMSSLeaf.steps;
        this.seed = Arrays.clone(gMSSLeaf.seed);
        this.privateKeyOTS = Arrays.clone(gMSSLeaf.privateKeyOTS);
    }

    private int getLog(int r3) {
        int r0 = 1;
        int r1 = 2;
        while (r1 < r3) {
            r1 <<= 1;
            r0++;
        }
        return r0;
    }

    private void updateLeafCalc() {
        byte[] bArr = new byte[this.messDigestOTS.getDigestSize()];
        for (int r2 = 0; r2 < this.steps + 10000; r2++) {
            int r3 = this.f2405i;
            if (r3 == this.keysize && this.f2406j == this.two_power_w - 1) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.concHashs;
                digest.update(bArr2, 0, bArr2.length);
                byte[] bArr3 = new byte[this.messDigestOTS.getDigestSize()];
                this.leaf = bArr3;
                this.messDigestOTS.doFinal(bArr3, 0);
                return;
            }
            if (r3 == 0 || this.f2406j == this.two_power_w - 1) {
                this.f2405i = r3 + 1;
                this.f2406j = 0;
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr4 = this.privateKeyOTS;
                digest2.update(bArr4, 0, bArr4.length);
                this.privateKeyOTS = bArr;
                this.messDigestOTS.doFinal(bArr, 0);
                int r32 = this.f2406j + 1;
                this.f2406j = r32;
                if (r32 == this.two_power_w - 1) {
                    byte[] bArr5 = this.privateKeyOTS;
                    byte[] bArr6 = this.concHashs;
                    int r5 = this.mdsize;
                    System.arraycopy(bArr5, 0, bArr6, (this.f2405i - 1) * r5, r5);
                }
            }
        }
        throw new IllegalStateException("unable to updateLeaf in steps: " + this.steps + " " + this.f2405i + " " + this.f2406j);
    }

    public byte[] getLeaf() {
        return Arrays.clone(this.leaf);
    }

    public byte[][] getStatByte() {
        return new byte[][]{this.privateKeyOTS, this.seed, this.concHashs, this.leaf};
    }

    public int[] getStatInt() {
        return new int[]{this.f2405i, this.f2406j, this.steps, this.f2407w};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initLeafCalc(byte[] bArr) {
        this.f2405i = 0;
        this.f2406j = 0;
        byte[] bArr2 = new byte[this.mdsize];
        System.arraycopy(bArr, 0, bArr2, 0, this.seed.length);
        this.seed = this.gmssRandom.nextSeed(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GMSSLeaf nextLeaf() {
        GMSSLeaf gMSSLeaf = new GMSSLeaf(this);
        gMSSLeaf.updateLeafCalc();
        return gMSSLeaf;
    }

    public String toString() {
        StringBuilder sb;
        String str = "";
        for (int r2 = 0; r2 < 4; r2++) {
            str = str + getStatInt()[r2] + " ";
        }
        String str2 = str + " " + this.mdsize + " " + this.keysize + " " + this.two_power_w + " ";
        byte[][] statByte = getStatByte();
        for (int r0 = 0; r0 < 4; r0++) {
            if (statByte[r0] != null) {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append(new String(Hex.encode(statByte[r0])));
                sb.append(" ");
            } else {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append("null ");
            }
            str2 = sb.toString();
        }
        return str2;
    }
}
