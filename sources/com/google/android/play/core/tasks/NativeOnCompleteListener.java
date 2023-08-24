package com.google.android.play.core.tasks;

/* loaded from: classes3.dex */
public class NativeOnCompleteListener implements OnCompleteListener<Object> {

    /* renamed from: a */
    private final long f1089a;

    /* renamed from: b */
    private final int f1090b;

    public NativeOnCompleteListener(long j, int r3) {
        this.f1089a = j;
        this.f1090b = r3;
    }

    public native void nativeOnComplete(long j, int r3, Object obj, int r5);

    @Override // com.google.android.play.core.tasks.OnCompleteListener
    public void onComplete(Task<Object> task) {
        if (!task.isComplete()) {
            int r0 = this.f1090b;
            StringBuilder sb = new StringBuilder(50);
            sb.append("onComplete called for incomplete task: ");
            sb.append(r0);
            throw new IllegalStateException(sb.toString());
        } else if (task.isSuccessful()) {
            nativeOnComplete(this.f1089a, this.f1090b, task.getResult(), 0);
        } else {
            Exception exception = task.getException();
            if (!(exception instanceof AbstractC2683j)) {
                nativeOnComplete(this.f1089a, this.f1090b, null, -100);
                return;
            }
            int errorCode = ((AbstractC2683j) exception).getErrorCode();
            if (errorCode != 0) {
                nativeOnComplete(this.f1089a, this.f1090b, null, errorCode);
                return;
            }
            int r02 = this.f1090b;
            StringBuilder sb2 = new StringBuilder(51);
            sb2.append("TaskException has error code 0 on task: ");
            sb2.append(r02);
            throw new IllegalStateException(sb2.toString());
        }
    }
}
