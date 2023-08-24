package com.onesignal.shortcutbadger;

import android.content.ComponentName;
import android.content.Context;
import java.util.List;

/* loaded from: classes3.dex */
public interface Badger {
    void executeBadge(Context context, ComponentName componentName, int r3) throws ShortcutBadgeException;

    List<String> getSupportLaunchers();
}
