package com.onesignal;

import com.onesignal.OneSignal;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class OSInAppMessageRedisplayStats {
    private static final String DISPLAY_DELAY = "delay";
    private static final String DISPLAY_LIMIT = "limit";
    private long displayDelay;
    private int displayLimit;
    private int displayQuantity;
    private long lastDisplayTime;
    private boolean redisplayEnabled;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSInAppMessageRedisplayStats() {
        this.lastDisplayTime = -1L;
        this.displayQuantity = 0;
        this.displayLimit = 1;
        this.displayDelay = 0L;
        this.redisplayEnabled = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSInAppMessageRedisplayStats(int r3, long j) {
        this.displayLimit = 1;
        this.displayDelay = 0L;
        this.redisplayEnabled = false;
        this.displayQuantity = r3;
        this.lastDisplayTime = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSInAppMessageRedisplayStats(JSONObject jSONObject) throws JSONException {
        this.lastDisplayTime = -1L;
        this.displayQuantity = 0;
        this.displayLimit = 1;
        this.displayDelay = 0L;
        this.redisplayEnabled = true;
        Object obj = jSONObject.get(DISPLAY_LIMIT);
        Object obj2 = jSONObject.get(DISPLAY_DELAY);
        if (obj instanceof Integer) {
            this.displayLimit = ((Integer) obj).intValue();
        }
        if (obj2 instanceof Long) {
            this.displayDelay = ((Long) obj2).longValue();
        } else if (obj2 instanceof Integer) {
            this.displayDelay = ((Integer) obj2).intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDisplayStats(OSInAppMessageRedisplayStats oSInAppMessageRedisplayStats) {
        setLastDisplayTime(oSInAppMessageRedisplayStats.getLastDisplayTime());
        setDisplayQuantity(oSInAppMessageRedisplayStats.getDisplayQuantity());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getLastDisplayTime() {
        return this.lastDisplayTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLastDisplayTime(long j) {
        this.lastDisplayTime = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void incrementDisplayQuantity() {
        this.displayQuantity++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDisplayQuantity() {
        return this.displayQuantity;
    }

    void setDisplayQuantity(int r1) {
        this.displayQuantity = r1;
    }

    int getDisplayLimit() {
        return this.displayLimit;
    }

    void setDisplayLimit(int r1) {
        this.displayLimit = r1;
    }

    long getDisplayDelay() {
        return this.displayDelay;
    }

    void setDisplayDelay(long j) {
        this.displayDelay = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldDisplayAgain() {
        boolean z = this.displayQuantity < this.displayLimit;
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "OSInAppMessage shouldDisplayAgain: " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDelayTimeSatisfied() {
        if (this.lastDisplayTime < 0) {
            return true;
        }
        long currentTimeMillis = OneSignal.getTime().getCurrentTimeMillis() / 1000;
        long j = currentTimeMillis - this.lastDisplayTime;
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "OSInAppMessage lastDisplayTime: " + this.lastDisplayTime + " currentTimeInSeconds: " + currentTimeMillis + " diffInSeconds: " + j + " displayDelay: " + this.displayDelay);
        return j >= this.displayDelay;
    }

    public boolean isRedisplayEnabled() {
        return this.redisplayEnabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DISPLAY_LIMIT, this.displayLimit);
            jSONObject.put(DISPLAY_DELAY, this.displayDelay);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return "OSInAppMessageDisplayStats{lastDisplayTime=" + this.lastDisplayTime + ", displayQuantity=" + this.displayQuantity + ", displayLimit=" + this.displayLimit + ", displayDelay=" + this.displayDelay + '}';
    }
}
