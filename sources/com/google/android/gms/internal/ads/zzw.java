package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzw implements Parcelable {
    public static final Parcelable.Creator<zzw> CREATOR = new zzv();
    public final UUID zza;
    public final String zzb;
    public final String zzc;
    public final byte[] zzd;
    private int zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(Parcel parcel) {
        this.zza = new UUID(parcel.readLong(), parcel.readLong());
        this.zzb = parcel.readString();
        String readString = parcel.readString();
        int r1 = zzel.zza;
        this.zzc = readString;
        this.zzd = parcel.createByteArray();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzw) {
            if (obj == this) {
                return true;
            }
            zzw zzwVar = (zzw) obj;
            return zzel.zzT(this.zzb, zzwVar.zzb) && zzel.zzT(this.zzc, zzwVar.zzc) && zzel.zzT(this.zza, zzwVar.zza) && Arrays.equals(this.zzd, zzwVar.zzd);
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zze;
        if (r0 == 0) {
            int hashCode = this.zza.hashCode() * 31;
            String str = this.zzb;
            int hashCode2 = ((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.zzc.hashCode()) * 31) + Arrays.hashCode(this.zzd);
            this.zze = hashCode2;
            return hashCode2;
        }
        return r0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r4) {
        parcel.writeLong(this.zza.getMostSignificantBits());
        parcel.writeLong(this.zza.getLeastSignificantBits());
        parcel.writeString(this.zzb);
        parcel.writeString(this.zzc);
        parcel.writeByteArray(this.zzd);
    }

    public zzw(UUID r1, String str, String str2, byte[] bArr) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = null;
        this.zzc = str2;
        this.zzd = bArr;
    }
}
