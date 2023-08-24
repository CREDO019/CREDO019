package com.google.android.gms.internal.ads;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfkz implements zzfkw {
    private final int[] zza = new int[2];

    @Override // com.google.android.gms.internal.ads.zzfkw
    public final JSONObject zza(View view) {
        if (view == null) {
            return zzfle.zza(0, 0, 0, 0);
        }
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.zza);
        int[] r5 = this.zza;
        return zzfle.zza(r5[0], r5[1], width, height);
    }

    @Override // com.google.android.gms.internal.ads.zzfkw
    public final void zzb(View view, JSONObject jSONObject, zzfkv zzfkvVar, boolean z, boolean z2) {
        int r6;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!z || Build.VERSION.SDK_INT < 21) {
                for (int r0 = 0; r0 < viewGroup.getChildCount(); r0++) {
                    zzfkvVar.zza(viewGroup.getChildAt(r0), this, jSONObject, z2);
                }
                return;
            }
            HashMap hashMap = new HashMap();
            for (int r1 = 0; r1 < viewGroup.getChildCount(); r1++) {
                View childAt = viewGroup.getChildAt(r1);
                ArrayList arrayList = (ArrayList) hashMap.get(Float.valueOf(childAt.getZ()));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    hashMap.put(Float.valueOf(childAt.getZ()), arrayList);
                }
                arrayList.add(childAt);
            }
            ArrayList arrayList2 = new ArrayList(hashMap.keySet());
            Collections.sort(arrayList2);
            int size = arrayList2.size();
            int r2 = 0;
            while (r2 < size) {
                ArrayList arrayList3 = (ArrayList) hashMap.get((Float) arrayList2.get(r2));
                int size2 = arrayList3.size();
                int r5 = 0;
                while (true) {
                    r6 = r2 + 1;
                    if (r5 < size2) {
                        zzfkvVar.zza((View) arrayList3.get(r5), this, jSONObject, z2);
                        r5++;
                    }
                }
                r2 = r6;
            }
        }
    }
}
