package androidx.activity;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m183d2 = {"Landroidx/activity/Api19Impl;", "", "()V", "isAttachedToWindow", "", "view", "Landroid/view/View;", "activity-ktx_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* renamed from: androidx.activity.Api19Impl */
/* loaded from: classes.dex */
public final class PipHintTracker {
    public static final PipHintTracker INSTANCE = new PipHintTracker();

    private PipHintTracker() {
    }

    public final boolean isAttachedToWindow(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        return view.isAttachedToWindow();
    }
}
