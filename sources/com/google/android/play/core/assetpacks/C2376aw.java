package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.C2483a;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.InterfaceC2552cj;
import com.google.android.play.core.listener.AbstractC2581b;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.aw */
/* loaded from: classes3.dex */
public final class C2376aw extends AbstractC2581b<AssetPackState> {

    /* renamed from: c */
    private final C2423cp f447c;

    /* renamed from: d */
    private final C2403bw f448d;

    /* renamed from: e */
    private final InterfaceC2552cj<InterfaceC2478w> f449e;

    /* renamed from: f */
    private final C2394bn f450f;

    /* renamed from: g */
    private final C2406bz f451g;

    /* renamed from: h */
    private final C2483a f452h;

    /* renamed from: i */
    private final InterfaceC2552cj<Executor> f453i;

    /* renamed from: j */
    private final InterfaceC2552cj<Executor> f454j;

    /* renamed from: k */
    private final Handler f455k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2376aw(Context context, C2423cp c2423cp, C2403bw c2403bw, InterfaceC2552cj<InterfaceC2478w> interfaceC2552cj, C2406bz c2406bz, C2394bn c2394bn, C2483a c2483a, InterfaceC2552cj<Executor> interfaceC2552cj2, InterfaceC2552cj<Executor> interfaceC2552cj3) {
        super(new C2494af("AssetPackServiceListenerRegistry"), new IntentFilter("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE"), context);
        this.f455k = new Handler(Looper.getMainLooper());
        this.f447c = c2423cp;
        this.f448d = c2403bw;
        this.f449e = interfaceC2552cj;
        this.f451g = c2406bz;
        this.f450f = c2394bn;
        this.f452h = c2483a;
        this.f453i = interfaceC2552cj2;
        this.f454j = interfaceC2552cj3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.play.core.listener.AbstractC2581b
    /* renamed from: a */
    public final void mo507a(Context context, Intent intent) {
        final Bundle bundleExtra = intent.getBundleExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE");
        if (bundleExtra == null) {
            this.f868a.m806b("Empty bundle received from broadcast.", new Object[0]);
            return;
        }
        ArrayList<String> stringArrayList = bundleExtra.getStringArrayList("pack_names");
        if (stringArrayList == null || stringArrayList.size() != 1) {
            this.f868a.m806b("Corrupt bundle received from broadcast.", new Object[0]);
            return;
        }
        Bundle bundleExtra2 = intent.getBundleExtra("com.google.android.play.core.FLAGS");
        if (bundleExtra2 != null) {
            this.f452h.m820a(bundleExtra2);
        }
        final AssetPackState m1038a = AssetPackState.m1038a(bundleExtra, stringArrayList.get(0), this.f451g, C2378ay.f465a);
        this.f868a.m808a("ListenerRegistryBroadcastReceiver.onReceive: %s", m1038a);
        PendingIntent pendingIntent = (PendingIntent) bundleExtra.getParcelable("confirmation_intent");
        if (pendingIntent != null) {
            this.f450f.m961a(pendingIntent);
        }
        this.f454j.m713a().execute(new Runnable(this, bundleExtra, m1038a) { // from class: com.google.android.play.core.assetpacks.au

            /* renamed from: a */
            private final C2376aw f442a;

            /* renamed from: b */
            private final Bundle f443b;

            /* renamed from: c */
            private final AssetPackState f444c;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f442a = this;
                this.f443b = bundleExtra;
                this.f444c = m1038a;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f442a.m1011a(this.f443b, this.f444c);
            }
        });
        this.f453i.m713a().execute(new Runnable(this, bundleExtra) { // from class: com.google.android.play.core.assetpacks.av

            /* renamed from: a */
            private final C2376aw f445a;

            /* renamed from: b */
            private final Bundle f446b;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f445a = this;
                this.f446b = bundleExtra;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f445a.m1012a(this.f446b);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m1012a(Bundle bundle) {
        if (this.f447c.m940a(bundle)) {
            this.f448d.m951a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m1011a(Bundle bundle, AssetPackState assetPackState) {
        if (this.f447c.m934b(bundle)) {
            m1010a(assetPackState);
            this.f449e.m713a().mo834a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m1010a(final AssetPackState assetPackState) {
        this.f455k.post(new Runnable(this, assetPackState) { // from class: com.google.android.play.core.assetpacks.at

            /* renamed from: a */
            private final C2376aw f440a;

            /* renamed from: b */
            private final AssetPackState f441b;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f440a = this;
                this.f441b = assetPackState;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f440a.m640a((C2376aw) this.f441b);
            }
        });
    }
}
