package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.AbstractC2543ca;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* renamed from: com.google.android.play.core.assetpacks.be */
/* loaded from: classes3.dex */
final class C2385be extends AbstractC2543ca {

    /* renamed from: a */
    private final File f480a;

    /* renamed from: b */
    private final File f481b;

    /* renamed from: c */
    private final NavigableMap<Long, File> f482c = new TreeMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2385be(File file, File file2) throws IOException {
        this.f480a = file;
        this.f481b = file2;
        List<File> m866a = C2454dt.m866a(file, file2);
        if (m866a.isEmpty()) {
            throw new C2402bv(String.format("Virtualized slice archive empty for %s, %s", file, file2));
        }
        int size = m866a.size();
        long j = 0;
        for (int r2 = 0; r2 < size; r2++) {
            File file3 = m866a.get(r2);
            this.f482c.put(Long.valueOf(j), file3);
            j += file3.length();
        }
    }

    /* renamed from: a */
    private final InputStream m970a(long j, Long l) throws IOException {
        FileInputStream fileInputStream = new FileInputStream((File) this.f482c.get(l));
        if (fileInputStream.skip(j - l.longValue()) == j - l.longValue()) {
            return fileInputStream;
        }
        throw new C2402bv(String.format("Virtualized slice archive corrupt, could not skip in file with key %s", l));
    }

    @Override // com.google.android.play.core.internal.AbstractC2543ca
    /* renamed from: a */
    public final long mo719a() {
        Map.Entry<Long, File> lastEntry = this.f482c.lastEntry();
        return lastEntry.getKey().longValue() + lastEntry.getValue().length();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.play.core.internal.AbstractC2543ca
    /* renamed from: a */
    public final InputStream mo717a(long j, long j2) throws IOException {
        if (j < 0 || j2 < 0) {
            throw new C2402bv(String.format("Invalid input parameters %s, %s", Long.valueOf(j), Long.valueOf(j2)));
        }
        long j3 = j + j2;
        if (j3 <= mo719a()) {
            Long floorKey = this.f482c.floorKey(Long.valueOf(j));
            Long floorKey2 = this.f482c.floorKey(Long.valueOf(j3));
            if (floorKey.equals(floorKey2)) {
                return new C2384bd(m970a(j, floorKey), j2);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(m970a(j, floorKey));
            Collection<File> values = this.f482c.subMap(floorKey, false, floorKey2, false).values();
            if (!values.isEmpty()) {
                arrayList.add(new C2437dc(Collections.enumeration(values)));
            }
            arrayList.add(new C2384bd(new FileInputStream((File) this.f482c.get(floorKey2)), j2 - (floorKey2.longValue() - j)));
            return new SequenceInputStream(Collections.enumeration(arrayList));
        }
        throw new C2402bv(String.format("Trying to access archive out of bounds. Archive ends at: %s. Tried accessing: %s", Long.valueOf(mo719a()), Long.valueOf(j3)));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
