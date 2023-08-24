package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.ds */
/* loaded from: classes3.dex */
public final /* synthetic */ class C2453ds implements FilenameFilter {

    /* renamed from: a */
    static final FilenameFilter f731a = new C2453ds();

    private C2453ds() {
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        boolean matches;
        matches = C2454dt.f732a.matcher(str).matches();
        return matches;
    }
}
