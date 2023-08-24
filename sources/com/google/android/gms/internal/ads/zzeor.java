package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeor implements zzeun {
    private final zzfyy zza;
    private final zzfyy zzb;
    private final Context zzc;
    private final zzfdn zzd;
    private final View zze;

    public zzeor(zzfyy zzfyyVar, zzfyy zzfyyVar2, Context context, zzfdn zzfdnVar, ViewGroup viewGroup) {
        this.zza = zzfyyVar;
        this.zzb = zzfyyVar2;
        this.zzc = context;
        this.zzd = zzfdnVar;
        this.zze = viewGroup;
    }

    private final List zze() {
        ArrayList arrayList = new ArrayList();
        View view = this.zze;
        while (view != null) {
            ViewParent parent = view.getParent();
            if (parent == null) {
                break;
            }
            int indexOfChild = parent instanceof ViewGroup ? ((ViewGroup) parent).indexOfChild(view) : -1;
            Bundle bundle = new Bundle();
            bundle.putString(SessionDescription.ATTR_TYPE, parent.getClass().getName());
            bundle.putInt("index_of_child", indexOfChild);
            arrayList.add(bundle);
            if (!(parent instanceof View)) {
                break;
            }
            view = (View) parent;
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 3;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        zzbiy.zzc(this.zzc);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziz)).booleanValue()) {
            return this.zzb.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeop
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return zzeor.this.zzc();
                }
            });
        }
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeoq
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeor.this.zzd();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzeos zzc() throws Exception {
        return new zzeos(this.zzc, this.zzd.zze, zze());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzeos zzd() throws Exception {
        return new zzeos(this.zzc, this.zzd.zze, zze());
    }
}
