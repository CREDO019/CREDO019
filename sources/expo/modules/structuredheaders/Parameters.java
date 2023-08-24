package expo.modules.structuredheaders;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* loaded from: classes3.dex */
public class Parameters implements Map<String, Item<? extends Object>> {
    public static final Parameters EMPTY = new Parameters(Collections.emptyMap());
    private final Map<String, Item<? extends Object>> delegate;

    private Parameters(Map<String, Object> map) {
        this.delegate = Collections.unmodifiableMap(checkAndTransformMap(map));
    }

    public static Parameters valueOf(Map<String, Object> map) {
        return new Parameters(map);
    }

    public StringBuilder serializeTo(StringBuilder sb) {
        for (Map.Entry<String, Item<? extends Object>> entry : this.delegate.entrySet()) {
            sb.append(';');
            sb.append(entry.getKey());
            if (!entry.getValue().get().equals(Boolean.TRUE)) {
                sb.append('=');
                entry.getValue().serializeTo(sb);
            }
        }
        return sb;
    }

    private static Map<String, Item<? extends Object>> checkAndTransformMap(Map<String, Object> map) {
        Objects.requireNonNull(map, "Map must not be null");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String checkKey = C4561Utils.checkKey(entry.getKey());
            Item<? extends Object> asItem = asItem(checkKey, entry.getValue());
            if (!asItem.getParams().isEmpty()) {
                throw new IllegalArgumentException("Parameter value for '" + checkKey + "' must be bare item (no parameters)");
            }
            linkedHashMap.put(entry.getKey(), asItem);
        }
        return linkedHashMap;
    }

    private static Item<? extends Object> asItem(String str, Object obj) {
        if (obj instanceof Item) {
            return (Item) obj;
        }
        if (obj instanceof Integer) {
            return IntegerItem.valueOf(((Integer) obj).longValue());
        }
        if (obj instanceof Long) {
            return IntegerItem.valueOf(((Long) obj).longValue());
        }
        if (obj instanceof String) {
            return StringItem.valueOf((String) obj);
        }
        if (obj instanceof Boolean) {
            return BooleanItem.valueOf(((Boolean) obj).booleanValue());
        }
        if (obj instanceof byte[]) {
            return ByteSequenceItem.valueOf((byte[]) obj);
        }
        if (obj instanceof BigDecimal) {
            return DecimalItem.valueOf((BigDecimal) obj);
        }
        throw new IllegalArgumentException("Can't map value for parameter '" + str + "': " + obj.getClass());
    }

    @Override // java.util.Map
    public void clear() {
        this.delegate.clear();
    }

    @Override // java.util.Map
    public Item<? extends Object> compute(String str, BiFunction<? super String, ? super Item<? extends Object>, ? extends Item<? extends Object>> biFunction) {
        return this.delegate.compute(str, biFunction);
    }

    @Override // java.util.Map
    public Item<? extends Object> computeIfAbsent(String str, Function<? super String, ? extends Item<? extends Object>> function) {
        return this.delegate.computeIfAbsent(str, function);
    }

    @Override // java.util.Map
    public Item<? extends Object> computeIfPresent(String str, BiFunction<? super String, ? super Item<? extends Object>, ? extends Item<? extends Object>> biFunction) {
        return this.delegate.computeIfPresent(str, biFunction);
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.delegate.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.delegate.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Item<? extends Object>>> entrySet() {
        return this.delegate.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.delegate.equals(obj);
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super String, ? super Item<? extends Object>> biConsumer) {
        this.delegate.forEach(biConsumer);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    public Item<? extends Object> get(Object obj) {
        return this.delegate.get(obj);
    }

    @Override // java.util.Map
    public Item<? extends Object> getOrDefault(Object obj, Item<? extends Object> item) {
        return this.delegate.getOrDefault(obj, item);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.delegate.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.delegate.keySet();
    }

    @Override // java.util.Map
    public Item<? extends Object> merge(String str, Item<? extends Object> item, BiFunction<? super Item<? extends Object>, ? super Item<? extends Object>, ? extends Item<? extends Object>> biFunction) {
        return this.delegate.merge(str, item, biFunction);
    }

    @Override // java.util.Map
    public Item<? extends Object> put(String str, Item<? extends Object> item) {
        return this.delegate.put(str, item);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Item<? extends Object>> map) {
        this.delegate.putAll(map);
    }

    @Override // java.util.Map
    public Item<? extends Object> putIfAbsent(String str, Item<? extends Object> item) {
        return this.delegate.putIfAbsent(str, item);
    }

    @Override // java.util.Map
    public boolean remove(Object obj, Object obj2) {
        return this.delegate.remove(obj, obj2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    public Item<? extends Object> remove(Object obj) {
        return this.delegate.remove(obj);
    }

    @Override // java.util.Map
    public boolean replace(String str, Item<? extends Object> item, Item<? extends Object> item2) {
        return this.delegate.replace(str, item, item2);
    }

    @Override // java.util.Map
    public Item<? extends Object> replace(String str, Item<? extends Object> item) {
        return this.delegate.replace(str, item);
    }

    @Override // java.util.Map
    public void replaceAll(BiFunction<? super String, ? super Item<? extends Object>, ? extends Item<? extends Object>> biFunction) {
        this.delegate.replaceAll(biFunction);
    }

    @Override // java.util.Map
    public int size() {
        return this.delegate.size();
    }

    @Override // java.util.Map
    public Collection<Item<? extends Object>> values() {
        return this.delegate.values();
    }

    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }
}
