package com.google.common.util.concurrent;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public interface AsyncFunction<I, O> {
    ListenableFuture<O> apply(@ParametricNullness I r1) throws Exception;
}
