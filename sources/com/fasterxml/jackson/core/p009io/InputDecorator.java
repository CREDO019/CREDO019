package com.fasterxml.jackson.core.p009io;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;

/* renamed from: com.fasterxml.jackson.core.io.InputDecorator */
/* loaded from: classes.dex */
public abstract class InputDecorator implements Serializable {
    private static final long serialVersionUID = 1;

    public abstract InputStream decorate(IOContext iOContext, InputStream inputStream) throws IOException;

    public abstract InputStream decorate(IOContext iOContext, byte[] bArr, int r3, int r4) throws IOException;

    public abstract Reader decorate(IOContext iOContext, Reader reader) throws IOException;

    public DataInput decorate(IOContext iOContext, DataInput dataInput) throws IOException {
        throw new UnsupportedOperationException();
    }
}