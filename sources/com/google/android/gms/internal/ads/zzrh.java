package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzrh implements zzsi {
    private final ArrayList zza = new ArrayList(1);
    private final HashSet zzb = new HashSet(1);
    private final zzsp zzc = new zzsp();
    private final zzpi zzd = new zzpi();
    private Looper zze;
    private zzcn zzf;
    private zzmz zzg;

    @Override // com.google.android.gms.internal.ads.zzsi
    public final /* synthetic */ zzcn zzG() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzmz zzb() {
        zzmz zzmzVar = this.zzg;
        zzdd.zzb(zzmzVar);
        return zzmzVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzpi zzc(zzsg zzsgVar) {
        return this.zzd.zza(0, zzsgVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzpi zzd(int r2, zzsg zzsgVar) {
        return this.zzd.zza(r2, zzsgVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzsp zze(zzsg zzsgVar) {
        return this.zzc.zza(0, zzsgVar, 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzsp zzf(int r3, zzsg zzsgVar, long j) {
        return this.zzc.zza(r3, zzsgVar, 0L);
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzi(zzsh zzshVar) {
        boolean isEmpty = this.zzb.isEmpty();
        this.zzb.remove(zzshVar);
        if ((!isEmpty) && this.zzb.isEmpty()) {
            zzj();
        }
    }

    protected void zzj() {
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzk(zzsh zzshVar) {
        Objects.requireNonNull(this.zze);
        boolean isEmpty = this.zzb.isEmpty();
        this.zzb.add(zzshVar);
        if (isEmpty) {
            zzl();
        }
    }

    protected void zzl() {
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzm(zzsh zzshVar, zzfx zzfxVar, zzmz zzmzVar) {
        Looper myLooper = Looper.myLooper();
        Looper looper = this.zze;
        boolean z = true;
        if (looper != null && looper != myLooper) {
            z = false;
        }
        zzdd.zzd(z);
        this.zzg = zzmzVar;
        zzcn zzcnVar = this.zzf;
        this.zza.add(zzshVar);
        if (this.zze == null) {
            this.zze = myLooper;
            this.zzb.add(zzshVar);
            zzn(zzfxVar);
        } else if (zzcnVar != null) {
            zzk(zzshVar);
            zzshVar.zza(this, zzcnVar);
        }
    }

    protected abstract void zzn(zzfx zzfxVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzo(zzcn zzcnVar) {
        this.zzf = zzcnVar;
        ArrayList arrayList = this.zza;
        int size = arrayList.size();
        for (int r2 = 0; r2 < size; r2++) {
            ((zzsh) arrayList.get(r2)).zza(this, zzcnVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzp(zzsh zzshVar) {
        this.zza.remove(zzshVar);
        if (this.zza.isEmpty()) {
            this.zze = null;
            this.zzf = null;
            this.zzg = null;
            this.zzb.clear();
            zzq();
            return;
        }
        zzi(zzshVar);
    }

    protected abstract void zzq();

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzr(zzpj zzpjVar) {
        this.zzd.zzc(zzpjVar);
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzs(zzsq zzsqVar) {
        this.zzc.zzm(zzsqVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzt() {
        return !this.zzb.isEmpty();
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final /* synthetic */ boolean zzu() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzg(Handler handler, zzpj zzpjVar) {
        Objects.requireNonNull(zzpjVar);
        this.zzd.zzb(handler, zzpjVar);
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzh(Handler handler, zzsq zzsqVar) {
        Objects.requireNonNull(zzsqVar);
        this.zzc.zzb(handler, zzsqVar);
    }
}
