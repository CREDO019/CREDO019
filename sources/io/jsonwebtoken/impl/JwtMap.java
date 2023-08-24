package io.jsonwebtoken.impl;

import io.jsonwebtoken.lang.Assert;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class JwtMap implements Map<String, Object> {
    private final Map<String, Object> map;

    public JwtMap() {
        this(new LinkedHashMap());
    }

    public JwtMap(Map<String, Object> map) {
        Assert.notNull(map, "Map argument cannot be null.");
        this.map = map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getString(String str) {
        Object obj = get(str);
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    protected static Date toDate(Object obj, String str) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Number) {
            return new Date(((Number) obj).longValue() * 1000);
        }
        if (obj instanceof String) {
            return new Date(Long.parseLong((String) obj) * 1000);
        }
        throw new IllegalStateException("Cannot convert '" + str + "' value [" + obj + "] to Date instance.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setValue(String str, Object obj) {
        if (obj == null) {
            this.map.remove(str);
        } else {
            this.map.put(str, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Date getDate(String str) {
        return toDate(this.map.get(str), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDate(String str, Date date) {
        if (date == null) {
            this.map.remove(str);
        } else {
            this.map.put(str, Long.valueOf(date.getTime() / 1000));
        }
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return this.map.get(obj);
    }

    @Override // java.util.Map
    public Object put(String str, Object obj) {
        if (obj == null) {
            return this.map.remove(str);
        }
        return this.map.put(str, obj);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        if (map == null) {
            return;
        }
        for (String str : map.keySet()) {
            this.map.put(str, map.get(str));
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return this.map.values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public String toString() {
        return this.map.toString();
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }
}
