package androidx.sqlite.p007db;

/* renamed from: androidx.sqlite.db.SimpleSQLiteQuery */
/* loaded from: classes.dex */
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    private final Object[] mBindArgs;
    private final String mQuery;

    public SimpleSQLiteQuery(String str, Object[] objArr) {
        this.mQuery = str;
        this.mBindArgs = objArr;
    }

    public SimpleSQLiteQuery(String str) {
        this(str, null);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteQuery
    public String getSql() {
        return this.mQuery;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        bind(supportSQLiteProgram, this.mBindArgs);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteQuery
    public int getArgCount() {
        Object[] objArr = this.mBindArgs;
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    public static void bind(SupportSQLiteProgram supportSQLiteProgram, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        int length = objArr.length;
        int r1 = 0;
        while (r1 < length) {
            Object obj = objArr[r1];
            r1++;
            bind(supportSQLiteProgram, r1, obj);
        }
    }

    private static void bind(SupportSQLiteProgram supportSQLiteProgram, int r3, Object obj) {
        if (obj == null) {
            supportSQLiteProgram.bindNull(r3);
        } else if (obj instanceof byte[]) {
            supportSQLiteProgram.bindBlob(r3, (byte[]) obj);
        } else if (obj instanceof Float) {
            supportSQLiteProgram.bindDouble(r3, ((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            supportSQLiteProgram.bindDouble(r3, ((Double) obj).doubleValue());
        } else if (obj instanceof Long) {
            supportSQLiteProgram.bindLong(r3, ((Long) obj).longValue());
        } else if (obj instanceof Integer) {
            supportSQLiteProgram.bindLong(r3, ((Integer) obj).intValue());
        } else if (obj instanceof Short) {
            supportSQLiteProgram.bindLong(r3, ((Short) obj).shortValue());
        } else if (obj instanceof Byte) {
            supportSQLiteProgram.bindLong(r3, ((Byte) obj).byteValue());
        } else if (obj instanceof String) {
            supportSQLiteProgram.bindString(r3, (String) obj);
        } else if (obj instanceof Boolean) {
            supportSQLiteProgram.bindLong(r3, ((Boolean) obj).booleanValue() ? 1L : 0L);
        } else {
            throw new IllegalArgumentException("Cannot bind " + obj + " at index " + r3 + " Supported types: null, byte[], float, double, long, int, short, byte, string");
        }
    }
}
