package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfga {
    private final zzfft zza;
    private final zzfyx zzb;
    private boolean zzc = false;
    private boolean zzd = false;

    public zzfga(final zzfey zzfeyVar, final zzffs zzffsVar, final zzfft zzfftVar) {
        this.zza = zzfftVar;
        this.zzb = zzfyo.zzg(zzfyo.zzn(zzffsVar.zza(zzfftVar), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzffy
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzfga.this.zzb(zzffsVar, zzfeyVar, zzfftVar, (zzffh) obj);
            }
        }, zzfftVar.zzb()), Exception.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzffz
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzfga.this.zzc(zzffsVar, (Exception) obj);
            }
        }, zzfftVar.zzb());
    }

    public final synchronized zzfyx zza(zzfft zzfftVar) {
        if (!this.zzd && !this.zzc) {
            if (this.zza.zza() != null && zzfftVar.zza() != null && this.zza.zza().equals(zzfftVar.zza())) {
                this.zzc = true;
                return this.zzb;
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzb(zzffs zzffsVar, zzfey zzfeyVar, zzfft zzfftVar, zzffh zzffhVar) throws Exception {
        synchronized (this) {
            this.zzd = true;
            zzffsVar.zzb(zzffhVar);
            if (!this.zzc) {
                zzfeyVar.zzd(zzfftVar.zza(), zzffhVar);
                return zzfyo.zzi(null);
            }
            return zzfyo.zzi(new zzffr(zzffhVar, zzfftVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(zzffs zzffsVar, Exception exc) throws Exception {
        synchronized (this) {
            this.zzd = true;
            throw exc;
        }
    }

    public final synchronized void zzd(zzfyk zzfykVar) {
        zzfyo.zzr(zzfyo.zzn(this.zzb, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzffx
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                zzffr zzffrVar = (zzffr) obj;
                return zzfyo.zzj();
            }
        }, this.zza.zzb()), zzfykVar, this.zza.zzb());
    }
}
