package com.onesignal;

import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.messaging.Constants;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OSNotification {
    private List<ActionButton> actionButtons;
    private JSONObject additionalData;
    private int androidNotificationId;
    private BackgroundImageLayout backgroundImageLayout;
    private String bigPicture;
    private String body;
    private String collapseId;
    private String fromProjectNumber;
    private String groupKey;
    private String groupMessage;
    private List<OSNotification> groupedNotifications;
    private String largeIcon;
    private String launchURL;
    private String ledColor;
    private int lockScreenVisibility;
    private NotificationCompat.Extender notificationExtender;
    private String notificationId;
    private int priority;
    private String rawPayload;
    private long sentTime;
    private String smallIcon;
    private String smallIconAccentColor;
    private String sound;
    private String templateId;
    private String templateName;
    private String title;
    private int ttl;

    protected OSNotification() {
        this.lockScreenVisibility = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSNotification(JSONObject jSONObject) {
        this(null, jSONObject, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSNotification(List<OSNotification> list, JSONObject jSONObject, int r4) {
        this.lockScreenVisibility = 1;
        initPayloadData(jSONObject);
        this.groupedNotifications = list;
        this.androidNotificationId = r4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OSNotification(OSNotification oSNotification) {
        this.lockScreenVisibility = 1;
        this.notificationExtender = oSNotification.notificationExtender;
        this.groupedNotifications = oSNotification.groupedNotifications;
        this.androidNotificationId = oSNotification.androidNotificationId;
        this.notificationId = oSNotification.notificationId;
        this.templateName = oSNotification.templateName;
        this.templateId = oSNotification.templateId;
        this.title = oSNotification.title;
        this.body = oSNotification.body;
        this.additionalData = oSNotification.additionalData;
        this.smallIcon = oSNotification.smallIcon;
        this.largeIcon = oSNotification.largeIcon;
        this.bigPicture = oSNotification.bigPicture;
        this.smallIconAccentColor = oSNotification.smallIconAccentColor;
        this.launchURL = oSNotification.launchURL;
        this.sound = oSNotification.sound;
        this.ledColor = oSNotification.ledColor;
        this.lockScreenVisibility = oSNotification.lockScreenVisibility;
        this.groupKey = oSNotification.groupKey;
        this.groupMessage = oSNotification.groupMessage;
        this.actionButtons = oSNotification.actionButtons;
        this.fromProjectNumber = oSNotification.fromProjectNumber;
        this.backgroundImageLayout = oSNotification.backgroundImageLayout;
        this.collapseId = oSNotification.collapseId;
        this.priority = oSNotification.priority;
        this.rawPayload = oSNotification.rawPayload;
        this.sentTime = oSNotification.sentTime;
        this.ttl = oSNotification.ttl;
    }

    private void initPayloadData(JSONObject jSONObject) {
        try {
            JSONObject customJSONObject = NotificationBundleProcessor.getCustomJSONObject(jSONObject);
            long currentTimeMillis = OneSignal.getTime().getCurrentTimeMillis();
            if (jSONObject.has(Constants.MessagePayloadKeys.TTL)) {
                this.sentTime = jSONObject.optLong(Constants.MessagePayloadKeys.SENT_TIME, currentTimeMillis) / 1000;
                this.ttl = jSONObject.optInt(Constants.MessagePayloadKeys.TTL, 259200);
            } else if (jSONObject.has(OneSignalHmsEventBridge.HMS_TTL_KEY)) {
                this.sentTime = jSONObject.optLong(OneSignalHmsEventBridge.HMS_SENT_TIME_KEY, currentTimeMillis) / 1000;
                this.ttl = jSONObject.optInt(OneSignalHmsEventBridge.HMS_TTL_KEY, 259200);
            } else {
                this.sentTime = currentTimeMillis / 1000;
                this.ttl = 259200;
            }
            this.notificationId = customJSONObject.optString("i");
            this.templateId = customJSONObject.optString("ti");
            this.templateName = customJSONObject.optString("tn");
            this.rawPayload = jSONObject.toString();
            this.additionalData = customJSONObject.optJSONObject(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY);
            this.launchURL = customJSONObject.optString("u", null);
            this.body = jSONObject.optString("alert", null);
            this.title = jSONObject.optString("title", null);
            this.smallIcon = jSONObject.optString("sicon", null);
            this.bigPicture = jSONObject.optString("bicon", null);
            this.largeIcon = jSONObject.optString("licon", null);
            this.sound = jSONObject.optString("sound", null);
            this.groupKey = jSONObject.optString("grp", null);
            this.groupMessage = jSONObject.optString("grp_msg", null);
            this.smallIconAccentColor = jSONObject.optString("bgac", null);
            this.ledColor = jSONObject.optString("ledc", null);
            String optString = jSONObject.optString("vis", null);
            if (optString != null) {
                this.lockScreenVisibility = Integer.parseInt(optString);
            }
            this.fromProjectNumber = jSONObject.optString(Constants.MessagePayloadKeys.FROM, null);
            this.priority = jSONObject.optInt("pri", 0);
            String optString2 = jSONObject.optString(Constants.MessagePayloadKeys.COLLAPSE_KEY, null);
            if (!"do_not_collapse".equals(optString2)) {
                this.collapseId = optString2;
            }
            try {
                setActionButtons();
            } catch (Throwable th) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error assigning OSNotificationReceivedEvent.actionButtons values!", th);
            }
            try {
                setBackgroundImageLayout(jSONObject);
            } catch (Throwable th2) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error assigning OSNotificationReceivedEvent.backgroundImageLayout values!", th2);
            }
        } catch (Throwable th3) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error assigning OSNotificationReceivedEvent payload values!", th3);
        }
    }

    private void setActionButtons() throws Throwable {
        JSONObject jSONObject = this.additionalData;
        if (jSONObject == null || !jSONObject.has("actionButtons")) {
            return;
        }
        JSONArray jSONArray = this.additionalData.getJSONArray("actionButtons");
        this.actionButtons = new ArrayList();
        for (int r2 = 0; r2 < jSONArray.length(); r2++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(r2);
            ActionButton actionButton = new ActionButton();
            actionButton.f1301id = jSONObject2.optString("id", null);
            actionButton.text = jSONObject2.optString("text", null);
            actionButton.icon = jSONObject2.optString("icon", null);
            this.actionButtons.add(actionButton);
        }
        this.additionalData.remove(GenerateNotification.BUNDLE_KEY_ACTION_ID);
        this.additionalData.remove("actionButtons");
    }

    private void setBackgroundImageLayout(JSONObject jSONObject) throws Throwable {
        String optString = jSONObject.optString("bg_img", null);
        if (optString != null) {
            JSONObject jSONObject2 = new JSONObject(optString);
            BackgroundImageLayout backgroundImageLayout = new BackgroundImageLayout();
            this.backgroundImageLayout = backgroundImageLayout;
            backgroundImageLayout.image = jSONObject2.optString("img");
            this.backgroundImageLayout.titleTextColor = jSONObject2.optString("tc");
            this.backgroundImageLayout.bodyTextColor = jSONObject2.optString("bc");
        }
    }

    public OSMutableNotification mutableCopy() {
        return new OSMutableNotification(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSNotification copy() {
        return new OSNotificationBuilder().setNotificationExtender(this.notificationExtender).setGroupedNotifications(this.groupedNotifications).setAndroidNotificationId(this.androidNotificationId).setNotificationId(this.notificationId).setTemplateName(this.templateName).setTemplateId(this.templateId).setTitle(this.title).setBody(this.body).setAdditionalData(this.additionalData).setSmallIcon(this.smallIcon).setLargeIcon(this.largeIcon).setBigPicture(this.bigPicture).setSmallIconAccentColor(this.smallIconAccentColor).setLaunchURL(this.launchURL).setSound(this.sound).setLedColor(this.ledColor).setLockScreenVisibility(this.lockScreenVisibility).setGroupKey(this.groupKey).setGroupMessage(this.groupMessage).setActionButtons(this.actionButtons).setFromProjectNumber(this.fromProjectNumber).setBackgroundImageLayout(this.backgroundImageLayout).setCollapseId(this.collapseId).setPriority(this.priority).setRawPayload(this.rawPayload).setSenttime(this.sentTime).setTTL(this.ttl).build();
    }

    public NotificationCompat.Extender getNotificationExtender() {
        return this.notificationExtender;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setNotificationExtender(NotificationCompat.Extender extender) {
        this.notificationExtender = extender;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasNotificationId() {
        return this.androidNotificationId != 0;
    }

    public int getAndroidNotificationId() {
        return this.androidNotificationId;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAndroidNotificationId(int r1) {
        this.androidNotificationId = r1;
    }

    public List<OSNotification> getGroupedNotifications() {
        return this.groupedNotifications;
    }

    void setGroupedNotifications(List<OSNotification> list) {
        this.groupedNotifications = list;
    }

    public String getNotificationId() {
        return this.notificationId;
    }

    void setNotificationId(String str) {
        this.notificationId = str;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    void setTemplateName(String str) {
        this.templateName = str;
    }

    public String getTemplateId() {
        return this.templateId;
    }

    void setTemplateId(String str) {
        this.templateId = str;
    }

    public String getTitle() {
        return this.title;
    }

    void setTitle(String str) {
        this.title = str;
    }

    public String getBody() {
        return this.body;
    }

    void setBody(String str) {
        this.body = str;
    }

    public JSONObject getAdditionalData() {
        return this.additionalData;
    }

    void setAdditionalData(JSONObject jSONObject) {
        this.additionalData = jSONObject;
    }

    public String getSmallIcon() {
        return this.smallIcon;
    }

    void setSmallIcon(String str) {
        this.smallIcon = str;
    }

    public String getLargeIcon() {
        return this.largeIcon;
    }

    void setLargeIcon(String str) {
        this.largeIcon = str;
    }

    public String getBigPicture() {
        return this.bigPicture;
    }

    void setBigPicture(String str) {
        this.bigPicture = str;
    }

    public String getSmallIconAccentColor() {
        return this.smallIconAccentColor;
    }

    void setSmallIconAccentColor(String str) {
        this.smallIconAccentColor = str;
    }

    public String getLaunchURL() {
        return this.launchURL;
    }

    void setLaunchURL(String str) {
        this.launchURL = str;
    }

    public String getSound() {
        return this.sound;
    }

    void setSound(String str) {
        this.sound = str;
    }

    public String getLedColor() {
        return this.ledColor;
    }

    void setLedColor(String str) {
        this.ledColor = str;
    }

    public int getLockScreenVisibility() {
        return this.lockScreenVisibility;
    }

    void setLockScreenVisibility(int r1) {
        this.lockScreenVisibility = r1;
    }

    public String getGroupKey() {
        return this.groupKey;
    }

    void setGroupKey(String str) {
        this.groupKey = str;
    }

    public String getGroupMessage() {
        return this.groupMessage;
    }

    void setGroupMessage(String str) {
        this.groupMessage = str;
    }

    public List<ActionButton> getActionButtons() {
        return this.actionButtons;
    }

    void setActionButtons(List<ActionButton> list) {
        this.actionButtons = list;
    }

    public String getFromProjectNumber() {
        return this.fromProjectNumber;
    }

    void setFromProjectNumber(String str) {
        this.fromProjectNumber = str;
    }

    public BackgroundImageLayout getBackgroundImageLayout() {
        return this.backgroundImageLayout;
    }

    void setBackgroundImageLayout(BackgroundImageLayout backgroundImageLayout) {
        this.backgroundImageLayout = backgroundImageLayout;
    }

    public String getCollapseId() {
        return this.collapseId;
    }

    void setCollapseId(String str) {
        this.collapseId = str;
    }

    public int getPriority() {
        return this.priority;
    }

    void setPriority(int r1) {
        this.priority = r1;
    }

    public String getRawPayload() {
        return this.rawPayload;
    }

    void setRawPayload(String str) {
        this.rawPayload = str;
    }

    public long getSentTime() {
        return this.sentTime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSentTime(long j) {
        this.sentTime = j;
    }

    public int getTtl() {
        return this.ttl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTtl(int r1) {
        this.ttl = r1;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(GenerateNotification.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, this.androidNotificationId);
            JSONArray jSONArray = new JSONArray();
            List<OSNotification> list = this.groupedNotifications;
            if (list != null) {
                for (OSNotification oSNotification : list) {
                    jSONArray.put(oSNotification.toJSONObject());
                }
            }
            jSONObject.put("groupedNotifications", jSONArray);
            jSONObject.put("notificationId", this.notificationId);
            jSONObject.put("templateName", this.templateName);
            jSONObject.put("templateId", this.templateId);
            jSONObject.put("title", this.title);
            jSONObject.put(TtmlNode.TAG_BODY, this.body);
            jSONObject.put("smallIcon", this.smallIcon);
            jSONObject.put("largeIcon", this.largeIcon);
            jSONObject.put("bigPicture", this.bigPicture);
            jSONObject.put("smallIconAccentColor", this.smallIconAccentColor);
            jSONObject.put("launchURL", this.launchURL);
            jSONObject.put("sound", this.sound);
            jSONObject.put("ledColor", this.ledColor);
            jSONObject.put("lockScreenVisibility", this.lockScreenVisibility);
            jSONObject.put("groupKey", this.groupKey);
            jSONObject.put("groupMessage", this.groupMessage);
            jSONObject.put("fromProjectNumber", this.fromProjectNumber);
            jSONObject.put("collapseId", this.collapseId);
            jSONObject.put("priority", this.priority);
            JSONObject jSONObject2 = this.additionalData;
            if (jSONObject2 != null) {
                jSONObject.put("additionalData", jSONObject2);
            }
            if (this.actionButtons != null) {
                JSONArray jSONArray2 = new JSONArray();
                for (ActionButton actionButton : this.actionButtons) {
                    jSONArray2.put(actionButton.toJSONObject());
                }
                jSONObject.put("actionButtons", jSONArray2);
            }
            jSONObject.put("rawPayload", this.rawPayload);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return "OSNotification{notificationExtender=" + this.notificationExtender + ", groupedNotifications=" + this.groupedNotifications + ", androidNotificationId=" + this.androidNotificationId + ", notificationId='" + this.notificationId + "', templateName='" + this.templateName + "', templateId='" + this.templateId + "', title='" + this.title + "', body='" + this.body + "', additionalData=" + this.additionalData + ", smallIcon='" + this.smallIcon + "', largeIcon='" + this.largeIcon + "', bigPicture='" + this.bigPicture + "', smallIconAccentColor='" + this.smallIconAccentColor + "', launchURL='" + this.launchURL + "', sound='" + this.sound + "', ledColor='" + this.ledColor + "', lockScreenVisibility=" + this.lockScreenVisibility + ", groupKey='" + this.groupKey + "', groupMessage='" + this.groupMessage + "', actionButtons=" + this.actionButtons + ", fromProjectNumber='" + this.fromProjectNumber + "', backgroundImageLayout=" + this.backgroundImageLayout + ", collapseId='" + this.collapseId + "', priority=" + this.priority + ", rawPayload='" + this.rawPayload + "'}";
    }

    /* loaded from: classes3.dex */
    public static class ActionButton {
        private String icon;

        /* renamed from: id */
        private String f1301id;
        private String text;

        public ActionButton() {
        }

        public ActionButton(JSONObject jSONObject) {
            this.f1301id = jSONObject.optString("id");
            this.text = jSONObject.optString("text");
            this.icon = jSONObject.optString("icon");
        }

        public ActionButton(String str, String str2, String str3) {
            this.f1301id = str;
            this.text = str2;
            this.icon = str3;
        }

        public JSONObject toJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", this.f1301id);
                jSONObject.put("text", this.text);
                jSONObject.put("icon", this.icon);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return jSONObject;
        }

        public String getId() {
            return this.f1301id;
        }

        public String getText() {
            return this.text;
        }

        public String getIcon() {
            return this.icon;
        }
    }

    /* loaded from: classes3.dex */
    public static class BackgroundImageLayout {
        private String bodyTextColor;
        private String image;
        private String titleTextColor;

        public String getImage() {
            return this.image;
        }

        public String getTitleTextColor() {
            return this.titleTextColor;
        }

        public String getBodyTextColor() {
            return this.bodyTextColor;
        }
    }

    /* loaded from: classes3.dex */
    public static class OSNotificationBuilder {
        private List<ActionButton> actionButtons;
        private JSONObject additionalData;
        private int androidNotificationId;
        private BackgroundImageLayout backgroundImageLayout;
        private String bigPicture;
        private String body;
        private String collapseId;
        private String fromProjectNumber;
        private String groupKey;
        private String groupMessage;
        private List<OSNotification> groupedNotifications;
        private String largeIcon;
        private String launchURL;
        private String ledColor;
        private int lockScreenVisibility = 1;
        private NotificationCompat.Extender notificationExtender;
        private String notificationId;
        private int priority;
        private String rawPayload;
        private long sentTime;
        private String smallIcon;
        private String smallIconAccentColor;
        private String sound;
        private String templateId;
        private String templateName;
        private String title;
        private int ttl;

        public OSNotificationBuilder setNotificationExtender(NotificationCompat.Extender extender) {
            this.notificationExtender = extender;
            return this;
        }

        public OSNotificationBuilder setGroupedNotifications(List<OSNotification> list) {
            this.groupedNotifications = list;
            return this;
        }

        public OSNotificationBuilder setAndroidNotificationId(int r1) {
            this.androidNotificationId = r1;
            return this;
        }

        public OSNotificationBuilder setNotificationId(String str) {
            this.notificationId = str;
            return this;
        }

        public OSNotificationBuilder setTemplateName(String str) {
            this.templateName = str;
            return this;
        }

        public OSNotificationBuilder setTemplateId(String str) {
            this.templateId = str;
            return this;
        }

        public OSNotificationBuilder setTitle(String str) {
            this.title = str;
            return this;
        }

        public OSNotificationBuilder setBody(String str) {
            this.body = str;
            return this;
        }

        public OSNotificationBuilder setAdditionalData(JSONObject jSONObject) {
            this.additionalData = jSONObject;
            return this;
        }

        public OSNotificationBuilder setSmallIcon(String str) {
            this.smallIcon = str;
            return this;
        }

        public OSNotificationBuilder setLargeIcon(String str) {
            this.largeIcon = str;
            return this;
        }

        public OSNotificationBuilder setBigPicture(String str) {
            this.bigPicture = str;
            return this;
        }

        public OSNotificationBuilder setSmallIconAccentColor(String str) {
            this.smallIconAccentColor = str;
            return this;
        }

        public OSNotificationBuilder setLaunchURL(String str) {
            this.launchURL = str;
            return this;
        }

        public OSNotificationBuilder setSound(String str) {
            this.sound = str;
            return this;
        }

        public OSNotificationBuilder setLedColor(String str) {
            this.ledColor = str;
            return this;
        }

        public OSNotificationBuilder setLockScreenVisibility(int r1) {
            this.lockScreenVisibility = r1;
            return this;
        }

        public OSNotificationBuilder setGroupKey(String str) {
            this.groupKey = str;
            return this;
        }

        public OSNotificationBuilder setGroupMessage(String str) {
            this.groupMessage = str;
            return this;
        }

        public OSNotificationBuilder setActionButtons(List<ActionButton> list) {
            this.actionButtons = list;
            return this;
        }

        public OSNotificationBuilder setFromProjectNumber(String str) {
            this.fromProjectNumber = str;
            return this;
        }

        public OSNotificationBuilder setBackgroundImageLayout(BackgroundImageLayout backgroundImageLayout) {
            this.backgroundImageLayout = backgroundImageLayout;
            return this;
        }

        public OSNotificationBuilder setCollapseId(String str) {
            this.collapseId = str;
            return this;
        }

        public OSNotificationBuilder setPriority(int r1) {
            this.priority = r1;
            return this;
        }

        public OSNotificationBuilder setRawPayload(String str) {
            this.rawPayload = str;
            return this;
        }

        public OSNotificationBuilder setSenttime(long j) {
            this.sentTime = j;
            return this;
        }

        public OSNotificationBuilder setTTL(int r1) {
            this.ttl = r1;
            return this;
        }

        public OSNotification build() {
            OSNotification oSNotification = new OSNotification();
            oSNotification.setNotificationExtender(this.notificationExtender);
            oSNotification.setGroupedNotifications(this.groupedNotifications);
            oSNotification.setAndroidNotificationId(this.androidNotificationId);
            oSNotification.setNotificationId(this.notificationId);
            oSNotification.setTemplateName(this.templateName);
            oSNotification.setTemplateId(this.templateId);
            oSNotification.setTitle(this.title);
            oSNotification.setBody(this.body);
            oSNotification.setAdditionalData(this.additionalData);
            oSNotification.setSmallIcon(this.smallIcon);
            oSNotification.setLargeIcon(this.largeIcon);
            oSNotification.setBigPicture(this.bigPicture);
            oSNotification.setSmallIconAccentColor(this.smallIconAccentColor);
            oSNotification.setLaunchURL(this.launchURL);
            oSNotification.setSound(this.sound);
            oSNotification.setLedColor(this.ledColor);
            oSNotification.setLockScreenVisibility(this.lockScreenVisibility);
            oSNotification.setGroupKey(this.groupKey);
            oSNotification.setGroupMessage(this.groupMessage);
            oSNotification.setActionButtons(this.actionButtons);
            oSNotification.setFromProjectNumber(this.fromProjectNumber);
            oSNotification.setBackgroundImageLayout(this.backgroundImageLayout);
            oSNotification.setCollapseId(this.collapseId);
            oSNotification.setPriority(this.priority);
            oSNotification.setRawPayload(this.rawPayload);
            oSNotification.setSentTime(this.sentTime);
            oSNotification.setTtl(this.ttl);
            return oSNotification;
        }
    }
}
