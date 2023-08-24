package p042rx.internal.operators;

import p042rx.Notification;
import p042rx.Observable;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.OperatorDematerialize */
/* loaded from: classes6.dex */
public final class OperatorDematerialize<T> implements Observable.Operator<T, Notification<T>> {
    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorDematerialize$Holder */
    /* loaded from: classes6.dex */
    public static final class Holder {
        static final OperatorDematerialize<Object> INSTANCE = new OperatorDematerialize<>();

        Holder() {
        }
    }

    public static OperatorDematerialize instance() {
        return Holder.INSTANCE;
    }

    OperatorDematerialize() {
    }

    public Subscriber<? super Notification<T>> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<Notification<T>>(subscriber) { // from class: rx.internal.operators.OperatorDematerialize.1
            boolean terminated;

            @Override // p042rx.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((Notification) ((Notification) obj));
            }

            public void onNext(Notification<T> notification) {
                int r0 = C56212.$SwitchMap$rx$Notification$Kind[notification.getKind().ordinal()];
                if (r0 == 1) {
                    if (this.terminated) {
                        return;
                    }
                    subscriber.onNext(notification.getValue());
                } else if (r0 == 2) {
                    onError(notification.getThrowable());
                } else if (r0 == 3) {
                    onCompleted();
                } else {
                    onError(new IllegalArgumentException("Unsupported notification type: " + notification));
                }
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                subscriber.onError(th);
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                subscriber.onCompleted();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorDematerialize$2 */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class C56212 {
        static final /* synthetic */ int[] $SwitchMap$rx$Notification$Kind;

        static {
            int[] r0 = new int[Notification.Kind.values().length];
            $SwitchMap$rx$Notification$Kind = r0;
            try {
                r0[Notification.Kind.OnNext.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$rx$Notification$Kind[Notification.Kind.OnError.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$rx$Notification$Kind[Notification.Kind.OnCompleted.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
