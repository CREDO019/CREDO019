package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

/* loaded from: classes3.dex */
public class ParcelableSparseIntArray extends SparseIntArray implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseIntArray> CREATOR = new Parcelable.Creator<ParcelableSparseIntArray>() { // from class: com.google.android.material.internal.ParcelableSparseIntArray.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseIntArray createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            ParcelableSparseIntArray parcelableSparseIntArray = new ParcelableSparseIntArray(readInt);
            int[] r2 = new int[readInt];
            int[] r3 = new int[readInt];
            parcel.readIntArray(r2);
            parcel.readIntArray(r3);
            for (int r7 = 0; r7 < readInt; r7++) {
                parcelableSparseIntArray.put(r2[r7], r3[r7]);
            }
            return parcelableSparseIntArray;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseIntArray[] newArray(int r1) {
            return new ParcelableSparseIntArray[r1];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableSparseIntArray() {
    }

    public ParcelableSparseIntArray(int r1) {
        super(r1);
    }

    public ParcelableSparseIntArray(SparseIntArray sparseIntArray) {
        for (int r0 = 0; r0 < sparseIntArray.size(); r0++) {
            put(sparseIntArray.keyAt(r0), sparseIntArray.valueAt(r0));
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int r5) {
        int[] r52 = new int[size()];
        int[] r0 = new int[size()];
        for (int r1 = 0; r1 < size(); r1++) {
            r52[r1] = keyAt(r1);
            r0[r1] = valueAt(r1);
        }
        parcel.writeInt(size());
        parcel.writeIntArray(r52);
        parcel.writeIntArray(r0);
    }
}
