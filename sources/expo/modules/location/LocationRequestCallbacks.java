package expo.modules.location;

import android.location.Location;
import expo.modules.core.errors.CodedException;

/* loaded from: classes4.dex */
abstract class LocationRequestCallbacks {
    public void onLocationChanged(Location location) {
    }

    public void onLocationError(CodedException codedException) {
    }

    public void onRequestFailed(CodedException codedException) {
    }

    public void onRequestSuccess() {
    }
}
