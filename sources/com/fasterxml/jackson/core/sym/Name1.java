package com.fasterxml.jackson.core.sym;

/* loaded from: classes.dex */
public final class Name1 extends Name {
    private static final Name1 EMPTY = new Name1("", 0, 0);

    /* renamed from: q */
    private final int f184q;

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int r1, int r2, int r3) {
        return false;
    }

    Name1(String str, int r2, int r3) {
        super(str, r2);
        this.f184q = r3;
    }

    public static Name1 getEmptyName() {
        return EMPTY;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int r2) {
        return r2 == this.f184q;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int r2, int r3) {
        return r2 == this.f184q && r3 == 0;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int[] r3, int r4) {
        return r4 == 1 && r3[0] == this.f184q;
    }
}
