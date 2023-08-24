package com.facebook.common.logging;

/* loaded from: classes.dex */
public class FLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static LoggingDelegate sHandler = FLogDefaultLoggingDelegate.getInstance();

    public static void setLoggingDelegate(LoggingDelegate delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException();
        }
        sHandler = delegate;
    }

    public static boolean isLoggable(int level) {
        return sHandler.isLoggable(level);
    }

    public static void setMinimumLoggingLevel(int level) {
        sHandler.setMinimumLoggingLevel(level);
    }

    public static int getMinimumLoggingLevel() {
        return sHandler.getMinimumLoggingLevel();
    }

    /* renamed from: v */
    public static void m1300v(String tag, String msg) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(tag, msg);
        }
    }

    /* renamed from: v */
    public static void m1299v(String tag, String msg, Object arg1) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(tag, formatString(msg, arg1));
        }
    }

    /* renamed from: v */
    public static void m1298v(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(tag, formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: v */
    public static void m1297v(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: v */
    public static void m1296v(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: v */
    public static void m1308v(Class<?> cls, String msg) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(getTag(cls), msg);
        }
    }

    /* renamed from: v */
    public static void m1307v(Class<?> cls, String msg, Object arg1) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(getTag(cls), formatString(msg, arg1));
        }
    }

    /* renamed from: v */
    public static void m1306v(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: v */
    public static void m1305v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (isLoggable(2)) {
            m1308v(cls, formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: v */
    public static void m1304v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: v */
    public static void m1294v(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(tag, formatString(msg, args));
        }
    }

    /* renamed from: v */
    public static void m1293v(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1277v(tag, formatString(msg, args), tr);
        }
    }

    /* renamed from: v */
    public static void m1302v(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1278v(getTag(cls), formatString(msg, args));
        }
    }

    /* renamed from: v */
    public static void m1301v(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1277v(getTag(cls), formatString(msg, args), tr);
        }
    }

    /* renamed from: v */
    public static void m1295v(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1277v(tag, msg, tr);
        }
    }

    /* renamed from: v */
    public static void m1303v(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo1277v(getTag(cls), msg, tr);
        }
    }

    /* renamed from: d */
    public static void m1340d(String tag, String msg) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(tag, msg);
        }
    }

    /* renamed from: d */
    public static void m1339d(String tag, String msg, Object arg1) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(tag, formatString(msg, arg1));
        }
    }

    /* renamed from: d */
    public static void m1338d(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(tag, formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: d */
    public static void m1337d(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: d */
    public static void m1336d(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: d */
    public static void m1348d(Class<?> cls, String msg) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(getTag(cls), msg);
        }
    }

    /* renamed from: d */
    public static void m1347d(Class<?> cls, String msg, Object arg1) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(getTag(cls), formatString(msg, arg1));
        }
    }

    /* renamed from: d */
    public static void m1346d(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: d */
    public static void m1345d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(getTag(cls), formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: d */
    public static void m1344d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: d */
    public static void m1334d(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            m1340d(tag, formatString(msg, args));
        }
    }

    /* renamed from: d */
    public static void m1333d(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            m1335d(tag, formatString(msg, args), tr);
        }
    }

    /* renamed from: d */
    public static void m1342d(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1284d(getTag(cls), formatString(msg, args));
        }
    }

    /* renamed from: d */
    public static void m1341d(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1283d(getTag(cls), formatString(msg, args), tr);
        }
    }

    /* renamed from: d */
    public static void m1335d(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1283d(tag, msg, tr);
        }
    }

    /* renamed from: d */
    public static void m1343d(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo1283d(getTag(cls), msg, tr);
        }
    }

    /* renamed from: i */
    public static void m1316i(String tag, String msg) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(tag, msg);
        }
    }

    /* renamed from: i */
    public static void m1315i(String tag, String msg, Object arg1) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(tag, formatString(msg, arg1));
        }
    }

    /* renamed from: i */
    public static void m1314i(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(tag, formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: i */
    public static void m1313i(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: i */
    public static void m1312i(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: i */
    public static void m1324i(Class<?> cls, String msg) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(getTag(cls), msg);
        }
    }

    /* renamed from: i */
    public static void m1323i(Class<?> cls, String msg, Object arg1) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(getTag(cls), formatString(msg, arg1));
        }
    }

    /* renamed from: i */
    public static void m1322i(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: i */
    public static void m1321i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(getTag(cls), formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: i */
    public static void m1320i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: i */
    public static void m1310i(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(tag, formatString(msg, args));
        }
    }

    /* renamed from: i */
    public static void m1309i(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1279i(tag, formatString(msg, args), tr);
        }
    }

    /* renamed from: i */
    public static void m1318i(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1280i(getTag(cls), formatString(msg, args));
        }
    }

    /* renamed from: i */
    public static void m1317i(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (isLoggable(4)) {
            sHandler.mo1279i(getTag(cls), formatString(msg, args), tr);
        }
    }

    /* renamed from: i */
    public static void m1311i(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1279i(tag, msg, tr);
        }
    }

    /* renamed from: i */
    public static void m1319i(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo1279i(getTag(cls), msg, tr);
        }
    }

    /* renamed from: w */
    public static void m1288w(String tag, String msg) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo1276w(tag, msg);
        }
    }

    /* renamed from: w */
    public static void m1292w(Class<?> cls, String msg) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo1276w(getTag(cls), msg);
        }
    }

    /* renamed from: w */
    public static void m1286w(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo1276w(tag, formatString(msg, args));
        }
    }

    /* renamed from: w */
    public static void m1285w(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo1275w(tag, formatString(msg, args), tr);
        }
    }

    /* renamed from: w */
    public static void m1290w(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo1276w(getTag(cls), formatString(msg, args));
        }
    }

    /* renamed from: w */
    public static void m1289w(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (isLoggable(5)) {
            m1291w(cls, formatString(msg, args), tr);
        }
    }

    /* renamed from: w */
    public static void m1287w(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo1275w(tag, msg, tr);
        }
    }

    /* renamed from: w */
    public static void m1291w(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo1275w(getTag(cls), msg, tr);
        }
    }

    /* renamed from: e */
    public static void m1328e(String tag, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo1282e(tag, msg);
        }
    }

    /* renamed from: e */
    public static void m1332e(Class<?> cls, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo1282e(getTag(cls), msg);
        }
    }

    /* renamed from: e */
    public static void m1326e(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo1282e(tag, formatString(msg, args));
        }
    }

    /* renamed from: e */
    public static void m1325e(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo1281e(tag, formatString(msg, args), tr);
        }
    }

    /* renamed from: e */
    public static void m1330e(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo1282e(getTag(cls), formatString(msg, args));
        }
    }

    /* renamed from: e */
    public static void m1329e(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo1281e(getTag(cls), formatString(msg, args), tr);
        }
    }

    /* renamed from: e */
    public static void m1327e(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo1281e(tag, msg, tr);
        }
    }

    /* renamed from: e */
    public static void m1331e(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo1281e(getTag(cls), msg, tr);
        }
    }

    public static void wtf(String tag, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, msg);
        }
    }

    public static void wtf(Class<?> cls, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), msg);
        }
    }

    public static void wtf(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, formatString(msg, args));
        }
    }

    public static void wtf(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, formatString(msg, args), tr);
        }
    }

    public static void wtf(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), formatString(msg, args));
        }
    }

    public static void wtf(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, msg, tr);
        }
    }

    public static void wtf(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), msg, tr);
        }
    }

    private static String formatString(String str, Object... args) {
        return String.format(null, str, args);
    }

    private static String getTag(Class<?> clazz) {
        return clazz.getSimpleName();
    }
}
