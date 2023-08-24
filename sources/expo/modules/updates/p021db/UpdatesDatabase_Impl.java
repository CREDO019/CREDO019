package expo.modules.updates.p021db;

import android.os.Build;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.p007db.SupportSQLiteDatabase;
import androidx.sqlite.p007db.SupportSQLiteOpenHelper;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.updates.p021db.dao.AssetDao;
import expo.modules.updates.p021db.dao.AssetDao_Impl;
import expo.modules.updates.p021db.dao.JSONDataDao;
import expo.modules.updates.p021db.dao.JSONDataDao_Impl;
import expo.modules.updates.p021db.dao.UpdateDao;
import expo.modules.updates.p021db.dao.UpdateDao_Impl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: expo.modules.updates.db.UpdatesDatabase_Impl */
/* loaded from: classes5.dex */
public final class UpdatesDatabase_Impl extends UpdatesDatabase {
    private volatile AssetDao _assetDao;
    private volatile JSONDataDao _jSONDataDao;
    private volatile UpdateDao _updateDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(11) { // from class: expo.modules.updates.db.UpdatesDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `updates` (`id` BLOB NOT NULL, `commit_time` INTEGER NOT NULL, `runtime_version` TEXT NOT NULL, `scope_key` TEXT NOT NULL, `launch_asset_id` INTEGER, `manifest` TEXT, `status` INTEGER NOT NULL, `keep` INTEGER NOT NULL, `last_accessed` INTEGER NOT NULL, `successful_launch_count` INTEGER NOT NULL DEFAULT 0, `failed_launch_count` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`id`), FOREIGN KEY(`launch_asset_id`) REFERENCES `assets`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                _db.execSQL("CREATE INDEX IF NOT EXISTS `index_updates_launch_asset_id` ON `updates` (`launch_asset_id`)");
                _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_updates_scope_key_commit_time` ON `updates` (`scope_key`, `commit_time`)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `updates_assets` (`update_id` BLOB NOT NULL, `asset_id` INTEGER NOT NULL, PRIMARY KEY(`update_id`, `asset_id`), FOREIGN KEY(`update_id`) REFERENCES `updates`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`asset_id`) REFERENCES `assets`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                _db.execSQL("CREATE INDEX IF NOT EXISTS `index_updates_assets_asset_id` ON `updates_assets` (`asset_id`)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `assets` (`key` TEXT, `type` TEXT, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `url` TEXT, `headers` TEXT, `extra_request_headers` TEXT, `metadata` TEXT, `download_time` INTEGER, `relative_path` TEXT, `hash` BLOB, `hash_type` INTEGER NOT NULL, `expected_hash` TEXT, `marked_for_deletion` INTEGER NOT NULL)");
                _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_assets_key` ON `assets` (`key`)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `json_data` (`key` TEXT NOT NULL, `value` TEXT NOT NULL, `last_updated` INTEGER NOT NULL, `scope_key` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
                _db.execSQL("CREATE INDEX IF NOT EXISTS `index_json_data_scope_key` ON `json_data` (`scope_key`)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '31dd21ebb478946b3d4dde78b2bccf6f')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `updates`");
                _db.execSQL("DROP TABLE IF EXISTS `updates_assets`");
                _db.execSQL("DROP TABLE IF EXISTS `assets`");
                _db.execSQL("DROP TABLE IF EXISTS `json_data`");
                if (UpdatesDatabase_Impl.this.mCallbacks != null) {
                    int size = UpdatesDatabase_Impl.this.mCallbacks.size();
                    for (int r0 = 0; r0 < size; r0++) {
                        ((RoomDatabase.Callback) UpdatesDatabase_Impl.this.mCallbacks.get(r0)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (UpdatesDatabase_Impl.this.mCallbacks != null) {
                    int size = UpdatesDatabase_Impl.this.mCallbacks.size();
                    for (int r0 = 0; r0 < size; r0++) {
                        ((RoomDatabase.Callback) UpdatesDatabase_Impl.this.mCallbacks.get(r0)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                UpdatesDatabase_Impl.this.mDatabase = _db;
                _db.execSQL("PRAGMA foreign_keys = ON");
                UpdatesDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (UpdatesDatabase_Impl.this.mCallbacks != null) {
                    int size = UpdatesDatabase_Impl.this.mCallbacks.size();
                    for (int r0 = 0; r0 < size; r0++) {
                        ((RoomDatabase.Callback) UpdatesDatabase_Impl.this.mCallbacks.get(r0)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap hashMap = new HashMap(11);
                hashMap.put("id", new TableInfo.Column("id", "BLOB", true, 1, null, 1));
                hashMap.put("commit_time", new TableInfo.Column("commit_time", "INTEGER", true, 0, null, 1));
                hashMap.put("runtime_version", new TableInfo.Column("runtime_version", "TEXT", true, 0, null, 1));
                hashMap.put("scope_key", new TableInfo.Column("scope_key", "TEXT", true, 0, null, 1));
                hashMap.put("launch_asset_id", new TableInfo.Column("launch_asset_id", "INTEGER", false, 0, null, 1));
                hashMap.put("manifest", new TableInfo.Column("manifest", "TEXT", false, 0, null, 1));
                hashMap.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, 1));
                hashMap.put("keep", new TableInfo.Column("keep", "INTEGER", true, 0, null, 1));
                hashMap.put("last_accessed", new TableInfo.Column("last_accessed", "INTEGER", true, 0, null, 1));
                hashMap.put("successful_launch_count", new TableInfo.Column("successful_launch_count", "INTEGER", true, 0, SessionDescription.SUPPORTED_SDP_VERSION, 1));
                hashMap.put("failed_launch_count", new TableInfo.Column("failed_launch_count", "INTEGER", true, 0, SessionDescription.SUPPORTED_SDP_VERSION, 1));
                HashSet hashSet = new HashSet(1);
                hashSet.add(new TableInfo.ForeignKey("assets", "CASCADE", "NO ACTION", Arrays.asList("launch_asset_id"), Arrays.asList("id")));
                HashSet hashSet2 = new HashSet(2);
                hashSet2.add(new TableInfo.Index("index_updates_launch_asset_id", false, Arrays.asList("launch_asset_id"), Arrays.asList("ASC")));
                hashSet2.add(new TableInfo.Index("index_updates_scope_key_commit_time", true, Arrays.asList("scope_key", "commit_time"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo = new TableInfo("updates", hashMap, hashSet, hashSet2);
                TableInfo read = TableInfo.read(_db, "updates");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "updates(expo.modules.updates.db.entity.UpdateEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(2);
                hashMap2.put("update_id", new TableInfo.Column("update_id", "BLOB", true, 1, null, 1));
                hashMap2.put("asset_id", new TableInfo.Column("asset_id", "INTEGER", true, 2, null, 1));
                HashSet hashSet3 = new HashSet(2);
                hashSet3.add(new TableInfo.ForeignKey("updates", "CASCADE", "NO ACTION", Arrays.asList("update_id"), Arrays.asList("id")));
                hashSet3.add(new TableInfo.ForeignKey("assets", "CASCADE", "NO ACTION", Arrays.asList("asset_id"), Arrays.asList("id")));
                HashSet hashSet4 = new HashSet(1);
                hashSet4.add(new TableInfo.Index("index_updates_assets_asset_id", false, Arrays.asList("asset_id"), Arrays.asList("ASC")));
                TableInfo tableInfo2 = new TableInfo("updates_assets", hashMap2, hashSet3, hashSet4);
                TableInfo read2 = TableInfo.read(_db, "updates_assets");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "updates_assets(expo.modules.updates.db.entity.UpdateAssetEntity).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(13);
                hashMap3.put("key", new TableInfo.Column("key", "TEXT", false, 0, null, 1));
                hashMap3.put(SessionDescription.ATTR_TYPE, new TableInfo.Column(SessionDescription.ATTR_TYPE, "TEXT", false, 0, null, 1));
                hashMap3.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap3.put(ImagesContract.URL, new TableInfo.Column(ImagesContract.URL, "TEXT", false, 0, null, 1));
                hashMap3.put("headers", new TableInfo.Column("headers", "TEXT", false, 0, null, 1));
                hashMap3.put("extra_request_headers", new TableInfo.Column("extra_request_headers", "TEXT", false, 0, null, 1));
                hashMap3.put(TtmlNode.TAG_METADATA, new TableInfo.Column(TtmlNode.TAG_METADATA, "TEXT", false, 0, null, 1));
                hashMap3.put("download_time", new TableInfo.Column("download_time", "INTEGER", false, 0, null, 1));
                hashMap3.put("relative_path", new TableInfo.Column("relative_path", "TEXT", false, 0, null, 1));
                hashMap3.put("hash", new TableInfo.Column("hash", "BLOB", false, 0, null, 1));
                hashMap3.put("hash_type", new TableInfo.Column("hash_type", "INTEGER", true, 0, null, 1));
                hashMap3.put("expected_hash", new TableInfo.Column("expected_hash", "TEXT", false, 0, null, 1));
                hashMap3.put("marked_for_deletion", new TableInfo.Column("marked_for_deletion", "INTEGER", true, 0, null, 1));
                HashSet hashSet5 = new HashSet(0);
                HashSet hashSet6 = new HashSet(1);
                hashSet6.add(new TableInfo.Index("index_assets_key", true, Arrays.asList("key"), Arrays.asList("ASC")));
                TableInfo tableInfo3 = new TableInfo("assets", hashMap3, hashSet5, hashSet6);
                TableInfo read3 = TableInfo.read(_db, "assets");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "assets(expo.modules.updates.db.entity.AssetEntity).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                HashMap hashMap4 = new HashMap(5);
                hashMap4.put("key", new TableInfo.Column("key", "TEXT", true, 0, null, 1));
                hashMap4.put("value", new TableInfo.Column("value", "TEXT", true, 0, null, 1));
                hashMap4.put("last_updated", new TableInfo.Column("last_updated", "INTEGER", true, 0, null, 1));
                hashMap4.put("scope_key", new TableInfo.Column("scope_key", "TEXT", true, 0, null, 1));
                hashMap4.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                HashSet hashSet7 = new HashSet(0);
                HashSet hashSet8 = new HashSet(1);
                hashSet8.add(new TableInfo.Index("index_json_data_scope_key", false, Arrays.asList("scope_key"), Arrays.asList("ASC")));
                TableInfo tableInfo4 = new TableInfo("json_data", hashMap4, hashSet7, hashSet8);
                TableInfo read4 = TableInfo.read(_db, "json_data");
                if (!tableInfo4.equals(read4)) {
                    return new RoomOpenHelper.ValidationResult(false, "json_data(expo.modules.updates.db.entity.JSONDataEntity).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "31dd21ebb478946b3d4dde78b2bccf6f", "f2fb4f2eca88efe929fb3ad18ad87dec")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "updates", "updates_assets", "assets", "json_data");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        boolean z = Build.VERSION.SDK_INT >= 21;
        if (!z) {
            try {
                writableDatabase.execSQL("PRAGMA foreign_keys = FALSE");
            } finally {
                super.endTransaction();
                if (!z) {
                    writableDatabase.execSQL("PRAGMA foreign_keys = TRUE");
                }
                writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
                if (!writableDatabase.inTransaction()) {
                    writableDatabase.execSQL("VACUUM");
                }
            }
        }
        super.beginTransaction();
        if (z) {
            writableDatabase.execSQL("PRAGMA defer_foreign_keys = TRUE");
        }
        writableDatabase.execSQL("DELETE FROM `updates`");
        writableDatabase.execSQL("DELETE FROM `updates_assets`");
        writableDatabase.execSQL("DELETE FROM `assets`");
        writableDatabase.execSQL("DELETE FROM `json_data`");
        super.setTransactionSuccessful();
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(UpdateDao.class, UpdateDao_Impl.getRequiredConverters());
        hashMap.put(AssetDao.class, AssetDao_Impl.getRequiredConverters());
        hashMap.put(JSONDataDao.class, JSONDataDao_Impl.getRequiredConverters());
        return hashMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // expo.modules.updates.p021db.UpdatesDatabase
    public UpdateDao updateDao() {
        UpdateDao updateDao;
        if (this._updateDao != null) {
            return this._updateDao;
        }
        synchronized (this) {
            if (this._updateDao == null) {
                this._updateDao = new UpdateDao_Impl(this);
            }
            updateDao = this._updateDao;
        }
        return updateDao;
    }

    @Override // expo.modules.updates.p021db.UpdatesDatabase
    public AssetDao assetDao() {
        AssetDao assetDao;
        if (this._assetDao != null) {
            return this._assetDao;
        }
        synchronized (this) {
            if (this._assetDao == null) {
                this._assetDao = new AssetDao_Impl(this);
            }
            assetDao = this._assetDao;
        }
        return assetDao;
    }

    @Override // expo.modules.updates.p021db.UpdatesDatabase
    public JSONDataDao jsonDataDao() {
        JSONDataDao jSONDataDao;
        if (this._jSONDataDao != null) {
            return this._jSONDataDao;
        }
        synchronized (this) {
            if (this._jSONDataDao == null) {
                this._jSONDataDao = new JSONDataDao_Impl(this);
            }
            jSONDataDao = this._jSONDataDao;
        }
        return jSONDataDao;
    }
}
