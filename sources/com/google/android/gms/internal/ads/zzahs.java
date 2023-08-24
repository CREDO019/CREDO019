package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahs implements zzyq {
    private final zzej zza;
    private final zzed zzb = new zzed();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzahs(zzej zzejVar, zzahr zzahrVar) {
        this.zza = zzejVar;
    }

    @Override // com.google.android.gms.internal.ads.zzyq
    public final zzyp zza(zzzg zzzgVar, long j) throws IOException {
        int zzh;
        long j2;
        long zzf = zzzgVar.zzf();
        int min = (int) Math.min((long) SilenceSkippingAudioProcessor.DEFAULT_PADDING_SILENCE_US, zzzgVar.zzd() - zzf);
        this.zzb.zzC(min);
        ((zzyv) zzzgVar).zzm(this.zzb.zzH(), 0, min, false);
        zzed zzedVar = this.zzb;
        int r4 = -1;
        long j3 = -9223372036854775807L;
        int r7 = -1;
        while (zzedVar.zza() >= 4) {
            if (zzaht.zzh(zzedVar.zzH(), zzedVar.zzc()) != 442) {
                zzedVar.zzG(1);
            } else {
                zzedVar.zzG(4);
                long zzc = zzahu.zzc(zzedVar);
                if (zzc != C1856C.TIME_UNSET) {
                    long zzb = this.zza.zzb(zzc);
                    if (zzb > j) {
                        if (j3 == C1856C.TIME_UNSET) {
                            return zzyp.zzd(zzb, zzf);
                        }
                        j2 = r7;
                    } else if (100000 + zzb > j) {
                        j2 = zzedVar.zzc();
                    } else {
                        r7 = zzedVar.zzc();
                        j3 = zzb;
                    }
                    return zzyp.zze(zzf + j2);
                }
                int zzd = zzedVar.zzd();
                if (zzedVar.zza() < 10) {
                    zzedVar.zzF(zzd);
                } else {
                    zzedVar.zzG(9);
                    int zzk = zzedVar.zzk() & 7;
                    if (zzedVar.zza() < zzk) {
                        zzedVar.zzF(zzd);
                    } else {
                        zzedVar.zzG(zzk);
                        if (zzedVar.zza() < 4) {
                            zzedVar.zzF(zzd);
                        } else {
                            if (zzaht.zzh(zzedVar.zzH(), zzedVar.zzc()) == 443) {
                                zzedVar.zzG(4);
                                int zzo = zzedVar.zzo();
                                if (zzedVar.zza() < zzo) {
                                    zzedVar.zzF(zzd);
                                } else {
                                    zzedVar.zzG(zzo);
                                }
                            }
                            while (true) {
                                if (zzedVar.zza() < 4 || (zzh = zzaht.zzh(zzedVar.zzH(), zzedVar.zzc())) == 442 || zzh == 441 || (zzh >>> 8) != 1) {
                                    break;
                                }
                                zzedVar.zzG(4);
                                if (zzedVar.zza() >= 2) {
                                    zzedVar.zzF(Math.min(zzedVar.zzd(), zzedVar.zzc() + zzedVar.zzo()));
                                } else {
                                    zzedVar.zzF(zzd);
                                    break;
                                }
                            }
                        }
                    }
                }
                r4 = zzedVar.zzc();
            }
        }
        return j3 != C1856C.TIME_UNSET ? zzyp.zzf(j3, zzf + r4) : zzyp.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzyq
    public final void zzb() {
        zzed zzedVar = this.zzb;
        byte[] bArr = zzel.zzf;
        int length = bArr.length;
        zzedVar.zzD(bArr, 0);
    }
}
