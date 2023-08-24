package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/* loaded from: classes2.dex */
public final class PropertyBasedCreator {
    protected final SettableBeanProperty[] _allProperties;
    protected final int _propertyCount;
    protected final HashMap<String, SettableBeanProperty> _propertyLookup;
    protected final ValueInstantiator _valueInstantiator;

    protected PropertyBasedCreator(ValueInstantiator valueInstantiator, SettableBeanProperty[] settableBeanPropertyArr, boolean z) {
        this._valueInstantiator = valueInstantiator;
        if (z) {
            this._propertyLookup = new CaseInsensitiveMap();
        } else {
            this._propertyLookup = new HashMap<>();
        }
        int length = settableBeanPropertyArr.length;
        this._propertyCount = length;
        this._allProperties = new SettableBeanProperty[length];
        for (int r6 = 0; r6 < length; r6++) {
            SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[r6];
            this._allProperties[r6] = settableBeanProperty;
            this._propertyLookup.put(settableBeanProperty.getName(), settableBeanProperty);
        }
    }

    public static PropertyBasedCreator construct(DeserializationContext deserializationContext, ValueInstantiator valueInstantiator, SettableBeanProperty[] settableBeanPropertyArr) throws JsonMappingException {
        int length = settableBeanPropertyArr.length;
        SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[length];
        for (int r2 = 0; r2 < length; r2++) {
            SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[r2];
            if (!settableBeanProperty.hasValueDeserializer()) {
                settableBeanProperty = settableBeanProperty.withValueDeserializer(deserializationContext.findContextualValueDeserializer(settableBeanProperty.getType(), settableBeanProperty));
            }
            settableBeanPropertyArr2[r2] = settableBeanProperty;
        }
        return new PropertyBasedCreator(valueInstantiator, settableBeanPropertyArr2, deserializationContext.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
    }

    public Collection<SettableBeanProperty> properties() {
        return this._propertyLookup.values();
    }

    public SettableBeanProperty findCreatorProperty(String str) {
        return this._propertyLookup.get(str);
    }

    public SettableBeanProperty findCreatorProperty(int r4) {
        for (SettableBeanProperty settableBeanProperty : this._propertyLookup.values()) {
            if (settableBeanProperty.getPropertyIndex() == r4) {
                return settableBeanProperty;
            }
        }
        return null;
    }

    public PropertyValueBuffer startBuilding(JsonParser jsonParser, DeserializationContext deserializationContext, ObjectIdReader objectIdReader) {
        return new PropertyValueBuffer(jsonParser, deserializationContext, this._propertyCount, objectIdReader);
    }

    public Object build(DeserializationContext deserializationContext, PropertyValueBuffer propertyValueBuffer) throws IOException {
        Object createFromObjectWith = this._valueInstantiator.createFromObjectWith(deserializationContext, this._allProperties, propertyValueBuffer);
        if (createFromObjectWith != null) {
            createFromObjectWith = propertyValueBuffer.handleIdValue(deserializationContext, createFromObjectWith);
            for (PropertyValue buffered = propertyValueBuffer.buffered(); buffered != null; buffered = buffered.next) {
                buffered.assign(createFromObjectWith);
            }
        }
        return createFromObjectWith;
    }

    /* loaded from: classes2.dex */
    static class CaseInsensitiveMap extends HashMap<String, SettableBeanProperty> {
        private static final long serialVersionUID = 1;

        CaseInsensitiveMap() {
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public SettableBeanProperty get(Object obj) {
            return (SettableBeanProperty) super.get((Object) ((String) obj).toLowerCase());
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public SettableBeanProperty put(String str, SettableBeanProperty settableBeanProperty) {
            return (SettableBeanProperty) super.put((CaseInsensitiveMap) str.toLowerCase(), (String) settableBeanProperty);
        }
    }
}
