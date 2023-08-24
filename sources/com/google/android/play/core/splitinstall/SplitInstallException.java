package com.google.android.play.core.splitinstall;

import com.google.android.play.core.splitinstall.model.C2649a;
import com.google.android.play.core.tasks.AbstractC2683j;

/* loaded from: classes3.dex */
public class SplitInstallException extends AbstractC2683j {

    /* renamed from: a */
    private final int f930a;

    public SplitInstallException(int r4) {
        super(String.format("Split Install Error(%d): %s", Integer.valueOf(r4), C2649a.m520a(r4)));
        if (r4 == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
        this.f930a = r4;
    }

    @Override // com.google.android.play.core.tasks.AbstractC2683j
    public int getErrorCode() {
        return this.f930a;
    }
}
