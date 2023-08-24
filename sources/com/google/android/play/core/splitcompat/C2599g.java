package com.google.android.play.core.splitcompat;

import android.util.Log;
import com.google.android.play.core.internal.C2551ci;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitcompat.g */
/* loaded from: classes3.dex */
public final class C2599g implements InterfaceC2601i {

    /* renamed from: a */
    final /* synthetic */ Set f914a;

    /* renamed from: b */
    final /* synthetic */ C2609q f915b;

    /* renamed from: c */
    final /* synthetic */ ZipFile f916c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2599g(Set set, C2609q c2609q, ZipFile zipFile) {
        this.f914a = set;
        this.f915b = c2609q;
        this.f916c = zipFile;
    }

    @Override // com.google.android.play.core.splitcompat.InterfaceC2601i
    /* renamed from: a */
    public final void mo584a(C2602j c2602j, File file, boolean z) throws IOException {
        this.f914a.add(file);
        if (z) {
            return;
        }
        Log.i("SplitCompat", String.format("NativeLibraryExtractor: split '%s' has native library '%s' that does not exist; extracting from '%s!%s' to '%s'", this.f915b.m573b(), c2602j.f917a, this.f915b.m574a().getAbsolutePath(), c2602j.f918b.getName(), file.getAbsolutePath()));
        ZipFile zipFile = this.f916c;
        ZipEntry zipEntry = c2602j.f918b;
        int r0 = C2603k.f919a;
        byte[] bArr = new byte[4096];
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.close();
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    C2551ci.m714a(th, th2);
                }
            }
            throw th;
        }
    }
}
