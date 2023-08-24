package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
abstract class CommonMatcher {
    public abstract int end();

    public abstract boolean find();

    public abstract boolean find(int r1);

    public abstract boolean matches();

    public abstract String replaceAll(String str);

    public abstract int start();
}
