package com.polidea.rxandroidble.internal.serialization;

import com.polidea.rxandroidble.internal.operations.Operation;
import p042rx.Observable;

/* loaded from: classes3.dex */
public interface ClientOperationQueue {
    <T> Observable<T> queue(Operation<T> operation);
}
