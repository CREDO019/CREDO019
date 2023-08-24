package expo.modules.splashscreen.exceptions;

import expo.modules.core.errors.CodedException;
import kotlin.Metadata;

/* compiled from: NoContentViewException.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m183d2 = {"Lexpo/modules/splashscreen/exceptions/NoContentViewException;", "Lexpo/modules/core/errors/CodedException;", "()V", "getCode", "", "expo-splash-screen_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class NoContentViewException extends CodedException {
    @Override // expo.modules.core.errors.CodedException, expo.modules.core.interfaces.CodedThrowable
    public String getCode() {
        return "ERR_NO_CONTENT_VIEW_FOUND";
    }

    public NoContentViewException() {
        super("ContentView is not yet available. Call 'SplashScreen.show(...)' once 'setContentView()' is called.");
    }
}
