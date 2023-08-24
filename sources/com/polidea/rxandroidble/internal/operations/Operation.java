package com.polidea.rxandroidble.internal.operations;

import com.polidea.rxandroidble.internal.Priority;
import com.polidea.rxandroidble.internal.serialization.QueueReleaseInterface;
import p042rx.Observable;

/* loaded from: classes3.dex */
public interface Operation<T> extends Comparable<Operation<?>> {
    Priority definedPriority();

    Observable<T> run(QueueReleaseInterface queueReleaseInterface);
}
