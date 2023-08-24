package com.onesignal;

import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes3.dex */
class OneSignalSimpleDateFormat {
    OneSignalSimpleDateFormat() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleDateFormat iso8601Format() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
    }
}
