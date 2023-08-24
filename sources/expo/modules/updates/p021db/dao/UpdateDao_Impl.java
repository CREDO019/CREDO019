package expo.modules.updates.p021db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.p007db.SupportSQLiteStatement;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.updates.p021db.Converters;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.p021db.enums.UpdateStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/* renamed from: expo.modules.updates.db.dao.UpdateDao_Impl */
/* loaded from: classes5.dex */
public final class UpdateDao_Impl extends UpdateDao {
    private final Converters __converters = new Converters();
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<UpdateEntity> __deletionAdapterOfUpdateEntity;
    private final EntityInsertionAdapter<UpdateEntity> __insertionAdapterOfUpdateEntity;
    private final SharedSQLiteStatement __preparedStmtOf_keepUpdate;
    private final SharedSQLiteStatement __preparedStmtOf_markUpdateWithStatus;
    private final EntityDeletionOrUpdateAdapter<UpdateEntity> __updateAdapterOfUpdateEntity;

    public UpdateDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfUpdateEntity = new EntityInsertionAdapter<UpdateEntity>(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `updates` (`id`,`commit_time`,`runtime_version`,`scope_key`,`launch_asset_id`,`manifest`,`status`,`keep`,`last_accessed`,`successful_launch_count`,`failed_launch_count`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, UpdateEntity value) {
                byte[] uuidToBytes = UpdateDao_Impl.this.__converters.uuidToBytes(value.getId());
                if (uuidToBytes == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindBlob(1, uuidToBytes);
                }
                Long dateToLong = UpdateDao_Impl.this.__converters.dateToLong(value.getCommitTime());
                if (dateToLong == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindLong(2, dateToLong.longValue());
                }
                if (value.getRuntimeVersion() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getRuntimeVersion());
                }
                if (value.getScopeKey() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getScopeKey());
                }
                if (value.getLaunchAssetId() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindLong(5, value.getLaunchAssetId().longValue());
                }
                String jsonObjectToString = UpdateDao_Impl.this.__converters.jsonObjectToString(value.getManifest());
                if (jsonObjectToString == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, jsonObjectToString);
                }
                stmt.bindLong(7, UpdateDao_Impl.this.__converters.statusToInt(value.getStatus()));
                stmt.bindLong(8, value.getKeep() ? 1L : 0L);
                Long dateToLong2 = UpdateDao_Impl.this.__converters.dateToLong(value.getLastAccessed());
                if (dateToLong2 == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindLong(9, dateToLong2.longValue());
                }
                stmt.bindLong(10, value.getSuccessfulLaunchCount());
                stmt.bindLong(11, value.getFailedLaunchCount());
            }
        };
        this.__deletionAdapterOfUpdateEntity = new EntityDeletionOrUpdateAdapter<UpdateEntity>(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `updates` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, UpdateEntity value) {
                byte[] uuidToBytes = UpdateDao_Impl.this.__converters.uuidToBytes(value.getId());
                if (uuidToBytes == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindBlob(1, uuidToBytes);
                }
            }
        };
        this.__updateAdapterOfUpdateEntity = new EntityDeletionOrUpdateAdapter<UpdateEntity>(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `updates` SET `id` = ?,`commit_time` = ?,`runtime_version` = ?,`scope_key` = ?,`launch_asset_id` = ?,`manifest` = ?,`status` = ?,`keep` = ?,`last_accessed` = ?,`successful_launch_count` = ?,`failed_launch_count` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, UpdateEntity value) {
                byte[] uuidToBytes = UpdateDao_Impl.this.__converters.uuidToBytes(value.getId());
                if (uuidToBytes == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindBlob(1, uuidToBytes);
                }
                Long dateToLong = UpdateDao_Impl.this.__converters.dateToLong(value.getCommitTime());
                if (dateToLong == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindLong(2, dateToLong.longValue());
                }
                if (value.getRuntimeVersion() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getRuntimeVersion());
                }
                if (value.getScopeKey() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getScopeKey());
                }
                if (value.getLaunchAssetId() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindLong(5, value.getLaunchAssetId().longValue());
                }
                String jsonObjectToString = UpdateDao_Impl.this.__converters.jsonObjectToString(value.getManifest());
                if (jsonObjectToString == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, jsonObjectToString);
                }
                stmt.bindLong(7, UpdateDao_Impl.this.__converters.statusToInt(value.getStatus()));
                stmt.bindLong(8, value.getKeep() ? 1L : 0L);
                Long dateToLong2 = UpdateDao_Impl.this.__converters.dateToLong(value.getLastAccessed());
                if (dateToLong2 == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindLong(9, dateToLong2.longValue());
                }
                stmt.bindLong(10, value.getSuccessfulLaunchCount());
                stmt.bindLong(11, value.getFailedLaunchCount());
                byte[] uuidToBytes2 = UpdateDao_Impl.this.__converters.uuidToBytes(value.getId());
                if (uuidToBytes2 == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindBlob(12, uuidToBytes2);
                }
            }
        };
        this.__preparedStmtOf_keepUpdate = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE updates SET keep = 1 WHERE id = ?;";
            }
        };
        this.__preparedStmtOf_markUpdateWithStatus = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE updates SET status = ? WHERE id = ?;";
            }
        };
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public void insertUpdate(final UpdateEntity update) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUpdateEntity.insert((EntityInsertionAdapter<UpdateEntity>) update);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public void deleteUpdates(final List<UpdateEntity> updates) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfUpdateEntity.handleMultiple(updates);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public void _updateUpdate(final UpdateEntity update) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfUpdateEntity.handle(update);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public void markUpdateFinished(final UpdateEntity update, final boolean hasSkippedEmbeddedAssets) {
        this.__db.beginTransaction();
        try {
            super.markUpdateFinished(update, hasSkippedEmbeddedAssets);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public void _keepUpdate(final UUID id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_keepUpdate.acquire();
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, uuidToBytes);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOf_keepUpdate.release(acquire);
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public void _markUpdateWithStatus(final UpdateStatus status, final UUID id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_markUpdateWithStatus.acquire();
        acquire.bindLong(1, this.__converters.statusToInt(status));
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
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
            this.__preparedStmtOf_markUpdateWithStatus.release(acquire);
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public List<UpdateEntity> _loadLaunchableUpdatesForProjectWithStatuses(final String scopeKey, final List<? extends UpdateStatus> statuses) {
        byte[] blob;
        int r16;
        Long valueOf;
        int r17;
        int r19;
        String string;
        int r18;
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT * FROM updates WHERE scope_key = ");
        newStringBuilder.append("?");
        newStringBuilder.append(" AND (successful_launch_count > 0 OR failed_launch_count < 1) AND status IN (");
        int size = statuses.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(");");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 1);
        if (scopeKey == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, scopeKey);
        }
        int r0 = 2;
        for (UpdateStatus updateStatus : statuses) {
            acquire.bindLong(r0, this.__converters.statusToInt(updateStatus));
            r0++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "commit_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "runtime_version");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "scope_key");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "launch_asset_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "manifest");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "status");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "keep");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "last_accessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "successful_launch_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "failed_launch_count");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    r16 = columnIndexOrThrow;
                    blob = null;
                } else {
                    blob = query.getBlob(columnIndexOrThrow);
                    r16 = columnIndexOrThrow;
                }
                UUID bytesToUuid = this.__converters.bytesToUuid(blob);
                if (query.isNull(columnIndexOrThrow2)) {
                    r17 = columnIndexOrThrow2;
                    valueOf = null;
                } else {
                    valueOf = Long.valueOf(query.getLong(columnIndexOrThrow2));
                    r17 = columnIndexOrThrow2;
                }
                Date longToDate = this.__converters.longToDate(valueOf);
                String string2 = query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3);
                if (query.isNull(columnIndexOrThrow4)) {
                    r19 = columnIndexOrThrow3;
                    r18 = columnIndexOrThrow4;
                    string = null;
                } else {
                    r19 = columnIndexOrThrow3;
                    string = query.getString(columnIndexOrThrow4);
                    r18 = columnIndexOrThrow4;
                }
                UpdateEntity updateEntity = new UpdateEntity(bytesToUuid, longToDate, string2, string);
                updateEntity.setLaunchAssetId(query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5)));
                updateEntity.setManifest(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                updateEntity.setStatus(this.__converters.intToStatus(query.getInt(columnIndexOrThrow7)));
                updateEntity.setKeep(query.getInt(columnIndexOrThrow8) != 0);
                updateEntity.setLastAccessed(this.__converters.longToDate(query.isNull(columnIndexOrThrow9) ? null : Long.valueOf(query.getLong(columnIndexOrThrow9))));
                updateEntity.setSuccessfulLaunchCount(query.getInt(columnIndexOrThrow10));
                updateEntity.setFailedLaunchCount(query.getInt(columnIndexOrThrow11));
                arrayList.add(updateEntity);
                columnIndexOrThrow = r16;
                columnIndexOrThrow2 = r17;
                columnIndexOrThrow4 = r18;
                columnIndexOrThrow3 = r19;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public List<UpdateEntity> _loadUpdatesWithId(final UUID id) {
        byte[] blob;
        int r16;
        Long valueOf;
        int r17;
        int r19;
        String string;
        int r18;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM updates WHERE id = ?;", 1);
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, uuidToBytes);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "commit_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "runtime_version");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "scope_key");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "launch_asset_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "manifest");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "status");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "keep");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "last_accessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "successful_launch_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "failed_launch_count");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    r16 = columnIndexOrThrow;
                    blob = null;
                } else {
                    blob = query.getBlob(columnIndexOrThrow);
                    r16 = columnIndexOrThrow;
                }
                UUID bytesToUuid = this.__converters.bytesToUuid(blob);
                if (query.isNull(columnIndexOrThrow2)) {
                    r17 = columnIndexOrThrow2;
                    valueOf = null;
                } else {
                    valueOf = Long.valueOf(query.getLong(columnIndexOrThrow2));
                    r17 = columnIndexOrThrow2;
                }
                Date longToDate = this.__converters.longToDate(valueOf);
                String string2 = query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3);
                if (query.isNull(columnIndexOrThrow4)) {
                    r19 = columnIndexOrThrow3;
                    r18 = columnIndexOrThrow4;
                    string = null;
                } else {
                    r19 = columnIndexOrThrow3;
                    string = query.getString(columnIndexOrThrow4);
                    r18 = columnIndexOrThrow4;
                }
                UpdateEntity updateEntity = new UpdateEntity(bytesToUuid, longToDate, string2, string);
                updateEntity.setLaunchAssetId(query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5)));
                updateEntity.setManifest(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                updateEntity.setStatus(this.__converters.intToStatus(query.getInt(columnIndexOrThrow7)));
                updateEntity.setKeep(query.getInt(columnIndexOrThrow8) != 0);
                updateEntity.setLastAccessed(this.__converters.longToDate(query.isNull(columnIndexOrThrow9) ? null : Long.valueOf(query.getLong(columnIndexOrThrow9))));
                updateEntity.setSuccessfulLaunchCount(query.getInt(columnIndexOrThrow10));
                updateEntity.setFailedLaunchCount(query.getInt(columnIndexOrThrow11));
                arrayList.add(updateEntity);
                columnIndexOrThrow = r16;
                columnIndexOrThrow2 = r17;
                columnIndexOrThrow4 = r18;
                columnIndexOrThrow3 = r19;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public AssetEntity _loadLaunchAsset(final UUID id) {
        RoomSQLiteQuery roomSQLiteQuery;
        AssetEntity assetEntity;
        String string;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT assets.* FROM assets INNER JOIN updates ON updates.launch_asset_id = assets.id WHERE updates.id = ?;", 1);
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, uuidToBytes);
        }
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
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "marked_for_deletion");
            if (query.moveToFirst()) {
                String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                if (query.isNull(columnIndexOrThrow2)) {
                    roomSQLiteQuery = acquire;
                    string = null;
                } else {
                    string = query.getString(columnIndexOrThrow2);
                    roomSQLiteQuery = acquire;
                }
                try {
                    AssetEntity assetEntity2 = new AssetEntity(string2, string);
                    assetEntity2.setId(query.getLong(columnIndexOrThrow3));
                    assetEntity2.setUrl(this.__converters.stringToUri(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4)));
                    assetEntity2.setHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                    assetEntity2.setExtraRequestHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                    assetEntity2.setMetadata(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7)));
                    assetEntity2.setDownloadTime(this.__converters.longToDate(query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8))));
                    assetEntity2.setRelativePath(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                    assetEntity2.setHash(query.isNull(columnIndexOrThrow10) ? null : query.getBlob(columnIndexOrThrow10));
                    assetEntity2.setHashType(this.__converters.intToHashType(query.getInt(columnIndexOrThrow11)));
                    assetEntity2.setExpectedHash(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                    assetEntity2.setMarkedForDeletion(query.getInt(columnIndexOrThrow13) != 0);
                    assetEntity = assetEntity2;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } else {
                roomSQLiteQuery = acquire;
                assetEntity = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return assetEntity;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public List<UpdateEntity> loadAllUpdates() {
        byte[] blob;
        int r16;
        Long valueOf;
        int r17;
        int r19;
        String string;
        int r18;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM updates;", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "commit_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "runtime_version");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "scope_key");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "launch_asset_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "manifest");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "status");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "keep");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "last_accessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "successful_launch_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "failed_launch_count");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    r16 = columnIndexOrThrow;
                    blob = null;
                } else {
                    blob = query.getBlob(columnIndexOrThrow);
                    r16 = columnIndexOrThrow;
                }
                UUID bytesToUuid = this.__converters.bytesToUuid(blob);
                if (query.isNull(columnIndexOrThrow2)) {
                    r17 = columnIndexOrThrow2;
                    valueOf = null;
                } else {
                    valueOf = Long.valueOf(query.getLong(columnIndexOrThrow2));
                    r17 = columnIndexOrThrow2;
                }
                Date longToDate = this.__converters.longToDate(valueOf);
                String string2 = query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3);
                if (query.isNull(columnIndexOrThrow4)) {
                    r19 = columnIndexOrThrow3;
                    r18 = columnIndexOrThrow4;
                    string = null;
                } else {
                    r19 = columnIndexOrThrow3;
                    string = query.getString(columnIndexOrThrow4);
                    r18 = columnIndexOrThrow4;
                }
                UpdateEntity updateEntity = new UpdateEntity(bytesToUuid, longToDate, string2, string);
                updateEntity.setLaunchAssetId(query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5)));
                updateEntity.setManifest(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                updateEntity.setStatus(this.__converters.intToStatus(query.getInt(columnIndexOrThrow7)));
                updateEntity.setKeep(query.getInt(columnIndexOrThrow8) != 0);
                updateEntity.setLastAccessed(this.__converters.longToDate(query.isNull(columnIndexOrThrow9) ? null : Long.valueOf(query.getLong(columnIndexOrThrow9))));
                updateEntity.setSuccessfulLaunchCount(query.getInt(columnIndexOrThrow10));
                updateEntity.setFailedLaunchCount(query.getInt(columnIndexOrThrow11));
                arrayList.add(updateEntity);
                columnIndexOrThrow = r16;
                columnIndexOrThrow2 = r17;
                columnIndexOrThrow4 = r18;
                columnIndexOrThrow3 = r19;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public List<UpdateEntity> loadAllUpdatesWithStatus(final UpdateStatus status) {
        byte[] blob;
        int r16;
        Long valueOf;
        int r17;
        int r19;
        String string;
        int r18;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM updates WHERE status = ?;", 1);
        acquire.bindLong(1, this.__converters.statusToInt(status));
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "commit_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "runtime_version");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "scope_key");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "launch_asset_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "manifest");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "status");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "keep");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "last_accessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "successful_launch_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "failed_launch_count");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    r16 = columnIndexOrThrow;
                    blob = null;
                } else {
                    blob = query.getBlob(columnIndexOrThrow);
                    r16 = columnIndexOrThrow;
                }
                UUID bytesToUuid = this.__converters.bytesToUuid(blob);
                if (query.isNull(columnIndexOrThrow2)) {
                    r17 = columnIndexOrThrow2;
                    valueOf = null;
                } else {
                    valueOf = Long.valueOf(query.getLong(columnIndexOrThrow2));
                    r17 = columnIndexOrThrow2;
                }
                Date longToDate = this.__converters.longToDate(valueOf);
                String string2 = query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3);
                if (query.isNull(columnIndexOrThrow4)) {
                    r19 = columnIndexOrThrow3;
                    r18 = columnIndexOrThrow4;
                    string = null;
                } else {
                    r19 = columnIndexOrThrow3;
                    string = query.getString(columnIndexOrThrow4);
                    r18 = columnIndexOrThrow4;
                }
                UpdateEntity updateEntity = new UpdateEntity(bytesToUuid, longToDate, string2, string);
                updateEntity.setLaunchAssetId(query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5)));
                updateEntity.setManifest(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                updateEntity.setStatus(this.__converters.intToStatus(query.getInt(columnIndexOrThrow7)));
                updateEntity.setKeep(query.getInt(columnIndexOrThrow8) != 0);
                updateEntity.setLastAccessed(this.__converters.longToDate(query.isNull(columnIndexOrThrow9) ? null : Long.valueOf(query.getLong(columnIndexOrThrow9))));
                updateEntity.setSuccessfulLaunchCount(query.getInt(columnIndexOrThrow10));
                updateEntity.setFailedLaunchCount(query.getInt(columnIndexOrThrow11));
                arrayList.add(updateEntity);
                columnIndexOrThrow = r16;
                columnIndexOrThrow2 = r17;
                columnIndexOrThrow4 = r18;
                columnIndexOrThrow3 = r19;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public List<UUID> loadAllUpdateIdsWithStatus(final UpdateStatus status) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM updates WHERE status = ?;", 1);
        acquire.bindLong(1, this.__converters.statusToInt(status));
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(this.__converters.bytesToUuid(query.isNull(0) ? null : query.getBlob(0)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.p021db.dao.UpdateDao
    public void _markUpdatesWithMissingAssets(final List<Long> missingAssetIds, final UpdateStatus status) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE updates SET status = ");
        newStringBuilder.append("?");
        newStringBuilder.append(" WHERE id IN (SELECT DISTINCT update_id FROM updates_assets WHERE asset_id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, missingAssetIds.size());
        newStringBuilder.append("));");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        compileStatement.bindLong(1, this.__converters.statusToInt(status));
        int r5 = 2;
        for (Long l : missingAssetIds) {
            if (l == null) {
                compileStatement.bindNull(r5);
            } else {
                compileStatement.bindLong(r5, l.longValue());
            }
            r5++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
