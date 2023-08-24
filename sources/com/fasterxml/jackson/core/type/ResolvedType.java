package com.fasterxml.jackson.core.type;

/* loaded from: classes.dex */
public abstract class ResolvedType {
    public abstract ResolvedType containedType(int r1);

    public abstract int containedTypeCount();

    public abstract String containedTypeName(int r1);

    public abstract ResolvedType getContentType();

    public abstract ResolvedType getKeyType();

    @Deprecated
    public Class<?> getParameterSource() {
        return null;
    }

    public abstract Class<?> getRawClass();

    public abstract ResolvedType getReferencedType();

    public abstract boolean hasGenericTypes();

    public abstract boolean hasRawClass(Class<?> cls);

    public abstract boolean isAbstract();

    public abstract boolean isArrayType();

    public abstract boolean isCollectionLikeType();

    public abstract boolean isConcrete();

    public abstract boolean isContainerType();

    public abstract boolean isEnumType();

    public abstract boolean isFinal();

    public abstract boolean isInterface();

    public abstract boolean isMapLikeType();

    public abstract boolean isPrimitive();

    public abstract boolean isThrowable();

    public abstract String toCanonical();

    public boolean isReferenceType() {
        return getReferencedType() != null;
    }
}
