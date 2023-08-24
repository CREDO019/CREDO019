package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.view.Surface;
import android.view.TextureView;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcjo extends zzcic implements TextureView.SurfaceTextureListener, zzcim {
    private final zzciw zzc;
    private final zzcix zzd;
    private final zzciv zze;
    private zzcib zzf;
    private Surface zzg;
    private zzcin zzh;
    private String zzi;
    private String[] zzj;
    private boolean zzk;
    private int zzl;
    private zzciu zzm;
    private final boolean zzn;
    private boolean zzo;
    private boolean zzp;
    private int zzq;
    private int zzr;
    private float zzs;

    public zzcjo(Context context, zzcix zzcixVar, zzciw zzciwVar, boolean z, boolean z2, zzciv zzcivVar) {
        super(context);
        this.zzl = 1;
        this.zzc = zzciwVar;
        this.zzd = zzcixVar;
        this.zzn = z;
        this.zze = zzcivVar;
        setSurfaceTextureListener(this);
        zzcixVar.zza(this);
    }

    private static String zzS(String str, Exception exc) {
        String canonicalName = exc.getClass().getCanonicalName();
        String message = exc.getMessage();
        return str + "/" + canonicalName + ParameterizedMessage.ERROR_MSG_SEPARATOR + message;
    }

    private final void zzT() {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            zzcinVar.zzM(true);
        }
    }

    private final void zzU() {
        if (this.zzo) {
            return;
        }
        this.zzo = true;
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcji
            @Override // java.lang.Runnable
            public final void run() {
                zzcjo.this.zzH();
            }
        });
        zzn();
        this.zzd.zzb();
        if (this.zzp) {
            zzp();
        }
    }

    private final void zzV(boolean z) {
        zzcin zzcinVar = this.zzh;
        if ((zzcinVar != null && !z) || this.zzi == null || this.zzg == null) {
            return;
        }
        if (z) {
            if (zzad()) {
                zzcinVar.zzQ();
                zzX();
            } else {
                com.google.android.gms.ads.internal.util.zze.zzj("No valid ExoPlayerAdapter exists when switch source.");
                return;
            }
        }
        if (this.zzi.startsWith("cache:")) {
            zzckz zzr = this.zzc.zzr(this.zzi);
            if (zzr instanceof zzcli) {
                zzcin zzj = ((zzcli) zzr).zzj();
                this.zzh = zzj;
                if (!zzj.zzR()) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Precached video player has been released.");
                    return;
                }
            } else if (zzr instanceof zzclf) {
                zzclf zzclfVar = (zzclf) zzr;
                String zzE = zzE();
                ByteBuffer zzl = zzclfVar.zzl();
                boolean zzm = zzclfVar.zzm();
                String zzi = zzclfVar.zzi();
                if (zzi == null) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Stream cache URL is null.");
                    return;
                }
                zzcin zzD = zzD();
                this.zzh = zzD;
                zzD.zzD(new Uri[]{Uri.parse(zzi)}, zzE, zzl, zzm);
            } else {
                com.google.android.gms.ads.internal.util.zze.zzj("Stream cache miss: ".concat(String.valueOf(this.zzi)));
                return;
            }
        } else {
            this.zzh = zzD();
            String zzE2 = zzE();
            Uri[] uriArr = new Uri[this.zzj.length];
            int r2 = 0;
            while (true) {
                String[] strArr = this.zzj;
                if (r2 >= strArr.length) {
                    break;
                }
                uriArr[r2] = Uri.parse(strArr[r2]);
                r2++;
            }
            this.zzh.zzC(uriArr, zzE2);
        }
        this.zzh.zzI(this);
        zzZ(this.zzg, false);
        if (this.zzh.zzR()) {
            int zzt = this.zzh.zzt();
            this.zzl = zzt;
            if (zzt == 3) {
                zzU();
            }
        }
    }

    private final void zzW() {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            zzcinVar.zzM(false);
        }
    }

    private final void zzX() {
        if (this.zzh != null) {
            zzZ(null, true);
            zzcin zzcinVar = this.zzh;
            if (zzcinVar != null) {
                zzcinVar.zzI(null);
                this.zzh.zzE();
                this.zzh = null;
            }
            this.zzl = 1;
            this.zzk = false;
            this.zzo = false;
            this.zzp = false;
        }
    }

    private final void zzY(float f, boolean z) {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            try {
                zzcinVar.zzP(f, false);
                return;
            } catch (IOException e) {
                zzcgn.zzk("", e);
                return;
            }
        }
        com.google.android.gms.ads.internal.util.zze.zzj("Trying to set volume before player is initialized.");
    }

    private final void zzZ(Surface surface, boolean z) {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            try {
                zzcinVar.zzO(surface, z);
                return;
            } catch (IOException e) {
                zzcgn.zzk("", e);
                return;
            }
        }
        com.google.android.gms.ads.internal.util.zze.zzj("Trying to set surface before player is initialized.");
    }

    private final void zzaa() {
        zzab(this.zzq, this.zzr);
    }

    private final void zzab(int r1, int r2) {
        float f = r2 > 0 ? r1 / r2 : 1.0f;
        if (this.zzs != f) {
            this.zzs = f;
            requestLayout();
        }
    }

    private final boolean zzac() {
        return zzad() && this.zzl != 1;
    }

    private final boolean zzad() {
        zzcin zzcinVar = this.zzh;
        return (zzcinVar == null || !zzcinVar.zzR() || this.zzk) ? false : true;
    }

    @Override // android.view.View
    protected final void onMeasure(int r5, int r6) {
        super.onMeasure(r5, r6);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f = this.zzs;
        if (f != 0.0f && this.zzm == null) {
            float f2 = measuredWidth;
            float f3 = f2 / measuredHeight;
            if (f > f3) {
                measuredHeight = (int) (f2 / f);
            }
            if (f < f3) {
                measuredWidth = (int) (measuredHeight * f);
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
        zzciu zzciuVar = this.zzm;
        if (zzciuVar != null) {
            zzciuVar.zzc(measuredWidth, measuredHeight);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int r4, int r5) {
        if (this.zzn) {
            zzciu zzciuVar = new zzciu(getContext());
            this.zzm = zzciuVar;
            zzciuVar.zzd(surfaceTexture, r4, r5);
            this.zzm.start();
            SurfaceTexture zzb = this.zzm.zzb();
            if (zzb != null) {
                surfaceTexture = zzb;
            } else {
                this.zzm.zze();
                this.zzm = null;
            }
        }
        Surface surface = new Surface(surfaceTexture);
        this.zzg = surface;
        if (this.zzh == null) {
            zzV(false);
        } else {
            zzZ(surface, true);
            if (!this.zze.zza) {
                zzT();
            }
        }
        if (this.zzq == 0 || this.zzr == 0) {
            zzab(r4, r5);
        } else {
            zzaa();
        }
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjj
            @Override // java.lang.Runnable
            public final void run() {
                zzcjo.this.zzL();
            }
        });
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzo();
        zzciu zzciuVar = this.zzm;
        if (zzciuVar != null) {
            zzciuVar.zze();
            this.zzm = null;
        }
        if (this.zzh != null) {
            zzW();
            Surface surface = this.zzg;
            if (surface != null) {
                surface.release();
            }
            this.zzg = null;
            zzZ(null, true);
        }
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjm
            @Override // java.lang.Runnable
            public final void run() {
                zzcjo.this.zzM();
            }
        });
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, final int r3, final int r4) {
        zzciu zzciuVar = this.zzm;
        if (zzciuVar != null) {
            zzciuVar.zzc(r3, r4);
        }
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjl
            @Override // java.lang.Runnable
            public final void run() {
                zzcjo.this.zzN(r3, r4);
            }
        });
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzd.zzf(this);
        this.zza.zza(surfaceTexture, this.zzf);
    }

    @Override // android.view.View
    protected final void onWindowVisibilityChanged(final int r3) {
        com.google.android.gms.ads.internal.util.zze.zza("AdExoPlayerView3 window visibility changed to " + r3);
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjk
            @Override // java.lang.Runnable
            public final void run() {
                zzcjo.this.zzP(r3);
            }
        });
        super.onWindowVisibilityChanged(r3);
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzA(int r2) {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            zzcinVar.zzN(r2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzC(int r1, int r2) {
        this.zzq = r1;
        this.zzr = r2;
        zzaa();
    }

    final zzcin zzD() {
        if (this.zze.zzm) {
            return new zzcma(this.zzc.getContext(), this.zze, this.zzc);
        }
        return new zzcke(this.zzc.getContext(), this.zze, this.zzc);
    }

    final String zzE() {
        return com.google.android.gms.ads.internal.zzt.zzq().zzc(this.zzc.getContext(), this.zzc.zzp().zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzF(String str) {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.zzb("ExoPlayerAdapter error", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzG() {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.zza();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzH() {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.zzf();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzI(boolean z, long j) {
        this.zzc.zzx(z, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzJ(String str) {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.zzc("ExoPlayerAdapter exception", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzK() {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.zzg();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzL() {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.zzh();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzM() {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.zzi();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzN(int r2, int r3) {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.zzj(r2, r3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzO() {
        zzY(this.zzb.zza(), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzP(int r2) {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.onWindowVisibilityChanged(r2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzQ() {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.zzd();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzR() {
        zzcib zzcibVar = this.zzf;
        if (zzcibVar != null) {
            zzcibVar.zze();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final int zza() {
        if (zzac()) {
            return (int) this.zzh.zzy();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final int zzb() {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            return zzcinVar.zzr();
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final int zzc() {
        if (zzac()) {
            return (int) this.zzh.zzz();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final int zzd() {
        return this.zzr;
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final int zze() {
        return this.zzq;
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final long zzf() {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            return zzcinVar.zzx();
        }
        return -1L;
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final long zzg() {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            return zzcinVar.zzA();
        }
        return -1L;
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final long zzh() {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            return zzcinVar.zzB();
        }
        return -1L;
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzi(final boolean z, final long j) {
        if (this.zzc != null) {
            zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjb
                @Override // java.lang.Runnable
                public final void run() {
                    zzcjo.this.zzI(z, j);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final String zzj() {
        return "ExoPlayer/3".concat(true != this.zzn ? "" : " spherical");
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzk(String str, Exception exc) {
        final String zzS = zzS(str, exc);
        com.google.android.gms.ads.internal.util.zze.zzj("ExoPlayerAdapter error: ".concat(zzS));
        this.zzk = true;
        if (this.zze.zza) {
            zzW();
        }
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcje
            @Override // java.lang.Runnable
            public final void run() {
                zzcjo.this.zzF(zzS);
            }
        });
        com.google.android.gms.ads.internal.zzt.zzp().zzs(exc, "AdExoPlayerView.onError");
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzl(String str, Exception exc) {
        final String zzS = zzS("onLoadException", exc);
        com.google.android.gms.ads.internal.util.zze.zzj("ExoPlayerAdapter exception: ".concat(zzS));
        com.google.android.gms.ads.internal.zzt.zzp().zzs(exc, "AdExoPlayerView.onException");
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjd
            @Override // java.lang.Runnable
            public final void run() {
                zzcjo.this.zzJ(zzS);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzm(int r2) {
        if (this.zzl != r2) {
            this.zzl = r2;
            if (r2 == 3) {
                zzU();
            } else if (r2 != 4) {
            } else {
                if (this.zze.zza) {
                    zzW();
                }
                this.zzd.zze();
                this.zzb.zzc();
                com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjc
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzcjo.this.zzG();
                    }
                });
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic, com.google.android.gms.internal.ads.zzciz
    public final void zzn() {
        if (this.zze.zzm) {
            com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjg
                @Override // java.lang.Runnable
                public final void run() {
                    zzcjo.this.zzO();
                }
            });
        } else {
            zzY(this.zzb.zza(), false);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzo() {
        if (zzac()) {
            if (this.zze.zza) {
                zzW();
            }
            this.zzh.zzL(false);
            this.zzd.zze();
            this.zzb.zzc();
            com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjh
                @Override // java.lang.Runnable
                public final void run() {
                    zzcjo.this.zzQ();
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzp() {
        if (!zzac()) {
            this.zzp = true;
            return;
        }
        if (this.zze.zza) {
            zzT();
        }
        this.zzh.zzL(true);
        this.zzd.zzc();
        this.zzb.zzb();
        this.zza.zzb();
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjn
            @Override // java.lang.Runnable
            public final void run() {
                zzcjo.this.zzR();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzq(int r4) {
        if (zzac()) {
            this.zzh.zzF(r4);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzr(zzcib zzcibVar) {
        this.zzf = zzcibVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzs(String str) {
        if (str != null) {
            zzB(str, null);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzt() {
        if (zzad()) {
            this.zzh.zzQ();
            zzX();
        }
        this.zzd.zze();
        this.zzb.zzc();
        this.zzd.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzu(float f, float f2) {
        zzciu zzciuVar = this.zzm;
        if (zzciuVar != null) {
            zzciuVar.zzf(f, f2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzv() {
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcjf
            @Override // java.lang.Runnable
            public final void run() {
                zzcjo.this.zzK();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzw(int r2) {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            zzcinVar.zzG(r2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzx(int r2) {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            zzcinVar.zzH(r2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzy(int r2) {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            zzcinVar.zzJ(r2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzz(int r2) {
        zzcin zzcinVar = this.zzh;
        if (zzcinVar != null) {
            zzcinVar.zzK(r2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcic
    public final void zzB(String str, String[] strArr) {
        if (str == null) {
            return;
        }
        boolean z = true;
        if (strArr != null) {
            this.zzj = (String[]) Arrays.copyOf(strArr, strArr.length);
        } else {
            this.zzj = new String[]{str};
        }
        String str2 = this.zzi;
        z = (!this.zze.zzn || str2 == null || str.equals(str2) || this.zzl != 4) ? false : false;
        this.zzi = str;
        zzV(z);
    }
}
