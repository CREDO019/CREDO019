package com.google.android.gms.internal.ads;

import android.os.Environment;
import android.util.Base64;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbel {
    private final zzber zza;
    private final zzbga zzb;
    private final boolean zzc;

    private zzbel() {
        this.zzb = zzbgb.zzd();
        this.zzc = false;
        this.zza = new zzber();
    }

    public static zzbel zza() {
        return new zzbel();
    }

    private final synchronized String zzd(int r5) {
        return String.format("id=%s,timestamp=%s,event=%s,data=%s\n", this.zzb.zzk(), Long.valueOf(com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime()), Integer.valueOf(r5 - 1), Base64.encodeToString(((zzbgb) this.zzb.zzal()).zzaw(), 3));
    }

    private final synchronized void zze(int r4) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null) {
            return;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(externalStorageDirectory, "clearcut_events.txt"), true);
            try {
                try {
                    fileOutputStream.write(zzd(r4).getBytes());
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        com.google.android.gms.ads.internal.util.zze.zza("Could not close Clearcut output stream.");
                    }
                } catch (IOException unused2) {
                    com.google.android.gms.ads.internal.util.zze.zza("Could not write Clearcut to file.");
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                        com.google.android.gms.ads.internal.util.zze.zza("Could not close Clearcut output stream.");
                    }
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                    com.google.android.gms.ads.internal.util.zze.zza("Could not close Clearcut output stream.");
                }
                throw th;
            }
        } catch (FileNotFoundException unused5) {
            com.google.android.gms.ads.internal.util.zze.zza("Could not find file for Clearcut");
        }
    }

    private final synchronized void zzf(int r8) {
        zzbga zzbgaVar = this.zzb;
        zzbgaVar.zzd();
        List<String> zzb = zzbiy.zzb();
        ArrayList arrayList = new ArrayList();
        for (String str : zzb) {
            for (String str2 : str.split(",")) {
                try {
                    arrayList.add(Long.valueOf(str2));
                } catch (NumberFormatException unused) {
                    com.google.android.gms.ads.internal.util.zze.zza("Experiment ID is not a number");
                }
            }
        }
        zzbgaVar.zzc(arrayList);
        zzbeq zzbeqVar = new zzbeq(this.zza, ((zzbgb) this.zzb.zzal()).zzaw(), null);
        int r82 = r8 - 1;
        zzbeqVar.zza(r82);
        zzbeqVar.zzc();
        com.google.android.gms.ads.internal.util.zze.zza("Logging Event with event code : ".concat(String.valueOf(Integer.toString(r82, 10))));
    }

    public final synchronized void zzb(zzbek zzbekVar) {
        if (this.zzc) {
            try {
                zzbekVar.zza(this.zzb);
            } catch (NullPointerException e) {
                com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "AdMobClearcutLogger.modify");
            }
        }
    }

    public final synchronized void zzc(int r3) {
        if (this.zzc) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdY)).booleanValue()) {
                zze(r3);
            } else {
                zzf(r3);
            }
        }
    }

    public zzbel(zzber zzberVar) {
        this.zzb = zzbgb.zzd();
        this.zza = zzberVar;
        this.zzc = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdX)).booleanValue();
    }
}
