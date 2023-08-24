package p042rx.internal.util;

import java.util.concurrent.CountDownLatch;
import p042rx.Subscription;

/* renamed from: rx.internal.util.BlockingUtils */
/* loaded from: classes6.dex */
public final class BlockingUtils {
    private BlockingUtils() {
    }

    public static void awaitForComplete(CountDownLatch countDownLatch, Subscription subscription) {
        if (countDownLatch.getCount() == 0) {
            return;
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            subscription.unsubscribe();
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for subscription to complete.", e);
        }
    }
}
