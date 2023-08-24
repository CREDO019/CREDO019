package com.polidea.rxandroidble.helpers;

import android.content.Context;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.ClientComponent;
import com.polidea.rxandroidble.DaggerClientComponent;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.functions.Action1;
import p042rx.internal.operators.OnSubscribeCreate;

/* loaded from: classes3.dex */
public class LocationServicesOkObservable extends Observable<Boolean> {
    public static LocationServicesOkObservable createInstance(Context context) {
        return DaggerClientComponent.builder().clientModule(new ClientComponent.ClientModule(context)).build().locationServicesOkObservable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public LocationServicesOkObservable(@Named("location-ok-boolean-observable") final Observable<Boolean> observable) {
        super(new OnSubscribeCreate(new Action1<Emitter<Boolean>>() { // from class: com.polidea.rxandroidble.helpers.LocationServicesOkObservable.1
            @Override // p042rx.functions.Action1
            public void call(Emitter<Boolean> emitter) {
                emitter.setSubscription(Observable.this.subscribe(emitter));
            }
        }, Emitter.BackpressureMode.LATEST));
    }
}
