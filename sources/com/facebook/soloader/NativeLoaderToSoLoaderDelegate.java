package com.facebook.soloader;

import com.facebook.soloader.nativeloader.NativeLoaderDelegate;
import java.io.IOException;

/* loaded from: classes.dex */
public class NativeLoaderToSoLoaderDelegate implements NativeLoaderDelegate {
    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public boolean loadLibrary(String str, int r3) {
        return SoLoader.loadLibrary(str, ((r3 & 1) != 0 ? 16 : 0) | 0);
    }

    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public String getLibraryPath(String str) throws IOException {
        return SoLoader.getLibraryPath(str);
    }

    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public int getSoSourcesVersion() {
        return SoLoader.getSoSourcesVersion();
    }
}
