package com.reactnativecommunity.picker;

/* loaded from: classes3.dex */
public class ReactPickerLocalData {
    private final int height;

    public ReactPickerLocalData(int r1) {
        this.height = r1;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.height == ((ReactPickerLocalData) obj).height;
    }

    public int hashCode() {
        return this.height + 31;
    }

    public String toString() {
        return "RectPickerLocalData{height=" + this.height + '}';
    }
}
