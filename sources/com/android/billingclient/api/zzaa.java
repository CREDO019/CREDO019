package com.android.billingclient.api;

import com.android.billingclient.api.Purchase;
import java.util.concurrent.Callable;

/* compiled from: com.android.billingclient:billing@@4.0.0 */
/* loaded from: classes.dex */
final class zzaa implements Callable<Void> {
    final /* synthetic */ String zza;
    final /* synthetic */ PurchasesResponseListener zzb;
    final /* synthetic */ BillingClientImpl zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(BillingClientImpl billingClientImpl, String str, PurchasesResponseListener purchasesResponseListener) {
        this.zzc = billingClientImpl;
        this.zza = str;
        this.zzb = purchasesResponseListener;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Void call() throws Exception {
        Purchase.PurchasesResult zzk = BillingClientImpl.zzk(this.zzc, this.zza);
        if (zzk.getPurchasesList() != null) {
            this.zzb.onQueryPurchasesResponse(zzk.getBillingResult(), zzk.getPurchasesList());
            return null;
        }
        this.zzb.onQueryPurchasesResponse(zzk.getBillingResult(), com.google.android.gms.internal.play_billing.zzp.zzg());
        return null;
    }
}
