package org.apache.logging.log4j.spi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.util.PropertiesUtil;

/* loaded from: classes5.dex */
public class DefaultThreadContextMap implements ThreadContextMap {
    public static final String INHERITABLE_MAP = "isThreadContextMapInheritable";
    private final ThreadLocal<Map<String, String>> localMap;
    private final boolean useMap;

    public DefaultThreadContextMap(boolean z) {
        this.useMap = z;
        this.localMap = createThreadLocalMap(z);
    }

    static ThreadLocal<Map<String, String>> createThreadLocalMap(final boolean z) {
        if (PropertiesUtil.getProperties().getBooleanProperty(INHERITABLE_MAP)) {
            return new InheritableThreadLocal<Map<String, String>>() { // from class: org.apache.logging.log4j.spi.DefaultThreadContextMap.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // java.lang.InheritableThreadLocal
                public Map<String, String> childValue(Map<String, String> map) {
                    if (map == null || !z) {
                        return null;
                    }
                    return Collections.unmodifiableMap(new HashMap(map));
                }
            };
        }
        return new ThreadLocal<>();
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void put(String str, String str2) {
        if (this.useMap) {
            Map<String, String> map = this.localMap.get();
            HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
            hashMap.put(str, str2);
            this.localMap.set(Collections.unmodifiableMap(hashMap));
        }
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public String get(String str) {
        Map<String, String> map = this.localMap.get();
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void remove(String str) {
        Map<String, String> map = this.localMap.get();
        if (map != null) {
            HashMap hashMap = new HashMap(map);
            hashMap.remove(str);
            this.localMap.set(Collections.unmodifiableMap(hashMap));
        }
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void clear() {
        this.localMap.remove();
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public boolean containsKey(String str) {
        Map<String, String> map = this.localMap.get();
        return map != null && map.containsKey(str);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getCopy() {
        Map<String, String> map = this.localMap.get();
        return map == null ? new HashMap() : new HashMap(map);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getImmutableMapOrNull() {
        return this.localMap.get();
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public boolean isEmpty() {
        Map<String, String> map = this.localMap.get();
        return map == null || map.size() == 0;
    }

    public String toString() {
        Map<String, String> map = this.localMap.get();
        return map == null ? "{}" : map.toString();
    }

    public int hashCode() {
        Map<String, String> map = this.localMap.get();
        return (((map == null ? 0 : map.hashCode()) + 31) * 31) + (this.useMap ? 1231 : 1237);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if ((!(obj instanceof DefaultThreadContextMap) || this.useMap == ((DefaultThreadContextMap) obj).useMap) && (obj instanceof ThreadContextMap)) {
            Map<String, String> map = this.localMap.get();
            Map<String, String> immutableMapOrNull = ((ThreadContextMap) obj).getImmutableMapOrNull();
            if (map == null) {
                if (immutableMapOrNull != null) {
                    return false;
                }
            } else if (!map.equals(immutableMapOrNull)) {
                return false;
            }
            return true;
        }
        return false;
    }
}
