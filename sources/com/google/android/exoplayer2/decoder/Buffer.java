package com.google.android.exoplayer2.decoder;

import com.google.android.exoplayer2.C1856C;

/* loaded from: classes2.dex */
public abstract class Buffer {
    private int flags;

    public void clear() {
        this.flags = 0;
    }

    public final boolean isDecodeOnly() {
        return getFlag(Integer.MIN_VALUE);
    }

    public final boolean isFirstSample() {
        return getFlag(C1856C.BUFFER_FLAG_FIRST_SAMPLE);
    }

    public final boolean isEndOfStream() {
        return getFlag(4);
    }

    public final boolean isKeyFrame() {
        return getFlag(1);
    }

    public final boolean hasSupplementalData() {
        return getFlag(268435456);
    }

    public final void setFlags(int r1) {
        this.flags = r1;
    }

    public final void addFlag(int r2) {
        this.flags = r2 | this.flags;
    }

    public final void clearFlag(int r2) {
        this.flags = (~r2) & this.flags;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean getFlag(int r2) {
        return (this.flags & r2) == r2;
    }
}
