package androidx.room;

import androidx.sqlite.p007db.SupportSQLiteStatement;

/* loaded from: classes.dex */
public abstract class EntityDeletionOrUpdateAdapter<T> extends SharedSQLiteStatement {
    protected abstract void bind(SupportSQLiteStatement supportSQLiteStatement, T t);

    @Override // androidx.room.SharedSQLiteStatement
    protected abstract String createQuery();

    public EntityDeletionOrUpdateAdapter(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    public final int handle(T t) {
        SupportSQLiteStatement acquire = acquire();
        try {
            bind(acquire, t);
            return acquire.executeUpdateDelete();
        } finally {
            release(acquire);
        }
    }

    public final int handleMultiple(Iterable<? extends T> iterable) {
        SupportSQLiteStatement acquire = acquire();
        int r1 = 0;
        try {
            for (T t : iterable) {
                bind(acquire, t);
                r1 += acquire.executeUpdateDelete();
            }
            return r1;
        } finally {
            release(acquire);
        }
    }

    public final int handleMultiple(T[] tArr) {
        SupportSQLiteStatement acquire = acquire();
        try {
            int r3 = 0;
            for (T t : tArr) {
                bind(acquire, t);
                r3 += acquire.executeUpdateDelete();
            }
            return r3;
        } finally {
            release(acquire);
        }
    }
}
