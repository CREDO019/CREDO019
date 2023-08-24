package com.RNFetchBlob;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.SparseArray;
import androidx.core.content.FileProvider;
import com.RNFetchBlob.RNFetchBlobProgressConfig;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.network.CookieJarContainer;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.facebook.react.modules.network.OkHttpClientProvider;
import expo.modules.imagepicker.MediaTypes;
import java.io.File;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

/* renamed from: com.RNFetchBlob.RNFetchBlob */
/* loaded from: classes.dex */
public class C0908RNFetchBlob extends ReactContextBaseJavaModule {
    static ReactApplicationContext RCTContext;
    private final OkHttpClient mClient;
    private static LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 5000, TimeUnit.MILLISECONDS, taskQueue);
    static LinkedBlockingQueue<Runnable> fsTaskQueue = new LinkedBlockingQueue<>();
    private static ThreadPoolExecutor fsThreadPool = new ThreadPoolExecutor(2, 10, 5000, TimeUnit.MILLISECONDS, taskQueue);
    private static boolean ActionViewVisible = false;
    private static SparseArray<Promise> promiseTable = new SparseArray<>();

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNFetchBlob";
    }

    public C0908RNFetchBlob(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        OkHttpClient okHttpClient = OkHttpClientProvider.getOkHttpClient();
        this.mClient = okHttpClient;
        ((CookieJarContainer) okHttpClient.cookieJar()).setCookieJar(new JavaNetCookieJar(new ForwardingCookieHandler(reactApplicationContext)));
        RCTContext = reactApplicationContext;
        reactApplicationContext.addActivityEventListener(new ActivityEventListener() { // from class: com.RNFetchBlob.RNFetchBlob.1
            @Override // com.facebook.react.bridge.ActivityEventListener
            public void onNewIntent(Intent intent) {
            }

            @Override // com.facebook.react.bridge.ActivityEventListener
            public void onActivityResult(Activity activity, int r2, int r3, Intent intent) {
                if (r2 == RNFetchBlobConst.GET_CONTENT_INTENT.intValue() && r3 == -1) {
                    ((Promise) C0908RNFetchBlob.promiseTable.get(RNFetchBlobConst.GET_CONTENT_INTENT.intValue())).resolve(intent.getData().toString());
                    C0908RNFetchBlob.promiseTable.remove(RNFetchBlobConst.GET_CONTENT_INTENT.intValue());
                }
            }
        });
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return RNFetchBlobFS.getSystemfolders(getReactApplicationContext());
    }

    @ReactMethod
    public void createFile(final String str, final String str2, final String str3, final Promise promise) {
        threadPool.execute(new Runnable() { // from class: com.RNFetchBlob.RNFetchBlob.2
            @Override // java.lang.Runnable
            public void run() {
                RNFetchBlobFS.createFile(str, str2, str3, promise);
            }
        });
    }

    @ReactMethod
    public void createFileASCII(final String str, final ReadableArray readableArray, final Promise promise) {
        threadPool.execute(new Runnable() { // from class: com.RNFetchBlob.RNFetchBlob.3
            @Override // java.lang.Runnable
            public void run() {
                RNFetchBlobFS.createFileASCII(str, readableArray, promise);
            }
        });
    }

    @ReactMethod
    public void actionViewIntent(String str, String str2, final Promise promise) {
        try {
            Activity currentActivity = getCurrentActivity();
            Uri uriForFile = FileProvider.getUriForFile(currentActivity, getReactApplicationContext().getPackageName() + ".provider", new File(str));
            if (Build.VERSION.SDK_INT >= 24) {
                Intent dataAndType = new Intent("android.intent.action.VIEW").setDataAndType(uriForFile, str2);
                dataAndType.setFlags(1);
                dataAndType.addFlags(268435456);
                if (dataAndType.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
                    getReactApplicationContext().startActivity(dataAndType);
                }
            } else {
                Intent intent = new Intent("android.intent.action.VIEW");
                getReactApplicationContext().startActivity(intent.setDataAndType(Uri.parse("file://" + str), str2).setFlags(268435456));
            }
            ActionViewVisible = true;
            RCTContext.addLifecycleEventListener(new LifecycleEventListener() { // from class: com.RNFetchBlob.RNFetchBlob.4
                @Override // com.facebook.react.bridge.LifecycleEventListener
                public void onHostDestroy() {
                }

                @Override // com.facebook.react.bridge.LifecycleEventListener
                public void onHostPause() {
                }

                @Override // com.facebook.react.bridge.LifecycleEventListener
                public void onHostResume() {
                    if (C0908RNFetchBlob.ActionViewVisible) {
                        promise.resolve(null);
                    }
                    C0908RNFetchBlob.RCTContext.removeLifecycleEventListener(this);
                }
            });
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    @ReactMethod
    public void writeArrayChunk(String str, ReadableArray readableArray, Callback callback) {
        RNFetchBlobFS.writeArrayChunk(str, readableArray, callback);
    }

    @ReactMethod
    public void unlink(String str, Callback callback) {
        RNFetchBlobFS.unlink(str, callback);
    }

    @ReactMethod
    public void mkdir(String str, Promise promise) {
        RNFetchBlobFS.mkdir(str, promise);
    }

    @ReactMethod
    public void exists(String str, Callback callback) {
        RNFetchBlobFS.exists(str, callback);
    }

    @ReactMethod
    /* renamed from: cp */
    public void m1381cp(final String str, final String str2, final Callback callback) {
        threadPool.execute(new Runnable() { // from class: com.RNFetchBlob.RNFetchBlob.5
            @Override // java.lang.Runnable
            public void run() {
                RNFetchBlobFS.m1377cp(str, str2, callback);
            }
        });
    }

    @ReactMethod
    /* renamed from: mv */
    public void m1378mv(String str, String str2, Callback callback) {
        RNFetchBlobFS.m1374mv(str, str2, callback);
    }

    @ReactMethod
    /* renamed from: ls */
    public void m1379ls(String str, Promise promise) {
        RNFetchBlobFS.m1375ls(str, promise);
    }

    @ReactMethod
    public void writeStream(String str, String str2, boolean z, Callback callback) {
        new RNFetchBlobFS(getReactApplicationContext()).writeStream(str, str2, z, callback);
    }

    @ReactMethod
    public void writeChunk(String str, String str2, Callback callback) {
        RNFetchBlobFS.writeChunk(str, str2, callback);
    }

    @ReactMethod
    public void closeStream(String str, Callback callback) {
        RNFetchBlobFS.closeStream(str, callback);
    }

    @ReactMethod
    public void removeSession(ReadableArray readableArray, Callback callback) {
        RNFetchBlobFS.removeSession(readableArray, callback);
    }

    @ReactMethod
    public void readFile(final String str, final String str2, final Promise promise) {
        threadPool.execute(new Runnable() { // from class: com.RNFetchBlob.RNFetchBlob.6
            @Override // java.lang.Runnable
            public void run() {
                RNFetchBlobFS.readFile(str, str2, promise);
            }
        });
    }

    @ReactMethod
    public void writeFileArray(final String str, final ReadableArray readableArray, final boolean z, final Promise promise) {
        threadPool.execute(new Runnable() { // from class: com.RNFetchBlob.RNFetchBlob.7
            @Override // java.lang.Runnable
            public void run() {
                RNFetchBlobFS.writeFile(str, readableArray, z, promise);
            }
        });
    }

    @ReactMethod
    public void writeFile(final String str, final String str2, final String str3, final boolean z, final Promise promise) {
        threadPool.execute(new Runnable() { // from class: com.RNFetchBlob.RNFetchBlob.8
            @Override // java.lang.Runnable
            public void run() {
                RNFetchBlobFS.writeFile(str, str2, str3, z, promise);
            }
        });
    }

    @ReactMethod
    public void lstat(String str, Callback callback) {
        RNFetchBlobFS.lstat(str, callback);
    }

    @ReactMethod
    public void stat(String str, Callback callback) {
        RNFetchBlobFS.stat(str, callback);
    }

    @ReactMethod
    public void scanFile(final ReadableArray readableArray, final Callback callback) {
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        threadPool.execute(new Runnable() { // from class: com.RNFetchBlob.RNFetchBlob.9
            @Override // java.lang.Runnable
            public void run() {
                int size = readableArray.size();
                String[] strArr = new String[size];
                String[] strArr2 = new String[size];
                for (int r3 = 0; r3 < size; r3++) {
                    ReadableMap map = readableArray.getMap(r3);
                    if (map.hasKey(RNFetchBlobConst.RNFB_RESPONSE_PATH)) {
                        strArr[r3] = map.getString(RNFetchBlobConst.RNFB_RESPONSE_PATH);
                        if (map.hasKey("mime")) {
                            strArr2[r3] = map.getString("mime");
                        } else {
                            strArr2[r3] = null;
                        }
                    }
                }
                new RNFetchBlobFS(reactApplicationContext).scanFile(strArr, strArr2, callback);
            }
        });
    }

    @ReactMethod
    public void hash(final String str, final String str2, final Promise promise) {
        threadPool.execute(new Runnable() { // from class: com.RNFetchBlob.RNFetchBlob.10
            @Override // java.lang.Runnable
            public void run() {
                RNFetchBlobFS.hash(str, str2, promise);
            }
        });
    }

    @ReactMethod
    public void readStream(final String str, final String str2, final int r13, final int r14, final String str3) {
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        fsThreadPool.execute(new Runnable() { // from class: com.RNFetchBlob.RNFetchBlob.11
            @Override // java.lang.Runnable
            public void run() {
                new RNFetchBlobFS(reactApplicationContext).readStream(str, str2, r13, r14, str3);
            }
        });
    }

    @ReactMethod
    public void cancelRequest(String str, Callback callback) {
        try {
            RNFetchBlobReq.cancelTask(str);
            callback.invoke(null, str);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage(), null);
        }
    }

    @ReactMethod
    public void slice(String str, String str2, int r9, int r10, Promise promise) {
        RNFetchBlobFS.slice(str, str2, r9, r10, "", promise);
    }

    @ReactMethod
    public void enableProgressReport(String str, int r5, int r6) {
        RNFetchBlobReq.progressReport.put(str, new RNFetchBlobProgressConfig(true, r5, r6, RNFetchBlobProgressConfig.ReportType.Download));
    }

    @ReactMethod
    /* renamed from: df */
    public void m1380df(final Callback callback) {
        fsThreadPool.execute(new Runnable() { // from class: com.RNFetchBlob.RNFetchBlob.12
            @Override // java.lang.Runnable
            public void run() {
                RNFetchBlobFS.m1376df(callback);
            }
        });
    }

    @ReactMethod
    public void enableUploadProgressReport(String str, int r5, int r6) {
        RNFetchBlobReq.uploadProgressReport.put(str, new RNFetchBlobProgressConfig(true, r5, r6, RNFetchBlobProgressConfig.ReportType.Upload));
    }

    @ReactMethod
    public void fetchBlob(ReadableMap readableMap, String str, String str2, String str3, ReadableMap readableMap2, String str4, Callback callback) {
        new RNFetchBlobReq(readableMap, str, str2, str3, readableMap2, str4, null, this.mClient, callback).run();
    }

    @ReactMethod
    public void fetchBlobForm(ReadableMap readableMap, String str, String str2, String str3, ReadableMap readableMap2, ReadableArray readableArray, Callback callback) {
        new RNFetchBlobReq(readableMap, str, str2, str3, readableMap2, null, readableArray, this.mClient, callback).run();
    }

    @ReactMethod
    public void getContentIntent(String str, Promise promise) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        if (str != null) {
            intent.setType(str);
        } else {
            intent.setType(MediaTypes.AllMimeType);
        }
        promiseTable.put(RNFetchBlobConst.GET_CONTENT_INTENT.intValue(), promise);
        getReactApplicationContext().startActivityForResult(intent, RNFetchBlobConst.GET_CONTENT_INTENT.intValue(), null);
    }

    @ReactMethod
    public void addCompleteDownload(ReadableMap readableMap, Promise promise) {
        DownloadManager downloadManager = (DownloadManager) RCTContext.getSystemService("download");
        if (readableMap == null || !readableMap.hasKey(RNFetchBlobConst.RNFB_RESPONSE_PATH)) {
            promise.reject("EINVAL", "RNFetchblob.addCompleteDownload config or path missing.");
            return;
        }
        String normalizePath = RNFetchBlobFS.normalizePath(readableMap.getString(RNFetchBlobConst.RNFB_RESPONSE_PATH));
        if (normalizePath == null) {
            promise.reject("EINVAL", "RNFetchblob.addCompleteDownload can not resolve URI:" + readableMap.getString(RNFetchBlobConst.RNFB_RESPONSE_PATH));
            return;
        }
        try {
            downloadManager.addCompletedDownload(readableMap.hasKey("title") ? readableMap.getString("title") : "", readableMap.hasKey("description") ? readableMap.getString("description") : "", true, readableMap.hasKey("mime") ? readableMap.getString("mime") : null, normalizePath, Long.valueOf(RNFetchBlobFS.statFile(normalizePath).getString("size")).longValue(), readableMap.hasKey("showNotification") && readableMap.getBoolean("showNotification"));
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    @ReactMethod
    public void getSDCardDir(Promise promise) {
        RNFetchBlobFS.getSDCardDir(promise);
    }

    @ReactMethod
    public void getSDCardApplicationDir(Promise promise) {
        RNFetchBlobFS.getSDCardApplicationDir(getReactApplicationContext(), promise);
    }
}
