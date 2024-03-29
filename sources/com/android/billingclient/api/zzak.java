package com.android.billingclient.api;

import com.android.billingclient.api.BillingResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@4.0.0 */
/* loaded from: classes.dex */
public final class zzak {
    static final BillingResult zza;
    static final BillingResult zzb;
    static final BillingResult zzc;
    static final BillingResult zzd;
    static final BillingResult zze;
    static final BillingResult zzf;
    static final BillingResult zzg;
    static final BillingResult zzh;
    static final BillingResult zzi;
    static final BillingResult zzj;
    static final BillingResult zzk;
    static final BillingResult zzl;
    static final BillingResult zzm;
    static final BillingResult zzn;
    static final BillingResult zzo;
    static final BillingResult zzp;
    static final BillingResult zzq;
    static final BillingResult zzr;
    static final BillingResult zzs;
    static final BillingResult zzt;
    static final BillingResult zzu;
    static final BillingResult zzv;

    static {
        BillingResult.Builder newBuilder = BillingResult.newBuilder();
        newBuilder.setResponseCode(3);
        newBuilder.setDebugMessage("Google Play In-app Billing API version is less than 3");
        zza = newBuilder.build();
        BillingResult.Builder newBuilder2 = BillingResult.newBuilder();
        newBuilder2.setResponseCode(3);
        newBuilder2.setDebugMessage("Google Play In-app Billing API version is less than 9");
        zzb = newBuilder2.build();
        BillingResult.Builder newBuilder3 = BillingResult.newBuilder();
        newBuilder3.setResponseCode(3);
        newBuilder3.setDebugMessage("Billing service unavailable on device.");
        zzc = newBuilder3.build();
        BillingResult.Builder newBuilder4 = BillingResult.newBuilder();
        newBuilder4.setResponseCode(5);
        newBuilder4.setDebugMessage("Client is already in the process of connecting to billing service.");
        zzd = newBuilder4.build();
        BillingResult.Builder newBuilder5 = BillingResult.newBuilder();
        newBuilder5.setResponseCode(3);
        newBuilder5.setDebugMessage("Play Store version installed does not support cross selling products.");
        zze = newBuilder5.build();
        BillingResult.Builder newBuilder6 = BillingResult.newBuilder();
        newBuilder6.setResponseCode(5);
        newBuilder6.setDebugMessage("The list of SKUs can't be empty.");
        zzf = newBuilder6.build();
        BillingResult.Builder newBuilder7 = BillingResult.newBuilder();
        newBuilder7.setResponseCode(5);
        newBuilder7.setDebugMessage("SKU type can't be empty.");
        zzg = newBuilder7.build();
        BillingResult.Builder newBuilder8 = BillingResult.newBuilder();
        newBuilder8.setResponseCode(-2);
        newBuilder8.setDebugMessage("Client does not support extra params.");
        zzh = newBuilder8.build();
        BillingResult.Builder newBuilder9 = BillingResult.newBuilder();
        newBuilder9.setResponseCode(-2);
        newBuilder9.setDebugMessage("Client does not support the feature.");
        zzi = newBuilder9.build();
        BillingResult.Builder newBuilder10 = BillingResult.newBuilder();
        newBuilder10.setResponseCode(-2);
        newBuilder10.setDebugMessage("Client does not support get purchase history.");
        zzj = newBuilder10.build();
        BillingResult.Builder newBuilder11 = BillingResult.newBuilder();
        newBuilder11.setResponseCode(5);
        newBuilder11.setDebugMessage("Invalid purchase token.");
        zzk = newBuilder11.build();
        BillingResult.Builder newBuilder12 = BillingResult.newBuilder();
        newBuilder12.setResponseCode(6);
        newBuilder12.setDebugMessage("An internal error occurred.");
        zzl = newBuilder12.build();
        BillingResult.Builder newBuilder13 = BillingResult.newBuilder();
        newBuilder13.setResponseCode(4);
        newBuilder13.setDebugMessage("Item is unavailable for purchase.");
        zzm = newBuilder13.build();
        BillingResult.Builder newBuilder14 = BillingResult.newBuilder();
        newBuilder14.setResponseCode(5);
        newBuilder14.setDebugMessage("SKU can't be null.");
        zzn = newBuilder14.build();
        BillingResult.Builder newBuilder15 = BillingResult.newBuilder();
        newBuilder15.setResponseCode(5);
        newBuilder15.setDebugMessage("SKU type can't be null.");
        zzo = newBuilder15.build();
        BillingResult.Builder newBuilder16 = BillingResult.newBuilder();
        newBuilder16.setResponseCode(0);
        zzp = newBuilder16.build();
        BillingResult.Builder newBuilder17 = BillingResult.newBuilder();
        newBuilder17.setResponseCode(-1);
        newBuilder17.setDebugMessage("Service connection is disconnected.");
        zzq = newBuilder17.build();
        BillingResult.Builder newBuilder18 = BillingResult.newBuilder();
        newBuilder18.setResponseCode(-3);
        newBuilder18.setDebugMessage("Timeout communicating with service.");
        zzr = newBuilder18.build();
        BillingResult.Builder newBuilder19 = BillingResult.newBuilder();
        newBuilder19.setResponseCode(-2);
        newBuilder19.setDebugMessage("Client doesn't support subscriptions.");
        zzs = newBuilder19.build();
        BillingResult.Builder newBuilder20 = BillingResult.newBuilder();
        newBuilder20.setResponseCode(-2);
        newBuilder20.setDebugMessage("Client doesn't support subscriptions update.");
        zzt = newBuilder20.build();
        BillingResult.Builder newBuilder21 = BillingResult.newBuilder();
        newBuilder21.setResponseCode(-2);
        newBuilder21.setDebugMessage("Client doesn't support multi-item purchases.");
        zzu = newBuilder21.build();
        BillingResult.Builder newBuilder22 = BillingResult.newBuilder();
        newBuilder22.setResponseCode(5);
        newBuilder22.setDebugMessage("Unknown feature");
        zzv = newBuilder22.build();
    }
}
