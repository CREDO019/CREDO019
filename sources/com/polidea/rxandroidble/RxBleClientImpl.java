package com.polidea.rxandroidble;

import android.bluetooth.BluetoothDevice;
import bleshadow.dagger.Lazy;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.ClientComponent;
import com.polidea.rxandroidble.RxBleAdapterStateObservable;
import com.polidea.rxandroidble.RxBleClient;
import com.polidea.rxandroidble.exceptions.BleScanException;
import com.polidea.rxandroidble.internal.RxBleDeviceProvider;
import com.polidea.rxandroidble.internal.operations.LegacyScanOperation;
import com.polidea.rxandroidble.internal.scan.RxBleInternalScanResult;
import com.polidea.rxandroidble.internal.scan.RxBleInternalScanResultLegacy;
import com.polidea.rxandroidble.internal.scan.ScanPreconditionsVerifier;
import com.polidea.rxandroidble.internal.scan.ScanSetup;
import com.polidea.rxandroidble.internal.scan.ScanSetupBuilder;
import com.polidea.rxandroidble.internal.serialization.ClientOperationQueue;
import com.polidea.rxandroidble.internal.util.ClientStateObservable;
import com.polidea.rxandroidble.internal.util.LocationServicesStatus;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble.internal.util.UUIDUtil;
import com.polidea.rxandroidble.scan.ScanFilter;
import com.polidea.rxandroidble.scan.ScanResult;
import com.polidea.rxandroidble.scan.ScanSettings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.functions.Action0;
import p042rx.functions.Func0;
import p042rx.functions.Func1;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class RxBleClientImpl extends RxBleClient {
    private final Scheduler bluetoothInteractionScheduler;
    private final ClientComponent.ClientComponentFinalizer clientComponentFinalizer;
    private final Func1<RxBleInternalScanResult, ScanResult> internalToExternalScanResultMapFunction;
    private final Lazy<ClientStateObservable> lazyClientStateObservable;
    private final LocationServicesStatus locationServicesStatus;
    private final ClientOperationQueue operationQueue;
    private final Map<Set<UUID>, Observable<RxBleScanResult>> queuedScanOperations = new HashMap();
    private final Observable<RxBleAdapterStateObservable.BleAdapterState> rxBleAdapterStateObservable;
    private final RxBleAdapterWrapper rxBleAdapterWrapper;
    private final RxBleDeviceProvider rxBleDeviceProvider;
    private final ScanPreconditionsVerifier scanPreconditionVerifier;
    private final ScanSetupBuilder scanSetupBuilder;
    private final UUIDUtil uuidUtil;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public RxBleClientImpl(RxBleAdapterWrapper rxBleAdapterWrapper, ClientOperationQueue clientOperationQueue, Observable<RxBleAdapterStateObservable.BleAdapterState> observable, UUIDUtil uUIDUtil, LocationServicesStatus locationServicesStatus, Lazy<ClientStateObservable> lazy, RxBleDeviceProvider rxBleDeviceProvider, ScanSetupBuilder scanSetupBuilder, ScanPreconditionsVerifier scanPreconditionsVerifier, Func1<RxBleInternalScanResult, ScanResult> func1, @Named("bluetooth_interaction") Scheduler scheduler, ClientComponent.ClientComponentFinalizer clientComponentFinalizer) {
        this.uuidUtil = uUIDUtil;
        this.operationQueue = clientOperationQueue;
        this.rxBleAdapterWrapper = rxBleAdapterWrapper;
        this.rxBleAdapterStateObservable = observable;
        this.locationServicesStatus = locationServicesStatus;
        this.lazyClientStateObservable = lazy;
        this.rxBleDeviceProvider = rxBleDeviceProvider;
        this.scanSetupBuilder = scanSetupBuilder;
        this.scanPreconditionVerifier = scanPreconditionsVerifier;
        this.internalToExternalScanResultMapFunction = func1;
        this.bluetoothInteractionScheduler = scheduler;
        this.clientComponentFinalizer = clientComponentFinalizer;
    }

    protected void finalize() throws Throwable {
        this.clientComponentFinalizer.onFinalize();
        super.finalize();
    }

    @Override // com.polidea.rxandroidble.RxBleClient
    public RxBleDevice getBleDevice(String str) {
        guardBluetoothAdapterAvailable();
        return this.rxBleDeviceProvider.getBleDevice(str);
    }

    @Override // com.polidea.rxandroidble.RxBleClient
    public Set<RxBleDevice> getBondedDevices() {
        guardBluetoothAdapterAvailable();
        HashSet hashSet = new HashSet();
        for (BluetoothDevice bluetoothDevice : this.rxBleAdapterWrapper.getBondedDevices()) {
            hashSet.add(getBleDevice(bluetoothDevice.getAddress()));
        }
        return hashSet;
    }

    @Override // com.polidea.rxandroidble.RxBleClient
    public Observable<ScanResult> scanBleDevices(final ScanSettings scanSettings, final ScanFilter... scanFilterArr) {
        return Observable.defer(new Func0<Observable<ScanResult>>() { // from class: com.polidea.rxandroidble.RxBleClientImpl.1
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public Observable<ScanResult> call() {
                RxBleClientImpl.this.scanPreconditionVerifier.verify();
                ScanSetup build = RxBleClientImpl.this.scanSetupBuilder.build(scanSettings, scanFilterArr);
                return RxBleClientImpl.this.operationQueue.queue(build.scanOperation).unsubscribeOn(RxBleClientImpl.this.bluetoothInteractionScheduler).compose(build.scanOperationBehaviourEmulatorTransformer).map(RxBleClientImpl.this.internalToExternalScanResultMapFunction).mergeWith(RxBleClientImpl.this.bluetoothAdapterOffExceptionObservable());
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleClient
    public Observable<RxBleScanResult> scanBleDevices(final UUID... r2) {
        return Observable.defer(new Func0<Observable<RxBleScanResult>>() { // from class: com.polidea.rxandroidble.RxBleClientImpl.2
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public Observable<RxBleScanResult> call() {
                RxBleClientImpl.this.scanPreconditionVerifier.verify();
                return RxBleClientImpl.this.initializeScan(r2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<RxBleScanResult> initializeScan(UUID[] r4) {
        Observable<RxBleScanResult> observable;
        Set<UUID> distinctSet = this.uuidUtil.toDistinctSet(r4);
        synchronized (this.queuedScanOperations) {
            observable = this.queuedScanOperations.get(distinctSet);
            if (observable == null) {
                observable = createScanOperationApi18(r4);
                this.queuedScanOperations.put(distinctSet, observable);
            }
        }
        return observable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> Observable<T> bluetoothAdapterOffExceptionObservable() {
        return (Observable<T>) this.rxBleAdapterStateObservable.filter(new Func1<RxBleAdapterStateObservable.BleAdapterState, Boolean>() { // from class: com.polidea.rxandroidble.RxBleClientImpl.4
            @Override // p042rx.functions.Func1
            public Boolean call(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
                return Boolean.valueOf(bleAdapterState != RxBleAdapterStateObservable.BleAdapterState.STATE_ON);
            }
        }).first().flatMap(new Func1<RxBleAdapterStateObservable.BleAdapterState, Observable<? extends T>>() { // from class: com.polidea.rxandroidble.RxBleClientImpl.3
            @Override // p042rx.functions.Func1
            public Observable<? extends T> call(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
                return Observable.error(new BleScanException(1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RxBleScanResult convertToPublicScanResult(RxBleInternalScanResultLegacy rxBleInternalScanResultLegacy) {
        return new RxBleScanResult(getBleDevice(rxBleInternalScanResultLegacy.getBluetoothDevice().getAddress()), rxBleInternalScanResultLegacy.getRssi(), rxBleInternalScanResultLegacy.getScanRecord());
    }

    private Observable<RxBleScanResult> createScanOperationApi18(UUID[] r5) {
        final Set<UUID> distinctSet = this.uuidUtil.toDistinctSet(r5);
        return this.operationQueue.queue(new LegacyScanOperation(r5, this.rxBleAdapterWrapper, this.uuidUtil)).doOnUnsubscribe(new Action0() { // from class: com.polidea.rxandroidble.RxBleClientImpl.6
            @Override // p042rx.functions.Action0
            public void call() {
                synchronized (RxBleClientImpl.this.queuedScanOperations) {
                    RxBleClientImpl.this.queuedScanOperations.remove(distinctSet);
                }
            }
        }).mergeWith(bluetoothAdapterOffExceptionObservable()).map(new Func1<RxBleInternalScanResultLegacy, RxBleScanResult>() { // from class: com.polidea.rxandroidble.RxBleClientImpl.5
            @Override // p042rx.functions.Func1
            public RxBleScanResult call(RxBleInternalScanResultLegacy rxBleInternalScanResultLegacy) {
                return RxBleClientImpl.this.convertToPublicScanResult(rxBleInternalScanResultLegacy);
            }
        }).share();
    }

    private void guardBluetoothAdapterAvailable() {
        if (!this.rxBleAdapterWrapper.hasBluetoothAdapter()) {
            throw new UnsupportedOperationException("RxAndroidBle library needs a BluetoothAdapter to be available in the system to work. If this is a test on an emulator then you can use 'https://github.com/Polidea/RxAndroidBle/tree/master/mockrxandroidble'");
        }
    }

    @Override // com.polidea.rxandroidble.RxBleClient
    public Observable<RxBleClient.State> observeStateChanges() {
        return this.lazyClientStateObservable.get();
    }

    @Override // com.polidea.rxandroidble.RxBleClient
    public RxBleClient.State getState() {
        if (!this.rxBleAdapterWrapper.hasBluetoothAdapter()) {
            return RxBleClient.State.BLUETOOTH_NOT_AVAILABLE;
        }
        if (!this.locationServicesStatus.isLocationPermissionOk()) {
            return RxBleClient.State.LOCATION_PERMISSION_NOT_GRANTED;
        }
        if (!this.rxBleAdapterWrapper.isBluetoothEnabled()) {
            return RxBleClient.State.BLUETOOTH_NOT_ENABLED;
        }
        if (!this.locationServicesStatus.isLocationProviderOk()) {
            return RxBleClient.State.LOCATION_SERVICES_NOT_ENABLED;
        }
        return RxBleClient.State.READY;
    }
}
