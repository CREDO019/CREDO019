package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzauq implements Parcelable {
    public static final Parcelable.Creator<zzauq> CREATOR = new zzaup();
    public final String zza;
    public final byte[] zzb;
    public final boolean zzc;
    private int zzd;
    private final UUID zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzauq(Parcel parcel) {
        this.zze = new UUID(parcel.readLong(), parcel.readLong());
        this.zza = parcel.readString();
        this.zzb = parcel.createByteArray();
        this.zzc = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzauq) {
            if (obj == this) {
                return true;
            }
            zzauq zzauqVar = (zzauq) obj;
            return this.zza.equals(zzauqVar.zza) && zzban.zzo(this.zze, zzauqVar.zze) && Arrays.equals(this.zzb, zzauqVar.zzb);
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzd;
        if (r0 == 0) {
            int hashCode = (((this.zze.hashCode() * 31) + this.zza.hashCode()) * 31) + Arrays.hashCode(this.zzb);
            this.zzd = hashCode;
            return hashCode;
        }
        return r0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r4) {
        parcel.writeLong(this.zze.getMostSignificantBits());
        parcel.writeLong(this.zze.getLeastSignificantBits());
        parcel.writeString(this.zza);
        parcel.writeByteArray(this.zzb);
        parcel.writeByte(this.zzc ? (byte) 1 : (byte) 0);
    }

    public zzauq(UUID r1, String str, byte[] bArr, boolean z) {
        Objects.requireNonNull(r1);
        this.zze = r1;
        this.zza = str;
        Objects.requireNonNull(bArr);
        this.zzb = bArr;
        this.zzc = false;
    }
}
