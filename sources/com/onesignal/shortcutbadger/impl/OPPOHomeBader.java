package com.onesignal.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class OPPOHomeBader implements Badger {
    private static final String INTENT_ACTION = "com.oppo.unsettledevent";
    private static final String INTENT_EXTRA_BADGEUPGRADE_COUNT = "app_badge_count";
    private static final String INTENT_EXTRA_BADGE_COUNT = "number";
    private static final String INTENT_EXTRA_BADGE_UPGRADENUMBER = "upgradeNumber";
    private static final String INTENT_EXTRA_PACKAGENAME = "pakeageName";
    private static final String PROVIDER_CONTENT_URI = "content://com.android.badge/badge";
    private int mCurrentTotalCount = -1;

    @Override // com.onesignal.shortcutbadger.Badger
    public void executeBadge(Context context, ComponentName componentName, int r5) throws ShortcutBadgeException {
        if (this.mCurrentTotalCount == r5) {
            return;
        }
        this.mCurrentTotalCount = r5;
        if (Build.VERSION.SDK_INT >= 11) {
            executeBadgeByContentProvider(context, r5);
        } else {
            executeBadgeByBroadcast(context, componentName, r5);
        }
    }

    @Override // com.onesignal.shortcutbadger.Badger
    public List<String> getSupportLaunchers() {
        return Collections.singletonList("com.oppo.launcher");
    }

    private void executeBadgeByBroadcast(Context context, ComponentName componentName, int r5) throws ShortcutBadgeException {
        if (r5 == 0) {
            r5 = -1;
        }
        Intent intent = new Intent(INTENT_ACTION);
        intent.putExtra(INTENT_EXTRA_PACKAGENAME, componentName.getPackageName());
        intent.putExtra(INTENT_EXTRA_BADGE_COUNT, r5);
        intent.putExtra(INTENT_EXTRA_BADGE_UPGRADENUMBER, r5);
        BroadcastHelper.sendIntentExplicitly(context, intent);
    }

    private void executeBadgeByContentProvider(Context context, int r5) throws ShortcutBadgeException {
        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_EXTRA_BADGEUPGRADE_COUNT, r5);
        context.getContentResolver().call(Uri.parse(PROVIDER_CONTENT_URI), "setAppBadgeCount", (String) null, bundle);
    }
}
