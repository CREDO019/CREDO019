package com.google.android.gms.internal.ads;

import android.graphics.Color;
import com.canhub.cropper.CropImage;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzblj extends zzblr {
    static final int zza;
    static final int zzb;
    private static final int zzc;
    private final String zzd;
    private final List zze = new ArrayList();
    private final List zzf = new ArrayList();
    private final int zzg;
    private final int zzh;
    private final int zzi;
    private final int zzj;
    private final int zzk;

    static {
        int rgb = Color.rgb(12, 174, 206);
        zzc = rgb;
        zza = Color.rgb((int) CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE, (int) CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE, (int) CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE);
        zzb = rgb;
    }

    public zzblj(String str, List list, Integer num, Integer num2, Integer num3, int r7, int r8, boolean z) {
        int r2;
        int r22;
        this.zzd = str;
        for (int r23 = 0; r23 < list.size(); r23++) {
            zzblm zzblmVar = (zzblm) list.get(r23);
            this.zze.add(zzblmVar);
            this.zzf.add(zzblmVar);
        }
        if (num != null) {
            r2 = num.intValue();
        } else {
            r2 = zza;
        }
        this.zzg = r2;
        if (num2 != null) {
            r22 = num2.intValue();
        } else {
            r22 = zzb;
        }
        this.zzh = r22;
        this.zzi = num3 != null ? num3.intValue() : 12;
        this.zzj = r7;
        this.zzk = r8;
    }

    public final int zzb() {
        return this.zzj;
    }

    public final int zzc() {
        return this.zzk;
    }

    public final int zzd() {
        return this.zzg;
    }

    public final int zze() {
        return this.zzh;
    }

    public final int zzf() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.ads.zzbls
    public final String zzg() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzbls
    public final List zzh() {
        return this.zzf;
    }

    public final List zzi() {
        return this.zze;
    }
}
