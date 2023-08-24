package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzn implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundle = null;
        ArrayList<String> arrayList = null;
        String str = null;
        zzfb zzfbVar = null;
        Location location = null;
        String str2 = null;
        Bundle bundle2 = null;
        Bundle bundle3 = null;
        ArrayList<String> arrayList2 = null;
        String str3 = null;
        String str4 = null;
        zzc zzcVar = null;
        String str5 = null;
        ArrayList<String> arrayList3 = null;
        String str6 = null;
        long j = 0;
        int r7 = 0;
        int r11 = 0;
        boolean z = false;
        int r14 = 0;
        boolean z2 = false;
        boolean z3 = false;
        int r27 = 0;
        int r30 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    r7 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 3:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 4:
                    r11 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    arrayList = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 7:
                    r14 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 8:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 9:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 10:
                    zzfbVar = (zzfb) SafeParcelReader.createParcelable(parcel, readHeader, zzfb.CREATOR);
                    break;
                case 11:
                    location = (Location) SafeParcelReader.createParcelable(parcel, readHeader, Location.CREATOR);
                    break;
                case 12:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 13:
                    bundle2 = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 14:
                    bundle3 = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 15:
                    arrayList2 = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 16:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 17:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 18:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 19:
                    zzcVar = (zzc) SafeParcelReader.createParcelable(parcel, readHeader, zzc.CREATOR);
                    break;
                case 20:
                    r27 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 21:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 22:
                    arrayList3 = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 23:
                    r30 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 24:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzl(r7, j, bundle, r11, arrayList, z, r14, z2, str, zzfbVar, location, str2, bundle2, bundle3, arrayList2, str3, str4, z3, zzcVar, r27, str5, arrayList3, r30, str6);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzl[r1];
    }
}
