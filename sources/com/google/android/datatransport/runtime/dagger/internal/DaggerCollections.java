package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes2.dex */
public final class DaggerCollections {
    private static final int MAX_POWER_OF_TWO = 1073741824;

    private static int calculateInitialCapacity(int r1) {
        if (r1 < 3) {
            return r1 + 1;
        }
        if (r1 < 1073741824) {
            return (int) ((r1 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    private DaggerCollections() {
    }

    public static <T> List<T> presizedList(int r1) {
        if (r1 == 0) {
            return Collections.emptyList();
        }
        return new ArrayList(r1);
    }

    public static boolean hasDuplicates(List<?> list) {
        if (list.size() < 2) {
            return false;
        }
        return list.size() != new HashSet(list).size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> HashSet<T> newHashSetWithExpectedSize(int r1) {
        return new HashSet<>(calculateInitialCapacity(r1));
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int r1) {
        return new LinkedHashMap<>(calculateInitialCapacity(r1));
    }
}
