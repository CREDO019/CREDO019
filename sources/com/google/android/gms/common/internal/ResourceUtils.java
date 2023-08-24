package com.google.android.gms.common.internal;

import android.net.Uri;
import com.facebook.common.util.UriUtil;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
public final class ResourceUtils {
    private static final Uri zza = new Uri.Builder().scheme(UriUtil.QUALIFIED_RESOURCE_SCHEME).authority("com.google.android.gms").appendPath("drawable").build();

    private ResourceUtils() {
    }
}