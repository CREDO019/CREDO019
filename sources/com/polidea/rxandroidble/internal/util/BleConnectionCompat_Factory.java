package com.polidea.rxandroidble.internal.util;

import android.content.Context;
import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;

/* loaded from: classes3.dex */
public final class BleConnectionCompat_Factory implements Factory<BleConnectionCompat> {
    private final Provider<Context> contextProvider;

    public BleConnectionCompat_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // bleshadow.javax.inject.Provider
    public BleConnectionCompat get() {
        return new BleConnectionCompat(this.contextProvider.get());
    }

    public static BleConnectionCompat_Factory create(Provider<Context> provider) {
        return new BleConnectionCompat_Factory(provider);
    }
}
