package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzgtz extends zzguc implements zzaln {
    zzalo zza;
    protected final String zzb = "moov";

    public zzgtz(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzaln
    public final String zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzaln
    public final void zzb(zzgud zzgudVar, ByteBuffer byteBuffer, long j, zzalk zzalkVar) throws IOException {
        zzgudVar.zzb();
        byteBuffer.remaining();
        byteBuffer.remaining();
        this.zzd = zzgudVar;
        this.zzf = zzgudVar.zzb();
        zzgudVar.zze(zzgudVar.zzb() + j);
        this.zzg = zzgudVar.zzb();
        this.zzc = zzalkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaln
    public final void zzc(zzalo zzaloVar) {
        this.zza = zzaloVar;
    }
}
