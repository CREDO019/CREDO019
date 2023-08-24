package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaxr implements zzazu {
    final /* synthetic */ zzaxu zza;
    private final Uri zzb;
    private final zzazi zzc;
    private final zzaxs zzd;
    private final zzbaa zze;
    private final zzava zzf;
    private volatile boolean zzg;
    private boolean zzh;
    private long zzi;
    private long zzj;

    public zzaxr(zzaxu zzaxuVar, Uri uri, zzazi zzaziVar, zzaxs zzaxsVar, zzbaa zzbaaVar) {
        this.zza = zzaxuVar;
        Objects.requireNonNull(uri);
        this.zzb = uri;
        Objects.requireNonNull(zzaziVar);
        this.zzc = zzaziVar;
        Objects.requireNonNull(zzaxsVar);
        this.zzd = zzaxsVar;
        this.zze = zzbaaVar;
        this.zzf = new zzava();
        this.zzh = true;
        this.zzj = -1L;
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final void zzb() {
        this.zzg = true;
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final void zzc() throws IOException, InterruptedException {
        zzauu zzauuVar;
        long j;
        while (!this.zzg) {
            int r3 = 0;
            try {
                long j2 = this.zzf.zza;
                long zzb = this.zzc.zzb(new zzazk(this.zzb, null, j2, j2, -1L, null, 0));
                this.zzj = zzb;
                if (zzb != -1) {
                    j = j2;
                    zzb += j;
                    this.zzj = zzb;
                } else {
                    j = j2;
                }
                zzauuVar = new zzauu(this.zzc, j, zzb);
                try {
                    zzauv zzb2 = this.zzd.zzb(zzauuVar, this.zzc.zzc());
                    if (this.zzh) {
                        zzb2.zze(j, this.zzi);
                        this.zzh = false;
                    }
                    long j3 = j;
                    int r4 = 0;
                    while (true) {
                        if (r4 != 0) {
                            break;
                        }
                        try {
                            if (this.zzg) {
                                r4 = 0;
                                break;
                            }
                            this.zze.zza();
                            r4 = zzb2.zzf(zzauuVar, this.zzf);
                            if (zzauuVar.zzd() > zzaxu.zzf(this.zza) + j3) {
                                j3 = zzauuVar.zzd();
                                this.zze.zzb();
                                zzaxu zzaxuVar = this.zza;
                                zzaxu.zzj(zzaxuVar).post(zzaxu.zzo(zzaxuVar));
                            }
                        } catch (Throwable th) {
                            th = th;
                            r3 = r4;
                            if (r3 != 1 && zzauuVar != null) {
                                this.zzf.zza = zzauuVar.zzd();
                            }
                            zzban.zzm(this.zzc);
                            throw th;
                        }
                    }
                    if (r4 != 1) {
                        this.zzf.zza = zzauuVar.zzd();
                        r3 = r4;
                    }
                    zzban.zzm(this.zzc);
                    if (r3 != 0) {
                        return;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                zzauuVar = null;
            }
        }
    }

    public final void zzd(long j, long j2) {
        this.zzf.zza = j;
        this.zzi = j2;
        this.zzh = true;
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final boolean zze() {
        return this.zzg;
    }
}
