package com.polidea.rxandroidble.internal.scan;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.firebase.messaging.ServiceStarter;
import com.polidea.rxandroidble.internal.RxBleLog;
import com.polidea.rxandroidble.internal.util.ObservableUtil;
import com.polidea.rxandroidble.scan.ScanCallbackType;
import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.functions.Func0;
import p042rx.functions.Func1;
import p042rx.observables.GroupedObservable;

/* loaded from: classes3.dex */
public class ScanSettingsEmulator {
    private Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateFirstMatch;
    private final Scheduler scheduler;
    private Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateMatchLost = new Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.5
        @Override // p042rx.functions.Func1
        public Observable<RxBleInternalScanResult> call(Observable<RxBleInternalScanResult> observable) {
            return observable.debounce(10L, TimeUnit.SECONDS, ScanSettingsEmulator.this.scheduler).map(ScanSettingsEmulator.this.toMatchLost());
        }
    };
    private Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateFirstMatchAndMatchLost = new Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.7
        @Override // p042rx.functions.Func1
        public Observable<RxBleInternalScanResult> call(Observable<RxBleInternalScanResult> observable) {
            return observable.publish(new Func1<Observable<RxBleInternalScanResult>, Observable<RxBleInternalScanResult>>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.7.1
                @Override // p042rx.functions.Func1
                public Observable<RxBleInternalScanResult> call(Observable<RxBleInternalScanResult> observable2) {
                    return Observable.merge(observable2.compose(ScanSettingsEmulator.this.emulateFirstMatch), observable2.compose(ScanSettingsEmulator.this.emulateMatchLost));
                }
            });
        }
    };

    @Inject
    public ScanSettingsEmulator(@Named("computation") Scheduler scheduler) {
        this.scheduler = scheduler;
        this.emulateFirstMatch = new C39151(scheduler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator$1 */
    /* loaded from: classes3.dex */
    public class C39151 implements Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> {
        private final Func1<RxBleInternalScanResult, Observable<?>> emitAfterTimerFunc = new Func1<RxBleInternalScanResult, Observable<?>>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.1.1
            @Override // p042rx.functions.Func1
            public Observable<?> call(RxBleInternalScanResult rxBleInternalScanResult) {
                return C39151.this.timerObservable;
            }
        };
        private final Func1<Observable<RxBleInternalScanResult>, Observable<RxBleInternalScanResult>> takeFirstFromEachWindowFunc = new Func1<Observable<RxBleInternalScanResult>, Observable<RxBleInternalScanResult>>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.1.2
            @Override // p042rx.functions.Func1
            public Observable<RxBleInternalScanResult> call(Observable<RxBleInternalScanResult> observable) {
                return observable.take(1);
            }
        };
        private final Observable<Long> timerObservable;
        private Func1<RxBleInternalScanResult, RxBleInternalScanResult> toFirstMatchFunc;
        final /* synthetic */ Scheduler val$scheduler;

        C39151(Scheduler scheduler) {
            this.val$scheduler = scheduler;
            this.toFirstMatchFunc = ScanSettingsEmulator.this.toFirstMatch();
            this.timerObservable = Observable.timer(10L, TimeUnit.SECONDS, scheduler);
        }

        @Override // p042rx.functions.Func1
        public Observable<RxBleInternalScanResult> call(Observable<RxBleInternalScanResult> observable) {
            return observable.publish(new Func1<Observable<RxBleInternalScanResult>, Observable<RxBleInternalScanResult>>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.1.3
                @Override // p042rx.functions.Func1
                public Observable<RxBleInternalScanResult> call(final Observable<RxBleInternalScanResult> observable2) {
                    return observable2.window(new Func0<Observable<?>>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.1.3.1
                        @Override // p042rx.functions.Func0, java.util.concurrent.Callable
                        public Observable<?> call() {
                            return observable2.switchMap(C39151.this.emitAfterTimerFunc);
                        }
                    }).flatMap(C39151.this.takeFirstFromEachWindowFunc).map(C39151.this.toFirstMatchFunc);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateScanMode(int r2) {
        if (r2 == -1) {
            RxBleLog.m243d("Cannot emulate opportunistic scan mode since it is OS dependent - fallthrough to low power", new Object[0]);
        } else if (r2 != 0) {
            if (r2 == 1) {
                return scanModeBalancedTransformer();
            }
            return ObservableUtil.identityTransformer();
        }
        return scanModeLowPowerTransformer();
    }

    private Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> scanModeBalancedTransformer() {
        return repeatedWindowTransformer(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS);
    }

    private Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> scanModeLowPowerTransformer() {
        return repeatedWindowTransformer(ServiceStarter.ERROR_UNKNOWN);
    }

    private Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> repeatedWindowTransformer(final int r5) {
        final long max = Math.max(TimeUnit.SECONDS.toMillis(5L) - r5, 0L);
        return new Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.2
            @Override // p042rx.functions.Func1
            public Observable<RxBleInternalScanResult> call(Observable<RxBleInternalScanResult> observable) {
                return observable.take(r5, TimeUnit.MILLISECONDS, ScanSettingsEmulator.this.scheduler).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.2.1
                    @Override // p042rx.functions.Func1
                    public Observable<?> call(Observable<? extends Void> observable2) {
                        return observable2.delay(max, TimeUnit.MILLISECONDS, ScanSettingsEmulator.this.scheduler);
                    }
                });
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateCallbackType(int r2) {
        if (r2 != 2) {
            if (r2 != 4) {
                if (r2 == 6) {
                    return splitByAddressAndForEach(this.emulateFirstMatchAndMatchLost);
                }
                return ObservableUtil.identityTransformer();
            }
            return splitByAddressAndForEach(this.emulateMatchLost);
        }
        return splitByAddressAndForEach(this.emulateFirstMatch);
    }

    private Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> splitByAddressAndForEach(final Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> transformer) {
        return new Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.3
            @Override // p042rx.functions.Func1
            public Observable<RxBleInternalScanResult> call(Observable<RxBleInternalScanResult> observable) {
                return observable.groupBy(new Func1<RxBleInternalScanResult, String>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.3.2
                    @Override // p042rx.functions.Func1
                    public String call(RxBleInternalScanResult rxBleInternalScanResult) {
                        return rxBleInternalScanResult.getBluetoothDevice().getAddress();
                    }
                }).flatMap(new Func1<GroupedObservable<String, RxBleInternalScanResult>, Observable<RxBleInternalScanResult>>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.3.1
                    @Override // p042rx.functions.Func1
                    public Observable<RxBleInternalScanResult> call(GroupedObservable<String, RxBleInternalScanResult> groupedObservable) {
                        return groupedObservable.compose(transformer);
                    }
                });
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Func1<RxBleInternalScanResult, RxBleInternalScanResult> toFirstMatch() {
        return new Func1<RxBleInternalScanResult, RxBleInternalScanResult>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.4
            @Override // p042rx.functions.Func1
            public RxBleInternalScanResult call(RxBleInternalScanResult rxBleInternalScanResult) {
                return new RxBleInternalScanResult(rxBleInternalScanResult.getBluetoothDevice(), rxBleInternalScanResult.getRssi(), rxBleInternalScanResult.getTimestampNanos(), rxBleInternalScanResult.getScanRecord(), ScanCallbackType.CALLBACK_TYPE_FIRST_MATCH);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Func1<RxBleInternalScanResult, RxBleInternalScanResult> toMatchLost() {
        return new Func1<RxBleInternalScanResult, RxBleInternalScanResult>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator.6
            @Override // p042rx.functions.Func1
            public RxBleInternalScanResult call(RxBleInternalScanResult rxBleInternalScanResult) {
                return new RxBleInternalScanResult(rxBleInternalScanResult.getBluetoothDevice(), rxBleInternalScanResult.getRssi(), rxBleInternalScanResult.getTimestampNanos(), rxBleInternalScanResult.getScanRecord(), ScanCallbackType.CALLBACK_TYPE_MATCH_LOST);
            }
        };
    }
}
