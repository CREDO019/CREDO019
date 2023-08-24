package expo.modules.core.arguments;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface ReadableArguments {
    boolean containsKey(String str);

    Object get(String str);

    ReadableArguments getArguments(String str);

    boolean getBoolean(String str);

    boolean getBoolean(String str, boolean z);

    double getDouble(String str);

    double getDouble(String str, double d);

    int getInt(String str);

    int getInt(String str, int r2);

    List getList(String str);

    List getList(String str, List list);

    Map getMap(String str);

    Map getMap(String str, Map map);

    String getString(String str);

    String getString(String str, String str2);

    boolean isEmpty();

    Collection<String> keys();

    int size();

    Bundle toBundle();

    /* renamed from: expo.modules.core.arguments.ReadableArguments$-CC  reason: invalid class name */
    /* loaded from: classes4.dex */
    public final /* synthetic */ class CC {
        public static ReadableArguments $default$getArguments(ReadableArguments _this, String str) {
            Object obj = _this.get(str);
            if (obj instanceof Map) {
                return new MapArguments((Map) obj);
            }
            return null;
        }

        public static Bundle $default$toBundle(ReadableArguments _this) {
            Bundle bundle = new Bundle();
            for (String str : _this.keys()) {
                Object obj = _this.get(str);
                if (obj == null) {
                    bundle.putString(str, null);
                } else if (obj instanceof String) {
                    bundle.putString(str, (String) obj);
                } else if (obj instanceof Integer) {
                    bundle.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(str, ((Double) obj).doubleValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(str, ((Long) obj).longValue());
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof ArrayList) {
                    bundle.putParcelableArrayList(str, (ArrayList) obj);
                } else if (obj instanceof Map) {
                    bundle.putBundle(str, new MapArguments((Map) obj).toBundle());
                } else if (obj instanceof Bundle) {
                    bundle.putBundle(str, (Bundle) obj);
                } else {
                    throw new UnsupportedOperationException("Could not put a value of " + obj.getClass() + " to bundle.");
                }
            }
            return bundle;
        }
    }
}
