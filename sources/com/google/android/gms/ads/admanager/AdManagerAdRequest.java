package com.google.android.gms.ads.admanager;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.internal.client.zzdr;
import com.google.android.gms.ads.query.AdInfo;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class AdManagerAdRequest extends AdRequest {

    /* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
    /* loaded from: classes2.dex */
    public static final class Builder extends AdRequest.Builder {
        public Builder addCategoryExclusion(String str) {
            this.zza.zzq(str);
            return this;
        }

        public Builder addCustomTargeting(String str, String str2) {
            this.zza.zzs(str, str2);
            return this;
        }

        @Override // com.google.android.gms.ads.AdRequest.Builder
        @Deprecated
        public final /* bridge */ /* synthetic */ AdRequest.Builder setAdInfo(AdInfo adInfo) {
            setAdInfo(adInfo);
            return this;
        }

        @Override // com.google.android.gms.ads.AdRequest.Builder
        public final /* bridge */ /* synthetic */ AdRequest.Builder setAdString(String str) {
            setAdString(str);
            return this;
        }

        public Builder setPublisherProvidedId(String str) {
            this.zza.zzG(str);
            return this;
        }

        public Builder addCustomTargeting(String str, List<String> list) {
            if (list != null) {
                this.zza.zzs(str, TextUtils.join(",", list));
            }
            return this;
        }

        @Override // com.google.android.gms.ads.AdRequest.Builder
        public AdManagerAdRequest build() {
            return new AdManagerAdRequest(this, null);
        }

        @Override // com.google.android.gms.ads.AdRequest.Builder
        @Deprecated
        public Builder setAdInfo(AdInfo adInfo) {
            this.zza.zzy(adInfo);
            return this;
        }

        @Override // com.google.android.gms.ads.AdRequest.Builder
        public Builder setAdString(String str) {
            this.zza.zzz(str);
            return this;
        }
    }

    /* synthetic */ AdManagerAdRequest(Builder builder, zza zzaVar) {
        super(builder);
    }

    @Override // com.google.android.gms.ads.AdRequest
    public Bundle getCustomTargeting() {
        return this.zza.zze();
    }

    public String getPublisherProvidedId() {
        return this.zza.zzm();
    }

    @Override // com.google.android.gms.ads.AdRequest
    public final zzdr zza() {
        return this.zza;
    }
}
