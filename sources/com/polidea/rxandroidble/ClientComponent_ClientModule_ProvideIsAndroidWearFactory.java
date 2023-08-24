package com.polidea.rxandroidble;

import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble.ClientComponent;

/* loaded from: classes3.dex */
public final class ClientComponent_ClientModule_ProvideIsAndroidWearFactory implements Factory<Boolean> {
    private final Provider<Integer> deviceSdkProvider;
    private final ClientComponent.ClientModule module;

    public ClientComponent_ClientModule_ProvideIsAndroidWearFactory(ClientComponent.ClientModule clientModule, Provider<Integer> provider) {
        this.module = clientModule;
        this.deviceSdkProvider = provider;
    }

    @Override // bleshadow.javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(this.module.provideIsAndroidWear(this.deviceSdkProvider.get().intValue()));
    }

    public static ClientComponent_ClientModule_ProvideIsAndroidWearFactory create(ClientComponent.ClientModule clientModule, Provider<Integer> provider) {
        return new ClientComponent_ClientModule_ProvideIsAndroidWearFactory(clientModule, provider);
    }

    public static boolean proxyProvideIsAndroidWear(ClientComponent.ClientModule clientModule, int r1) {
        return clientModule.provideIsAndroidWear(r1);
    }
}
