package com.polidea.rxandroidble.internal.serialization;

import com.polidea.rxandroidble.exceptions.BleException;

/* loaded from: classes3.dex */
public interface ConnectionOperationQueue extends ClientOperationQueue {
    void terminate(BleException bleException);
}
