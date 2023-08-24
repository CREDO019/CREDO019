package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractBinderC2573t;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.tasks.C2682i;
import java.util.List;

/* renamed from: com.google.android.play.core.assetpacks.ak */
/* loaded from: classes3.dex */
class BinderC2364ak<T> extends AbstractBinderC2573t {

    /* renamed from: a */
    final C2682i<T> f419a;

    /* renamed from: b */
    final /* synthetic */ C2371ar f420b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2364ak(C2371ar c2371ar, C2682i<T> c2682i) {
        this.f420b = c2371ar;
        this.f419a = c2682i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2364ak(C2371ar c2371ar, C2682i c2682i, byte[] bArr) {
        this(c2371ar, c2682i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2364ak(C2371ar c2371ar, C2682i c2682i, char[] cArr) {
        this(c2371ar, c2682i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2364ak(C2371ar c2371ar, C2682i c2682i, int[] r3) {
        this(c2371ar, c2682i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2364ak(C2371ar c2371ar, C2682i c2682i, short[] sArr) {
        this(c2371ar, c2682i);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: a */
    public void mo662a() {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onCancelDownloads()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: a */
    public final void mo661a(int r4) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onCancelDownload(%d)", Integer.valueOf(r4));
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: a */
    public void mo660a(int r3, Bundle bundle) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onStartDownload(%d)", Integer.valueOf(r3));
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: a */
    public void mo659a(Bundle bundle) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        int r5 = bundle.getInt("error_code");
        c2494af = C2371ar.f431a;
        c2494af.m806b("onError(%d)", Integer.valueOf(r5));
        this.f419a.m455b(new AssetPackException(r5));
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: a */
    public void mo658a(Bundle bundle, Bundle bundle2) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f436f;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onKeepAlive(%b)", Boolean.valueOf(bundle.getBoolean("keep_alive")));
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: a */
    public void mo657a(List<Bundle> list) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onGetSessionStates", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: b */
    public void mo656b() {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onRemoveModule()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: b */
    public final void mo655b(int r4) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onGetSession(%d)", Integer.valueOf(r4));
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: b */
    public void mo654b(Bundle bundle) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onNotifyChunkTransferred(%s, %s, %d, session=%d)", bundle.getString("module_name"), bundle.getString("slice_id"), Integer.valueOf(bundle.getInt("chunk_number")), Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: b */
    public void mo653b(Bundle bundle, Bundle bundle2) throws RemoteException {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onGetChunkFileDescriptor", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: c */
    public void mo652c(Bundle bundle) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onNotifyModuleCompleted(%s, sessionId=%d)", bundle.getString("module_name"), Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: c */
    public void mo651c(Bundle bundle, Bundle bundle2) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onRequestDownloadInfo()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: d */
    public void mo650d(Bundle bundle) {
        C2504ap c2504ap;
        C2494af c2494af;
        c2504ap = this.f420b.f435e;
        c2504ap.m801a();
        c2494af = C2371ar.f431a;
        c2494af.m805c("onNotifySessionFailed(%d)", Integer.valueOf(bundle.getInt("session_id")));
    }
}
