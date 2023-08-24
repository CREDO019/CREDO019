package com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class CompactStringObjectMap implements Serializable {
    private static final CompactStringObjectMap EMPTY = new CompactStringObjectMap(1, 0, new Object[4]);
    private static final long serialVersionUID = 1;
    private final Object[] _hashArea;
    private final int _hashMask;
    private final int _spillCount;

    private static final int findSize(int r1) {
        if (r1 <= 5) {
            return 8;
        }
        if (r1 <= 12) {
            return 16;
        }
        int r0 = 32;
        while (r0 < r1 + (r1 >> 2)) {
            r0 += r0;
        }
        return r0;
    }

    private CompactStringObjectMap(int r1, int r2, Object[] objArr) {
        this._hashMask = r1;
        this._spillCount = r2;
        this._hashArea = objArr;
    }

    public static <T> CompactStringObjectMap construct(Map<String, T> map) {
        if (map.isEmpty()) {
            return EMPTY;
        }
        int findSize = findSize(map.size());
        int r1 = findSize - 1;
        int r2 = (findSize >> 1) + findSize;
        Object[] objArr = new Object[r2 * 2];
        int r4 = 0;
        for (Map.Entry<String, T> entry : map.entrySet()) {
            String key = entry.getKey();
            int hashCode = key.hashCode() & r1;
            int r8 = hashCode + hashCode;
            if (objArr[r8] != null) {
                r8 = ((hashCode >> 1) + findSize) << 1;
                if (objArr[r8] != null) {
                    r8 = (r2 << 1) + r4;
                    r4 += 2;
                    if (r8 >= objArr.length) {
                        objArr = Arrays.copyOf(objArr, objArr.length + 4);
                    }
                }
            }
            objArr[r8] = key;
            objArr[r8 + 1] = entry.getValue();
        }
        return new CompactStringObjectMap(r1, r4, objArr);
    }

    public Object find(String str) {
        int hashCode = str.hashCode() & this._hashMask;
        int r1 = hashCode << 1;
        Object obj = this._hashArea[r1];
        if (obj == str || str.equals(obj)) {
            return this._hashArea[r1 + 1];
        }
        return _find2(str, hashCode, obj);
    }

    private final Object _find2(String str, int r5, Object obj) {
        if (obj == null) {
            return null;
        }
        int r6 = this._hashMask + 1;
        int r52 = ((r5 >> 1) + r6) << 1;
        Object obj2 = this._hashArea[r52];
        if (str.equals(obj2)) {
            return this._hashArea[r52 + 1];
        }
        if (obj2 != null) {
            int r53 = (r6 + (r6 >> 1)) << 1;
            int r62 = this._spillCount + r53;
            while (r53 < r62) {
                Object obj3 = this._hashArea[r53];
                if (obj3 == str || str.equals(obj3)) {
                    return this._hashArea[r53 + 1];
                }
                r53 += 2;
            }
        }
        return null;
    }

    public List<String> keys() {
        int length = this._hashArea.length;
        ArrayList arrayList = new ArrayList(length >> 2);
        for (int r2 = 0; r2 < length; r2 += 2) {
            Object obj = this._hashArea[r2];
            if (obj != null) {
                arrayList.add((String) obj);
            }
        }
        return arrayList;
    }
}
