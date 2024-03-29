package p042rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func0;
import p042rx.functions.Func1;

/* renamed from: rx.internal.operators.OnSubscribeToMultimap */
/* loaded from: classes6.dex */
public final class OnSubscribeToMultimap<T, K, V> implements Observable.OnSubscribe<Map<K, Collection<V>>>, Func0<Map<K, Collection<V>>> {
    private final Func1<? super K, ? extends Collection<V>> collectionFactory;
    private final Func1<? super T, ? extends K> keySelector;
    private final Func0<? extends Map<K, Collection<V>>> mapFactory;
    private final Observable<T> source;
    private final Func1<? super T, ? extends V> valueSelector;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeToMultimap(Observable<T> observable, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(observable, func1, func12, null, DefaultMultimapCollectionFactory.instance());
    }

    public OnSubscribeToMultimap(Observable<T> observable, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, Collection<V>>> func0) {
        this(observable, func1, func12, func0, DefaultMultimapCollectionFactory.instance());
    }

    public OnSubscribeToMultimap(Observable<T> observable, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, Collection<V>>> func0, Func1<? super K, ? extends Collection<V>> func13) {
        this.source = observable;
        this.keySelector = func1;
        this.valueSelector = func12;
        if (func0 == null) {
            this.mapFactory = this;
        } else {
            this.mapFactory = func0;
        }
        this.collectionFactory = func13;
    }

    @Override // p042rx.functions.Func0, java.util.concurrent.Callable
    public Map<K, Collection<V>> call() {
        return new HashMap();
    }

    public void call(Subscriber<? super Map<K, Collection<V>>> subscriber) {
        try {
            new ToMultimapSubscriber(subscriber, this.mapFactory.call(), this.keySelector, this.valueSelector, this.collectionFactory).subscribeTo(this.source);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: rx.internal.operators.OnSubscribeToMultimap$ToMultimapSubscriber */
    /* loaded from: classes6.dex */
    public static final class ToMultimapSubscriber<T, K, V> extends DeferredScalarSubscriberSafe<T, Map<K, Collection<V>>> {
        private final Func1<? super K, ? extends Collection<V>> collectionFactory;
        private final Func1<? super T, ? extends K> keySelector;
        private final Func1<? super T, ? extends V> valueSelector;

        /* JADX WARN: Multi-variable type inference failed */
        ToMultimapSubscriber(Subscriber<? super Map<K, Collection<V>>> subscriber, Map<K, Collection<V>> map, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func1<? super K, ? extends Collection<V>> func13) {
            super(subscriber);
            this.value = map;
            this.hasValue = true;
            this.keySelector = func1;
            this.valueSelector = func12;
            this.collectionFactory = func13;
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                K call = this.keySelector.call(t);
                V call2 = this.valueSelector.call(t);
                Collection<V> collection = (Collection) ((Map) this.value).get(call);
                if (collection == null) {
                    collection = this.collectionFactory.call(call);
                    ((Map) this.value).put(call, collection);
                }
                collection.add(call2);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }
    }

    /* renamed from: rx.internal.operators.OnSubscribeToMultimap$DefaultMultimapCollectionFactory */
    /* loaded from: classes6.dex */
    private static final class DefaultMultimapCollectionFactory<K, V> implements Func1<K, Collection<V>> {
        private static final DefaultMultimapCollectionFactory<Object, Object> INSTANCE = new DefaultMultimapCollectionFactory<>();

        private DefaultMultimapCollectionFactory() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p042rx.functions.Func1
        public /* bridge */ /* synthetic */ Object call(Object obj) {
            return call((DefaultMultimapCollectionFactory<K, V>) obj);
        }

        static <K, V> DefaultMultimapCollectionFactory<K, V> instance() {
            return (DefaultMultimapCollectionFactory<K, V>) INSTANCE;
        }

        @Override // p042rx.functions.Func1
        public Collection<V> call(K k) {
            return new ArrayList();
        }
    }
}
