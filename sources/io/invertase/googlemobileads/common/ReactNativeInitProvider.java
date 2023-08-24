package io.invertase.googlemobileads.common;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/* loaded from: classes5.dex */
public class ReactNativeInitProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    @OverridingMethodsMustInvokeSuper
    public boolean onCreate() {
        if (ReactNativeApp.getApplicationContext() == null) {
            Context context = getContext();
            if (context != null && context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            ReactNativeApp.setApplicationContext(context);
            return false;
        }
        return false;
    }
}
