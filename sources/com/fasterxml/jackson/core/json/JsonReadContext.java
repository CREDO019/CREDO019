package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.p009io.CharTypes;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public final class JsonReadContext extends JsonStreamContext {
    protected JsonReadContext _child;
    protected int _columnNr;
    protected String _currentName;
    protected Object _currentValue;
    protected DupDetector _dups;
    protected int _lineNr;
    protected final JsonReadContext _parent;

    public JsonReadContext(JsonReadContext jsonReadContext, DupDetector dupDetector, int r3, int r4, int r5) {
        this._parent = jsonReadContext;
        this._dups = dupDetector;
        this._type = r3;
        this._lineNr = r4;
        this._columnNr = r5;
        this._index = -1;
    }

    protected void reset(int r1, int r2, int r3) {
        this._type = r1;
        this._index = -1;
        this._lineNr = r2;
        this._columnNr = r3;
        this._currentName = null;
        this._currentValue = null;
        DupDetector dupDetector = this._dups;
        if (dupDetector != null) {
            dupDetector.reset();
        }
    }

    public JsonReadContext withDupDetector(DupDetector dupDetector) {
        this._dups = dupDetector;
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public Object getCurrentValue() {
        return this._currentValue;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public void setCurrentValue(Object obj) {
        this._currentValue = obj;
    }

    public static JsonReadContext createRootContext(int r7, int r8, DupDetector dupDetector) {
        return new JsonReadContext(null, dupDetector, 0, r7, r8);
    }

    public static JsonReadContext createRootContext(DupDetector dupDetector) {
        return new JsonReadContext(null, dupDetector, 0, 1, 0);
    }

    public JsonReadContext createChildArrayContext(int r8, int r9) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            DupDetector dupDetector = this._dups;
            jsonReadContext = new JsonReadContext(this, dupDetector == null ? null : dupDetector.child(), 1, r8, r9);
            this._child = jsonReadContext;
        } else {
            jsonReadContext.reset(1, r8, r9);
        }
        return jsonReadContext;
    }

    public JsonReadContext createChildObjectContext(int r8, int r9) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            DupDetector dupDetector = this._dups;
            JsonReadContext jsonReadContext2 = new JsonReadContext(this, dupDetector == null ? null : dupDetector.child(), 2, r8, r9);
            this._child = jsonReadContext2;
            return jsonReadContext2;
        }
        jsonReadContext.reset(2, r8, r9);
        return jsonReadContext;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public String getCurrentName() {
        return this._currentName;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public JsonReadContext getParent() {
        return this._parent;
    }

    public JsonReadContext clearAndGetParent() {
        this._currentValue = null;
        return this._parent;
    }

    public JsonLocation getStartLocation(Object obj) {
        return new JsonLocation(obj, -1L, this._lineNr, this._columnNr);
    }

    public DupDetector getDupDetector() {
        return this._dups;
    }

    public boolean expectComma() {
        int r0 = this._index + 1;
        this._index = r0;
        return this._type != 0 && r0 > 0;
    }

    public void setCurrentName(String str) throws JsonProcessingException {
        this._currentName = str;
        DupDetector dupDetector = this._dups;
        if (dupDetector != null) {
            _checkDup(dupDetector, str);
        }
    }

    private void _checkDup(DupDetector dupDetector, String str) throws JsonProcessingException {
        if (dupDetector.isDup(str)) {
            Object source = dupDetector.getSource();
            JsonParser jsonParser = source instanceof JsonParser ? (JsonParser) source : null;
            throw new JsonParseException(jsonParser, "Duplicate field '" + str + "'");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        int r1 = this._type;
        if (r1 == 0) {
            sb.append("/");
        } else if (r1 == 1) {
            sb.append('[');
            sb.append(getCurrentIndex());
            sb.append(']');
        } else {
            sb.append('{');
            if (this._currentName != null) {
                sb.append(Typography.quote);
                CharTypes.appendQuoted(sb, this._currentName);
                sb.append(Typography.quote);
            } else {
                sb.append('?');
            }
            sb.append('}');
        }
        return sb.toString();
    }
}
