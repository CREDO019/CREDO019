package com.polidea.rxandroidble.internal.cache;

import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.ClientScope;
import com.polidea.rxandroidble.internal.DeviceComponent;
import com.polidea.rxandroidble.internal.cache.DeviceComponentWeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import p042rx.Observable;
import p042rx.functions.Action2;
import p042rx.functions.Func0;
import p042rx.functions.Func1;

@ClientScope
/* loaded from: classes3.dex */
public class DeviceComponentCache implements Map<String, DeviceComponent> {
    private final HashMap<String, DeviceComponentWeakReference> cache;
    private final DeviceComponentWeakReference.Provider deviceComponentReferenceProvider;

    @Inject
    public DeviceComponentCache() {
        this(new DeviceComponentWeakReference.Provider() { // from class: com.polidea.rxandroidble.internal.cache.DeviceComponentCache.1
            @Override // com.polidea.rxandroidble.internal.cache.DeviceComponentWeakReference.Provider
            public DeviceComponentWeakReference provide(DeviceComponent deviceComponent) {
                return new DeviceComponentWeakReference(deviceComponent);
            }
        });
    }

    DeviceComponentCache(DeviceComponentWeakReference.Provider provider) {
        this.cache = new HashMap<>();
        this.deviceComponentReferenceProvider = provider;
    }

    @Override // java.util.Map
    public void clear() {
        this.cache.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.cache.containsKey(obj) && get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        for (DeviceComponentWeakReference deviceComponentWeakReference : this.cache.values()) {
            if (deviceComponentWeakReference.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, DeviceComponent>> entrySet() {
        return (Set) Observable.from(this.cache.entrySet()).filter(new Func1<Map.Entry<String, DeviceComponentWeakReference>, Boolean>() { // from class: com.polidea.rxandroidble.internal.cache.DeviceComponentCache.5
            @Override // p042rx.functions.Func1
            public Boolean call(Map.Entry<String, DeviceComponentWeakReference> entry) {
                return Boolean.valueOf(!entry.getValue().isEmpty());
            }
        }).map(new Func1<Map.Entry<String, DeviceComponentWeakReference>, CacheEntry>() { // from class: com.polidea.rxandroidble.internal.cache.DeviceComponentCache.4
            @Override // p042rx.functions.Func1
            public CacheEntry call(Map.Entry<String, DeviceComponentWeakReference> entry) {
                return new CacheEntry(entry.getKey(), DeviceComponentCache.this.deviceComponentReferenceProvider.provide((DeviceComponent) entry.getValue().get()));
            }
        }).collect(new Func0<HashSet<Map.Entry<String, DeviceComponent>>>() { // from class: com.polidea.rxandroidble.internal.cache.DeviceComponentCache.2
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public HashSet<Map.Entry<String, DeviceComponent>> call() {
                return new HashSet<>();
            }
        }, new Action2<HashSet<Map.Entry<String, DeviceComponent>>, CacheEntry>() { // from class: com.polidea.rxandroidble.internal.cache.DeviceComponentCache.3
            @Override // p042rx.functions.Action2
            public void call(HashSet<Map.Entry<String, DeviceComponent>> hashSet, CacheEntry cacheEntry) {
                hashSet.add(cacheEntry);
            }
        }).toBlocking().first();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    public DeviceComponent get(Object obj) {
        DeviceComponentWeakReference deviceComponentWeakReference = this.cache.get(obj);
        if (deviceComponentWeakReference != null) {
            return (DeviceComponent) deviceComponentWeakReference.get();
        }
        return null;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        evictEmptyReferences();
        return this.cache.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.cache.keySet();
    }

    @Override // java.util.Map
    public DeviceComponent put(String str, DeviceComponent deviceComponent) {
        this.cache.put(str, this.deviceComponentReferenceProvider.provide(deviceComponent));
        evictEmptyReferences();
        return deviceComponent;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends DeviceComponent> map) {
        for (Map.Entry<? extends String, ? extends DeviceComponent> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    public DeviceComponent remove(Object obj) {
        DeviceComponentWeakReference remove = this.cache.remove(obj);
        evictEmptyReferences();
        if (remove != null) {
            return (DeviceComponent) remove.get();
        }
        return null;
    }

    @Override // java.util.Map
    public int size() {
        evictEmptyReferences();
        return this.cache.size();
    }

    @Override // java.util.Map
    public Collection<DeviceComponent> values() {
        return (Collection) Observable.from(this.cache.values()).filter(new Func1<DeviceComponentWeakReference, Boolean>() { // from class: com.polidea.rxandroidble.internal.cache.DeviceComponentCache.7
            @Override // p042rx.functions.Func1
            public Boolean call(DeviceComponentWeakReference deviceComponentWeakReference) {
                return Boolean.valueOf(!deviceComponentWeakReference.isEmpty());
            }
        }).map(new Func1<DeviceComponentWeakReference, DeviceComponent>() { // from class: com.polidea.rxandroidble.internal.cache.DeviceComponentCache.6
            @Override // p042rx.functions.Func1
            public DeviceComponent call(DeviceComponentWeakReference deviceComponentWeakReference) {
                return (DeviceComponent) deviceComponentWeakReference.get();
            }
        }).toList().toBlocking().first();
    }

    private void evictEmptyReferences() {
        Iterator<Map.Entry<String, DeviceComponentWeakReference>> it = this.cache.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().isEmpty()) {
                it.remove();
            }
        }
    }
}
