package com.fasterxml.jackson.core;

import expo.modules.updates.codesigning.CodeSigningAlgorithmKt;

/* loaded from: classes.dex */
public abstract class JsonStreamContext {
    protected static final int TYPE_ARRAY = 1;
    protected static final int TYPE_OBJECT = 2;
    protected static final int TYPE_ROOT = 0;
    protected int _index;
    protected int _type;

    public abstract String getCurrentName();

    public Object getCurrentValue() {
        return null;
    }

    public abstract JsonStreamContext getParent();

    public void setCurrentValue(Object obj) {
    }

    public final boolean inArray() {
        return this._type == 1;
    }

    public final boolean inRoot() {
        return this._type == 0;
    }

    public final boolean inObject() {
        return this._type == 2;
    }

    @Deprecated
    public final String getTypeDesc() {
        int r0 = this._type;
        return r0 != 0 ? r0 != 1 ? r0 != 2 ? "?" : "OBJECT" : "ARRAY" : "ROOT";
    }

    public String typeDesc() {
        int r0 = this._type;
        return r0 != 0 ? r0 != 1 ? r0 != 2 ? "?" : "Object" : "Array" : CodeSigningAlgorithmKt.CODE_SIGNING_METADATA_DEFAULT_KEY_ID;
    }

    public final int getEntryCount() {
        return this._index + 1;
    }

    public final int getCurrentIndex() {
        int r0 = this._index;
        if (r0 < 0) {
            return 0;
        }
        return r0;
    }
}
