package com.google.android.play.core.internal;

import android.util.Log;
import java.io.File;

/* renamed from: com.google.android.play.core.internal.bj */
/* loaded from: classes3.dex */
final class C2525bj implements InterfaceC2513ay {
    @Override // com.google.android.play.core.internal.InterfaceC2513ay
    /* renamed from: a */
    public final boolean mo759a(Object obj, File file, File file2) {
        try {
            return !((Boolean) C2532bq.m748a(Class.forName("dalvik.system.DexFile"), Boolean.class, String.class, file.getPath())).booleanValue();
        } catch (ClassNotFoundException unused) {
            Log.e("SplitCompat", "Unexpected missing dalvik.system.DexFile.");
            return false;
        }
    }
}
