package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@4.0.0 */
/* loaded from: classes.dex */
public final class zzh {
    private final Context zza;
    private final zzg zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(Context context, PurchasesUpdatedListener purchasesUpdatedListener) {
        this.zza = context;
        this.zzb = new zzg(this, purchasesUpdatedListener, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final PurchasesUpdatedListener zzb() {
        return zzg.zza(this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc() {
        this.zzb.zzc(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd() {
        this.zzb.zzb(this.zza, new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED"));
    }
}
