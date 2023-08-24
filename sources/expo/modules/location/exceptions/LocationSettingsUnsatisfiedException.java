package expo.modules.location.exceptions;

import expo.modules.core.errors.CodedException;
import expo.modules.core.interfaces.CodedThrowable;

/* loaded from: classes4.dex */
public class LocationSettingsUnsatisfiedException extends CodedException implements CodedThrowable {
    @Override // expo.modules.core.errors.CodedException, expo.modules.core.interfaces.CodedThrowable
    public String getCode() {
        return "E_LOCATION_SETTINGS_UNSATISFIED";
    }

    public LocationSettingsUnsatisfiedException() {
        super("Location request failed due to unsatisfied device settings.");
    }
}
