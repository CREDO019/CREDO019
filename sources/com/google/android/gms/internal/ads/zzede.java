package com.google.android.gms.internal.ads;

import android.os.Binder;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzede {
    private final zzfyy zza;
    private final zzecl zzb;
    private final zzgul zzc;

    public zzede(zzfyy zzfyyVar, zzecl zzeclVar, zzgul zzgulVar) {
        this.zza = zzfyyVar;
        this.zzb = zzeclVar;
        this.zzc = zzgulVar;
    }

    private final zzfyx zzg(final zzcba zzcbaVar, zzedd zzeddVar, final zzedd zzeddVar2, final zzfxv zzfxvVar) {
        zzfyx zzg;
        String str = zzcbaVar.zzd;
        com.google.android.gms.ads.internal.zzt.zzq();
        if (com.google.android.gms.ads.internal.util.zzs.zzy(str)) {
            zzg = zzfyo.zzh(new zzecu(1));
        } else {
            zzg = zzfyo.zzg(zzeddVar.zza(zzcbaVar), ExecutionException.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzecv
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    return zzfyo.zzh(((ExecutionException) obj).getCause());
                }
            }, this.zza);
        }
        return zzfyo.zzg(zzfyo.zzn(zzfyf.zzv(zzg), zzfxvVar, this.zza), zzecu.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzedc
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzede.this.zzb(zzeddVar2, zzcbaVar, zzfxvVar, (zzecu) obj);
            }
        }, this.zza);
    }

    public final zzfyx zza(final zzcba zzcbaVar) {
        zzfxv zzfxvVar = new zzfxv() { // from class: com.google.android.gms.internal.ads.zzecz
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                zzcba zzcbaVar2 = zzcba.this;
                zzcbaVar2.zzj = new String(zzfwx.zza((InputStream) obj), zzfrs.zzc);
                return zzfyo.zzi(zzcbaVar2);
            }
        };
        final zzecl zzeclVar = this.zzb;
        return zzg(zzcbaVar, new zzedd() { // from class: com.google.android.gms.internal.ads.zzeda
            @Override // com.google.android.gms.internal.ads.zzedd
            public final zzfyx zza(zzcba zzcbaVar2) {
                return zzecl.this.zzb(zzcbaVar2);
            }
        }, new zzedd() { // from class: com.google.android.gms.internal.ads.zzedb
            @Override // com.google.android.gms.internal.ads.zzedd
            public final zzfyx zza(zzcba zzcbaVar2) {
                return zzede.this.zzc(zzcbaVar2);
            }
        }, zzfxvVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzb(zzedd zzeddVar, zzcba zzcbaVar, zzfxv zzfxvVar, zzecu zzecuVar) throws Exception {
        return zzfyo.zzn(zzeddVar.zza(zzcbaVar), zzfxvVar, this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(zzcba zzcbaVar) {
        return ((zzedv) this.zzc.zzb()).zzb(zzcbaVar, Binder.getCallingUid());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzd(zzcba zzcbaVar) {
        String str;
        zzecl zzeclVar = this.zzb;
        if (((Boolean) zzbku.zzd.zze()).booleanValue()) {
            str = zzcbaVar.zzh;
        } else {
            str = zzcbaVar.zzj;
        }
        return zzeclVar.zzc(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zze(zzcba zzcbaVar) {
        String str;
        zzedv zzedvVar = (zzedv) this.zzc.zzb();
        if (((Boolean) zzbku.zzd.zze()).booleanValue()) {
            str = zzcbaVar.zzh;
        } else {
            str = zzcbaVar.zzj;
        }
        return zzedvVar.zzi(str);
    }

    public final zzfyx zzf(zzcba zzcbaVar) {
        if (zzaqe.zzg(zzcbaVar.zzj)) {
            return zzfyo.zzh(new zzeas(2, "Pool key missing from removeUrl call."));
        }
        return zzg(zzcbaVar, new zzedd() { // from class: com.google.android.gms.internal.ads.zzecx
            @Override // com.google.android.gms.internal.ads.zzedd
            public final zzfyx zza(zzcba zzcbaVar2) {
                return zzede.this.zzd(zzcbaVar2);
            }
        }, new zzedd() { // from class: com.google.android.gms.internal.ads.zzecy
            @Override // com.google.android.gms.internal.ads.zzedd
            public final zzfyx zza(zzcba zzcbaVar2) {
                return zzede.this.zze(zzcbaVar2);
            }
        }, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzecw
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                InputStream inputStream = (InputStream) obj;
                return zzfyo.zzi(null);
            }
        });
    }
}
