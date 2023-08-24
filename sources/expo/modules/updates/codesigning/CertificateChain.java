package expo.modules.updates.codesigning;

import expo.modules.updates.UpdatesConfiguration;
import java.io.ByteArrayInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;

/* compiled from: CertificateChain.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\r"}, m183d2 = {"Lexpo/modules/updates/codesigning/CertificateChain;", "", "certificateStrings", "", "", "(Ljava/util/List;)V", UpdatesConfiguration.UPDATES_CONFIGURATION_CODE_SIGNING_CERTIFICATE, "Ljava/security/cert/X509Certificate;", "getCodeSigningCertificate", "()Ljava/security/cert/X509Certificate;", "codeSigningCertificate$delegate", "Lkotlin/Lazy;", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class CertificateChain {
    public static final Companion Companion = new Companion(null);
    private final List<String> certificateStrings;
    private final Lazy codeSigningCertificate$delegate;

    public CertificateChain(List<String> certificateStrings) {
        Intrinsics.checkNotNullParameter(certificateStrings, "certificateStrings");
        this.certificateStrings = certificateStrings;
        this.codeSigningCertificate$delegate = LazyKt.lazy(new Functions<X509Certificate>() { // from class: expo.modules.updates.codesigning.CertificateChain$codeSigningCertificate$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final X509Certificate invoke() {
                List list;
                List list2;
                boolean isCodeSigningCertificate;
                X509Certificate constructCertificate;
                list = CertificateChain.this.certificateStrings;
                if (!list.isEmpty()) {
                    list2 = CertificateChain.this.certificateStrings;
                    List<String> list3 = list2;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                    for (String str : list3) {
                        constructCertificate = CertificateChain.Companion.constructCertificate(str);
                        arrayList.add(constructCertificate);
                    }
                    ArrayList arrayList2 = arrayList;
                    CertificateChain.Companion.validateChain(arrayList2);
                    X509Certificate x509Certificate = (X509Certificate) arrayList2.get(0);
                    isCodeSigningCertificate = CertificateChain.Companion.isCodeSigningCertificate(x509Certificate);
                    if (isCodeSigningCertificate) {
                        return x509Certificate;
                    }
                    throw new CertificateException("First certificate in chain is not a code signing certificate. Must have X509v3 Key Usage: Digital Signature and X509v3 Extended Key Usage: Code Signing");
                }
                throw new CertificateException("No code signing certificates provided");
            }
        });
    }

    public final X509Certificate getCodeSigningCertificate() {
        return (X509Certificate) this.codeSigningCertificate$delegate.getValue();
    }

    /* compiled from: CertificateChain.kt */
    @Metadata(m184d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\f\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0004J\f\u0010\t\u001a\u00020\n*\u00020\u0004H\u0002J\f\u0010\u000b\u001a\u00020\n*\u00020\u0004H\u0002J\u0012\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00040\u000eH\u0002¨\u0006\u000f"}, m183d2 = {"Lexpo/modules/updates/codesigning/CertificateChain$Companion;", "", "()V", "constructCertificate", "Ljava/security/cert/X509Certificate;", "certificateString", "", "expoProjectInformation", "Lexpo/modules/updates/codesigning/ExpoProjectInformation;", "isCACertificate", "", "isCodeSigningCertificate", "validateChain", "", "", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final X509Certificate constructCertificate(String str) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            Certificate generateCertificate = certificateFactory.generateCertificate(new ByteArrayInputStream(bytes));
            Objects.requireNonNull(generateCertificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            X509Certificate x509Certificate = (X509Certificate) generateCertificate;
            x509Certificate.checkValidity();
            return x509Certificate;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isCodeSigningCertificate(X509Certificate x509Certificate) {
            if (x509Certificate.getKeyUsage() != null) {
                boolean[] keyUsage = x509Certificate.getKeyUsage();
                Intrinsics.checkNotNullExpressionValue(keyUsage, "keyUsage");
                if ((!(keyUsage.length == 0)) && x509Certificate.getKeyUsage()[0] && x509Certificate.getExtendedKeyUsage() != null && x509Certificate.getExtendedKeyUsage().contains("1.3.6.1.5.5.7.3.3")) {
                    return true;
                }
            }
            return false;
        }

        public final ExpoProjectInformation expoProjectInformation(X509Certificate x509Certificate) {
            ASN1Primitive fromByteArray;
            String str;
            Intrinsics.checkNotNullParameter(x509Certificate, "<this>");
            byte[] extensionValue = x509Certificate.getExtensionValue("1.2.840.113556.1.8000.2554.43437.254.128.102.157.7894389.20439.2.1");
            if (extensionValue == null || (fromByteArray = ASN1Primitive.fromByteArray(extensionValue)) == null) {
                return null;
            }
            if (fromByteArray instanceof DEROctetString) {
                byte[] octets = ((DEROctetString) fromByteArray).getOctets();
                Intrinsics.checkNotNullExpressionValue(octets, "it.octets");
                str = StringsKt.decodeToString(octets);
            } else {
                str = null;
            }
            if (str == null) {
                return null;
            }
            List<String> split$default = StringsKt.split$default((CharSequence) str, new char[]{','}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
            for (String str2 : split$default) {
                arrayList.add(StringsKt.trim((CharSequence) str2).toString());
            }
            ArrayList arrayList2 = arrayList;
            if (arrayList2.size() != 2) {
                throw new CertificateException("Invalid Expo project information extension value");
            }
            return new ExpoProjectInformation((String) arrayList2.get(0), (String) arrayList2.get(1));
        }

        private final boolean isCACertificate(X509Certificate x509Certificate) {
            if (x509Certificate.getBasicConstraints() <= -1 || x509Certificate.getKeyUsage() == null) {
                return false;
            }
            boolean[] keyUsage = x509Certificate.getKeyUsage();
            Intrinsics.checkNotNullExpressionValue(keyUsage, "keyUsage");
            return ((keyUsage.length == 0) ^ true) && x509Certificate.getKeyUsage()[5];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void validateChain(List<? extends X509Certificate> list) {
            int size = list.size() - 1;
            int r2 = 0;
            while (r2 < size) {
                int r3 = r2 + 1;
                X509Certificate x509Certificate = list.get(r2);
                X509Certificate x509Certificate2 = list.get(r3);
                if (!x509Certificate.getIssuerX500Principal().equals(x509Certificate2.getSubjectX500Principal())) {
                    throw new CertificateException("Certificates do not chain");
                }
                x509Certificate.verify(x509Certificate2.getPublicKey());
                r2 = r3;
            }
            if (!((X509Certificate) CollectionsKt.last((List<? extends Object>) list)).getIssuerX500Principal().equals(((X509Certificate) CollectionsKt.last((List<? extends Object>) list)).getSubjectX500Principal())) {
                throw new CertificateException("Root certificate not self-signed");
            }
            ((X509Certificate) CollectionsKt.last((List<? extends Object>) list)).verify(((X509Certificate) CollectionsKt.last((List<? extends Object>) list)).getPublicKey());
            if (list.size() > 1) {
                X509Certificate x509Certificate3 = (X509Certificate) CollectionsKt.last((List<? extends Object>) list);
                if (!isCACertificate(x509Certificate3)) {
                    throw new CertificateException("Root certificate subject must be a Certificate Authority");
                }
                ExpoProjectInformation expoProjectInformation = expoProjectInformation(x509Certificate3);
                int basicConstraints = x509Certificate3.getBasicConstraints();
                int size2 = list.size() - 2;
                if (1 <= size2) {
                    while (true) {
                        int r4 = size2 - 1;
                        X509Certificate x509Certificate4 = list.get(size2);
                        if (!isCACertificate(x509Certificate4)) {
                            throw new CertificateException("Non-leaf certificate subject must be a Certificate Authority");
                        }
                        ExpoProjectInformation expoProjectInformation2 = expoProjectInformation(x509Certificate4);
                        if (expoProjectInformation != null && !Intrinsics.areEqual(expoProjectInformation, expoProjectInformation2)) {
                            throw new CertificateException("Expo project information must be a subset or equal of that of parent certificates");
                        }
                        if (basicConstraints <= 0) {
                            throw new CertificateException("pathLenConstraint violated by intermediate certificate");
                        }
                        basicConstraints = Math.min(x509Certificate4.getBasicConstraints(), basicConstraints - 1);
                        if (1 > r4) {
                            expoProjectInformation = expoProjectInformation2;
                            break;
                        } else {
                            size2 = r4;
                            expoProjectInformation = expoProjectInformation2;
                        }
                    }
                }
                if (expoProjectInformation != null && !Intrinsics.areEqual(expoProjectInformation, expoProjectInformation((X509Certificate) CollectionsKt.first((List<? extends Object>) list)))) {
                    throw new CertificateException("Expo project information must be a subset of or equal to that of parent certificates");
                }
            }
        }
    }
}
