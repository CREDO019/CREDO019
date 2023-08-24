package com.google.android.gms.ads.internal.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import androidx.webkit.internal.AssetHelper;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzcha;
import com.google.android.gms.internal.ads.zzeae;
import com.google.android.gms.internal.ads.zzeai;
import com.google.android.gms.internal.ads.zzfyy;
import com.polidea.multiplatformbleadapter.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzas {
    private final Context zza;
    private final zzeai zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private int zzg;
    private int zzh;
    private PointF zzi;
    private PointF zzj;
    private Handler zzk;
    private Runnable zzl;

    public zzas(Context context) {
        this.zzg = 0;
        this.zzl = new Runnable() { // from class: com.google.android.gms.ads.internal.util.zzar
            @Override // java.lang.Runnable
            public final void run() {
                zzas.this.zzg();
            }
        };
        this.zza = context;
        this.zzh = ViewConfiguration.get(context).getScaledTouchSlop();
        com.google.android.gms.ads.internal.zzt.zzu().zzb();
        this.zzk = com.google.android.gms.ads.internal.zzt.zzu().zza();
        this.zzb = com.google.android.gms.ads.internal.zzt.zzt().zza();
    }

    private final void zzs(Context context) {
        ArrayList arrayList = new ArrayList();
        int zzu = zzu(arrayList, Constants.BluetoothLogLevel.NONE, true);
        final int zzu2 = zzu(arrayList, "Shake", true);
        final int zzu3 = zzu(arrayList, "Flick", true);
        zzeae zzeaeVar = zzeae.NONE;
        int ordinal = this.zzb.zza().ordinal();
        final int r7 = ordinal != 1 ? ordinal != 2 ? zzu : zzu3 : zzu2;
        com.google.android.gms.ads.internal.zzt.zzq();
        AlertDialog.Builder zzG = zzs.zzG(context);
        final AtomicInteger atomicInteger = new AtomicInteger(r7);
        zzG.setTitle("Setup gesture");
        zzG.setSingleChoiceItems((CharSequence[]) arrayList.toArray(new String[0]), r7, new DialogInterface.OnClickListener() { // from class: com.google.android.gms.ads.internal.util.zzaj
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int r2) {
                atomicInteger.set(r2);
            }
        });
        zzG.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() { // from class: com.google.android.gms.ads.internal.util.zzak
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int r2) {
                zzas.this.zzr();
            }
        });
        zzG.setPositiveButton("Save", new DialogInterface.OnClickListener() { // from class: com.google.android.gms.ads.internal.util.zzal
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int r9) {
                zzas.this.zzh(atomicInteger, r7, zzu2, zzu3, dialogInterface, r9);
            }
        });
        zzG.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.google.android.gms.ads.internal.util.zzam
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                zzas.this.zzr();
            }
        });
        zzG.create().show();
    }

    private final boolean zzt(float f, float f2, float f3, float f4) {
        return Math.abs(this.zzi.x - f) < ((float) this.zzh) && Math.abs(this.zzi.y - f2) < ((float) this.zzh) && Math.abs(this.zzj.x - f3) < ((float) this.zzh) && Math.abs(this.zzj.y - f4) < ((float) this.zzh);
    }

    private static final int zzu(List list, String str, boolean z) {
        if (z) {
            list.add(str);
            return list.size() - 1;
        }
        return -1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("{Dialog: ");
        sb.append(this.zzc);
        sb.append(",DebugSignal: ");
        sb.append(this.zzf);
        sb.append(",AFMA Version: ");
        sb.append(this.zze);
        sb.append(",Ad Unit ID: ");
        sb.append(this.zzd);
        sb.append("}");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza() {
        zzs(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb() {
        zzs(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzfyy zzfyyVar) {
        if (!com.google.android.gms.ads.internal.zzt.zzt().zzj(this.zza, this.zzd, this.zze)) {
            com.google.android.gms.ads.internal.zzt.zzt().zzd(this.zza, this.zzd, this.zze);
        } else {
            zzfyyVar.execute(new Runnable() { // from class: com.google.android.gms.ads.internal.util.zzaf
                @Override // java.lang.Runnable
                public final void run() {
                    zzas.this.zzb();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zzfyy zzfyyVar) {
        if (!com.google.android.gms.ads.internal.zzt.zzt().zzj(this.zza, this.zzd, this.zze)) {
            com.google.android.gms.ads.internal.zzt.zzt().zzd(this.zza, this.zzd, this.zze);
        } else {
            zzfyyVar.execute(new Runnable() { // from class: com.google.android.gms.ads.internal.util.zzaq
                @Override // java.lang.Runnable
                public final void run() {
                    zzas.this.zzf();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zze() {
        com.google.android.gms.ads.internal.zzt.zzt().zzc(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf() {
        com.google.android.gms.ads.internal.zzt.zzt().zzc(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzg() {
        this.zzg = 4;
        zzr();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(AtomicInteger atomicInteger, int r2, int r3, int r4, DialogInterface dialogInterface, int r6) {
        if (atomicInteger.get() != r2) {
            if (atomicInteger.get() == r3) {
                this.zzb.zzj(zzeae.SHAKE);
            } else if (atomicInteger.get() == r4) {
                this.zzb.zzj(zzeae.FLICK);
            } else {
                this.zzb.zzj(zzeae.NONE);
            }
        }
        zzr();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(String str, DialogInterface dialogInterface, int r4) {
        com.google.android.gms.ads.internal.zzt.zzq();
        zzs.zzJ(this.zza, Intent.createChooser(new Intent("android.intent.action.SEND").setType(AssetHelper.DEFAULT_MIME_TYPE).putExtra("android.intent.extra.TEXT", str), "Share via"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzj(int r1, int r2, int r3, int r4, int r5, DialogInterface dialogInterface, int r7) {
        if (r7 != r1) {
            if (r7 == r2) {
                zze.zze("Debug mode [Creative Preview] selected.");
                zzcha.zza.execute(new Runnable() { // from class: com.google.android.gms.ads.internal.util.zzac
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzas.this.zzl();
                    }
                });
            } else if (r7 == r3) {
                zze.zze("Debug mode [Troubleshooting] selected.");
                zzcha.zza.execute(new Runnable() { // from class: com.google.android.gms.ads.internal.util.zzag
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzas.this.zzk();
                    }
                });
            } else if (r7 == r4) {
                final zzfyy zzfyyVar = zzcha.zze;
                zzfyy zzfyyVar2 = zzcha.zza;
                if (this.zzb.zzm()) {
                    zzfyyVar.execute(new Runnable() { // from class: com.google.android.gms.ads.internal.util.zzan
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzas.this.zze();
                        }
                    });
                } else {
                    zzfyyVar2.execute(new Runnable() { // from class: com.google.android.gms.ads.internal.util.zzao
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzas.this.zzd(zzfyyVar);
                        }
                    });
                }
            } else if (r7 == r5) {
                final zzfyy zzfyyVar3 = zzcha.zze;
                zzfyy zzfyyVar4 = zzcha.zza;
                if (this.zzb.zzm()) {
                    zzfyyVar3.execute(new Runnable() { // from class: com.google.android.gms.ads.internal.util.zzah
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzas.this.zza();
                        }
                    });
                } else {
                    zzfyyVar4.execute(new Runnable() { // from class: com.google.android.gms.ads.internal.util.zzai
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzas.this.zzc(zzfyyVar3);
                        }
                    });
                }
            }
        } else if (!(this.zza instanceof Activity)) {
            zze.zzi("Can not create dialog without Activity Context");
        } else {
            String str = this.zzc;
            final String str2 = "No debug information";
            if (!TextUtils.isEmpty(str)) {
                Uri build = new Uri.Builder().encodedQuery(str.replaceAll("\\+", "%20")).build();
                StringBuilder sb = new StringBuilder();
                com.google.android.gms.ads.internal.zzt.zzq();
                Map zzL = zzs.zzL(build);
                for (String str3 : zzL.keySet()) {
                    sb.append(str3);
                    sb.append(" = ");
                    sb.append((String) zzL.get(str3));
                    sb.append("\n\n");
                }
                String trim = sb.toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    str2 = trim;
                }
            }
            com.google.android.gms.ads.internal.zzt.zzq();
            AlertDialog.Builder zzG = zzs.zzG(this.zza);
            zzG.setMessage(str2);
            zzG.setTitle("Ad Information");
            zzG.setPositiveButton("Share", new DialogInterface.OnClickListener() { // from class: com.google.android.gms.ads.internal.util.zzad
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface2, int r42) {
                    zzas.this.zzi(str2, dialogInterface2, r42);
                }
            });
            zzG.setNegativeButton("Close", new DialogInterface.OnClickListener() { // from class: com.google.android.gms.ads.internal.util.zzae
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface2, int r22) {
                }
            });
            zzG.create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzk() {
        zzaw zzt = com.google.android.gms.ads.internal.zzt.zzt();
        Context context = this.zza;
        String str = this.zzd;
        String str2 = this.zze;
        String str3 = this.zzf;
        boolean zzm = zzt.zzm();
        zzt.zzh(zzt.zzj(context, str, str2));
        if (zzt.zzm()) {
            if (!zzm && !TextUtils.isEmpty(str3)) {
                zzt.zze(context, str2, str3, str);
            }
            zze.zze("Device is linked for debug signals.");
            zzt.zzi(context, "The device is successfully linked for troubleshooting.", false, true);
            return;
        }
        zzt.zzd(context, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzl() {
        zzaw zzt = com.google.android.gms.ads.internal.zzt.zzt();
        Context context = this.zza;
        String str = this.zzd;
        String str2 = this.zze;
        if (!zzt.zzk(context, str, str2)) {
            zzt.zzi(context, "In-app preview failed to load because of a system error. Please try again later.", true, true);
        } else if ("2".equals(zzt.zza)) {
            zze.zze("Creative is not pushed for this device.");
            zzt.zzi(context, "There was no creative pushed from DFP to the device.", false, false);
        } else if (IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(zzt.zza)) {
            zze.zze("The app is not linked for creative preview.");
            zzt.zzd(context, str, str2);
        } else if (SessionDescription.SUPPORTED_SDP_VERSION.equals(zzt.zza)) {
            zze.zze("Device is linked for in app preview.");
            zzt.zzi(context, "The device is successfully linked for creative preview.", false, true);
        }
    }

    public final void zzm(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int historySize = motionEvent.getHistorySize();
        int pointerCount = motionEvent.getPointerCount();
        if (actionMasked == 0) {
            this.zzg = 0;
            this.zzi = new PointF(motionEvent.getX(0), motionEvent.getY(0));
            return;
        }
        int r4 = this.zzg;
        if (r4 == -1) {
            return;
        }
        if (r4 == 0) {
            if (actionMasked == 5) {
                this.zzg = 5;
                this.zzj = new PointF(motionEvent.getX(1), motionEvent.getY(1));
                this.zzk.postDelayed(this.zzl, ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdL)).longValue());
            }
        } else if (r4 == 5) {
            if (pointerCount == 2) {
                if (actionMasked != 2) {
                    return;
                }
                boolean z = false;
                for (int r0 = 0; r0 < historySize; r0++) {
                    z |= !zzt(motionEvent.getHistoricalX(0, r0), motionEvent.getHistoricalY(0, r0), motionEvent.getHistoricalX(1, r0), motionEvent.getHistoricalY(1, r0));
                }
                if (zzt(motionEvent.getX(), motionEvent.getY(), motionEvent.getX(1), motionEvent.getY(1)) && !z) {
                    return;
                }
            }
            this.zzg = -1;
            this.zzk.removeCallbacks(this.zzl);
        }
    }

    public final void zzn(String str) {
        this.zzd = str;
    }

    public final void zzo(String str) {
        this.zze = str;
    }

    public final void zzp(String str) {
        this.zzc = str;
    }

    public final void zzq(String str) {
        this.zzf = str;
    }

    public final void zzr() {
        try {
            if (!(this.zza instanceof Activity)) {
                zze.zzi("Can not create dialog without Activity Context");
                return;
            }
            String str = "Creative preview (enabled)";
            if (true == TextUtils.isEmpty(com.google.android.gms.ads.internal.zzt.zzt().zzb())) {
                str = "Creative preview";
            }
            String str2 = true != com.google.android.gms.ads.internal.zzt.zzt().zzm() ? "Troubleshooting" : "Troubleshooting (enabled)";
            ArrayList arrayList = new ArrayList();
            final int zzu = zzu(arrayList, "Ad information", true);
            final int zzu2 = zzu(arrayList, str, true);
            final int zzu3 = zzu(arrayList, str2, true);
            boolean booleanValue = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhO)).booleanValue();
            final int zzu4 = zzu(arrayList, "Open ad inspector", booleanValue);
            final int zzu5 = zzu(arrayList, "Ad inspector settings", booleanValue);
            com.google.android.gms.ads.internal.zzt.zzq();
            AlertDialog.Builder zzG = zzs.zzG(this.zza);
            zzG.setTitle("Select a debug mode").setItems((CharSequence[]) arrayList.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: com.google.android.gms.ads.internal.util.zzap
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int r10) {
                    zzas.this.zzj(zzu, zzu2, zzu3, zzu4, zzu5, dialogInterface, r10);
                }
            });
            zzG.create().show();
        } catch (WindowManager.BadTokenException e) {
            zze.zzb("", e);
        }
    }

    public zzas(Context context, String str) {
        this(context);
        this.zzc = str;
    }
}
