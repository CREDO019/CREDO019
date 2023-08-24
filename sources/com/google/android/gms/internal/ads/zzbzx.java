package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.ads.query.UpdateClickUrlCallback;
import com.google.android.gms.ads.query.UpdateImpressionUrlsCallback;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbzx {
    @Nonnull
    private final View zza;
    private final Map zzb;
    private final zzcfe zzc;

    public zzbzx(zzbzw zzbzwVar) {
        View view;
        Map map;
        View view2;
        view = zzbzwVar.zza;
        this.zza = view;
        map = zzbzwVar.zzb;
        this.zzb = map;
        view2 = zzbzwVar.zza;
        zzcfe zza = zzbzr.zza(view2.getContext());
        this.zzc = zza;
        if (zza == null || map.isEmpty()) {
            return;
        }
        try {
            zza.zzf(new zzbzy(ObjectWrapper.wrap(view).asBinder(), ObjectWrapper.wrap(map).asBinder()));
        } catch (RemoteException unused) {
            zzcgn.zzg("Failed to call remote method.");
        }
    }

    public final void zza(List list) {
        if (list == null || list.isEmpty()) {
            zzcgn.zzj("No click urls were passed to recordClick");
            return;
        }
        if (this.zzc == null) {
            zzcgn.zzj("Failed to get internal reporting info generator in recordClick.");
        }
        try {
            this.zzc.zzg(list, ObjectWrapper.wrap(this.zza), new zzbzv(this, list));
        } catch (RemoteException e) {
            zzcgn.zzg("RemoteException recording click: ".concat(e.toString()));
        }
    }

    public final void zzb(List list) {
        if (list == null || list.isEmpty()) {
            zzcgn.zzj("No impression urls were passed to recordImpression");
            return;
        }
        zzcfe zzcfeVar = this.zzc;
        if (zzcfeVar != null) {
            try {
                zzcfeVar.zzh(list, ObjectWrapper.wrap(this.zza), new zzbzu(this, list));
                return;
            } catch (RemoteException e) {
                zzcgn.zzg("RemoteException recording impression urls: ".concat(e.toString()));
                return;
            }
        }
        zzcgn.zzj("Failed to get internal reporting info generator from recordImpression.");
    }

    public final void zzc(MotionEvent motionEvent) {
        zzcfe zzcfeVar = this.zzc;
        if (zzcfeVar != null) {
            try {
                zzcfeVar.zzj(ObjectWrapper.wrap(motionEvent));
                return;
            } catch (RemoteException unused) {
                zzcgn.zzg("Failed to call remote method.");
                return;
            }
        }
        zzcgn.zze("Failed to get internal reporting info generator.");
    }

    public final void zzd(Uri uri, UpdateClickUrlCallback updateClickUrlCallback) {
        if (this.zzc == null) {
            updateClickUrlCallback.onFailure("Failed to get internal reporting info generator.");
        }
        try {
            this.zzc.zzk(new ArrayList(Arrays.asList(uri)), ObjectWrapper.wrap(this.zza), new zzbzt(this, updateClickUrlCallback));
        } catch (RemoteException e) {
            updateClickUrlCallback.onFailure("Internal error: ".concat(e.toString()));
        }
    }

    public final void zze(List list, UpdateImpressionUrlsCallback updateImpressionUrlsCallback) {
        if (this.zzc == null) {
            updateImpressionUrlsCallback.onFailure("Failed to get internal reporting info generator.");
        }
        try {
            this.zzc.zzl(list, ObjectWrapper.wrap(this.zza), new zzbzs(this, updateImpressionUrlsCallback));
        } catch (RemoteException e) {
            updateImpressionUrlsCallback.onFailure("Internal error: ".concat(e.toString()));
        }
    }
}
