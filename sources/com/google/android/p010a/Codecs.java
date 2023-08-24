package com.google.android.p010a;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.google.android.a.c */
/* loaded from: classes2.dex */
public final class Codecs {
    static {
        Codecs.class.getClassLoader();
    }

    private Codecs() {
    }

    /* renamed from: a */
    public static <T extends Parcelable> T m1238a(Parcel parcel, Parcelable.Creator<T> creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    /* renamed from: a */
    public static void m1237a(Parcel parcel, Parcelable parcelable) {
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }
}
