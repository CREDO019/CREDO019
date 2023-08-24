package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzacj implements zzbp {
    public static final Parcelable.Creator<zzacj> CREATOR = new zzaci();
    public final int zza;
    public final String zzb;
    public final String zzc;
    public final String zzd;
    public final boolean zze;
    public final int zzf;

    public zzacj(int r3, String str, String str2, String str3, boolean z, int r8) {
        boolean z2 = true;
        if (r8 != -1 && r8 <= 0) {
            z2 = false;
        }
        zzdd.zzd(z2);
        this.zza = r3;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = z;
        this.zzf = r8;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzacj zzacjVar = (zzacj) obj;
            if (this.zza == zzacjVar.zza && zzel.zzT(this.zzb, zzacjVar.zzb) && zzel.zzT(this.zzc, zzacjVar.zzc) && zzel.zzT(this.zzd, zzacjVar.zzd) && this.zze == zzacjVar.zze && this.zzf == zzacjVar.zzf) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = (this.zza + 527) * 31;
        String str = this.zzb;
        int hashCode = (r0 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.zzc;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.zzd;
        return ((((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + (this.zze ? 1 : 0)) * 31) + this.zzf;
    }

    public final String toString() {
        String str = this.zzc;
        String str2 = this.zzb;
        int r2 = this.zza;
        int r3 = this.zzf;
        return "IcyHeaders: name=\"" + str + "\", genre=\"" + str2 + "\", bitrate=" + r2 + ", metadataInterval=" + r3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r2) {
        parcel.writeInt(this.zza);
        parcel.writeString(this.zzb);
        parcel.writeString(this.zzc);
        parcel.writeString(this.zzd);
        zzel.zzS(parcel, this.zze);
        parcel.writeInt(this.zzf);
    }

    @Override // com.google.android.gms.internal.ads.zzbp
    public final void zza(zzbk zzbkVar) {
        String str = this.zzc;
        if (str != null) {
            zzbkVar.zzq(str);
        }
        String str2 = this.zzb;
        if (str2 != null) {
            zzbkVar.zzj(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzacj(Parcel parcel) {
        this.zza = parcel.readInt();
        this.zzb = parcel.readString();
        this.zzc = parcel.readString();
        this.zzd = parcel.readString();
        this.zze = zzel.zzZ(parcel);
        this.zzf = parcel.readInt();
    }
}
