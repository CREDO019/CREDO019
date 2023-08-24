package com.airbnb.android.react.maps;

import android.content.Context;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes.dex */
public class AirMapTileWorker extends Worker {
    private static final int BUFFER_SIZE = 16384;

    public AirMapTileWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        String string = getInputData().getString("filename");
        try {
            int r1 = getInputData().getInt("maxAge", 0);
            if (r1 >= 0) {
                if ((System.currentTimeMillis() - new File(string).lastModified()) / 1000 < r1) {
                    return ListenableWorker.Result.failure();
                }
            }
            try {
                byte[] fetchTile = fetchTile(new URL(getInputData().getString(ImagesContract.URL)));
                if (fetchTile != null) {
                    if (!writeTileImage(fetchTile, string)) {
                        return ListenableWorker.Result.failure();
                    }
                    Log.d("urlTile", "Worker fetched " + string);
                    return ListenableWorker.Result.success();
                }
                return ListenableWorker.Result.retry();
            } catch (MalformedURLException e) {
                throw new AssertionError(e);
            }
        } catch (Error unused) {
            return ListenableWorker.Result.failure();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.net.URL] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] fetchTile(java.net.URL r9) {
        /*
            r8 = this;
            r0 = 0
            java.io.InputStream r9 = r9.openStream()     // Catch: java.lang.Throwable -> L38 java.lang.OutOfMemoryError -> L3d java.io.IOException -> L3f
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L2e java.lang.OutOfMemoryError -> L33 java.io.IOException -> L35
            r1.<init>()     // Catch: java.lang.Throwable -> L2e java.lang.OutOfMemoryError -> L33 java.io.IOException -> L35
            r2 = 16384(0x4000, float:2.2959E-41)
            byte[] r3 = new byte[r2]     // Catch: java.lang.OutOfMemoryError -> L2a java.io.IOException -> L2c java.lang.Throwable -> L52
        Le:
            r4 = 0
            int r5 = r9.read(r3, r4, r2)     // Catch: java.lang.OutOfMemoryError -> L2a java.io.IOException -> L2c java.lang.Throwable -> L52
            r6 = -1
            if (r5 == r6) goto L1a
            r1.write(r3, r4, r5)     // Catch: java.lang.OutOfMemoryError -> L2a java.io.IOException -> L2c java.lang.Throwable -> L52
            goto Le
        L1a:
            r1.flush()     // Catch: java.lang.OutOfMemoryError -> L2a java.io.IOException -> L2c java.lang.Throwable -> L52
            byte[] r0 = r1.toByteArray()     // Catch: java.lang.OutOfMemoryError -> L2a java.io.IOException -> L2c java.lang.Throwable -> L52
            if (r9 == 0) goto L26
            r9.close()     // Catch: java.lang.Exception -> L26
        L26:
            r1.close()     // Catch: java.lang.Exception -> L29
        L29:
            return r0
        L2a:
            r2 = move-exception
            goto L42
        L2c:
            r2 = move-exception
            goto L42
        L2e:
            r1 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L53
        L33:
            r2 = move-exception
            goto L36
        L35:
            r2 = move-exception
        L36:
            r1 = r0
            goto L42
        L38:
            r9 = move-exception
            r1 = r0
            r0 = r9
            r9 = r1
            goto L53
        L3d:
            r2 = move-exception
            goto L40
        L3f:
            r2 = move-exception
        L40:
            r9 = r0
            r1 = r9
        L42:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L52
            if (r9 == 0) goto L4c
            r9.close()     // Catch: java.lang.Exception -> L4b
            goto L4c
        L4b:
        L4c:
            if (r1 == 0) goto L51
            r1.close()     // Catch: java.lang.Exception -> L51
        L51:
            return r0
        L52:
            r0 = move-exception
        L53:
            if (r9 == 0) goto L5a
            r9.close()     // Catch: java.lang.Exception -> L59
            goto L5a
        L59:
        L5a:
            if (r1 == 0) goto L5f
            r1.close()     // Catch: java.lang.Exception -> L5f
        L5f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.AirMapTileWorker.fetchTile(java.net.URL):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean writeTileImage(byte[] r4, java.lang.String r5) {
        /*
            r3 = this;
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L26 java.lang.OutOfMemoryError -> L28 java.io.IOException -> L2a
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L26 java.lang.OutOfMemoryError -> L28 java.io.IOException -> L2a
            java.io.File r5 = r2.getParentFile()     // Catch: java.lang.Throwable -> L26 java.lang.OutOfMemoryError -> L28 java.io.IOException -> L2a
            r5.mkdirs()     // Catch: java.lang.Throwable -> L26 java.lang.OutOfMemoryError -> L28 java.io.IOException -> L2a
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L26 java.lang.OutOfMemoryError -> L28 java.io.IOException -> L2a
            r5.<init>(r2)     // Catch: java.lang.Throwable -> L26 java.lang.OutOfMemoryError -> L28 java.io.IOException -> L2a
            r5.write(r4)     // Catch: java.lang.Throwable -> L1e java.lang.OutOfMemoryError -> L21 java.io.IOException -> L23
            r4 = 1
            r5.close()     // Catch: java.lang.Exception -> L1d
        L1d:
            return r4
        L1e:
            r4 = move-exception
            r1 = r5
            goto L34
        L21:
            r4 = move-exception
            goto L24
        L23:
            r4 = move-exception
        L24:
            r1 = r5
            goto L2b
        L26:
            r4 = move-exception
            goto L34
        L28:
            r4 = move-exception
            goto L2b
        L2a:
            r4 = move-exception
        L2b:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L26
            if (r1 == 0) goto L33
            r1.close()     // Catch: java.lang.Exception -> L33
        L33:
            return r0
        L34:
            if (r1 == 0) goto L39
            r1.close()     // Catch: java.lang.Exception -> L39
        L39:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.AirMapTileWorker.writeTileImage(byte[], java.lang.String):boolean");
    }
}
