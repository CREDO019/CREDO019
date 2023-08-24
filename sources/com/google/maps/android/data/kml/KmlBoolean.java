package com.google.maps.android.data.kml;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;

/* loaded from: classes3.dex */
public class KmlBoolean {
    public static boolean parseBoolean(String str) {
        return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(str) || "true".equals(str);
    }
}
