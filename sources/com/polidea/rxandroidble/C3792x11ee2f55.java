package com.polidea.rxandroidble;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import com.polidea.rxandroidble.ClientComponent;
import java.util.concurrent.ExecutorService;

/* renamed from: com.polidea.rxandroidble.ClientComponent_ClientModule_ProvideConnectionQueueExecutorServiceFactory */
/* loaded from: classes3.dex */
public final class C3792x11ee2f55 implements Factory<ExecutorService> {
    private static final C3792x11ee2f55 INSTANCE = new C3792x11ee2f55();

    @Override // bleshadow.javax.inject.Provider
    public ExecutorService get() {
        return (ExecutorService) Preconditions.checkNotNull(ClientComponent.ClientModule.provideConnectionQueueExecutorService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static C3792x11ee2f55 create() {
        return INSTANCE;
    }

    public static ExecutorService proxyProvideConnectionQueueExecutorService() {
        return (ExecutorService) Preconditions.checkNotNull(ClientComponent.ClientModule.provideConnectionQueueExecutorService(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
