package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes3.dex */
public final class zzce extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzce> CREATOR = new zzcf();
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;

    public zzce(int r7, int r8, int r9, int r10) {
        Preconditions.checkState(r7 >= 0 && r7 <= 23, "Start hour must be in range [0, 23].");
        Preconditions.checkState(r8 >= 0 && r8 <= 59, "Start minute must be in range [0, 59].");
        Preconditions.checkState(r9 >= 0 && r9 <= 23, "End hour must be in range [0, 23].");
        Preconditions.checkState(r10 >= 0 && r10 <= 59, "End minute must be in range [0, 59].");
        Preconditions.checkState(((r7 + r8) + r9) + r10 > 0, "Parameters can't be all 0.");
        this.zza = r7;
        this.zzb = r8;
        this.zzc = r9;
        this.zzd = r10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzce) {
            zzce zzceVar = (zzce) obj;
            return this.zza == zzceVar.zza && this.zzb == zzceVar.zzb && this.zzc == zzceVar.zzc && this.zzd == zzceVar.zzd;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzd));
    }

    public final String toString() {
        int r0 = this.zza;
        int r1 = this.zzb;
        int r2 = this.zzc;
        int r3 = this.zzd;
        StringBuilder sb = new StringBuilder(117);
        sb.append("UserPreferredSleepWindow [startHour=");
        sb.append(r0);
        sb.append(", startMinute=");
        sb.append(r1);
        sb.append(", endHour=");
        sb.append(r2);
        sb.append(", endMinute=");
        sb.append(r3);
        sb.append(']');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r4) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
