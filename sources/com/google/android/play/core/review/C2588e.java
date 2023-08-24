package com.google.android.play.core.review;

import android.os.RemoteException;
import com.google.android.play.core.common.PlayCoreVersion;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.review.e */
/* loaded from: classes3.dex */
public final class C2588e extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ C2682i f888a;

    /* renamed from: b */
    final /* synthetic */ C2591h f889b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2588e(C2591h c2591h, C2682i c2682i, C2682i c2682i2) {
        super(c2682i);
        this.f889b = c2591h;
        this.f888a = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        String str;
        String str2;
        try {
            str2 = this.f889b.f895c;
            this.f889b.f894a.m796b().mo643a(str2, PlayCoreVersion.m823a(), new BinderC2590g(this.f889b, this.f888a));
        } catch (RemoteException e) {
            c2494af = C2591h.f893b;
            str = this.f889b.f895c;
            c2494af.m807a(e, "error requesting in-app review for %s", str);
            this.f888a.m455b((Exception) new RuntimeException(e));
        }
    }
}
