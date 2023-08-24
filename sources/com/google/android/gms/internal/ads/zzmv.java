package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.metrics.LogSessionId;
import android.media.metrics.MediaMetricsManager;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackSession;
import android.media.metrics.TrackChangeEvent;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.PlaybackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzmv implements zzkp, zzmw {
    private final Context zza;
    private final zzmx zzb;
    private final PlaybackSession zzc;
    private String zzi;
    private PlaybackMetrics.Builder zzj;
    private int zzk;
    private zzbw zzn;
    private zzmu zzo;
    private zzmu zzp;
    private zzmu zzq;
    private zzaf zzr;
    private zzaf zzs;
    private zzaf zzt;
    private boolean zzu;
    private boolean zzv;
    private int zzw;
    private int zzx;
    private int zzy;
    private boolean zzz;
    private final zzcm zze = new zzcm();
    private final zzck zzf = new zzck();
    private final HashMap zzh = new HashMap();
    private final HashMap zzg = new HashMap();
    private final long zzd = SystemClock.elapsedRealtime();
    private int zzl = 0;
    private int zzm = 0;

    private zzmv(Context context, PlaybackSession playbackSession) {
        this.zza = context.getApplicationContext();
        this.zzc = playbackSession;
        zzmt zzmtVar = new zzmt(zzmt.zza);
        this.zzb = zzmtVar;
        zzmtVar.zzg(this);
    }

    public static zzmv zzb(Context context) {
        MediaMetricsManager mediaMetricsManager = (MediaMetricsManager) context.getSystemService("media_metrics");
        if (mediaMetricsManager == null) {
            return null;
        }
        return new zzmv(context, mediaMetricsManager.createPlaybackSession());
    }

    private static int zzr(int r0) {
        switch (zzel.zzl(r0)) {
            case PlaybackException.ERROR_CODE_DRM_PROVISIONING_FAILED /* 6002 */:
                return 24;
            case PlaybackException.ERROR_CODE_DRM_CONTENT_ERROR /* 6003 */:
                return 28;
            case PlaybackException.ERROR_CODE_DRM_LICENSE_ACQUISITION_FAILED /* 6004 */:
                return 25;
            case PlaybackException.ERROR_CODE_DRM_DISALLOWED_OPERATION /* 6005 */:
                return 26;
            default:
                return 27;
        }
    }

    private final void zzs() {
        PlaybackMetrics.Builder builder = this.zzj;
        if (builder != null && this.zzz) {
            builder.setAudioUnderrunCount(this.zzy);
            this.zzj.setVideoFramesDropped(this.zzw);
            this.zzj.setVideoFramesPlayed(this.zzx);
            Long l = (Long) this.zzg.get(this.zzi);
            this.zzj.setNetworkTransferDurationMillis(l == null ? 0L : l.longValue());
            Long l2 = (Long) this.zzh.get(this.zzi);
            this.zzj.setNetworkBytesRead(l2 == null ? 0L : l2.longValue());
            this.zzj.setStreamSource((l2 == null || l2.longValue() <= 0) ? 0 : 1);
            this.zzc.reportPlaybackMetrics(this.zzj.build());
        }
        this.zzj = null;
        this.zzi = null;
        this.zzy = 0;
        this.zzw = 0;
        this.zzx = 0;
        this.zzr = null;
        this.zzs = null;
        this.zzt = null;
        this.zzz = false;
    }

    private final void zzt(long j, zzaf zzafVar, int r10) {
        if (zzel.zzT(this.zzs, zzafVar)) {
            return;
        }
        int r5 = this.zzs == null ? 1 : 0;
        this.zzs = zzafVar;
        zzx(0, j, zzafVar, r5);
    }

    private final void zzu(long j, zzaf zzafVar, int r10) {
        if (zzel.zzT(this.zzt, zzafVar)) {
            return;
        }
        int r5 = this.zzt == null ? 1 : 0;
        this.zzt = zzafVar;
        zzx(2, j, zzafVar, r5);
    }

    @RequiresNonNull({"metricsBuilder"})
    private final void zzv(zzcn zzcnVar, zzsg zzsgVar) {
        int zza;
        PlaybackMetrics.Builder builder = this.zzj;
        if (zzsgVar == null || (zza = zzcnVar.zza(zzsgVar.zza)) == -1) {
            return;
        }
        int r2 = 0;
        zzcnVar.zzd(zza, this.zzf, false);
        zzcnVar.zze(this.zzf.zzd, this.zze, 0L);
        zzay zzayVar = this.zze.zzd.zzd;
        if (zzayVar != null) {
            int zzp = zzel.zzp(zzayVar.zza);
            r2 = zzp != 0 ? zzp != 1 ? zzp != 2 ? 1 : 4 : 5 : 3;
        }
        builder.setStreamType(r2);
        zzcm zzcmVar = this.zze;
        if (zzcmVar.zzn != C1856C.TIME_UNSET && !zzcmVar.zzl && !zzcmVar.zzi && !zzcmVar.zzb()) {
            builder.setMediaDurationMillis(zzel.zzz(this.zze.zzn));
        }
        builder.setPlaybackType(true != this.zze.zzb() ? 1 : 2);
        this.zzz = true;
    }

    private final void zzw(long j, zzaf zzafVar, int r10) {
        if (zzel.zzT(this.zzr, zzafVar)) {
            return;
        }
        int r5 = this.zzr == null ? 1 : 0;
        this.zzr = zzafVar;
        zzx(1, j, zzafVar, r5);
    }

    private final void zzx(int r4, long j, zzaf zzafVar, int r8) {
        TrackChangeEvent.Builder timeSinceCreatedMillis = new TrackChangeEvent.Builder(r4).setTimeSinceCreatedMillis(j - this.zzd);
        if (zzafVar != null) {
            timeSinceCreatedMillis.setTrackState(1);
            timeSinceCreatedMillis.setTrackChangeReason(r8 != 1 ? 1 : 2);
            String str = zzafVar.zzl;
            if (str != null) {
                timeSinceCreatedMillis.setContainerMimeType(str);
            }
            String str2 = zzafVar.zzm;
            if (str2 != null) {
                timeSinceCreatedMillis.setSampleMimeType(str2);
            }
            String str3 = zzafVar.zzj;
            if (str3 != null) {
                timeSinceCreatedMillis.setCodecName(str3);
            }
            int r82 = zzafVar.zzi;
            if (r82 != -1) {
                timeSinceCreatedMillis.setBitrate(r82);
            }
            int r83 = zzafVar.zzr;
            if (r83 != -1) {
                timeSinceCreatedMillis.setWidth(r83);
            }
            int r84 = zzafVar.zzs;
            if (r84 != -1) {
                timeSinceCreatedMillis.setHeight(r84);
            }
            int r85 = zzafVar.zzz;
            if (r85 != -1) {
                timeSinceCreatedMillis.setChannelCount(r85);
            }
            int r86 = zzafVar.zzA;
            if (r86 != -1) {
                timeSinceCreatedMillis.setAudioSampleRate(r86);
            }
            String str4 = zzafVar.zzd;
            if (str4 != null) {
                String[] zzag = zzel.zzag(str4, "-");
                Pair create = Pair.create(zzag[0], zzag.length >= 2 ? zzag[1] : null);
                timeSinceCreatedMillis.setLanguage((String) create.first);
                if (create.second != null) {
                    timeSinceCreatedMillis.setLanguageRegion((String) create.second);
                }
            }
            float f = zzafVar.zzt;
            if (f != -1.0f) {
                timeSinceCreatedMillis.setVideoFrameRate(f);
            }
        } else {
            timeSinceCreatedMillis.setTrackState(0);
        }
        this.zzz = true;
        this.zzc.reportTrackChangeEvent(timeSinceCreatedMillis.build());
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    private final boolean zzy(zzmu zzmuVar) {
        return zzmuVar != null && zzmuVar.zzc.equals(this.zzb.zzd());
    }

    public final LogSessionId zza() {
        return this.zzc.getSessionId();
    }

    @Override // com.google.android.gms.internal.ads.zzmw
    public final void zzc(zzkn zzknVar, String str) {
        zzsg zzsgVar = zzknVar.zzd;
        if (zzsgVar == null || !zzsgVar.zzb()) {
            zzs();
            this.zzi = str;
            this.zzj = new PlaybackMetrics.Builder().setPlayerName("AndroidXMedia3").setPlayerVersion("1.0.0-beta01");
            zzv(zzknVar.zzb, zzknVar.zzd);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzmw
    public final void zzd(zzkn zzknVar, String str, boolean z) {
        zzsg zzsgVar = zzknVar.zzd;
        if ((zzsgVar == null || !zzsgVar.zzb()) && str.equals(this.zzi)) {
            zzs();
        }
        this.zzg.remove(str);
        this.zzh.remove(str);
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final /* synthetic */ void zze(zzkn zzknVar, zzaf zzafVar, zzgr zzgrVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzf(zzkn zzknVar, int r7, long j, long j2) {
        zzsg zzsgVar = zzknVar.zzd;
        if (zzsgVar != null) {
            String zze = this.zzb.zze(zzknVar.zzb, zzsgVar);
            Long l = (Long) this.zzh.get(zze);
            Long l2 = (Long) this.zzg.get(zze);
            this.zzh.put(zze, Long.valueOf((l == null ? 0L : l.longValue()) + j));
            this.zzg.put(zze, Long.valueOf((l2 != null ? l2.longValue() : 0L) + r7));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzg(zzkn zzknVar, zzsc zzscVar) {
        zzsg zzsgVar = zzknVar.zzd;
        if (zzsgVar == null) {
            return;
        }
        zzaf zzafVar = zzscVar.zzb;
        Objects.requireNonNull(zzafVar);
        zzmu zzmuVar = new zzmu(zzafVar, 0, this.zzb.zze(zzknVar.zzb, zzsgVar));
        int r6 = zzscVar.zza;
        if (r6 != 0) {
            if (r6 == 1) {
                this.zzp = zzmuVar;
                return;
            } else if (r6 != 2) {
                if (r6 != 3) {
                    return;
                }
                this.zzq = zzmuVar;
                return;
            }
        }
        this.zzo = zzmuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final /* synthetic */ void zzh(zzkn zzknVar, int r2, long j) {
    }

    /* JADX WARN: Removed duplicated region for block: B:186:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:271:? A[RETURN, SYNTHETIC] */
    @Override // com.google.android.gms.internal.ads.zzkp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzi(com.google.android.gms.internal.ads.zzcg r21, com.google.android.gms.internal.ads.zzko r22) {
        /*
            Method dump skipped, instructions count: 1038
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzmv.zzi(com.google.android.gms.internal.ads.zzcg, com.google.android.gms.internal.ads.zzko):void");
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzj(zzkn zzknVar, zzrx zzrxVar, zzsc zzscVar, IOException iOException, boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final /* synthetic */ void zzk(zzkn zzknVar, int r2) {
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzl(zzkn zzknVar, zzbw zzbwVar) {
        this.zzn = zzbwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzm(zzkn zzknVar, zzcf zzcfVar, zzcf zzcfVar2, int r4) {
        if (r4 == 1) {
            this.zzu = true;
            r4 = 1;
        }
        this.zzk = r4;
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final /* synthetic */ void zzn(zzkn zzknVar, Object obj, long j) {
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzo(zzkn zzknVar, zzgq zzgqVar) {
        this.zzw += zzgqVar.zzg;
        this.zzx += zzgqVar.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final /* synthetic */ void zzp(zzkn zzknVar, zzaf zzafVar, zzgr zzgrVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzq(zzkn zzknVar, zzda zzdaVar) {
        zzmu zzmuVar = this.zzo;
        if (zzmuVar != null) {
            zzaf zzafVar = zzmuVar.zza;
            if (zzafVar.zzs == -1) {
                zzad zzb = zzafVar.zzb();
                zzb.zzX(zzdaVar.zzc);
                zzb.zzF(zzdaVar.zzd);
                this.zzo = new zzmu(zzb.zzY(), 0, zzmuVar.zzc);
            }
        }
    }
}
