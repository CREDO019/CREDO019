package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgat {
    private final Class zza;
    private zzgau zzc;
    private ConcurrentMap zzb = new ConcurrentHashMap();
    private final zzgfc zzd = zzgfc.zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgat(Class cls, zzgas zzgasVar) {
        this.zza = cls;
    }

    private final zzgat zzd(Object obj, zzgjs zzgjsVar, boolean z) throws GeneralSecurityException {
        byte[] array;
        if (this.zzb == null) {
            throw new IllegalStateException("addPrimitive cannot be called after build");
        }
        if (zzgjsVar.zzi() == 3) {
            zzgay zzgayVar = new zzgay(zzgjsVar.zzc().zzf(), zzgjsVar.zzj(), null);
            int zzj = zzgjsVar.zzj() - 2;
            if (zzj == 1) {
                array = ByteBuffer.allocate(5).put((byte) 1).putInt(zzgjsVar.zza()).array();
            } else {
                if (zzj != 2) {
                    if (zzj == 3) {
                        array = zzfzx.zza;
                    } else if (zzj != 4) {
                        throw new GeneralSecurityException("unknown output prefix type");
                    }
                }
                array = ByteBuffer.allocate(5).put((byte) 0).putInt(zzgjsVar.zza()).array();
            }
            zzgau zzgauVar = new zzgau(obj, array, zzgjsVar.zzi(), zzgjsVar.zzj(), zzgjsVar.zza(), zzgayVar);
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzgauVar);
            zzgaw zzgawVar = new zzgaw(zzgauVar.zzb(), null);
            List list = (List) this.zzb.put(zzgawVar, Collections.unmodifiableList(arrayList));
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(list);
                arrayList2.add(zzgauVar);
                this.zzb.put(zzgawVar, Collections.unmodifiableList(arrayList2));
            }
            if (z) {
                if (this.zzc == null) {
                    this.zzc = zzgauVar;
                } else {
                    throw new IllegalStateException("you cannot set two primary primitives");
                }
            }
            return this;
        }
        throw new GeneralSecurityException("only ENABLED key is allowed");
    }

    public final zzgat zza(Object obj, zzgjs zzgjsVar) throws GeneralSecurityException {
        zzd(obj, zzgjsVar, true);
        return this;
    }

    public final zzgat zzb(Object obj, zzgjs zzgjsVar) throws GeneralSecurityException {
        zzd(obj, zzgjsVar, false);
        return this;
    }

    public final zzgba zzc() throws GeneralSecurityException {
        ConcurrentMap concurrentMap = this.zzb;
        if (concurrentMap != null) {
            zzgba zzgbaVar = new zzgba(concurrentMap, this.zzc, this.zzd, this.zza, null);
            this.zzb = null;
            return zzgbaVar;
        }
        throw new IllegalStateException("build cannot be called twice");
    }
}
