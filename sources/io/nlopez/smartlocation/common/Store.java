package io.nlopez.smartlocation.common;

/* loaded from: classes5.dex */
public interface Store<T> {
    T get(String str);

    void put(String str, T t);

    void remove(String str);
}
