package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.ah */
/* loaded from: classes3.dex */
public final class C2618ah extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ List f960a;

    /* renamed from: b */
    final /* synthetic */ C2682i f961b;

    /* renamed from: c */
    final /* synthetic */ C2632av f962c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2618ah(C2632av c2632av, C2682i c2682i, List list, C2682i c2682i2) {
        super(c2682i);
        this.f962c = c2632av;
        this.f960a = list;
        this.f961b = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        String str;
        try {
            str = this.f962c.f979d;
            this.f962c.f978a.m796b().mo727d(str, C2632av.m544b((Collection) this.f960a), C2632av.m546b(), new BinderC2625ao(this.f962c, this.f961b));
        } catch (RemoteException e) {
            c2494af = C2632av.f976b;
            c2494af.m807a(e, "deferredLanguageInstall(%s)", this.f960a);
            this.f961b.m455b((Exception) new RuntimeException(e));
        }
    }
}
