package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SetFactory<T> implements Factory<Set<T>> {
    private static final Factory<Set<Object>> EMPTY_FACTORY = InstanceFactory.create(Collections.emptySet());
    private final List<Provider<Collection<T>>> collectionProviders;
    private final List<Provider<T>> individualProviders;

    public static <T> Factory<Set<T>> empty() {
        return (Factory<Set<T>>) EMPTY_FACTORY;
    }

    public static <T> Builder<T> builder(int r2, int r3) {
        return new Builder<>(r2, r3);
    }

    /* loaded from: classes2.dex */
    public static final class Builder<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final List<Provider<Collection<T>>> collectionProviders;
        private final List<Provider<T>> individualProviders;

        private Builder(int r1, int r2) {
            this.individualProviders = DaggerCollections.presizedList(r1);
            this.collectionProviders = DaggerCollections.presizedList(r2);
        }

        public Builder<T> addProvider(Provider<? extends T> provider) {
            this.individualProviders.add(provider);
            return this;
        }

        public Builder<T> addCollectionProvider(Provider<? extends Collection<? extends T>> provider) {
            this.collectionProviders.add(provider);
            return this;
        }

        public SetFactory<T> build() {
            return new SetFactory<>(this.individualProviders, this.collectionProviders);
        }
    }

    private SetFactory(List<Provider<T>> list, List<Provider<Collection<T>>> list2) {
        this.individualProviders = list;
        this.collectionProviders = list2;
    }

    @Override // javax.inject.Provider
    public Set<T> get() {
        int size = this.individualProviders.size();
        ArrayList arrayList = new ArrayList(this.collectionProviders.size());
        int size2 = this.collectionProviders.size();
        for (int r4 = 0; r4 < size2; r4++) {
            Collection<T> collection = this.collectionProviders.get(r4).get();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet newHashSetWithExpectedSize = DaggerCollections.newHashSetWithExpectedSize(size);
        int size3 = this.individualProviders.size();
        for (int r42 = 0; r42 < size3; r42++) {
            newHashSetWithExpectedSize.add(Preconditions.checkNotNull(this.individualProviders.get(r42).get()));
        }
        int size4 = arrayList.size();
        for (int r3 = 0; r3 < size4; r3++) {
            for (Object obj : (Collection) arrayList.get(r3)) {
                newHashSetWithExpectedSize.add(Preconditions.checkNotNull(obj));
            }
        }
        return Collections.unmodifiableSet(newHashSetWithExpectedSize);
    }
}
