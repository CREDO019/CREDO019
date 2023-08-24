package com.google.common.util.concurrent;

import com.google.common.primitives.ImmutableLongArray;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongArray;
import okhttp3.HttpUrl;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public class AtomicDoubleArray implements Serializable {
    private static final long serialVersionUID = 0;
    private transient AtomicLongArray longs;

    public AtomicDoubleArray(int r2) {
        this.longs = new AtomicLongArray(r2);
    }

    public AtomicDoubleArray(double[] dArr) {
        int length = dArr.length;
        long[] jArr = new long[length];
        for (int r2 = 0; r2 < length; r2++) {
            jArr[r2] = Double.doubleToRawLongBits(dArr[r2]);
        }
        this.longs = new AtomicLongArray(jArr);
    }

    public final int length() {
        return this.longs.length();
    }

    public final double get(int r3) {
        return Double.longBitsToDouble(this.longs.get(r3));
    }

    public final void set(int r2, double d) {
        this.longs.set(r2, Double.doubleToRawLongBits(d));
    }

    public final void lazySet(int r2, double d) {
        this.longs.lazySet(r2, Double.doubleToRawLongBits(d));
    }

    public final double getAndSet(int r2, double d) {
        return Double.longBitsToDouble(this.longs.getAndSet(r2, Double.doubleToRawLongBits(d)));
    }

    public final boolean compareAndSet(int r7, double d, double d2) {
        return this.longs.compareAndSet(r7, Double.doubleToRawLongBits(d), Double.doubleToRawLongBits(d2));
    }

    public final boolean weakCompareAndSet(int r7, double d, double d2) {
        return this.longs.weakCompareAndSet(r7, Double.doubleToRawLongBits(d), Double.doubleToRawLongBits(d2));
    }

    public final double getAndAdd(int r10, double d) {
        long j;
        double longBitsToDouble;
        do {
            j = this.longs.get(r10);
            longBitsToDouble = Double.longBitsToDouble(j);
        } while (!this.longs.compareAndSet(r10, j, Double.doubleToRawLongBits(longBitsToDouble + d)));
        return longBitsToDouble;
    }

    public double addAndGet(int r10, double d) {
        long j;
        double longBitsToDouble;
        do {
            j = this.longs.get(r10);
            longBitsToDouble = Double.longBitsToDouble(j) + d;
        } while (!this.longs.compareAndSet(r10, j, Double.doubleToRawLongBits(longBitsToDouble)));
        return longBitsToDouble;
    }

    public String toString() {
        int length = length() - 1;
        if (length == -1) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder((length + 1) * 19);
        sb.append('[');
        int r2 = 0;
        while (true) {
            sb.append(Double.longBitsToDouble(this.longs.get(r2)));
            if (r2 == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(',');
            sb.append(' ');
            r2++;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int length = length();
        objectOutputStream.writeInt(length);
        for (int r1 = 0; r1 < length; r1++) {
            objectOutputStream.writeDouble(get(r1));
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        ImmutableLongArray.Builder builder = ImmutableLongArray.builder();
        for (int r2 = 0; r2 < readInt; r2++) {
            builder.add(Double.doubleToRawLongBits(objectInputStream.readDouble()));
        }
        this.longs = new AtomicLongArray(builder.build().toArray());
    }
}
