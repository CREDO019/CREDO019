package com.facebook.react.modules.websocket;

import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeWebSocketModuleSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.net.HttpHeaders;
import com.onesignal.OneSignalDbContract;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.C5015Response;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

@ReactModule(hasConstants = false, name = "WebSocketModule")
/* loaded from: classes.dex */
public final class WebSocketModule extends NativeWebSocketModuleSpec {
    public static final String NAME = "WebSocketModule";
    public static final String TAG = "WebSocketModule";
    private final Map<Integer, ContentHandler> mContentHandlers;
    private ForwardingCookieHandler mCookieHandler;
    private final Map<Integer, WebSocket> mWebSocketConnections;

    /* loaded from: classes.dex */
    public interface ContentHandler {
        void onMessage(String str, WritableMap writableMap);

        void onMessage(ByteString byteString, WritableMap writableMap);
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "WebSocketModule";
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void removeListeners(double d) {
    }

    public WebSocketModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mWebSocketConnections = new ConcurrentHashMap();
        this.mContentHandlers = new ConcurrentHashMap();
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContextIfActiveOrWarn.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    public void setContentHandler(int r2, ContentHandler contentHandler) {
        if (contentHandler != null) {
            this.mContentHandlers.put(Integer.valueOf(r2), contentHandler);
        } else {
            this.mContentHandlers.remove(Integer.valueOf(r2));
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void connect(String str, ReadableArray readableArray, ReadableMap readableMap, double d) {
        boolean z;
        final int r13 = (int) d;
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS).readTimeout(0L, TimeUnit.MINUTES).build();
        Request.Builder url = new Request.Builder().tag(Integer.valueOf(r13)).url(str);
        String cookie = getCookie(str);
        if (cookie != null) {
            url.addHeader(HttpHeaders.COOKIE, cookie);
        }
        if (readableMap != null && readableMap.hasKey("headers") && readableMap.getType("headers").equals(ReadableType.Map)) {
            ReadableMap map = readableMap.getMap("headers");
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            z = false;
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                if (ReadableType.String.equals(map.getType(nextKey))) {
                    if (nextKey.equalsIgnoreCase("origin")) {
                        z = true;
                    }
                    url.addHeader(nextKey, map.getString(nextKey));
                } else {
                    FLog.m1288w(ReactConstants.TAG, "Ignoring: requested " + nextKey + ", value not a string");
                }
            }
        } else {
            z = false;
        }
        if (!z) {
            url.addHeader("origin", getDefaultOrigin(str));
        }
        if (readableArray != null && readableArray.size() > 0) {
            StringBuilder sb = new StringBuilder("");
            for (int r2 = 0; r2 < readableArray.size(); r2++) {
                String trim = readableArray.getString(r2).trim();
                if (!trim.isEmpty() && !trim.contains(",")) {
                    sb.append(trim);
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.replace(sb.length() - 1, sb.length(), "");
                url.addHeader(HttpHeaders.SEC_WEBSOCKET_PROTOCOL, sb.toString());
            }
        }
        build.newWebSocket(url.build(), new WebSocketListener() { // from class: com.facebook.react.modules.websocket.WebSocketModule.1
            @Override // okhttp3.WebSocketListener
            public void onOpen(WebSocket webSocket, C5015Response c5015Response) {
                WebSocketModule.this.mWebSocketConnections.put(Integer.valueOf(r13), webSocket);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", r13);
                createMap.putString("protocol", c5015Response.header(HttpHeaders.SEC_WEBSOCKET_PROTOCOL, ""));
                WebSocketModule.this.sendEvent("websocketOpen", createMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onClosing(WebSocket webSocket, int r22, String str2) {
                webSocket.close(r22, str2);
            }

            @Override // okhttp3.WebSocketListener
            public void onClosed(WebSocket webSocket, int r4, String str2) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", r13);
                createMap.putInt("code", r4);
                createMap.putString("reason", str2);
                WebSocketModule.this.sendEvent("websocketClosed", createMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onFailure(WebSocket webSocket, Throwable th, C5015Response c5015Response) {
                WebSocketModule.this.notifyWebSocketFailed(r13, th.getMessage());
            }

            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, String str2) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", r13);
                createMap.putString(SessionDescription.ATTR_TYPE, "text");
                ContentHandler contentHandler = (ContentHandler) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(r13));
                if (contentHandler != null) {
                    contentHandler.onMessage(str2, createMap);
                } else {
                    createMap.putString("data", str2);
                }
                WebSocketModule.this.sendEvent("websocketMessage", createMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, ByteString byteString) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", r13);
                createMap.putString(SessionDescription.ATTR_TYPE, "binary");
                ContentHandler contentHandler = (ContentHandler) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(r13));
                if (contentHandler != null) {
                    contentHandler.onMessage(byteString, createMap);
                } else {
                    createMap.putString("data", byteString.base64());
                }
                WebSocketModule.this.sendEvent("websocketMessage", createMap);
            }
        });
        build.dispatcher().executorService().shutdown();
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void close(double d, String str, double d2) {
        int r5 = (int) d2;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(r5));
        if (webSocket == null) {
            return;
        }
        try {
            webSocket.close((int) d, str);
            this.mWebSocketConnections.remove(Integer.valueOf(r5));
            this.mContentHandlers.remove(Integer.valueOf(r5));
        } catch (Exception e) {
            FLog.m1327e(ReactConstants.TAG, "Could not close WebSocket connection for id " + r5, e);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void send(String str, double d) {
        int r4 = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(r4));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", r4);
            createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", r4);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(r4));
            this.mContentHandlers.remove(Integer.valueOf(r4));
            return;
        }
        try {
            webSocket.send(str);
        } catch (Exception e) {
            notifyWebSocketFailed(r4, e.getMessage());
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void sendBinary(String str, double d) {
        int r4 = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(r4));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", r4);
            createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", r4);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(r4));
            this.mContentHandlers.remove(Integer.valueOf(r4));
            return;
        }
        try {
            webSocket.send(ByteString.decodeBase64(str));
        } catch (Exception e) {
            notifyWebSocketFailed(r4, e.getMessage());
        }
    }

    public void sendBinary(ByteString byteString, int r5) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(r5));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", r5);
            createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", r5);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(r5));
            this.mContentHandlers.remove(Integer.valueOf(r5));
            return;
        }
        try {
            webSocket.send(byteString);
        } catch (Exception e) {
            notifyWebSocketFailed(r5, e.getMessage());
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void ping(double d) {
        int r4 = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(r4));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", r4);
            createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", r4);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(r4));
            this.mContentHandlers.remove(Integer.valueOf(r4));
            return;
        }
        try {
            webSocket.send(ByteString.EMPTY);
        } catch (Exception e) {
            notifyWebSocketFailed(r4, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWebSocketFailed(int r3, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", r3);
        createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, str);
        sendEvent("websocketFailed", createMap);
    }

    private static String getDefaultOrigin(String str) {
        char c;
        try {
            String str2 = "";
            URI r1 = new URI(str);
            String scheme = r1.getScheme();
            int hashCode = scheme.hashCode();
            if (hashCode == 3804) {
                if (scheme.equals("ws")) {
                    c = 1;
                }
                c = 65535;
            } else if (hashCode == 118039) {
                if (scheme.equals("wss")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 3213448) {
                if (hashCode == 99617003 && scheme.equals("https")) {
                    c = 3;
                }
                c = 65535;
            } else {
                if (scheme.equals("http")) {
                    c = 2;
                }
                c = 65535;
            }
            if (c == 0) {
                str2 = "https";
            } else if (c == 1) {
                str2 = "http";
            } else if (c == 2 || c == 3) {
                str2 = "" + r1.getScheme();
            }
            return r1.getPort() != -1 ? String.format("%s://%s:%s", str2, r1.getHost(), Integer.valueOf(r1.getPort())) : String.format("%s://%s", str2, r1.getHost());
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to set " + str + " as default origin header");
        }
    }

    private String getCookie(String str) {
        try {
            List<String> list = this.mCookieHandler.get(new URI(getDefaultOrigin(str)), new HashMap()).get(HttpHeaders.COOKIE);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
            return null;
        } catch (IOException | URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to get cookie from " + str);
        }
    }
}
