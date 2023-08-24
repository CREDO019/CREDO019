package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgoc {
    private static final zzgoa zza = new zzgob();
    private static final zzgoa zzb;

    static {
        zzgoa zzgoaVar;
        try {
            zzgoaVar = (zzgoa) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzgoaVar = null;
        }
        zzb = zzgoaVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgoa zza() {
        zzgoa zzgoaVar = zzb;
        if (zzgoaVar != null) {
            return zzgoaVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgoa zzb() {
        return zza;
    }
}
