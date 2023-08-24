package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

/* loaded from: classes3.dex */
public class ParcelableSparseArray extends SparseArray<Parcelable> implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseArray> CREATOR = new Parcelable.ClassLoaderCreator<ParcelableSparseArray>() { // from class: com.google.android.material.internal.ParcelableSparseArray.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public ParcelableSparseArray createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new ParcelableSparseArray(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableSparseArray createFromParcel(Parcel parcel) {
            return new ParcelableSparseArray(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableSparseArray[] newArray(int r1) {
            return new ParcelableSparseArray[r1];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableSparseArray() {
    }

    public ParcelableSparseArray(Parcel parcel, ClassLoader classLoader) {
        int readInt = parcel.readInt();
        int[] r1 = new int[readInt];
        parcel.readIntArray(r1);
        Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
        for (int r6 = 0; r6 < readInt; r6++) {
            put(r1[r6], readParcelableArray[r6]);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int r7) {
        int size = size();
        int[] r1 = new int[size];
        Parcelable[] parcelableArr = new Parcelable[size];
        for (int r3 = 0; r3 < size; r3++) {
            r1[r3] = keyAt(r3);
            parcelableArr[r3] = valueAt(r3);
        }
        parcel.writeInt(size);
        parcel.writeIntArray(r1);
        parcel.writeParcelableArray(parcelableArr, r7);
    }
}
