package expo.modules.core.errors;

import expo.modules.core.interfaces.CodedThrowable;

/* loaded from: classes4.dex */
public class ModuleNotFoundException extends CodedException implements CodedThrowable {
    @Override // expo.modules.core.errors.CodedException, expo.modules.core.interfaces.CodedThrowable
    public String getCode() {
        return "E_MODULE_NOT_FOUND";
    }

    public ModuleNotFoundException(String str) {
        super("Module '" + str + "' not found. Are you sure all modules are linked correctly?");
    }
}
