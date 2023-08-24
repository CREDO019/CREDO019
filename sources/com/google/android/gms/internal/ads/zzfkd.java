package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfkd extends zzfjz {
    private static final Pattern zza = Pattern.compile("^[a-zA-Z0-9 ]+$");
    private final zzfkb zzb;
    private final zzfka zzc;
    private zzflx zze;
    private zzfla zzf;
    private final List zzd = new ArrayList();
    private boolean zzg = false;
    private boolean zzh = false;
    private final String zzi = UUID.randomUUID().toString();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfkd(zzfka zzfkaVar, zzfkb zzfkbVar) {
        this.zzc = zzfkaVar;
        this.zzb = zzfkbVar;
        zzk(null);
        if (zzfkbVar.zzd() == zzfkc.HTML || zzfkbVar.zzd() == zzfkc.JAVASCRIPT) {
            this.zzf = new zzflb(zzfkbVar.zza());
        } else {
            this.zzf = new zzfld(zzfkbVar.zzi(), null);
        }
        this.zzf.zzj();
        zzfko.zza().zzd(this);
        zzfkt.zza().zzd(this.zzf.zza(), zzfkaVar.zzb());
    }

    private final void zzk(View view) {
        this.zze = new zzflx(view);
    }

    @Override // com.google.android.gms.internal.ads.zzfjz
    public final void zzb(View view, zzfkf zzfkfVar, String str) {
        zzfkq zzfkqVar;
        if (this.zzh) {
            return;
        }
        if (zza.matcher("Ad overlay").matches()) {
            Iterator it = this.zzd.iterator();
            while (true) {
                if (!it.hasNext()) {
                    zzfkqVar = null;
                    break;
                }
                zzfkqVar = (zzfkq) it.next();
                if (zzfkqVar.zzb().get() == view) {
                    break;
                }
            }
            if (zzfkqVar == null) {
                this.zzd.add(new zzfkq(view, zzfkfVar, "Ad overlay"));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("FriendlyObstruction has detailed reason that contains characters not in [a-z][A-Z][0-9] or space");
    }

    @Override // com.google.android.gms.internal.ads.zzfjz
    public final void zzc() {
        if (this.zzh) {
            return;
        }
        this.zze.clear();
        if (!this.zzh) {
            this.zzd.clear();
        }
        this.zzh = true;
        zzfkt.zza().zzc(this.zzf.zza());
        zzfko.zza().zze(this);
        this.zzf.zzc();
        this.zzf = null;
    }

    @Override // com.google.android.gms.internal.ads.zzfjz
    public final void zzd(View view) {
        if (this.zzh || zzf() == view) {
            return;
        }
        zzk(view);
        this.zzf.zzb();
        Collection<zzfkd> zzc = zzfko.zza().zzc();
        if (zzc == null || zzc.isEmpty()) {
            return;
        }
        for (zzfkd zzfkdVar : zzc) {
            if (zzfkdVar != this && zzfkdVar.zzf() == view) {
                zzfkdVar.zze.clear();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfjz
    public final void zze() {
        if (this.zzg) {
            return;
        }
        this.zzg = true;
        zzfko.zza().zzf(this);
        this.zzf.zzh(zzfku.zzb().zza());
        this.zzf.zzf(this, this.zzb);
    }

    public final View zzf() {
        return (View) this.zze.get();
    }

    public final zzfla zzg() {
        return this.zzf;
    }

    public final String zzh() {
        return this.zzi;
    }

    public final List zzi() {
        return this.zzd;
    }

    public final boolean zzj() {
        return this.zzg && !this.zzh;
    }
}
