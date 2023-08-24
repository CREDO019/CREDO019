package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
public interface ICameraUpdateFactoryDelegate extends IInterface {
    IObjectWrapper newCameraPosition(CameraPosition cameraPosition) throws RemoteException;

    IObjectWrapper newLatLng(LatLng latLng) throws RemoteException;

    IObjectWrapper newLatLngBounds(LatLngBounds latLngBounds, int r2) throws RemoteException;

    IObjectWrapper newLatLngBoundsWithSize(LatLngBounds latLngBounds, int r2, int r3, int r4) throws RemoteException;

    IObjectWrapper newLatLngZoom(LatLng latLng, float f) throws RemoteException;

    IObjectWrapper scrollBy(float f, float f2) throws RemoteException;

    IObjectWrapper zoomBy(float f) throws RemoteException;

    IObjectWrapper zoomByWithFocus(float f, int r2, int r3) throws RemoteException;

    IObjectWrapper zoomIn() throws RemoteException;

    IObjectWrapper zoomOut() throws RemoteException;

    IObjectWrapper zoomTo(float f) throws RemoteException;
}