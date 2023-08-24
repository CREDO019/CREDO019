package com.polidea.rxandroidble;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import com.polidea.rxandroidble.ClientComponent;
import java.util.concurrent.ExecutorService;

/* renamed from: com.polidea.rxandroidble.ClientComponent_ClientModule_ProvideBluetoothInteractionExecutorServiceFactory */
/* loaded from: classes3.dex */
public final class C3790xa9312dd2 implements Factory<ExecutorService> {
    private static final C3790xa9312dd2 INSTANCE = new C3790xa9312dd2();

    @Override // bleshadow.javax.inject.Provider
    public ExecutorService get() {
        return (ExecutorService) Preconditions.checkNotNull(ClientComponent.ClientModule.provideBluetoothInteractionExecutorService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static C3790xa9312dd2 create() {
        return INSTANCE;
    }

    public static ExecutorService proxyProvideBluetoothInteractionExecutorService() {
        return (ExecutorService) Preconditions.checkNotNull(ClientComponent.ClientModule.provideBluetoothInteractionExecutorService(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
