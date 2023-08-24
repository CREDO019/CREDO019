package p042rx;

/* renamed from: rx.Observer */
/* loaded from: classes4.dex */
public interface Observer<T> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(T t);
}
