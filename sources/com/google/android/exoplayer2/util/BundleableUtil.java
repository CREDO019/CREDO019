package com.google.android.exoplayer2.util;

import android.os.Bundle;
import android.util.SparseArray;
import com.google.android.exoplayer2.Bundleable;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public final class BundleableUtil {
    public static <T extends Bundleable> ImmutableList<Bundle> toBundleList(List<T> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int r1 = 0; r1 < list.size(); r1++) {
            builder.add((ImmutableList.Builder) list.get(r1).toBundle());
        }
        return builder.build();
    }

    public static <T extends Bundleable> ImmutableList<T> fromBundleList(Bundleable.Creator<T> creator, List<Bundle> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int r1 = 0; r1 < list.size(); r1++) {
            builder.add((ImmutableList.Builder) creator.fromBundle((Bundle) Assertions.checkNotNull(list.get(r1))));
        }
        return builder.build();
    }

    public static <T extends Bundleable> ArrayList<Bundle> toBundleArrayList(Collection<T> collection) {
        ArrayList<Bundle> arrayList = new ArrayList<>(collection.size());
        for (T t : collection) {
            arrayList.add(t.toBundle());
        }
        return arrayList;
    }

    public static <T extends Bundleable> SparseArray<T> fromBundleSparseArray(Bundleable.Creator<T> creator, SparseArray<Bundle> sparseArray) {
        SparseArray<T> sparseArray2 = new SparseArray<>(sparseArray.size());
        for (int r1 = 0; r1 < sparseArray.size(); r1++) {
            sparseArray2.put(sparseArray.keyAt(r1), creator.fromBundle(sparseArray.valueAt(r1)));
        }
        return sparseArray2;
    }

    public static <T extends Bundleable> SparseArray<Bundle> toBundleSparseArray(SparseArray<T> sparseArray) {
        SparseArray<Bundle> sparseArray2 = new SparseArray<>(sparseArray.size());
        for (int r1 = 0; r1 < sparseArray.size(); r1++) {
            sparseArray2.put(sparseArray.keyAt(r1), sparseArray.valueAt(r1).toBundle());
        }
        return sparseArray2;
    }

    public static void ensureClassLoader(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader((ClassLoader) Util.castNonNull(BundleableUtil.class.getClassLoader()));
        }
    }

    private BundleableUtil() {
    }
}
