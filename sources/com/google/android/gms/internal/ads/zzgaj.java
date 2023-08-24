package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgaj {
    private static final Logger zza = Logger.getLogger(zzgaj.class.getName());
    private final ConcurrentMap zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgaj() {
        this.zzb = new ConcurrentHashMap();
    }

    private final synchronized zzgai zzg(String str) throws GeneralSecurityException {
        if (!this.zzb.containsKey(str)) {
            throw new GeneralSecurityException("No key manager found for key type ".concat(String.valueOf(str)));
        }
        return (zzgai) this.zzb.get(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x005d, code lost:
        r6.zzb.putIfAbsent(r0, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0063, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final synchronized void zzh(com.google.android.gms.internal.ads.zzgai r7, boolean r8) throws java.security.GeneralSecurityException {
        /*
            r6 = this;
            monitor-enter(r6)
            com.google.android.gms.internal.ads.zzgac r0 = r7.zzb()     // Catch: java.lang.Throwable -> L6b
            java.lang.String r0 = r0.zzf()     // Catch: java.lang.Throwable -> L6b
            java.util.concurrent.ConcurrentMap r1 = r6.zzb     // Catch: java.lang.Throwable -> L6b
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> L6b
            com.google.android.gms.internal.ads.zzgai r1 = (com.google.android.gms.internal.ads.zzgai) r1     // Catch: java.lang.Throwable -> L6b
            if (r1 == 0) goto L5b
            java.lang.Class r2 = r1.zzc()     // Catch: java.lang.Throwable -> L6b
            java.lang.Class r3 = r7.zzc()     // Catch: java.lang.Throwable -> L6b
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Throwable -> L6b
            if (r2 == 0) goto L22
            goto L5b
        L22:
            java.util.logging.Logger r8 = com.google.android.gms.internal.ads.zzgaj.zza     // Catch: java.lang.Throwable -> L6b
            java.util.logging.Level r2 = java.util.logging.Level.WARNING     // Catch: java.lang.Throwable -> L6b
            java.lang.String r3 = "com.google.crypto.tink.KeyManagerRegistry"
            java.lang.String r4 = "registerKeyManagerContainer"
            java.lang.String r5 = "Attempted overwrite of a registered key manager for key type "
            java.lang.String r5 = r5.concat(r0)     // Catch: java.lang.Throwable -> L6b
            r8.logp(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L6b
            java.security.GeneralSecurityException r8 = new java.security.GeneralSecurityException     // Catch: java.lang.Throwable -> L6b
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L6b
            r3 = 0
            r2[r3] = r0     // Catch: java.lang.Throwable -> L6b
            r0 = 1
            java.lang.Class r1 = r1.zzc()     // Catch: java.lang.Throwable -> L6b
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L6b
            r2[r0] = r1     // Catch: java.lang.Throwable -> L6b
            r0 = 2
            java.lang.Class r7 = r7.zzc()     // Catch: java.lang.Throwable -> L6b
            java.lang.String r7 = r7.getName()     // Catch: java.lang.Throwable -> L6b
            r2[r0] = r7     // Catch: java.lang.Throwable -> L6b
            java.lang.String r7 = "typeUrl (%s) is already registered with %s, cannot be re-registered with %s"
            java.lang.String r7 = java.lang.String.format(r7, r2)     // Catch: java.lang.Throwable -> L6b
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L6b
            throw r8     // Catch: java.lang.Throwable -> L6b
        L5b:
            if (r8 != 0) goto L64
            java.util.concurrent.ConcurrentMap r8 = r6.zzb     // Catch: java.lang.Throwable -> L6b
            r8.putIfAbsent(r0, r7)     // Catch: java.lang.Throwable -> L6b
            monitor-exit(r6)
            return
        L64:
            java.util.concurrent.ConcurrentMap r8 = r6.zzb     // Catch: java.lang.Throwable -> L6b
            r8.put(r0, r7)     // Catch: java.lang.Throwable -> L6b
            monitor-exit(r6)
            return
        L6b:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgaj.zzh(com.google.android.gms.internal.ads.zzgai, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgac zza(String str, Class cls) throws GeneralSecurityException {
        zzgai zzg = zzg(str);
        if (zzg.zze().contains(cls)) {
            return zzg.zza(cls);
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzg.zzc());
        Set<Class> zze = zzg.zze();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class cls2 : zze) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(cls2.getCanonicalName());
            z = false;
        }
        String sb2 = sb.toString();
        throw new GeneralSecurityException("Primitive type " + name + " not supported by key manager of type " + valueOf + ", supported primitives: " + sb2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgac zzb(String str) throws GeneralSecurityException {
        return zzg(str).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzc(zzgeo zzgeoVar, zzgem zzgemVar) throws GeneralSecurityException {
        Class zzd;
        int zze = zzgemVar.zze();
        if (!zzgcy.zza(1)) {
            String valueOf = String.valueOf(zzgeoVar.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf + " as it is not FIPS compatible.");
        } else if (zzgcy.zza(zze)) {
            String zzc = zzgeoVar.zzc();
            String zzc2 = zzgemVar.zzc();
            if (this.zzb.containsKey(zzc) && ((zzgai) this.zzb.get(zzc)).zzd() != null && (zzd = ((zzgai) this.zzb.get(zzc)).zzd()) != null && !zzd.getName().equals(zzgemVar.getClass().getName())) {
                Logger logger = zza;
                Level level = Level.WARNING;
                logger.logp(level, "com.google.crypto.tink.KeyManagerRegistry", "registerAsymmetricKeyManagers", "Attempted overwrite of a registered key manager for key type " + zzc + " with inconsistent public key type " + zzc2);
                throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", zzgeoVar.getClass().getName(), zzd.getName(), zzgemVar.getClass().getName()));
            }
            zzh(new zzgah(zzgeoVar, zzgemVar), true);
            zzh(new zzgag(zzgemVar), false);
        } else {
            String valueOf2 = String.valueOf(zzgemVar.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf2 + " as it is not FIPS compatible.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzd(zzgac zzgacVar) throws GeneralSecurityException {
        if (zzgcy.zza(1)) {
            zzh(new zzgaf(zzgacVar), false);
        } else {
            throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zze(zzgem zzgemVar) throws GeneralSecurityException {
        if (zzgcy.zza(zzgemVar.zze())) {
            zzh(new zzgag(zzgemVar), false);
        } else {
            String valueOf = String.valueOf(zzgemVar.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf + " as it is not FIPS compatible.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzf(String str) {
        return this.zzb.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgaj(zzgaj zzgajVar) {
        this.zzb = new ConcurrentHashMap(zzgajVar.zzb);
    }
}
