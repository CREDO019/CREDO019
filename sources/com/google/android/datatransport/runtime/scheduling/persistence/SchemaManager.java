package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class SchemaManager extends SQLiteOpenHelper {
    private static final String CREATE_CONTEXTS_SQL_V1 = "CREATE TABLE transport_contexts (_id INTEGER PRIMARY KEY, backend_name TEXT NOT NULL, priority INTEGER NOT NULL, next_request_ms INTEGER NOT NULL)";
    private static final String CREATE_CONTEXT_BACKEND_PRIORITY_INDEX_V1 = "CREATE UNIQUE INDEX contexts_backend_priority on transport_contexts(backend_name, priority)";
    private static final String CREATE_EVENTS_SQL_V1 = "CREATE TABLE events (_id INTEGER PRIMARY KEY, context_id INTEGER NOT NULL, transport_name TEXT NOT NULL, timestamp_ms INTEGER NOT NULL, uptime_ms INTEGER NOT NULL, payload BLOB NOT NULL, code INTEGER, num_attempts INTEGER NOT NULL,FOREIGN KEY (context_id) REFERENCES transport_contexts(_id) ON DELETE CASCADE)";
    private static final String CREATE_EVENT_BACKEND_INDEX_V1 = "CREATE INDEX events_backend_id on events(context_id)";
    private static final String CREATE_EVENT_METADATA_SQL_V1 = "CREATE TABLE event_metadata (_id INTEGER PRIMARY KEY, event_id INTEGER NOT NULL, name TEXT NOT NULL, value TEXT NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE)";
    private static final String CREATE_PAYLOADS_TABLE_V4 = "CREATE TABLE event_payloads (sequence_num INTEGER NOT NULL, event_id INTEGER NOT NULL, bytes BLOB NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE,PRIMARY KEY (sequence_num, event_id))";
    static final String DB_NAME = "com.google.android.datatransport.events";
    private static final String DROP_CONTEXTS_SQL = "DROP TABLE transport_contexts";
    private static final String DROP_EVENTS_SQL = "DROP TABLE events";
    private static final String DROP_EVENT_METADATA_SQL = "DROP TABLE event_metadata";
    private static final String DROP_PAYLOADS_SQL = "DROP TABLE IF EXISTS event_payloads";
    private static final List<Migration> INCREMENTAL_MIGRATIONS;
    private static final Migration MIGRATE_TO_V1;
    private static final Migration MIGRATE_TO_V2;
    private static final Migration MIGRATE_TO_V3;
    private static final Migration MIGRATE_TO_V4;
    static int SCHEMA_VERSION = 4;
    private boolean configured;
    private final int schemaVersion;

    /* loaded from: classes2.dex */
    public interface Migration {
        void upgrade(SQLiteDatabase sQLiteDatabase);
    }

    static {
        SchemaManager$$ExternalSyntheticLambda0 schemaManager$$ExternalSyntheticLambda0 = new Migration() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager$$ExternalSyntheticLambda0
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager.Migration
            public final void upgrade(SQLiteDatabase sQLiteDatabase) {
                SchemaManager.lambda$static$0(sQLiteDatabase);
            }
        };
        MIGRATE_TO_V1 = schemaManager$$ExternalSyntheticLambda0;
        SchemaManager$$ExternalSyntheticLambda1 schemaManager$$ExternalSyntheticLambda1 = new Migration() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager$$ExternalSyntheticLambda1
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager.Migration
            public final void upgrade(SQLiteDatabase sQLiteDatabase) {
                SchemaManager.lambda$static$1(sQLiteDatabase);
            }
        };
        MIGRATE_TO_V2 = schemaManager$$ExternalSyntheticLambda1;
        SchemaManager$$ExternalSyntheticLambda2 schemaManager$$ExternalSyntheticLambda2 = new Migration() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager$$ExternalSyntheticLambda2
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager.Migration
            public final void upgrade(SQLiteDatabase sQLiteDatabase) {
                sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN payload_encoding TEXT");
            }
        };
        MIGRATE_TO_V3 = schemaManager$$ExternalSyntheticLambda2;
        SchemaManager$$ExternalSyntheticLambda3 schemaManager$$ExternalSyntheticLambda3 = new Migration() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager$$ExternalSyntheticLambda3
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager.Migration
            public final void upgrade(SQLiteDatabase sQLiteDatabase) {
                SchemaManager.lambda$static$3(sQLiteDatabase);
            }
        };
        MIGRATE_TO_V4 = schemaManager$$ExternalSyntheticLambda3;
        INCREMENTAL_MIGRATIONS = Arrays.asList(schemaManager$$ExternalSyntheticLambda0, schemaManager$$ExternalSyntheticLambda1, schemaManager$$ExternalSyntheticLambda2, schemaManager$$ExternalSyntheticLambda3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$0(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_EVENTS_SQL_V1);
        sQLiteDatabase.execSQL(CREATE_EVENT_METADATA_SQL_V1);
        sQLiteDatabase.execSQL(CREATE_CONTEXTS_SQL_V1);
        sQLiteDatabase.execSQL(CREATE_EVENT_BACKEND_INDEX_V1);
        sQLiteDatabase.execSQL(CREATE_CONTEXT_BACKEND_PRIORITY_INDEX_V1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$1(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE transport_contexts ADD COLUMN extras BLOB");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX contexts_backend_priority_extras on transport_contexts(backend_name, priority, extras)");
        sQLiteDatabase.execSQL("DROP INDEX contexts_backend_priority");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$3(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN inline BOOLEAN NOT NULL DEFAULT 1");
        sQLiteDatabase.execSQL(DROP_PAYLOADS_SQL);
        sQLiteDatabase.execSQL(CREATE_PAYLOADS_TABLE_V4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public SchemaManager(Context context, @Named("SQLITE_DB_NAME") String str, @Named("SCHEMA_VERSION") int r4) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, r4);
        this.configured = false;
        this.schemaVersion = r4;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        this.configured = true;
        sQLiteDatabase.rawQuery("PRAGMA busy_timeout=0;", new String[0]).close();
        if (Build.VERSION.SDK_INT >= 16) {
            sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
        }
    }

    private void ensureConfigured(SQLiteDatabase sQLiteDatabase) {
        if (this.configured) {
            return;
        }
        onConfigure(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        onCreate(sQLiteDatabase, this.schemaVersion);
    }

    private void onCreate(SQLiteDatabase sQLiteDatabase, int r3) {
        ensureConfigured(sQLiteDatabase);
        upgrade(sQLiteDatabase, 0, r3);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int r2, int r3) {
        ensureConfigured(sQLiteDatabase);
        upgrade(sQLiteDatabase, r2, r3);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int r2, int r3) {
        sQLiteDatabase.execSQL(DROP_EVENTS_SQL);
        sQLiteDatabase.execSQL(DROP_EVENT_METADATA_SQL);
        sQLiteDatabase.execSQL(DROP_CONTEXTS_SQL);
        sQLiteDatabase.execSQL(DROP_PAYLOADS_SQL);
        onCreate(sQLiteDatabase, r3);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        ensureConfigured(sQLiteDatabase);
    }

    private void upgrade(SQLiteDatabase sQLiteDatabase, int r5, int r6) {
        List<Migration> list = INCREMENTAL_MIGRATIONS;
        if (r6 <= list.size()) {
            while (r5 < r6) {
                INCREMENTAL_MIGRATIONS.get(r5).upgrade(sQLiteDatabase);
                r5++;
            }
            return;
        }
        throw new IllegalArgumentException("Migration from " + r5 + " to " + r6 + " was requested, but cannot be performed. Only " + list.size() + " migrations are provided");
    }
}