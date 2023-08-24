package com.google.android.play.core.install;

import com.google.android.play.core.install.model.C2487a;
import com.google.android.play.core.tasks.AbstractC2683j;

/* loaded from: classes3.dex */
public class InstallException extends AbstractC2683j {

    /* renamed from: a */
    private final int f795a;

    public InstallException(int r4) {
        super(String.format("Install Error(%d): %s", Integer.valueOf(r4), C2487a.m815a(r4)));
        if (r4 == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
        this.f795a = r4;
    }

    @Override // com.google.android.play.core.tasks.AbstractC2683j
    public int getErrorCode() {
        return this.f795a;
    }
}
