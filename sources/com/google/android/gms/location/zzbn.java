package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes3.dex */
public final class zzbn implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        zzbv[] zzbvVarArr = null;
        int r9 = 1000;
        int r10 = 1;
        int r11 = 1;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    r10 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    r11 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    r9 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    zzbvVarArr = (zzbv[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzbv.CREATOR);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new LocationAvailability(r9, r10, r11, j, zzbvVarArr, z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new LocationAvailability[r1];
    }
}
