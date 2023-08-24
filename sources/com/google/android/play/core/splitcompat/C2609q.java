package com.google.android.play.core.splitcompat;

import java.io.File;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitcompat.q */
/* loaded from: classes3.dex */
public final class C2609q {

    /* renamed from: a */
    private final File f928a;

    /* renamed from: b */
    private final String f929b;

    C2609q() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2609q(File file, String str) {
        this();
        Objects.requireNonNull(file, "Null splitFile");
        this.f928a = file;
        Objects.requireNonNull(str, "Null splitId");
        this.f929b = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public File m574a() {
        return this.f928a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public String m573b() {
        return this.f929b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C2609q) {
            C2609q c2609q = (C2609q) obj;
            if (this.f928a.equals(c2609q.m574a()) && this.f929b.equals(c2609q.m573b())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((this.f928a.hashCode() ^ 1000003) * 1000003) ^ this.f929b.hashCode();
    }

    public String toString() {
        String valueOf = String.valueOf(this.f928a);
        String str = this.f929b;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 35 + str.length());
        sb.append("SplitFileInfo{splitFile=");
        sb.append(valueOf);
        sb.append(", splitId=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
