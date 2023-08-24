package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.engines.ChaChaEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.pqc.crypto.sphincs.Tree;
import org.bouncycastle.util.Pack;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class Seed {
    Seed() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void get_seed(HashFunctions hashFunctions, byte[] bArr, int r10, byte[] bArr2, Tree.leafaddr leafaddrVar) {
        byte[] bArr3 = new byte[40];
        for (int r2 = 0; r2 < 32; r2++) {
            bArr3[r2] = bArr2[r2];
        }
        Pack.longToLittleEndian((leafaddrVar.subleaf << 59) | leafaddrVar.level | (leafaddrVar.subtree << 4), bArr3, 32);
        hashFunctions.varlen_hash(bArr, r10, bArr3, 40);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void prg(byte[] bArr, int r8, long j, byte[] bArr2, int r12) {
        ChaChaEngine chaChaEngine = new ChaChaEngine(12);
        chaChaEngine.init(true, new ParametersWithIV(new KeyParameter(bArr2, r12, 32), new byte[8]));
        chaChaEngine.processBytes(bArr, r8, (int) j, bArr, r8);
    }
}
