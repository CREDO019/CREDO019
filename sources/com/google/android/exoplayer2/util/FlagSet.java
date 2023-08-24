package com.google.android.exoplayer2.util;

import android.util.SparseBooleanArray;

/* loaded from: classes2.dex */
public final class FlagSet {
    private final SparseBooleanArray flags;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private boolean buildCalled;
        private final SparseBooleanArray flags = new SparseBooleanArray();

        public Builder add(int r3) {
            Assertions.checkState(!this.buildCalled);
            this.flags.append(r3, true);
            return this;
        }

        public Builder addIf(int r1, boolean z) {
            return z ? add(r1) : this;
        }

        public Builder addAll(int... r4) {
            for (int r2 : r4) {
                add(r2);
            }
            return this;
        }

        public Builder addAll(FlagSet flagSet) {
            for (int r0 = 0; r0 < flagSet.size(); r0++) {
                add(flagSet.get(r0));
            }
            return this;
        }

        public Builder remove(int r2) {
            Assertions.checkState(!this.buildCalled);
            this.flags.delete(r2);
            return this;
        }

        public Builder removeIf(int r1, boolean z) {
            return z ? remove(r1) : this;
        }

        public Builder removeAll(int... r4) {
            for (int r2 : r4) {
                remove(r2);
            }
            return this;
        }

        public FlagSet build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new FlagSet(this.flags);
        }
    }

    private FlagSet(SparseBooleanArray sparseBooleanArray) {
        this.flags = sparseBooleanArray;
    }

    public boolean contains(int r2) {
        return this.flags.get(r2);
    }

    public boolean containsAny(int... r5) {
        for (int r3 : r5) {
            if (contains(r3)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.flags.size();
    }

    public int get(int r3) {
        Assertions.checkIndex(r3, 0, size());
        return this.flags.keyAt(r3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FlagSet) {
            FlagSet flagSet = (FlagSet) obj;
            if (Util.SDK_INT < 24) {
                if (size() != flagSet.size()) {
                    return false;
                }
                for (int r1 = 0; r1 < size(); r1++) {
                    if (get(r1) != flagSet.get(r1)) {
                        return false;
                    }
                }
                return true;
            }
            return this.flags.equals(flagSet.flags);
        }
        return false;
    }

    public int hashCode() {
        if (Util.SDK_INT < 24) {
            int size = size();
            for (int r1 = 0; r1 < size(); r1++) {
                size = (size * 31) + get(r1);
            }
            return size;
        }
        return this.flags.hashCode();
    }
}
