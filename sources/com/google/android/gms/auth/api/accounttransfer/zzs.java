package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class zzs implements Parcelable.Creator<zzr> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr[] newArray(int r1) {
        return new zzr[r1];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int r3 = 0;
        zzt zztVar = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                r3 = SafeParcelReader.readInt(parcel, readHeader);
                hashSet.add(1);
            } else if (fieldId == 2) {
                zztVar = (zzt) SafeParcelReader.createParcelable(parcel, readHeader, zzt.CREATOR);
                hashSet.add(2);
            } else if (fieldId == 3) {
                str = SafeParcelReader.createString(parcel, readHeader);
                hashSet.add(3);
            } else if (fieldId == 4) {
                str2 = SafeParcelReader.createString(parcel, readHeader);
                hashSet.add(4);
            } else if (fieldId == 5) {
                str3 = SafeParcelReader.createString(parcel, readHeader);
                hashSet.add(5);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        if (parcel.dataPosition() != validateObjectHeader) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Overread allowed size end=");
            sb.append(validateObjectHeader);
            throw new SafeParcelReader.ParseException(sb.toString(), parcel);
        }
        return new zzr(hashSet, r3, zztVar, str, str2, str3);
    }
}
