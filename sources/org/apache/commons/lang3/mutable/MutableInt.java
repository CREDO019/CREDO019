package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;

/* loaded from: classes5.dex */
public class MutableInt extends Number implements Comparable<MutableInt>, Mutable<Number> {
    private static final long serialVersionUID = 512176391864L;
    private int value;

    public MutableInt() {
    }

    public MutableInt(int r1) {
        this.value = r1;
    }

    public MutableInt(Number number) {
        this.value = number.intValue();
    }

    public MutableInt(String str) {
        this.value = Integer.parseInt(str);
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* renamed from: getValue */
    public Number getValue2() {
        return Integer.valueOf(this.value);
    }

    public void setValue(int r1) {
        this.value = r1;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number number) {
        this.value = number.intValue();
    }

    public void increment() {
        this.value++;
    }

    public int getAndIncrement() {
        int r0 = this.value;
        this.value = r0 + 1;
        return r0;
    }

    public int incrementAndGet() {
        int r0 = this.value + 1;
        this.value = r0;
        return r0;
    }

    public void decrement() {
        this.value--;
    }

    public int getAndDecrement() {
        int r0 = this.value;
        this.value = r0 - 1;
        return r0;
    }

    public int decrementAndGet() {
        int r0 = this.value - 1;
        this.value = r0;
        return r0;
    }

    public void add(int r2) {
        this.value += r2;
    }

    public void add(Number number) {
        this.value += number.intValue();
    }

    public void subtract(int r2) {
        this.value -= r2;
    }

    public void subtract(Number number) {
        this.value -= number.intValue();
    }

    public int addAndGet(int r2) {
        int r0 = this.value + r2;
        this.value = r0;
        return r0;
    }

    public int addAndGet(Number number) {
        int intValue = this.value + number.intValue();
        this.value = intValue;
        return intValue;
    }

    public int getAndAdd(int r2) {
        int r0 = this.value;
        this.value = r2 + r0;
        return r0;
    }

    public int getAndAdd(Number number) {
        int r0 = this.value;
        this.value = number.intValue() + r0;
        return r0;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public Integer toInteger() {
        return Integer.valueOf(intValue());
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableInt) && this.value == ((MutableInt) obj).intValue();
    }

    public int hashCode() {
        return this.value;
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableInt mutableInt) {
        return NumberUtils.compare(this.value, mutableInt.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
