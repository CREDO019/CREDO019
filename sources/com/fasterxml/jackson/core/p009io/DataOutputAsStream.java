package com.fasterxml.jackson.core.p009io;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.fasterxml.jackson.core.io.DataOutputAsStream */
/* loaded from: classes.dex */
public class DataOutputAsStream extends OutputStream {
    protected final DataOutput _output;

    public DataOutputAsStream(DataOutput dataOutput) {
        this._output = dataOutput;
    }

    @Override // java.io.OutputStream
    public void write(int r2) throws IOException {
        this._output.write(r2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this._output.write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int r3, int r4) throws IOException {
        this._output.write(bArr, r3, r4);
    }
}
