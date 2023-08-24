package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* compiled from: com.android.billingclient:billing@@4.0.0 */
/* loaded from: classes.dex */
public final class zzg extends BroadcastReceiver {
    final /* synthetic */ zzh zza;
    private final PurchasesUpdatedListener zzb;
    private boolean zzc;

    public /* synthetic */ zzg(zzh zzhVar, PurchasesUpdatedListener purchasesUpdatedListener, zzf zzfVar) {
        this.zza = zzhVar;
        this.zzb = purchasesUpdatedListener;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.zzb.onPurchasesUpdated(com.google.android.gms.internal.play_billing.zza.zzg(intent, "BillingBroadcastManager"), com.google.android.gms.internal.play_billing.zza.zzi(intent.getExtras()));
    }

    public final void zzb(Context context, IntentFilter intentFilter) {
        zzg zzgVar;
        if (this.zzc) {
            return;
        }
        zzgVar = this.zza.zzb;
        context.registerReceiver(zzgVar, intentFilter);
        this.zzc = true;
    }

    public final void zzc(Context context) {
        zzg zzgVar;
        if (this.zzc) {
            zzgVar = this.zza.zzb;
            context.unregisterReceiver(zzgVar);
            this.zzc = false;
            return;
        }
        com.google.android.gms.internal.play_billing.zza.zzk("BillingBroadcastManager", "Receiver is not registered.");
    }
}
