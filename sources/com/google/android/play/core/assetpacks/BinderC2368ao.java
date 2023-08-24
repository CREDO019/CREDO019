package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.assetpacks.ao */
/* loaded from: classes3.dex */
final class BinderC2368ao extends BinderC2364ak<Void> {

    /* renamed from: c */
    final int f423c;

    /* renamed from: d */
    final String f424d;

    /* renamed from: e */
    final int f425e;

    /* renamed from: f */
    final /* synthetic */ C2371ar f426f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BinderC2368ao(C2371ar c2371ar, C2682i<Void> c2682i, int r3, String str, int r5) {
        super(c2371ar, c2682i);
        this.f426f = c2371ar;
        this.f423c = r3;
        this.f424d = str;
        this.f425e = r5;
    }

    @Override // com.google.android.play.core.assetpacks.BinderC2364ak, com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: a */
    public final void mo659a(Bundle bundle) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f426f.f435e;
        c2504ap.m801a();
        int r4 = bundle.getInt("error_code");
        c2494af = C2371ar.f431a;
        c2494af.m806b("onError(%d), retrying notifyModuleCompleted...", Integer.valueOf(r4));
        int r42 = this.f425e;
        if (r42 > 0) {
            this.f426f.m1031a(this.f423c, this.f424d, r42 - 1);
        }
    }
}
