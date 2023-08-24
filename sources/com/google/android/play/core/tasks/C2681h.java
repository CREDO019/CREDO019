package com.google.android.play.core.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.tasks.h */
/* loaded from: classes3.dex */
public final class C2681h<ResultT> {

    /* renamed from: a */
    private final Object f1107a = new Object();

    /* renamed from: b */
    private Queue<InterfaceC2680g<ResultT>> f1108b;

    /* renamed from: c */
    private boolean f1109c;

    /* renamed from: a */
    public final void m460a(Task<ResultT> task) {
        InterfaceC2680g<ResultT> poll;
        synchronized (this.f1107a) {
            if (this.f1108b != null && !this.f1109c) {
                this.f1109c = true;
                while (true) {
                    synchronized (this.f1107a) {
                        poll = this.f1108b.poll();
                        if (poll == null) {
                            this.f1109c = false;
                            return;
                        }
                    }
                    poll.mo461a(task);
                }
            }
        }
    }

    /* renamed from: a */
    public final void m459a(InterfaceC2680g<ResultT> interfaceC2680g) {
        synchronized (this.f1107a) {
            if (this.f1108b == null) {
                this.f1108b = new ArrayDeque();
            }
            this.f1108b.add(interfaceC2680g);
        }
    }
}
