package expo.modules.updates.loader;

import android.util.Base64;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.C5015Response;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.ResponseBody;

@Metadata(m184d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a&\u0010\f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b\u001a \u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m183d2 = {"EXPO_PUBLIC_KEY_URL", "", "fetchExpoPublicKeyAndVerifyPublicRSASignature", "", "isFirstAttempt", "", "plainText", "cipherText", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lexpo/modules/updates/loader/RSASignatureListener;", "verifyExpoPublicRSASignature", "data", "signature", "verifyPublicRSASignature", "publicKey", "expo-updates_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.updates.loader.LegacySignatureUtilsKt */
/* loaded from: classes5.dex */
public final class LegacySignatureUtils {
    private static final String EXPO_PUBLIC_KEY_URL = "https://exp.host/--/manifest-public-key";

    public static final void verifyExpoPublicRSASignature(FileDownloader fileDownloader, String data, String signature, RSASignatureListener listener) {
        Intrinsics.checkNotNullParameter(fileDownloader, "fileDownloader");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(listener, "listener");
        fetchExpoPublicKeyAndVerifyPublicRSASignature(true, data, signature, fileDownloader, listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fetchExpoPublicKeyAndVerifyPublicRSASignature(final boolean z, final String str, final String str2, final FileDownloader fileDownloader, final RSASignatureListener rSASignatureListener) {
        fileDownloader.downloadData(new Request.Builder().url(EXPO_PUBLIC_KEY_URL).cacheControl(z ? CacheControl.FORCE_CACHE : CacheControl.FORCE_NETWORK).build(), new Callback() { // from class: expo.modules.updates.loader.LegacySignatureUtilsKt$fetchExpoPublicKeyAndVerifyPublicRSASignature$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                RSASignatureListener.this.onError(e, true);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, C5015Response response) throws IOException {
                boolean verifyPublicRSASignature;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                try {
                    ResponseBody body = response.body();
                    Intrinsics.checkNotNull(body);
                    verifyPublicRSASignature = LegacySignatureUtils.verifyPublicRSASignature(body.string(), str, str2);
                    RSASignatureListener.this.onCompleted(verifyPublicRSASignature);
                } catch (Exception e) {
                    if (z) {
                        LegacySignatureUtils.fetchExpoPublicKeyAndVerifyPublicRSASignature(false, str, str2, fileDownloader, RSASignatureListener.this);
                    } else {
                        RSASignatureListener.this.onError(e, false);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean verifyPublicRSASignature(String str, String str2, String str3) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        Object[] array = new Regex("\\r?\\n").split(str, 0).toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        int length = strArr.length;
        String str4 = "";
        int r3 = 0;
        while (r3 < length) {
            String str5 = strArr[r3];
            r3++;
            if (!StringsKt.contains$default((CharSequence) str5, (CharSequence) "PUBLIC KEY-----", false, 2, (Object) null)) {
                str4 = str4 + str5 + "\n";
            }
        }
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str4, 0))));
        byte[] bytes = str2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        signature.update(bytes);
        return signature.verify(Base64.decode(str3, 0));
    }
}
