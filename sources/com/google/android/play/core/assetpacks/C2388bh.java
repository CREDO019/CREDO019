package com.google.android.play.core.assetpacks;

import java.util.Objects;

/* renamed from: com.google.android.play.core.assetpacks.bh */
/* loaded from: classes3.dex */
final class C2388bh extends AssetPackState {

    /* renamed from: a */
    private final String f489a;

    /* renamed from: b */
    private final int f490b;

    /* renamed from: c */
    private final int f491c;

    /* renamed from: d */
    private final long f492d;

    /* renamed from: e */
    private final long f493e;

    /* renamed from: f */
    private final int f494f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2388bh(String str, int r3, int r4, long j, long j2, int r9) {
        Objects.requireNonNull(str, "Null name");
        this.f489a = str;
        this.f490b = r3;
        this.f491c = r4;
        this.f492d = j;
        this.f493e = j2;
        this.f494f = r9;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final long bytesDownloaded() {
        return this.f492d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackState) {
            AssetPackState assetPackState = (AssetPackState) obj;
            if (this.f489a.equals(assetPackState.name()) && this.f490b == assetPackState.status() && this.f491c == assetPackState.errorCode() && this.f492d == assetPackState.bytesDownloaded() && this.f493e == assetPackState.totalBytesToDownload() && this.f494f == assetPackState.transferProgressPercentage()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final int errorCode() {
        return this.f491c;
    }

    public final int hashCode() {
        int hashCode = this.f489a.hashCode();
        int r1 = this.f490b;
        int r2 = this.f491c;
        long j = this.f492d;
        long j2 = this.f493e;
        return ((((((((((hashCode ^ 1000003) * 1000003) ^ r1) * 1000003) ^ r2) * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.f494f;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final String name() {
        return this.f489a;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final int status() {
        return this.f490b;
    }

    public final String toString() {
        String str = this.f489a;
        int r1 = this.f490b;
        int r2 = this.f491c;
        long j = this.f492d;
        long j2 = this.f493e;
        int r7 = this.f494f;
        StringBuilder sb = new StringBuilder(str.length() + 185);
        sb.append("AssetPackState{name=");
        sb.append(str);
        sb.append(", status=");
        sb.append(r1);
        sb.append(", errorCode=");
        sb.append(r2);
        sb.append(", bytesDownloaded=");
        sb.append(j);
        sb.append(", totalBytesToDownload=");
        sb.append(j2);
        sb.append(", transferProgressPercentage=");
        sb.append(r7);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final long totalBytesToDownload() {
        return this.f493e;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final int transferProgressPercentage() {
        return this.f494f;
    }
}
