package com.google.android.gms.internal.ads;

import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzglp {
    public static final zzglp zza;
    public static final zzglp zzb;
    public static final zzglp zzc;
    public static final zzglp zzd;
    public static final zzglp zze;
    public static final zzglp zzf;
    public static final zzglp zzg;
    private static final Logger zzh = Logger.getLogger(zzglp.class.getName());
    private static final List zzi;
    private static final boolean zzj;
    private final zzglx zzk;

    static {
        if (zzgcz.zzb()) {
            zzi = zzb(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL", "Conscrypt");
            zzj = false;
        } else if (zzgmh.zzb()) {
            zzi = zzb(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL");
            zzj = true;
        } else {
            zzi = new ArrayList();
            zzj = true;
        }
        zza = new zzglp(new zzglq());
        zzb = new zzglp(new zzglu());
        zzc = new zzglp(new zzglw());
        zzd = new zzglp(new zzglv());
        zze = new zzglp(new zzglr());
        zzf = new zzglp(new zzglt());
        zzg = new zzglp(new zzgls());
    }

    public zzglp(zzglx zzglxVar) {
        this.zzk = zzglxVar;
    }

    public static List zzb(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Provider provider = Security.getProvider(str);
            if (provider != null) {
                arrayList.add(provider);
            } else {
                zzh.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", str));
            }
        }
        return arrayList;
    }

    public final Object zza(String str) throws GeneralSecurityException {
        Exception exc = null;
        for (Provider provider : zzi) {
            try {
                return this.zzk.zza(str, provider);
            } catch (Exception e) {
                if (exc == null) {
                    exc = e;
                }
            }
        }
        if (zzj) {
            return this.zzk.zza(str, null);
        }
        throw new GeneralSecurityException("No good Provider found.", exc);
    }
}
