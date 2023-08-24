package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
public final class zae implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Long l = null;
        Long l2 = null;
        int r4 = 0;
        int r5 = 0;
        int r8 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                r4 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 2) {
                r5 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                l = SafeParcelReader.readLongObject(parcel, readHeader);
            } else if (fieldId == 4) {
                l2 = SafeParcelReader.readLongObject(parcel, readHeader);
            } else if (fieldId == 5) {
                r8 = SafeParcelReader.readInt(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ModuleInstallStatusUpdate(r4, r5, l, l2, r8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new ModuleInstallStatusUpdate[r1];
    }
}
