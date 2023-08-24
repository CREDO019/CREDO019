package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaxd implements Parcelable {
    public static final Parcelable.Creator<zzaxd> CREATOR = new zzaxb();
    private final zzaxc[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaxd(Parcel parcel) {
        this.zza = new zzaxc[parcel.readInt()];
        int r0 = 0;
        while (true) {
            zzaxc[] zzaxcVarArr = this.zza;
            if (r0 >= zzaxcVarArr.length) {
                return;
            }
            zzaxcVarArr[r0] = (zzaxc) parcel.readParcelable(zzaxc.class.getClassLoader());
            r0++;
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.zza, ((zzaxd) obj).zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r6) {
        parcel.writeInt(this.zza.length);
        for (zzaxc zzaxcVar : this.zza) {
            parcel.writeParcelable(zzaxcVar, 0);
        }
    }

    public final int zza() {
        return this.zza.length;
    }

    public final zzaxc zzb(int r2) {
        return this.zza[r2];
    }

    public zzaxd(List list) {
        zzaxc[] zzaxcVarArr = new zzaxc[list.size()];
        this.zza = zzaxcVarArr;
        list.toArray(zzaxcVarArr);
    }
}
