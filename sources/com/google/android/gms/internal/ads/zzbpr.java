package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;
import expo.modules.interfaces.permissions.PermissionsResponse;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbpr implements zzbpq {
    private final zzeai zza;

    public zzbpr(zzeai zzeaiVar) {
        Preconditions.checkNotNull(zzeaiVar, "The Inspector Manager must not be null");
        this.zza = zzeaiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final void zza(Object obj, Map map) {
        if (map == null || !map.containsKey("extras")) {
            return;
        }
        long j = Long.MAX_VALUE;
        if (map.containsKey(PermissionsResponse.EXPIRES_KEY)) {
            try {
                j = Long.parseLong((String) map.get(PermissionsResponse.EXPIRES_KEY));
            } catch (NumberFormatException unused) {
            }
        }
        this.zza.zzh((String) map.get("extras"), j);
    }
}
