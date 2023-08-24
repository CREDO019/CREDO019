package com.polidea.rxandroidble;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import com.polidea.rxandroidble.ClientComponent;

/* renamed from: com.polidea.rxandroidble.ClientComponent_ClientModule_ProvideEnableNotificationValueFactory */
/* loaded from: classes3.dex */
public final class C3794xd399626d implements Factory<byte[]> {
    private static final C3794xd399626d INSTANCE = new C3794xd399626d();

    @Override // bleshadow.javax.inject.Provider
    public byte[] get() {
        return (byte[]) Preconditions.checkNotNull(ClientComponent.ClientModule.provideEnableNotificationValue(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static C3794xd399626d create() {
        return INSTANCE;
    }

    public static byte[] proxyProvideEnableNotificationValue() {
        return (byte[]) Preconditions.checkNotNull(ClientComponent.ClientModule.provideEnableNotificationValue(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
