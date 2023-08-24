package com.polidea.rxandroidble;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble.ClientComponent;
import java.util.concurrent.ExecutorService;
import p042rx.Scheduler;

/* renamed from: com.polidea.rxandroidble.ClientComponent_ClientModule_ProvideBluetoothInteractionSchedulerFactory */
/* loaded from: classes3.dex */
public final class C3791xd84c18d9 implements Factory<Scheduler> {
    private final Provider<ExecutorService> serviceProvider;

    public C3791xd84c18d9(Provider<ExecutorService> provider) {
        this.serviceProvider = provider;
    }

    @Override // bleshadow.javax.inject.Provider
    public Scheduler get() {
        return (Scheduler) Preconditions.checkNotNull(ClientComponent.ClientModule.provideBluetoothInteractionScheduler(this.serviceProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static C3791xd84c18d9 create(Provider<ExecutorService> provider) {
        return new C3791xd84c18d9(provider);
    }

    public static Scheduler proxyProvideBluetoothInteractionScheduler(ExecutorService executorService) {
        return (Scheduler) Preconditions.checkNotNull(ClientComponent.ClientModule.provideBluetoothInteractionScheduler(executorService), "Cannot return null from a non-@Nullable @Provides method");
    }
}
