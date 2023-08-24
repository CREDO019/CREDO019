package com.google.android.gms.internal.ads;

import androidx.core.view.InputDeviceCompat;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahz implements zzaim {
    private final zzahy zza;
    private final zzed zzb = new zzed(32);
    private int zzc;
    private int zzd;
    private boolean zze;
    private boolean zzf;

    public zzahz(zzahy zzahyVar) {
        this.zza = zzahyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaim
    public final void zza(zzed zzedVar, int r8) {
        int r82 = r8 & 1;
        int zzc = r82 != 0 ? zzedVar.zzc() + zzedVar.zzk() : -1;
        if (this.zzf) {
            if (r82 == 0) {
                return;
            }
            this.zzf = false;
            zzedVar.zzF(zzc);
            this.zzd = 0;
        }
        while (zzedVar.zza() > 0) {
            int r83 = this.zzd;
            if (r83 < 3) {
                if (r83 == 0) {
                    int zzk = zzedVar.zzk();
                    zzedVar.zzF(zzedVar.zzc() - 1);
                    if (zzk == 255) {
                        this.zzf = true;
                        return;
                    }
                }
                int min = Math.min(zzedVar.zza(), 3 - this.zzd);
                zzedVar.zzB(this.zzb.zzH(), this.zzd, min);
                int r3 = this.zzd + min;
                this.zzd = r3;
                if (r3 == 3) {
                    this.zzb.zzF(0);
                    this.zzb.zzE(3);
                    this.zzb.zzG(1);
                    int zzk2 = this.zzb.zzk();
                    int zzk3 = this.zzb.zzk();
                    this.zze = (zzk2 & 128) != 0;
                    this.zzc = (((zzk2 & 15) << 8) | zzk3) + 3;
                    int zzb = this.zzb.zzb();
                    int r2 = this.zzc;
                    if (zzb < r2) {
                        int zzb2 = this.zzb.zzb();
                        this.zzb.zzz(Math.min((int) InputDeviceCompat.SOURCE_TOUCHSCREEN, Math.max(r2, zzb2 + zzb2)));
                    }
                }
            } else {
                int min2 = Math.min(zzedVar.zza(), this.zzc - r83);
                zzedVar.zzB(this.zzb.zzH(), this.zzd, min2);
                int r22 = this.zzd + min2;
                this.zzd = r22;
                int r84 = this.zzc;
                if (r22 != r84) {
                    continue;
                } else {
                    if (this.zze) {
                        if (zzel.zzg(this.zzb.zzH(), 0, r84, -1) != 0) {
                            this.zzf = true;
                            return;
                        }
                        this.zzb.zzE(this.zzc - 4);
                    } else {
                        this.zzb.zzE(r84);
                    }
                    this.zzb.zzF(0);
                    this.zza.zza(this.zzb);
                    this.zzd = 0;
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaim
    public final void zzb(zzej zzejVar, zzzi zzziVar, zzail zzailVar) {
        this.zza.zzb(zzejVar, zzziVar, zzailVar);
        this.zzf = true;
    }

    @Override // com.google.android.gms.internal.ads.zzaim
    public final void zzc() {
        this.zzf = true;
    }
}
