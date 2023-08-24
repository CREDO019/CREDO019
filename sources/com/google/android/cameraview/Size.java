package com.google.android.cameraview;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class Size implements Comparable<Size>, Parcelable {
    public static final Parcelable.Creator<Size> CREATOR = new Parcelable.Creator<Size>() { // from class: com.google.android.cameraview.Size.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Size createFromParcel(Parcel parcel) {
            return new Size(parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Size[] newArray(int r1) {
            return new Size[r1];
        }
    };
    private final int mHeight;
    private final int mWidth;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Size(int r1, int r2) {
        this.mWidth = r1;
        this.mHeight = r2;
    }

    public static Size parse(String str) {
        int indexOf = str.indexOf(120);
        if (indexOf == -1) {
            throw new IllegalArgumentException("Malformed size: " + str);
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Malformed size: " + str, e);
        }
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            return this.mWidth == size.mWidth && this.mHeight == size.mHeight;
        }
        return false;
    }

    public String toString() {
        return this.mWidth + "x" + this.mHeight;
    }

    public int hashCode() {
        int r0 = this.mHeight;
        int r1 = this.mWidth;
        return r0 ^ ((r1 >>> 16) | (r1 << 16));
    }

    @Override // java.lang.Comparable
    public int compareTo(Size size) {
        return (this.mWidth * this.mHeight) - (size.mWidth * size.mHeight);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int r2) {
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mHeight);
    }
}
