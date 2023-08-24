package com.google.android.play.core.review;

import android.content.Context;
import android.content.Intent;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.InterfaceC2490ab;
import com.google.android.play.core.tasks.C2682i;
import com.google.android.play.core.tasks.Task;

/* renamed from: com.google.android.play.core.review.h */
/* loaded from: classes3.dex */
public final class C2591h {

    /* renamed from: b */
    private static final C2494af f893b = new C2494af("ReviewService");

    /* renamed from: a */
    final C2504ap<InterfaceC2490ab> f894a;

    /* renamed from: c */
    private final String f895c;

    public C2591h(Context context) {
        this.f895c = context.getPackageName();
        this.f894a = new C2504ap<>(context, f893b, "com.google.android.finsky.inappreviewservice.InAppReviewService", new Intent("com.google.android.finsky.BIND_IN_APP_REVIEW_SERVICE").setPackage("com.android.vending"), C2587d.f887a);
    }

    /* renamed from: a */
    public final Task<ReviewInfo> m621a() {
        f893b.m805c("requestInAppReview (%s)", this.f895c);
        C2682i c2682i = new C2682i();
        this.f894a.m800a(new C2588e(this, c2682i, c2682i));
        return c2682i.m458a();
    }
}
