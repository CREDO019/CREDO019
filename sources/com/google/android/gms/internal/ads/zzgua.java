package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgua extends zzgty implements zzaln {
    private int zza;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzgua(String str) {
        super("mvhd");
    }

    public final int zzh() {
        if (!this.zzc) {
            zzg();
        }
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long zzi(ByteBuffer byteBuffer) {
        this.zza = zzalm.zzc(byteBuffer.get());
        zzalm.zzd(byteBuffer);
        byteBuffer.get();
        return 4L;
    }
}
