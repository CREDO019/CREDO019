package com.android.billingclient.api;

import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/* compiled from: com.android.billingclient:billing@@4.0.0 */
/* loaded from: classes.dex */
public class BillingFlowParams {
    public static final String EXTRA_PARAM_KEY_ACCOUNT_ID = "accountId";
    public static final String EXTRA_PARAM_KEY_OLD_SKUS = "skusToReplace";
    public static final String EXTRA_PARAM_KEY_OLD_SKU_PURCHASE_TOKEN = "oldSkuPurchaseToken";
    public static final String EXTRA_PARAM_KEY_REPLACE_SKUS_PRORATION_MODE = "prorationMode";
    public static final String EXTRA_PARAM_KEY_VR = "vr";
    private boolean zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private int zze = 0;
    private ArrayList<SkuDetails> zzf;
    private boolean zzg;

    /* compiled from: com.android.billingclient:billing@@4.0.0 */
    /* loaded from: classes.dex */
    public static class Builder {
        private String zza;
        private String zzb;
        private String zzc;
        private int zzd = 0;
        private ArrayList<SkuDetails> zze;
        private boolean zzf;

        private Builder() {
        }

        /* synthetic */ Builder(zzai zzaiVar) {
        }

        public BillingFlowParams build() {
            ArrayList<SkuDetails> arrayList = this.zze;
            if (arrayList == null || arrayList.isEmpty()) {
                throw new IllegalArgumentException("SkuDetails must be provided.");
            }
            ArrayList<SkuDetails> arrayList2 = this.zze;
            int size = arrayList2.size();
            int r3 = 0;
            while (r3 < size) {
                int r4 = r3 + 1;
                if (arrayList2.get(r3) == null) {
                    throw new IllegalArgumentException("SKU cannot be null.");
                }
                r3 = r4;
            }
            if (this.zze.size() > 1) {
                SkuDetails skuDetails = this.zze.get(0);
                String type = skuDetails.getType();
                ArrayList<SkuDetails> arrayList3 = this.zze;
                int size2 = arrayList3.size();
                for (int r6 = 0; r6 < size2; r6++) {
                    SkuDetails skuDetails2 = arrayList3.get(r6);
                    if (!type.equals("play_pass_subs") && !skuDetails2.getType().equals("play_pass_subs") && !type.equals(skuDetails2.getType())) {
                        throw new IllegalArgumentException("SKUs should have the same type.");
                    }
                }
                String zzc = skuDetails.zzc();
                ArrayList<SkuDetails> arrayList4 = this.zze;
                int size3 = arrayList4.size();
                for (int r62 = 0; r62 < size3; r62++) {
                    SkuDetails skuDetails3 = arrayList4.get(r62);
                    if (!type.equals("play_pass_subs") && !skuDetails3.getType().equals("play_pass_subs") && !zzc.equals(skuDetails3.zzc())) {
                        throw new IllegalArgumentException("All SKUs must have the same package name.");
                    }
                }
            }
            BillingFlowParams billingFlowParams = new BillingFlowParams(null);
            billingFlowParams.zza = true ^ this.zze.get(0).zzc().isEmpty();
            billingFlowParams.zzb = this.zza;
            billingFlowParams.zzd = this.zzc;
            billingFlowParams.zzc = this.zzb;
            billingFlowParams.zze = this.zzd;
            billingFlowParams.zzf = this.zze;
            billingFlowParams.zzg = this.zzf;
            return billingFlowParams;
        }

        public Builder setObfuscatedAccountId(String str) {
            this.zza = str;
            return this;
        }

        public Builder setObfuscatedProfileId(String str) {
            this.zzc = str;
            return this;
        }

        public Builder setSkuDetails(SkuDetails skuDetails) {
            ArrayList<SkuDetails> arrayList = new ArrayList<>();
            arrayList.add(skuDetails);
            this.zze = arrayList;
            return this;
        }

        public Builder setSubscriptionUpdateParams(SubscriptionUpdateParams subscriptionUpdateParams) {
            this.zzb = subscriptionUpdateParams.getOldSkuPurchaseToken();
            this.zzd = subscriptionUpdateParams.getReplaceSkusProrationMode();
            return this;
        }

        public Builder setVrPurchaseFlow(boolean z) {
            this.zzf = z;
            return this;
        }
    }

    /* compiled from: com.android.billingclient:billing@@4.0.0 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ProrationMode {
        public static final int DEFERRED = 4;
        public static final int IMMEDIATE_AND_CHARGE_FULL_PRICE = 5;
        public static final int IMMEDIATE_AND_CHARGE_PRORATED_PRICE = 2;
        public static final int IMMEDIATE_WITHOUT_PRORATION = 3;
        public static final int IMMEDIATE_WITH_TIME_PRORATION = 1;
        public static final int UNKNOWN_SUBSCRIPTION_UPGRADE_DOWNGRADE_POLICY = 0;
    }

    /* compiled from: com.android.billingclient:billing@@4.0.0 */
    /* loaded from: classes.dex */
    public static class SubscriptionUpdateParams {
        private String zza;
        private int zzb = 0;

        /* compiled from: com.android.billingclient:billing@@4.0.0 */
        /* loaded from: classes.dex */
        public static class Builder {
            private String zza;
            private int zzb = 0;

            private Builder() {
            }

            /* synthetic */ Builder(zzai zzaiVar) {
            }

            public SubscriptionUpdateParams build() {
                if (!TextUtils.isEmpty(this.zza) || !TextUtils.isEmpty(null)) {
                    SubscriptionUpdateParams subscriptionUpdateParams = new SubscriptionUpdateParams(null);
                    subscriptionUpdateParams.zza = this.zza;
                    subscriptionUpdateParams.zzb = this.zzb;
                    return subscriptionUpdateParams;
                }
                throw new IllegalArgumentException("Old SKU purchase token/id must be provided.");
            }

            public Builder setOldSkuPurchaseToken(String str) {
                this.zza = str;
                return this;
            }

            public Builder setReplaceSkusProrationMode(int r1) {
                this.zzb = r1;
                return this;
            }
        }

        private SubscriptionUpdateParams() {
        }

        /* synthetic */ SubscriptionUpdateParams(zzai zzaiVar) {
        }

        public static Builder newBuilder() {
            return new Builder(null);
        }

        String getOldSkuPurchaseToken() {
            return this.zza;
        }

        int getReplaceSkusProrationMode() {
            return this.zzb;
        }
    }

    private BillingFlowParams() {
    }

    /* synthetic */ BillingFlowParams(zzai zzaiVar) {
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public boolean getVrPurchaseFlow() {
        return this.zzg;
    }

    public final int zzb() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzb;
    }

    public final String zzg() {
        return this.zzd;
    }

    public final String zzh() {
        return this.zzc;
    }

    public final ArrayList<SkuDetails> zzj() {
        ArrayList<SkuDetails> arrayList = new ArrayList<>();
        arrayList.addAll(this.zzf);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzm() {
        return (!this.zzg && this.zzb == null && this.zzd == null && this.zze == 0 && !this.zza) ? false : true;
    }
}
