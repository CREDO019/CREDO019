package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbyh extends zzbnd {
    final /* synthetic */ zzbyk zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbyh(zzbyk zzbykVar, zzbyg zzbygVar) {
        this.zza = zzbykVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbne
    public final void zze(zzbmu zzbmuVar, String str) {
        zzbyk zzbykVar = this.zza;
        if (zzbyk.zzc(zzbykVar) == null) {
            return;
        }
        zzbyk.zzc(zzbykVar).onCustomClick(zzbyk.zze(zzbykVar, zzbmuVar), str);
    }
}
