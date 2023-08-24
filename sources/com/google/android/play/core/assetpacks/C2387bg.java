package com.google.android.play.core.assetpacks;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.bg */
/* loaded from: classes3.dex */
public final class C2387bg extends AssetPackLocation {

    /* renamed from: a */
    private final int f486a;

    /* renamed from: b */
    private final String f487b;

    /* renamed from: c */
    private final String f488c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2387bg(int r1, String str, String str2) {
        this.f486a = r1;
        this.f487b = str;
        this.f488c = str2;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackLocation
    public final String assetsPath() {
        return this.f488c;
    }

    public final boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackLocation) {
            AssetPackLocation assetPackLocation = (AssetPackLocation) obj;
            if (this.f486a == assetPackLocation.packStorageMethod() && ((str = this.f487b) != null ? str.equals(assetPackLocation.path()) : assetPackLocation.path() == null)) {
                String str2 = this.f488c;
                String assetsPath = assetPackLocation.assetsPath();
                if (str2 != null ? str2.equals(assetsPath) : assetsPath == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = (this.f486a ^ 1000003) * 1000003;
        String str = this.f487b;
        int hashCode = (r0 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.f488c;
        return hashCode ^ (str2 != null ? str2.hashCode() : 0);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackLocation
    public final int packStorageMethod() {
        return this.f486a;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackLocation
    public final String path() {
        return this.f487b;
    }

    public final String toString() {
        int r0 = this.f486a;
        String str = this.f487b;
        String str2 = this.f488c;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str2).length());
        sb.append("AssetPackLocation{packStorageMethod=");
        sb.append(r0);
        sb.append(", path=");
        sb.append(str);
        sb.append(", assetsPath=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }
}
