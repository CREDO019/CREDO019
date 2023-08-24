package com.google.android.datatransport.runtime.retries;

/* loaded from: classes2.dex */
public interface RetryStrategy<TInput, TResult> {
    TInput shouldRetry(TInput r1, TResult tresult);
}
