package com.onesignal.outcomes.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.onesignal.influence.domain.OSInfluenceChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OSOutcomeTableProvider.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, m183d2 = {"Lcom/onesignal/outcomes/data/OSOutcomeTableProvider;", "", "()V", "upgradeCacheOutcomeTableRevision1To2", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "upgradeOutcomeTableRevision1To2", "upgradeOutcomeTableRevision2To3", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* loaded from: classes3.dex */
public final class OSOutcomeTableProvider {
    public final void upgradeOutcomeTableRevision1To2(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        try {
            try {
                db.execSQL("BEGIN TRANSACTION;");
                db.execSQL("CREATE TEMPORARY TABLE outcome_backup(_id,session,notification_ids,name,timestamp);");
                db.execSQL("INSERT INTO outcome_backup SELECT _id,session,notification_ids,name,timestamp FROM outcome;");
                db.execSQL("DROP TABLE outcome;");
                db.execSQL(OutcomesDbContract.SQL_CREATE_OUTCOME_ENTRIES_V2);
                db.execSQL("INSERT INTO outcome (_id,session,notification_ids,name,timestamp, weight) SELECT _id,session,notification_ids,name,timestamp, 0 FROM outcome_backup;");
                db.execSQL("DROP TABLE outcome_backup;");
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } finally {
            db.execSQL("COMMIT;");
        }
    }

    public final void upgradeOutcomeTableRevision2To3(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        try {
            try {
                db.execSQL("BEGIN TRANSACTION;");
                db.execSQL("ALTER TABLE outcome RENAME TO outcome_aux;");
                db.execSQL(OutcomesDbContract.SQL_CREATE_OUTCOME_ENTRIES_V3);
                db.execSQL("INSERT INTO outcome(_id,name,timestamp,notification_ids,weight,notification_influence_type) SELECT _id,name,timestamp,notification_ids,weight,session FROM outcome_aux;");
                StringBuilder sb = new StringBuilder();
                sb.append("DROP TABLE ");
                sb.append("outcome_aux");
                sb.append(';');
                db.execSQL(sb.toString());
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } finally {
            db.execSQL("COMMIT;");
        }
    }

    public final void upgradeCacheOutcomeTableRevision1To2(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        try {
            try {
                db.execSQL("BEGIN TRANSACTION;");
                db.execSQL(OutcomesDbContract.SQL_CREATE_UNIQUE_OUTCOME_ENTRIES_V2);
                db.execSQL("INSERT INTO cached_unique_outcome(_id,name,channel_influence_id) SELECT _id,name,notification_id FROM " + OSOutcomesDbContract.TABLE_NAME_V1 + ";");
                StringBuilder sb = new StringBuilder();
                sb.append("UPDATE cached_unique_outcome SET channel_type = '");
                sb.append(OSInfluenceChannel.NOTIFICATION.toString());
                sb.append("';");
                db.execSQL(sb.toString());
                db.execSQL("DROP TABLE " + OSOutcomesDbContract.TABLE_NAME_V1 + ';');
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } finally {
            db.execSQL("COMMIT;");
        }
    }
}
