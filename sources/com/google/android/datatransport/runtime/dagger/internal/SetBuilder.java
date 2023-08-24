package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public final class SetBuilder<T> {
    private static final String SET_CONTRIBUTIONS_CANNOT_BE_NULL = "Set contributions cannot be null";
    private final List<T> contributions;

    private SetBuilder(int r2) {
        this.contributions = new ArrayList(r2);
    }

    public static <T> SetBuilder<T> newSetBuilder(int r1) {
        return new SetBuilder<>(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SetBuilder<T> add(T t) {
        this.contributions.add(Preconditions.checkNotNull(t, SET_CONTRIBUTIONS_CANNOT_BE_NULL));
        return this;
    }

    public SetBuilder<T> addAll(Collection<? extends T> collection) {
        for (T t : collection) {
            Preconditions.checkNotNull(t, SET_CONTRIBUTIONS_CANNOT_BE_NULL);
        }
        this.contributions.addAll(collection);
        return this;
    }

    public Set<T> build() {
        int size = this.contributions.size();
        if (size != 0) {
            if (size == 1) {
                return Collections.singleton(this.contributions.get(0));
            }
            return Collections.unmodifiableSet(new HashSet(this.contributions));
        }
        return Collections.emptySet();
    }
}
