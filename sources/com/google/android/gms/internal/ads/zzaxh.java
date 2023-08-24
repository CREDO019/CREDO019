package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaxh extends zzaxj {
    public static final Parcelable.Creator<zzaxh> CREATOR = new zzaxg();
    public final String zza;
    public final String zzb;
    public final String zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaxh(Parcel parcel) {
        super(CommentFrame.f227ID);
        this.zza = parcel.readString();
        this.zzb = parcel.readString();
        this.zzc = parcel.readString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzaxh zzaxhVar = (zzaxh) obj;
            if (zzban.zzo(this.zzb, zzaxhVar.zzb) && zzban.zzo(this.zza, zzaxhVar.zza) && zzban.zzo(this.zzc, zzaxhVar.zzc)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.zza;
        int hashCode = ((str != null ? str.hashCode() : 0) + 527) * 31;
        String str2 = this.zzb;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.zzc;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r2) {
        parcel.writeString(this.zze);
        parcel.writeString(this.zza);
        parcel.writeString(this.zzc);
    }

    public zzaxh(String str, String str2, String str3) {
        super(CommentFrame.f227ID);
        this.zza = C1856C.LANGUAGE_UNDETERMINED;
        this.zzb = str2;
        this.zzc = str3;
    }
}
