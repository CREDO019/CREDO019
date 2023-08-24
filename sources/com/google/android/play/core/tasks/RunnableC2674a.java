package com.google.android.play.core.tasks;

/* renamed from: com.google.android.play.core.tasks.a */
/* loaded from: classes3.dex */
final class RunnableC2674a implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Task f1092a;

    /* renamed from: b */
    final /* synthetic */ C2675b f1093b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2674a(C2675b c2675b, Task task) {
        this.f1093b = c2675b;
        this.f1092a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnCompleteListener onCompleteListener;
        OnCompleteListener onCompleteListener2;
        obj = this.f1093b.f1095b;
        synchronized (obj) {
            onCompleteListener = this.f1093b.f1096c;
            if (onCompleteListener != null) {
                onCompleteListener2 = this.f1093b.f1096c;
                onCompleteListener2.onComplete(this.f1092a);
            }
        }
    }
}
