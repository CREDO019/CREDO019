package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApkChecksum;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqo extends zzarm {
    private static final zzarn zzi = new zzarn();
    private final Context zzj;

    public zzaqo(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13, Context context, zzama zzamaVar) {
        super(zzaqbVar, "CL5CLQrzdJf7Vwsm6HGAxqUk+UMWsbN6k++UR113AdLOMpqSaLq8guKKFWwu33sx", "AU2/Ti/cc5wfSsdvyvo7rvRQAPyBVB0PKDRfRe8Q8Qo=", zzamhVar, r12, 27);
        this.zzj = context;
    }

    private final String zzc() {
        try {
            if (this.zzb.zzl() != null) {
                this.zzb.zzl().get();
            }
            zzamx zzc = this.zzb.zzc();
            if (zzc == null || !zzc.zzai()) {
                return null;
            }
            return zzc.zzh();
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        Boolean bool;
        int r2;
        zzann zzannVar;
        AtomicReference zza = zzi.zza(this.zzj.getPackageName());
        synchronized (zza) {
            zzann zzannVar2 = (zzann) zza.get();
            if (zzannVar2 == null || zzaqe.zzg(zzannVar2.zza) || zzannVar2.zza.equals(ExifInterface.LONGITUDE_EAST) || zzannVar2.zza.equals("0000000000000000000000000000000000000000000000000000000000000000")) {
                if (zzaqe.zzg(null)) {
                    if (zzaqe.zzg(null)) {
                        bool = false;
                    } else {
                        bool = false;
                    }
                    r2 = (bool.booleanValue() && this.zzb.zzp()) ? 4 : 3;
                } else {
                    r2 = 5;
                }
                Boolean valueOf = Boolean.valueOf(r2 == 3);
                Boolean bool2 = (Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbW);
                String zzb = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbV)).booleanValue() ? zzb() : null;
                if (bool2.booleanValue() && this.zzb.zzp() && zzaqe.zzg(zzb)) {
                    zzb = zzc();
                }
                zzann zzannVar3 = new zzann((String) this.zzf.invoke(null, this.zzj, valueOf, zzb));
                if (zzaqe.zzg(zzannVar3.zza) || zzannVar3.zza.equals(ExifInterface.LONGITUDE_EAST)) {
                    int r22 = r2 - 1;
                    if (r22 == 3) {
                        String zzc = zzc();
                        if (!zzaqe.zzg(zzc)) {
                            zzannVar3.zza = zzc;
                        }
                    } else if (r22 == 4) {
                        throw null;
                    }
                }
                zza.set(zzannVar3);
            }
            zzannVar = (zzann) zza.get();
        }
        synchronized (this.zze) {
            if (zzannVar != null) {
                this.zze.zzw(zzannVar.zza);
                this.zze.zzW(zzannVar.zzb);
                this.zze.zzY(zzannVar.zzc);
                this.zze.zzh(zzannVar.zzd);
                this.zze.zzv(zzannVar.zze);
            }
        }
    }

    protected final String zzb() {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            byte[] zzi2 = zzaqe.zzi((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbX));
            ArrayList arrayList = new ArrayList();
            arrayList.add(certificateFactory.generateCertificate(new ByteArrayInputStream(zzi2)));
            if (!Build.TYPE.equals("user")) {
                arrayList.add(certificateFactory.generateCertificate(new ByteArrayInputStream(zzaqe.zzi((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbY)))));
            }
            Context context = this.zzj;
            String packageName = context.getPackageName();
            this.zzb.zzk();
            if (Build.VERSION.SDK_INT > 30 || Build.VERSION.CODENAME.equals(ExifInterface.LATITUDE_SOUTH)) {
                final zzfzg zzf = zzfzg.zzf();
                context.getPackageManager().requestChecksums(packageName, false, 8, arrayList, new PackageManager.OnChecksumsReadyListener() { // from class: com.google.android.gms.internal.ads.zzaro
                    @Override // android.content.pm.PackageManager.OnChecksumsReadyListener
                    public final void onChecksumsReady(List list) {
                        zzfzg zzfzgVar = zzfzg.this;
                        if (list == null) {
                            zzfzgVar.zzd(null);
                            return;
                        }
                        try {
                            int size = list.size();
                            for (int r3 = 0; r3 < size; r3++) {
                                ApkChecksum apkChecksum = (ApkChecksum) list.get(r3);
                                if (apkChecksum.getType() == 8) {
                                    zzfzgVar.zzd(zzaqe.zzc(apkChecksum.getValue()));
                                    return;
                                }
                            }
                            zzfzgVar.zzd(null);
                        } catch (Throwable unused) {
                            zzfzgVar.zzd(null);
                        }
                    }
                });
                return (String) zzf.get();
            }
            return null;
        } catch (PackageManager.NameNotFoundException | InterruptedException | NoClassDefFoundError | CertificateEncodingException | CertificateException | ExecutionException unused) {
            return null;
        }
    }
}
