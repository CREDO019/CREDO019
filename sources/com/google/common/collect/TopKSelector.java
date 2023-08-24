package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class TopKSelector<T> {
    private final T[] buffer;
    private int bufferSize;
    private final Comparator<? super T> comparator;

    /* renamed from: k */
    private final int f1160k;
    @CheckForNull
    private T threshold;

    public static <T extends Comparable<? super T>> TopKSelector<T> least(int r1) {
        return least(r1, Ordering.natural());
    }

    public static <T> TopKSelector<T> least(int r1, Comparator<? super T> comparator) {
        return new TopKSelector<>(comparator, r1);
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> greatest(int r1) {
        return greatest(r1, Ordering.natural());
    }

    public static <T> TopKSelector<T> greatest(int r1, Comparator<? super T> comparator) {
        return new TopKSelector<>(Ordering.from(comparator).reverse(), r1);
    }

    private TopKSelector(Comparator<? super T> comparator, int r5) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator, "comparator");
        this.f1160k = r5;
        Preconditions.checkArgument(r5 >= 0, "k (%s) must be >= 0", r5);
        Preconditions.checkArgument(r5 <= 1073741823, "k (%s) must be <= Integer.MAX_VALUE / 2", r5);
        this.buffer = (T[]) new Object[IntMath.checkedMultiply(r5, 2)];
        this.bufferSize = 0;
        this.threshold = null;
    }

    public void offer(@ParametricNullness T t) {
        int r0 = this.f1160k;
        if (r0 == 0) {
            return;
        }
        int r1 = this.bufferSize;
        if (r1 == 0) {
            this.buffer[0] = t;
            this.threshold = t;
            this.bufferSize = 1;
        } else if (r1 < r0) {
            T[] tArr = this.buffer;
            this.bufferSize = r1 + 1;
            tArr[r1] = t;
            if (this.comparator.compare(t, (Object) NullnessCasts.uncheckedCastNullableTToT(this.threshold)) > 0) {
                this.threshold = t;
            }
        } else if (this.comparator.compare(t, (Object) NullnessCasts.uncheckedCastNullableTToT(this.threshold)) < 0) {
            T[] tArr2 = this.buffer;
            int r12 = this.bufferSize;
            int r2 = r12 + 1;
            this.bufferSize = r2;
            tArr2[r12] = t;
            if (r2 == this.f1160k * 2) {
                trim();
            }
        }
    }

    private void trim() {
        int r0 = (this.f1160k * 2) - 1;
        int log2 = IntMath.log2(r0 + 0, RoundingMode.CEILING) * 3;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        while (true) {
            if (r2 < r0) {
                int partition = partition(r2, r0, ((r2 + r0) + 1) >>> 1);
                int r6 = this.f1160k;
                if (partition <= r6) {
                    if (partition >= r6) {
                        break;
                    }
                    r2 = Math.max(partition, r2 + 1);
                    r4 = partition;
                } else {
                    r0 = partition - 1;
                }
                r3++;
                if (r3 >= log2) {
                    Arrays.sort(this.buffer, r2, r0 + 1, this.comparator);
                    break;
                }
            } else {
                break;
            }
        }
        this.bufferSize = this.f1160k;
        this.threshold = (T) NullnessCasts.uncheckedCastNullableTToT(this.buffer[r4]);
        while (true) {
            r4++;
            if (r4 >= this.f1160k) {
                return;
            }
            if (this.comparator.compare((Object) NullnessCasts.uncheckedCastNullableTToT(this.buffer[r4]), (Object) NullnessCasts.uncheckedCastNullableTToT(this.threshold)) > 0) {
                this.threshold = this.buffer[r4];
            }
        }
    }

    private int partition(int r4, int r5, int r6) {
        Object uncheckedCastNullableTToT = NullnessCasts.uncheckedCastNullableTToT(this.buffer[r6]);
        T[] tArr = this.buffer;
        tArr[r6] = tArr[r5];
        int r62 = r4;
        while (r4 < r5) {
            if (this.comparator.compare((Object) NullnessCasts.uncheckedCastNullableTToT(this.buffer[r4]), uncheckedCastNullableTToT) < 0) {
                swap(r62, r4);
                r62++;
            }
            r4++;
        }
        T[] tArr2 = this.buffer;
        tArr2[r5] = tArr2[r62];
        tArr2[r62] = uncheckedCastNullableTToT;
        return r62;
    }

    private void swap(int r4, int r5) {
        T[] tArr = this.buffer;
        T t = tArr[r4];
        tArr[r4] = tArr[r5];
        tArr[r5] = t;
    }

    public void offerAll(Iterable<? extends T> iterable) {
        offerAll(iterable.iterator());
    }

    public void offerAll(Iterator<? extends T> it) {
        while (it.hasNext()) {
            offer(it.next());
        }
    }

    public List<T> topK() {
        Arrays.sort(this.buffer, 0, this.bufferSize, this.comparator);
        int r0 = this.bufferSize;
        int r1 = this.f1160k;
        if (r0 > r1) {
            T[] tArr = this.buffer;
            Arrays.fill(tArr, r1, tArr.length, (Object) null);
            int r02 = this.f1160k;
            this.bufferSize = r02;
            this.threshold = this.buffer[r02 - 1];
        }
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(this.buffer, this.bufferSize)));
    }
}
