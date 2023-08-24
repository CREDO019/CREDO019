package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzacc implements zzbp {
    public static final Parcelable.Creator<zzacc> CREATOR;
    private static final zzaf zzf;
    private static final zzaf zzg;
    public final String zza;
    public final String zzb;
    public final long zzc;
    public final long zzd;
    public final byte[] zze;
    private int zzh;

    static {
        zzad zzadVar = new zzad();
        zzadVar.zzS(MimeTypes.APPLICATION_ID3);
        zzf = zzadVar.zzY();
        zzad zzadVar2 = new zzad();
        zzadVar2.zzS(MimeTypes.APPLICATION_SCTE35);
        zzg = zzadVar2.zzY();
        CREATOR = new zzacb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzacc(Parcel parcel) {
        String readString = parcel.readString();
        int r1 = zzel.zza;
        this.zza = readString;
        this.zzb = parcel.readString();
        this.zzc = parcel.readLong();
        this.zzd = parcel.readLong();
        this.zze = (byte[]) zzel.zzH(parcel.createByteArray());
    }

    public zzacc(String str, String str2, long j, long j2, byte[] bArr) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = j;
        this.zzd = j2;
        this.zze = bArr;
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
            zzacc zzaccVar = (zzacc) obj;
            if (this.zzc == zzaccVar.zzc && this.zzd == zzaccVar.zzd && zzel.zzT(this.zza, zzaccVar.zza) && zzel.zzT(this.zzb, zzaccVar.zzb) && Arrays.equals(this.zze, zzaccVar.zze)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzh;
        if (r0 == 0) {
            String str = this.zza;
            int hashCode = ((str != null ? str.hashCode() : 0) + 527) * 31;
            String str2 = this.zzb;
            int hashCode2 = str2 != null ? str2.hashCode() : 0;
            long j = this.zzc;
            long j2 = this.zzd;
            int hashCode3 = ((((((hashCode + hashCode2) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31) + Arrays.hashCode(this.zze);
            this.zzh = hashCode3;
            return hashCode3;
        }
        return r0;
    }

    public final String toString() {
        String str = this.zza;
        long j = this.zzd;
        long j2 = this.zzc;
        String str2 = this.zzb;
        return "EMSG: scheme=" + str + ", id=" + j + ", durationMs=" + j2 + ", value=" + str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r4) {
        parcel.writeString(this.zza);
        parcel.writeString(this.zzb);
        parcel.writeLong(this.zzc);
        parcel.writeLong(this.zzd);
        parcel.writeByteArray(this.zze);
    }

    @Override // com.google.android.gms.internal.ads.zzbp
    public final /* synthetic */ void zza(zzbk zzbkVar) {
    }
}
