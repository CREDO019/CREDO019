package com.polidea.rxandroidble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.internal.DelegateFactory;
import bleshadow.dagger.internal.DoubleCheck;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.SetBuilder;
import bleshadow.javax.inject.Provider;
import com.jakewharton.rxrelay.BehaviorRelay;
import com.polidea.rxandroidble.ClientComponent;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.helpers.LocationServicesOkObservable;
import com.polidea.rxandroidble.helpers.LocationServicesOkObservable_Factory;
import com.polidea.rxandroidble.internal.DeviceComponent;
import com.polidea.rxandroidble.internal.DeviceModule;
import com.polidea.rxandroidble.internal.DeviceModule_ProvideBluetoothDeviceFactory;
import com.polidea.rxandroidble.internal.DeviceModule_ProvideConnectionStateChangeListenerFactory;
import com.polidea.rxandroidble.internal.DeviceModule_ProvideConnectionStateRelayFactory;
import com.polidea.rxandroidble.internal.DeviceModule_ProvideMacAddressFactory;
import com.polidea.rxandroidble.internal.DeviceModule_ProvidesConnectTimeoutConfFactory;
import com.polidea.rxandroidble.internal.DeviceModule_ProvidesDisconnectTimeoutConfFactory;
import com.polidea.rxandroidble.internal.RxBleDeviceImpl_Factory;
import com.polidea.rxandroidble.internal.RxBleDeviceProvider;
import com.polidea.rxandroidble.internal.RxBleDeviceProvider_Factory;
import com.polidea.rxandroidble.internal.cache.DeviceComponentCache;
import com.polidea.rxandroidble.internal.cache.DeviceComponentCache_Factory;
import com.polidea.rxandroidble.internal.connection.BluetoothGattProvider;
import com.polidea.rxandroidble.internal.connection.BluetoothGattProvider_Factory;
import com.polidea.rxandroidble.internal.connection.ConnectionComponent;
import com.polidea.rxandroidble.internal.connection.ConnectionModule;
import com.polidea.rxandroidble.internal.connection.ConnectionModuleBinder_GattWriteMtuOverheadFactory;
import com.polidea.rxandroidble.internal.connection.ConnectionModuleBinder_MinimumMtuFactory;
import com.polidea.rxandroidble.internal.connection.ConnectionModuleBinder_ProvideBluetoothGattFactory;
import com.polidea.rxandroidble.internal.connection.ConnectionModule_ProvideAutoConnectFactory;
import com.polidea.rxandroidble.internal.connection.ConnectionModule_ProvideCharacteristicPropertiesParserFactory;
import com.polidea.rxandroidble.internal.connection.ConnectionModule_ProvideIllegalOperationHandlerFactory;
import com.polidea.rxandroidble.internal.connection.ConnectionModule_ProvidesOperationTimeoutConfFactory;
import com.polidea.rxandroidble.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble.internal.connection.ConnectionSubscriptionWatcher;
import com.polidea.rxandroidble.internal.connection.ConnectorImpl_Factory;
import com.polidea.rxandroidble.internal.connection.DescriptorWriter_Factory;
import com.polidea.rxandroidble.internal.connection.DisconnectAction_Factory;
import com.polidea.rxandroidble.internal.connection.DisconnectionRouter_Factory;
import com.polidea.rxandroidble.internal.connection.IllegalOperationChecker_Factory;
import com.polidea.rxandroidble.internal.connection.IllegalOperationMessageCreator_Factory;
import com.polidea.rxandroidble.internal.connection.LoggingIllegalOperationHandler_Factory;
import com.polidea.rxandroidble.internal.connection.LongWriteOperationBuilderImpl_Factory;
import com.polidea.rxandroidble.internal.connection.MtuBasedPayloadSizeLimit_Factory;
import com.polidea.rxandroidble.internal.connection.MtuWatcher_Factory;
import com.polidea.rxandroidble.internal.connection.NativeCallbackDispatcher_Factory;
import com.polidea.rxandroidble.internal.connection.NotificationAndIndicationManager_Factory;
import com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl;
import com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl_Factory;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback_Factory;
import com.polidea.rxandroidble.internal.connection.ServiceDiscoveryManager_Factory;
import com.polidea.rxandroidble.internal.connection.ThrowingIllegalOperationHandler_Factory;
import com.polidea.rxandroidble.internal.operations.ConnectOperation;
import com.polidea.rxandroidble.internal.operations.ConnectOperation_Factory;
import com.polidea.rxandroidble.internal.operations.DisconnectOperation_Factory;
import com.polidea.rxandroidble.internal.operations.OperationsProviderImpl_Factory;
import com.polidea.rxandroidble.internal.operations.ReadRssiOperation_Factory;
import com.polidea.rxandroidble.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble.internal.scan.AndroidScanObjectsConverter_Factory;
import com.polidea.rxandroidble.internal.scan.InternalScanResultCreator;
import com.polidea.rxandroidble.internal.scan.InternalScanResultCreator_Factory;
import com.polidea.rxandroidble.internal.scan.InternalToExternalScanResultConverter_Factory;
import com.polidea.rxandroidble.internal.scan.ScanPreconditionsVerifierApi18_Factory;
import com.polidea.rxandroidble.internal.scan.ScanPreconditionsVerifierApi24_Factory;
import com.polidea.rxandroidble.internal.scan.ScanSettingsEmulator_Factory;
import com.polidea.rxandroidble.internal.scan.ScanSetupBuilder;
import com.polidea.rxandroidble.internal.scan.ScanSetupBuilderImplApi18_Factory;
import com.polidea.rxandroidble.internal.scan.ScanSetupBuilderImplApi21_Factory;
import com.polidea.rxandroidble.internal.scan.ScanSetupBuilderImplApi23_Factory;
import com.polidea.rxandroidble.internal.serialization.ClientOperationQueue;
import com.polidea.rxandroidble.internal.serialization.ClientOperationQueueImpl_Factory;
import com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueueImpl;
import com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueueImpl_Factory;
import com.polidea.rxandroidble.internal.util.BleConnectionCompat;
import com.polidea.rxandroidble.internal.util.CheckerLocationPermission_Factory;
import com.polidea.rxandroidble.internal.util.CheckerLocationProvider_Factory;
import com.polidea.rxandroidble.internal.util.ClientStateObservable_Factory;
import com.polidea.rxandroidble.internal.util.LocationServicesOkObservableApi23_Factory;
import com.polidea.rxandroidble.internal.util.LocationServicesStatusApi18_Factory;
import com.polidea.rxandroidble.internal.util.LocationServicesStatusApi23_Factory;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper_Factory;
import com.polidea.rxandroidble.internal.util.RxBleServicesLogger_Factory;
import com.polidea.rxandroidble.internal.util.UUIDUtil_Factory;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import p042rx.Observable;
import p042rx.Scheduler;

/* loaded from: classes3.dex */
public final class DaggerClientComponent implements ClientComponent {
    private AndroidScanObjectsConverter_Factory androidScanObjectsConverterProvider;
    private Provider<ClientOperationQueue> bindClientOperationQueueProvider;
    private Provider<RxBleClient> bindRxBleClientProvider;
    private CheckerLocationPermission_Factory checkerLocationPermissionProvider;
    private CheckerLocationProvider_Factory checkerLocationProvider;
    private ClientComponent.ClientModule clientModule;
    private ClientOperationQueueImpl_Factory clientOperationQueueImplProvider;
    private ClientStateObservable_Factory clientStateObservableProvider;
    private Provider<DeviceComponent.Builder> deviceComponentBuilderProvider;
    private Provider<DeviceComponentCache> deviceComponentCacheProvider;
    private Provider<InternalScanResultCreator> internalScanResultCreatorProvider;
    private InternalToExternalScanResultConverter_Factory internalToExternalScanResultConverterProvider;
    private LocationServicesOkObservableApi23_Factory locationServicesOkObservableApi23Provider;
    private LocationServicesStatusApi23_Factory locationServicesStatusApi23Provider;
    private ClientComponent_ClientModule_ProvideApplicationContextFactory provideApplicationContextProvider;
    private Provider<ExecutorService> provideBluetoothCallbacksExecutorServiceProvider;
    private Provider<Scheduler> provideBluetoothCallbacksSchedulerProvider;
    private Provider<ExecutorService> provideBluetoothInteractionExecutorServiceProvider;
    private Provider<Scheduler> provideBluetoothInteractionSchedulerProvider;
    private ClientComponent_ClientModule_ProvideBluetoothManagerFactory provideBluetoothManagerProvider;
    private Provider<ExecutorService> provideConnectionQueueExecutorServiceProvider;
    private ClientComponent_ClientModule_ProvideContentResolverFactory provideContentResolverProvider;
    private ClientComponent_ClientModule_ProvideFinalizationCloseableFactory provideFinalizationCloseableProvider;
    private ClientComponent_ClientModule_ProvideIsAndroidWearFactory provideIsAndroidWearProvider;
    private ClientComponent_ClientModule_ProvideLocationManagerFactory provideLocationManagerProvider;
    private C3795x61f40e72 provideLocationServicesOkObservableProvider;
    private C3796x9cd4449f provideLocationServicesStatusProvider;
    private C3797x8de81979 provideScanPreconditionVerifierProvider;
    private Provider<ScanSetupBuilder> provideScanSetupProvider;
    private ClientComponent_ClientModule_ProvideTargetSdkFactory provideTargetSdkProvider;
    private RxBleAdapterStateObservable_Factory rxBleAdapterStateObservableProvider;
    private RxBleAdapterWrapper_Factory rxBleAdapterWrapperProvider;
    private RxBleClientImpl_Factory rxBleClientImplProvider;
    private Provider<RxBleDeviceProvider> rxBleDeviceProvider;
    private ScanPreconditionsVerifierApi18_Factory scanPreconditionsVerifierApi18Provider;
    private ScanPreconditionsVerifierApi24_Factory scanPreconditionsVerifierApi24Provider;
    private ScanSettingsEmulator_Factory scanSettingsEmulatorProvider;
    private ScanSetupBuilderImplApi18_Factory scanSetupBuilderImplApi18Provider;
    private ScanSetupBuilderImplApi21_Factory scanSetupBuilderImplApi21Provider;
    private ScanSetupBuilderImplApi23_Factory scanSetupBuilderImplApi23Provider;

    private DaggerClientComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private Observable<Boolean> getNamedObservableOfBoolean() {
        return C3795x61f40e72.proxyProvideLocationServicesOkObservable(this.clientModule, ClientComponent.ClientModule.provideDeviceSdk(), this.locationServicesOkObservableApi23Provider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RxBleAdapterWrapper getRxBleAdapterWrapper() {
        return new RxBleAdapterWrapper(ClientComponent.ClientModule.provideBluetoothAdapter());
    }

    private void initialize(Builder builder) {
        this.clientModule = builder.clientModule;
        this.provideApplicationContextProvider = ClientComponent_ClientModule_ProvideApplicationContextFactory.create(builder.clientModule);
        this.provideContentResolverProvider = ClientComponent_ClientModule_ProvideContentResolverFactory.create(builder.clientModule);
        ClientComponent_ClientModule_ProvideLocationManagerFactory create = ClientComponent_ClientModule_ProvideLocationManagerFactory.create(builder.clientModule);
        this.provideLocationManagerProvider = create;
        this.checkerLocationProvider = CheckerLocationProvider_Factory.create(this.provideContentResolverProvider, create);
        this.checkerLocationPermissionProvider = CheckerLocationPermission_Factory.create(this.provideApplicationContextProvider);
        this.provideTargetSdkProvider = ClientComponent_ClientModule_ProvideTargetSdkFactory.create(builder.clientModule);
        ClientComponent_ClientModule_ProvideIsAndroidWearFactory create2 = ClientComponent_ClientModule_ProvideIsAndroidWearFactory.create(builder.clientModule, ClientComponent_ClientModule_ProvideDeviceSdkFactory.create());
        this.provideIsAndroidWearProvider = create2;
        this.locationServicesStatusApi23Provider = LocationServicesStatusApi23_Factory.create(this.checkerLocationProvider, this.checkerLocationPermissionProvider, this.provideTargetSdkProvider, create2);
        C3796x9cd4449f create3 = C3796x9cd4449f.create(builder.clientModule, ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), LocationServicesStatusApi18_Factory.create(), this.locationServicesStatusApi23Provider);
        this.provideLocationServicesStatusProvider = create3;
        this.locationServicesOkObservableApi23Provider = LocationServicesOkObservableApi23_Factory.create(this.provideApplicationContextProvider, create3);
        this.rxBleAdapterWrapperProvider = RxBleAdapterWrapper_Factory.create(ClientComponent_ClientModule_ProvideBluetoothAdapterFactory.create());
        Provider<ExecutorService> provider = DoubleCheck.provider(C3790xa9312dd2.create());
        this.provideBluetoothInteractionExecutorServiceProvider = provider;
        Provider<Scheduler> provider2 = DoubleCheck.provider(C3791xd84c18d9.create(provider));
        this.provideBluetoothInteractionSchedulerProvider = provider2;
        ClientOperationQueueImpl_Factory create4 = ClientOperationQueueImpl_Factory.create(provider2);
        this.clientOperationQueueImplProvider = create4;
        this.bindClientOperationQueueProvider = DoubleCheck.provider(create4);
        this.rxBleAdapterStateObservableProvider = RxBleAdapterStateObservable_Factory.create(this.provideApplicationContextProvider);
        C3795x61f40e72 create5 = C3795x61f40e72.create(builder.clientModule, ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), this.locationServicesOkObservableApi23Provider);
        this.provideLocationServicesOkObservableProvider = create5;
        this.clientStateObservableProvider = ClientStateObservable_Factory.create(this.rxBleAdapterWrapperProvider, this.rxBleAdapterStateObservableProvider, create5, this.provideLocationServicesStatusProvider, ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create());
        this.deviceComponentCacheProvider = DoubleCheck.provider(DeviceComponentCache_Factory.create());
        Provider<DeviceComponent.Builder> provider3 = new Provider<DeviceComponent.Builder>() { // from class: com.polidea.rxandroidble.DaggerClientComponent.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bleshadow.javax.inject.Provider
            public DeviceComponent.Builder get() {
                return new DeviceComponentBuilder();
            }
        };
        this.deviceComponentBuilderProvider = provider3;
        this.rxBleDeviceProvider = DoubleCheck.provider(RxBleDeviceProvider_Factory.create(this.deviceComponentCacheProvider, provider3));
        this.internalScanResultCreatorProvider = DoubleCheck.provider(InternalScanResultCreator_Factory.create(UUIDUtil_Factory.create()));
        ScanSettingsEmulator_Factory create6 = ScanSettingsEmulator_Factory.create(ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create());
        this.scanSettingsEmulatorProvider = create6;
        this.scanSetupBuilderImplApi18Provider = ScanSetupBuilderImplApi18_Factory.create(this.rxBleAdapterWrapperProvider, this.internalScanResultCreatorProvider, create6);
        AndroidScanObjectsConverter_Factory create7 = AndroidScanObjectsConverter_Factory.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create());
        this.androidScanObjectsConverterProvider = create7;
        this.scanSetupBuilderImplApi21Provider = ScanSetupBuilderImplApi21_Factory.create(this.rxBleAdapterWrapperProvider, this.internalScanResultCreatorProvider, this.scanSettingsEmulatorProvider, create7);
        this.scanSetupBuilderImplApi23Provider = ScanSetupBuilderImplApi23_Factory.create(this.rxBleAdapterWrapperProvider, this.internalScanResultCreatorProvider, this.androidScanObjectsConverterProvider);
        this.provideScanSetupProvider = DoubleCheck.provider(ClientComponent_ClientModule_ProvideScanSetupProviderFactory.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), this.scanSetupBuilderImplApi18Provider, this.scanSetupBuilderImplApi21Provider, this.scanSetupBuilderImplApi23Provider));
        ScanPreconditionsVerifierApi18_Factory create8 = ScanPreconditionsVerifierApi18_Factory.create(this.rxBleAdapterWrapperProvider, this.provideLocationServicesStatusProvider);
        this.scanPreconditionsVerifierApi18Provider = create8;
        this.scanPreconditionsVerifierApi24Provider = ScanPreconditionsVerifierApi24_Factory.create(create8, ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create());
        this.provideScanPreconditionVerifierProvider = C3797x8de81979.create(ClientComponent_ClientModule_ProvideDeviceSdkFactory.create(), this.scanPreconditionsVerifierApi18Provider, this.scanPreconditionsVerifierApi24Provider);
        this.internalToExternalScanResultConverterProvider = InternalToExternalScanResultConverter_Factory.create(this.rxBleDeviceProvider);
        this.provideBluetoothCallbacksExecutorServiceProvider = DoubleCheck.provider(C3788x4a2ba98e.create());
        Provider<ExecutorService> provider4 = DoubleCheck.provider(C3792x11ee2f55.create());
        this.provideConnectionQueueExecutorServiceProvider = provider4;
        this.provideFinalizationCloseableProvider = ClientComponent_ClientModule_ProvideFinalizationCloseableFactory.create(this.provideBluetoothInteractionExecutorServiceProvider, this.provideBluetoothCallbacksExecutorServiceProvider, provider4);
        RxBleClientImpl_Factory create9 = RxBleClientImpl_Factory.create(this.rxBleAdapterWrapperProvider, this.bindClientOperationQueueProvider, this.rxBleAdapterStateObservableProvider, UUIDUtil_Factory.create(), this.provideLocationServicesStatusProvider, this.clientStateObservableProvider, this.rxBleDeviceProvider, this.provideScanSetupProvider, this.provideScanPreconditionVerifierProvider, this.internalToExternalScanResultConverterProvider, this.provideBluetoothInteractionSchedulerProvider, this.provideFinalizationCloseableProvider);
        this.rxBleClientImplProvider = create9;
        this.bindRxBleClientProvider = DoubleCheck.provider(create9);
        this.provideBluetoothCallbacksSchedulerProvider = DoubleCheck.provider(C3789x76cd1195.create(this.provideBluetoothCallbacksExecutorServiceProvider));
        this.provideBluetoothManagerProvider = ClientComponent_ClientModule_ProvideBluetoothManagerFactory.create(builder.clientModule);
    }

    @Override // com.polidea.rxandroidble.ClientComponent
    public LocationServicesOkObservable locationServicesOkObservable() {
        return LocationServicesOkObservable_Factory.newLocationServicesOkObservable(getNamedObservableOfBoolean());
    }

    @Override // com.polidea.rxandroidble.ClientComponent
    public RxBleClient rxBleClient() {
        return this.bindRxBleClientProvider.get();
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private ClientComponent.ClientModule clientModule;

        private Builder() {
        }

        public ClientComponent build() {
            if (this.clientModule == null) {
                throw new IllegalStateException(ClientComponent.ClientModule.class.getCanonicalName() + " must be set");
            }
            return new DaggerClientComponent(this);
        }

        public Builder clientModule(ClientComponent.ClientModule clientModule) {
            this.clientModule = (ClientComponent.ClientModule) Preconditions.checkNotNull(clientModule);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class DeviceComponentBuilder implements DeviceComponent.Builder {
        private DeviceModule deviceModule;

        private DeviceComponentBuilder() {
        }

        @Override // com.polidea.rxandroidble.internal.DeviceComponent.Builder
        public DeviceComponent build() {
            if (this.deviceModule == null) {
                throw new IllegalStateException(DeviceModule.class.getCanonicalName() + " must be set");
            }
            return new DeviceComponentImpl(this);
        }

        @Override // com.polidea.rxandroidble.internal.DeviceComponent.Builder
        public DeviceComponentBuilder deviceModule(DeviceModule deviceModule) {
            this.deviceModule = (DeviceModule) Preconditions.checkNotNull(deviceModule);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class DeviceComponentImpl implements DeviceComponent {
        private Provider<ConnectionComponent.Builder> connectionComponentBuilderProvider;
        private ConnectorImpl_Factory connectorImplProvider;
        private DeviceModule deviceModule;
        private DeviceModule_ProvideBluetoothDeviceFactory provideBluetoothDeviceProvider;
        private Provider<ConnectionStateChangeListener> provideConnectionStateChangeListenerProvider;
        private Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> provideConnectionStateRelayProvider;
        private DeviceModule_ProvideMacAddressFactory provideMacAddressProvider;
        private DeviceModule_ProvidesDisconnectTimeoutConfFactory providesDisconnectTimeoutConfProvider;
        private Provider rxBleDeviceImplProvider;

        private DeviceComponentImpl(DeviceComponentBuilder deviceComponentBuilder) {
            initialize(deviceComponentBuilder);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public BluetoothDevice getBluetoothDevice() {
            return DeviceModule_ProvideBluetoothDeviceFactory.proxyProvideBluetoothDevice(this.deviceModule, DaggerClientComponent.this.getRxBleAdapterWrapper());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public TimeoutConfiguration getNamedTimeoutConfiguration() {
            return DeviceModule_ProvidesConnectTimeoutConfFactory.proxyProvidesConnectTimeoutConf(ClientComponent_ClientModule_ProvideComputationSchedulerFactory.proxyProvideComputationScheduler());
        }

        private void initialize(DeviceComponentBuilder deviceComponentBuilder) {
            this.provideBluetoothDeviceProvider = DeviceModule_ProvideBluetoothDeviceFactory.create(deviceComponentBuilder.deviceModule, DaggerClientComponent.this.rxBleAdapterWrapperProvider);
            this.connectionComponentBuilderProvider = new Provider<ConnectionComponent.Builder>() { // from class: com.polidea.rxandroidble.DaggerClientComponent.DeviceComponentImpl.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bleshadow.javax.inject.Provider
                public ConnectionComponent.Builder get() {
                    return new ConnectionComponentBuilder();
                }
            };
            this.connectorImplProvider = ConnectorImpl_Factory.create(DaggerClientComponent.this.bindClientOperationQueueProvider, this.connectionComponentBuilderProvider, DaggerClientComponent.this.provideBluetoothCallbacksSchedulerProvider);
            Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> provider = DoubleCheck.provider(DeviceModule_ProvideConnectionStateRelayFactory.create());
            this.provideConnectionStateRelayProvider = provider;
            this.rxBleDeviceImplProvider = DoubleCheck.provider(RxBleDeviceImpl_Factory.create(this.provideBluetoothDeviceProvider, this.connectorImplProvider, provider));
            this.deviceModule = deviceComponentBuilder.deviceModule;
            this.provideMacAddressProvider = DeviceModule_ProvideMacAddressFactory.create(deviceComponentBuilder.deviceModule);
            this.provideConnectionStateChangeListenerProvider = DoubleCheck.provider(DeviceModule_ProvideConnectionStateChangeListenerFactory.create(this.provideConnectionStateRelayProvider));
            this.providesDisconnectTimeoutConfProvider = DeviceModule_ProvidesDisconnectTimeoutConfFactory.create(ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create());
        }

        @Override // com.polidea.rxandroidble.internal.DeviceComponent
        public RxBleDevice provideDevice() {
            return (RxBleDevice) this.rxBleDeviceImplProvider.get();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public final class ConnectionComponentBuilder implements ConnectionComponent.Builder {
            private ConnectionModule connectionModule;

            private ConnectionComponentBuilder() {
            }

            @Override // com.polidea.rxandroidble.internal.connection.ConnectionComponent.Builder
            public ConnectionComponent build() {
                if (this.connectionModule == null) {
                    throw new IllegalStateException(ConnectionModule.class.getCanonicalName() + " must be set");
                }
                return new ConnectionComponentImpl(this);
            }

            @Override // com.polidea.rxandroidble.internal.connection.ConnectionComponent.Builder
            public ConnectionComponentBuilder connectionModule(ConnectionModule connectionModule) {
                this.connectionModule = (ConnectionModule) Preconditions.checkNotNull(connectionModule);
                return this;
            }
        }

        /* loaded from: classes3.dex */
        private final class ConnectionComponentImpl implements ConnectionComponent {
            private Provider<BluetoothGattProvider> bluetoothGattProvider;
            private Provider<ConnectionOperationQueueImpl> connectionOperationQueueImplProvider;
            private Provider descriptorWriterProvider;
            private Provider disconnectActionProvider;
            private DisconnectOperation_Factory disconnectOperationProvider;
            private Provider disconnectionRouterProvider;
            private IllegalOperationChecker_Factory illegalOperationCheckerProvider;
            private IllegalOperationMessageCreator_Factory illegalOperationMessageCreatorProvider;
            private LoggingIllegalOperationHandler_Factory loggingIllegalOperationHandlerProvider;
            private LongWriteOperationBuilderImpl_Factory longWriteOperationBuilderImplProvider;
            private Provider mtuBasedPayloadSizeLimitProvider;
            private Provider mtuWatcherProvider;
            private Provider notificationAndIndicationManagerProvider;
            private OperationsProviderImpl_Factory operationsProviderImplProvider;
            private Provider<Boolean> provideAutoConnectProvider;
            private Provider<BluetoothGatt> provideBluetoothGattProvider;
            private ConnectionModule_ProvideCharacteristicPropertiesParserFactory provideCharacteristicPropertiesParserProvider;
            private ConnectionModule_ProvideIllegalOperationHandlerFactory provideIllegalOperationHandlerProvider;
            private ConnectionModule_ProvidesOperationTimeoutConfFactory providesOperationTimeoutConfProvider;
            private ReadRssiOperation_Factory readRssiOperationProvider;
            private Provider<RxBleConnectionImpl> rxBleConnectionImplProvider;
            private Provider<RxBleGattCallback> rxBleGattCallbackProvider;
            private RxBleServicesLogger_Factory rxBleServicesLoggerProvider;
            private Provider serviceDiscoveryManagerProvider;
            private ThrowingIllegalOperationHandler_Factory throwingIllegalOperationHandlerProvider;

            private ConnectionComponentImpl(ConnectionComponentBuilder connectionComponentBuilder) {
                initialize(connectionComponentBuilder);
            }

            private BleConnectionCompat getBleConnectionCompat() {
                return new BleConnectionCompat(ClientComponent_ClientModule_ProvideApplicationContextFactory.proxyProvideApplicationContext(DaggerClientComponent.this.clientModule));
            }

            private void initialize(ConnectionComponentBuilder connectionComponentBuilder) {
                this.bluetoothGattProvider = DoubleCheck.provider(BluetoothGattProvider_Factory.create());
                this.disconnectionRouterProvider = DoubleCheck.provider(DisconnectionRouter_Factory.create(DeviceComponentImpl.this.provideMacAddressProvider, DaggerClientComponent.this.rxBleAdapterWrapperProvider, DaggerClientComponent.this.rxBleAdapterStateObservableProvider));
                this.rxBleGattCallbackProvider = DoubleCheck.provider(RxBleGattCallback_Factory.create(DaggerClientComponent.this.provideBluetoothCallbacksSchedulerProvider, this.bluetoothGattProvider, this.disconnectionRouterProvider, NativeCallbackDispatcher_Factory.create()));
                this.provideAutoConnectProvider = DoubleCheck.provider(ConnectionModule_ProvideAutoConnectFactory.create(connectionComponentBuilder.connectionModule));
                this.connectionOperationQueueImplProvider = DoubleCheck.provider(ConnectionOperationQueueImpl_Factory.create(DeviceComponentImpl.this.provideMacAddressProvider, this.disconnectionRouterProvider, DaggerClientComponent.this.provideConnectionQueueExecutorServiceProvider, DaggerClientComponent.this.provideBluetoothInteractionSchedulerProvider));
                this.provideBluetoothGattProvider = DoubleCheck.provider(ConnectionModuleBinder_ProvideBluetoothGattFactory.create(this.bluetoothGattProvider));
                ConnectionModule_ProvideCharacteristicPropertiesParserFactory create = ConnectionModule_ProvideCharacteristicPropertiesParserFactory.create(connectionComponentBuilder.connectionModule);
                this.provideCharacteristicPropertiesParserProvider = create;
                this.rxBleServicesLoggerProvider = RxBleServicesLogger_Factory.create(create);
                ConnectionModule_ProvidesOperationTimeoutConfFactory create2 = ConnectionModule_ProvidesOperationTimeoutConfFactory.create(connectionComponentBuilder.connectionModule, ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create());
                this.providesOperationTimeoutConfProvider = create2;
                this.readRssiOperationProvider = ReadRssiOperation_Factory.create(this.rxBleGattCallbackProvider, this.provideBluetoothGattProvider, create2);
                OperationsProviderImpl_Factory create3 = OperationsProviderImpl_Factory.create(this.rxBleGattCallbackProvider, this.provideBluetoothGattProvider, this.rxBleServicesLoggerProvider, this.providesOperationTimeoutConfProvider, DaggerClientComponent.this.provideBluetoothInteractionSchedulerProvider, ClientComponent_ClientModule_ProvideComputationSchedulerFactory.create(), this.readRssiOperationProvider);
                this.operationsProviderImplProvider = create3;
                this.serviceDiscoveryManagerProvider = DoubleCheck.provider(ServiceDiscoveryManager_Factory.create(this.connectionOperationQueueImplProvider, this.provideBluetoothGattProvider, create3));
                this.descriptorWriterProvider = DoubleCheck.provider(DescriptorWriter_Factory.create(this.connectionOperationQueueImplProvider, this.operationsProviderImplProvider));
                this.notificationAndIndicationManagerProvider = DoubleCheck.provider(NotificationAndIndicationManager_Factory.create(C3794xd399626d.create(), ClientComponent_ClientModule_ProvideEnableIndicationValueFactory.create(), C3793x975cafc6.create(), this.provideBluetoothGattProvider, this.rxBleGattCallbackProvider, this.descriptorWriterProvider));
                this.mtuWatcherProvider = DoubleCheck.provider(MtuWatcher_Factory.create(this.rxBleGattCallbackProvider, ConnectionModuleBinder_MinimumMtuFactory.create()));
                DelegateFactory delegateFactory = new DelegateFactory();
                this.rxBleConnectionImplProvider = delegateFactory;
                Provider provider = DoubleCheck.provider(MtuBasedPayloadSizeLimit_Factory.create(delegateFactory, ConnectionModuleBinder_GattWriteMtuOverheadFactory.create()));
                this.mtuBasedPayloadSizeLimitProvider = provider;
                this.longWriteOperationBuilderImplProvider = LongWriteOperationBuilderImpl_Factory.create(this.connectionOperationQueueImplProvider, provider, this.rxBleConnectionImplProvider, this.operationsProviderImplProvider);
                IllegalOperationMessageCreator_Factory create4 = IllegalOperationMessageCreator_Factory.create(this.provideCharacteristicPropertiesParserProvider);
                this.illegalOperationMessageCreatorProvider = create4;
                this.loggingIllegalOperationHandlerProvider = LoggingIllegalOperationHandler_Factory.create(create4);
                this.throwingIllegalOperationHandlerProvider = ThrowingIllegalOperationHandler_Factory.create(this.illegalOperationMessageCreatorProvider);
                ConnectionModule_ProvideIllegalOperationHandlerFactory create5 = ConnectionModule_ProvideIllegalOperationHandlerFactory.create(connectionComponentBuilder.connectionModule, this.loggingIllegalOperationHandlerProvider, this.throwingIllegalOperationHandlerProvider);
                this.provideIllegalOperationHandlerProvider = create5;
                this.illegalOperationCheckerProvider = IllegalOperationChecker_Factory.create(create5);
                Provider<RxBleConnectionImpl> provider2 = DoubleCheck.provider(RxBleConnectionImpl_Factory.create(this.connectionOperationQueueImplProvider, this.rxBleGattCallbackProvider, this.provideBluetoothGattProvider, this.serviceDiscoveryManagerProvider, this.notificationAndIndicationManagerProvider, this.mtuWatcherProvider, this.descriptorWriterProvider, this.operationsProviderImplProvider, this.longWriteOperationBuilderImplProvider, DaggerClientComponent.this.provideBluetoothInteractionSchedulerProvider, this.illegalOperationCheckerProvider));
                this.rxBleConnectionImplProvider = provider2;
                ((DelegateFactory) this.rxBleConnectionImplProvider).setDelegatedProvider(provider2);
                this.disconnectOperationProvider = DisconnectOperation_Factory.create(this.rxBleGattCallbackProvider, this.bluetoothGattProvider, DeviceComponentImpl.this.provideMacAddressProvider, DaggerClientComponent.this.provideBluetoothManagerProvider, DaggerClientComponent.this.provideBluetoothInteractionSchedulerProvider, DeviceComponentImpl.this.providesDisconnectTimeoutConfProvider, DeviceComponentImpl.this.provideConnectionStateChangeListenerProvider);
                this.disconnectActionProvider = DoubleCheck.provider(DisconnectAction_Factory.create(DaggerClientComponent.this.bindClientOperationQueueProvider, this.disconnectOperationProvider));
            }

            @Override // com.polidea.rxandroidble.internal.connection.ConnectionComponent
            public ConnectOperation connectOperation() {
                return ConnectOperation_Factory.newConnectOperation(DeviceComponentImpl.this.getBluetoothDevice(), getBleConnectionCompat(), this.rxBleGattCallbackProvider.get(), this.bluetoothGattProvider.get(), DeviceComponentImpl.this.getNamedTimeoutConfiguration(), this.provideAutoConnectProvider.get().booleanValue(), (ConnectionStateChangeListener) DeviceComponentImpl.this.provideConnectionStateChangeListenerProvider.get());
            }

            @Override // com.polidea.rxandroidble.internal.connection.ConnectionComponent
            public RxBleConnection rxBleConnection() {
                return this.rxBleConnectionImplProvider.get();
            }

            @Override // com.polidea.rxandroidble.internal.connection.ConnectionComponent
            public RxBleGattCallback gattCallback() {
                return this.rxBleGattCallbackProvider.get();
            }

            @Override // com.polidea.rxandroidble.internal.connection.ConnectionComponent
            public Set<ConnectionSubscriptionWatcher> connectionSubscriptionWatchers() {
                return SetBuilder.newSetBuilder(3).add((ConnectionSubscriptionWatcher) this.mtuWatcherProvider.get()).add((ConnectionSubscriptionWatcher) this.disconnectActionProvider.get()).add(this.connectionOperationQueueImplProvider.get()).build();
            }
        }
    }
}
