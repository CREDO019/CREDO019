package com.airbnb.android.react.maps;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.util.Property;
import android.view.View;
import android.widget.LinearLayout;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/* loaded from: classes.dex */
public class AirMapMarker extends AirMapFeature {
    private boolean anchorIsSet;
    private float anchorX;
    private float anchorY;
    private boolean calloutAnchorIsSet;
    private float calloutAnchorX;
    private float calloutAnchorY;
    private AirMapCallout calloutView;
    private final Context context;
    private DataSource<CloseableReference<CloseableImage>> dataSource;
    private boolean draggable;
    private boolean flat;
    private boolean hasCustomMarkerView;
    private int height;
    private Bitmap iconBitmap;
    private BitmapDescriptor iconBitmapDescriptor;
    private String identifier;
    private String imageUri;
    private final DraweeHolder<?> logoHolder;
    private Bitmap mLastBitmapCreated;
    private final ControllerListener<ImageInfo> mLogoControllerListener;
    private Marker marker;
    private float markerHue;
    private final AirMapMarkerManager markerManager;
    private MarkerOptions markerOptions;
    private float opacity;
    private LatLng position;
    private float rotation;
    private String snippet;
    private String title;
    private boolean tracksViewChanges;
    private boolean tracksViewChangesActive;
    private int width;
    private View wrappedCalloutView;
    private int zIndex;

    public AirMapMarker(Context context, AirMapMarkerManager airMapMarkerManager) {
        super(context);
        this.markerHue = 0.0f;
        this.rotation = 0.0f;
        this.flat = false;
        this.draggable = false;
        this.zIndex = 0;
        this.opacity = 1.0f;
        this.tracksViewChanges = true;
        this.tracksViewChangesActive = false;
        this.hasCustomMarkerView = false;
        this.mLogoControllerListener = new BaseControllerListener<ImageInfo>() { // from class: com.airbnb.android.react.maps.AirMapMarker.1
            @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
            public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
                CloseableReference closeableReference;
                Throwable th;
                Bitmap underlyingBitmap;
                try {
                    closeableReference = (CloseableReference) AirMapMarker.this.dataSource.getResult();
                    if (closeableReference != null) {
                        try {
                            CloseableImage closeableImage = (CloseableImage) closeableReference.get();
                            if ((closeableImage instanceof CloseableStaticBitmap) && (underlyingBitmap = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap()) != null) {
                                Bitmap copy = underlyingBitmap.copy(Bitmap.Config.ARGB_8888, true);
                                AirMapMarker.this.iconBitmap = copy;
                                AirMapMarker.this.iconBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(copy);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            AirMapMarker.this.dataSource.close();
                            if (closeableReference != null) {
                                CloseableReference.closeSafely(closeableReference);
                            }
                            throw th;
                        }
                    }
                    AirMapMarker.this.dataSource.close();
                    if (closeableReference != null) {
                        CloseableReference.closeSafely(closeableReference);
                    }
                    if (AirMapMarker.this.markerManager != null && AirMapMarker.this.imageUri != null) {
                        AirMapMarker.this.markerManager.getSharedIcon(AirMapMarker.this.imageUri).updateIcon(AirMapMarker.this.iconBitmapDescriptor, AirMapMarker.this.iconBitmap);
                    }
                    AirMapMarker.this.update(true);
                } catch (Throwable th3) {
                    closeableReference = null;
                    th = th3;
                }
            }
        };
        this.mLastBitmapCreated = null;
        this.context = context;
        this.markerManager = airMapMarkerManager;
        DraweeHolder<?> create = DraweeHolder.create(createDraweeHierarchy(), context);
        this.logoHolder = create;
        create.onAttach();
    }

    public AirMapMarker(Context context, MarkerOptions markerOptions, AirMapMarkerManager airMapMarkerManager) {
        super(context);
        this.markerHue = 0.0f;
        this.rotation = 0.0f;
        this.flat = false;
        this.draggable = false;
        this.zIndex = 0;
        this.opacity = 1.0f;
        this.tracksViewChanges = true;
        this.tracksViewChangesActive = false;
        this.hasCustomMarkerView = false;
        this.mLogoControllerListener = new BaseControllerListener<ImageInfo>() { // from class: com.airbnb.android.react.maps.AirMapMarker.1
            @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
            public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
                CloseableReference closeableReference;
                Throwable th;
                Bitmap underlyingBitmap;
                try {
                    closeableReference = (CloseableReference) AirMapMarker.this.dataSource.getResult();
                    if (closeableReference != null) {
                        try {
                            CloseableImage closeableImage = (CloseableImage) closeableReference.get();
                            if ((closeableImage instanceof CloseableStaticBitmap) && (underlyingBitmap = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap()) != null) {
                                Bitmap copy = underlyingBitmap.copy(Bitmap.Config.ARGB_8888, true);
                                AirMapMarker.this.iconBitmap = copy;
                                AirMapMarker.this.iconBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(copy);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            AirMapMarker.this.dataSource.close();
                            if (closeableReference != null) {
                                CloseableReference.closeSafely(closeableReference);
                            }
                            throw th;
                        }
                    }
                    AirMapMarker.this.dataSource.close();
                    if (closeableReference != null) {
                        CloseableReference.closeSafely(closeableReference);
                    }
                    if (AirMapMarker.this.markerManager != null && AirMapMarker.this.imageUri != null) {
                        AirMapMarker.this.markerManager.getSharedIcon(AirMapMarker.this.imageUri).updateIcon(AirMapMarker.this.iconBitmapDescriptor, AirMapMarker.this.iconBitmap);
                    }
                    AirMapMarker.this.update(true);
                } catch (Throwable th3) {
                    closeableReference = null;
                    th = th3;
                }
            }
        };
        this.mLastBitmapCreated = null;
        this.context = context;
        this.markerManager = airMapMarkerManager;
        DraweeHolder<?> create = DraweeHolder.create(createDraweeHierarchy(), context);
        this.logoHolder = create;
        create.onAttach();
        this.position = markerOptions.getPosition();
        setAnchor(markerOptions.getAnchorU(), markerOptions.getAnchorV());
        setCalloutAnchor(markerOptions.getInfoWindowAnchorU(), markerOptions.getInfoWindowAnchorV());
        setTitle(markerOptions.getTitle());
        setSnippet(markerOptions.getSnippet());
        setRotation(markerOptions.getRotation());
        setFlat(markerOptions.isFlat());
        setDraggable(markerOptions.isDraggable());
        setZIndex(Math.round(markerOptions.getZIndex()));
        setAlpha(markerOptions.getAlpha());
        this.iconBitmapDescriptor = markerOptions.getIcon();
    }

    private GenericDraweeHierarchy createDraweeHierarchy() {
        return new GenericDraweeHierarchyBuilder(getResources()).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setFadeDuration(0).build();
    }

    public void setCoordinate(ReadableMap readableMap) {
        LatLng latLng = new LatLng(readableMap.getDouble("latitude"), readableMap.getDouble("longitude"));
        this.position = latLng;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setPosition(latLng);
        }
        update(false);
    }

    public void setIdentifier(String str) {
        this.identifier = str;
        update(false);
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setTitle(String str) {
        this.title = str;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setTitle(str);
        }
        update(false);
    }

    public void setSnippet(String str) {
        this.snippet = str;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setSnippet(str);
        }
        update(false);
    }

    @Override // android.view.View
    public void setRotation(float f) {
        this.rotation = f;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setRotation(f);
        }
        update(false);
    }

    public void setFlat(boolean z) {
        this.flat = z;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setFlat(z);
        }
        update(false);
    }

    public void setDraggable(boolean z) {
        this.draggable = z;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setDraggable(z);
        }
        update(false);
    }

    public void setZIndex(int r2) {
        this.zIndex = r2;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setZIndex(r2);
        }
        update(false);
    }

    public void setOpacity(float f) {
        this.opacity = f;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setAlpha(f);
        }
        update(false);
    }

    public void setMarkerHue(float f) {
        this.markerHue = f;
        update(false);
    }

    public void setAnchor(double d, double d2) {
        this.anchorIsSet = true;
        float f = (float) d;
        this.anchorX = f;
        float f2 = (float) d2;
        this.anchorY = f2;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setAnchor(f, f2);
        }
        update(false);
    }

    public void setCalloutAnchor(double d, double d2) {
        this.calloutAnchorIsSet = true;
        float f = (float) d;
        this.calloutAnchorX = f;
        float f2 = (float) d2;
        this.calloutAnchorY = f2;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setInfoWindowAnchor(f, f2);
        }
        update(false);
    }

    public void setTracksViewChanges(boolean z) {
        this.tracksViewChanges = z;
        updateTracksViewChanges();
    }

    private void updateTracksViewChanges() {
        boolean z = this.tracksViewChanges && this.hasCustomMarkerView && this.marker != null;
        if (z == this.tracksViewChangesActive) {
            return;
        }
        this.tracksViewChangesActive = z;
        if (z) {
            ViewChangesTracker.getInstance().addMarker(this);
            return;
        }
        ViewChangesTracker.getInstance().removeMarker(this);
        updateMarkerIcon();
    }

    public boolean updateCustomForTracking() {
        if (this.tracksViewChangesActive) {
            updateMarkerIcon();
            return true;
        }
        return false;
    }

    public void updateMarkerIcon() {
        Marker marker = this.marker;
        if (marker == null) {
            return;
        }
        marker.setIcon(getIcon());
    }

    public LatLng interpolate(float f, LatLng latLng, LatLng latLng2) {
        double d = f;
        return new LatLng(((latLng2.latitude - latLng.latitude) * d) + latLng.latitude, ((latLng2.longitude - latLng.longitude) * d) + latLng.longitude);
    }

    public void animateToCoodinate(LatLng latLng, Integer num) {
        TypeEvaluator<LatLng> typeEvaluator = new TypeEvaluator<LatLng>() { // from class: com.airbnb.android.react.maps.AirMapMarker.2
            @Override // android.animation.TypeEvaluator
            public LatLng evaluate(float f, LatLng latLng2, LatLng latLng3) {
                return AirMapMarker.this.interpolate(f, latLng2, latLng3);
            }
        };
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.marker, Property.of(Marker.class, LatLng.class, ViewProps.POSITION), typeEvaluator, latLng);
        ofObject.setDuration(num.intValue());
        ofObject.start();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setImage(java.lang.String r6) {
        /*
            r5 = this;
            com.airbnb.android.react.maps.AirMapMarkerManager r0 = r5.markerManager
            r1 = 1
            if (r0 == 0) goto L27
            java.lang.String r2 = r5.imageUri
            if (r2 == 0) goto L17
            com.airbnb.android.react.maps.AirMapMarkerManager$AirMapMarkerSharedIcon r0 = r0.getSharedIcon(r2)
            r0.removeMarker(r5)
            com.airbnb.android.react.maps.AirMapMarkerManager r0 = r5.markerManager
            java.lang.String r2 = r5.imageUri
            r0.removeSharedIconIfEmpty(r2)
        L17:
            if (r6 == 0) goto L27
            com.airbnb.android.react.maps.AirMapMarkerManager r0 = r5.markerManager
            com.airbnb.android.react.maps.AirMapMarkerManager$AirMapMarkerSharedIcon r0 = r0.getSharedIcon(r6)
            r0.addMarker(r5)
            boolean r0 = r0.shouldLoadImage()
            goto L28
        L27:
            r0 = 1
        L28:
            r5.imageUri = r6
            if (r0 != 0) goto L2d
            return
        L2d:
            if (r6 != 0) goto L37
            r6 = 0
            r5.iconBitmapDescriptor = r6
            r5.update(r1)
            goto Lf4
        L37:
            java.lang.String r0 = "http://"
            boolean r0 = r6.startsWith(r0)
            if (r0 != 0) goto Lb7
            java.lang.String r0 = "https://"
            boolean r0 = r6.startsWith(r0)
            if (r0 != 0) goto Lb7
            java.lang.String r0 = "file://"
            boolean r0 = r6.startsWith(r0)
            if (r0 != 0) goto Lb7
            java.lang.String r0 = "asset://"
            boolean r0 = r6.startsWith(r0)
            if (r0 != 0) goto Lb7
            java.lang.String r0 = "data:"
            boolean r0 = r6.startsWith(r0)
            if (r0 == 0) goto L60
            goto Lb7
        L60:
            com.google.android.gms.maps.model.BitmapDescriptor r0 = r5.getBitmapDescriptorByName(r6)
            r5.iconBitmapDescriptor = r0
            int r0 = r5.getDrawableResourceByName(r6)
            android.content.res.Resources r2 = r5.getResources()
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeResource(r2, r0)
            r5.iconBitmap = r2
            if (r2 != 0) goto La4
            android.content.res.Resources r2 = r5.getResources()
            android.graphics.drawable.Drawable r0 = r2.getDrawable(r0)
            int r2 = r0.getIntrinsicWidth()
            int r3 = r0.getIntrinsicHeight()
            android.graphics.Bitmap$Config r4 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r2, r3, r4)
            r5.iconBitmap = r2
            int r2 = r0.getIntrinsicWidth()
            int r3 = r0.getIntrinsicHeight()
            r4 = 0
            r0.setBounds(r4, r4, r2, r3)
            android.graphics.Canvas r2 = new android.graphics.Canvas
            android.graphics.Bitmap r3 = r5.iconBitmap
            r2.<init>(r3)
            r0.draw(r2)
        La4:
            com.airbnb.android.react.maps.AirMapMarkerManager r0 = r5.markerManager
            if (r0 == 0) goto Lb3
            com.airbnb.android.react.maps.AirMapMarkerManager$AirMapMarkerSharedIcon r6 = r0.getSharedIcon(r6)
            com.google.android.gms.maps.model.BitmapDescriptor r0 = r5.iconBitmapDescriptor
            android.graphics.Bitmap r2 = r5.iconBitmap
            r6.updateIcon(r0, r2)
        Lb3:
            r5.update(r1)
            goto Lf4
        Lb7:
            android.net.Uri r6 = android.net.Uri.parse(r6)
            com.facebook.imagepipeline.request.ImageRequestBuilder r6 = com.facebook.imagepipeline.request.ImageRequestBuilder.newBuilderWithSource(r6)
            com.facebook.imagepipeline.request.ImageRequest r6 = r6.build()
            com.facebook.imagepipeline.core.ImagePipeline r0 = com.facebook.drawee.backends.pipeline.Fresco.getImagePipeline()
            com.facebook.datasource.DataSource r0 = r0.fetchDecodedImage(r6, r5)
            r5.dataSource = r0
            com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder r0 = com.facebook.drawee.backends.pipeline.Fresco.newDraweeControllerBuilder()
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r6 = r0.setImageRequest(r6)
            com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder r6 = (com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder) r6
            com.facebook.drawee.controller.ControllerListener<com.facebook.imagepipeline.image.ImageInfo> r0 = r5.mLogoControllerListener
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r6 = r6.setControllerListener(r0)
            com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder r6 = (com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder) r6
            com.facebook.drawee.view.DraweeHolder<?> r0 = r5.logoHolder
            com.facebook.drawee.interfaces.DraweeController r0 = r0.getController()
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r6 = r6.setOldController(r0)
            com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder r6 = (com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder) r6
            com.facebook.drawee.controller.AbstractDraweeController r6 = r6.build()
            com.facebook.drawee.view.DraweeHolder<?> r0 = r5.logoHolder
            r0.setController(r6)
        Lf4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.AirMapMarker.setImage(java.lang.String):void");
    }

    public void setIconBitmapDescriptor(BitmapDescriptor bitmapDescriptor, Bitmap bitmap) {
        this.iconBitmapDescriptor = bitmapDescriptor;
        this.iconBitmap = bitmap;
        update(true);
    }

    public void setIconBitmap(Bitmap bitmap) {
        this.iconBitmap = bitmap;
    }

    public MarkerOptions getMarkerOptions() {
        if (this.markerOptions == null) {
            this.markerOptions = new MarkerOptions();
        }
        fillMarkerOptions(this.markerOptions);
        return this.markerOptions;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int r2) {
        super.addView(view, r2);
        if (!(view instanceof AirMapCallout)) {
            this.hasCustomMarkerView = true;
            updateTracksViewChanges();
        }
        update(true);
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        if (getChildCount() == 0 && this.hasCustomMarkerView) {
            this.hasCustomMarkerView = false;
            clearDrawableCache();
            updateTracksViewChanges();
            update(true);
        }
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public Object getFeature() {
        return this.marker;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void addToMap(GoogleMap googleMap) {
        this.marker = googleMap.addMarker(getMarkerOptions());
        updateTracksViewChanges();
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void removeFromMap(GoogleMap googleMap) {
        Marker marker = this.marker;
        if (marker == null) {
            return;
        }
        marker.remove();
        this.marker = null;
        updateTracksViewChanges();
    }

    private BitmapDescriptor getIcon() {
        if (this.hasCustomMarkerView) {
            if (this.iconBitmapDescriptor != null) {
                Bitmap createDrawable = createDrawable();
                Bitmap createBitmap = Bitmap.createBitmap(Math.max(this.iconBitmap.getWidth(), createDrawable.getWidth()), Math.max(this.iconBitmap.getHeight(), createDrawable.getHeight()), this.iconBitmap.getConfig());
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawBitmap(this.iconBitmap, 0.0f, 0.0f, (Paint) null);
                canvas.drawBitmap(createDrawable, 0.0f, 0.0f, (Paint) null);
                return BitmapDescriptorFactory.fromBitmap(createBitmap);
            }
            return BitmapDescriptorFactory.fromBitmap(createDrawable());
        }
        BitmapDescriptor bitmapDescriptor = this.iconBitmapDescriptor;
        return bitmapDescriptor != null ? bitmapDescriptor : BitmapDescriptorFactory.defaultMarker(this.markerHue);
    }

    private MarkerOptions fillMarkerOptions(MarkerOptions markerOptions) {
        markerOptions.position(this.position);
        if (this.anchorIsSet) {
            markerOptions.anchor(this.anchorX, this.anchorY);
        }
        if (this.calloutAnchorIsSet) {
            markerOptions.infoWindowAnchor(this.calloutAnchorX, this.calloutAnchorY);
        }
        markerOptions.title(this.title);
        markerOptions.snippet(this.snippet);
        markerOptions.rotation(this.rotation);
        markerOptions.flat(this.flat);
        markerOptions.draggable(this.draggable);
        markerOptions.zIndex(this.zIndex);
        markerOptions.alpha(this.opacity);
        markerOptions.icon(getIcon());
        return markerOptions;
    }

    public void update(boolean z) {
        if (this.marker == null) {
            return;
        }
        if (z) {
            updateMarkerIcon();
        }
        if (this.anchorIsSet) {
            this.marker.setAnchor(this.anchorX, this.anchorY);
        } else {
            this.marker.setAnchor(0.5f, 1.0f);
        }
        if (this.calloutAnchorIsSet) {
            this.marker.setInfoWindowAnchor(this.calloutAnchorX, this.calloutAnchorY);
        } else {
            this.marker.setInfoWindowAnchor(0.5f, 0.0f);
        }
    }

    public void update(int r1, int r2) {
        this.width = r1;
        this.height = r2;
        update(true);
    }

    private void clearDrawableCache() {
        this.mLastBitmapCreated = null;
    }

    private Bitmap createDrawable() {
        int r0 = this.width;
        if (r0 <= 0) {
            r0 = 100;
        }
        int r2 = this.height;
        int r1 = r2 > 0 ? r2 : 100;
        buildDrawingCache();
        Bitmap bitmap = this.mLastBitmapCreated;
        if (bitmap == null || bitmap.isRecycled() || bitmap.getWidth() != r0 || bitmap.getHeight() != r1) {
            bitmap = Bitmap.createBitmap(r0, r1, Bitmap.Config.ARGB_8888);
            this.mLastBitmapCreated = bitmap;
        } else {
            bitmap.eraseColor(0);
        }
        draw(new Canvas(bitmap));
        return bitmap;
    }

    public void setCalloutView(AirMapCallout airMapCallout) {
        this.calloutView = airMapCallout;
    }

    public AirMapCallout getCalloutView() {
        return this.calloutView;
    }

    public View getCallout() {
        if (this.calloutView == null) {
            return null;
        }
        if (this.wrappedCalloutView == null) {
            wrapCalloutView();
        }
        if (this.calloutView.getTooltip()) {
            return this.wrappedCalloutView;
        }
        return null;
    }

    public View getInfoContents() {
        if (this.calloutView == null) {
            return null;
        }
        if (this.wrappedCalloutView == null) {
            wrapCalloutView();
        }
        if (this.calloutView.getTooltip()) {
            return null;
        }
        return this.wrappedCalloutView;
    }

    private void wrapCalloutView() {
        AirMapCallout airMapCallout = this.calloutView;
        if (airMapCallout == null || airMapCallout.getChildCount() == 0) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(this.context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(this.calloutView.width, this.calloutView.height, 0.0f));
        LinearLayout linearLayout2 = new LinearLayout(this.context);
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(this.calloutView.width, this.calloutView.height, 0.0f));
        linearLayout.addView(linearLayout2);
        linearLayout2.addView(this.calloutView);
        this.wrappedCalloutView = linearLayout;
    }

    private int getDrawableResourceByName(String str) {
        return getResources().getIdentifier(str, "drawable", getContext().getPackageName());
    }

    private BitmapDescriptor getBitmapDescriptorByName(String str) {
        return BitmapDescriptorFactory.fromResource(getDrawableResourceByName(str));
    }
}
