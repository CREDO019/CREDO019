package com.google.android.play.core.internal;

import android.os.Build;
import java.io.File;

/* renamed from: com.google.android.play.core.internal.av */
/* loaded from: classes3.dex */
public final class C2510av {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public static InterfaceC2509au m776a() {
        if (Build.VERSION.SDK_INT >= 21) {
            switch (Build.VERSION.SDK_INT) {
                case 21:
                    return new C2516ba();
                case 22:
                    return new C2517bb();
                case 23:
                    return new C2521bf();
                case 24:
                    return new C2522bg();
                case 25:
                    return new C2523bh();
                case 26:
                    return new C2526bk();
                case 27:
                    if (Build.VERSION.PREVIEW_SDK_INT == 0) {
                        return new C2527bl();
                    }
                    break;
            }
            return new C2529bn();
        }
        throw new AssertionError("Unsupported Android Version");
    }

    /* renamed from: a */
    public static String m775a(File file) {
        if (file.getName().endsWith(".apk")) {
            return file.getName().replaceFirst("(_\\d+)?\\.apk", "").replace("base-", "config.").replace("-", ".config.").replace(".config.master", "").replace("config.master", "");
        }
        throw new IllegalArgumentException("Non-apk found in splits directory.");
    }

    /* renamed from: a */
    public static <T> void m774a(T t, Object obj) {
        if (t == null) {
            throw new NullPointerException((String) obj);
        }
    }

    /* renamed from: a */
    public static void m773a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }
}
