package androidx.room.util;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* loaded from: classes.dex */
public class StringUtil {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static StringBuilder newStringBuilder() {
        return new StringBuilder();
    }

    public static void appendPlaceholders(StringBuilder sb, int r3) {
        for (int r0 = 0; r0 < r3; r0++) {
            sb.append("?");
            if (r0 < r3 - 1) {
                sb.append(",");
            }
        }
    }

    public static List<Integer> splitToIntList(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreElements()) {
            try {
                arrayList.add(Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken())));
            } catch (NumberFormatException e) {
                Log.e("ROOM", "Malformed integer list", e);
            }
        }
        return arrayList;
    }

    public static String joinIntoString(List<Integer> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        if (size == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int r2 = 0; r2 < size; r2++) {
            sb.append(Integer.toString(list.get(r2).intValue()));
            if (r2 < size - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private StringUtil() {
    }
}
