package com.google.android.gms.internal.ads;

import android.net.Network;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfpc extends zzfoq {
    private zzfsv<Integer> zza;
    private zzfsv<Integer> zzb;
    private zzfpb zzc;
    private HttpURLConnection zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfpc() {
        this(new zzfsv() { // from class: com.google.android.gms.internal.ads.zzfoz
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                return zzfpc.zzf();
            }
        }, new zzfsv() { // from class: com.google.android.gms.internal.ads.zzfpa
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                return zzfpc.zzg();
            }
        }, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfpc(zzfsv<Integer> zzfsvVar, zzfsv<Integer> zzfsvVar2, zzfpb zzfpbVar) {
        this.zza = zzfsvVar;
        this.zzb = zzfsvVar2;
        this.zzc = zzfpbVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer zzf() {
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer zzg() {
        return -1;
    }

    public static void zzs(HttpURLConnection httpURLConnection) {
        zzfor.zza();
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        zzs(this.zzd);
    }

    public HttpURLConnection zzm() throws IOException {
        zzfor.zzb(((Integer) this.zza.zza()).intValue(), ((Integer) this.zzb.zza()).intValue());
        zzfpb zzfpbVar = this.zzc;
        Objects.requireNonNull(zzfpbVar);
        HttpURLConnection httpURLConnection = (HttpURLConnection) zzfpbVar.zza();
        this.zzd = httpURLConnection;
        return httpURLConnection;
    }

    public HttpURLConnection zzn(zzfpb zzfpbVar, final int r3, final int r4) throws IOException {
        this.zza = new zzfsv() { // from class: com.google.android.gms.internal.ads.zzfos
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                Integer valueOf;
                valueOf = Integer.valueOf(r3);
                return valueOf;
            }
        };
        this.zzb = new zzfsv() { // from class: com.google.android.gms.internal.ads.zzfot
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                Integer valueOf;
                valueOf = Integer.valueOf(r4);
                return valueOf;
            }
        };
        this.zzc = zzfpbVar;
        return zzm();
    }

    public HttpURLConnection zzo(final Network network, final URL url, final int r4, final int r5) throws IOException {
        this.zza = new zzfsv() { // from class: com.google.android.gms.internal.ads.zzfou
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                Integer valueOf;
                valueOf = Integer.valueOf(r4);
                return valueOf;
            }
        };
        this.zzb = new zzfsv() { // from class: com.google.android.gms.internal.ads.zzfov
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                Integer valueOf;
                valueOf = Integer.valueOf(r5);
                return valueOf;
            }
        };
        this.zzc = new zzfpb() { // from class: com.google.android.gms.internal.ads.zzfow
            @Override // com.google.android.gms.internal.ads.zzfpb
            public final URLConnection zza() {
                URLConnection openConnection;
                openConnection = network.openConnection(url);
                return openConnection;
            }
        };
        return zzm();
    }

    public URLConnection zzr(final URL url, final int r3) throws IOException {
        this.zza = new zzfsv() { // from class: com.google.android.gms.internal.ads.zzfox
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                Integer valueOf;
                valueOf = Integer.valueOf(r3);
                return valueOf;
            }
        };
        this.zzc = new zzfpb() { // from class: com.google.android.gms.internal.ads.zzfoy
            @Override // com.google.android.gms.internal.ads.zzfpb
            public final URLConnection zza() {
                URLConnection openConnection;
                openConnection = url.openConnection();
                return openConnection;
            }
        };
        return zzm();
    }
}
