package expo.modules.core;

import android.content.Context;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class ExportedModule implements RegistryLifecycleListener {
    private Context mContext;
    private Map<String, MethodInfo> mExportedMethodInfos;
    private Map<String, Method> mExportedMethods;

    public abstract String getName();

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onCreate(ModuleRegistry moduleRegistry) {
        RegistryLifecycleListener.CC.$default$onCreate(this, moduleRegistry);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    /* loaded from: classes4.dex */
    public class MethodInfo {
        private Class<?>[] mParameterTypes;

        MethodInfo(Method method) {
            this.mParameterTypes = method.getParameterTypes();
        }

        public Class<?>[] getParameterTypes() {
            return this.mParameterTypes;
        }
    }

    public ExportedModule(Context context) {
        this.mContext = context;
    }

    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(Collections.emptyMap());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    public Map<String, MethodInfo> getExportedMethodInfos() {
        Map<String, MethodInfo> map = this.mExportedMethodInfos;
        if (map != null) {
            return map;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Method> entry : getExportedMethods().entrySet()) {
            hashMap.put(entry.getKey(), new MethodInfo(entry.getValue()));
        }
        this.mExportedMethodInfos = hashMap;
        return hashMap;
    }

    public Object invokeExportedMethod(String str, Collection<Object> collection) throws NoSuchMethodException, RuntimeException {
        Method method = this.mExportedMethods.get(str);
        if (method == null) {
            throw new NoSuchMethodException("Module " + getName() + "does not export method " + str + ".");
        }
        int length = method.getParameterTypes().length;
        if (collection.size() != length) {
            throw new IllegalArgumentException("Method " + str + " on class " + getName() + " expects " + length + " arguments, whereas " + collection.size() + " arguments have been provided.");
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        Iterator<Object> it = collection.iterator();
        ArrayList arrayList = new ArrayList(collection.size());
        for (int r12 = 0; r12 < length; r12++) {
            arrayList.add(transformArgumentToClass(it.next(), parameterTypes[r12]));
        }
        try {
            return method.invoke(this, arrayList.toArray());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception occurred while executing exported method " + str + " on module " + getName() + ": " + e.getMessage(), e);
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            throw new RuntimeException("Exception occurred while executing exported method " + str + " on module " + getName() + ": " + e2.getCause().getMessage(), e2.getCause());
        }
    }

    protected Object transformArgumentToClass(Object obj, Class<?> cls) {
        return ArgumentsHelper.validatedArgumentForClass(obj, cls);
    }

    public Map<String, Method> getExportedMethods() {
        Map<String, Method> map = this.mExportedMethods;
        if (map != null) {
            return map;
        }
        this.mExportedMethods = new HashMap();
        for (Class<?> cls = getClass(); cls != null && ExportedModule.class.isAssignableFrom(cls); cls = cls.getSuperclass()) {
            for (Map.Entry<String, Method> entry : getExportedMethods(cls).entrySet()) {
                if (!this.mExportedMethods.containsKey(entry.getKey())) {
                    this.mExportedMethods.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return this.mExportedMethods;
    }

    protected Map<String, Method> getExportedMethods(Class cls) {
        Method[] declaredMethods;
        HashMap hashMap = new HashMap();
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getAnnotation(ExpoMethod.class) != null) {
                String name = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length < 1) {
                    throw new IllegalArgumentException("Method " + name + " of Java Module " + getName() + " does not define any arguments - minimum argument set is a Promise");
                } else if (parameterTypes[parameterTypes.length - 1] != Promise.class) {
                    throw new IllegalArgumentException("Last argument of method " + name + " of Java Module " + getName() + " does not expect a Promise");
                } else if (hashMap.containsKey(name)) {
                    throw new IllegalArgumentException("Java Module " + getName() + " method name already registered: " + name + ".");
                } else {
                    hashMap.put(name, method);
                }
            }
        }
        return hashMap;
    }
}
