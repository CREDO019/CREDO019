package com.polidea.rxandroidble;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble.ClientComponent;
import com.polidea.rxandroidble.internal.scan.ScanSetupBuilder;
import com.polidea.rxandroidble.internal.scan.ScanSetupBuilderImplApi18;
import com.polidea.rxandroidble.internal.scan.ScanSetupBuilderImplApi21;
import com.polidea.rxandroidble.internal.scan.ScanSetupBuilderImplApi23;

/* loaded from: classes3.dex */
public final class ClientComponent_ClientModule_ProvideScanSetupProviderFactory implements Factory<ScanSetupBuilder> {
    private final Provider<Integer> deviceSdkProvider;
    private final Provider<ScanSetupBuilderImplApi18> scanSetupBuilderProviderForApi18Provider;
    private final Provider<ScanSetupBuilderImplApi21> scanSetupBuilderProviderForApi21Provider;
    private final Provider<ScanSetupBuilderImplApi23> scanSetupBuilderProviderForApi23Provider;

    public ClientComponent_ClientModule_ProvideScanSetupProviderFactory(Provider<Integer> provider, Provider<ScanSetupBuilderImplApi18> provider2, Provider<ScanSetupBuilderImplApi21> provider3, Provider<ScanSetupBuilderImplApi23> provider4) {
        this.deviceSdkProvider = provider;
        this.scanSetupBuilderProviderForApi18Provider = provider2;
        this.scanSetupBuilderProviderForApi21Provider = provider3;
        this.scanSetupBuilderProviderForApi23Provider = provider4;
    }

    @Override // bleshadow.javax.inject.Provider
    public ScanSetupBuilder get() {
        return (ScanSetupBuilder) Preconditions.checkNotNull(ClientComponent.ClientModule.provideScanSetupProvider(this.deviceSdkProvider.get().intValue(), this.scanSetupBuilderProviderForApi18Provider, this.scanSetupBuilderProviderForApi21Provider, this.scanSetupBuilderProviderForApi23Provider), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ClientComponent_ClientModule_ProvideScanSetupProviderFactory create(Provider<Integer> provider, Provider<ScanSetupBuilderImplApi18> provider2, Provider<ScanSetupBuilderImplApi21> provider3, Provider<ScanSetupBuilderImplApi23> provider4) {
        return new ClientComponent_ClientModule_ProvideScanSetupProviderFactory(provider, provider2, provider3, provider4);
    }

    public static ScanSetupBuilder proxyProvideScanSetupProvider(int r0, Provider<ScanSetupBuilderImplApi18> provider, Provider<ScanSetupBuilderImplApi21> provider2, Provider<ScanSetupBuilderImplApi23> provider3) {
        return (ScanSetupBuilder) Preconditions.checkNotNull(ClientComponent.ClientModule.provideScanSetupProvider(r0, provider, provider2, provider3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
