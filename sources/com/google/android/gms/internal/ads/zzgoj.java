package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzgoj;
import com.google.android.gms.internal.ads.zzgon;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzgoj<MessageType extends zzgon<MessageType, BuilderType>, BuilderType extends zzgoj<MessageType, BuilderType>> extends zzgmn<MessageType, BuilderType> {
    protected zzgon zza;
    protected boolean zzb = false;
    private final zzgon zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzgoj(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (zzgon) messagetype.zzb(4, null, null);
    }

    private static final void zza(zzgon zzgonVar, zzgon zzgonVar2) {
        zzgqf.zza().zzb(zzgonVar.getClass()).zzg(zzgonVar, zzgonVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzgmn
    protected final /* synthetic */ zzgmn zzag(zzgmo zzgmoVar) {
        zzaj((zzgon) zzgmoVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzgmn
    /* renamed from: zzai */
    public final zzgoj zzaf() {
        zzgoj zzgojVar = (zzgoj) this.zzc.zzb(5, null, null);
        zzgojVar.zzaj(zzan());
        return zzgojVar;
    }

    public final zzgoj zzaj(zzgon zzgonVar) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zza(this.zza, zzgonVar);
        return this;
    }

    public final zzgoj zzak(byte[] bArr, int r9, int r10, zzgnz zzgnzVar) throws zzgoz {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        try {
            zzgqf.zza().zzb(this.zza.getClass()).zzi(this.zza, bArr, 0, r10, new zzgmr(zzgnzVar));
            return this;
        } catch (zzgoz e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzgoz.zzj();
        }
    }

    public final MessageType zzal() {
        MessageType zzan = zzan();
        if (zzan.zzaR()) {
            return zzan;
        }
        throw new zzgrg(zzan);
    }

    @Override // com.google.android.gms.internal.ads.zzgpw
    /* renamed from: zzam */
    public MessageType zzan() {
        if (this.zzb) {
            return (MessageType) this.zza;
        }
        zzgon zzgonVar = this.zza;
        zzgqf.zza().zzb(zzgonVar.getClass()).zzf(zzgonVar);
        this.zzb = true;
        return (MessageType) this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzap() {
        zzgon zzgonVar = (zzgon) this.zza.zzb(4, null, null);
        zza(zzgonVar, this.zza);
        this.zza = zzgonVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgpy
    public final /* synthetic */ zzgpx zzbh() {
        return this.zzc;
    }
}
