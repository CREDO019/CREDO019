package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.play.core.splitinstall.u */
/* loaded from: classes3.dex */
final class RunnableC2668u implements Runnable {

    /* renamed from: a */
    final /* synthetic */ SplitInstallRequest f1075a;

    /* renamed from: b */
    final /* synthetic */ C2670w f1076b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2668u(C2670w c2670w, SplitInstallRequest splitInstallRequest) {
        this.f1076b = c2670w;
        this.f1075a = splitInstallRequest;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C2656t c2656t;
        List m477b;
        c2656t = this.f1076b.f1079b;
        List<String> moduleNames = this.f1075a.getModuleNames();
        m477b = C2670w.m477b(this.f1075a.getLanguages());
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", 0);
        bundle.putInt("status", 5);
        bundle.putInt("error_code", 0);
        if (!moduleNames.isEmpty()) {
            bundle.putStringArrayList("module_names", new ArrayList<>(moduleNames));
        }
        if (!m477b.isEmpty()) {
            bundle.putStringArrayList("languages", new ArrayList<>(m477b));
        }
        bundle.putLong("total_bytes_to_download", 0L);
        bundle.putLong("bytes_downloaded", 0L);
        c2656t.m640a((C2656t) SplitInstallSessionState.m570a(bundle));
    }
}
