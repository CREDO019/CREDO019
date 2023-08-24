package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfaa implements zzfru {
    final /* synthetic */ zzfad zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfaa(zzfad zzfadVar) {
        this.zza = zzfadVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfru
    @NullableDecl
    public final /* bridge */ /* synthetic */ Object apply(@NullableDecl Object obj) {
        zzfac zzfacVar;
        zzcba zzcbaVar = (zzcba) obj;
        this.zza.zzd = new zzfac(zzcbaVar, new zzffk(zzcbaVar.zzj), null);
        zzfacVar = this.zza.zzd;
        return zzfacVar;
    }
}
