package com.google.android.play.core.internal;

import com.google.android.play.core.listener.StateUpdatedListener;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.google.android.play.core.internal.ae */
/* loaded from: classes3.dex */
public final class C2493ae<StateT> {

    /* renamed from: a */
    protected final Set<StateUpdatedListener<StateT>> f804a = new HashSet();

    /* renamed from: a */
    public final synchronized void m813a(StateUpdatedListener<StateT> stateUpdatedListener) {
        this.f804a.add(stateUpdatedListener);
    }

    /* renamed from: a */
    public final synchronized void m812a(StateT statet) {
        for (StateUpdatedListener<StateT> stateUpdatedListener : this.f804a) {
            stateUpdatedListener.onStateUpdate(statet);
        }
    }

    /* renamed from: b */
    public final synchronized void m811b(StateUpdatedListener<StateT> stateUpdatedListener) {
        this.f804a.remove(stateUpdatedListener);
    }
}
