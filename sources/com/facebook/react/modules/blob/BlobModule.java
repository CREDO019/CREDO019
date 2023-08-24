package com.facebook.react.modules.blob;

import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.facebook.common.util.UriUtil;
import com.facebook.fbreact.specs.NativeBlobModuleSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.ByteString;

@ReactModule(name = BlobModule.NAME)
/* loaded from: classes.dex */
public class BlobModule extends NativeBlobModuleSpec {
    public static final String NAME = "BlobModule";
    private final Map<String, byte[]> mBlobs;
    private final NetworkingModule.RequestBodyHandler mNetworkingRequestBodyHandler;
    private final NetworkingModule.ResponseHandler mNetworkingResponseHandler;
    private final NetworkingModule.UriHandler mNetworkingUriHandler;
    private final WebSocketModule.ContentHandler mWebSocketContentHandler;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public BlobModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mBlobs = new HashMap();
        this.mWebSocketContentHandler = new WebSocketModule.ContentHandler() { // from class: com.facebook.react.modules.blob.BlobModule.1
            @Override // com.facebook.react.modules.websocket.WebSocketModule.ContentHandler
            public void onMessage(String str, WritableMap writableMap) {
                writableMap.putString("data", str);
            }

            @Override // com.facebook.react.modules.websocket.WebSocketModule.ContentHandler
            public void onMessage(ByteString byteString, WritableMap writableMap) {
                byte[] byteArray = byteString.toByteArray();
                WritableMap createMap = Arguments.createMap();
                createMap.putString("blobId", BlobModule.this.store(byteArray));
                createMap.putInt("offset", 0);
                createMap.putInt("size", byteArray.length);
                writableMap.putMap("data", createMap);
                writableMap.putString(SessionDescription.ATTR_TYPE, "blob");
            }
        };
        this.mNetworkingUriHandler = new NetworkingModule.UriHandler() { // from class: com.facebook.react.modules.blob.BlobModule.2
            @Override // com.facebook.react.modules.network.NetworkingModule.UriHandler
            public boolean supports(Uri uri, String str) {
                String scheme = uri.getScheme();
                return !("http".equals(scheme) || "https".equals(scheme)) && "blob".equals(str);
            }

            @Override // com.facebook.react.modules.network.NetworkingModule.UriHandler
            public WritableMap fetch(Uri uri) throws IOException {
                byte[] bytesFromUri = BlobModule.this.getBytesFromUri(uri);
                WritableMap createMap = Arguments.createMap();
                createMap.putString("blobId", BlobModule.this.store(bytesFromUri));
                createMap.putInt("offset", 0);
                createMap.putInt("size", bytesFromUri.length);
                createMap.putString(SessionDescription.ATTR_TYPE, BlobModule.this.getMimeTypeFromUri(uri));
                createMap.putString("name", BlobModule.this.getNameFromUri(uri));
                createMap.putDouble("lastModified", BlobModule.this.getLastModifiedFromUri(uri));
                return createMap;
            }
        };
        this.mNetworkingRequestBodyHandler = new NetworkingModule.RequestBodyHandler() { // from class: com.facebook.react.modules.blob.BlobModule.3
            @Override // com.facebook.react.modules.network.NetworkingModule.RequestBodyHandler
            public boolean supports(ReadableMap readableMap) {
                return readableMap.hasKey("blob");
            }

            @Override // com.facebook.react.modules.network.NetworkingModule.RequestBodyHandler
            public RequestBody toRequestBody(ReadableMap readableMap, String str) {
                if (readableMap.hasKey(SessionDescription.ATTR_TYPE) && !readableMap.getString(SessionDescription.ATTR_TYPE).isEmpty()) {
                    str = readableMap.getString(SessionDescription.ATTR_TYPE);
                }
                if (str == null) {
                    str = "application/octet-stream";
                }
                ReadableMap map = readableMap.getMap("blob");
                return RequestBody.create(MediaType.parse(str), BlobModule.this.resolve(map.getString("blobId"), map.getInt("offset"), map.getInt("size")));
            }
        };
        this.mNetworkingResponseHandler = new NetworkingModule.ResponseHandler() { // from class: com.facebook.react.modules.blob.BlobModule.4
            @Override // com.facebook.react.modules.network.NetworkingModule.ResponseHandler
            public boolean supports(String str) {
                return "blob".equals(str);
            }

            @Override // com.facebook.react.modules.network.NetworkingModule.ResponseHandler
            public WritableMap toResponseData(ResponseBody responseBody) throws IOException {
                byte[] bytes = responseBody.bytes();
                WritableMap createMap = Arguments.createMap();
                createMap.putString("blobId", BlobModule.this.store(bytes));
                createMap.putInt("offset", 0);
                createMap.putInt("size", bytes.length);
                return createMap;
            }
        };
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        BlobCollector.install(getReactApplicationContext(), this);
    }

    @Override // com.facebook.fbreact.specs.NativeBlobModuleSpec
    public Map<String, Object> getTypedExportedConstants() {
        Resources resources = getReactApplicationContext().getResources();
        int identifier = resources.getIdentifier("blob_provider_authority", "string", getReactApplicationContext().getPackageName());
        if (identifier == 0) {
            return MapBuilder.m1262of();
        }
        return MapBuilder.m1260of("BLOB_URI_SCHEME", UriUtil.LOCAL_CONTENT_SCHEME, "BLOB_URI_HOST", resources.getString(identifier));
    }

    public String store(byte[] bArr) {
        String str = UUID.randomUUID().toString();
        store(bArr, str);
        return str;
    }

    public void store(byte[] bArr, String str) {
        synchronized (this.mBlobs) {
            this.mBlobs.put(str, bArr);
        }
    }

    public void remove(String str) {
        synchronized (this.mBlobs) {
            this.mBlobs.remove(str);
        }
    }

    public byte[] resolve(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        String queryParameter = uri.getQueryParameter("offset");
        int parseInt = queryParameter != null ? Integer.parseInt(queryParameter, 10) : 0;
        String queryParameter2 = uri.getQueryParameter("size");
        return resolve(lastPathSegment, parseInt, queryParameter2 != null ? Integer.parseInt(queryParameter2, 10) : -1);
    }

    public byte[] resolve(String str, int r4, int r5) {
        synchronized (this.mBlobs) {
            byte[] bArr = this.mBlobs.get(str);
            if (bArr == null) {
                return null;
            }
            if (r5 == -1) {
                r5 = bArr.length - r4;
            }
            if (r4 > 0 || r5 != bArr.length) {
                bArr = Arrays.copyOfRange(bArr, r4, r5 + r4);
            }
            return bArr;
        }
    }

    public byte[] resolve(ReadableMap readableMap) {
        return resolve(readableMap.getString("blobId"), readableMap.getInt("offset"), readableMap.getInt("size"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getBytesFromUri(Uri uri) throws IOException {
        InputStream openInputStream = getReactApplicationContext().getContentResolver().openInputStream(uri);
        if (openInputStream == null) {
            throw new FileNotFoundException("File not found for " + uri);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = openInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNameFromUri(Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return uri.getLastPathSegment();
        }
        Cursor query = getReactApplicationContext().getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    return query.getString(0);
                }
            } finally {
                query.close();
            }
        }
        return uri.getLastPathSegment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getLastModifiedFromUri(Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return new File(uri.toString()).lastModified();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getMimeTypeFromUri(Uri uri) {
        String fileExtensionFromUrl;
        String type = getReactApplicationContext().getContentResolver().getType(uri);
        if (type == null && (fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.getPath())) != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return type == null ? "" : type;
    }

    private WebSocketModule getWebSocketModule(String str) {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            return (WebSocketModule) reactApplicationContextIfActiveOrWarn.getNativeModule(WebSocketModule.class);
        }
        return null;
    }

    @Override // com.facebook.fbreact.specs.NativeBlobModuleSpec
    public void addNetworkingHandler() {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            NetworkingModule networkingModule = (NetworkingModule) reactApplicationContextIfActiveOrWarn.getNativeModule(NetworkingModule.class);
            networkingModule.addUriHandler(this.mNetworkingUriHandler);
            networkingModule.addRequestBodyHandler(this.mNetworkingRequestBodyHandler);
            networkingModule.addResponseHandler(this.mNetworkingResponseHandler);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeBlobModuleSpec
    public void addWebSocketHandler(double d) {
        int r2 = (int) d;
        WebSocketModule webSocketModule = getWebSocketModule("addWebSocketHandler");
        if (webSocketModule != null) {
            webSocketModule.setContentHandler(r2, this.mWebSocketContentHandler);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeBlobModuleSpec
    public void removeWebSocketHandler(double d) {
        int r2 = (int) d;
        WebSocketModule webSocketModule = getWebSocketModule("removeWebSocketHandler");
        if (webSocketModule != null) {
            webSocketModule.setContentHandler(r2, null);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeBlobModuleSpec
    public void sendOverSocket(ReadableMap readableMap, double d) {
        int r5 = (int) d;
        WebSocketModule webSocketModule = getWebSocketModule("sendOverSocket");
        if (webSocketModule != null) {
            byte[] resolve = resolve(readableMap.getString("blobId"), readableMap.getInt("offset"), readableMap.getInt("size"));
            if (resolve != null) {
                webSocketModule.sendBinary(ByteString.m150of(resolve), r5);
            } else {
                webSocketModule.sendBinary((ByteString) null, r5);
            }
        }
    }

    @Override // com.facebook.fbreact.specs.NativeBlobModuleSpec
    public void createFromParts(ReadableArray readableArray, String str) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        int r2 = 0;
        for (int r1 = 0; r1 < readableArray.size(); r1++) {
            ReadableMap map = readableArray.getMap(r1);
            String string = map.getString(SessionDescription.ATTR_TYPE);
            string.hashCode();
            if (string.equals("string")) {
                byte[] bytes = map.getString("data").getBytes(Charset.forName("UTF-8"));
                r2 += bytes.length;
                arrayList.add(r1, bytes);
            } else if (string.equals("blob")) {
                ReadableMap map2 = map.getMap("data");
                r2 += map2.getInt("size");
                arrayList.add(r1, resolve(map2));
            } else {
                throw new IllegalArgumentException("Invalid type for blob: " + map.getString(SessionDescription.ATTR_TYPE));
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(r2);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            allocate.put((byte[]) it.next());
        }
        store(allocate.array(), str);
    }

    @Override // com.facebook.fbreact.specs.NativeBlobModuleSpec
    public void release(String str) {
        remove(str);
    }
}
