package p042rx.subjects;

import p042rx.Observable;
import p042rx.Observer;

/* renamed from: rx.subjects.Subject */
/* loaded from: classes6.dex */
public abstract class Subject<T, R> extends Observable<R> implements Observer<T> {
    public abstract boolean hasObservers();

    /* JADX INFO: Access modifiers changed from: protected */
    public Subject(Observable.OnSubscribe<R> onSubscribe) {
        super(onSubscribe);
    }

    public final SerializedSubject<T, R> toSerialized() {
        if (getClass() == SerializedSubject.class) {
            return (SerializedSubject) this;
        }
        return new SerializedSubject<>(this);
    }
}
