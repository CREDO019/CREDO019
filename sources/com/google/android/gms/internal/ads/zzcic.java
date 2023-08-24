package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.TextureView;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcic extends TextureView implements zzciz {
    protected final zzciq zza;
    protected final zzcja zzb;

    public zzcic(Context context) {
        super(context);
        this.zza = new zzciq();
        this.zzb = new zzcja(context, this);
    }

    public void zzA(int r1) {
    }

    public void zzB(String str, String[] strArr) {
        zzs(str);
    }

    public abstract int zza();

    public abstract int zzb();

    public abstract int zzc();

    public abstract int zzd();

    public abstract int zze();

    public abstract long zzf();

    public abstract long zzg();

    public abstract long zzh();

    public abstract String zzj();

    public abstract void zzn();

    public abstract void zzo();

    public abstract void zzp();

    public abstract void zzq(int r1);

    public abstract void zzr(zzcib zzcibVar);

    public abstract void zzs(String str);

    public abstract void zzt();

    public abstract void zzu(float f, float f2);

    public void zzw(int r1) {
    }

    public void zzx(int r1) {
    }

    public void zzy(int r1) {
    }

    public void zzz(int r1) {
    }
}
