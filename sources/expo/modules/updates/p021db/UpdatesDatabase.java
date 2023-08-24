package expo.modules.updates.p021db;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.p007db.SupportSQLiteDatabase;
import expo.modules.updates.p021db.dao.AssetDao;
import expo.modules.updates.p021db.dao.JSONDataDao;
import expo.modules.updates.p021db.dao.UpdateDao;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesDatabase.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001a\u00020\bH&¨\u0006\n"}, m183d2 = {"Lexpo/modules/updates/db/UpdatesDatabase;", "Landroidx/room/RoomDatabase;", "()V", "assetDao", "Lexpo/modules/updates/db/dao/AssetDao;", "jsonDataDao", "Lexpo/modules/updates/db/dao/JSONDataDao;", "updateDao", "Lexpo/modules/updates/db/dao/UpdateDao;", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.updates.db.UpdatesDatabase */
/* loaded from: classes5.dex */
public abstract class UpdatesDatabase extends RoomDatabase {
    private static final String DB_NAME = "updates.db";
    private static UpdatesDatabase instance;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "UpdatesDatabase";
    private static final Migration MIGRATION_4_5 = new Migration() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_4_5$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            UpdatesDatabase.Companion.runInTransactionWithForeignKeysOff(database, new Function1<SupportSQLiteDatabase, Unit>() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_4_5$1$migrate$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
                    invoke2(supportSQLiteDatabase);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(SupportSQLiteDatabase runInTransactionWithForeignKeysOff) {
                    Intrinsics.checkNotNullParameter(runInTransactionWithForeignKeysOff, "$this$runInTransactionWithForeignKeysOff");
                    runInTransactionWithForeignKeysOff.execSQL("CREATE TABLE `new_assets` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `url` TEXT, `key` TEXT, `headers` TEXT, `type` TEXT NOT NULL, `metadata` TEXT, `download_time` INTEGER, `relative_path` TEXT, `hash` BLOB, `hash_type` INTEGER NOT NULL, `marked_for_deletion` INTEGER NOT NULL)");
                    runInTransactionWithForeignKeysOff.execSQL("INSERT INTO `new_assets` (`id`, `url`, `key`, `headers`, `type`, `metadata`, `download_time`, `relative_path`, `hash`, `hash_type`, `marked_for_deletion`) SELECT `id`, `url`, `key`, `headers`, `type`, `metadata`, `download_time`, `relative_path`, `hash`, `hash_type`, `marked_for_deletion` FROM `assets`");
                    runInTransactionWithForeignKeysOff.execSQL("DROP TABLE `assets`");
                    runInTransactionWithForeignKeysOff.execSQL("ALTER TABLE `new_assets` RENAME TO `assets`");
                    runInTransactionWithForeignKeysOff.execSQL("CREATE UNIQUE INDEX `index_assets_key` ON `assets` (`key`)");
                }
            });
        }
    };
    private static final Migration MIGRATION_5_6 = new Migration() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_5_6$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            UpdatesDatabase.Companion.runInTransactionWithForeignKeysOff(database, new Function1<SupportSQLiteDatabase, Unit>() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_5_6$1$migrate$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
                    invoke2(supportSQLiteDatabase);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(SupportSQLiteDatabase runInTransactionWithForeignKeysOff) {
                    Intrinsics.checkNotNullParameter(runInTransactionWithForeignKeysOff, "$this$runInTransactionWithForeignKeysOff");
                    runInTransactionWithForeignKeysOff.execSQL("CREATE TABLE `new_updates` (`id` BLOB NOT NULL, `scope_key` TEXT NOT NULL, `commit_time` INTEGER NOT NULL, `runtime_version` TEXT NOT NULL, `launch_asset_id` INTEGER, `manifest` TEXT, `status` INTEGER NOT NULL, `keep` INTEGER NOT NULL, `last_accessed` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`launch_asset_id`) REFERENCES `assets`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                    runInTransactionWithForeignKeysOff.execSQL("INSERT INTO `new_updates` (`id`, `scope_key`, `commit_time`, `runtime_version`, `launch_asset_id`, `manifest`, `status`, `keep`, `last_accessed`) SELECT `id`, `scope_key`, `commit_time`, `runtime_version`, `launch_asset_id`, `metadata` AS `manifest`, `status`, `keep`, ?1 AS `last_accessed` FROM `updates`", new Object[]{Long.valueOf(new Date().getTime())});
                    runInTransactionWithForeignKeysOff.execSQL("DROP TABLE `updates`");
                    runInTransactionWithForeignKeysOff.execSQL("ALTER TABLE `new_updates` RENAME TO `updates`");
                    runInTransactionWithForeignKeysOff.execSQL("CREATE INDEX `index_updates_launch_asset_id` ON `updates` (`launch_asset_id`)");
                    runInTransactionWithForeignKeysOff.execSQL("CREATE UNIQUE INDEX `index_updates_scope_key_commit_time` ON `updates` (`scope_key`, `commit_time`)");
                }
            });
        }
    };
    private static final Migration MIGRATION_6_7 = new Migration() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_6_7$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            UpdatesDatabase.Companion.runInTransactionWithForeignKeysOff(database, new Function1<SupportSQLiteDatabase, Unit>() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_6_7$1$migrate$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
                    invoke2(supportSQLiteDatabase);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(SupportSQLiteDatabase runInTransactionWithForeignKeysOff) {
                    Intrinsics.checkNotNullParameter(runInTransactionWithForeignKeysOff, "$this$runInTransactionWithForeignKeysOff");
                    runInTransactionWithForeignKeysOff.execSQL("CREATE TABLE IF NOT EXISTS `new_assets` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `url` TEXT, `key` TEXT, `headers` TEXT, `type` TEXT, `metadata` TEXT, `download_time` INTEGER, `relative_path` TEXT, `hash` BLOB, `hash_type` INTEGER NOT NULL, `marked_for_deletion` INTEGER NOT NULL)");
                    runInTransactionWithForeignKeysOff.execSQL("INSERT INTO `new_assets` (`id`, `url`, `key`, `headers`, `type`, `metadata`, `download_time`, `relative_path`, `hash`, `hash_type`, `marked_for_deletion`) SELECT `id`, `url`, `key`, `headers`, `type`, `metadata`, `download_time`, `relative_path`, `hash`, `hash_type`, `marked_for_deletion` FROM `assets`");
                    runInTransactionWithForeignKeysOff.execSQL("DROP TABLE `assets`");
                    runInTransactionWithForeignKeysOff.execSQL("ALTER TABLE `new_assets` RENAME TO `assets`");
                    runInTransactionWithForeignKeysOff.execSQL("CREATE UNIQUE INDEX `index_assets_key` ON `assets` (`key`)");
                }
            });
        }
    };
    private static final Migration MIGRATION_7_8 = new Migration() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_7_8$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            UpdatesDatabase.Companion.runInTransactionWithForeignKeysOff(database, new Function1<SupportSQLiteDatabase, Unit>() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_7_8$1$migrate$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
                    invoke2(supportSQLiteDatabase);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(SupportSQLiteDatabase runInTransactionWithForeignKeysOff) {
                    Intrinsics.checkNotNullParameter(runInTransactionWithForeignKeysOff, "$this$runInTransactionWithForeignKeysOff");
                    runInTransactionWithForeignKeysOff.execSQL("CREATE TABLE `new_updates` (`id` BLOB NOT NULL, `scope_key` TEXT NOT NULL, `commit_time` INTEGER NOT NULL, `runtime_version` TEXT NOT NULL, `launch_asset_id` INTEGER, `manifest` TEXT, `status` INTEGER NOT NULL, `keep` INTEGER NOT NULL, `last_accessed` INTEGER NOT NULL, `successful_launch_count` INTEGER NOT NULL DEFAULT 0, `failed_launch_count` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`id`), FOREIGN KEY(`launch_asset_id`) REFERENCES `assets`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                    runInTransactionWithForeignKeysOff.execSQL("INSERT INTO `new_updates` (`id`, `scope_key`, `commit_time`, `runtime_version`, `launch_asset_id`, `manifest`, `status`, `keep`, `last_accessed`, `successful_launch_count`, `failed_launch_count`) SELECT `id`, `scope_key`, `commit_time`, `runtime_version`, `launch_asset_id`, `manifest`, `status`, `keep`, `last_accessed`, 1 AS `successful_launch_count`, 0 AS `failed_launch_count` FROM `updates`");
                    runInTransactionWithForeignKeysOff.execSQL("DROP TABLE `updates`");
                    runInTransactionWithForeignKeysOff.execSQL("ALTER TABLE `new_updates` RENAME TO `updates`");
                    runInTransactionWithForeignKeysOff.execSQL("CREATE INDEX `index_updates_launch_asset_id` ON `updates` (`launch_asset_id`)");
                    runInTransactionWithForeignKeysOff.execSQL("CREATE UNIQUE INDEX `index_updates_scope_key_commit_time` ON `updates` (`scope_key`, `commit_time`)");
                }
            });
        }
    };
    private static final Migration MIGRATION_8_9 = new Migration() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_8_9$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            UpdatesDatabase.Companion.runInTransactionWithForeignKeysOff(database, new Function1<SupportSQLiteDatabase, Unit>() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_8_9$1$migrate$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
                    invoke2(supportSQLiteDatabase);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(SupportSQLiteDatabase runInTransactionWithForeignKeysOff) {
                    Intrinsics.checkNotNullParameter(runInTransactionWithForeignKeysOff, "$this$runInTransactionWithForeignKeysOff");
                    runInTransactionWithForeignKeysOff.execSQL("ALTER TABLE `assets` ADD COLUMN `extra_request_headers` TEXT");
                }
            });
        }
    };
    private static final Migration MIGRATION_9_10 = new Migration() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_9_10$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            UpdatesDatabase.Companion.runInTransactionWithForeignKeysOff(database, new Function1<SupportSQLiteDatabase, Unit>() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_9_10$1$migrate$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
                    invoke2(supportSQLiteDatabase);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(SupportSQLiteDatabase runInTransactionWithForeignKeysOff) {
                    Intrinsics.checkNotNullParameter(runInTransactionWithForeignKeysOff, "$this$runInTransactionWithForeignKeysOff");
                    runInTransactionWithForeignKeysOff.execSQL("ALTER TABLE `assets` ADD COLUMN `expected_hash` TEXT");
                }
            });
        }
    };
    private static final Migration MIGRATION_10_11 = new Migration() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_10_11$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            UpdatesDatabase.Companion.runInTransaction(database, new Function1<SupportSQLiteDatabase, Unit>() { // from class: expo.modules.updates.db.UpdatesDatabase$Companion$MIGRATION_10_11$1$migrate$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
                    invoke2(supportSQLiteDatabase);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(SupportSQLiteDatabase runInTransaction) {
                    Intrinsics.checkNotNullParameter(runInTransaction, "$this$runInTransaction");
                    runInTransaction.execSQL("UPDATE `assets` SET `expected_hash` = NULL");
                }
            });
        }
    };

    @JvmStatic
    public static final synchronized UpdatesDatabase getInstance(Context context) {
        UpdatesDatabase companion;
        synchronized (UpdatesDatabase.class) {
            companion = Companion.getInstance(context);
        }
        return companion;
    }

    public abstract AssetDao assetDao();

    public abstract JSONDataDao jsonDataDao();

    public abstract UpdateDao updateDao();

    /* compiled from: UpdatesDatabase.kt */
    @Metadata(m184d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0007J%\u0010\u001c\u001a\u00020\u001d*\u00020\u001e2\u0017\u0010\u001f\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001d0 ¢\u0006\u0002\b!H\u0002J%\u0010\"\u001a\u00020\u001d*\u00020\u001e2\u0017\u0010\u001f\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001d0 ¢\u0006\u0002\b!H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0016\u0010\u0015\u001a\n \u0016*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, m183d2 = {"Lexpo/modules/updates/db/UpdatesDatabase$Companion;", "", "()V", "DB_NAME", "", "MIGRATION_10_11", "Landroidx/room/migration/Migration;", "getMIGRATION_10_11", "()Landroidx/room/migration/Migration;", "MIGRATION_4_5", "getMIGRATION_4_5", "MIGRATION_5_6", "getMIGRATION_5_6", "MIGRATION_6_7", "getMIGRATION_6_7", "MIGRATION_7_8", "getMIGRATION_7_8", "MIGRATION_8_9", "getMIGRATION_8_9", "MIGRATION_9_10", "getMIGRATION_9_10", "TAG", "kotlin.jvm.PlatformType", "instance", "Lexpo/modules/updates/db/UpdatesDatabase;", "getInstance", "context", "Landroid/content/Context;", "runInTransaction", "", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "runInTransactionWithForeignKeysOff", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.updates.db.UpdatesDatabase$Companion */
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final synchronized UpdatesDatabase getInstance(Context context) {
            UpdatesDatabase updatesDatabase;
            if (UpdatesDatabase.instance == null) {
                Intrinsics.checkNotNull(context);
                UpdatesDatabase.instance = (UpdatesDatabase) Room.databaseBuilder(context, UpdatesDatabase.class, UpdatesDatabase.DB_NAME).addMigrations(getMIGRATION_4_5()).addMigrations(getMIGRATION_5_6()).addMigrations(getMIGRATION_6_7()).addMigrations(getMIGRATION_7_8()).addMigrations(getMIGRATION_8_9()).addMigrations(getMIGRATION_9_10()).addMigrations(getMIGRATION_10_11()).fallbackToDestructiveMigration().allowMainThreadQueries().build();
            }
            updatesDatabase = UpdatesDatabase.instance;
            Intrinsics.checkNotNull(updatesDatabase);
            return updatesDatabase;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void runInTransaction(SupportSQLiteDatabase supportSQLiteDatabase, Function1<? super SupportSQLiteDatabase, Unit> function1) {
            supportSQLiteDatabase.beginTransaction();
            try {
                function1.invoke(supportSQLiteDatabase);
                supportSQLiteDatabase.setTransactionSuccessful();
            } finally {
                supportSQLiteDatabase.endTransaction();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void runInTransactionWithForeignKeysOff(SupportSQLiteDatabase supportSQLiteDatabase, Function1<? super SupportSQLiteDatabase, Unit> function1) {
            try {
                supportSQLiteDatabase.execSQL("PRAGMA foreign_keys=OFF");
                supportSQLiteDatabase.beginTransaction();
                function1.invoke(supportSQLiteDatabase);
                supportSQLiteDatabase.setTransactionSuccessful();
                supportSQLiteDatabase.endTransaction();
            } finally {
                supportSQLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
            }
        }

        public final Migration getMIGRATION_4_5() {
            return UpdatesDatabase.MIGRATION_4_5;
        }

        public final Migration getMIGRATION_5_6() {
            return UpdatesDatabase.MIGRATION_5_6;
        }

        public final Migration getMIGRATION_6_7() {
            return UpdatesDatabase.MIGRATION_6_7;
        }

        public final Migration getMIGRATION_7_8() {
            return UpdatesDatabase.MIGRATION_7_8;
        }

        public final Migration getMIGRATION_8_9() {
            return UpdatesDatabase.MIGRATION_8_9;
        }

        public final Migration getMIGRATION_9_10() {
            return UpdatesDatabase.MIGRATION_9_10;
        }

        public final Migration getMIGRATION_10_11() {
            return UpdatesDatabase.MIGRATION_10_11;
        }
    }
}
