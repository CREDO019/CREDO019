package com.google.android.gms.internal.ads;

import android.location.Location;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.hermes.intl.Constants;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbwi implements NativeMediationAdRequest {
    private final Date zza;
    private final int zzb;
    private final Set zzc;
    private final boolean zzd;
    private final Location zze;
    private final int zzf;
    private final zzblo zzg;
    private final boolean zzi;
    private final String zzk;
    private final List zzh = new ArrayList();
    private final Map zzj = new HashMap();

    public zzbwi(Date date, int r2, Set set, Location location, boolean z, int r6, zzblo zzbloVar, List list, boolean z2, int r10, String str) {
        this.zza = date;
        this.zzb = r2;
        this.zzc = set;
        this.zze = location;
        this.zzd = z;
        this.zzf = r6;
        this.zzg = zzbloVar;
        this.zzi = z2;
        this.zzk = str;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                if (str2.startsWith("custom:")) {
                    String[] split = str2.split(ParameterizedMessage.ERROR_MSG_SEPARATOR, 3);
                    if (split.length == 3) {
                        if ("true".equals(split[2])) {
                            this.zzj.put(split[1], true);
                        } else if (Constants.CASEFIRST_FALSE.equals(split[2])) {
                            this.zzj.put(split[1], false);
                        }
                    }
                } else {
                    this.zzh.add(str2);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final float getAdVolume() {
        return com.google.android.gms.ads.internal.client.zzed.zzf().zza();
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final Date getBirthday() {
        return this.zza;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final int getGender() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Set<String> getKeywords() {
        return this.zzc;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Location getLocation() {
        return this.zze;
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final NativeAdOptions getNativeAdOptions() {
        zzblo zzbloVar = this.zzg;
        NativeAdOptions.Builder builder = new NativeAdOptions.Builder();
        if (zzbloVar == null) {
            return builder.build();
        }
        int r2 = zzbloVar.zza;
        if (r2 != 2) {
            if (r2 != 3) {
                if (r2 == 4) {
                    builder.setRequestCustomMuteThisAd(zzbloVar.zzg);
                    builder.setMediaAspectRatio(zzbloVar.zzh);
                }
                builder.setReturnUrlsForImageAssets(zzbloVar.zzb);
                builder.setImageOrientation(zzbloVar.zzc);
                builder.setRequestMultipleImages(zzbloVar.zzd);
                return builder.build();
            }
            com.google.android.gms.ads.internal.client.zzff zzffVar = zzbloVar.zzf;
            if (zzffVar != null) {
                builder.setVideoOptions(new VideoOptions(zzffVar));
            }
        }
        builder.setAdChoicesPlacement(zzbloVar.zze);
        builder.setReturnUrlsForImageAssets(zzbloVar.zzb);
        builder.setImageOrientation(zzbloVar.zzc);
        builder.setRequestMultipleImages(zzbloVar.zzd);
        return builder.build();
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final com.google.android.gms.ads.nativead.NativeAdOptions getNativeAdRequestOptions() {
        return zzblo.zza(this.zzg);
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isAdMuted() {
        return com.google.android.gms.ads.internal.client.zzed.zzf().zzv();
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzi;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final boolean isTesting() {
        return this.zzd;
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isUnifiedNativeAdRequested() {
        return this.zzh.contains("6");
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final int taggedForChildDirectedTreatment() {
        return this.zzf;
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final Map zza() {
        return this.zzj;
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean zzb() {
        return this.zzh.contains(ExifInterface.GPS_MEASUREMENT_3D);
    }
}
