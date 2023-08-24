package com.google.android.play.core.appupdate;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import com.google.android.play.core.common.IntentSenderForResultStarter;

/* renamed from: com.google.android.play.core.appupdate.d */
/* loaded from: classes3.dex */
final class C2331d implements IntentSenderForResultStarter {

    /* renamed from: a */
    final /* synthetic */ Activity f297a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2331d(Activity activity) {
        this.f297a = activity;
    }

    @Override // com.google.android.play.core.common.IntentSenderForResultStarter
    public final void startIntentSenderForResult(IntentSender intentSender, int r10, Intent intent, int r12, int r13, int r14, Bundle bundle) throws IntentSender.SendIntentException {
        this.f297a.startIntentSenderForResult(intentSender, r10, intent, r12, r13, r14, bundle);
    }
}
