package kotlin.reflect.jvm.internal.impl.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: kotlin.reflect.jvm.internal.impl.utils.CollectionsKt */
/* loaded from: classes5.dex */
public final class collections {
    public static final <K> Map<K, Integer> mapToIndex(Iterable<? extends K> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int r1 = 0;
        for (K k : iterable) {
            linkedHashMap.put(k, Integer.valueOf(r1));
            r1++;
        }
        return linkedHashMap;
    }

    public static final <T> void addIfNotNull(Collection<T> collection, T t) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        if (t != null) {
            collection.add(t);
        }
    }

    public static final <K, V> HashMap<K, V> newHashMapWithExpectedSize(int r1) {
        return new HashMap<>(capacity(r1));
    }

    public static final <E> HashSet<E> newHashSetWithExpectedSize(int r1) {
        return new HashSet<>(capacity(r1));
    }

    public static final <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int r1) {
        return new LinkedHashSet<>(capacity(r1));
    }

    private static final int capacity(int r1) {
        if (r1 < 3) {
            return 3;
        }
        return r1 + (r1 / 3) + 1;
    }

    public static final <T> List<T> compact(ArrayList<T> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        int size = arrayList.size();
        if (size != 0) {
            if (size == 1) {
                return kotlin.collections.CollectionsKt.listOf(kotlin.collections.CollectionsKt.first((List<? extends Object>) arrayList));
            }
            arrayList.trimToSize();
            return arrayList;
        }
        return kotlin.collections.CollectionsKt.emptyList();
    }
}
