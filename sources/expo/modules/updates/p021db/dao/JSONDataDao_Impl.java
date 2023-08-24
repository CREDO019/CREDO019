package expo.modules.updates.p021db.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p007db.SupportSQLiteStatement;
import expo.modules.updates.p021db.Converters;
import expo.modules.updates.p021db.entity.JSONDataEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: expo.modules.updates.db.dao.JSONDataDao_Impl */
/* loaded from: classes5.dex */
public final class JSONDataDao_Impl extends JSONDataDao {
    private final Converters __converters = new Converters();
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<JSONDataEntity> __insertionAdapterOfJSONDataEntity;
    private final SharedSQLiteStatement __preparedStmtOf_deleteJSONDataForKey;

    public JSONDataDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfJSONDataEntity = new EntityInsertionAdapter<JSONDataEntity>(__db) { // from class: expo.modules.updates.db.dao.JSONDataDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `json_data` (`key`,`value`,`last_updated`,`scope_key`,`id`) VALUES (?,?,?,?,nullif(?, 0))";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, JSONDataEntity value) {
                if (value.getKey() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getKey());
                }
                if (value.getValue() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getValue());
                }
                Long dateToLong = JSONDataDao_Impl.this.__converters.dateToLong(value.getLastUpdated());
                if (dateToLong == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindLong(3, dateToLong.longValue());
                }
                if (value.getScopeKey() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getScopeKey());
                }
                stmt.bindLong(5, value.getId());
            }
        };
        this.__preparedStmtOf_deleteJSONDataForKey = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.JSONDataDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM json_data WHERE `key` = ? AND scope_key = ?;";
            }
        };
    }

    @Override // expo.modules.updates.p021db.dao.JSONDataDao
    public void _insertJSONData(final JSONDataEntity jsonDataEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfJSONDataEntity.insert((EntityInsertionAdapter<JSONDataEntity>) jsonDataEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.JSONDataDao
    public void setJSONStringForKey(final String key, final String value, final String scopeKey) {
        this.__db.beginTransaction();
        try {
            super.setJSONStringForKey(key, value, scopeKey);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.JSONDataDao
    public void setMultipleFields(final Map<String, String> fields, final String scopeKey) {
        this.__db.beginTransaction();
        try {
            super.setMultipleFields(fields, scopeKey);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.JSONDataDao
    public void _deleteJSONDataForKey(final String key, final String scopeKey) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_deleteJSONDataForKey.acquire();
        if (key == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, key);
        }
        if (scopeKey == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, scopeKey);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOf_deleteJSONDataForKey.release(acquire);
        }
    }

    @Override // expo.modules.updates.p021db.dao.JSONDataDao
    public List<JSONDataEntity> _loadJSONDataForKey(final String key, final String scopeKey) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM json_data WHERE `key` = ? AND scope_key = ? ORDER BY last_updated DESC LIMIT 1;", 2);
        if (key == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, key);
        }
        if (scopeKey == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, scopeKey);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "key");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "value");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "last_updated");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "scope_key");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "id");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                JSONDataEntity jSONDataEntity = new JSONDataEntity(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), this.__converters.longToDate(query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3))), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                jSONDataEntity.setId(query.getLong(columnIndexOrThrow5));
                arrayList.add(jSONDataEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
