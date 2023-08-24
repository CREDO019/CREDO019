package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfut extends zzfsx {
    private final zzfuv zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfut(zzfuv zzfuvVar, int r3) {
        super(zzfuvVar.size(), r3);
        this.zza = zzfuvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfsx
    protected final Object zza(int r2) {
        return this.zza.get(r2);
    }
}
