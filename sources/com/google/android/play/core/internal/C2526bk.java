package com.google.android.play.core.internal;

import com.RNFetchBlob.RNFetchBlobConst;
import java.io.File;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.internal.bk */
/* loaded from: classes3.dex */
public final class C2526bk implements InterfaceC2509au {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m760b(ClassLoader classLoader, Set<File> set) {
        C2521bf.m765a(classLoader, set, new C2524bi());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m761b(ClassLoader classLoader, File file, File file2, boolean z) {
        return C2516ba.m770a(classLoader, file, file2, z, C2521bf.m766a(), RNFetchBlobConst.RNFB_RESPONSE_PATH, new C2525bj());
    }

    @Override // com.google.android.play.core.internal.InterfaceC2509au
    /* renamed from: a */
    public final void mo757a(ClassLoader classLoader, Set<File> set) {
        m760b(classLoader, set);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2509au
    /* renamed from: a */
    public final boolean mo758a(ClassLoader classLoader, File file, File file2, boolean z) {
        return m761b(classLoader, file, file2, z);
    }
}
