package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzajy implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zzaka zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzajy(zzaka zzakaVar, String str, long j) {
        this.zzc = zzakaVar;
        this.zza = str;
        this.zzb = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzakl zzaklVar;
        zzakl zzaklVar2;
        zzaklVar = this.zzc.zza;
        zzaklVar.zza(this.zza, this.zzb);
        zzaka zzakaVar = this.zzc;
        zzaklVar2 = zzakaVar.zza;
        zzaklVar2.zzb(zzakaVar.toString());
    }
}
