package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbq implements Parcelable {
    public static final Parcelable.Creator<zzbq> CREATOR = new zzbo();
    private final zzbp[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbq(Parcel parcel) {
        this.zza = new zzbp[parcel.readInt()];
        int r0 = 0;
        while (true) {
            zzbp[] zzbpVarArr = this.zza;
            if (r0 >= zzbpVarArr.length) {
                return;
            }
            zzbpVarArr[r0] = (zzbp) parcel.readParcelable(zzbp.class.getClassLoader());
            r0++;
        }
    }

    public zzbq(zzbp... zzbpVarArr) {
        this.zza = zzbpVarArr;
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
        return Arrays.equals(this.zza, ((zzbq) obj).zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        return "entries=".concat(String.valueOf(Arrays.toString(this.zza)));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r6) {
        parcel.writeInt(this.zza.length);
        for (zzbp zzbpVar : this.zza) {
            parcel.writeParcelable(zzbpVar, 0);
        }
    }

    public final int zza() {
        return this.zza.length;
    }

    public final zzbp zzb(int r2) {
        return this.zza[r2];
    }

    public final zzbq zzc(zzbp... zzbpVarArr) {
        return zzbpVarArr.length == 0 ? this : new zzbq((zzbp[]) zzel.zzad(this.zza, zzbpVarArr));
    }

    public final zzbq zzd(zzbq zzbqVar) {
        return zzbqVar == null ? this : zzc(zzbqVar.zza);
    }

    public zzbq(List list) {
        this.zza = (zzbp[]) list.toArray(new zzbp[0]);
    }
}
