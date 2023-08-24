package com.amplitude.api;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum AmplitudeServerZone {
    US,
    EU;
    
    private static Map<AmplitudeServerZone, String> amplitudeServerZoneEventLogApiMap = new HashMap<AmplitudeServerZone, String>() { // from class: com.amplitude.api.AmplitudeServerZone.1
        {
            put(AmplitudeServerZone.US, Constants.EVENT_LOG_URL);
            put(AmplitudeServerZone.EU, Constants.EVENT_LOG_EU_URL);
        }
    };
    private static Map<AmplitudeServerZone, String> amplitudeServerZoneDynamicConfigMap = new HashMap<AmplitudeServerZone, String>() { // from class: com.amplitude.api.AmplitudeServerZone.2
        {
            put(AmplitudeServerZone.US, Constants.DYNAMIC_CONFIG_URL);
            put(AmplitudeServerZone.EU, Constants.DYNAMIC_CONFIG_EU_URL);
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getEventLogApiForZone(AmplitudeServerZone amplitudeServerZone) {
        return amplitudeServerZoneEventLogApiMap.containsKey(amplitudeServerZone) ? amplitudeServerZoneEventLogApiMap.get(amplitudeServerZone) : Constants.EVENT_LOG_URL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getDynamicConfigApi(AmplitudeServerZone amplitudeServerZone) {
        return amplitudeServerZoneDynamicConfigMap.containsKey(amplitudeServerZone) ? amplitudeServerZoneDynamicConfigMap.get(amplitudeServerZone) : Constants.DYNAMIC_CONFIG_URL;
    }

    public static AmplitudeServerZone getServerZone(String str) {
        AmplitudeServerZone amplitudeServerZone = US;
        str.hashCode();
        if (str.equals("EU")) {
            return EU;
        }
        str.equals("US");
        return amplitudeServerZone;
    }
}
