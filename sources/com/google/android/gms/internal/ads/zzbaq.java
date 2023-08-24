package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbaq implements Parcelable {
    public static final Parcelable.Creator<zzbaq> CREATOR = new zzbap();
    public final int zza;
    public final int zzb;
    public final int zzc;
    public final byte[] zzd;
    private int zze;

    public zzbaq(int r1, int r2, int r3, byte[] bArr) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbaq(Parcel parcel) {
        this.zza = parcel.readInt();
        this.zzb = parcel.readInt();
        this.zzc = parcel.readInt();
        this.zzd = parcel.readInt() != 0 ? parcel.createByteArray() : null;
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
            zzbaq zzbaqVar = (zzbaq) obj;
            if (this.zza == zzbaqVar.zza && this.zzb == zzbaqVar.zzb && this.zzc == zzbaqVar.zzc && Arrays.equals(this.zzd, zzbaqVar.zzd)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zze;
        if (r0 == 0) {
            int hashCode = ((((((this.zza + 527) * 31) + this.zzb) * 31) + this.zzc) * 31) + Arrays.hashCode(this.zzd);
            this.zze = hashCode;
            return hashCode;
        }
        return r0;
    }

    public final String toString() {
        int r0 = this.zza;
        int r1 = this.zzb;
        int r2 = this.zzc;
        boolean z = this.zzd != null;
        return "ColorInfo(" + r0 + ", " + r1 + ", " + r2 + ", " + z + ")";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r2) {
        parcel.writeInt(this.zza);
        parcel.writeInt(this.zzb);
        parcel.writeInt(this.zzc);
        parcel.writeInt(this.zzd != null ? 1 : 0);
        byte[] bArr = this.zzd;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
    }
}
