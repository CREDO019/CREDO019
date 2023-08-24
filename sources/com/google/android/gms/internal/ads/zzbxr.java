package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbxr implements DialogInterface.OnClickListener {
    final /* synthetic */ zzbxs zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbxr(zzbxs zzbxsVar) {
        this.zza = zzbxsVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int r2) {
        this.zza.zzg("Operation denied by user.");
    }
}
