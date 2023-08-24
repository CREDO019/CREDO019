package expo.modules.securestore;

import androidx.biometric.BiometricPrompt;
import expo.modules.core.Promise;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: AuthenticationHelper.kt */
@Metadata(m184d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, m183d2 = {"expo/modules/securestore/AuthenticationHelper$openAuthenticationPrompt$1$1", "Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "onAuthenticationError", "", "errorCode", "", "errString", "", "onAuthenticationSucceeded", "result", "Landroidx/biometric/BiometricPrompt$AuthenticationResult;", "expo-secure-store_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class AuthenticationHelper$openAuthenticationPrompt$1$1 extends BiometricPrompt.AuthenticationCallback {
    final /* synthetic */ EncryptionCallback $encryptionCallback;
    final /* synthetic */ GCMParameterSpec $gcmParameterSpec;
    final /* synthetic */ PostEncryptionCallback $postEncryptionCallback;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ AuthenticationHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthenticationHelper$openAuthenticationPrompt$1$1(AuthenticationHelper authenticationHelper, Promise promise, EncryptionCallback encryptionCallback, GCMParameterSpec gCMParameterSpec, PostEncryptionCallback postEncryptionCallback) {
        this.this$0 = authenticationHelper;
        this.$promise = promise;
        this.$encryptionCallback = encryptionCallback;
        this.$gcmParameterSpec = gCMParameterSpec;
        this.$postEncryptionCallback = postEncryptionCallback;
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        super.onAuthenticationSucceeded(result);
        this.this$0.isAuthenticating = false;
        BiometricPrompt.CryptoObject cryptoObject = result.getCryptoObject();
        Intrinsics.checkNotNull(cryptoObject);
        Cipher cipher = cryptoObject.getCipher();
        Intrinsics.checkNotNull(cipher);
        Intrinsics.checkNotNullExpressionValue(cipher, "result.cryptoObject!!.cipher!!");
        AuthenticationHelper authenticationHelper = this.this$0;
        Promise promise = this.$promise;
        EncryptionCallback encryptionCallback = this.$encryptionCallback;
        GCMParameterSpec gCMParameterSpec = this.$gcmParameterSpec;
        final PostEncryptionCallback postEncryptionCallback = this.$postEncryptionCallback;
        authenticationHelper.handleEncryptionCallback(promise, encryptionCallback, cipher, gCMParameterSpec, new PostEncryptionCallback() { // from class: expo.modules.securestore.AuthenticationHelper$openAuthenticationPrompt$1$1$$ExternalSyntheticLambda0
            @Override // expo.modules.securestore.PostEncryptionCallback
            public final void run(Promise promise2, Object obj) {
                AuthenticationHelper$openAuthenticationPrompt$1$1.m1693onAuthenticationSucceeded$lambda0(PostEncryptionCallback.this, promise2, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAuthenticationSucceeded$lambda-0  reason: not valid java name */
    public static final void m1693onAuthenticationSucceeded$lambda0(PostEncryptionCallback postEncryptionCallback, Promise promise, Object result) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        Intrinsics.checkNotNullParameter(result, "result");
        ((JSONObject) result).put(AuthenticationHelper.REQUIRE_AUTHENTICATION_PROPERTY, true);
        if (postEncryptionCallback == null) {
            return;
        }
        postEncryptionCallback.run(promise, result);
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationError(int r2, CharSequence errString) {
        Intrinsics.checkNotNullParameter(errString, "errString");
        super.onAuthenticationError(r2, errString);
        this.this$0.isAuthenticating = false;
        if (r2 == 10 || r2 == 13) {
            this.$promise.reject("ERR_SECURESTORE_AUTH_CANCELLED", "User canceled the authentication");
        } else {
            this.$promise.reject("ERR_SECURESTORE_AUTH_FAILURE", "Could not authenticate the user");
        }
    }
}
