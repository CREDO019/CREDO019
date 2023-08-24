package androidx.room.migration;

import androidx.sqlite.p007db.SupportSQLiteDatabase;

/* loaded from: classes.dex */
public abstract class Migration {
    public final int endVersion;
    public final int startVersion;

    public abstract void migrate(SupportSQLiteDatabase supportSQLiteDatabase);

    public Migration(int r1, int r2) {
        this.startVersion = r1;
        this.endVersion = r2;
    }
}
