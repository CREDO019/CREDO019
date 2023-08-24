package com.RNFetchBlob;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.os.Build;
import android.util.Base64;
import com.RNFetchBlob.Response.RNFetchBlobFileResp;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.polidea.rxandroidble.ClientComponent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.C5015Response;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;

/* loaded from: classes.dex */
public class RNFetchBlobReq extends BroadcastReceiver implements Runnable {
    Callback callback;
    OkHttpClient client;
    long contentLength;
    String destPath;
    long downloadManagerId;
    ReadableMap headers;
    String method;
    RNFetchBlobConfig options;
    String rawRequestBody;
    ReadableArray rawRequestBodyArray;
    RNFetchBlobBody requestBody;
    RequestType requestType;
    WritableMap respInfo;
    ResponseType responseType;
    String taskId;
    String url;
    public static HashMap<String, Call> taskTable = new HashMap<>();
    public static HashMap<String, Long> androidDownloadManagerTaskTable = new HashMap<>();
    static HashMap<String, RNFetchBlobProgressConfig> progressReport = new HashMap<>();
    static HashMap<String, RNFetchBlobProgressConfig> uploadProgressReport = new HashMap<>();
    static ConnectionPool pool = new ConnectionPool();
    ResponseFormat responseFormat = ResponseFormat.Auto;
    boolean timeout = false;
    ArrayList<String> redirects = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum RequestType {
        Form,
        SingleFile,
        AsIs,
        WithoutBody,
        Others
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum ResponseFormat {
        Auto,
        UTF8,
        BASE64
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum ResponseType {
        KeepInMemory,
        FileStorage
    }

    public RNFetchBlobReq(ReadableMap readableMap, String str, String str2, String str3, ReadableMap readableMap2, String str4, ReadableArray readableArray, OkHttpClient okHttpClient, Callback callback) {
        this.method = str2.toUpperCase();
        RNFetchBlobConfig rNFetchBlobConfig = new RNFetchBlobConfig(readableMap);
        this.options = rNFetchBlobConfig;
        this.taskId = str;
        this.url = str3;
        this.headers = readableMap2;
        this.callback = callback;
        this.rawRequestBody = str4;
        this.rawRequestBodyArray = readableArray;
        this.client = okHttpClient;
        if (rNFetchBlobConfig.fileCache.booleanValue() || this.options.path != null) {
            this.responseType = ResponseType.FileStorage;
        } else {
            this.responseType = ResponseType.KeepInMemory;
        }
        if (str4 != null) {
            this.requestType = RequestType.SingleFile;
        } else if (readableArray != null) {
            this.requestType = RequestType.Form;
        } else {
            this.requestType = RequestType.WithoutBody;
        }
    }

    public static void cancelTask(String str) {
        if (taskTable.containsKey(str)) {
            taskTable.get(str).cancel();
            taskTable.remove(str);
        }
        if (androidDownloadManagerTaskTable.containsKey(str)) {
            ((DownloadManager) C0908RNFetchBlob.RCTContext.getApplicationContext().getSystemService("download")).remove(androidDownloadManagerTaskTable.get(str).longValue());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0405 A[Catch: Exception -> 0x049d, TryCatch #1 {Exception -> 0x049d, blocks: (B:55:0x01b2, B:57:0x01bc, B:59:0x01c9, B:61:0x01d4, B:63:0x01da, B:65:0x01ee, B:69:0x01fd, B:72:0x0204, B:74:0x020a, B:78:0x021f, B:75:0x0218, B:80:0x0233, B:81:0x0238, B:82:0x023d, B:86:0x024c, B:88:0x0255, B:89:0x0259, B:91:0x025f, B:93:0x0271, B:95:0x0279, B:96:0x027e, B:98:0x0286, B:99:0x028b, B:100:0x029a, B:103:0x02a8, B:105:0x02b0, B:108:0x02b9, B:139:0x033d, B:159:0x042f, B:161:0x044d, B:162:0x045f, B:147:0x035f, B:149:0x0367, B:151:0x036f, B:154:0x0378, B:155:0x0380, B:156:0x038f, B:157:0x03da, B:158:0x0405, B:109:0x02bf, B:111:0x02cb, B:118:0x02e5, B:120:0x02e9, B:122:0x02f1, B:125:0x02fc, B:127:0x0306, B:130:0x0313, B:131:0x0318, B:133:0x0328, B:134:0x032b, B:136:0x0331, B:137:0x0334, B:138:0x0339, B:112:0x02d0, B:114:0x02d6, B:116:0x02dc, B:117:0x02e1, B:85:0x0249, B:58:0x01c3), top: B:169:0x01b2, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x044d A[Catch: Exception -> 0x049d, TryCatch #1 {Exception -> 0x049d, blocks: (B:55:0x01b2, B:57:0x01bc, B:59:0x01c9, B:61:0x01d4, B:63:0x01da, B:65:0x01ee, B:69:0x01fd, B:72:0x0204, B:74:0x020a, B:78:0x021f, B:75:0x0218, B:80:0x0233, B:81:0x0238, B:82:0x023d, B:86:0x024c, B:88:0x0255, B:89:0x0259, B:91:0x025f, B:93:0x0271, B:95:0x0279, B:96:0x027e, B:98:0x0286, B:99:0x028b, B:100:0x029a, B:103:0x02a8, B:105:0x02b0, B:108:0x02b9, B:139:0x033d, B:159:0x042f, B:161:0x044d, B:162:0x045f, B:147:0x035f, B:149:0x0367, B:151:0x036f, B:154:0x0378, B:155:0x0380, B:156:0x038f, B:157:0x03da, B:158:0x0405, B:109:0x02bf, B:111:0x02cb, B:118:0x02e5, B:120:0x02e9, B:122:0x02f1, B:125:0x02fc, B:127:0x0306, B:130:0x0313, B:131:0x0318, B:133:0x0328, B:134:0x032b, B:136:0x0331, B:137:0x0334, B:138:0x0339, B:112:0x02d0, B:114:0x02d6, B:116:0x02dc, B:117:0x02e1, B:85:0x0249, B:58:0x01c3), top: B:169:0x01b2, inners: #0 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 1227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.RNFetchBlobReq.run():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.RNFetchBlob.RNFetchBlobReq$4 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C09284 {
        static final /* synthetic */ int[] $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType;
        static final /* synthetic */ int[] $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$ResponseType;

        static {
            int[] r0 = new int[ResponseType.values().length];
            $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$ResponseType = r0;
            try {
                r0[ResponseType.KeepInMemory.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$ResponseType[ResponseType.FileStorage.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] r2 = new int[RequestType.values().length];
            $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType = r2;
            try {
                r2[RequestType.SingleFile.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType[RequestType.AsIs.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType[RequestType.Form.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType[RequestType.WithoutBody.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseTaskResource() {
        if (taskTable.containsKey(this.taskId)) {
            taskTable.remove(this.taskId);
        }
        if (androidDownloadManagerTaskTable.containsKey(this.taskId)) {
            androidDownloadManagerTaskTable.remove(this.taskId);
        }
        if (uploadProgressReport.containsKey(this.taskId)) {
            uploadProgressReport.remove(this.taskId);
        }
        if (progressReport.containsKey(this.taskId)) {
            progressReport.remove(this.taskId);
        }
        RNFetchBlobBody rNFetchBlobBody = this.requestBody;
        if (rNFetchBlobBody != null) {
            rNFetchBlobBody.clearRequestBody();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void done(C5015Response c5015Response) {
        boolean isBlobResponse = isBlobResponse(c5015Response);
        emitStateEvent(getResponseInfo(c5015Response, isBlobResponse));
        int r1 = C09284.$SwitchMap$com$RNFetchBlob$RNFetchBlobReq$ResponseType[this.responseType.ordinal()];
        if (r1 == 1) {
            if (isBlobResponse) {
                try {
                    if (this.options.auto.booleanValue()) {
                        String tmpPath = RNFetchBlobFS.getTmpPath(this.taskId);
                        InputStream byteStream = c5015Response.body().byteStream();
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(tmpPath));
                        byte[] bArr = new byte[10240];
                        while (true) {
                            int read = byteStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        byteStream.close();
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        this.callback.invoke(null, RNFetchBlobConst.RNFB_RESPONSE_PATH, tmpPath);
                    }
                } catch (IOException unused) {
                    this.callback.invoke("RNFetchBlob failed to encode response data to BASE64 string.", null);
                }
            }
            byte[] bytes = c5015Response.body().bytes();
            CharsetEncoder newEncoder = Charset.forName("UTF-8").newEncoder();
            if (this.responseFormat == ResponseFormat.BASE64) {
                this.callback.invoke(null, RNFetchBlobConst.RNFB_RESPONSE_BASE64, Base64.encodeToString(bytes, 2));
                return;
            }
            try {
                newEncoder.encode(ByteBuffer.wrap(bytes).asCharBuffer());
                this.callback.invoke(null, RNFetchBlobConst.RNFB_RESPONSE_UTF8, new String(bytes));
            } catch (CharacterCodingException unused2) {
                if (this.responseFormat == ResponseFormat.UTF8) {
                    this.callback.invoke(null, RNFetchBlobConst.RNFB_RESPONSE_UTF8, new String(bytes));
                } else {
                    this.callback.invoke(null, RNFetchBlobConst.RNFB_RESPONSE_BASE64, Base64.encodeToString(bytes, 2));
                }
            }
        } else if (r1 == 2) {
            ResponseBody body = c5015Response.body();
            try {
                body.bytes();
            } catch (Exception unused3) {
            }
            RNFetchBlobFileResp rNFetchBlobFileResp = (RNFetchBlobFileResp) body;
            if (rNFetchBlobFileResp != null && !rNFetchBlobFileResp.isDownloadComplete()) {
                this.callback.invoke("Download interrupted.", null);
            } else {
                String replace = this.destPath.replace("?append=true", "");
                this.destPath = replace;
                this.callback.invoke(null, RNFetchBlobConst.RNFB_RESPONSE_PATH, replace);
            }
        } else {
            try {
                this.callback.invoke(null, RNFetchBlobConst.RNFB_RESPONSE_UTF8, new String(c5015Response.body().bytes(), "UTF-8"));
            } catch (IOException unused4) {
                this.callback.invoke("RNFetchBlob failed to encode response data to UTF8 string.", null);
            }
        }
        c5015Response.body().close();
        releaseTaskResource();
    }

    public static RNFetchBlobProgressConfig getReportProgress(String str) {
        if (progressReport.containsKey(str)) {
            return progressReport.get(str);
        }
        return null;
    }

    public static RNFetchBlobProgressConfig getReportUploadProgress(String str) {
        if (uploadProgressReport.containsKey(str)) {
            return uploadProgressReport.get(str);
        }
        return null;
    }

    private WritableMap getResponseInfo(C5015Response c5015Response, boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("status", c5015Response.code());
        createMap.putString("state", "2");
        createMap.putString("taskId", this.taskId);
        createMap.putBoolean(ClientComponent.NamedSchedulers.TIMEOUT, this.timeout);
        WritableMap createMap2 = Arguments.createMap();
        for (int r2 = 0; r2 < c5015Response.headers().size(); r2++) {
            createMap2.putString(c5015Response.headers().name(r2), c5015Response.headers().value(r2));
        }
        WritableArray createArray = Arguments.createArray();
        Iterator<String> it = this.redirects.iterator();
        while (it.hasNext()) {
            createArray.pushString(it.next());
        }
        createMap.putArray("redirects", createArray);
        createMap.putMap("headers", createMap2);
        Headers headers = c5015Response.headers();
        if (z) {
            createMap.putString("respType", "blob");
        } else if (getHeaderIgnoreCases(headers, "content-type").equalsIgnoreCase("text/")) {
            createMap.putString("respType", "text");
        } else if (getHeaderIgnoreCases(headers, "content-type").contains("application/json")) {
            createMap.putString("respType", "json");
        } else {
            createMap.putString("respType", "");
        }
        return createMap;
    }

    private boolean isBlobResponse(C5015Response c5015Response) {
        boolean z;
        String headerIgnoreCases = getHeaderIgnoreCases(c5015Response.headers(), "Content-Type");
        boolean z2 = !headerIgnoreCases.equalsIgnoreCase("text/");
        boolean z3 = !headerIgnoreCases.equalsIgnoreCase("application/json");
        if (this.options.binaryContentTypes != null) {
            for (int r3 = 0; r3 < this.options.binaryContentTypes.size(); r3++) {
                if (headerIgnoreCases.toLowerCase().contains(this.options.binaryContentTypes.getString(r3).toLowerCase())) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        return !(z3 || z2) || z;
    }

    private String getHeaderIgnoreCases(Headers headers, String str) {
        String str2 = headers.get(str);
        return str2 != null ? str2 : headers.get(str.toLowerCase()) == null ? "" : headers.get(str.toLowerCase());
    }

    private String getHeaderIgnoreCases(HashMap<String, String> hashMap, String str) {
        String str2 = hashMap.get(str);
        if (str2 != null) {
            return str2;
        }
        String str3 = hashMap.get(str.toLowerCase());
        return str3 == null ? "" : str3;
    }

    private void emitStateEvent(WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) C0908RNFetchBlob.RCTContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNFetchBlobConst.EVENT_HTTP_STATE, writableMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReceive(android.content.Context r14, android.content.Intent r15) {
        /*
            Method dump skipped, instructions count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.RNFetchBlobReq.onReceive(android.content.Context, android.content.Intent):void");
    }

    public static OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder builder) {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT <= 19) {
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                KeyStore keyStore = null;
                trustManagerFactory.init((KeyStore) null);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                    throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
                }
                X509TrustManager x509TrustManager = (X509TrustManager) trustManagers[0];
                SSLContext sSLContext = SSLContext.getInstance("SSL");
                sSLContext.init(null, new TrustManager[]{x509TrustManager}, null);
                builder.sslSocketFactory(sSLContext.getSocketFactory(), x509TrustManager);
                ConnectionSpec build = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build();
                ArrayList arrayList = new ArrayList();
                arrayList.add(build);
                arrayList.add(ConnectionSpec.COMPATIBLE_TLS);
                arrayList.add(ConnectionSpec.CLEARTEXT);
                builder.connectionSpecs(arrayList);
            } catch (Exception e) {
                FLog.m1327e("OkHttpClientProvider", "Error while enabling TLS 1.2", e);
            }
        }
        return builder;
    }
}
