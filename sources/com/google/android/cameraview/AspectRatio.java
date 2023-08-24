package com.google.android.cameraview;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.SparseArrayCompat;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* loaded from: classes2.dex */
public class AspectRatio implements Comparable<AspectRatio>, Parcelable {

    /* renamed from: mX */
    private final int f206mX;

    /* renamed from: mY */
    private final int f207mY;
    private static final SparseArrayCompat<SparseArrayCompat<AspectRatio>> sCache = new SparseArrayCompat<>(16);
    public static final Parcelable.Creator<AspectRatio> CREATOR = new Parcelable.Creator<AspectRatio>() { // from class: com.google.android.cameraview.AspectRatio.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AspectRatio createFromParcel(Parcel parcel) {
            return AspectRatio.m1236of(parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AspectRatio[] newArray(int r1) {
            return new AspectRatio[r1];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: of */
    public static AspectRatio m1236of(int r3, int r4) {
        int gcd = gcd(r3, r4);
        int r32 = r3 / gcd;
        int r42 = r4 / gcd;
        SparseArrayCompat<SparseArrayCompat<AspectRatio>> sparseArrayCompat = sCache;
        SparseArrayCompat<AspectRatio> sparseArrayCompat2 = sparseArrayCompat.get(r32);
        if (sparseArrayCompat2 == null) {
            AspectRatio aspectRatio = new AspectRatio(r32, r42);
            SparseArrayCompat<AspectRatio> sparseArrayCompat3 = new SparseArrayCompat<>();
            sparseArrayCompat3.put(r42, aspectRatio);
            sparseArrayCompat.put(r32, sparseArrayCompat3);
            return aspectRatio;
        }
        AspectRatio aspectRatio2 = sparseArrayCompat2.get(r42);
        if (aspectRatio2 == null) {
            AspectRatio aspectRatio3 = new AspectRatio(r32, r42);
            sparseArrayCompat2.put(r42, aspectRatio3);
            return aspectRatio3;
        }
        return aspectRatio2;
    }

    public static AspectRatio parse(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            throw new IllegalArgumentException("Malformed aspect ratio: " + str);
        }
        try {
            return m1236of(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Malformed aspect ratio: " + str, e);
        }
    }

    private AspectRatio(int r1, int r2) {
        this.f206mX = r1;
        this.f207mY = r2;
    }

    public int getX() {
        return this.f206mX;
    }

    public int getY() {
        return this.f207mY;
    }

    public boolean matches(Size size) {
        int gcd = gcd(size.getWidth(), size.getHeight());
        return this.f206mX == size.getWidth() / gcd && this.f207mY == size.getHeight() / gcd;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof AspectRatio) {
            AspectRatio aspectRatio = (AspectRatio) obj;
            return this.f206mX == aspectRatio.f206mX && this.f207mY == aspectRatio.f207mY;
        }
        return false;
    }

    public String toString() {
        return this.f206mX + ParameterizedMessage.ERROR_MSG_SEPARATOR + this.f207mY;
    }

    public float toFloat() {
        return this.f206mX / this.f207mY;
    }

    public int hashCode() {
        int r0 = this.f207mY;
        int r1 = this.f206mX;
        return r0 ^ ((r1 >>> 16) | (r1 << 16));
    }

    @Override // java.lang.Comparable
    public int compareTo(AspectRatio aspectRatio) {
        if (equals(aspectRatio)) {
            return 0;
        }
        return toFloat() - aspectRatio.toFloat() > 0.0f ? 1 : -1;
    }

    public AspectRatio inverse() {
        return m1236of(this.f207mY, this.f206mX);
    }

    private static int gcd(int r1, int r2) {
        while (true) {
            int r0 = r2;
            int r22 = r1;
            r1 = r0;
            if (r1 == 0) {
                return r22;
            }
            r2 = r22 % r1;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int r2) {
        parcel.writeInt(this.f206mX);
        parcel.writeInt(this.f207mY);
    }
}
