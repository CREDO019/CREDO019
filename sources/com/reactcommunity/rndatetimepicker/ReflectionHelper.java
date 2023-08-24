package com.reactcommunity.rndatetimepicker;

import java.lang.reflect.Field;

/* loaded from: classes3.dex */
public class ReflectionHelper {
    public static Field findField(Class cls, Class cls2, String str) {
        Field[] declaredFields;
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (NoSuchFieldException unused) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.getType() == cls2) {
                    field.setAccessible(true);
                    return field;
                }
            }
            return null;
        }
    }
}
