package com.google.maps.android.data.kml;

import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class KmlFeatureParser {
    private static final String BOUNDARY_REGEX = "outerBoundaryIs|innerBoundaryIs";
    private static final String COMPASS_REGEX = "north|south|east|west";
    private static final String EXTENDED_DATA = "ExtendedData";
    private static final String GEOMETRY_REGEX = "Point|LineString|Polygon|MultiGeometry";
    private static final int LATITUDE_INDEX = 1;
    private static final int LONGITUDE_INDEX = 0;
    private static final String PROPERTY_REGEX = "name|description|drawOrder|visibility|open|address|phoneNumber";
    private static final String STYLE_TAG = "Style";
    private static final String STYLE_URL_TAG = "styleUrl";

    KmlFeatureParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KmlPlacemark createPlacemark(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        HashMap hashMap = new HashMap();
        int eventType = xmlPullParser.getEventType();
        Geometry geometry = null;
        String str = null;
        KmlStyle kmlStyle = null;
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("Placemark")) {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals(STYLE_URL_TAG)) {
                        str = xmlPullParser.nextText();
                    } else if (xmlPullParser.getName().matches(GEOMETRY_REGEX)) {
                        geometry = createGeometry(xmlPullParser, xmlPullParser.getName());
                    } else if (xmlPullParser.getName().matches(PROPERTY_REGEX)) {
                        hashMap.put(xmlPullParser.getName(), xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals(EXTENDED_DATA)) {
                        hashMap.putAll(setExtendedDataProperties(xmlPullParser));
                    } else if (xmlPullParser.getName().equals(STYLE_TAG)) {
                        kmlStyle = KmlStyleParser.createStyle(xmlPullParser);
                    }
                }
                eventType = xmlPullParser.next();
            } else {
                return new KmlPlacemark(geometry, str, kmlStyle, hashMap);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KmlGroundOverlay createGroundOverlay(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        int eventType = xmlPullParser.getEventType();
        String str = null;
        float f = 0.0f;
        int r6 = 1;
        float f2 = 0.0f;
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("GroundOverlay")) {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals("Icon")) {
                        str = getImageUrl(xmlPullParser);
                    } else if (xmlPullParser.getName().equals("drawOrder")) {
                        f = Float.parseFloat(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals("visibility")) {
                        r6 = Integer.parseInt(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals(EXTENDED_DATA)) {
                        hashMap.putAll(setExtendedDataProperties(xmlPullParser));
                    } else if (xmlPullParser.getName().equals(ViewProps.ROTATION)) {
                        f2 = getRotation(xmlPullParser);
                    } else if (xmlPullParser.getName().matches(PROPERTY_REGEX) || xmlPullParser.getName().equals("color")) {
                        hashMap.put(xmlPullParser.getName(), xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().matches(COMPASS_REGEX)) {
                        hashMap2.put(xmlPullParser.getName(), Double.valueOf(Double.parseDouble(xmlPullParser.nextText())));
                    }
                }
                eventType = xmlPullParser.next();
            } else {
                return new KmlGroundOverlay(str, createLatLngBounds((Double) hashMap2.get("north"), (Double) hashMap2.get("south"), (Double) hashMap2.get("east"), (Double) hashMap2.get("west")), f, r6, hashMap, f2);
            }
        }
    }

    private static float getRotation(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        return -Float.parseFloat(xmlPullParser.nextText());
    }

    private static String getImageUrl(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Icon")) {
                return null;
            }
            if (eventType == 2 && xmlPullParser.getName().equals("href")) {
                return xmlPullParser.nextText();
            }
            eventType = xmlPullParser.next();
        }
    }

    private static Geometry createGeometry(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals(str)) {
                return null;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("Point")) {
                    return createPoint(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("LineString")) {
                    return createLineString(xmlPullParser);
                }
                if (xmlPullParser.getName().equals(KmlPolygon.GEOMETRY_TYPE)) {
                    return createPolygon(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("MultiGeometry")) {
                    return createMultiGeometry(xmlPullParser);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static HashMap<String, String> setExtendedDataProperties(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        int eventType = xmlPullParser.getEventType();
        String str = null;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals(EXTENDED_DATA)) {
                return hashMap;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("Data")) {
                    str = xmlPullParser.getAttributeValue(null, "name");
                } else if (xmlPullParser.getName().equals("value") && str != null) {
                    hashMap.put(str, xmlPullParser.nextText());
                    str = null;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static KmlPoint createPoint(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        LatLng latLng = null;
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("Point")) {
                if (eventType == 2 && xmlPullParser.getName().equals("coordinates")) {
                    latLng = convertToLatLng(xmlPullParser.nextText());
                }
                eventType = xmlPullParser.next();
            } else {
                return new KmlPoint(latLng);
            }
        }
    }

    private static KmlLineString createLineString(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("LineString")) {
                if (eventType == 2 && xmlPullParser.getName().equals("coordinates")) {
                    arrayList = convertToLatLngArray(xmlPullParser.nextText());
                }
                eventType = xmlPullParser.next();
            } else {
                return new KmlLineString(arrayList);
            }
        }
    }

    private static KmlPolygon createPolygon(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Boolean bool = false;
        ArrayList<LatLng> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals(KmlPolygon.GEOMETRY_TYPE)) {
                if (eventType == 2) {
                    if (xmlPullParser.getName().matches(BOUNDARY_REGEX)) {
                        bool = Boolean.valueOf(xmlPullParser.getName().equals("outerBoundaryIs"));
                    } else if (xmlPullParser.getName().equals("coordinates")) {
                        if (bool.booleanValue()) {
                            arrayList = convertToLatLngArray(xmlPullParser.nextText());
                        } else {
                            arrayList2.add(convertToLatLngArray(xmlPullParser.nextText()));
                        }
                    }
                }
                eventType = xmlPullParser.next();
            } else {
                return new KmlPolygon(arrayList, arrayList2);
            }
        }
    }

    private static KmlMultiGeometry createMultiGeometry(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        int next = xmlPullParser.next();
        while (true) {
            if (next != 3 || !xmlPullParser.getName().equals("MultiGeometry")) {
                if (next == 2 && xmlPullParser.getName().matches(GEOMETRY_REGEX)) {
                    arrayList.add(createGeometry(xmlPullParser, xmlPullParser.getName()));
                }
                next = xmlPullParser.next();
            } else {
                return new KmlMultiGeometry(arrayList);
            }
        }
    }

    private static ArrayList<LatLng> convertToLatLngArray(String str) {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        for (String str2 : str.trim().split("(\\s+)")) {
            arrayList.add(convertToLatLng(str2));
        }
        return arrayList;
    }

    private static LatLng convertToLatLng(String str) {
        String[] split = str.split(",");
        return new LatLng(Double.valueOf(Double.parseDouble(split[1])).doubleValue(), Double.valueOf(Double.parseDouble(split[0])).doubleValue());
    }

    private static LatLngBounds createLatLngBounds(Double d, Double d2, Double d3, Double d4) {
        return new LatLngBounds(new LatLng(d2.doubleValue(), d4.doubleValue()), new LatLng(d.doubleValue(), d3.doubleValue()));
    }
}
