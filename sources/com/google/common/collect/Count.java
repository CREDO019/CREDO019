package com.google.common.collect;

import java.io.Serializable;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class Count implements Serializable {
    private int value;

    Count(int r1) {
        this.value = r1;
    }

    public int get() {
        return this.value;
    }

    public void add(int r2) {
        this.value += r2;
    }

    public int addAndGet(int r2) {
        int r0 = this.value + r2;
        this.value = r0;
        return r0;
    }

    public void set(int r1) {
        this.value = r1;
    }

    public int getAndSet(int r2) {
        int r0 = this.value;
        this.value = r2;
        return r0;
    }

    public int hashCode() {
        return this.value;
    }

    public boolean equals(@CheckForNull Object obj) {
        return (obj instanceof Count) && ((Count) obj).value == this.value;
    }

    public String toString() {
        return Integer.toString(this.value);
    }
}
