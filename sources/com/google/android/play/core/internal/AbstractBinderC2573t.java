package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.play.core.internal.t */
/* loaded from: classes3.dex */
public abstract class AbstractBinderC2573t extends BinderC2563j implements InterfaceC2574u {
    public AbstractBinderC2573t() {
        super("com.google.android.play.core.assetpacks.protocol.IAssetModuleServiceCallback");
    }

    @Override // com.google.android.play.core.internal.BinderC2563j
    /* renamed from: a */
    protected final boolean mo649a(int r2, Parcel parcel) throws RemoteException {
        switch (r2) {
            case 2:
                mo660a(parcel.readInt(), (Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 3:
                int readInt = parcel.readInt();
                Bundle bundle = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                mo661a(readInt);
                return true;
            case 4:
                int readInt2 = parcel.readInt();
                Bundle bundle2 = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                mo655b(readInt2);
                return true;
            case 5:
                mo657a(parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 6:
                Bundle bundle3 = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                mo654b((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 7:
                mo659a((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 8:
                Bundle bundle4 = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                mo652c((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 9:
            default:
                return false;
            case 10:
                Bundle bundle5 = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                mo650d((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 11:
                mo658a((Bundle) C2564k.m680a(parcel, Bundle.CREATOR), (Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 12:
                mo653b((Bundle) C2564k.m680a(parcel, Bundle.CREATOR), (Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 13:
                mo651c((Bundle) C2564k.m680a(parcel, Bundle.CREATOR), (Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 14:
                Bundle bundle6 = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                Bundle bundle7 = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                mo656b();
                return true;
            case 15:
                Bundle bundle8 = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                mo662a();
                return true;
        }
    }
}
