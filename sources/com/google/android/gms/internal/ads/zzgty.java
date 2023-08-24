package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgty implements zzaln {
    private static final zzguj zza = zzguj.zzb(zzgty.class);
    protected final String zzb;
    long zze;
    zzgud zzg;
    private zzalo zzh;
    private ByteBuffer zzi;
    long zzf = -1;
    private ByteBuffer zzj = null;
    boolean zzd = true;
    boolean zzc = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzgty(String str) {
        this.zzb = str;
    }

    private final synchronized void zzd() {
        if (this.zzd) {
            return;
        }
        try {
            zzguj zzgujVar = zza;
            String str = this.zzb;
            zzgujVar.zza(str.length() != 0 ? "mem mapping ".concat(str) : new String("mem mapping "));
            this.zzi = this.zzg.zzd(this.zze, this.zzf);
            this.zzd = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaln
    public final String zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzaln
    public final void zzb(zzgud zzgudVar, ByteBuffer byteBuffer, long j, zzalk zzalkVar) throws IOException {
        this.zze = zzgudVar.zzb();
        byteBuffer.remaining();
        this.zzf = j;
        this.zzg = zzgudVar;
        zzgudVar.zze(zzgudVar.zzb() + j);
        this.zzd = false;
        this.zzc = false;
        zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzaln
    public final void zzc(zzalo zzaloVar) {
        this.zzh = zzaloVar;
    }

    protected abstract void zzf(ByteBuffer byteBuffer);

    public final synchronized void zzg() {
        zzd();
        zzguj zzgujVar = zza;
        String str = this.zzb;
        zzgujVar.zza(str.length() != 0 ? "parsing details of ".concat(str) : new String("parsing details of "));
        ByteBuffer byteBuffer = this.zzi;
        if (byteBuffer != null) {
            this.zzc = true;
            byteBuffer.rewind();
            zzf(byteBuffer);
            if (byteBuffer.remaining() > 0) {
                this.zzj = byteBuffer.slice();
            }
            this.zzi = null;
        }
    }
}
