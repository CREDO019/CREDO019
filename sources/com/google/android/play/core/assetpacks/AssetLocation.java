package com.google.android.play.core.assetpacks;

/* loaded from: classes3.dex */
public abstract class AssetLocation {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AssetLocation m1044a(String str, long j, long j2) {
        return new C2386bf(str, j, j2);
    }

    public abstract long offset();

    public abstract String path();

    public abstract long size();
}
