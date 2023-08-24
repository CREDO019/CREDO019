package com.bumptech.glide.load.data.mediastore;

import android.net.Uri;
import com.facebook.common.util.UriUtil;

/* loaded from: classes.dex */
public final class MediaStoreUtil {
    private static final int MINI_THUMB_HEIGHT = 384;
    private static final int MINI_THUMB_WIDTH = 512;

    public static boolean isThumbnailSize(int r1, int r2) {
        return r1 != Integer.MIN_VALUE && r2 != Integer.MIN_VALUE && r1 <= 512 && r2 <= 384;
    }

    private MediaStoreUtil() {
    }

    public static boolean isMediaStoreUri(Uri uri) {
        return uri != null && UriUtil.LOCAL_CONTENT_SCHEME.equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    private static boolean isVideoUri(Uri uri) {
        return uri.getPathSegments().contains("video");
    }

    public static boolean isMediaStoreVideoUri(Uri uri) {
        return isMediaStoreUri(uri) && isVideoUri(uri);
    }

    public static boolean isMediaStoreImageUri(Uri uri) {
        return isMediaStoreUri(uri) && !isVideoUri(uri);
    }
}
