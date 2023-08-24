package androidx.room;

import androidx.room.RoomDatabase;
import androidx.sqlite.p007db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class QueryInterceptorStatement implements SupportSQLiteStatement {
    private final List<Object> mBindArgsCache = new ArrayList();
    private final SupportSQLiteStatement mDelegate;
    private final RoomDatabase.QueryCallback mQueryCallback;
    private final Executor mQueryCallbackExecutor;
    private final String mSqlStatement;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QueryInterceptorStatement(SupportSQLiteStatement supportSQLiteStatement, RoomDatabase.QueryCallback queryCallback, String str, Executor executor) {
        this.mDelegate = supportSQLiteStatement;
        this.mQueryCallback = queryCallback;
        this.mSqlStatement = str;
        this.mQueryCallbackExecutor = executor;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteStatement
    public void execute() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorStatement$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.this.m1458lambda$execute$0$androidxroomQueryInterceptorStatement();
            }
        });
        this.mDelegate.execute();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$execute$0$androidx-room-QueryInterceptorStatement  reason: not valid java name */
    public /* synthetic */ void m1458lambda$execute$0$androidxroomQueryInterceptorStatement() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteStatement
    public int executeUpdateDelete() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorStatement$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.this.m1388x7bfa4cf9();
            }
        });
        return this.mDelegate.executeUpdateDelete();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$executeUpdateDelete$1$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void m1388x7bfa4cf9() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteStatement
    public long executeInsert() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorStatement$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.this.m1459lambda$executeInsert$2$androidxroomQueryInterceptorStatement();
            }
        });
        return this.mDelegate.executeInsert();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$executeInsert$2$androidx-room-QueryInterceptorStatement  reason: not valid java name */
    public /* synthetic */ void m1459lambda$executeInsert$2$androidxroomQueryInterceptorStatement() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$simpleQueryForLong$3$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void m1387xa983133b() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteStatement
    public long simpleQueryForLong() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorStatement$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.this.m1387xa983133b();
            }
        });
        return this.mDelegate.simpleQueryForLong();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$simpleQueryForString$4$androidx-room-QueryInterceptorStatement */
    public /* synthetic */ void m1386x12aaf991() {
        this.mQueryCallback.onQuery(this.mSqlStatement, this.mBindArgsCache);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteStatement
    public String simpleQueryForString() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.QueryInterceptorStatement$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.this.m1386x12aaf991();
            }
        });
        return this.mDelegate.simpleQueryForString();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindNull(int r2) {
        saveArgsToCache(r2, this.mBindArgsCache.toArray());
        this.mDelegate.bindNull(r2);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindLong(int r2, long j) {
        saveArgsToCache(r2, Long.valueOf(j));
        this.mDelegate.bindLong(r2, j);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindDouble(int r2, double d) {
        saveArgsToCache(r2, Double.valueOf(d));
        this.mDelegate.bindDouble(r2, d);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindString(int r2, String str) {
        saveArgsToCache(r2, str);
        this.mDelegate.bindString(r2, str);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindBlob(int r2, byte[] bArr) {
        saveArgsToCache(r2, bArr);
        this.mDelegate.bindBlob(r2, bArr);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void clearBindings() {
        this.mBindArgsCache.clear();
        this.mDelegate.clearBindings();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mDelegate.close();
    }

    private void saveArgsToCache(int r4, Object obj) {
        int r42 = r4 - 1;
        if (r42 >= this.mBindArgsCache.size()) {
            for (int size = this.mBindArgsCache.size(); size <= r42; size++) {
                this.mBindArgsCache.add(null);
            }
        }
        this.mBindArgsCache.set(r42, obj);
    }
}
