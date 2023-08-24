package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-auth@@19.2.0 */
/* loaded from: classes2.dex */
public final class zbe implements Parcelable.Creator<HintRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ HintRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        CredentialPickerConfig credentialPickerConfig = null;
        String[] strArr = null;
        String str = null;
        String str2 = null;
        int r4 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1000) {
                r4 = SafeParcelReader.readInt(parcel, readHeader);
            } else {
                switch (fieldId) {
                    case 1:
                        credentialPickerConfig = (CredentialPickerConfig) SafeParcelReader.createParcelable(parcel, readHeader, CredentialPickerConfig.CREATOR);
                        continue;
                    case 2:
                        z = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    case 3:
                        z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    case 4:
                        strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                        continue;
                    case 5:
                        z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    case 6:
                        str = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 7:
                        str2 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        continue;
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new HintRequest(r4, credentialPickerConfig, z, z2, strArr, z3, str, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ HintRequest[] newArray(int r1) {
        return new HintRequest[r1];
    }
}
