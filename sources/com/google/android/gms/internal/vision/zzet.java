package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzet;
import com.google.android.gms.internal.vision.zzew;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzet<MessageType extends zzet<MessageType, BuilderType>, BuilderType extends zzew<MessageType, BuilderType>> implements zzic {
    protected int zzri = 0;

    @Override // com.google.android.gms.internal.vision.zzic
    public final zzfh zzdk() {
        try {
            zzfp zzap = zzfh.zzap(zzgf());
            zzb(zzap.zzew());
            return zzap.zzev();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzgf()];
            zzga zze = zzga.zze(bArr);
            zzb(zze);
            zze.zzfh();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "byte array".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzdl() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzad(int r1) {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzgt.checkNotNull(iterable);
        if (iterable instanceof zzhj) {
            List<?> zzgx = ((zzhj) iterable).zzgx();
            zzhj zzhjVar = (zzhj) list;
            int size = list.size();
            for (Object obj : zzgx) {
                if (obj == null) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(zzhjVar.size() - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    for (int size2 = zzhjVar.size() - 1; size2 >= size; size2--) {
                        zzhjVar.remove(size2);
                    }
                    throw new NullPointerException(sb2);
                } else if (obj instanceof zzfh) {
                    zzhjVar.zzc((zzfh) obj);
                } else {
                    zzhjVar.add((String) obj);
                }
            }
        } else if (iterable instanceof zzio) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
            }
            int size3 = list.size();
            for (T t : iterable) {
                if (t == null) {
                    StringBuilder sb3 = new StringBuilder(37);
                    sb3.append("Element at index ");
                    sb3.append(list.size() - size3);
                    sb3.append(" is null.");
                    String sb4 = sb3.toString();
                    for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                        list.remove(size4);
                    }
                    throw new NullPointerException(sb4);
                }
                list.add(t);
            }
        }
    }
}
