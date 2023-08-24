package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzim {
    private static final zzik zzzl = zzhn();
    private static final zzik zzzm = new zzij();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzik zzhl() {
        return zzzl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzik zzhm() {
        return zzzm;
    }

    private static zzik zzhn() {
        try {
            return (zzik) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
