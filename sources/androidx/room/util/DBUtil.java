package androidx.room.util;

import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.room.RoomDatabase;
import androidx.sqlite.p007db.SupportSQLiteCompat;
import androidx.sqlite.p007db.SupportSQLiteDatabase;
import androidx.sqlite.p007db.SupportSQLiteQuery;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class DBUtil {
    @Deprecated
    public static Cursor query(RoomDatabase roomDatabase, SupportSQLiteQuery supportSQLiteQuery, boolean z) {
        return query(roomDatabase, supportSQLiteQuery, z, null);
    }

    public static Cursor query(RoomDatabase roomDatabase, SupportSQLiteQuery supportSQLiteQuery, boolean z, CancellationSignal cancellationSignal) {
        Cursor query = roomDatabase.query(supportSQLiteQuery, cancellationSignal);
        if (z && (query instanceof AbstractWindowedCursor)) {
            AbstractWindowedCursor abstractWindowedCursor = (AbstractWindowedCursor) query;
            int count = abstractWindowedCursor.getCount();
            return (Build.VERSION.SDK_INT < 23 || (abstractWindowedCursor.hasWindow() ? abstractWindowedCursor.getWindow().getNumRows() : count) < count) ? CursorUtil.copyAndClose(abstractWindowedCursor) : query;
        }
        return query;
    }

    public static void dropFtsSyncTriggers(SupportSQLiteDatabase supportSQLiteDatabase) {
        ArrayList<String> arrayList = new ArrayList();
        Cursor query = supportSQLiteDatabase.query("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        while (query.moveToNext()) {
            try {
                arrayList.add(query.getString(0));
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        query.close();
        for (String str : arrayList) {
            if (str.startsWith("room_fts_content_sync_")) {
                supportSQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS " + str);
            }
        }
    }

    public static void foreignKeyCheck(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Cursor query = supportSQLiteDatabase.query("PRAGMA foreign_key_check(`" + str + "`)");
        try {
            if (query.getCount() <= 0) {
                return;
            }
            throw new IllegalStateException(processForeignKeyCheckFailure(query));
        } finally {
            query.close();
        }
    }

    public static int readVersion(File file) throws IOException {
        FileChannel fileChannel = null;
        try {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            FileChannel channel = new FileInputStream(file).getChannel();
            channel.tryLock(60L, 4L, true);
            channel.position(60L);
            if (channel.read(allocate) != 4) {
                throw new IOException("Bad database header, unable to read 4 bytes at offset 60");
            }
            allocate.rewind();
            int r10 = allocate.getInt();
            if (channel != null) {
                channel.close();
            }
            return r10;
        } catch (Throwable th) {
            if (0 != 0) {
                fileChannel.close();
            }
            throw th;
        }
    }

    public static CancellationSignal createCancellationSignal() {
        if (Build.VERSION.SDK_INT >= 16) {
            return SupportSQLiteCompat.Api16Impl.createCancellationSignal();
        }
        return null;
    }

    private static String processForeignKeyCheckFailure(Cursor cursor) {
        int count = cursor.getCount();
        HashMap hashMap = new HashMap();
        String str = null;
        while (cursor.moveToNext()) {
            if (str == null) {
                str = cursor.getString(0);
            }
            String string = cursor.getString(3);
            if (!hashMap.containsKey(string)) {
                hashMap.put(string, cursor.getString(2));
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Foreign key violation(s) detected in '");
        sb.append(str);
        sb.append("'.\n");
        sb.append("Number of different violations discovered: ");
        sb.append(hashMap.keySet().size());
        sb.append("\n");
        sb.append("Number of rows in violation: ");
        sb.append(count);
        sb.append("\n");
        sb.append("Violation(s) detected in the following constraint(s):\n");
        for (Map.Entry entry : hashMap.entrySet()) {
            sb.append("\tParent Table = ");
            sb.append((String) entry.getValue());
            sb.append(", Foreign Key Constraint Index = ");
            sb.append((String) entry.getKey());
            sb.append("\n");
        }
        return sb.toString();
    }

    private DBUtil() {
    }
}
