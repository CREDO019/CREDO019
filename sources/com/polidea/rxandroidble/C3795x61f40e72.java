package com.polidea.rxandroidble;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble.ClientComponent;
import com.polidea.rxandroidble.internal.util.LocationServicesOkObservableApi23;
import p042rx.Observable;

/* renamed from: com.polidea.rxandroidble.ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory */
/* loaded from: classes3.dex */
public final class C3795x61f40e72 implements Factory<Observable<Boolean>> {
    private final Provider<Integer> deviceSdkProvider;
    private final Provider<LocationServicesOkObservableApi23> locationServicesOkObservableApi23Provider;
    private final ClientComponent.ClientModule module;

    public C3795x61f40e72(ClientComponent.ClientModule clientModule, Provider<Integer> provider, Provider<LocationServicesOkObservableApi23> provider2) {
        this.module = clientModule;
        this.deviceSdkProvider = provider;
        this.locationServicesOkObservableApi23Provider = provider2;
    }

    @Override // bleshadow.javax.inject.Provider
    public Observable<Boolean> get() {
        return (Observable) Preconditions.checkNotNull(this.module.provideLocationServicesOkObservable(this.deviceSdkProvider.get().intValue(), this.locationServicesOkObservableApi23Provider), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static C3795x61f40e72 create(ClientComponent.ClientModule clientModule, Provider<Integer> provider, Provider<LocationServicesOkObservableApi23> provider2) {
        return new C3795x61f40e72(clientModule, provider, provider2);
    }

    public static Observable<Boolean> proxyProvideLocationServicesOkObservable(ClientComponent.ClientModule clientModule, int r1, Provider<LocationServicesOkObservableApi23> provider) {
        return (Observable) Preconditions.checkNotNull(clientModule.provideLocationServicesOkObservable(r1, provider), "Cannot return null from a non-@Nullable @Provides method");
    }
}
