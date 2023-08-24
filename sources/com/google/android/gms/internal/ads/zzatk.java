package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzatk implements Runnable {
    final /* synthetic */ zzass zza;
    final /* synthetic */ zzato zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzatk(zzato zzatoVar, zzass zzassVar) {
        this.zzb = zzatoVar;
        this.zza = zzassVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzatp zzatpVar;
        zzatpVar = this.zzb.zzb;
        zzatpVar.zzh(this.zza);
    }
}
