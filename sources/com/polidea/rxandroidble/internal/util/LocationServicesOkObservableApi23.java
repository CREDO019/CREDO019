package com.polidea.rxandroidble.internal.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import bleshadow.javax.inject.Inject;
import java.util.concurrent.atomic.AtomicBoolean;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.functions.Action1;
import p042rx.functions.Cancellable;
import p042rx.internal.operators.OnSubscribeCreate;

/* loaded from: classes3.dex */
public class LocationServicesOkObservableApi23 extends Observable<Boolean> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public LocationServicesOkObservableApi23(final Context context, final LocationServicesStatus locationServicesStatus) {
        super(new OnSubscribeCreate(new Action1<Emitter<Boolean>>() { // from class: com.polidea.rxandroidble.internal.util.LocationServicesOkObservableApi23.1
            @Override // p042rx.functions.Action1
            public void call(final Emitter<Boolean> emitter) {
                boolean isLocationProviderOk = LocationServicesStatus.this.isLocationProviderOk();
                final AtomicBoolean atomicBoolean = new AtomicBoolean(isLocationProviderOk);
                emitter.onNext(Boolean.valueOf(isLocationProviderOk));
                final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.polidea.rxandroidble.internal.util.LocationServicesOkObservableApi23.1.1
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context2, Intent intent) {
                        boolean isLocationProviderOk2 = LocationServicesStatus.this.isLocationProviderOk();
                        if (atomicBoolean.compareAndSet(!isLocationProviderOk2, isLocationProviderOk2)) {
                            emitter.onNext(Boolean.valueOf(isLocationProviderOk2));
                        }
                    }
                };
                context.registerReceiver(broadcastReceiver, new IntentFilter("android.location.MODE_CHANGED"));
                emitter.setCancellation(new Cancellable() { // from class: com.polidea.rxandroidble.internal.util.LocationServicesOkObservableApi23.1.2
                    @Override // p042rx.functions.Cancellable
                    public void cancel() throws Exception {
                        context.unregisterReceiver(broadcastReceiver);
                    }
                });
            }
        }, Emitter.BackpressureMode.LATEST));
    }
}
