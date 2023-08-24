package com.onesignal;

import android.content.ContentValues;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import com.onesignal.OneSignalRestClient;
import com.onesignal.outcomes.OSOutcomeConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSInAppMessageRepository {
    static final long IAM_CACHE_DATA_LIFETIME = 15552000;
    static final String IAM_DATA_RESPONSE_RETRY_KEY = "retry";
    private final OneSignalDbHelper dbHelper;
    private int htmlNetworkRequestAttemptCount = 0;
    private final OSLogger logger;
    private final OSSharedPreferences sharedPreferences;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface OSInAppMessageRequestResponse {
        void onFailure(String str);

        void onSuccess(String str);
    }

    static /* synthetic */ int access$408(OSInAppMessageRepository oSInAppMessageRepository) {
        int r0 = oSInAppMessageRepository.htmlNetworkRequestAttemptCount;
        oSInAppMessageRepository.htmlNetworkRequestAttemptCount = r0 + 1;
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSInAppMessageRepository(OneSignalDbHelper oneSignalDbHelper, OSLogger oSLogger, OSSharedPreferences oSSharedPreferences) {
        this.dbHelper = oneSignalDbHelper;
        this.logger = oSLogger;
        this.sharedPreferences = oSSharedPreferences;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendIAMClick(String str, String str2, String str3, int r14, String str4, String str5, boolean z, final Set<String> set, final OSInAppMessageRequestResponse oSInAppMessageRequestResponse) {
        try {
            JSONObject jSONObject = new JSONObject(str, r14, str2, str5, str3, z) { // from class: com.onesignal.OSInAppMessageRepository.1
                final /* synthetic */ String val$appId;
                final /* synthetic */ String val$clickId;
                final /* synthetic */ int val$deviceType;
                final /* synthetic */ boolean val$isFirstClick;
                final /* synthetic */ String val$userId;
                final /* synthetic */ String val$variantId;

                {
                    this.val$appId = str;
                    this.val$deviceType = r14;
                    this.val$userId = str2;
                    this.val$clickId = str5;
                    this.val$variantId = str3;
                    this.val$isFirstClick = z;
                    put(OSOutcomeConstants.APP_ID, str);
                    put(OSOutcomeConstants.DEVICE_TYPE, r14);
                    put("player_id", str2);
                    put("click_id", str5);
                    put("variant_id", str3);
                    if (z) {
                        put("first_click", true);
                    }
                }
            };
            OneSignalRestClient.post("in_app_messages/" + str4 + "/click", jSONObject, new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.OSInAppMessageRepository.2
                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onSuccess(String str6) {
                    OSInAppMessageRepository.this.printHttpSuccessForInAppMessageRequest("engagement", str6);
                    OSInAppMessageRepository.this.saveClickedMessagesId(set);
                }

                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onFailure(int r2, String str6, Throwable th) {
                    OSInAppMessageRepository.this.printHttpErrorForInAppMessageRequest("engagement", r2, str6);
                    oSInAppMessageRequestResponse.onFailure(str6);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            this.logger.error("Unable to execute in-app message action HTTP request due to invalid JSON");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendIAMPageImpression(String str, String str2, String str3, int r13, String str4, String str5, final Set<String> set, final OSInAppMessageRequestResponse oSInAppMessageRequestResponse) {
        try {
            JSONObject jSONObject = new JSONObject(str, str2, str3, r13, str5) { // from class: com.onesignal.OSInAppMessageRepository.3
                final /* synthetic */ String val$appId;
                final /* synthetic */ int val$deviceType;
                final /* synthetic */ String val$pageId;
                final /* synthetic */ String val$userId;
                final /* synthetic */ String val$variantId;

                {
                    this.val$appId = str;
                    this.val$userId = str2;
                    this.val$variantId = str3;
                    this.val$deviceType = r13;
                    this.val$pageId = str5;
                    put(OSOutcomeConstants.APP_ID, str);
                    put("player_id", str2);
                    put("variant_id", str3);
                    put(OSOutcomeConstants.DEVICE_TYPE, r13);
                    put("page_id", str5);
                }
            };
            OneSignalRestClient.post("in_app_messages/" + str4 + "/pageImpression", jSONObject, new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.OSInAppMessageRepository.4
                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onSuccess(String str6) {
                    OSInAppMessageRepository.this.printHttpSuccessForInAppMessageRequest("page impression", str6);
                    OSInAppMessageRepository.this.saveViewPageImpressionedIds(set);
                }

                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onFailure(int r2, String str6, Throwable th) {
                    OSInAppMessageRepository.this.printHttpErrorForInAppMessageRequest("page impression", r2, str6);
                    oSInAppMessageRequestResponse.onFailure(str6);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            this.logger.error("Unable to execute in-app message impression HTTP request due to invalid JSON");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendIAMImpression(String str, String str2, String str3, int r11, String str4, final Set<String> set, final OSInAppMessageRequestResponse oSInAppMessageRequestResponse) {
        try {
            JSONObject jSONObject = new JSONObject(str, str2, str3, r11) { // from class: com.onesignal.OSInAppMessageRepository.5
                final /* synthetic */ String val$appId;
                final /* synthetic */ int val$deviceType;
                final /* synthetic */ String val$userId;
                final /* synthetic */ String val$variantId;

                {
                    this.val$appId = str;
                    this.val$userId = str2;
                    this.val$variantId = str3;
                    this.val$deviceType = r11;
                    put(OSOutcomeConstants.APP_ID, str);
                    put("player_id", str2);
                    put("variant_id", str3);
                    put(OSOutcomeConstants.DEVICE_TYPE, r11);
                    put("first_impression", true);
                }
            };
            OneSignalRestClient.post("in_app_messages/" + str4 + "/impression", jSONObject, new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.OSInAppMessageRepository.6
                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onSuccess(String str5) {
                    OSInAppMessageRepository.this.printHttpSuccessForInAppMessageRequest("impression", str5);
                    OSInAppMessageRepository.this.saveImpressionedMessages(set);
                }

                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onFailure(int r2, String str5, Throwable th) {
                    OSInAppMessageRepository.this.printHttpErrorForInAppMessageRequest("impression", r2, str5);
                    oSInAppMessageRequestResponse.onFailure(str5);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            this.logger.error("Unable to execute in-app message impression HTTP request due to invalid JSON");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getIAMPreviewData(String str, String str2, final OSInAppMessageRequestResponse oSInAppMessageRequestResponse) {
        OneSignalRestClient.get("in_app_messages/device_preview?preview_id=" + str2 + "&app_id=" + str, new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.OSInAppMessageRepository.7
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onFailure(int r2, String str3, Throwable th) {
                OSInAppMessageRepository.this.printHttpErrorForInAppMessageRequest(OSInAppMessageContentKt.HTML, r2, str3);
                oSInAppMessageRequestResponse.onFailure(str3);
            }

            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onSuccess(String str3) {
                oSInAppMessageRequestResponse.onSuccess(str3);
            }
        }, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getIAMData(String str, String str2, String str3, final OSInAppMessageRequestResponse oSInAppMessageRequestResponse) {
        OneSignalRestClient.get(htmlPathForMessage(str2, str3, str), new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.OSInAppMessageRepository.8
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onFailure(int r2, String str4, Throwable th) {
                OSInAppMessageRepository.this.printHttpErrorForInAppMessageRequest(OSInAppMessageContentKt.HTML, r2, str4);
                JSONObject jSONObject = new JSONObject();
                if (!OSUtils.shouldRetryNetworkRequest(r2) || OSInAppMessageRepository.this.htmlNetworkRequestAttemptCount >= OSUtils.MAX_NETWORK_REQUEST_ATTEMPT_COUNT) {
                    OSInAppMessageRepository.this.htmlNetworkRequestAttemptCount = 0;
                    try {
                        jSONObject.put(OSInAppMessageRepository.IAM_DATA_RESPONSE_RETRY_KEY, false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    OSInAppMessageRepository.access$408(OSInAppMessageRepository.this);
                    try {
                        jSONObject.put(OSInAppMessageRepository.IAM_DATA_RESPONSE_RETRY_KEY, true);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                oSInAppMessageRequestResponse.onFailure(jSONObject.toString());
            }

            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onSuccess(String str4) {
                OSInAppMessageRepository.this.htmlNetworkRequestAttemptCount = 0;
                oSInAppMessageRequestResponse.onSuccess(str4);
            }
        }, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void saveInAppMessage(OSInAppMessageInternal oSInAppMessageInternal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("message_id", oSInAppMessageInternal.messageId);
        contentValues.put(OneSignalDbContract.InAppMessageTable.COLUMN_NAME_DISPLAY_QUANTITY, Integer.valueOf(oSInAppMessageInternal.getRedisplayStats().getDisplayQuantity()));
        contentValues.put(OneSignalDbContract.InAppMessageTable.COLUMN_NAME_LAST_DISPLAY, Long.valueOf(oSInAppMessageInternal.getRedisplayStats().getLastDisplayTime()));
        contentValues.put(OneSignalDbContract.InAppMessageTable.COLUMN_CLICK_IDS, oSInAppMessageInternal.getClickedClickIds().toString());
        contentValues.put(OneSignalDbContract.InAppMessageTable.COLUMN_DISPLAYED_IN_SESSION, Boolean.valueOf(oSInAppMessageInternal.isDisplayedInSession()));
        if (this.dbHelper.update(OneSignalDbContract.InAppMessageTable.TABLE_NAME, contentValues, "message_id = ?", new String[]{oSInAppMessageInternal.messageId}) == 0) {
            this.dbHelper.insert(OneSignalDbContract.InAppMessageTable.TABLE_NAME, null, contentValues);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0074, code lost:
        if (r1.isClosed() == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0076, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008a, code lost:
        if (r1.isClosed() == false) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0070 A[Catch: all -> 0x009b, TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:14:0x0070, B:16:0x0076, B:23:0x0086, B:5:0x0007, B:7:0x001b, B:11:0x0052, B:21:0x007d), top: B:38:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.util.List<com.onesignal.OSInAppMessageInternal> getCachedInAppMessages() {
        /*
            r10 = this;
            monitor-enter(r10)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L9b
            r0.<init>()     // Catch: java.lang.Throwable -> L9b
            r1 = 0
            com.onesignal.OneSignalDbHelper r2 = r10.dbHelper     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            java.lang.String r3 = "in_app_message"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            boolean r2 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            if (r2 == 0) goto L6e
        L1b:
            java.lang.String r2 = "message_id"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            java.lang.String r3 = "click_ids"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            java.lang.String r3 = r1.getString(r3)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            java.lang.String r4 = "display_quantity"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            int r4 = r1.getInt(r4)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            java.lang.String r5 = "last_display"
            int r5 = r1.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            long r5 = r1.getLong(r5)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            java.lang.String r7 = "displayed_in_session"
            int r7 = r1.getColumnIndex(r7)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            int r7 = r1.getInt(r7)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            r8 = 1
            if (r7 != r8) goto L51
            goto L52
        L51:
            r8 = 0
        L52:
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            r7.<init>(r3)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            java.util.Set r3 = com.onesignal.OSUtils.newStringSetFromJSONArray(r7)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            com.onesignal.OSInAppMessageInternal r7 = new com.onesignal.OSInAppMessageInternal     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            com.onesignal.OSInAppMessageRedisplayStats r9 = new com.onesignal.OSInAppMessageRedisplayStats     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            r9.<init>(r4, r5)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            r7.<init>(r2, r3, r8, r9)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            r0.add(r7)     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L7a org.json.JSONException -> L7c
            if (r2 != 0) goto L1b
        L6e:
            if (r1 == 0) goto L8d
            boolean r2 = r1.isClosed()     // Catch: java.lang.Throwable -> L9b
            if (r2 != 0) goto L8d
        L76:
            r1.close()     // Catch: java.lang.Throwable -> L9b
            goto L8d
        L7a:
            r0 = move-exception
            goto L8f
        L7c:
            r2 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch: java.lang.Throwable -> L7a
            java.lang.String r4 = "Generating JSONArray from iam click ids:JSON Failed."
            com.onesignal.OneSignal.Log(r3, r4, r2)     // Catch: java.lang.Throwable -> L7a
            if (r1 == 0) goto L8d
            boolean r2 = r1.isClosed()     // Catch: java.lang.Throwable -> L9b
            if (r2 != 0) goto L8d
            goto L76
        L8d:
            monitor-exit(r10)
            return r0
        L8f:
            if (r1 == 0) goto L9a
            boolean r2 = r1.isClosed()     // Catch: java.lang.Throwable -> L9b
            if (r2 != 0) goto L9a
            r1.close()     // Catch: java.lang.Throwable -> L9b
        L9a:
            throw r0     // Catch: java.lang.Throwable -> L9b
        L9b:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSInAppMessageRepository.getCachedInAppMessages():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0074, code lost:
        if (r12.isClosed() == false) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0070 A[Catch: all -> 0x00b8, TRY_ENTER, TryCatch #3 {, blocks: (B:3:0x0001, B:15:0x0070, B:17:0x0076, B:34:0x009d, B:21:0x0083, B:23:0x0089, B:31:0x0096, B:5:0x0029, B:7:0x0038, B:10:0x003f, B:12:0x0045, B:19:0x007a, B:29:0x0091), top: B:46:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void cleanCachedInAppMessages() {
        /*
            r13 = this;
            monitor-enter(r13)
            java.lang.String r0 = "message_id"
            java.lang.String r1 = "click_ids"
            java.lang.String[] r4 = new java.lang.String[]{r0, r1}     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r0 = "last_display < ?"
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lb8
            r5 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r5
            r5 = 15552000(0xed4e00, double:7.683709E-317)
            long r1 = r1 - r5
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> Lb8
            r2 = 1
            java.lang.String[] r10 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> Lb8
            r2 = 0
            r10[r2] = r1     // Catch: java.lang.Throwable -> Lb8
            java.util.Set r1 = com.onesignal.OSUtils.newConcurrentSet()     // Catch: java.lang.Throwable -> Lb8
            java.util.Set r11 = com.onesignal.OSUtils.newConcurrentSet()     // Catch: java.lang.Throwable -> Lb8
            r12 = 0
            com.onesignal.OneSignalDbHelper r2 = r13.dbHelper     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            java.lang.String r3 = "in_app_message"
            r7 = 0
            r8 = 0
            r9 = 0
            r5 = r0
            r6 = r10
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            if (r12 == 0) goto L7a
            int r2 = r12.getCount()     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            if (r2 != 0) goto L3f
            goto L7a
        L3f:
            boolean r2 = r12.moveToFirst()     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            if (r2 == 0) goto L6e
        L45:
            java.lang.String r2 = "message_id"
            int r2 = r12.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            java.lang.String r2 = r12.getString(r2)     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            java.lang.String r3 = "click_ids"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            java.lang.String r3 = r12.getString(r3)     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            r1.add(r2)     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            java.util.Set r2 = com.onesignal.OSUtils.newStringSetFromJSONArray(r2)     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            r11.addAll(r2)     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            boolean r2 = r12.moveToNext()     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            if (r2 != 0) goto L45
        L6e:
            if (r12 == 0) goto L9d
            boolean r2 = r12.isClosed()     // Catch: java.lang.Throwable -> Lb8
            if (r2 != 0) goto L9d
        L76:
            r12.close()     // Catch: java.lang.Throwable -> Lb8
            goto L9d
        L7a:
            com.onesignal.OneSignal$LOG_LEVEL r2 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            java.lang.String r3 = "Attempted to clean 6 month old IAM data, but none exists!"
            com.onesignal.OneSignal.onesignalLog(r2, r3)     // Catch: java.lang.Throwable -> L8e org.json.JSONException -> L90
            if (r12 == 0) goto L8c
            boolean r0 = r12.isClosed()     // Catch: java.lang.Throwable -> Lb8
            if (r0 != 0) goto L8c
            r12.close()     // Catch: java.lang.Throwable -> Lb8
        L8c:
            monitor-exit(r13)
            return
        L8e:
            r0 = move-exception
            goto Lac
        L90:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L8e
            if (r12 == 0) goto L9d
            boolean r2 = r12.isClosed()     // Catch: java.lang.Throwable -> Lb8
            if (r2 != 0) goto L9d
            goto L76
        L9d:
            com.onesignal.OneSignalDbHelper r2 = r13.dbHelper     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r3 = "in_app_message"
            r2.delete(r3, r0, r10)     // Catch: java.lang.Throwable -> Lb8
            r13.cleanInAppMessageIds(r1)     // Catch: java.lang.Throwable -> Lb8
            r13.cleanInAppMessageClickedClickIds(r11)     // Catch: java.lang.Throwable -> Lb8
            monitor-exit(r13)
            return
        Lac:
            if (r12 == 0) goto Lb7
            boolean r1 = r12.isClosed()     // Catch: java.lang.Throwable -> Lb8
            if (r1 != 0) goto Lb7
            r12.close()     // Catch: java.lang.Throwable -> Lb8
        Lb7:
            throw r0     // Catch: java.lang.Throwable -> Lb8
        Lb8:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSInAppMessageRepository.cleanCachedInAppMessages():void");
    }

    private void cleanInAppMessageIds(Set<String> set) {
        if (set == null || set.size() <= 0) {
            return;
        }
        Set<String> stringSet = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_DISMISSED_IAMS, null);
        Set<String> stringSet2 = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_IMPRESSIONED_IAMS, null);
        if (stringSet != null && stringSet.size() > 0) {
            stringSet.removeAll(set);
            OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_DISMISSED_IAMS, stringSet);
        }
        if (stringSet2 == null || stringSet2.size() <= 0) {
            return;
        }
        stringSet2.removeAll(set);
        OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_IMPRESSIONED_IAMS, stringSet2);
    }

    private void cleanInAppMessageClickedClickIds(Set<String> set) {
        Set<String> stringSet;
        if (set == null || set.size() <= 0 || (stringSet = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_CLICKED_CLICK_IDS_IAMS, null)) == null || stringSet.size() <= 0) {
            return;
        }
        stringSet.removeAll(set);
        OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_CLICKED_CLICK_IDS_IAMS, stringSet);
    }

    private String htmlPathForMessage(String str, String str2, String str3) {
        if (str2 == null) {
            OSLogger oSLogger = this.logger;
            oSLogger.error("Unable to find a variant for in-app message " + str);
            return null;
        }
        return "in_app_messages/" + str + "/variants/" + str2 + "/html?app_id=" + str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> getClickedMessagesId() {
        return this.sharedPreferences.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_CLICKED_CLICK_IDS_IAMS, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveClickedMessagesId(Set<String> set) {
        this.sharedPreferences.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_CLICKED_CLICK_IDS_IAMS, set);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> getImpressionesMessagesId() {
        return this.sharedPreferences.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_IMPRESSIONED_IAMS, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveImpressionedMessages(Set<String> set) {
        this.sharedPreferences.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_IMPRESSIONED_IAMS, set);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> getViewPageImpressionedIds() {
        return this.sharedPreferences.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_PAGE_IMPRESSIONED_IAMS, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveViewPageImpressionedIds(Set<String> set) {
        this.sharedPreferences.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_PAGE_IMPRESSIONED_IAMS, set);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> getDismissedMessagesId() {
        return this.sharedPreferences.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_DISMISSED_IAMS, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveDismissedMessagesId(Set<String> set) {
        this.sharedPreferences.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_DISMISSED_IAMS, set);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getSavedIAMs() {
        return this.sharedPreferences.getString(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_CACHED_IAMS, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveIAMs(String str) {
        this.sharedPreferences.saveString(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_CACHED_IAMS, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveLastTimeInAppDismissed(Date date) {
        this.sharedPreferences.saveString(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_LAST_TIME_IAM_DISMISSED, date != null ? date.toString() : null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Date getLastTimeInAppDismissed() {
        String string = this.sharedPreferences.getString(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_LAST_TIME_IAM_DISMISSED, null);
        if (string == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(string);
        } catch (ParseException e) {
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.ERROR, e.getLocalizedMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printHttpSuccessForInAppMessageRequest(String str, String str2) {
        OSLogger oSLogger = this.logger;
        oSLogger.debug("Successful post for in-app message " + str + " request: " + str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printHttpErrorForInAppMessageRequest(String str, int r5, String str2) {
        OSLogger oSLogger = this.logger;
        oSLogger.error("Encountered a " + r5 + " error while attempting in-app message " + str + " request: " + str2);
    }
}
