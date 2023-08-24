package expo.modules.updates;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.facebook.common.util.UriUtil;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.base.Ascii;
import com.onesignal.outcomes.data.OutcomeEventsTable;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.p021db.entity.AssetEntity;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p023io.Closeable;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.time.TimeZones;
import org.apache.commons.p028io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: UpdatesUtils.kt */
@Metadata(m184d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\u0012\u001a\u00020\u0006J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\u0006\u0010\u0012\u001a\u00020\u0006J\u0010\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0006J(\u0010\u001f\u001a\u00020 2\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"2\u0006\u0010$\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010&J\u000e\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u0014J\u000e\u0010'\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0006J\u0016\u0010*\u001a\u00020+2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0016J \u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00142\b\u00100\u001a\u0004\u0018\u00010\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u00061"}, m183d2 = {"Lexpo/modules/updates/UpdatesUtils;", "", "()V", "HEX_ARRAY", "", "TAG", "", "kotlin.jvm.PlatformType", "UPDATES_DIRECTORY_NAME", "UPDATES_EVENT_NAME", "bytesToHex", "bytes", "", "createFilenameForAsset", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "getMapFromJSONString", "", "stringifiedJSON", "getOrCreateUpdatesDirectory", "Ljava/io/File;", "context", "Landroid/content/Context;", "getRuntimeVersion", "updatesConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "getStringListFromJSONString", "", "parseDateString", "Ljava/util/Date;", "dateString", "sendEventToReactNative", "", "reactNativeHost", "Ljava/lang/ref/WeakReference;", "Lcom/facebook/react/ReactNativeHost;", "eventName", OutcomeEventsTable.COLUMN_NAME_PARAMS, "Lcom/facebook/react/bridge/WritableMap;", "sha256", "file", "string", "shouldCheckForUpdateOnLaunch", "", "verifySHA256AndWriteToFile", "inputStream", "Ljava/io/InputStream;", "destination", "expectedBase64URLEncodedHash", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class UpdatesUtils {
    private static final char[] HEX_ARRAY;
    public static final UpdatesUtils INSTANCE = new UpdatesUtils();
    private static final String TAG = "UpdatesUtils";
    private static final String UPDATES_DIRECTORY_NAME = ".expo-internal";
    private static final String UPDATES_EVENT_NAME = "Expo.nativeUpdatesEvent";

    /* compiled from: UpdatesUtils.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[UpdatesConfiguration.CheckAutomaticallyConfiguration.values().length];
            r0[UpdatesConfiguration.CheckAutomaticallyConfiguration.NEVER.ordinal()] = 1;
            r0[UpdatesConfiguration.CheckAutomaticallyConfiguration.ERROR_RECOVERY_ONLY.ordinal()] = 2;
            r0[UpdatesConfiguration.CheckAutomaticallyConfiguration.WIFI_ONLY.ordinal()] = 3;
            r0[UpdatesConfiguration.CheckAutomaticallyConfiguration.ALWAYS.ordinal()] = 4;
            $EnumSwitchMapping$0 = r0;
        }
    }

    private UpdatesUtils() {
    }

    static {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        HEX_ARRAY = charArray;
    }

    public final Map<String, String> getMapFromJSONString(String stringifiedJSON) throws Exception {
        Intrinsics.checkNotNullParameter(stringifiedJSON, "stringifiedJSON");
        JSONObject jSONObject = new JSONObject(stringifiedJSON);
        Iterator<String> keys = jSONObject.keys();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (keys.hasNext()) {
            String key = keys.next();
            Intrinsics.checkNotNullExpressionValue(key, "key");
            try {
                Object obj = jSONObject.get(key);
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                linkedHashMap.put(key, (String) obj);
            } catch (ClassCastException unused) {
                throw new Exception("The values in the JSON object must be strings");
            }
        }
        return linkedHashMap;
    }

    public final List<String> getStringListFromJSONString(String stringifiedJSON) throws Exception {
        Intrinsics.checkNotNullParameter(stringifiedJSON, "stringifiedJSON");
        JSONArray jSONArray = new JSONArray(stringifiedJSON);
        int length = jSONArray.length();
        ArrayList arrayList = new ArrayList(length);
        for (int r2 = 0; r2 < length; r2++) {
            arrayList.add(jSONArray.getString(r2));
        }
        return arrayList;
    }

    public final File getOrCreateUpdatesDirectory(Context context) throws Exception {
        Intrinsics.checkNotNullParameter(context, "context");
        File file = new File(context.getFilesDir(), UPDATES_DIRECTORY_NAME);
        if (file.exists()) {
            if (file.isFile()) {
                throw new Exception("File already exists at the location of the Updates Directory: " + file + " ; aborting");
            }
        } else if (!file.mkdir()) {
            throw new Exception("Failed to create Updates Directory: mkdir() returned false");
        }
        return file;
    }

    public final String sha256(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Intrinsics.checkNotNullParameter(string, "string");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            Charset forName = Charset.forName("UTF-8");
            Intrinsics.checkNotNullExpressionValue(forName, "forName(charsetName)");
            byte[] bytes = string.getBytes(forName);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            messageDigest.update(bytes, 0, bytes.length);
            byte[] sha1hash = messageDigest.digest();
            Intrinsics.checkNotNullExpressionValue(sha1hash, "sha1hash");
            return bytesToHex(sha1hash);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Failed to checksum string via SHA-256", e);
            throw e;
        } catch (NoSuchAlgorithmException e2) {
            Log.e(TAG, "Failed to checksum string via SHA-256", e2);
            throw e2;
        }
    }

    public final byte[] sha256(File file) throws NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, MessageDigest.getInstance("SHA-256"));
            byte[] digest = digestInputStream.getMessageDigest().digest();
            Intrinsics.checkNotNullExpressionValue(digest, "md.digest()");
            Closeable.closeFinally(digestInputStream, null);
            Closeable.closeFinally(fileInputStream, null);
            return digest;
        } catch (IOException e) {
            String str = TAG;
            Log.e(str, "Failed to checksum file via SHA-256: " + file, e);
            throw e;
        } catch (NoSuchAlgorithmException e2) {
            String str2 = TAG;
            Log.e(str2, "Failed to checksum file via SHA-256: " + file, e2);
            throw e2;
        }
    }

    public final byte[] verifySHA256AndWriteToFile(InputStream inputStream, File destination, String str) throws NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(destination, "destination");
        DigestInputStream digestInputStream = new DigestInputStream(inputStream, MessageDigest.getInstance("SHA-256"));
        try {
            DigestInputStream digestInputStream2 = digestInputStream;
            String absolutePath = destination.getAbsolutePath();
            File file = new File(absolutePath + DefaultDiskStorage.FileType.TEMP);
            FileUtils.copyInputStreamToFile(digestInputStream2, file);
            byte[] hash = digestInputStream2.getMessageDigest().digest();
            String encodeToString = Base64.encodeToString(hash, 11);
            if (str != null && !Intrinsics.areEqual(str, encodeToString)) {
                throw new IOException("File download was successful but base64url-encoded SHA-256 did not match expected; expected: " + str + "; actual: " + encodeToString);
            }
            if (!file.renameTo(destination)) {
                String absolutePath2 = destination.getAbsolutePath();
                throw new IOException("File download was successful, but failed to move from temporary to permanent location " + absolutePath2);
            }
            Intrinsics.checkNotNullExpressionValue(hash, "hash");
            Closeable.closeFinally(digestInputStream, null);
            return hash;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                Closeable.closeFinally(digestInputStream, th);
                throw th2;
            }
        }
    }

    public final String createFilenameForAsset(AssetEntity asset) {
        String str;
        Intrinsics.checkNotNullParameter(asset, "asset");
        if (asset.getType() != null) {
            String type = asset.getType();
            Intrinsics.checkNotNull(type);
            if (StringsKt.startsWith$default(type, ".", false, 2, (Object) null)) {
                str = asset.getType();
            } else {
                str = "." + asset.getType();
            }
        } else {
            str = "";
        }
        if (asset.getKey() == null) {
            return "asset-" + new Date().getTime() + "-" + new Random().nextInt() + str;
        }
        return asset.getKey() + str;
    }

    public final void sendEventToReactNative(WeakReference<ReactNativeHost> weakReference, final String eventName, final WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        final ReactNativeHost reactNativeHost = weakReference == null ? null : weakReference.get();
        if (reactNativeHost != null) {
            AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.UpdatesUtils$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    UpdatesUtils.m1722sendEventToReactNative$lambda4(ReactNativeHost.this, writableMap, eventName);
                }
            });
            return;
        }
        String str = TAG;
        Log.e(str, "Could not emit " + eventName + " event; UpdatesController was not initialized with an instance of ReactApplication.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendEventToReactNative$lambda-4  reason: not valid java name */
    public static final void m1722sendEventToReactNative$lambda4(ReactNativeHost reactNativeHost, WritableMap writableMap, String eventName) {
        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter;
        Intrinsics.checkNotNullParameter(eventName, "$eventName");
        ReactContext reactContext = null;
        int r1 = 0;
        while (r1 < 5) {
            r1++;
            try {
                if (reactNativeHost.hasInstance() && (reactContext = reactNativeHost.getReactInstanceManager().getCurrentReactContext()) != null) {
                    break;
                }
                Thread.sleep(1000L);
            } catch (Exception unused) {
                String str = TAG;
                Log.e(str, "Could not emit " + eventName + " event; no react context was found.");
                return;
            }
        }
        if (reactContext != null && (rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)) != null) {
            if (writableMap == null) {
                writableMap = Arguments.createMap();
            }
            Intrinsics.checkNotNull(writableMap);
            writableMap.putString(SessionDescription.ATTR_TYPE, eventName);
            rCTDeviceEventEmitter.emit(UPDATES_EVENT_NAME, writableMap);
            return;
        }
        String str2 = TAG;
        Log.e(str2, "Could not emit " + eventName + " event; no event emitter was found.");
    }

    public final boolean shouldCheckForUpdateOnLaunch(UpdatesConfiguration updatesConfiguration, Context context) {
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        Intrinsics.checkNotNullParameter(context, "context");
        if (updatesConfiguration.getUpdateUrl() == null) {
            return false;
        }
        int r4 = WhenMappings.$EnumSwitchMapping$0[updatesConfiguration.getCheckOnLaunch().ordinal()];
        if (r4 == 1 || r4 == 2) {
            return false;
        }
        if (r4 == 3) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                Log.e(TAG, "Could not determine active network connection is metered; not checking for updates");
                return false;
            } else if (connectivityManager.isActiveNetworkMetered()) {
                return false;
            }
        } else if (r4 != 4) {
            throw new NoWhenBranchMatchedException();
        }
        return true;
    }

    public final String getRuntimeVersion(UpdatesConfiguration updatesConfiguration) {
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        String runtimeVersion = updatesConfiguration.getRuntimeVersion();
        String sdkVersion = updatesConfiguration.getSdkVersion();
        if (runtimeVersion != null) {
            if (runtimeVersion.length() > 0) {
                return runtimeVersion;
            }
        }
        if (sdkVersion != null) {
            if (sdkVersion.length() > 0) {
                return sdkVersion;
            }
        }
        return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
    }

    public final String bytesToHex(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        char[] cArr = new char[bytes.length * 2];
        int length = bytes.length;
        int r2 = 0;
        while (r2 < length) {
            int r3 = r2 + 1;
            byte b = (byte) (bytes[r2] & (-1));
            int r22 = r2 * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[r22] = cArr2[b >>> 4];
            cArr[r22 + 1] = cArr2[b & Ascii.f1128SI];
            r2 = r3;
        }
        return new String(cArr);
    }

    public final Date parseDateString(String str) throws ParseException {
        try {
            Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US).parse(str);
            Intrinsics.checkNotNullExpressionValue(parse, "{\n      val formatter: D…r.parse(dateString)\n    }");
            return parse;
        } catch (IllegalArgumentException e) {
            String str2 = TAG;
            Log.e(str2, "Failed to parse date string on first try: " + str, e);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
            Date parse2 = simpleDateFormat.parse(str);
            Intrinsics.checkNotNullExpressionValue(parse2, "{\n      Log.e(TAG, \"Fail…r.parse(dateString)\n    }");
            return parse2;
        } catch (ParseException e2) {
            String str3 = TAG;
            Log.e(str3, "Failed to parse date string on first try: " + str, e2);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
            Date parse3 = simpleDateFormat2.parse(str);
            Intrinsics.checkNotNullExpressionValue(parse3, "{\n      Log.e(TAG, \"Fail…r.parse(dateString)\n    }");
            return parse3;
        }
    }
}
