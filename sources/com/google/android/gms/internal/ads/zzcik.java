package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.common.internal.Preconditions;
import com.onesignal.influence.OSInfluenceConstants;
import java.util.HashMap;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzcik extends FrameLayout implements zzcib {
    final zzciy zza;
    private final zzciw zzb;
    private final FrameLayout zzc;
    private final View zzd;
    private final zzbjn zze;
    private final long zzf;
    private final zzcic zzg;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;
    private long zzl;
    private long zzm;
    private String zzn;
    private String[] zzo;
    private Bitmap zzp;
    private final ImageView zzq;
    private boolean zzr;

    public zzcik(Context context, zzciw zzciwVar, int r18, boolean z, zzbjn zzbjnVar, zzciv zzcivVar) {
        super(context);
        zzcic zzciaVar;
        this.zzb = zzciwVar;
        this.zze = zzbjnVar;
        FrameLayout frameLayout = new FrameLayout(context);
        this.zzc = frameLayout;
        addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        Preconditions.checkNotNull(zzciwVar.zzm());
        zzcid zzcidVar = zzciwVar.zzm().zza;
        zzcix zzcixVar = new zzcix(context, zzciwVar.zzp(), zzciwVar.zzu(), zzbjnVar, zzciwVar.zzn());
        if (r18 != 2) {
            zzciaVar = new zzcia(context, zzciwVar, z, zzcio.zza(zzciwVar), zzcivVar, new zzcix(context, zzciwVar.zzp(), zzciwVar.zzu(), zzbjnVar, zzciwVar.zzn()));
        } else {
            zzciaVar = new zzcjo(context, zzcixVar, zzciwVar, z, zzcio.zza(zzciwVar), zzcivVar);
        }
        this.zzg = zzciaVar;
        View view = new View(context);
        this.zzd = view;
        view.setBackgroundColor(0);
        frameLayout.addView(zzciaVar, new FrameLayout.LayoutParams(-1, -1, 17));
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzD)).booleanValue()) {
            frameLayout.addView(view, new FrameLayout.LayoutParams(-1, -1));
            frameLayout.bringChildToFront(view);
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzA)).booleanValue()) {
            zzm();
        }
        this.zzq = new ImageView(context);
        this.zzf = ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzF)).longValue();
        boolean booleanValue = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzC)).booleanValue();
        this.zzk = booleanValue;
        if (zzbjnVar != null) {
            zzbjnVar.zzd("spinner_used", true != booleanValue ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
        this.zza = new zzciy(this);
        zzciaVar.zzr(this);
    }

    private final void zzI() {
        if (this.zzb.zzk() == null || !this.zzi || this.zzj) {
            return;
        }
        this.zzb.zzk().getWindow().clearFlags(128);
        this.zzi = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzJ(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, str);
        String str2 = null;
        for (String str3 : strArr) {
            if (str2 == null) {
                str2 = str3;
            } else {
                hashMap.put(str2, str3);
                str2 = null;
            }
        }
        this.zzb.zzd("onVideoEvent", hashMap);
    }

    private final boolean zzK() {
        return this.zzq.getParent() != null;
    }

    public final void finalize() throws Throwable {
        try {
            this.zza.zza();
            final zzcic zzcicVar = this.zzg;
            if (zzcicVar != null) {
                zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcie
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzcic.this.zzt();
                    }
                });
            }
        } finally {
            super.finalize();
        }
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(final boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.zza.zzb();
        } else {
            this.zza.zza();
            this.zzm = this.zzl;
        }
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcif
            @Override // java.lang.Runnable
            public final void run() {
                zzcik.this.zzp(z);
            }
        });
    }

    @Override // android.view.View, com.google.android.gms.internal.ads.zzcib
    public final void onWindowVisibilityChanged(int r3) {
        boolean z;
        super.onWindowVisibilityChanged(r3);
        if (r3 == 0) {
            this.zza.zzb();
            z = true;
        } else {
            this.zza.zza();
            this.zzm = this.zzl;
            z = false;
        }
        com.google.android.gms.ads.internal.util.zzs.zza.post(new zzcij(this, z));
    }

    public final void zzA(int r2) {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzz(r2);
    }

    public final void zzB(int r3) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzD)).booleanValue()) {
            this.zzc.setBackgroundColor(r3);
            this.zzd.setBackgroundColor(r3);
        }
    }

    public final void zzC(int r2) {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzA(r2);
    }

    public final void zzD(String str, String[] strArr) {
        this.zzn = str;
        this.zzo = strArr;
    }

    public final void zzE(int r3, int r4, int r5, int r6) {
        if (com.google.android.gms.ads.internal.util.zze.zzc()) {
            com.google.android.gms.ads.internal.util.zze.zza("Set video bounds to x:" + r3 + ";y:" + r4 + ";w:" + r5 + ";h:" + r6);
        }
        if (r5 == 0 || r6 == 0) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(r5, r6);
        layoutParams.setMargins(r3, r4, 0, 0);
        this.zzc.setLayoutParams(layoutParams);
        requestLayout();
    }

    public final void zzF(float f) {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzb.zze(f);
        zzcicVar.zzn();
    }

    public final void zzG(float f, float f2) {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar != null) {
            zzcicVar.zzu(f, f2);
        }
    }

    public final void zzH() {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzb.zzd(false);
        zzcicVar.zzn();
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zza() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbE)).booleanValue()) {
            this.zza.zza();
        }
        zzJ("ended", new String[0]);
        zzI();
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zzb(String str, String str2) {
        zzJ("error", "what", str, "extra", str2);
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zzc(String str, String str2) {
        zzJ("exception", "what", "ExoPlayerAdapter exception", "extra", str2);
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zzd() {
        zzJ("pause", new String[0]);
        zzI();
        this.zzh = false;
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zze() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbE)).booleanValue()) {
            this.zza.zzb();
        }
        if (this.zzb.zzk() != null && !this.zzi) {
            boolean z = (this.zzb.zzk().getWindow().getAttributes().flags & 128) != 0;
            this.zzj = z;
            if (!z) {
                this.zzb.zzk().getWindow().addFlags(128);
                this.zzi = true;
            }
        }
        this.zzh = true;
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zzf() {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar != null && this.zzm == 0) {
            zzJ("canplaythrough", "duration", String.valueOf(zzcicVar.zzc() / 1000.0f), "videoWidth", String.valueOf(this.zzg.zze()), "videoHeight", String.valueOf(this.zzg.zzd()));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zzg() {
        this.zzd.setVisibility(4);
        com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcig
            @Override // java.lang.Runnable
            public final void run() {
                zzcik.this.zzo();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zzh() {
        this.zza.zzb();
        com.google.android.gms.ads.internal.util.zzs.zza.post(new zzcih(this));
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zzi() {
        if (this.zzr && this.zzp != null && !zzK()) {
            this.zzq.setImageBitmap(this.zzp);
            this.zzq.invalidate();
            this.zzc.addView(this.zzq, new FrameLayout.LayoutParams(-1, -1));
            this.zzc.bringChildToFront(this.zzq);
        }
        this.zza.zza();
        this.zzm = this.zzl;
        com.google.android.gms.ads.internal.util.zzs.zza.post(new zzcii(this));
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zzj(int r4, int r5) {
        if (this.zzk) {
            int max = Math.max(r4 / ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzE)).intValue(), 1);
            int max2 = Math.max(r5 / ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzE)).intValue(), 1);
            Bitmap bitmap = this.zzp;
            if (bitmap != null && bitmap.getWidth() == max && this.zzp.getHeight() == max2) {
                return;
            }
            this.zzp = Bitmap.createBitmap(max, max2, Bitmap.Config.ARGB_8888);
            this.zzr = false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcib
    public final void zzk() {
        if (this.zzh && zzK()) {
            this.zzc.removeView(this.zzq);
        }
        if (this.zzg == null || this.zzp == null) {
            return;
        }
        long elapsedRealtime = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
        if (this.zzg.getBitmap(this.zzp) != null) {
            this.zzr = true;
        }
        long elapsedRealtime2 = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime() - elapsedRealtime;
        if (com.google.android.gms.ads.internal.util.zze.zzc()) {
            com.google.android.gms.ads.internal.util.zze.zza("Spinner frame grab took " + elapsedRealtime2 + "ms");
        }
        if (elapsedRealtime2 > this.zzf) {
            com.google.android.gms.ads.internal.util.zze.zzj("Spinner frame grab crossed jank threshold! Suspending spinner.");
            this.zzk = false;
            this.zzp = null;
            zzbjn zzbjnVar = this.zze;
            if (zzbjnVar != null) {
                zzbjnVar.zzd("spinner_jank", Long.toString(elapsedRealtime2));
            }
        }
    }

    public final void zzm() {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        TextView textView = new TextView(zzcicVar.getContext());
        textView.setText("AdMob - ".concat(this.zzg.zzj()));
        textView.setTextColor(SupportMenu.CATEGORY_MASK);
        textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
        this.zzc.addView(textView, new FrameLayout.LayoutParams(-2, -2, 17));
        this.zzc.bringChildToFront(textView);
    }

    public final void zzn() {
        this.zza.zza();
        zzcic zzcicVar = this.zzg;
        if (zzcicVar != null) {
            zzcicVar.zzt();
        }
        zzI();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzo() {
        zzJ("firstFrameRendered", new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzp(boolean z) {
        zzJ("windowFocusChanged", "hasWindowFocus", String.valueOf(z));
    }

    public final void zzq() {
        if (this.zzg == null) {
            return;
        }
        if (!TextUtils.isEmpty(this.zzn)) {
            this.zzg.zzB(this.zzn, this.zzo);
        } else {
            zzJ("no_src", new String[0]);
        }
    }

    public final void zzr() {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzb.zzd(true);
        zzcicVar.zzn();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzs() {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        long zza = zzcicVar.zza();
        if (this.zzl == zza || zza <= 0) {
            return;
        }
        float f = ((float) zza) / 1000.0f;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue()) {
            zzJ("timeupdate", OSInfluenceConstants.TIME, String.valueOf(f), "totalBytes", String.valueOf(this.zzg.zzh()), "qoeCachedBytes", String.valueOf(this.zzg.zzf()), "qoeLoadedBytes", String.valueOf(this.zzg.zzg()), "droppedFrames", String.valueOf(this.zzg.zzb()), "reportTime", String.valueOf(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis()));
        } else {
            zzJ("timeupdate", OSInfluenceConstants.TIME, String.valueOf(f));
        }
        this.zzl = zza;
    }

    public final void zzt() {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzo();
    }

    public final void zzu() {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzp();
    }

    public final void zzv(int r2) {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzq(r2);
    }

    public final void zzw(MotionEvent motionEvent) {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.dispatchTouchEvent(motionEvent);
    }

    public final void zzx(int r2) {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzw(r2);
    }

    public final void zzy(int r2) {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzx(r2);
    }

    public final void zzz(int r2) {
        zzcic zzcicVar = this.zzg;
        if (zzcicVar == null) {
            return;
        }
        zzcicVar.zzy(r2);
    }
}
