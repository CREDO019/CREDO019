package com.google.android.gms.internal.vision;

import android.net.Uri;
import androidx.collection.ArrayMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzbb {
    private static final ArrayMap<String, Uri> zzfu = new ArrayMap<>();

    public static synchronized Uri getContentProviderUri(String str) {
        Uri uri;
        synchronized (zzbb.class) {
            ArrayMap<String, Uri> arrayMap = zzfu;
            uri = arrayMap.get(str);
            if (uri == null) {
                String valueOf = String.valueOf(Uri.encode(str));
                uri = Uri.parse(valueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(valueOf) : new String("content://com.google.android.gms.phenotype/"));
                arrayMap.put(str, uri);
            }
        }
        return uri;
    }
}
