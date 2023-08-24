package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

/* loaded from: classes2.dex */
public class BeanUtil {
    public static String okNameForGetter(AnnotatedMethod annotatedMethod, boolean z) {
        String name = annotatedMethod.getName();
        String okNameForIsGetter = okNameForIsGetter(annotatedMethod, name, z);
        return okNameForIsGetter == null ? okNameForRegularGetter(annotatedMethod, name, z) : okNameForIsGetter;
    }

    public static String okNameForRegularGetter(AnnotatedMethod annotatedMethod, String str, boolean z) {
        if (str.startsWith("get")) {
            if ("getCallbacks".equals(str)) {
                if (isCglibGetCallbacks(annotatedMethod)) {
                    return null;
                }
            } else if ("getMetaClass".equals(str) && isGroovyMetaClassGetter(annotatedMethod)) {
                return null;
            }
            return z ? stdManglePropertyName(str, 3) : legacyManglePropertyName(str, 3);
        }
        return null;
    }

    public static String okNameForIsGetter(AnnotatedMethod annotatedMethod, String str, boolean z) {
        if (str.startsWith("is")) {
            Class<?> rawType = annotatedMethod.getRawType();
            if (rawType == Boolean.class || rawType == Boolean.TYPE) {
                return z ? stdManglePropertyName(str, 2) : legacyManglePropertyName(str, 2);
            }
            return null;
        }
        return null;
    }

    public static String okNameForSetter(AnnotatedMethod annotatedMethod, boolean z) {
        String okNameForMutator = okNameForMutator(annotatedMethod, "set", z);
        if (okNameForMutator != null) {
            if ("metaClass".equals(okNameForMutator) && isGroovyMetaClassSetter(annotatedMethod)) {
                return null;
            }
            return okNameForMutator;
        }
        return null;
    }

    public static String okNameForMutator(AnnotatedMethod annotatedMethod, String str, boolean z) {
        String name = annotatedMethod.getName();
        if (name.startsWith(str)) {
            int length = str.length();
            return z ? stdManglePropertyName(name, length) : legacyManglePropertyName(name, length);
        }
        return null;
    }

    @Deprecated
    public static String okNameForGetter(AnnotatedMethod annotatedMethod) {
        return okNameForGetter(annotatedMethod, false);
    }

    @Deprecated
    public static String okNameForRegularGetter(AnnotatedMethod annotatedMethod, String str) {
        return okNameForRegularGetter(annotatedMethod, str, false);
    }

    @Deprecated
    public static String okNameForIsGetter(AnnotatedMethod annotatedMethod, String str) {
        return okNameForIsGetter(annotatedMethod, str, false);
    }

    @Deprecated
    public static String okNameForSetter(AnnotatedMethod annotatedMethod) {
        return okNameForSetter(annotatedMethod, false);
    }

    @Deprecated
    public static String okNameForMutator(AnnotatedMethod annotatedMethod, String str) {
        return okNameForMutator(annotatedMethod, str, false);
    }

    protected static boolean isCglibGetCallbacks(AnnotatedMethod annotatedMethod) {
        String packageName;
        Class<?> rawType = annotatedMethod.getRawType();
        return rawType != null && rawType.isArray() && (packageName = ClassUtil.getPackageName(rawType.getComponentType())) != null && packageName.contains(".cglib") && (packageName.startsWith("net.sf.cglib") || packageName.startsWith("org.hibernate.repackage.cglib") || packageName.startsWith("org.springframework.cglib"));
    }

    protected static boolean isGroovyMetaClassSetter(AnnotatedMethod annotatedMethod) {
        String packageName = ClassUtil.getPackageName(annotatedMethod.getRawParameterType(0));
        return packageName != null && packageName.startsWith("groovy.lang");
    }

    protected static boolean isGroovyMetaClassGetter(AnnotatedMethod annotatedMethod) {
        String packageName;
        Class<?> rawType = annotatedMethod.getRawType();
        return (rawType == null || rawType.isArray() || (packageName = ClassUtil.getPackageName(rawType)) == null || !packageName.startsWith("groovy.lang")) ? false : true;
    }

    protected static String legacyManglePropertyName(String str, int r5) {
        int length = str.length();
        if (length == r5) {
            return null;
        }
        char charAt = str.charAt(r5);
        char lowerCase = Character.toLowerCase(charAt);
        if (charAt == lowerCase) {
            return str.substring(r5);
        }
        StringBuilder sb = new StringBuilder(length - r5);
        sb.append(lowerCase);
        while (true) {
            r5++;
            if (r5 >= length) {
                break;
            }
            char charAt2 = str.charAt(r5);
            char lowerCase2 = Character.toLowerCase(charAt2);
            if (charAt2 == lowerCase2) {
                sb.append((CharSequence) str, r5, length);
                break;
            }
            sb.append(lowerCase2);
        }
        return sb.toString();
    }

    protected static String stdManglePropertyName(String str, int r5) {
        int length = str.length();
        if (length == r5) {
            return null;
        }
        char charAt = str.charAt(r5);
        char lowerCase = Character.toLowerCase(charAt);
        if (charAt == lowerCase) {
            return str.substring(r5);
        }
        int r1 = r5 + 1;
        if (r1 < length && Character.isUpperCase(str.charAt(r1))) {
            return str.substring(r5);
        }
        StringBuilder sb = new StringBuilder(length - r5);
        sb.append(lowerCase);
        sb.append((CharSequence) str, r1, length);
        return sb.toString();
    }
}
