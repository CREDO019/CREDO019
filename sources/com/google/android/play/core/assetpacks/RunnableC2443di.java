package com.google.android.play.core.assetpacks;

/* renamed from: com.google.android.play.core.assetpacks.di */
/* loaded from: classes3.dex */
final /* synthetic */ class RunnableC2443di implements Runnable {

    /* renamed from: a */
    private final C2382bb f688a;

    private RunnableC2443di(C2382bb c2382bb) {
        this.f688a = c2382bb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Runnable m890a(C2382bb c2382bb) {
        return new RunnableC2443di(c2382bb);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f688a.m993c();
    }
}
