package com.google.android.play.core.listener;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2510av;
import com.google.android.play.core.splitcompat.C2608p;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* renamed from: com.google.android.play.core.listener.b */
/* loaded from: classes3.dex */
public abstract class AbstractC2581b<StateT> {

    /* renamed from: a */
    protected final C2494af f868a;

    /* renamed from: c */
    private final IntentFilter f870c;

    /* renamed from: d */
    private final Context f871d;

    /* renamed from: b */
    protected final Set<StateUpdatedListener<StateT>> f869b = new HashSet();

    /* renamed from: e */
    private C2580a f872e = null;

    /* renamed from: f */
    private volatile boolean f873f = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC2581b(C2494af c2494af, IntentFilter intentFilter, Context context) {
        this.f868a = c2494af;
        this.f870c = intentFilter;
        this.f871d = C2608p.m576a(context);
    }

    /* renamed from: c */
    private final void m636c() {
        C2580a c2580a;
        if ((this.f873f || !this.f869b.isEmpty()) && this.f872e == null) {
            C2580a c2580a2 = new C2580a(this);
            this.f872e = c2580a2;
            this.f871d.registerReceiver(c2580a2, this.f870c);
        }
        if (this.f873f || !this.f869b.isEmpty() || (c2580a = this.f872e) == null) {
            return;
        }
        this.f871d.unregisterReceiver(c2580a);
        this.f872e = null;
    }

    /* renamed from: a */
    public final synchronized void m642a() {
        this.f868a.m805c("clearListeners", new Object[0]);
        this.f869b.clear();
        m636c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo507a(Context context, Intent intent);

    /* renamed from: a */
    public final synchronized void m641a(StateUpdatedListener<StateT> stateUpdatedListener) {
        this.f868a.m805c("registerListener", new Object[0]);
        C2510av.m774a(stateUpdatedListener, "Registered Play Core listener should not be null.");
        this.f869b.add(stateUpdatedListener);
        m636c();
    }

    /* renamed from: a */
    public final synchronized void m640a(StateT statet) {
        Iterator it = new HashSet(this.f869b).iterator();
        while (it.hasNext()) {
            ((StateUpdatedListener) it.next()).onStateUpdate(statet);
        }
    }

    /* renamed from: a */
    public final synchronized void m639a(boolean z) {
        this.f873f = z;
        m636c();
    }

    /* renamed from: b */
    public final synchronized void m637b(StateUpdatedListener<StateT> stateUpdatedListener) {
        this.f868a.m805c("unregisterListener", new Object[0]);
        C2510av.m774a(stateUpdatedListener, "Unregistered Play Core listener should not be null.");
        this.f869b.remove(stateUpdatedListener);
        m636c();
    }

    /* renamed from: b */
    public final synchronized boolean m638b() {
        return this.f872e != null;
    }
}
