package androidx.room.migration;

import androidx.sqlite.p007db.SupportSQLiteDatabase;

/* loaded from: classes.dex */
public interface AutoMigrationSpec {

    /* renamed from: androidx.room.migration.AutoMigrationSpec$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static void $default$onPostMigrate(AutoMigrationSpec _this, SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }

    void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase);
}
