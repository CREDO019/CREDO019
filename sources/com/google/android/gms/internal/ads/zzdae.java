package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdae implements zzfyk {
    final /* synthetic */ zzdaf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdae(zzdaf zzdafVar) {
        this.zza = zzdafVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzdic zzdicVar;
        zzdicVar = this.zza.zzf;
        zzdicVar.zzh(false);
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(@NullableDecl Object obj) {
        zzdic zzdicVar;
        Void r2 = (Void) obj;
        zzdicVar = this.zza.zzf;
        zzdicVar.zzh(true);
    }
}