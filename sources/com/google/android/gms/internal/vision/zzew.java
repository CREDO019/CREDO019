package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzet;
import com.google.android.gms.internal.vision.zzew;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzew<MessageType extends zzet<MessageType, BuilderType>, BuilderType extends zzew<MessageType, BuilderType>> implements zzib {
    protected abstract BuilderType zza(MessageType messagetype);

    public abstract BuilderType zza(zzft zzftVar, zzgd zzgdVar) throws IOException;

    @Override // 
    /* renamed from: zzdn */
    public abstract BuilderType clone();

    public BuilderType zza(byte[] bArr, int r4, int r5, zzgd zzgdVar) throws zzhc {
        try {
            zzft zza = zzft.zza(bArr, 0, r5, false);
            zza(zza, zzgdVar);
            zza.zzaq(0);
            return this;
        } catch (zzhc e) {
            throw e;
        } catch (IOException e2) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 60 + "byte array".length());
            sb.append("Reading ");
            sb.append(name);
            sb.append(" from a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzib
    public final /* synthetic */ zzib zza(zzic zzicVar) {
        if (!zzgd().getClass().isInstance(zzicVar)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        return zza((zzew<MessageType, BuilderType>) ((zzet) zzicVar));
    }
}
