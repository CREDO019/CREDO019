package com.amplitude.api;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IngestionMetadata {
    private static final String TAG = "com.amplitude.api.IngestionMetadata";
    private String sourceName;
    private String sourceVersion;

    public IngestionMetadata setSourceName(String str) {
        this.sourceName = str;
        return this;
    }

    public IngestionMetadata setSourceVersion(String str) {
        this.sourceVersion = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!C1060Utils.isEmptyString(this.sourceName)) {
                jSONObject.put(Constants.AMP_INGESTION_METADATA_SOURCE_NAME, this.sourceName);
            }
            if (!C1060Utils.isEmptyString(this.sourceVersion)) {
                jSONObject.put(Constants.AMP_INGESTION_METADATA_SOURCE_VERSION, this.sourceVersion);
            }
        } catch (JSONException unused) {
            AmplitudeLog.getLogger().m1368e(TAG, "JSON Serialization of ingestion metadata object failed");
        }
        return jSONObject;
    }
}
