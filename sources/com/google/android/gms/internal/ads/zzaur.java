package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaur implements Comparator<zzauq>, Parcelable {
    public static final Parcelable.Creator<zzaur> CREATOR = new zzauo();
    public final int zza;
    private final zzauq[] zzb;
    private int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaur(Parcel parcel) {
        zzauq[] zzauqVarArr = (zzauq[]) parcel.createTypedArray(zzauq.CREATOR);
        this.zzb = zzauqVarArr;
        this.zza = zzauqVarArr.length;
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(zzauq zzauqVar, zzauq zzauqVar2) {
        UUID r1;
        UUID r3;
        UUID r4;
        UUID r42;
        zzauq zzauqVar3 = zzauqVar;
        zzauq zzauqVar4 = zzauqVar2;
        UUID r0 = zzasd.zzb;
        r1 = zzauqVar3.zze;
        if (r0.equals(r1)) {
            UUID r32 = zzasd.zzb;
            r42 = zzauqVar4.zze;
            return !r32.equals(r42) ? 1 : 0;
        }
        r3 = zzauqVar3.zze;
        r4 = zzauqVar4.zze;
        return r3.compareTo(r4);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.zzb, ((zzaur) obj).zzb);
    }

    public final int hashCode() {
        int r0 = this.zzc;
        if (r0 == 0) {
            int hashCode = Arrays.hashCode(this.zzb);
            this.zzc = hashCode;
            return hashCode;
        }
        return r0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r3) {
        parcel.writeTypedArray(this.zzb, 0);
    }

    public final zzauq zza(int r2) {
        return this.zzb[r2];
    }

    public zzaur(List list) {
        this(false, (zzauq[]) list.toArray(new zzauq[list.size()]));
    }

    private zzaur(boolean z, zzauq... zzauqVarArr) {
        UUID r0;
        UUID r1;
        UUID r3;
        zzauqVarArr = z ? (zzauq[]) zzauqVarArr.clone() : zzauqVarArr;
        Arrays.sort(zzauqVarArr, this);
        int r32 = 1;
        while (true) {
            int length = zzauqVarArr.length;
            if (r32 >= length) {
                this.zzb = zzauqVarArr;
                this.zza = length;
                return;
            }
            r0 = zzauqVarArr[r32 - 1].zze;
            r1 = zzauqVarArr[r32].zze;
            if (r0.equals(r1)) {
                r3 = zzauqVarArr[r32].zze;
                throw new IllegalArgumentException("Duplicate data for uuid: ".concat(String.valueOf(String.valueOf(r3))));
            }
            r32++;
        }
    }

    public zzaur(zzauq... zzauqVarArr) {
        this(true, zzauqVarArr);
    }
}
