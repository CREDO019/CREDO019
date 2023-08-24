package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgbe {
    private static final Logger zza = Logger.getLogger(zzgbe.class.getName());
    private static final AtomicReference zzb = new AtomicReference(new zzgaj());
    private static final ConcurrentMap zzc = new ConcurrentHashMap();
    private static final ConcurrentMap zzd = new ConcurrentHashMap();
    private static final ConcurrentMap zze = new ConcurrentHashMap();
    private static final ConcurrentMap zzf = new ConcurrentHashMap();
    private static final ConcurrentMap zzg = new ConcurrentHashMap();

    private zzgbe() {
    }

    @Deprecated
    public static zzfzv zza(String str) throws GeneralSecurityException {
        if (str == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        zzfzv zzfzvVar = (zzfzv) zze.get(str.toLowerCase(Locale.US));
        if (zzfzvVar == null) {
            String format = String.format("no catalogue found for %s. ", str);
            if (str.toLowerCase(Locale.US).startsWith("tinkaead")) {
                format = String.valueOf(format).concat("Maybe call AeadConfig.register().");
            }
            if (str.toLowerCase(Locale.US).startsWith("tinkdeterministicaead")) {
                format = String.valueOf(format).concat("Maybe call DeterministicAeadConfig.register().");
            } else if (str.toLowerCase(Locale.US).startsWith("tinkstreamingaead")) {
                format = String.valueOf(format).concat("Maybe call StreamingAeadConfig.register().");
            } else if (str.toLowerCase(Locale.US).startsWith("tinkhybriddecrypt") || str.toLowerCase(Locale.US).startsWith("tinkhybridencrypt")) {
                format = String.valueOf(format).concat("Maybe call HybridConfig.register().");
            } else if (str.toLowerCase(Locale.US).startsWith("tinkmac")) {
                format = String.valueOf(format).concat("Maybe call MacConfig.register().");
            } else if (str.toLowerCase(Locale.US).startsWith("tinkpublickeysign") || str.toLowerCase(Locale.US).startsWith("tinkpublickeyverify")) {
                format = String.valueOf(format).concat("Maybe call SignatureConfig.register().");
            } else if (str.toLowerCase(Locale.US).startsWith("tink")) {
                format = String.valueOf(format).concat("Maybe call TinkConfig.register().");
            }
            throw new GeneralSecurityException(format);
        }
        return zzfzvVar;
    }

    public static zzgac zzb(String str) throws GeneralSecurityException {
        return ((zzgaj) zzb.get()).zzb(str);
    }

    public static synchronized zzgjg zzc(zzgjl zzgjlVar) throws GeneralSecurityException {
        zzgjg zza2;
        synchronized (zzgbe.class) {
            zzgac zzb2 = zzb(zzgjlVar.zzf());
            if (((Boolean) zzd.get(zzgjlVar.zzf())).booleanValue()) {
                zza2 = zzb2.zza(zzgjlVar.zze());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type ".concat(String.valueOf(zzgjlVar.zzf())));
            }
        }
        return zza2;
    }

    public static synchronized zzgpx zzd(zzgjl zzgjlVar) throws GeneralSecurityException {
        zzgpx zzb2;
        synchronized (zzgbe.class) {
            zzgac zzb3 = zzb(zzgjlVar.zzf());
            if (((Boolean) zzd.get(zzgjlVar.zzf())).booleanValue()) {
                zzb2 = zzb3.zzb(zzgjlVar.zze());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type ".concat(String.valueOf(zzgjlVar.zzf())));
            }
        }
        return zzb2;
    }

    public static Class zze(Class cls) {
        zzgbb zzgbbVar = (zzgbb) zzf.get(cls);
        if (zzgbbVar == null) {
            return null;
        }
        return zzgbbVar.zza();
    }

    public static Object zzf(zzgjg zzgjgVar, Class cls) throws GeneralSecurityException {
        return zzg(zzgjgVar.zzf(), zzgjgVar.zze(), cls);
    }

    public static Object zzg(String str, zzgnf zzgnfVar, Class cls) throws GeneralSecurityException {
        return ((zzgaj) zzb.get()).zza(str, cls).zzd(zzgnfVar);
    }

    public static Object zzh(String str, zzgpx zzgpxVar, Class cls) throws GeneralSecurityException {
        return ((zzgaj) zzb.get()).zza(str, cls).zze(zzgpxVar);
    }

    public static Object zzi(String str, byte[] bArr, Class cls) throws GeneralSecurityException {
        return zzg(str, zzgnf.zzv(bArr), cls);
    }

    public static Object zzj(zzgba zzgbaVar, Class cls) throws GeneralSecurityException {
        zzgbb zzgbbVar = (zzgbb) zzf.get(cls);
        if (zzgbbVar == null) {
            throw new GeneralSecurityException("No wrapper found for ".concat(String.valueOf(zzgbaVar.zzb().getName())));
        }
        if (!zzgbbVar.zza().equals(zzgbaVar.zzb())) {
            String obj = zzgbbVar.zza().toString();
            String obj2 = zzgbaVar.zzb().toString();
            throw new GeneralSecurityException("Wrong input primitive class, expected " + obj + ", got " + obj2);
        }
        return zzgbbVar.zzc(zzgbaVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized Map zzk() {
        Map unmodifiableMap;
        synchronized (zzgbe.class) {
            unmodifiableMap = Collections.unmodifiableMap(zzg);
        }
        return unmodifiableMap;
    }

    public static synchronized void zzl(zzgeo zzgeoVar, zzgem zzgemVar, boolean z) throws GeneralSecurityException {
        synchronized (zzgbe.class) {
            AtomicReference atomicReference = zzb;
            zzgaj zzgajVar = new zzgaj((zzgaj) atomicReference.get());
            zzgajVar.zzc(zzgeoVar, zzgemVar);
            String zzc2 = zzgeoVar.zzc();
            String zzc3 = zzgemVar.zzc();
            zzp(zzc2, zzgeoVar.zza().zzc(), true);
            zzp(zzc3, Collections.emptyMap(), false);
            if (!((zzgaj) atomicReference.get()).zzf(zzc2)) {
                zzc.put(zzc2, new zzgbd(zzgeoVar));
                zzq(zzgeoVar.zzc(), zzgeoVar.zza().zzc());
            }
            ConcurrentMap concurrentMap = zzd;
            concurrentMap.put(zzc2, true);
            concurrentMap.put(zzc3, false);
            atomicReference.set(zzgajVar);
        }
    }

    public static synchronized void zzn(zzgem zzgemVar, boolean z) throws GeneralSecurityException {
        synchronized (zzgbe.class) {
            AtomicReference atomicReference = zzb;
            zzgaj zzgajVar = new zzgaj((zzgaj) atomicReference.get());
            zzgajVar.zze(zzgemVar);
            String zzc2 = zzgemVar.zzc();
            zzp(zzc2, zzgemVar.zza().zzc(), true);
            if (!((zzgaj) atomicReference.get()).zzf(zzc2)) {
                zzc.put(zzc2, new zzgbd(zzgemVar));
                zzq(zzc2, zzgemVar.zza().zzc());
            }
            zzd.put(zzc2, true);
            atomicReference.set(zzgajVar);
        }
    }

    public static synchronized void zzo(zzgbb zzgbbVar) throws GeneralSecurityException {
        synchronized (zzgbe.class) {
            if (zzgbbVar != null) {
                Class zzb2 = zzgbbVar.zzb();
                ConcurrentMap concurrentMap = zzf;
                if (concurrentMap.containsKey(zzb2)) {
                    zzgbb zzgbbVar2 = (zzgbb) concurrentMap.get(zzb2);
                    if (!zzgbbVar.getClass().getName().equals(zzgbbVar2.getClass().getName())) {
                        zza.logp(Level.WARNING, "com.google.crypto.tink.Registry", "registerPrimitiveWrapper", "Attempted overwrite of a registered PrimitiveWrapper for type ".concat(zzb2.toString()));
                        throw new GeneralSecurityException(String.format("PrimitiveWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", zzb2.getName(), zzgbbVar2.getClass().getName(), zzgbbVar.getClass().getName()));
                    }
                }
                concurrentMap.put(zzb2, zzgbbVar);
            } else {
                throw new IllegalArgumentException("wrapper must be non-null");
            }
        }
    }

    private static synchronized void zzp(String str, Map map, boolean z) throws GeneralSecurityException {
        synchronized (zzgbe.class) {
            if (z) {
                ConcurrentMap concurrentMap = zzd;
                if (concurrentMap.containsKey(str) && !((Boolean) concurrentMap.get(str)).booleanValue()) {
                    throw new GeneralSecurityException("New keys are already disallowed for key type ".concat(str));
                }
                if (((zzgaj) zzb.get()).zzf(str)) {
                    for (Map.Entry entry : map.entrySet()) {
                        if (!zzg.containsKey(entry.getKey())) {
                            throw new GeneralSecurityException("Attempted to register a new key template " + ((String) entry.getKey()) + " from an existing key manager of type " + str);
                        }
                    }
                } else {
                    for (Map.Entry entry2 : map.entrySet()) {
                        if (zzg.containsKey(entry2.getKey())) {
                            throw new GeneralSecurityException("Attempted overwrite of a registered key template ".concat(String.valueOf((String) entry2.getKey())));
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.google.android.gms.internal.ads.zzgpx, java.lang.Object] */
    private static void zzq(String str, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            zzg.put((String) entry.getKey(), zzgak.zze(str, ((zzgek) entry.getValue()).zza.zzaw(), ((zzgek) entry.getValue()).zzb));
        }
    }

    public static synchronized void zzm(zzgac zzgacVar, boolean z) throws GeneralSecurityException {
        synchronized (zzgbe.class) {
            try {
                if (zzgacVar == null) {
                    throw new IllegalArgumentException("key manager must be non-null.");
                }
                AtomicReference atomicReference = zzb;
                zzgaj zzgajVar = new zzgaj((zzgaj) atomicReference.get());
                zzgajVar.zzd(zzgacVar);
                if (!zzgcy.zza(1)) {
                    throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
                }
                String zzf2 = zzgacVar.zzf();
                zzp(zzf2, Collections.emptyMap(), z);
                zzd.put(zzf2, Boolean.valueOf(z));
                atomicReference.set(zzgajVar);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
