package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzadn implements zzbp {
    public static final Parcelable.Creator<zzadn> CREATOR = new zzadl();
    public final String zza;
    public final byte[] zzb;
    public final int zzc;
    public final int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzadn(Parcel parcel, zzadm zzadmVar) {
        String readString = parcel.readString();
        int r0 = zzel.zza;
        this.zza = readString;
        this.zzb = (byte[]) zzel.zzH(parcel.createByteArray());
        this.zzc = parcel.readInt();
        this.zzd = parcel.readInt();
    }

    public zzadn(String str, byte[] bArr, int r3, int r4) {
        this.zza = str;
        this.zzb = bArr;
        this.zzc = r3;
        this.zzd = r4;
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
            zzadn zzadnVar = (zzadn) obj;
            if (this.zza.equals(zzadnVar.zza) && Arrays.equals(this.zzb, zzadnVar.zzb) && this.zzc == zzadnVar.zzc && this.zzd == zzadnVar.zzd) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((this.zza.hashCode() + 527) * 31) + Arrays.hashCode(this.zzb)) * 31) + this.zzc) * 31) + this.zzd;
    }

    public final String toString() {
        return "mdta: key=".concat(String.valueOf(this.zza));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r2) {
        parcel.writeString(this.zza);
        parcel.writeByteArray(this.zzb);
        parcel.writeInt(this.zzc);
        parcel.writeInt(this.zzd);
    }

    @Override // com.google.android.gms.internal.ads.zzbp
    public final /* synthetic */ void zza(zzbk zzbkVar) {
    }
}
