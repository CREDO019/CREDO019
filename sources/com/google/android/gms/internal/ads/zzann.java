package com.google.android.gms.internal.ads;

import androidx.exifinterface.media.ExifInterface;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzann extends zzanl {
    public String zza;
    public long zzb;
    public String zzc;
    public String zzd;
    public String zze;

    public zzann() {
        this.zza = ExifInterface.LONGITUDE_EAST;
        this.zzb = -1L;
        this.zzc = ExifInterface.LONGITUDE_EAST;
        this.zzd = ExifInterface.LONGITUDE_EAST;
        this.zze = ExifInterface.LONGITUDE_EAST;
    }

    @Override // com.google.android.gms.internal.ads.zzanl
    protected final HashMap zzb() {
        HashMap hashMap = new HashMap();
        hashMap.put(0, this.zza);
        hashMap.put(4, this.zze);
        hashMap.put(3, this.zzd);
        hashMap.put(2, this.zzc);
        hashMap.put(1, Long.valueOf(this.zzb));
        return hashMap;
    }

    public zzann(String str) {
        String str2 = ExifInterface.LONGITUDE_EAST;
        this.zza = ExifInterface.LONGITUDE_EAST;
        this.zzb = -1L;
        this.zzc = ExifInterface.LONGITUDE_EAST;
        this.zzd = ExifInterface.LONGITUDE_EAST;
        this.zze = ExifInterface.LONGITUDE_EAST;
        HashMap zza = zza(str);
        if (zza != null) {
            this.zza = zza.get(0) == null ? ExifInterface.LONGITUDE_EAST : (String) zza.get(0);
            this.zzb = zza.get(1) != null ? ((Long) zza.get(1)).longValue() : -1L;
            this.zzc = zza.get(2) == null ? ExifInterface.LONGITUDE_EAST : (String) zza.get(2);
            this.zzd = zza.get(3) == null ? ExifInterface.LONGITUDE_EAST : (String) zza.get(3);
            this.zze = zza.get(4) != null ? (String) zza.get(4) : str2;
        }
    }
}
