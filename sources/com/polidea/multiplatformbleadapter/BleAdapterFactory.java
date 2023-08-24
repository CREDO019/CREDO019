package com.polidea.multiplatformbleadapter;

import android.content.Context;

/* loaded from: classes3.dex */
public class BleAdapterFactory {
    private static BleAdapterCreator bleAdapterCreator = new BleAdapterCreator() { // from class: com.polidea.multiplatformbleadapter.BleAdapterFactory.1
        @Override // com.polidea.multiplatformbleadapter.BleAdapterCreator
        public BleAdapter createAdapter(Context context) {
            return new BleModule(context);
        }
    };

    public static BleAdapter getNewAdapter(Context context) {
        return bleAdapterCreator.createAdapter(context);
    }

    public static void setBleAdapterCreator(BleAdapterCreator bleAdapterCreator2) {
        bleAdapterCreator = bleAdapterCreator2;
    }
}
