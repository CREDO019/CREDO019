package org.bouncycastle.crypto.modes.gcm;

import java.util.Vector;

/* loaded from: classes5.dex */
public class Tables1kGCMExponentiator implements GCMExponentiator {
    private Vector lookupPowX2;

    private void ensureAvailable(int r4) {
        int size = this.lookupPowX2.size() - 1;
        if (size >= r4) {
            return;
        }
        long[] jArr = (long[]) this.lookupPowX2.elementAt(size);
        while (true) {
            long[] jArr2 = new long[2];
            GCMUtil.square(jArr, jArr2);
            this.lookupPowX2.addElement(jArr2);
            size++;
            if (size >= r4) {
                return;
            }
            jArr = jArr2;
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        long[] oneAsLongs = GCMUtil.oneAsLongs();
        int r1 = 0;
        while (j > 0) {
            if ((1 & j) != 0) {
                ensureAvailable(r1);
                GCMUtil.multiply(oneAsLongs, (long[]) this.lookupPowX2.elementAt(r1));
            }
            r1++;
            j >>>= 1;
        }
        GCMUtil.asBytes(oneAsLongs, bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        long[] asLongs = GCMUtil.asLongs(bArr);
        Vector vector = this.lookupPowX2;
        if (vector == null || 0 == GCMUtil.areEqual(asLongs, (long[]) vector.elementAt(0))) {
            Vector vector2 = new Vector(8);
            this.lookupPowX2 = vector2;
            vector2.addElement(asLongs);
        }
    }
}
