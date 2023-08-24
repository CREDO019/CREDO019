package com.google.android.play.core.review;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.review.b */
/* loaded from: classes3.dex */
final class ResultReceiverC2585b extends ResultReceiver {

    /* renamed from: a */
    final /* synthetic */ C2682i f884a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResultReceiverC2585b(Handler handler, C2682i c2682i) {
        super(handler);
        this.f884a = c2682i;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int r1, Bundle bundle) {
        this.f884a.m454b((C2682i) null);
    }
}
