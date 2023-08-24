package com.facebook.react.uimanager;

import android.content.Context;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ViewManagersPropertyCache {
    private static final Map<Class, Map<String, PropSetter>> CLASS_PROPS_CACHE = new HashMap();
    private static final Map<String, PropSetter> EMPTY_PROPS_MAP = new HashMap();

    ViewManagersPropertyCache() {
    }

    public static void clear() {
        CLASS_PROPS_CACHE.clear();
        EMPTY_PROPS_MAP.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class PropSetter {
        protected final Integer mIndex;
        protected final String mPropName;
        protected final String mPropType;
        protected final Method mSetter;
        private static final ThreadLocal<Object[]> VIEW_MGR_ARGS = ViewManagersPropertyCache.createThreadLocalArray(2);
        private static final ThreadLocal<Object[]> VIEW_MGR_GROUP_ARGS = ViewManagersPropertyCache.createThreadLocalArray(3);
        private static final ThreadLocal<Object[]> SHADOW_ARGS = ViewManagersPropertyCache.createThreadLocalArray(1);
        private static final ThreadLocal<Object[]> SHADOW_GROUP_ARGS = ViewManagersPropertyCache.createThreadLocalArray(2);

        protected abstract Object getValueOrDefault(Object obj, Context context);

        private PropSetter(ReactProp reactProp, String str, Method method) {
            this.mPropName = reactProp.name();
            this.mPropType = "__default_type__".equals(reactProp.customType()) ? str : reactProp.customType();
            this.mSetter = method;
            this.mIndex = null;
        }

        private PropSetter(ReactPropGroup reactPropGroup, String str, Method method, int r6) {
            this.mPropName = reactPropGroup.names()[r6];
            this.mPropType = "__default_type__".equals(reactPropGroup.customType()) ? str : reactPropGroup.customType();
            this.mSetter = method;
            this.mIndex = Integer.valueOf(r6);
        }

        public String getPropName() {
            return this.mPropName;
        }

        public String getPropType() {
            return this.mPropType;
        }

        public void updateViewProp(ViewManager viewManager, View view, Object obj) {
            Object[] objArr;
            try {
                if (this.mIndex == null) {
                    objArr = VIEW_MGR_ARGS.get();
                    objArr[0] = view;
                    objArr[1] = getValueOrDefault(obj, view.getContext());
                } else {
                    objArr = VIEW_MGR_GROUP_ARGS.get();
                    objArr[0] = view;
                    objArr[1] = this.mIndex;
                    objArr[2] = getValueOrDefault(obj, view.getContext());
                }
                this.mSetter.invoke(viewManager, objArr);
                Arrays.fill(objArr, (Object) null);
            } catch (Throwable th) {
                FLog.m1331e(ViewManager.class, "Error while updating prop " + this.mPropName, th);
                throw new JSApplicationIllegalArgumentException("Error while updating property '" + this.mPropName + "' of a view managed by: " + viewManager.getName(), th);
            }
        }

        public void updateShadowNodeProp(ReactShadowNode reactShadowNode, Object obj) {
            Object[] objArr;
            try {
                if (this.mIndex == null) {
                    objArr = SHADOW_ARGS.get();
                    objArr[0] = getValueOrDefault(obj, reactShadowNode.getThemedContext());
                } else {
                    objArr = SHADOW_GROUP_ARGS.get();
                    objArr[0] = this.mIndex;
                    objArr[1] = getValueOrDefault(obj, reactShadowNode.getThemedContext());
                }
                this.mSetter.invoke(reactShadowNode, objArr);
                Arrays.fill(objArr, (Object) null);
            } catch (Throwable th) {
                FLog.m1331e(ViewManager.class, "Error while updating prop " + this.mPropName, th);
                throw new JSApplicationIllegalArgumentException("Error while updating property '" + this.mPropName + "' in shadow node of type: " + reactShadowNode.getViewClass(), th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DynamicPropSetter extends PropSetter {
        public DynamicPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "mixed", method);
        }

        public DynamicPropSetter(ReactPropGroup reactPropGroup, Method method, int r9) {
            super(reactPropGroup, "mixed", method, r9);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return obj instanceof Dynamic ? obj : new DynamicFromObject(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class IntPropSetter extends PropSetter {
        private final int mDefaultValue;

        public IntPropSetter(ReactProp reactProp, Method method, int r5) {
            super(reactProp, "number", method);
            this.mDefaultValue = r5;
        }

        public IntPropSetter(ReactPropGroup reactPropGroup, Method method, int r9, int r10) {
            super(reactPropGroup, "number", method, r9);
            this.mDefaultValue = r10;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return Integer.valueOf(obj == null ? this.mDefaultValue : Integer.valueOf(((Double) obj).intValue()).intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DoublePropSetter extends PropSetter {
        private final double mDefaultValue;

        public DoublePropSetter(ReactProp reactProp, Method method, double d) {
            super(reactProp, "number", method);
            this.mDefaultValue = d;
        }

        public DoublePropSetter(ReactPropGroup reactPropGroup, Method method, int r9, double d) {
            super(reactPropGroup, "number", method, r9);
            this.mDefaultValue = d;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return Double.valueOf(obj == null ? this.mDefaultValue : ((Double) obj).doubleValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ColorPropSetter extends PropSetter {
        private final int mDefaultValue;

        public ColorPropSetter(ReactProp reactProp, Method method) {
            this(reactProp, method, 0);
        }

        public ColorPropSetter(ReactProp reactProp, Method method, int r5) {
            super(reactProp, "mixed", method);
            this.mDefaultValue = r5;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            if (obj == null) {
                return Integer.valueOf(this.mDefaultValue);
            }
            return ColorPropConverter.getColor(obj, context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class BooleanPropSetter extends PropSetter {
        private final boolean mDefaultValue;

        public BooleanPropSetter(ReactProp reactProp, Method method, boolean z) {
            super(reactProp, "boolean", method);
            this.mDefaultValue = z;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return obj == null ? this.mDefaultValue : ((Boolean) obj).booleanValue() ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class FloatPropSetter extends PropSetter {
        private final float mDefaultValue;

        public FloatPropSetter(ReactProp reactProp, Method method, float f) {
            super(reactProp, "number", method);
            this.mDefaultValue = f;
        }

        public FloatPropSetter(ReactPropGroup reactPropGroup, Method method, int r9, float f) {
            super(reactPropGroup, "number", method, r9);
            this.mDefaultValue = f;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return Float.valueOf(obj == null ? this.mDefaultValue : Float.valueOf(((Double) obj).floatValue()).floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ArrayPropSetter extends PropSetter {
        public ArrayPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "Array", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return (ReadableArray) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class MapPropSetter extends PropSetter {
        public MapPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "Map", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return (ReadableMap) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class StringPropSetter extends PropSetter {
        public StringPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "String", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return (String) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class BoxedBooleanPropSetter extends PropSetter {
        public BoxedBooleanPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "boolean", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            if (obj != null) {
                return ((Boolean) obj).booleanValue() ? Boolean.TRUE : Boolean.FALSE;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class BoxedIntPropSetter extends PropSetter {
        public BoxedIntPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "number", method);
        }

        public BoxedIntPropSetter(ReactPropGroup reactPropGroup, Method method, int r9) {
            super(reactPropGroup, "number", method, r9);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            if (obj != null) {
                if (obj instanceof Double) {
                    return Integer.valueOf(((Double) obj).intValue());
                }
                return (Integer) obj;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class BoxedColorPropSetter extends PropSetter {
        public BoxedColorPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "mixed", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            if (obj != null) {
                return ColorPropConverter.getColor(obj, context);
            }
            return null;
        }
    }

    static Map<String, String> getNativePropsForView(Class<? extends ViewManager> cls, Class<? extends ReactShadowNode> cls2) {
        HashMap hashMap = new HashMap();
        for (PropSetter propSetter : getNativePropSettersForViewManagerClass(cls).values()) {
            hashMap.put(propSetter.getPropName(), propSetter.getPropType());
        }
        for (PropSetter propSetter2 : getNativePropSettersForShadowNodeClass(cls2).values()) {
            hashMap.put(propSetter2.getPropName(), propSetter2.getPropType());
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, PropSetter> getNativePropSettersForViewManagerClass(Class<? extends ViewManager> cls) {
        if (cls == ViewManager.class) {
            return EMPTY_PROPS_MAP;
        }
        Map<Class, Map<String, PropSetter>> map = CLASS_PROPS_CACHE;
        Map<String, PropSetter> map2 = map.get(cls);
        if (map2 != null) {
            return map2;
        }
        HashMap hashMap = new HashMap(getNativePropSettersForViewManagerClass(cls.getSuperclass()));
        extractPropSettersFromViewManagerClassDefinition(cls, hashMap);
        map.put(cls, hashMap);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, PropSetter> getNativePropSettersForShadowNodeClass(Class<? extends ReactShadowNode> cls) {
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (cls2 == ReactShadowNode.class) {
                return EMPTY_PROPS_MAP;
            }
        }
        Map<Class, Map<String, PropSetter>> map = CLASS_PROPS_CACHE;
        Map<String, PropSetter> map2 = map.get(cls);
        if (map2 != null) {
            return map2;
        }
        HashMap hashMap = new HashMap(getNativePropSettersForShadowNodeClass(cls.getSuperclass()));
        extractPropSettersFromShadowNodeClassDefinition(cls, hashMap);
        map.put(cls, hashMap);
        return hashMap;
    }

    private static PropSetter createPropSetter(ReactProp reactProp, Method method, Class<?> cls) {
        if (cls == Dynamic.class) {
            return new DynamicPropSetter(reactProp, method);
        }
        if (cls == Boolean.TYPE) {
            return new BooleanPropSetter(reactProp, method, reactProp.defaultBoolean());
        }
        if (cls == Integer.TYPE) {
            if ("Color".equals(reactProp.customType())) {
                return new ColorPropSetter(reactProp, method, reactProp.defaultInt());
            }
            return new IntPropSetter(reactProp, method, reactProp.defaultInt());
        } else if (cls == Float.TYPE) {
            return new FloatPropSetter(reactProp, method, reactProp.defaultFloat());
        } else {
            if (cls == Double.TYPE) {
                return new DoublePropSetter(reactProp, method, reactProp.defaultDouble());
            }
            if (cls == String.class) {
                return new StringPropSetter(reactProp, method);
            }
            if (cls == Boolean.class) {
                return new BoxedBooleanPropSetter(reactProp, method);
            }
            if (cls == Integer.class) {
                if ("Color".equals(reactProp.customType())) {
                    return new BoxedColorPropSetter(reactProp, method);
                }
                return new BoxedIntPropSetter(reactProp, method);
            } else if (cls == ReadableArray.class) {
                return new ArrayPropSetter(reactProp, method);
            } else {
                if (cls == ReadableMap.class) {
                    return new MapPropSetter(reactProp, method);
                }
                throw new RuntimeException("Unrecognized type: " + cls + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
            }
        }
    }

    private static void createPropSetters(ReactPropGroup reactPropGroup, Method method, Class<?> cls, Map<String, PropSetter> map) {
        String[] names = reactPropGroup.names();
        int r2 = 0;
        if (cls == Dynamic.class) {
            while (r2 < names.length) {
                map.put(names[r2], new DynamicPropSetter(reactPropGroup, method, r2));
                r2++;
            }
        } else if (cls == Integer.TYPE) {
            while (r2 < names.length) {
                map.put(names[r2], new IntPropSetter(reactPropGroup, method, r2, reactPropGroup.defaultInt()));
                r2++;
            }
        } else if (cls == Float.TYPE) {
            while (r2 < names.length) {
                map.put(names[r2], new FloatPropSetter(reactPropGroup, method, r2, reactPropGroup.defaultFloat()));
                r2++;
            }
        } else if (cls == Double.TYPE) {
            while (r2 < names.length) {
                map.put(names[r2], new DoublePropSetter(reactPropGroup, method, r2, reactPropGroup.defaultDouble()));
                r2++;
            }
        } else if (cls == Integer.class) {
            while (r2 < names.length) {
                map.put(names[r2], new BoxedIntPropSetter(reactPropGroup, method, r2));
                r2++;
            }
        } else {
            throw new RuntimeException("Unrecognized type: " + cls + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
        }
    }

    private static void extractPropSettersFromViewManagerClassDefinition(Class<? extends ViewManager> cls, Map<String, PropSetter> map) {
        Method[] declaredMethods;
        for (Method method : cls.getDeclaredMethods()) {
            ReactProp reactProp = (ReactProp) method.getAnnotation(ReactProp.class);
            if (reactProp != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 2) {
                    throw new RuntimeException("Wrong number of args for prop setter: " + cls.getName() + "#" + method.getName());
                } else if (!View.class.isAssignableFrom(parameterTypes[0])) {
                    throw new RuntimeException("First param should be a view subclass to be updated: " + cls.getName() + "#" + method.getName());
                } else {
                    map.put(reactProp.name(), createPropSetter(reactProp, method, parameterTypes[1]));
                }
            }
            ReactPropGroup reactPropGroup = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (reactPropGroup != null) {
                Class<?>[] parameterTypes2 = method.getParameterTypes();
                if (parameterTypes2.length != 3) {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + cls.getName() + "#" + method.getName());
                } else if (!View.class.isAssignableFrom(parameterTypes2[0])) {
                    throw new RuntimeException("First param should be a view subclass to be updated: " + cls.getName() + "#" + method.getName());
                } else if (parameterTypes2[1] != Integer.TYPE) {
                    throw new RuntimeException("Second argument should be property index: " + cls.getName() + "#" + method.getName());
                } else {
                    createPropSetters(reactPropGroup, method, parameterTypes2[2], map);
                }
            }
        }
    }

    private static void extractPropSettersFromShadowNodeClassDefinition(Class<? extends ReactShadowNode> cls, Map<String, PropSetter> map) {
        Method[] declaredMethods;
        for (Method method : cls.getDeclaredMethods()) {
            ReactProp reactProp = (ReactProp) method.getAnnotation(ReactProp.class);
            if (reactProp != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new RuntimeException("Wrong number of args for prop setter: " + cls.getName() + "#" + method.getName());
                }
                map.put(reactProp.name(), createPropSetter(reactProp, method, parameterTypes[0]));
            }
            ReactPropGroup reactPropGroup = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (reactPropGroup != null) {
                Class<?>[] parameterTypes2 = method.getParameterTypes();
                if (parameterTypes2.length != 2) {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + cls.getName() + "#" + method.getName());
                } else if (parameterTypes2[0] != Integer.TYPE) {
                    throw new RuntimeException("Second argument should be property index: " + cls.getName() + "#" + method.getName());
                } else {
                    createPropSetters(reactPropGroup, method, parameterTypes2[1], map);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ThreadLocal<Object[]> createThreadLocalArray(final int r1) {
        if (r1 <= 0) {
            return null;
        }
        return new ThreadLocal<Object[]>() { // from class: com.facebook.react.uimanager.ViewManagersPropertyCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public Object[] initialValue() {
                return new Object[r1];
            }
        };
    }
}