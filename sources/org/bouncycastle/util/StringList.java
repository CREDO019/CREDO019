package org.bouncycastle.util;

/* loaded from: classes4.dex */
public interface StringList extends Iterable<String> {
    boolean add(String str);

    String get(int r1);

    int size();

    String[] toStringArray();

    String[] toStringArray(int r1, int r2);
}
