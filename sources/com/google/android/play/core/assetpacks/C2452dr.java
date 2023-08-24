package com.google.android.play.core.assetpacks;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2551ci;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Properties;

/* renamed from: com.google.android.play.core.assetpacks.dr */
/* loaded from: classes3.dex */
final class C2452dr {

    /* renamed from: a */
    private static final C2494af f723a = new C2494af("SliceMetadataManager");

    /* renamed from: c */
    private final C2382bb f725c;

    /* renamed from: d */
    private final String f726d;

    /* renamed from: e */
    private final int f727e;

    /* renamed from: f */
    private final long f728f;

    /* renamed from: g */
    private final String f729g;

    /* renamed from: b */
    private final byte[] f724b = new byte[8192];

    /* renamed from: h */
    private int f730h = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2452dr(C2382bb c2382bb, String str, int r4, long j, String str2) {
        this.f725c = c2382bb;
        this.f726d = str;
        this.f727e = r4;
        this.f728f = j;
        this.f729g = str2;
    }

    /* renamed from: e */
    private final File m868e() {
        File m977f = this.f725c.m977f(this.f726d, this.f727e, this.f728f, this.f729g);
        if (!m977f.exists()) {
            m977f.mkdirs();
        }
        return m977f;
    }

    /* renamed from: f */
    private final File m867f() throws IOException {
        File m989c = this.f725c.m989c(this.f726d, this.f727e, this.f728f, this.f729g);
        m989c.getParentFile().mkdirs();
        m989c.createNewFile();
        return m989c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final C2451dq m881a() throws IOException {
        File m989c = this.f725c.m989c(this.f726d, this.f727e, this.f728f, this.f729g);
        if (m989c.exists()) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(m989c);
            try {
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("fileStatus") == null || properties.getProperty("previousChunk") == null) {
                    throw new C2402bv("Slice checkpoint file corrupt.");
                }
                try {
                    int parseInt = Integer.parseInt(properties.getProperty("fileStatus"));
                    String property = properties.getProperty("fileName");
                    long parseLong = Long.parseLong(properties.getProperty("fileOffset", "-1"));
                    long parseLong2 = Long.parseLong(properties.getProperty("remainingBytes", "-1"));
                    int parseInt2 = Integer.parseInt(properties.getProperty("previousChunk"));
                    this.f730h = Integer.parseInt(properties.getProperty("metadataFileCounter", SessionDescription.SUPPORTED_SDP_VERSION));
                    return new C2451dq(parseInt, property, parseLong, parseLong2, parseInt2);
                } catch (NumberFormatException e) {
                    throw new C2402bv("Slice checkpoint file corrupt.", e);
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    C2551ci.m714a(th, th2);
                }
                throw th;
            }
        }
        throw new C2402bv("Slice checkpoint file does not exist.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m880a(int r4) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", ExifInterface.GPS_MEASUREMENT_3D);
        properties.put("fileOffset", String.valueOf(m873b().length()));
        properties.put("previousChunk", String.valueOf(r4));
        properties.put("metadataFileCounter", String.valueOf(this.f730h));
        FileOutputStream fileOutputStream = new FileOutputStream(m867f());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                C2551ci.m714a(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m879a(long j, byte[] bArr, int r7, int r8) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(m873b(), "rw");
        try {
            randomAccessFile.seek(j);
            randomAccessFile.write(bArr, r7, r8);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                C2551ci.m714a(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m878a(InputStream inputStream, long j) throws IOException {
        int read;
        RandomAccessFile randomAccessFile = new RandomAccessFile(m873b(), "rw");
        try {
            randomAccessFile.seek(j);
            do {
                read = inputStream.read(this.f724b);
                if (read > 0) {
                    randomAccessFile.write(this.f724b, 0, read);
                }
            } while (read == this.f724b.length);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                C2551ci.m714a(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m877a(String str, long j, long j2, int r9) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        properties.put("fileName", str);
        properties.put("fileOffset", String.valueOf(j));
        properties.put("remainingBytes", String.valueOf(j2));
        properties.put("previousChunk", String.valueOf(r9));
        properties.put("metadataFileCounter", String.valueOf(this.f730h));
        FileOutputStream fileOutputStream = new FileOutputStream(m867f());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                C2551ci.m714a(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m876a(byte[] bArr) throws IOException {
        this.f730h++;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(m868e(), String.format("%s-LFH.dat", Integer.valueOf(this.f730h))));
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new C2402bv("Could not write metadata file.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m875a(byte[] bArr, int r10) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "2");
        properties.put("previousChunk", String.valueOf(r10));
        properties.put("metadataFileCounter", String.valueOf(this.f730h));
        FileOutputStream fileOutputStream = new FileOutputStream(m867f());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            File m985d = this.f725c.m985d(this.f726d, this.f727e, this.f728f, this.f729g);
            if (m985d.exists()) {
                m985d.delete();
            }
            fileOutputStream = new FileOutputStream(m985d);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.close();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (Throwable th) {
                    C2551ci.m714a(th, th);
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m874a(byte[] bArr, InputStream inputStream) throws IOException {
        this.f730h++;
        FileOutputStream fileOutputStream = new FileOutputStream(m873b());
        try {
            fileOutputStream.write(bArr);
            int read = inputStream.read(this.f724b);
            while (read > 0) {
                fileOutputStream.write(this.f724b, 0, read);
                read = inputStream.read(this.f724b);
            }
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                C2551ci.m714a(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final File m873b() {
        return new File(m868e(), String.format("%s-NAM.dat", Integer.valueOf(this.f730h)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m872b(int r4) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "4");
        properties.put("previousChunk", String.valueOf(r4));
        properties.put("metadataFileCounter", String.valueOf(this.f730h));
        FileOutputStream fileOutputStream = new FileOutputStream(m867f());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                C2551ci.m714a(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m871b(byte[] bArr, int r4) throws IOException {
        this.f730h++;
        FileOutputStream fileOutputStream = new FileOutputStream(m873b());
        try {
            fileOutputStream.write(bArr, 0, r4);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                C2551ci.m714a(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final int m870c() throws IOException {
        File m989c = this.f725c.m989c(this.f726d, this.f727e, this.f728f, this.f729g);
        if (m989c.exists()) {
            FileInputStream fileInputStream = new FileInputStream(m989c);
            try {
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                if (Integer.parseInt(properties.getProperty("fileStatus", "-1")) == 4) {
                    return -1;
                }
                if (properties.getProperty("previousChunk") != null) {
                    return Integer.parseInt(properties.getProperty("previousChunk")) + 1;
                }
                throw new C2402bv("Slice checkpoint file corrupt.");
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    C2551ci.m714a(th, th2);
                }
                throw th;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final boolean m869d() {
        File m989c = this.f725c.m989c(this.f726d, this.f727e, this.f728f, this.f729g);
        if (m989c.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(m989c);
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("fileStatus") != null) {
                    return Integer.parseInt(properties.getProperty("fileStatus")) == 4;
                }
                f723a.m806b("Slice checkpoint file corrupt while checking if extraction finished.", new Object[0]);
                return false;
            } catch (IOException e) {
                f723a.m806b("Could not read checkpoint while checking if extraction finished. %s", e);
                return false;
            }
        }
        return false;
    }
}
