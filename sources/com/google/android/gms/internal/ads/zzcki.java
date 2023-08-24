package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.text.TextUtils;
import androidx.browser.trusted.sharing.ShareTarget;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcki implements zzazr {
    private static final Pattern zzb = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private final int zzc;
    private final int zzd;
    private final String zze;
    private final zzazq zzf;
    private final zzazx zzg;
    private zzazk zzh;
    private HttpURLConnection zzi;
    private final Queue zzj;
    private InputStream zzk;
    private boolean zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private long zzp;
    private long zzq;
    private final long zzr;
    private final long zzs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcki(String str, zzazx zzazxVar, int r3, int r4, long j, long j2) {
        zzazy.zzb(str);
        this.zze = str;
        this.zzg = zzazxVar;
        this.zzf = new zzazq();
        this.zzc = r3;
        this.zzd = r4;
        this.zzj = new ArrayDeque();
        this.zzr = j;
        this.zzs = j2;
    }

    private final void zzg() {
        while (!this.zzj.isEmpty()) {
            try {
                ((HttpURLConnection) this.zzj.remove()).disconnect();
            } catch (Exception e) {
                com.google.android.gms.ads.internal.util.zze.zzh("Unexpected error while disconnecting", e);
            }
        }
        this.zzi = null;
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final int zza(byte[] bArr, int r19, int r20) throws zzazo {
        if (r20 == 0) {
            return 0;
        }
        try {
            long j = this.zzm;
            long j2 = this.zzn;
            if (j - j2 == 0) {
                return -1;
            }
            long j3 = r20;
            long j4 = this.zzs;
            long j5 = this.zzq;
            long j6 = j5 + 1;
            if (this.zzo + j2 + j3 + j4 > j6) {
                long j7 = this.zzp;
                if (j5 < j7) {
                    long min = Math.min(j7, Math.max(((this.zzr + j6) - j4) - 1, (-1) + j6 + j3));
                    zzf(j6, min, 2);
                    this.zzq = min;
                    j5 = min;
                }
            }
            int read = this.zzk.read(bArr, r19, (int) Math.min(j3, ((j5 + 1) - this.zzo) - this.zzn));
            if (read != -1) {
                this.zzn += read;
                zzazx zzazxVar = this.zzg;
                if (zzazxVar != null) {
                    ((zzcke) zzazxVar).zzW(this, read);
                }
                return read;
            }
            throw new EOFException();
        } catch (IOException e) {
            throw new zzazo(e, this.zzh, 2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final Uri zzc() {
        HttpURLConnection httpURLConnection = this.zzi;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final void zzd() throws zzazo {
        try {
            InputStream inputStream = this.zzk;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new zzazo(e, this.zzh, 3);
                }
            }
        } finally {
            this.zzk = null;
            zzg();
            if (this.zzl) {
                this.zzl = false;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzazr
    public final Map zze() {
        HttpURLConnection httpURLConnection = this.zzi;
        if (httpURLConnection == null) {
            return null;
        }
        return httpURLConnection.getHeaderFields();
    }

    final HttpURLConnection zzf(long j, long j2, int r11) throws zzazo {
        String uri = this.zzh.zza.toString();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri).openConnection();
            httpURLConnection.setConnectTimeout(this.zzc);
            httpURLConnection.setReadTimeout(this.zzd);
            for (Map.Entry entry : this.zzf.zza().entrySet()) {
                httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            httpURLConnection.setRequestProperty("Range", "bytes=" + j + "-" + j2);
            httpURLConnection.setRequestProperty("User-Agent", this.zze);
            httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
            httpURLConnection.setRequestMethod(ShareTarget.METHOD_GET);
            httpURLConnection.connect();
            this.zzj.add(httpURLConnection);
            String uri2 = this.zzh.zza.toString();
            try {
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode > 299) {
                    Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                    zzg();
                    throw new zzckh(responseCode, headerFields, this.zzh, r11);
                }
                try {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    if (this.zzk != null) {
                        inputStream = new SequenceInputStream(this.zzk, inputStream);
                    }
                    this.zzk = inputStream;
                    return httpURLConnection;
                } catch (IOException e) {
                    zzg();
                    throw new zzazo(e, this.zzh, r11);
                }
            } catch (IOException e2) {
                zzg();
                throw new zzazo("Unable to connect to ".concat(String.valueOf(uri2)), e2, this.zzh, r11);
            }
        } catch (IOException e3) {
            throw new zzazo("Unable to connect to ".concat(String.valueOf(uri)), e3, this.zzh, r11);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final long zzb(zzazk zzazkVar) throws zzazo {
        this.zzh = zzazkVar;
        this.zzn = 0L;
        long j = zzazkVar.zzc;
        long j2 = zzazkVar.zzd;
        long min = j2 == -1 ? this.zzr : Math.min(this.zzr, j2);
        this.zzo = j;
        HttpURLConnection zzf = zzf(j, (min + j) - 1, 1);
        this.zzi = zzf;
        String headerField = zzf.getHeaderField(HttpHeaders.CONTENT_RANGE);
        if (!TextUtils.isEmpty(headerField)) {
            Matcher matcher = zzb.matcher(headerField);
            if (matcher.find()) {
                try {
                    Long.parseLong(matcher.group(1));
                    long parseLong = Long.parseLong(matcher.group(2));
                    long parseLong2 = Long.parseLong(matcher.group(3));
                    long j3 = zzazkVar.zzd;
                    if (j3 != -1) {
                        this.zzm = j3;
                        this.zzp = Math.max(parseLong, (this.zzo + j3) - 1);
                    } else {
                        this.zzm = parseLong2 - this.zzo;
                        this.zzp = parseLong2 - 1;
                    }
                    this.zzq = parseLong;
                    this.zzl = true;
                    zzazx zzazxVar = this.zzg;
                    if (zzazxVar != null) {
                        ((zzcke) zzazxVar).zzk(this, zzazkVar);
                    }
                    return this.zzm;
                } catch (NumberFormatException unused) {
                    com.google.android.gms.ads.internal.util.zze.zzg("Unexpected Content-Range [" + headerField + "]");
                }
            }
        }
        throw new zzckg(headerField, zzazkVar);
    }
}
