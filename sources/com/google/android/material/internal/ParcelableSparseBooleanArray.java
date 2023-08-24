package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

/* loaded from: classes3.dex */
public class ParcelableSparseBooleanArray extends SparseBooleanArray implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseBooleanArray> CREATOR = new Parcelable.Creator<ParcelableSparseBooleanArray>() { // from class: com.google.android.material.internal.ParcelableSparseBooleanArray.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseBooleanArray createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            ParcelableSparseBooleanArray parcelableSparseBooleanArray = new ParcelableSparseBooleanArray(readInt);
            int[] r2 = new int[readInt];
            boolean[] zArr = new boolean[readInt];
            parcel.readIntArray(r2);
            parcel.readBooleanArray(zArr);
            for (int r7 = 0; r7 < readInt; r7++) {
                parcelableSparseBooleanArray.put(r2[r7], zArr[r7]);
            }
            return parcelableSparseBooleanArray;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseBooleanArray[] newArray(int r1) {
            return new ParcelableSparseBooleanArray[r1];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableSparseBooleanArray() {
    }

    public ParcelableSparseBooleanArray(int r1) {
        super(r1);
    }

    public ParcelableSparseBooleanArray(SparseBooleanArray sparseBooleanArray) {
        super(sparseBooleanArray.size());
        for (int r0 = 0; r0 < sparseBooleanArray.size(); r0++) {
            put(sparseBooleanArray.keyAt(r0), sparseBooleanArray.valueAt(r0));
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int r5) {
        int[] r52 = new int[size()];
        boolean[] zArr = new boolean[size()];
        for (int r1 = 0; r1 < size(); r1++) {
            r52[r1] = keyAt(r1);
            zArr[r1] = valueAt(r1);
        }
        parcel.writeInt(size());
        parcel.writeIntArray(r52);
        parcel.writeBooleanArray(zArr);
    }
}
