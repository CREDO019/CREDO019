package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbm {
    public static final zzbm zza = new zzbm(new zzbk());
    public static final zzn zzb = new zzn() { // from class: com.google.android.gms.internal.ads.zzbi
    };
    public final CharSequence zzc;
    public final CharSequence zzd;
    public final CharSequence zze;
    public final CharSequence zzf;
    public final CharSequence zzg;
    public final byte[] zzh;
    public final Integer zzi;
    public final Integer zzj;
    public final Integer zzk;
    @Deprecated
    public final Integer zzl;
    public final Integer zzm;
    public final Integer zzn;
    public final Integer zzo;
    public final Integer zzp;
    public final Integer zzq;
    public final Integer zzr;
    public final CharSequence zzs;
    public final CharSequence zzt;
    public final CharSequence zzu;
    public final CharSequence zzv;
    public final CharSequence zzw;

    private zzbm(zzbk zzbkVar) {
        CharSequence charSequence;
        CharSequence charSequence2;
        CharSequence charSequence3;
        CharSequence charSequence4;
        CharSequence charSequence5;
        byte[] bArr;
        Integer num;
        Integer num2;
        Integer num3;
        Integer num4;
        Integer num5;
        Integer num6;
        Integer num7;
        Integer num8;
        Integer num9;
        Integer num10;
        CharSequence charSequence6;
        CharSequence charSequence7;
        CharSequence charSequence8;
        CharSequence charSequence9;
        CharSequence charSequence10;
        charSequence = zzbkVar.zza;
        this.zzc = charSequence;
        charSequence2 = zzbkVar.zzb;
        this.zzd = charSequence2;
        charSequence3 = zzbkVar.zzc;
        this.zze = charSequence3;
        charSequence4 = zzbkVar.zzd;
        this.zzf = charSequence4;
        charSequence5 = zzbkVar.zze;
        this.zzg = charSequence5;
        bArr = zzbkVar.zzf;
        this.zzh = bArr;
        num = zzbkVar.zzg;
        this.zzi = num;
        num2 = zzbkVar.zzh;
        this.zzj = num2;
        num3 = zzbkVar.zzi;
        this.zzk = num3;
        num4 = zzbkVar.zzj;
        this.zzl = num4;
        num5 = zzbkVar.zzj;
        this.zzm = num5;
        num6 = zzbkVar.zzk;
        this.zzn = num6;
        num7 = zzbkVar.zzl;
        this.zzo = num7;
        num8 = zzbkVar.zzm;
        this.zzp = num8;
        num9 = zzbkVar.zzn;
        this.zzq = num9;
        num10 = zzbkVar.zzo;
        this.zzr = num10;
        charSequence6 = zzbkVar.zzp;
        this.zzs = charSequence6;
        charSequence7 = zzbkVar.zzq;
        this.zzt = charSequence7;
        charSequence8 = zzbkVar.zzr;
        this.zzu = charSequence8;
        charSequence9 = zzbkVar.zzs;
        this.zzv = charSequence9;
        charSequence10 = zzbkVar.zzt;
        this.zzw = charSequence10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzbm zzbmVar = (zzbm) obj;
            if (zzel.zzT(this.zzc, zzbmVar.zzc) && zzel.zzT(this.zzd, zzbmVar.zzd) && zzel.zzT(this.zze, zzbmVar.zze) && zzel.zzT(this.zzf, zzbmVar.zzf) && zzel.zzT(null, null) && zzel.zzT(null, null) && zzel.zzT(this.zzg, zzbmVar.zzg) && zzel.zzT(null, null) && zzel.zzT(null, null) && Arrays.equals(this.zzh, zzbmVar.zzh) && zzel.zzT(this.zzi, zzbmVar.zzi) && zzel.zzT(null, null) && zzel.zzT(this.zzj, zzbmVar.zzj) && zzel.zzT(this.zzk, zzbmVar.zzk) && zzel.zzT(null, null) && zzel.zzT(null, null) && zzel.zzT(this.zzm, zzbmVar.zzm) && zzel.zzT(this.zzn, zzbmVar.zzn) && zzel.zzT(this.zzo, zzbmVar.zzo) && zzel.zzT(this.zzp, zzbmVar.zzp) && zzel.zzT(this.zzq, zzbmVar.zzq) && zzel.zzT(this.zzr, zzbmVar.zzr) && zzel.zzT(this.zzs, zzbmVar.zzs) && zzel.zzT(this.zzt, zzbmVar.zzt) && zzel.zzT(this.zzu, zzbmVar.zzu) && zzel.zzT(null, null) && zzel.zzT(null, null) && zzel.zzT(this.zzv, zzbmVar.zzv) && zzel.zzT(null, null) && zzel.zzT(this.zzw, zzbmVar.zzw)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzc, this.zzd, this.zze, this.zzf, null, null, this.zzg, null, null, Integer.valueOf(Arrays.hashCode(this.zzh)), this.zzi, null, this.zzj, this.zzk, null, null, this.zzm, this.zzn, this.zzo, this.zzp, this.zzq, this.zzr, this.zzs, this.zzt, this.zzu, null, null, this.zzv, null, this.zzw});
    }

    public final zzbk zza() {
        return new zzbk(this, null);
    }
}
