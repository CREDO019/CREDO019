package com.google.android.gms.internal.ads;

import android.media.MediaCodec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzqk extends zzge {
    public final zzql zza;
    public final String zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzqk(Throwable th, zzql zzqlVar) {
        super("Decoder failed: ".concat(String.valueOf(zzqlVar == null ? null : zzqlVar.zza)), th);
        String str = null;
        this.zza = zzqlVar;
        if (zzel.zza >= 21 && (th instanceof MediaCodec.CodecException)) {
            str = ((MediaCodec.CodecException) th).getDiagnosticInfo();
        }
        this.zzb = str;
    }
}
