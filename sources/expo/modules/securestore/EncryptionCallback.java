package expo.modules.securestore;

import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.Promise;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import org.json.JSONException;

/* compiled from: EncryptionCallback.kt */
@Metadata(m184d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH&¨\u0006\u000b"}, m183d2 = {"Lexpo/modules/securestore/EncryptionCallback;", "", "run", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "cipher", "Ljavax/crypto/Cipher;", "gcmParameterSpec", "Ljavax/crypto/spec/GCMParameterSpec;", "postEncryptionCallback", "Lexpo/modules/securestore/PostEncryptionCallback;", "expo-secure-store_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public interface EncryptionCallback {
    Object run(Promise promise, Cipher cipher, GCMParameterSpec gCMParameterSpec, PostEncryptionCallback postEncryptionCallback) throws GeneralSecurityException, JSONException;
}
