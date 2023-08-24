package com.fasterxml.jackson.core.sym;

/* loaded from: classes.dex */
public abstract class Name {
    protected final int _hashCode;
    protected final String _name;

    public abstract boolean equals(int r1);

    public abstract boolean equals(int r1, int r2);

    public abstract boolean equals(int r1, int r2, int r3);

    public boolean equals(Object obj) {
        return obj == this;
    }

    public abstract boolean equals(int[] r1, int r2);

    /* JADX INFO: Access modifiers changed from: protected */
    public Name(String str, int r2) {
        this._name = str;
        this._hashCode = r2;
    }

    public String getName() {
        return this._name;
    }

    public String toString() {
        return this._name;
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
