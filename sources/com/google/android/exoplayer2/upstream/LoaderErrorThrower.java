package com.google.android.exoplayer2.upstream;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface LoaderErrorThrower {

    /* loaded from: classes2.dex */
    public static final class Dummy implements LoaderErrorThrower {
        @Override // com.google.android.exoplayer2.upstream.LoaderErrorThrower
        public void maybeThrowError() {
        }

        @Override // com.google.android.exoplayer2.upstream.LoaderErrorThrower
        public void maybeThrowError(int r1) {
        }
    }

    void maybeThrowError() throws IOException;

    void maybeThrowError(int r1) throws IOException;
}
