package androidx.sqlite.p007db.framework;

import android.database.sqlite.SQLiteProgram;
import androidx.sqlite.p007db.SupportSQLiteProgram;

/* renamed from: androidx.sqlite.db.framework.FrameworkSQLiteProgram */
/* loaded from: classes.dex */
class FrameworkSQLiteProgram implements SupportSQLiteProgram {
    private final SQLiteProgram mDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrameworkSQLiteProgram(SQLiteProgram sQLiteProgram) {
        this.mDelegate = sQLiteProgram;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindNull(int r2) {
        this.mDelegate.bindNull(r2);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindLong(int r2, long j) {
        this.mDelegate.bindLong(r2, j);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindDouble(int r2, double d) {
        this.mDelegate.bindDouble(r2, d);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindString(int r2, String str) {
        this.mDelegate.bindString(r2, str);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindBlob(int r2, byte[] bArr) {
        this.mDelegate.bindBlob(r2, bArr);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void clearBindings() {
        this.mDelegate.clearBindings();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mDelegate.close();
    }
}
