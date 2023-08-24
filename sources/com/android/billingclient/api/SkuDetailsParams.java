package com.android.billingclient.api;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.android.billingclient:billing@@4.0.0 */
/* loaded from: classes.dex */
public class SkuDetailsParams {
    private String zza;
    private List<String> zzb;

    /* compiled from: com.android.billingclient:billing@@4.0.0 */
    /* loaded from: classes.dex */
    public static class Builder {
        private String zza;
        private List<String> zzb;

        private Builder() {
        }

        /* synthetic */ Builder(zzan zzanVar) {
        }

        public SkuDetailsParams build() {
            if (this.zza != null) {
                if (this.zzb != null) {
                    SkuDetailsParams skuDetailsParams = new SkuDetailsParams();
                    skuDetailsParams.zza = this.zza;
                    skuDetailsParams.zzb = this.zzb;
                    return skuDetailsParams;
                }
                throw new IllegalArgumentException("SKU list or SkuWithOffer list must be set");
            }
            throw new IllegalArgumentException("SKU type must be set");
        }

        public Builder setSkusList(List<String> list) {
            this.zzb = new ArrayList(list);
            return this;
        }

        public Builder setType(String str) {
            this.zza = str;
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public String getSkuType() {
        return this.zza;
    }

    public List<String> getSkusList() {
        return this.zzb;
    }
}
