package com.google.android.play.core.assetpacks;

import java.util.Map;

/* renamed from: com.google.android.play.core.assetpacks.bi */
/* loaded from: classes3.dex */
final class C2389bi extends AssetPackStates {

    /* renamed from: a */
    private final long f495a;

    /* renamed from: b */
    private final Map<String, AssetPackState> f496b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2389bi(long j, Map<String, AssetPackState> map) {
        this.f495a = j;
        this.f496b = map;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackStates) {
            AssetPackStates assetPackStates = (AssetPackStates) obj;
            if (this.f495a == assetPackStates.totalBytes() && this.f496b.equals(assetPackStates.packStates())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j = this.f495a;
        return this.f496b.hashCode() ^ ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackStates
    public final Map<String, AssetPackState> packStates() {
        return this.f496b;
    }

    public final String toString() {
        long j = this.f495a;
        String valueOf = String.valueOf(this.f496b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 61);
        sb.append("AssetPackStates{totalBytes=");
        sb.append(j);
        sb.append(", packStates=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackStates
    public final long totalBytes() {
        return this.f495a;
    }
}
