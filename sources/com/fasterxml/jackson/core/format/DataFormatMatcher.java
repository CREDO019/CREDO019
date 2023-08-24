package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.p009io.MergedStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class DataFormatMatcher {
    protected final byte[] _bufferedData;
    protected final int _bufferedLength;
    protected final int _bufferedStart;
    protected final JsonFactory _match;
    protected final MatchStrength _matchStrength;
    protected final InputStream _originalStream;

    /* JADX INFO: Access modifiers changed from: protected */
    public DataFormatMatcher(InputStream inputStream, byte[] bArr, int r3, int r4, JsonFactory jsonFactory, MatchStrength matchStrength) {
        this._originalStream = inputStream;
        this._bufferedData = bArr;
        this._bufferedStart = r3;
        this._bufferedLength = r4;
        this._match = jsonFactory;
        this._matchStrength = matchStrength;
    }

    public boolean hasMatch() {
        return this._match != null;
    }

    public MatchStrength getMatchStrength() {
        MatchStrength matchStrength = this._matchStrength;
        return matchStrength == null ? MatchStrength.INCONCLUSIVE : matchStrength;
    }

    public JsonFactory getMatch() {
        return this._match;
    }

    public String getMatchedFormatName() {
        return this._match.getFormatName();
    }

    public JsonParser createParserWithMatch() throws IOException {
        JsonFactory jsonFactory = this._match;
        if (jsonFactory == null) {
            return null;
        }
        if (this._originalStream == null) {
            return jsonFactory.createParser(this._bufferedData, this._bufferedStart, this._bufferedLength);
        }
        return jsonFactory.createParser(getDataStream());
    }

    public InputStream getDataStream() {
        if (this._originalStream == null) {
            return new ByteArrayInputStream(this._bufferedData, this._bufferedStart, this._bufferedLength);
        }
        return new MergedStream(null, this._originalStream, this._bufferedData, this._bufferedStart, this._bufferedLength);
    }
}
