package expo.modules.documentpicker;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DocumentDetailsReader.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J:\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\n¨\u0006\u001b"}, m183d2 = {"Lexpo/modules/documentpicker/DocumentDetails;", "", "name", "", "uri", "size", "", "mimeType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getMimeType", "()Ljava/lang/String;", "getName", "getSize", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUri", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lexpo/modules/documentpicker/DocumentDetails;", "equals", "", "other", "hashCode", "toString", "expo-document-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class DocumentDetails {
    private final String mimeType;
    private final String name;
    private final Integer size;
    private final String uri;

    public static /* synthetic */ DocumentDetails copy$default(DocumentDetails documentDetails, String str, String str2, Integer num, String str3, int r5, Object obj) {
        if ((r5 & 1) != 0) {
            str = documentDetails.name;
        }
        if ((r5 & 2) != 0) {
            str2 = documentDetails.uri;
        }
        if ((r5 & 4) != 0) {
            num = documentDetails.size;
        }
        if ((r5 & 8) != 0) {
            str3 = documentDetails.mimeType;
        }
        return documentDetails.copy(str, str2, num, str3);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.uri;
    }

    public final Integer component3() {
        return this.size;
    }

    public final String component4() {
        return this.mimeType;
    }

    public final DocumentDetails copy(String name, String uri, Integer num, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uri, "uri");
        return new DocumentDetails(name, uri, num, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DocumentDetails) {
            DocumentDetails documentDetails = (DocumentDetails) obj;
            return Intrinsics.areEqual(this.name, documentDetails.name) && Intrinsics.areEqual(this.uri, documentDetails.uri) && Intrinsics.areEqual(this.size, documentDetails.size) && Intrinsics.areEqual(this.mimeType, documentDetails.mimeType);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.name.hashCode() * 31) + this.uri.hashCode()) * 31;
        Integer num = this.size;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.mimeType;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        String str = this.name;
        String str2 = this.uri;
        Integer num = this.size;
        String str3 = this.mimeType;
        return "DocumentDetails(name=" + str + ", uri=" + str2 + ", size=" + num + ", mimeType=" + str3 + ")";
    }

    public DocumentDetails(String name, String uri, Integer num, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.name = name;
        this.uri = uri;
        this.size = num;
        this.mimeType = str;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final String getName() {
        return this.name;
    }

    public final Integer getSize() {
        return this.size;
    }

    public final String getUri() {
        return this.uri;
    }
}
