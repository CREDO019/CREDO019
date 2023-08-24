package expo.modules.updates.loader;

import android.content.Context;
import android.net.Uri;
import com.amplitude.api.DeviceInfo;
import com.facebook.common.util.UriUtil;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.onesignal.OneSignalDbContract;
import expo.modules.easclient.EASClientID;
import expo.modules.manifests.core.Manifest;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.codesigning.CodeSigningConfiguration;
import expo.modules.updates.codesigning.ExpoProjectInformation;
import expo.modules.updates.codesigning.SignatureValidationResult;
import expo.modules.updates.codesigning.ValidationResult;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.manifest.ManifestFactory;
import expo.modules.updates.manifest.ManifestHeaderData;
import expo.modules.updates.manifest.ManifestMetadata;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.UpdatesDatabase;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.selectionpolicy.SelectionPolicies;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.p023io.Closeable;
import kotlin.reflect.KClass;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.C5015Response;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.ParameterParser;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FileDownloader.kt */
@Metadata(m184d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 32\u00020\u0001:\u00042345B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J0\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0017J \u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J*\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u001eH\u0002J(\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\"J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001cH\u0002J<\u0010&\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010!2\b\u0010+\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\"H\u0002J%\u0010,\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\"H\u0000¢\u0006\u0002\b/J(\u00100\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020.2\u0006\u00101\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\"H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, m183d2 = {"Lexpo/modules/updates/loader/FileDownloader;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "client", "Lokhttp3/OkHttpClient;", "(Landroid/content/Context;Lokhttp3/OkHttpClient;)V", "logger", "Lexpo/modules/updates/logging/UpdatesLogger;", "downloadAsset", "", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "destinationDirectory", "Ljava/io/File;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "callback", "Lexpo/modules/updates/loader/FileDownloader$AssetDownloadCallback;", "downloadData", "request", "Lokhttp3/Request;", "Lokhttp3/Callback;", "isRetry", "", "downloadFileAndVerifyHashAndWriteToPath", "expectedBase64URLEncodedSHA256Hash", "", "destination", "Lexpo/modules/updates/loader/FileDownloader$FileDownloadCallback;", "downloadManifest", "extraHeaders", "Lorg/json/JSONObject;", "Lexpo/modules/updates/loader/FileDownloader$ManifestDownloadCallback;", "parseHeaders", "Lokhttp3/Headers;", "text", "parseManifest", "manifestBody", "manifestHeaderData", "Lexpo/modules/updates/manifest/ManifestHeaderData;", "extensions", "certificateChainFromManifestResponse", "parseManifestResponse", "response", "Lokhttp3/Response;", "parseManifestResponse$expo_updates_release", "parseMultipartManifestResponse", "boundary", "AssetDownloadCallback", "Companion", "FileDownloadCallback", "ManifestDownloadCallback", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public class FileDownloader {
    private static final String CRLF = "\r\n";
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "FileDownloader";
    private final OkHttpClient client;
    private final UpdatesLogger logger;

    /* compiled from: FileDownloader.kt */
    @Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, m183d2 = {"Lexpo/modules/updates/loader/FileDownloader$AssetDownloadCallback;", "", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "assetEntity", "Lexpo/modules/updates/db/entity/AssetEntity;", "onSuccess", "isNew", "", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public interface AssetDownloadCallback {
        void onFailure(Exception exc, AssetEntity assetEntity);

        void onSuccess(AssetEntity assetEntity, boolean z);
    }

    /* compiled from: FileDownloader.kt */
    @Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, m183d2 = {"Lexpo/modules/updates/loader/FileDownloader$FileDownloadCallback;", "", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "file", "Ljava/io/File;", "hash", "", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public interface FileDownloadCallback {
        void onFailure(Exception exc);

        void onSuccess(File file, byte[] bArr);
    }

    /* compiled from: FileDownloader.kt */
    @Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, m183d2 = {"Lexpo/modules/updates/loader/FileDownloader$ManifestDownloadCallback;", "", "onFailure", "", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "updateManifest", "Lexpo/modules/updates/manifest/UpdateManifest;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public interface ManifestDownloadCallback {
        void onFailure(String str, Exception exc);

        void onSuccess(UpdateManifest updateManifest);
    }

    public FileDownloader(Context context, OkHttpClient client) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(client, "client");
        this.client = client;
        this.logger = new UpdatesLogger(context);
    }

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public FileDownloader(Context context) {
        this(context, new OkHttpClient.Builder().cache(Companion.getCache(context)).build());
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void downloadFileAndVerifyHashAndWriteToPath(Request request, final String str, final File file, final FileDownloadCallback fileDownloadCallback) {
        downloadData(request, new Callback() { // from class: expo.modules.updates.loader.FileDownloader$downloadFileAndVerifyHashAndWriteToPath$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                FileDownloader.FileDownloadCallback.this.onFailure(e);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, C5015Response response) throws IOException {
                UpdatesLogger updatesLogger;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    FileDownloader.FileDownloadCallback fileDownloadCallback2 = FileDownloader.FileDownloadCallback.this;
                    ResponseBody body = response.body();
                    Intrinsics.checkNotNull(body);
                    String string = body.string();
                    fileDownloadCallback2.onFailure(new Exception("Network request failed: " + string));
                    return;
                }
                try {
                    ResponseBody body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    InputStream byteStream = body2.byteStream();
                    File file2 = file;
                    String str2 = str;
                    FileDownloader.FileDownloadCallback fileDownloadCallback3 = FileDownloader.FileDownloadCallback.this;
                    fileDownloadCallback3.onSuccess(file2, UpdatesUtils.INSTANCE.verifySHA256AndWriteToFile(byteStream, file2, str2));
                    Unit unit = Unit.INSTANCE;
                    Closeable.closeFinally(byteStream, null);
                } catch (Exception e) {
                    updatesLogger = this.logger;
                    File file3 = file;
                    String localizedMessage = e.getLocalizedMessage();
                    updatesLogger.error("Failed to download file to destination " + file3 + ": " + localizedMessage, UpdatesErrorCode.AssetsFailedToLoad, e);
                    FileDownloader.FileDownloadCallback.this.onFailure(e);
                }
            }
        });
    }

    public final void parseManifestResponse$expo_updates_release(C5015Response response, UpdatesConfiguration configuration, ManifestDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(callback, "callback");
        String header$default = C5015Response.header$default(response, "content-type", null, 2, null);
        if (header$default == null) {
            header$default = "";
        }
        if (StringsKt.startsWith(header$default, FileUploadBase.MULTIPART, true)) {
            String str = new ParameterParser().parse(header$default, ';').get("boundary");
            if (str == null) {
                UpdatesLogger.error$default(this.logger, "Missing boundary in multipart manifest content-type", UpdatesErrorCode.UpdateFailedToLoad, null, 4, null);
                callback.onFailure("Missing boundary in multipart manifest content-type", new IOException("Missing boundary in multipart manifest content-type"));
                return;
            }
            parseMultipartManifestResponse(response, str, configuration, callback);
            return;
        }
        Headers headers = response.headers();
        ManifestHeaderData manifestHeaderData = new ManifestHeaderData(headers.get("expo-protocol-version"), headers.get("expo-server-defined-headers"), headers.get("expo-manifest-filters"), headers.get("expo-manifest-signature"), headers.get("expo-signature"));
        ResponseBody body = response.body();
        Intrinsics.checkNotNull(body);
        parseManifest(body.string(), manifestHeaderData, null, null, configuration, callback);
    }

    private final Headers parseHeaders(String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str2 : StringsKt.split$default((CharSequence) str, new String[]{"\r\n"}, false, 0, 6, (Object) null)) {
            int indexOf$default = StringsKt.indexOf$default((CharSequence) str2, ParameterizedMessage.ERROR_MSG_SEPARATOR, 0, false, 6, (Object) null);
            if (indexOf$default != -1) {
                String substring = str2.substring(0, indexOf$default);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                String obj = StringsKt.trim((CharSequence) substring).toString();
                String substring2 = str2.substring(indexOf$default + 1);
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                linkedHashMap.put(obj, StringsKt.trim((CharSequence) substring2).toString());
            }
        }
        return Headers.Companion.m153of(linkedHashMap);
    }

    private final void parseMultipartManifestResponse(C5015Response c5015Response, String str, UpdatesConfiguration updatesConfiguration, ManifestDownloadCallback manifestDownloadCallback) {
        JSONObject jSONObject;
        String str2;
        ResponseBody body = c5015Response.body();
        Intrinsics.checkNotNull(body);
        InputStream byteStream = body.byteStream();
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        MultipartStream multipartStream = new MultipartStream(byteStream, bytes);
        try {
            Tuples tuples = null;
            String str3 = null;
            String str4 = null;
            for (boolean skipPreamble = multipartStream.skipPreamble(); skipPreamble; skipPreamble = multipartStream.readBoundary()) {
                String readHeaders = multipartStream.readHeaders();
                Intrinsics.checkNotNullExpressionValue(readHeaders, "multipartStream.readHeaders()");
                Headers parseHeaders = parseHeaders(readHeaders);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                multipartStream.readBodyData(byteArrayOutputStream);
                String str5 = parseHeaders.get("content-disposition");
                if (str5 != null && (str2 = new ParameterParser().parse(str5, ';').get("name")) != null) {
                    int hashCode = str2.hashCode();
                    if (hashCode != -1809421292) {
                        if (hashCode != -1044926951) {
                            if (hashCode == 130625071 && str2.equals("manifest")) {
                                String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                                Intrinsics.checkNotNullExpressionValue(byteArrayOutputStream2, "output.toString()");
                                tuples = new Tuples(byteArrayOutputStream2, parseHeaders);
                            }
                        } else if (str2.equals("certificate_chain")) {
                            str4 = byteArrayOutputStream.toString();
                        }
                    } else if (str2.equals("extensions")) {
                        str3 = byteArrayOutputStream.toString();
                    }
                }
            }
            if (tuples == null) {
                UpdatesLogger.error$default(this.logger, "Multipart manifest response missing manifest part", UpdatesErrorCode.UpdateFailedToLoad, null, 4, null);
                manifestDownloadCallback.onFailure("Multipart manifest response missing manifest part", new IOException("Malformed multipart manifest response"));
                return;
            }
            if (str3 == null) {
                jSONObject = null;
            } else {
                try {
                    jSONObject = new JSONObject(str3);
                } catch (Exception e) {
                    UpdatesLogger.error$default(this.logger, "Failed to parse multipart manifest extensions", UpdatesErrorCode.UpdateFailedToLoad, null, 4, null);
                    manifestDownloadCallback.onFailure("Failed to parse multipart manifest extensions", e);
                    return;
                }
            }
            Headers headers = c5015Response.headers();
            parseManifest((String) tuples.getFirst(), new ManifestHeaderData(headers.get("expo-protocol-version"), headers.get("expo-server-defined-headers"), headers.get("expo-manifest-filters"), headers.get("expo-manifest-signature"), ((Headers) tuples.getSecond()).get("expo-signature")), jSONObject, str4, updatesConfiguration, manifestDownloadCallback);
        } catch (Exception e2) {
            this.logger.error("Error while reading multipart manifest response", UpdatesErrorCode.UpdateFailedToLoad, e2);
            manifestDownloadCallback.onFailure("Error while reading multipart manifest response", e2);
        }
    }

    private final void parseManifest(final String str, final ManifestHeaderData manifestHeaderData, final JSONObject jSONObject, final String str2, final UpdatesConfiguration updatesConfiguration, final ManifestDownloadCallback manifestDownloadCallback) {
        String manifestSignature;
        try {
            Companion companion = Companion;
            JSONObject extractUpdateResponseJson = companion.extractUpdateResponseJson(str, updatesConfiguration);
            boolean z = extractUpdateResponseJson.has("manifestString") && extractUpdateResponseJson.has("signature");
            if (!z) {
                manifestSignature = manifestHeaderData.getManifestSignature();
            } else if (extractUpdateResponseJson.has("signature")) {
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                    manifestSignature = extractUpdateResponseJson.getString("signature");
                    if (manifestSignature == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    manifestSignature = (String) Double.valueOf(extractUpdateResponseJson.getDouble("signature"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    manifestSignature = (String) Integer.valueOf(extractUpdateResponseJson.getInt("signature"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    manifestSignature = (String) Long.valueOf(extractUpdateResponseJson.getLong("signature"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    manifestSignature = (String) Boolean.valueOf(extractUpdateResponseJson.getBoolean("signature"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = extractUpdateResponseJson.getJSONArray("signature");
                    if (jSONArray == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    manifestSignature = (String) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject2 = extractUpdateResponseJson.getJSONObject("signature");
                    if (jSONObject2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    manifestSignature = (String) jSONObject2;
                } else {
                    Object obj = extractUpdateResponseJson.get("signature");
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    manifestSignature = (String) obj;
                }
            } else {
                manifestSignature = null;
            }
            String str3 = manifestSignature;
            String manifestString = z ? extractUpdateResponseJson.getString("manifestString") : str;
            final JSONObject jSONObject3 = new JSONObject(manifestString);
            boolean areEqual = Intrinsics.areEqual("UNSIGNED", str3);
            if (str3 != null && !areEqual) {
                Intrinsics.checkNotNullExpressionValue(manifestString, "manifestString");
                LegacySignatureUtils.verifyExpoPublicRSASignature(this, manifestString, str3, new RSASignatureListener() { // from class: expo.modules.updates.loader.FileDownloader$parseManifest$1
                    @Override // expo.modules.updates.loader.RSASignatureListener
                    public void onError(Exception exception, boolean z2) {
                        Intrinsics.checkNotNullParameter(exception, "exception");
                        FileDownloader.ManifestDownloadCallback.this.onFailure("Could not validate signed manifest", exception);
                    }

                    @Override // expo.modules.updates.loader.RSASignatureListener
                    public void onCompleted(boolean z2) {
                        UpdatesLogger updatesLogger;
                        UpdatesLogger updatesLogger2;
                        if (!z2) {
                            updatesLogger2 = this.logger;
                            UpdatesLogger.error$default(updatesLogger2, "Manifest signature is invalid; aborting", UpdatesErrorCode.UpdateHasInvalidSignature, null, 4, null);
                            FileDownloader.ManifestDownloadCallback.this.onFailure("Manifest signature is invalid; aborting", new Exception("Manifest signature is invalid"));
                            return;
                        }
                        try {
                            FileDownloader.Companion companion2 = FileDownloader.Companion;
                            String str4 = str;
                            JSONObject jSONObject4 = jSONObject3;
                            ManifestHeaderData manifestHeaderData2 = manifestHeaderData;
                            JSONObject jSONObject5 = jSONObject;
                            String str5 = str2;
                            UpdatesConfiguration updatesConfiguration2 = updatesConfiguration;
                            updatesLogger = this.logger;
                            companion2.checkCodeSigningAndCreateManifest(str4, jSONObject4, manifestHeaderData2, jSONObject5, str5, true, updatesConfiguration2, updatesLogger, FileDownloader.ManifestDownloadCallback.this);
                        } catch (Exception e) {
                            FileDownloader.ManifestDownloadCallback.this.onFailure("Failed to parse manifest data", e);
                        }
                    }
                });
                return;
            }
            companion.checkCodeSigningAndCreateManifest(str, jSONObject3, manifestHeaderData, jSONObject, str2, false, updatesConfiguration, this.logger, manifestDownloadCallback);
        } catch (Exception e) {
            String str4 = "Failed to parse manifest data: " + e.getLocalizedMessage();
            this.logger.error(str4, UpdatesErrorCode.UpdateFailedToLoad, e);
            manifestDownloadCallback.onFailure(str4, e);
        }
    }

    public final void downloadManifest(final UpdatesConfiguration configuration, JSONObject jSONObject, Context context, final ManifestDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            downloadData(Companion.createRequestForManifest$expo_updates_release(configuration, jSONObject, context), new Callback() { // from class: expo.modules.updates.loader.FileDownloader$downloadManifest$1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e) {
                    UpdatesLogger updatesLogger;
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(e, "e");
                    String str = "Failed to download manifest from URL: " + UpdatesConfiguration.this.getUpdateUrl() + ": " + e.getLocalizedMessage();
                    updatesLogger = this.logger;
                    IOException iOException = e;
                    updatesLogger.error(str, UpdatesErrorCode.UpdateFailedToLoad, iOException);
                    callback.onFailure(str, iOException);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, C5015Response response) throws IOException {
                    UpdatesLogger updatesLogger;
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (!response.isSuccessful()) {
                        String str = "Failed to download manifest from URL: " + UpdatesConfiguration.this.getUpdateUrl();
                        updatesLogger = this.logger;
                        UpdatesLogger.error$default(updatesLogger, str, UpdatesErrorCode.UpdateFailedToLoad, null, 4, null);
                        FileDownloader.ManifestDownloadCallback manifestDownloadCallback = callback;
                        ResponseBody body = response.body();
                        Intrinsics.checkNotNull(body);
                        manifestDownloadCallback.onFailure(str, new Exception(body.string()));
                        return;
                    }
                    this.parseManifestResponse$expo_updates_release(response, UpdatesConfiguration.this, callback);
                }
            });
        } catch (Exception e) {
            String str = "Failed to download manifest from URL: " + configuration.getUpdateUrl() + ": " + e.getLocalizedMessage();
            this.logger.error(str, UpdatesErrorCode.UpdateFailedToLoad, e);
            callback.onFailure(str, e);
        }
    }

    public final void downloadAsset(final AssetEntity asset, File file, UpdatesConfiguration configuration, Context context, final AssetDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (asset.getUrl() == null) {
            String str = "Could not download asset " + asset.getKey() + " with no URL";
            UpdatesLogger.error$default(this.logger, str, UpdatesErrorCode.AssetsFailedToLoad, null, 4, null);
            callback.onFailure(new Exception(str), asset);
            return;
        }
        final String createFilenameForAsset = UpdatesUtils.INSTANCE.createFilenameForAsset(asset);
        File file2 = new File(file, createFilenameForAsset);
        if (file2.exists()) {
            asset.setRelativePath(createFilenameForAsset);
            callback.onSuccess(asset, false);
            return;
        }
        try {
            downloadFileAndVerifyHashAndWriteToPath(Companion.createRequestForAsset$expo_updates_release(asset, configuration, context), asset.getExpectedHash(), file2, new FileDownloadCallback() { // from class: expo.modules.updates.loader.FileDownloader$downloadAsset$1
                @Override // expo.modules.updates.loader.FileDownloader.FileDownloadCallback
                public void onFailure(Exception e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    FileDownloader.AssetDownloadCallback.this.onFailure(e, asset);
                }

                @Override // expo.modules.updates.loader.FileDownloader.FileDownloadCallback
                public void onSuccess(File file3, byte[] hash) {
                    Intrinsics.checkNotNullParameter(file3, "file");
                    Intrinsics.checkNotNullParameter(hash, "hash");
                    asset.setDownloadTime(new Date());
                    asset.setRelativePath(createFilenameForAsset);
                    asset.setHash(hash);
                    FileDownloader.AssetDownloadCallback.this.onSuccess(asset, true);
                }
            });
        } catch (Exception e) {
            this.logger.error("Failed to download asset " + asset.getKey() + ": " + e.getLocalizedMessage(), UpdatesErrorCode.AssetsFailedToLoad, e);
            callback.onFailure(e, asset);
        }
    }

    public final void downloadData(Request request, Callback callback) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(callback, "callback");
        downloadData(request, callback, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downloadData(final Request request, final Callback callback, final boolean z) {
        this.client.newCall(request).enqueue(new Callback() { // from class: expo.modules.updates.loader.FileDownloader$downloadData$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                if (!z) {
                    this.downloadData(request, callback, true);
                } else {
                    callback.onFailure(call, e);
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, C5015Response response) throws IOException {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                callback.onResponse(call, response);
            }
        });
    }

    /* compiled from: FileDownloader.kt */
    @Metadata(m184d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001eJ'\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010 \u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b!J\u0018\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J*\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010,J\u0016\u0010.\u001a\u00020/*\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, m183d2 = {"Lexpo/modules/updates/loader/FileDownloader$Companion;", "", "()V", "CRLF", "", "TAG", "kotlin.jvm.PlatformType", "checkCodeSigningAndCreateManifest", "", "bodyString", "preManifest", "Lorg/json/JSONObject;", "manifestHeaderData", "Lexpo/modules/updates/manifest/ManifestHeaderData;", "extensions", "certificateChainFromManifestResponse", "isVerified", "", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "logger", "Lexpo/modules/updates/logging/UpdatesLogger;", "callback", "Lexpo/modules/updates/loader/FileDownloader$ManifestDownloadCallback;", "createRequestForAsset", "Lokhttp3/Request;", "assetEntity", "Lexpo/modules/updates/db/entity/AssetEntity;", "context", "Landroid/content/Context;", "createRequestForAsset$expo_updates_release", "createRequestForManifest", "extraHeaders", "createRequestForManifest$expo_updates_release", "extractUpdateResponseJson", "manifestString", "getCache", "Lokhttp3/Cache;", "getCacheDirectory", "Ljava/io/File;", "getExtraHeaders", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "embeddedUpdate", "addHeadersFromJSONObject", "Lokhttp3/Request$Builder;", "headers", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void checkCodeSigningAndCreateManifest(String str, JSONObject jSONObject, ManifestHeaderData manifestHeaderData, JSONObject jSONObject2, String str2, boolean z, UpdatesConfiguration updatesConfiguration, UpdatesLogger updatesLogger, ManifestDownloadCallback manifestDownloadCallback) throws Exception {
            if (updatesConfiguration.getExpectsSignedManifest()) {
                jSONObject.put("isVerified", z);
            }
            try {
                CodeSigningConfiguration codeSigningConfiguration = updatesConfiguration.getCodeSigningConfiguration();
                if (codeSigningConfiguration != null) {
                    String signature = manifestHeaderData.getSignature();
                    byte[] bytes = str.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    SignatureValidationResult validateSignature = codeSigningConfiguration.validateSignature(signature, bytes, str2);
                    if (validateSignature.getValidationResult() == ValidationResult.INVALID) {
                        throw new IOException("Manifest download was successful, but signature was incorrect");
                    }
                    if (validateSignature.getValidationResult() != ValidationResult.SKIPPED) {
                        Manifest manifest = ManifestFactory.INSTANCE.getManifest(jSONObject, manifestHeaderData, jSONObject2, updatesConfiguration).getManifest();
                        ExpoProjectInformation expoProjectInformation = validateSignature.getExpoProjectInformation();
                        if (expoProjectInformation != null && (!Intrinsics.areEqual(expoProjectInformation.getProjectId(), manifest.getEASProjectID()) || !Intrinsics.areEqual(expoProjectInformation.getScopeKey(), manifest.getScopeKey()))) {
                            throw new CertificateException("Invalid certificate for manifest project ID or scope key");
                        }
                        UpdatesLogger.info$default(updatesLogger, "Update code signature verified successfully", null, 2, null);
                        jSONObject.put("isVerified", true);
                    }
                }
                UpdateManifest manifest2 = ManifestFactory.INSTANCE.getManifest(jSONObject, manifestHeaderData, jSONObject2, updatesConfiguration);
                SelectionPolicies selectionPolicies = SelectionPolicies.INSTANCE;
                UpdateEntity updateEntity = manifest2.getUpdateEntity();
                Intrinsics.checkNotNull(updateEntity);
                if (!selectionPolicies.matchesFilters(updateEntity, manifest2.getManifestFilters())) {
                    manifestDownloadCallback.onFailure("Downloaded manifest is invalid; provides filters that do not match its content", new Exception("Downloaded manifest is invalid; provides filters that do not match its content"));
                } else {
                    manifestDownloadCallback.onSuccess(manifest2);
                }
            } catch (Exception e) {
                String message = e.getMessage();
                Intrinsics.checkNotNull(message);
                UpdatesLogger.error$default(updatesLogger, message, UpdatesErrorCode.UpdateCodeSigningError, null, 4, null);
                String message2 = e.getMessage();
                Intrinsics.checkNotNull(message2);
                manifestDownloadCallback.onFailure(message2, e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final JSONObject extractUpdateResponseJson(String str, UpdatesConfiguration updatesConfiguration) throws IOException {
            try {
                try {
                    return new JSONObject(str);
                } catch (JSONException e) {
                    throw new IOException("Manifest string is not a valid JSONObject or JSONArray: " + str, e);
                }
            } catch (JSONException unused) {
                JSONArray jSONArray = new JSONArray(str);
                int r1 = 0;
                int length = jSONArray.length();
                while (r1 < length) {
                    int r3 = r1 + 1;
                    JSONObject manifestCandidate = jSONArray.getJSONObject(r1);
                    String string = manifestCandidate.getString(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY);
                    if (updatesConfiguration.getSdkVersion() != null && StringsKt.split$default((CharSequence) updatesConfiguration.getSdkVersion(), new String[]{","}, false, 0, 6, (Object) null).contains(string)) {
                        Intrinsics.checkNotNullExpressionValue(manifestCandidate, "manifestCandidate");
                        return manifestCandidate;
                    }
                    r1 = r3;
                }
                String sdkVersion = updatesConfiguration.getSdkVersion();
                throw new IOException("No compatible manifest found. SDK Versions supported: " + sdkVersion + " Provided manifestString: " + str);
            }
        }

        private final Request.Builder addHeadersFromJSONObject(Request.Builder builder, JSONObject jSONObject) {
            JSONObject jSONObject2;
            if (jSONObject == null) {
                return builder;
            }
            Iterator<String> keys = jSONObject.keys();
            Intrinsics.checkNotNullExpressionValue(keys, "headers.keys()");
            for (String key : SequencesKt.asSequence(keys)) {
                Intrinsics.checkNotNullExpressionValue(key, "key");
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                    String string = jSONObject.getString(key);
                    Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.Any");
                    jSONObject2 = string;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    jSONObject2 = Double.valueOf(jSONObject.getDouble(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    jSONObject2 = Integer.valueOf(jSONObject.getInt(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    jSONObject2 = Long.valueOf(jSONObject.getLong(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    jSONObject2 = Boolean.valueOf(jSONObject.getBoolean(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = jSONObject.getJSONArray(key);
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.Any");
                    jSONObject2 = jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject(key);
                    Objects.requireNonNull(jSONObject3, "null cannot be cast to non-null type kotlin.Any");
                    jSONObject2 = jSONObject3;
                } else {
                    jSONObject2 = jSONObject.get(key);
                    Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.Any");
                }
                builder.header(key, jSONObject2.toString());
            }
            return builder;
        }

        public final Request createRequestForAsset$expo_updates_release(AssetEntity assetEntity, UpdatesConfiguration configuration, Context context) {
            Intrinsics.checkNotNullParameter(assetEntity, "assetEntity");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            Intrinsics.checkNotNullParameter(context, "context");
            Request.Builder builder = new Request.Builder();
            Uri url = assetEntity.getUrl();
            Intrinsics.checkNotNull(url);
            String uri = url.toString();
            Intrinsics.checkNotNullExpressionValue(uri, "assetEntity.url!!.toString()");
            Request.Builder header = addHeadersFromJSONObject(builder.url(uri), assetEntity.getExtraRequestHeaders()).header("Expo-Platform", DeviceInfo.OS_NAME).header("Expo-API-Version", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).header("Expo-Updates-Environment", "BARE");
            String str = new EASClientID(context).getUuid().toString();
            Intrinsics.checkNotNullExpressionValue(str, "EASClientID(context).uuid.toString()");
            Request.Builder header2 = header.header("EAS-Client-ID", str);
            for (Map.Entry<String, String> entry : configuration.getRequestHeaders().entrySet()) {
                header2.header(entry.getKey(), entry.getValue());
            }
            return header2.build();
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x00b3  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00dd A[LOOP:0: B:21:0x00d7->B:23:0x00dd, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00fa  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final okhttp3.Request createRequestForManifest$expo_updates_release(expo.modules.updates.UpdatesConfiguration r6, org.json.JSONObject r7, android.content.Context r8) {
            /*
                r5 = this;
                java.lang.String r0 = "configuration"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                okhttp3.Request$Builder r0 = new okhttp3.Request$Builder
                r0.<init>()
                android.net.Uri r1 = r6.getUpdateUrl()
                java.lang.String r1 = java.lang.String.valueOf(r1)
                okhttp3.Request$Builder r0 = r0.url(r1)
                okhttp3.Request$Builder r7 = r5.addHeadersFromJSONObject(r0, r7)
                java.lang.String r0 = "Accept"
                java.lang.String r1 = "multipart/mixed,application/expo+json,application/json"
                okhttp3.Request$Builder r7 = r7.header(r0, r1)
                java.lang.String r0 = "Expo-Platform"
                java.lang.String r1 = "android"
                okhttp3.Request$Builder r7 = r7.header(r0, r1)
                java.lang.String r0 = "Expo-API-Version"
                java.lang.String r1 = "1"
                okhttp3.Request$Builder r7 = r7.header(r0, r1)
                java.lang.String r0 = "Expo-Updates-Environment"
                java.lang.String r1 = "BARE"
                okhttp3.Request$Builder r7 = r7.header(r0, r1)
                java.lang.String r0 = "Expo-JSON-Error"
                java.lang.String r1 = "true"
                okhttp3.Request$Builder r7 = r7.header(r0, r1)
                boolean r0 = r6.getExpectsSignedManifest()
                java.lang.String r0 = java.lang.String.valueOf(r0)
                java.lang.String r1 = "Expo-Accept-Signature"
                okhttp3.Request$Builder r7 = r7.header(r1, r0)
                expo.modules.easclient.EASClientID r0 = new expo.modules.easclient.EASClientID
                r0.<init>(r8)
                java.util.UUID r0 = r0.getUuid()
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "EASClientID(context).uuid.toString()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                java.lang.String r1 = "EAS-Client-ID"
                okhttp3.Request$Builder r7 = r7.header(r1, r0)
                java.lang.String r0 = r6.getRuntimeVersion()
                java.lang.String r1 = r6.getSdkVersion()
                r2 = 1
                r3 = 0
                if (r0 == 0) goto L8d
                r4 = r0
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4
                int r4 = r4.length()
                if (r4 <= 0) goto L84
                r4 = 1
                goto L85
            L84:
                r4 = 0
            L85:
                if (r4 == 0) goto L8d
                java.lang.String r1 = "Expo-Runtime-Version"
                r7.header(r1, r0)
                goto La1
            L8d:
                if (r1 == 0) goto La1
                r0 = r1
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0
                int r0 = r0.length()
                if (r0 <= 0) goto L99
                goto L9a
            L99:
                r2 = 0
            L9a:
                if (r2 == 0) goto La1
                java.lang.String r0 = "Expo-SDK-Version"
                r7.header(r0, r1)
            La1:
                java.lang.String r0 = r6.getReleaseChannel()
                java.lang.String r1 = "Expo-Release-Channel"
                okhttp3.Request$Builder r7 = r7.header(r1, r0)
                expo.modules.updates.launcher.NoDatabaseLauncher$Companion r0 = expo.modules.updates.launcher.NoDatabaseLauncher.Companion
                java.lang.String r8 = r0.consumeErrorLog(r8)
                if (r8 == 0) goto Lcb
                r0 = 1024(0x400, float:1.435E-42)
                int r1 = r8.length()
                int r0 = java.lang.Math.min(r0, r1)
                java.lang.String r8 = r8.substring(r3, r0)
                java.lang.String r0 = "this as java.lang.String…ing(startIndex, endIndex)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)
                java.lang.String r0 = "Expo-Fatal-Error"
                r7.header(r0, r8)
            Lcb:
                java.util.Map r8 = r6.getRequestHeaders()
                java.util.Set r8 = r8.entrySet()
                java.util.Iterator r8 = r8.iterator()
            Ld7:
                boolean r0 = r8.hasNext()
                if (r0 == 0) goto Lf3
                java.lang.Object r0 = r8.next()
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0
                java.lang.Object r1 = r0.getKey()
                java.lang.String r1 = (java.lang.String) r1
                java.lang.Object r0 = r0.getValue()
                java.lang.String r0 = (java.lang.String) r0
                r7.header(r1, r0)
                goto Ld7
            Lf3:
                expo.modules.updates.codesigning.CodeSigningConfiguration r6 = r6.getCodeSigningConfiguration()
                if (r6 != 0) goto Lfa
                goto L103
            Lfa:
                java.lang.String r6 = r6.getAcceptSignatureHeader()
                java.lang.String r8 = "expo-expect-signature"
                r7.header(r8, r6)
            L103:
                okhttp3.Request r6 = r7.build()
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.updates.loader.FileDownloader.Companion.createRequestForManifest$expo_updates_release(expo.modules.updates.UpdatesConfiguration, org.json.JSONObject, android.content.Context):okhttp3.Request");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Cache getCache(Context context) {
            return new Cache(getCacheDirectory(context), 52428800);
        }

        private final File getCacheDirectory(Context context) {
            return new File(context.getCacheDir(), "okhttp");
        }

        public final JSONObject getExtraHeaders(UpdatesDatabase database, UpdatesConfiguration configuration, UpdateEntity updateEntity, UpdateEntity updateEntity2) {
            Intrinsics.checkNotNullParameter(database, "database");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            JSONObject serverDefinedHeaders = ManifestMetadata.getServerDefinedHeaders(database, configuration);
            if (serverDefinedHeaders == null) {
                serverDefinedHeaders = new JSONObject();
            }
            if (updateEntity != null) {
                String str = updateEntity.getId().toString();
                Intrinsics.checkNotNullExpressionValue(str, "it.id.toString()");
                String lowerCase = str.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                serverDefinedHeaders.put("Expo-Current-Update-ID", lowerCase);
            }
            if (updateEntity2 != null) {
                String str2 = updateEntity2.getId().toString();
                Intrinsics.checkNotNullExpressionValue(str2, "it.id.toString()");
                String lowerCase2 = str2.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                serverDefinedHeaders.put("Expo-Embedded-Update-ID", lowerCase2);
            }
            return serverDefinedHeaders;
        }
    }
}
