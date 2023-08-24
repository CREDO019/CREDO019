package com.google.android.play.core.review.testing;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

/* loaded from: classes3.dex */
public class FakeReviewManager implements ReviewManager {

    /* renamed from: a */
    private final Context f896a;

    /* renamed from: b */
    private ReviewInfo f897b;

    public FakeReviewManager(Context context) {
        this.f896a = context;
    }

    @Override // com.google.android.play.core.review.ReviewManager
    public Task<Void> launchReviewFlow(Activity activity, ReviewInfo reviewInfo) {
        return reviewInfo != this.f897b ? Tasks.m469a((Exception) new C2592a()) : Tasks.m468a((Object) null);
    }

    @Override // com.google.android.play.core.review.ReviewManager
    public Task<ReviewInfo> requestReviewFlow() {
        ReviewInfo m624a = ReviewInfo.m624a(PendingIntent.getBroadcast(this.f896a, 0, new Intent(), 0));
        this.f897b = m624a;
        return Tasks.m468a(m624a);
    }
}
