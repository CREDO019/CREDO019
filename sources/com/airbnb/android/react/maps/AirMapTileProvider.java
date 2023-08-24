package com.airbnb.android.react.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes.dex */
public class AirMapTileProvider implements TileProvider {
    protected static final int BUFFER_SIZE = 16384;
    protected static final int TARGET_TILE_SIZE = 512;
    protected Context context;
    protected boolean customMode;
    protected boolean doubleTileSize;
    protected boolean flipY;
    protected int maximumNativeZ;
    protected int maximumZ;
    protected int minimumZ;
    protected boolean offlineMode;
    protected int tileCacheMaxAge;
    protected String tileCachePath;
    protected UrlTileProvider tileProvider;
    protected int tileSize;
    protected String urlTemplate;

    public void setCustomMode() {
    }

    /* loaded from: classes.dex */
    class AIRMapUrlTileProvider extends UrlTileProvider {
        private String urlTemplate;

        public AIRMapUrlTileProvider(int r2, int r3, String str) {
            super(r2, r3);
            this.urlTemplate = str;
        }

        @Override // com.google.android.gms.maps.model.UrlTileProvider
        public URL getTileUrl(int r3, int r4, int r5) {
            if (AirMapTileProvider.this.flipY) {
                r4 = ((1 << r5) - r4) - 1;
            }
            String replace = this.urlTemplate.replace("{x}", Integer.toString(r3)).replace("{y}", Integer.toString(r4)).replace("{z}", Integer.toString(r5));
            if (AirMapTileProvider.this.maximumZ <= 0 || r5 <= AirMapTileProvider.this.maximumZ) {
                if (AirMapTileProvider.this.minimumZ <= 0 || r5 >= AirMapTileProvider.this.minimumZ) {
                    try {
                        return new URL(replace);
                    } catch (MalformedURLException e) {
                        throw new AssertionError(e);
                    }
                }
                return null;
            }
            return null;
        }

        public void setUrlTemplate(String str) {
            this.urlTemplate = str;
        }
    }

    public AirMapTileProvider(int r2, boolean z, String str, int r5, int r6, int r7, boolean z2, String str2, int r10, boolean z3, Context context, boolean z4) {
        this.tileProvider = new AIRMapUrlTileProvider(r2, r2, str);
        this.tileSize = r2;
        this.doubleTileSize = z;
        this.urlTemplate = str;
        this.maximumZ = r5;
        this.maximumNativeZ = r6;
        this.minimumZ = r7;
        this.flipY = z2;
        this.tileCachePath = str2;
        this.tileCacheMaxAge = r10;
        this.offlineMode = z3;
        this.context = context;
        this.customMode = z4;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    public Tile getTile(int r6, int r7, int r8) {
        byte[] bArr;
        int r1;
        if (this.customMode) {
            int r0 = this.maximumZ;
            if (r0 <= 0) {
                r0 = Integer.MAX_VALUE;
            }
            if (this.tileSize != 256 || !this.doubleTileSize || (r1 = r8 + 1) > this.maximumNativeZ || r1 > r0) {
                bArr = null;
            } else {
                Log.d("urlTile", "pullTilesFromHigherZoom");
                bArr = pullTilesFromHigherZoom(r6, r7, r8);
            }
            if (r8 > this.maximumNativeZ) {
                Log.d("urlTile", "scaleLowerZoomTile");
                bArr = scaleLowerZoomTile(r6, r7, r8, this.maximumNativeZ);
            }
            if (bArr == null && r8 <= r0) {
                Log.d("urlTile", "getTileImage");
                bArr = getTileImage(r6, r7, r8);
            }
            if (bArr == null && this.tileCachePath != null && this.offlineMode) {
                Log.d("urlTile", "findLowerZoomTileForScaling");
                int r02 = this.maximumNativeZ;
                int max = Math.max(this.minimumZ, r8 - 3);
                for (int r03 = r8 > r02 ? r02 - 1 : r8 - 1; r03 >= max; r03--) {
                    bArr = scaleLowerZoomTile(r6, r7, r8, r03);
                    if (bArr != null) {
                        break;
                    }
                }
            }
            if (bArr == null) {
                return null;
            }
            int r62 = this.tileSize;
            return new Tile(r62, r62, bArr);
        }
        return this.tileProvider.getTile(r6, r7, r8);
    }

    byte[] getTileImage(int r12, int r13, int r14) {
        byte[] bArr;
        if (this.tileCachePath != null) {
            bArr = readTileImage(r12, r13, r14);
            if (bArr != null) {
                Log.d("urlTile", "tile cache HIT for " + r14 + "/" + r12 + "/" + r13);
            } else {
                Log.d("urlTile", "tile cache MISS for " + r14 + "/" + r12 + "/" + r13);
            }
            if (bArr != null && !this.offlineMode) {
                checkForRefresh(r12, r13, r14);
            }
        } else {
            bArr = null;
        }
        if (bArr == null && !this.offlineMode && this.tileCachePath != null) {
            String tileFilename = getTileFilename(r12, r13, r14);
            Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
            WorkManager workManager = WorkManager.getInstance(this.context.getApplicationContext());
            try {
                workManager.enqueueUniqueWork(tileFilename, ExistingWorkPolicy.KEEP, new OneTimeWorkRequest.Builder(AirMapTileWorker.class).setConstraints(build).addTag(tileFilename).setInputData(new Data.Builder().putString(ImagesContract.URL, getTileUrl(r12, r13, r14).toString()).putString("filename", tileFilename).putInt("maxAge", -1).build()).build()).getResult().get(1L, TimeUnit.SECONDS);
                Thread.sleep(500L);
                Log.d("urlTile: ", workManager.getWorkInfosByTag(tileFilename).get(1L, TimeUnit.SECONDS).get(0).toString());
                if (this.tileCachePath != null) {
                    bArr = readTileImage(r12, r13, r14);
                    if (bArr != null) {
                        Log.d("urlTile", "tile cache fetch HIT for " + r14 + "/" + r12 + "/" + r13);
                    } else {
                        Log.d("urlTile", "tile cache fetch MISS for " + r14 + "/" + r12 + "/" + r13);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (bArr == null && !this.offlineMode) {
            Log.d("urlTile", "Normal fetch");
            bArr = fetchTile(r12, r13, r14);
            if (bArr == null) {
                Log.d("urlTile", "tile fetch TIMEOUT / FAIL for " + r14 + "/" + r12 + "/" + r13);
            }
        }
        return bArr;
    }

    byte[] pullTilesFromHigherZoom(int r7, int r8, int r9) {
        Bitmap newBitmap = getNewBitmap();
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        int r72 = r7 * 2;
        int r82 = r8 * 2;
        int r92 = r9 + 1;
        byte[] tileImage = getTileImage(r72, r82, r92);
        int r4 = r82 + 1;
        byte[] tileImage2 = getTileImage(r72, r4, r92);
        int r73 = r72 + 1;
        byte[] tileImage3 = getTileImage(r73, r82, r92);
        byte[] tileImage4 = getTileImage(r73, r4, r92);
        if (tileImage == null || tileImage2 == null || tileImage3 == null || tileImage4 == null) {
            return null;
        }
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(tileImage, 0, tileImage.length);
        canvas.drawBitmap(decodeByteArray, 0.0f, 0.0f, paint);
        decodeByteArray.recycle();
        Bitmap decodeByteArray2 = BitmapFactory.decodeByteArray(tileImage2, 0, tileImage2.length);
        canvas.drawBitmap(decodeByteArray2, 0.0f, 256.0f, paint);
        decodeByteArray2.recycle();
        Bitmap decodeByteArray3 = BitmapFactory.decodeByteArray(tileImage3, 0, tileImage3.length);
        canvas.drawBitmap(decodeByteArray3, 256.0f, 0.0f, paint);
        decodeByteArray3.recycle();
        Bitmap decodeByteArray4 = BitmapFactory.decodeByteArray(tileImage4, 0, tileImage4.length);
        canvas.drawBitmap(decodeByteArray4, 256.0f, 256.0f, paint);
        decodeByteArray4.recycle();
        byte[] bitmapToByteArray = bitmapToByteArray(newBitmap);
        newBitmap.recycle();
        return bitmapToByteArray;
    }

    Bitmap getNewBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(0);
        return createBitmap;
    }

    byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    byte[] scaleLowerZoomTile(int r7, int r8, int r9, int r10) {
        int r102 = r9 - r10;
        int r0 = 1 << r102;
        int r1 = r7 >> r102;
        int r2 = r8 >> r102;
        int r92 = r9 - r102;
        int r72 = r7 % r0;
        int r82 = r8 % r0;
        Bitmap newBitmap = getNewBitmap();
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        byte[] tileImage = getTileImage(r1, r2, r92);
        if (tileImage == null) {
            return null;
        }
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(tileImage, 0, tileImage.length);
        int r12 = this.tileSize / r0;
        int r73 = r72 * r12;
        int r83 = r82 * r12;
        canvas.drawBitmap(decodeByteArray, new Rect(r73, r83, r73 + r12, r12 + r83), new Rect(0, 0, 512, 512), paint);
        decodeByteArray.recycle();
        byte[] bitmapToByteArray = bitmapToByteArray(newBitmap);
        newBitmap.recycle();
        return bitmapToByteArray;
    }

    void checkForRefresh(int r7, int r8, int r9) {
        String tileFilename = getTileFilename(r7, r8, r9);
        if ((System.currentTimeMillis() - new File(tileFilename).lastModified()) / 1000 > this.tileCacheMaxAge) {
            Log.d("urlTile", "Refreshing");
            Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
            WorkManager.getInstance(this.context.getApplicationContext()).enqueueUniqueWork(tileFilename, ExistingWorkPolicy.KEEP, new OneTimeWorkRequest.Builder(AirMapTileWorker.class).setConstraints(build).addTag(tileFilename).setInputData(new Data.Builder().putString(ImagesContract.URL, getTileUrl(r7, r8, r9).toString()).putString("filename", tileFilename).putInt("maxAge", this.tileCacheMaxAge).build()).build());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0056 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.net.URL] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v0, types: [int] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v3, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    byte[] fetchTile(int r7, int r8, int r9) {
        /*
            r6 = this;
            java.net.URL r7 = r6.getTileUrl(r7, r8, r9)
            r8 = 0
            java.net.URLConnection r7 = r7.openConnection()     // Catch: java.lang.Throwable -> L40 java.lang.OutOfMemoryError -> L45 java.io.IOException -> L47
            java.io.InputStream r7 = r7.getInputStream()     // Catch: java.lang.Throwable -> L40 java.lang.OutOfMemoryError -> L45 java.io.IOException -> L47
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L36 java.lang.OutOfMemoryError -> L3b java.io.IOException -> L3d
            r9.<init>()     // Catch: java.lang.Throwable -> L36 java.lang.OutOfMemoryError -> L3b java.io.IOException -> L3d
            r0 = 16384(0x4000, float:2.2959E-41)
            byte[] r1 = new byte[r0]     // Catch: java.lang.OutOfMemoryError -> L32 java.io.IOException -> L34 java.lang.Throwable -> L5a
        L16:
            r2 = 0
            int r3 = r7.read(r1, r2, r0)     // Catch: java.lang.OutOfMemoryError -> L32 java.io.IOException -> L34 java.lang.Throwable -> L5a
            r4 = -1
            if (r3 == r4) goto L22
            r9.write(r1, r2, r3)     // Catch: java.lang.OutOfMemoryError -> L32 java.io.IOException -> L34 java.lang.Throwable -> L5a
            goto L16
        L22:
            r9.flush()     // Catch: java.lang.OutOfMemoryError -> L32 java.io.IOException -> L34 java.lang.Throwable -> L5a
            byte[] r8 = r9.toByteArray()     // Catch: java.lang.OutOfMemoryError -> L32 java.io.IOException -> L34 java.lang.Throwable -> L5a
            if (r7 == 0) goto L2e
            r7.close()     // Catch: java.lang.Exception -> L2e
        L2e:
            r9.close()     // Catch: java.lang.Exception -> L31
        L31:
            return r8
        L32:
            r0 = move-exception
            goto L4a
        L34:
            r0 = move-exception
            goto L4a
        L36:
            r9 = move-exception
            r5 = r9
            r9 = r8
            r8 = r5
            goto L5b
        L3b:
            r0 = move-exception
            goto L3e
        L3d:
            r0 = move-exception
        L3e:
            r9 = r8
            goto L4a
        L40:
            r7 = move-exception
            r9 = r8
            r8 = r7
            r7 = r9
            goto L5b
        L45:
            r0 = move-exception
            goto L48
        L47:
            r0 = move-exception
        L48:
            r7 = r8
            r9 = r7
        L4a:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L5a
            if (r7 == 0) goto L54
            r7.close()     // Catch: java.lang.Exception -> L53
            goto L54
        L53:
        L54:
            if (r9 == 0) goto L59
            r9.close()     // Catch: java.lang.Exception -> L59
        L59:
            return r8
        L5a:
            r8 = move-exception
        L5b:
            if (r7 == 0) goto L62
            r7.close()     // Catch: java.lang.Exception -> L61
            goto L62
        L61:
        L62:
            if (r9 == 0) goto L67
            r9.close()     // Catch: java.lang.Exception -> L67
        L67:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.AirMapTileProvider.fetchTile(int, int, int):byte[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:61:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.FileInputStream, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    byte[] readTileImage(int r7, int r8, int r9) {
        /*
            r6 = this;
            java.lang.String r7 = r6.getTileFilename(r7, r8, r9)
            r8 = 0
            if (r7 != 0) goto L8
            return r8
        L8:
            java.io.File r9 = new java.io.File
            r9.<init>(r7)
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4d java.lang.OutOfMemoryError -> L52 java.io.IOException -> L54
            r7.<init>(r9)     // Catch: java.lang.Throwable -> L4d java.lang.OutOfMemoryError -> L52 java.io.IOException -> L54
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L44 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
            r0.<init>()     // Catch: java.lang.Throwable -> L44 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
            r1 = 16384(0x4000, float:2.2959E-41)
            byte[] r2 = new byte[r1]     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L67
        L1b:
            r3 = 0
            int r4 = r7.read(r2, r3, r1)     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L67
            r5 = -1
            if (r4 == r5) goto L27
            r0.write(r2, r3, r4)     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L67
            goto L1b
        L27:
            r0.flush()     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L67
            int r1 = r6.tileCacheMaxAge     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L67
            if (r1 != 0) goto L35
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L67
            r9.setLastModified(r1)     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L67
        L35:
            byte[] r8 = r0.toByteArray()     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L67
            r7.close()     // Catch: java.lang.Exception -> L3c
        L3c:
            r0.close()     // Catch: java.lang.Exception -> L3f
        L3f:
            return r8
        L40:
            r9 = move-exception
            goto L57
        L42:
            r9 = move-exception
            goto L57
        L44:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto L68
        L48:
            r9 = move-exception
            goto L4b
        L4a:
            r9 = move-exception
        L4b:
            r0 = r8
            goto L57
        L4d:
            r7 = move-exception
            r0 = r8
            r8 = r7
            r7 = r0
            goto L68
        L52:
            r9 = move-exception
            goto L55
        L54:
            r9 = move-exception
        L55:
            r7 = r8
            r0 = r7
        L57:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L67
            if (r7 == 0) goto L61
            r7.close()     // Catch: java.lang.Exception -> L60
            goto L61
        L60:
        L61:
            if (r0 == 0) goto L66
            r0.close()     // Catch: java.lang.Exception -> L66
        L66:
            return r8
        L67:
            r8 = move-exception
        L68:
            if (r7 == 0) goto L6f
            r7.close()     // Catch: java.lang.Exception -> L6e
            goto L6f
        L6e:
        L6f:
            if (r0 == 0) goto L74
            r0.close()     // Catch: java.lang.Exception -> L74
        L74:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.AirMapTileProvider.readTileImage(int, int, int):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0034 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean writeTileImage(byte[] r2, int r3, int r4, int r5) {
        /*
            r1 = this;
            java.lang.String r3 = r1.getTileFilename(r3, r4, r5)
            r4 = 0
            if (r3 != 0) goto L8
            return r4
        L8:
            r5 = 0
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            java.io.File r3 = r0.getParentFile()     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            r3.mkdirs()     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            r3.write(r2)     // Catch: java.lang.Throwable -> L22 java.lang.OutOfMemoryError -> L25 java.io.IOException -> L27
            r2 = 1
            r3.close()     // Catch: java.lang.Exception -> L21
        L21:
            return r2
        L22:
            r2 = move-exception
            r5 = r3
            goto L38
        L25:
            r2 = move-exception
            goto L28
        L27:
            r2 = move-exception
        L28:
            r5 = r3
            goto L2f
        L2a:
            r2 = move-exception
            goto L38
        L2c:
            r2 = move-exception
            goto L2f
        L2e:
            r2 = move-exception
        L2f:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L2a
            if (r5 == 0) goto L37
            r5.close()     // Catch: java.lang.Exception -> L37
        L37:
            return r4
        L38:
            if (r5 == 0) goto L3d
            r5.close()     // Catch: java.lang.Exception -> L3d
        L3d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.AirMapTileProvider.writeTileImage(byte[], int, int, int):boolean");
    }

    String getTileFilename(int r3, int r4, int r5) {
        if (this.tileCachePath == null) {
            return null;
        }
        return this.tileCachePath + IOUtils.DIR_SEPARATOR_UNIX + r5 + "/" + r3 + "/" + r4;
    }

    protected URL getTileUrl(int r2, int r3, int r4) {
        return this.tileProvider.getTileUrl(r2, r3, r4);
    }

    public void setUrlTemplate(String str) {
        this.urlTemplate = str;
    }

    public void setTileSize(int r3) {
        if (this.tileSize != r3) {
            this.tileProvider = new AIRMapUrlTileProvider(r3, r3, this.urlTemplate);
        }
        this.tileSize = r3;
    }

    public void setDoubleTileSize(boolean z) {
        this.doubleTileSize = z;
    }

    public void setMaximumZ(int r1) {
        this.maximumZ = r1;
    }

    public void setMaximumNativeZ(int r1) {
        this.maximumNativeZ = r1;
    }

    public void setMinimumZ(int r1) {
        this.minimumZ = r1;
    }

    public void setFlipY(boolean z) {
        this.flipY = z;
    }

    public void setTileCachePath(String str) {
        this.tileCachePath = str;
    }

    public void setTileCacheMaxAge(int r1) {
        this.tileCacheMaxAge = r1;
    }

    public void setOfflineMode(boolean z) {
        this.offlineMode = z;
    }
}
