package expo.modules.apploader;

import android.content.Context;
import expo.modules.core.interfaces.Consumer;

/* loaded from: classes4.dex */
public interface HeadlessAppLoader {
    boolean invalidateApp(String str);

    boolean isRunning(String str);

    void loadApp(Context context, Params params, Runnable runnable, Consumer<Boolean> consumer) throws AppConfigurationError;

    /* loaded from: classes4.dex */
    public static class AppConfigurationError extends Exception {
        public AppConfigurationError(String str) {
            super(str);
        }

        public AppConfigurationError(String str, Exception exc) {
            super(str, exc);
        }
    }

    /* loaded from: classes4.dex */
    public static final class Params {
        private final String appScopeKey;
        private final String appUrl;

        public Params(String str, String str2) {
            this.appScopeKey = str;
            this.appUrl = str2;
        }

        public String getAppScopeKey() {
            return this.appScopeKey;
        }

        public String getAppUrl() {
            return this.appUrl;
        }
    }
}
