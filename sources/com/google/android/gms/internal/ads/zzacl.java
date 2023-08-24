package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzacl extends zzada {
    public static final Parcelable.Creator<zzacl> CREATOR = new zzack();
    public final String zza;
    public final String zzb;
    public final int zzc;
    public final byte[] zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzacl(Parcel parcel) {
        super(ApicFrame.f224ID);
        String readString = parcel.readString();
        int r1 = zzel.zza;
        this.zza = readString;
        this.zzb = parcel.readString();
        this.zzc = parcel.readInt();
        this.zzd = (byte[]) zzel.zzH(parcel.createByteArray());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzacl zzaclVar = (zzacl) obj;
            if (this.zzc == zzaclVar.zzc && zzel.zzT(this.zza, zzaclVar.zza) && zzel.zzT(this.zzb, zzaclVar.zzb) && Arrays.equals(this.zzd, zzaclVar.zzd)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = (this.zzc + 527) * 31;
        String str = this.zza;
        int hashCode = (r0 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.zzb;
        return ((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Arrays.hashCode(this.zzd);
    }

    @Override // com.google.android.gms.internal.ads.zzada
    public final String toString() {
        String str = this.zzf;
        String str2 = this.zza;
        String str3 = this.zzb;
        return str + ": mimeType=" + str2 + ", description=" + str3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r2) {
        parcel.writeString(this.zza);
        parcel.writeString(this.zzb);
        parcel.writeInt(this.zzc);
        parcel.writeByteArray(this.zzd);
    }

    @Override // com.google.android.gms.internal.ads.zzada, com.google.android.gms.internal.ads.zzbp
    public final void zza(zzbk zzbkVar) {
        zzbkVar.zza(this.zzd, this.zzc);
    }

    public zzacl(String str, String str2, int r4, byte[] bArr) {
        super(ApicFrame.f224ID);
        this.zza = str;
        this.zzb = str2;
        this.zzc = r4;
        this.zzd = bArr;
    }
}
