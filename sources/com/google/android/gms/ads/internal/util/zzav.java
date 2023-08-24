package com.google.android.gms.ads.internal.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.polidea.multiplatformbleadapter.utils.Constants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzav implements Runnable {
    final /* synthetic */ Context zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ boolean zzc;
    final /* synthetic */ boolean zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzav(zzaw zzawVar, Context context, String str, boolean z, boolean z2) {
        this.zza = context;
        this.zzb = str;
        this.zzc = z;
        this.zzd = z2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.google.android.gms.ads.internal.zzt.zzq();
        AlertDialog.Builder zzG = zzs.zzG(this.zza);
        zzG.setMessage(this.zzb);
        if (this.zzc) {
            zzG.setTitle(Constants.BluetoothLogLevel.ERROR);
        } else {
            zzG.setTitle(Constants.BluetoothLogLevel.INFO);
        }
        if (this.zzd) {
            zzG.setNeutralButton("Dismiss", (DialogInterface.OnClickListener) null);
        } else {
            zzG.setPositiveButton("Learn More", new zzau(this));
            zzG.setNegativeButton("Dismiss", (DialogInterface.OnClickListener) null);
        }
        zzG.create().show();
    }
}
