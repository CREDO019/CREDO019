package com.amplitude.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class DatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_EVENTS_TABLE = "CREATE TABLE IF NOT EXISTS events (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);";
    private static final String CREATE_IDENTIFYS_TABLE = "CREATE TABLE IF NOT EXISTS identifys (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);";
    private static final String CREATE_LONG_STORE_TABLE = "CREATE TABLE IF NOT EXISTS long_store (key TEXT PRIMARY KEY NOT NULL, value INTEGER);";
    private static final String CREATE_STORE_TABLE = "CREATE TABLE IF NOT EXISTS store (key TEXT PRIMARY KEY NOT NULL, value TEXT);";
    private static final String EVENT_FIELD = "event";
    protected static final String EVENT_TABLE_NAME = "events";
    protected static final String IDENTIFY_TABLE_NAME = "identifys";
    private static final String ID_FIELD = "id";
    private static final String KEY_FIELD = "key";
    protected static final String LONG_STORE_TABLE_NAME = "long_store";
    protected static final String STORE_TABLE_NAME = "store";
    private static final String TAG = "com.amplitude.api.DatabaseHelper";
    private static final String VALUE_FIELD = "value";
    static final Map<String, DatabaseHelper> instances = new HashMap();
    private static final AmplitudeLog logger = AmplitudeLog.getLogger();
    private boolean callResetListenerOnDatabaseReset;
    private DatabaseResetListener databaseResetListener;
    File file;
    private String instanceName;

    @Deprecated
    static DatabaseHelper getDatabaseHelper(Context context) {
        return getDatabaseHelper(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized DatabaseHelper getDatabaseHelper(Context context, String str) {
        DatabaseHelper databaseHelper;
        synchronized (DatabaseHelper.class) {
            String normalizeInstanceName = C1060Utils.normalizeInstanceName(str);
            Map<String, DatabaseHelper> map = instances;
            databaseHelper = map.get(normalizeInstanceName);
            if (databaseHelper == null) {
                databaseHelper = new DatabaseHelper(context.getApplicationContext(), normalizeInstanceName);
                map.put(normalizeInstanceName, databaseHelper);
            }
        }
        return databaseHelper;
    }

    private static String getDatabaseName(String str) {
        if (C1060Utils.isEmptyString(str) || str.equals(Constants.DEFAULT_INSTANCE)) {
            return "com.amplitude.api";
        }
        return "com.amplitude.api_" + str;
    }

    protected DatabaseHelper(Context context) {
        this(context, null);
    }

    protected DatabaseHelper(Context context, String str) {
        super(context, getDatabaseName(str), (SQLiteDatabase.CursorFactory) null, 3);
        this.callResetListenerOnDatabaseReset = true;
        this.file = context.getDatabasePath(getDatabaseName(str));
        this.instanceName = C1060Utils.normalizeInstanceName(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDatabaseResetListener(DatabaseResetListener databaseResetListener) {
        this.databaseResetListener = databaseResetListener;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_STORE_TABLE);
        sQLiteDatabase.execSQL(CREATE_LONG_STORE_TABLE);
        sQLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
        sQLiteDatabase.execSQL(CREATE_IDENTIFYS_TABLE);
        DatabaseResetListener databaseResetListener = this.databaseResetListener;
        if (databaseResetListener == null || !this.callResetListenerOnDatabaseReset) {
            return;
        }
        try {
            try {
                this.callResetListenerOnDatabaseReset = false;
                databaseResetListener.onDatabaseReset(sQLiteDatabase);
            } catch (SQLiteException e) {
                logger.m1367e(TAG, String.format("databaseReset callback failed during onCreate", new Object[0]), e);
            }
        } finally {
            this.callResetListenerOnDatabaseReset = true;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int r5, int r6) {
        if (r5 > r6) {
            logger.m1368e(TAG, "onUpgrade() with invalid oldVersion and newVersion");
            resetDatabase(sQLiteDatabase);
        } else if (r6 <= 1) {
        } else {
            if (r5 == 1) {
                sQLiteDatabase.execSQL(CREATE_STORE_TABLE);
                if (r6 <= 2) {
                    return;
                }
            } else if (r5 != 2) {
                if (r5 != 3) {
                    AmplitudeLog amplitudeLog = logger;
                    String str = TAG;
                    amplitudeLog.m1368e(str, "onUpgrade() with unknown oldVersion " + r5);
                    resetDatabase(sQLiteDatabase);
                    return;
                }
                return;
            }
            sQLiteDatabase.execSQL(CREATE_IDENTIFYS_TABLE);
            sQLiteDatabase.execSQL(CREATE_LONG_STORE_TABLE);
        }
    }

    private void resetDatabase(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS store");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS long_store");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS events");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS identifys");
        onCreate(sQLiteDatabase);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long insertOrReplaceKeyValue(String str, String str2) {
        long insertOrReplaceKeyValueToTable;
        if (str2 == null) {
            insertOrReplaceKeyValueToTable = deleteKeyFromTable(STORE_TABLE_NAME, str);
        } else {
            insertOrReplaceKeyValueToTable = insertOrReplaceKeyValueToTable(STORE_TABLE_NAME, str, str2);
        }
        return insertOrReplaceKeyValueToTable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long insertOrReplaceKeyLongValue(String str, Long l) {
        long insertOrReplaceKeyValueToTable;
        if (l == null) {
            insertOrReplaceKeyValueToTable = deleteKeyFromTable(LONG_STORE_TABLE_NAME, str);
        } else {
            insertOrReplaceKeyValueToTable = insertOrReplaceKeyValueToTable(LONG_STORE_TABLE_NAME, str, l);
        }
        return insertOrReplaceKeyValueToTable;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0035, code lost:
        if (r2.isOpen() != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
        close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0056, code lost:
        if (r2.isOpen() != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized long insertOrReplaceKeyValueToTable(java.lang.String r6, java.lang.String r7, java.lang.Object r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            r1 = 1
            r2 = 0
            android.database.sqlite.SQLiteDatabase r2 = r5.getWritableDatabase()     // Catch: java.lang.Throwable -> L18 java.lang.StackOverflowError -> L1a android.database.sqlite.SQLiteException -> L3b
            long r6 = r5.insertOrReplaceKeyValueToTable(r2, r6, r7, r8)     // Catch: java.lang.Throwable -> L18 java.lang.StackOverflowError -> L1a android.database.sqlite.SQLiteException -> L3b
            if (r2 == 0) goto L5b
            boolean r8 = r2.isOpen()     // Catch: java.lang.Throwable -> L69
            if (r8 == 0) goto L5b
            r5.close()     // Catch: java.lang.Throwable -> L69
            goto L5b
        L18:
            r6 = move-exception
            goto L5d
        L1a:
            r7 = move-exception
            com.amplitude.api.AmplitudeLog r8 = com.amplitude.api.DatabaseHelper.logger     // Catch: java.lang.Throwable -> L18
            java.lang.String r3 = com.amplitude.api.DatabaseHelper.TAG     // Catch: java.lang.Throwable -> L18
            java.lang.String r4 = "insertOrReplaceKeyValue in %s failed"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L18
            r1[r0] = r6     // Catch: java.lang.Throwable -> L18
            java.lang.String r6 = java.lang.String.format(r4, r1)     // Catch: java.lang.Throwable -> L18
            r8.m1367e(r3, r6, r7)     // Catch: java.lang.Throwable -> L18
            r5.delete()     // Catch: java.lang.Throwable -> L18
            if (r2 == 0) goto L59
            boolean r6 = r2.isOpen()     // Catch: java.lang.Throwable -> L69
            if (r6 == 0) goto L59
        L37:
            r5.close()     // Catch: java.lang.Throwable -> L69
            goto L59
        L3b:
            r7 = move-exception
            com.amplitude.api.AmplitudeLog r8 = com.amplitude.api.DatabaseHelper.logger     // Catch: java.lang.Throwable -> L18
            java.lang.String r3 = com.amplitude.api.DatabaseHelper.TAG     // Catch: java.lang.Throwable -> L18
            java.lang.String r4 = "insertOrReplaceKeyValue in %s failed"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L18
            r1[r0] = r6     // Catch: java.lang.Throwable -> L18
            java.lang.String r6 = java.lang.String.format(r4, r1)     // Catch: java.lang.Throwable -> L18
            r8.m1367e(r3, r6, r7)     // Catch: java.lang.Throwable -> L18
            r5.delete()     // Catch: java.lang.Throwable -> L18
            if (r2 == 0) goto L59
            boolean r6 = r2.isOpen()     // Catch: java.lang.Throwable -> L69
            if (r6 == 0) goto L59
            goto L37
        L59:
            r6 = -1
        L5b:
            monitor-exit(r5)
            return r6
        L5d:
            if (r2 == 0) goto L68
            boolean r7 = r2.isOpen()     // Catch: java.lang.Throwable -> L69
            if (r7 == 0) goto L68
            r5.close()     // Catch: java.lang.Throwable -> L69
        L68:
            throw r6     // Catch: java.lang.Throwable -> L69
        L69:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.insertOrReplaceKeyValueToTable(java.lang.String, java.lang.String, java.lang.Object):long");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long insertOrReplaceKeyValueToTable(SQLiteDatabase sQLiteDatabase, String str, String str2, Object obj) throws SQLiteException, StackOverflowError {
        long insertKeyValueContentValuesIntoTable;
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIELD, str2);
        if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else {
            contentValues.put("value", (String) obj);
        }
        insertKeyValueContentValuesIntoTable = insertKeyValueContentValuesIntoTable(sQLiteDatabase, str, contentValues);
        if (insertKeyValueContentValuesIntoTable == -1) {
            logger.m1362w(TAG, "Insert failed");
        }
        return insertKeyValueContentValuesIntoTable;
    }

    synchronized long insertKeyValueContentValuesIntoTable(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) throws SQLiteException, StackOverflowError {
        return sQLiteDatabase.insertWithOnConflict(str, null, contentValues, 5);
    }

    synchronized long deleteKeyFromTable(String str, String str2) {
        long j;
        try {
            try {
                j = getWritableDatabase().delete(str, "key=?", new String[]{str2});
                close();
            } catch (SQLiteException e) {
                logger.m1367e(TAG, String.format("deleteKey from %s failed", str), e);
                delete();
                close();
                j = -1;
                return j;
            }
        } catch (StackOverflowError e2) {
            logger.m1367e(TAG, String.format("deleteKey from %s failed", str), e2);
            delete();
            close();
            j = -1;
            return j;
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long addEvent(String str) {
        return addEventToTable(EVENT_TABLE_NAME, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long addIdentify(String str) {
        return addEventToTable(IDENTIFY_TABLE_NAME, str);
    }

    private synchronized long addEventToTable(String str, String str2) {
        long j;
        long j2 = -1;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("event", str2);
            j = insertEventContentValuesIntoTable(writableDatabase, str, contentValues);
            if (j == -1) {
                try {
                    logger.m1362w(TAG, String.format("Insert into %s failed", str));
                } catch (SQLiteException e) {
                    e = e;
                    j2 = j;
                    logger.m1367e(TAG, String.format("addEvent to %s failed", str), e);
                    delete();
                    close();
                    j = j2;
                    return j;
                } catch (StackOverflowError e2) {
                    e = e2;
                    j2 = j;
                    logger.m1367e(TAG, String.format("addEvent to %s failed", str), e);
                    delete();
                    close();
                    j = j2;
                    return j;
                }
            }
            close();
        } catch (SQLiteException e3) {
            e = e3;
        } catch (StackOverflowError e4) {
            e = e4;
        }
        return j;
    }

    synchronized long insertEventContentValuesIntoTable(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) throws SQLiteException, StackOverflowError {
        return sQLiteDatabase.insert(str, null, contentValues);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String getValue(String str) {
        return (String) getValueFromTable(STORE_TABLE_NAME, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Long getLongValue(String str) {
        return (Long) getValueFromTable(LONG_STORE_TABLE_NAME, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a5 A[Catch: all -> 0x0065, TRY_ENTER, TryCatch #3 {, blocks: (B:13:0x003e, B:36:0x0061, B:29:0x0053, B:35:0x005e, B:44:0x007f, B:50:0x009b, B:57:0x00a5, B:58:0x00a8, B:59:0x00ab), top: B:63:0x0004 }] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected synchronized java.lang.Object getValueFromTable(java.lang.String r14, java.lang.String r15) {
        /*
            r13 = this;
            monitor-enter(r13)
            r0 = 0
            r1 = 0
            r2 = 1
            android.database.sqlite.SQLiteDatabase r4 = r13.getReadableDatabase()     // Catch: java.lang.Throwable -> L4a java.lang.RuntimeException -> L4c java.lang.IllegalStateException -> L57 java.lang.StackOverflowError -> L67 android.database.sqlite.SQLiteException -> L83
            java.lang.String r3 = "key"
            java.lang.String r5 = "value"
            java.lang.String[] r6 = new java.lang.String[]{r3, r5}     // Catch: java.lang.Throwable -> L4a java.lang.RuntimeException -> L4c java.lang.IllegalStateException -> L57 java.lang.StackOverflowError -> L67 android.database.sqlite.SQLiteException -> L83
            java.lang.String r7 = "key = ?"
            java.lang.String[] r8 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L4a java.lang.RuntimeException -> L4c java.lang.IllegalStateException -> L57 java.lang.StackOverflowError -> L67 android.database.sqlite.SQLiteException -> L83
            r8[r1] = r15     // Catch: java.lang.Throwable -> L4a java.lang.RuntimeException -> L4c java.lang.IllegalStateException -> L57 java.lang.StackOverflowError -> L67 android.database.sqlite.SQLiteException -> L83
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r3 = r13
            r5 = r14
            android.database.Cursor r15 = r3.queryDb(r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L4a java.lang.RuntimeException -> L4c java.lang.IllegalStateException -> L57 java.lang.StackOverflowError -> L67 android.database.sqlite.SQLiteException -> L83
            boolean r3 = r15.moveToFirst()     // Catch: java.lang.RuntimeException -> L42 java.lang.IllegalStateException -> L44 java.lang.StackOverflowError -> L46 android.database.sqlite.SQLiteException -> L48 java.lang.Throwable -> La1
            if (r3 == 0) goto L3c
            java.lang.String r3 = "store"
            boolean r3 = r14.equals(r3)     // Catch: java.lang.RuntimeException -> L42 java.lang.IllegalStateException -> L44 java.lang.StackOverflowError -> L46 android.database.sqlite.SQLiteException -> L48 java.lang.Throwable -> La1
            if (r3 == 0) goto L33
            java.lang.String r14 = r15.getString(r2)     // Catch: java.lang.RuntimeException -> L42 java.lang.IllegalStateException -> L44 java.lang.StackOverflowError -> L46 android.database.sqlite.SQLiteException -> L48 java.lang.Throwable -> La1
            goto L3b
        L33:
            long r3 = r15.getLong(r2)     // Catch: java.lang.RuntimeException -> L42 java.lang.IllegalStateException -> L44 java.lang.StackOverflowError -> L46 android.database.sqlite.SQLiteException -> L48 java.lang.Throwable -> La1
            java.lang.Long r14 = java.lang.Long.valueOf(r3)     // Catch: java.lang.RuntimeException -> L42 java.lang.IllegalStateException -> L44 java.lang.StackOverflowError -> L46 android.database.sqlite.SQLiteException -> L48 java.lang.Throwable -> La1
        L3b:
            r0 = r14
        L3c:
            if (r15 == 0) goto L61
            r15.close()     // Catch: java.lang.Throwable -> L65
            goto L61
        L42:
            r14 = move-exception
            goto L4e
        L44:
            r14 = move-exception
            goto L59
        L46:
            r3 = move-exception
            goto L69
        L48:
            r3 = move-exception
            goto L85
        L4a:
            r14 = move-exception
            goto La3
        L4c:
            r14 = move-exception
            r15 = r0
        L4e:
            convertIfCursorWindowException(r14)     // Catch: java.lang.Throwable -> La1
            if (r15 == 0) goto L61
            r15.close()     // Catch: java.lang.Throwable -> L65
            goto L61
        L57:
            r14 = move-exception
            r15 = r0
        L59:
            r13.handleIfCursorRowTooLargeException(r14)     // Catch: java.lang.Throwable -> La1
            if (r15 == 0) goto L61
            r15.close()     // Catch: java.lang.Throwable -> L65
        L61:
            r13.close()     // Catch: java.lang.Throwable -> L65
            goto L9f
        L65:
            r14 = move-exception
            goto Lac
        L67:
            r3 = move-exception
            r15 = r0
        L69:
            com.amplitude.api.AmplitudeLog r4 = com.amplitude.api.DatabaseHelper.logger     // Catch: java.lang.Throwable -> La1
            java.lang.String r5 = com.amplitude.api.DatabaseHelper.TAG     // Catch: java.lang.Throwable -> La1
            java.lang.String r6 = "getValue from %s failed"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> La1
            r2[r1] = r14     // Catch: java.lang.Throwable -> La1
            java.lang.String r14 = java.lang.String.format(r6, r2)     // Catch: java.lang.Throwable -> La1
            r4.m1367e(r5, r14, r3)     // Catch: java.lang.Throwable -> La1
            r13.delete()     // Catch: java.lang.Throwable -> La1
            if (r15 == 0) goto L61
            r15.close()     // Catch: java.lang.Throwable -> L65
            goto L61
        L83:
            r3 = move-exception
            r15 = r0
        L85:
            com.amplitude.api.AmplitudeLog r4 = com.amplitude.api.DatabaseHelper.logger     // Catch: java.lang.Throwable -> La1
            java.lang.String r5 = com.amplitude.api.DatabaseHelper.TAG     // Catch: java.lang.Throwable -> La1
            java.lang.String r6 = "getValue from %s failed"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> La1
            r2[r1] = r14     // Catch: java.lang.Throwable -> La1
            java.lang.String r14 = java.lang.String.format(r6, r2)     // Catch: java.lang.Throwable -> La1
            r4.m1367e(r5, r14, r3)     // Catch: java.lang.Throwable -> La1
            r13.delete()     // Catch: java.lang.Throwable -> La1
            if (r15 == 0) goto L61
            r15.close()     // Catch: java.lang.Throwable -> L65
            goto L61
        L9f:
            monitor-exit(r13)
            return r0
        La1:
            r14 = move-exception
            r0 = r15
        La3:
            if (r0 == 0) goto La8
            r0.close()     // Catch: java.lang.Throwable -> L65
        La8:
            r13.close()     // Catch: java.lang.Throwable -> L65
            throw r14     // Catch: java.lang.Throwable -> L65
        Lac:
            monitor-exit(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.getValueFromTable(java.lang.String, java.lang.String):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized List<JSONObject> getEvents(long j, long j2) throws JSONException {
        return getEventsFromTable(EVENT_TABLE_NAME, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized List<JSONObject> getIdentifys(long j, long j2) throws JSONException {
        return getEventsFromTable(IDENTIFY_TABLE_NAME, j, j2);
    }

    protected synchronized List<JSONObject> getEventsFromTable(String str, long j, long j2) throws JSONException {
        LinkedList linkedList;
        String str2;
        String str3;
        linkedList = new LinkedList();
        Cursor cursor = null;
        try {
            try {
                try {
                    try {
                        SQLiteDatabase readableDatabase = getReadableDatabase();
                        String[] strArr = {"id", "event"};
                        if (j >= 0) {
                            str2 = "id <= " + j;
                        } else {
                            str2 = null;
                        }
                        if (j2 >= 0) {
                            str3 = "" + j2;
                        } else {
                            str3 = null;
                        }
                        cursor = queryDb(readableDatabase, str, strArr, str2, null, null, null, "id ASC", str3);
                        while (cursor.moveToNext()) {
                            long j3 = cursor.getLong(0);
                            String string = cursor.getString(1);
                            if (!C1060Utils.isEmptyString(string)) {
                                JSONObject jSONObject = new JSONObject(string);
                                jSONObject.put("event_id", j3);
                                linkedList.add(jSONObject);
                            }
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                    } catch (IllegalStateException e) {
                        handleIfCursorRowTooLargeException(e);
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                } catch (SQLiteException e2) {
                    logger.m1367e(TAG, String.format("getEvents from %s failed", str), e2);
                    delete();
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            } catch (RuntimeException e3) {
                convertIfCursorWindowException(e3);
                if (cursor != null) {
                    cursor.close();
                }
            }
        } catch (StackOverflowError e4) {
            logger.m1367e(TAG, String.format("getEvents from %s failed", str), e4);
            delete();
            if (cursor != null) {
                cursor.close();
            }
        }
        close();
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long getEventCount() {
        return getEventCountFromTable(EVENT_TABLE_NAME);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long getIdentifyCount() {
        return getEventCountFromTable(IDENTIFY_TABLE_NAME);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long getTotalEventCount() {
        return getEventCount() + getIdentifyCount();
    }

    private synchronized long getEventCountFromTable(String str) {
        long j;
        SQLiteStatement sQLiteStatement = null;
        try {
            try {
                SQLiteDatabase readableDatabase = getReadableDatabase();
                sQLiteStatement = readableDatabase.compileStatement("SELECT COUNT(*) FROM " + str);
                j = sQLiteStatement.simpleQueryForLong();
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                close();
            } catch (StackOverflowError e) {
                logger.m1367e(TAG, String.format("getNumberRows for %s failed", str), e);
                delete();
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                close();
                j = 0;
                return j;
            }
        } catch (SQLiteException e2) {
            logger.m1367e(TAG, String.format("getNumberRows for %s failed", str), e2);
            delete();
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
            close();
            j = 0;
            return j;
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long getNthEventId(long j) {
        return getNthEventIdFromTable(EVENT_TABLE_NAME, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long getNthIdentifyId(long j) {
        return getNthEventIdFromTable(IDENTIFY_TABLE_NAME, j);
    }

    private synchronized long getNthEventIdFromTable(String str, long j) {
        long j2;
        j2 = -1;
        SQLiteStatement sQLiteStatement = null;
        try {
            try {
                SQLiteDatabase readableDatabase = getReadableDatabase();
                SQLiteStatement compileStatement = readableDatabase.compileStatement("SELECT id FROM " + str + " LIMIT 1 OFFSET " + (j - 1));
                try {
                    j2 = compileStatement.simpleQueryForLong();
                } catch (SQLiteDoneException e) {
                    logger.m1360w(TAG, e);
                }
                if (compileStatement != null) {
                    compileStatement.close();
                }
            } catch (SQLiteException e2) {
                logger.m1367e(TAG, String.format("getNthEventId from %s failed", str), e2);
                delete();
                if (0 != 0) {
                    sQLiteStatement.close();
                }
            }
        } catch (StackOverflowError e3) {
            logger.m1367e(TAG, String.format("getNthEventId from %s failed", str), e3);
            delete();
            if (0 != 0) {
                sQLiteStatement.close();
            }
        }
        close();
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void removeEvents(long j) {
        removeEventsFromTable(EVENT_TABLE_NAME, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void removeIdentifys(long j) {
        removeEventsFromTable(IDENTIFY_TABLE_NAME, j);
    }

    private synchronized void removeEventsFromTable(String str, long j) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.delete(str, "id <= " + j, null);
        } catch (SQLiteException e) {
            logger.m1367e(TAG, String.format("removeEvents from %s failed", str), e);
            delete();
        } catch (StackOverflowError e2) {
            logger.m1367e(TAG, String.format("removeEvents from %s failed", str), e2);
            delete();
        }
        close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void removeEvent(long j) {
        removeEventFromTable(EVENT_TABLE_NAME, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void removeIdentify(long j) {
        removeEventFromTable(IDENTIFY_TABLE_NAME, j);
    }

    private synchronized void removeEventFromTable(String str, long j) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.delete(str, "id = " + j, null);
        } catch (SQLiteException e) {
            logger.m1367e(TAG, String.format("removeEvent from %s failed", str), e);
            delete();
        } catch (StackOverflowError e2) {
            logger.m1367e(TAG, String.format("removeEvent from %s failed", str), e2);
            delete();
        }
        close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x00c7, code lost:
        if (r1.isOpen() != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00c9, code lost:
        close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00e5, code lost:
        if (r1.isOpen() != false) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void delete() {
        /*
            Method dump skipped, instructions count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.delete():void");
    }

    boolean dbFileExists() {
        return this.file.exists();
    }

    Cursor queryDb(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
    }

    private void handleIfCursorRowTooLargeException(IllegalStateException illegalStateException) {
        String message = illegalStateException.getMessage();
        if (!C1060Utils.isEmptyString(message) && message.contains("Couldn't read") && message.contains("CursorWindow")) {
            delete();
            return;
        }
        throw illegalStateException;
    }

    private static void convertIfCursorWindowException(RuntimeException runtimeException) {
        String message = runtimeException.getMessage();
        if (!C1060Utils.isEmptyString(message) && (message.startsWith("Cursor window allocation of") || message.startsWith("Could not allocate CursorWindow"))) {
            throw new CursorWindowAllocationException(message);
        }
        throw runtimeException;
    }
}
