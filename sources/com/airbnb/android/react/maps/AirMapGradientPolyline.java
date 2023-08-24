package com.airbnb.android.react.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import java.io.ByteArrayOutputStream;
import java.util.List;

/* loaded from: classes.dex */
public class AirMapGradientPolyline extends AirMapFeature {
    private int[] colors;
    protected final Context context;
    private GoogleMap map;
    private List<LatLng> points;
    private TileOverlay tileOverlay;
    private float width;
    private float zIndex;

    public AirMapGradientPolyline(Context context) {
        super(context);
        this.context = context;
    }

    public void setCoordinates(List<LatLng> list) {
        this.points = list;
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.remove();
        }
        GoogleMap googleMap = this.map;
        if (googleMap != null) {
            this.tileOverlay = googleMap.addTileOverlay(createTileOverlayOptions());
        }
    }

    public void setStrokeColors(int[] r2) {
        this.colors = r2;
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.remove();
        }
        GoogleMap googleMap = this.map;
        if (googleMap != null) {
            this.tileOverlay = googleMap.addTileOverlay(createTileOverlayOptions());
        }
    }

    public void setZIndex(float f) {
        this.zIndex = f;
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.setZIndex(f);
        }
    }

    public void setWidth(float f) {
        this.width = f;
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.remove();
        }
        GoogleMap googleMap = this.map;
        if (googleMap != null) {
            this.tileOverlay = googleMap.addTileOverlay(createTileOverlayOptions());
        }
    }

    private TileOverlayOptions createTileOverlayOptions() {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.zIndex(this.zIndex);
        tileOverlayOptions.tileProvider(new AirMapGradientPolylineProvider(this.context, this.points, this.colors, this.width));
        return tileOverlayOptions;
    }

    public static int interpolateColor(int[] r6, float f) {
        float length = f * (r6.length - 1);
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        for (int r0 = 0; r0 < r6.length; r0++) {
            float max = Math.max(1.0f - Math.abs(length - r0), 0.0f);
            r1 += (int) (Color.red(r6[r0]) * max);
            r2 += (int) (Color.green(r6[r0]) * max);
            r3 += (int) (Color.blue(r6[r0]) * max);
        }
        return Color.rgb(r1, r2, r3);
    }

    /* loaded from: classes.dex */
    public class AirMapGradientPolylineProvider implements TileProvider {
        public static final int BASE_TILE_SIZE = 256;
        protected final int[] colors;
        protected final float density;
        protected final List<LatLng> points;
        protected Point[] projectedPtMids;
        protected Point[] projectedPts;
        protected final SphericalMercatorProjection projection;
        protected final int tileDimension;
        protected LatLng[] trailLatLngs;
        protected final float width;

        public AirMapGradientPolylineProvider(Context context, List<LatLng> list, int[] r4, float f) {
            this.points = list;
            this.colors = r4;
            this.width = f;
            float f2 = context.getResources().getDisplayMetrics().density;
            this.density = f2;
            this.tileDimension = (int) (f2 * 256.0f);
            this.projection = new SphericalMercatorProjection(256.0d);
            calculatePoints();
        }

        public void calculatePoints() {
            this.trailLatLngs = new LatLng[this.points.size()];
            this.projectedPts = new Point[this.points.size()];
            this.projectedPtMids = new Point[Math.max(this.points.size() - 1, 0)];
            for (int r1 = 0; r1 < this.points.size(); r1++) {
                LatLng latLng = this.points.get(r1);
                this.trailLatLngs[r1] = latLng;
                this.projectedPts[r1] = this.projection.toPoint(latLng);
                if (r1 > 0) {
                    int r3 = r1 - 1;
                    this.projectedPtMids[r3] = this.projection.toPoint(SphericalUtil.interpolate(this.points.get(r3), latLng, 0.5d));
                }
            }
        }

        @Override // com.google.android.gms.maps.model.TileProvider
        public Tile getTile(int r19, int r20, int r21) {
            int r0 = this.tileDimension;
            Bitmap createBitmap = Bitmap.createBitmap(r0, r0, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Matrix matrix = new Matrix();
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(this.width);
            paint.setStrokeCap(Paint.Cap.BUTT);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setFlags(1);
            paint.setShader(new LinearGradient(0.0f, 0.0f, 1.0f, 0.0f, this.colors, (float[]) null, Shader.TileMode.CLAMP));
            paint.getShader().setLocalMatrix(matrix);
            Paint paint2 = new Paint();
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(this.width);
            paint2.setStrokeCap(Paint.Cap.BUTT);
            paint2.setStrokeJoin(Paint.Join.ROUND);
            paint2.setFlags(1);
            renderTrail(canvas, matrix, paint, paint2, (float) (Math.pow(2.0d, r21) * this.density), r19, r20);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            int r2 = this.tileDimension;
            return new Tile(r2, r2, byteArrayOutputStream.toByteArray());
        }

        public void renderTrail(Canvas canvas, Matrix matrix, Paint paint, Paint paint2, float f, int r28, int r29) {
            Canvas canvas2 = canvas;
            MutPoint mutPoint = new MutPoint();
            MutPoint mutPoint2 = new MutPoint();
            MutPoint mutPoint3 = new MutPoint();
            MutPoint mutPoint4 = new MutPoint();
            MutPoint mutPoint5 = new MutPoint();
            float f2 = 1.0f;
            if (this.points.size() == 1) {
                mutPoint.set(this.projectedPts[0], f, r28, r29, this.tileDimension);
                paint2.setStyle(Paint.Style.FILL);
                paint2.setColor(AirMapGradientPolyline.interpolateColor(this.colors, 1.0f));
                canvas2.drawCircle((float) mutPoint.f69x, (float) mutPoint.f70y, paint2.getStrokeWidth() / 2.0f, paint2);
                paint2.setStyle(Paint.Style.STROKE);
            } else if (this.points.size() == 2) {
                mutPoint.set(this.projectedPts[0], f, r28, r29, this.tileDimension);
                mutPoint2.set(this.projectedPts[1], f, r28, r29, this.tileDimension);
                drawLine(canvas, paint2, mutPoint, mutPoint2, 0.0f);
            } else {
                int r6 = 2;
                while (r6 < this.points.size()) {
                    int r18 = r6 - 2;
                    mutPoint.set(this.projectedPts[r18], f, r28, r29, this.tileDimension);
                    int r19 = r6 - 1;
                    mutPoint2.set(this.projectedPts[r19], f, r28, r29, this.tileDimension);
                    mutPoint3.set(this.projectedPts[r6], f, r28, r29, this.tileDimension);
                    mutPoint4.set(this.projectedPtMids[r18], f, r28, r29, this.tileDimension);
                    mutPoint5.set(this.projectedPtMids[r19], f, r28, r29, this.tileDimension);
                    float f3 = r6;
                    float size = (f3 - 2.0f) / this.points.size();
                    float size2 = (f3 - f2) / this.points.size();
                    float f4 = (size + size2) / 2.0f;
                    Log.d("AirMapGradientPolyline", String.valueOf(f4));
                    paint2.setStyle(Paint.Style.FILL);
                    paint2.setColor(AirMapGradientPolyline.interpolateColor(this.colors, f4));
                    canvas2.drawCircle((float) mutPoint2.f69x, (float) mutPoint2.f70y, paint2.getStrokeWidth() / 2.0f, paint2);
                    paint2.setStyle(Paint.Style.STROKE);
                    int r10 = r6;
                    drawLine(canvas, matrix, paint, paint2, r18 == 0 ? mutPoint : mutPoint4, mutPoint2, size, f4);
                    drawLine(canvas, matrix, paint, paint2, mutPoint2, r10 == this.points.size() + (-1) ? mutPoint3 : mutPoint5, f4, size2);
                    r6 = r10 + 1;
                    canvas2 = canvas;
                    f2 = 1.0f;
                }
            }
        }

        public void drawLine(Canvas canvas, Matrix matrix, Paint paint, Paint paint2, MutPoint mutPoint, MutPoint mutPoint2, float f, float f2) {
            if (f == f2) {
                drawLine(canvas, paint2, mutPoint, mutPoint2, f);
                return;
            }
            matrix.reset();
            matrix.preRotate((float) Math.toDegrees(Math.atan2(mutPoint2.f70y - mutPoint.f70y, mutPoint2.f69x - mutPoint.f69x)), (float) mutPoint.f69x, (float) mutPoint.f70y);
            matrix.preTranslate((float) mutPoint.f69x, (float) mutPoint.f70y);
            float sqrt = (float) Math.sqrt(Math.pow(mutPoint2.f69x - mutPoint.f69x, 2.0d) + Math.pow(mutPoint2.f70y - mutPoint.f70y, 2.0d));
            matrix.preScale(sqrt, sqrt);
            float f3 = 1.0f / (f2 - f);
            matrix.preScale(f3, f3);
            matrix.preTranslate(-f, 0.0f);
            paint.getShader().setLocalMatrix(matrix);
            canvas.drawLine((float) mutPoint.f69x, (float) mutPoint.f70y, (float) mutPoint2.f69x, (float) mutPoint2.f70y, paint);
        }

        public void drawLine(Canvas canvas, Paint paint, MutPoint mutPoint, MutPoint mutPoint2, float f) {
            paint.setColor(AirMapGradientPolyline.interpolateColor(this.colors, f));
            canvas.drawLine((float) mutPoint.f69x, (float) mutPoint.f70y, (float) mutPoint2.f69x, (float) mutPoint2.f70y, paint);
        }
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public Object getFeature() {
        return this.tileOverlay;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void addToMap(GoogleMap googleMap) {
        Log.d("AirMapGradientPolyline", "ADDTOMAP");
        this.map = googleMap;
        this.tileOverlay = googleMap.addTileOverlay(createTileOverlayOptions());
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void removeFromMap(GoogleMap googleMap) {
        this.tileOverlay.remove();
    }

    /* loaded from: classes.dex */
    public static class MutPoint {

        /* renamed from: x */
        public double f69x;

        /* renamed from: y */
        public double f70y;

        public MutPoint set(Point point, float f, int r7, int r8, int r9) {
            double d = f;
            this.f69x = (point.f1212x * d) - (r7 * r9);
            this.f70y = (point.f1213y * d) - (r8 * r9);
            return this;
        }
    }
}
