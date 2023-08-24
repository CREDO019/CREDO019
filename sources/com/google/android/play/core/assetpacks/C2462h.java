package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnFailureListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.h */
/* loaded from: classes3.dex */
public final /* synthetic */ class C2462h implements OnFailureListener {

    /* renamed from: a */
    static final OnFailureListener f749a = new C2462h();

    private C2462h() {
    }

    @Override // com.google.android.play.core.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        C2464j.f752a.m804d(String.format("Could not sync active asset packs. %s", exc), new Object[0]);
    }
}
