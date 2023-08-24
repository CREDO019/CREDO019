package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.internal.ads.zzapa;
import com.google.android.gms.internal.ads.zzapb;
import com.google.android.gms.internal.ads.zzcgt;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzo implements Callable {
    final /* synthetic */ zzs zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(zzs zzsVar) {
        this.zza = zzsVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzcgt zzcgtVar;
        Context context;
        zzs zzsVar = this.zza;
        zzcgtVar = zzsVar.zza;
        String str = zzcgtVar.zza;
        context = zzsVar.zzd;
        return new zzapb(zzapa.zzs(str, context, false));
    }
}
