package com.google.android.play.core.assetpacks;

/* loaded from: classes3.dex */
public abstract class AssetPackLocation {

    /* renamed from: a */
    private static final AssetPackLocation f377a = new C2387bg(1, null, null);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AssetPackLocation m1040a() {
        return f377a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AssetPackLocation m1039a(String str, String str2) {
        return new C2387bg(0, str, str2);
    }

    public abstract String assetsPath();

    public abstract int packStorageMethod();

    public abstract String path();
}
