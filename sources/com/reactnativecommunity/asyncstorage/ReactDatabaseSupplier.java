package com.reactnativecommunity.asyncstorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class ReactDatabaseSupplier extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "RKStorage";
    private static final int DATABASE_VERSION = 1;
    static final String KEY_COLUMN = "key";
    private static final int SLEEP_TIME_MS = 30;
    static final String TABLE_CATALYST = "catalystLocalStorage";
    static final String VALUE_COLUMN = "value";
    static final String VERSION_TABLE_CREATE = "CREATE TABLE catalystLocalStorage (key TEXT PRIMARY KEY, value TEXT NOT NULL)";
    @Nullable
    private static ReactDatabaseSupplier sReactDatabaseSupplierInstance;
    private Context mContext;
    @Nullable
    private SQLiteDatabase mDb;
    private long mMaximumDatabaseSize;

    private ReactDatabaseSupplier(Context context) {
        super(context, "RKStorage", (SQLiteDatabase.CursorFactory) null, 1);
        this.mMaximumDatabaseSize = BuildConfig.AsyncStorage_db_size.longValue() * 1024 * 1024;
        this.mContext = context;
    }

    public static ReactDatabaseSupplier getInstance(Context context) {
        if (sReactDatabaseSupplierInstance == null) {
            sReactDatabaseSupplierInstance = new ReactDatabaseSupplier(context.getApplicationContext());
        }
        return sReactDatabaseSupplierInstance;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(VERSION_TABLE_CREATE);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int r2, int r3) {
        if (r2 != r3) {
            deleteDatabase();
            onCreate(sQLiteDatabase);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean ensureDatabase() {
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            SQLiteException e = null;
            for (int r2 = 0; r2 < 2; r2++) {
                if (r2 > 0) {
                    try {
                        deleteDatabase();
                    } catch (SQLiteException e2) {
                        e = e2;
                        try {
                            Thread.sleep(30L);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                this.mDb = getWritableDatabase();
            }
            SQLiteDatabase sQLiteDatabase2 = this.mDb;
            if (sQLiteDatabase2 == null) {
                throw e;
            }
            sQLiteDatabase2.setMaximumSize(this.mMaximumDatabaseSize);
            return true;
        }
        return true;
    }

    public synchronized SQLiteDatabase get() {
        ensureDatabase();
        return this.mDb;
    }

    public synchronized void clearAndCloseDatabase() throws RuntimeException {
        try {
            clear();
            closeDatabase();
            FLog.m1340d(ReactConstants.TAG, "Cleaned RKStorage");
        } catch (Exception unused) {
            if (deleteDatabase()) {
                FLog.m1340d(ReactConstants.TAG, "Deleted Local Database RKStorage");
                return;
            }
            throw new RuntimeException("Clearing and deleting database RKStorage failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void clear() {
        get().delete(TABLE_CATALYST, null, null);
    }

    public synchronized void setMaximumSize(long j) {
        this.mMaximumDatabaseSize = j;
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.setMaximumSize(j);
        }
    }

    private synchronized boolean deleteDatabase() {
        closeDatabase();
        return this.mContext.deleteDatabase("RKStorage");
    }

    public synchronized void closeDatabase() {
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            this.mDb.close();
            this.mDb = null;
        }
    }

    public static void deleteInstance() {
        sReactDatabaseSupplierInstance = null;
    }
}
