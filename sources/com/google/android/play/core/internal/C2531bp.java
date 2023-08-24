package com.google.android.play.core.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

/* renamed from: com.google.android.play.core.internal.bp */
/* loaded from: classes3.dex */
public final class C2531bp<T> {

    /* renamed from: a */
    private final Object f838a;

    /* renamed from: b */
    private final Field f839b;

    /* renamed from: c */
    private final Class<T> f840c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2531bp(Object obj, Field field, Class<T> cls) {
        this.f838a = obj;
        this.f839b = field;
        this.f840c = cls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2531bp(Object obj, Field field, Class cls, byte[] bArr) {
        this(obj, field, Array.newInstance(cls, 0).getClass());
    }

    /* renamed from: c */
    private Class m751c() {
        return m753b().getType().getComponentType();
    }

    /* renamed from: a */
    public final T m756a() {
        try {
            return this.f840c.cast(this.f839b.get(this.f838a));
        } catch (Exception e) {
            throw new C2533br(String.format("Failed to get value of field %s of type %s on object of type %s", this.f839b.getName(), this.f838a.getClass().getName(), this.f840c.getName()), e);
        }
    }

    /* renamed from: a */
    public final void m755a(T t) {
        try {
            this.f839b.set(this.f838a, t);
        } catch (Exception e) {
            throw new C2533br(String.format("Failed to set value of field %s of type %s on object of type %s", this.f839b.getName(), this.f838a.getClass().getName(), this.f840c.getName()), e);
        }
    }

    /* renamed from: a */
    public void m754a(Collection collection) {
        Object[] objArr = (Object[]) m756a();
        int length = objArr == null ? 0 : objArr.length;
        Object[] objArr2 = (Object[]) Array.newInstance(m751c(), collection.size() + length);
        if (objArr != null) {
            System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        }
        for (Object obj : collection) {
            objArr2[length] = obj;
            length++;
        }
        m755a((C2531bp<T>) objArr2);
    }

    /* renamed from: b */
    protected final Field m753b() {
        return this.f839b;
    }

    /* renamed from: b */
    public void m752b(Collection collection) {
        Object[] objArr = (Object[]) m756a();
        int r1 = 0;
        Object[] objArr2 = (Object[]) Array.newInstance(m751c(), (objArr == null ? 0 : objArr.length) + collection.size());
        if (objArr != null) {
            System.arraycopy(objArr, 0, objArr2, collection.size(), objArr.length);
        }
        for (Object obj : collection) {
            objArr2[r1] = obj;
            r1++;
        }
        m755a((C2531bp<T>) objArr2);
    }
}
