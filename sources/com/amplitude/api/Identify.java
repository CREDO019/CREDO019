package com.amplitude.api;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Identify {
    private static final String TAG = "com.amplitude.api.Identify";
    protected JSONObject userPropertiesOperations = new JSONObject();
    protected Set<String> userProperties = new HashSet();

    public Identify setOnce(String str, boolean z) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, Boolean.valueOf(z));
        return this;
    }

    public Identify setOnce(String str, double d) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, Double.valueOf(d));
        return this;
    }

    public Identify setOnce(String str, float f) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, Float.valueOf(f));
        return this;
    }

    public Identify setOnce(String str, int r3) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, Integer.valueOf(r3));
        return this;
    }

    public Identify setOnce(String str, long j) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, Long.valueOf(j));
        return this;
    }

    public Identify setOnce(String str, String str2) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, str2);
        return this;
    }

    public Identify setOnce(String str, JSONArray jSONArray) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, jSONArray);
        return this;
    }

    public Identify setOnce(String str, JSONObject jSONObject) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, jSONObject);
        return this;
    }

    public Identify setOnce(String str, boolean[] zArr) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, booleanArrayToJSONArray(zArr));
        return this;
    }

    public Identify setOnce(String str, double[] dArr) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, doubleArrayToJSONArray(dArr));
        return this;
    }

    public Identify setOnce(String str, float[] fArr) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, floatArrayToJSONArray(fArr));
        return this;
    }

    public Identify setOnce(String str, int[] r3) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, intArrayToJSONArray(r3));
        return this;
    }

    public Identify setOnce(String str, long[] jArr) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, longArrayToJSONArray(jArr));
        return this;
    }

    public Identify setOnce(String str, String[] strArr) {
        addToUserProperties(Constants.AMP_OP_SET_ONCE, str, stringArrayToJSONArray(strArr));
        return this;
    }

    public Identify set(String str, boolean z) {
        addToUserProperties("$set", str, Boolean.valueOf(z));
        return this;
    }

    public Identify set(String str, double d) {
        addToUserProperties("$set", str, Double.valueOf(d));
        return this;
    }

    public Identify set(String str, float f) {
        addToUserProperties("$set", str, Float.valueOf(f));
        return this;
    }

    public Identify set(String str, int r3) {
        addToUserProperties("$set", str, Integer.valueOf(r3));
        return this;
    }

    public Identify set(String str, long j) {
        addToUserProperties("$set", str, Long.valueOf(j));
        return this;
    }

    public Identify set(String str, String str2) {
        addToUserProperties("$set", str, str2);
        return this;
    }

    public Identify set(String str, JSONObject jSONObject) {
        addToUserProperties("$set", str, jSONObject);
        return this;
    }

    public Identify set(String str, JSONArray jSONArray) {
        addToUserProperties("$set", str, jSONArray);
        return this;
    }

    public Identify set(String str, boolean[] zArr) {
        addToUserProperties("$set", str, booleanArrayToJSONArray(zArr));
        return this;
    }

    public Identify set(String str, double[] dArr) {
        addToUserProperties("$set", str, doubleArrayToJSONArray(dArr));
        return this;
    }

    public Identify set(String str, float[] fArr) {
        addToUserProperties("$set", str, floatArrayToJSONArray(fArr));
        return this;
    }

    public Identify set(String str, int[] r3) {
        addToUserProperties("$set", str, intArrayToJSONArray(r3));
        return this;
    }

    public Identify set(String str, long[] jArr) {
        addToUserProperties("$set", str, longArrayToJSONArray(jArr));
        return this;
    }

    public Identify set(String str, String[] strArr) {
        addToUserProperties("$set", str, stringArrayToJSONArray(strArr));
        return this;
    }

    public Identify add(String str, double d) {
        addToUserProperties(Constants.AMP_OP_ADD, str, Double.valueOf(d));
        return this;
    }

    public Identify add(String str, float f) {
        addToUserProperties(Constants.AMP_OP_ADD, str, Float.valueOf(f));
        return this;
    }

    public Identify add(String str, int r3) {
        addToUserProperties(Constants.AMP_OP_ADD, str, Integer.valueOf(r3));
        return this;
    }

    public Identify add(String str, long j) {
        addToUserProperties(Constants.AMP_OP_ADD, str, Long.valueOf(j));
        return this;
    }

    public Identify add(String str, String str2) {
        addToUserProperties(Constants.AMP_OP_ADD, str, str2);
        return this;
    }

    public Identify add(String str, JSONObject jSONObject) {
        addToUserProperties(Constants.AMP_OP_ADD, str, jSONObject);
        return this;
    }

    public Identify append(String str, boolean z) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, Boolean.valueOf(z));
        return this;
    }

    public Identify append(String str, double d) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, Double.valueOf(d));
        return this;
    }

    public Identify append(String str, float f) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, Float.valueOf(f));
        return this;
    }

    public Identify append(String str, int r3) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, Integer.valueOf(r3));
        return this;
    }

    public Identify append(String str, long j) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, Long.valueOf(j));
        return this;
    }

    public Identify append(String str, String str2) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, str2);
        return this;
    }

    public Identify append(String str, JSONArray jSONArray) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, jSONArray);
        return this;
    }

    public Identify append(String str, JSONObject jSONObject) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, jSONObject);
        return this;
    }

    public Identify append(String str, boolean[] zArr) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, booleanArrayToJSONArray(zArr));
        return this;
    }

    public Identify append(String str, double[] dArr) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, doubleArrayToJSONArray(dArr));
        return this;
    }

    public Identify append(String str, float[] fArr) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, floatArrayToJSONArray(fArr));
        return this;
    }

    public Identify append(String str, int[] r3) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, intArrayToJSONArray(r3));
        return this;
    }

    public Identify append(String str, long[] jArr) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, longArrayToJSONArray(jArr));
        return this;
    }

    public Identify append(String str, String[] strArr) {
        addToUserProperties(Constants.AMP_OP_APPEND, str, stringArrayToJSONArray(strArr));
        return this;
    }

    public Identify prepend(String str, boolean z) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, Boolean.valueOf(z));
        return this;
    }

    public Identify prepend(String str, double d) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, Double.valueOf(d));
        return this;
    }

    public Identify prepend(String str, float f) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, Float.valueOf(f));
        return this;
    }

    public Identify prepend(String str, int r3) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, Integer.valueOf(r3));
        return this;
    }

    public Identify prepend(String str, long j) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, Long.valueOf(j));
        return this;
    }

    public Identify prepend(String str, String str2) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, str2);
        return this;
    }

    public Identify prepend(String str, JSONArray jSONArray) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, jSONArray);
        return this;
    }

    public Identify prepend(String str, JSONObject jSONObject) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, jSONObject);
        return this;
    }

    public Identify prepend(String str, boolean[] zArr) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, booleanArrayToJSONArray(zArr));
        return this;
    }

    public Identify prepend(String str, double[] dArr) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, doubleArrayToJSONArray(dArr));
        return this;
    }

    public Identify prepend(String str, float[] fArr) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, floatArrayToJSONArray(fArr));
        return this;
    }

    public Identify prepend(String str, int[] r3) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, intArrayToJSONArray(r3));
        return this;
    }

    public Identify prepend(String str, long[] jArr) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, longArrayToJSONArray(jArr));
        return this;
    }

    public Identify prepend(String str, String[] strArr) {
        addToUserProperties(Constants.AMP_OP_PREPEND, str, stringArrayToJSONArray(strArr));
        return this;
    }

    public Identify unset(String str) {
        addToUserProperties("$unset", str, "-");
        return this;
    }

    public Identify clearAll() {
        if (this.userPropertiesOperations.length() > 0) {
            if (!this.userProperties.contains("$clearAll")) {
                AmplitudeLog.getLogger().m1362w(TAG, String.format("Need to send $clearAll on its own Identify object without any other operations, ignoring $clearAll", new Object[0]));
            }
            return this;
        }
        try {
            this.userPropertiesOperations.put("$clearAll", "-");
        } catch (JSONException e) {
            AmplitudeLog.getLogger().m1368e(TAG, e.toString());
        }
        return this;
    }

    public Identify preInsert(String str, boolean z) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, Boolean.valueOf(z));
        return this;
    }

    public Identify preInsert(String str, double d) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, Double.valueOf(d));
        return this;
    }

    public Identify preInsert(String str, float f) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, Float.valueOf(f));
        return this;
    }

    public Identify preInsert(String str, int r3) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, Integer.valueOf(r3));
        return this;
    }

    public Identify preInsert(String str, long j) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, Long.valueOf(j));
        return this;
    }

    public Identify preInsert(String str, String str2) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, str2);
        return this;
    }

    public Identify preInsert(String str, JSONArray jSONArray) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, jSONArray);
        return this;
    }

    public Identify preInsert(String str, JSONObject jSONObject) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, jSONObject);
        return this;
    }

    public Identify preInsert(String str, boolean[] zArr) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, booleanArrayToJSONArray(zArr));
        return this;
    }

    public Identify preInsert(String str, double[] dArr) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, doubleArrayToJSONArray(dArr));
        return this;
    }

    public Identify preInsert(String str, float[] fArr) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, floatArrayToJSONArray(fArr));
        return this;
    }

    public Identify preInsert(String str, int[] r3) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, intArrayToJSONArray(r3));
        return this;
    }

    public Identify preInsert(String str, long[] jArr) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, longArrayToJSONArray(jArr));
        return this;
    }

    public Identify preInsert(String str, String[] strArr) {
        addToUserProperties(Constants.AMP_OP_PREINSERT, str, stringArrayToJSONArray(strArr));
        return this;
    }

    public Identify postInsert(String str, boolean z) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, Boolean.valueOf(z));
        return this;
    }

    public Identify postInsert(String str, double d) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, Double.valueOf(d));
        return this;
    }

    public Identify postInsert(String str, float f) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, Float.valueOf(f));
        return this;
    }

    public Identify postInsert(String str, int r3) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, Integer.valueOf(r3));
        return this;
    }

    public Identify postInsert(String str, long j) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, Long.valueOf(j));
        return this;
    }

    public Identify postInsert(String str, String str2) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, str2);
        return this;
    }

    public Identify postInsert(String str, JSONArray jSONArray) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, jSONArray);
        return this;
    }

    public Identify postInsert(String str, JSONObject jSONObject) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, jSONObject);
        return this;
    }

    public Identify postInsert(String str, boolean[] zArr) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, booleanArrayToJSONArray(zArr));
        return this;
    }

    public Identify postInsert(String str, double[] dArr) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, doubleArrayToJSONArray(dArr));
        return this;
    }

    public Identify postInsert(String str, float[] fArr) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, floatArrayToJSONArray(fArr));
        return this;
    }

    public Identify postInsert(String str, int[] r3) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, intArrayToJSONArray(r3));
        return this;
    }

    public Identify postInsert(String str, long[] jArr) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, longArrayToJSONArray(jArr));
        return this;
    }

    public Identify postInsert(String str, String[] strArr) {
        addToUserProperties(Constants.AMP_OP_POSTINSERT, str, stringArrayToJSONArray(strArr));
        return this;
    }

    public Identify remove(String str, boolean z) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, Boolean.valueOf(z));
        return this;
    }

    public Identify remove(String str, double d) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, Double.valueOf(d));
        return this;
    }

    public Identify remove(String str, float f) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, Float.valueOf(f));
        return this;
    }

    public Identify remove(String str, int r3) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, Integer.valueOf(r3));
        return this;
    }

    public Identify remove(String str, long j) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, Long.valueOf(j));
        return this;
    }

    public Identify remove(String str, String str2) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, str2);
        return this;
    }

    public Identify remove(String str, JSONArray jSONArray) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, jSONArray);
        return this;
    }

    public Identify remove(String str, JSONObject jSONObject) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, jSONObject);
        return this;
    }

    public Identify remove(String str, boolean[] zArr) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, booleanArrayToJSONArray(zArr));
        return this;
    }

    public Identify remove(String str, double[] dArr) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, doubleArrayToJSONArray(dArr));
        return this;
    }

    public Identify remove(String str, float[] fArr) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, floatArrayToJSONArray(fArr));
        return this;
    }

    public Identify remove(String str, int[] r3) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, intArrayToJSONArray(r3));
        return this;
    }

    public Identify remove(String str, long[] jArr) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, longArrayToJSONArray(jArr));
        return this;
    }

    public Identify remove(String str, String[] strArr) {
        addToUserProperties(Constants.AMP_OP_REMOVE, str, stringArrayToJSONArray(strArr));
        return this;
    }

    private void addToUserProperties(String str, String str2, Object obj) {
        if (C1060Utils.isEmptyString(str2)) {
            AmplitudeLog.getLogger().m1362w(TAG, String.format("Attempting to perform operation %s with a null or empty string property, ignoring", str));
        } else if (obj == null) {
            AmplitudeLog.getLogger().m1362w(TAG, String.format("Attempting to perform operation %s with null value for property %s, ignoring", str, str2));
        } else if (this.userPropertiesOperations.has("$clearAll")) {
            AmplitudeLog.getLogger().m1362w(TAG, String.format("This Identify already contains a $clearAll operation, ignoring operation %s", str));
        } else if (this.userProperties.contains(str2)) {
            AmplitudeLog.getLogger().m1362w(TAG, String.format("Already used property %s in previous operation, ignoring operation %s", str2, str));
        } else {
            try {
                if (!this.userPropertiesOperations.has(str)) {
                    this.userPropertiesOperations.put(str, new JSONObject());
                }
                this.userPropertiesOperations.getJSONObject(str).put(str2, obj);
                this.userProperties.add(str2);
            } catch (JSONException e) {
                AmplitudeLog.getLogger().m1368e(TAG, e.toString());
            }
        }
    }

    private JSONArray booleanArrayToJSONArray(boolean[] zArr) {
        JSONArray jSONArray = new JSONArray();
        for (boolean z : zArr) {
            jSONArray.put(z);
        }
        return jSONArray;
    }

    private JSONArray floatArrayToJSONArray(float[] fArr) {
        JSONArray jSONArray = new JSONArray();
        for (float f : fArr) {
            try {
                jSONArray.put(f);
            } catch (JSONException e) {
                AmplitudeLog.getLogger().m1368e(TAG, String.format("Error converting float %f to JSON: %s", Float.valueOf(f), e.toString()));
            }
        }
        return jSONArray;
    }

    private JSONArray doubleArrayToJSONArray(double[] dArr) {
        JSONArray jSONArray = new JSONArray();
        for (double d : dArr) {
            try {
                jSONArray.put(d);
            } catch (JSONException e) {
                AmplitudeLog.getLogger().m1368e(TAG, String.format("Error converting double %d to JSON: %s", Double.valueOf(d), e.toString()));
            }
        }
        return jSONArray;
    }

    private JSONArray intArrayToJSONArray(int[] r5) {
        JSONArray jSONArray = new JSONArray();
        for (int r3 : r5) {
            jSONArray.put(r3);
        }
        return jSONArray;
    }

    private JSONArray longArrayToJSONArray(long[] jArr) {
        JSONArray jSONArray = new JSONArray();
        for (long j : jArr) {
            jSONArray.put(j);
        }
        return jSONArray;
    }

    private JSONArray stringArrayToJSONArray(String[] strArr) {
        JSONArray jSONArray = new JSONArray();
        for (String str : strArr) {
            jSONArray.put(str);
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Identify setUserProperty(String str, Object obj) {
        addToUserProperties("$set", str, obj);
        return this;
    }

    public Identify setOnce(String str, Object obj) {
        AmplitudeLog.getLogger().m1362w(TAG, "This version of setOnce is deprecated. Please use one with a different signature.");
        return this;
    }

    public Identify set(String str, Object obj) {
        AmplitudeLog.getLogger().m1362w(TAG, "This version of set is deprecated. Please use one with a different signature.");
        return this;
    }

    public JSONObject getUserPropertiesOperations() {
        try {
            return new JSONObject(this.userPropertiesOperations.toString());
        } catch (JSONException e) {
            AmplitudeLog.getLogger().m1368e(TAG, e.toString());
            return new JSONObject();
        }
    }
}
