package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.GeofenceStatusCodes;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes.dex */
final class zzba extends zzaj {
    private final BaseImplementation.ResultHolder zza;

    public zzba(BaseImplementation.ResultHolder resultHolder) {
        Preconditions.checkArgument(resultHolder != null, "listener can't be null.");
        this.zza = resultHolder;
    }

    private final void zze(int r5) {
        BaseImplementation.ResultHolder resultHolder = this.zza;
        int r2 = GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE;
        if (r5 != 0 && (r5 < 1000 || r5 >= 1006)) {
            r5 = 13;
        }
        resultHolder.setResult(new Status(r5));
    }

    @Override // com.google.android.gms.internal.location.zzak
    public final void zzb(int r1, String[] strArr) {
        zze(r1);
    }

    @Override // com.google.android.gms.internal.location.zzak
    public final void zzc(int r1, PendingIntent pendingIntent) {
        zze(r1);
    }

    @Override // com.google.android.gms.internal.location.zzak
    public final void zzd(int r1, String[] strArr) {
        zze(r1);
    }
}
