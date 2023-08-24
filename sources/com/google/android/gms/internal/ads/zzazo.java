package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzazo extends IOException {
    public final zzazk zza;

    public zzazo(IOException iOException, zzazk zzazkVar, int r3) {
        super(iOException);
        this.zza = zzazkVar;
    }

    public zzazo(String str, zzazk zzazkVar, int r3) {
        super(str);
        this.zza = zzazkVar;
    }

    public zzazo(String str, IOException iOException, zzazk zzazkVar, int r4) {
        super(str, iOException);
        this.zza = zzazkVar;
    }
}
