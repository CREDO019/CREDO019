package com.google.android.play.core.tasks;

import com.google.android.play.core.internal.C2510av;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.play.core.tasks.m */
/* loaded from: classes3.dex */
final class C2686m<ResultT> extends Task<ResultT> {

    /* renamed from: a */
    private final Object f1112a = new Object();

    /* renamed from: b */
    private final C2681h<ResultT> f1113b = new C2681h<>();

    /* renamed from: c */
    private boolean f1114c;

    /* renamed from: d */
    private ResultT f1115d;

    /* renamed from: e */
    private Exception f1116e;

    /* renamed from: a */
    private final void m453a() {
        C2510av.m773a(this.f1114c, "Task is not yet complete");
    }

    /* renamed from: b */
    private final void m450b() {
        C2510av.m773a(!this.f1114c, "Task is already complete");
    }

    /* renamed from: c */
    private final void m447c() {
        synchronized (this.f1112a) {
            if (this.f1114c) {
                this.f1113b.m460a(this);
            }
        }
    }

    /* renamed from: a */
    public final void m452a(Exception exc) {
        synchronized (this.f1112a) {
            m450b();
            this.f1114c = true;
            this.f1116e = exc;
        }
        this.f1113b.m460a(this);
    }

    /* renamed from: a */
    public final void m451a(ResultT resultt) {
        synchronized (this.f1112a) {
            m450b();
            this.f1114c = true;
            this.f1115d = resultt;
        }
        this.f1113b.m460a(this);
    }

    @Override // com.google.android.play.core.tasks.Task
    public final Task<ResultT> addOnCompleteListener(OnCompleteListener<ResultT> onCompleteListener) {
        this.f1113b.m459a(new C2675b(TaskExecutors.MAIN_THREAD, onCompleteListener));
        m447c();
        return this;
    }

    @Override // com.google.android.play.core.tasks.Task
    public final Task<ResultT> addOnCompleteListener(Executor executor, OnCompleteListener<ResultT> onCompleteListener) {
        this.f1113b.m459a(new C2675b(executor, onCompleteListener));
        m447c();
        return this;
    }

    @Override // com.google.android.play.core.tasks.Task
    public final Task<ResultT> addOnFailureListener(OnFailureListener onFailureListener) {
        addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
        return this;
    }

    @Override // com.google.android.play.core.tasks.Task
    public final Task<ResultT> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        this.f1113b.m459a(new C2677d(executor, onFailureListener));
        m447c();
        return this;
    }

    @Override // com.google.android.play.core.tasks.Task
    public final Task<ResultT> addOnSuccessListener(OnSuccessListener<? super ResultT> onSuccessListener) {
        addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
        return this;
    }

    @Override // com.google.android.play.core.tasks.Task
    public final Task<ResultT> addOnSuccessListener(Executor executor, OnSuccessListener<? super ResultT> onSuccessListener) {
        this.f1113b.m459a(new C2679f(executor, onSuccessListener));
        m447c();
        return this;
    }

    /* renamed from: b */
    public final boolean m449b(Exception exc) {
        synchronized (this.f1112a) {
            if (this.f1114c) {
                return false;
            }
            this.f1114c = true;
            this.f1116e = exc;
            this.f1113b.m460a(this);
            return true;
        }
    }

    /* renamed from: b */
    public final boolean m448b(ResultT resultt) {
        synchronized (this.f1112a) {
            if (this.f1114c) {
                return false;
            }
            this.f1114c = true;
            this.f1115d = resultt;
            this.f1113b.m460a(this);
            return true;
        }
    }

    @Override // com.google.android.play.core.tasks.Task
    public final Exception getException() {
        Exception exc;
        synchronized (this.f1112a) {
            exc = this.f1116e;
        }
        return exc;
    }

    @Override // com.google.android.play.core.tasks.Task
    public final ResultT getResult() {
        ResultT resultt;
        synchronized (this.f1112a) {
            m453a();
            Exception exc = this.f1116e;
            if (exc != null) {
                throw new RuntimeExecutionException(exc);
            }
            resultt = this.f1115d;
        }
        return resultt;
    }

    @Override // com.google.android.play.core.tasks.Task
    public final <X extends Throwable> ResultT getResult(Class<X> cls) throws Throwable {
        ResultT resultt;
        synchronized (this.f1112a) {
            m453a();
            if (cls.isInstance(this.f1116e)) {
                throw cls.cast(this.f1116e);
            }
            Exception exc = this.f1116e;
            if (exc != null) {
                throw new RuntimeExecutionException(exc);
            }
            resultt = this.f1115d;
        }
        return resultt;
    }

    @Override // com.google.android.play.core.tasks.Task
    public final boolean isComplete() {
        boolean z;
        synchronized (this.f1112a) {
            z = this.f1114c;
        }
        return z;
    }

    @Override // com.google.android.play.core.tasks.Task
    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.f1112a) {
            z = false;
            if (this.f1114c && this.f1116e == null) {
                z = true;
            }
        }
        return z;
    }
}
