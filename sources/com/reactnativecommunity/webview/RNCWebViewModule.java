package com.reactnativecommunity.webview;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.util.Pair;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import expo.modules.imagepicker.MediaTypes;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

@ReactModule(name = RNCWebViewModule.MODULE_NAME)
/* loaded from: classes3.dex */
public class RNCWebViewModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final int FILE_DOWNLOAD_PERMISSION_REQUEST = 1;
    public static final String MODULE_NAME = "RNCWebView";
    private static final int PICKER = 1;
    private static final int PICKER_LEGACY = 3;
    protected static final ShouldOverrideUrlLoadingLock shouldOverrideUrlLoadingLock = new ShouldOverrideUrlLoadingLock();
    private DownloadManager.Request downloadRequest;
    private ValueCallback<Uri[]> filePathCallback;
    private ValueCallback<Uri> filePathCallbackLegacy;
    private File outputImage;
    private File outputVideo;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    /* loaded from: classes3.dex */
    protected static class ShouldOverrideUrlLoadingLock {
        private int nextLockIdentifier = 1;
        private final HashMap<Integer, AtomicReference<ShouldOverrideCallbackState>> shouldOverrideLocks = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: classes3.dex */
        public enum ShouldOverrideCallbackState {
            UNDECIDED,
            SHOULD_OVERRIDE,
            DO_NOT_OVERRIDE
        }

        protected ShouldOverrideUrlLoadingLock() {
        }

        public synchronized Pair<Integer, AtomicReference<ShouldOverrideCallbackState>> getNewLock() {
            int r0;
            AtomicReference<ShouldOverrideCallbackState> atomicReference;
            r0 = this.nextLockIdentifier;
            this.nextLockIdentifier = r0 + 1;
            atomicReference = new AtomicReference<>(ShouldOverrideCallbackState.UNDECIDED);
            this.shouldOverrideLocks.put(Integer.valueOf(r0), atomicReference);
            return new Pair<>(Integer.valueOf(r0), atomicReference);
        }

        public synchronized AtomicReference<ShouldOverrideCallbackState> getLock(Integer num) {
            return this.shouldOverrideLocks.get(num);
        }

        public synchronized void removeLock(Integer num) {
            this.shouldOverrideLocks.remove(num);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum MimeType {
        DEFAULT(MediaTypes.AllMimeType),
        IMAGE("image"),
        VIDEO("video");
        
        private final String value;

        MimeType(String str) {
            this.value = str;
        }
    }

    private PermissionListener getWebviewFileDownloaderPermissionListener(final String str, final String str2) {
        return new PermissionListener() { // from class: com.reactnativecommunity.webview.RNCWebViewModule.1
            @Override // com.facebook.react.modules.core.PermissionListener
            public boolean onRequestPermissionsResult(int r2, String[] strArr, int[] r4) {
                if (r2 != 1) {
                    return false;
                }
                if (r4.length <= 0 || r4[0] != 0) {
                    Toast.makeText(RNCWebViewModule.this.getCurrentActivity().getApplicationContext(), str2, 1).show();
                } else if (RNCWebViewModule.this.downloadRequest != null) {
                    RNCWebViewModule.this.downloadFile(str);
                }
                return true;
            }
        };
    }

    public RNCWebViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
    }

    @ReactMethod
    public void isFileUploadSupported(Promise promise) {
        int r1 = Build.VERSION.SDK_INT;
        Boolean bool = true;
        Boolean bool2 = r1 >= 21 ? bool : false;
        if (r1 < 16 || r1 > 18) {
            bool = bool2;
        }
        promise.resolve(bool);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public void onShouldStartLoadWithRequestCallback(boolean z, int r3) {
        AtomicReference<ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState> lock = shouldOverrideUrlLoadingLock.getLock(Integer.valueOf(r3));
        if (lock != null) {
            synchronized (lock) {
                lock.set(z ? ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.DO_NOT_OVERRIDE : ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.SHOULD_OVERRIDE);
                lock.notify();
            }
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int r9, int r10, Intent intent) {
        if (this.filePathCallback == null && this.filePathCallbackLegacy == null) {
            return;
        }
        File file = this.outputImage;
        boolean z = file != null && file.length() > 0;
        File file2 = this.outputVideo;
        boolean z2 = file2 != null && file2.length() > 0;
        if (r9 != 1) {
            if (r9 == 3) {
                if (r10 != -1) {
                    this.filePathCallbackLegacy.onReceiveValue(null);
                } else if (z) {
                    this.filePathCallbackLegacy.onReceiveValue(getOutputUri(this.outputImage));
                } else if (z2) {
                    this.filePathCallbackLegacy.onReceiveValue(getOutputUri(this.outputVideo));
                } else {
                    this.filePathCallbackLegacy.onReceiveValue(intent.getData());
                }
            }
        } else if (r10 != -1) {
            ValueCallback<Uri[]> valueCallback = this.filePathCallback;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
            }
        } else if (z) {
            this.filePathCallback.onReceiveValue(new Uri[]{getOutputUri(this.outputImage)});
        } else if (z2) {
            this.filePathCallback.onReceiveValue(new Uri[]{getOutputUri(this.outputVideo)});
        } else {
            this.filePathCallback.onReceiveValue(getSelectedFiles(intent, r10));
        }
        File file3 = this.outputImage;
        if (file3 != null && !z) {
            file3.delete();
        }
        File file4 = this.outputVideo;
        if (file4 != null && !z2) {
            file4.delete();
        }
        this.filePathCallback = null;
        this.filePathCallbackLegacy = null;
        this.outputImage = null;
        this.outputVideo = null;
    }

    private Uri[] getSelectedFiles(Intent intent, int r5) {
        if (intent == null) {
            return null;
        }
        if (intent.getClipData() != null) {
            int itemCount = intent.getClipData().getItemCount();
            Uri[] uriArr = new Uri[itemCount];
            for (int r1 = 0; r1 < itemCount; r1++) {
                uriArr[r1] = intent.getClipData().getItemAt(r1).getUri();
            }
            return uriArr;
        } else if (intent.getData() == null || r5 != -1 || Build.VERSION.SDK_INT < 21) {
            return null;
        } else {
            return WebChromeClient.FileChooserParams.parseResult(r5, intent);
        }
    }

    public void startPhotoPickerIntent(ValueCallback<Uri> valueCallback, String str) {
        Intent videoIntent;
        Intent photoIntent;
        this.filePathCallbackLegacy = valueCallback;
        Intent createChooser = Intent.createChooser(getFileChooserIntent(str), "");
        ArrayList arrayList = new ArrayList();
        if (acceptsImages(str).booleanValue() && (photoIntent = getPhotoIntent()) != null) {
            arrayList.add(photoIntent);
        }
        if (acceptsVideo(str).booleanValue() && (videoIntent = getVideoIntent()) != null) {
            arrayList.add(videoIntent);
        }
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (createChooser.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivityForResult(createChooser, 3);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
    }

    public boolean startPhotoPickerIntent(ValueCallback<Uri[]> valueCallback, String[] strArr, boolean z) {
        Intent videoIntent;
        Intent photoIntent;
        this.filePathCallback = valueCallback;
        ArrayList arrayList = new ArrayList();
        if (!needsCameraPermission()) {
            if (acceptsImages(strArr).booleanValue() && (photoIntent = getPhotoIntent()) != null) {
                arrayList.add(photoIntent);
            }
            if (acceptsVideo(strArr).booleanValue() && (videoIntent = getVideoIntent()) != null) {
                arrayList.add(videoIntent);
            }
        }
        Intent fileChooserIntent = getFileChooserIntent(strArr, z);
        Intent intent = new Intent("android.intent.action.CHOOSER");
        intent.putExtra("android.intent.extra.INTENT", fileChooserIntent);
        intent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (intent.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivityForResult(intent, 1);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
        return true;
    }

    public void setDownloadRequest(DownloadManager.Request request) {
        this.downloadRequest = request;
    }

    public void downloadFile(String str) {
        try {
            ((DownloadManager) getCurrentActivity().getBaseContext().getSystemService("download")).enqueue(this.downloadRequest);
            Toast.makeText(getCurrentActivity().getApplicationContext(), str, 1).show();
        } catch (IllegalArgumentException e) {
            Log.w("RNCWebViewModule", "Unsupported URI, aborting download", e);
        }
    }

    public boolean grantFileDownloaderPermissions(String str, String str2) {
        if (Build.VERSION.SDK_INT > 28) {
            return true;
        }
        boolean z = ContextCompat.checkSelfPermission(getCurrentActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        if (!z && Build.VERSION.SDK_INT >= 23) {
            getPermissionAwareActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1, getWebviewFileDownloaderPermissionListener(str, str2));
        }
        return z;
    }

    protected boolean needsCameraPermission() {
        try {
            if (Arrays.asList(getCurrentActivity().getPackageManager().getPackageInfo(getReactApplicationContext().getPackageName(), 4096).requestedPermissions).contains("android.permission.CAMERA")) {
                if (ContextCompat.checkSelfPermission(getCurrentActivity(), "android.permission.CAMERA") != 0) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return true;
        }
    }

    private Intent getPhotoIntent() {
        Intent intent;
        Exception e;
        try {
            File capturedFile = getCapturedFile(MimeType.IMAGE);
            this.outputImage = capturedFile;
            Uri outputUri = getOutputUri(capturedFile);
            intent = new Intent("android.media.action.IMAGE_CAPTURE");
            try {
                intent.putExtra("output", outputUri);
            } catch (IOException e2) {
                e = e2;
                Log.e("CREATE FILE", "Error occurred while creating the File", e);
                e.printStackTrace();
                return intent;
            } catch (IllegalArgumentException e3) {
                e = e3;
                Log.e("CREATE FILE", "Error occurred while creating the File", e);
                e.printStackTrace();
                return intent;
            }
        } catch (IOException | IllegalArgumentException e4) {
            intent = null;
            e = e4;
        }
        return intent;
    }

    private Intent getVideoIntent() {
        Intent intent;
        Exception e;
        try {
            File capturedFile = getCapturedFile(MimeType.VIDEO);
            this.outputVideo = capturedFile;
            Uri outputUri = getOutputUri(capturedFile);
            intent = new Intent("android.media.action.VIDEO_CAPTURE");
            try {
                intent.putExtra("output", outputUri);
            } catch (IOException e2) {
                e = e2;
                Log.e("CREATE FILE", "Error occurred while creating the File", e);
                e.printStackTrace();
                return intent;
            } catch (IllegalArgumentException e3) {
                e = e3;
                Log.e("CREATE FILE", "Error occurred while creating the File", e);
                e.printStackTrace();
                return intent;
            }
        } catch (IOException | IllegalArgumentException e4) {
            intent = null;
            e = e4;
        }
        return intent;
    }

    private Intent getFileChooserIntent(String str) {
        String str2 = str.isEmpty() ? MimeType.DEFAULT.value : str;
        if (str.matches("\\.\\w+")) {
            str2 = getMimeTypeFromExtension(str.replace(".", ""));
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(str2);
        return intent;
    }

    private Intent getFileChooserIntent(String[] strArr, boolean z) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(MimeType.DEFAULT.value);
        intent.putExtra("android.intent.extra.MIME_TYPES", getAcceptedMimeType(strArr));
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", z);
        return intent;
    }

    private Boolean acceptsImages(String str) {
        if (str.matches("\\.\\w+")) {
            str = getMimeTypeFromExtension(str.replace(".", ""));
        }
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains(MimeType.IMAGE.value));
    }

    private Boolean acceptsImages(String[] strArr) {
        String[] acceptedMimeType = getAcceptedMimeType(strArr);
        return Boolean.valueOf(arrayContainsString(acceptedMimeType, MimeType.DEFAULT.value).booleanValue() || arrayContainsString(acceptedMimeType, MimeType.IMAGE.value).booleanValue());
    }

    private Boolean acceptsVideo(String str) {
        boolean z = false;
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        if (str.matches("\\.\\w+")) {
            str = getMimeTypeFromExtension(str.replace(".", ""));
        }
        return Boolean.valueOf((str.isEmpty() || str.toLowerCase().contains(MimeType.VIDEO.value)) ? true : true);
    }

    private Boolean acceptsVideo(String[] strArr) {
        boolean z = false;
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        String[] acceptedMimeType = getAcceptedMimeType(strArr);
        return Boolean.valueOf((arrayContainsString(acceptedMimeType, MimeType.DEFAULT.value).booleanValue() || arrayContainsString(acceptedMimeType, MimeType.VIDEO.value).booleanValue()) ? true : true);
    }

    private Boolean arrayContainsString(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private String[] getAcceptedMimeType(String[] strArr) {
        if (noAcceptTypesSet(strArr).booleanValue()) {
            return new String[]{MimeType.DEFAULT.value};
        }
        String[] strArr2 = new String[strArr.length];
        for (int r1 = 0; r1 < strArr.length; r1++) {
            String str = strArr[r1];
            if (str.matches("\\.\\w+")) {
                String mimeTypeFromExtension = getMimeTypeFromExtension(str.replace(".", ""));
                if (mimeTypeFromExtension != null) {
                    strArr2[r1] = mimeTypeFromExtension;
                } else {
                    strArr2[r1] = str;
                }
            } else {
                strArr2[r1] = str;
            }
        }
        return strArr2;
    }

    private String getMimeTypeFromExtension(String str) {
        if (str != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        }
        return null;
    }

    private Uri getOutputUri(File file) {
        if (Build.VERSION.SDK_INT < 23) {
            return Uri.fromFile(file);
        }
        String packageName = getReactApplicationContext().getPackageName();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        return FileProvider.getUriForFile(reactApplicationContext, packageName + ".fileprovider", file);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.reactnativecommunity.webview.RNCWebViewModule$2 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C40342 {

        /* renamed from: $SwitchMap$com$reactnativecommunity$webview$RNCWebViewModule$MimeType */
        static final /* synthetic */ int[] f1336xcd6a845b;

        static {
            int[] r0 = new int[MimeType.values().length];
            f1336xcd6a845b = r0;
            try {
                r0[MimeType.IMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1336xcd6a845b[MimeType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.io.File getCapturedFile(com.reactnativecommunity.webview.RNCWebViewModule.MimeType r7) throws java.io.IOException {
        /*
            r6 = this;
            int[] r0 = com.reactnativecommunity.webview.RNCWebViewModule.C40342.f1336xcd6a845b
            int r7 = r7.ordinal()
            r7 = r0[r7]
            r0 = 1
            java.lang.String r1 = ""
            if (r7 == r0) goto L1a
            r0 = 2
            if (r7 == r0) goto L13
            r7 = r1
            r0 = r7
            goto L23
        L13:
            java.lang.String r1 = android.os.Environment.DIRECTORY_MOVIES
            java.lang.String r7 = "video-"
            java.lang.String r0 = ".mp4"
            goto L20
        L1a:
            java.lang.String r1 = android.os.Environment.DIRECTORY_PICTURES
            java.lang.String r7 = "image-"
            java.lang.String r0 = ".jpg"
        L20:
            r5 = r1
            r1 = r7
            r7 = r5
        L23:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            long r3 = java.lang.System.currentTimeMillis()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 23
            if (r3 >= r4) goto L4d
            java.io.File r7 = android.os.Environment.getExternalStoragePublicDirectory(r7)
            java.io.File r0 = new java.io.File
            r0.<init>(r7, r2)
            goto L5a
        L4d:
            com.facebook.react.bridge.ReactApplicationContext r7 = r6.getReactApplicationContext()
            r2 = 0
            java.io.File r7 = r7.getExternalFilesDir(r2)
            java.io.File r0 = java.io.File.createTempFile(r1, r0, r7)
        L5a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.webview.RNCWebViewModule.getCapturedFile(com.reactnativecommunity.webview.RNCWebViewModule$MimeType):java.io.File");
    }

    private Boolean noAcceptTypesSet(String[] strArr) {
        boolean z = true;
        if (strArr.length != 0 && (strArr.length != 1 || strArr[0] == null || strArr[0].length() != 0)) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private PermissionAwareActivity getPermissionAwareActivity() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        }
        if (!(currentActivity instanceof PermissionAwareActivity)) {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
        return (PermissionAwareActivity) currentActivity;
    }
}
