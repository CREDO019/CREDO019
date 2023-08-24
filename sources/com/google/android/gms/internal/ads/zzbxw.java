package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbxw implements DialogInterface.OnClickListener {
    final /* synthetic */ zzbxx zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbxw(zzbxx zzbxxVar) {
        this.zza = zzbxxVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int r2) {
        this.zza.zzg("User canceled the download.");
    }
}
