package com.google.firebase.installations.remote;

import com.google.firebase.installations.C3253Utils;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
class RequestLimiter {
    private static final long MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS = TimeUnit.HOURS.toMillis(24);
    private static final long MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS = TimeUnit.MINUTES.toMillis(30);
    private int attemptCount;
    private long nextRequestTime;
    private final C3253Utils utils;

    private static boolean isRetryableError(int r1) {
        return r1 == 429 || (r1 >= 500 && r1 < 600);
    }

    private static boolean isSuccessfulOrRequiresNewFidCreation(int r1) {
        return (r1 >= 200 && r1 < 300) || r1 == 401 || r1 == 404;
    }

    RequestLimiter(C3253Utils c3253Utils) {
        this.utils = c3253Utils;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestLimiter() {
        this.utils = C3253Utils.getInstance();
    }

    public synchronized void setNextRequestTime(int r5) {
        if (isSuccessfulOrRequiresNewFidCreation(r5)) {
            resetBackoffStrategy();
            return;
        }
        this.attemptCount++;
        this.nextRequestTime = this.utils.currentTimeInMillis() + getBackoffDuration(r5);
    }

    private synchronized void resetBackoffStrategy() {
        this.attemptCount = 0;
    }

    private synchronized long getBackoffDuration(int r5) {
        if (!isRetryableError(r5)) {
            return MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS;
        }
        return (long) Math.min(Math.pow(2.0d, this.attemptCount) + this.utils.getRandomDelayForSyncPrevention(), MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS);
    }

    public synchronized boolean isRequestAllowed() {
        boolean z;
        if (this.attemptCount != 0) {
            z = this.utils.currentTimeInMillis() > this.nextRequestTime;
        }
        return z;
    }
}
