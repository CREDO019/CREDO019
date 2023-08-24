package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.play.core.internal.v */
/* loaded from: classes3.dex */
public abstract class AbstractBinderC2575v extends BinderC2563j implements InterfaceC2576w {
    public AbstractBinderC2575v() {
        super("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionService");
    }

    @Override // com.google.android.play.core.internal.BinderC2563j
    /* renamed from: a */
    protected final boolean mo649a(int r4, Parcel parcel) throws RemoteException {
        InterfaceC2578y interfaceC2578y = null;
        if (r4 == 2) {
            Bundle bundle = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
                interfaceC2578y = queryLocalInterface instanceof InterfaceC2578y ? (InterfaceC2578y) queryLocalInterface : new C2577x(readStrongBinder);
            }
            mo648a(bundle, interfaceC2578y);
            return true;
        } else if (r4 != 3) {
            return false;
        } else {
            Bundle bundle2 = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            if (readStrongBinder2 != null) {
                IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
                interfaceC2578y = queryLocalInterface2 instanceof InterfaceC2578y ? (InterfaceC2578y) queryLocalInterface2 : new C2577x(readStrongBinder2);
            }
            mo647a(interfaceC2578y);
            return true;
        }
    }
}
