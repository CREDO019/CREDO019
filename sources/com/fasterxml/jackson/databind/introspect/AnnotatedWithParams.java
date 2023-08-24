package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public abstract class AnnotatedWithParams extends AnnotatedMember {
    private static final long serialVersionUID = 1;
    protected final AnnotationMap[] _paramAnnotations;

    public abstract Object call() throws Exception;

    public abstract Object call(Object[] objArr) throws Exception;

    public abstract Object call1(Object obj) throws Exception;

    @Deprecated
    public abstract Type getGenericParameterType(int r1);

    public abstract int getParameterCount();

    public abstract JavaType getParameterType(int r1);

    public abstract Class<?> getRawParameterType(int r1);

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotatedWithParams(TypeResolutionContext typeResolutionContext, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(typeResolutionContext, annotationMap);
        this._paramAnnotations = annotationMapArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotatedWithParams(AnnotatedWithParams annotatedWithParams, AnnotationMap[] annotationMapArr) {
        super(annotatedWithParams);
        this._paramAnnotations = annotationMapArr;
    }

    public final void addOrOverrideParam(int r3, Annotation annotation) {
        AnnotationMap annotationMap = this._paramAnnotations[r3];
        if (annotationMap == null) {
            annotationMap = new AnnotationMap();
            this._paramAnnotations[r3] = annotationMap;
        }
        annotationMap.add(annotation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotatedParameter replaceParameterAnnotations(int r2, AnnotationMap annotationMap) {
        this._paramAnnotations[r2] = annotationMap;
        return getParameter(r2);
    }

    public final AnnotationMap getParameterAnnotations(int r3) {
        AnnotationMap[] annotationMapArr = this._paramAnnotations;
        if (annotationMapArr == null || r3 < 0 || r3 >= annotationMapArr.length) {
            return null;
        }
        return annotationMapArr[r3];
    }

    public final AnnotatedParameter getParameter(int r4) {
        return new AnnotatedParameter(this, getParameterType(r4), getParameterAnnotations(r4), r4);
    }

    public final int getAnnotationCount() {
        return this._annotations.size();
    }
}
