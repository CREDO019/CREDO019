package com.polidea.multiplatformbleadapter.utils;

import java.util.HashMap;

/* loaded from: classes3.dex */
public class IdGenerator {
    private static HashMap<IdGeneratorKey, Integer> idMap = new HashMap<>();
    private static int nextKey = 0;

    public static int getIdForKey(IdGeneratorKey idGeneratorKey) {
        Integer num = idMap.get(idGeneratorKey);
        if (num != null) {
            return num.intValue();
        }
        HashMap<IdGeneratorKey, Integer> hashMap = idMap;
        int r1 = nextKey + 1;
        nextKey = r1;
        hashMap.put(idGeneratorKey, Integer.valueOf(r1));
        return nextKey;
    }

    public static void clear() {
        idMap.clear();
        nextKey = 0;
    }
}
