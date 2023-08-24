package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaxf extends zzaxj {
    public static final Parcelable.Creator<zzaxf> CREATOR = new zzaxe();
    public final String zza;
    public final String zzb;
    public final int zzc;
    public final byte[] zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaxf(Parcel parcel) {
        super(ApicFrame.f224ID);
        this.zza = parcel.readString();
        this.zzb = parcel.readString();
        this.zzc = parcel.readInt();
        this.zzd = parcel.createByteArray();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzaxf zzaxfVar = (zzaxf) obj;
            if (this.zzc == zzaxfVar.zzc && zzban.zzo(this.zza, zzaxfVar.zza) && zzban.zzo(this.zzb, zzaxfVar.zzb) && Arrays.equals(this.zzd, zzaxfVar.zzd)) {
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

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r2) {
        parcel.writeString(this.zza);
        parcel.writeString(this.zzb);
        parcel.writeInt(this.zzc);
        parcel.writeByteArray(this.zzd);
    }

    public zzaxf(String str, String str2, int r3, byte[] bArr) {
        super(ApicFrame.f224ID);
        this.zza = str;
        this.zzb = null;
        this.zzc = 3;
        this.zzd = bArr;
    }
}
