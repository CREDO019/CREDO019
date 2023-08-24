package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes3.dex */
public final class zzcb implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
        int r9 = 0;
        boolean z = false;
        int r11 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    r3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    r4 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 3:
                    r5 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    r6 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    r7 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 6:
                    r8 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 7:
                    r9 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 9:
                    r11 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SleepClassifyEvent(r3, r4, r5, r6, r7, r8, r9, z, r11);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new SleepClassifyEvent[r1];
    }
}
