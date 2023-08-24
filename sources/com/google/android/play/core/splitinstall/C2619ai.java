package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.ai */
/* loaded from: classes3.dex */
public final class C2619ai extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ List f963a;

    /* renamed from: b */
    final /* synthetic */ C2682i f964b;

    /* renamed from: c */
    final /* synthetic */ C2632av f965c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2619ai(C2632av c2632av, C2682i c2682i, List list, C2682i c2682i2) {
        super(c2682i);
        this.f965c = c2632av;
        this.f963a = list;
        this.f964b = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        String str;
        try {
            str = this.f965c.f979d;
            this.f965c.f978a.m796b().mo726e(str, C2632av.m544b((Collection) this.f963a), C2632av.m546b(), new BinderC2626ap(this.f965c, this.f964b));
        } catch (RemoteException e) {
            c2494af = C2632av.f976b;
            c2494af.m807a(e, "deferredLanguageUninstall(%s)", this.f963a);
            this.f964b.m455b((Exception) new RuntimeException(e));
        }
    }
}
