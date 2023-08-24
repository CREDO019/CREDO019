package expo.modules.location.exceptions;

import expo.modules.core.errors.CodedException;
import expo.modules.core.interfaces.CodedThrowable;

/* loaded from: classes4.dex */
public class LocationUnavailableException extends CodedException implements CodedThrowable {
    @Override // expo.modules.core.errors.CodedException, expo.modules.core.interfaces.CodedThrowable
    public String getCode() {
        return "E_LOCATION_UNAVAILABLE";
    }

    public LocationUnavailableException() {
        super("Location is unavailable. Make sure that location services are enabled.");
    }
}
