package com.polidea.reactnativeble.utils;

import com.facebook.react.bridge.ReadableArray;

/* loaded from: classes3.dex */
public class ReadableArrayConverter {
    public static String[] toStringArray(ReadableArray readableArray) {
        String[] strArr = new String[readableArray.size()];
        for (int r1 = 0; r1 < readableArray.size(); r1++) {
            strArr[r1] = readableArray.getString(r1);
        }
        return strArr;
    }
}
