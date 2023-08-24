package com.google.android.play.core.tasks;

/* renamed from: com.google.android.play.core.tasks.e */
/* loaded from: classes3.dex */
final class RunnableC2678e implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Task f1102a;

    /* renamed from: b */
    final /* synthetic */ C2679f f1103b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2678e(C2679f c2679f, Task task) {
        this.f1103b = c2679f;
        this.f1102a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnSuccessListener onSuccessListener;
        OnSuccessListener onSuccessListener2;
        obj = this.f1103b.f1105b;
        synchronized (obj) {
            onSuccessListener = this.f1103b.f1106c;
            if (onSuccessListener != null) {
                onSuccessListener2 = this.f1103b.f1106c;
                onSuccessListener2.onSuccess(this.f1102a.getResult());
            }
        }
    }
}
