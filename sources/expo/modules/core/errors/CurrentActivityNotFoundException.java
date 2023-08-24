package expo.modules.core.errors;

import expo.modules.core.interfaces.CodedThrowable;

/* loaded from: classes4.dex */
public class CurrentActivityNotFoundException extends CodedException implements CodedThrowable {
    @Override // expo.modules.core.errors.CodedException, expo.modules.core.interfaces.CodedThrowable
    public String getCode() {
        return "E_CURRENT_ACTIVITY_NOT_FOUND";
    }

    public CurrentActivityNotFoundException() {
        super("Current activity not found. Make sure to call this method while your application is in foreground.");
    }
}
