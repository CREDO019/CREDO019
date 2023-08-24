package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzarc extends zzarm {
    private List zzi;

    public zzarc(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "aOe/514coVpPRQegN4yl3ZJgMMZH4bY8vGVrQ08DnDuyKsRCp48F+Zjpb0HjBNAa", "MvgiGujNJnCbH7w8ay+vn+9KOY0pB5PpnwUR2iVU8Do=", zzamhVar, r12, 31);
        this.zzi = null;
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zze.zzV(-1L);
        this.zze.zzR(-1L);
        if (this.zzi == null) {
            this.zzi = (List) this.zzf.invoke(null, this.zzb.zzb());
        }
        List list = this.zzi;
        if (list == null || list.size() != 2) {
            return;
        }
        synchronized (this.zze) {
            this.zze.zzV(((Long) this.zzi.get(0)).longValue());
            this.zze.zzR(((Long) this.zzi.get(1)).longValue());
        }
    }
}
