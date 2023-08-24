package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractBinderC2575v;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2540by;
import com.google.android.play.core.internal.InterfaceC2578y;
import java.util.Arrays;

/* renamed from: com.google.android.play.core.assetpacks.b */
/* loaded from: classes3.dex */
final class BinderC2380b extends AbstractBinderC2575v {

    /* renamed from: a */
    private final C2494af f466a = new C2494af("AssetPackExtractionService");

    /* renamed from: b */
    private final Context f467b;

    /* renamed from: c */
    private final AssetPackExtractionService f468c;

    /* renamed from: d */
    private final C2382bb f469d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2380b(Context context, AssetPackExtractionService assetPackExtractionService, C2382bb c2382bb) {
        this.f467b = context;
        this.f468c = assetPackExtractionService;
        this.f469d = c2382bb;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2576w
    /* renamed from: a */
    public final void mo648a(Bundle bundle, InterfaceC2578y interfaceC2578y) throws RemoteException {
        String[] packagesForUid;
        this.f466a.m808a("updateServiceState AIDL call", new Object[0]);
        if (C2540by.m723a(this.f467b) && (packagesForUid = this.f467b.getPackageManager().getPackagesForUid(Binder.getCallingUid())) != null && Arrays.asList(packagesForUid).contains("com.android.vending")) {
            interfaceC2578y.mo645a(this.f468c.m1042a(bundle), new Bundle());
            return;
        }
        interfaceC2578y.mo646a(new Bundle());
        this.f468c.m1043a();
    }

    @Override // com.google.android.play.core.internal.InterfaceC2576w
    /* renamed from: a */
    public final void mo647a(InterfaceC2578y interfaceC2578y) throws RemoteException {
        this.f469d.m980f();
        interfaceC2578y.mo644b(new Bundle());
    }
}
