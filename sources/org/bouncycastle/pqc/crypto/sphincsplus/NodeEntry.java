package org.bouncycastle.pqc.crypto.sphincsplus;

/* loaded from: classes3.dex */
class NodeEntry {
    final int nodeHeight;
    final byte[] nodeValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NodeEntry(byte[] bArr, int r2) {
        this.nodeValue = bArr;
        this.nodeHeight = r2;
    }
}
