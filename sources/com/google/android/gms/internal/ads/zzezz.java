package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezz implements zzfru {
    final /* synthetic */ zzfad zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzezz(zzfad zzfadVar) {
        this.zza = zzfadVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfru
    @NullableDecl
    public final /* bridge */ /* synthetic */ Object apply(@NullableDecl Object obj) {
        zzffi zze;
        zzfac zzfacVar;
        zzcgn.zzh("", (zzecu) obj);
        com.google.android.gms.ads.internal.util.zze.zza("Failed to get a cache key, reverting to legacy flow.");
        zzfad zzfadVar = this.zza;
        zze = zzfadVar.zze();
        zzfadVar.zzd = new zzfac(null, zze, null);
        zzfacVar = this.zza.zzd;
        return zzfacVar;
    }
}
