package com.onesignal.outcomes.data;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import com.onesignal.OSLogger;
import com.onesignal.OSSharedPreferences;
import com.onesignal.OneSignalDb;
import com.onesignal.influence.domain.OSInfluenceChannel;
import com.onesignal.influence.domain.OSInfluenceType;
import com.onesignal.outcomes.OSOutcomeConstants;
import com.onesignal.outcomes.domain.OSCachedUniqueOutcome;
import com.onesignal.outcomes.domain.OSOutcomeEventParams;
import com.onesignal.outcomes.domain.OSOutcomeSource;
import com.onesignal.outcomes.domain.OSOutcomeSourceBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: OSOutcomeEventsCache.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ(\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J \u0010\u001a\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000eH\u0007J\u0010\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"H\u0007J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0$H\u0007J4\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u000e2\b\u0010,\u001a\u0004\u0018\u00010&H\u0002J$\u0010-\u001a\b\u0012\u0004\u0012\u00020.0$2\u0006\u0010/\u001a\u00020\u000e2\f\u00100\u001a\b\u0012\u0004\u0012\u00020.0$H\u0007J*\u00101\u001a\u0004\u0018\u00010&2\u0006\u00102\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\u001c2\u0006\u00103\u001a\u00020\u000eH\u0002J\u0010\u00104\u001a\u00020\u00122\u0006\u00105\u001a\u00020\"H\u0007J\u0018\u00106\u001a\u00020\u00122\u0010\u00107\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\rJ\u0010\u00108\u001a\u00020\u00122\u0006\u00105\u001a\u00020\"H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u00069"}, m183d2 = {"Lcom/onesignal/outcomes/data/OSOutcomeEventsCache;", "", "logger", "Lcom/onesignal/OSLogger;", "dbHelper", "Lcom/onesignal/OneSignalDb;", "preferences", "Lcom/onesignal/OSSharedPreferences;", "(Lcom/onesignal/OSLogger;Lcom/onesignal/OneSignalDb;Lcom/onesignal/OSSharedPreferences;)V", "isOutcomesV2ServiceEnabled", "", "()Z", "unattributedUniqueOutcomeEventsSentByChannel", "", "", "getUnattributedUniqueOutcomeEventsSentByChannel", "()Ljava/util/Set;", "addIdToListFromChannel", "", "cachedUniqueOutcomes", "", "Lcom/onesignal/outcomes/domain/OSCachedUniqueOutcome;", "channelIds", "Lorg/json/JSONArray;", "channel", "Lcom/onesignal/influence/domain/OSInfluenceChannel;", "addIdsToListFromSource", "sourceBody", "Lcom/onesignal/outcomes/domain/OSOutcomeSourceBody;", "cleanCachedUniqueOutcomeEventNotifications", "notificationTableName", "notificationIdColumnName", "deleteOldOutcomeEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/onesignal/outcomes/domain/OSOutcomeEventParams;", "getAllEventsToSend", "", "getIAMInfluenceSource", "Lcom/onesignal/outcomes/domain/OSOutcomeSource;", "iamInfluenceType", "Lcom/onesignal/influence/domain/OSInfluenceType;", "directSourceBody", "indirectSourceBody", "iamIds", "source", "getNotCachedUniqueInfluencesForOutcome", "Lcom/onesignal/influence/domain/OSInfluence;", "name", "influences", "getNotificationInfluenceSource", "notificationInfluenceType", "notificationIds", "saveOutcomeEvent", "eventParams", "saveUnattributedUniqueOutcomeEventsSentByChannel", "unattributedUniqueOutcomeEvents", "saveUniqueOutcomeEventParams", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* loaded from: classes3.dex */
public final class OSOutcomeEventsCache {
    private final OneSignalDb dbHelper;
    private final OSLogger logger;
    private final OSSharedPreferences preferences;

    @Metadata(m185bv = {1, 0, 3}, m182k = 3, m181mv = {1, 4, 2})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] r0 = new int[OSInfluenceType.values().length];
            $EnumSwitchMapping$0 = r0;
            r0[OSInfluenceType.DIRECT.ordinal()] = 1;
            r0[OSInfluenceType.INDIRECT.ordinal()] = 2;
            int[] r02 = new int[OSInfluenceType.values().length];
            $EnumSwitchMapping$1 = r02;
            r02[OSInfluenceType.DIRECT.ordinal()] = 1;
            r02[OSInfluenceType.INDIRECT.ordinal()] = 2;
        }
    }

    public OSOutcomeEventsCache(OSLogger logger, OneSignalDb dbHelper, OSSharedPreferences preferences) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(dbHelper, "dbHelper");
        Intrinsics.checkNotNullParameter(preferences, "preferences");
        this.logger = logger;
        this.dbHelper = dbHelper;
        this.preferences = preferences;
    }

    public final boolean isOutcomesV2ServiceEnabled() {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        return oSSharedPreferences.getBool(oSSharedPreferences.getPreferencesName(), this.preferences.getOutcomesV2KeyName(), false);
    }

    public final Set<String> getUnattributedUniqueOutcomeEventsSentByChannel() {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        return oSSharedPreferences.getStringSet(oSSharedPreferences.getPreferencesName(), OSOutcomeConstants.PREFS_OS_UNATTRIBUTED_UNIQUE_OUTCOME_EVENTS_SENT, null);
    }

    public final void saveUnattributedUniqueOutcomeEventsSentByChannel(Set<String> set) {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        String preferencesName = oSSharedPreferences.getPreferencesName();
        Intrinsics.checkNotNull(set);
        oSSharedPreferences.saveStringSet(preferencesName, OSOutcomeConstants.PREFS_OS_UNATTRIBUTED_UNIQUE_OUTCOME_EVENTS_SENT, set);
    }

    public final synchronized void deleteOldOutcomeEvent(OSOutcomeEventParams event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.dbHelper.delete("outcome", "timestamp = ?", new String[]{String.valueOf(event.getTimestamp())});
    }

    public final synchronized void saveOutcomeEvent(OSOutcomeEventParams eventParams) {
        OSOutcomeSourceBody indirectBody;
        OSOutcomeSourceBody directBody;
        Intrinsics.checkNotNullParameter(eventParams, "eventParams");
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        OSInfluenceType oSInfluenceType = OSInfluenceType.UNATTRIBUTED;
        OSInfluenceType oSInfluenceType2 = OSInfluenceType.UNATTRIBUTED;
        OSOutcomeSource outcomeSource = eventParams.getOutcomeSource();
        if (outcomeSource != null && (directBody = outcomeSource.getDirectBody()) != null) {
            JSONArray notificationIds = directBody.getNotificationIds();
            if (notificationIds != null && notificationIds.length() > 0) {
                oSInfluenceType = OSInfluenceType.DIRECT;
                jSONArray = notificationIds;
            }
            JSONArray inAppMessagesIds = directBody.getInAppMessagesIds();
            if (inAppMessagesIds != null && inAppMessagesIds.length() > 0) {
                oSInfluenceType2 = OSInfluenceType.DIRECT;
                jSONArray2 = inAppMessagesIds;
            }
        }
        OSOutcomeSource outcomeSource2 = eventParams.getOutcomeSource();
        if (outcomeSource2 != null && (indirectBody = outcomeSource2.getIndirectBody()) != null) {
            JSONArray notificationIds2 = indirectBody.getNotificationIds();
            if (notificationIds2 != null && notificationIds2.length() > 0) {
                oSInfluenceType = OSInfluenceType.INDIRECT;
                jSONArray = notificationIds2;
            }
            JSONArray inAppMessagesIds2 = indirectBody.getInAppMessagesIds();
            if (inAppMessagesIds2 != null && inAppMessagesIds2.length() > 0) {
                oSInfluenceType2 = OSInfluenceType.INDIRECT;
                jSONArray2 = inAppMessagesIds2;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("notification_ids", jSONArray.toString());
        contentValues.put(OutcomeEventsTable.COLUMN_NAME_IAM_IDS, jSONArray2.toString());
        String oSInfluenceType3 = oSInfluenceType.toString();
        if (oSInfluenceType3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = oSInfluenceType3.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
        contentValues.put(OutcomeEventsTable.COLUMN_NAME_NOTIFICATION_INFLUENCE_TYPE, lowerCase);
        String oSInfluenceType4 = oSInfluenceType2.toString();
        if (oSInfluenceType4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase2 = oSInfluenceType4.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "(this as java.lang.String).toLowerCase()");
        contentValues.put(OutcomeEventsTable.COLUMN_NAME_IAM_INFLUENCE_TYPE, lowerCase2);
        contentValues.put("name", eventParams.getOutcomeId());
        contentValues.put("weight", Float.valueOf(eventParams.getWeight()));
        contentValues.put("timestamp", Long.valueOf(eventParams.getTimestamp()));
        this.dbHelper.insert("outcome", null, contentValues);
    }

    public final synchronized List<OSOutcomeEventParams> getAllEventsToSend() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Cursor cursor = null;
        Cursor cursor2 = null;
        try {
            Cursor query = this.dbHelper.query("outcome", null, null, null, null, null, null);
            try {
                if (query.moveToFirst()) {
                    do {
                        OSInfluenceType fromString = OSInfluenceType.Companion.fromString(query.getString(query.getColumnIndex(OutcomeEventsTable.COLUMN_NAME_NOTIFICATION_INFLUENCE_TYPE)));
                        OSInfluenceType fromString2 = OSInfluenceType.Companion.fromString(query.getString(query.getColumnIndex(OutcomeEventsTable.COLUMN_NAME_IAM_INFLUENCE_TYPE)));
                        String string = query.getString(query.getColumnIndex("notification_ids"));
                        if (string == null) {
                            string = HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
                        }
                        String string2 = query.getString(query.getColumnIndex(OutcomeEventsTable.COLUMN_NAME_IAM_IDS));
                        if (string2 == null) {
                            string2 = HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
                        }
                        String str = string2;
                        String name = query.getString(query.getColumnIndex("name"));
                        float f = query.getFloat(query.getColumnIndex("weight"));
                        long j = query.getLong(query.getColumnIndex("timestamp"));
                        try {
                            OSOutcomeSourceBody oSOutcomeSourceBody = new OSOutcomeSourceBody(null, null, 3, null);
                            OSOutcomeSourceBody oSOutcomeSourceBody2 = new OSOutcomeSourceBody(null, null, 3, null);
                            OSOutcomeSource notificationInfluenceSource = getNotificationInfluenceSource(fromString, oSOutcomeSourceBody, oSOutcomeSourceBody2, string);
                            getIAMInfluenceSource(fromString2, oSOutcomeSourceBody, oSOutcomeSourceBody2, str, notificationInfluenceSource);
                            if (notificationInfluenceSource == null) {
                                notificationInfluenceSource = new OSOutcomeSource(null, null);
                            }
                            OSOutcomeSource oSOutcomeSource = notificationInfluenceSource;
                            Intrinsics.checkNotNullExpressionValue(name, "name");
                            arrayList.add(new OSOutcomeEventParams(name, oSOutcomeSource, f, j));
                        } catch (JSONException e) {
                            this.logger.error("Generating JSONArray from notifications ids outcome:JSON Failed.", e);
                        }
                    } while (query.moveToNext());
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                } else if (query != null) {
                    query.close();
                }
            } catch (Throwable th) {
                th = th;
                cursor = query;
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return arrayList;
    }

    private final OSOutcomeSource getNotificationInfluenceSource(OSInfluenceType oSInfluenceType, OSOutcomeSourceBody oSOutcomeSourceBody, OSOutcomeSourceBody oSOutcomeSourceBody2, String str) {
        OSOutcomeSource oSOutcomeSource;
        int r3 = WhenMappings.$EnumSwitchMapping$0[oSInfluenceType.ordinal()];
        if (r3 == 1) {
            oSOutcomeSourceBody.setNotificationIds(new JSONArray(str));
            oSOutcomeSource = new OSOutcomeSource(oSOutcomeSourceBody, null);
        } else if (r3 != 2) {
            return null;
        } else {
            oSOutcomeSourceBody2.setNotificationIds(new JSONArray(str));
            oSOutcomeSource = new OSOutcomeSource(null, oSOutcomeSourceBody2);
        }
        return oSOutcomeSource;
    }

    private final OSOutcomeSource getIAMInfluenceSource(OSInfluenceType oSInfluenceType, OSOutcomeSourceBody oSOutcomeSourceBody, OSOutcomeSourceBody oSOutcomeSourceBody2, String str, OSOutcomeSource oSOutcomeSource) {
        OSOutcomeSource directBody;
        OSOutcomeSource indirectBody;
        int r3 = WhenMappings.$EnumSwitchMapping$1[oSInfluenceType.ordinal()];
        if (r3 == 1) {
            oSOutcomeSourceBody.setInAppMessagesIds(new JSONArray(str));
            return (oSOutcomeSource == null || (directBody = oSOutcomeSource.setDirectBody(oSOutcomeSourceBody)) == null) ? new OSOutcomeSource(oSOutcomeSourceBody, null) : directBody;
        } else if (r3 != 2) {
            return oSOutcomeSource;
        } else {
            oSOutcomeSourceBody2.setInAppMessagesIds(new JSONArray(str));
            return (oSOutcomeSource == null || (indirectBody = oSOutcomeSource.setIndirectBody(oSOutcomeSourceBody2)) == null) ? new OSOutcomeSource(null, oSOutcomeSourceBody2) : indirectBody;
        }
    }

    private final void addIdToListFromChannel(List<OSCachedUniqueOutcome> list, JSONArray jSONArray, OSInfluenceChannel oSInfluenceChannel) {
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int r0 = 0; r0 < length; r0++) {
                try {
                    String influenceId = jSONArray.getString(r0);
                    Intrinsics.checkNotNullExpressionValue(influenceId, "influenceId");
                    list.add(new OSCachedUniqueOutcome(influenceId, oSInfluenceChannel));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private final void addIdsToListFromSource(List<OSCachedUniqueOutcome> list, OSOutcomeSourceBody oSOutcomeSourceBody) {
        if (oSOutcomeSourceBody != null) {
            JSONArray inAppMessagesIds = oSOutcomeSourceBody.getInAppMessagesIds();
            JSONArray notificationIds = oSOutcomeSourceBody.getNotificationIds();
            addIdToListFromChannel(list, inAppMessagesIds, OSInfluenceChannel.IAM);
            addIdToListFromChannel(list, notificationIds, OSInfluenceChannel.NOTIFICATION);
        }
    }

    public final synchronized void saveUniqueOutcomeEventParams(OSOutcomeEventParams eventParams) {
        Intrinsics.checkNotNullParameter(eventParams, "eventParams");
        OSLogger oSLogger = this.logger;
        oSLogger.debug("OneSignal saveUniqueOutcomeEventParams: " + eventParams);
        String outcomeId = eventParams.getOutcomeId();
        ArrayList arrayList = new ArrayList();
        OSOutcomeSource outcomeSource = eventParams.getOutcomeSource();
        OSOutcomeSourceBody directBody = outcomeSource != null ? outcomeSource.getDirectBody() : null;
        OSOutcomeSource outcomeSource2 = eventParams.getOutcomeSource();
        OSOutcomeSourceBody indirectBody = outcomeSource2 != null ? outcomeSource2.getIndirectBody() : null;
        addIdsToListFromSource(arrayList, directBody);
        addIdsToListFromSource(arrayList, indirectBody);
        for (OSCachedUniqueOutcome oSCachedUniqueOutcome : arrayList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("channel_influence_id", oSCachedUniqueOutcome.getInfluenceId());
            contentValues.put("channel_type", oSCachedUniqueOutcome.getChannel().toString());
            contentValues.put("name", outcomeId);
            this.dbHelper.insert("cached_unique_outcome", null, contentValues);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0095, code lost:
        if (r4.isClosed() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0097, code lost:
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a7, code lost:
        if (r4.isClosed() == false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized java.util.List<com.onesignal.influence.domain.OSInfluence> getNotCachedUniqueInfluencesForOutcome(java.lang.String r23, java.util.List<com.onesignal.influence.domain.OSInfluence> r24) {
        /*
            r22 = this;
            r1 = r22
            r0 = r23
            monitor-enter(r22)
            java.lang.String r2 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r2 = "influences"
            r3 = r24
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)     // Catch: java.lang.Throwable -> Lb8
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Lb8
            r2.<init>()     // Catch: java.lang.Throwable -> Lb8
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Throwable -> Lb8
            r4 = 0
            r5 = r4
            android.database.Cursor r5 = (android.database.Cursor) r5     // Catch: java.lang.Throwable -> Lb8
            java.util.Iterator r3 = r24.iterator()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
        L20:
            boolean r5 = r3.hasNext()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            if (r5 == 0) goto L8f
            java.lang.Object r5 = r3.next()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            com.onesignal.influence.domain.OSInfluence r5 = (com.onesignal.influence.domain.OSInfluence) r5     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            r6.<init>()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            org.json.JSONArray r7 = r5.getIds()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            if (r7 == 0) goto L20
            int r8 = r7.length()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            r9 = 0
            r10 = 0
        L3d:
            if (r10 >= r8) goto L7e
            java.lang.String r11 = r7.getString(r10)     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            com.onesignal.influence.domain.OSInfluenceChannel r12 = r5.getInfluenceChannel()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            java.lang.String[] r15 = new java.lang.String[r9]     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            java.lang.String r16 = "channel_influence_id = ? AND channel_type = ? AND name = ?"
            r13 = 3
            java.lang.String[] r14 = new java.lang.String[r13]     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            r14[r9] = r11     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            r13 = 1
            r14[r13] = r12     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            r12 = 2
            r14[r12] = r0     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            com.onesignal.OneSignalDb r13 = r1.dbHelper     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            java.lang.String r12 = "cached_unique_outcome"
            r18 = 0
            r19 = 0
            r20 = 0
            java.lang.String r21 = "1"
            r17 = r14
            r14 = r12
            android.database.Cursor r4 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            java.lang.String r12 = "cursor"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r12)     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            int r12 = r4.getCount()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            if (r12 != 0) goto L7b
            r6.put(r11)     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
        L7b:
            int r10 = r10 + 1
            goto L3d
        L7e:
            int r7 = r6.length()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            if (r7 <= 0) goto L20
            com.onesignal.influence.domain.OSInfluence r5 = r5.copy()     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            r5.setIds(r6)     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            r2.add(r5)     // Catch: java.lang.Throwable -> L9b org.json.JSONException -> L9d
            goto L20
        L8f:
            if (r4 == 0) goto Laa
            boolean r0 = r4.isClosed()     // Catch: java.lang.Throwable -> Lb8
            if (r0 != 0) goto Laa
        L97:
            r4.close()     // Catch: java.lang.Throwable -> Lb8
            goto Laa
        L9b:
            r0 = move-exception
            goto Lac
        L9d:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L9b
            if (r4 == 0) goto Laa
            boolean r0 = r4.isClosed()     // Catch: java.lang.Throwable -> Lb8
            if (r0 != 0) goto Laa
            goto L97
        Laa:
            monitor-exit(r22)
            return r2
        Lac:
            if (r4 == 0) goto Lb7
            boolean r2 = r4.isClosed()     // Catch: java.lang.Throwable -> Lb8
            if (r2 != 0) goto Lb7
            r4.close()     // Catch: java.lang.Throwable -> Lb8
        Lb7:
            throw r0     // Catch: java.lang.Throwable -> Lb8
        Lb8:
            r0 = move-exception
            monitor-exit(r22)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.outcomes.data.OSOutcomeEventsCache.getNotCachedUniqueInfluencesForOutcome(java.lang.String, java.util.List):java.util.List");
    }

    public final synchronized void cleanCachedUniqueOutcomeEventNotifications(String notificationTableName, String notificationIdColumnName) {
        Intrinsics.checkNotNullParameter(notificationTableName, "notificationTableName");
        Intrinsics.checkNotNullParameter(notificationIdColumnName, "notificationIdColumnName");
        StringBuilder sb = new StringBuilder();
        sb.append("NOT EXISTS(SELECT NULL FROM ");
        sb.append(notificationTableName);
        sb.append(" n ");
        sb.append("WHERE");
        sb.append(" n.");
        sb.append(notificationIdColumnName);
        sb.append(" = ");
        sb.append("channel_influence_id");
        sb.append(" AND ");
        sb.append("channel_type");
        sb.append(" = \"");
        String oSInfluenceChannel = OSInfluenceChannel.NOTIFICATION.toString();
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(locale, "Locale.ROOT");
        if (oSInfluenceChannel == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = oSInfluenceChannel.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        sb.append(lowerCase);
        sb.append("\")");
        this.dbHelper.delete("cached_unique_outcome", sb.toString(), null);
    }
}
