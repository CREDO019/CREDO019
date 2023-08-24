package com.RNFetchBlob;

import android.content.res.AssetFileDescriptor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.RNFetchBlob.Utils.PathResolver;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class RNFetchBlobFS {
    private static HashMap<String, RNFetchBlobFS> fileStreams = new HashMap<>();
    private DeviceEventManagerModule.RCTDeviceEventEmitter emitter;
    private ReactApplicationContext mCtx;
    private String encoding = RNFetchBlobConst.RNFB_RESPONSE_BASE64;
    private OutputStream writeStreamInstance = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RNFetchBlobFS(ReactApplicationContext reactApplicationContext) {
        this.mCtx = reactApplicationContext;
        this.emitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeFile(String str, String str2, String str3, boolean z, Promise promise) {
        int length;
        FileOutputStream fileOutputStream;
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    promise.reject("EUNSPECIFIED", "Failed to create parent directory of '" + str + "'");
                    return;
                } else if (!file.createNewFile()) {
                    promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created");
                    return;
                }
            }
            if (str2.equalsIgnoreCase("uri")) {
                String normalizePath = normalizePath(str3);
                File file2 = new File(normalizePath);
                if (!file2.exists()) {
                    promise.reject("ENOENT", "No such file '" + str + "' ('" + normalizePath + "')");
                    return;
                }
                byte[] bArr = new byte[10240];
                FileInputStream fileInputStream = null;
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(file2);
                    try {
                        fileOutputStream = new FileOutputStream(file, z);
                        length = 0;
                        while (true) {
                            try {
                                int read = fileInputStream2.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                                length += read;
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                throw th;
                            }
                        }
                        fileInputStream2.close();
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            } else {
                byte[] stringToBytes = stringToBytes(str3, str2);
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, z);
                try {
                    fileOutputStream2.write(stringToBytes);
                    length = stringToBytes.length;
                } finally {
                    fileOutputStream2.close();
                }
            }
            promise.resolve(Integer.valueOf(length));
        } catch (FileNotFoundException unused) {
            promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created, or it is a directory");
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeFile(String str, ReadableArray readableArray, boolean z, Promise promise) {
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    promise.reject("ENOTDIR", "Failed to create parent directory of '" + str + "'");
                    return;
                } else if (!file.createNewFile()) {
                    promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created");
                    return;
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, z);
            byte[] bArr = new byte[readableArray.size()];
            for (int r3 = 0; r3 < readableArray.size(); r3++) {
                bArr[r3] = (byte) readableArray.getInt(r3);
            }
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            promise.resolve(Integer.valueOf(readableArray.size()));
        } catch (FileNotFoundException unused) {
            promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created");
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00cd, code lost:
        if (r0 == 1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00cf, code lost:
        if (r0 == 2) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d1, code lost:
        r10.resolve(new java.lang.String(r4));
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00db, code lost:
        r10.resolve(new java.lang.String(r4));
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e4, code lost:
        r9 = com.facebook.react.bridge.Arguments.createArray();
        r0 = r4.length;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e9, code lost:
        if (r3 >= r0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00eb, code lost:
        r9.pushInt(r4[r3]);
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00f3, code lost:
        r10.resolve(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:?, code lost:
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0076 A[Catch: Exception -> 0x00ff, FileNotFoundException -> 0x0108, TryCatch #2 {FileNotFoundException -> 0x0108, Exception -> 0x00ff, blocks: (B:7:0x000e, B:9:0x0014, B:14:0x0076, B:16:0x0093, B:35:0x00d1, B:36:0x00db, B:37:0x00e4, B:39:0x00eb, B:40:0x00f3, B:41:0x00f7, B:23:0x00ae, B:26:0x00b8, B:29:0x00c2, B:11:0x003f, B:12:0x005b), top: B:51:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0093 A[Catch: Exception -> 0x00ff, FileNotFoundException -> 0x0108, TryCatch #2 {FileNotFoundException -> 0x0108, Exception -> 0x00ff, blocks: (B:7:0x000e, B:9:0x0014, B:14:0x0076, B:16:0x0093, B:35:0x00d1, B:36:0x00db, B:37:0x00e4, B:39:0x00eb, B:40:0x00f3, B:41:0x00f7, B:23:0x00ae, B:26:0x00b8, B:29:0x00c2, B:11:0x003f, B:12:0x005b), top: B:51:0x000e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void readFile(java.lang.String r8, java.lang.String r9, com.facebook.react.bridge.Promise r10) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.RNFetchBlobFS.readFile(java.lang.String, java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> getSystemfolders(ReactApplicationContext reactApplicationContext) {
        HashMap hashMap = new HashMap();
        hashMap.put("DocumentDir", reactApplicationContext.getFilesDir().getAbsolutePath());
        hashMap.put("CacheDir", reactApplicationContext.getCacheDir().getAbsolutePath());
        hashMap.put("DCIMDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
        hashMap.put("PictureDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        hashMap.put("MusicDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
        hashMap.put("DownloadDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        hashMap.put("MovieDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath());
        hashMap.put("RingtoneDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath());
        if (Environment.getExternalStorageState().equals("mounted")) {
            hashMap.put("SDCardDir", Environment.getExternalStorageDirectory().getAbsolutePath());
            File externalFilesDir = reactApplicationContext.getExternalFilesDir(null);
            if (externalFilesDir != null) {
                hashMap.put("SDCardApplicationDir", externalFilesDir.getParentFile().getAbsolutePath());
            } else {
                hashMap.put("SDCardApplicationDir", "");
            }
        }
        hashMap.put("MainBundleDir", reactApplicationContext.getApplicationInfo().dataDir);
        return hashMap;
    }

    public static void getSDCardDir(Promise promise) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            promise.resolve(Environment.getExternalStorageDirectory().getAbsolutePath());
        } else {
            promise.reject("RNFetchBlob.getSDCardDir", "External storage not mounted");
        }
    }

    public static void getSDCardApplicationDir(ReactApplicationContext reactApplicationContext, Promise promise) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                promise.resolve(reactApplicationContext.getExternalFilesDir(null).getParentFile().getAbsolutePath());
                return;
            } catch (Exception e) {
                promise.reject("RNFetchBlob.getSDCardApplicationDir", e.getLocalizedMessage());
                return;
            }
        }
        promise.reject("RNFetchBlob.getSDCardApplicationDir", "External storage not mounted");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getTmpPath(String str) {
        return C0908RNFetchBlob.RCTContext.getFilesDir() + "/RNFetchBlobTmp_" + str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:54:0x010e A[Catch: Exception -> 0x0117, FileNotFoundException -> 0x0137, TryCatch #2 {FileNotFoundException -> 0x0137, Exception -> 0x0117, blocks: (B:6:0x0018, B:14:0x002b, B:16:0x0031, B:20:0x005b, B:23:0x0069, B:24:0x0073, B:26:0x0079, B:28:0x008e, B:54:0x010e, B:55:0x0113, B:30:0x0094, B:32:0x009c, B:34:0x00a2, B:36:0x00a9, B:37:0x00b1, B:39:0x00b6, B:41:0x00bd, B:43:0x00c3, B:47:0x00cc, B:50:0x00e4, B:48:0x00da, B:52:0x00f0, B:18:0x0042, B:19:0x0051), top: B:61:0x0018 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void readStream(java.lang.String r17, java.lang.String r18, int r19, int r20, java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.RNFetchBlobFS.readStream(java.lang.String, java.lang.String, int, int, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeStream(String str, String str2, boolean z, Callback callback) {
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    callback.invoke("ENOTDIR", "Failed to create parent directory of '" + str + "'");
                    return;
                } else if (!file.createNewFile()) {
                    callback.invoke("ENOENT", "File '" + str + "' does not exist and could not be created");
                    return;
                }
            } else if (file.isDirectory()) {
                callback.invoke("EISDIR", "Expecting a file but '" + str + "' is a directory");
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str, z);
            this.encoding = str2;
            String str3 = UUID.randomUUID().toString();
            fileStreams.put(str3, this);
            this.writeStreamInstance = fileOutputStream;
            callback.invoke(null, null, str3);
        } catch (Exception e) {
            callback.invoke("EUNSPECIFIED", "Failed to create write stream at path `" + str + "`; " + e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeChunk(String str, String str2, Callback callback) {
        RNFetchBlobFS rNFetchBlobFS = fileStreams.get(str);
        try {
            rNFetchBlobFS.writeStreamInstance.write(stringToBytes(str2, rNFetchBlobFS.encoding));
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeArrayChunk(String str, ReadableArray readableArray, Callback callback) {
        try {
            OutputStream outputStream = fileStreams.get(str).writeStreamInstance;
            byte[] bArr = new byte[readableArray.size()];
            for (int r2 = 0; r2 < readableArray.size(); r2++) {
                bArr[r2] = (byte) readableArray.getInt(r2);
            }
            outputStream.write(bArr);
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void closeStream(String str, Callback callback) {
        try {
            OutputStream outputStream = fileStreams.get(str).writeStreamInstance;
            fileStreams.remove(str);
            outputStream.close();
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void unlink(String str, Callback callback) {
        try {
            deleteRecursive(new File(normalizePath(str)));
            callback.invoke(null, true);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage(), false);
        }
    }

    private static void deleteRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new NullPointerException("Received null trying to list files of directory '" + file + "'");
            }
            for (File file2 : listFiles) {
                deleteRecursive(file2);
            }
        }
        if (file.delete()) {
            return;
        }
        throw new IOException("Failed to delete '" + file + "'");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void mkdir(String str, Promise promise) {
        File file = new File(str);
        if (file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append(file.isDirectory() ? "Folder" : "File");
            sb.append(" '");
            sb.append(str);
            sb.append("' already exists");
            promise.reject("EEXIST", sb.toString());
            return;
        }
        try {
            if (!file.mkdirs()) {
                promise.reject("EUNSPECIFIED", "mkdir failed to create some or all directories in '" + str + "'");
                return;
            }
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00fc A[Catch: Exception -> 0x00f8, TRY_LEAVE, TryCatch #4 {Exception -> 0x00f8, blocks: (B:57:0x00f4, B:61:0x00fc), top: B:69:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: cp */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m1377cp(java.lang.String r6, java.lang.String r7, com.facebook.react.bridge.Callback r8) {
        /*
            Method dump skipped, instructions count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.RNFetchBlobFS.m1377cp(java.lang.String, java.lang.String, com.facebook.react.bridge.Callback):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mv */
    public static void m1374mv(String str, String str2, Callback callback) {
        File file = new File(str);
        if (!file.exists()) {
            callback.invoke("Source file at path `" + str + "` does not exist");
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    file.delete();
                    callback.invoke(new Object[0]);
                    return;
                }
            }
        } catch (FileNotFoundException unused) {
            callback.invoke("Source file not found.");
        } catch (Exception e) {
            callback.invoke(e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void exists(String str, Callback callback) {
        if (isAsset(str)) {
            try {
                C0908RNFetchBlob.RCTContext.getAssets().openFd(str.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, ""));
                callback.invoke(true, false);
                return;
            } catch (IOException unused) {
                callback.invoke(false, false);
                return;
            }
        }
        String normalizePath = normalizePath(str);
        if (normalizePath != null) {
            callback.invoke(Boolean.valueOf(new File(normalizePath).exists()), Boolean.valueOf(new File(normalizePath).isDirectory()));
        } else {
            callback.invoke(false, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: ls */
    public static void m1375ls(String str, Promise promise) {
        try {
            String normalizePath = normalizePath(str);
            File file = new File(normalizePath);
            if (!file.exists()) {
                promise.reject("ENOENT", "No such file '" + normalizePath + "'");
            } else if (file.isDirectory()) {
                String[] list = new File(normalizePath).list();
                WritableArray createArray = Arguments.createArray();
                for (String str2 : list) {
                    createArray.pushString(str2);
                }
                promise.resolve(createArray);
            } else {
                promise.reject("ENOTDIR", "Not a directory '" + normalizePath + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void slice(String str, String str2, int r8, int r9, String str3, Promise promise) {
        try {
            String normalizePath = normalizePath(str);
            File file = new File(normalizePath);
            if (file.isDirectory()) {
                promise.reject("EISDIR", "Expecting a file but '" + normalizePath + "' is a directory");
            } else if (!file.exists()) {
                promise.reject("ENOENT", "No such file '" + normalizePath + "'");
            } else {
                int length = (int) file.length();
                int min = Math.min(length, r9) - r8;
                FileInputStream fileInputStream = new FileInputStream(new File(normalizePath));
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
                int skip = (int) fileInputStream.skip(r8);
                if (skip != r8) {
                    promise.reject("EUNSPECIFIED", "Skipped " + skip + " instead of the specified " + r8 + " bytes, size is " + length);
                    return;
                }
                byte[] bArr = new byte[10240];
                int r3 = 0;
                while (r3 < min) {
                    int read = fileInputStream.read(bArr, 0, 10240);
                    int r5 = min - r3;
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, Math.min(r5, read));
                    r3 += read;
                }
                fileInputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                promise.resolve(str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.RNFetchBlob.RNFetchBlobFS$1] */
    public static void lstat(String str, final Callback callback) {
        new AsyncTask<String, Integer, Integer>() { // from class: com.RNFetchBlob.RNFetchBlobFS.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Integer doInBackground(String... strArr) {
                String[] list;
                WritableArray createArray = Arguments.createArray();
                if (strArr[0] == null) {
                    Callback.this.invoke("the path specified for lstat is either `null` or `undefined`.");
                    return 0;
                }
                File file = new File(strArr[0]);
                if (!file.exists()) {
                    Callback.this.invoke("failed to lstat path `" + strArr[0] + "` because it does not exist or it is not a folder");
                    return 0;
                }
                if (file.isDirectory()) {
                    for (String str2 : file.list()) {
                        createArray.pushMap(RNFetchBlobFS.statFile(file.getPath() + "/" + str2));
                    }
                } else {
                    createArray.pushMap(RNFetchBlobFS.statFile(file.getAbsolutePath()));
                }
                Callback.this.invoke(null, createArray);
                return 0;
            }
        }.execute(normalizePath(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void stat(String str, Callback callback) {
        try {
            String normalizePath = normalizePath(str);
            WritableMap statFile = statFile(normalizePath);
            if (statFile == null) {
                callback.invoke("failed to stat path `" + normalizePath + "` because it does not exist or it is not a folder", null);
            } else {
                callback.invoke(null, statFile);
            }
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WritableMap statFile(String str) {
        try {
            String normalizePath = normalizePath(str);
            WritableMap createMap = Arguments.createMap();
            if (isAsset(normalizePath)) {
                String replace = normalizePath.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, "");
                AssetFileDescriptor openFd = C0908RNFetchBlob.RCTContext.getAssets().openFd(replace);
                createMap.putString("filename", replace);
                createMap.putString(RNFetchBlobConst.RNFB_RESPONSE_PATH, normalizePath);
                createMap.putString(SessionDescription.ATTR_TYPE, UriUtil.LOCAL_ASSET_SCHEME);
                createMap.putString("size", String.valueOf(openFd.getLength()));
                createMap.putInt("lastModified", 0);
            } else {
                File file = new File(normalizePath);
                if (!file.exists()) {
                    return null;
                }
                createMap.putString("filename", file.getName());
                createMap.putString(RNFetchBlobConst.RNFB_RESPONSE_PATH, file.getPath());
                createMap.putString(SessionDescription.ATTR_TYPE, file.isDirectory() ? "directory" : "file");
                createMap.putString("size", String.valueOf(file.length()));
                createMap.putString("lastModified", String.valueOf(file.lastModified()));
            }
            return createMap;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scanFile(String[] strArr, String[] strArr2, final Callback callback) {
        try {
            MediaScannerConnection.scanFile(this.mCtx, strArr, strArr2, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.RNFetchBlob.RNFetchBlobFS.2
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str, Uri uri) {
                    callback.invoke(null, true);
                }
            });
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage(), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void hash(String str, String str2, Promise promise) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("md5", MessageDigestAlgorithms.MD5);
            hashMap.put("sha1", "SHA-1");
            hashMap.put("sha224", McElieceCCA2KeyGenParameterSpec.SHA224);
            hashMap.put("sha256", "SHA-256");
            hashMap.put("sha384", "SHA-384");
            hashMap.put("sha512", "SHA-512");
            if (!hashMap.containsKey(str2)) {
                promise.reject("EINVAL", "Invalid algorithm '" + str2 + "', must be one of md5, sha1, sha224, sha256, sha384, sha512");
                return;
            }
            File file = new File(str);
            if (file.isDirectory()) {
                promise.reject("EISDIR", "Expecting a file but '" + str + "' is a directory");
            } else if (file.exists()) {
                MessageDigest messageDigest = MessageDigest.getInstance((String) hashMap.get(str2));
                FileInputStream fileInputStream = new FileInputStream(str);
                byte[] bArr = new byte[1048576];
                if (file.length() != 0) {
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read);
                    }
                }
                StringBuilder sb = new StringBuilder();
                byte[] digest = messageDigest.digest();
                int length = digest.length;
                for (int r1 = 0; r1 < length; r1++) {
                    sb.append(String.format("%02x", Byte.valueOf(digest[r1])));
                }
                promise.resolve(sb.toString());
            } else {
                promise.reject("ENOENT", "No such file '" + str + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void createFile(String str, String str2, String str3, Promise promise) {
        try {
            File file = new File(str);
            boolean createNewFile = file.createNewFile();
            if (str3.equals("uri")) {
                File file2 = new File(str2.replace(RNFetchBlobConst.FILE_PREFIX, ""));
                if (!file2.exists()) {
                    promise.reject("ENOENT", "Source file : " + str2 + " does not exist");
                    return;
                }
                FileInputStream fileInputStream = new FileInputStream(file2);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[10240];
                for (int read = fileInputStream.read(bArr); read > 0; read = fileInputStream.read(bArr)) {
                    fileOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
                fileOutputStream.close();
            } else if (!createNewFile) {
                promise.reject("EEXIST", "File `" + str + "` already exists");
                return;
            } else {
                new FileOutputStream(file).write(stringToBytes(str2, str3));
            }
            promise.resolve(str);
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void createFileASCII(String str, ReadableArray readableArray, Promise promise) {
        try {
            File file = new File(str);
            if (!file.createNewFile()) {
                promise.reject("EEXIST", "File at path `" + str + "` already exists");
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[readableArray.size()];
            for (int r2 = 0; r2 < readableArray.size(); r2++) {
                bArr[r2] = (byte) readableArray.getInt(r2);
            }
            fileOutputStream.write(bArr);
            promise.resolve(str);
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: df */
    public static void m1376df(Callback callback) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        WritableMap createMap = Arguments.createMap();
        if (Build.VERSION.SDK_INT >= 18) {
            createMap.putString("internal_free", String.valueOf(statFs.getFreeBytes()));
            createMap.putString("internal_total", String.valueOf(statFs.getTotalBytes()));
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getPath());
            createMap.putString("external_free", String.valueOf(statFs2.getFreeBytes()));
            createMap.putString("external_total", String.valueOf(statFs2.getTotalBytes()));
        }
        callback.invoke(null, createMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeSession(ReadableArray readableArray, final Callback callback) {
        new AsyncTask<ReadableArray, Integer, Integer>() { // from class: com.RNFetchBlob.RNFetchBlobFS.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Integer doInBackground(ReadableArray... readableArrayArr) {
                try {
                    ArrayList arrayList = new ArrayList();
                    for (int r3 = 0; r3 < readableArrayArr[0].size(); r3++) {
                        String string = readableArrayArr[0].getString(r3);
                        File file = new File(string);
                        if (file.exists() && !file.delete()) {
                            arrayList.add(string);
                        }
                    }
                    if (arrayList.isEmpty()) {
                        Callback.this.invoke(null, true);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to delete: ");
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            sb.append((String) it.next());
                            sb.append(", ");
                        }
                        Callback.this.invoke(sb.toString());
                    }
                } catch (Exception e) {
                    Callback.this.invoke(e.getLocalizedMessage());
                }
                return Integer.valueOf(readableArrayArr[0].size());
            }
        }.execute(readableArray);
    }

    private static byte[] stringToBytes(String str, String str2) {
        if (str2.equalsIgnoreCase("ascii")) {
            return str.getBytes(Charset.forName("US-ASCII"));
        }
        if (str2.toLowerCase().contains(RNFetchBlobConst.RNFB_RESPONSE_BASE64)) {
            return Base64.decode(str, 2);
        }
        if (str2.equalsIgnoreCase(RNFetchBlobConst.RNFB_RESPONSE_UTF8)) {
            return str.getBytes(Charset.forName("UTF-8"));
        }
        return str.getBytes(Charset.forName("US-ASCII"));
    }

    private void emitStreamEvent(String str, String str2, String str3) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(NotificationCompat.CATEGORY_EVENT, str2);
        createMap.putString("detail", str3);
        this.emitter.emit(str, createMap);
    }

    private void emitStreamEvent(String str, String str2, WritableArray writableArray) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(NotificationCompat.CATEGORY_EVENT, str2);
        createMap.putArray("detail", writableArray);
        this.emitter.emit(str, createMap);
    }

    private void emitStreamEvent(String str, String str2, String str3, String str4) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(NotificationCompat.CATEGORY_EVENT, str2);
        createMap.putString("code", str3);
        createMap.putString("detail", str4);
        this.emitter.emit(str, createMap);
    }

    private static InputStream inputStreamFromPath(String str) throws IOException {
        if (str.startsWith(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET)) {
            return C0908RNFetchBlob.RCTContext.getAssets().open(str.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, ""));
        }
        return new FileInputStream(new File(str));
    }

    private static boolean isPathExists(String str) {
        if (str.startsWith(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET)) {
            try {
                C0908RNFetchBlob.RCTContext.getAssets().open(str.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, ""));
                return true;
            } catch (IOException unused) {
                return false;
            }
        }
        return new File(str).exists();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAsset(String str) {
        return str != null && str.startsWith(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String normalizePath(String str) {
        if (str == null) {
            return null;
        }
        if (str.matches("\\w+\\:.*")) {
            if (str.startsWith("file://")) {
                return str.replace("file://", "");
            }
            return str.startsWith(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET) ? str : PathResolver.getRealPathFromURI(C0908RNFetchBlob.RCTContext, Uri.parse(str));
        }
        return str;
    }
}
