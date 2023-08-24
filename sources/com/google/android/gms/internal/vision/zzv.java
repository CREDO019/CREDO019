package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzv implements Parcelable.Creator<zzw> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzw[] newArray(int r1) {
        return new zzw[r1];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzw createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        float f = 0.0f;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                r4 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                r5 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 4) {
                r6 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 5) {
                r7 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 6) {
                f = SafeParcelReader.readFloat(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzw(r4, r5, r6, r7, f);
    }
}
