package expo.modules.updates.p021db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p007db.SupportSQLiteStatement;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.updates.p021db.Converters;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateAssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* renamed from: expo.modules.updates.db.dao.AssetDao_Impl */
/* loaded from: classes5.dex */
public final class AssetDao_Impl extends AssetDao {
    private final Converters __converters = new Converters();
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<AssetEntity> __insertionAdapterOfAssetEntity;
    private final EntityInsertionAdapter<UpdateAssetEntity> __insertionAdapterOfUpdateAssetEntity;
    private final SharedSQLiteStatement __preparedStmtOf_deleteAssetsMarkedForDeletion;
    private final SharedSQLiteStatement __preparedStmtOf_markAllAssetsForDeletion;
    private final SharedSQLiteStatement __preparedStmtOf_setUpdateLaunchAsset;
    private final SharedSQLiteStatement __preparedStmtOf_unmarkDuplicateUsedAssetsFromDeletion;
    private final SharedSQLiteStatement __preparedStmtOf_unmarkUsedAssetsFromDeletion;
    private final EntityDeletionOrUpdateAdapter<AssetEntity> __updateAdapterOfAssetEntity;

    public AssetDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfAssetEntity = new EntityInsertionAdapter<AssetEntity>(__db) { // from class: expo.modules.updates.db.dao.AssetDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `assets` (`key`,`type`,`id`,`url`,`headers`,`extra_request_headers`,`metadata`,`download_time`,`relative_path`,`hash`,`hash_type`,`expected_hash`,`marked_for_deletion`) VALUES (?,?,nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, AssetEntity value) {
                if (value.getKey() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getKey());
                }
                if (value.getType() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getType());
                }
                stmt.bindLong(3, value.getId());
                String uriToString = AssetDao_Impl.this.__converters.uriToString(value.getUrl());
                if (uriToString == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, uriToString);
                }
                String jsonObjectToString = AssetDao_Impl.this.__converters.jsonObjectToString(value.getHeaders());
                if (jsonObjectToString == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, jsonObjectToString);
                }
                String jsonObjectToString2 = AssetDao_Impl.this.__converters.jsonObjectToString(value.getExtraRequestHeaders());
                if (jsonObjectToString2 == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, jsonObjectToString2);
                }
                String jsonObjectToString3 = AssetDao_Impl.this.__converters.jsonObjectToString(value.getMetadata());
                if (jsonObjectToString3 == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, jsonObjectToString3);
                }
                Long dateToLong = AssetDao_Impl.this.__converters.dateToLong(value.getDownloadTime());
                if (dateToLong == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindLong(8, dateToLong.longValue());
                }
                if (value.getRelativePath() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getRelativePath());
                }
                if (value.getHash() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindBlob(10, value.getHash());
                }
                stmt.bindLong(11, AssetDao_Impl.this.__converters.hashTypeToInt(value.getHashType()));
                if (value.getExpectedHash() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getExpectedHash());
                }
                stmt.bindLong(13, value.getMarkedForDeletion() ? 1L : 0L);
            }
        };
        this.__insertionAdapterOfUpdateAssetEntity = new EntityInsertionAdapter<UpdateAssetEntity>(__db) { // from class: expo.modules.updates.db.dao.AssetDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `updates_assets` (`update_id`,`asset_id`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, UpdateAssetEntity value) {
                byte[] uuidToBytes = AssetDao_Impl.this.__converters.uuidToBytes(value.getUpdateId());
                if (uuidToBytes == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindBlob(1, uuidToBytes);
                }
                stmt.bindLong(2, value.getAssetId());
            }
        };
        this.__updateAdapterOfAssetEntity = new EntityDeletionOrUpdateAdapter<AssetEntity>(__db) { // from class: expo.modules.updates.db.dao.AssetDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `assets` SET `key` = ?,`type` = ?,`id` = ?,`url` = ?,`headers` = ?,`extra_request_headers` = ?,`metadata` = ?,`download_time` = ?,`relative_path` = ?,`hash` = ?,`hash_type` = ?,`expected_hash` = ?,`marked_for_deletion` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, AssetEntity value) {
                if (value.getKey() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getKey());
                }
                if (value.getType() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getType());
                }
                stmt.bindLong(3, value.getId());
                String uriToString = AssetDao_Impl.this.__converters.uriToString(value.getUrl());
                if (uriToString == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, uriToString);
                }
                String jsonObjectToString = AssetDao_Impl.this.__converters.jsonObjectToString(value.getHeaders());
                if (jsonObjectToString == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, jsonObjectToString);
                }
                String jsonObjectToString2 = AssetDao_Impl.this.__converters.jsonObjectToString(value.getExtraRequestHeaders());
                if (jsonObjectToString2 == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, jsonObjectToString2);
                }
                String jsonObjectToString3 = AssetDao_Impl.this.__converters.jsonObjectToString(value.getMetadata());
                if (jsonObjectToString3 == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, jsonObjectToString3);
                }
                Long dateToLong = AssetDao_Impl.this.__converters.dateToLong(value.getDownloadTime());
                if (dateToLong == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindLong(8, dateToLong.longValue());
                }
                if (value.getRelativePath() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getRelativePath());
                }
                if (value.getHash() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindBlob(10, value.getHash());
                }
                stmt.bindLong(11, AssetDao_Impl.this.__converters.hashTypeToInt(value.getHashType()));
                if (value.getExpectedHash() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getExpectedHash());
                }
                stmt.bindLong(13, value.getMarkedForDeletion() ? 1L : 0L);
                stmt.bindLong(14, value.getId());
            }
        };
        this.__preparedStmtOf_setUpdateLaunchAsset = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.AssetDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE updates SET launch_asset_id = ? WHERE id = ?;";
            }
        };
        this.__preparedStmtOf_markAllAssetsForDeletion = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.AssetDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE assets SET marked_for_deletion = 1;";
            }
        };
        this.__preparedStmtOf_unmarkUsedAssetsFromDeletion = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.AssetDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE assets SET marked_for_deletion = 0 WHERE id IN ( SELECT asset_id FROM updates_assets INNER JOIN updates ON updates_assets.update_id = updates.id WHERE updates.keep);";
            }
        };
        this.__preparedStmtOf_unmarkDuplicateUsedAssetsFromDeletion = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.AssetDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE assets SET marked_for_deletion = 0 WHERE relative_path IN ( SELECT relative_path FROM assets WHERE marked_for_deletion = 0);";
            }
        };
        this.__preparedStmtOf_deleteAssetsMarkedForDeletion = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.AssetDao_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM assets WHERE marked_for_deletion = 1;";
            }
        };
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public long _insertAsset(final AssetEntity asset) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfAssetEntity.insertAndReturnId(asset);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public void _insertUpdateAsset(final UpdateAssetEntity updateAsset) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUpdateAssetEntity.insert((EntityInsertionAdapter<UpdateAssetEntity>) updateAsset);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public void updateAsset(final AssetEntity assetEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfAssetEntity.handle(assetEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public void insertAssets(final List<AssetEntity> assets, final UpdateEntity update) {
        this.__db.beginTransaction();
        try {
            super.insertAssets(assets, update);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public boolean addExistingAssetToUpdate(final UpdateEntity update, final AssetEntity asset, final boolean isLaunchAsset) {
        this.__db.beginTransaction();
        try {
            boolean addExistingAssetToUpdate = super.addExistingAssetToUpdate(update, asset, isLaunchAsset);
            this.__db.setTransactionSuccessful();
            return addExistingAssetToUpdate;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public List<AssetEntity> deleteUnusedAssets() {
        this.__db.beginTransaction();
        try {
            List<AssetEntity> deleteUnusedAssets = super.deleteUnusedAssets();
            this.__db.setTransactionSuccessful();
            return deleteUnusedAssets;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public void _setUpdateLaunchAsset(final long assetId, final UUID updateId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_setUpdateLaunchAsset.acquire();
        acquire.bindLong(1, assetId);
        byte[] uuidToBytes = this.__converters.uuidToBytes(updateId);
        if (uuidToBytes == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindBlob(2, uuidToBytes);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOf_setUpdateLaunchAsset.release(acquire);
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public void _markAllAssetsForDeletion() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_markAllAssetsForDeletion.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOf_markAllAssetsForDeletion.release(acquire);
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public void _unmarkUsedAssetsFromDeletion() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_unmarkUsedAssetsFromDeletion.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOf_unmarkUsedAssetsFromDeletion.release(acquire);
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public void _unmarkDuplicateUsedAssetsFromDeletion() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_unmarkDuplicateUsedAssetsFromDeletion.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOf_unmarkDuplicateUsedAssetsFromDeletion.release(acquire);
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public void _deleteAssetsMarkedForDeletion() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_deleteAssetsMarkedForDeletion.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOf_deleteAssetsMarkedForDeletion.release(acquire);
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public List<AssetEntity> _loadAssetsMarkedForDeletion() {
        RoomSQLiteQuery roomSQLiteQuery;
        int r19;
        String string;
        int r18;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM assets WHERE marked_for_deletion = 1;", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "key");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, SessionDescription.ATTR_TYPE);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, ImagesContract.URL);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "headers");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "extra_request_headers");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.TAG_METADATA);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "download_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "relative_path");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "hash");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "hash_type");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "expected_hash");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "marked_for_deletion");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                    if (query.isNull(columnIndexOrThrow2)) {
                        r19 = columnIndexOrThrow;
                        r18 = columnIndexOrThrow2;
                        string = null;
                    } else {
                        r19 = columnIndexOrThrow;
                        string = query.getString(columnIndexOrThrow2);
                        r18 = columnIndexOrThrow2;
                    }
                    AssetEntity assetEntity = new AssetEntity(string2, string);
                    ArrayList arrayList2 = arrayList;
                    assetEntity.setId(query.getLong(columnIndexOrThrow3));
                    assetEntity.setUrl(this.__converters.stringToUri(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4)));
                    assetEntity.setHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                    assetEntity.setExtraRequestHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                    assetEntity.setMetadata(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7)));
                    assetEntity.setDownloadTime(this.__converters.longToDate(query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8))));
                    assetEntity.setRelativePath(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                    assetEntity.setHash(query.isNull(columnIndexOrThrow10) ? null : query.getBlob(columnIndexOrThrow10));
                    assetEntity.setHashType(this.__converters.intToHashType(query.getInt(columnIndexOrThrow11)));
                    assetEntity.setExpectedHash(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                    int r3 = columnIndexOrThrow13;
                    assetEntity.setMarkedForDeletion(query.getInt(r3) != 0);
                    arrayList2.add(assetEntity);
                    columnIndexOrThrow13 = r3;
                    columnIndexOrThrow2 = r18;
                    arrayList = arrayList2;
                    columnIndexOrThrow = r19;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public List<AssetEntity> _loadAssetWithKey(final String key) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int r19;
        String string;
        int r18;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM assets WHERE `key` = ? LIMIT 1;", 1);
        if (key == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, key);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "key");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, SessionDescription.ATTR_TYPE);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, ImagesContract.URL);
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "headers");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "extra_request_headers");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.TAG_METADATA);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "download_time");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "relative_path");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "hash");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "hash_type");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "expected_hash");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "marked_for_deletion");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                if (query.isNull(columnIndexOrThrow2)) {
                    r19 = columnIndexOrThrow;
                    r18 = columnIndexOrThrow2;
                    string = null;
                } else {
                    r19 = columnIndexOrThrow;
                    string = query.getString(columnIndexOrThrow2);
                    r18 = columnIndexOrThrow2;
                }
                AssetEntity assetEntity = new AssetEntity(string2, string);
                int r0 = columnIndexOrThrow12;
                assetEntity.setId(query.getLong(columnIndexOrThrow3));
                assetEntity.setUrl(this.__converters.stringToUri(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4)));
                assetEntity.setHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                assetEntity.setExtraRequestHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                assetEntity.setMetadata(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7)));
                assetEntity.setDownloadTime(this.__converters.longToDate(query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8))));
                assetEntity.setRelativePath(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                assetEntity.setHash(query.isNull(columnIndexOrThrow10) ? null : query.getBlob(columnIndexOrThrow10));
                assetEntity.setHashType(this.__converters.intToHashType(query.getInt(columnIndexOrThrow11)));
                assetEntity.setExpectedHash(query.isNull(r0) ? null : query.getString(r0));
                int r4 = columnIndexOrThrow13;
                assetEntity.setMarkedForDeletion(query.getInt(r4) != 0);
                arrayList.add(assetEntity);
                columnIndexOrThrow13 = r4;
                columnIndexOrThrow2 = r18;
                columnIndexOrThrow12 = r0;
                columnIndexOrThrow = r19;
            }
            query.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public List<AssetEntity> loadAllAssets() {
        RoomSQLiteQuery roomSQLiteQuery;
        int r19;
        String string;
        int r18;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM assets;", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "key");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, SessionDescription.ATTR_TYPE);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, ImagesContract.URL);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "headers");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "extra_request_headers");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.TAG_METADATA);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "download_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "relative_path");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "hash");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "hash_type");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "expected_hash");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "marked_for_deletion");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                    if (query.isNull(columnIndexOrThrow2)) {
                        r19 = columnIndexOrThrow;
                        r18 = columnIndexOrThrow2;
                        string = null;
                    } else {
                        r19 = columnIndexOrThrow;
                        string = query.getString(columnIndexOrThrow2);
                        r18 = columnIndexOrThrow2;
                    }
                    AssetEntity assetEntity = new AssetEntity(string2, string);
                    ArrayList arrayList2 = arrayList;
                    assetEntity.setId(query.getLong(columnIndexOrThrow3));
                    assetEntity.setUrl(this.__converters.stringToUri(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4)));
                    assetEntity.setHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                    assetEntity.setExtraRequestHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                    assetEntity.setMetadata(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7)));
                    assetEntity.setDownloadTime(this.__converters.longToDate(query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8))));
                    assetEntity.setRelativePath(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                    assetEntity.setHash(query.isNull(columnIndexOrThrow10) ? null : query.getBlob(columnIndexOrThrow10));
                    assetEntity.setHashType(this.__converters.intToHashType(query.getInt(columnIndexOrThrow11)));
                    assetEntity.setExpectedHash(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                    int r3 = columnIndexOrThrow13;
                    assetEntity.setMarkedForDeletion(query.getInt(r3) != 0);
                    arrayList2.add(assetEntity);
                    columnIndexOrThrow13 = r3;
                    columnIndexOrThrow2 = r18;
                    arrayList = arrayList2;
                    columnIndexOrThrow = r19;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // expo.modules.updates.p021db.dao.AssetDao
    public List<AssetEntity> loadAssetsForUpdate(final UUID id) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int r19;
        String string;
        int r18;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT assets.* FROM assets INNER JOIN updates_assets ON updates_assets.asset_id = assets.id INNER JOIN updates ON updates_assets.update_id = updates.id WHERE updates.id = ?;", 1);
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, uuidToBytes);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "key");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, SessionDescription.ATTR_TYPE);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, ImagesContract.URL);
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "headers");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "extra_request_headers");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.TAG_METADATA);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "download_time");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "relative_path");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "hash");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "hash_type");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "expected_hash");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "marked_for_deletion");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                if (query.isNull(columnIndexOrThrow2)) {
                    r19 = columnIndexOrThrow;
                    r18 = columnIndexOrThrow2;
                    string = null;
                } else {
                    r19 = columnIndexOrThrow;
                    string = query.getString(columnIndexOrThrow2);
                    r18 = columnIndexOrThrow2;
                }
                AssetEntity assetEntity = new AssetEntity(string2, string);
                ArrayList arrayList2 = arrayList;
                int r0 = columnIndexOrThrow12;
                assetEntity.setId(query.getLong(columnIndexOrThrow3));
                assetEntity.setUrl(this.__converters.stringToUri(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4)));
                assetEntity.setHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                assetEntity.setExtraRequestHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                assetEntity.setMetadata(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7)));
                assetEntity.setDownloadTime(this.__converters.longToDate(query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8))));
                assetEntity.setRelativePath(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                assetEntity.setHash(query.isNull(columnIndexOrThrow10) ? null : query.getBlob(columnIndexOrThrow10));
                assetEntity.setHashType(this.__converters.intToHashType(query.getInt(columnIndexOrThrow11)));
                assetEntity.setExpectedHash(query.isNull(r0) ? null : query.getString(r0));
                int r3 = columnIndexOrThrow13;
                assetEntity.setMarkedForDeletion(query.getInt(r3) != 0);
                arrayList2.add(assetEntity);
                columnIndexOrThrow12 = r0;
                columnIndexOrThrow13 = r3;
                arrayList = arrayList2;
                columnIndexOrThrow2 = r18;
                columnIndexOrThrow = r19;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
