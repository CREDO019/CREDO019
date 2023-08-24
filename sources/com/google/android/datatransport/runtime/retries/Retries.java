package com.google.android.datatransport.runtime.retries;

/* loaded from: classes2.dex */
public final class Retries {
    private Retries() {
    }

    public static <TInput, TResult, TException extends Throwable> TResult retry(int r2, TInput r3, Function<TInput, TResult, TException> function, RetryStrategy<TInput, TResult> retryStrategy) throws Throwable {
        TResult apply;
        if (r2 < 1) {
            return function.apply(r3);
        }
        do {
            apply = function.apply(r3);
            r3 = retryStrategy.shouldRetry(r3, apply);
            if (r3 == null) {
                break;
            }
            r2--;
        } while (r2 >= 1);
        return apply;
    }
}
