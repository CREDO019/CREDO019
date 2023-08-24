package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdt {
    private final zzde zza;
    private final zzdn zzb;
    private final zzdr zzc;
    private final CopyOnWriteArraySet zzd;
    private final ArrayDeque zze;
    private final ArrayDeque zzf;
    private boolean zzg;

    public zzdt(Looper looper, zzde zzdeVar, zzdr zzdrVar) {
        this(new CopyOnWriteArraySet(), looper, zzdeVar, zzdrVar);
    }

    public static /* synthetic */ boolean zzg(zzdt zzdtVar, Message message) {
        Iterator it = zzdtVar.zzd.iterator();
        while (it.hasNext()) {
            ((zzds) it.next()).zzb(zzdtVar.zzc);
            if (zzdtVar.zzb.zzf(0)) {
                return true;
            }
        }
        return true;
    }

    public final zzdt zza(Looper looper, zzdr zzdrVar) {
        return new zzdt(this.zzd, looper, this.zza, zzdrVar);
    }

    public final void zzb(Object obj) {
        if (this.zzg) {
            return;
        }
        this.zzd.add(new zzds(obj));
    }

    public final void zzc() {
        if (this.zzf.isEmpty()) {
            return;
        }
        if (!this.zzb.zzf(0)) {
            zzdn zzdnVar = this.zzb;
            zzdnVar.zzj(zzdnVar.zza(0));
        }
        boolean isEmpty = this.zze.isEmpty();
        this.zze.addAll(this.zzf);
        this.zzf.clear();
        if (!isEmpty) {
            return;
        }
        while (!this.zze.isEmpty()) {
            ((Runnable) this.zze.peekFirst()).run();
            this.zze.removeFirst();
        }
    }

    public final void zzd(final int r4, final zzdq zzdqVar) {
        final CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet(this.zzd);
        this.zzf.add(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdp
            @Override // java.lang.Runnable
            public final void run() {
                CopyOnWriteArraySet copyOnWriteArraySet2 = copyOnWriteArraySet;
                int r1 = r4;
                zzdq zzdqVar2 = zzdqVar;
                Iterator it = copyOnWriteArraySet2.iterator();
                while (it.hasNext()) {
                    ((zzds) it.next()).zza(r1, zzdqVar2);
                }
            }
        });
    }

    public final void zze() {
        Iterator it = this.zzd.iterator();
        while (it.hasNext()) {
            ((zzds) it.next()).zzc(this.zzc);
        }
        this.zzd.clear();
        this.zzg = true;
    }

    public final void zzf(Object obj) {
        Iterator it = this.zzd.iterator();
        while (it.hasNext()) {
            zzds zzdsVar = (zzds) it.next();
            if (zzdsVar.zza.equals(obj)) {
                zzdsVar.zzc(this.zzc);
                this.zzd.remove(zzdsVar);
            }
        }
    }

    private zzdt(CopyOnWriteArraySet copyOnWriteArraySet, Looper looper, zzde zzdeVar, zzdr zzdrVar) {
        this.zza = zzdeVar;
        this.zzd = copyOnWriteArraySet;
        this.zzc = zzdrVar;
        this.zze = new ArrayDeque();
        this.zzf = new ArrayDeque();
        this.zzb = zzdeVar.zzb(looper, new Handler.Callback() { // from class: com.google.android.gms.internal.ads.zzdo
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                zzdt.zzg(zzdt.this, message);
                return true;
            }
        });
    }
}
