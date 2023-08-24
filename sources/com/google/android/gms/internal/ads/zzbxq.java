package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbxq implements DialogInterface.OnClickListener {
    final /* synthetic */ zzbxs zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbxq(zzbxs zzbxsVar) {
        this.zza = zzbxsVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int r2) {
        Context context;
        Intent zzb = this.zza.zzb();
        com.google.android.gms.ads.internal.zzt.zzq();
        context = this.zza.zzb;
        com.google.android.gms.ads.internal.util.zzs.zzJ(context, zzb);
    }
}
