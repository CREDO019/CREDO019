package com.facebook.soloader;

import android.os.StrictMode;
import java.io.File;

/* loaded from: classes.dex */
public class NoopSoSource extends SoSource {
    @Override // com.facebook.soloader.SoSource
    public int loadLibrary(String str, int r2, StrictMode.ThreadPolicy threadPolicy) {
        return 1;
    }

    @Override // com.facebook.soloader.SoSource
    public File unpackLibrary(String str) {
        throw new UnsupportedOperationException("unpacking not supported in test mode");
    }
}
