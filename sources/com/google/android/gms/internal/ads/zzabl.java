package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzabl implements zzzf {
    public static final zzzm zza = new zzzm() { // from class: com.google.android.gms.internal.ads.zzabk
        @Override // com.google.android.gms.internal.ads.zzzm
        public final zzzf[] zza() {
            zzzm zzzmVar = zzabl.zza;
            return new zzzf[]{new zzabl(0)};
        }

        @Override // com.google.android.gms.internal.ads.zzzm
        public final /* synthetic */ zzzf[] zzb(Uri uri, Map map) {
            return zzzl.zza(this, uri, map);
        }
    };
    private final byte[] zzb;
    private final zzed zzc;
    private final zzzn zzd;
    private zzzi zze;
    private zzaam zzf;
    private int zzg;
    private zzbq zzh;
    private zzzs zzi;
    private int zzj;
    private int zzk;
    private zzabj zzl;
    private int zzm;
    private long zzn;

    public zzabl() {
        this(0);
    }

    public zzabl(int r3) {
        this.zzb = new byte[42];
        this.zzc = new zzed(new byte[32768], 0);
        this.zzd = new zzzn();
        this.zzg = 0;
    }

    private final long zze(zzed zzedVar, boolean z) {
        boolean z2;
        Objects.requireNonNull(this.zzi);
        int zzc = zzedVar.zzc();
        while (zzc <= zzedVar.zzd() - 16) {
            zzedVar.zzF(zzc);
            if (zzzo.zzc(zzedVar, this.zzi, this.zzk, this.zzd)) {
                zzedVar.zzF(zzc);
                return this.zzd.zza;
            }
            zzc++;
        }
        if (z) {
            while (zzc <= zzedVar.zzd() - this.zzj) {
                zzedVar.zzF(zzc);
                try {
                    z2 = zzzo.zzc(zzedVar, this.zzi, this.zzk, this.zzd);
                } catch (IndexOutOfBoundsException unused) {
                    z2 = false;
                }
                if (zzedVar.zzc() <= zzedVar.zzd() && z2) {
                    zzedVar.zzF(zzc);
                    return this.zzd.zza;
                }
                zzc++;
            }
            zzedVar.zzF(zzedVar.zzd());
            return -1L;
        }
        zzedVar.zzF(zzc);
        return -1L;
    }

    private final void zzf() {
        long j = this.zzn;
        zzzs zzzsVar = this.zzi;
        int r3 = zzel.zza;
        this.zzf.zzs((j * 1000000) / zzzsVar.zze, 1, this.zzm, 0, null);
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzb(zzzi zzziVar) {
        this.zze = zzziVar;
        this.zzf = zzziVar.zzv(0, 1);
        zzziVar.zzB();
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final boolean zzd(zzzg zzzgVar) throws IOException {
        zzzp.zza(zzzgVar, false);
        zzed zzedVar = new zzed(4);
        ((zzyv) zzzgVar).zzm(zzedVar.zzH(), 0, 4, false);
        return zzedVar.zzs() == 1716281667;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzc(long j, long j2) {
        if (j == 0) {
            this.zzg = 0;
        } else {
            zzabj zzabjVar = this.zzl;
            if (zzabjVar != null) {
                zzabjVar.zzd(j2);
            }
        }
        this.zzn = j2 != 0 ? -1L : 0L;
        this.zzm = 0;
        this.zzc.zzC(0);
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final int zza(zzzg zzzgVar, zzaaf zzaafVar) throws IOException {
        boolean zzl;
        zzaai zzaahVar;
        boolean z;
        int r2 = this.zzg;
        if (r2 == 0) {
            zzzgVar.zzj();
            long zze = zzzgVar.zze();
            zzbq zza2 = zzzp.zza(zzzgVar, true);
            ((zzyv) zzzgVar).zzo((int) (zzzgVar.zze() - zze), false);
            this.zzh = zza2;
            this.zzg = 1;
            return 0;
        } else if (r2 == 1) {
            ((zzyv) zzzgVar).zzm(this.zzb, 0, 42, false);
            zzzgVar.zzj();
            this.zzg = 2;
            return 0;
        } else if (r2 == 2) {
            zzed zzedVar = new zzed(4);
            ((zzyv) zzzgVar).zzn(zzedVar.zzH(), 0, 4, false);
            if (zzedVar.zzs() == 1716281667) {
                this.zzg = 3;
                return 0;
            }
            throw zzbu.zza("Failed to read FLAC stream marker.", null);
        } else if (r2 == 3) {
            zzzs zzzsVar = this.zzi;
            do {
                zzzgVar.zzj();
                zzec zzecVar = new zzec(new byte[4], 4);
                zzyv zzyvVar = (zzyv) zzzgVar;
                zzyvVar.zzm(zzecVar.zza, 0, 4, false);
                zzl = zzecVar.zzl();
                int zzc = zzecVar.zzc(7);
                int zzc2 = zzecVar.zzc(24) + 4;
                if (zzc == 0) {
                    byte[] bArr = new byte[38];
                    zzyvVar.zzn(bArr, 0, 38, false);
                    zzzsVar = new zzzs(bArr, 4);
                } else if (zzzsVar == null) {
                    throw new IllegalArgumentException();
                } else {
                    if (zzc == 3) {
                        zzed zzedVar2 = new zzed(zzc2);
                        zzyvVar.zzn(zzedVar2.zzH(), 0, zzc2, false);
                        zzzsVar = zzzsVar.zzf(zzzp.zzb(zzedVar2));
                    } else if (zzc == 4) {
                        zzed zzedVar3 = new zzed(zzc2);
                        zzyvVar.zzn(zzedVar3.zzH(), 0, zzc2, false);
                        zzedVar3.zzG(4);
                        zzzsVar = zzzsVar.zzg(Arrays.asList(zzaas.zzc(zzedVar3, false, false).zzb));
                    } else if (zzc != 6) {
                        zzyvVar.zzo(zzc2, false);
                    } else {
                        zzed zzedVar4 = new zzed(zzc2);
                        zzyvVar.zzn(zzedVar4.zzH(), 0, zzc2, false);
                        zzedVar4.zzG(4);
                        zzzsVar = zzzsVar.zze(zzfuv.zzp(zzacf.zzb(zzedVar4)));
                    }
                }
                int r3 = zzel.zza;
                this.zzi = zzzsVar;
            } while (!zzl);
            Objects.requireNonNull(zzzsVar);
            this.zzj = Math.max(zzzsVar.zzc, 6);
            this.zzf.zzk(this.zzi.zzc(this.zzb, this.zzh));
            this.zzg = 4;
            return 0;
        } else if (r2 == 4) {
            zzzgVar.zzj();
            zzed zzedVar5 = new zzed(2);
            ((zzyv) zzzgVar).zzm(zzedVar5.zzH(), 0, 2, false);
            int zzo = zzedVar5.zzo();
            if ((zzo >> 2) != 16382) {
                zzzgVar.zzj();
                throw zzbu.zza("First frame does not start with sync code.", null);
            }
            zzzgVar.zzj();
            this.zzk = zzo;
            zzzi zzziVar = this.zze;
            int r32 = zzel.zza;
            long zzf = zzzgVar.zzf();
            long zzd = zzzgVar.zzd();
            zzzs zzzsVar2 = this.zzi;
            Objects.requireNonNull(zzzsVar2);
            if (zzzsVar2.zzk != null) {
                zzaahVar = new zzzq(zzzsVar2, zzf);
            } else if (zzd != -1 && zzzsVar2.zzj > 0) {
                zzabj zzabjVar = new zzabj(zzzsVar2, this.zzk, zzf, zzd);
                this.zzl = zzabjVar;
                zzaahVar = zzabjVar.zzb();
            } else {
                zzaahVar = new zzaah(zzzsVar2.zza(), 0L);
            }
            zzziVar.zzL(zzaahVar);
            this.zzg = 5;
            return 0;
        } else {
            Objects.requireNonNull(this.zzf);
            zzzs zzzsVar3 = this.zzi;
            Objects.requireNonNull(zzzsVar3);
            zzabj zzabjVar2 = this.zzl;
            if (zzabjVar2 == null || !zzabjVar2.zze()) {
                if (this.zzn == -1) {
                    this.zzn = zzzo.zzb(zzzgVar, zzzsVar3);
                    return 0;
                }
                zzed zzedVar6 = this.zzc;
                int zzd2 = zzedVar6.zzd();
                if (zzd2 < 32768) {
                    int zza3 = zzzgVar.zza(zzedVar6.zzH(), zzd2, 32768 - zzd2);
                    z = zza3 == -1;
                    if (!z) {
                        this.zzc.zzE(zzd2 + zza3);
                    } else if (this.zzc.zza() == 0) {
                        zzf();
                        return -1;
                    }
                } else {
                    z = false;
                }
                zzed zzedVar7 = this.zzc;
                int zzc3 = zzedVar7.zzc();
                int r5 = this.zzm;
                int r6 = this.zzj;
                if (r5 < r6) {
                    zzedVar7.zzG(Math.min(r6 - r5, zzedVar7.zza()));
                }
                long zze2 = zze(this.zzc, z);
                zzed zzedVar8 = this.zzc;
                int zzc4 = zzedVar8.zzc() - zzc3;
                zzedVar8.zzF(zzc3);
                zzaak.zzb(this.zzf, this.zzc, zzc4);
                this.zzm += zzc4;
                if (zze2 != -1) {
                    zzf();
                    this.zzm = 0;
                    this.zzn = zze2;
                }
                zzed zzedVar9 = this.zzc;
                if (zzedVar9.zza() >= 16) {
                    return 0;
                }
                int zza4 = zzedVar9.zza();
                System.arraycopy(zzedVar9.zzH(), zzedVar9.zzc(), zzedVar9.zzH(), 0, zza4);
                this.zzc.zzF(0);
                this.zzc.zzE(zza4);
                return 0;
            }
            return zzabjVar2.zza(zzzgVar, zzaafVar);
        }
    }
}
