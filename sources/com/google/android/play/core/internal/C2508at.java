package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.play.core.splitcompat.C2595c;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.google.android.play.core.internal.at */
/* loaded from: classes3.dex */
public final class C2508at {

    /* renamed from: a */
    private final C2595c f835a;

    /* renamed from: b */
    private final Context f836b;

    /* renamed from: c */
    private final C2510av f837c;

    public C2508at(Context context, C2595c c2595c, C2510av c2510av, byte[] bArr) {
        this.f835a = c2595c;
        this.f837c = c2510av;
        this.f836b = context;
    }

    /* renamed from: a */
    private static X509Certificate m778a(Signature signature) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
        } catch (CertificateException e) {
            Log.e("SplitCompat", "Cannot decode certificate.", e);
            return null;
        }
    }

    /* renamed from: a */
    public final boolean m779a() {
        Signature[] signatureArr;
        String sb;
        int r12;
        try {
            File m600c = this.f835a.m600c();
            ArrayList<X509Certificate> arrayList = null;
            try {
                signatureArr = this.f836b.getPackageManager().getPackageInfo(this.f836b.getPackageName(), 64).signatures;
            } catch (PackageManager.NameNotFoundException unused) {
                signatureArr = null;
            }
            if (signatureArr != null) {
                arrayList = new ArrayList();
                for (Signature signature : signatureArr) {
                    X509Certificate m778a = m778a(signature);
                    if (m778a != null) {
                        arrayList.add(m778a);
                    }
                }
            }
            if (arrayList == null || arrayList.isEmpty()) {
                Log.e("SplitCompat", "No app certificates found.");
                return false;
            }
            File[] listFiles = m600c.listFiles();
            Arrays.sort(listFiles);
            int length = listFiles.length;
            loop1: while (true) {
                length--;
                if (length < 0) {
                    return true;
                }
                File file = listFiles[length];
                try {
                    String absolutePath = file.getAbsolutePath();
                    try {
                        X509Certificate[][] m700a = C2561h.m700a(absolutePath);
                        if (m700a == null || m700a.length == 0 || m700a[0].length == 0) {
                            break;
                        } else if (arrayList.isEmpty()) {
                            sb = "No certificates found for app.";
                            break;
                        } else {
                            for (X509Certificate x509Certificate : arrayList) {
                                int length2 = m700a.length;
                                while (r12 < length2) {
                                    r12 = m700a[r12][0].equals(x509Certificate) ? 0 : r12 + 1;
                                }
                                Log.i("SplitCompat", "There's an app certificate that doesn't sign the split.");
                            }
                            try {
                                file.renameTo(this.f835a.m607a(file));
                            } catch (IOException e) {
                                Log.e("SplitCompat", "Cannot write verified split.", e);
                                return false;
                            }
                        }
                    } catch (Exception e2) {
                        StringBuilder sb2 = new StringBuilder(String.valueOf(absolutePath).length() + 32);
                        sb2.append("Downloaded split ");
                        sb2.append(absolutePath);
                        sb2.append(" is not signed.");
                        Log.e("SplitCompat", sb2.toString(), e2);
                    }
                } catch (Exception e3) {
                    Log.e("SplitCompat", "Split verification error.", e3);
                    return false;
                }
            }
            Log.e("SplitCompat", sb);
            Log.e("SplitCompat", "Split verification failure.");
            return false;
        } catch (IOException e4) {
            Log.e("SplitCompat", "Cannot access directory for unverified splits.", e4);
            return false;
        }
    }

    /* renamed from: a */
    public final boolean m777a(List<Intent> list) throws IOException {
        for (Intent intent : list) {
            if (!this.f835a.m601b(intent.getStringExtra("split_id")).exists()) {
                return false;
            }
        }
        return true;
    }
}
