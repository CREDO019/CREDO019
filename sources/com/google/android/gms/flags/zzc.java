package com.google.android.gms.flags;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public interface zzc extends IInterface {
    boolean getBooleanFlagValue(String str, boolean z, int r3) throws RemoteException;

    int getIntFlagValue(String str, int r2, int r3) throws RemoteException;

    long getLongFlagValue(String str, long j, int r4) throws RemoteException;

    String getStringFlagValue(String str, String str2, int r3) throws RemoteException;

    void init(IObjectWrapper iObjectWrapper) throws RemoteException;
}
