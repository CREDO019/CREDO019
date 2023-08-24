package org.apache.commons.p028io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.Appendable;

/* renamed from: org.apache.commons.io.output.AppendableOutputStream */
/* loaded from: classes5.dex */
public class AppendableOutputStream<T extends Appendable> extends OutputStream {
    private final T appendable;

    public AppendableOutputStream(T t) {
        this.appendable = t;
    }

    @Override // java.io.OutputStream
    public void write(int r2) throws IOException {
        this.appendable.append((char) r2);
    }

    public T getAppendable() {
        return this.appendable;
    }
}
