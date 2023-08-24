package expo.modules.core.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes4.dex */
public interface ReactActivityLifecycleListener {

    /* renamed from: expo.modules.core.interfaces.ReactActivityLifecycleListener$-CC  reason: invalid class name */
    /* loaded from: classes4.dex */
    public final /* synthetic */ class CC {
        public static boolean $default$onBackPressed(ReactActivityLifecycleListener _this) {
            return false;
        }

        public static void $default$onCreate(ReactActivityLifecycleListener _this, Activity activity, Bundle bundle) {
        }

        public static void $default$onDestroy(ReactActivityLifecycleListener _this, Activity activity) {
        }

        public static boolean $default$onNewIntent(ReactActivityLifecycleListener _this, Intent intent) {
            return false;
        }

        public static void $default$onPause(ReactActivityLifecycleListener _this, Activity activity) {
        }

        public static void $default$onResume(ReactActivityLifecycleListener _this, Activity activity) {
        }
    }

    boolean onBackPressed();

    void onCreate(Activity activity, Bundle bundle);

    void onDestroy(Activity activity);

    boolean onNewIntent(Intent intent);

    void onPause(Activity activity);

    void onResume(Activity activity);
}
