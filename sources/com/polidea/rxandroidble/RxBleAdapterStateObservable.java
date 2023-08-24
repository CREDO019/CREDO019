package com.polidea.rxandroidble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import bleshadow.javax.inject.Inject;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.functions.Action1;
import p042rx.functions.Cancellable;
import p042rx.internal.operators.OnSubscribeCreate;

/* loaded from: classes3.dex */
public class RxBleAdapterStateObservable extends Observable<BleAdapterState> {
    static /* synthetic */ IntentFilter access$100() {
        return createFilter();
    }

    /* loaded from: classes3.dex */
    public static class BleAdapterState {
        private final boolean isUsable;
        public static final BleAdapterState STATE_ON = new BleAdapterState(true);
        public static final BleAdapterState STATE_OFF = new BleAdapterState(false);
        public static final BleAdapterState STATE_TURNING_ON = new BleAdapterState(false);
        public static final BleAdapterState STATE_TURNING_OFF = new BleAdapterState(false);

        private BleAdapterState(boolean z) {
            this.isUsable = z;
        }

        public boolean isUsable() {
            return this.isUsable;
        }
    }

    @Inject
    public RxBleAdapterStateObservable(final Context context) {
        super(new OnSubscribeCreate(new Action1<Emitter<BleAdapterState>>() { // from class: com.polidea.rxandroidble.RxBleAdapterStateObservable.1
            @Override // p042rx.functions.Action1
            public void call(final Emitter<BleAdapterState> emitter) {
                final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.polidea.rxandroidble.RxBleAdapterStateObservable.1.1
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context2, Intent intent) {
                        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                            emitter.onNext(RxBleAdapterStateObservable.mapToBleAdapterState(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1)));
                        }
                    }
                };
                context.registerReceiver(broadcastReceiver, RxBleAdapterStateObservable.access$100());
                emitter.setCancellation(new Cancellable() { // from class: com.polidea.rxandroidble.RxBleAdapterStateObservable.1.2
                    @Override // p042rx.functions.Cancellable
                    public void cancel() throws Exception {
                        context.unregisterReceiver(broadcastReceiver);
                    }
                });
            }
        }, Emitter.BackpressureMode.LATEST));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BleAdapterState mapToBleAdapterState(int r0) {
        switch (r0) {
            case 11:
                return BleAdapterState.STATE_TURNING_ON;
            case 12:
                return BleAdapterState.STATE_ON;
            case 13:
                return BleAdapterState.STATE_TURNING_OFF;
            default:
                return BleAdapterState.STATE_OFF;
        }
    }

    private static IntentFilter createFilter() {
        return new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
    }
}
