package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzakw implements zzajk {
    private final zzakv zzc;
    private final Map zza = new LinkedHashMap(16, 0.75f, true);
    private long zzb = 0;
    private final int zzd = 5242880;

    public zzakw(zzakv zzakvVar, int r5) {
        this.zzc = zzakvVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(InputStream inputStream) throws IOException {
        return (zzn(inputStream) << 24) | zzn(inputStream) | (zzn(inputStream) << 8) | (zzn(inputStream) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzf(InputStream inputStream) throws IOException {
        return (zzn(inputStream) & 255) | ((zzn(inputStream) & 255) << 8) | ((zzn(inputStream) & 255) << 16) | ((zzn(inputStream) & 255) << 24) | ((zzn(inputStream) & 255) << 32) | ((zzn(inputStream) & 255) << 40) | ((zzn(inputStream) & 255) << 48) | ((255 & zzn(inputStream)) << 56);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzh(zzaku zzakuVar) throws IOException {
        return new String(zzm(zzakuVar, zzf(zzakuVar)), "UTF-8");
    }

    static void zzj(OutputStream outputStream, int r2) throws IOException {
        outputStream.write(r2 & 255);
        outputStream.write((r2 >> 8) & 255);
        outputStream.write((r2 >> 16) & 255);
        outputStream.write((r2 >> 24) & 255);
    }

    static void zzk(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) j);
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 48));
        outputStream.write((byte) (j >>> 56));
    }

    static void zzl(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        int length = bytes.length;
        zzk(outputStream, length);
        outputStream.write(bytes, 0, length);
    }

    static byte[] zzm(zzaku zzakuVar, long j) throws IOException {
        long zza = zzakuVar.zza();
        if (j >= 0 && j <= zza) {
            int r2 = (int) j;
            if (r2 == j) {
                byte[] bArr = new byte[r2];
                new DataInputStream(zzakuVar).readFully(bArr);
                return bArr;
            }
        }
        throw new IOException("streamToBytes length=" + j + ", maxLength=" + zza);
    }

    private static int zzn(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    private final void zzo(String str, zzakt zzaktVar) {
        if (this.zza.containsKey(str)) {
            this.zzb += zzaktVar.zza - ((zzakt) this.zza.get(str)).zza;
        } else {
            this.zzb += zzaktVar.zza;
        }
        this.zza.put(str, zzaktVar);
    }

    private final void zzp(String str) {
        zzakt zzaktVar = (zzakt) this.zza.remove(str);
        if (zzaktVar != null) {
            this.zzb -= zzaktVar.zza;
        }
    }

    private static final String zzq(String str) {
        int length = str.length() / 2;
        return String.valueOf(String.valueOf(str.substring(0, length).hashCode())).concat(String.valueOf(String.valueOf(str.substring(length).hashCode())));
    }

    @Override // com.google.android.gms.internal.ads.zzajk
    public final synchronized zzajj zza(String str) {
        zzakt zzaktVar = (zzakt) this.zza.get(str);
        if (zzaktVar == null) {
            return null;
        }
        File zzg = zzg(str);
        try {
            zzaku zzakuVar = new zzaku(new BufferedInputStream(new FileInputStream(zzg)), zzg.length());
            try {
                zzakt zza = zzakt.zza(zzakuVar);
                if (!TextUtils.equals(str, zza.zzb)) {
                    zzakm.zza("%s: key=%s, found=%s", zzg.getAbsolutePath(), str, zza.zzb);
                    zzp(str);
                    return null;
                }
                byte[] zzm = zzm(zzakuVar, zzakuVar.zza());
                zzajj zzajjVar = new zzajj();
                zzajjVar.zza = zzm;
                zzajjVar.zzb = zzaktVar.zzc;
                zzajjVar.zzc = zzaktVar.zzd;
                zzajjVar.zzd = zzaktVar.zze;
                zzajjVar.zze = zzaktVar.zzf;
                zzajjVar.zzf = zzaktVar.zzg;
                List<zzajs> list = zzaktVar.zzh;
                TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                for (zzajs zzajsVar : list) {
                    treeMap.put(zzajsVar.zza(), zzajsVar.zzb());
                }
                zzajjVar.zzg = treeMap;
                zzajjVar.zzh = Collections.unmodifiableList(zzaktVar.zzh);
                return zzajjVar;
            } finally {
                zzakuVar.close();
            }
        } catch (IOException e) {
            zzakm.zza("%s: %s", zzg.getAbsolutePath(), e.toString());
            zzi(str);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajk
    public final synchronized void zzb() {
        File zza = this.zzc.zza();
        if (!zza.exists()) {
            if (zza.mkdirs()) {
                return;
            }
            zzakm.zzb("Unable to create cache dir %s", zza.getAbsolutePath());
            return;
        }
        File[] listFiles = zza.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file : listFiles) {
            try {
                long length = file.length();
                zzaku zzakuVar = new zzaku(new BufferedInputStream(new FileInputStream(file)), length);
                try {
                    zzakt zza2 = zzakt.zza(zzakuVar);
                    zza2.zza = length;
                    zzo(zza2.zzb, zza2);
                    zzakuVar.close();
                } catch (Throwable th) {
                    zzakuVar.close();
                    throw th;
                    break;
                }
            } catch (IOException unused) {
                file.delete();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajk
    public final synchronized void zzc(String str, boolean z) {
        zzajj zza = zza(str);
        if (zza != null) {
            zza.zzf = 0L;
            zza.zze = 0L;
            zzd(str, zza);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajk
    public final synchronized void zzd(String str, zzajj zzajjVar) {
        BufferedOutputStream bufferedOutputStream;
        zzakt zzaktVar;
        long j;
        long j2 = this.zzb;
        int length = zzajjVar.zza.length;
        int r6 = this.zzd;
        if (j2 + length <= r6 || length <= r6 * 0.9f) {
            File zzg = zzg(str);
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(zzg));
                zzaktVar = new zzakt(str, zzajjVar);
            } catch (IOException unused) {
                if (!zzg.delete()) {
                    zzakm.zza("Could not clean up file %s", zzg.getAbsolutePath());
                }
                if (!this.zzc.zza().exists()) {
                    zzakm.zza("Re-initializing cache after external clearing.", new Object[0]);
                    this.zza.clear();
                    this.zzb = 0L;
                    zzb();
                    return;
                }
            }
            try {
                zzj(bufferedOutputStream, 538247942);
                zzl(bufferedOutputStream, zzaktVar.zzb);
                String str2 = zzaktVar.zzc;
                if (str2 == null) {
                    str2 = "";
                }
                zzl(bufferedOutputStream, str2);
                zzk(bufferedOutputStream, zzaktVar.zzd);
                zzk(bufferedOutputStream, zzaktVar.zze);
                zzk(bufferedOutputStream, zzaktVar.zzf);
                zzk(bufferedOutputStream, zzaktVar.zzg);
                List<zzajs> list = zzaktVar.zzh;
                if (list != null) {
                    zzj(bufferedOutputStream, list.size());
                    for (zzajs zzajsVar : list) {
                        zzl(bufferedOutputStream, zzajsVar.zza());
                        zzl(bufferedOutputStream, zzajsVar.zzb());
                    }
                } else {
                    zzj(bufferedOutputStream, 0);
                }
                bufferedOutputStream.flush();
                bufferedOutputStream.write(zzajjVar.zza);
                bufferedOutputStream.close();
                zzaktVar.zza = zzg.length();
                zzo(str, zzaktVar);
                if (this.zzb >= this.zzd) {
                    if (zzakm.zzb) {
                        zzakm.zzd("Pruning old cache entries.", new Object[0]);
                    }
                    long j3 = this.zzb;
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    Iterator it = this.zza.entrySet().iterator();
                    int r2 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            j = elapsedRealtime;
                            break;
                        }
                        zzakt zzaktVar2 = (zzakt) ((Map.Entry) it.next()).getValue();
                        if (zzg(zzaktVar2.zzb).delete()) {
                            j = elapsedRealtime;
                            this.zzb -= zzaktVar2.zza;
                        } else {
                            j = elapsedRealtime;
                            String str3 = zzaktVar2.zzb;
                            zzakm.zza("Could not delete cache entry for key=%s, filename=%s", str3, zzq(str3));
                        }
                        it.remove();
                        r2++;
                        if (((float) this.zzb) < this.zzd * 0.9f) {
                            break;
                        }
                        elapsedRealtime = j;
                    }
                    if (zzakm.zzb) {
                        zzakm.zzd("pruned %d files, %d bytes, %d ms", Integer.valueOf(r2), Long.valueOf(this.zzb - j3), Long.valueOf(SystemClock.elapsedRealtime() - j));
                    }
                }
            } catch (IOException e) {
                zzakm.zza("%s", e.toString());
                bufferedOutputStream.close();
                zzakm.zza("Failed to write header for %s", zzg.getAbsolutePath());
                throw new IOException();
            }
        }
    }

    public final File zzg(String str) {
        return new File(this.zzc.zza(), zzq(str));
    }

    public final synchronized void zzi(String str) {
        boolean delete = zzg(str).delete();
        zzp(str);
        if (delete) {
            return;
        }
        zzakm.zza("Could not delete cache entry for key=%s, filename=%s", str, zzq(str));
    }

    public zzakw(File file, int r5) {
        this.zzc = new zzaks(this, file);
    }
}
