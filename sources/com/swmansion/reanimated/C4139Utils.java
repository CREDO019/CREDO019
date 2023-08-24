package com.swmansion.reanimated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.swmansion.reanimated.Utils */
/* loaded from: classes3.dex */
public class C4139Utils {
    protected static boolean isChromeDebugger = false;

    public static Map<String, Integer> processMapping(ReadableMap readableMap) {
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        HashMap hashMap = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            hashMap.put(nextKey, Integer.valueOf(readableMap.getInt(nextKey)));
        }
        return hashMap;
    }

    public static int[] processIntArray(ReadableArray readableArray) {
        int size = readableArray.size();
        int[] r1 = new int[size];
        for (int r2 = 0; r2 < size; r2++) {
            r1[r2] = readableArray.getInt(r2);
        }
        return r1;
    }
}
