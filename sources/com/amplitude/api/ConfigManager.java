package com.amplitude.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ConfigManager {
    private static String KEY_INGESTION_ENDPOINT = "ingestionEndpoint";
    private static ConfigManager instance;
    private String ingestionEndpoint = Constants.EVENT_LOG_URL;

    /* loaded from: classes.dex */
    interface RefreshListener {
        void onFinished();
    }

    public String getIngestionEndpoint() {
        return this.ingestionEndpoint;
    }

    private ConfigManager() {
    }

    public void refresh(RefreshListener refreshListener, AmplitudeServerZone amplitudeServerZone) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(AmplitudeServerZone.getDynamicConfigApi(amplitudeServerZone)).openConnection();
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                bufferedReader.close();
                JSONObject jSONObject = new JSONObject(stringBuffer.toString());
                if (jSONObject.has(KEY_INGESTION_ENDPOINT)) {
                    this.ingestionEndpoint = "https://" + jSONObject.getString(KEY_INGESTION_ENDPOINT);
                }
            }
        } catch (MalformedURLException | IOException | JSONException | Exception unused) {
        }
        refreshListener.onFinished();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
}
