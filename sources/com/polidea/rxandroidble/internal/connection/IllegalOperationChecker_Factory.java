package com.polidea.rxandroidble.internal.connection;

import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;

/* loaded from: classes3.dex */
public final class IllegalOperationChecker_Factory implements Factory<IllegalOperationChecker> {
    private final Provider<IllegalOperationHandler> resultHandlerProvider;

    public IllegalOperationChecker_Factory(Provider<IllegalOperationHandler> provider) {
        this.resultHandlerProvider = provider;
    }

    @Override // bleshadow.javax.inject.Provider
    public IllegalOperationChecker get() {
        return new IllegalOperationChecker(this.resultHandlerProvider.get());
    }

    public static IllegalOperationChecker_Factory create(Provider<IllegalOperationHandler> provider) {
        return new IllegalOperationChecker_Factory(provider);
    }
}
