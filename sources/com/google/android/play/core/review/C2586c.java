package com.google.android.play.core.review;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.tasks.C2682i;
import com.google.android.play.core.tasks.Task;

/* renamed from: com.google.android.play.core.review.c */
/* loaded from: classes3.dex */
public final class C2586c implements ReviewManager {

    /* renamed from: a */
    private final C2591h f885a;

    /* renamed from: b */
    private final Handler f886b = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2586c(C2591h c2591h) {
        this.f885a = c2591h;
    }

    @Override // com.google.android.play.core.review.ReviewManager
    public final Task<Void> launchReviewFlow(Activity activity, ReviewInfo reviewInfo) {
        Intent intent = new Intent(activity, PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", reviewInfo.mo623a());
        C2682i c2682i = new C2682i();
        intent.putExtra("result_receiver", new ResultReceiverC2585b(this.f886b, c2682i));
        activity.startActivity(intent);
        return c2682i.m458a();
    }

    @Override // com.google.android.play.core.review.ReviewManager
    public final Task<ReviewInfo> requestReviewFlow() {
        return this.f885a.m621a();
    }
}
