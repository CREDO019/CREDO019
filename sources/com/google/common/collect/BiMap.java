package com.google.common.collect;

import com.android.tools.r8.annotations.SynthesizedClass;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public interface BiMap<K, V> extends Map<K, V> {
    @CheckForNull
    V forcePut(@ParametricNullness K k, @ParametricNullness V v);

    BiMap<V, K> inverse();

    @Override // com.google.common.collect.BiMap
    @CheckForNull
    V put(@ParametricNullness K k, @ParametricNullness V v);

    @Override // com.google.common.collect.BiMap
    void putAll(Map<? extends K, ? extends V> map);

    @Override // java.util.Map
    Set<V> values();

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: com.google.common.collect.BiMap$-CC  reason: invalid class name */
    /* loaded from: classes3.dex */
    public final /* synthetic */ class CC<K, V> {
    }
}
