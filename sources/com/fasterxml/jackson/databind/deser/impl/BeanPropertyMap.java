package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
public class BeanPropertyMap implements Iterable<SettableBeanProperty>, Serializable {
    private static final long serialVersionUID = 2;
    protected final boolean _caseInsensitive;
    private Object[] _hashArea;
    private int _hashMask;
    private SettableBeanProperty[] _propsInOrder;
    private int _size;
    private int _spillCount;

    private static final int findSize(int r1) {
        if (r1 <= 5) {
            return 8;
        }
        if (r1 <= 12) {
            return 16;
        }
        int r0 = 32;
        while (r0 < r1 + (r1 >> 2)) {
            r0 += r0;
        }
        return r0;
    }

    public BeanPropertyMap(boolean z, Collection<SettableBeanProperty> collection) {
        this._caseInsensitive = z;
        this._propsInOrder = (SettableBeanProperty[]) collection.toArray(new SettableBeanProperty[collection.size()]);
        init(collection);
    }

    protected BeanPropertyMap(BeanPropertyMap beanPropertyMap, boolean z) {
        this._caseInsensitive = z;
        SettableBeanProperty[] settableBeanPropertyArr = beanPropertyMap._propsInOrder;
        SettableBeanProperty[] settableBeanPropertyArr2 = (SettableBeanProperty[]) Arrays.copyOf(settableBeanPropertyArr, settableBeanPropertyArr.length);
        this._propsInOrder = settableBeanPropertyArr2;
        init(Arrays.asList(settableBeanPropertyArr2));
    }

    public BeanPropertyMap withCaseInsensitivity(boolean z) {
        return this._caseInsensitive == z ? this : new BeanPropertyMap(this, z);
    }

    protected void init(Collection<SettableBeanProperty> collection) {
        int size = collection.size();
        this._size = size;
        int findSize = findSize(size);
        this._hashMask = findSize - 1;
        int r1 = (findSize >> 1) + findSize;
        Object[] objArr = new Object[r1 * 2];
        int r3 = 0;
        for (SettableBeanProperty settableBeanProperty : collection) {
            if (settableBeanProperty != null) {
                String propertyName = getPropertyName(settableBeanProperty);
                int _hashCode = _hashCode(propertyName);
                int r7 = _hashCode << 1;
                if (objArr[r7] != null) {
                    r7 = ((_hashCode >> 1) + findSize) << 1;
                    if (objArr[r7] != null) {
                        r7 = (r1 << 1) + r3;
                        r3 += 2;
                        if (r7 >= objArr.length) {
                            objArr = Arrays.copyOf(objArr, objArr.length + 4);
                        }
                    }
                }
                objArr[r7] = propertyName;
                objArr[r7 + 1] = settableBeanProperty;
            }
        }
        this._hashArea = objArr;
        this._spillCount = r3;
    }

    public static BeanPropertyMap construct(Collection<SettableBeanProperty> collection, boolean z) {
        return new BeanPropertyMap(z, collection);
    }

    public BeanPropertyMap withProperty(SettableBeanProperty settableBeanProperty) {
        String propertyName = getPropertyName(settableBeanProperty);
        int length = this._hashArea.length;
        for (int r3 = 1; r3 < length; r3 += 2) {
            SettableBeanProperty settableBeanProperty2 = (SettableBeanProperty) this._hashArea[r3];
            if (settableBeanProperty2 != null && settableBeanProperty2.getName().equals(propertyName)) {
                this._hashArea[r3] = settableBeanProperty;
                this._propsInOrder[_findFromOrdered(settableBeanProperty2)] = settableBeanProperty;
                return this;
            }
        }
        int _hashCode = _hashCode(propertyName);
        int r32 = this._hashMask + 1;
        int r4 = _hashCode << 1;
        Object[] objArr = this._hashArea;
        if (objArr[r4] != null) {
            r4 = ((_hashCode >> 1) + r32) << 1;
            if (objArr[r4] != null) {
                int r1 = (r32 + (r32 >> 1)) << 1;
                int r33 = this._spillCount;
                r4 = r1 + r33;
                this._spillCount = r33 + 2;
                if (r4 >= objArr.length) {
                    this._hashArea = Arrays.copyOf(objArr, objArr.length + 4);
                }
            }
        }
        Object[] objArr2 = this._hashArea;
        objArr2[r4] = propertyName;
        objArr2[r4 + 1] = settableBeanProperty;
        SettableBeanProperty[] settableBeanPropertyArr = this._propsInOrder;
        int length2 = settableBeanPropertyArr.length;
        SettableBeanProperty[] settableBeanPropertyArr2 = (SettableBeanProperty[]) Arrays.copyOf(settableBeanPropertyArr, length2 + 1);
        this._propsInOrder = settableBeanPropertyArr2;
        settableBeanPropertyArr2[length2] = settableBeanProperty;
        return this;
    }

    public BeanPropertyMap assignIndexes() {
        int length = this._hashArea.length;
        int r2 = 0;
        for (int r1 = 1; r1 < length; r1 += 2) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) this._hashArea[r1];
            if (settableBeanProperty != null) {
                settableBeanProperty.assignIndex(r2);
                r2++;
            }
        }
        return this;
    }

    public BeanPropertyMap renameAll(NameTransformer nameTransformer) {
        if (nameTransformer == null || nameTransformer == NameTransformer.NOP) {
            return this;
        }
        int length = this._propsInOrder.length;
        ArrayList arrayList = new ArrayList(length);
        for (int r2 = 0; r2 < length; r2++) {
            SettableBeanProperty settableBeanProperty = this._propsInOrder[r2];
            if (settableBeanProperty == null) {
                arrayList.add(settableBeanProperty);
            } else {
                arrayList.add(_rename(settableBeanProperty, nameTransformer));
            }
        }
        return new BeanPropertyMap(this._caseInsensitive, arrayList);
    }

    public BeanPropertyMap withoutProperties(Collection<String> collection) {
        if (collection.isEmpty()) {
            return this;
        }
        int length = this._propsInOrder.length;
        ArrayList arrayList = new ArrayList(length);
        for (int r2 = 0; r2 < length; r2++) {
            SettableBeanProperty settableBeanProperty = this._propsInOrder[r2];
            if (settableBeanProperty != null && !collection.contains(settableBeanProperty.getName())) {
                arrayList.add(settableBeanProperty);
            }
        }
        return new BeanPropertyMap(this._caseInsensitive, arrayList);
    }

    public void replace(SettableBeanProperty settableBeanProperty) {
        String propertyName = getPropertyName(settableBeanProperty);
        int _findIndexInHash = _findIndexInHash(propertyName);
        if (_findIndexInHash >= 0) {
            Object[] objArr = this._hashArea;
            objArr[_findIndexInHash] = settableBeanProperty;
            this._propsInOrder[_findFromOrdered((SettableBeanProperty) objArr[_findIndexInHash])] = settableBeanProperty;
            return;
        }
        throw new NoSuchElementException("No entry '" + propertyName + "' found, can't replace");
    }

    private List<SettableBeanProperty> properties() {
        ArrayList arrayList = new ArrayList(this._size);
        int length = this._hashArea.length;
        for (int r2 = 1; r2 < length; r2 += 2) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) this._hashArea[r2];
            if (settableBeanProperty != null) {
                arrayList.add(settableBeanProperty);
            }
        }
        return arrayList;
    }

    @Override // java.lang.Iterable
    public Iterator<SettableBeanProperty> iterator() {
        return properties().iterator();
    }

    public SettableBeanProperty[] getPropertiesInInsertionOrder() {
        return this._propsInOrder;
    }

    protected final String getPropertyName(SettableBeanProperty settableBeanProperty) {
        boolean z = this._caseInsensitive;
        String name = settableBeanProperty.getName();
        return z ? name.toLowerCase() : name;
    }

    public SettableBeanProperty find(int r5) {
        int length = this._hashArea.length;
        for (int r1 = 1; r1 < length; r1 += 2) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) this._hashArea[r1];
            if (settableBeanProperty != null && r5 == settableBeanProperty.getPropertyIndex()) {
                return settableBeanProperty;
            }
        }
        return null;
    }

    public SettableBeanProperty find(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Can not pass null property name");
        }
        if (this._caseInsensitive) {
            str = str.toLowerCase();
        }
        int hashCode = str.hashCode() & this._hashMask;
        int r1 = hashCode << 1;
        Object obj = this._hashArea[r1];
        if (obj == str || str.equals(obj)) {
            return (SettableBeanProperty) this._hashArea[r1 + 1];
        }
        return _find2(str, hashCode, obj);
    }

    private final SettableBeanProperty _find2(String str, int r5, Object obj) {
        if (obj == null) {
            return null;
        }
        int r6 = this._hashMask + 1;
        int r52 = ((r5 >> 1) + r6) << 1;
        Object obj2 = this._hashArea[r52];
        if (str.equals(obj2)) {
            return (SettableBeanProperty) this._hashArea[r52 + 1];
        }
        if (obj2 != null) {
            int r53 = (r6 + (r6 >> 1)) << 1;
            int r62 = this._spillCount + r53;
            while (r53 < r62) {
                Object obj3 = this._hashArea[r53];
                if (obj3 == str || str.equals(obj3)) {
                    return (SettableBeanProperty) this._hashArea[r53 + 1];
                }
                r53 += 2;
            }
        }
        return null;
    }

    public int size() {
        return this._size;
    }

    public void remove(SettableBeanProperty settableBeanProperty) {
        ArrayList arrayList = new ArrayList(this._size);
        String propertyName = getPropertyName(settableBeanProperty);
        int length = this._hashArea.length;
        boolean z = false;
        for (int r3 = 1; r3 < length; r3 += 2) {
            Object[] objArr = this._hashArea;
            SettableBeanProperty settableBeanProperty2 = (SettableBeanProperty) objArr[r3];
            if (settableBeanProperty2 != null) {
                if (!z && (z = propertyName.equals(objArr[r3 - 1]))) {
                    this._propsInOrder[_findFromOrdered(settableBeanProperty2)] = null;
                } else {
                    arrayList.add(settableBeanProperty2);
                }
            }
        }
        if (!z) {
            throw new NoSuchElementException("No entry '" + settableBeanProperty.getName() + "' found, can't remove");
        }
        init(arrayList);
    }

    public boolean findDeserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        SettableBeanProperty find = find(str);
        if (find == null) {
            return false;
        }
        try {
            find.deserializeAndSet(jsonParser, deserializationContext, obj);
            return true;
        } catch (Exception e) {
            wrapAndThrow(e, obj, str, deserializationContext);
            return true;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties=[");
        Iterator<SettableBeanProperty> it = iterator();
        int r2 = 0;
        while (it.hasNext()) {
            SettableBeanProperty next = it.next();
            int r4 = r2 + 1;
            if (r2 > 0) {
                sb.append(", ");
            }
            sb.append(next.getName());
            sb.append('(');
            sb.append(next.getType());
            sb.append(')');
            r2 = r4;
        }
        sb.append(']');
        return sb.toString();
    }

    protected SettableBeanProperty _rename(SettableBeanProperty settableBeanProperty, NameTransformer nameTransformer) {
        JsonDeserializer<Object> unwrappingDeserializer;
        if (settableBeanProperty == null) {
            return settableBeanProperty;
        }
        SettableBeanProperty withSimpleName = settableBeanProperty.withSimpleName(nameTransformer.transform(settableBeanProperty.getName()));
        JsonDeserializer<Object> valueDeserializer = withSimpleName.getValueDeserializer();
        return (valueDeserializer == null || (unwrappingDeserializer = valueDeserializer.unwrappingDeserializer(nameTransformer)) == valueDeserializer) ? withSimpleName : withSimpleName.withValueDeserializer(unwrappingDeserializer);
    }

    protected void wrapAndThrow(Throwable th, Object obj, String str, DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        boolean z = deserializationContext == null || deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (th instanceof IOException) {
            if (!z || !(th instanceof JsonProcessingException)) {
                throw ((IOException) th);
            }
        } else if (!z && (th instanceof RuntimeException)) {
            throw ((RuntimeException) th);
        }
        throw JsonMappingException.wrapWithPath(th, obj, str);
    }

    private final int _findIndexInHash(String str) {
        int _hashCode = _hashCode(str);
        int r1 = _hashCode << 1;
        if (str.equals(this._hashArea[r1])) {
            return r1 + 1;
        }
        int r12 = this._hashMask + 1;
        int r0 = ((_hashCode >> 1) + r12) << 1;
        if (str.equals(this._hashArea[r0])) {
            return r0 + 1;
        }
        int r02 = (r12 + (r12 >> 1)) << 1;
        int r13 = this._spillCount + r02;
        while (r02 < r13) {
            if (str.equals(this._hashArea[r02])) {
                return r02 + 1;
            }
            r02 += 2;
        }
        return -1;
    }

    private final int _findFromOrdered(SettableBeanProperty settableBeanProperty) {
        int length = this._propsInOrder.length;
        for (int r1 = 0; r1 < length; r1++) {
            if (this._propsInOrder[r1] == settableBeanProperty) {
                return r1;
            }
        }
        throw new IllegalStateException("Illegal state: property '" + settableBeanProperty.getName() + "' missing from _propsInOrder");
    }

    private final int _hashCode(String str) {
        return str.hashCode() & this._hashMask;
    }
}
