package com.fasterxml.jackson.core.sym;

/* loaded from: classes.dex */
public final class Name3 extends Name {

    /* renamed from: q1 */
    private final int f187q1;

    /* renamed from: q2 */
    private final int f188q2;

    /* renamed from: q3 */
    private final int f189q3;

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int r1) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int r1, int r2) {
        return false;
    }

    Name3(String str, int r2, int r3, int r4, int r5) {
        super(str, r2);
        this.f187q1 = r3;
        this.f188q2 = r4;
        this.f189q3 = r5;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int r2, int r3, int r4) {
        return this.f187q1 == r2 && this.f188q2 == r3 && this.f189q3 == r4;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int[] r4, int r5) {
        return r5 == 3 && r4[0] == this.f187q1 && r4[1] == this.f188q2 && r4[2] == this.f189q3;
    }
}
