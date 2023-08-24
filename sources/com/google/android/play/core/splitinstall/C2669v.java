package com.google.android.play.core.splitinstall;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import com.google.android.play.core.common.IntentSenderForResultStarter;

/* renamed from: com.google.android.play.core.splitinstall.v */
/* loaded from: classes3.dex */
final class C2669v implements IntentSenderForResultStarter {

    /* renamed from: a */
    final /* synthetic */ Activity f1077a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2669v(Activity activity) {
        this.f1077a = activity;
    }

    @Override // com.google.android.play.core.common.IntentSenderForResultStarter
    public final void startIntentSenderForResult(IntentSender intentSender, int r9, Intent intent, int r11, int r12, int r13, Bundle bundle) throws IntentSender.SendIntentException {
        this.f1077a.startIntentSenderForResult(intentSender, r9, intent, r11, r12, r13);
    }
}
