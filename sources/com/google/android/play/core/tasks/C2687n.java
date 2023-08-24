package com.google.android.play.core.tasks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.android.play.core.tasks.n */
/* loaded from: classes3.dex */
final class C2687n implements OnSuccessListener, OnFailureListener {

    /* renamed from: a */
    private final CountDownLatch f1117a = new CountDownLatch(1);

    private C2687n() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ C2687n(byte[] bArr) {
    }

    /* renamed from: a */
    public final void m446a() throws InterruptedException {
        this.f1117a.await();
    }

    /* renamed from: a */
    public final boolean m445a(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.f1117a.await(j, timeUnit);
    }

    @Override // com.google.android.play.core.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        this.f1117a.countDown();
    }

    @Override // com.google.android.play.core.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        this.f1117a.countDown();
    }
}
