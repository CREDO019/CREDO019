package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.C1856C;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaxw implements zzaya, zzaxz {
    private final Uri zza;
    private final zzazh zzb;
    private final zzaux zzc;
    private final int zzd;
    private final Handler zze;
    private final zzaxv zzf;
    private final zzatb zzg = new zzatb();
    private final int zzh;
    private zzaxz zzi;
    private zzatd zzj;
    private boolean zzk;

    public zzaxw(Uri uri, zzazh zzazhVar, zzaux zzauxVar, int r4, Handler handler, zzaxv zzaxvVar, String str, int r8) {
        this.zza = uri;
        this.zzb = zzazhVar;
        this.zzc = zzauxVar;
        this.zzd = r4;
        this.zze = handler;
        this.zzf = zzaxvVar;
        this.zzh = r8;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zza() throws IOException {
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzb(zzasi zzasiVar, boolean z, zzaxz zzaxzVar) {
        this.zzi = zzaxzVar;
        zzayn zzaynVar = new zzayn(C1856C.TIME_UNSET, false);
        this.zzj = zzaynVar;
        zzaxzVar.zzg(zzaynVar, null);
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzc(zzaxy zzaxyVar) {
        ((zzaxu) zzaxyVar).zzx();
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzd() {
        this.zzi = null;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final zzaxy zze(int r13, zzazl zzazlVar) {
        zzazy.zzc(r13 == 0);
        return new zzaxu(this.zza, this.zzb.zza(), this.zzc.zza(), this.zzd, this.zze, this.zzf, this, zzazlVar, null, this.zzh, null);
    }

    @Override // com.google.android.gms.internal.ads.zzaxz
    public final void zzg(zzatd zzatdVar, Object obj) {
        zzatb zzatbVar = this.zzg;
        zzatdVar.zzd(0, zzatbVar, false);
        boolean z = zzatbVar.zzc != C1856C.TIME_UNSET;
        if (!this.zzk || z) {
            this.zzj = zzatdVar;
            this.zzk = z;
            this.zzi.zzg(zzatdVar, null);
        }
    }
}
