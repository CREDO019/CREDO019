package com.google.maps.android.data.kml;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.Layer;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes3.dex */
public class KmlLayer extends Layer {
    public KmlLayer(GoogleMap googleMap, int r3, Context context) throws XmlPullParserException, IOException {
        this(googleMap, context.getResources().openRawResource(r3), context);
    }

    public KmlLayer(GoogleMap googleMap, InputStream inputStream, Context context) throws XmlPullParserException, IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("KML InputStream cannot be null");
        }
        KmlRenderer kmlRenderer = new KmlRenderer(googleMap, context);
        KmlParser kmlParser = new KmlParser(createXmlParser(inputStream));
        kmlParser.parseKml();
        inputStream.close();
        kmlRenderer.storeKmlData(kmlParser.getStyles(), kmlParser.getStyleMaps(), kmlParser.getPlacemarks(), kmlParser.getContainers(), kmlParser.getGroundOverlays());
        storeRenderer(kmlRenderer);
    }

    private static XmlPullParser createXmlParser(InputStream inputStream) throws XmlPullParserException {
        XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
        newInstance.setNamespaceAware(true);
        XmlPullParser newPullParser = newInstance.newPullParser();
        newPullParser.setInput(inputStream, null);
        return newPullParser;
    }

    public void addLayerToMap() throws IOException, XmlPullParserException {
        super.addKMLToMap();
    }

    public boolean hasPlacemarks() {
        return hasFeatures();
    }

    public Iterable<KmlPlacemark> getPlacemarks() {
        return getFeatures();
    }

    @Override // com.google.maps.android.data.Layer
    public boolean hasContainers() {
        return super.hasContainers();
    }

    @Override // com.google.maps.android.data.Layer
    public Iterable<KmlContainer> getContainers() {
        return super.getContainers();
    }

    @Override // com.google.maps.android.data.Layer
    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        return super.getGroundOverlays();
    }
}
