package com.rnfs;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.SparseArray;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.rnfs.DownloadParams;
import com.rnfs.UploadParams;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

@ReactModule(name = RNFSManager.MODULE_NAME)
/* loaded from: classes3.dex */
public class RNFSManager extends ReactContextBaseJavaModule {
    static final String MODULE_NAME = "RNFSManager";
    private static final String RNFSCachesDirectoryPath = "RNFSCachesDirectoryPath";
    private static final String RNFSDocumentDirectory = "RNFSDocumentDirectory";
    private static final String RNFSDocumentDirectoryPath = "RNFSDocumentDirectoryPath";
    private static final String RNFSDownloadDirectoryPath = "RNFSDownloadDirectoryPath";
    private static final String RNFSExternalCachesDirectoryPath = "RNFSExternalCachesDirectoryPath";
    private static final String RNFSExternalDirectoryPath = "RNFSExternalDirectoryPath";
    private static final String RNFSExternalStorageDirectoryPath = "RNFSExternalStorageDirectoryPath";
    private static final String RNFSFileTypeDirectory = "RNFSFileTypeDirectory";
    private static final String RNFSFileTypeRegular = "RNFSFileTypeRegular";
    private static final String RNFSPicturesDirectoryPath = "RNFSPicturesDirectoryPath";
    private static final String RNFSTemporaryDirectoryPath = "RNFSTemporaryDirectoryPath";
    private SparseArray<Downloader> downloaders;
    private ReactApplicationContext reactContext;
    private SparseArray<Uploader> uploaders;

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void pathForBundle(String str, Promise promise) {
    }

    @ReactMethod
    public void pathForGroup(String str, Promise promise) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public RNFSManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.downloaders = new SparseArray<>();
        this.uploaders = new SparseArray<>();
        this.reactContext = reactApplicationContext;
    }

    private Uri getFileUri(String str, boolean z) throws IORejectionException {
        Uri parse = Uri.parse(str);
        if (parse.getScheme() == null) {
            File file = new File(str);
            if (!z && file.isDirectory()) {
                throw new IORejectionException("EISDIR", "EISDIR: illegal operation on a directory, read '" + str + "'");
            }
            return Uri.parse("file://" + str);
        }
        return parse;
    }

    private String getOriginalFilepath(String str, boolean z) throws IORejectionException {
        Uri fileUri = getFileUri(str, z);
        if (fileUri.getScheme().equals(UriUtil.LOCAL_CONTENT_SCHEME)) {
            try {
                Cursor query = this.reactContext.getContentResolver().query(fileUri, null, null, null, null);
                if (query.moveToFirst()) {
                    str = query.getString(query.getColumnIndexOrThrow("_data"));
                }
                query.close();
            } catch (IllegalArgumentException unused) {
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputStream getInputStream(String str) throws IORejectionException {
        try {
            InputStream openInputStream = this.reactContext.getContentResolver().openInputStream(getFileUri(str, false));
            if (openInputStream != null) {
                return openInputStream;
            }
            throw new IORejectionException("ENOENT", "ENOENT: could not open an input stream for '" + str + "'");
        } catch (FileNotFoundException e) {
            throw new IORejectionException("ENOENT", "ENOENT: " + e.getMessage() + ", open '" + str + "'");
        }
    }

    private String getWriteAccessByAPILevel() {
        return Build.VERSION.SDK_INT <= 28 ? "w" : "rwt";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OutputStream getOutputStream(String str, boolean z) throws IORejectionException {
        try {
            OutputStream openOutputStream = this.reactContext.getContentResolver().openOutputStream(getFileUri(str, false), z ? "wa" : getWriteAccessByAPILevel());
            if (openOutputStream != null) {
                return openOutputStream;
            }
            throw new IORejectionException("ENOENT", "ENOENT: could not open an output stream for '" + str + "'");
        } catch (FileNotFoundException e) {
            throw new IORejectionException("ENOENT", "ENOENT: " + e.getMessage() + ", open '" + str + "'");
        }
    }

    private static byte[] getInputStreamBytes(InputStream inputStream) throws IOException {
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
        return byteArrayOutputStream.toByteArray();
    }

    @ReactMethod
    public void writeFile(String str, String str2, ReadableMap readableMap, Promise promise) {
        try {
            byte[] decode = Base64.decode(str2, 0);
            OutputStream outputStream = getOutputStream(str, false);
            outputStream.write(decode);
            outputStream.close();
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void appendFile(String str, String str2, Promise promise) {
        try {
            byte[] decode = Base64.decode(str2, 0);
            OutputStream outputStream = getOutputStream(str, true);
            outputStream.write(decode);
            outputStream.close();
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void write(String str, String str2, int r6, Promise promise) {
        try {
            byte[] decode = Base64.decode(str2, 0);
            if (r6 < 0) {
                OutputStream outputStream = getOutputStream(str, true);
                outputStream.write(decode);
                outputStream.close();
            } else {
                RandomAccessFile randomAccessFile = new RandomAccessFile(str, "rw");
                randomAccessFile.seek(r6);
                randomAccessFile.write(decode);
                randomAccessFile.close();
            }
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void exists(String str, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(new File(str).exists()));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void readFile(String str, Promise promise) {
        try {
            promise.resolve(Base64.encodeToString(getInputStreamBytes(getInputStream(str)), 2));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void read(String str, int r6, int r7, Promise promise) {
        try {
            InputStream inputStream = getInputStream(str);
            byte[] bArr = new byte[r6];
            inputStream.skip(r7);
            promise.resolve(Base64.encodeToString(bArr, 0, inputStream.read(bArr, 0, r6), 2));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void readFileAssets(String str, Promise promise) {
        InputStream inputStream = null;
        try {
            try {
                inputStream = getReactApplicationContext().getAssets().open(str, 0);
            } catch (Exception e) {
                e.printStackTrace();
                reject(promise, str, e);
                if (0 == 0) {
                    return;
                }
            }
            if (inputStream == null) {
                reject(promise, str, new Exception("Failed to open file"));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                        return;
                    } catch (IOException unused) {
                        return;
                    }
                }
                return;
            }
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            promise.resolve(Base64.encodeToString(bArr, 2));
            if (inputStream == null) {
                return;
            }
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    @ReactMethod
    public void readFileRes(String str, Promise promise) {
        InputStream inputStream = null;
        try {
            try {
                inputStream = getReactApplicationContext().getResources().openRawResource(getResIdentifier(str));
            } catch (Exception e) {
                e.printStackTrace();
                reject(promise, str, e);
                if (0 == 0) {
                    return;
                }
            }
            if (inputStream == null) {
                reject(promise, str, new Exception("Failed to open file"));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                        return;
                    } catch (IOException unused) {
                        return;
                    }
                }
                return;
            }
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            promise.resolve(Base64.encodeToString(bArr, 2));
            if (inputStream == null) {
                return;
            }
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    private int getResIdentifier(String str) {
        boolean z = true;
        String substring = str.substring(str.lastIndexOf(".") + 1);
        String substring2 = str.substring(0, str.lastIndexOf("."));
        if (!substring.equals("png") && !substring.equals("jpg") && !substring.equals("jpeg") && !substring.equals("bmp") && !substring.equals("gif") && !substring.equals("webp") && !substring.equals("psd") && !substring.equals("svg") && !substring.equals("tiff")) {
            z = false;
        }
        return getReactApplicationContext().getResources().getIdentifier(substring2, Boolean.valueOf(z).booleanValue() ? "drawable" : "raw", getReactApplicationContext().getPackageName());
    }

    @ReactMethod
    public void hash(String str, String str2, Promise promise) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("md5", MessageDigestAlgorithms.MD5);
            hashMap.put("sha1", "SHA-1");
            hashMap.put("sha224", McElieceCCA2KeyGenParameterSpec.SHA224);
            hashMap.put("sha256", "SHA-256");
            hashMap.put("sha384", "SHA-384");
            hashMap.put("sha512", "SHA-512");
            if (!hashMap.containsKey(str2)) {
                throw new Exception("Invalid hash algorithm");
            }
            File file = new File(str);
            if (file.isDirectory()) {
                rejectFileIsDirectory(promise);
            } else if (!file.exists()) {
                rejectFileNotFound(promise, str);
            } else {
                MessageDigest messageDigest = MessageDigest.getInstance((String) hashMap.get(str2));
                FileInputStream fileInputStream = new FileInputStream(str);
                byte[] bArr = new byte[10240];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                StringBuilder sb = new StringBuilder();
                byte[] digest = messageDigest.digest();
                int length = digest.length;
                for (int r2 = 0; r2 < length; r2++) {
                    sb.append(String.format("%02x", Byte.valueOf(digest[r2])));
                }
                promise.resolve(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.rnfs.RNFSManager$1] */
    @ReactMethod
    public void moveFile(final String str, String str2, ReadableMap readableMap, final Promise promise) {
        try {
            final File file = new File(str);
            if (!file.renameTo(new File(str2))) {
                new CopyFileTask() { // from class: com.rnfs.RNFSManager.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super();
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(Exception exc) {
                        if (exc == null) {
                            file.delete();
                            promise.resolve(true);
                            return;
                        }
                        exc.printStackTrace();
                        RNFSManager.this.reject(promise, str, exc);
                    }
                }.execute(new String[]{str, str2});
            } else {
                promise.resolve(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.rnfs.RNFSManager$2] */
    @ReactMethod
    public void copyFile(final String str, String str2, ReadableMap readableMap, final Promise promise) {
        new CopyFileTask() { // from class: com.rnfs.RNFSManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Exception exc) {
                if (exc == null) {
                    promise.resolve(null);
                    return;
                }
                exc.printStackTrace();
                RNFSManager.this.reject(promise, str, exc);
            }
        }.execute(new String[]{str, str2});
    }

    /* loaded from: classes3.dex */
    private class CopyFileTask extends AsyncTask<String, Void, Exception> {
        private CopyFileTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Exception doInBackground(String... strArr) {
            try {
                String str = strArr[0];
                String str2 = strArr[1];
                InputStream inputStream = RNFSManager.this.getInputStream(str);
                OutputStream outputStream = RNFSManager.this.getOutputStream(str2, false);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        outputStream.write(bArr, 0, read);
                        Thread.yield();
                    } else {
                        inputStream.close();
                        outputStream.close();
                        return null;
                    }
                }
            } catch (Exception e) {
                return e;
            }
        }
    }

    @ReactMethod
    public void readDir(String str, Promise promise) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                throw new Exception("Folder does not exist");
            }
            File[] listFiles = file.listFiles();
            WritableArray createArray = Arguments.createArray();
            for (File file2 : listFiles) {
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("mtime", file2.lastModified() / 1000.0d);
                createMap.putString("name", file2.getName());
                createMap.putString(RNFetchBlobConst.RNFB_RESPONSE_PATH, file2.getAbsolutePath());
                createMap.putDouble("size", file2.length());
                createMap.putInt(SessionDescription.ATTR_TYPE, file2.isDirectory() ? 1 : 0);
                createArray.pushMap(createMap);
            }
            promise.resolve(createArray);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0066  */
    @com.facebook.react.bridge.ReactMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void readDirAssets(java.lang.String r13, com.facebook.react.bridge.Promise r14) {
        /*
            r12 = this;
            com.facebook.react.bridge.ReactApplicationContext r0 = r12.getReactApplicationContext()     // Catch: java.io.IOException -> L74
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch: java.io.IOException -> L74
            java.lang.String[] r1 = r0.list(r13)     // Catch: java.io.IOException -> L74
            com.facebook.react.bridge.WritableArray r2 = com.facebook.react.bridge.Arguments.createArray()     // Catch: java.io.IOException -> L74
            int r3 = r1.length     // Catch: java.io.IOException -> L74
            r4 = 0
            r5 = 0
        L13:
            if (r5 >= r3) goto L70
            r6 = r1[r5]     // Catch: java.io.IOException -> L74
            com.facebook.react.bridge.WritableMap r7 = com.facebook.react.bridge.Arguments.createMap()     // Catch: java.io.IOException -> L74
            java.lang.String r8 = "name"
            r7.putString(r8, r6)     // Catch: java.io.IOException -> L74
            boolean r8 = r13.isEmpty()     // Catch: java.io.IOException -> L74
            r9 = 1
            if (r8 == 0) goto L28
            goto L35
        L28:
            java.lang.String r8 = "%s/%s"
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch: java.io.IOException -> L74
            r10[r4] = r13     // Catch: java.io.IOException -> L74
            r10[r9] = r6     // Catch: java.io.IOException -> L74
            java.lang.String r6 = java.lang.String.format(r8, r10)     // Catch: java.io.IOException -> L74
        L35:
            java.lang.String r8 = "path"
            r7.putString(r8, r6)     // Catch: java.io.IOException -> L74
            android.content.res.AssetFileDescriptor r6 = r0.openFd(r6)     // Catch: java.io.IOException -> L4f
            if (r6 == 0) goto L4c
            long r10 = r6.getLength()     // Catch: java.io.IOException -> L4f
            int r8 = (int) r10
            r6.close()     // Catch: java.io.IOException -> L4a
            r6 = 0
            goto L5c
        L4a:
            r6 = move-exception
            goto L51
        L4c:
            r6 = 1
            r8 = 0
            goto L5c
        L4f:
            r6 = move-exception
            r8 = 0
        L51:
            java.lang.String r6 = r6.getMessage()     // Catch: java.io.IOException -> L74
            java.lang.String r10 = "compressed"
            boolean r6 = r6.contains(r10)     // Catch: java.io.IOException -> L74
            r6 = r6 ^ r9
        L5c:
            java.lang.String r10 = "size"
            r7.putInt(r10, r8)     // Catch: java.io.IOException -> L74
            java.lang.String r8 = "type"
            if (r6 == 0) goto L66
            goto L67
        L66:
            r9 = 0
        L67:
            r7.putInt(r8, r9)     // Catch: java.io.IOException -> L74
            r2.pushMap(r7)     // Catch: java.io.IOException -> L74
            int r5 = r5 + 1
            goto L13
        L70:
            r14.resolve(r2)     // Catch: java.io.IOException -> L74
            goto L78
        L74:
            r0 = move-exception
            r12.reject(r14, r13, r0)
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.RNFSManager.readDirAssets(java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public void copyFileAssets(String str, String str2, Promise promise) {
        try {
            copyInputStream(getReactApplicationContext().getAssets().open(str), str, str2, promise);
        } catch (IOException unused) {
            reject(promise, str, new Exception(String.format("Asset '%s' could not be opened", str)));
        }
    }

    @ReactMethod
    public void copyFileRes(String str, String str2, Promise promise) {
        try {
            copyInputStream(getReactApplicationContext().getResources().openRawResource(getResIdentifier(str)), str, str2, promise);
        } catch (Exception unused) {
            reject(promise, str, new Exception(String.format("Res '%s' could not be opened", str)));
        }
    }

    @ReactMethod
    public void existsAssets(String str, Promise promise) {
        try {
            AssetManager assets = getReactApplicationContext().getAssets();
            try {
                String[] list = assets.list(str);
                if (list != null && list.length > 0) {
                    promise.resolve(true);
                    return;
                }
            } catch (Exception unused) {
            }
            InputStream inputStream = null;
            try {
                inputStream = assets.open(str);
                promise.resolve(true);
                if (inputStream == null) {
                    return;
                }
            } catch (Exception unused2) {
                promise.resolve(false);
                if (inputStream == null) {
                    return;
                }
            }
            try {
                inputStream.close();
            } catch (Exception unused3) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void existsRes(String str, Promise promise) {
        try {
            if (getResIdentifier(str) > 0) {
                promise.resolve(true);
            } else {
                promise.resolve(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0026: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:18:0x0026 */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void copyInputStream(java.io.InputStream r8, java.lang.String r9, java.lang.String r10, com.facebook.react.bridge.Promise r11) {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            java.io.OutputStream r2 = r7.getOutputStream(r10, r1)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            r3 = 10240(0x2800, float:1.4349E-41)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L28
        La:
            int r4 = r8.read(r3)     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L28
            r5 = -1
            if (r4 == r5) goto L15
            r2.write(r3, r1, r4)     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L28
            goto La
        L15:
            r11.resolve(r0)     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L28
            if (r8 == 0) goto L1f
            r8.close()     // Catch: java.io.IOException -> L1e
            goto L1f
        L1e:
        L1f:
            if (r2 == 0) goto L57
        L21:
            r2.close()     // Catch: java.io.IOException -> L57
            goto L57
        L25:
            r9 = move-exception
            r0 = r2
            goto L58
        L28:
            r0 = move-exception
            goto L30
        L2a:
            r9 = move-exception
            goto L58
        L2c:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
        L30:
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Throwable -> L25
            java.lang.String r4 = "Failed to copy '%s' to %s (%s)"
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L25
            r5[r1] = r9     // Catch: java.lang.Throwable -> L25
            r1 = 1
            r5[r1] = r10     // Catch: java.lang.Throwable -> L25
            r10 = 2
            java.lang.String r0 = r0.getLocalizedMessage()     // Catch: java.lang.Throwable -> L25
            r5[r10] = r0     // Catch: java.lang.Throwable -> L25
            java.lang.String r10 = java.lang.String.format(r4, r5)     // Catch: java.lang.Throwable -> L25
            r3.<init>(r10)     // Catch: java.lang.Throwable -> L25
            r7.reject(r11, r9, r3)     // Catch: java.lang.Throwable -> L25
            if (r8 == 0) goto L54
            r8.close()     // Catch: java.io.IOException -> L53
            goto L54
        L53:
        L54:
            if (r2 == 0) goto L57
            goto L21
        L57:
            return
        L58:
            if (r8 == 0) goto L5f
            r8.close()     // Catch: java.io.IOException -> L5e
            goto L5f
        L5e:
        L5f:
            if (r0 == 0) goto L64
            r0.close()     // Catch: java.io.IOException -> L64
        L64:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.RNFSManager.copyInputStream(java.io.InputStream, java.lang.String, java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public void setReadable(String str, Boolean bool, Boolean bool2, Promise promise) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                throw new Exception("File does not exist");
            }
            file.setReadable(bool.booleanValue(), bool2.booleanValue());
            promise.resolve(true);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void stat(String str, Promise promise) {
        int r0 = 1;
        try {
            String originalFilepath = getOriginalFilepath(str, true);
            File file = new File(originalFilepath);
            if (!file.exists()) {
                throw new Exception("File does not exist");
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("ctime", (int) (file.lastModified() / 1000));
            createMap.putInt("mtime", (int) (file.lastModified() / 1000));
            createMap.putDouble("size", file.length());
            if (!file.isDirectory()) {
                r0 = 0;
            }
            createMap.putInt(SessionDescription.ATTR_TYPE, r0);
            createMap.putString("originalFilepath", originalFilepath);
            promise.resolve(createMap);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void unlink(String str, Promise promise) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                throw new Exception("File does not exist");
            }
            DeleteRecursive(file);
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    private void DeleteRecursive(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                DeleteRecursive(file2);
            }
        }
        file.delete();
    }

    @ReactMethod
    public void mkdir(String str, ReadableMap readableMap, Promise promise) {
        try {
            File file = new File(str);
            file.mkdirs();
            if (!file.exists()) {
                throw new Exception("Directory could not be created");
            }
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(ReactContext reactContext, String str, WritableMap writableMap) {
        ((RCTNativeAppEventEmitter) reactContext.getJSModule(RCTNativeAppEventEmitter.class)).emit(str, writableMap);
    }

    @ReactMethod
    public void downloadFile(final ReadableMap readableMap, final Promise promise) {
        try {
            File file = new File(readableMap.getString("toFile"));
            URL url = new URL(readableMap.getString("fromUrl"));
            final int r3 = readableMap.getInt("jobId");
            ReadableMap map = readableMap.getMap("headers");
            int r5 = readableMap.getInt("progressInterval");
            int r6 = readableMap.getInt("progressDivider");
            int r7 = readableMap.getInt("readTimeout");
            int r8 = readableMap.getInt("connectionTimeout");
            boolean z = readableMap.getBoolean("hasBeginCallback");
            boolean z2 = readableMap.getBoolean("hasProgressCallback");
            DownloadParams downloadParams = new DownloadParams();
            downloadParams.src = url;
            downloadParams.dest = file;
            downloadParams.headers = map;
            downloadParams.progressInterval = r5;
            downloadParams.progressDivider = r6;
            downloadParams.readTimeout = r7;
            downloadParams.connectionTimeout = r8;
            downloadParams.onTaskCompleted = new DownloadParams.OnTaskCompleted() { // from class: com.rnfs.RNFSManager.3
                @Override // com.rnfs.DownloadParams.OnTaskCompleted
                public void onTaskCompleted(DownloadResult downloadResult) {
                    if (downloadResult.exception != null) {
                        RNFSManager.this.reject(promise, readableMap.getString("toFile"), downloadResult.exception);
                        return;
                    }
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("jobId", r3);
                    createMap.putInt("statusCode", downloadResult.statusCode);
                    createMap.putDouble("bytesWritten", downloadResult.bytesWritten);
                    promise.resolve(createMap);
                }
            };
            if (z) {
                downloadParams.onDownloadBegin = new DownloadParams.OnDownloadBegin() { // from class: com.rnfs.RNFSManager.4
                    @Override // com.rnfs.DownloadParams.OnDownloadBegin
                    public void onDownloadBegin(int r4, long j, Map<String, String> map2) {
                        WritableMap createMap = Arguments.createMap();
                        for (Map.Entry<String, String> entry : map2.entrySet()) {
                            createMap.putString(entry.getKey(), entry.getValue());
                        }
                        WritableMap createMap2 = Arguments.createMap();
                        createMap2.putInt("jobId", r3);
                        createMap2.putInt("statusCode", r4);
                        createMap2.putDouble("contentLength", j);
                        createMap2.putMap("headers", createMap);
                        RNFSManager rNFSManager = RNFSManager.this;
                        rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "DownloadBegin", createMap2);
                    }
                };
            }
            if (z2) {
                downloadParams.onDownloadProgress = new DownloadParams.OnDownloadProgress() { // from class: com.rnfs.RNFSManager.5
                    @Override // com.rnfs.DownloadParams.OnDownloadProgress
                    public void onDownloadProgress(long j, long j2) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt("jobId", r3);
                        createMap.putDouble("contentLength", j);
                        createMap.putDouble("bytesWritten", j2);
                        RNFSManager rNFSManager = RNFSManager.this;
                        rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "DownloadProgress", createMap);
                    }
                };
            }
            Downloader downloader = new Downloader();
            downloader.execute(downloadParams);
            this.downloaders.put(r3, downloader);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, readableMap.getString("toFile"), e);
        }
    }

    @ReactMethod
    public void stopDownload(int r2) {
        Downloader downloader = this.downloaders.get(r2);
        if (downloader != null) {
            downloader.stop();
        }
    }

    @ReactMethod
    public void uploadFiles(final ReadableMap readableMap, final Promise promise) {
        String str;
        try {
            ReadableArray array = readableMap.getArray("files");
            URL url = new URL(readableMap.getString("toUrl"));
            final int r6 = readableMap.getInt("jobId");
            ReadableMap map = readableMap.getMap("headers");
            ReadableMap map2 = readableMap.getMap("fields");
            String string = readableMap.getString("method");
            boolean z = readableMap.getBoolean("binaryStreamOnly");
            boolean z2 = readableMap.getBoolean("hasBeginCallback");
            boolean z3 = readableMap.getBoolean("hasProgressCallback");
            ArrayList<ReadableMap> arrayList = new ArrayList<>();
            UploadParams uploadParams = new UploadParams();
            str = "toUrl";
            for (int r15 = 0; r15 < array.size(); r15++) {
                try {
                    arrayList.add(array.getMap(r15));
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    reject(promise, readableMap.getString(str), e);
                    return;
                }
            }
            uploadParams.src = url;
            uploadParams.files = arrayList;
            uploadParams.headers = map;
            uploadParams.method = string;
            uploadParams.fields = map2;
            uploadParams.binaryStreamOnly = z;
            uploadParams.onUploadComplete = new UploadParams.onUploadComplete() { // from class: com.rnfs.RNFSManager.6
                @Override // com.rnfs.UploadParams.onUploadComplete
                public void onUploadComplete(UploadResult uploadResult) {
                    if (uploadResult.exception != null) {
                        RNFSManager.this.reject(promise, readableMap.getString("toUrl"), uploadResult.exception);
                        return;
                    }
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("jobId", r6);
                    createMap.putInt("statusCode", uploadResult.statusCode);
                    createMap.putMap("headers", uploadResult.headers);
                    createMap.putString(TtmlNode.TAG_BODY, uploadResult.body);
                    promise.resolve(createMap);
                }
            };
            if (z2) {
                uploadParams.onUploadBegin = new UploadParams.onUploadBegin() { // from class: com.rnfs.RNFSManager.7
                    @Override // com.rnfs.UploadParams.onUploadBegin
                    public void onUploadBegin() {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt("jobId", r6);
                        RNFSManager rNFSManager = RNFSManager.this;
                        rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "UploadBegin", createMap);
                    }
                };
            }
            if (z3) {
                uploadParams.onUploadProgress = new UploadParams.onUploadProgress() { // from class: com.rnfs.RNFSManager.8
                    @Override // com.rnfs.UploadParams.onUploadProgress
                    public void onUploadProgress(int r4, int r5) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt("jobId", r6);
                        createMap.putInt("totalBytesExpectedToSend", r4);
                        createMap.putInt("totalBytesSent", r5);
                        RNFSManager rNFSManager = RNFSManager.this;
                        rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "UploadProgress", createMap);
                    }
                };
            }
            Uploader uploader = new Uploader();
            uploader.execute(uploadParams);
            this.uploaders.put(r6, uploader);
        } catch (Exception e2) {
            e = e2;
            str = "toUrl";
        }
    }

    @ReactMethod
    public void stopUpload(int r2) {
        Uploader uploader = this.uploaders.get(r2);
        if (uploader != null) {
            uploader.stop();
        }
    }

    @ReactMethod
    public void getFSInfo(Promise promise) {
        long blockCount;
        long availableBlocks;
        long j;
        long j2;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            blockCount = statFs.getTotalBytes();
            availableBlocks = statFs.getFreeBytes();
            j2 = statFs2.getTotalBytes();
            j = statFs2.getFreeBytes();
        } else {
            long blockSize = statFs.getBlockSize();
            blockCount = statFs.getBlockCount() * blockSize;
            availableBlocks = statFs.getAvailableBlocks() * blockSize;
            j = 0;
            j2 = 0;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("totalSpace", blockCount);
        createMap.putDouble("freeSpace", availableBlocks);
        createMap.putDouble("totalSpaceEx", j2);
        createMap.putDouble("freeSpaceEx", j);
        promise.resolve(createMap);
    }

    @ReactMethod
    public void touch(String str, double d, double d2, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(new File(str).setLastModified((long) d)));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void getAllExternalFilesDirs(Promise promise) {
        File[] externalFilesDirs = getReactApplicationContext().getExternalFilesDirs(null);
        WritableArray createArray = Arguments.createArray();
        for (File file : externalFilesDirs) {
            if (file != null) {
                createArray.pushString(file.getAbsolutePath());
            }
        }
        promise.resolve(createArray);
    }

    @ReactMethod
    public void scanFile(String str, final Promise promise) {
        MediaScannerConnection.scanFile(getReactApplicationContext(), new String[]{str}, null, new MediaScannerConnection.MediaScannerConnectionClient() { // from class: com.rnfs.RNFSManager.9
            @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
            public void onMediaScannerConnected() {
            }

            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public void onScanCompleted(String str2, Uri uri) {
                promise.resolve(str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reject(Promise promise, String str, Exception exc) {
        if (exc instanceof FileNotFoundException) {
            rejectFileNotFound(promise, str);
        } else if (exc instanceof IORejectionException) {
            IORejectionException iORejectionException = (IORejectionException) exc;
            promise.reject(iORejectionException.getCode(), iORejectionException.getMessage());
        } else {
            promise.reject((String) null, exc.getMessage());
        }
    }

    private void rejectFileNotFound(Promise promise, String str) {
        promise.reject("ENOENT", "ENOENT: no such file or directory, open '" + str + "'");
    }

    private void rejectFileIsDirectory(Promise promise) {
        promise.reject("EISDIR", "EISDIR: illegal operation on a directory, read");
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put(RNFSDocumentDirectory, 0);
        hashMap.put(RNFSDocumentDirectoryPath, getReactApplicationContext().getFilesDir().getAbsolutePath());
        hashMap.put(RNFSTemporaryDirectoryPath, getReactApplicationContext().getCacheDir().getAbsolutePath());
        hashMap.put(RNFSPicturesDirectoryPath, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        hashMap.put(RNFSCachesDirectoryPath, getReactApplicationContext().getCacheDir().getAbsolutePath());
        hashMap.put(RNFSDownloadDirectoryPath, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        hashMap.put(RNFSFileTypeRegular, 0);
        hashMap.put(RNFSFileTypeDirectory, 1);
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            hashMap.put(RNFSExternalStorageDirectoryPath, externalStorageDirectory.getAbsolutePath());
        } else {
            hashMap.put(RNFSExternalStorageDirectoryPath, null);
        }
        File externalFilesDir = getReactApplicationContext().getExternalFilesDir(null);
        if (externalFilesDir != null) {
            hashMap.put(RNFSExternalDirectoryPath, externalFilesDir.getAbsolutePath());
        } else {
            hashMap.put(RNFSExternalDirectoryPath, null);
        }
        File externalCacheDir = getReactApplicationContext().getExternalCacheDir();
        if (externalCacheDir != null) {
            hashMap.put(RNFSExternalCachesDirectoryPath, externalCacheDir.getAbsolutePath());
        } else {
            hashMap.put(RNFSExternalCachesDirectoryPath, null);
        }
        return hashMap;
    }
}
