package expo.modules.location.exceptions;

import expo.modules.core.errors.CodedException;
import expo.modules.core.interfaces.CodedThrowable;

/* loaded from: classes4.dex */
public class LocationRequestRejectedException extends CodedException implements CodedThrowable {
    @Override // expo.modules.core.errors.CodedException, expo.modules.core.interfaces.CodedThrowable
    public String getCode() {
        return "E_LOCATION_REQUEST_REJECTED";
    }

    public LocationRequestRejectedException(Exception exc) {
        super("Location request has been rejected: " + exc.getMessage());
    }
}
