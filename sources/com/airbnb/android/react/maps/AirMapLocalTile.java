package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

/* loaded from: classes.dex */
public class AirMapLocalTile extends AirMapFeature {
    private String pathTemplate;
    private TileOverlay tileOverlay;
    private TileOverlayOptions tileOverlayOptions;
    private AIRMapLocalTileProvider tileProvider;
    private float tileSize;
    private boolean useAssets;
    private float zIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class AIRMapLocalTileProvider implements TileProvider {
        private static final int BUFFER_SIZE = 16384;
        private String pathTemplate;
        private int tileSize;
        private final boolean useAssets;

        public AIRMapLocalTileProvider(int r2, String str, boolean z) {
            this.tileSize = r2;
            this.pathTemplate = str;
            this.useAssets = z;
        }

        @Override // com.google.android.gms.maps.model.TileProvider
        public Tile getTile(int r1, int r2, int r3) {
            byte[] readTileImage = readTileImage(r1, r2, r3);
            if (readTileImage == null) {
                return TileProvider.NO_TILE;
            }
            int r32 = this.tileSize;
            return new Tile(r32, r32, readTileImage);
        }

        public void setPathTemplate(String str) {
            this.pathTemplate = str;
        }

        public void setTileSize(int r1) {
            this.tileSize = r1;
        }

        /* JADX WARN: Removed duplicated region for block: B:58:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:68:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private byte[] readTileImage(int r7, int r8, int r9) {
            /*
                r6 = this;
                java.lang.String r7 = r6.getTileFilename(r7, r8, r9)
                r8 = 0
                boolean r9 = r6.useAssets     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                if (r9 == 0) goto L18
                com.airbnb.android.react.maps.AirMapLocalTile r9 = com.airbnb.android.react.maps.AirMapLocalTile.this     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                android.content.Context r9 = r9.getContext()     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                android.content.res.AssetManager r9 = r9.getAssets()     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                java.io.InputStream r7 = r9.open(r7)     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                goto L1e
            L18:
                java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                r9.<init>(r7)     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                r7 = r9
            L1e:
                java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L50 java.lang.OutOfMemoryError -> L56 java.io.IOException -> L58
                r9.<init>()     // Catch: java.lang.Throwable -> L50 java.lang.OutOfMemoryError -> L56 java.io.IOException -> L58
                r0 = 16384(0x4000, float:2.2959E-41)
                byte[] r1 = new byte[r0]     // Catch: java.lang.Throwable -> L43 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
            L27:
                r2 = 0
                int r3 = r7.read(r1, r2, r0)     // Catch: java.lang.Throwable -> L43 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
                r4 = -1
                if (r3 == r4) goto L33
                r9.write(r1, r2, r3)     // Catch: java.lang.Throwable -> L43 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
                goto L27
            L33:
                r9.flush()     // Catch: java.lang.Throwable -> L43 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
                byte[] r8 = r9.toByteArray()     // Catch: java.lang.Throwable -> L43 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
                if (r7 == 0) goto L3f
                r7.close()     // Catch: java.lang.Exception -> L3f
            L3f:
                r9.close()     // Catch: java.lang.Exception -> L42
            L42:
                return r8
            L43:
                r8 = move-exception
                r5 = r8
                r8 = r7
                r7 = r5
                goto L79
            L48:
                r0 = move-exception
                goto L4b
            L4a:
                r0 = move-exception
            L4b:
                r5 = r9
                r9 = r7
                r7 = r0
                r0 = r5
                goto L66
            L50:
                r9 = move-exception
                r5 = r8
                r8 = r7
                r7 = r9
                r9 = r5
                goto L79
            L56:
                r9 = move-exception
                goto L59
            L58:
                r9 = move-exception
            L59:
                r0 = r8
                r5 = r9
                r9 = r7
                r7 = r5
                goto L66
            L5e:
                r7 = move-exception
                r9 = r8
                goto L79
            L61:
                r7 = move-exception
                goto L64
            L63:
                r7 = move-exception
            L64:
                r9 = r8
                r0 = r9
            L66:
                r7.printStackTrace()     // Catch: java.lang.Throwable -> L76
                if (r9 == 0) goto L70
                r9.close()     // Catch: java.lang.Exception -> L6f
                goto L70
            L6f:
            L70:
                if (r0 == 0) goto L75
                r0.close()     // Catch: java.lang.Exception -> L75
            L75:
                return r8
            L76:
                r7 = move-exception
                r8 = r9
                r9 = r0
            L79:
                if (r8 == 0) goto L80
                r8.close()     // Catch: java.lang.Exception -> L7f
                goto L80
            L7f:
            L80:
                if (r9 == 0) goto L85
                r9.close()     // Catch: java.lang.Exception -> L85
            L85:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.AirMapLocalTile.AIRMapLocalTileProvider.readTileImage(int, int, int):byte[]");
        }

        private String getTileFilename(int r3, int r4, int r5) {
            return this.pathTemplate.replace("{x}", Integer.toString(r3)).replace("{y}", Integer.toString(r4)).replace("{z}", Integer.toString(r5));
        }
    }

    public AirMapLocalTile(Context context) {
        super(context);
    }

    public void setPathTemplate(String str) {
        this.pathTemplate = str;
        AIRMapLocalTileProvider aIRMapLocalTileProvider = this.tileProvider;
        if (aIRMapLocalTileProvider != null) {
            aIRMapLocalTileProvider.setPathTemplate(str);
        }
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setZIndex(float f) {
        this.zIndex = f;
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.setZIndex(f);
        }
    }

    public void setTileSize(float f) {
        this.tileSize = f;
        AIRMapLocalTileProvider aIRMapLocalTileProvider = this.tileProvider;
        if (aIRMapLocalTileProvider != null) {
            aIRMapLocalTileProvider.setTileSize((int) f);
        }
    }

    public void setUseAssets(boolean z) {
        this.useAssets = z;
    }

    public TileOverlayOptions getTileOverlayOptions() {
        if (this.tileOverlayOptions == null) {
            this.tileOverlayOptions = createTileOverlayOptions();
        }
        return this.tileOverlayOptions;
    }

    private TileOverlayOptions createTileOverlayOptions() {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.zIndex(this.zIndex);
        AIRMapLocalTileProvider aIRMapLocalTileProvider = new AIRMapLocalTileProvider((int) this.tileSize, this.pathTemplate, this.useAssets);
        this.tileProvider = aIRMapLocalTileProvider;
        tileOverlayOptions.tileProvider(aIRMapLocalTileProvider);
        return tileOverlayOptions;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public Object getFeature() {
        return this.tileOverlay;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void addToMap(GoogleMap googleMap) {
        this.tileOverlay = googleMap.addTileOverlay(getTileOverlayOptions());
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void removeFromMap(GoogleMap googleMap) {
        this.tileOverlay.remove();
    }
}
