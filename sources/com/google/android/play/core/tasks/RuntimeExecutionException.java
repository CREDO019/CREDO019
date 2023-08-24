package com.google.android.play.core.tasks;

/* loaded from: classes3.dex */
public class RuntimeExecutionException extends AbstractC2683j {
    public RuntimeExecutionException(Throwable th) {
        super(th);
    }

    @Override // com.google.android.play.core.tasks.AbstractC2683j
    public final int getErrorCode() {
        return -100;
    }
}
