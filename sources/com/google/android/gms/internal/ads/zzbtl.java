package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbtl extends zzchm {
    private final Object zza = new Object();
    private final zzbtq zzb;
    private boolean zzc;

    public zzbtl(zzbtq zzbtqVar) {
        this.zzb = zzbtqVar;
    }

    public final void zzb() {
        synchronized (this.zza) {
            if (this.zzc) {
                return;
            }
            this.zzc = true;
            zzi(new zzbti(this), new zzchi());
            zzi(new zzbtj(this), new zzbtk(this));
        }
    }
}
