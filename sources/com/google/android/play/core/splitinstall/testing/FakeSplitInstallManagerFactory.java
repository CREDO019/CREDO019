package com.google.android.play.core.splitinstall.testing;

import android.content.Context;
import com.google.android.play.core.common.LocalTestingException;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitinstall.C2646k;
import com.google.android.play.core.splitinstall.C2652p;
import java.io.File;

/* loaded from: classes3.dex */
public class FakeSplitInstallManagerFactory {

    /* renamed from: a */
    private static FakeSplitInstallManager f1040a;

    public static FakeSplitInstallManager create(Context context) {
        try {
            File mo521b = C2646k.m525a(context).mo521b();
            if (mo521b != null) {
                if (mo521b.exists()) {
                    return create(context, mo521b);
                }
                throw new LocalTestingException(String.format("Local testing directory not found: %s", mo521b));
            }
            throw new LocalTestingException("Failed to retrieve local testing directory path");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized FakeSplitInstallManager create(Context context, File file) {
        FakeSplitInstallManager fakeSplitInstallManager;
        synchronized (FakeSplitInstallManagerFactory.class) {
            FakeSplitInstallManager fakeSplitInstallManager2 = f1040a;
            if (fakeSplitInstallManager2 == null) {
                f1040a = createNewInstance(context, file);
            } else if (!fakeSplitInstallManager2.m504a().getAbsolutePath().equals(file.getAbsolutePath())) {
                throw new RuntimeException(String.format("Different module directories used to initialize FakeSplitInstallManager: '%s' and '%s'", f1040a.m504a().getAbsolutePath(), file.getAbsolutePath()));
            }
            fakeSplitInstallManager = f1040a;
        }
        return fakeSplitInstallManager;
    }

    public static FakeSplitInstallManager createNewInstance(Context context, File file) {
        SplitCompat.install(context);
        return new FakeSplitInstallManager(context, file, new C2652p(context, context.getPackageName()));
    }
}
