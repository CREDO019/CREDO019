package com.google.android.play.core.internal;

/* renamed from: com.google.android.play.core.internal.cl */
/* loaded from: classes3.dex */
public final class C2554cl<T> implements InterfaceC2556cn, InterfaceC2552cj {

    /* renamed from: a */
    private static final Object f855a = new Object();

    /* renamed from: b */
    private volatile InterfaceC2556cn<T> f856b;

    /* renamed from: c */
    private volatile Object f857c = f855a;

    private C2554cl(InterfaceC2556cn<T> interfaceC2556cn) {
        this.f856b = interfaceC2556cn;
    }

    /* renamed from: a */
    public static <P extends InterfaceC2556cn<T>, T> InterfaceC2556cn<T> m711a(P p) {
        C2532bq.m745a(p);
        return p instanceof C2554cl ? p : new C2554cl(p);
    }

    /* renamed from: b */
    public static <P extends InterfaceC2556cn<T>, T> InterfaceC2552cj<T> m710b(P p) {
        if (p instanceof InterfaceC2552cj) {
            return (InterfaceC2552cj) p;
        }
        C2532bq.m745a(p);
        return new C2554cl(p);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final T mo473a() {
        T t = (T) this.f857c;
        Object obj = f855a;
        if (t == obj) {
            synchronized (this) {
                t = this.f857c;
                if (t == obj) {
                    t = this.f856b.mo473a();
                    Object obj2 = this.f857c;
                    if (obj2 != obj && !(obj2 instanceof C2555cm) && obj2 != t) {
                        String valueOf = String.valueOf(obj2);
                        String valueOf2 = String.valueOf(t);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 118 + String.valueOf(valueOf2).length());
                        sb.append("Scoped provider was invoked recursively returning different results: ");
                        sb.append(valueOf);
                        sb.append(" & ");
                        sb.append(valueOf2);
                        sb.append(". This is likely due to a circular dependency.");
                        throw new IllegalStateException(sb.toString());
                    }
                    this.f857c = t;
                    this.f856b = null;
                }
            }
        }
        return t;
    }
}
