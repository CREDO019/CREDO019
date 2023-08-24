package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.play.core.tasks.C2682i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.play.core.internal.ap */
/* loaded from: classes3.dex */
public final class C2504ap<T extends IInterface> {

    /* renamed from: a */
    private static final Map<String, Handler> f815a = new HashMap();

    /* renamed from: b */
    private final Context f816b;

    /* renamed from: c */
    private final C2494af f817c;

    /* renamed from: d */
    private final String f818d;

    /* renamed from: f */
    private boolean f820f;

    /* renamed from: g */
    private final Intent f821g;

    /* renamed from: h */
    private final InterfaceC2500al<T> f822h;

    /* renamed from: k */
    private ServiceConnection f825k;

    /* renamed from: l */
    private T f826l;

    /* renamed from: e */
    private final List<AbstractRunnableC2495ag> f819e = new ArrayList();

    /* renamed from: j */
    private final IBinder.DeathRecipient f824j = new IBinder.DeathRecipient(this) { // from class: com.google.android.play.core.internal.ah

        /* renamed from: a */
        private final C2504ap f807a;

        /* JADX INFO: Access modifiers changed from: package-private */
        {
            this.f807a = this;
        }

        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            this.f807a.m792c();
        }
    };

    /* renamed from: i */
    private final WeakReference<InterfaceC2499ak> f823i = new WeakReference<>(null);

    public C2504ap(Context context, C2494af c2494af, String str, Intent intent, InterfaceC2500al<T> interfaceC2500al) {
        this.f816b = context;
        this.f817c = c2494af;
        this.f818d = str;
        this.f821g = intent;
        this.f822h = interfaceC2500al;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m797a(C2504ap c2504ap, AbstractRunnableC2495ag abstractRunnableC2495ag) {
        if (c2504ap.f826l != null || c2504ap.f820f) {
            if (!c2504ap.f820f) {
                abstractRunnableC2495ag.run();
                return;
            }
            c2504ap.f817c.m805c("Waiting to bind to the service.", new Object[0]);
            c2504ap.f819e.add(abstractRunnableC2495ag);
            return;
        }
        c2504ap.f817c.m805c("Initiate binding to the service.", new Object[0]);
        c2504ap.f819e.add(abstractRunnableC2495ag);
        ServiceConnectionC2503ao serviceConnectionC2503ao = new ServiceConnectionC2503ao(c2504ap);
        c2504ap.f825k = serviceConnectionC2503ao;
        c2504ap.f820f = true;
        if (c2504ap.f816b.bindService(c2504ap.f821g, serviceConnectionC2503ao, 1)) {
            return;
        }
        c2504ap.f817c.m805c("Failed to bind to the service.", new Object[0]);
        c2504ap.f820f = false;
        List<AbstractRunnableC2495ag> list = c2504ap.f819e;
        int size = list.size();
        for (int r1 = 0; r1 < size; r1++) {
            C2682i<?> m803b = list.get(r1).m803b();
            if (m803b != null) {
                m803b.m455b((Exception) new C2505aq());
            }
        }
        c2504ap.f819e.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public final void m795b(AbstractRunnableC2495ag abstractRunnableC2495ag) {
        Handler handler;
        Map<String, Handler> map = f815a;
        synchronized (map) {
            if (!map.containsKey(this.f818d)) {
                HandlerThread handlerThread = new HandlerThread(this.f818d, 10);
                handlerThread.start();
                map.put(this.f818d, new Handler(handlerThread.getLooper()));
            }
            handler = map.get(this.f818d);
        }
        handler.post(abstractRunnableC2495ag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public static /* synthetic */ void m788f(C2504ap c2504ap) {
        c2504ap.f817c.m805c("linkToDeath", new Object[0]);
        try {
            c2504ap.f826l.asBinder().linkToDeath(c2504ap.f824j, 0);
        } catch (RemoteException e) {
            c2504ap.f817c.m807a(e, "linkToDeath failed", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public static /* synthetic */ void m786h(C2504ap c2504ap) {
        c2504ap.f817c.m805c("unlinkToDeath", new Object[0]);
        c2504ap.f826l.asBinder().unlinkToDeath(c2504ap.f824j, 0);
    }

    /* renamed from: a */
    public final void m801a() {
        m795b(new C2498aj(this));
    }

    /* renamed from: a */
    public final void m800a(AbstractRunnableC2495ag abstractRunnableC2495ag) {
        m795b(new C2497ai(this, abstractRunnableC2495ag.m803b(), abstractRunnableC2495ag));
    }

    /* renamed from: b */
    public final T m796b() {
        return this.f826l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final /* bridge */ /* synthetic */ void m792c() {
        this.f817c.m805c("reportBinderDeath", new Object[0]);
        InterfaceC2499ak interfaceC2499ak = this.f823i.get();
        if (interfaceC2499ak != null) {
            this.f817c.m805c("calling onBinderDied", new Object[0]);
            interfaceC2499ak.m802a();
            return;
        }
        this.f817c.m805c("%s : Binder has died.", this.f818d);
        List<AbstractRunnableC2495ag> list = this.f819e;
        int size = list.size();
        for (int r1 = 0; r1 < size; r1++) {
            C2682i<?> m803b = list.get(r1).m803b();
            if (m803b != null) {
                m803b.m455b((Exception) (Build.VERSION.SDK_INT < 15 ? new RemoteException() : new RemoteException(String.valueOf(this.f818d).concat(" : Binder has died."))));
            }
        }
        this.f819e.clear();
    }
}
