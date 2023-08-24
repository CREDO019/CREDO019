package com.canhub.cropper.utils;

import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.facebook.common.util.UriUtil;
import com.facebook.react.uimanager.events.TouchesHelper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m184d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a \u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a \u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¨\u0006\u0012"}, m183d2 = {"copy", "", "source", "Ljava/io/InputStream;", TouchesHelper.TARGET_KEY, "Ljava/io/OutputStream;", "getFileExtension", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "getFileFromContentUri", "Ljava/io/File;", "contentUri", "uniqueName", "", "getFilePathFromUri", "cropper_release"}, m182k = 2, m181mv = {1, 5, 1}, m179xi = 48)
/* renamed from: com.canhub.cropper.utils.GetFilePathFromUriKt */
/* loaded from: classes.dex */
public final class GetFilePathFromUri {
    public static final String getFilePathFromUri(Context context, Uri uri, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String path = uri.getPath();
        if (!(path != null && StringsKt.contains$default((CharSequence) path, (CharSequence) "file://", false, 2, (Object) null))) {
            String path2 = getFileFromContentUri(context, uri, z).getPath();
            Intrinsics.checkNotNullExpressionValue(path2, "getFileFromContentUri(context, uri, uniqueName).path");
            return path2;
        }
        String path3 = uri.getPath();
        Intrinsics.checkNotNull(path3);
        Intrinsics.checkNotNullExpressionValue(path3, "uri.path!!");
        return path3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x007a, code lost:
        if (r1 == null) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final java.io.File getFileFromContentUri(android.content.Context r5, android.net.Uri r6, boolean r7) {
        /*
            java.lang.String r0 = getFileExtension(r5, r6)
            java.lang.String r1 = ""
            if (r0 != 0) goto L9
            r0 = r1
        L9:
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.util.Locale r3 = java.util.Locale.getDefault()
            java.lang.String r4 = "yyyyMMdd_HHmmss"
            r2.<init>(r4, r3)
            java.util.Date r3 = new java.util.Date
            r3.<init>()
            java.lang.String r2 = r2.format(r3)
            if (r7 == 0) goto L21
            r1 = r2
        L21:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r2 = "temp_file_"
            r7.append(r2)
            r7.append(r1)
            java.lang.String r1 = "."
            r7.append(r1)
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            java.io.File r0 = new java.io.File
            java.io.File r1 = r5.getCacheDir()
            r0.<init>(r1, r7)
            r0.createNewFile()
            r7 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L7d
            java.io.InputStream r7 = r5.openInputStream(r6)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L7d
            if (r7 != 0) goto L57
            goto L5d
        L57:
            r5 = r1
            java.io.OutputStream r5 = (java.io.OutputStream) r5     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L7d
            copy(r7, r5)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L7d
        L5d:
            r1.flush()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L7d
            if (r7 != 0) goto L63
            goto L66
        L63:
            r7.close()
        L66:
            r1.close()
            goto L7c
        L6a:
            r5 = move-exception
            goto L71
        L6c:
            r5 = move-exception
            r1 = r7
            goto L7e
        L6f:
            r5 = move-exception
            r1 = r7
        L71:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L7d
            if (r7 != 0) goto L77
            goto L7a
        L77:
            r7.close()
        L7a:
            if (r1 != 0) goto L66
        L7c:
            return r0
        L7d:
            r5 = move-exception
        L7e:
            if (r7 != 0) goto L81
            goto L84
        L81:
            r7.close()
        L84:
            if (r1 != 0) goto L87
            goto L8a
        L87:
            r1.close()
        L8a:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.utils.GetFilePathFromUri.getFileFromContentUri(android.content.Context, android.net.Uri, boolean):java.io.File");
    }

    private static final String getFileExtension(Context context, Uri uri) {
        if (Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME)) {
            return MimeTypeMap.getSingleton().getExtensionFromMimeType(context.getContentResolver().getType(uri));
        }
        String path = uri.getPath();
        if (path == null) {
            return null;
        }
        return MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(path)).toString());
    }

    private static final void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }
}
