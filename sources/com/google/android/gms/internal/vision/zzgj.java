package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzgj {
    private static final zzgf<?> zztf = new zzgh();
    private static final zzgf<?> zztg = zzfp();

    private static zzgf<?> zzfp() {
        try {
            return (zzgf) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgf<?> zzfq() {
        return zztf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgf<?> zzfr() {
        zzgf<?> zzgfVar = zztg;
        if (zzgfVar != null) {
            return zzgfVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
