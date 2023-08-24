package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfc implements zzev {
    private final Context zza;
    private final List zzb = new ArrayList();
    private final zzev zzc;
    private zzev zzd;
    private zzev zze;
    private zzev zzf;
    private zzev zzg;
    private zzev zzh;
    private zzev zzi;
    private zzev zzj;
    private zzev zzk;

    public zzfc(Context context, zzev zzevVar) {
        this.zza = context.getApplicationContext();
        this.zzc = zzevVar;
    }

    private final zzev zzg() {
        if (this.zze == null) {
            zzeo zzeoVar = new zzeo(this.zza);
            this.zze = zzeoVar;
            zzh(zzeoVar);
        }
        return this.zze;
    }

    private final void zzh(zzev zzevVar) {
        for (int r0 = 0; r0 < this.zzb.size(); r0++) {
            zzevVar.zzf((zzfx) this.zzb.get(r0));
        }
    }

    private static final void zzi(zzev zzevVar, zzfx zzfxVar) {
        if (zzevVar != null) {
            zzevVar.zzf(zzfxVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final long zzb(zzfa zzfaVar) throws IOException {
        zzev zzevVar;
        zzdd.zzf(this.zzk == null);
        String scheme = zzfaVar.zza.getScheme();
        if (zzel.zzW(zzfaVar.zza)) {
            String path = zzfaVar.zza.getPath();
            if (path != null && path.startsWith("/android_asset/")) {
                this.zzk = zzg();
            } else {
                if (this.zzd == null) {
                    zzfl zzflVar = new zzfl();
                    this.zzd = zzflVar;
                    zzh(zzflVar);
                }
                this.zzk = this.zzd;
            }
        } else if (UriUtil.LOCAL_ASSET_SCHEME.equals(scheme)) {
            this.zzk = zzg();
        } else if (UriUtil.LOCAL_CONTENT_SCHEME.equals(scheme)) {
            if (this.zzf == null) {
                zzes zzesVar = new zzes(this.zza);
                this.zzf = zzesVar;
                zzh(zzesVar);
            }
            this.zzk = this.zzf;
        } else if ("rtmp".equals(scheme)) {
            if (this.zzg == null) {
                try {
                    zzev zzevVar2 = (zzev) Class.forName("androidx.media3.datasource.rtmp.RtmpDataSource").getConstructor(new Class[0]).newInstance(new Object[0]);
                    this.zzg = zzevVar2;
                    zzh(zzevVar2);
                } catch (ClassNotFoundException unused) {
                    Log.w("DefaultDataSource", "Attempting to play RTMP stream without depending on the RTMP extension");
                } catch (Exception e) {
                    throw new RuntimeException("Error instantiating RTMP extension", e);
                }
                if (this.zzg == null) {
                    this.zzg = this.zzc;
                }
            }
            this.zzk = this.zzg;
        } else if ("udp".equals(scheme)) {
            if (this.zzh == null) {
                zzfz zzfzVar = new zzfz(2000);
                this.zzh = zzfzVar;
                zzh(zzfzVar);
            }
            this.zzk = this.zzh;
        } else if ("data".equals(scheme)) {
            if (this.zzi == null) {
                zzet zzetVar = new zzet();
                this.zzi = zzetVar;
                zzh(zzetVar);
            }
            this.zzk = this.zzi;
        } else {
            if (RawResourceDataSource.RAW_RESOURCE_SCHEME.equals(scheme) || UriUtil.QUALIFIED_RESOURCE_SCHEME.equals(scheme)) {
                if (this.zzj == null) {
                    zzfv zzfvVar = new zzfv(this.zza);
                    this.zzj = zzfvVar;
                    zzh(zzfvVar);
                }
                zzevVar = this.zzj;
            } else {
                zzevVar = this.zzc;
            }
            this.zzk = zzevVar;
        }
        return this.zzk.zzb(zzfaVar);
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final Uri zzc() {
        zzev zzevVar = this.zzk;
        if (zzevVar == null) {
            return null;
        }
        return zzevVar.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzd() throws IOException {
        zzev zzevVar = this.zzk;
        if (zzevVar != null) {
            try {
                zzevVar.zzd();
            } finally {
                this.zzk = null;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzev, com.google.android.gms.internal.ads.zzfs
    public final Map zze() {
        zzev zzevVar = this.zzk;
        return zzevVar == null ? Collections.emptyMap() : zzevVar.zze();
    }

    @Override // com.google.android.gms.internal.ads.zzr
    public final int zza(byte[] bArr, int r3, int r4) throws IOException {
        zzev zzevVar = this.zzk;
        Objects.requireNonNull(zzevVar);
        return zzevVar.zza(bArr, r3, r4);
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzf(zzfx zzfxVar) {
        Objects.requireNonNull(zzfxVar);
        this.zzc.zzf(zzfxVar);
        this.zzb.add(zzfxVar);
        zzi(this.zzd, zzfxVar);
        zzi(this.zze, zzfxVar);
        zzi(this.zzf, zzfxVar);
        zzi(this.zzg, zzfxVar);
        zzi(this.zzh, zzfxVar);
        zzi(this.zzi, zzfxVar);
        zzi(this.zzj, zzfxVar);
    }
}
