package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfd implements zzeu {
    private zzfx zzb;
    private String zzc;
    private boolean zzf;
    private final zzfr zza = new zzfr();
    private int zzd = 8000;
    private int zze = 8000;

    public final zzfd zzb(boolean z) {
        this.zzf = true;
        return this;
    }

    public final zzfd zzc(int r1) {
        this.zzd = r1;
        return this;
    }

    public final zzfd zzd(int r1) {
        this.zze = r1;
        return this;
    }

    public final zzfd zze(zzfx zzfxVar) {
        this.zzb = zzfxVar;
        return this;
    }

    public final zzfd zzf(String str) {
        this.zzc = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzeu
    /* renamed from: zzg */
    public final zzfi zza() {
        zzfi zzfiVar = new zzfi(this.zzc, this.zzd, this.zze, this.zzf, this.zza);
        zzfx zzfxVar = this.zzb;
        if (zzfxVar != null) {
            zzfiVar.zzf(zzfxVar);
        }
        return zzfiVar;
    }
}
