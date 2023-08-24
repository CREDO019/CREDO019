package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzab implements Parcelable.Creator<zzac> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzac[] newArray(int r1) {
        return new zzac[r1];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzac createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzaj[] zzajVarArr = null;
        zzw zzwVar = null;
        zzw zzwVar2 = null;
        zzw zzwVar3 = null;
        String str = null;
        String str2 = null;
        float f = 0.0f;
        int r13 = 0;
        boolean z = false;
        int r15 = 0;
        int r16 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    zzajVarArr = (zzaj[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzaj.CREATOR);
                    break;
                case 3:
                    zzwVar = (zzw) SafeParcelReader.createParcelable(parcel, readHeader, zzw.CREATOR);
                    break;
                case 4:
                    zzwVar2 = (zzw) SafeParcelReader.createParcelable(parcel, readHeader, zzw.CREATOR);
                    break;
                case 5:
                    zzwVar3 = (zzw) SafeParcelReader.createParcelable(parcel, readHeader, zzw.CREATOR);
                    break;
                case 6:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 8:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 9:
                    r13 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 10:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 11:
                    r15 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 12:
                    r16 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzac(zzajVarArr, zzwVar, zzwVar2, zzwVar3, str, f, str2, r13, z, r15, r16);
    }
}
