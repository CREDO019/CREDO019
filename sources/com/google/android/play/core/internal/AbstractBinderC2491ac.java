package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.play.core.internal.ac */
/* loaded from: classes3.dex */
public abstract class AbstractBinderC2491ac extends BinderC2563j implements InterfaceC2492ad {
    public AbstractBinderC2491ac() {
        super("com.google.android.play.core.inappreview.protocol.IInAppReviewServiceCallback");
    }

    @Override // com.google.android.play.core.internal.BinderC2563j
    /* renamed from: a */
    protected final boolean mo649a(int r2, Parcel parcel) throws RemoteException {
        if (r2 == 2) {
            mo622a((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
            return true;
        }
        return false;
    }
}
