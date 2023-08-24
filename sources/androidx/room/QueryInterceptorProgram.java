package androidx.room;

import androidx.sqlite.p007db.SupportSQLiteProgram;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class QueryInterceptorProgram implements SupportSQLiteProgram {
    private List<Object> mBindArgsCache = new ArrayList();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindNull(int r2) {
        saveArgsToCache(r2, null);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindLong(int r1, long j) {
        saveArgsToCache(r1, Long.valueOf(j));
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindDouble(int r1, double d) {
        saveArgsToCache(r1, Double.valueOf(d));
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindString(int r1, String str) {
        saveArgsToCache(r1, str);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindBlob(int r1, byte[] bArr) {
        saveArgsToCache(r1, bArr);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void clearBindings() {
        this.mBindArgsCache.clear();
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Object> getBindArgs() {
        return this.mBindArgsCache;
    }
}
