package com.facebook.react.modules.share;

import android.app.Activity;
import android.content.Intent;
import androidx.webkit.internal.AssetHelper;
import com.facebook.fbreact.specs.NativeShareModuleSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.onesignal.OneSignalDbContract;

@ReactModule(name = ShareModule.NAME)
/* loaded from: classes.dex */
public class ShareModule extends NativeShareModuleSpec {
    static final String ACTION_SHARED = "sharedAction";
    static final String ERROR_INVALID_CONTENT = "E_INVALID_CONTENT";
    static final String ERROR_UNABLE_TO_OPEN_DIALOG = "E_UNABLE_TO_OPEN_DIALOG";
    public static final String NAME = "ShareModule";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public ShareModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.fbreact.specs.NativeShareModuleSpec
    public void share(ReadableMap readableMap, String str, Promise promise) {
        if (readableMap == null) {
            promise.reject(ERROR_INVALID_CONTENT, "Content cannot be null");
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setTypeAndNormalize(AssetHelper.DEFAULT_MIME_TYPE);
            if (readableMap.hasKey("title")) {
                intent.putExtra("android.intent.extra.SUBJECT", readableMap.getString("title"));
            }
            if (readableMap.hasKey(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE)) {
                intent.putExtra("android.intent.extra.TEXT", readableMap.getString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE));
            }
            Intent createChooser = Intent.createChooser(intent, str);
            createChooser.addCategory("android.intent.category.DEFAULT");
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                currentActivity.startActivity(createChooser);
            } else {
                getReactApplicationContext().startActivity(createChooser);
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putString("action", ACTION_SHARED);
            promise.resolve(createMap);
        } catch (Exception unused) {
            promise.reject(ERROR_UNABLE_TO_OPEN_DIALOG, "Failed to open share dialog");
        }
    }
}