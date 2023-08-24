package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import com.google.android.play.core.splitcompat.C2595c;
import com.google.android.play.core.splitcompat.C2608p;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitinstall.InterfaceC2639d;
import com.google.android.play.core.splitinstall.InterfaceC2641f;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.play.core.internal.as */
/* loaded from: classes3.dex */
public final class C2507as implements InterfaceC2641f {

    /* renamed from: a */
    private final Context f830a;

    /* renamed from: b */
    private final C2595c f831b;

    /* renamed from: c */
    private final C2508at f832c;

    /* renamed from: d */
    private final Executor f833d;

    /* renamed from: e */
    private final C2608p f834e;

    public C2507as(Context context, Executor executor, C2508at c2508at, C2595c c2595c, C2608p c2608p) {
        this.f830a = context;
        this.f831b = c2595c;
        this.f832c = c2508at;
        this.f833d = executor;
        this.f834e = c2608p;
    }

    /* renamed from: a */
    private final Integer m780a(List<Intent> list) {
        FileLock fileLock;
        try {
            FileChannel channel = new RandomAccessFile(this.f831b.m603b(), "rw").getChannel();
            Integer num = null;
            try {
                fileLock = channel.tryLock();
            } catch (OverlappingFileLockException unused) {
                fileLock = null;
            }
            if (fileLock != null) {
                int r3 = 0;
                try {
                    Log.i("SplitCompat", "Copying splits.");
                    for (Intent intent : list) {
                        String stringExtra = intent.getStringExtra("split_id");
                        AssetFileDescriptor openAssetFileDescriptor = this.f830a.getContentResolver().openAssetFileDescriptor(intent.getData(), "r");
                        File m605a = this.f831b.m605a(stringExtra);
                        if ((!m605a.exists() || m605a.length() == openAssetFileDescriptor.getLength()) && m605a.exists()) {
                        }
                        if (this.f831b.m601b(stringExtra).exists()) {
                            continue;
                        } else {
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(openAssetFileDescriptor.createInputStream());
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(m605a);
                                byte[] bArr = new byte[4096];
                                while (true) {
                                    int read = bufferedInputStream.read(bArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                }
                                fileOutputStream.close();
                                bufferedInputStream.close();
                            } catch (Throwable th) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Throwable th2) {
                                    C2551ci.m714a(th, th2);
                                }
                                throw th;
                            }
                        }
                    }
                    Log.i("SplitCompat", "Splits copied.");
                    try {
                    } catch (Exception e) {
                        Log.e("SplitCompat", "Error verifying splits.", e);
                    }
                } catch (Exception e2) {
                    Log.e("SplitCompat", "Error copying splits.", e2);
                    r3 = -13;
                }
                if (this.f832c.m779a()) {
                    Log.i("SplitCompat", "Splits verified.");
                    num = Integer.valueOf(r3);
                    fileLock.release();
                } else {
                    Log.e("SplitCompat", "Split verification failed.");
                    r3 = -11;
                    num = Integer.valueOf(r3);
                    fileLock.release();
                }
            }
            if (channel != null) {
                channel.close();
            }
            return num;
        } catch (Exception e3) {
            Log.e("SplitCompat", "Error locking files.", e3);
            return -13;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m782a(C2507as c2507as, InterfaceC2639d interfaceC2639d) {
        try {
            if (SplitCompat.m617a(C2608p.m576a(c2507as.f830a))) {
                Log.i("SplitCompat", "Splits installed.");
                interfaceC2639d.mo483a();
                return;
            }
            Log.e("SplitCompat", "Emulating splits failed.");
            interfaceC2639d.mo482a(-12);
        } catch (Exception e) {
            Log.e("SplitCompat", "Error emulating splits.", e);
            interfaceC2639d.mo482a(-12);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m781a(C2507as c2507as, List list, InterfaceC2639d interfaceC2639d) {
        Integer m780a = c2507as.m780a(list);
        if (m780a == null) {
            return;
        }
        if (m780a.intValue() == 0) {
            interfaceC2639d.mo481b();
        } else {
            interfaceC2639d.mo482a(m780a.intValue());
        }
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2641f
    /* renamed from: a */
    public final void mo530a(List<Intent> list, InterfaceC2639d interfaceC2639d) {
        if (!SplitCompat.m618a()) {
            throw new IllegalStateException("Ingestion should only be called in SplitCompat mode.");
        }
        this.f833d.execute(new RunnableC2506ar(this, list, interfaceC2639d));
    }
}
