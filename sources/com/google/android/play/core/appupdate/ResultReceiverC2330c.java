package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.appupdate.c */
/* loaded from: classes3.dex */
final class ResultReceiverC2330c extends ResultReceiver {

    /* renamed from: a */
    final /* synthetic */ C2682i f296a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResultReceiverC2330c(Handler handler, C2682i c2682i) {
        super(handler);
        this.f296a = c2682i;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int r2, Bundle bundle) {
        C2682i c2682i;
        int r3 = 1;
        if (r2 == 1) {
            c2682i = this.f296a;
            r3 = -1;
        } else if (r2 != 2) {
            c2682i = this.f296a;
        } else {
            c2682i = this.f296a;
            r3 = 0;
        }
        c2682i.m454b((C2682i) Integer.valueOf(r3));
    }
}
