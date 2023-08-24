package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.vision.barcode.Barcode;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzd implements Parcelable.Creator<Barcode.CalendarDateTime> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.CalendarDateTime[] newArray(int r1) {
        return new Barcode.CalendarDateTime[r1];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.CalendarDateTime createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
        int r9 = 0;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
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
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Barcode.CalendarDateTime(r4, r5, r6, r7, r8, r9, z, str);
    }
}
