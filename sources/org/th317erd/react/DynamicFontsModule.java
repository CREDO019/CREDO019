package org.th317erd.react;

import android.graphics.Typeface;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.views.text.ReactFontManager;
import java.io.File;

/* loaded from: classes5.dex */
class DynamicFontsModule extends ReactContextBaseJavaModule {
    WritableMap response;
    int tempNameCounter;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "DynamicFonts";
    }

    public DynamicFontsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.tempNameCounter = 0;
    }

    @ReactMethod
    public void loadFontFromFile(ReadableMap readableMap, Callback callback) {
        if (getCurrentActivity() == null) {
            callback.invoke("Invalid activity");
            return;
        }
        String string = readableMap.hasKey("filePath") ? readableMap.getString("filePath") : null;
        String string2 = readableMap.hasKey("name") ? readableMap.getString("name") : null;
        if (string == null || string.length() == 0) {
            callback.invoke("filePath property empty");
            return;
        }
        File file = new File(string);
        if (file.exists() && file.canRead()) {
            try {
                Typeface createFromFile = Typeface.createFromFile(file);
                ReactFontManager.getInstance().setTypeface(string2, createFromFile.getStyle(), createFromFile);
                callback.invoke(null, string2);
            } finally {
            }
        } else {
            callback.invoke("invalid file");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f7 A[Catch: all -> 0x013c, Exception -> 0x013e, TryCatch #0 {Exception -> 0x013e, blocks: (B:47:0x00bb, B:49:0x00ea, B:51:0x00f7, B:52:0x0108, B:54:0x010e, B:55:0x011f, B:58:0x0138, B:59:0x013b), top: B:72:0x00bb, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x010e A[Catch: all -> 0x013c, Exception -> 0x013e, TryCatch #0 {Exception -> 0x013e, blocks: (B:47:0x00bb, B:49:0x00ea, B:51:0x00f7, B:52:0x0108, B:54:0x010e, B:55:0x011f, B:58:0x0138, B:59:0x013b), top: B:72:0x00bb, outer: #2 }] */
    @com.facebook.react.bridge.ReactMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void loadFont(com.facebook.react.bridge.ReadableMap r12, com.facebook.react.bridge.Callback r13) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.th317erd.react.DynamicFontsModule.loadFont(com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Callback):void");
    }
}
