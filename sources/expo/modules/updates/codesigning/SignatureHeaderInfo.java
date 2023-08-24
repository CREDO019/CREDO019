package expo.modules.updates.codesigning;

import expo.modules.structuredheaders.ListElement;
import expo.modules.structuredheaders.Parser;
import expo.modules.structuredheaders.StringItem;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SignatureHeaderInfo.kt */
@Metadata(m184d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, m183d2 = {"Lexpo/modules/updates/codesigning/SignatureHeaderInfo;", "", "signature", "", "keyId", "algorithm", "Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;", "(Ljava/lang/String;Ljava/lang/String;Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;)V", "getAlgorithm", "()Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;", "getKeyId", "()Ljava/lang/String;", "getSignature", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class SignatureHeaderInfo {
    public static final Companion Companion = new Companion(null);
    private final CodeSigningAlgorithm algorithm;
    private final String keyId;
    private final String signature;

    public static /* synthetic */ SignatureHeaderInfo copy$default(SignatureHeaderInfo signatureHeaderInfo, String str, String str2, CodeSigningAlgorithm codeSigningAlgorithm, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            str = signatureHeaderInfo.signature;
        }
        if ((r4 & 2) != 0) {
            str2 = signatureHeaderInfo.keyId;
        }
        if ((r4 & 4) != 0) {
            codeSigningAlgorithm = signatureHeaderInfo.algorithm;
        }
        return signatureHeaderInfo.copy(str, str2, codeSigningAlgorithm);
    }

    public final String component1() {
        return this.signature;
    }

    public final String component2() {
        return this.keyId;
    }

    public final CodeSigningAlgorithm component3() {
        return this.algorithm;
    }

    public final SignatureHeaderInfo copy(String signature, String keyId, CodeSigningAlgorithm algorithm) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        return new SignatureHeaderInfo(signature, keyId, algorithm);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SignatureHeaderInfo) {
            SignatureHeaderInfo signatureHeaderInfo = (SignatureHeaderInfo) obj;
            return Intrinsics.areEqual(this.signature, signatureHeaderInfo.signature) && Intrinsics.areEqual(this.keyId, signatureHeaderInfo.keyId) && this.algorithm == signatureHeaderInfo.algorithm;
        }
        return false;
    }

    public int hashCode() {
        return (((this.signature.hashCode() * 31) + this.keyId.hashCode()) * 31) + this.algorithm.hashCode();
    }

    public String toString() {
        String str = this.signature;
        String str2 = this.keyId;
        CodeSigningAlgorithm codeSigningAlgorithm = this.algorithm;
        return "SignatureHeaderInfo(signature=" + str + ", keyId=" + str2 + ", algorithm=" + codeSigningAlgorithm + ")";
    }

    /* compiled from: SignatureHeaderInfo.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/updates/codesigning/SignatureHeaderInfo$Companion;", "", "()V", "parseSignatureHeader", "Lexpo/modules/updates/codesigning/SignatureHeaderInfo;", "signatureHeader", "", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SignatureHeaderInfo parseSignatureHeader(String signatureHeader) {
            Intrinsics.checkNotNullParameter(signatureHeader, "signatureHeader");
            Map<String, ListElement<? extends Object>> map = new Parser(signatureHeader).parseDictionary().get();
            ListElement<? extends Object> listElement = map.get(SignatureHeaderInfoKt.CODE_SIGNING_SIGNATURE_STRUCTURED_FIELD_KEY_SIGNATURE);
            ListElement<? extends Object> listElement2 = map.get("keyid");
            ListElement<? extends Object> listElement3 = map.get("alg");
            if (listElement instanceof StringItem) {
                String signature = ((StringItem) listElement).get();
                String keyId = listElement2 instanceof StringItem ? ((StringItem) listElement2).get() : CodeSigningAlgorithmKt.CODE_SIGNING_METADATA_DEFAULT_KEY_ID;
                String str = listElement3 instanceof StringItem ? ((StringItem) listElement3).get() : null;
                Intrinsics.checkNotNullExpressionValue(signature, "signature");
                Intrinsics.checkNotNullExpressionValue(keyId, "keyId");
                return new SignatureHeaderInfo(signature, keyId, CodeSigningAlgorithm.Companion.parseFromString(str));
            }
            throw new Exception("Structured field sig not found in expo-signature header");
        }
    }

    public SignatureHeaderInfo(String signature, String keyId, CodeSigningAlgorithm algorithm) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.signature = signature;
        this.keyId = keyId;
        this.algorithm = algorithm;
    }

    public final CodeSigningAlgorithm getAlgorithm() {
        return this.algorithm;
    }

    public final String getKeyId() {
        return this.keyId;
    }

    public final String getSignature() {
        return this.signature;
    }
}
