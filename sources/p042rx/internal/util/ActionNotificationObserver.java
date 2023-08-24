package p042rx.internal.util;

import p042rx.Notification;
import p042rx.Observer;
import p042rx.functions.Action1;

/* renamed from: rx.internal.util.ActionNotificationObserver */
/* loaded from: classes6.dex */
public final class ActionNotificationObserver<T> implements Observer<T> {
    final Action1<Notification<? super T>> onNotification;

    public ActionNotificationObserver(Action1<Notification<? super T>> action1) {
        this.onNotification = action1;
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        this.onNotification.call(Notification.createOnNext(t));
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        this.onNotification.call(Notification.createOnError(th));
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        this.onNotification.call(Notification.createOnCompleted());
    }
}
