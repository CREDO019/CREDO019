package expo.modules.core.interfaces;

import android.app.Application;
import android.content.res.Configuration;

/* loaded from: classes4.dex */
public interface ApplicationLifecycleListener {

    /* renamed from: expo.modules.core.interfaces.ApplicationLifecycleListener$-CC  reason: invalid class name */
    /* loaded from: classes4.dex */
    public final /* synthetic */ class CC {
        public static void $default$onConfigurationChanged(ApplicationLifecycleListener _this, Configuration configuration) {
        }

        public static void $default$onCreate(ApplicationLifecycleListener _this, Application application) {
        }
    }

    void onConfigurationChanged(Configuration configuration);

    void onCreate(Application application);
}
