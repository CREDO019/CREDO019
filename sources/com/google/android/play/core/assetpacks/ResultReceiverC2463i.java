package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.assetpacks.i */
/* loaded from: classes3.dex */
final class ResultReceiverC2463i extends ResultReceiver {

    /* renamed from: a */
    final /* synthetic */ C2682i f750a;

    /* renamed from: b */
    final /* synthetic */ C2464j f751b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResultReceiverC2463i(C2464j c2464j, Handler handler, C2682i c2682i) {
        super(handler);
        this.f751b = c2464j;
        this.f750a = c2682i;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int r2, Bundle bundle) {
        C2394bn c2394bn;
        if (r2 == 1) {
            this.f750a.m454b((C2682i) (-1));
            c2394bn = this.f751b.f759h;
            c2394bn.m961a(null);
        } else if (r2 != 2) {
            this.f750a.m455b((Exception) new AssetPackException(-100));
        } else {
            this.f750a.m454b((C2682i) 0);
        }
    }
}
