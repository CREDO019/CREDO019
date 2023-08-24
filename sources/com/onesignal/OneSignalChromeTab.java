package com.onesignal;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Build;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OneSignalChromeTab {
    OneSignalChromeTab() {
    }

    private static boolean hasChromeTabLibrary() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean open(String str, boolean z) {
        if (hasChromeTabLibrary()) {
            return CustomTabsClient.bindCustomTabsService(OneSignal.appContext, "com.android.chrome", new OneSignalCustomTabsServiceConnection(str, z));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class OneSignalCustomTabsServiceConnection extends CustomTabsServiceConnection {
        private boolean openActivity;
        private String url;

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        OneSignalCustomTabsServiceConnection(String str, boolean z) {
            this.url = str;
            this.openActivity = z;
        }

        @Override // androidx.browser.customtabs.CustomTabsServiceConnection
        public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
            customTabsClient.warmup(0L);
            CustomTabsSession newSession = customTabsClient.newSession(null);
            if (newSession == null) {
                return;
            }
            Uri parse = Uri.parse(this.url);
            newSession.mayLaunchUrl(parse, null, null);
            if (this.openActivity) {
                CustomTabsIntent build = new CustomTabsIntent.Builder(newSession).build();
                build.intent.setData(parse);
                build.intent.addFlags(268435456);
                if (Build.VERSION.SDK_INT >= 16) {
                    OneSignal.appContext.startActivity(build.intent, build.startAnimationBundle);
                } else {
                    OneSignal.appContext.startActivity(build.intent);
                }
            }
        }
    }
}
