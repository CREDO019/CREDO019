package com.facebook.react.devsupport;

import androidx.core.p005os.EnvironmentCompat;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.MultipartStreamReader;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.C5015Response;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import org.apache.commons.fileupload.FileUploadBase;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BundleDownloader {
    private static final int FILES_CHANGED_COUNT_NOT_BUILT_BY_BUNDLER = -2;
    private static final String TAG = "BundleDownloader";
    private final OkHttpClient mClient;
    private Call mDownloadBundleFromURLCall;

    /* loaded from: classes.dex */
    public static class BundleInfo {
        private int mFilesChangedCount;
        private String mUrl;

        public static BundleInfo fromJSONString(String str) {
            if (str == null) {
                return null;
            }
            BundleInfo bundleInfo = new BundleInfo();
            try {
                JSONObject jSONObject = new JSONObject(str);
                bundleInfo.mUrl = jSONObject.getString(ImagesContract.URL);
                bundleInfo.mFilesChangedCount = jSONObject.getInt("filesChangedCount");
                return bundleInfo;
            } catch (JSONException e) {
                FLog.m1327e(BundleDownloader.TAG, "Invalid bundle info: ", e);
                return null;
            }
        }

        public String toJSONString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(ImagesContract.URL, this.mUrl);
                jSONObject.put("filesChangedCount", this.mFilesChangedCount);
                return jSONObject.toString();
            } catch (JSONException e) {
                FLog.m1327e(BundleDownloader.TAG, "Can't serialize bundle info: ", e);
                return null;
            }
        }

        public String getUrl() {
            String str = this.mUrl;
            return str != null ? str : EnvironmentCompat.MEDIA_UNKNOWN;
        }

        public int getFilesChangedCount() {
            return this.mFilesChangedCount;
        }
    }

    public BundleDownloader(OkHttpClient okHttpClient) {
        this.mClient = okHttpClient;
    }

    public void downloadBundleFromURL(DevBundleDownloadListener devBundleDownloadListener, File file, String str, BundleInfo bundleInfo) {
        downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo, new Request.Builder());
    }

    public void downloadBundleFromURL(final DevBundleDownloadListener devBundleDownloadListener, final File file, String str, final BundleInfo bundleInfo, Request.Builder builder) {
        Call call = (Call) Assertions.assertNotNull(this.mClient.newCall(builder.url(str).addHeader("Accept", FileUploadBase.MULTIPART_MIXED).build()));
        this.mDownloadBundleFromURLCall = call;
        call.enqueue(new Callback() { // from class: com.facebook.react.devsupport.BundleDownloader.1
            @Override // okhttp3.Callback
            public void onFailure(Call call2, IOException iOException) {
                if (BundleDownloader.this.mDownloadBundleFromURLCall == null || BundleDownloader.this.mDownloadBundleFromURLCall.isCanceled()) {
                    BundleDownloader.this.mDownloadBundleFromURLCall = null;
                    return;
                }
                BundleDownloader.this.mDownloadBundleFromURLCall = null;
                String httpUrl = call2.request().url().toString();
                DevBundleDownloadListener devBundleDownloadListener2 = devBundleDownloadListener;
                devBundleDownloadListener2.onFailure(DebugServerException.makeGeneric(httpUrl, "Could not connect to development server.", "URL: " + httpUrl, iOException));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call2, C5015Response c5015Response) throws IOException {
                if (BundleDownloader.this.mDownloadBundleFromURLCall == null || BundleDownloader.this.mDownloadBundleFromURLCall.isCanceled()) {
                    BundleDownloader.this.mDownloadBundleFromURLCall = null;
                    return;
                }
                BundleDownloader.this.mDownloadBundleFromURLCall = null;
                String httpUrl = c5015Response.request().url().toString();
                Matcher matcher = Pattern.compile("multipart/mixed;.*boundary=\"([^\"]+)\"").matcher(c5015Response.header("content-type"));
                try {
                    if (matcher.find()) {
                        BundleDownloader.this.processMultipartResponse(httpUrl, c5015Response, matcher.group(1), file, bundleInfo, devBundleDownloadListener);
                    } else {
                        BundleDownloader.this.processBundleResult(httpUrl, c5015Response.code(), c5015Response.headers(), Okio.buffer(c5015Response.body().source()), file, bundleInfo, devBundleDownloadListener);
                    }
                    if (c5015Response != null) {
                        c5015Response.close();
                    }
                } catch (Throwable th) {
                    if (c5015Response != null) {
                        try {
                            c5015Response.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processMultipartResponse(final String str, final C5015Response c5015Response, String str2, final File file, final BundleInfo bundleInfo, final DevBundleDownloadListener devBundleDownloadListener) throws IOException {
        if (new MultipartStreamReader(c5015Response.body().source(), str2).readAllParts(new MultipartStreamReader.ChunkListener() { // from class: com.facebook.react.devsupport.BundleDownloader.2
            @Override // com.facebook.react.devsupport.MultipartStreamReader.ChunkListener
            public void onChunkComplete(Map<String, String> map, Buffer buffer, boolean z) throws IOException {
                if (z) {
                    int code = c5015Response.code();
                    if (map.containsKey("X-Http-Status")) {
                        code = Integer.parseInt(map.get("X-Http-Status"));
                    }
                    BundleDownloader.this.processBundleResult(str, code, Headers.m155of(map), buffer, file, bundleInfo, devBundleDownloadListener);
                } else if (map.containsKey("Content-Type") && map.get("Content-Type").equals("application/json")) {
                    try {
                        JSONObject jSONObject = new JSONObject(buffer.readUtf8());
                        devBundleDownloadListener.onProgress(jSONObject.has("status") ? jSONObject.getString("status") : "Bundling", jSONObject.has("done") ? Integer.valueOf(jSONObject.getInt("done")) : null, jSONObject.has("total") ? Integer.valueOf(jSONObject.getInt("total")) : null);
                    } catch (JSONException e) {
                        FLog.m1328e(ReactConstants.TAG, "Error parsing progress JSON. " + e.toString());
                    }
                }
            }

            @Override // com.facebook.react.devsupport.MultipartStreamReader.ChunkListener
            public void onChunkProgress(Map<String, String> map, long j, long j2) {
                if ("application/javascript".equals(map.get("Content-Type"))) {
                    devBundleDownloadListener.onProgress("Downloading", Integer.valueOf((int) (j / 1024)), Integer.valueOf((int) (j2 / 1024)));
                }
            }
        })) {
            return;
        }
        devBundleDownloadListener.onFailure(new DebugServerException("Error while reading multipart response.\n\nResponse code: " + c5015Response.code() + "\n\nURL: " + str.toString() + "\n\n"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processBundleResult(String str, int r3, Headers headers, BufferedSource bufferedSource, File file, BundleInfo bundleInfo, DevBundleDownloadListener devBundleDownloadListener) throws IOException {
        if (r3 != 200) {
            String readUtf8 = bufferedSource.readUtf8();
            DebugServerException parse = DebugServerException.parse(str, readUtf8);
            if (parse != null) {
                devBundleDownloadListener.onFailure(parse);
                return;
            }
            devBundleDownloadListener.onFailure(new DebugServerException("The development server returned response error code: " + r3 + "\n\nURL: " + str + "\n\nBody:\n" + readUtf8));
            return;
        }
        if (bundleInfo != null) {
            populateBundleInfo(str, headers, bundleInfo);
        }
        File file2 = new File(file.getPath() + DefaultDiskStorage.FileType.TEMP);
        if (storePlainJSInFile(bufferedSource, file2) && !file2.renameTo(file)) {
            throw new IOException("Couldn't rename " + file2 + " to " + file);
        }
        devBundleDownloadListener.onSuccess();
    }

    private static boolean storePlainJSInFile(BufferedSource bufferedSource, File file) throws IOException {
        Sink sink;
        try {
            sink = Okio.sink(file);
            try {
                bufferedSource.readAll(sink);
                if (sink != null) {
                    sink.close();
                    return true;
                }
                return true;
            } catch (Throwable th) {
                th = th;
                if (sink != null) {
                    sink.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            sink = null;
        }
    }

    private static void populateBundleInfo(String str, Headers headers, BundleInfo bundleInfo) {
        bundleInfo.mUrl = str;
        String str2 = headers.get("X-Metro-Files-Changed-Count");
        if (str2 != null) {
            try {
                bundleInfo.mFilesChangedCount = Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                bundleInfo.mFilesChangedCount = -2;
            }
        }
    }
}
