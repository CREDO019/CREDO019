package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzhx {
    private static final zzhv zzyq = zzhh();
    private static final zzhv zzyr = new zzhy();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhv zzhf() {
        return zzyq;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhv zzhg() {
        return zzyr;
    }

    private static zzhv zzhh() {
        try {
            return (zzhv) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
