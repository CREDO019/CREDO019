package androidx.room.util;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;

/* loaded from: classes.dex */
public class CursorUtil {
    public static Cursor copyAndClose(Cursor cursor) {
        try {
            MatrixCursor matrixCursor = new MatrixCursor(cursor.getColumnNames(), cursor.getCount());
            while (cursor.moveToNext()) {
                Object[] objArr = new Object[cursor.getColumnCount()];
                for (int r2 = 0; r2 < cursor.getColumnCount(); r2++) {
                    int type = cursor.getType(r2);
                    if (type == 0) {
                        objArr[r2] = null;
                    } else if (type == 1) {
                        objArr[r2] = Long.valueOf(cursor.getLong(r2));
                    } else if (type == 2) {
                        objArr[r2] = Double.valueOf(cursor.getDouble(r2));
                    } else if (type == 3) {
                        objArr[r2] = cursor.getString(r2);
                    } else if (type == 4) {
                        objArr[r2] = cursor.getBlob(r2);
                    } else {
                        throw new IllegalStateException();
                    }
                }
                matrixCursor.addRow(objArr);
            }
            return matrixCursor;
        } finally {
            cursor.close();
        }
    }

    public static int getColumnIndex(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        int columnIndex2 = cursor.getColumnIndex("`" + str + "`");
        return columnIndex2 >= 0 ? columnIndex2 : findColumnIndexBySuffix(cursor, str);
    }

    public static int getColumnIndexOrThrow(Cursor cursor, String str) {
        String str2;
        int columnIndex = getColumnIndex(cursor, str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        try {
            str2 = Arrays.toString(cursor.getColumnNames());
        } catch (Exception e) {
            Log.d("RoomCursorUtil", "Cannot collect column names for debug purposes", e);
            str2 = "";
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist. Available columns: " + str2);
    }

    private static int findColumnIndexBySuffix(Cursor cursor, String str) {
        if (Build.VERSION.SDK_INT <= 25 && str.length() != 0) {
            return findColumnIndexBySuffix(cursor.getColumnNames(), str);
        }
        return -1;
    }

    static int findColumnIndexBySuffix(String[] strArr, String str) {
        String str2 = "." + str;
        String str3 = "." + str + "`";
        for (int r3 = 0; r3 < strArr.length; r3++) {
            String str4 = strArr[r3];
            if (str4.length() >= str.length() + 2) {
                if (str4.endsWith(str2)) {
                    return r3;
                }
                if (str4.charAt(0) == '`' && str4.endsWith(str3)) {
                    return r3;
                }
            }
        }
        return -1;
    }

    private CursorUtil() {
    }
}
