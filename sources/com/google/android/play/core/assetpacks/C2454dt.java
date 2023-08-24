package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2551ci;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* renamed from: com.google.android.play.core.assetpacks.dt */
/* loaded from: classes3.dex */
final class C2454dt {

    /* renamed from: a */
    private static final Pattern f732a = Pattern.compile("[0-9]+-(NAM|LFH)\\.dat");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static List<File> m866a(File file, File file2) throws IOException {
        File[] fileArr;
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file2.listFiles(C2453ds.f731a);
        if (listFiles == null) {
            fileArr = new File[0];
        } else {
            File[] fileArr2 = new File[listFiles.length];
            for (File file3 : listFiles) {
                int parseInt = Integer.parseInt(file3.getName().split("-")[0]);
                if (parseInt > listFiles.length || fileArr2[parseInt] != null) {
                    throw new C2402bv("Metadata folder ordering corrupt.");
                }
                fileArr2[parseInt] = file3;
            }
            fileArr = fileArr2;
        }
        for (File file4 : fileArr) {
            arrayList.add(file4);
            if (file4.getName().contains("LFH")) {
                FileInputStream fileInputStream = new FileInputStream(file4);
                try {
                    C2458dx m968a = new C2393bm(fileInputStream).m968a();
                    if (m968a.m859c() == null) {
                        throw new C2402bv("Metadata files corrupt. Could not read local file header.");
                    }
                    File file5 = new File(file, m968a.m859c());
                    if (!file5.exists()) {
                        throw new C2402bv(String.format("Missing asset file %s during slice reconstruction.", file5.getCanonicalPath()));
                    }
                    arrayList.add(file5);
                    fileInputStream.close();
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        C2551ci.m714a(th, th2);
                    }
                    throw th;
                }
            }
        }
        return arrayList;
    }
}
