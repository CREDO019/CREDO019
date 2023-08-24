package com.polidea.rxandroidble;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble.ClientComponent;
import java.util.concurrent.ExecutorService;
import p042rx.Scheduler;

/* renamed from: com.polidea.rxandroidble.ClientComponent_ClientModule_ProvideBluetoothCallbacksSchedulerFactory */
/* loaded from: classes3.dex */
public final class C3789x76cd1195 implements Factory<Scheduler> {
    private final Provider<ExecutorService> serviceProvider;

    public C3789x76cd1195(Provider<ExecutorService> provider) {
        this.serviceProvider = provider;
    }

    @Override // bleshadow.javax.inject.Provider
    public Scheduler get() {
        return (Scheduler) Preconditions.checkNotNull(ClientComponent.ClientModule.provideBluetoothCallbacksScheduler(this.serviceProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static C3789x76cd1195 create(Provider<ExecutorService> provider) {
        return new C3789x76cd1195(provider);
    }

    public static Scheduler proxyProvideBluetoothCallbacksScheduler(ExecutorService executorService) {
        return (Scheduler) Preconditions.checkNotNull(ClientComponent.ClientModule.provideBluetoothCallbacksScheduler(executorService), "Cannot return null from a non-@Nullable @Provides method");
    }
}
