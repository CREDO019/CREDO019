package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzgmn;
import com.google.android.gms.internal.ads.zzgmo;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgmo<MessageType extends zzgmo<MessageType, BuilderType>, BuilderType extends zzgmn<MessageType, BuilderType>> implements zzgpx {
    protected int zza = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public static void zzat(Iterable iterable, List list) {
        zzgox.zze(iterable);
        if (list instanceof ArrayList) {
            ((ArrayList) list).ensureCapacity(list.size() + iterable.size());
        }
        int size = list.size();
        for (Object obj : iterable) {
            if (obj != null) {
                list.add(obj);
            } else {
                int size2 = list.size();
                String str = "Element at index " + (size2 - size) + " is null.";
                int size3 = list.size();
                while (true) {
                    size3--;
                    if (size3 < size) {
                        break;
                    }
                    list.remove(size3);
                }
                throw new NullPointerException(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzar() {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzgpx
    public final zzgnf zzas() {
        try {
            int zzax = zzax();
            zzgnf zzgnfVar = zzgnf.zzb;
            byte[] bArr = new byte[zzax];
            zzgnu zzG = zzgnu.zzG(bArr);
            zzaQ(zzG);
            zzG.zzI();
            return new zzgnb(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzau(int r1) {
        throw null;
    }

    public final void zzav(OutputStream outputStream) throws IOException {
        zzgnu zzH = zzgnu.zzH(outputStream, zzgnu.zzB(zzax()));
        zzaQ(zzH);
        zzH.zzN();
    }

    @Override // com.google.android.gms.internal.ads.zzgpx
    public final byte[] zzaw() {
        try {
            byte[] bArr = new byte[zzax()];
            zzgnu zzG = zzgnu.zzG(bArr);
            zzaQ(zzG);
            zzG.zzI();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
