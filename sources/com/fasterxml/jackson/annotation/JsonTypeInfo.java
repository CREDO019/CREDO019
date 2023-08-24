package com.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface JsonTypeInfo {

    /* renamed from: com.fasterxml.jackson.annotation.JsonTypeInfo$As */
    /* loaded from: classes.dex */
    public enum EnumC1748As {
        PROPERTY,
        WRAPPER_OBJECT,
        WRAPPER_ARRAY,
        EXTERNAL_PROPERTY,
        EXISTING_PROPERTY
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static abstract class None {
    }

    Class<?> defaultImpl() default JsonTypeInfo.class;

    EnumC1748As include() default EnumC1748As.PROPERTY;

    String property() default "";

    EnumC1749Id use();

    boolean visible() default false;

    /* renamed from: com.fasterxml.jackson.annotation.JsonTypeInfo$Id */
    /* loaded from: classes.dex */
    public enum EnumC1749Id {
        NONE(null),
        CLASS("@class"),
        MINIMAL_CLASS("@c"),
        NAME("@type"),
        CUSTOM(null);
        
        private final String _defaultPropertyName;

        EnumC1749Id(String str) {
            this._defaultPropertyName = str;
        }

        public String getDefaultPropertyName() {
            return this._defaultPropertyName;
        }
    }
}
