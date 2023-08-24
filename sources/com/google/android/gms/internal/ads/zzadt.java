package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzadt implements zzbp {
    public static final Parcelable.Creator<zzadt> CREATOR = new zzadr();
    public final float zza;
    public final int zzb;

    public zzadt(float f, int r2) {
        this.zza = f;
        this.zzb = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzadt(Parcel parcel, zzads zzadsVar) {
        this.zza = parcel.readFloat();
        this.zzb = parcel.readInt();
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
            zzadt zzadtVar = (zzadt) obj;
            if (this.zza == zzadtVar.zza && this.zzb == zzadtVar.zzb) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((Float.valueOf(this.zza).hashCode() + 527) * 31) + this.zzb;
    }

    public final String toString() {
        float f = this.zza;
        int r1 = this.zzb;
        return "smta: captureFrameRate=" + f + ", svcTemporalLayerCount=" + r1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r2) {
        parcel.writeFloat(this.zza);
        parcel.writeInt(this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzbp
    public final /* synthetic */ void zza(zzbk zzbkVar) {
    }
}
