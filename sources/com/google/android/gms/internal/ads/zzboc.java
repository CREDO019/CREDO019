package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzboc extends zzbnd {
    final /* synthetic */ zzbof zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzboc(zzbof zzbofVar, zzbob zzbobVar) {
        this.zza = zzbofVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbne
    public final void zze(zzbmu zzbmuVar, String str) {
        zzbof zzbofVar = this.zza;
        if (zzbof.zza(zzbofVar) == null) {
            return;
        }
        zzbof.zza(zzbofVar).onCustomClick(zzbof.zzc(zzbofVar, zzbmuVar), str);
    }
}
