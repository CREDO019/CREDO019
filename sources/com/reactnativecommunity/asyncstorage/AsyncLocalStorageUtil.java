package com.reactnativecommunity.asyncstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.react.bridge.ReadableArray;
import java.util.Arrays;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class AsyncLocalStorageUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String buildKeySelection(int r2) {
        String[] strArr = new String[r2];
        Arrays.fill(strArr, "?");
        return "key IN (" + TextUtils.join(", ", strArr) + ")";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] buildKeySelectionArgs(ReadableArray readableArray, int r4, int r5) {
        String[] strArr = new String[r5];
        for (int r1 = 0; r1 < r5; r1++) {
            strArr[r1] = readableArray.getString(r4 + r1);
        }
        return strArr;
    }

    @Nullable
    public static String getItemImpl(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query = sQLiteDatabase.query("catalystLocalStorage", new String[]{"value"}, "key=?", new String[]{str}, null, null, null);
        try {
            if (query.moveToFirst()) {
                return query.getString(0);
            }
            return null;
        } finally {
            query.close();
        }
    }

    static boolean setItemImpl(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", str);
        contentValues.put("value", str2);
        return -1 != sQLiteDatabase.insertWithOnConflict("catalystLocalStorage", null, contentValues, 5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean mergeImpl(SQLiteDatabase sQLiteDatabase, String str, String str2) throws JSONException {
        String itemImpl = getItemImpl(sQLiteDatabase, str);
        if (itemImpl != null) {
            JSONObject jSONObject = new JSONObject(itemImpl);
            deepMergeInto(jSONObject, new JSONObject(str2));
            str2 = jSONObject.toString();
        }
        return setItemImpl(sQLiteDatabase, str, str2);
    }

    private static void deepMergeInto(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject optJSONObject = jSONObject2.optJSONObject(next);
            JSONObject optJSONObject2 = jSONObject.optJSONObject(next);
            if (optJSONObject != null && optJSONObject2 != null) {
                deepMergeInto(optJSONObject2, optJSONObject);
                jSONObject.put(next, optJSONObject2);
            } else {
                jSONObject.put(next, jSONObject2.get(next));
            }
        }
    }

    public static void verifyAndForceSqliteCheckpoint(Context context) {
        if (Build.VERSION.SDK_INT < 28) {
            Log.i("AsyncStorage_Next", "SQLite checkpoint not required on this API version.");
        }
        if (!(!context.getDatabasePath("AsyncStorage").exists() && context.getDatabasePath("RKStorage").exists())) {
            Log.i("AsyncStorage_Next", "SQLite checkpoint not required.");
            return;
        }
        try {
            ReactDatabaseSupplier reactDatabaseSupplier = ReactDatabaseSupplier.getInstance(context);
            reactDatabaseSupplier.get().rawQuery("PRAGMA wal_checkpoint", null).close();
            reactDatabaseSupplier.closeDatabase();
            Log.i("AsyncStorage_Next", "Forcing SQLite checkpoint successful.");
        } catch (Exception e) {
            Log.w("AsyncStorage_Next", "Could not force checkpoint on RKStorage, the Next storage might not migrate the data properly: " + e.getMessage());
        }
    }
}
