package com.onesignal;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSObservable<ObserverType, StateType> {
    private boolean fireOnMainThread;
    private String methodName;
    private List<Object> observers = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSObservable(String str, boolean z) {
        this.methodName = str;
        this.fireOnMainThread = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addObserver(ObserverType observertype) {
        this.observers.add(new WeakReference(observertype));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addObserverStrong(ObserverType observertype) {
        this.observers.add(observertype);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeObserver(ObserverType observertype) {
        for (int r0 = 0; r0 < this.observers.size(); r0++) {
            Object obj = ((WeakReference) this.observers.get(r0)).get();
            if (obj != null && obj.equals(observertype)) {
                this.observers.remove(r0);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean notifyChange(final StateType statetype) {
        Iterator<Object> it = this.observers.iterator();
        boolean z = false;
        while (it.hasNext()) {
            final Object next = it.next();
            if (next instanceof WeakReference) {
                next = ((WeakReference) next).get();
            }
            if (next != null) {
                try {
                    final Method declaredMethod = next.getClass().getDeclaredMethod(this.methodName, statetype.getClass());
                    declaredMethod.setAccessible(true);
                    if (this.fireOnMainThread) {
                        OSUtils.runOnMainUIThread(new Runnable() { // from class: com.onesignal.OSObservable.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    declaredMethod.invoke(next, statetype);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        });
                    } else {
                        try {
                            declaredMethod.invoke(next, statetype);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e2) {
                            e2.printStackTrace();
                        }
                    }
                    z = true;
                } catch (NoSuchMethodException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return z;
    }
}
