package com.google.android.play.core.tasks;

/* renamed from: com.google.android.play.core.tasks.c */
/* loaded from: classes3.dex */
final class RunnableC2676c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Task f1097a;

    /* renamed from: b */
    final /* synthetic */ C2677d f1098b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2676c(C2677d c2677d, Task task) {
        this.f1098b = c2677d;
        this.f1097a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnFailureListener onFailureListener;
        OnFailureListener onFailureListener2;
        obj = this.f1098b.f1100b;
        synchronized (obj) {
            onFailureListener = this.f1098b.f1101c;
            if (onFailureListener != null) {
                onFailureListener2 = this.f1098b.f1101c;
                onFailureListener2.onFailure(this.f1097a.getException());
            }
        }
    }
}
