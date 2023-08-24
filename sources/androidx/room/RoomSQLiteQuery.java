package androidx.room;

import androidx.sqlite.p007db.SupportSQLiteProgram;
import androidx.sqlite.p007db.SupportSQLiteQuery;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class RoomSQLiteQuery implements SupportSQLiteQuery, SupportSQLiteProgram {
    private static final int BLOB = 5;
    static final int DESIRED_POOL_SIZE = 10;
    private static final int DOUBLE = 3;
    private static final int LONG = 2;
    private static final int NULL = 1;
    static final int POOL_LIMIT = 15;
    private static final int STRING = 4;
    static final TreeMap<Integer, RoomSQLiteQuery> sQueryPool = new TreeMap<>();
    int mArgCount;
    private final int[] mBindingTypes;
    final byte[][] mBlobBindings;
    final int mCapacity;
    final double[] mDoubleBindings;
    final long[] mLongBindings;
    private volatile String mQuery;
    final String[] mStringBindings;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public static RoomSQLiteQuery copyFrom(SupportSQLiteQuery supportSQLiteQuery) {
        RoomSQLiteQuery acquire = acquire(supportSQLiteQuery.getSql(), supportSQLiteQuery.getArgCount());
        supportSQLiteQuery.bindTo(new SupportSQLiteProgram() { // from class: androidx.room.RoomSQLiteQuery.1
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // androidx.sqlite.p007db.SupportSQLiteProgram
            public void bindNull(int r2) {
                RoomSQLiteQuery.this.bindNull(r2);
            }

            @Override // androidx.sqlite.p007db.SupportSQLiteProgram
            public void bindLong(int r2, long j) {
                RoomSQLiteQuery.this.bindLong(r2, j);
            }

            @Override // androidx.sqlite.p007db.SupportSQLiteProgram
            public void bindDouble(int r2, double d) {
                RoomSQLiteQuery.this.bindDouble(r2, d);
            }

            @Override // androidx.sqlite.p007db.SupportSQLiteProgram
            public void bindString(int r2, String str) {
                RoomSQLiteQuery.this.bindString(r2, str);
            }

            @Override // androidx.sqlite.p007db.SupportSQLiteProgram
            public void bindBlob(int r2, byte[] bArr) {
                RoomSQLiteQuery.this.bindBlob(r2, bArr);
            }

            @Override // androidx.sqlite.p007db.SupportSQLiteProgram
            public void clearBindings() {
                RoomSQLiteQuery.this.clearBindings();
            }
        });
        return acquire;
    }

    public static RoomSQLiteQuery acquire(String str, int r4) {
        TreeMap<Integer, RoomSQLiteQuery> treeMap = sQueryPool;
        synchronized (treeMap) {
            Map.Entry<Integer, RoomSQLiteQuery> ceilingEntry = treeMap.ceilingEntry(Integer.valueOf(r4));
            if (ceilingEntry != null) {
                treeMap.remove(ceilingEntry.getKey());
                RoomSQLiteQuery value = ceilingEntry.getValue();
                value.init(str, r4);
                return value;
            }
            RoomSQLiteQuery roomSQLiteQuery = new RoomSQLiteQuery(r4);
            roomSQLiteQuery.init(str, r4);
            return roomSQLiteQuery;
        }
    }

    private RoomSQLiteQuery(int r2) {
        this.mCapacity = r2;
        int r22 = r2 + 1;
        this.mBindingTypes = new int[r22];
        this.mLongBindings = new long[r22];
        this.mDoubleBindings = new double[r22];
        this.mStringBindings = new String[r22];
        this.mBlobBindings = new byte[r22];
    }

    void init(String str, int r2) {
        this.mQuery = str;
        this.mArgCount = r2;
    }

    public void release() {
        TreeMap<Integer, RoomSQLiteQuery> treeMap = sQueryPool;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.mCapacity), this);
            prunePoolLocked();
        }
    }

    private static void prunePoolLocked() {
        TreeMap<Integer, RoomSQLiteQuery> treeMap = sQueryPool;
        if (treeMap.size() <= 15) {
            return;
        }
        int size = treeMap.size() - 10;
        Iterator<Integer> it = treeMap.descendingKeySet().iterator();
        while (true) {
            int r2 = size - 1;
            if (size <= 0) {
                return;
            }
            it.next();
            it.remove();
            size = r2;
        }
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteQuery
    public String getSql() {
        return this.mQuery;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteQuery
    public int getArgCount() {
        return this.mArgCount;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        for (int r1 = 1; r1 <= this.mArgCount; r1++) {
            int r2 = this.mBindingTypes[r1];
            if (r2 == 1) {
                supportSQLiteProgram.bindNull(r1);
            } else if (r2 == 2) {
                supportSQLiteProgram.bindLong(r1, this.mLongBindings[r1]);
            } else if (r2 == 3) {
                supportSQLiteProgram.bindDouble(r1, this.mDoubleBindings[r1]);
            } else if (r2 == 4) {
                supportSQLiteProgram.bindString(r1, this.mStringBindings[r1]);
            } else if (r2 == 5) {
                supportSQLiteProgram.bindBlob(r1, this.mBlobBindings[r1]);
            }
        }
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindNull(int r3) {
        this.mBindingTypes[r3] = 1;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindLong(int r3, long j) {
        this.mBindingTypes[r3] = 2;
        this.mLongBindings[r3] = j;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindDouble(int r3, double d) {
        this.mBindingTypes[r3] = 3;
        this.mDoubleBindings[r3] = d;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindString(int r3, String str) {
        this.mBindingTypes[r3] = 4;
        this.mStringBindings[r3] = str;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void bindBlob(int r3, byte[] bArr) {
        this.mBindingTypes[r3] = 5;
        this.mBlobBindings[r3] = bArr;
    }

    public void copyArgumentsFrom(RoomSQLiteQuery roomSQLiteQuery) {
        int argCount = roomSQLiteQuery.getArgCount() + 1;
        System.arraycopy(roomSQLiteQuery.mBindingTypes, 0, this.mBindingTypes, 0, argCount);
        System.arraycopy(roomSQLiteQuery.mLongBindings, 0, this.mLongBindings, 0, argCount);
        System.arraycopy(roomSQLiteQuery.mStringBindings, 0, this.mStringBindings, 0, argCount);
        System.arraycopy(roomSQLiteQuery.mBlobBindings, 0, this.mBlobBindings, 0, argCount);
        System.arraycopy(roomSQLiteQuery.mDoubleBindings, 0, this.mDoubleBindings, 0, argCount);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteProgram
    public void clearBindings() {
        Arrays.fill(this.mBindingTypes, 1);
        Arrays.fill(this.mStringBindings, (Object) null);
        Arrays.fill(this.mBlobBindings, (Object) null);
        this.mQuery = null;
    }
}
