package androidx.work.impl.model;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.p007db.SupportSQLiteStatement;
import androidx.webkit.ProxyConfig;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final class WorkSpecDao_Impl implements WorkSpecDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<WorkSpec> __insertionAdapterOfWorkSpec;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfIncrementWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfMarkWorkSpecScheduled;

    /* renamed from: __preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast */
    private final SharedSQLiteStatement f62xd91a9514;
    private final SharedSQLiteStatement __preparedStmtOfResetScheduledState;
    private final SharedSQLiteStatement __preparedStmtOfResetWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfSetOutput;
    private final SharedSQLiteStatement __preparedStmtOfSetPeriodStartTime;

    public WorkSpecDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfWorkSpec = new EntityInsertionAdapter<WorkSpec>(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`period_start_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, WorkSpec value) {
                if (value.f59id == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.f59id);
                }
                stmt.bindLong(2, WorkTypeConverters.stateToInt(value.state));
                if (value.workerClassName == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.workerClassName);
                }
                if (value.inputMergerClassName == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.inputMergerClassName);
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(value.input);
                if (byteArrayInternal == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindBlob(5, byteArrayInternal);
                }
                byte[] byteArrayInternal2 = Data.toByteArrayInternal(value.output);
                if (byteArrayInternal2 == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindBlob(6, byteArrayInternal2);
                }
                stmt.bindLong(7, value.initialDelay);
                stmt.bindLong(8, value.intervalDuration);
                stmt.bindLong(9, value.flexDuration);
                stmt.bindLong(10, value.runAttemptCount);
                stmt.bindLong(11, WorkTypeConverters.backoffPolicyToInt(value.backoffPolicy));
                stmt.bindLong(12, value.backoffDelayDuration);
                stmt.bindLong(13, value.periodStartTime);
                stmt.bindLong(14, value.minimumRetentionDuration);
                stmt.bindLong(15, value.scheduleRequestedAt);
                stmt.bindLong(16, value.expedited ? 1L : 0L);
                stmt.bindLong(17, WorkTypeConverters.outOfQuotaPolicyToInt(value.outOfQuotaPolicy));
                Constraints constraints = value.constraints;
                if (constraints != null) {
                    stmt.bindLong(18, WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()));
                    stmt.bindLong(19, constraints.requiresCharging() ? 1L : 0L);
                    stmt.bindLong(20, constraints.requiresDeviceIdle() ? 1L : 0L);
                    stmt.bindLong(21, constraints.requiresBatteryNotLow() ? 1L : 0L);
                    stmt.bindLong(22, constraints.requiresStorageNotLow() ? 1L : 0L);
                    stmt.bindLong(23, constraints.getTriggerContentUpdateDelay());
                    stmt.bindLong(24, constraints.getTriggerMaxContentDelay());
                    byte[] contentUriTriggersToByteArray = WorkTypeConverters.contentUriTriggersToByteArray(constraints.getContentUriTriggers());
                    if (contentUriTriggersToByteArray == null) {
                        stmt.bindNull(25);
                        return;
                    } else {
                        stmt.bindBlob(25, contentUriTriggersToByteArray);
                        return;
                    }
                }
                stmt.bindNull(18);
                stmt.bindNull(19);
                stmt.bindNull(20);
                stmt.bindNull(21);
                stmt.bindNull(22);
                stmt.bindNull(23);
                stmt.bindNull(24);
                stmt.bindNull(25);
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.__preparedStmtOfSetOutput = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetPeriodStartTime = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET period_start_time=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecRunAttemptCount = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.__preparedStmtOfMarkWorkSpecScheduled = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetScheduledState = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.f62xd91a9514 = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void insertWorkSpec(final WorkSpec workSpec) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkSpec.insert((EntityInsertionAdapter<WorkSpec>) workSpec);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void delete(final String id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDelete.acquire();
        if (id == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(acquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setOutput(final String id, final Data output) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetOutput.acquire();
        byte[] byteArrayInternal = Data.toByteArrayInternal(output);
        if (byteArrayInternal == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, byteArrayInternal);
        }
        if (id == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, id);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetOutput.release(acquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setPeriodStartTime(final String id, final long periodStartTime) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetPeriodStartTime.acquire();
        acquire.bindLong(1, periodStartTime);
        if (id == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, id);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetPeriodStartTime.release(acquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int incrementWorkSpecRunAttemptCount(final String id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.acquire();
        if (id == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(acquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetWorkSpecRunAttemptCount(final String id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetWorkSpecRunAttemptCount.acquire();
        if (id == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(acquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int markWorkSpecScheduled(final String id, final long startTime) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkWorkSpecScheduled.acquire();
        acquire.bindLong(1, startTime);
        if (id == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, id);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkWorkSpecScheduled.release(acquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetScheduledState() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetScheduledState.acquire();
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetScheduledState.release(acquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.f62xd91a9514.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.f62xd91a9514.release(acquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec getWorkSpec(final String id) {
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
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        WorkSpec workSpec;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE id=?", 1);
        if (id == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "input");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "output");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "period_start_time");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
            if (query.moveToFirst()) {
                String string = query.getString(columnIndexOrThrow9);
                String string2 = query.getString(columnIndexOrThrow11);
                Constraints constraints = new Constraints();
                constraints.setRequiredNetworkType(WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow)));
                constraints.setRequiresCharging(query.getInt(columnIndexOrThrow2) != 0);
                constraints.setRequiresDeviceIdle(query.getInt(columnIndexOrThrow3) != 0);
                constraints.setRequiresBatteryNotLow(query.getInt(columnIndexOrThrow4) != 0);
                constraints.setRequiresStorageNotLow(query.getInt(columnIndexOrThrow5) != 0);
                constraints.setTriggerContentUpdateDelay(query.getLong(columnIndexOrThrow6));
                constraints.setTriggerMaxContentDelay(query.getLong(columnIndexOrThrow7));
                constraints.setContentUriTriggers(WorkTypeConverters.byteArrayToContentUriTriggers(query.getBlob(columnIndexOrThrow8)));
                WorkSpec workSpec2 = new WorkSpec(string, string2);
                workSpec2.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow10));
                workSpec2.inputMergerClassName = query.getString(columnIndexOrThrow12);
                workSpec2.input = Data.fromByteArray(query.getBlob(columnIndexOrThrow13));
                workSpec2.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow14));
                workSpec2.initialDelay = query.getLong(columnIndexOrThrow15);
                workSpec2.intervalDuration = query.getLong(columnIndexOrThrow16);
                workSpec2.flexDuration = query.getLong(columnIndexOrThrow17);
                workSpec2.runAttemptCount = query.getInt(columnIndexOrThrow18);
                workSpec2.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow19));
                workSpec2.backoffDelayDuration = query.getLong(columnIndexOrThrow20);
                workSpec2.periodStartTime = query.getLong(columnIndexOrThrow21);
                workSpec2.minimumRetentionDuration = query.getLong(columnIndexOrThrow22);
                workSpec2.scheduleRequestedAt = query.getLong(columnIndexOrThrow23);
                workSpec2.expedited = query.getInt(columnIndexOrThrow24) != 0;
                workSpec2.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(columnIndexOrThrow25));
                workSpec2.constraints = constraints;
                workSpec = workSpec2;
            } else {
                workSpec = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return workSpec;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec[] getWorkSpecs(final List<String> ids) {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT ");
        newStringBuilder.append(ProxyConfig.MATCH_ALL_SCHEMES);
        newStringBuilder.append(" FROM workspec WHERE id IN (");
        int size = ids.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int r5 = 1;
        for (String str : ids) {
            if (str == null) {
                acquire.bindNull(r5);
            } else {
                acquire.bindString(r5, str);
            }
            r5++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "output");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "period_start_time");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                WorkSpec[] workSpecArr = new WorkSpec[query.getCount()];
                int r28 = 0;
                while (query.moveToNext()) {
                    WorkSpec[] workSpecArr2 = workSpecArr;
                    String string = query.getString(columnIndexOrThrow9);
                    int r30 = columnIndexOrThrow9;
                    String string2 = query.getString(columnIndexOrThrow11);
                    int r31 = columnIndexOrThrow11;
                    Constraints constraints = new Constraints();
                    int r33 = columnIndexOrThrow;
                    constraints.setRequiredNetworkType(WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow)));
                    constraints.setRequiresCharging(query.getInt(columnIndexOrThrow2) != 0);
                    constraints.setRequiresDeviceIdle(query.getInt(columnIndexOrThrow3) != 0);
                    constraints.setRequiresBatteryNotLow(query.getInt(columnIndexOrThrow4) != 0);
                    constraints.setRequiresStorageNotLow(query.getInt(columnIndexOrThrow5) != 0);
                    int r0 = columnIndexOrThrow2;
                    int r32 = columnIndexOrThrow3;
                    constraints.setTriggerContentUpdateDelay(query.getLong(columnIndexOrThrow6));
                    constraints.setTriggerMaxContentDelay(query.getLong(columnIndexOrThrow7));
                    constraints.setContentUriTriggers(WorkTypeConverters.byteArrayToContentUriTriggers(query.getBlob(columnIndexOrThrow8)));
                    WorkSpec workSpec = new WorkSpec(string, string2);
                    workSpec.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow10));
                    workSpec.inputMergerClassName = query.getString(columnIndexOrThrow12);
                    workSpec.input = Data.fromByteArray(query.getBlob(columnIndexOrThrow13));
                    workSpec.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow14));
                    int r7 = columnIndexOrThrow14;
                    int r2 = columnIndexOrThrow15;
                    workSpec.initialDelay = query.getLong(r2);
                    columnIndexOrThrow15 = r2;
                    int r02 = columnIndexOrThrow16;
                    workSpec.intervalDuration = query.getLong(r02);
                    int r18 = columnIndexOrThrow12;
                    int r1 = columnIndexOrThrow17;
                    workSpec.flexDuration = query.getLong(r1);
                    int r22 = columnIndexOrThrow18;
                    workSpec.runAttemptCount = query.getInt(r22);
                    int r3 = columnIndexOrThrow19;
                    workSpec.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(r3));
                    columnIndexOrThrow17 = r1;
                    int r03 = columnIndexOrThrow20;
                    workSpec.backoffDelayDuration = query.getLong(r03);
                    int r12 = columnIndexOrThrow21;
                    workSpec.periodStartTime = query.getLong(r12);
                    columnIndexOrThrow21 = r12;
                    int r23 = columnIndexOrThrow22;
                    workSpec.minimumRetentionDuration = query.getLong(r23);
                    columnIndexOrThrow22 = r23;
                    int r04 = columnIndexOrThrow23;
                    workSpec.scheduleRequestedAt = query.getLong(r04);
                    int r13 = columnIndexOrThrow24;
                    workSpec.expedited = query.getInt(r13) != 0;
                    int r24 = columnIndexOrThrow25;
                    workSpec.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(r24));
                    workSpec.constraints = constraints;
                    workSpecArr2[r28] = workSpec;
                    r28++;
                    columnIndexOrThrow25 = r24;
                    columnIndexOrThrow2 = r0;
                    columnIndexOrThrow23 = r04;
                    workSpecArr = workSpecArr2;
                    columnIndexOrThrow9 = r30;
                    columnIndexOrThrow11 = r31;
                    columnIndexOrThrow = r33;
                    columnIndexOrThrow24 = r13;
                    columnIndexOrThrow14 = r7;
                    columnIndexOrThrow3 = r32;
                    columnIndexOrThrow20 = r03;
                    columnIndexOrThrow12 = r18;
                    columnIndexOrThrow16 = r02;
                    columnIndexOrThrow18 = r22;
                    columnIndexOrThrow19 = r3;
                }
                WorkSpec[] workSpecArr3 = workSpecArr;
                query.close();
                roomSQLiteQuery.release();
                return workSpecArr3;
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

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(final String name) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (name == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, name);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                WorkSpec.IdAndState idAndState = new WorkSpec.IdAndState();
                idAndState.f60id = query.getString(columnIndexOrThrow);
                idAndState.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                arrayList.add(idAndState);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getAllWorkSpecIds() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData<List<String>> getAllWorkSpecIdsLiveData() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"workspec"}, true, new Callable<List<String>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.10
            @Override // java.util.concurrent.Callable
            public List<String> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, false, null);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(query.getString(0));
                    }
                    WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return arrayList;
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkInfo.State getState(final String id) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT state FROM workspec WHERE id=?", 1);
        if (id == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            return query.moveToFirst() ? WorkTypeConverters.intToState(query.getInt(0)) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec.WorkInfoPojo getWorkStatusPojoForId(final String id) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id=?", 1);
        if (id == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            WorkSpec.WorkInfoPojo workInfoPojo = null;
            Cursor query = DBUtil.query(this.__db, acquire, true, null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            ArrayMap<String, ArrayList<String>> arrayMap = new ArrayMap<>();
            ArrayMap<String, ArrayList<Data>> arrayMap2 = new ArrayMap<>();
            while (query.moveToNext()) {
                if (!query.isNull(columnIndexOrThrow)) {
                    String string = query.getString(columnIndexOrThrow);
                    if (arrayMap.get(string) == null) {
                        arrayMap.put(string, new ArrayList<>());
                    }
                }
                if (!query.isNull(columnIndexOrThrow)) {
                    String string2 = query.getString(columnIndexOrThrow);
                    if (arrayMap2.get(string2) == null) {
                        arrayMap2.put(string2, new ArrayList<>());
                    }
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
            __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
            if (query.moveToFirst()) {
                ArrayList<String> arrayList = !query.isNull(columnIndexOrThrow) ? arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                ArrayList<Data> arrayList2 = query.isNull(columnIndexOrThrow) ? null : arrayMap2.get(query.getString(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                WorkSpec.WorkInfoPojo workInfoPojo2 = new WorkSpec.WorkInfoPojo();
                workInfoPojo2.f61id = query.getString(columnIndexOrThrow);
                workInfoPojo2.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                workInfoPojo2.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                workInfoPojo2.runAttemptCount = query.getInt(columnIndexOrThrow4);
                workInfoPojo2.tags = arrayList;
                workInfoPojo2.progress = arrayList2;
                workInfoPojo = workInfoPojo2;
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return workInfoPojo;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForIds(final List<String> ids) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (");
        int size = ids.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int r2 = 1;
        for (String str : ids) {
            if (str == null) {
                acquire.bindNull(r2);
            } else {
                acquire.bindString(r2, str);
            }
            r2++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, true, null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            ArrayMap<String, ArrayList<String>> arrayMap = new ArrayMap<>();
            ArrayMap<String, ArrayList<Data>> arrayMap2 = new ArrayMap<>();
            while (query.moveToNext()) {
                if (!query.isNull(columnIndexOrThrow)) {
                    String string = query.getString(columnIndexOrThrow);
                    if (arrayMap.get(string) == null) {
                        arrayMap.put(string, new ArrayList<>());
                    }
                }
                if (!query.isNull(columnIndexOrThrow)) {
                    String string2 = query.getString(columnIndexOrThrow);
                    if (arrayMap2.get(string2) == null) {
                        arrayMap2.put(string2, new ArrayList<>());
                    }
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
            __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                ArrayList<String> arrayList2 = !query.isNull(columnIndexOrThrow) ? arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<Data> arrayList3 = !query.isNull(columnIndexOrThrow) ? arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList<>();
                }
                WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                workInfoPojo.f61id = query.getString(columnIndexOrThrow);
                workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                workInfoPojo.tags = arrayList2;
                workInfoPojo.progress = arrayList3;
                arrayList.add(workInfoPojo);
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForIds(final List<String> ids) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (");
        int size = ids.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int r2 = 1;
        for (String str : ids) {
            if (str == null) {
                acquire.bindNull(r2);
            } else {
                acquire.bindString(r2, str);
            }
            r2++;
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.11
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, true, null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
                    ArrayMap arrayMap = new ArrayMap();
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap.get(string)) == null) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string2 = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap2.get(string2)) == null) {
                                arrayMap2.put(string2, new ArrayList());
                            }
                        }
                    }
                    query.moveToPosition(-1);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ArrayList arrayList2 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                        workInfoPojo.f61id = query.getString(columnIndexOrThrow);
                        workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                        workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                        workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                        workInfoPojo.tags = arrayList2;
                        workInfoPojo.progress = arrayList3;
                        arrayList.add(workInfoPojo);
                    }
                    WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return arrayList;
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForTag(final String tag) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (tag == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, tag);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, true, null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            ArrayMap<String, ArrayList<String>> arrayMap = new ArrayMap<>();
            ArrayMap<String, ArrayList<Data>> arrayMap2 = new ArrayMap<>();
            while (query.moveToNext()) {
                if (!query.isNull(columnIndexOrThrow)) {
                    String string = query.getString(columnIndexOrThrow);
                    if (arrayMap.get(string) == null) {
                        arrayMap.put(string, new ArrayList<>());
                    }
                }
                if (!query.isNull(columnIndexOrThrow)) {
                    String string2 = query.getString(columnIndexOrThrow);
                    if (arrayMap2.get(string2) == null) {
                        arrayMap2.put(string2, new ArrayList<>());
                    }
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
            __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                ArrayList<String> arrayList2 = !query.isNull(columnIndexOrThrow) ? arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<Data> arrayList3 = !query.isNull(columnIndexOrThrow) ? arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList<>();
                }
                WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                workInfoPojo.f61id = query.getString(columnIndexOrThrow);
                workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                workInfoPojo.tags = arrayList2;
                workInfoPojo.progress = arrayList3;
                arrayList.add(workInfoPojo);
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForTag(final String tag) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (tag == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, tag);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec", "worktag"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.12
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, true, null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
                    ArrayMap arrayMap = new ArrayMap();
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap.get(string)) == null) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string2 = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap2.get(string2)) == null) {
                                arrayMap2.put(string2, new ArrayList());
                            }
                        }
                    }
                    query.moveToPosition(-1);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ArrayList arrayList2 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                        workInfoPojo.f61id = query.getString(columnIndexOrThrow);
                        workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                        workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                        workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                        workInfoPojo.tags = arrayList2;
                        workInfoPojo.progress = arrayList3;
                        arrayList.add(workInfoPojo);
                    }
                    WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return arrayList;
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForName(final String name) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (name == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, name);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, true, null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            ArrayMap<String, ArrayList<String>> arrayMap = new ArrayMap<>();
            ArrayMap<String, ArrayList<Data>> arrayMap2 = new ArrayMap<>();
            while (query.moveToNext()) {
                if (!query.isNull(columnIndexOrThrow)) {
                    String string = query.getString(columnIndexOrThrow);
                    if (arrayMap.get(string) == null) {
                        arrayMap.put(string, new ArrayList<>());
                    }
                }
                if (!query.isNull(columnIndexOrThrow)) {
                    String string2 = query.getString(columnIndexOrThrow);
                    if (arrayMap2.get(string2) == null) {
                        arrayMap2.put(string2, new ArrayList<>());
                    }
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
            __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                ArrayList<String> arrayList2 = !query.isNull(columnIndexOrThrow) ? arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<Data> arrayList3 = !query.isNull(columnIndexOrThrow) ? arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList<>();
                }
                WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                workInfoPojo.f61id = query.getString(columnIndexOrThrow);
                workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                workInfoPojo.tags = arrayList2;
                workInfoPojo.progress = arrayList3;
                arrayList.add(workInfoPojo);
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForName(final String name) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (name == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, name);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec", "workname"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.13
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, true, null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
                    ArrayMap arrayMap = new ArrayMap();
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap.get(string)) == null) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string2 = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap2.get(string2)) == null) {
                                arrayMap2.put(string2, new ArrayList());
                            }
                        }
                    }
                    query.moveToPosition(-1);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ArrayList arrayList2 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                        workInfoPojo.f61id = query.getString(columnIndexOrThrow);
                        workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                        workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                        workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                        workInfoPojo.tags = arrayList2;
                        workInfoPojo.progress = arrayList3;
                        arrayList.add(workInfoPojo);
                    }
                    WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                    query.close();
                    return arrayList;
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<Data> getInputsFromPrerequisites(final String id) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
        if (id == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(Data.fromByteArray(query.getBlob(0)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getUnfinishedWorkWithTag(final String tag) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (tag == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, tag);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getUnfinishedWorkWithName(final String name) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (name == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, name);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getAllUnfinishedWork() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public boolean hasUnfinishedWork() {
        boolean z = false;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                if (query.getInt(0) != 0) {
                    z = true;
                }
            }
            return z;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData<Long> getScheduleRequestedAtLiveData(final String id) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT schedule_requested_at FROM workspec WHERE id=?", 1);
        if (id == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"workspec"}, false, new Callable<Long>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.14
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                Long l = null;
                Cursor query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, false, null);
                try {
                    if (query.moveToFirst() && !query.isNull(0)) {
                        l = Long.valueOf(query.getLong(0));
                    }
                    return l;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getEligibleWorkForScheduling(final int schedulerLimit) {
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
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY period_start_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))", 1);
        acquire.bindLong(1, schedulerLimit);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "input");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "output");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "period_start_time");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
            int r28 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                String string = query.getString(columnIndexOrThrow9);
                int r29 = columnIndexOrThrow9;
                String string2 = query.getString(columnIndexOrThrow11);
                int r30 = columnIndexOrThrow11;
                Constraints constraints = new Constraints();
                int r32 = columnIndexOrThrow;
                constraints.setRequiredNetworkType(WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow)));
                constraints.setRequiresCharging(query.getInt(columnIndexOrThrow2) != 0);
                constraints.setRequiresDeviceIdle(query.getInt(columnIndexOrThrow3) != 0);
                constraints.setRequiresBatteryNotLow(query.getInt(columnIndexOrThrow4) != 0);
                constraints.setRequiresStorageNotLow(query.getInt(columnIndexOrThrow5) != 0);
                int r0 = columnIndexOrThrow2;
                int r31 = columnIndexOrThrow3;
                constraints.setTriggerContentUpdateDelay(query.getLong(columnIndexOrThrow6));
                constraints.setTriggerMaxContentDelay(query.getLong(columnIndexOrThrow7));
                constraints.setContentUriTriggers(WorkTypeConverters.byteArrayToContentUriTriggers(query.getBlob(columnIndexOrThrow8)));
                WorkSpec workSpec = new WorkSpec(string, string2);
                workSpec.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow10));
                workSpec.inputMergerClassName = query.getString(columnIndexOrThrow12);
                workSpec.input = Data.fromByteArray(query.getBlob(columnIndexOrThrow13));
                int r1 = r28;
                workSpec.output = Data.fromByteArray(query.getBlob(r1));
                r28 = r1;
                int r7 = columnIndexOrThrow15;
                workSpec.initialDelay = query.getLong(r7);
                int r17 = columnIndexOrThrow12;
                int r02 = columnIndexOrThrow16;
                workSpec.intervalDuration = query.getLong(r02);
                int r2 = columnIndexOrThrow4;
                int r12 = columnIndexOrThrow17;
                workSpec.flexDuration = query.getLong(r12);
                int r72 = columnIndexOrThrow18;
                workSpec.runAttemptCount = query.getInt(r72);
                int r8 = columnIndexOrThrow19;
                workSpec.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(r8));
                columnIndexOrThrow17 = r12;
                int r03 = columnIndexOrThrow20;
                workSpec.backoffDelayDuration = query.getLong(r03);
                int r13 = columnIndexOrThrow21;
                workSpec.periodStartTime = query.getLong(r13);
                columnIndexOrThrow21 = r13;
                int r73 = columnIndexOrThrow22;
                workSpec.minimumRetentionDuration = query.getLong(r73);
                int r04 = columnIndexOrThrow23;
                workSpec.scheduleRequestedAt = query.getLong(r04);
                int r14 = columnIndexOrThrow24;
                workSpec.expedited = query.getInt(r14) != 0;
                int r22 = columnIndexOrThrow25;
                workSpec.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(r22));
                workSpec.constraints = constraints;
                arrayList.add(workSpec);
                columnIndexOrThrow25 = r22;
                columnIndexOrThrow2 = r0;
                columnIndexOrThrow12 = r17;
                columnIndexOrThrow15 = r7;
                columnIndexOrThrow16 = r02;
                columnIndexOrThrow18 = r72;
                columnIndexOrThrow23 = r04;
                columnIndexOrThrow9 = r29;
                columnIndexOrThrow11 = r30;
                columnIndexOrThrow = r32;
                columnIndexOrThrow24 = r14;
                columnIndexOrThrow22 = r73;
                columnIndexOrThrow3 = r31;
                columnIndexOrThrow20 = r03;
                columnIndexOrThrow4 = r2;
                columnIndexOrThrow19 = r8;
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

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getAllEligibleWorkSpecsForScheduling(final int maxLimit) {
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
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 ORDER BY period_start_time LIMIT ?", 1);
        acquire.bindLong(1, maxLimit);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "input");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "output");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "period_start_time");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
            int r28 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                String string = query.getString(columnIndexOrThrow9);
                int r29 = columnIndexOrThrow9;
                String string2 = query.getString(columnIndexOrThrow11);
                int r30 = columnIndexOrThrow11;
                Constraints constraints = new Constraints();
                int r32 = columnIndexOrThrow;
                constraints.setRequiredNetworkType(WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow)));
                constraints.setRequiresCharging(query.getInt(columnIndexOrThrow2) != 0);
                constraints.setRequiresDeviceIdle(query.getInt(columnIndexOrThrow3) != 0);
                constraints.setRequiresBatteryNotLow(query.getInt(columnIndexOrThrow4) != 0);
                constraints.setRequiresStorageNotLow(query.getInt(columnIndexOrThrow5) != 0);
                int r0 = columnIndexOrThrow2;
                int r31 = columnIndexOrThrow3;
                constraints.setTriggerContentUpdateDelay(query.getLong(columnIndexOrThrow6));
                constraints.setTriggerMaxContentDelay(query.getLong(columnIndexOrThrow7));
                constraints.setContentUriTriggers(WorkTypeConverters.byteArrayToContentUriTriggers(query.getBlob(columnIndexOrThrow8)));
                WorkSpec workSpec = new WorkSpec(string, string2);
                workSpec.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow10));
                workSpec.inputMergerClassName = query.getString(columnIndexOrThrow12);
                workSpec.input = Data.fromByteArray(query.getBlob(columnIndexOrThrow13));
                int r1 = r28;
                workSpec.output = Data.fromByteArray(query.getBlob(r1));
                r28 = r1;
                int r7 = columnIndexOrThrow15;
                workSpec.initialDelay = query.getLong(r7);
                int r17 = columnIndexOrThrow12;
                int r02 = columnIndexOrThrow16;
                workSpec.intervalDuration = query.getLong(r02);
                int r2 = columnIndexOrThrow4;
                int r12 = columnIndexOrThrow17;
                workSpec.flexDuration = query.getLong(r12);
                int r72 = columnIndexOrThrow18;
                workSpec.runAttemptCount = query.getInt(r72);
                int r8 = columnIndexOrThrow19;
                workSpec.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(r8));
                columnIndexOrThrow17 = r12;
                int r03 = columnIndexOrThrow20;
                workSpec.backoffDelayDuration = query.getLong(r03);
                int r13 = columnIndexOrThrow21;
                workSpec.periodStartTime = query.getLong(r13);
                columnIndexOrThrow21 = r13;
                int r73 = columnIndexOrThrow22;
                workSpec.minimumRetentionDuration = query.getLong(r73);
                int r04 = columnIndexOrThrow23;
                workSpec.scheduleRequestedAt = query.getLong(r04);
                int r14 = columnIndexOrThrow24;
                workSpec.expedited = query.getInt(r14) != 0;
                int r22 = columnIndexOrThrow25;
                workSpec.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(r22));
                workSpec.constraints = constraints;
                arrayList.add(workSpec);
                columnIndexOrThrow25 = r22;
                columnIndexOrThrow2 = r0;
                columnIndexOrThrow12 = r17;
                columnIndexOrThrow15 = r7;
                columnIndexOrThrow16 = r02;
                columnIndexOrThrow18 = r72;
                columnIndexOrThrow23 = r04;
                columnIndexOrThrow9 = r29;
                columnIndexOrThrow11 = r30;
                columnIndexOrThrow = r32;
                columnIndexOrThrow24 = r14;
                columnIndexOrThrow22 = r73;
                columnIndexOrThrow3 = r31;
                columnIndexOrThrow20 = r03;
                columnIndexOrThrow4 = r2;
                columnIndexOrThrow19 = r8;
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

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getScheduledWork() {
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
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 AND schedule_requested_at<>-1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "input");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "output");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "period_start_time");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
            int r28 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                String string = query.getString(columnIndexOrThrow9);
                int r29 = columnIndexOrThrow9;
                String string2 = query.getString(columnIndexOrThrow11);
                int r30 = columnIndexOrThrow11;
                Constraints constraints = new Constraints();
                int r32 = columnIndexOrThrow;
                constraints.setRequiredNetworkType(WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow)));
                constraints.setRequiresCharging(query.getInt(columnIndexOrThrow2) != 0);
                constraints.setRequiresDeviceIdle(query.getInt(columnIndexOrThrow3) != 0);
                constraints.setRequiresBatteryNotLow(query.getInt(columnIndexOrThrow4) != 0);
                constraints.setRequiresStorageNotLow(query.getInt(columnIndexOrThrow5) != 0);
                int r0 = columnIndexOrThrow2;
                int r33 = columnIndexOrThrow3;
                constraints.setTriggerContentUpdateDelay(query.getLong(columnIndexOrThrow6));
                constraints.setTriggerMaxContentDelay(query.getLong(columnIndexOrThrow7));
                constraints.setContentUriTriggers(WorkTypeConverters.byteArrayToContentUriTriggers(query.getBlob(columnIndexOrThrow8)));
                WorkSpec workSpec = new WorkSpec(string, string2);
                workSpec.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow10));
                workSpec.inputMergerClassName = query.getString(columnIndexOrThrow12);
                workSpec.input = Data.fromByteArray(query.getBlob(columnIndexOrThrow13));
                int r1 = r28;
                workSpec.output = Data.fromByteArray(query.getBlob(r1));
                r28 = r1;
                int r6 = columnIndexOrThrow15;
                workSpec.initialDelay = query.getLong(r6);
                int r17 = columnIndexOrThrow13;
                int r02 = columnIndexOrThrow16;
                workSpec.intervalDuration = query.getLong(r02);
                int r2 = columnIndexOrThrow4;
                int r12 = columnIndexOrThrow17;
                workSpec.flexDuration = query.getLong(r12);
                int r62 = columnIndexOrThrow18;
                workSpec.runAttemptCount = query.getInt(r62);
                int r7 = columnIndexOrThrow19;
                workSpec.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(r7));
                columnIndexOrThrow17 = r12;
                int r03 = columnIndexOrThrow20;
                workSpec.backoffDelayDuration = query.getLong(r03);
                int r13 = columnIndexOrThrow21;
                workSpec.periodStartTime = query.getLong(r13);
                columnIndexOrThrow21 = r13;
                int r63 = columnIndexOrThrow22;
                workSpec.minimumRetentionDuration = query.getLong(r63);
                int r04 = columnIndexOrThrow23;
                workSpec.scheduleRequestedAt = query.getLong(r04);
                int r14 = columnIndexOrThrow24;
                workSpec.expedited = query.getInt(r14) != 0;
                int r22 = columnIndexOrThrow25;
                workSpec.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(r22));
                workSpec.constraints = constraints;
                arrayList.add(workSpec);
                columnIndexOrThrow25 = r22;
                columnIndexOrThrow2 = r0;
                columnIndexOrThrow13 = r17;
                columnIndexOrThrow15 = r6;
                columnIndexOrThrow16 = r02;
                columnIndexOrThrow18 = r62;
                columnIndexOrThrow23 = r04;
                columnIndexOrThrow9 = r29;
                columnIndexOrThrow11 = r30;
                columnIndexOrThrow = r32;
                columnIndexOrThrow24 = r14;
                columnIndexOrThrow22 = r63;
                columnIndexOrThrow3 = r33;
                columnIndexOrThrow20 = r03;
                columnIndexOrThrow4 = r2;
                columnIndexOrThrow19 = r7;
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

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getRunningWork() {
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
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "input");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "output");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "period_start_time");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
            int r28 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                String string = query.getString(columnIndexOrThrow9);
                int r29 = columnIndexOrThrow9;
                String string2 = query.getString(columnIndexOrThrow11);
                int r30 = columnIndexOrThrow11;
                Constraints constraints = new Constraints();
                int r32 = columnIndexOrThrow;
                constraints.setRequiredNetworkType(WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow)));
                constraints.setRequiresCharging(query.getInt(columnIndexOrThrow2) != 0);
                constraints.setRequiresDeviceIdle(query.getInt(columnIndexOrThrow3) != 0);
                constraints.setRequiresBatteryNotLow(query.getInt(columnIndexOrThrow4) != 0);
                constraints.setRequiresStorageNotLow(query.getInt(columnIndexOrThrow5) != 0);
                int r0 = columnIndexOrThrow2;
                int r33 = columnIndexOrThrow3;
                constraints.setTriggerContentUpdateDelay(query.getLong(columnIndexOrThrow6));
                constraints.setTriggerMaxContentDelay(query.getLong(columnIndexOrThrow7));
                constraints.setContentUriTriggers(WorkTypeConverters.byteArrayToContentUriTriggers(query.getBlob(columnIndexOrThrow8)));
                WorkSpec workSpec = new WorkSpec(string, string2);
                workSpec.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow10));
                workSpec.inputMergerClassName = query.getString(columnIndexOrThrow12);
                workSpec.input = Data.fromByteArray(query.getBlob(columnIndexOrThrow13));
                int r1 = r28;
                workSpec.output = Data.fromByteArray(query.getBlob(r1));
                r28 = r1;
                int r6 = columnIndexOrThrow15;
                workSpec.initialDelay = query.getLong(r6);
                int r17 = columnIndexOrThrow13;
                int r02 = columnIndexOrThrow16;
                workSpec.intervalDuration = query.getLong(r02);
                int r2 = columnIndexOrThrow4;
                int r12 = columnIndexOrThrow17;
                workSpec.flexDuration = query.getLong(r12);
                int r62 = columnIndexOrThrow18;
                workSpec.runAttemptCount = query.getInt(r62);
                int r7 = columnIndexOrThrow19;
                workSpec.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(r7));
                columnIndexOrThrow17 = r12;
                int r03 = columnIndexOrThrow20;
                workSpec.backoffDelayDuration = query.getLong(r03);
                int r13 = columnIndexOrThrow21;
                workSpec.periodStartTime = query.getLong(r13);
                columnIndexOrThrow21 = r13;
                int r63 = columnIndexOrThrow22;
                workSpec.minimumRetentionDuration = query.getLong(r63);
                int r04 = columnIndexOrThrow23;
                workSpec.scheduleRequestedAt = query.getLong(r04);
                int r14 = columnIndexOrThrow24;
                workSpec.expedited = query.getInt(r14) != 0;
                int r22 = columnIndexOrThrow25;
                workSpec.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(r22));
                workSpec.constraints = constraints;
                arrayList.add(workSpec);
                columnIndexOrThrow25 = r22;
                columnIndexOrThrow2 = r0;
                columnIndexOrThrow13 = r17;
                columnIndexOrThrow15 = r6;
                columnIndexOrThrow16 = r02;
                columnIndexOrThrow18 = r62;
                columnIndexOrThrow23 = r04;
                columnIndexOrThrow9 = r29;
                columnIndexOrThrow11 = r30;
                columnIndexOrThrow = r32;
                columnIndexOrThrow24 = r14;
                columnIndexOrThrow22 = r63;
                columnIndexOrThrow3 = r33;
                columnIndexOrThrow20 = r03;
                columnIndexOrThrow4 = r2;
                columnIndexOrThrow19 = r7;
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

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getRecentlyCompletedWork(final long startingAt) {
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
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE period_start_time >= ? AND state IN (2, 3, 5) ORDER BY period_start_time DESC", 1);
        acquire.bindLong(1, startingAt);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "input");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "output");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "period_start_time");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
            int r27 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                String string = query.getString(columnIndexOrThrow9);
                int r28 = columnIndexOrThrow9;
                String string2 = query.getString(columnIndexOrThrow11);
                int r29 = columnIndexOrThrow11;
                Constraints constraints = new Constraints();
                int r31 = columnIndexOrThrow;
                constraints.setRequiredNetworkType(WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow)));
                constraints.setRequiresCharging(query.getInt(columnIndexOrThrow2) != 0);
                constraints.setRequiresDeviceIdle(query.getInt(columnIndexOrThrow3) != 0);
                constraints.setRequiresBatteryNotLow(query.getInt(columnIndexOrThrow4) != 0);
                constraints.setRequiresStorageNotLow(query.getInt(columnIndexOrThrow5) != 0);
                int r0 = columnIndexOrThrow2;
                int r30 = columnIndexOrThrow3;
                constraints.setTriggerContentUpdateDelay(query.getLong(columnIndexOrThrow6));
                constraints.setTriggerMaxContentDelay(query.getLong(columnIndexOrThrow7));
                constraints.setContentUriTriggers(WorkTypeConverters.byteArrayToContentUriTriggers(query.getBlob(columnIndexOrThrow8)));
                WorkSpec workSpec = new WorkSpec(string, string2);
                workSpec.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow10));
                workSpec.inputMergerClassName = query.getString(columnIndexOrThrow12);
                workSpec.input = Data.fromByteArray(query.getBlob(columnIndexOrThrow13));
                int r1 = r27;
                workSpec.output = Data.fromByteArray(query.getBlob(r1));
                int r7 = columnIndexOrThrow15;
                r27 = r1;
                workSpec.initialDelay = query.getLong(r7);
                int r13 = columnIndexOrThrow12;
                int r02 = columnIndexOrThrow16;
                workSpec.intervalDuration = query.getLong(r02);
                int r2 = columnIndexOrThrow4;
                int r12 = columnIndexOrThrow17;
                workSpec.flexDuration = query.getLong(r12);
                int r72 = columnIndexOrThrow18;
                workSpec.runAttemptCount = query.getInt(r72);
                int r8 = columnIndexOrThrow19;
                workSpec.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(r8));
                columnIndexOrThrow17 = r12;
                int r03 = columnIndexOrThrow20;
                workSpec.backoffDelayDuration = query.getLong(r03);
                int r14 = columnIndexOrThrow21;
                workSpec.periodStartTime = query.getLong(r14);
                columnIndexOrThrow21 = r14;
                int r73 = columnIndexOrThrow22;
                workSpec.minimumRetentionDuration = query.getLong(r73);
                int r04 = columnIndexOrThrow23;
                workSpec.scheduleRequestedAt = query.getLong(r04);
                int r15 = columnIndexOrThrow24;
                workSpec.expedited = query.getInt(r15) != 0;
                int r22 = columnIndexOrThrow25;
                workSpec.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(r22));
                workSpec.constraints = constraints;
                arrayList.add(workSpec);
                columnIndexOrThrow2 = r0;
                columnIndexOrThrow25 = r22;
                columnIndexOrThrow12 = r13;
                columnIndexOrThrow15 = r7;
                columnIndexOrThrow16 = r02;
                columnIndexOrThrow18 = r72;
                columnIndexOrThrow23 = r04;
                columnIndexOrThrow9 = r28;
                columnIndexOrThrow11 = r29;
                columnIndexOrThrow = r31;
                columnIndexOrThrow24 = r15;
                columnIndexOrThrow22 = r73;
                columnIndexOrThrow3 = r30;
                columnIndexOrThrow20 = r03;
                columnIndexOrThrow4 = r2;
                columnIndexOrThrow19 = r8;
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

    @Override // androidx.work.impl.model.WorkSpecDao
    public int setState(final WorkInfo.State state, final String... ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE workspec SET state=");
        newStringBuilder.append("?");
        newStringBuilder.append(" WHERE id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, ids.length);
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        compileStatement.bindLong(1, WorkTypeConverters.stateToInt(state));
        int r1 = 2;
        for (String str : ids) {
            if (str == null) {
                compileStatement.bindNull(r1);
            } else {
                compileStatement.bindString(r1, str);
            }
            r1++;
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(final ArrayMap<String, ArrayList<String>> _map) {
        ArrayList<String> arrayList;
        int r5;
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<String>> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int r4 = 0;
            loop0: while (true) {
                r5 = 0;
                while (r4 < size) {
                    arrayMap.put(_map.keyAt(r4), _map.valueAt(r4));
                    r4++;
                    r5++;
                    if (r5 == 999) {
                        break;
                    }
                }
                __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            }
            if (r5 > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int r2 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(r2);
            } else {
                acquire.bindString(r2, str);
            }
            r2++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "work_spec_id");
            if (columnIndex == -1) {
                return;
            }
            while (query.moveToNext()) {
                if (!query.isNull(columnIndex) && (arrayList = _map.get(query.getString(columnIndex))) != null) {
                    arrayList.add(query.getString(0));
                }
            }
        } finally {
            query.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(final ArrayMap<String, ArrayList<Data>> _map) {
        ArrayList<Data> arrayList;
        int r5;
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<Data>> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int r4 = 0;
            loop0: while (true) {
                r5 = 0;
                while (r4 < size) {
                    arrayMap.put(_map.keyAt(r4), _map.valueAt(r4));
                    r4++;
                    r5++;
                    if (r5 == 999) {
                        break;
                    }
                }
                __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap);
                arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            }
            if (r5 > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int r2 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(r2);
            } else {
                acquire.bindString(r2, str);
            }
            r2++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "work_spec_id");
            if (columnIndex == -1) {
                return;
            }
            while (query.moveToNext()) {
                if (!query.isNull(columnIndex) && (arrayList = _map.get(query.getString(columnIndex))) != null) {
                    arrayList.add(Data.fromByteArray(query.getBlob(0)));
                }
            }
        } finally {
            query.close();
        }
    }
}
