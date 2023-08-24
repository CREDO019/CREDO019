package com.facebook.react.modules.network;

import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeNetworkingAndroidSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.StandardCharsets;
import com.facebook.react.common.network.OkHttpCallUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.C5015Response;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.ByteString;
import okio.GzipSource;
import okio.Okio;

@ReactModule(name = NetworkingModule.NAME)
/* loaded from: classes.dex */
public final class NetworkingModule extends NativeNetworkingAndroidSpec {
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    public static final String NAME = "Networking";
    private static final String REQUEST_BODY_KEY_BASE64 = "base64";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String TAG = "NetworkingModule";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";
    private static CustomClientBuilder customClientBuilder;
    private final OkHttpClient mClient;
    private final ForwardingCookieHandler mCookieHandler;
    private final CookieJarContainer mCookieJarContainer;
    private final String mDefaultUserAgent;
    private final List<RequestBodyHandler> mRequestBodyHandlers;
    private final Set<Integer> mRequestIds;
    private final List<ResponseHandler> mResponseHandlers;
    private boolean mShuttingDown;
    private final List<UriHandler> mUriHandlers;

    /* loaded from: classes.dex */
    public interface CustomClientBuilder {
        void apply(OkHttpClient.Builder builder);
    }

    /* loaded from: classes.dex */
    public interface RequestBodyHandler {
        boolean supports(ReadableMap readableMap);

        RequestBody toRequestBody(ReadableMap readableMap, String str);
    }

    /* loaded from: classes.dex */
    public interface ResponseHandler {
        boolean supports(String str);

        WritableMap toResponseData(ResponseBody responseBody) throws IOException;
    }

    /* loaded from: classes.dex */
    public interface UriHandler {
        WritableMap fetch(Uri uri) throws IOException;

        boolean supports(Uri uri, String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldDispatch(long j, long j2) {
        return j2 + 100000000 < j;
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void removeListeners(double d) {
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, String str, OkHttpClient okHttpClient, List<NetworkInterceptorCreator> list) {
        super(reactApplicationContext);
        this.mRequestBodyHandlers = new ArrayList();
        this.mUriHandlers = new ArrayList();
        this.mResponseHandlers = new ArrayList();
        if (list != null) {
            OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
            for (NetworkInterceptorCreator networkInterceptorCreator : list) {
                newBuilder.addNetworkInterceptor(networkInterceptorCreator.create());
            }
            okHttpClient = newBuilder.build();
        }
        this.mClient = okHttpClient;
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
        this.mCookieJarContainer = (CookieJarContainer) okHttpClient.cookieJar();
        this.mShuttingDown = false;
        this.mDefaultUserAgent = str;
        this.mRequestIds = new HashSet();
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, String str, OkHttpClient okHttpClient) {
        this(reactApplicationContext, str, okHttpClient, null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null, OkHttpClientProvider.createClient(reactApplicationContext), null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, List<NetworkInterceptorCreator> list) {
        this(reactApplicationContext, null, OkHttpClientProvider.createClient(reactApplicationContext), list);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, String str) {
        this(reactApplicationContext, str, OkHttpClientProvider.createClient(reactApplicationContext), null);
    }

    public static void setCustomClientBuilder(CustomClientBuilder customClientBuilder2) {
        customClientBuilder = customClientBuilder2;
    }

    private static void applyCustomBuilder(OkHttpClient.Builder builder) {
        CustomClientBuilder customClientBuilder2 = customClientBuilder;
        if (customClientBuilder2 != null) {
            customClientBuilder2.apply(builder);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        this.mCookieJarContainer.setCookieJar(new JavaNetCookieJar(this.mCookieHandler));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        this.mShuttingDown = true;
        cancelAllRequests();
        this.mCookieHandler.destroy();
        this.mCookieJarContainer.removeCookieJar();
        this.mRequestBodyHandlers.clear();
        this.mResponseHandlers.clear();
        this.mUriHandlers.clear();
    }

    public void addUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.add(uriHandler);
    }

    public void addRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.add(requestBodyHandler);
    }

    public void addResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.add(responseHandler);
    }

    public void removeUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.remove(uriHandler);
    }

    public void removeRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.remove(requestBodyHandler);
    }

    public void removeResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.remove(responseHandler);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void sendRequest(String str, String str2, double d, ReadableArray readableArray, ReadableMap readableMap, String str3, boolean z, double d2, boolean z2) {
        int r10 = (int) d;
        try {
            sendRequestInternal(str, str2, r10, readableArray, readableMap, str3, z, (int) d2, z2);
        } catch (Throwable th) {
            FLog.m1327e(TAG, "Failed to send url request: " + str2, th);
            DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("sendRequest error");
            if (eventEmitter != null) {
                ResponseUtil.onRequestError(eventEmitter, r10, th.getMessage(), th);
            }
        }
    }

    public void sendRequestInternal(String str, String str2, final int r17, ReadableArray readableArray, ReadableMap readableMap, final String str3, final boolean z, int r22, boolean z2) {
        RequestBodyHandler requestBodyHandler;
        RequestBody emptyBody;
        Charset charset;
        final DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("sendRequestInternal");
        try {
            Uri parse = Uri.parse(str2);
            for (UriHandler uriHandler : this.mUriHandlers) {
                if (uriHandler.supports(parse, str3)) {
                    ResponseUtil.onDataReceived(eventEmitter, r17, uriHandler.fetch(parse));
                    ResponseUtil.onRequestSuccess(eventEmitter, r17);
                    return;
                }
            }
            try {
                Request.Builder url = new Request.Builder().url(str2);
                if (r17 != 0) {
                    url.tag(Integer.valueOf(r17));
                }
                OkHttpClient.Builder newBuilder = this.mClient.newBuilder();
                applyCustomBuilder(newBuilder);
                if (!z2) {
                    newBuilder.cookieJar(CookieJar.NO_COOKIES);
                }
                if (z) {
                    newBuilder.addNetworkInterceptor(new Interceptor() { // from class: com.facebook.react.modules.network.NetworkingModule.1
                        @Override // okhttp3.Interceptor
                        public C5015Response intercept(Interceptor.Chain chain) throws IOException {
                            C5015Response proceed = chain.proceed(chain.request());
                            return proceed.newBuilder().body(new ProgressResponseBody(proceed.body(), new ProgressListener() { // from class: com.facebook.react.modules.network.NetworkingModule.1.1
                                long last = System.nanoTime();

                                @Override // com.facebook.react.modules.network.ProgressListener
                                public void onProgress(long j, long j2, boolean z3) {
                                    long nanoTime = System.nanoTime();
                                    if ((z3 || NetworkingModule.shouldDispatch(nanoTime, this.last)) && !str3.equals("text")) {
                                        ResponseUtil.onDataReceivedProgress(eventEmitter, r17, j, j2);
                                        this.last = nanoTime;
                                    }
                                }
                            })).build();
                        }
                    });
                }
                if (r22 != this.mClient.connectTimeoutMillis()) {
                    newBuilder.connectTimeout(r22, TimeUnit.MILLISECONDS);
                }
                OkHttpClient build = newBuilder.build();
                Headers extractHeaders = extractHeaders(readableArray, readableMap);
                if (extractHeaders == null) {
                    ResponseUtil.onRequestError(eventEmitter, r17, "Unrecognized headers format", null);
                    return;
                }
                String str4 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
                String str5 = extractHeaders.get(CONTENT_ENCODING_HEADER_NAME);
                url.headers(extractHeaders);
                if (readableMap != null) {
                    Iterator<RequestBodyHandler> it = this.mRequestBodyHandlers.iterator();
                    while (it.hasNext()) {
                        requestBodyHandler = it.next();
                        if (requestBodyHandler.supports(readableMap)) {
                            break;
                        }
                    }
                }
                requestBodyHandler = null;
                if (readableMap == null || str.toLowerCase(Locale.ROOT).equals("get") || str.toLowerCase(Locale.ROOT).equals(TtmlNode.TAG_HEAD)) {
                    emptyBody = RequestBodyUtil.getEmptyBody(str);
                } else if (requestBodyHandler != null) {
                    emptyBody = requestBodyHandler.toRequestBody(readableMap, str4);
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
                    if (str4 == null) {
                        ResponseUtil.onRequestError(eventEmitter, r17, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    String string = readableMap.getString(REQUEST_BODY_KEY_STRING);
                    MediaType parse2 = MediaType.parse(str4);
                    if (RequestBodyUtil.isGzipEncoding(str5)) {
                        emptyBody = RequestBodyUtil.createGzip(parse2, string);
                        if (emptyBody == null) {
                            ResponseUtil.onRequestError(eventEmitter, r17, "Failed to gzip request body", null);
                            return;
                        }
                    } else {
                        if (parse2 == null) {
                            charset = StandardCharsets.UTF_8;
                        } else {
                            charset = parse2.charset(StandardCharsets.UTF_8);
                        }
                        emptyBody = RequestBody.create(parse2, string.getBytes(charset));
                    }
                } else if (readableMap.hasKey("base64")) {
                    if (str4 == null) {
                        ResponseUtil.onRequestError(eventEmitter, r17, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    emptyBody = RequestBody.create(MediaType.parse(str4), ByteString.decodeBase64(readableMap.getString("base64")));
                } else if (readableMap.hasKey("uri")) {
                    if (str4 == null) {
                        ResponseUtil.onRequestError(eventEmitter, r17, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    String string2 = readableMap.getString("uri");
                    InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), string2);
                    if (fileInputStream == null) {
                        ResponseUtil.onRequestError(eventEmitter, r17, "Could not retrieve file for uri " + string2, null);
                        return;
                    }
                    emptyBody = RequestBodyUtil.create(MediaType.parse(str4), fileInputStream);
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_FORMDATA)) {
                    if (str4 == null) {
                        str4 = "multipart/form-data";
                    }
                    MultipartBody.Builder constructMultipartBody = constructMultipartBody(readableMap.getArray(REQUEST_BODY_KEY_FORMDATA), str4, r17);
                    if (constructMultipartBody == null) {
                        return;
                    }
                    emptyBody = constructMultipartBody.build();
                } else {
                    emptyBody = RequestBodyUtil.getEmptyBody(str);
                }
                url.method(str, wrapRequestBodyWithProgressEmitter(emptyBody, eventEmitter, r17));
                addRequest(r17);
                build.newCall(url.build()).enqueue(new Callback() { // from class: com.facebook.react.modules.network.NetworkingModule.2
                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException iOException) {
                        String str6;
                        if (NetworkingModule.this.mShuttingDown) {
                            return;
                        }
                        NetworkingModule.this.removeRequest(r17);
                        if (iOException.getMessage() != null) {
                            str6 = iOException.getMessage();
                        } else {
                            str6 = "Error while executing request: " + iOException.getClass().getSimpleName();
                        }
                        ResponseUtil.onRequestError(eventEmitter, r17, str6, iOException);
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, C5015Response c5015Response) throws IOException {
                        if (NetworkingModule.this.mShuttingDown) {
                            return;
                        }
                        NetworkingModule.this.removeRequest(r17);
                        ResponseUtil.onResponseReceived(eventEmitter, r17, c5015Response.code(), NetworkingModule.translateHeaders(c5015Response.headers()), c5015Response.request().url().toString());
                        try {
                            ResponseBody body = c5015Response.body();
                            if ("gzip".equalsIgnoreCase(c5015Response.header("Content-Encoding")) && body != null) {
                                GzipSource gzipSource = new GzipSource(body.source());
                                String header = c5015Response.header("Content-Type");
                                body = ResponseBody.create(header != null ? MediaType.parse(header) : null, -1L, Okio.buffer(gzipSource));
                            }
                            for (ResponseHandler responseHandler : NetworkingModule.this.mResponseHandlers) {
                                if (responseHandler.supports(str3)) {
                                    ResponseUtil.onDataReceived(eventEmitter, r17, responseHandler.toResponseData(body));
                                    ResponseUtil.onRequestSuccess(eventEmitter, r17);
                                    return;
                                }
                            }
                            if (z && str3.equals("text")) {
                                NetworkingModule.this.readWithProgress(eventEmitter, r17, body);
                                ResponseUtil.onRequestSuccess(eventEmitter, r17);
                                return;
                            }
                            String str6 = "";
                            if (str3.equals("text")) {
                                try {
                                    str6 = body.string();
                                } catch (IOException e) {
                                    if (!c5015Response.request().method().equalsIgnoreCase("HEAD")) {
                                        ResponseUtil.onRequestError(eventEmitter, r17, e.getMessage(), e);
                                    }
                                }
                            } else if (str3.equals("base64")) {
                                str6 = Base64.encodeToString(body.bytes(), 2);
                            }
                            ResponseUtil.onDataReceived(eventEmitter, r17, str6);
                            ResponseUtil.onRequestSuccess(eventEmitter, r17);
                        } catch (IOException e2) {
                            ResponseUtil.onRequestError(eventEmitter, r17, e2.getMessage(), e2);
                        }
                    }
                });
            } catch (Exception e) {
                ResponseUtil.onRequestError(eventEmitter, r17, e.getMessage(), null);
            }
        } catch (IOException e2) {
            ResponseUtil.onRequestError(eventEmitter, r17, e2.getMessage(), e2);
        }
    }

    private RequestBody wrapRequestBodyWithProgressEmitter(RequestBody requestBody, final DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, final int r4) {
        if (requestBody == null) {
            return null;
        }
        return RequestBodyUtil.createProgressRequest(requestBody, new ProgressListener() { // from class: com.facebook.react.modules.network.NetworkingModule.3
            long last = System.nanoTime();

            @Override // com.facebook.react.modules.network.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                long nanoTime = System.nanoTime();
                if (z || NetworkingModule.shouldDispatch(nanoTime, this.last)) {
                    ResponseUtil.onDataSend(rCTDeviceEventEmitter, r4, j, j2);
                    this.last = nanoTime;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readWithProgress(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int r16, ResponseBody responseBody) throws IOException {
        long j;
        Charset charset;
        long j2 = -1;
        try {
            ProgressResponseBody progressResponseBody = (ProgressResponseBody) responseBody;
            j = progressResponseBody.totalBytesRead();
            try {
                j2 = progressResponseBody.contentLength();
            } catch (ClassCastException unused) {
            }
        } catch (ClassCastException unused2) {
            j = -1;
        }
        if (responseBody.contentType() == null) {
            charset = StandardCharsets.UTF_8;
        } else {
            charset = responseBody.contentType().charset(StandardCharsets.UTF_8);
        }
        ProgressiveStringDecoder progressiveStringDecoder = new ProgressiveStringDecoder(charset);
        InputStream byteStream = responseBody.byteStream();
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int read = byteStream.read(bArr);
                if (read == -1) {
                    return;
                }
                ResponseUtil.onIncrementalDataReceived(rCTDeviceEventEmitter, r16, progressiveStringDecoder.decodeNext(bArr, read), j, j2);
            }
        } finally {
            byteStream.close();
        }
    }

    private synchronized void addRequest(int r2) {
        this.mRequestIds.add(Integer.valueOf(r2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void removeRequest(int r2) {
        this.mRequestIds.remove(Integer.valueOf(r2));
    }

    private synchronized void cancelAllRequests() {
        for (Integer num : this.mRequestIds) {
            cancelRequest(num.intValue());
        }
        this.mRequestIds.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WritableMap translateHeaders(Headers headers) {
        Bundle bundle = new Bundle();
        for (int r1 = 0; r1 < headers.size(); r1++) {
            String name = headers.name(r1);
            if (bundle.containsKey(name)) {
                bundle.putString(name, bundle.getString(name) + ", " + headers.value(r1));
            } else {
                bundle.putString(name, headers.value(r1));
            }
        }
        return Arguments.fromBundle(bundle);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void abortRequest(double d) {
        int r1 = (int) d;
        cancelRequest(r1);
        removeRequest(r1);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.facebook.react.modules.network.NetworkingModule$4] */
    private void cancelRequest(final int r3) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.network.NetworkingModule.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                OkHttpCallUtil.cancelTag(NetworkingModule.this.mClient, Integer.valueOf(r3));
            }
        }.execute(new Void[0]);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    @ReactMethod
    public void clearCookies(com.facebook.react.bridge.Callback callback) {
        this.mCookieHandler.clearCookies(callback);
    }

    private MultipartBody.Builder constructMultipartBody(ReadableArray readableArray, String str, int r12) {
        MediaType mediaType;
        DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("constructMultipartBody");
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MediaType.parse(str));
        int size = readableArray.size();
        for (int r2 = 0; r2 < size; r2++) {
            ReadableMap map = readableArray.getMap(r2);
            Headers extractHeaders = extractHeaders(map.getArray("headers"), null);
            if (extractHeaders == null) {
                ResponseUtil.onRequestError(eventEmitter, r12, "Missing or invalid header format for FormData part.", null);
                return null;
            }
            String str2 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
            if (str2 != null) {
                mediaType = MediaType.parse(str2);
                extractHeaders = extractHeaders.newBuilder().removeAll(CONTENT_TYPE_HEADER_NAME).build();
            } else {
                mediaType = null;
            }
            if (map.hasKey(REQUEST_BODY_KEY_STRING)) {
                builder.addPart(extractHeaders, RequestBody.create(mediaType, map.getString(REQUEST_BODY_KEY_STRING)));
            } else if (!map.hasKey("uri")) {
                ResponseUtil.onRequestError(eventEmitter, r12, "Unrecognized FormData part.", null);
            } else if (mediaType == null) {
                ResponseUtil.onRequestError(eventEmitter, r12, "Binary FormData part needs a content-type header.", null);
                return null;
            } else {
                String string = map.getString("uri");
                InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), string);
                if (fileInputStream == null) {
                    ResponseUtil.onRequestError(eventEmitter, r12, "Could not retrieve file for uri " + string, null);
                    return null;
                }
                builder.addPart(extractHeaders, RequestBodyUtil.create(mediaType, fileInputStream));
            }
        }
        return builder;
    }

    private Headers extractHeaders(ReadableArray readableArray, ReadableMap readableMap) {
        String str;
        if (readableArray == null) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        int size = readableArray.size();
        boolean z = false;
        for (int r4 = 0; r4 < size; r4++) {
            ReadableArray array = readableArray.getArray(r4);
            if (array != null && array.size() == 2) {
                String stripHeaderName = HeaderUtil.stripHeaderName(array.getString(0));
                String stripHeaderValue = HeaderUtil.stripHeaderValue(array.getString(1));
                if (stripHeaderName != null && stripHeaderValue != null) {
                    builder.add(stripHeaderName, stripHeaderValue);
                }
            }
            return null;
        }
        if (builder.get(USER_AGENT_HEADER_NAME) == null && (str = this.mDefaultUserAgent) != null) {
            builder.add(USER_AGENT_HEADER_NAME, str);
        }
        if (readableMap != null && readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
            z = true;
        }
        if (!z) {
            builder.removeAll(CONTENT_ENCODING_HEADER_NAME);
        }
        return builder.build();
    }

    private DeviceEventManagerModule.RCTDeviceEventEmitter getEventEmitter(String str) {
        if (getReactApplicationContextIfActiveOrWarn() != null) {
            return (DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        return null;
    }
}
