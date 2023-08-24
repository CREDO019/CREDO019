package expo.modules.updates.codesigning;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CodeSigningAlgorithm.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007¨\u0006\t"}, m183d2 = {"Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;", "", "algorithmName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAlgorithmName", "()Ljava/lang/String;", "RSA_SHA256", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public enum CodeSigningAlgorithm {
    RSA_SHA256("rsa-v1_5-sha256");
    
    public static final Companion Companion = new Companion(null);
    private final String algorithmName;

    CodeSigningAlgorithm(String str) {
        this.algorithmName = str;
    }

    public final String getAlgorithmName() {
        return this.algorithmName;
    }

    /* compiled from: CodeSigningAlgorithm.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/updates/codesigning/CodeSigningAlgorithm$Companion;", "", "()V", "parseFromString", "Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;", "str", "", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CodeSigningAlgorithm parseFromString(String str) {
            if (Intrinsics.areEqual(str, CodeSigningAlgorithm.RSA_SHA256.getAlgorithmName())) {
                return CodeSigningAlgorithm.RSA_SHA256;
            }
            if (str == null) {
                return CodeSigningAlgorithm.RSA_SHA256;
            }
            throw new Exception("Invalid code signing algorithm name: " + str);
        }
    }
}
