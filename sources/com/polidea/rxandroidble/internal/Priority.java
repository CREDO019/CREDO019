package com.polidea.rxandroidble.internal;

/* loaded from: classes3.dex */
public class Priority {
    final int priority;
    public static final Priority HIGH = new Priority(100);
    public static final Priority NORMAL = new Priority(50);
    public static final Priority LOW = new Priority(0);

    private Priority(int r1) {
        this.priority = r1;
    }
}
