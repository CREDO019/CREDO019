package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzblo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzblo> CREATOR = new zzblp();
    public final int zza;
    public final boolean zzb;
    public final int zzc;
    public final boolean zzd;
    public final int zze;
    public final com.google.android.gms.ads.internal.client.zzff zzf;
    public final boolean zzg;
    public final int zzh;

    public zzblo(int r1, boolean z, int r3, boolean z2, int r5, com.google.android.gms.ads.internal.client.zzff zzffVar, boolean z3, int r8) {
        this.zza = r1;
        this.zzb = z;
        this.zzc = r3;
        this.zzd = z2;
        this.zze = r5;
        this.zzf = zzffVar;
        this.zzg = z3;
        this.zzh = r8;
    }

    public static NativeAdOptions zza(zzblo zzbloVar) {
        NativeAdOptions.Builder builder = new NativeAdOptions.Builder();
        if (zzbloVar == null) {
            return builder.build();
        }
        int r1 = zzbloVar.zza;
        if (r1 != 2) {
            if (r1 != 3) {
                if (r1 == 4) {
                    builder.setRequestCustomMuteThisAd(zzbloVar.zzg);
                    builder.setMediaAspectRatio(zzbloVar.zzh);
                }
                builder.setReturnUrlsForImageAssets(zzbloVar.zzb);
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
        builder.setRequestMultipleImages(zzbloVar.zzd);
        return builder.build();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r6) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzd);
        SafeParcelWriter.writeInt(parcel, 5, this.zze);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, r6, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzg);
        SafeParcelWriter.writeInt(parcel, 8, this.zzh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzblo(com.google.android.gms.ads.formats.NativeAdOptions nativeAdOptions) {
        this(4, nativeAdOptions.shouldReturnUrlsForImageAssets(), nativeAdOptions.getImageOrientation(), nativeAdOptions.shouldRequestMultipleImages(), nativeAdOptions.getAdChoicesPlacement(), nativeAdOptions.getVideoOptions() != null ? new com.google.android.gms.ads.internal.client.zzff(nativeAdOptions.getVideoOptions()) : null, nativeAdOptions.zza(), nativeAdOptions.getMediaAspectRatio());
    }
}
