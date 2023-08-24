package expo.modules.updates.codesigning;

import android.util.Base64;
import android.util.Log;
import expo.modules.structuredheaders.BooleanItem;
import expo.modules.structuredheaders.Dictionary;
import expo.modules.structuredheaders.StringItem;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.codesigning.CodeSigningAlgorithm;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CodeSigningConfiguration.kt */
@Metadata(m184d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010\u0014\u001a\u00020\u0003J\"\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003J\"\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u0002R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, m183d2 = {"Lexpo/modules/updates/codesigning/CodeSigningConfiguration;", "", "embeddedCertificateString", "", UpdatesConfiguration.UPDATES_CONFIGURATION_CODE_SIGNING_METADATA, "", "includeManifestResponseCertificateChain", "", "allowUnsignedManifests", "(Ljava/lang/String;Ljava/util/Map;ZZ)V", "algorithmFromMetadata", "Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;", "getAlgorithmFromMetadata", "()Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;", "algorithmFromMetadata$delegate", "Lkotlin/Lazy;", "keyIdFromMetadata", "getKeyIdFromMetadata", "()Ljava/lang/String;", "keyIdFromMetadata$delegate", "getAcceptSignatureHeader", "validateSignature", "Lexpo/modules/updates/codesigning/SignatureValidationResult;", "signature", "bodyBytes", "", "manifestResponseCertificateChain", "validateSignatureInternal", "info", "Lexpo/modules/updates/codesigning/SignatureHeaderInfo;", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class CodeSigningConfiguration {
    public static final Companion Companion = new Companion(null);
    private final Lazy algorithmFromMetadata$delegate;
    private final boolean allowUnsignedManifests;
    private final Map<String, String> codeSigningMetadata;
    private final String embeddedCertificateString;
    private final boolean includeManifestResponseCertificateChain;
    private final Lazy keyIdFromMetadata$delegate;

    public CodeSigningConfiguration(String embeddedCertificateString, Map<String, String> map, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(embeddedCertificateString, "embeddedCertificateString");
        this.embeddedCertificateString = embeddedCertificateString;
        this.codeSigningMetadata = map;
        this.includeManifestResponseCertificateChain = z;
        this.allowUnsignedManifests = z2;
        this.algorithmFromMetadata$delegate = LazyKt.lazy(new Functions<CodeSigningAlgorithm>() { // from class: expo.modules.updates.codesigning.CodeSigningConfiguration$algorithmFromMetadata$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final CodeSigningAlgorithm invoke() {
                Map map2;
                CodeSigningAlgorithm.Companion companion = CodeSigningAlgorithm.Companion;
                map2 = CodeSigningConfiguration.this.codeSigningMetadata;
                return companion.parseFromString(map2 == null ? null : (String) map2.get("alg"));
            }
        });
        this.keyIdFromMetadata$delegate = LazyKt.lazy(new Functions<String>() { // from class: expo.modules.updates.codesigning.CodeSigningConfiguration$keyIdFromMetadata$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final String invoke() {
                Map map2;
                String str;
                map2 = CodeSigningConfiguration.this.codeSigningMetadata;
                return (map2 == null || (str = (String) map2.get("keyid")) == null) ? CodeSigningAlgorithmKt.CODE_SIGNING_METADATA_DEFAULT_KEY_ID : str;
            }
        });
    }

    private final CodeSigningAlgorithm getAlgorithmFromMetadata() {
        return (CodeSigningAlgorithm) this.algorithmFromMetadata$delegate.getValue();
    }

    private final String getKeyIdFromMetadata() {
        return (String) this.keyIdFromMetadata$delegate.getValue();
    }

    public final SignatureValidationResult validateSignature(String str, byte[] bodyBytes, String str2) {
        Intrinsics.checkNotNullParameter(bodyBytes, "bodyBytes");
        if (str == null) {
            if (!this.allowUnsignedManifests) {
                throw new Exception("No expo-signature header specified");
            }
            return new SignatureValidationResult(ValidationResult.SKIPPED, null);
        }
        return validateSignatureInternal(SignatureHeaderInfo.Companion.parseSignatureHeader(str), bodyBytes, str2);
    }

    private final SignatureValidationResult validateSignatureInternal(SignatureHeaderInfo signatureHeaderInfo, byte[] bArr, String str) {
        CertificateChain certificateChain;
        if (this.includeManifestResponseCertificateChain) {
            Companion companion = Companion;
            if (str == null) {
                str = "";
            }
            certificateChain = new CertificateChain(CollectionsKt.plus((Collection<? extends String>) companion.separateCertificateChain(str), this.embeddedCertificateString));
        } else if (!Intrinsics.areEqual(signatureHeaderInfo.getKeyId(), getKeyIdFromMetadata())) {
            String keyId = signatureHeaderInfo.getKeyId();
            throw new Exception("Key with keyid=" + keyId + " from signature not found in client configuration");
        } else {
            if (signatureHeaderInfo.getAlgorithm() != getAlgorithmFromMetadata()) {
                CodeSigningAlgorithm algorithm = signatureHeaderInfo.getAlgorithm();
                Log.i("CodeSigning", "Key with alg=" + algorithm + " from signature does not match client configuration algorithm, continuing");
            }
            certificateChain = new CertificateChain(CollectionsKt.listOf(this.embeddedCertificateString));
        }
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(certificateChain.getCodeSigningCertificate().getPublicKey());
        signature.update(bArr);
        return new SignatureValidationResult(signature.verify(Base64.decode(signatureHeaderInfo.getSignature(), 0)) ? ValidationResult.VALID : ValidationResult.INVALID, CertificateChain.Companion.expoProjectInformation(certificateChain.getCodeSigningCertificate()));
    }

    public final String getAcceptSignatureHeader() {
        String serialize = Dictionary.valueOf(MapsKt.mapOf(TuplesKt.m176to(SignatureHeaderInfoKt.CODE_SIGNING_SIGNATURE_STRUCTURED_FIELD_KEY_SIGNATURE, BooleanItem.valueOf(true)), TuplesKt.m176to("keyid", StringItem.valueOf(getKeyIdFromMetadata())), TuplesKt.m176to("alg", StringItem.valueOf(getAlgorithmFromMetadata().getAlgorithmName())))).serialize();
        Intrinsics.checkNotNullExpressionValue(serialize, "valueOf(\n      mapOf(\n  …      )\n    ).serialize()");
        return serialize;
    }

    /* compiled from: CodeSigningConfiguration.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/updates/codesigning/CodeSigningConfiguration$Companion;", "", "()V", "separateCertificateChain", "", "", "certificateChainInManifestResponse", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> separateCertificateChain(String certificateChainInManifestResponse) {
            Intrinsics.checkNotNullParameter(certificateChainInManifestResponse, "certificateChainInManifestResponse");
            ArrayList arrayList = new ArrayList();
            int r1 = 0;
            while (true) {
                String str = certificateChainInManifestResponse;
                int r4 = r1;
                int indexOf$default = StringsKt.indexOf$default((CharSequence) str, "-----BEGIN CERTIFICATE-----", r4, false, 4, (Object) null);
                int indexOf$default2 = StringsKt.indexOf$default((CharSequence) str, "-----END CERTIFICATE-----", r4, false, 4, (Object) null);
                if (indexOf$default == -1 || indexOf$default2 == -1) {
                    break;
                }
                r1 = indexOf$default2 + 25;
                String substring = certificateChainInManifestResponse.substring(indexOf$default, r1);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                arrayList.add(substring);
            }
            return arrayList;
        }
    }
}
