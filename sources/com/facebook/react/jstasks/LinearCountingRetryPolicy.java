package com.facebook.react.jstasks;

/* loaded from: classes.dex */
public class LinearCountingRetryPolicy implements HeadlessJsTaskRetryPolicy {
    private final int mDelayBetweenAttemptsInMs;
    private final int mRetryAttempts;

    public LinearCountingRetryPolicy(int r1, int r2) {
        this.mRetryAttempts = r1;
        this.mDelayBetweenAttemptsInMs = r2;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public boolean canRetry() {
        return this.mRetryAttempts > 0;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public int getDelay() {
        return this.mDelayBetweenAttemptsInMs;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public HeadlessJsTaskRetryPolicy update() {
        int r0 = this.mRetryAttempts - 1;
        if (r0 > 0) {
            return new LinearCountingRetryPolicy(r0, this.mDelayBetweenAttemptsInMs);
        }
        return NoRetryPolicy.INSTANCE;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public HeadlessJsTaskRetryPolicy copy() {
        return new LinearCountingRetryPolicy(this.mRetryAttempts, this.mDelayBetweenAttemptsInMs);
    }
}
