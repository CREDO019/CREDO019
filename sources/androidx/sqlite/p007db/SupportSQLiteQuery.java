package androidx.sqlite.p007db;

/* renamed from: androidx.sqlite.db.SupportSQLiteQuery */
/* loaded from: classes.dex */
public interface SupportSQLiteQuery {
    void bindTo(SupportSQLiteProgram supportSQLiteProgram);

    int getArgCount();

    String getSql();
}
