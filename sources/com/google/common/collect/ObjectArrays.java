package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class ObjectArrays {
    private ObjectArrays() {
    }

    public static <T> T[] newArray(Class<T> cls, int r1) {
        return (T[]) ((Object[]) Array.newInstance((Class<?>) cls, r1));
    }

    public static <T> T[] newArray(T[] tArr, int r1) {
        return (T[]) Platform.newArray(tArr, r1);
    }

    public static <T> T[] concat(T[] tArr, T[] tArr2, Class<T> cls) {
        T[] tArr3 = (T[]) newArray(cls, tArr.length + tArr2.length);
        System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
        System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
        return tArr3;
    }

    public static <T> T[] concat(@ParametricNullness T t, T[] tArr) {
        T[] tArr2 = (T[]) newArray(tArr, tArr.length + 1);
        tArr2[0] = t;
        System.arraycopy(tArr, 0, tArr2, 1, tArr.length);
        return tArr2;
    }

    public static <T> T[] concat(T[] tArr, @ParametricNullness T t) {
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, tArr.length + 1);
        tArr2[tArr.length] = t;
        return tArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T[] toArrayImpl(Collection<?> collection, T[] tArr) {
        int size = collection.size();
        if (tArr.length < size) {
            tArr = (T[]) newArray(tArr, size);
        }
        fillArray(collection, tArr);
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T[] toArrayImpl(Object[] objArr, int r3, int r4, T[] tArr) {
        Preconditions.checkPositionIndexes(r3, r3 + r4, objArr.length);
        if (tArr.length < r4) {
            tArr = (T[]) newArray(tArr, r4);
        } else if (tArr.length > r4) {
            tArr[r4] = null;
        }
        System.arraycopy(objArr, r3, tArr, 0, r4);
        return tArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] toArrayImpl(Collection<?> collection) {
        return fillArray(collection, new Object[collection.size()]);
    }

    static Object[] copyAsObjectArray(Object[] objArr, int r3, int r4) {
        Preconditions.checkPositionIndexes(r3, r3 + r4, objArr.length);
        if (r4 == 0) {
            return new Object[0];
        }
        Object[] objArr2 = new Object[r4];
        System.arraycopy(objArr, r3, objArr2, 0, r4);
        return objArr2;
    }

    private static Object[] fillArray(Iterable<?> iterable, Object[] objArr) {
        Iterator<?> it = iterable.iterator();
        int r0 = 0;
        while (it.hasNext()) {
            objArr[r0] = it.next();
            r0++;
        }
        return objArr;
    }

    static void swap(Object[] objArr, int r3, int r4) {
        Object obj = objArr[r3];
        objArr[r3] = objArr[r4];
        objArr[r4] = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] checkElementsNotNull(Object... objArr) {
        checkElementsNotNull(objArr, objArr.length);
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] checkElementsNotNull(Object[] objArr, int r3) {
        for (int r0 = 0; r0 < r3; r0++) {
            checkElementNotNull(objArr[r0], r0);
        }
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object checkElementNotNull(@CheckForNull Object obj, int r3) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(20);
        sb.append("at index ");
        sb.append(r3);
        throw new NullPointerException(sb.toString());
    }
}
