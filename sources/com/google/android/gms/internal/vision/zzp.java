package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.vision.Frame;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzp> CREATOR = new zzo();
    public int height;

    /* renamed from: id */
    public int f266id;
    public int rotation;
    public int width;
    public long zzar;

    public zzp() {
    }

    public zzp(int r1, int r2, int r3, long j, int r6) {
        this.width = r1;
        this.height = r2;
        this.f266id = r3;
        this.zzar = j;
        this.rotation = r6;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r5) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.width);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.f266id);
        SafeParcelWriter.writeLong(parcel, 5, this.zzar);
        SafeParcelWriter.writeInt(parcel, 6, this.rotation);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static zzp zzc(Frame frame) {
        zzp zzpVar = new zzp();
        zzpVar.width = frame.getMetadata().getWidth();
        zzpVar.height = frame.getMetadata().getHeight();
        zzpVar.rotation = frame.getMetadata().getRotation();
        zzpVar.f266id = frame.getMetadata().getId();
        zzpVar.zzar = frame.getMetadata().getTimestampMillis();
        return zzpVar;
    }
}
