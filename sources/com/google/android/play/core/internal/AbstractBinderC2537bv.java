package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.play.core.internal.bv */
/* loaded from: classes3.dex */
public abstract class AbstractBinderC2537bv extends BinderC2563j implements InterfaceC2538bw {
    public AbstractBinderC2537bv() {
        super("com.google.android.play.core.splitinstall.protocol.ISplitInstallServiceCallback");
    }

    @Override // com.google.android.play.core.internal.BinderC2563j
    /* renamed from: a */
    protected final boolean mo649a(int r2, Parcel parcel) throws RemoteException {
        switch (r2) {
            case 2:
                mo556c(parcel.readInt(), (Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 3:
                int readInt = parcel.readInt();
                Bundle bundle = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                mo563a(readInt);
                return true;
            case 4:
                mo562a(parcel.readInt(), (Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 5:
                mo558b(parcel.readInt(), (Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 6:
                mo553e((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 7:
                mo560a(parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 8:
                mo554d((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 9:
                mo561a((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 10:
                Bundle bundle2 = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                mo559b();
                return true;
            case 11:
                Bundle bundle3 = (Bundle) C2564k.m680a(parcel, Bundle.CREATOR);
                mo564a();
                return true;
            case 12:
                mo557b((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            case 13:
                mo555c((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
