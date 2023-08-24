package com.google.android.play.core.splitinstall.testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.internal.C2493ae;
import com.google.android.play.core.internal.C2510av;
import com.google.android.play.core.internal.C2539bx;
import com.google.android.play.core.splitcompat.C2608p;
import com.google.android.play.core.splitinstall.C2643h;
import com.google.android.play.core.splitinstall.C2652p;
import com.google.android.play.core.splitinstall.EnumC2647l;
import com.google.android.play.core.splitinstall.InterfaceC2640e;
import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public class FakeSplitInstallManager implements SplitInstallManager {

    /* renamed from: a */
    public static final /* synthetic */ int f1025a = 0;

    /* renamed from: c */
    private static final long f1026c = TimeUnit.SECONDS.toMillis(1);

    /* renamed from: b */
    private final Handler f1027b;

    /* renamed from: d */
    private final Context f1028d;

    /* renamed from: e */
    private final C2652p f1029e;

    /* renamed from: f */
    private final C2539bx f1030f;

    /* renamed from: g */
    private final C2493ae<SplitInstallSessionState> f1031g;

    /* renamed from: h */
    private final Executor f1032h;

    /* renamed from: i */
    private final InterfaceC2640e f1033i;

    /* renamed from: j */
    private final File f1034j;

    /* renamed from: k */
    private final AtomicReference<SplitInstallSessionState> f1035k;

    /* renamed from: l */
    private final Set<String> f1036l;

    /* renamed from: m */
    private final Set<String> f1037m;

    /* renamed from: n */
    private final AtomicBoolean f1038n;

    /* renamed from: o */
    private final C2657a f1039o;

    @Deprecated
    public FakeSplitInstallManager(Context context, File file) {
        this(context, file, new C2652p(context, context.getPackageName()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FakeSplitInstallManager(Context context, File file, C2652p c2652p) {
        Executor m577a = C2608p.m577a();
        C2539bx c2539bx = new C2539bx(context);
        C2657a c2657a = C2657a.f1041a;
        this.f1027b = new Handler(Looper.getMainLooper());
        this.f1035k = new AtomicReference<>();
        this.f1036l = Collections.synchronizedSet(new HashSet());
        this.f1037m = Collections.synchronizedSet(new HashSet());
        this.f1038n = new AtomicBoolean(false);
        this.f1028d = context;
        this.f1034j = file;
        this.f1029e = c2652p;
        this.f1032h = m577a;
        this.f1030f = c2539bx;
        this.f1039o = c2657a;
        this.f1031g = new C2493ae<>();
        this.f1033i = EnumC2647l.f1005a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static final /* synthetic */ SplitInstallSessionState m501a(int r11, SplitInstallSessionState splitInstallSessionState) {
        int status;
        if (splitInstallSessionState != null && r11 == splitInstallSessionState.sessionId() && ((status = splitInstallSessionState.status()) == 1 || status == 2 || status == 8 || status == 9 || status == 7)) {
            return SplitInstallSessionState.create(r11, 7, splitInstallSessionState.errorCode(), splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages());
        }
        throw new SplitInstallException(-3);
    }

    /* renamed from: a */
    private final synchronized SplitInstallSessionState m494a(InterfaceC2666j interfaceC2666j) {
        SplitInstallSessionState m485c = m485c();
        SplitInstallSessionState mo480a = interfaceC2666j.mo480a(m485c);
        if (this.f1035k.compareAndSet(m485c, mo480a)) {
            return mo480a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static final /* synthetic */ SplitInstallSessionState m493a(Integer num, int r12, int r13, Long l, Long l2, List list, List list2, SplitInstallSessionState splitInstallSessionState) {
        SplitInstallSessionState create = splitInstallSessionState == null ? SplitInstallSessionState.create(0, 0, 0, 0L, 0L, new ArrayList(), new ArrayList()) : splitInstallSessionState;
        return SplitInstallSessionState.create(num == null ? create.sessionId() : num.intValue(), r12, r13, l == null ? create.bytesDownloaded() : l.longValue(), l2 == null ? create.totalBytesToDownload() : l2.longValue(), list == null ? create.moduleNames() : list, list2 == null ? create.languages() : list2);
    }

    /* renamed from: a */
    private static String m492a(String str) {
        return str.split("\\.config\\.", 2)[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public final void m489a(List<Intent> list, List<String> list2, List<String> list3, long j, boolean z) {
        this.f1033i.mo524a().mo530a(list, new C2665i(this, list2, list3, j, z, list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public final boolean m503a(int r9) {
        return m502a(6, r9, null, null, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public final boolean m502a(final int r11, final int r12, final Long l, final Long l2, final List<String> list, final Integer num, final List<String> list2) {
        SplitInstallSessionState m494a = m494a(new InterfaceC2666j(num, r11, r12, l, l2, list, list2) { // from class: com.google.android.play.core.splitinstall.testing.b

            /* renamed from: a */
            private final Integer f1042a;

            /* renamed from: b */
            private final int f1043b;

            /* renamed from: c */
            private final int f1044c;

            /* renamed from: d */
            private final Long f1045d;

            /* renamed from: e */
            private final Long f1046e;

            /* renamed from: f */
            private final List f1047f;

            /* renamed from: g */
            private final List f1048g;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f1042a = num;
                this.f1043b = r11;
                this.f1044c = r12;
                this.f1045d = l;
                this.f1046e = l2;
                this.f1047f = list;
                this.f1048g = list2;
            }

            @Override // com.google.android.play.core.splitinstall.testing.InterfaceC2666j
            /* renamed from: a */
            public final SplitInstallSessionState mo480a(SplitInstallSessionState splitInstallSessionState) {
                return FakeSplitInstallManager.m493a(this.f1042a, this.f1043b, this.f1044c, this.f1045d, this.f1046e, this.f1047f, this.f1048g, splitInstallSessionState);
            }
        });
        if (m494a != null) {
            m487b(m494a);
            return true;
        }
        return false;
    }

    /* renamed from: b */
    static final /* synthetic */ void m488b() {
        SystemClock.sleep(f1026c);
    }

    /* renamed from: b */
    private final void m487b(final SplitInstallSessionState splitInstallSessionState) {
        this.f1027b.post(new Runnable(this, splitInstallSessionState) { // from class: com.google.android.play.core.splitinstall.testing.f

            /* renamed from: a */
            private final FakeSplitInstallManager f1054a;

            /* renamed from: b */
            private final SplitInstallSessionState f1055b;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f1054a = this;
                this.f1055b = splitInstallSessionState;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f1054a.m499a(this.f1055b);
            }
        });
    }

    /* renamed from: c */
    private final SplitInstallSessionState m485c() {
        return this.f1035k.get();
    }

    /* renamed from: d */
    private final C2643h m484d() {
        C2643h m512c = this.f1029e.m512c();
        if (m512c != null) {
            return m512c;
        }
        throw new IllegalStateException("Language information could not be found. Make sure you are using the target application context, not the tests context, and the app is built as a bundle.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final File m504a() {
        return this.f1034j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m500a(final long j, final List list, final List list2, final List list3) {
        long j2 = j / 3;
        long j3 = 0;
        for (int r4 = 0; r4 < 3; r4++) {
            j3 = Math.min(j, j3 + j2);
            m502a(2, 0, Long.valueOf(j3), Long.valueOf(j), null, null, null);
            m488b();
            SplitInstallSessionState m485c = m485c();
            if (m485c.status() == 9 || m485c.status() == 7 || m485c.status() == 6) {
                return;
            }
        }
        this.f1032h.execute(new Runnable(this, list, list2, list3, j) { // from class: com.google.android.play.core.splitinstall.testing.h

            /* renamed from: a */
            private final FakeSplitInstallManager f1061a;

            /* renamed from: b */
            private final List f1062b;

            /* renamed from: c */
            private final List f1063c;

            /* renamed from: d */
            private final List f1064d;

            /* renamed from: e */
            private final long f1065e;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f1061a = this;
                this.f1062b = list;
                this.f1063c = list2;
                this.f1064d = list3;
                this.f1065e = j;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f1061a.m490a(this.f1062b, this.f1063c, this.f1064d, this.f1065e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m499a(SplitInstallSessionState splitInstallSessionState) {
        this.f1031g.m812a((C2493ae<SplitInstallSessionState>) splitInstallSessionState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m491a(List list, final List list2) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        int size = list.size();
        for (int r1 = 0; r1 < size; r1++) {
            File file = (File) list.get(r1);
            String m775a = C2510av.m775a(file);
            Uri fromFile = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(fromFile, this.f1028d.getContentResolver().getType(fromFile));
            intent.addFlags(1);
            intent.putExtra("module_name", m492a(m775a));
            intent.putExtra("split_id", m775a);
            arrayList.add(intent);
            arrayList2.add(m492a(C2510av.m775a(file)));
        }
        SplitInstallSessionState m485c = m485c();
        if (m485c == null) {
            return;
        }
        final long j = m485c.totalBytesToDownload();
        this.f1032h.execute(new Runnable(this, j, arrayList, arrayList2, list2) { // from class: com.google.android.play.core.splitinstall.testing.g

            /* renamed from: a */
            private final FakeSplitInstallManager f1056a;

            /* renamed from: b */
            private final long f1057b;

            /* renamed from: c */
            private final List f1058c;

            /* renamed from: d */
            private final List f1059d;

            /* renamed from: e */
            private final List f1060e;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f1056a = this;
                this.f1057b = j;
                this.f1058c = arrayList;
                this.f1059d = arrayList2;
                this.f1060e = list2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f1056a.m500a(this.f1057b, this.f1058c, this.f1059d, this.f1060e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m490a(List list, List list2, List list3, long j) {
        if (this.f1038n.get()) {
            m503a(-6);
        } else {
            m489a((List<Intent>) list, (List<String>) list2, (List<String>) list3, j, false);
        }
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> cancelInstall(final int r2) {
        try {
            SplitInstallSessionState m494a = m494a(new InterfaceC2666j(r2) { // from class: com.google.android.play.core.splitinstall.testing.e

                /* renamed from: a */
                private final int f1053a;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.f1053a = r2;
                }

                @Override // com.google.android.play.core.splitinstall.testing.InterfaceC2666j
                /* renamed from: a */
                public final SplitInstallSessionState mo480a(SplitInstallSessionState splitInstallSessionState) {
                    return FakeSplitInstallManager.m501a(this.f1053a, splitInstallSessionState);
                }
            });
            if (m494a != null) {
                m487b(m494a);
            }
            return Tasks.m468a((Object) null);
        } catch (SplitInstallException e) {
            return Tasks.m469a((Exception) e);
        }
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredInstall(List<String> list) {
        return Tasks.m469a((Exception) new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        return Tasks.m469a((Exception) new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        return Tasks.m469a((Exception) new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredUninstall(List<String> list) {
        return Tasks.m469a((Exception) new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledLanguages() {
        return new HashSet(this.f1037m);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledModules() {
        return new HashSet(this.f1036l);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<SplitInstallSessionState> getSessionState(int r3) {
        SplitInstallSessionState m485c = m485c();
        return (m485c == null || m485c.sessionId() != r3) ? Tasks.m469a((Exception) new SplitInstallException(-4)) : Tasks.m468a(m485c);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        SplitInstallSessionState m485c = m485c();
        return Tasks.m468a(m485c != null ? Collections.singletonList(m485c) : Collections.emptyList());
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void registerListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.f1031g.m813a(splitInstallStateUpdatedListener);
    }

    public void setShouldNetworkError(boolean z) {
        this.f1038n.set(z);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, Activity activity, int r3) throws IntentSender.SendIntentException {
        return false;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, IntentSenderForResultStarter intentSenderForResultStarter, int r3) throws IntentSender.SendIntentException {
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0133, code lost:
        if (r0.contains(r6) == false) goto L50;
     */
    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.play.core.tasks.Task<java.lang.Integer> startInstall(final com.google.android.play.core.splitinstall.SplitInstallRequest r22) {
        /*
            Method dump skipped, instructions count: 566
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager.startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest):com.google.android.play.core.tasks.Task");
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void unregisterListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.f1031g.m811b(splitInstallStateUpdatedListener);
    }
}
