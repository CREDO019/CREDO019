package com.google.android.play.core.assetpacks;

import android.content.Context;

/* loaded from: classes3.dex */
public class AssetPackManagerFactory {
    public static synchronized AssetPackManager getInstance(Context context) {
        AssetPackManager mo956a;
        synchronized (AssetPackManagerFactory.class) {
            mo956a = C2436db.m907a(context).mo956a();
        }
        return mo956a;
    }
}
