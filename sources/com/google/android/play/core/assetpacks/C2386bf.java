package com.google.android.play.core.assetpacks;

import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.bf */
/* loaded from: classes3.dex */
public final class C2386bf extends AssetLocation {

    /* renamed from: a */
    private final String f483a;

    /* renamed from: b */
    private final long f484b;

    /* renamed from: c */
    private final long f485c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2386bf(String str, long j, long j2) {
        Objects.requireNonNull(str, "Null path");
        this.f483a = str;
        this.f484b = j;
        this.f485c = j2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetLocation) {
            AssetLocation assetLocation = (AssetLocation) obj;
            if (this.f483a.equals(assetLocation.path()) && this.f484b == assetLocation.offset() && this.f485c == assetLocation.size()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f483a.hashCode();
        long j = this.f484b;
        long j2 = this.f485c;
        return ((((hashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2));
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final long offset() {
        return this.f484b;
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final String path() {
        return this.f483a;
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final long size() {
        return this.f485c;
    }

    public final String toString() {
        String str = this.f483a;
        long j = this.f484b;
        long j2 = this.f485c;
        StringBuilder sb = new StringBuilder(str.length() + 76);
        sb.append("AssetLocation{path=");
        sb.append(str);
        sb.append(", offset=");
        sb.append(j);
        sb.append(", size=");
        sb.append(j2);
        sb.append("}");
        return sb.toString();
    }
}
