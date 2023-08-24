package com.google.android.exoplayer2.drm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes2.dex */
public final class DrmInitData implements Comparator<SchemeData>, Parcelable {
    public static final Parcelable.Creator<DrmInitData> CREATOR = new Parcelable.Creator<DrmInitData>() { // from class: com.google.android.exoplayer2.drm.DrmInitData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DrmInitData createFromParcel(Parcel parcel) {
            return new DrmInitData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DrmInitData[] newArray(int r1) {
            return new DrmInitData[r1];
        }
    };
    private int hashCode;
    public final int schemeDataCount;
    private final SchemeData[] schemeDatas;
    public final String schemeType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static DrmInitData createSessionCreationData(DrmInitData drmInitData, DrmInitData drmInitData2) {
        String str;
        SchemeData[] schemeDataArr;
        SchemeData[] schemeDataArr2;
        ArrayList arrayList = new ArrayList();
        if (drmInitData != null) {
            str = drmInitData.schemeType;
            for (SchemeData schemeData : drmInitData.schemeDatas) {
                if (schemeData.hasData()) {
                    arrayList.add(schemeData);
                }
            }
        } else {
            str = null;
        }
        if (drmInitData2 != null) {
            if (str == null) {
                str = drmInitData2.schemeType;
            }
            int size = arrayList.size();
            for (SchemeData schemeData2 : drmInitData2.schemeDatas) {
                if (schemeData2.hasData() && !containsSchemeDataWithUuid(arrayList, size, schemeData2.uuid)) {
                    arrayList.add(schemeData2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new DrmInitData(str, arrayList);
    }

    public DrmInitData(List<SchemeData> list) {
        this(null, false, (SchemeData[]) list.toArray(new SchemeData[0]));
    }

    public DrmInitData(String str, List<SchemeData> list) {
        this(str, false, (SchemeData[]) list.toArray(new SchemeData[0]));
    }

    public DrmInitData(SchemeData... schemeDataArr) {
        this((String) null, schemeDataArr);
    }

    public DrmInitData(String str, SchemeData... schemeDataArr) {
        this(str, true, schemeDataArr);
    }

    private DrmInitData(String str, boolean z, SchemeData... schemeDataArr) {
        this.schemeType = str;
        schemeDataArr = z ? (SchemeData[]) schemeDataArr.clone() : schemeDataArr;
        this.schemeDatas = schemeDataArr;
        this.schemeDataCount = schemeDataArr.length;
        Arrays.sort(schemeDataArr, this);
    }

    DrmInitData(Parcel parcel) {
        this.schemeType = parcel.readString();
        SchemeData[] schemeDataArr = (SchemeData[]) Util.castNonNull((SchemeData[]) parcel.createTypedArray(SchemeData.CREATOR));
        this.schemeDatas = schemeDataArr;
        this.schemeDataCount = schemeDataArr.length;
    }

    public SchemeData get(int r2) {
        return this.schemeDatas[r2];
    }

    public DrmInitData copyWithSchemeType(String str) {
        return Util.areEqual(this.schemeType, str) ? this : new DrmInitData(str, false, this.schemeDatas);
    }

    public DrmInitData merge(DrmInitData drmInitData) {
        String str;
        String str2 = this.schemeType;
        Assertions.checkState(str2 == null || (str = drmInitData.schemeType) == null || TextUtils.equals(str2, str));
        String str3 = this.schemeType;
        if (str3 == null) {
            str3 = drmInitData.schemeType;
        }
        return new DrmInitData(str3, (SchemeData[]) Util.nullSafeArrayConcatenation(this.schemeDatas, drmInitData.schemeDatas));
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.schemeType;
            this.hashCode = ((str == null ? 0 : str.hashCode()) * 31) + Arrays.hashCode(this.schemeDatas);
        }
        return this.hashCode;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DrmInitData drmInitData = (DrmInitData) obj;
        return Util.areEqual(this.schemeType, drmInitData.schemeType) && Arrays.equals(this.schemeDatas, drmInitData.schemeDatas);
    }

    @Override // java.util.Comparator
    public int compare(SchemeData schemeData, SchemeData schemeData2) {
        if (C1856C.UUID_NIL.equals(schemeData.uuid)) {
            return C1856C.UUID_NIL.equals(schemeData2.uuid) ? 0 : 1;
        }
        return schemeData.uuid.compareTo(schemeData2.uuid);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int r3) {
        parcel.writeString(this.schemeType);
        parcel.writeTypedArray(this.schemeDatas, 0);
    }

    private static boolean containsSchemeDataWithUuid(ArrayList<SchemeData> arrayList, int r4, UUID r5) {
        for (int r1 = 0; r1 < r4; r1++) {
            if (arrayList.get(r1).uuid.equals(r5)) {
                return true;
            }
        }
        return false;
    }

    /* loaded from: classes2.dex */
    public static final class SchemeData implements Parcelable {
        public static final Parcelable.Creator<SchemeData> CREATOR = new Parcelable.Creator<SchemeData>() { // from class: com.google.android.exoplayer2.drm.DrmInitData.SchemeData.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SchemeData createFromParcel(Parcel parcel) {
                return new SchemeData(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SchemeData[] newArray(int r1) {
                return new SchemeData[r1];
            }
        };
        public final byte[] data;
        private int hashCode;
        public final String licenseServerUrl;
        public final String mimeType;
        public final UUID uuid;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SchemeData(UUID r2, String str, byte[] bArr) {
            this(r2, null, str, bArr);
        }

        public SchemeData(UUID r1, String str, String str2, byte[] bArr) {
            this.uuid = (UUID) Assertions.checkNotNull(r1);
            this.licenseServerUrl = str;
            this.mimeType = (String) Assertions.checkNotNull(str2);
            this.data = bArr;
        }

        SchemeData(Parcel parcel) {
            this.uuid = new UUID(parcel.readLong(), parcel.readLong());
            this.licenseServerUrl = parcel.readString();
            this.mimeType = (String) Util.castNonNull(parcel.readString());
            this.data = parcel.createByteArray();
        }

        public boolean matches(UUID r3) {
            return C1856C.UUID_NIL.equals(this.uuid) || r3.equals(this.uuid);
        }

        public boolean canReplace(SchemeData schemeData) {
            return hasData() && !schemeData.hasData() && matches(schemeData.uuid);
        }

        public boolean hasData() {
            return this.data != null;
        }

        public SchemeData copyWithData(byte[] bArr) {
            return new SchemeData(this.uuid, this.licenseServerUrl, this.mimeType, bArr);
        }

        public boolean equals(Object obj) {
            if (obj instanceof SchemeData) {
                if (obj == this) {
                    return true;
                }
                SchemeData schemeData = (SchemeData) obj;
                return Util.areEqual(this.licenseServerUrl, schemeData.licenseServerUrl) && Util.areEqual(this.mimeType, schemeData.mimeType) && Util.areEqual(this.uuid, schemeData.uuid) && Arrays.equals(this.data, schemeData.data);
            }
            return false;
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                int hashCode = this.uuid.hashCode() * 31;
                String str = this.licenseServerUrl;
                this.hashCode = ((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.mimeType.hashCode()) * 31) + Arrays.hashCode(this.data);
            }
            return this.hashCode;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int r4) {
            parcel.writeLong(this.uuid.getMostSignificantBits());
            parcel.writeLong(this.uuid.getLeastSignificantBits());
            parcel.writeString(this.licenseServerUrl);
            parcel.writeString(this.mimeType);
            parcel.writeByteArray(this.data);
        }
    }
}
