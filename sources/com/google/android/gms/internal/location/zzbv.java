package com.google.android.gms.internal.location;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes.dex */
public final class zzbv extends zzbt {
    private final zzbx zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbv(zzbx zzbxVar, int r3) {
        super(zzbxVar.size(), r3);
        this.zza = zzbxVar;
    }

    @Override // com.google.android.gms.internal.location.zzbt
    protected final Object zza(int r2) {
        return this.zza.get(r2);
    }
}
