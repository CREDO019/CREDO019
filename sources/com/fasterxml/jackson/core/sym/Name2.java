package com.fasterxml.jackson.core.sym;

/* loaded from: classes.dex */
public final class Name2 extends Name {

    /* renamed from: q1 */
    private final int f185q1;

    /* renamed from: q2 */
    private final int f186q2;

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int r1) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int r1, int r2, int r3) {
        return false;
    }

    Name2(String str, int r2, int r3, int r4) {
        super(str, r2);
        this.f185q1 = r3;
        this.f186q2 = r4;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int r2, int r3) {
        return r2 == this.f185q1 && r3 == this.f186q2;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int[] r4, int r5) {
        return r5 == 2 && r4[0] == this.f185q1 && r4[1] == this.f186q2;
    }
}
