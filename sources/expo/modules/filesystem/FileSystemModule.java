package expo.modules.filesystem;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.messaging.Constants;
import com.onesignal.outcomes.data.OutcomeEventsTable;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ActivityEventListener;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.filesystem.FileSystemModule;
import expo.modules.filesystem.UploadType;
import expo.modules.interfaces.filesystem.FilePermissionModuleInterface;
import expo.modules.interfaces.filesystem.Permission;
import expo.modules.interfaces.permissions.PermissionsResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.CookieHandler;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.p023io.Closeable;
import kotlin.text.StringsKt;
import okhttp3.C5015Response;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Okio__JvmOkioKt;
import okio.Source;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.p028io.FileUtils;
import org.apache.commons.p028io.IOUtils;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* compiled from: FileSystemModule.kt */
@Metadata(m184d1 = {"\u0000ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002:\f\u0087\u0001\u0088\u0001\u0089\u0001\u008a\u0001\u008b\u0001\u008c\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J(\u0010 \u001a\u00020!2\u0016\u0010\"\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$0#2\u0006\u0010%\u001a\u00020\u000bH\u0007J.\u0010&\u001a\u0004\u0018\u00010'2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020$0#2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J.\u0010*\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010\u00112\b\u0010,\u001a\u0004\u0018\u00010\u00112\b\u0010-\u001a\u0004\u0018\u00010\u00112\u0006\u0010%\u001a\u00020\u000bH\u0007J>\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u00112\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020$0#2\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020)H\u0002J2\u00102\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010\u00112\u0016\u0010\"\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$0#2\u0006\u0010%\u001a\u00020\u000bH\u0007J<\u00103\u001a\u00020!2\u0006\u00100\u001a\u00020\u00112\b\u0010+\u001a\u0004\u0018\u00010\u00112\u0018\u0010\"\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$\u0018\u00010#2\u0006\u0010%\u001a\u00020\u000bH\u0007J\u0018\u00104\u001a\u00020!2\u0006\u00105\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u000bH\u0007JJ\u00106\u001a\u00020!2\u0006\u00100\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u00112\u0016\u0010\"\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$0#2\b\u00108\u001a\u0004\u0018\u00010\u00112\u0006\u0010%\u001a\u00020\u000bH\u0007J\u0010\u00109\u001a\u00020!2\u0006\u0010:\u001a\u00020\u001fH\u0002J\u0018\u0010;\u001a\u00020!2\u0006\u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020>H\u0002J \u0010;\u001a\u00020!2\u0006\u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0011H\u0002J\u0010\u0010@\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0016\u0010A\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010$0#H\u0016J\u0018\u0010B\u001a\u00020!2\u0006\u0010<\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u000bH\u0007J \u0010C\u001a\u00020\u00112\u0016\u0010\"\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$0#H\u0002J\u0010\u0010D\u001a\u00020E2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010F\u001a\u00020!2\u0006\u0010%\u001a\u00020\u000bH\u0007J0\u0010G\u001a\u00020!2\u0006\u0010H\u001a\u00020\u00112\u0016\u0010\"\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$0#2\u0006\u0010%\u001a\u00020\u000bH\u0007J\u0010\u0010I\u001a\u00020J2\u0006\u0010<\u001a\u00020\u001bH\u0002J\u0010\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020JH\u0002J\b\u0010N\u001a\u00020\u0011H\u0016J\u0012\u0010O\u001a\u0004\u0018\u00010P2\u0006\u0010<\u001a\u00020\u001bH\u0002J\u0010\u0010Q\u001a\u00020R2\u0006\u0010<\u001a\u00020\u001bH\u0002J\u0010\u0010S\u001a\u00020!2\u0006\u0010%\u001a\u00020\u000bH\u0007J2\u0010T\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010\u00112\u0016\u0010\"\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$0#2\u0006\u0010%\u001a\u00020\u000bH\u0007J$\u0010U\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010\u00112\b\u0010V\u001a\u0004\u0018\u00010\u00112\u0006\u0010%\u001a\u00020\u000bH\u0007J\u0010\u0010W\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001f\u0010X\u001a\u0010\u0012\f\u0012\n [*\u0004\u0018\u0001HZHZ0Y\"\u0006\b\u0000\u0010Z\u0018\u0001H\u0082\bJ(\u0010\\\u001a\u00020!2\u0016\u0010\"\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$0#2\u0006\u0010%\u001a\u00020\u000bH\u0007J\u0018\u0010]\u001a\u00020!2\u0006\u00105\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u000bH\u0007J*\u0010^\u001a\u00020!2\u0006\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020b2\b\u0010d\u001a\u0004\u0018\u00010eH\u0017J\u0010\u0010f\u001a\u00020!2\u0006\u0010X\u001a\u00020gH\u0016J\u0010\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020eH\u0016J\u0010\u0010j\u001a\u00020J2\u0006\u0010<\u001a\u00020\u001bH\u0002J\u0012\u0010k\u001a\u00020J2\b\u0010l\u001a\u0004\u0018\u00010\u0011H\u0002J\u0010\u0010m\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u0011H\u0002J\u001a\u0010n\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010o2\b\u0010p\u001a\u0004\u0018\u00010\u0011H\u0002J\u0016\u0010q\u001a\b\u0012\u0004\u0012\u00020>0o2\u0006\u0010<\u001a\u00020\u001bH\u0002J\u0018\u0010r\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010o2\u0006\u0010<\u001a\u00020\u001bH\u0002J2\u0010s\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010\u00112\u0016\u0010\"\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$0#2\u0006\u0010%\u001a\u00020\u000bH\u0007J4\u0010t\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010\u00112\u0018\u0010\"\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$\u0018\u00010#2\u0006\u0010%\u001a\u00020\u000bH\u0007J4\u0010u\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010\u00112\u0018\u0010\"\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$\u0018\u00010#2\u0006\u0010%\u001a\u00020\u000bH\u0007J\u001a\u0010v\u001a\u00020!2\b\u0010w\u001a\u0004\u0018\u00010\u00112\u0006\u0010%\u001a\u00020\u000bH\u0007J \u0010x\u001a\u00020!2\u0006\u0010y\u001a\u00020P2\u0006\u0010z\u001a\u00020\u001f2\u0006\u0010{\u001a\u00020\u001aH\u0002J\u0010\u0010|\u001a\u00020}2\u0006\u0010~\u001a\u00020\u007fH\u0002J5\u0010\u0080\u0001\u001a\u00020!2\u0006\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u00112\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020$0#2\u0006\u0010%\u001a\u00020\u000bH\u0007J=\u0010\u0081\u0001\u001a\u00020!2\u0006\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u00112\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020$0#2\u0006\u0010%\u001a\u00020\u000bH\u0007J>\u0010\u0082\u0001\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010\u00112\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u00112\u0016\u0010\"\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010$0#2\u0006\u0010%\u001a\u00020\u000bH\u0007J\r\u0010\u0084\u0001\u001a\u00020!*\u00020\u001bH\u0002J\r\u0010\u0085\u0001\u001a\u00020!*\u00020\u001bH\u0002J\r\u0010\u0086\u0001\u001a\u00020\u001f*\u00020\u001bH\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u0004\u0018\u00010\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0019\u001a\u00020\u001a*\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001c¨\u0006\u008d\u0001²\u0006\f\u0010\u008e\u0001\u001a\u00030\u008f\u0001X\u008a\u0084\u0002²\u0006\f\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u008a\u0084\u0002²\u0006\f\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u008a\u0084\u0002²\u0006\f\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u008a\u0084\u0002"}, m183d2 = {"Lexpo/modules/filesystem/FileSystemModule;", "Lexpo/modules/core/ExportedModule;", "Lexpo/modules/core/interfaces/ActivityEventListener;", "context", "Landroid/content/Context;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Landroid/content/Context;Lexpo/modules/core/ModuleRegistryDelegate;)V", "client", "Lokhttp3/OkHttpClient;", "dirPermissionsRequest", "Lexpo/modules/core/Promise;", "okHttpClient", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "taskHandlers", "", "", "Lexpo/modules/filesystem/FileSystemModule$TaskHandler;", "uIManager", "Lexpo/modules/core/interfaces/services/UIManager;", "getUIManager", "()Lexpo/modules/core/interfaces/services/UIManager;", "uIManager$delegate", "Lkotlin/Lazy;", "isSAFUri", "", "Landroid/net/Uri;", "(Landroid/net/Uri;)Z", "contentUriFromFile", "file", "Ljava/io/File;", "copyAsync", "", "options", "", "", BaseJavaModule.METHOD_TYPE_PROMISE, "createRequestBody", "Lokhttp3/RequestBody;", "decorator", "Lexpo/modules/filesystem/RequestBodyDecorator;", "createSAFFileAsync", "uriStr", "fileName", "mimeType", "createUploadRequest", "Lokhttp3/Request;", ImagesContract.URL, "fileUriString", "deleteAsync", "downloadAsync", "downloadResumablePauseAsync", "uuid", "downloadResumableStartAsync", "fileUriStr", "resumeData", "ensureDirExists", "dir", "ensurePermission", "uri", "permission", "Lexpo/modules/interfaces/filesystem/Permission;", "errorMsg", "forceDelete", "getConstants", "getContentUriAsync", "getEncodingFromOptions", "getFileSize", "", "getFreeDiskStorageAsync", "getInfoAsync", "_uriStr", "getInputStream", "Ljava/io/InputStream;", "getInputStreamBytes", "", "inputStream", "getName", "getNearestSAFFile", "Landroidx/documentfile/provider/DocumentFile;", "getOutputStream", "Ljava/io/OutputStream;", "getTotalDiskCapacityAsync", "makeDirectoryAsync", "makeSAFDirectoryAsync", "dirName", "md5", "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "moveAsync", "networkTaskCancelAsync", "onActivityResult", "activity", "Landroid/app/Activity;", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "Lexpo/modules/core/ModuleRegistry;", "onNewIntent", "intent", "openAssetInputStream", "openResourceInputStream", "resourceName", "parseFileUri", "permissionsForPath", "Ljava/util/EnumSet;", RNFetchBlobConst.RNFB_RESPONSE_PATH, "permissionsForSAFUri", "permissionsForUri", "readAsStringAsync", "readDirectoryAsync", "readSAFDirectoryAsync", "requestDirectoryPermissionsAsync", "initialFileUrl", "transformFilesFromSAF", "documentFile", "outputDir", "copy", "translateHeaders", "Landroid/os/Bundle;", "headers", "Lokhttp3/Headers;", "uploadAsync", "uploadTaskStartAsync", "writeAsStringAsync", "string", "checkIfFileDirExists", "checkIfFileExists", "toFile", "DownloadResumableTask", "DownloadResumableTaskParams", "DownloadTaskHandler", "ProgressListener", "ProgressResponseBody", "TaskHandler", "expo-file-system_release", "filePermissionModule", "Lexpo/modules/interfaces/filesystem/FilePermissionModuleInterface;", "activityProvider", "Lexpo/modules/core/interfaces/ActivityProvider;", "cookieHandler", "Ljava/net/CookieHandler;"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public class FileSystemModule extends ExportedModule implements ActivityEventListener {
    private OkHttpClient client;
    private Promise dirPermissionsRequest;
    private final ModuleRegistryDelegate moduleRegistryDelegate;
    private final Map<String, TaskHandler> taskHandlers;
    private final Lazy uIManager$delegate;

    /* compiled from: FileSystemModule.kt */
    @Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, m183d2 = {"Lexpo/modules/filesystem/FileSystemModule$ProgressListener;", "", "update", "", "bytesRead", "", "contentLength", "done", "", "expo-file-system_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public interface ProgressListener {
        void update(long j, long j2, boolean z);
    }

    /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.isRegister()" because "arg" is null
        	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
        	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
        	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
        	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
        	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
        	at jadx.core.dex.visitors.MarkMethodsForInline.visit(MarkMethodsForInline.java:37)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadAsync$lambda-42  reason: not valid java name */
    public static final okhttp3.RequestBody m1634uploadAsync$lambda42(okhttp3.RequestBody r1) {
        /*
            java.lang.String r0 = "requestBody"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule.m1634uploadAsync$lambda42(okhttp3.RequestBody):okhttp3.RequestBody");
    }

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExponentFileSystem";
    }

    @Override // expo.modules.core.interfaces.ActivityEventListener
    public void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
    }

    public /* synthetic */ FileSystemModule(Context context, ModuleRegistryDelegate moduleRegistryDelegate, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (r3 & 2) != 0 ? new ModuleRegistryDelegate() : moduleRegistryDelegate);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileSystemModule(Context context, ModuleRegistryDelegate moduleRegistryDelegate) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.moduleRegistryDelegate = moduleRegistryDelegate;
        try {
            File filesDir = getContext().getFilesDir();
            Intrinsics.checkNotNullExpressionValue(filesDir, "getContext().filesDir");
            ensureDirExists(filesDir);
            File cacheDir = getContext().getCacheDir();
            Intrinsics.checkNotNullExpressionValue(cacheDir, "getContext().cacheDir");
            ensureDirExists(cacheDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ModuleRegistryDelegate moduleRegistryDelegate2 = this.moduleRegistryDelegate;
        this.uIManager$delegate = LazyKt.lazy(new Functions<UIManager>() { // from class: expo.modules.filesystem.FileSystemModule$special$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.core.interfaces.services.UIManager, java.lang.Object] */
            @Override // kotlin.jvm.functions.Functions
            public final UIManager invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(UIManager.class);
            }
        });
        this.taskHandlers = new HashMap();
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.filesystem.FileSystemModule$moduleRegistry$$inlined$getFromModuleRegistry$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final T invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                Intrinsics.reifiedOperationMarker(4, "T");
                return (T) moduleRegistry.getModule(Object.class);
            }
        });
    }

    private final UIManager getUIManager() {
        Object value = this.uIManager$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-uIManager>(...)");
        return (UIManager) value;
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistryDelegate.onCreate(moduleRegistry);
    }

    @Override // expo.modules.core.ExportedModule
    public Map<String, Object> getConstants() {
        String uri = Uri.fromFile(getContext().getFilesDir()).toString();
        String uri2 = Uri.fromFile(getContext().getCacheDir()).toString();
        return MapsKt.mapOf(TuplesKt.m176to("documentDirectory", uri + "/"), TuplesKt.m176to("cacheDirectory", uri2 + "/"), TuplesKt.m176to("bundleDirectory", "asset:///"));
    }

    private final void checkIfFileExists(Uri uri) throws IOException {
        File file = toFile(uri);
        if (file.exists()) {
            return;
        }
        String path = file.getPath();
        throw new IOException("Directory for '" + path + "' doesn't exist.");
    }

    private final void checkIfFileDirExists(Uri uri) throws IOException {
        File file = toFile(uri);
        File parentFile = file.getParentFile();
        if (parentFile == null || !parentFile.exists()) {
            String path = file.getPath();
            String parent = file.getParent();
            throw new IOException("Directory for '" + path + "' doesn't exist. Please make sure directory '" + parent + "' exists before calling downloadAsync.");
        }
    }

    /* renamed from: permissionsForPath$lambda-0  reason: not valid java name */
    private static final FilePermissionModuleInterface m1632permissionsForPath$lambda0(Lazy<? extends FilePermissionModuleInterface> lazy) {
        FilePermissionModuleInterface value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "permissionsForPath$lambda-0(...)");
        return value;
    }

    private final EnumSet<Permission> permissionsForUri(Uri uri) {
        if (isSAFUri(uri)) {
            return permissionsForSAFUri(uri);
        }
        if (!Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME) && !Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
            return Intrinsics.areEqual(uri.getScheme(), "file") ? permissionsForPath(uri.getPath()) : uri.getScheme() == null ? EnumSet.of(Permission.READ) : EnumSet.noneOf(Permission.class);
        }
        return EnumSet.of(Permission.READ);
    }

    private final EnumSet<Permission> permissionsForSAFUri(Uri uri) {
        DocumentFile nearestSAFFile = getNearestSAFFile(uri);
        EnumSet<Permission> noneOf = EnumSet.noneOf(Permission.class);
        if (nearestSAFFile != null) {
            if (nearestSAFFile.canRead()) {
                noneOf.add(Permission.READ);
            }
            if (nearestSAFFile.canWrite()) {
                noneOf.add(Permission.WRITE);
            }
        }
        Intrinsics.checkNotNullExpressionValue(noneOf, "noneOf(Permission::class…)\n        }\n      }\n    }");
        return noneOf;
    }

    private final void ensurePermission(Uri uri, Permission permission, String str) throws IOException {
        EnumSet<Permission> permissionsForUri = permissionsForUri(uri);
        boolean z = false;
        if (permissionsForUri != null && permissionsForUri.contains(permission)) {
            z = true;
        }
        if (!z) {
            throw new IOException(str);
        }
    }

    private final void ensurePermission(Uri uri, Permission permission) throws IOException {
        if (permission == Permission.READ) {
            ensurePermission(uri, permission, "Location '" + uri + "' isn't readable.");
        }
        if (permission == Permission.WRITE) {
            ensurePermission(uri, permission, "Location '" + uri + "' isn't writable.");
        }
        String name = permission.name();
        ensurePermission(uri, permission, "Location '" + uri + "' doesn't have permission '" + name + "'.");
    }

    private final InputStream openAssetInputStream(Uri uri) throws IOException {
        String path = uri.getPath();
        if (path == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        String substring = path.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        InputStream open = getContext().getAssets().open(substring);
        Intrinsics.checkNotNullExpressionValue(open, "context.assets.open(asset)");
        return open;
    }

    private final InputStream openResourceInputStream(String str) throws IOException {
        int identifier = getContext().getResources().getIdentifier(str, "raw", getContext().getPackageName());
        if (identifier == 0 && (identifier = getContext().getResources().getIdentifier(str, "drawable", getContext().getPackageName())) == 0) {
            throw new FileNotFoundException("No resource found with the name '" + str + "'");
        }
        InputStream openRawResource = getContext().getResources().openRawResource(identifier);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "context.resources.openRawResource(resourceId)");
        return openRawResource;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0147 A[Catch: FileNotFoundException -> 0x0195, Exception -> 0x01a5, TryCatch #0 {FileNotFoundException -> 0x0195, blocks: (B:34:0x0109, B:36:0x010f, B:41:0x011e, B:44:0x0125, B:51:0x0147, B:53:0x0168, B:55:0x0176, B:56:0x018b, B:57:0x018f, B:58:0x0194, B:45:0x0132, B:48:0x0139, B:49:0x0141), top: B:68:0x0109, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x018f A[Catch: FileNotFoundException -> 0x0195, Exception -> 0x01a5, TryCatch #0 {FileNotFoundException -> 0x0195, blocks: (B:34:0x0109, B:36:0x010f, B:41:0x011e, B:44:0x0125, B:51:0x0147, B:53:0x0168, B:55:0x0176, B:56:0x018b, B:57:0x018f, B:58:0x0194, B:45:0x0132, B:48:0x0139, B:49:0x0141), top: B:68:0x0109, outer: #1 }] */
    @expo.modules.core.interfaces.ExpoMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void getInfoAsync(java.lang.String r17, java.util.Map<java.lang.String, ? extends java.lang.Object> r18, expo.modules.core.Promise r19) {
        /*
            Method dump skipped, instructions count: 442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule.getInfoAsync(java.lang.String, java.util.Map, expo.modules.core.Promise):void");
    }

    @ExpoMethod
    public final void readAsStringAsync(String str, Map<String, ? extends Object> options, Promise promise) {
        String str2;
        String slashifyFilePath;
        String iOUtils;
        String str3;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
            Uri uri = Uri.parse(slashifyFilePath);
            Intrinsics.checkNotNullExpressionValue(uri, "uri");
            ensurePermission(uri, Permission.READ);
            if (StringsKt.equals(getEncodingFromOptions(options), RNFetchBlobConst.RNFB_RESPONSE_BASE64, true)) {
                InputStream inputStream = getInputStream(uri);
                InputStream inputStream2 = inputStream;
                if (options.containsKey(SessionDescription.ATTR_LENGTH) && options.containsKey(ViewProps.POSITION)) {
                    Object obj = options.get(SessionDescription.ATTR_LENGTH);
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Number");
                    }
                    int intValue = ((Number) obj).intValue();
                    Object obj2 = options.get(ViewProps.POSITION);
                    if (obj2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Number");
                    }
                    byte[] bArr = new byte[intValue];
                    inputStream2.skip(((Number) obj2).intValue());
                    str3 = Base64.encodeToString(bArr, 0, inputStream2.read(bArr, 0, intValue), 2);
                } else {
                    str3 = Base64.encodeToString(getInputStreamBytes(inputStream2), 2);
                }
                Unit unit = Unit.INSTANCE;
                Closeable.closeFinally(inputStream, null);
            } else {
                if (Intrinsics.areEqual(uri.getScheme(), "file")) {
                    iOUtils = IOUtils.toString(new FileInputStream(toFile(uri)));
                } else if (Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
                    iOUtils = IOUtils.toString(openAssetInputStream(uri));
                } else if (uri.getScheme() == null) {
                    iOUtils = IOUtils.toString(openResourceInputStream(str));
                } else if (!isSAFUri(uri)) {
                    throw new IOException("Unsupported scheme for location '" + uri + "'.");
                } else {
                    iOUtils = IOUtils.toString(getContext().getContentResolver().openInputStream(uri));
                }
                str3 = iOUtils;
            }
            promise.resolve(str3);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str2 = FileSystemModuleKt.TAG;
                Log.e(str2, message);
            }
            promise.reject(e);
        }
    }

    @ExpoMethod
    public final void writeAsStringAsync(String str, String str2, Map<String, ? extends Object> options, Promise promise) {
        String str3;
        String slashifyFilePath;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
            Uri uri = Uri.parse(slashifyFilePath);
            Intrinsics.checkNotNullExpressionValue(uri, "uri");
            ensurePermission(uri, Permission.WRITE);
            String encodingFromOptions = getEncodingFromOptions(options);
            OutputStream outputStream = getOutputStream(uri);
            OutputStream outputStream2 = outputStream;
            if (Intrinsics.areEqual(encodingFromOptions, RNFetchBlobConst.RNFB_RESPONSE_BASE64)) {
                outputStream2.write(Base64.decode(str2, 0));
            } else {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream2);
                try {
                    outputStreamWriter.write(str2);
                    Unit unit = Unit.INSTANCE;
                    Closeable.closeFinally(outputStreamWriter, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        Closeable.closeFinally(outputStreamWriter, th);
                        throw th2;
                    }
                }
            }
            Unit unit2 = Unit.INSTANCE;
            Closeable.closeFinally(outputStream, null);
            promise.resolve(null);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str3 = FileSystemModuleKt.TAG;
                Log.e(str3, message);
            }
            promise.reject(e);
        }
    }

    @ExpoMethod
    public final void deleteAsync(String str, Map<String, ? extends Object> options, Promise promise) {
        String str2;
        String slashifyFilePath;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
            Uri uri = Uri.parse(slashifyFilePath);
            Uri appendedUri = Uri.withAppendedPath(uri, "..");
            Intrinsics.checkNotNullExpressionValue(appendedUri, "appendedUri");
            Permission permission = Permission.WRITE;
            ensurePermission(appendedUri, permission, "Location '" + uri + "' isn't deletable.");
            if (Intrinsics.areEqual(uri.getScheme(), "file")) {
                Intrinsics.checkNotNullExpressionValue(uri, "uri");
                File file = toFile(uri);
                if (file.exists()) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        FileUtils.forceDelete(file);
                    } else {
                        forceDelete(file);
                    }
                    promise.resolve(null);
                    return;
                }
                if (options.containsKey("idempotent")) {
                    Object obj = options.get("idempotent");
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                    }
                    if (((Boolean) obj).booleanValue()) {
                        promise.resolve(null);
                        return;
                    }
                }
                promise.reject("ERR_FILESYSTEM_CANNOT_FIND_FILE", "File '" + uri + "' could not be deleted because it could not be found");
                return;
            }
            Intrinsics.checkNotNullExpressionValue(uri, "uri");
            if (isSAFUri(uri)) {
                DocumentFile nearestSAFFile = getNearestSAFFile(uri);
                if (nearestSAFFile != null && nearestSAFFile.exists()) {
                    nearestSAFFile.delete();
                    promise.resolve(null);
                    return;
                }
                if (options.containsKey("idempotent")) {
                    Object obj2 = options.get("idempotent");
                    if (obj2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                    }
                    if (((Boolean) obj2).booleanValue()) {
                        promise.resolve(null);
                        return;
                    }
                }
                promise.reject("ERR_FILESYSTEM_CANNOT_FIND_FILE", "File '" + uri + "' could not be deleted because it could not be found");
                return;
            }
            throw new IOException("Unsupported scheme for location '" + uri + "'.");
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str2 = FileSystemModuleKt.TAG;
                Log.e(str2, message);
            }
            promise.reject(e);
        }
    }

    @ExpoMethod
    public final void moveAsync(Map<String, ? extends Object> options, Promise promise) {
        String str;
        String slashifyFilePath;
        String slashifyFilePath2;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            if (options.containsKey(Constants.MessagePayloadKeys.FROM)) {
                slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) options.get(Constants.MessagePayloadKeys.FROM));
                Uri fromUri = Uri.parse(slashifyFilePath);
                Uri withAppendedPath = Uri.withAppendedPath(fromUri, "..");
                Intrinsics.checkNotNullExpressionValue(withAppendedPath, "withAppendedPath(fromUri, \"..\")");
                Permission permission = Permission.WRITE;
                ensurePermission(withAppendedPath, permission, "Location '" + fromUri + "' isn't movable.");
                if (options.containsKey("to")) {
                    slashifyFilePath2 = FileSystemModuleKt.slashifyFilePath((String) options.get("to"));
                    Uri toUri = Uri.parse(slashifyFilePath2);
                    Intrinsics.checkNotNullExpressionValue(toUri, "toUri");
                    ensurePermission(toUri, Permission.WRITE);
                    if (Intrinsics.areEqual(fromUri.getScheme(), "file")) {
                        Intrinsics.checkNotNullExpressionValue(fromUri, "fromUri");
                        if (toFile(fromUri).renameTo(toFile(toUri))) {
                            promise.resolve(null);
                            return;
                        }
                        promise.reject("ERR_FILESYSTEM_CANNOT_MOVE_FILE", "File '" + fromUri + "' could not be moved to '" + toUri + "'");
                        return;
                    }
                    Intrinsics.checkNotNullExpressionValue(fromUri, "fromUri");
                    if (isSAFUri(fromUri)) {
                        DocumentFile nearestSAFFile = getNearestSAFFile(fromUri);
                        if (nearestSAFFile != null && nearestSAFFile.exists()) {
                            transformFilesFromSAF(nearestSAFFile, new File(toUri.getPath()), false);
                            promise.resolve(null);
                            return;
                        }
                        promise.reject("ERR_FILESYSTEM_CANNOT_MOVE_FILE", "File '" + fromUri + "' could not be moved to '" + toUri + "'");
                        return;
                    }
                    throw new IOException("Unsupported scheme for location '" + fromUri + "'.");
                }
                promise.reject("ERR_FILESYSTEM_MISSING_PARAMETER", "`FileSystem.moveAsync` needs a `to` path.");
                return;
            }
            promise.reject("ERR_FILESYSTEM_MISSING_PARAMETER", "`FileSystem.moveAsync` needs a `from` path.");
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str = FileSystemModuleKt.TAG;
                Log.e(str, message);
            }
            promise.reject(e);
        }
    }

    @ExpoMethod
    public final void copyAsync(Map<String, ? extends Object> options, Promise promise) {
        String str;
        String slashifyFilePath;
        String slashifyFilePath2;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            if (options.containsKey(Constants.MessagePayloadKeys.FROM)) {
                slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) options.get(Constants.MessagePayloadKeys.FROM));
                Uri fromUri = Uri.parse(slashifyFilePath);
                Intrinsics.checkNotNullExpressionValue(fromUri, "fromUri");
                ensurePermission(fromUri, Permission.READ);
                if (options.containsKey("to")) {
                    slashifyFilePath2 = FileSystemModuleKt.slashifyFilePath((String) options.get("to"));
                    Uri toUri = Uri.parse(slashifyFilePath2);
                    Intrinsics.checkNotNullExpressionValue(toUri, "toUri");
                    ensurePermission(toUri, Permission.WRITE);
                    if (Intrinsics.areEqual(fromUri.getScheme(), "file")) {
                        File file = toFile(fromUri);
                        File file2 = toFile(toUri);
                        if (file.isDirectory()) {
                            FileUtils.copyDirectory(file, file2);
                        } else {
                            FileUtils.copyFile(file, file2);
                        }
                        promise.resolve(null);
                        return;
                    } else if (isSAFUri(fromUri)) {
                        DocumentFile nearestSAFFile = getNearestSAFFile(fromUri);
                        if (nearestSAFFile != null && nearestSAFFile.exists()) {
                            transformFilesFromSAF(nearestSAFFile, new File(toUri.getPath()), true);
                            promise.resolve(null);
                            return;
                        }
                        promise.reject("ERR_FILESYSTEM_CANNOT_FIND_FILE", "File '" + fromUri + "' could not be copied because it could not be found");
                        return;
                    } else if (Intrinsics.areEqual(fromUri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME)) {
                        IOUtils.copy(getContext().getContentResolver().openInputStream(fromUri), new FileOutputStream(toFile(toUri)));
                        promise.resolve(null);
                        return;
                    } else if (Intrinsics.areEqual(fromUri.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
                        IOUtils.copy(openAssetInputStream(fromUri), new FileOutputStream(toFile(toUri)));
                        promise.resolve(null);
                        return;
                    } else if (fromUri.getScheme() == null) {
                        IOUtils.copy(openResourceInputStream((String) options.get(Constants.MessagePayloadKeys.FROM)), new FileOutputStream(toFile(toUri)));
                        promise.resolve(null);
                        return;
                    } else {
                        throw new IOException("Unsupported scheme for location '" + fromUri + "'.");
                    }
                }
                promise.reject("ERR_FILESYSTEM_MISSING_PARAMETER", "`FileSystem.moveAsync` needs a `to` path.");
                return;
            }
            promise.reject("ERR_FILESYSTEM_MISSING_PARAMETER", "`FileSystem.moveAsync` needs a `from` path.");
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str = FileSystemModuleKt.TAG;
                Log.e(str, message);
            }
            promise.reject(e);
        }
    }

    private final void transformFilesFromSAF(DocumentFile documentFile, File file, boolean z) throws IOException {
        if (documentFile.exists()) {
            if (!file.exists() && !file.mkdirs()) {
                throw new IOException("Couldn't create folder in output dir.");
            }
            if (documentFile.isDirectory()) {
                DocumentFile[] listFiles = documentFile.listFiles();
                Intrinsics.checkNotNullExpressionValue(listFiles, "documentFile.listFiles()");
                int r1 = 0;
                int length = listFiles.length;
                while (r1 < length) {
                    DocumentFile file2 = listFiles[r1];
                    r1++;
                    String name = documentFile.getName();
                    if (name != null) {
                        Intrinsics.checkNotNullExpressionValue(file2, "file");
                        transformFilesFromSAF(file2, new File(file, name), z);
                    }
                }
                if (z) {
                    return;
                }
                documentFile.delete();
                return;
            }
            String name2 = documentFile.getName();
            if (name2 == null) {
                return;
            }
            File file3 = new File(file.getPath(), name2);
            InputStream openInputStream = getContext().getContentResolver().openInputStream(documentFile.getUri());
            try {
                InputStream inputStream = openInputStream;
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                IOUtils.copy(inputStream, fileOutputStream);
                Closeable.closeFinally(fileOutputStream, null);
                Closeable.closeFinally(openInputStream, null);
                if (z) {
                    return;
                }
                documentFile.delete();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    Closeable.closeFinally(openInputStream, th);
                    throw th2;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0053 A[Catch: Exception -> 0x00a0, TryCatch #0 {Exception -> 0x00a0, blocks: (B:3:0x000c, B:5:0x002a, B:7:0x0038, B:9:0x003e, B:16:0x0053, B:22:0x0063, B:23:0x007f, B:17:0x0058, B:12:0x0048, B:13:0x004f, B:24:0x0084, B:25:0x009f), top: B:33:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0058 A[Catch: Exception -> 0x00a0, TryCatch #0 {Exception -> 0x00a0, blocks: (B:3:0x000c, B:5:0x002a, B:7:0x0038, B:9:0x003e, B:16:0x0053, B:22:0x0063, B:23:0x007f, B:17:0x0058, B:12:0x0048, B:13:0x004f, B:24:0x0084, B:25:0x009f), top: B:33:0x000c }] */
    @expo.modules.core.interfaces.ExpoMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void makeDirectoryAsync(java.lang.String r5, java.util.Map<java.lang.String, ? extends java.lang.Object> r6, expo.modules.core.Promise r7) {
        /*
            r4 = this;
            java.lang.String r0 = "intermediates"
            java.lang.String r1 = "options"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)
            java.lang.String r1 = "promise"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
            java.lang.String r5 = expo.modules.filesystem.FileSystemModuleKt.access$slashifyFilePath(r5)     // Catch: java.lang.Exception -> La0
            android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch: java.lang.Exception -> La0
            java.lang.String r1 = "uri"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r1)     // Catch: java.lang.Exception -> La0
            expo.modules.interfaces.filesystem.Permission r1 = expo.modules.interfaces.filesystem.Permission.WRITE     // Catch: java.lang.Exception -> La0
            r4.ensurePermission(r5, r1)     // Catch: java.lang.Exception -> La0
            java.lang.String r1 = r5.getScheme()     // Catch: java.lang.Exception -> La0
            java.lang.String r2 = "file"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)     // Catch: java.lang.Exception -> La0
            if (r1 == 0) goto L84
            java.io.File r1 = r4.toFile(r5)     // Catch: java.lang.Exception -> La0
            boolean r2 = r1.isDirectory()     // Catch: java.lang.Exception -> La0
            boolean r3 = r6.containsKey(r0)     // Catch: java.lang.Exception -> La0
            if (r3 == 0) goto L50
            java.lang.Object r6 = r6.get(r0)     // Catch: java.lang.Exception -> La0
            if (r6 == 0) goto L48
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch: java.lang.Exception -> La0
            boolean r6 = r6.booleanValue()     // Catch: java.lang.Exception -> La0
            if (r6 == 0) goto L50
            r6 = 1
            goto L51
        L48:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException     // Catch: java.lang.Exception -> La0
            java.lang.String r6 = "null cannot be cast to non-null type kotlin.Boolean"
            r5.<init>(r6)     // Catch: java.lang.Exception -> La0
            throw r5     // Catch: java.lang.Exception -> La0
        L50:
            r6 = 0
        L51:
            if (r6 == 0) goto L58
            boolean r0 = r1.mkdirs()     // Catch: java.lang.Exception -> La0
            goto L5c
        L58:
            boolean r0 = r1.mkdir()     // Catch: java.lang.Exception -> La0
        L5c:
            if (r0 != 0) goto L7f
            if (r6 == 0) goto L63
            if (r2 == 0) goto L63
            goto L7f
        L63:
            java.lang.String r6 = "ERR_FILESYSTEM_CANNOT_CREATE_DIRECTORY"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La0
            r0.<init>()     // Catch: java.lang.Exception -> La0
            java.lang.String r1 = "Directory '"
            r0.append(r1)     // Catch: java.lang.Exception -> La0
            r0.append(r5)     // Catch: java.lang.Exception -> La0
            java.lang.String r5 = "' could not be created or already exists."
            r0.append(r5)     // Catch: java.lang.Exception -> La0
            java.lang.String r5 = r0.toString()     // Catch: java.lang.Exception -> La0
            r7.reject(r6, r5)     // Catch: java.lang.Exception -> La0
            goto Lb4
        L7f:
            r5 = 0
            r7.resolve(r5)     // Catch: java.lang.Exception -> La0
            goto Lb4
        L84:
            java.io.IOException r6 = new java.io.IOException     // Catch: java.lang.Exception -> La0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La0
            r0.<init>()     // Catch: java.lang.Exception -> La0
            java.lang.String r1 = "Unsupported scheme for location '"
            r0.append(r1)     // Catch: java.lang.Exception -> La0
            r0.append(r5)     // Catch: java.lang.Exception -> La0
            java.lang.String r5 = "'."
            r0.append(r5)     // Catch: java.lang.Exception -> La0
            java.lang.String r5 = r0.toString()     // Catch: java.lang.Exception -> La0
            r6.<init>(r5)     // Catch: java.lang.Exception -> La0
            throw r6     // Catch: java.lang.Exception -> La0
        La0:
            r5 = move-exception
            java.lang.String r6 = r5.getMessage()
            if (r6 != 0) goto La8
            goto Laf
        La8:
            java.lang.String r0 = expo.modules.filesystem.FileSystemModuleKt.access$getTAG$p()
            android.util.Log.e(r0, r6)
        Laf:
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r7.reject(r5)
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule.makeDirectoryAsync(java.lang.String, java.util.Map, expo.modules.core.Promise):void");
    }

    @ExpoMethod
    public final void readDirectoryAsync(String str, Map<String, ? extends Object> map, Promise promise) {
        String str2;
        String slashifyFilePath;
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
            Uri uri = Uri.parse(slashifyFilePath);
            Intrinsics.checkNotNullExpressionValue(uri, "uri");
            ensurePermission(uri, Permission.READ);
            if (Intrinsics.areEqual(uri.getScheme(), "file")) {
                File[] listFiles = toFile(uri).listFiles();
                if (listFiles == null) {
                    promise.reject("ERR_FILESYSTEM_CANNOT_READ_DIRECTORY", "Directory '" + uri + "' could not be read.");
                    return;
                }
                ArrayList arrayList = new ArrayList(listFiles.length);
                int r0 = 0;
                int length = listFiles.length;
                while (r0 < length) {
                    File file = listFiles[r0];
                    r0++;
                    arrayList.add(file.getName());
                }
                promise.resolve(arrayList);
            } else if (isSAFUri(uri)) {
                promise.reject("ERR_FILESYSTEM_UNSUPPORTED_SCHEME", "Can't read Storage Access Framework directory, use StorageAccessFramework.readDirectoryAsync() instead.");
            } else {
                throw new IOException("Unsupported scheme for location '" + uri + "'.");
            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str2 = FileSystemModuleKt.TAG;
                Log.e(str2, message);
            }
            promise.reject(e);
        }
    }

    @ExpoMethod
    public final void getTotalDiskCapacityAsync(Promise promise) {
        String str;
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            promise.resolve(Double.valueOf(Math.min(BigInteger.valueOf(statFs.getBlockCountLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).doubleValue(), Math.pow(2.0d, 53.0d) - 1)));
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str = FileSystemModuleKt.TAG;
                Log.e(str, message);
            }
            promise.reject("ERR_FILESYSTEM_CANNOT_DETERMINE_DISK_CAPACITY", "Unable to access total disk capacity", e);
        }
    }

    @ExpoMethod
    public final void getFreeDiskStorageAsync(Promise promise) {
        String str;
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            promise.resolve(Double.valueOf(Math.min(BigInteger.valueOf(statFs.getAvailableBlocksLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).doubleValue(), Math.pow(2.0d, 53.0d) - 1)));
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str = FileSystemModuleKt.TAG;
                Log.e(str, message);
            }
            promise.reject("ERR_FILESYSTEM_CANNOT_DETERMINE_DISK_CAPACITY", "Unable to determine free disk storage capacity", e);
        }
    }

    @ExpoMethod
    public final void getContentUriAsync(String uri, Promise promise) {
        String str;
        String slashifyFilePath;
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(uri);
            Uri fileUri = Uri.parse(slashifyFilePath);
            Intrinsics.checkNotNullExpressionValue(fileUri, "fileUri");
            ensurePermission(fileUri, Permission.WRITE);
            ensurePermission(fileUri, Permission.READ);
            checkIfFileDirExists(fileUri);
            if (Intrinsics.areEqual(fileUri.getScheme(), "file")) {
                promise.resolve(contentUriFromFile(toFile(fileUri)).toString());
            } else {
                promise.reject("ERR_FILESYSTEM_CANNOT_READ_DIRECTORY", "No readable files with the uri '" + uri + "'. Please use other uri.");
            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str = FileSystemModuleKt.TAG;
                Log.e(str, message);
            }
            promise.reject(e);
        }
    }

    /* renamed from: contentUriFromFile$lambda-27  reason: not valid java name */
    private static final ActivityProvider m1631contentUriFromFile$lambda27(Lazy<? extends ActivityProvider> lazy) {
        ActivityProvider value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "contentUriFromFile$lambda-27(...)");
        return value;
    }

    @ExpoMethod
    public final void readSAFDirectoryAsync(String str, Map<String, ? extends Object> map, Promise promise) {
        String str2;
        String slashifyFilePath;
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
            Uri uri = Uri.parse(slashifyFilePath);
            Intrinsics.checkNotNullExpressionValue(uri, "uri");
            ensurePermission(uri, Permission.READ);
            if (isSAFUri(uri)) {
                DocumentFile fromTreeUri = DocumentFile.fromTreeUri(getContext(), uri);
                if (fromTreeUri != null && fromTreeUri.exists() && fromTreeUri.isDirectory()) {
                    DocumentFile[] listFiles = fromTreeUri.listFiles();
                    Intrinsics.checkNotNullExpressionValue(listFiles, "file.listFiles()");
                    ArrayList arrayList = new ArrayList(listFiles.length);
                    int r0 = 0;
                    int length = listFiles.length;
                    while (r0 < length) {
                        DocumentFile documentFile = listFiles[r0];
                        r0++;
                        arrayList.add(documentFile.getUri().toString());
                    }
                    promise.resolve(arrayList);
                    return;
                }
                promise.reject("ERR_FILESYSTEM_CANNOT_READ_DIRECTORY", "Uri '" + uri + "' doesn't exist or isn't a directory.");
                return;
            }
            throw new IOException("The URI '" + uri + "' is not a Storage Access Framework URI. Try using FileSystem.readDirectoryAsync instead.");
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str2 = FileSystemModuleKt.TAG;
                Log.e(str2, message);
            }
            promise.reject(e);
        }
    }

    @ExpoMethod
    public final void makeSAFDirectoryAsync(String str, String str2, Promise promise) {
        String slashifyFilePath;
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
            Uri uri = Uri.parse(slashifyFilePath);
            Intrinsics.checkNotNullExpressionValue(uri, "uri");
            ensurePermission(uri, Permission.WRITE);
            if (!isSAFUri(uri)) {
                throw new IOException("The URI '" + uri + "' is not a Storage Access Framework URI. Try using FileSystem.makeDirectoryAsync instead.");
            }
            DocumentFile nearestSAFFile = getNearestSAFFile(uri);
            if (nearestSAFFile != null && !nearestSAFFile.isDirectory()) {
                promise.reject("ERR_FILESYSTEM_CANNOT_CREATE_DIRECTORY", "Provided uri '" + uri + "' is not pointing to a directory.");
                return;
            }
            DocumentFile documentFile = null;
            if (str2 != null && nearestSAFFile != null) {
                documentFile = nearestSAFFile.createDirectory(str2);
            }
            if (documentFile == null) {
                promise.reject("ERR_FILESYSTEM_CANNOT_CREATE_DIRECTORY", "Unknown error.");
            } else {
                promise.resolve(documentFile.getUri().toString());
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ExpoMethod
    public final void createSAFFileAsync(String str, String str2, String str3, Promise promise) {
        String slashifyFilePath;
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
            Uri uri = Uri.parse(slashifyFilePath);
            Intrinsics.checkNotNullExpressionValue(uri, "uri");
            ensurePermission(uri, Permission.WRITE);
            if (isSAFUri(uri)) {
                DocumentFile nearestSAFFile = getNearestSAFFile(uri);
                if (nearestSAFFile != null && nearestSAFFile.isDirectory()) {
                    if (str3 != null && str2 != null) {
                        DocumentFile createFile = nearestSAFFile.createFile(str3, str2);
                        if (createFile == null) {
                            promise.reject("ERR_FILESYSTEM_CANNOT_CREATE_FILE", "Unknown error.");
                            return;
                        } else {
                            promise.resolve(createFile.getUri().toString());
                            return;
                        }
                    }
                    promise.reject("ERR_FILESYSTEM_CANNOT_CREATE_FILE", "Parameters fileName and mimeType can not be null.");
                    return;
                }
                promise.reject("ERR_FILESYSTEM_CANNOT_CREATE_FILE", "Provided uri '" + uri + "' is not pointing to a directory.");
                return;
            }
            throw new IOException("The URI '" + uri + "' is not a Storage Access Framework URI.");
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ExpoMethod
    public final void requestDirectoryPermissionsAsync(String str, Promise promise) {
        String str2;
        String slashifyFilePath;
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (this.dirPermissionsRequest != null) {
            promise.reject("ERR_FILESYSTEM_CANNOT_ASK_FOR_PERMISSIONS", "You have an unfinished permission request.");
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            if (Build.VERSION.SDK_INT >= 26 && str != null) {
                slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
                Uri parse = Uri.parse(slashifyFilePath);
                if (parse != null) {
                    intent.putExtra("android.provider.extra.INITIAL_URI", parse);
                }
            }
            final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
            Activity currentActivity = m1633requestDirectoryPermissionsAsync$lambda33(LazyKt.lazy(new Functions<ActivityProvider>() { // from class: expo.modules.filesystem.FileSystemModule$requestDirectoryPermissionsAsync$$inlined$moduleRegistry$1
                {
                    super(0);
                }

                /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, expo.modules.core.interfaces.ActivityProvider] */
                @Override // kotlin.jvm.functions.Functions
                public final ActivityProvider invoke() {
                    ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                    Intrinsics.checkNotNull(moduleRegistry);
                    return moduleRegistry.getModule(ActivityProvider.class);
                }
            })).getCurrentActivity();
            if (currentActivity == null) {
                promise.reject("ERR_FILESYSTEM_CANNOT_ASK_FOR_PERMISSIONS", "Can't find activity.");
                return;
            }
            getUIManager().registerActivityEventListener(this);
            this.dirPermissionsRequest = promise;
            currentActivity.startActivityForResult(intent, 5394);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str2 = FileSystemModuleKt.TAG;
                Log.e(str2, message);
            }
            promise.reject("ERR_FILESYSTEM_CANNOT_ASK_FOR_PERMISSIONS", "Can't ask for permissions.", e);
        }
    }

    /* renamed from: requestDirectoryPermissionsAsync$lambda-33  reason: not valid java name */
    private static final ActivityProvider m1633requestDirectoryPermissionsAsync$lambda33(Lazy<? extends ActivityProvider> lazy) {
        ActivityProvider value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "requestDirectoryPermissionsAsync$lambda-33(...)");
        return value;
    }

    private final Request createUploadRequest(String str, String str2, Map<String, ? extends Object> map, Promise promise, RequestBodyDecorator requestBodyDecorator) {
        String str3;
        String slashifyFilePath;
        Map map2;
        try {
            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str2);
            Uri fileUri = Uri.parse(slashifyFilePath);
            Intrinsics.checkNotNullExpressionValue(fileUri, "fileUri");
            ensurePermission(fileUri, Permission.READ);
            checkIfFileExists(fileUri);
            if (!map.containsKey("httpMethod")) {
                promise.reject("ERR_FILESYSTEM_MISSING_HTTP_METHOD", "Missing HTTP method.", null);
                return null;
            }
            String str4 = (String) map.get("httpMethod");
            if (!map.containsKey("uploadType")) {
                promise.reject("ERR_FILESYSTEM_MISSING_UPLOAD_TYPE", "Missing upload type.", null);
                return null;
            }
            Request.Builder url = new Request.Builder().url(str);
            if (map.containsKey("headers") && (map2 = (Map) map.get("headers")) != null) {
                for (Map.Entry entry : map2.entrySet()) {
                    url.addHeader((String) entry.getKey(), entry.getValue().toString());
                }
            }
            RequestBody createRequestBody = createRequestBody(map, requestBodyDecorator, toFile(fileUri));
            if (str4 == null) {
                return null;
            }
            return url.method(str4, createRequestBody).build();
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                str3 = FileSystemModuleKt.TAG;
                Log.e(str3, message);
            }
            promise.reject(e);
            return null;
        }
    }

    private final RequestBody createRequestBody(Map<String, ? extends Object> map, RequestBodyDecorator requestBodyDecorator, File file) {
        UploadType.Companion companion = UploadType.Companion;
        Object obj = map.get("uploadType");
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Double");
        UploadType fromInt = companion.fromInt((int) ((Double) obj).doubleValue());
        if (fromInt == UploadType.BINARY_CONTENT) {
            return requestBodyDecorator.decorate(RequestBody.Companion.create((MediaType) null, file));
        }
        if (fromInt == UploadType.MULTIPART) {
            MultipartBody.Builder type = new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM);
            Object obj2 = map.get("parameters");
            if (obj2 != null) {
                for (Map.Entry entry : ((Map) obj2).entrySet()) {
                    type.addFormDataPart((String) entry.getKey(), entry.getValue().toString());
                }
            }
            Object obj3 = map.get("mimeType");
            String str = obj3 == null ? null : (String) obj3;
            if (str == null) {
                str = URLConnection.guessContentTypeFromName(file.getName());
                Intrinsics.checkNotNullExpressionValue(str, "guessContentTypeFromName(file.name)");
            }
            Object obj4 = map.get("fieldName");
            String fieldName = obj4 != null ? (String) obj4 : null;
            if (fieldName == null) {
                fieldName = file.getName();
            }
            Intrinsics.checkNotNullExpressionValue(fieldName, "fieldName");
            type.addFormDataPart(fieldName, file.getName(), requestBodyDecorator.decorate(RequestBody.Companion.create(file, MediaType.Companion.parse(str))));
            return type.build();
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("Invalid upload type: %s.", Arrays.copyOf(new Object[]{map.get("uploadType")}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        throw new IllegalArgumentException("ERR_FILESYSTEM_INVALID_UPLOAD_TYPE. " + format);
    }

    @ExpoMethod
    public final void uploadAsync(String url, String fileUriString, Map<String, ? extends Object> options, final Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(fileUriString, "fileUriString");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        Request createUploadRequest = createUploadRequest(url, fileUriString, options, promise, new RequestBodyDecorator() { // from class: expo.modules.filesystem.FileSystemModule$$ExternalSyntheticLambda0
            @Override // expo.modules.filesystem.RequestBodyDecorator
            public final RequestBody decorate(RequestBody requestBody) {
                return FileSystemModule.m1634uploadAsync$lambda42(requestBody);
            }
        });
        if (createUploadRequest == null) {
            return;
        }
        OkHttpClient okHttpClient = getOkHttpClient();
        if (okHttpClient == null) {
            unit = null;
        } else {
            okHttpClient.newCall(createUploadRequest).enqueue(new Callback() { // from class: expo.modules.filesystem.FileSystemModule$uploadAsync$1$1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e) {
                    String str;
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(e, "e");
                    str = FileSystemModuleKt.TAG;
                    Log.e(str, String.valueOf(e.getMessage()));
                    Promise.this.reject(e);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, C5015Response response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    Bundle bundle = new Bundle();
                    FileSystemModule fileSystemModule = this;
                    ResponseBody body = response.body();
                    bundle.putString(TtmlNode.TAG_BODY, body == null ? null : body.string());
                    bundle.putInt("status", response.code());
                    bundle.putBundle("headers", fileSystemModule.translateHeaders(response.headers()));
                    response.close();
                    Promise.this.resolve(bundle);
                }
            });
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            promise.reject(new NullPointerException("okHttpClient is null"));
        }
    }

    @ExpoMethod
    public final void uploadTaskStartAsync(String url, String fileUriString, final String uuid, Map<String, ? extends Object> options, final Promise promise) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(fileUriString, "fileUriString");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        final CountingRequestListener countingRequestListener = new CountingRequestListener() { // from class: expo.modules.filesystem.FileSystemModule$uploadTaskStartAsync$progressListener$1
            private long mLastUpdate = -1;

            /* renamed from: onProgress$lambda-0  reason: not valid java name */
            private static final EventEmitter m1636onProgress$lambda0(Lazy<? extends EventEmitter> lazy) {
                EventEmitter value = lazy.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "onProgress$lambda-0(...)");
                return value;
            }

            @Override // expo.modules.filesystem.CountingRequestListener
            public void onProgress(long j, long j2) {
                final ModuleRegistryDelegate moduleRegistryDelegate = FileSystemModule.this.moduleRegistryDelegate;
                Lazy lazy = LazyKt.lazy(new Functions<EventEmitter>() { // from class: expo.modules.filesystem.FileSystemModule$uploadTaskStartAsync$progressListener$1$onProgress$$inlined$moduleRegistry$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, expo.modules.core.interfaces.services.EventEmitter] */
                    @Override // kotlin.jvm.functions.Functions
                    public final EventEmitter invoke() {
                        ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                        Intrinsics.checkNotNull(moduleRegistry);
                        return moduleRegistry.getModule(EventEmitter.class);
                    }
                });
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis > this.mLastUpdate + 100 || j == j2) {
                    this.mLastUpdate = currentTimeMillis;
                    bundle2.putDouble("totalByteSent", j);
                    bundle2.putDouble("totalBytesExpectedToSend", j2);
                    bundle.putString("uuid", uuid);
                    bundle.putBundle("data", bundle2);
                    m1636onProgress$lambda0(lazy).emit("expo-file-system.uploadProgress", bundle);
                }
            }
        };
        Request createUploadRequest = createUploadRequest(url, fileUriString, options, promise, new RequestBodyDecorator() { // from class: expo.modules.filesystem.FileSystemModule$uploadTaskStartAsync$request$1
            @Override // expo.modules.filesystem.RequestBodyDecorator
            public RequestBody decorate(RequestBody requestBody) {
                Intrinsics.checkNotNullParameter(requestBody, "requestBody");
                return new CountingRequestBody(requestBody, CountingRequestListener.this);
            }
        });
        if (createUploadRequest == null) {
            return;
        }
        OkHttpClient okHttpClient = getOkHttpClient();
        Intrinsics.checkNotNull(okHttpClient);
        Call newCall = okHttpClient.newCall(createUploadRequest);
        this.taskHandlers.put(uuid, new TaskHandler(newCall));
        newCall.enqueue(new Callback() { // from class: expo.modules.filesystem.FileSystemModule$uploadTaskStartAsync$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                String str;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                if (!call.isCanceled()) {
                    str = FileSystemModuleKt.TAG;
                    Log.e(str, String.valueOf(e.getMessage()));
                    Promise.this.reject(e);
                    return;
                }
                Promise.this.resolve(null);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, C5015Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Bundle bundle = new Bundle();
                ResponseBody body = response.body();
                FileSystemModule fileSystemModule = this;
                bundle.putString(TtmlNode.TAG_BODY, body == null ? null : body.string());
                bundle.putInt("status", response.code());
                bundle.putBundle("headers", fileSystemModule.translateHeaders(response.headers()));
                response.close();
                Promise.this.resolve(bundle);
            }
        });
    }

    @ExpoMethod
    public final void downloadAsync(String url, String str, final Map<String, ? extends Object> map, final Promise promise) {
        String str2;
        String slashifyFilePath;
        Call newCall;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
            final Uri uri = Uri.parse(slashifyFilePath);
            Intrinsics.checkNotNullExpressionValue(uri, "uri");
            ensurePermission(uri, Permission.WRITE);
            checkIfFileDirExists(uri);
            if (!StringsKt.contains$default((CharSequence) url, (CharSequence) ParameterizedMessage.ERROR_MSG_SEPARATOR, false, 2, (Object) null)) {
                Context context = getContext();
                InputStream openRawResource = context.getResources().openRawResource(context.getResources().getIdentifier(url, "raw", context.getPackageName()));
                Intrinsics.checkNotNullExpressionValue(openRawResource, "context.resources.openRawResource(resourceId)");
                BufferedSource buffer = Okio.buffer(Okio.source(openRawResource));
                File file = toFile(uri);
                file.delete();
                BufferedSink buffer2 = Okio.buffer(Okio__JvmOkioKt.sink$default(file, false, 1, null));
                buffer2.writeAll(buffer);
                buffer2.close();
                Bundle bundle = new Bundle();
                bundle.putString("uri", Uri.fromFile(file).toString());
                Object obj = map == null ? null : map.get("md5");
                if ((Intrinsics.areEqual(obj, (Object) true) ? obj : null) != null) {
                    bundle.putString("md5", md5(file));
                }
                promise.resolve(bundle);
            } else if (Intrinsics.areEqual("file", uri.getScheme())) {
                Request.Builder url2 = new Request.Builder().url(url);
                if (map != null && map.containsKey("headers")) {
                    try {
                        Map map2 = (Map) map.get("headers");
                        if (map2 != null) {
                            for (Map.Entry entry : map2.entrySet()) {
                                url2.addHeader((String) entry.getKey(), entry.getValue().toString());
                            }
                        }
                    } catch (ClassCastException e) {
                        promise.reject("ERR_FILESYSTEM_INVALID_HEADERS", "Invalid headers dictionary. Keys and values should be strings.", e);
                        return;
                    }
                }
                OkHttpClient okHttpClient = getOkHttpClient();
                if (okHttpClient != null && (newCall = okHttpClient.newCall(url2.build())) != null) {
                    newCall.enqueue(new Callback() { // from class: expo.modules.filesystem.FileSystemModule$downloadAsync$4
                        @Override // okhttp3.Callback
                        public void onFailure(Call call, IOException e2) {
                            String str3;
                            Intrinsics.checkNotNullParameter(call, "call");
                            Intrinsics.checkNotNullParameter(e2, "e");
                            str3 = FileSystemModuleKt.TAG;
                            Log.e(str3, String.valueOf(e2.getMessage()));
                            Promise.this.reject(e2);
                        }

                        @Override // okhttp3.Callback
                        public void onResponse(Call call, C5015Response response) throws IOException {
                            File file2;
                            Intrinsics.checkNotNullParameter(call, "call");
                            Intrinsics.checkNotNullParameter(response, "response");
                            FileSystemModule fileSystemModule = this;
                            Uri uri2 = uri;
                            Intrinsics.checkNotNullExpressionValue(uri2, "uri");
                            file2 = fileSystemModule.toFile(uri2);
                            file2.delete();
                            BufferedSink buffer3 = Okio.buffer(Okio__JvmOkioKt.sink$default(file2, false, 1, null));
                            ResponseBody body = response.body();
                            Intrinsics.checkNotNull(body);
                            buffer3.writeAll(body.source());
                            buffer3.close();
                            Bundle bundle2 = new Bundle();
                            FileSystemModule fileSystemModule2 = this;
                            Map<String, Object> map3 = map;
                            bundle2.putString("uri", Uri.fromFile(file2).toString());
                            bundle2.putInt("status", response.code());
                            bundle2.putBundle("headers", fileSystemModule2.translateHeaders(response.headers()));
                            if (map3 != null ? Intrinsics.areEqual(map3.get("md5"), (Object) true) : false) {
                                bundle2.putString("md5", fileSystemModule2.md5(file2));
                            }
                            response.close();
                            Promise.this.resolve(bundle2);
                        }
                    });
                    r6 = Unit.INSTANCE;
                }
                if (r6 == null) {
                    FileSystemModule fileSystemModule = this;
                    promise.reject(new NullPointerException("okHttpClient is null"));
                }
            } else {
                throw new IOException("Unsupported scheme for location '" + uri + "'.");
            }
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message != null) {
                str2 = FileSystemModuleKt.TAG;
                Log.e(str2, message);
            }
            promise.reject(e2);
        }
    }

    @ExpoMethod
    public final void networkTaskCancelAsync(String uuid, Promise promise) {
        Call call;
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(promise, "promise");
        TaskHandler taskHandler = this.taskHandlers.get(uuid);
        if (taskHandler != null && (call = taskHandler.getCall()) != null) {
            call.cancel();
        }
        promise.resolve(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006c, code lost:
        r19.reject(new java.lang.NullPointerException("okHttpClient is null"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0078, code lost:
        return;
     */
    @expo.modules.core.interfaces.ExpoMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void downloadResumableStartAsync(java.lang.String r14, java.lang.String r15, final java.lang.String r16, java.util.Map<java.lang.String, ? extends java.lang.Object> r17, final java.lang.String r18, expo.modules.core.Promise r19) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule.downloadResumableStartAsync(java.lang.String, java.lang.String, java.lang.String, java.util.Map, java.lang.String, expo.modules.core.Promise):void");
    }

    @ExpoMethod
    public final void downloadResumablePauseAsync(String uuid, Promise promise) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(promise, "promise");
        TaskHandler taskHandler = this.taskHandlers.get(uuid);
        if (taskHandler == null) {
            IOException iOException = new IOException("No download object available");
            String message = iOException.getMessage();
            if (message != null) {
                str2 = FileSystemModuleKt.TAG;
                Log.e(str2, message);
            }
            promise.reject(iOException);
        } else if (!(taskHandler instanceof DownloadTaskHandler)) {
            promise.reject("ERR_FILESYSTEM_CANNOT_FIND_TASK", "Cannot find task.");
        } else {
            ((DownloadTaskHandler) taskHandler).getCall().cancel();
            this.taskHandlers.remove(uuid);
            try {
                File file = toFile(((DownloadTaskHandler) taskHandler).getFileUri());
                Bundle bundle = new Bundle();
                bundle.putString("resumeData", String.valueOf(file.length()));
                promise.resolve(bundle);
            } catch (Exception e) {
                String message2 = e.getMessage();
                if (message2 != null) {
                    str = FileSystemModuleKt.TAG;
                    Log.e(str, message2);
                }
                promise.reject(e);
            }
        }
    }

    @Override // expo.modules.core.interfaces.ActivityEventListener
    public void onActivityResult(Activity activity, int r4, int r5, Intent intent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (r4 != 5394 || this.dirPermissionsRequest == null) {
            return;
        }
        Bundle bundle = new Bundle();
        if (r5 == -1 && intent != null) {
            Uri data = intent.getData();
            int flags = intent.getFlags() & 3;
            if (data != null) {
                activity.getContentResolver().takePersistableUriPermission(data, flags);
            }
            bundle.putBoolean(PermissionsResponse.GRANTED_KEY, true);
            bundle.putString("directoryUri", String.valueOf(data));
        } else {
            bundle.putBoolean(PermissionsResponse.GRANTED_KEY, false);
        }
        Promise promise = this.dirPermissionsRequest;
        if (promise != null) {
            promise.resolve(bundle);
        }
        getUIManager().unregisterActivityEventListener(this);
        this.dirPermissionsRequest = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileSystemModule.kt */
    @Metadata(m184d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0002\u0018\u00002\u00020\u0001BA\b\u0000\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0016\"\u0004\b\u0017\u0010\u0018R,\u0010\u0002\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006!"}, m183d2 = {"Lexpo/modules/filesystem/FileSystemModule$DownloadResumableTaskParams;", "", "options", "", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "file", "Ljava/io/File;", "isResume", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "(Ljava/util/Map;Lokhttp3/Call;Ljava/io/File;ZLexpo/modules/core/Promise;)V", "getCall", "()Lokhttp3/Call;", "setCall", "(Lokhttp3/Call;)V", "getFile", "()Ljava/io/File;", "setFile", "(Ljava/io/File;)V", "()Z", "setResume", "(Z)V", "getOptions", "()Ljava/util/Map;", "setOptions", "(Ljava/util/Map;)V", "getPromise", "()Lexpo/modules/core/Promise;", "setPromise", "(Lexpo/modules/core/Promise;)V", "expo-file-system_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class DownloadResumableTaskParams {
        private Call call;
        private File file;
        private boolean isResume;
        private Map<String, ? extends Object> options;
        private Promise promise;

        public DownloadResumableTaskParams(Map<String, ? extends Object> map, Call call, File file, boolean z, Promise promise) {
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(promise, "promise");
            this.options = map;
            this.call = call;
            this.file = file;
            this.isResume = z;
            this.promise = promise;
        }

        public final Call getCall() {
            return this.call;
        }

        public final File getFile() {
            return this.file;
        }

        public final Map<String, Object> getOptions() {
            return this.options;
        }

        public final Promise getPromise() {
            return this.promise;
        }

        public final boolean isResume() {
            return this.isResume;
        }

        public final void setCall(Call call) {
            Intrinsics.checkNotNullParameter(call, "<set-?>");
            this.call = call;
        }

        public final void setFile(File file) {
            Intrinsics.checkNotNullParameter(file, "<set-?>");
            this.file = file;
        }

        public final void setOptions(Map<String, ? extends Object> map) {
            this.options = map;
        }

        public final void setPromise(Promise promise) {
            Intrinsics.checkNotNullParameter(promise, "<set-?>");
            this.promise = promise;
        }

        public final void setResume(boolean z) {
            this.isResume = z;
        }
    }

    /* compiled from: FileSystemModule.kt */
    @Metadata(m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0016\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0007\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\b¨\u0006\t"}, m183d2 = {"Lexpo/modules/filesystem/FileSystemModule$DownloadResumableTask;", "Landroid/os/AsyncTask;", "Lexpo/modules/filesystem/FileSystemModule$DownloadResumableTaskParams;", "Ljava/lang/Void;", "(Lexpo/modules/filesystem/FileSystemModule;)V", "doInBackground", OutcomeEventsTable.COLUMN_NAME_PARAMS, "", "([Lexpo/modules/filesystem/FileSystemModule$DownloadResumableTaskParams;)Ljava/lang/Void;", "expo-file-system_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    private final class DownloadResumableTask extends AsyncTask<DownloadResumableTaskParams, Void, Void> {
        final /* synthetic */ FileSystemModule this$0;

        public DownloadResumableTask(FileSystemModule this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(DownloadResumableTaskParams... params) {
            String str;
            Intrinsics.checkNotNullParameter(params, "params");
            boolean z = false;
            DownloadResumableTaskParams downloadResumableTaskParams = params[0];
            Call call = downloadResumableTaskParams == null ? null : downloadResumableTaskParams.getCall();
            DownloadResumableTaskParams downloadResumableTaskParams2 = params[0];
            Promise promise = downloadResumableTaskParams2 == null ? null : downloadResumableTaskParams2.getPromise();
            DownloadResumableTaskParams downloadResumableTaskParams3 = params[0];
            File file = downloadResumableTaskParams3 == null ? null : downloadResumableTaskParams3.getFile();
            DownloadResumableTaskParams downloadResumableTaskParams4 = params[0];
            Boolean valueOf = downloadResumableTaskParams4 == null ? null : Boolean.valueOf(downloadResumableTaskParams4.isResume());
            DownloadResumableTaskParams downloadResumableTaskParams5 = params[0];
            Map<String, Object> options = downloadResumableTaskParams5 == null ? null : downloadResumableTaskParams5.getOptions();
            try {
                Intrinsics.checkNotNull(call);
                C5015Response execute = call.execute();
                ResponseBody body = execute.body();
                Intrinsics.checkNotNull(body);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(body.byteStream());
                FileOutputStream fileOutputStream = new FileOutputStream(file, Intrinsics.areEqual((Object) valueOf, (Object) true));
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                Bundle bundle = new Bundle();
                FileSystemModule fileSystemModule = this.this$0;
                bundle.putString("uri", Uri.fromFile(file).toString());
                bundle.putInt("status", execute.code());
                bundle.putBundle("headers", fileSystemModule.translateHeaders(execute.headers()));
                Object obj = options == null ? null : options.get("md5");
                if (!Intrinsics.areEqual(obj, (Object) true)) {
                    obj = null;
                }
                if (obj != null) {
                    bundle.putString("md5", file == null ? null : fileSystemModule.md5(file));
                }
                execute.close();
                if (promise != null) {
                    promise.resolve(bundle);
                }
                Void r13 = null;
            } catch (Exception e) {
                if (call != null && call.isCanceled()) {
                    z = true;
                }
                if (z) {
                    if (promise != null) {
                        promise.resolve(null);
                    }
                    return null;
                }
                String message = e.getMessage();
                if (message != null) {
                    str = FileSystemModuleKt.TAG;
                    Log.e(str, message);
                }
                if (promise != null) {
                    promise.reject(e);
                }
            }
            return null;
        }
    }

    /* compiled from: FileSystemModule.kt */
    @Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0012\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/filesystem/FileSystemModule$TaskHandler;", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "(Lokhttp3/Call;)V", "getCall", "()Lokhttp3/Call;", "expo-file-system_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    private static class TaskHandler {
        private final Call call;

        public TaskHandler(Call call) {
            Intrinsics.checkNotNullParameter(call, "call");
            this.call = call;
        }

        public final Call getCall() {
            return this.call;
        }
    }

    /* compiled from: FileSystemModule.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, m183d2 = {"Lexpo/modules/filesystem/FileSystemModule$DownloadTaskHandler;", "Lexpo/modules/filesystem/FileSystemModule$TaskHandler;", "fileUri", "Landroid/net/Uri;", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "(Landroid/net/Uri;Lokhttp3/Call;)V", "getFileUri", "()Landroid/net/Uri;", "expo-file-system_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    private static final class DownloadTaskHandler extends TaskHandler {
        private final Uri fileUri;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadTaskHandler(Uri fileUri, Call call) {
            super(call);
            Intrinsics.checkNotNullParameter(fileUri, "fileUri");
            Intrinsics.checkNotNullParameter(call, "call");
            this.fileUri = fileUri;
        }

        public final Uri getFileUri() {
            return this.fileUri;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileSystemModule.kt */
    @Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m183d2 = {"Lexpo/modules/filesystem/FileSystemModule$ProgressResponseBody;", "Lokhttp3/ResponseBody;", "responseBody", "progressListener", "Lexpo/modules/filesystem/FileSystemModule$ProgressListener;", "(Lokhttp3/ResponseBody;Lexpo/modules/filesystem/FileSystemModule$ProgressListener;)V", "bufferedSource", "Lokio/BufferedSource;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "source", "Lokio/Source;", "expo-file-system_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class ProgressResponseBody extends ResponseBody {
        private BufferedSource bufferedSource;
        private final ProgressListener progressListener;
        private final ResponseBody responseBody;

        public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            Intrinsics.checkNotNullParameter(progressListener, "progressListener");
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            ResponseBody responseBody = this.responseBody;
            if (responseBody == null) {
                return null;
            }
            return responseBody.contentType();
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            ResponseBody responseBody = this.responseBody;
            if (responseBody == null) {
                return -1L;
            }
            return responseBody.contentLength();
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            BufferedSource bufferedSource = this.bufferedSource;
            if (bufferedSource == null) {
                ResponseBody responseBody = this.responseBody;
                Intrinsics.checkNotNull(responseBody);
                return Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private final Source source(final Source source) {
            return new ForwardingSource(this) { // from class: expo.modules.filesystem.FileSystemModule$ProgressResponseBody$source$1
                final /* synthetic */ FileSystemModule.ProgressResponseBody this$0;
                private long totalBytesRead;

                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(Source.this);
                    this.this$0 = this;
                }

                public final long getTotalBytesRead() {
                    return this.totalBytesRead;
                }

                public final void setTotalBytesRead(long j) {
                    this.totalBytesRead = j;
                }

                @Override // okio.ForwardingSource, okio.Source
                public long read(Buffer sink, long j) throws IOException {
                    FileSystemModule.ProgressListener progressListener;
                    ResponseBody responseBody;
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    long read = super.read(sink, j);
                    int r13 = (read > (-1L) ? 1 : (read == (-1L) ? 0 : -1));
                    this.totalBytesRead += r13 != 0 ? read : 0L;
                    progressListener = this.this$0.progressListener;
                    long j2 = this.totalBytesRead;
                    responseBody = this.this$0.responseBody;
                    progressListener.update(j2, responseBody != null ? responseBody.contentLength() : -1L, r13 == 0);
                    return read;
                }
            };
        }
    }

    private final synchronized OkHttpClient getOkHttpClient() {
        if (this.client == null) {
            OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS);
            final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
            writeTimeout.cookieJar(new JavaNetCookieJar(m1630_get_okHttpClient_$lambda59(LazyKt.lazy(new Functions<CookieHandler>() { // from class: expo.modules.filesystem.FileSystemModule$special$$inlined$moduleRegistry$2
                {
                    super(0);
                }

                /* JADX WARN: Type inference failed for: r0v2, types: [java.net.CookieHandler, java.lang.Object] */
                @Override // kotlin.jvm.functions.Functions
                public final CookieHandler invoke() {
                    ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                    Intrinsics.checkNotNull(moduleRegistry);
                    return moduleRegistry.getModule(CookieHandler.class);
                }
            }))));
            this.client = writeTimeout.build();
        }
        return this.client;
    }

    /* renamed from: _get_okHttpClient_$lambda-59  reason: not valid java name */
    private static final CookieHandler m1630_get_okHttpClient_$lambda59(Lazy<? extends CookieHandler> lazy) {
        CookieHandler value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "_get_okHttpClient_$lambda-59(...)");
        return value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String md5(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            char[] encodeHex = Hex.encodeHex(DigestUtils.md5(fileInputStream));
            Intrinsics.checkNotNullExpressionValue(encodeHex, "encodeHex(md5bytes)");
            String str = new String(encodeHex);
            Closeable.closeFinally(fileInputStream, null);
            return str;
        } finally {
        }
    }

    private final void ensureDirExists(File file) throws IOException {
        if (file.isDirectory() || file.mkdirs()) {
            return;
        }
        throw new IOException("Couldn't create directory '" + file + "'");
    }

    private final void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("Failed to list contents of " + file);
            }
            IOException e = null;
            int r2 = 0;
            int length = listFiles.length;
            while (r2 < length) {
                File f = listFiles[r2];
                r2++;
                try {
                    Intrinsics.checkNotNullExpressionValue(f, "f");
                    forceDelete(f);
                } catch (IOException e2) {
                    e = e2;
                }
            }
            if (e != null) {
                throw e;
            }
            if (file.delete()) {
                return;
            }
            throw new IOException("Unable to delete directory " + file + ".");
        } else if (file.delete()) {
        } else {
            throw new IOException("Unable to delete file: " + file);
        }
    }

    private final long getFileSize(File file) {
        Object obj;
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0L;
        }
        ArrayList arrayList = new ArrayList(listFiles.length);
        int r3 = 0;
        int length = listFiles.length;
        while (r3 < length) {
            File it = listFiles[r3];
            r3++;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            arrayList.add(Long.valueOf(getFileSize(it)));
        }
        Iterator it2 = arrayList.iterator();
        if (it2.hasNext()) {
            Object next = it2.next();
            while (it2.hasNext()) {
                next = Long.valueOf(((Number) next).longValue() + ((Number) it2.next()).longValue());
            }
            obj = next;
        } else {
            obj = null;
        }
        Long l = (Long) obj;
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    private final String getEncodingFromOptions(Map<String, ? extends Object> map) {
        if (map.containsKey("encoding") && (map.get("encoding") instanceof String)) {
            Object obj = map.get("encoding");
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
            Locale ROOT = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
            String lowerCase = ((String) obj).toLowerCase(ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            return lowerCase;
        }
        return RNFetchBlobConst.RNFB_RESPONSE_UTF8;
    }

    private final InputStream getInputStream(Uri uri) throws IOException {
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            return new FileInputStream(toFile(uri));
        }
        if (Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
            return openAssetInputStream(uri);
        }
        if (isSAFUri(uri)) {
            InputStream openInputStream = getContext().getContentResolver().openInputStream(uri);
            Intrinsics.checkNotNull(openInputStream);
            Intrinsics.checkNotNullExpressionValue(openInputStream, "context.contentResolver.openInputStream(uri)!!");
            return openInputStream;
        }
        throw new IOException("Unsupported scheme for location '" + uri + "'.");
    }

    private final OutputStream getOutputStream(Uri uri) throws IOException {
        FileOutputStream openOutputStream;
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            openOutputStream = new FileOutputStream(toFile(uri));
        } else if (!isSAFUri(uri)) {
            throw new IOException("Unsupported scheme for location '" + uri + "'.");
        } else {
            openOutputStream = getContext().getContentResolver().openOutputStream(uri);
            Intrinsics.checkNotNull(openOutputStream);
        }
        Intrinsics.checkNotNullExpressionValue(openOutputStream, "when {\n    uri.scheme ==…or location '$uri'.\")\n  }");
        return openOutputStream;
    }

    private final DocumentFile getNearestSAFFile(Uri uri) {
        DocumentFile fromSingleUri = DocumentFile.fromSingleUri(getContext(), uri);
        return (fromSingleUri == null || !fromSingleUri.isFile()) ? DocumentFile.fromTreeUri(getContext(), uri) : fromSingleUri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File toFile(Uri uri) {
        return new File(uri.getPath());
    }

    private final boolean isSAFUri(Uri uri) {
        if (Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME)) {
            String host = uri.getHost();
            return host == null ? false : StringsKt.startsWith$default(host, "com.android.externalstorage", false, 2, (Object) null);
        }
        return false;
    }

    private final String parseFileUri(String str) {
        String substring = str.substring(StringsKt.indexOf$default((CharSequence) str, ':', 0, false, 6, (Object) null) + 3);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        return substring;
    }

    private final byte[] getInputStreamBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                }
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "byteBuffer.toByteArray()");
        return byteArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bundle translateHeaders(Headers headers) {
        Bundle bundle = new Bundle();
        int size = headers.size();
        int r2 = 0;
        while (r2 < size) {
            int r3 = r2 + 1;
            String name = headers.name(r2);
            if (bundle.get(name) != null) {
                String string = bundle.getString(name);
                String value = headers.value(r2);
                bundle.putString(name, string + ", " + value);
            } else {
                bundle.putString(name, headers.value(r2));
            }
            r2 = r3;
        }
        return bundle;
    }

    private final EnumSet<Permission> permissionsForPath(String str) {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        return m1632permissionsForPath$lambda0(LazyKt.lazy(new Functions<FilePermissionModuleInterface>() { // from class: expo.modules.filesystem.FileSystemModule$permissionsForPath$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.interfaces.filesystem.FilePermissionModuleInterface, java.lang.Object] */
            @Override // kotlin.jvm.functions.Functions
            public final FilePermissionModuleInterface invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(FilePermissionModuleInterface.class);
            }
        })).getPathPermissions(getContext(), str);
    }

    private final Uri contentUriFromFile(File file) {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Application application = m1631contentUriFromFile$lambda27(LazyKt.lazy(new Functions<ActivityProvider>() { // from class: expo.modules.filesystem.FileSystemModule$contentUriFromFile$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, expo.modules.core.interfaces.ActivityProvider] */
            @Override // kotlin.jvm.functions.Functions
            public final ActivityProvider invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(ActivityProvider.class);
            }
        })).getCurrentActivity().getApplication();
        Application application2 = application;
        String packageName = application.getPackageName();
        Uri uriForFile = FileProvider.getUriForFile(application2, packageName + ".FileSystemFileProvider", file);
        Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(applicatio…ystemFileProvider\", file)");
        return uriForFile;
    }
}
