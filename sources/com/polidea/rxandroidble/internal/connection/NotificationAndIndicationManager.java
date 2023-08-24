package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.NotificationSetupMode;
import com.polidea.rxandroidble.exceptions.BleCannotSetCharacteristicNotificationException;
import com.polidea.rxandroidble.exceptions.BleConflictingNotificationAlreadySetException;
import com.polidea.rxandroidble.internal.util.ActiveCharacteristicNotification;
import com.polidea.rxandroidble.internal.util.CharacteristicChangedEvent;
import com.polidea.rxandroidble.internal.util.CharacteristicNotificationId;
import com.polidea.rxandroidble.internal.util.ObservableUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import p042rx.Completable;
import p042rx.Observable;
import p042rx.functions.Action0;
import p042rx.functions.Actions;
import p042rx.functions.Func0;
import p042rx.functions.Func1;
import p042rx.subjects.PublishSubject;

/* JADX INFO: Access modifiers changed from: package-private */
@ConnectionScope
/* loaded from: classes3.dex */
public class NotificationAndIndicationManager {
    static final UUID CLIENT_CHARACTERISTIC_CONFIG_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    private final Map<CharacteristicNotificationId, ActiveCharacteristicNotification> activeNotificationObservableMap = new HashMap();
    private final BluetoothGatt bluetoothGatt;
    private final byte[] configDisable;
    private final byte[] configEnableIndication;
    private final byte[] configEnableNotification;
    private final DescriptorWriter descriptorWriter;
    private final RxBleGattCallback gattCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public NotificationAndIndicationManager(@Named("enable-notification-value") byte[] bArr, @Named("enable-indication-value") byte[] bArr2, @Named("disable-notification-value") byte[] bArr3, BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, DescriptorWriter descriptorWriter) {
        this.configEnableNotification = bArr;
        this.configEnableIndication = bArr2;
        this.configDisable = bArr3;
        this.bluetoothGatt = bluetoothGatt;
        this.gattCallback = rxBleGattCallback;
        this.descriptorWriter = descriptorWriter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable<Observable<byte[]>> setupServerInitiatedCharacteristicRead(final BluetoothGattCharacteristic bluetoothGattCharacteristic, final NotificationSetupMode notificationSetupMode, final boolean z) {
        return Observable.defer(new Func0<Observable<Observable<byte[]>>>() { // from class: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager.1
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public Observable<Observable<byte[]>> call() {
                synchronized (NotificationAndIndicationManager.this.activeNotificationObservableMap) {
                    final CharacteristicNotificationId characteristicNotificationId = new CharacteristicNotificationId(bluetoothGattCharacteristic.getUuid(), Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()));
                    ActiveCharacteristicNotification activeCharacteristicNotification = (ActiveCharacteristicNotification) NotificationAndIndicationManager.this.activeNotificationObservableMap.get(characteristicNotificationId);
                    boolean z2 = true;
                    if (activeCharacteristicNotification != null) {
                        if (activeCharacteristicNotification.isIndication == z) {
                            return activeCharacteristicNotification.notificationObservable;
                        }
                        UUID uuid = bluetoothGattCharacteristic.getUuid();
                        if (z) {
                            z2 = false;
                        }
                        return Observable.error(new BleConflictingNotificationAlreadySetException(uuid, z2));
                    }
                    byte[] bArr = z ? NotificationAndIndicationManager.this.configEnableIndication : NotificationAndIndicationManager.this.configEnableNotification;
                    final PublishSubject create = PublishSubject.create();
                    Observable<Observable<byte[]>> refCount = NotificationAndIndicationManager.setCharacteristicNotification(NotificationAndIndicationManager.this.bluetoothGatt, bluetoothGattCharacteristic, true).andThen(ObservableUtil.justOnNext(NotificationAndIndicationManager.observeOnCharacteristicChangeCallbacks(NotificationAndIndicationManager.this.gattCallback, characteristicNotificationId))).compose(NotificationAndIndicationManager.setupModeTransformer(NotificationAndIndicationManager.this.descriptorWriter, bluetoothGattCharacteristic, bArr, notificationSetupMode)).map(new Func1<Observable<byte[]>, Observable<byte[]>>() { // from class: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager.1.2
                        @Override // p042rx.functions.Func1
                        public Observable<byte[]> call(Observable<byte[]> observable) {
                            return Observable.amb(create.cast(byte[].class), observable.takeUntil(create));
                        }
                    }).doOnUnsubscribe(new Action0() { // from class: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager.1.1
                        @Override // p042rx.functions.Action0
                        public void call() {
                            create.onCompleted();
                            synchronized (NotificationAndIndicationManager.this.activeNotificationObservableMap) {
                                NotificationAndIndicationManager.this.activeNotificationObservableMap.remove(characteristicNotificationId);
                            }
                            NotificationAndIndicationManager.setCharacteristicNotification(NotificationAndIndicationManager.this.bluetoothGatt, bluetoothGattCharacteristic, false).compose(NotificationAndIndicationManager.teardownModeTransformer(NotificationAndIndicationManager.this.descriptorWriter, bluetoothGattCharacteristic, NotificationAndIndicationManager.this.configDisable, notificationSetupMode)).subscribe(Actions.empty(), Actions.toAction1(Actions.empty()));
                        }
                    }).mergeWith(NotificationAndIndicationManager.this.gattCallback.observeDisconnect()).replay(1).refCount();
                    NotificationAndIndicationManager.this.activeNotificationObservableMap.put(characteristicNotificationId, new ActiveCharacteristicNotification(refCount, z));
                    return refCount;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Completable setCharacteristicNotification(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final boolean z) {
        return Completable.fromAction(new Action0() { // from class: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager.2
            @Override // p042rx.functions.Action0
            public void call() {
                if (!bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z)) {
                    throw new BleCannotSetCharacteristicNotificationException(bluetoothGattCharacteristic, 1, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Observable.Transformer<Observable<byte[]>, Observable<byte[]>> setupModeTransformer(final DescriptorWriter descriptorWriter, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final byte[] bArr, final NotificationSetupMode notificationSetupMode) {
        return new Observable.Transformer<Observable<byte[]>, Observable<byte[]>>() { // from class: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager.3
            @Override // p042rx.functions.Func1
            public Observable<Observable<byte[]>> call(Observable<Observable<byte[]>> observable) {
                int r0 = C38558.$SwitchMap$com$polidea$rxandroidble$NotificationSetupMode[NotificationSetupMode.this.ordinal()];
                if (r0 != 1) {
                    if (r0 != 2) {
                        return NotificationAndIndicationManager.writeClientCharacteristicConfig(bluetoothGattCharacteristic, descriptorWriter, bArr).andThen(observable);
                    }
                    final Completable completable = NotificationAndIndicationManager.writeClientCharacteristicConfig(bluetoothGattCharacteristic, descriptorWriter, bArr).toObservable().publish().autoConnect(2).toCompletable();
                    return observable.mergeWith(completable.toObservable()).map(new Func1<Observable<byte[]>, Observable<byte[]>>() { // from class: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager.3.1
                        @Override // p042rx.functions.Func1
                        public Observable<byte[]> call(Observable<byte[]> observable2) {
                            return observable2.mergeWith(completable.onErrorComplete().toObservable());
                        }
                    });
                }
                return observable;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager$8 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C38558 {
        static final /* synthetic */ int[] $SwitchMap$com$polidea$rxandroidble$NotificationSetupMode;

        static {
            int[] r0 = new int[NotificationSetupMode.values().length];
            $SwitchMap$com$polidea$rxandroidble$NotificationSetupMode = r0;
            try {
                r0[NotificationSetupMode.COMPAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$polidea$rxandroidble$NotificationSetupMode[NotificationSetupMode.QUICK_SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$polidea$rxandroidble$NotificationSetupMode[NotificationSetupMode.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Completable.Transformer teardownModeTransformer(final DescriptorWriter descriptorWriter, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final byte[] bArr, final NotificationSetupMode notificationSetupMode) {
        return new Completable.Transformer() { // from class: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager.4
            @Override // p042rx.functions.Func1
            public Completable call(Completable completable) {
                return NotificationSetupMode.this == NotificationSetupMode.COMPAT ? completable : completable.andThen(NotificationAndIndicationManager.writeClientCharacteristicConfig(bluetoothGattCharacteristic, descriptorWriter, bArr));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Observable<byte[]> observeOnCharacteristicChangeCallbacks(RxBleGattCallback rxBleGattCallback, final CharacteristicNotificationId characteristicNotificationId) {
        return rxBleGattCallback.getOnCharacteristicChanged().filter(new Func1<CharacteristicChangedEvent, Boolean>() { // from class: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager.6
            @Override // p042rx.functions.Func1
            public Boolean call(CharacteristicChangedEvent characteristicChangedEvent) {
                return Boolean.valueOf(characteristicChangedEvent.equals(CharacteristicNotificationId.this));
            }
        }).map(new Func1<CharacteristicChangedEvent, byte[]>() { // from class: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager.5
            @Override // p042rx.functions.Func1
            public byte[] call(CharacteristicChangedEvent characteristicChangedEvent) {
                return characteristicChangedEvent.data;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Completable writeClientCharacteristicConfig(final BluetoothGattCharacteristic bluetoothGattCharacteristic, DescriptorWriter descriptorWriter, byte[] bArr) {
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG_UUID);
        if (descriptor == null) {
            return Completable.error(new BleCannotSetCharacteristicNotificationException(bluetoothGattCharacteristic, 2, null));
        }
        return descriptorWriter.writeDescriptor(descriptor, bArr).toCompletable().onErrorResumeNext(new Func1<Throwable, Completable>() { // from class: com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager.7
            @Override // p042rx.functions.Func1
            public Completable call(Throwable th) {
                return Completable.error(new BleCannotSetCharacteristicNotificationException(bluetoothGattCharacteristic, 3, th));
            }
        });
    }
}
