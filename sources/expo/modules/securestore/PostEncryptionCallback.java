package expo.modules.securestore;

import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.Promise;
import java.security.GeneralSecurityException;
import kotlin.Metadata;
import org.json.JSONException;

/* compiled from: PostEncryptionCallback.kt */
@Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H&¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/securestore/PostEncryptionCallback;", "", "run", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "result", "expo-secure-store_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public interface PostEncryptionCallback {
    void run(Promise promise, Object obj) throws JSONException, GeneralSecurityException;
}
