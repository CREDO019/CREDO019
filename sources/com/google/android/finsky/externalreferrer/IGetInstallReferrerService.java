package com.google.android.finsky.externalreferrer;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.p010a.BaseProxy;
import com.google.android.p010a.BaseStub;
import com.google.android.p010a.Codecs;

/* loaded from: classes2.dex */
public interface IGetInstallReferrerService extends IInterface {

    /* loaded from: classes2.dex */
    public static abstract class Stub extends BaseStub implements IGetInstallReferrerService {

        /* loaded from: classes2.dex */
        public static class Proxy extends BaseProxy implements IGetInstallReferrerService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            }

            @Override // com.google.android.finsky.externalreferrer.IGetInstallReferrerService
            /* renamed from: a */
            public final Bundle mo1111a(Bundle bundle) throws RemoteException {
                Parcel m1240a = m1240a();
                Codecs.m1237a(m1240a, bundle);
                Parcel m1239a = m1239a(m1240a);
                Bundle bundle2 = (Bundle) Codecs.m1238a(m1239a, Bundle.CREATOR);
                m1239a.recycle();
                return bundle2;
            }
        }

        public Stub() {
            super("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
        }

        /* renamed from: a */
        public static IGetInstallReferrerService m1112a(IBinder iBinder) {
            if (iBinder != null) {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
                if (queryLocalInterface instanceof IGetInstallReferrerService) {
                    return (IGetInstallReferrerService) queryLocalInterface;
                }
                return new Proxy(iBinder);
            }
            return null;
        }
    }

    /* renamed from: a */
    Bundle mo1111a(Bundle bundle) throws RemoteException;
}
