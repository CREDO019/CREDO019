package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzacf implements zzbp {
    public static final Parcelable.Creator<zzacf> CREATOR = new zzace();
    public final int zza;
    public final String zzb;
    public final String zzc;
    public final int zzd;
    public final int zze;
    public final int zzf;
    public final int zzg;
    public final byte[] zzh;

    public zzacf(int r1, String str, String str2, int r4, int r5, int r6, int r7, byte[] bArr) {
        this.zza = r1;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzacf(Parcel parcel) {
        this.zza = parcel.readInt();
        String readString = parcel.readString();
        int r1 = zzel.zza;
        this.zzb = readString;
        this.zzc = parcel.readString();
        this.zzd = parcel.readInt();
        this.zze = parcel.readInt();
        this.zzf = parcel.readInt();
        this.zzg = parcel.readInt();
        this.zzh = (byte[]) zzel.zzH(parcel.createByteArray());
    }

    public static zzacf zzb(zzed zzedVar) {
        int zze = zzedVar.zze();
        String zzx = zzedVar.zzx(zzedVar.zze(), zzfrs.zza);
        String zzx2 = zzedVar.zzx(zzedVar.zze(), zzfrs.zzc);
        int zze2 = zzedVar.zze();
        int zze3 = zzedVar.zze();
        int zze4 = zzedVar.zze();
        int zze5 = zzedVar.zze();
        int zze6 = zzedVar.zze();
        byte[] bArr = new byte[zze6];
        zzedVar.zzB(bArr, 0, zze6);
        return new zzacf(zze, zzx, zzx2, zze2, zze3, zze4, zze5, bArr);
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
            zzacf zzacfVar = (zzacf) obj;
            if (this.zza == zzacfVar.zza && this.zzb.equals(zzacfVar.zzb) && this.zzc.equals(zzacfVar.zzc) && this.zzd == zzacfVar.zzd && this.zze == zzacfVar.zze && this.zzf == zzacfVar.zzf && this.zzg == zzacfVar.zzg && Arrays.equals(this.zzh, zzacfVar.zzh)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((this.zza + 527) * 31) + this.zzb.hashCode()) * 31) + this.zzc.hashCode()) * 31) + this.zzd) * 31) + this.zze) * 31) + this.zzf) * 31) + this.zzg) * 31) + Arrays.hashCode(this.zzh);
    }

    public final String toString() {
        String str = this.zzb;
        String str2 = this.zzc;
        return "Picture: mimeType=" + str + ", description=" + str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r2) {
        parcel.writeInt(this.zza);
        parcel.writeString(this.zzb);
        parcel.writeString(this.zzc);
        parcel.writeInt(this.zzd);
        parcel.writeInt(this.zze);
        parcel.writeInt(this.zzf);
        parcel.writeInt(this.zzg);
        parcel.writeByteArray(this.zzh);
    }

    @Override // com.google.android.gms.internal.ads.zzbp
    public final void zza(zzbk zzbkVar) {
        zzbkVar.zza(this.zzh, this.zza);
    }
}
