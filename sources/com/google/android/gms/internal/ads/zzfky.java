package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfky implements zzfkw {
    private final zzfkw zza;

    public zzfky(zzfkw zzfkwVar) {
        this.zza = zzfkwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfkw
    public final JSONObject zza(View view) {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzfkw
    public final void zzb(View view, JSONObject jSONObject, zzfkv zzfkvVar, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        zzfko zza = zzfko.zza();
        if (zza != null) {
            Collection<zzfkd> zzb = zza.zzb();
            int size = zzb.size();
            IdentityHashMap identityHashMap = new IdentityHashMap(size + size + 3);
            for (zzfkd zzfkdVar : zzb) {
                View zzf = zzfkdVar.zzf();
                if (zzf != null && zzf.isAttachedToWindow() && zzf.isShown()) {
                    View view2 = zzf;
                    while (true) {
                        if (view2 != null) {
                            if (view2.getAlpha() != 0.0f) {
                                ViewParent parent = view2.getParent();
                                view2 = parent instanceof View ? (View) parent : null;
                            }
                        } else {
                            View rootView = zzf.getRootView();
                            if (rootView != null && !identityHashMap.containsKey(rootView)) {
                                identityHashMap.put(rootView, rootView);
                                float zza2 = zzflh.zza(rootView);
                                int size2 = arrayList.size();
                                while (size2 > 0) {
                                    int r4 = size2 - 1;
                                    if (zzflh.zza((View) arrayList.get(r4)) <= zza2) {
                                        break;
                                    }
                                    size2 = r4;
                                }
                                arrayList.add(size2, rootView);
                            }
                        }
                    }
                }
            }
        }
        int size3 = arrayList.size();
        for (int r0 = 0; r0 < size3; r0++) {
            zzfkvVar.zza((View) arrayList.get(r0), this.zza, jSONObject, z2);
        }
    }
}
