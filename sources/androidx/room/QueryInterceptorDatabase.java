package androidx.room;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.room.RoomDatabase;
import androidx.sqlite.p007db.SupportSQLiteDatabase;
import androidx.sqlite.p007db.SupportSQLiteQuery;
import androidx.sqlite.p007db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class QueryInterceptorDatabase implements SupportSQLiteDatabase {
    private final SupportSQLiteDatabase mDelegate;
    private final RoomDatabase.QueryCallback mQueryCallback;
    private final Executor mQueryCallbackExecutor;

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public /* synthetic */ void execPerConnectionSQL(String str, Object[] objArr) {
        SupportSQLiteDatabase.CC.$default$execPerConnectionSQL(this, str, objArr);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public /* synthetic */ boolean isExecPerConnectionSQLSupported() {
        return SupportSQLiteDatabase.CC.$default$isExecPerConnectionSQLSupported(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QueryInterceptorDatabase(SupportSQLiteDatabase supportSQLiteDatabase, RoomDatabase.QueryCallback queryCallback, Executor executor) {
        this.mDelegate = supportSQLiteDatabase;
        this.mQueryCallback = queryCallback;
        this.mQueryCallbackExecutor = executor;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public SupportSQLiteStatement compileStatement(String str) {
        return new QueryInterceptorStatement(this.mDelegate.compileStatement(str), this.mQueryCallback, str, this.mQueryCallbackExecutor);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void beginTransaction() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1450lambda$beginTransaction$0$androidxroomQueryInterceptorDatabase();
            }
        });
        this.mDelegate.beginTransaction();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$beginTransaction$0$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m1450lambda$beginTransaction$0$androidxroomQueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", Collections.emptyList());
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void beginTransactionNonExclusive() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1392x9d2bf0fa();
            }
        });
        this.mDelegate.beginTransactionNonExclusive();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$beginTransactionNonExclusive$1$androidx-room-QueryInterceptorDatabase */
    public /* synthetic */ void m1392x9d2bf0fa() {
        this.mQueryCallback.onQuery("BEGIN DEFERRED TRANSACTION", Collections.emptyList());
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1391xe4527eb0();
            }
        });
        this.mDelegate.beginTransactionWithListener(sQLiteTransactionListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$beginTransactionWithListener$2$androidx-room-QueryInterceptorDatabase */
    public /* synthetic */ void m1391xe4527eb0() {
        this.mQueryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", Collections.emptyList());
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1390xa78c463e();
            }
        });
        this.mDelegate.beginTransactionWithListenerNonExclusive(sQLiteTransactionListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$beginTransactionWithListenerNonExclusive$3$androidx-room-QueryInterceptorDatabase */
    public /* synthetic */ void m1390xa78c463e() {
        this.mQueryCallback.onQuery("BEGIN DEFERRED TRANSACTION", Collections.emptyList());
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void endTransaction() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1451lambda$endTransaction$4$androidxroomQueryInterceptorDatabase();
            }
        });
        this.mDelegate.endTransaction();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$endTransaction$4$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m1451lambda$endTransaction$4$androidxroomQueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("END TRANSACTION", Collections.emptyList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setTransactionSuccessful$5$androidx-room-QueryInterceptorDatabase */
    public /* synthetic */ void m1389x7d646086() {
        this.mQueryCallback.onQuery("TRANSACTION SUCCESSFUL", Collections.emptyList());
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void setTransactionSuccessful() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1389x7d646086();
            }
        });
        this.mDelegate.setTransactionSuccessful();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public boolean inTransaction() {
        return this.mDelegate.inTransaction();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public boolean isDbLockedByCurrentThread() {
        return this.mDelegate.isDbLockedByCurrentThread();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public boolean yieldIfContendedSafely() {
        return this.mDelegate.yieldIfContendedSafely();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public boolean yieldIfContendedSafely(long j) {
        return this.mDelegate.yieldIfContendedSafely(j);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public int getVersion() {
        return this.mDelegate.getVersion();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void setVersion(int r2) {
        this.mDelegate.setVersion(r2);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public long getMaximumSize() {
        return this.mDelegate.getMaximumSize();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public long setMaximumSize(long j) {
        return this.mDelegate.setMaximumSize(j);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public long getPageSize() {
        return this.mDelegate.getPageSize();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void setPageSize(long j) {
        this.mDelegate.setPageSize(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$query$6$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m1454lambda$query$6$androidxroomQueryInterceptorDatabase(String str) {
        this.mQueryCallback.onQuery(str, Collections.emptyList());
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public Cursor query(final String str) {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1454lambda$query$6$androidxroomQueryInterceptorDatabase(str);
            }
        });
        return this.mDelegate.query(str);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public Cursor query(final String str, Object[] objArr) {
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(objArr));
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1455lambda$query$7$androidxroomQueryInterceptorDatabase(str, arrayList);
            }
        });
        return this.mDelegate.query(str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$query$7$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m1455lambda$query$7$androidxroomQueryInterceptorDatabase(String str, List list) {
        this.mQueryCallback.onQuery(str, list);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public Cursor query(final SupportSQLiteQuery supportSQLiteQuery) {
        final QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        supportSQLiteQuery.bindTo(queryInterceptorProgram);
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1456lambda$query$8$androidxroomQueryInterceptorDatabase(supportSQLiteQuery, queryInterceptorProgram);
            }
        });
        return this.mDelegate.query(supportSQLiteQuery);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$query$8$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m1456lambda$query$8$androidxroomQueryInterceptorDatabase(SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.mQueryCallback.onQuery(supportSQLiteQuery.getSql(), queryInterceptorProgram.getBindArgs());
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public Cursor query(final SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        final QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        supportSQLiteQuery.bindTo(queryInterceptorProgram);
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1457lambda$query$9$androidxroomQueryInterceptorDatabase(supportSQLiteQuery, queryInterceptorProgram);
            }
        });
        return this.mDelegate.query(supportSQLiteQuery);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$query$9$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m1457lambda$query$9$androidxroomQueryInterceptorDatabase(SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.mQueryCallback.onQuery(supportSQLiteQuery.getSql(), queryInterceptorProgram.getBindArgs());
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public long insert(String str, int r3, ContentValues contentValues) throws SQLException {
        return this.mDelegate.insert(str, r3, contentValues);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public int delete(String str, String str2, Object[] objArr) {
        return this.mDelegate.delete(str, str2, objArr);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public int update(String str, int r8, ContentValues contentValues, String str2, Object[] objArr) {
        return this.mDelegate.update(str, r8, contentValues, str2, objArr);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void execSQL(final String str) throws SQLException {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1452lambda$execSQL$10$androidxroomQueryInterceptorDatabase(str);
            }
        });
        this.mDelegate.execSQL(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$execSQL$10$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m1452lambda$execSQL$10$androidxroomQueryInterceptorDatabase(String str) {
        this.mQueryCallback.onQuery(str, new ArrayList(0));
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void execSQL(final String str, Object[] objArr) throws SQLException {
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(objArr));
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorDatabase$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.m1453lambda$execSQL$11$androidxroomQueryInterceptorDatabase(str, arrayList);
            }
        });
        this.mDelegate.execSQL(str, arrayList.toArray());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$execSQL$11$androidx-room-QueryInterceptorDatabase  reason: not valid java name */
    public /* synthetic */ void m1453lambda$execSQL$11$androidxroomQueryInterceptorDatabase(String str, List list) {
        this.mQueryCallback.onQuery(str, list);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public boolean isReadOnly() {
        return this.mDelegate.isReadOnly();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public boolean isOpen() {
        return this.mDelegate.isOpen();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public boolean needUpgrade(int r2) {
        return this.mDelegate.needUpgrade(r2);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public String getPath() {
        return this.mDelegate.getPath();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void setLocale(Locale locale) {
        this.mDelegate.setLocale(locale);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void setMaxSqlCacheSize(int r2) {
        this.mDelegate.setMaxSqlCacheSize(r2);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void setForeignKeyConstraintsEnabled(boolean z) {
        this.mDelegate.setForeignKeyConstraintsEnabled(z);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public boolean enableWriteAheadLogging() {
        return this.mDelegate.enableWriteAheadLogging();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public void disableWriteAheadLogging() {
        this.mDelegate.disableWriteAheadLogging();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public boolean isWriteAheadLoggingEnabled() {
        return this.mDelegate.isWriteAheadLoggingEnabled();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public List<Pair<String, String>> getAttachedDbs() {
        return this.mDelegate.getAttachedDbs();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
    public boolean isDatabaseIntegrityOk() {
        return this.mDelegate.isDatabaseIntegrityOk();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mDelegate.close();
    }
}
