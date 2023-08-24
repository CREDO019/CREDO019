package cl.json;

import android.app.Application;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import androidx.loader.content.CursorLoader;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.ReactContext;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* loaded from: classes.dex */
public class RNSharePathUtil {
    private static final ArrayList<String> authorities = new ArrayList<>();

    public static void compileAuthorities(ReactContext reactContext) {
        ArrayList<String> arrayList = authorities;
        if (arrayList.size() == 0) {
            Application application = (Application) reactContext.getApplicationContext();
            if (application instanceof ShareApplication) {
                arrayList.add(((ShareApplication) application).getFileProviderAuthority());
            }
            arrayList.add(reactContext.getPackageName() + ".rnshare.fileprovider");
        }
    }

    public static Uri compatUriFromFile(ReactContext reactContext, File file) {
        compileAuthorities(reactContext);
        String authority = Uri.fromFile(file).getAuthority();
        if (!TextUtils.isEmpty(authority) && authorities.contains(authority)) {
            return Uri.fromFile(file);
        }
        if (file.getAbsolutePath().startsWith(RNFetchBlobConst.FILE_PREFIX_CONTENT)) {
            return Uri.fromFile(file);
        }
        Uri uri = null;
        int r1 = 0;
        while (true) {
            ArrayList<String> arrayList = authorities;
            if (r1 >= arrayList.size()) {
                break;
            }
            try {
                uri = FileProvider.getUriForFile(reactContext, arrayList.get(r1), file);
            } catch (Exception e) {
                PrintStream printStream = System.out;
                printStream.println("RNSharePathUtil::compatUriFromFile ERROR " + e.getMessage());
            }
            if (uri != null) {
                break;
            }
            r1++;
        }
        return uri;
    }

    public static String getRealPathFromURI(Context context, Uri uri, Boolean bool) {
        Uri uri2 = null;
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(ParameterizedMessage.ERROR_MSG_SEPARATOR);
                String str = split[0];
                if ("primary".equalsIgnoreCase(str) || SessionDescription.SUPPORTED_SDP_VERSION.equalsIgnoreCase(str)) {
                    File cacheDir = bool.booleanValue() ? context.getCacheDir() : context.getExternalCacheDir();
                    return "" + cacheDir + "/" + split[1];
                } else if ("raw".equalsIgnoreCase(str)) {
                    return "" + split[1];
                } else if (!TextUtils.isEmpty(str)) {
                    return "/storage/" + str + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (documentId.startsWith("raw:")) {
                    return "" + documentId.replaceFirst("raw:", "");
                }
                Uri withAppendedId = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue());
                return "" + getDataColumn(context, withAppendedId, null, null);
            } else if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(ParameterizedMessage.ERROR_MSG_SEPARATOR);
                String str2 = split2[0];
                if ("image".equals(str2)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str2)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str2)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else if ("raw".equalsIgnoreCase(str2)) {
                    return "" + split2[1];
                }
                String[] strArr = {split2[1]};
                return "" + getDataColumn(context, uri2, "_id=?", strArr);
            }
        } else if (UriUtil.LOCAL_CONTENT_SCHEME.equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return "" + getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor loadInBackground = new CursorLoader(context, uri, new String[]{"_data"}, str, strArr, null).loadInBackground();
            if (loadInBackground != null) {
                try {
                    if (loadInBackground.moveToFirst()) {
                        String string = loadInBackground.getString(loadInBackground.getColumnIndexOrThrow("_data"));
                        if (loadInBackground != null) {
                            loadInBackground.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = loadInBackground;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (loadInBackground != null) {
                loadInBackground.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
