package com.polidea.rxandroidble;

import android.content.Context;
import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;

/* loaded from: classes3.dex */
public final class RxBleAdapterStateObservable_Factory implements Factory<RxBleAdapterStateObservable> {
    private final Provider<Context> contextProvider;

    public RxBleAdapterStateObservable_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // bleshadow.javax.inject.Provider
    public RxBleAdapterStateObservable get() {
        return new RxBleAdapterStateObservable(this.contextProvider.get());
    }

    public static RxBleAdapterStateObservable_Factory create(Provider<Context> provider) {
        return new RxBleAdapterStateObservable_Factory(provider);
    }
}
