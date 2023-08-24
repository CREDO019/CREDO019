package androidx.media;

import android.os.Bundle;
import android.support.p001v4.media.MediaBrowserCompat;

/* loaded from: classes.dex */
public class MediaBrowserCompatUtils {
    public static boolean areSameOptions(Bundle options1, Bundle options2) {
        if (options1 == options2) {
            return true;
        }
        return options1 == null ? options2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == -1 && options2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == -1 : options2 == null ? options1.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == -1 && options1.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == -1 : options1.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == options2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) && options1.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == options2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
    }

    public static boolean hasDuplicatedItems(Bundle options1, Bundle options2) {
        int r6;
        int r2;
        int r0;
        int r22 = options1 == null ? -1 : options1.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int r02 = options2 == null ? -1 : options2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int r62 = options1 == null ? -1 : options1.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        int r7 = options2 == null ? -1 : options2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        int r3 = Integer.MAX_VALUE;
        if (r22 == -1 || r62 == -1) {
            r6 = Integer.MAX_VALUE;
            r2 = 0;
        } else {
            r2 = r22 * r62;
            r6 = (r62 + r2) - 1;
        }
        if (r02 == -1 || r7 == -1) {
            r0 = 0;
        } else {
            r0 = r02 * r7;
            r3 = (r7 + r0) - 1;
        }
        return r6 >= r0 && r3 >= r2;
    }

    private MediaBrowserCompatUtils() {
    }
}
