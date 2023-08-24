package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import java.util.Map;

/* loaded from: classes.dex */
class EngineKeyFactory {
    /* JADX INFO: Access modifiers changed from: package-private */
    public EngineKey buildKey(Object obj, Key key, int r13, int r14, Map<Class<?>, Transformation<?>> map, Class<?> cls, Class<?> cls2, Options options) {
        return new EngineKey(obj, key, r13, r14, map, cls, cls2, options);
    }
}