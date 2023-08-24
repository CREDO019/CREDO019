package org.bouncycastle.pqc.crypto.sphincsplus;

/* loaded from: classes3.dex */
class IndexedDigest {
    final byte[] digest;
    final int idx_leaf;
    final long idx_tree;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IndexedDigest(long j, int r3, byte[] bArr) {
        this.idx_tree = j;
        this.idx_leaf = r3;
        this.digest = bArr;
    }
}
