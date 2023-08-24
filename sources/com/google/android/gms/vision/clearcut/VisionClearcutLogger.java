package com.google.android.gms.vision.clearcut;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.internal.vision.zzds;
import com.google.android.gms.internal.vision.zzea;
import com.google.android.gms.internal.vision.zzgd;
import com.google.android.gms.vision.C2148L;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class VisionClearcutLogger {
    private final ClearcutLogger zzbv;
    private boolean zzbw = true;

    public VisionClearcutLogger(Context context) {
        this.zzbv = new ClearcutLogger(context, "VISION", null);
    }

    public final void zzb(int r5, zzea.zzo zzoVar) {
        byte[] byteArray = zzoVar.toByteArray();
        if (r5 < 0 || r5 > 3) {
            C2148L.m1078i("Illegal event code: %d", Integer.valueOf(r5));
            return;
        }
        try {
            if (this.zzbw) {
                this.zzbv.newEvent(byteArray).setEventCode(r5).log();
                return;
            }
            zzea.zzo.zza zzdi = zzea.zzo.zzdi();
            try {
                zzdi.zza(byteArray, 0, byteArray.length, zzgd.zzfm());
                C2148L.m1080e("Would have logged:\n%s", zzdi.toString());
            } catch (Exception e) {
                C2148L.m1079e(e, "Parsing error", new Object[0]);
            }
        } catch (Exception e2) {
            zzds.zza(e2);
            C2148L.m1079e(e2, "Failed to log", new Object[0]);
        }
    }
}
