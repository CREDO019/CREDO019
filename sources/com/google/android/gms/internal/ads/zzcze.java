package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcze implements zzczf {
    private final Map zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcze(Map map) {
        this.zza = map;
    }

    @Override // com.google.android.gms.internal.ads.zzczf
    public final zzegk zza(int r1, String str) {
        return (zzegk) this.zza.get(str);
    }
}
