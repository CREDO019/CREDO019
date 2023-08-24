package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbga extends zzgoj implements zzgpy {
    private zzbga() {
        super(zzbgb.zze());
    }

    public final zzbex zza() {
        return ((zzbgb) this.zza).zza();
    }

    public final zzbft zzb() {
        return ((zzbgb) this.zza).zzc();
    }

    public final zzbga zzc(Iterable iterable) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbgb.zzh((zzbgb) this.zza, iterable);
        return this;
    }

    public final zzbga zzd() {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbgb.zzi((zzbgb) this.zza);
        return this;
    }

    public final zzbga zze(zzbew zzbewVar) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbgb.zzk((zzbgb) this.zza, (zzbex) zzbewVar.zzal());
        return this;
    }

    public final zzbga zzf(zzbfg zzbfgVar) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbgb.zzn((zzbgb) this.zza, zzbfgVar);
        return this;
    }

    public final zzbga zzg(zzbfs zzbfsVar) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbgb.zzj((zzbgb) this.zza, (zzbft) zzbfsVar.zzal());
        return this;
    }

    public final zzbga zzh(String str) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbgb.zzg((zzbgb) this.zza, str);
        return this;
    }

    public final zzbga zzi(zzbgz zzbgzVar) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbgb.zzl((zzbgb) this.zza, zzbgzVar);
        return this;
    }

    public final zzbga zzj(zzbig zzbigVar) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbgb.zzm((zzbgb) this.zza, zzbigVar);
        return this;
    }

    public final String zzk() {
        return ((zzbgb) this.zza).zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbga(zzbes zzbesVar) {
        super(zzbgb.zze());
    }
}
