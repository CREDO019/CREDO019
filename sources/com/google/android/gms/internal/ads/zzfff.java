package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzfff extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfff> CREATOR = new zzffg();
    @Nullable
    public final Context zza;
    public final zzffc zzb;
    public final int zzc;
    public final int zzd;
    public final int zze;
    public final String zzf;
    public final int zzg;
    private final zzffc[] zzh;
    private final int zzi;
    private final int zzj;
    private final int zzk;
    private final int[] zzl;
    private final int[] zzm;

    public zzfff(int r5, int r6, int r7, int r8, String str, int r10, int r11) {
        zzffc[] values = zzffc.values();
        this.zzh = values;
        int[] zza = zzffd.zza();
        this.zzl = zza;
        int[] zza2 = zzffe.zza();
        this.zzm = zza2;
        this.zza = null;
        this.zzi = r5;
        this.zzb = values[r5];
        this.zzc = r6;
        this.zzd = r7;
        this.zze = r8;
        this.zzf = str;
        this.zzj = r10;
        this.zzg = zza[r10];
        this.zzk = r11;
        int r52 = zza2[r11];
    }

    @Nullable
    public static zzfff zza(zzffc zzffcVar, Context context) {
        if (zzffcVar == zzffc.Rewarded) {
            return new zzfff(context, zzffcVar, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzft)).intValue(), ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfz)).intValue(), ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfB)).intValue(), (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfD), (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfv), (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfx));
        } else if (zzffcVar == zzffc.Interstitial) {
            return new zzfff(context, zzffcVar, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfu)).intValue(), ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfA)).intValue(), ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfC)).intValue(), (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfE), (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfw), (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfy));
        } else if (zzffcVar == zzffc.AppOpen) {
            return new zzfff(context, zzffcVar, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfH)).intValue(), ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfJ)).intValue(), ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfK)).intValue(), (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfF), (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfG), (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfI));
        } else {
            return null;
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r5) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzi);
        SafeParcelWriter.writeInt(parcel, 2, this.zzc);
        SafeParcelWriter.writeInt(parcel, 3, this.zzd);
        SafeParcelWriter.writeInt(parcel, 4, this.zze);
        SafeParcelWriter.writeString(parcel, 5, this.zzf, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzj);
        SafeParcelWriter.writeInt(parcel, 7, this.zzk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private zzfff(@Nullable Context context, zzffc zzffcVar, int r4, int r5, int r6, String str, String str2, String str3) {
        this.zzh = zzffc.values();
        this.zzl = zzffd.zza();
        this.zzm = zzffe.zza();
        this.zza = context;
        this.zzi = zzffcVar.ordinal();
        this.zzb = zzffcVar;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = str;
        int r3 = 2;
        if ("oldest".equals(str2)) {
            r3 = 1;
        } else if (!"lru".equals(str2) && "lfu".equals(str2)) {
            r3 = 3;
        }
        this.zzg = r3;
        this.zzj = r3 - 1;
        "onAdClosed".equals(str3);
        this.zzk = 0;
    }
}
