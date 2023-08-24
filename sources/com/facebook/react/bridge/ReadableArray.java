package com.facebook.react.bridge;

import java.util.ArrayList;

/* loaded from: classes.dex */
public interface ReadableArray {
    ReadableArray getArray(int r1);

    boolean getBoolean(int r1);

    double getDouble(int r1);

    Dynamic getDynamic(int r1);

    int getInt(int r1);

    ReadableMap getMap(int r1);

    String getString(int r1);

    ReadableType getType(int r1);

    boolean isNull(int r1);

    int size();

    ArrayList<Object> toArrayList();
}
