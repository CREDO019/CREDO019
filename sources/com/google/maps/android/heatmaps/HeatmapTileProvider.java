package com.google.maps.android.heatmaps;

import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.collection.LongSparseArray;
import com.facebook.imageutils.JfifUtil;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.quadtree.PointQuadTree;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class HeatmapTileProvider implements TileProvider {
    public static final Gradient DEFAULT_GRADIENT;
    private static final int[] DEFAULT_GRADIENT_COLORS;
    private static final float[] DEFAULT_GRADIENT_START_POINTS;
    private static final int DEFAULT_MAX_ZOOM = 11;
    private static final int DEFAULT_MIN_ZOOM = 5;
    public static final double DEFAULT_OPACITY = 0.7d;
    public static final int DEFAULT_RADIUS = 20;
    private static final int MAX_RADIUS = 50;
    private static final int MAX_ZOOM_LEVEL = 22;
    private static final int MIN_RADIUS = 10;
    private static final int SCREEN_SIZE = 1280;
    private static final int TILE_DIM = 512;
    static final double WORLD_WIDTH = 1.0d;
    private Bounds mBounds;
    private int[] mColorMap;
    private Collection<WeightedLatLng> mData;
    private Gradient mGradient;
    private double[] mKernel;
    private double[] mMaxIntensity;
    private double mOpacity;
    private int mRadius;
    private PointQuadTree<WeightedLatLng> mTree;

    static {
        int[] r1 = {Color.rgb(102, (int) JfifUtil.MARKER_APP1, 0), Color.rgb(255, 0, 0)};
        DEFAULT_GRADIENT_COLORS = r1;
        float[] fArr = {0.2f, 1.0f};
        DEFAULT_GRADIENT_START_POINTS = fArr;
        DEFAULT_GRADIENT = new Gradient(r1, fArr);
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private Collection<WeightedLatLng> data;
        private int radius = 20;
        private Gradient gradient = HeatmapTileProvider.DEFAULT_GRADIENT;
        private double opacity = 0.7d;

        public Builder data(Collection<LatLng> collection) {
            return weightedData(HeatmapTileProvider.wrapData(collection));
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            this.data = collection;
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("No input points.");
            }
            return this;
        }

        public Builder radius(int r2) {
            this.radius = r2;
            if (r2 < 10 || r2 > 50) {
                throw new IllegalArgumentException("Radius not within bounds.");
            }
            return this;
        }

        public Builder gradient(Gradient gradient) {
            this.gradient = gradient;
            return this;
        }

        public Builder opacity(double d) {
            this.opacity = d;
            if (d < 0.0d || d > 1.0d) {
                throw new IllegalArgumentException("Opacity must be in range [0, 1]");
            }
            return this;
        }

        public HeatmapTileProvider build() {
            if (this.data == null) {
                throw new IllegalStateException("No input data: you must use either .data or .weightedData before building");
            }
            return new HeatmapTileProvider(this);
        }
    }

    private HeatmapTileProvider(Builder builder) {
        this.mData = builder.data;
        this.mRadius = builder.radius;
        this.mGradient = builder.gradient;
        this.mOpacity = builder.opacity;
        int r5 = this.mRadius;
        this.mKernel = generateKernel(r5, r5 / 3.0d);
        setGradient(this.mGradient);
        setWeightedData(this.mData);
    }

    public void setWeightedData(Collection<WeightedLatLng> collection) {
        this.mData = collection;
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("No input points.");
        }
        Bounds bounds = getBounds(this.mData);
        this.mBounds = bounds;
        this.mTree = new PointQuadTree<>(bounds);
        for (WeightedLatLng weightedLatLng : this.mData) {
            this.mTree.add(weightedLatLng);
        }
        this.mMaxIntensity = getMaxIntensities(this.mRadius);
    }

    public void setData(Collection<LatLng> collection) {
        setWeightedData(wrapData(collection));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Collection<WeightedLatLng> wrapData(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : collection) {
            arrayList.add(new WeightedLatLng(latLng));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00b4  */
    @Override // com.google.android.gms.maps.model.TileProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.android.gms.maps.model.Tile getTile(int r37, int r38, int r39) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.heatmaps.HeatmapTileProvider.getTile(int, int, int):com.google.android.gms.maps.model.Tile");
    }

    public void setGradient(Gradient gradient) {
        this.mGradient = gradient;
        this.mColorMap = gradient.generateColorMap(this.mOpacity);
    }

    public void setRadius(int r5) {
        this.mRadius = r5;
        this.mKernel = generateKernel(r5, r5 / 3.0d);
        this.mMaxIntensity = getMaxIntensities(this.mRadius);
    }

    public void setOpacity(double d) {
        this.mOpacity = d;
        setGradient(this.mGradient);
    }

    private double[] getMaxIntensities(int r13) {
        int r4;
        double[] dArr = new double[22];
        int r3 = 5;
        while (true) {
            if (r3 >= 11) {
                break;
            }
            dArr[r3] = getMaxValue(this.mData, this.mBounds, r13, (int) (Math.pow(2.0d, r3 - 3) * 1280.0d));
            if (r3 == 5) {
                for (int r42 = 0; r42 < r3; r42++) {
                    dArr[r42] = dArr[r3];
                }
            }
            r3++;
        }
        for (r4 = 11; r4 < 22; r4++) {
            dArr[r4] = dArr[10];
        }
        return dArr;
    }

    private static Tile convertBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return new Tile(512, 512, byteArrayOutputStream.toByteArray());
    }

    static Bounds getBounds(Collection<WeightedLatLng> collection) {
        Iterator<WeightedLatLng> it = collection.iterator();
        WeightedLatLng next = it.next();
        double d = next.getPoint().f1212x;
        double d2 = next.getPoint().f1212x;
        double d3 = d;
        double d4 = d2;
        double d5 = next.getPoint().f1213y;
        double d6 = next.getPoint().f1213y;
        while (it.hasNext()) {
            WeightedLatLng next2 = it.next();
            double d7 = next2.getPoint().f1212x;
            double d8 = next2.getPoint().f1213y;
            if (d7 < d3) {
                d3 = d7;
            }
            if (d7 > d4) {
                d4 = d7;
            }
            if (d8 < d5) {
                d5 = d8;
            }
            if (d8 > d6) {
                d6 = d8;
            }
        }
        return new Bounds(d3, d4, d5, d6);
    }

    static double[] generateKernel(int r7, double d) {
        double[] dArr = new double[(r7 * 2) + 1];
        for (int r1 = -r7; r1 <= r7; r1++) {
            dArr[r1 + r7] = Math.exp(((-r1) * r1) / ((2.0d * d) * d));
        }
        return dArr;
    }

    static double[][] convolve(double[][] dArr, double[] dArr2) {
        int floor = (int) Math.floor(dArr2.length / 2.0d);
        int length = dArr.length;
        int r5 = length - (floor * 2);
        int r7 = 1;
        int r6 = (floor + r5) - 1;
        double[][] dArr3 = (double[][]) Array.newInstance(double.class, length, length);
        int r11 = 0;
        while (true) {
            double d = 0.0d;
            if (r11 >= length) {
                break;
            }
            int r14 = 0;
            while (r14 < length) {
                double d2 = dArr[r11][r14];
                if (d2 != d) {
                    int r15 = r11 + floor;
                    if (r6 < r15) {
                        r15 = r6;
                    }
                    int r152 = r15 + 1;
                    int r12 = r11 - floor;
                    for (int r13 = floor > r12 ? floor : r12; r13 < r152; r13++) {
                        double[] dArr4 = dArr3[r13];
                        dArr4[r14] = dArr4[r14] + (dArr2[r13 - r12] * d2);
                    }
                }
                r14++;
                d = 0.0d;
            }
            r11++;
        }
        double[][] dArr5 = (double[][]) Array.newInstance(double.class, r5, r5);
        int r2 = floor;
        while (r2 < r6 + 1) {
            int r52 = 0;
            while (r52 < length) {
                double d3 = dArr3[r2][r52];
                if (d3 != 0.0d) {
                    int r8 = r52 + floor;
                    if (r6 < r8) {
                        r8 = r6;
                    }
                    int r82 = r8 + r7;
                    int r153 = r52 - floor;
                    for (int r72 = floor > r153 ? floor : r153; r72 < r82; r72++) {
                        double[] dArr6 = dArr5[r2 - floor];
                        int r18 = r72 - floor;
                        dArr6[r18] = dArr6[r18] + (dArr2[r72 - r153] * d3);
                    }
                }
                r52++;
                r7 = 1;
            }
            r2++;
            r7 = 1;
        }
        return dArr5;
    }

    static Bitmap colorize(double[][] dArr, int[] r18, double d) {
        int r2 = r18[r18.length - 1];
        double length = (r18.length - 1) / d;
        int length2 = dArr.length;
        int[] r6 = new int[length2 * length2];
        for (int r7 = 0; r7 < length2; r7++) {
            for (int r8 = 0; r8 < length2; r8++) {
                double d2 = dArr[r8][r7];
                int r9 = (r7 * length2) + r8;
                int r13 = (int) (d2 * length);
                if (d2 != 0.0d) {
                    if (r13 < r18.length) {
                        r6[r9] = r18[r13];
                    } else {
                        r6[r9] = r2;
                    }
                } else {
                    r6[r9] = 0;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(r6, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    static double getMaxValue(Collection<WeightedLatLng> collection, Bounds bounds, int r23, int r24) {
        double d = bounds.minX;
        double d2 = bounds.maxX;
        double d3 = bounds.minY;
        double d4 = d2 - d;
        double d5 = bounds.maxY - d3;
        if (d4 <= d5) {
            d4 = d5;
        }
        double d6 = ((int) ((r24 / (r23 * 2)) + 0.5d)) / d4;
        LongSparseArray longSparseArray = new LongSparseArray();
        double d7 = 0.0d;
        for (WeightedLatLng weightedLatLng : collection) {
            double d8 = weightedLatLng.getPoint().f1212x;
            int r9 = (int) ((weightedLatLng.getPoint().f1213y - d3) * d6);
            long j = (int) ((d8 - d) * d6);
            LongSparseArray longSparseArray2 = (LongSparseArray) longSparseArray.get(j);
            if (longSparseArray2 == null) {
                longSparseArray2 = new LongSparseArray();
                longSparseArray.put(j, longSparseArray2);
            }
            long j2 = r9;
            Double d9 = (Double) longSparseArray2.get(j2);
            if (d9 == null) {
                d9 = Double.valueOf(0.0d);
            }
            Double valueOf = Double.valueOf(d9.doubleValue() + weightedLatLng.getIntensity());
            longSparseArray2.put(j2, valueOf);
            if (valueOf.doubleValue() > d7) {
                d7 = valueOf.doubleValue();
            }
        }
        return d7;
    }
}
