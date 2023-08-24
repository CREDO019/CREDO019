package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.internal.C2561h;

/* loaded from: classes3.dex */
public abstract class AssetPackState {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AssetPackState m1038a(Bundle bundle, String str, C2406bz c2406bz, InterfaceC2379az interfaceC2379az) {
        return m1037a(str, interfaceC2379az.mo946a(bundle.getInt(C2561h.m699a("status", str)), str), bundle.getInt(C2561h.m699a("error_code", str)), bundle.getLong(C2561h.m699a("bytes_downloaded", str)), bundle.getLong(C2561h.m699a("total_bytes_to_download", str)), c2406bz.m947b(str));
    }

    /* renamed from: a */
    public static AssetPackState m1037a(String str, int r11, int r12, long j, long j2, double d) {
        return new C2388bh(str, r11, r12, j, j2, (int) Math.rint(100.0d * d));
    }

    public abstract long bytesDownloaded();

    public abstract int errorCode();

    public abstract String name();

    public abstract int status();

    public abstract long totalBytesToDownload();

    public abstract int transferProgressPercentage();
}
