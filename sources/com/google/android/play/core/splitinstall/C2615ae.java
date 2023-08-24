package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.ae */
/* loaded from: classes3.dex */
public final class C2615ae extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ Collection f950a;

    /* renamed from: b */
    final /* synthetic */ Collection f951b;

    /* renamed from: c */
    final /* synthetic */ C2682i f952c;

    /* renamed from: d */
    final /* synthetic */ C2632av f953d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2615ae(C2632av c2632av, C2682i c2682i, Collection collection, Collection collection2, C2682i c2682i2) {
        super(c2682i);
        this.f953d = c2632av;
        this.f950a = collection;
        this.f951b = collection2;
        this.f952c = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        String str;
        ArrayList m549a = C2632av.m549a(this.f950a);
        m549a.addAll(C2632av.m544b(this.f951b));
        try {
            str = this.f953d.f979d;
            this.f953d.f978a.m796b().mo730a(str, m549a, C2632av.m546b(), new BinderC2630at(this.f953d, this.f952c));
        } catch (RemoteException e) {
            c2494af = C2632av.f976b;
            c2494af.m807a(e, "startInstall(%s,%s)", this.f950a, this.f951b);
            this.f952c.m455b((Exception) new RuntimeException(e));
        }
    }
}
