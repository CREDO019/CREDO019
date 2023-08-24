package p042rx.internal.util;

import java.util.Arrays;
import p042rx.functions.Action1;
import p042rx.internal.util.unsafe.Pow2;

/* renamed from: rx.internal.util.OpenHashSet */
/* loaded from: classes6.dex */
public final class OpenHashSet<T> {
    private static final int INT_PHI = -1640531527;
    T[] keys;
    final float loadFactor;
    int mask;
    int maxSize;
    int size;

    static int mix(int r1) {
        int r12 = r1 * INT_PHI;
        return r12 ^ (r12 >>> 16);
    }

    public OpenHashSet() {
        this(16, 0.75f);
    }

    public OpenHashSet(int r2) {
        this(r2, 0.75f);
    }

    public OpenHashSet(int r2, float f) {
        this.loadFactor = f;
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(r2);
        this.mask = roundToPowerOfTwo - 1;
        this.maxSize = (int) (f * roundToPowerOfTwo);
        this.keys = (T[]) new Object[roundToPowerOfTwo];
    }

    public boolean add(T t) {
        T t2;
        T[] tArr = this.keys;
        int r1 = this.mask;
        int mix = mix(t.hashCode()) & r1;
        T t3 = tArr[mix];
        if (t3 != null) {
            if (t3.equals(t)) {
                return false;
            }
            do {
                mix = (mix + 1) & r1;
                t2 = tArr[mix];
                if (t2 == null) {
                }
            } while (!t2.equals(t));
            return false;
        }
        tArr[mix] = t;
        int r7 = this.size + 1;
        this.size = r7;
        if (r7 >= this.maxSize) {
            rehash();
        }
        return true;
    }

    public boolean remove(T t) {
        T t2;
        T[] tArr = this.keys;
        int r1 = this.mask;
        int mix = mix(t.hashCode()) & r1;
        T t3 = tArr[mix];
        if (t3 == null) {
            return false;
        }
        if (t3.equals(t)) {
            return removeEntry(mix, tArr, r1);
        }
        do {
            mix = (mix + 1) & r1;
            t2 = tArr[mix];
            if (t2 == null) {
                return false;
            }
        } while (!t2.equals(t));
        return removeEntry(mix, tArr, r1);
    }

    boolean removeEntry(int r5, T[] tArr, int r7) {
        int r0;
        T t;
        this.size--;
        while (true) {
            int r02 = r5 + 1;
            while (true) {
                r0 = r02 & r7;
                t = tArr[r0];
                if (t == null) {
                    tArr[r5] = null;
                    return true;
                }
                int mix = mix(t.hashCode()) & r7;
                if (r5 > r0) {
                    if (r5 >= mix && mix > r0) {
                        break;
                    }
                    r02 = r0 + 1;
                } else if (r5 < mix && mix <= r0) {
                    r02 = r0 + 1;
                }
            }
            tArr[r5] = t;
            r5 = r0;
        }
    }

    public void clear(Action1<? super T> action1) {
        if (this.size == 0) {
            return;
        }
        T[] tArr = this.keys;
        for (T t : tArr) {
            Object obj = (Object) t;
            if (obj != 0) {
                action1.call(obj);
            }
        }
        Arrays.fill(tArr, (Object) null);
        this.size = 0;
    }

    public void terminate() {
        this.size = 0;
        this.keys = (T[]) new Object[0];
    }

    void rehash() {
        T[] tArr = this.keys;
        int length = tArr.length;
        int r2 = length << 1;
        int r3 = r2 - 1;
        T[] tArr2 = (T[]) new Object[r2];
        int r5 = this.size;
        while (true) {
            int r6 = r5 - 1;
            if (r5 != 0) {
                do {
                    length--;
                } while (tArr[length] == null);
                int mix = mix(tArr[length].hashCode()) & r3;
                if (tArr2[mix] != null) {
                    do {
                        mix = (mix + 1) & r3;
                    } while (tArr2[mix] != null);
                }
                tArr2[mix] = tArr[length];
                r5 = r6;
            } else {
                this.mask = r3;
                this.maxSize = (int) (r2 * this.loadFactor);
                this.keys = tArr2;
                return;
            }
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T[] values() {
        return this.keys;
    }
}
