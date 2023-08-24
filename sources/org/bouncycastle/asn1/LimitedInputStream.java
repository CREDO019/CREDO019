package org.bouncycastle.asn1;

import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public abstract class LimitedInputStream extends InputStream {
    protected final InputStream _in;
    private int _limit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LimitedInputStream(InputStream inputStream, int r2) {
        this._in = inputStream;
        this._limit = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLimit() {
        return this._limit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setParentEofDetect(boolean z) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).setEofOn00(z);
        }
    }
}
