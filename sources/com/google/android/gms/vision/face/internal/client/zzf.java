package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zze();
    public int landmarkType;
    public int mode;
    public float proportionalMinFaceSize;
    public boolean trackingEnabled;
    public boolean zzcn;
    public int zzco;

    public zzf() {
    }

    public zzf(int r1, int r2, int r3, boolean z, boolean z2, float f) {
        this.mode = r1;
        this.landmarkType = r2;
        this.zzco = r3;
        this.zzcn = z;
        this.trackingEnabled = z2;
        this.proportionalMinFaceSize = f;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r4) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.mode);
        SafeParcelWriter.writeInt(parcel, 3, this.landmarkType);
        SafeParcelWriter.writeInt(parcel, 4, this.zzco);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzcn);
        SafeParcelWriter.writeBoolean(parcel, 6, this.trackingEnabled);
        SafeParcelWriter.writeFloat(parcel, 7, this.proportionalMinFaceSize);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
