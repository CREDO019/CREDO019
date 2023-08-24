package com.google.maps.android.data.geojson;

import android.util.Log;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class GeoJsonParser {
    private static final String BOUNDING_BOX = "bbox";
    private static final String FEATURE = "Feature";
    private static final String FEATURE_COLLECTION = "FeatureCollection";
    private static final String FEATURE_COLLECTION_ARRAY = "features";
    private static final String FEATURE_GEOMETRY = "geometry";
    private static final String FEATURE_ID = "id";
    private static final String GEOMETRY_COLLECTION = "GeometryCollection";
    private static final String GEOMETRY_COLLECTION_ARRAY = "geometries";
    private static final String GEOMETRY_COORDINATES_ARRAY = "coordinates";
    private static final String LINESTRING = "LineString";
    private static final String LOG_TAG = "GeoJsonParser";
    private static final String MULTILINESTRING = "MultiLineString";
    private static final String MULTIPOINT = "MultiPoint";
    private static final String MULTIPOLYGON = "MultiPolygon";
    private static final String POINT = "Point";
    private static final String POLYGON = "Polygon";
    private static final String PROPERTIES = "properties";
    private final JSONObject mGeoJsonFile;
    private final ArrayList<GeoJsonFeature> mGeoJsonFeatures = new ArrayList<>();
    private LatLngBounds mBoundingBox = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeoJsonParser(JSONObject jSONObject) {
        this.mGeoJsonFile = jSONObject;
        parseGeoJson();
    }

    private static boolean isGeometry(String str) {
        return str.matches("Point|MultiPoint|LineString|MultiLineString|Polygon|MultiPolygon|GeometryCollection");
    }

    private static GeoJsonFeature parseFeature(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String string = jSONObject.has("id") ? jSONObject.getString("id") : null;
            LatLngBounds parseBoundingBox = jSONObject.has(BOUNDING_BOX) ? parseBoundingBox(jSONObject.getJSONArray(BOUNDING_BOX)) : null;
            Geometry parseGeometry = (!jSONObject.has(FEATURE_GEOMETRY) || jSONObject.isNull(FEATURE_GEOMETRY)) ? null : parseGeometry(jSONObject.getJSONObject(FEATURE_GEOMETRY));
            if (jSONObject.has(PROPERTIES) && !jSONObject.isNull(PROPERTIES)) {
                hashMap = parseProperties(jSONObject.getJSONObject(PROPERTIES));
            }
            return new GeoJsonFeature(parseGeometry, string, hashMap, parseBoundingBox);
        } catch (JSONException unused) {
            Log.w(LOG_TAG, "Feature could not be successfully parsed " + jSONObject.toString());
            return null;
        }
    }

    private static LatLngBounds parseBoundingBox(JSONArray jSONArray) throws JSONException {
        return new LatLngBounds(new LatLng(jSONArray.getDouble(1), jSONArray.getDouble(0)), new LatLng(jSONArray.getDouble(3), jSONArray.getDouble(2)));
    }

    private static Geometry parseGeometry(JSONObject jSONObject) {
        String string;
        JSONArray jSONArray;
        try {
            string = jSONObject.getString(SessionDescription.ATTR_TYPE);
        } catch (JSONException unused) {
        }
        if (string.equals(GEOMETRY_COLLECTION)) {
            jSONArray = jSONObject.getJSONArray(GEOMETRY_COLLECTION_ARRAY);
        } else {
            if (isGeometry(string)) {
                jSONArray = jSONObject.getJSONArray(GEOMETRY_COORDINATES_ARRAY);
            }
            return null;
        }
        return createGeometry(string, jSONArray);
    }

    private static GeoJsonFeature parseGeometryToFeature(JSONObject jSONObject) {
        Geometry parseGeometry = parseGeometry(jSONObject);
        if (parseGeometry != null) {
            return new GeoJsonFeature(parseGeometry, null, new HashMap(), null);
        }
        Log.w(LOG_TAG, "Geometry could not be parsed");
        return null;
    }

    private static HashMap<String, String> parseProperties(JSONObject jSONObject) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.isNull(next) ? null : jSONObject.getString(next));
        }
        return hashMap;
    }

    private static Geometry createGeometry(String str, JSONArray jSONArray) throws JSONException {
        if (str.equals(POINT)) {
            return createPoint(jSONArray);
        }
        if (str.equals(MULTIPOINT)) {
            return createMultiPoint(jSONArray);
        }
        if (str.equals(LINESTRING)) {
            return createLineString(jSONArray);
        }
        if (str.equals(MULTILINESTRING)) {
            return createMultiLineString(jSONArray);
        }
        if (str.equals("Polygon")) {
            return createPolygon(jSONArray);
        }
        if (str.equals(MULTIPOLYGON)) {
            return createMultiPolygon(jSONArray);
        }
        if (str.equals(GEOMETRY_COLLECTION)) {
            return createGeometryCollection(jSONArray);
        }
        return null;
    }

    private static GeoJsonPoint createPoint(JSONArray jSONArray) throws JSONException {
        return new GeoJsonPoint(parseCoordinate(jSONArray));
    }

    private static GeoJsonMultiPoint createMultiPoint(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int r1 = 0; r1 < jSONArray.length(); r1++) {
            arrayList.add(createPoint(jSONArray.getJSONArray(r1)));
        }
        return new GeoJsonMultiPoint(arrayList);
    }

    private static GeoJsonLineString createLineString(JSONArray jSONArray) throws JSONException {
        return new GeoJsonLineString(parseCoordinatesArray(jSONArray));
    }

    private static GeoJsonMultiLineString createMultiLineString(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int r1 = 0; r1 < jSONArray.length(); r1++) {
            arrayList.add(createLineString(jSONArray.getJSONArray(r1)));
        }
        return new GeoJsonMultiLineString(arrayList);
    }

    private static GeoJsonPolygon createPolygon(JSONArray jSONArray) throws JSONException {
        return new GeoJsonPolygon(parseCoordinatesArrays(jSONArray));
    }

    private static GeoJsonMultiPolygon createMultiPolygon(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int r1 = 0; r1 < jSONArray.length(); r1++) {
            arrayList.add(createPolygon(jSONArray.getJSONArray(r1)));
        }
        return new GeoJsonMultiPolygon(arrayList);
    }

    private static GeoJsonGeometryCollection createGeometryCollection(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int r1 = 0; r1 < jSONArray.length(); r1++) {
            Geometry parseGeometry = parseGeometry(jSONArray.getJSONObject(r1));
            if (parseGeometry != null) {
                arrayList.add(parseGeometry);
            }
        }
        return new GeoJsonGeometryCollection(arrayList);
    }

    private static LatLng parseCoordinate(JSONArray jSONArray) throws JSONException {
        return new LatLng(jSONArray.getDouble(1), jSONArray.getDouble(0));
    }

    private static ArrayList<LatLng> parseCoordinatesArray(JSONArray jSONArray) throws JSONException {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        for (int r1 = 0; r1 < jSONArray.length(); r1++) {
            arrayList.add(parseCoordinate(jSONArray.getJSONArray(r1)));
        }
        return arrayList;
    }

    private static ArrayList<ArrayList<LatLng>> parseCoordinatesArrays(JSONArray jSONArray) throws JSONException {
        ArrayList<ArrayList<LatLng>> arrayList = new ArrayList<>();
        for (int r1 = 0; r1 < jSONArray.length(); r1++) {
            arrayList.add(parseCoordinatesArray(jSONArray.getJSONArray(r1)));
        }
        return arrayList;
    }

    private void parseGeoJson() {
        try {
            String string = this.mGeoJsonFile.getString(SessionDescription.ATTR_TYPE);
            if (string.equals(FEATURE)) {
                GeoJsonFeature parseFeature = parseFeature(this.mGeoJsonFile);
                if (parseFeature != null) {
                    this.mGeoJsonFeatures.add(parseFeature);
                }
            } else if (string.equals(FEATURE_COLLECTION)) {
                this.mGeoJsonFeatures.addAll(parseFeatureCollection(this.mGeoJsonFile));
            } else if (isGeometry(string)) {
                GeoJsonFeature parseGeometryToFeature = parseGeometryToFeature(this.mGeoJsonFile);
                if (parseGeometryToFeature != null) {
                    this.mGeoJsonFeatures.add(parseGeometryToFeature);
                }
            } else {
                Log.w(LOG_TAG, "GeoJSON file could not be parsed.");
            }
        } catch (JSONException unused) {
            Log.w(LOG_TAG, "GeoJSON file could not be parsed.");
        }
    }

    private ArrayList<GeoJsonFeature> parseFeatureCollection(JSONObject jSONObject) {
        ArrayList<GeoJsonFeature> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(FEATURE_COLLECTION_ARRAY);
            if (jSONObject.has(BOUNDING_BOX)) {
                this.mBoundingBox = parseBoundingBox(jSONObject.getJSONArray(BOUNDING_BOX));
            }
            for (int r8 = 0; r8 < jSONArray.length(); r8++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(r8);
                    if (jSONObject2.getString(SessionDescription.ATTR_TYPE).equals(FEATURE)) {
                        GeoJsonFeature parseFeature = parseFeature(jSONObject2);
                        if (parseFeature != null) {
                            arrayList.add(parseFeature);
                        } else {
                            Log.w(LOG_TAG, "Index of Feature in Feature Collection that could not be created: " + r8);
                        }
                    }
                } catch (JSONException unused) {
                    Log.w(LOG_TAG, "Index of Feature in Feature Collection that could not be created: " + r8);
                }
            }
            return arrayList;
        } catch (JSONException unused2) {
            Log.w(LOG_TAG, "Feature Collection could not be created.");
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<GeoJsonFeature> getFeatures() {
        return this.mGeoJsonFeatures;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LatLngBounds getBoundingBox() {
        return this.mBoundingBox;
    }
}
