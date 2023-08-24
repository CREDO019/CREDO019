package expo.modules.core.arguments;

import android.os.Bundle;
import expo.modules.core.arguments.ReadableArguments;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class MapArguments implements ReadableArguments {
    private Map<String, Object> mMap;

    @Override // expo.modules.core.arguments.ReadableArguments
    public /* synthetic */ ReadableArguments getArguments(String str) {
        return ReadableArguments.CC.$default$getArguments(this, str);
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public /* synthetic */ boolean getBoolean(String str) {
        boolean z;
        z = getBoolean(str, false);
        return z;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public /* synthetic */ double getDouble(String str) {
        double d;
        d = getDouble(str, 0.0d);
        return d;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public /* synthetic */ int getInt(String str) {
        int r1;
        r1 = getInt(str, 0);
        return r1;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public /* synthetic */ List getList(String str) {
        List list;
        list = getList(str, null);
        return list;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public /* synthetic */ Map getMap(String str) {
        Map map;
        map = getMap(str, null);
        return map;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public /* synthetic */ String getString(String str) {
        String string;
        string = getString(str, null);
        return string;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public /* synthetic */ Bundle toBundle() {
        return ReadableArguments.CC.$default$toBundle(this);
    }

    public MapArguments() {
        this.mMap = new HashMap();
    }

    public MapArguments(Map<String, Object> map) {
        this.mMap = map;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public Collection<String> keys() {
        return this.mMap.keySet();
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public boolean containsKey(String str) {
        return this.mMap.containsKey(str);
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public Object get(String str) {
        return this.mMap.get(str);
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public boolean getBoolean(String str, boolean z) {
        Object obj = this.mMap.get(str);
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public double getDouble(String str, double d) {
        Object obj = this.mMap.get(str);
        return obj instanceof Number ? ((Number) obj).doubleValue() : d;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public int getInt(String str, int r3) {
        Object obj = this.mMap.get(str);
        return obj instanceof Number ? ((Number) obj).intValue() : r3;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public String getString(String str, String str2) {
        Object obj = this.mMap.get(str);
        return obj instanceof String ? (String) obj : str2;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public List getList(String str, List list) {
        Object obj = this.mMap.get(str);
        return obj instanceof List ? (List) obj : list;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public Map getMap(String str, Map map) {
        Object obj = this.mMap.get(str);
        return obj instanceof Map ? (Map) obj : map;
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public boolean isEmpty() {
        return this.mMap.isEmpty();
    }

    @Override // expo.modules.core.arguments.ReadableArguments
    public int size() {
        return this.mMap.size();
    }
}
