package com.google.android.play.core.splitcompat;

import java.io.File;
import java.io.IOException;

/* renamed from: com.google.android.play.core.splitcompat.d */
/* loaded from: classes3.dex */
final class C2596d implements InterfaceC2601i {

    /* renamed from: a */
    final /* synthetic */ C2597e f906a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2596d(C2597e c2597e) {
        this.f906a = c2597e;
    }

    @Override // com.google.android.play.core.splitcompat.InterfaceC2601i
    /* renamed from: a */
    public final void mo584a(C2602j c2602j, File file, boolean z) throws IOException {
        this.f906a.f908b.add(file);
        if (z) {
            return;
        }
        this.f906a.f909c.set(false);
    }
}
