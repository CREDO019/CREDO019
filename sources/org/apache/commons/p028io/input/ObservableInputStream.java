package org.apache.commons.p028io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: org.apache.commons.io.input.ObservableInputStream */
/* loaded from: classes5.dex */
public class ObservableInputStream extends ProxyInputStream {
    private final List<Observer> observers;

    /* renamed from: org.apache.commons.io.input.ObservableInputStream$Observer */
    /* loaded from: classes5.dex */
    public static abstract class Observer {
        void closed() throws IOException {
        }

        void data(int r1) throws IOException {
        }

        void data(byte[] bArr, int r2, int r3) throws IOException {
        }

        void finished() throws IOException {
        }

        void error(IOException iOException) throws IOException {
            throw iOException;
        }
    }

    public ObservableInputStream(InputStream inputStream) {
        super(inputStream);
        this.observers = new ArrayList();
    }

    public void add(Observer observer) {
        this.observers.add(observer);
    }

    public void remove(Observer observer) {
        this.observers.remove(observer);
    }

    public void removeAllObservers() {
        this.observers.clear();
    }

    @Override // org.apache.commons.p028io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int r0;
        try {
            r0 = super.read();
            e = null;
        } catch (IOException e) {
            e = e;
            r0 = 0;
        }
        if (e != null) {
            noteError(e);
        } else if (r0 == -1) {
            noteFinished();
        } else {
            noteDataByte(r0);
        }
        return r0;
    }

    @Override // org.apache.commons.p028io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int r1;
        try {
            r1 = super.read(bArr);
            e = null;
        } catch (IOException e) {
            e = e;
            r1 = 0;
        }
        if (e != null) {
            noteError(e);
        } else if (r1 == -1) {
            noteFinished();
        } else if (r1 > 0) {
            noteDataBytes(bArr, 0, r1);
        }
        return r1;
    }

    @Override // org.apache.commons.p028io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int r3, int r4) throws IOException {
        int r42;
        try {
            r42 = super.read(bArr, r3, r4);
            e = null;
        } catch (IOException e) {
            e = e;
            r42 = 0;
        }
        if (e != null) {
            noteError(e);
        } else if (r42 == -1) {
            noteFinished();
        } else if (r42 > 0) {
            noteDataBytes(bArr, r3, r42);
        }
        return r42;
    }

    protected void noteDataBytes(byte[] bArr, int r4, int r5) throws IOException {
        for (Observer observer : getObservers()) {
            observer.data(bArr, r4, r5);
        }
    }

    protected void noteFinished() throws IOException {
        for (Observer observer : getObservers()) {
            observer.finished();
        }
    }

    protected void noteDataByte(int r3) throws IOException {
        for (Observer observer : getObservers()) {
            observer.data(r3);
        }
    }

    protected void noteError(IOException iOException) throws IOException {
        for (Observer observer : getObservers()) {
            observer.error(iOException);
        }
    }

    protected void noteClosed() throws IOException {
        for (Observer observer : getObservers()) {
            observer.closed();
        }
    }

    protected List<Observer> getObservers() {
        return this.observers;
    }

    @Override // org.apache.commons.p028io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            super.close();
            e = null;
        } catch (IOException e) {
            e = e;
        }
        if (e == null) {
            noteClosed();
        } else {
            noteError(e);
        }
    }

    public void consume() throws IOException {
        do {
        } while (read(new byte[8192]) != -1);
    }
}
