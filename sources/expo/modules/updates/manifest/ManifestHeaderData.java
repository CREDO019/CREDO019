package expo.modules.updates.manifest;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ManifestHeaderData.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003JE\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, m183d2 = {"Lexpo/modules/updates/manifest/ManifestHeaderData;", "", "protocolVersion", "", "serverDefinedHeaders", "manifestFilters", "manifestSignature", "signature", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getManifestFilters", "()Ljava/lang/String;", "getManifestSignature", "getProtocolVersion", "getServerDefinedHeaders", "getSignature", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ManifestHeaderData {
    private final String manifestFilters;
    private final String manifestSignature;
    private final String protocolVersion;
    private final String serverDefinedHeaders;
    private final String signature;

    public ManifestHeaderData() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ ManifestHeaderData copy$default(ManifestHeaderData manifestHeaderData, String str, String str2, String str3, String str4, String str5, int r9, Object obj) {
        if ((r9 & 1) != 0) {
            str = manifestHeaderData.protocolVersion;
        }
        if ((r9 & 2) != 0) {
            str2 = manifestHeaderData.serverDefinedHeaders;
        }
        String str6 = str2;
        if ((r9 & 4) != 0) {
            str3 = manifestHeaderData.manifestFilters;
        }
        String str7 = str3;
        if ((r9 & 8) != 0) {
            str4 = manifestHeaderData.manifestSignature;
        }
        String str8 = str4;
        if ((r9 & 16) != 0) {
            str5 = manifestHeaderData.signature;
        }
        return manifestHeaderData.copy(str, str6, str7, str8, str5);
    }

    public final String component1() {
        return this.protocolVersion;
    }

    public final String component2() {
        return this.serverDefinedHeaders;
    }

    public final String component3() {
        return this.manifestFilters;
    }

    public final String component4() {
        return this.manifestSignature;
    }

    public final String component5() {
        return this.signature;
    }

    public final ManifestHeaderData copy(String str, String str2, String str3, String str4, String str5) {
        return new ManifestHeaderData(str, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ManifestHeaderData) {
            ManifestHeaderData manifestHeaderData = (ManifestHeaderData) obj;
            return Intrinsics.areEqual(this.protocolVersion, manifestHeaderData.protocolVersion) && Intrinsics.areEqual(this.serverDefinedHeaders, manifestHeaderData.serverDefinedHeaders) && Intrinsics.areEqual(this.manifestFilters, manifestHeaderData.manifestFilters) && Intrinsics.areEqual(this.manifestSignature, manifestHeaderData.manifestSignature) && Intrinsics.areEqual(this.signature, manifestHeaderData.signature);
        }
        return false;
    }

    public int hashCode() {
        String str = this.protocolVersion;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.serverDefinedHeaders;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.manifestFilters;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.manifestSignature;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.signature;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        String str = this.protocolVersion;
        String str2 = this.serverDefinedHeaders;
        String str3 = this.manifestFilters;
        String str4 = this.manifestSignature;
        String str5 = this.signature;
        return "ManifestHeaderData(protocolVersion=" + str + ", serverDefinedHeaders=" + str2 + ", manifestFilters=" + str3 + ", manifestSignature=" + str4 + ", signature=" + str5 + ")";
    }

    public ManifestHeaderData(String str, String str2, String str3, String str4, String str5) {
        this.protocolVersion = str;
        this.serverDefinedHeaders = str2;
        this.manifestFilters = str3;
        this.manifestSignature = str4;
        this.signature = str5;
    }

    public /* synthetic */ ManifestHeaderData(String str, String str2, String str3, String str4, String str5, int r10, DefaultConstructorMarker defaultConstructorMarker) {
        this((r10 & 1) != 0 ? null : str, (r10 & 2) != 0 ? null : str2, (r10 & 4) != 0 ? null : str3, (r10 & 8) != 0 ? null : str4, (r10 & 16) != 0 ? null : str5);
    }

    public final String getProtocolVersion() {
        return this.protocolVersion;
    }

    public final String getServerDefinedHeaders() {
        return this.serverDefinedHeaders;
    }

    public final String getManifestFilters() {
        return this.manifestFilters;
    }

    public final String getManifestSignature() {
        return this.manifestSignature;
    }

    public final String getSignature() {
        return this.signature;
    }
}
