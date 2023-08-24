package com.airbnb.android.react.maps;

import android.graphics.Bitmap;
import com.google.android.gms.maps.model.BitmapDescriptor;

/* loaded from: classes.dex */
public interface ImageReadable {
    void setIconBitmap(Bitmap bitmap);

    void setIconBitmapDescriptor(BitmapDescriptor bitmapDescriptor);

    void update();
}
