package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class JsonParserSequence extends JsonParserDelegate {
    protected final boolean _checkForExistingToken;
    protected boolean _hasToken;
    protected int _nextParserIndex;
    protected final JsonParser[] _parsers;

    @Deprecated
    protected JsonParserSequence(JsonParser[] jsonParserArr) {
        this(false, jsonParserArr);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected JsonParserSequence(boolean z, JsonParser[] jsonParserArr) {
        super(jsonParserArr[0]);
        boolean z2 = false;
        this._checkForExistingToken = z;
        if (z && this.delegate.hasCurrentToken()) {
            z2 = true;
        }
        this._hasToken = z2;
        this._parsers = jsonParserArr;
        this._nextParserIndex = 1;
    }

    public static JsonParserSequence createFlattened(boolean z, JsonParser jsonParser, JsonParser jsonParser2) {
        boolean z2 = jsonParser instanceof JsonParserSequence;
        if (!z2 && !(jsonParser2 instanceof JsonParserSequence)) {
            return new JsonParserSequence(z, new JsonParser[]{jsonParser, jsonParser2});
        }
        ArrayList arrayList = new ArrayList();
        if (z2) {
            ((JsonParserSequence) jsonParser).addFlattenedActiveParsers(arrayList);
        } else {
            arrayList.add(jsonParser);
        }
        if (jsonParser2 instanceof JsonParserSequence) {
            ((JsonParserSequence) jsonParser2).addFlattenedActiveParsers(arrayList);
        } else {
            arrayList.add(jsonParser2);
        }
        return new JsonParserSequence(z, (JsonParser[]) arrayList.toArray(new JsonParser[arrayList.size()]));
    }

    @Deprecated
    public static JsonParserSequence createFlattened(JsonParser jsonParser, JsonParser jsonParser2) {
        return createFlattened(false, jsonParser, jsonParser2);
    }

    protected void addFlattenedActiveParsers(List<JsonParser> list) {
        int length = this._parsers.length;
        for (int r0 = this._nextParserIndex - 1; r0 < length; r0++) {
            JsonParser jsonParser = this._parsers[r0];
            if (jsonParser instanceof JsonParserSequence) {
                ((JsonParserSequence) jsonParser).addFlattenedActiveParsers(list);
            } else {
                list.add(jsonParser);
            }
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        do {
            this.delegate.close();
        } while (switchToNext());
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() throws IOException {
        if (this.delegate == null) {
            return null;
        }
        if (this._hasToken) {
            this._hasToken = false;
            return this.delegate.currentToken();
        }
        JsonToken nextToken = this.delegate.nextToken();
        return nextToken == null ? switchAndReturnNext() : nextToken;
    }

    public int containedParsersCount() {
        return this._parsers.length;
    }

    protected boolean switchToNext() {
        int r0 = this._nextParserIndex;
        JsonParser[] jsonParserArr = this._parsers;
        if (r0 < jsonParserArr.length) {
            this._nextParserIndex = r0 + 1;
            this.delegate = jsonParserArr[r0];
            return true;
        }
        return false;
    }

    protected JsonToken switchAndReturnNext() throws IOException {
        JsonToken nextToken;
        do {
            int r0 = this._nextParserIndex;
            JsonParser[] jsonParserArr = this._parsers;
            if (r0 >= jsonParserArr.length) {
                return null;
            }
            this._nextParserIndex = r0 + 1;
            this.delegate = jsonParserArr[r0];
            if (this._checkForExistingToken && this.delegate.hasCurrentToken()) {
                return this.delegate.getCurrentToken();
            }
            nextToken = this.delegate.nextToken();
        } while (nextToken == null);
        return nextToken;
    }
}
