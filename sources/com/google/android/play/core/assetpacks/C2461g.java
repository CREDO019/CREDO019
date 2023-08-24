package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnSuccessListener;
import java.util.List;

/* renamed from: com.google.android.play.core.assetpacks.g */
/* loaded from: classes3.dex */
final /* synthetic */ class C2461g implements OnSuccessListener {

    /* renamed from: a */
    private final C2382bb f748a;

    private C2461g(C2382bb c2382bb) {
        this.f748a = c2382bb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static OnSuccessListener m853a(C2382bb c2382bb) {
        return new C2461g(c2382bb);
    }

    @Override // com.google.android.play.core.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        this.f748a.m999a((List) obj);
    }
}
