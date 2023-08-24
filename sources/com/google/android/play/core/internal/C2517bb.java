package com.google.android.play.core.internal;

import io.jsonwebtoken.Header;
import java.io.File;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.internal.bb */
/* loaded from: classes3.dex */
public final class C2517bb implements InterfaceC2509au {
    @Override // com.google.android.play.core.internal.InterfaceC2509au
    /* renamed from: a */
    public final void mo757a(ClassLoader classLoader, Set<File> set) {
        C2516ba.m768b(classLoader, set);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2509au
    /* renamed from: a */
    public final boolean mo758a(ClassLoader classLoader, File file, File file2, boolean z) {
        return C2516ba.m770a(classLoader, file, file2, z, C2516ba.m772a(), Header.COMPRESSION_ALGORITHM, C2516ba.m769b());
    }
}
