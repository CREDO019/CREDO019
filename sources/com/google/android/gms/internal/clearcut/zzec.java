package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzec implements zzdm {
    private final String info;
    private final zzdo zzmn;
    private final zzed zzng;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzec(zzdo zzdoVar, String str, Object[] objArr) {
        this.zzmn = zzdoVar;
        this.info = str;
        this.zzng = new zzed(zzdoVar.getClass(), str, objArr);
    }

    public final int getFieldCount() {
        int r0;
        r0 = this.zzng.zznj;
        return r0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdm
    public final int zzcf() {
        int r0;
        r0 = this.zzng.flags;
        return (r0 & 1) == 1 ? zzcg.zzg.zzkl : zzcg.zzg.zzkm;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdm
    public final boolean zzcg() {
        int r0;
        r0 = this.zzng.flags;
        return (r0 & 2) == 2;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdm
    public final zzdo zzch() {
        return this.zzmn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzed zzco() {
        return this.zzng;
    }

    public final int zzcp() {
        int r0;
        r0 = this.zzng.zzmk;
        return r0;
    }

    public final int zzcq() {
        int r0;
        r0 = this.zzng.zzml;
        return r0;
    }

    public final int zzcr() {
        int r0;
        r0 = this.zzng.zznm;
        return r0;
    }

    public final int zzcs() {
        int r0;
        r0 = this.zzng.zzno;
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int[] zzct() {
        int[] r0;
        r0 = this.zzng.zzms;
        return r0;
    }

    public final int zzcu() {
        int r0;
        r0 = this.zzng.zznn;
        return r0;
    }

    public final int zzcv() {
        int r0;
        r0 = this.zzng.zzmm;
        return r0;
    }
}
