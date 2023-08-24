package expo.modules.updates.loader;

import kotlin.Metadata;

/* compiled from: LegacySignatureUtils.kt */
@Metadata(m184d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001c\u0010\u0006\u001a\u00020\u00032\n\u0010\u0007\u001a\u00060\bj\u0002`\t2\u0006\u0010\n\u001a\u00020\u0005H&¨\u0006\u000b"}, m183d2 = {"Lexpo/modules/updates/loader/RSASignatureListener;", "", "onCompleted", "", "isValid", "", "onError", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "isNetworkError", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public interface RSASignatureListener {
    void onCompleted(boolean z);

    void onError(Exception exc, boolean z);
}
