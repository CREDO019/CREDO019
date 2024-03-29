package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzl implements Parcelable.Creator<PolygonOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ PolygonOptions createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = null;
        float f = 0.0f;
        int r5 = 0;
        int r6 = 0;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int r11 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, LatLng.CREATOR);
                    break;
                case 3:
                    SafeParcelReader.readList(parcel, readHeader, arrayList, getClass().getClassLoader());
                    break;
                case 4:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 5:
                    r5 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 6:
                    r6 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 7:
                    f2 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 9:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 10:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 11:
                    r11 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 12:
                    arrayList3 = SafeParcelReader.createTypedList(parcel, readHeader, PatternItem.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new PolygonOptions(arrayList2, arrayList, f, r5, r6, f2, z, z2, z3, r11, arrayList3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PolygonOptions[] newArray(int r1) {
        return new PolygonOptions[r1];
    }
}
