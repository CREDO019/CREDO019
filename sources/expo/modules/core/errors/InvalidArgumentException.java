package expo.modules.core.errors;

/* loaded from: classes4.dex */
public class InvalidArgumentException extends CodedRuntimeException {
    @Override // expo.modules.core.errors.CodedRuntimeException, expo.modules.core.interfaces.CodedThrowable
    public String getCode() {
        return "ERR_INVALID_ARGUMENT";
    }

    public InvalidArgumentException(String str) {
        super(str);
    }

    public InvalidArgumentException(Throwable th) {
        super(th);
    }

    public InvalidArgumentException(String str, Throwable th) {
        super(str, th);
    }
}
